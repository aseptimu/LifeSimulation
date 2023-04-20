package com.aseptimu.javabackendlearningcourse.actions;

import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class PredatorCreator extends EntityCreator{
    public static final int MAX_PREDATOR_AMOUNT = Field.FIELD_SIZE / 4;

    @Override
    public void perform(Field field) {
        if (isInitialized) {
            for (int i = 0; i < amount; i++) {
                Coordinate coordinate = generateCoordinate(field);
                field.addEntity(coordinate, new Predator(coordinate, field));
            }
        }
    }

    @Override
    public boolean initEntity(int amount) {
        if (amount > MAX_PREDATOR_AMOUNT || amount < 0) {
            return false;
        }
        return super.initEntity(amount);
    }
}
