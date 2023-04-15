package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.entities.obstacles.Rock;
import com.aseptimu.javabackendlearningcourse.entities.obstacles.Tree;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;

import java.util.HashMap;
import java.util.Random;

public class EntitiesCreator {
    private int grassCount;
    private int rockCount;
    private int treeCount;
    private int herbivoreCount;
    private int predatorCount;

    public EntitiesCreator() {}

    public void initEntities(int herbivoreCount, int predatorCount, double density) {
        int square = Field.WIDTH * Field.HEIGHT;
        if (herbivoreCount > square / 4 || herbivoreCount < 1) {
            throw new IllegalArgumentException("The number of herbivores should be from 1 to " + square / 4);
        } else if (predatorCount > square / 4 || predatorCount < 1) {
            throw new IllegalArgumentException("The number of predators should be from 1 to " + square / 4);
        } else if (density > 1 || density < 0) {
            throw new IllegalArgumentException("Density should be between 0 and 1");
        }
        this.herbivoreCount = herbivoreCount;
        this.predatorCount = predatorCount;
        this.grassCount = herbivoreCount;
        double obstacles = density * (Field.WIDTH * Field.HEIGHT - predatorCount - herbivoreCount - grassCount);
        this.rockCount = (int)obstacles / 2;
        this.treeCount = (int)obstacles / 2;
    }
    public HashMap<Coordinate, Entity> generateDefaultEntities(Field field) {
        HashMap<Coordinate, Entity> entities = new HashMap<>();
        Coordinate coordinate;
        for (int i = 0; i < treeCount; i++) {
            coordinate = generateCoordinate(entities);
            entities.put(coordinate, new Tree(coordinate));
        }
        for (int i = 0; i < rockCount; i++) {
            coordinate = generateCoordinate(entities);
            entities.put(coordinate, new Rock(coordinate));
        }
        for (int i = 0; i < grassCount; i++) {
            coordinate = generateCoordinate(entities);
            entities.put(coordinate, new Grass(coordinate));
        }
        for (int i = 0; i < predatorCount; i++) {
            coordinate = generateCoordinate(entities);
            entities.put(coordinate, new Predator(coordinate, field));
        }
        for (int i = 0; i < herbivoreCount; i++) {
            coordinate = generateCoordinate(entities);
            entities.put(coordinate, new Herbivore(coordinate, field));
        }
        return entities;
    }

    private Coordinate generateCoordinate(HashMap<Coordinate, Entity> map) {
        Random random = new Random();
        Coordinate coordinate;
        do {
            coordinate = new Coordinate(random.nextInt(Field.HEIGHT), random.nextInt(Field.WIDTH));
        } while (map.get(coordinate) != null);
        return coordinate;
    }

    public void generateEntity(Class<? extends Entity> entity, Field field) {
        HashMap<Coordinate, Entity> map = field.getEntities();
        Coordinate coordinate = generateCoordinate(map);
        if (entity == Grass.class) {
            map.put(coordinate, new Grass(coordinate));
        } else if (entity == Rock.class) {
            map.put(coordinate, new Rock(coordinate));
        } else if (entity == Tree.class) {
            map.put(coordinate, new Tree(coordinate));
        } else if (entity == Herbivore.class) {
            map.put(coordinate, new Herbivore(coordinate, field));
        } else if (entity == Predator.class) {
            map.put(coordinate, new Predator(coordinate, field));
        }
    }
}
