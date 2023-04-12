package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;
    protected Field field;

    public Creature(Coordinate coordinate,
                    String view,
                    int speed,
                    int hp,
                    Field field) {
        super(coordinate, view);
        this.speed = speed;
        this.hp = hp;
        this.field = field;
    }

    public abstract Creature makeMove();
}
