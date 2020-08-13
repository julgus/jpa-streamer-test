package com.speedment.jpastreamer.test.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "language", schema = "sakila")
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", nullable = false, updatable = false, columnDefinition = "tinyint(5)")
    private Integer languageId;

    @Column(name = "name", nullable = false, columnDefinition = "char(20)")
    private String name;

    @OneToMany(mappedBy = "language")
    private Set<Film> films;

    @ManyToMany
    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer language_id) {
        this.languageId = language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(languageId, language.languageId) &&
                Objects.equals(name, language.name) &&
                Objects.equals(films, language.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, name, films);
    }
}
