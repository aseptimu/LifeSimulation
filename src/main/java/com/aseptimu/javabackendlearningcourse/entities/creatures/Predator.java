package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Predator extends Creatures{
    public Predator(Coordinate coordinate, int speed, int hp) {
        super(coordinate, "\uD83D\uDC3A", speed, hp);
    }

    @Override
    protected void makeMove() {

    }
}
