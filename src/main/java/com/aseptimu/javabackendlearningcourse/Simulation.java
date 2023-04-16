package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.map.Field;
import com.aseptimu.javabackendlearningcourse.map.MapRenderer;

public class Simulation {
    private static int moveCount = 0;
    private boolean running;
    private final Field map;
    private int delay = 1000;
    public MapRenderer renderer = new MapRenderer();

    private boolean stopped;

    public Simulation(Field map) {
        this.map = map;
    }

    private void nextTurn() {
        if (!stopped) {
            renderer.render(map);
            if (Herbivore.getNumberOfHerbivores() == 0) {
                System.out.println("\u001B[34mNo herbivores left");
                running = false;
                return ;
            }
            map.nextTurn();
            moveCount++;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void startSimulation() {
        running = true;
        while (running) {
            nextTurn();
        }
    }

    public static int getMoveCount() {
        return moveCount;
    }

    public MapRenderer getRenderer() {
        return renderer;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void stop() {
        stopped = !stopped;
    }
    public void finish() {
        running = false;
    }
}
