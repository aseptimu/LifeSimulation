package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Herbivore extends Creatures{
    public Herbivore(Coordinate coordinate, int speed, int hp) {
        super(coordinate, "\uD83E\uDD8C", speed, hp);
    }

    @Override
    protected void makeMove() {

    }
}
