package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public abstract class Creatures extends Entity {
    private int speed;
    private int hp;

    public Creatures(Coordinate coordinate, String view, int speed, int hp) {
        super(coordinate, view);
        this.speed = speed;
        this.hp = hp;
    }

    public abstract void makeMove();
}
