package com.aseptimu.javabackendlearningcourse.entities.obstacles;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Tree extends Entity implements Obstacles {
    private static final String VIEW = "\uD83C\uDF32";
    public Tree(Coordinate coordinate) {
        super(coordinate, VIEW);
    }
}
