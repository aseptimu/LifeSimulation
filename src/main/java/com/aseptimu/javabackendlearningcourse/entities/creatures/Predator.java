package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Predator extends Creatures {
    private static final int HP = 100;
    private static final int SPEED = 1;
    private static final String VIEW = "\uD83D\uDC3A";
    public Predator(Coordinate coordinate) {
        super(coordinate, VIEW, SPEED, HP);
    }

    @Override
    protected void makeMove() {

    }
}
