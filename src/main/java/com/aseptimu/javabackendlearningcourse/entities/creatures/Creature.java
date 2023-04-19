package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;
import com.aseptimu.javabackendlearningcourse.map.PathFinder;

public abstract class Creature extends Entity {
    protected int speed;

    protected Field field;

    protected PathFinder pathFinder;

    public Creature(Coordinate coordinate,
                    String view,
                    int speed,
                    Field field) {
        super(coordinate, view);
        this.speed = speed;
        this.field = field;
        pathFinder = new PathFinder(field);
    }



    public abstract void makeMove();


}
