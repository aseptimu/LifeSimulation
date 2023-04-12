package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.Simulation;
import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;

public class MapRenderer {
    public void render(Field field) {
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Entity entity = field.getEntityByCoordinate(coordinate);
                if (entity != null) { //TODO: fix null
                    System.out.print("\u001b[48;5;94m" + "\033[1m" + entity.getView() + "\u001B[0m");
                } else {
                    System.out.print("\u001b[48;5;94m" + "  " + "\033[0m");
                }
            }
                printInfo(i);

            System.out.println();
        }
        System.out.println();
    }

    private void printInfo(int i) {
        if (i == 0) {
            System.out.print(" Number of steps: " + Simulation.getMoveCount());
        } else if (i == 1) {
            System.out.print(" Number of herbivores: " + Herbivore.getNumberOfHerbivores());
        } else if (i == 2) {
            System.out.print(" Number of predators: " + Predator.getNumberOfPredators());
        }
    }
}
