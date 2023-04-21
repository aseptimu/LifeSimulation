package com.aseptimu.javabackendlearningcourse;

import com.aseptimu.javabackendlearningcourse.actions.*;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.map.Field;
import com.aseptimu.javabackendlearningcourse.map.MapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static int moveCount = 0;
    private boolean running;
    private final Field map;
    private int delay = 1000;
    public MapRenderer renderer = new MapRenderer();
    private final List<Action> initActions;
    private final Move move;
    private final GrassController grassController;

    private boolean stopped;

    public Simulation(Field map) {
        initActions = new ArrayList<>();
        move = new Move();
        grassController = new GrassController();
        this.map = map;
    }
    public void init() {
        for (Action initAction : initActions) {
            initAction.perform(map);
        }
    }

    private void nextTurn() {
        if (!stopped) {
            renderer.render(map);
            if (Herbivore.getNumberOfHerbivores() == 0) {
                renderer.endSimulation();
                running = false;
                return ;
            }
            move.perform(map);
            grassController.perform(map);
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

    public void addInitAction(Action action) {
        initActions.add(action);
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
