package com.droid232.WordPack.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.droid232.WordPack.dto.WordRequestDto;
import com.droid232.WordPack.dto.WordResponseDto;
import com.droid232.WordPack.entity.User;
import com.droid232.WordPack.entity.Word;
import com.droid232.WordPack.error.ResourceNotFoundException;
import com.droid232.WordPack.error.UnAuthorizedException;
import com.droid232.WordPack.repository.IWordPackRepository;
import com.droid232.WordPack.repository.IWordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordService {

    private final IWordRepository wordRepository;
    private final JdbcTemplate jdbcTemplate;
    private final IWordPackRepository wordPackRepository;

    public List<WordResponseDto> createWords(List<Word> words, Long wordPackId) {

        jdbcTemplate.batchUpdate(
                "INSERT INTO word (title, description, word_pack_id) VALUES (?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        var word = words.get(i);
                        ps.setString(1, word.getTitle());
                        ps.setString(2, word.getDescription());
                        ps.setLong(3, wordPackId);
                    }

                    @Override
                    public int getBatchSize() {
                        return words.size();
                    }
                });

        return getWords(wordPackId);
    }

    public List<WordResponseDto> getWords(Long wordPackId) {
        return wordRepository.findByWordPackId(wordPackId).stream().map(Word::toWordResponseDto)
                .collect(Collectors.toList());
    }

    public WordResponseDto createWord(WordRequestDto wordRequestDto, Long wordPackId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var wordPack = wordPackRepository.findById(wordPackId)
                .orElseThrow(() -> new ResourceNotFoundException("Word pack was not found"));
        if (!user.getId().equals(wordPack.getUser().getId())) {
            throw new UnAuthorizedException("The word pack does not belong to user");
        }
        var word = WordRequestDto.tWord(wordRequestDto);
        word.setWordPack(wordPack);
        return Word.toWordResponseDto(wordRepository.save(word));
    }

    public void deleteWord(Long wordId, Long wordPackId) {
        var wordPack = wordPackRepository.findById(wordPackId)
                .orElseThrow(() -> new ResourceNotFoundException("Word pack was not found"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getId().equals(wordPack.getUser().getId())) {
            throw new UnAuthorizedException("The word pack does not belong to user");
        }
        wordRepository.deleteById(wordId);
    }

    public WordResponseDto updateWord(WordRequestDto wordRequestDto, Long wordPackId, Long wordId) {
        var wordPack = wordPackRepository.findById(wordPackId)
                .orElseThrow(() -> new ResourceNotFoundException("Word pack was not found"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getId().equals(wordPack.getUser().getId())) {
            throw new UnAuthorizedException("The word pack does not belong to user");
        }

        var word = wordRepository.findById(wordId)
                .orElseThrow(() -> new ResourceNotFoundException("Word not found"));
        word.setTitle(wordRequestDto.getTitle());
        word.setDescription(wordRequestDto.getDescription());
        return Word.toWordResponseDto(wordRepository.save(word));
    }
}
