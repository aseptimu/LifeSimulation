package com.aseptimu.javabackendlearningcourse.entities;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public class Grass extends Entity {
    private static final String VIEW = "\uD83C\uDF31";
    private static int grassCount;
    public Grass(Coordinate coordinate) {
        super(coordinate, VIEW);
        grassCount++;
    }

    public static int getGrassCount() {
        return grassCount;
    }

    public void decrementNumberOfGrass() {
        grassCount--;
    }
}
