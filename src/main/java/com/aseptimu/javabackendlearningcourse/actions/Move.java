package com.aseptimu.javabackendlearningcourse.actions;

import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.List;

public class Move extends Action {
    @Override
    public void perform(Field field) {
        List<Herbivore> herbivores = field.getEntitiesByType(Herbivore.class);
        List<Predator> predators = field.getEntitiesByType(Predator.class);
        for (Herbivore herbivore : herbivores) {
            herbivore.makeMove();
        }
        for (Predator predator : predators) {
            predator.makeMove();
        }
    }

}
