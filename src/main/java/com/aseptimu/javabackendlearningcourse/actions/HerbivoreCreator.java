package com.aseptimu.javabackendlearningcourse.actions;

import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class HerbivoreCreator extends EntityCreator{
    public static final int MAX_HERBIVORE_AMOUNT = Field.FIELD_SIZE / 4;

    @Override
    public boolean initEntity(int amount) {
        if (amount > MAX_HERBIVORE_AMOUNT || amount < 1) {
            return false;
        }
        return super.initEntity(amount);
    }

    @Override
    public void perform(Field field) {
        if (isInitialized) {
            for (int i = 0; i < amount; i++) {
                Coordinate coordinate = generateCoordinate(field);
                field.addEntity(coordinate, new Herbivore(coordinate, field));
            }
        }
    }
}
