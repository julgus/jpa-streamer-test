package com.speedment.jpastreamer.test;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.test.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {

    final static JPAStreamer jpaStreamer = JPAStreamer.createJPAStreamerBuilder("sakila")
            .build();

    public static void main(String[] args) {

        /* ONE-TO-MANY EXAMPLE */
        Map<Language, Set<Film>> languageFilmMap = jpaStreamer.stream(Language.class)
                .limit(10)
                .collect(
                        toMap(Function.identity(),
                                Language::getFilms)
                );

        languageFilmMap
                .forEach(
                        (k, v) -> System.out.format("%s: %s\n", k.getName(),
                                v.stream().map(f -> f.getTitle()).collect(toList())));

        /* MANY-TO-ONE EXAMPLE */
        Map<Film, Language> languageMap = jpaStreamer.stream(Film.class)
                .filter(Film$.rating.equal("PG-13")).limit(5)
                .collect(
                        Collectors.toMap(Function.identity(),
                                Film::getLanguage
                        )
                );

        languageMap
                .forEach(
                        (k, v) -> System.out.format("%s: %s\n", k.getTitle(), v.getName()));

        /* MANY-TO-MANY EXAMPLE */
        Map<Actor, List<Film>> filmography = jpaStreamer.stream(Actor.class)
                .collect(
                        Collectors.toMap(Function.identity(),
                                Actor::getFilms
                        )
                );

        filmography
                .forEach(
                        (k, v) -> System.out.format("%s: %s\n", k.getFirstName() + " " + k.getLastName(),
                                v));

        /* PIVOT EXAMPLE */
        Map<Actor, Map<String, Long>> pivot = jpaStreamer.stream(Actor.class)
                .collect(
                        groupingBy(Function.identity(),
                                Collectors.flatMapping(a -> a.getFilms().stream(),
                                        groupingBy(Film::getRating, counting())
                                )
                        )
                );

    }

    /* TRANSACTIONS EXAMPLE */
    private static void updateRentalRates() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            jpaStreamer.stream(Film.class)
                    .filter(Film$.rating.equal("R"))
                    .forEach(f ->
                        f.setRentalRate(f.getRentalRate() + 1)
                    );
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        }

    }


}
