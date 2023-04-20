package com.aseptimu.javabackendlearningcourse.actions;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.Random;

public abstract class EntityCreator extends Action {
    protected int amount;
    protected boolean isInitialized;

    public EntityCreator() {}

    protected boolean initEntity(int amount) {
        this.amount = amount;
        isInitialized = true;
        return true;
    }

    public boolean isInitialized() {
        return !isInitialized;
    }

    protected Coordinate generateCoordinate(Field field) {
        Random random = new Random();
        Coordinate coordinate;
        do {
            coordinate = new Coordinate(random.nextInt(Field.HEIGHT), random.nextInt(Field.WIDTH));
        } while (field.isCoordinateEmpty(coordinate));
        return coordinate;
    }
}
