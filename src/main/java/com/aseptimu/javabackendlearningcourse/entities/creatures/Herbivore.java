package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Herbivore extends Creatures{
    private static final int HP = 100;
    private static final int SPEED = 1;
    private static final String VIEW = "\uD83E\uDD8C";
    public Herbivore(Coordinate coordinate) {
        super(coordinate, VIEW, SPEED, HP);
    }

    @Override
    protected void makeMove() {

    }
}
