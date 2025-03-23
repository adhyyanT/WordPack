package com.droid232.WordPack.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.droid232.WordPack.dto.WordResponseDto;
import com.droid232.WordPack.entity.Word;
import com.droid232.WordPack.repository.IWordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordService {

    private final IWordRepository wordRepository;
    private final JdbcTemplate jdbcTemplate;

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
}
