package com.aseptimu.javabackendlearningcourse.actions;

import com.aseptimu.javabackendlearningcourse.Simulation;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
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
        createGrass(field);
    }

    private void createGrass(Field field) {
        if (Grass.getGrassCount() < Herbivore.getNumberOfHerbivores() / 2 + 1
                && Simulation.getMoveCount() % 2 == 0) {
            int generate = Herbivore.getNumberOfHerbivores() / 4 + 1;
            GrassCreator grassCreator = new GrassCreator();
            grassCreator.initEntity(generate);
            grassCreator.perform(field);
        }
    }
}
