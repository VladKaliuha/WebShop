package com.epam.preproduction.kaliuha.entity.impl;

import com.epam.preproduction.kaliuha.entity.Entity;

import java.util.Objects;

public class Fabricator extends Entity {

    private String name;

    public Fabricator(String name) {
        this.name = name;
    }

    public Fabricator(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fabricator that = (Fabricator) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Fabricator{" +
                "name='" + name + '\'' +
                '}';
    }
}
