package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class Herbivore extends Creature {
    private static final int HUNGER_THRESHOLD = 15;
    private static int herbivoreCount = 0;
    private int hp;
    private int stepsWithoutFood;

    public Herbivore(Coordinate coordinate, Field field) {
        super(coordinate, "\uD83E\uDD8C", 1, field);
        this.hp = 100;
        this.stepsWithoutFood = 0;
        herbivoreCount++;
    }

    @Override
    public void makeMove() {
        if (died()) {
            return;
        }
        Coordinate[] path = pathFinder.findPath(this.coordinate, this);
        Coordinate next = this.coordinate;
        Entity entity = this;
        for (int i = 0; i < speed && !(entity instanceof Grass); i++) {
            stepsWithoutFood++;
            if (stepsWithoutFood >= HUNGER_THRESHOLD) {
                hp -= 25;
            }
            if (died()) {
                return;
            }
            next = pathFinder.findNextMove(path, next);
            entity = field.getEntityByCoordinate(next);
        }
        if (entity instanceof Grass grass) {
            stepsWithoutFood = 0;
            hp = 100;
            grass.decrementNumberOfGrass();
        }
        field.removeEntity(coordinate);
        this.coordinate = next;
        field.addEntity(coordinate, this);
    }

    private boolean died() {
        if (hp <= 0) {
            herbivoreCount--;
            field.removeEntity(coordinate);
            return true;
        }
        return false;
    }

    public static int getNumberOfHerbivores() {
        return herbivoreCount;
    }

    public void hit(int damage) {
        this.hp -= damage;
    }

}
