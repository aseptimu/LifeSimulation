package com.aseptimu.javabackendlearningcourse.entities.obstacles;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Rock extends Entity implements Obstacles {
    private static final String VIEW = "\uD83E\uDEA8";
    public Rock(Coordinate coordinate) {
        super(coordinate, VIEW);
    }
}
