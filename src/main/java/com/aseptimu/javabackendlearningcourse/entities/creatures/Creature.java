package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;
import com.aseptimu.javabackendlearningcourse.pathfinding.PathFinder;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;
    protected Field field;

    protected PathFinder pathFinder;

    public Creature(Coordinate coordinate,
                    String view,
                    int speed,
                    int hp,
                    Field field) {
        super(coordinate, view);
        this.speed = speed;
        this.hp = hp;
        this.field = field;
        pathFinder = new PathFinder(field);
    }

    public abstract Creature makeMove();
}
