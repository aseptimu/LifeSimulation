package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.entities.Rock;
import com.aseptimu.javabackendlearningcourse.entities.Tree;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EntitiesCreator {
    private int grassCount;
    private int rockCount;
    private int treeCount;
    private int herbivoreCount;
    private int predatorCount;

    public EntitiesCreator() {}

    public boolean initEntities(int herbivoreCount, int predatorCount, double density) {
        int square = Field.WIDTH * Field.HEIGHT;
        if (herbivoreCount > square / 4 || herbivoreCount < 1) {
            return true;
        } else if (predatorCount > square / 4 || predatorCount < 1) {
            return true;
        } else if (density > 1 || density < 0) {
            return true;
        }
        this.herbivoreCount = herbivoreCount;
        this.predatorCount = predatorCount;
        this.grassCount = herbivoreCount;
        double obstacles = density * (Field.WIDTH * Field.HEIGHT - predatorCount - herbivoreCount - grassCount);
        this.rockCount = (int)obstacles / 2;
        this.treeCount = (int)obstacles / 2;
        return false;
    }
    public Map<Coordinate, Entity> generateEntities() {
        Map<Coordinate, Entity> entities = new HashMap<>();
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
            entities.put(coordinate, new Predator(coordinate));
        }
        for (int i = 0; i < herbivoreCount; i++) {
            coordinate = generateCoordinate(entities);
            entities.put(coordinate, new Herbivore(coordinate));
        }
        return entities;
    }

    private Coordinate generateCoordinate(Map<Coordinate, Entity> map) {
        Random random = new Random();
        Coordinate coordinate;
        do {
            coordinate = new Coordinate(random.nextInt(Field.HEIGHT), random.nextInt(Field.WIDTH));
        } while (map.containsKey(coordinate)); //TODO: fix infinite loop in case full map and big density or lots of Creatures
        return coordinate;
    }

}
