package com.droid232.WordPack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.droid232.WordPack.entity.Word;

@Repository
public interface IWordRepository extends JpaRepository<Word, Long> {

    @Query("SELECT w FROM Word w WHERE w.wordPack.id = :wordPackId")
    public List<Word> findByWordPackId(@Param("wordPackId") Long wordPackId);
}
