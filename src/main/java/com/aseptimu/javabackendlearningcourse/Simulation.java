package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Creatures;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.Map;

public class Simulation {
    private int moveCounter;
    private Field map;

    public Simulation(Field map) {
        this.map = map;
    }

    public void start() {
        for (Map.Entry<Coordinate, Entity> entry : map.getEntities().entrySet()) {
            Entity entity = entry.getValue();
            if (entity instanceof Creatures) {
                ((Creatures) entity).makeMove();
            }
        }
        map.render();
    }
}
