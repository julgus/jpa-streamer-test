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
                .filter(Film$.length.between(100, 120))
                .forEach(System.out::println);

        jpaStreamer.close();


    }
}
