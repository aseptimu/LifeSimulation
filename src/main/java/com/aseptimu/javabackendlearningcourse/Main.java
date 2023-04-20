package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.actions.*;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static void reader(Simulation simulation) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        HerbivoreCreator herbivoreCreator = new HerbivoreCreator();
        PredatorCreator predatorCreator = new PredatorCreator();
        GrassCreator grassCreator = new GrassCreator();
        ObstacleCreator obstacleCreator = new ObstacleCreator();
        try {
            int herbivores = 0;
            int predators = 0;
            while (herbivoreCreator.isInitialized()) {
                System.out.print("Enter number of herbivore: ");
                herbivores = scanner.nextInt();
                if (!herbivoreCreator.initEntity(herbivores)) {
                    System.err.println("The number of herbivores should be from 1 to "
                            + HerbivoreCreator.MAX_HERBIVORE_AMOUNT);
                } else {
                    grassCreator.initEntity(herbivores);
                }
            }
            simulation.addInitAction(herbivoreCreator);
            simulation.addInitAction(grassCreator);

            while (predatorCreator.isInitialized()) {
                System.out.print("Enter number of predators: ");
                predators = scanner.nextInt();
                if (!predatorCreator.initEntity(predators)) {
                    System.err.println("The number of predators should be from 0 to "
                            + PredatorCreator.MAX_PREDATOR_AMOUNT);
                }
            }
            simulation.addInitAction(predatorCreator);

            while (obstacleCreator.isInitialized()) {
                System.out.print("Enter obstacles density: ");
                double obstacles = scanner.nextDouble();
                if (!obstacleCreator.initEntity(obstacles, herbivores * 2 + predators)) {
                    System.err.println("Density should be between 0 and 1 (ex: 0.2)");
                }
            }
            simulation.addInitAction(obstacleCreator);
        } catch (InputMismatchException e) {
            scanner.next();
            System.err.println("Invalid input! Enter numbers...\n");
        }
    }

    public static void main(String[] args) {
        Field field = new Field();
        Simulation simulation = new Simulation(field);
        reader(simulation);

        Thread t = new Thread(new ConsoleHandler(simulation));
        t.setDaemon(true);
        t.start();
        simulation.init();
        simulation.startSimulation();
    }
}
