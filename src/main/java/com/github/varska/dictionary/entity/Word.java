package com.github.varska.dictionary.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(name = "definition", length = 1024)
    private String definition;

    @Column(name = "example", length = 1024)
    private String example;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // one user many descriptions, fetch - every description получает сведения о авторе
    private User user;

    private LocalDate localDate;

    public Word() {
    }

    public Word(String title, String definition, String example){
        this.title = title;
        this.definition = definition;
        this.example = example;
    }

    public Word(String title, String definition, String example, User user){
        this.title = title;
        this.definition = definition;
        this.example = example;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
