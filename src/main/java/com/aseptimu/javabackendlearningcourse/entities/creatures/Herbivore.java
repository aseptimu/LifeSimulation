package com.aseptimu.javabackendlearningcourse.entities.creatures;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class Herbivore extends Creature {
    private static int numberOfHerbivores = 0;
    private int hp;
    private int hpChange;
    public Herbivore(Coordinate coordinate, Field field) {
        super(coordinate, "\uD83E\uDD8C", 1, field);
        this.hp = 100;
        this.hpChange = 0;
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

    public void hit(int damage) {
        this.hp -= damage;
        this.hpChange += damage;
    }

    public int getHp() {
        return hp;
    }

    public void decrementNumberOfHerbivores() {
        numberOfHerbivores--;
    }

}
