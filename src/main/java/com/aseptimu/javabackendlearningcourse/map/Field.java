package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Creature;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

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
        Queue<Herbivore> herbivores = new ArrayDeque<>();
        Queue<Predator> predators = new ArrayDeque<>();
        for (Entity entity : entities.values()) {
            if (entity instanceof Herbivore) {
                herbivores.add((Herbivore) entity);
            } else if (entity instanceof Predator) {
                predators.add((Predator) entity);
            }
        }
        while (!herbivores.isEmpty()) {
            Creature creature = herbivores.remove();
            creature.makeMove();
            entities.put(creature.getCoordinate(), creature);
        }
        while (!predators.isEmpty()) {
            Creature creature = predators.remove();
            creature.makeMove();
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
