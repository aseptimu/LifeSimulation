package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.Simulation;
import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;

public class MapRenderer {
    private boolean showNotation;
    private String prompt = "";
    public void render(Field field) {
        System.out.print("\033[H\033[2J");
        if (showNotation) {
            printNotationX();
        }
        for (int i = 0; i < Field.HEIGHT; i++) {
            if (showNotation) {
                System.out.print(i + "\t");
            }
            for (int j = 0; j < Field.WIDTH; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Entity entity = field.getEntityByCoordinate(coordinate);
                if (entity != null) {
                    System.out.print("\u001b[48;5;94m" + "\033[1m" + entity.getView() + "\u001B[0m");
                } else {
                    System.out.print("\u001b[48;5;94m" + "  " + "\033[0m");
                }
            }
            printInfo(i);

            System.out.println();
        }
        System.out.println(prompt);
    }

    private void printInfo(int i) {
        if (i == 0) {
            System.out.print(" Number of steps: " + Simulation.getMoveCount());
        } else if (i == 1) {
            System.out.print(" Number of herbivores: " + Herbivore.getNumberOfHerbivores());
        } else if (i == 2) {
            System.out.print(" Number of predators: " + Predator.getNumberOfPredators());
        } else if (i == 3) {
            System.out.print(" Number of grass: " + Grass.getGrassCount());
        } else if (i == 5) {
            System.out.print(" Commands:");
        } else if (i == 6) {
            System.out.print(" change speed: 1 2 3");
        } else if (i == 7) {
            System.out.print(" turn on/off notation: n");
        } else if (i == 8) {
            System.out.print(" stop/start simulation: p");
        }
    }
    private void printNotationX() {
        System.out.print("\t");
        for (int i = 0; i < Field.WIDTH; i++) {
            System.out.print(i + (i < 10 ? " " : ""));
        }
        System.out.println();
    }

    public void showNotation() {
        showNotation = !showNotation;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
