package com.aseptimu.javabackendlearningcourse.map;

import com.aseptimu.javabackendlearningcourse.entities.Entity;

import java.util.Map;

public class MapRenderer {
    private Map<Coordinate, Entity> entities;
    public void render(Field field) {
        entities = field.getEntities();
        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                if (entities.containsKey(coordinate)) {
                    System.out.print("\u001b[48;5;94m" + "\033[1m" + entities.get(coordinate).getView() + "\u001B[0m");
                } else {
                    System.out.print("\u001b[48;5;94m" + "  " + "\033[0m");
                }
            }
            System.out.println();
        }
    }
}
