package com.aseptimu.javabackendlearningcourse.actions;

import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class GrassCreator extends EntityCreator{
    public static final int MAX_GRASS_AMOUNT = Field.FIELD_SIZE / 4;

    @Override
    public boolean initEntity(int amount) {
        if (amount > MAX_GRASS_AMOUNT || amount < 1) {
            return false;
        }
        return super.initEntity(amount);
    }

    @Override
    public void perform(Field field) {
        if (isInitialized) {
            for (int i = 0; i < amount; i++) {
                Coordinate coordinate = generateCoordinate(field);
                field.addEntity(coordinate, new Grass(coordinate));
            }
        }
    }
}
