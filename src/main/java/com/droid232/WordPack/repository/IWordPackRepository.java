package com.droid232.WordPack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.droid232.WordPack.dto.WordPackDetailsResponse;
import com.droid232.WordPack.entity.WordPack;

@Repository
public interface IWordPackRepository extends JpaRepository<WordPack, Long> {

    @Query("SELECT new com.droid232.WordPack.dto.WordPackDetailsResponse(wp.wordPackId, wp.title, wp.description, wp.isPublic) FROM WordPack wp WHERE wp.user.id = :userId")
    List<WordPackDetailsResponse> findByUserId(@Param("userId") Long id);

    @Query("SELECT new com.droid232.WordPack.dto.WordPackDetailsResponse(wp.wordPackId, wp.title, wp.description, wp.isPublic) FROM WordPack wp WHERE wp.isPublic = true")
    List<WordPackDetailsResponse> findByIsPublic();
}
