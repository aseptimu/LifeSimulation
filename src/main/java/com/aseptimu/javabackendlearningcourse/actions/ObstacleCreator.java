package com.aseptimu.javabackendlearningcourse.actions;

import com.aseptimu.javabackendlearningcourse.entities.obstacles.Rock;
import com.aseptimu.javabackendlearningcourse.entities.obstacles.Tree;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

public class ObstacleCreator extends EntityCreator {
    public boolean initEntity(double density, int numberOfEntities) {
        if (density > 1 || density < 0) {
            return false;
        }
        double obstacles = density * (Field.FIELD_SIZE - numberOfEntities);
        return super.initEntity((int) obstacles);
    }

    @Override
    public void perform(Field field) {
        if (isInitialized) {
            for (int i = 0; i < amount / 2; i++) {
                Coordinate coordinate = generateCoordinate(field);
                field.addEntity(coordinate, new Rock(coordinate));
                coordinate = generateCoordinate(field);
                field.addEntity(coordinate, new Tree(coordinate));
            }
        }
    }
}
