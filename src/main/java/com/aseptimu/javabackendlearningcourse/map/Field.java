package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Tree;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private EntitiesCreator creator;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    private Map<Coordinate, Entity> entities;

    public void createEntities(int herbivores, int predators, double density) { //TODO: remove
        creator = new EntitiesCreator(herbivores, predators, density);
        entities = creator.generateEntities();
    }
    public Field() {
    }

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }
}
