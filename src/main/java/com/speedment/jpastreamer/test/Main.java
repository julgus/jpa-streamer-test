package com.speedment.jpastreamer.test;

import com.speedment.jpastreamer.application.JpaStreamer;
import com.speedment.jpastreamer.test.model.Film;
import com.speedment.jpastreamer.test.model.Film$;

public class Main {

    public static void main(String[] args) {

        // Rework to JpaStreamer.create...

        //JpaStreamer.create(String persistenceName)
        //JpaStreamer.create()

        JpaStreamer jpaStreamer = JpaStreamer.createJpaStreamerBuilder("sakila")
                .build();

        jpaStreamer.stream(Film.class)
                .forEach(System.out::println);


        jpaStreamer.stream(Film.class)
                .filter(Film$.length.between(100, 120))
                .forEach(System.out::println);

        jpaStreamer.stream(Film.class)
                // Composing filters with "and"/"or"
                .filter(Film$.rating.equal("G").or(Film$.length.greaterOrEqual(140)))
                .forEach(System.out::println);


        jpaStreamer.stream(Film.class)
                .filter(Film$.rating.equal("G"))
                .sorted(Film$.length.reversed().thenComparing(Film$.title.comparator()))
                .skip(10)
                .limit(5)
                .forEach(System.out::println);

        long count = jpaStreamer.stream(Film.class)
                .filter(Film$.rating.equal("G"))
                .count();

        System.out.println("There are "+count+" films with rating G");

        jpaStreamer.close();


    }
}
