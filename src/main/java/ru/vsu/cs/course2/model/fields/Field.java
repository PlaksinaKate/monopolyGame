package ru.vsu.cs.course2.model.fields;

public class Field  {
    private String name;
    private int numberOfField;

    public Field(String name, int numberOfField) {
        this.name = name;
        this.numberOfField = numberOfField;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfField() {
        return numberOfField;
    }
}