package com.aseptimu.javabackendlearningcourse.pathfinding;

import com.aseptimu.javabackendlearningcourse.entities.Entity;
import com.aseptimu.javabackendlearningcourse.entities.Grass;
import com.aseptimu.javabackendlearningcourse.map.Coordinate;
import com.aseptimu.javabackendlearningcourse.map.Field;

import java.util.*;

public class PathFinder {
    private final Field map;
    private Coordinate closestTarget;

    public PathFinder(Field map) {
        this.map = map;
    }

    public Coordinate[] findPath(Coordinate creatureCoordinate) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        Coordinate[] paths = new Coordinate[Field.WIDTH * Field.HEIGHT];
        closestTarget = null;

        queue.add(creatureCoordinate);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.remove();
            if (map.getEntityByCoordinate(currentCoordinate) instanceof Grass) {
                closestTarget = currentCoordinate;
                break;
            }
            List<Coordinate> availableNeighbours = getAvailableNeighbours(currentCoordinate);
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

    private List<Coordinate> getAvailableNeighbours(Coordinate current) {
        List<Coordinate> neighbours = getNeighbours(current);
        List<Coordinate> availableNeighbours = new ArrayList<>();
        for (Coordinate neighbour : neighbours) {
            Entity entity = map.getEntityByCoordinate(neighbour);
            if (entity == null || entity instanceof Grass) {
                int x = neighbour.getX();
                int y = neighbour.getY();
                if (x >= 0 && x < Field.WIDTH && y >= 0 && y < Field.HEIGHT) {
                    availableNeighbours.add(neighbour);
                }
            }
        }
        return availableNeighbours;
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

    public Coordinate getClosestTarget() {
        return closestTarget;
    }
}
