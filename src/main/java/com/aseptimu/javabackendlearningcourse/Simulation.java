package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.map.Field;
import com.aseptimu.javabackendlearningcourse.map.MapRenderer;

public class Simulation {
    private int moveCounter;
    private Field map = new Field();
    private MapRenderer renderer = new MapRenderer();

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.map.createEntities(5, 3, 0.2); //TODO: fix null entities; Refactor Entities creator to map, not Field field;
        sim.renderer.render(sim.map);

    }
}
