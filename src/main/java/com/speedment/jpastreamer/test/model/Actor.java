package com.speedment.jpastreamer.test.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "actor", schema = "sakila")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false, updatable = false, columnDefinition = "smallint(5)")
    private Integer actorId;

    @ManyToMany(mappedBy = "actors")
    List<Film> films = new ArrayList<>();

    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(45)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(45)")
    private String lastName;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(actorId, actor.actorId) &&
                Objects.equals(films, actor.films) &&
                Objects.equals(firstName, actor.firstName) &&
                Objects.equals(lastName, actor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, films, firstName, lastName);
    }
}
