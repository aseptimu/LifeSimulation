package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import java.util.Map;

public class Field {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public MapRenderer renderer = new MapRenderer();
    private Map<Coordinate, Entity> entities;

    public Field(Map<Coordinate, Entity> entities) {
        this.entities = entities;
    }
    public void render() {
        renderer.render(this);
    }

    public Map<Coordinate, Entity> getEntities() {
        return entities;
    }

}
