package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class Herbivore extends Creature {
    private static int numberOfHerbivores = 0;
    public Herbivore(Coordinate coordinate, Field field) {
        super(coordinate, "\uD83E\uDD8C", 1, 100, field);
        numberOfHerbivores++;
    }

    @Override
    public Herbivore makeMove() {
        Coordinate[] path = pathFinder.findPath(this.coordinate, this);
        Coordinate next = pathFinder.findNextMove(path, this.coordinate);
        field.removeEntity(coordinate);
        this.coordinate = next;
        return this;
    }

    public static int getNumberOfHerbivores() {
        return numberOfHerbivores;
    }
}
