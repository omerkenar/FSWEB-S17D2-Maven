package com.workintech.s17d2.model;

public class Developer {

    private int id;
    private String name;
    private Double salary;
    private Experience experience;

    public Developer(int id, String name, Double salary, Experience experience) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setId(int id) {
        this.id = id;
    }
}
