package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class Predator extends Creature {
    private static int numberOfPredators = 0;
    private static final int DAMAGE = 50;
    public Predator(Coordinate coordinate, Field field) {
        super(coordinate, "\uD83D\uDC3A", 2, field);
        numberOfPredators++;
    }

    @Override
    public void makeMove() {
        Coordinate[] path = pathFinder.findPath(this.coordinate, this);
        Coordinate next = this.coordinate;
        for (int i = 0; i < speed && !(field.getEntityByCoordinate(next) instanceof Herbivore); i++) {
            next = pathFinder.findNextMove(path, next);
        }
        Entity entity = field.getEntityByCoordinate(next);
        if (entity instanceof Herbivore herbivore) {
            herbivore.hit(DAMAGE);
        } else {
            field.removeEntity(coordinate);
            this.coordinate = next;
            field.addEntity(coordinate, this);
        }
    }


    public static int getNumberOfPredators() {
        return numberOfPredators;
    }
}
