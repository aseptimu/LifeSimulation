package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.obstacles.Obstacles;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.Random;

public class Predator extends Creature {
    private static final int HP = 100;
    private static final int SPEED = 1;
    private static final String VIEW = "\uD83D\uDC3A";
    private static int numberOfPredators = 0;
    public Predator(Coordinate coordinate, Field field) {
        super(coordinate, VIEW, SPEED, HP, field);
        numberOfPredators++;
    }

    @Override
    public Predator makeMove() {//TODO: template. need to redo
        Random random = new Random();
        int direction = random.nextInt(4);
        Coordinate newCoordinate = null;
        if (direction % 4 == 0) {
            newCoordinate = new Coordinate(coordinate.getY() + 1, coordinate.getX());
        } else if (direction % 4 == 1) {
            newCoordinate = new Coordinate(coordinate.getY() - 1, coordinate.getX());
        } else if (direction % 4 == 2) {
            newCoordinate = new Coordinate(coordinate.getY(), coordinate.getX() + 1);
        } else {
            newCoordinate = new Coordinate(coordinate.getY(), coordinate.getX() - 1);
        }
        if (canMove(newCoordinate)) {
            field.removeEntity(coordinate);
            this.coordinate = newCoordinate;
        }
        return this;
    }
    private boolean canMove(Coordinate newCoordinate) {//TODO: template. need to redo
        Entity entity = field.getEntityByCoordinate(newCoordinate);
        int x = newCoordinate.getX();
        int y = newCoordinate.getY();
        if (entity instanceof Obstacles) {
            return false;
        } else if (entity instanceof Predator) {
            return false;
        } else if (x < 0 || x >= Field.WIDTH) {
            return false;
        } else if (y < 0 || y >= Field.HEIGHT) {
            return false;
        }
        return true;
    }

    public static int getNumberOfPredators() {
        return numberOfPredators;
    }
}
