package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.Simulation;
import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Creature;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Field {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private final HashMap<Coordinate, Entity> entities;
    private final EntitiesCreator entitiesCreator;

    public Field() {
        entitiesCreator = new EntitiesCreator();
        entities = new HashMap<>();
    }

    public void createEntities(int herbivores, int predators, double density) {
        entitiesCreator.initEntities(herbivores, predators, density);
        entitiesCreator.generateDefaultEntities(this);
    }
    public void nextTurn() {
        List<Herbivore> herbivores = getEntitiesByType(Herbivore.class);
        List<Predator> predators = getEntitiesByType(Predator.class);
        for (Herbivore herbivore : herbivores) {
            herbivore.makeMove();
        }
        for (Predator predator : predators) {
            predator.makeMove();
        }
        if (Grass.getGrassCount() < Herbivore.getNumberOfHerbivores() / 2 + 1
                && Simulation.getMoveCount() % 2 == 0) {
            int generate = Herbivore.getNumberOfHerbivores() / 4 + 1;
            for (int i = 0; i < generate; i++) {
                entitiesCreator.generateEntity(Grass.class, this);
            }
        }
    }

    private <T extends Creature> List<T> getEntitiesByType(Class<T> predatorClass) {
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
