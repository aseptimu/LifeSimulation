package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.Simulation;
import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
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
    private final EntitiesCreator entitiesCreator;

    public Field() {
        entitiesCreator = new EntitiesCreator();
    }

    public void createEntities(int herbivores, int predators, double density) {
        entitiesCreator.initEntities(herbivores, predators, density);
        entities = entitiesCreator.generateDefaultEntities(this);
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
            creature = creature.makeMove();
            if (creature != null) {
                entities.put(creature.getCoordinate(), creature);
            }
        }
        while (!predators.isEmpty()) {
            Creature creature = predators.remove();
            creature = creature.makeMove();
            if (creature != null) {
                entities.put(creature.getCoordinate(), creature);
            }
        }
        if (Grass.getGrassCount() < Herbivore.getNumberOfHerbivores() / 2 + 1
                && Simulation.getMoveCount() % 2 == 0) {
            int generate = Herbivore.getNumberOfHerbivores() / 4 + 1;
            for (int i = 0; i < generate; i++) {
                entitiesCreator.generateEntity(Grass.class, this);
            }
        }
    }
    public Entity getEntityByCoordinate(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public void removeEntity(Coordinate coordinate) {
        entities.put(coordinate, null);
    }

    public HashMap<Coordinate, Entity> getEntities() {
        return entities;
    }
}
