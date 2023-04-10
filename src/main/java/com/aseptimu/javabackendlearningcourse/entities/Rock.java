package com.aseptimu.javabackendlearningcourse.entities;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Rock extends Entity {
    private static final String VIEW = "\uD83E\uDEA8";
    public Rock(Coordinate coordinate) {
        super(coordinate, VIEW);
    }
}
