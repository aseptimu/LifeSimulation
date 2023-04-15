package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.map.ConsoleHandler;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static void reader(Field field) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter number of herbivore: ");
                int herbivores = scanner.nextInt();
                System.out.print("Enter number of predators: ");
                int predators = scanner.nextInt();
                System.out.print("Enter obstacles density: ");
                double obstacles = scanner.nextDouble();
                try {
                        field.createEntities(herbivores, predators, obstacles);
                        break;
                } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.err.println("Invalid input! Enter numbers...\n");
            }
        }
    }

    public static void main(String[] args) {
        Field field = new Field();
        reader(field);

        Simulation simulation = new Simulation(field);
        Thread t = new Thread(new ConsoleHandler(simulation));
        t.setDaemon(true);
        t.start();
        simulation.startSimulation();
    }
}
