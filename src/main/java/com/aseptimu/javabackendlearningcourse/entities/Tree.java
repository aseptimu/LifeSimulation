package com.aseptimu.javabackendlearningcourse.entities;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Tree extends Entity {
    private static final String VIEW = "\uD83C\uDF32";
    public Tree(Coordinate coordinate) {
        super(coordinate, VIEW);
    }
}
