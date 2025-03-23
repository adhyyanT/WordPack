package com.droid232.WordPack.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.droid232.WordPack.dto.WordPackDetailsResponse;
import com.droid232.WordPack.dto.WordPackRequestDto;
import com.droid232.WordPack.dto.WordPackResponse;
import com.droid232.WordPack.dto.WordRequestDto;
import com.droid232.WordPack.entity.User;
import com.droid232.WordPack.entity.WordPack;
import com.droid232.WordPack.error.ResourceNotFoundException;
import com.droid232.WordPack.error.UnAuthorizedException;
import com.droid232.WordPack.repository.IWordPackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordPackService {

    private final WordService wordService;
    private final IWordPackRepository wordPackRepository;

    public WordPackResponse createWordPack(WordPackRequestDto wordPackRequestDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var unSavedWordPack = WordPackRequestDto.toWordPack(wordPackRequestDto);
        unSavedWordPack.setUser(user);
        var savedWordPack = wordPackRepository.save(unSavedWordPack);

        var words = wordPackRequestDto.getWords()
                .stream()
                .map(word -> {
                    var w = WordRequestDto.tWord(word);
                    w.setWordPack(savedWordPack);
                    return w;
                })
                .collect(Collectors.toList());
        var savedWords = wordService.createWords(words, savedWordPack.getWordPackId());
        var wordPackResponse = WordPack.toWordPackResponse(savedWordPack);
        wordPackResponse.setWords(savedWords);
        return wordPackResponse;
    }

    public WordPackResponse getWordPackDetails(Long wordPackId) {
        var wordPack = wordPackRepository.findById(wordPackId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return WordPack.toWordPackResponse(wordPack);
    }

    public List<WordPackDetailsResponse> getMyWordPacks(Long userId) {
        var wordPacks = wordPackRepository.findByUserId(userId);

        return wordPacks;
    }

    public List<WordPackDetailsResponse> getPublicWordPacks() {
        var wordPacks = wordPackRepository.findByIsPublic();
        return wordPacks;
    }

    public void addToLibrary(Long wordPackId) {
        var wordPack = wordPackRepository.findById(wordPackId)
                .orElseThrow(() -> new ResourceNotFoundException("Word pack not found"));
        if (!wordPack.getIsPublic())
            throw new UnAuthorizedException("Cannot add this word pack to library");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (wordPack.getUser().getId().equals(user.getId()))
            throw new UnAuthorizedException("Cannot add this word pack to library");
        var newWordPack = WordPack.builder()
                .description(wordPack.getDescription())
                .title(wordPack.getTitle())
                .user(user)
                .build();
        var savedWordPack = wordPackRepository.save(newWordPack);
        var newWords = wordPack.getWords().stream().map((w) -> {
            w.setWordPack(savedWordPack);
            w.setWordId(null);
            return w;
        }).collect(Collectors.toList());
        wordService.createWords(newWords, savedWordPack.getWordPackId());
    }

    public WordPackResponse updateWordPack(WordPackRequestDto wordPack, Long wordPackId) {
        var existingWordPack = wordPackRepository.findById(wordPackId)
                .orElseThrow(() -> new ResourceNotFoundException("Word Pack was not found"));
        existingWordPack.setDescription(wordPack.getDescription());
        existingWordPack.setTitle(wordPack.getTitle());
        existingWordPack.setIsPublic(wordPack.getIsPublic());
        var savedWordPack = wordPackRepository.save(existingWordPack);

        return WordPack.toWordPackResponse(savedWordPack);
    }
}
