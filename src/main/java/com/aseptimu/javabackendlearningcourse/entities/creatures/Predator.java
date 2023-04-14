package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.Random;

public class Predator extends Creature {
    private static int numberOfPredators = 0;
    private static final int DAMAGE = 50;
    public Predator(Coordinate coordinate, Field field) {
        super(coordinate, "\uD83D\uDC3A", 1, field);
        numberOfPredators++;
    }

    @Override
    public Predator makeMove() {
        Coordinate[] path = pathFinder.findPath(this.coordinate, this);
        Coordinate next = pathFinder.findNextMove(path, this.coordinate);
        Entity entity = field.getEntityByCoordinate(next);
        if (entity instanceof Herbivore herbivore) {
            herbivore.hit(DAMAGE);
            if (herbivore.getHp() <= 0) {
                herbivore.decrementNumberOfHerbivores();
                field.removeEntity(coordinate);
                this.coordinate = next;
            }
        } else {
            field.removeEntity(coordinate);
            this.coordinate = next;
        }
        return this;
    }


    public static int getNumberOfPredators() {
        return numberOfPredators;
    }
}
