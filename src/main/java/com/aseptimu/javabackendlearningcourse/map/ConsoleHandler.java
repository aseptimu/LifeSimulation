package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.Simulation;

import java.util.Scanner;

public class ConsoleHandler implements Runnable {
    Simulation simulation;

    public ConsoleHandler(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        MapRenderer renderer = simulation.getRenderer();
        renderer.setPrompt("Enter command: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.next();
            switch (command) {
                case "1" -> simulation.setDelay(500);
                case "2" -> simulation.setDelay(1000);
                case "3" -> simulation.setDelay(2000);
                case "p" -> simulation.stop();
                case "n" -> renderer.showNotation();
            }
        }

    }
}
