package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.map.Field;
import com.aseptimu.javabackendlearningcourse.map.MapRenderer;

public class Simulation {
    private static int moveCount = 0;
    private boolean running;
    private final Field map;
    public MapRenderer renderer = new MapRenderer();

    public Simulation(Field map) {
        this.map = map;
    }

    public void nextTurn() {
        while (true) {
            map.nextTurn();
            renderer.render(map);
            moveCount++;
            try {
                Thread.sleep(1000);
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

    public void pauseSimulation() {
        running = false;
    }

    public static int getMoveCount() {
        return moveCount;
    }

    public MapRenderer getRenderer() {
        return renderer;
    }

}
