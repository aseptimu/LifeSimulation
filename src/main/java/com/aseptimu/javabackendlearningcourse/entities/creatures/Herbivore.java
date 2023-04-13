package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.obstacles.Obstacles;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.Random;

public class Herbivore extends Creature {
    private static final int HP = 100;
    private static final int SPEED = 1;
    private static final String VIEW = "\uD83E\uDD8C";
    private static int numberOfHerbivores = 0;
    public Herbivore(Coordinate coordinate, Field field) {
        super(coordinate, VIEW, SPEED, HP, field);
        numberOfHerbivores++;
    }

    @Override
    public Herbivore makeMove() {//TODO: template. need to redo
//        Random random = new Random();
//        int direction = random.nextInt(4);
//        Coordinate newCoordinate = null;
//        if (direction % 4 == 0) {
//            newCoordinate = new Coordinate(coordinate.getY() + 1, coordinate.getX());
//        } else if (direction % 4 == 1) {
//            newCoordinate = new Coordinate(coordinate.getY() - 1, coordinate.getX());
//        } else if (direction % 4 == 2) {
//            newCoordinate = new Coordinate(coordinate.getY(), coordinate.getX() + 1);
//        } else {
//            newCoordinate = new Coordinate(coordinate.getY(), coordinate.getX() - 1);
//        }
//        if (canMove(newCoordinate)) {
//            field.removeEntity(coordinate);
//            this.coordinate = newCoordinate;
//        }
        Coordinate[] path = pathFinder.findPath(this.coordinate);
        Coordinate toFind = pathFinder.getClosestTarget();
        if (toFind == null)
            return this;
        Coordinate prev = null;
        while (toFind != this.coordinate) {
            prev = toFind;
            toFind = path[pathFinder.getIndexOfCoordinate(toFind)];
        }
        field.removeEntity(coordinate);
        this.coordinate = prev;
        return this;
    }

    private boolean canMove(Coordinate newCoordinate) {//TODO: template. need to redo
        Entity entity = field.getEntityByCoordinate(newCoordinate);
        int x = newCoordinate.getX();
        int y = newCoordinate.getY();
        if (entity instanceof Obstacles) {
            return false;
        } else if (entity instanceof Creature) {
            return false;
        } else if (x < 0 || x >= Field.WIDTH) {
            return false;
        } else if (y < 0 || y >= Field.HEIGHT) {
            return false;
        }
        return true;
    }

    public static int getNumberOfHerbivores() {
        return numberOfHerbivores;
    }
}
