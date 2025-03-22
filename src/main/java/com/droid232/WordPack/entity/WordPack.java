package com.droid232.WordPack.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class WordPack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wordPackId;

    @Column(length = 300)
    private String description;

    @Column(length = 40)
    private String title;

    @Column(nullable = false)
    private Boolean isPublic = false;

    @OneToMany(mappedBy = "wordPack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Word> words;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
