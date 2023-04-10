package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.map.EntitiesCreator;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static void reader(EntitiesCreator entitiesCreator) {
        boolean error = true;
        Scanner scanner = new Scanner(System.in);
        while (error) {
            try {
                System.out.print("Enter number of herbivore: ");
                int herbivores = scanner.nextInt();
                System.out.print("Enter number of predators: ");
                int predators = scanner.nextInt();
                System.out.print("Enter obstacles density: ");
                double obstacles = scanner.nextDouble();
                error = entitiesCreator.initEntities(herbivores, predators, obstacles);
                if (error) {
                    System.err.println("The number of herbivores and predators must be between 1 and 100. " +
                            "Density should be between 0 and 1\n");
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.err.println("Invalid input! Enter numbers...\n");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        EntitiesCreator entitiesCreator = new EntitiesCreator();
        reader(entitiesCreator);
        Field field = new Field(entitiesCreator.generateEntities());

        Simulation simulation = new Simulation(field);
        simulation.start();
    }
}
