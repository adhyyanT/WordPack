package com.droid232.WordPack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wordId;

    @Column(length = 300)
    private String description;

    @Column(length = 20)
    private String title;

    @ManyToOne
    @JoinColumn(name = "word_pack_id")
    private WordPack wordPack;
}
