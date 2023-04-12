package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Field {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private HashMap<Coordinate, Entity> entities;

    public Field() {
        entities = new HashMap<>();
        EntitiesCreator entitiesCreator = new EntitiesCreator();
        entitiesCreator.generateEntities(this);
    }

    public void createEntities(int herbivores, int predators, double density) {
        EntitiesCreator entitiesCreator = new EntitiesCreator();
        entitiesCreator.initEntities(herbivores, predators, density);
        entities = entitiesCreator.generateEntities(this);
    }
    public void nextTurn() {
        List<Creature> movingCreatures = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entity instanceof Creature) {
                Creature creature = ((Creature) entity).makeMove();
                movingCreatures.add(creature);
            }
        }
        for (Creature creature : movingCreatures) {
            entities.put(creature.getCoordinate(), creature);
        }
    }
    public Entity getEntityByCoordinate(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public void removeEntity(Coordinate coordinate) {
        entities.put(coordinate, null);
    }
}
