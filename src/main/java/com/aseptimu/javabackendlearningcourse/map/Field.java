package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Field {
    public final static int FIELD_SIZE = Field.HEIGHT * Field.WIDTH;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private final HashMap<Coordinate, Entity> entities;


    public Field() {
        entities = new HashMap<>();
    }

    public <T extends Creature> List<T> getEntitiesByType(Class<T> predatorClass) {
        List<T> entity = new ArrayList<>();
        for (Object en : entities.values()) {
            if (predatorClass.isInstance(en)) {
                entity.add(predatorClass.cast(en));
            }
        }
        return entity;
    }

    public Entity getEntityByCoordinate(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public void removeEntity(Coordinate coordinate) {
        entities.put(coordinate, null);
    }

    public void addEntity(Coordinate coordinate, Entity entity) {
        entities.put(coordinate, entity);
    }

    public boolean isCoordinateEmpty(Coordinate coordinate) {
        return entities.get(coordinate) != null;
    }
}
