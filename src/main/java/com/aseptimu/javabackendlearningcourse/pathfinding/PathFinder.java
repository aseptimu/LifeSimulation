package com.aseptimu.javabackendlearningcourse.pathfinding;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Creature;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Herbivore;
import com.aseptimu.javabackendlearningcourse.entities.creatures.Predator;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.*;

public class PathFinder {
    private final Field map;
    private Coordinate closestTarget;

    public PathFinder(Field map) {
        this.map = map;
    }

    public Coordinate[] findPath(Coordinate creatureCoordinate, Creature creature) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        Coordinate[] paths = new Coordinate[Field.WIDTH * Field.HEIGHT];
        closestTarget = null;

        queue.add(creatureCoordinate);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.remove();
            if (checkCurrentTarget(currentCoordinate, creature)) {
                closestTarget = currentCoordinate;
                break;
            }
            List<Coordinate> availableNeighbours = getAvailableNeighbours(currentCoordinate, creature);
            for (Coordinate neighbour : availableNeighbours) {
                int neighbourIndex = getIndexOfCoordinate(neighbour);
                if (paths[neighbourIndex] == null) {
                    queue.add(neighbour);
                    paths[neighbourIndex] = currentCoordinate;
                }
            }
        }
        return paths;
    }

    private boolean checkCurrentTarget(Coordinate currentCoordinate, Creature creature) {
        if (creature instanceof Herbivore &&
                map.getEntityByCoordinate(currentCoordinate) instanceof Grass) {
            return true;
        } else if (creature instanceof Predator &&
                map.getEntityByCoordinate(currentCoordinate) instanceof Herbivore) {
            return true;
        }
        return false;
    }

    private List<Coordinate> getAvailableNeighbours(Coordinate current, Creature creature) {
        List<Coordinate> neighbours = getNeighbours(current);
        List<Coordinate> availableNeighbours = new ArrayList<>();
        for (Coordinate neighbour : neighbours) {
            if (checkSpecifiedNeighbour(neighbour, creature)) {
                availableNeighbours.add(neighbour);
            }
        }
        return availableNeighbours;
    }

    private boolean checkSpecifiedNeighbour(Coordinate neighbour, Creature creature) {
        Entity entity = map.getEntityByCoordinate(neighbour);
        int x = neighbour.getX();
        int y = neighbour.getY();
        if (x >= 0 && x < Field.WIDTH && y >= 0 && y < Field.HEIGHT) {
            if (entity == null) {
                return true;
            } else if (creature instanceof Predator && entity instanceof Herbivore) {
                return true;
            } else if (creature instanceof Herbivore && entity instanceof Grass) {
                return true;
            }
        }
        return false;
    }
    private List<Coordinate> getNeighbours(Coordinate current) {
        List<Coordinate> set = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                Coordinate neighbour = new Coordinate(i, j);
                if (!neighbour.equals(current)) {
                    set.add(neighbour);
                }
            }
        }
        return set;
    }

    public int getIndexOfCoordinate(Coordinate coordinate) {
        return coordinate.getY() * Field.HEIGHT + coordinate.getX();
    }

    public Coordinate findNextMove(Coordinate[] path, Coordinate current) {
        Coordinate toFind = closestTarget;
        if (closestTarget == null)
            return current;
        Coordinate prev = null;
        while (toFind != current) {
            prev = toFind;
            toFind = path[getIndexOfCoordinate(toFind)];
        }
        return prev;
    }
}
