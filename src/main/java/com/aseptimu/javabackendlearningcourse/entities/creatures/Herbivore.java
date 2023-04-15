package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class Herbivore extends Creature {
    private static int herbivoreCount = 0;
    private int hp;
    private int hpChange;
    public Herbivore(Coordinate coordinate, Field field) {
        super(coordinate, "\uD83E\uDD8C", 1, field);
        this.hp = 100;
        this.hpChange = 0;
        herbivoreCount++;
    }

    @Override
    public Herbivore makeMove() {
        if (hp <= 0) {
            herbivoreCount--;
            field.removeEntity(coordinate);
            return null;
        }
        Coordinate[] path = pathFinder.findPath(this.coordinate, this);
        Coordinate next = this.coordinate;
        Entity entity = this;
        for (int i = 0; i < speed && !(entity instanceof Grass); i++) {
            next = pathFinder.findNextMove(path, next);
            entity = field.getEntityByCoordinate(next);
        }
        if (entity instanceof Grass grass) {
            grass.decrementNumberOfGrass();
        }
        field.removeEntity(coordinate);
        this.coordinate = next;
        return this;
    }

    public static int getNumberOfHerbivores() {
        return herbivoreCount;
    }

    public void hit(int damage) {
        this.hp -= damage;
        this.hpChange += damage;
    }

}
