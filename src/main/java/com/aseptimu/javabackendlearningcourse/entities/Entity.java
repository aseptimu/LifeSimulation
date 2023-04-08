package com.aseptimu.javabackendlearningcourse.entities;

import com.aseptimu.javabackendlearningcourse.map.Coordinate;

public abstract class Entity {
    private final String view;
    private Coordinate coordinate;

    public Entity(Coordinate coordinate, String view) {
        this.view = view;
        this.coordinate = coordinate;
    }

    public String getView() {
        return view;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
