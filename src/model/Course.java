package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import gui.Credits;
import gui.Printable;

// abstract superclass that implements the interface

public abstract class Course implements Printable {

    protected final int id;
    protected Credits credits;
    protected String name;
    protected String overview;

    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    private List<Course> courses;

    public Course() {
        this.id = idGenerator.getAndIncrement();
        // this.courses = new ArrayList<>();

    }

    public Course(String name, Credits credits, String overview) {
        this.id = idGenerator.getAndIncrement();
        this.credits = credits;
        this.name = name;
        this.overview = overview;
    }

    public int getId() {
        return this.id;
    }

    public void setCredits(Credits credits) throws Exception {
        if (credits == null) {
            throw new Exception("Credits can't be null");
        }
        this.credits = credits;
    }

    public Credits getCredits() {
        return this.credits;
    }

    public void setName(String name) throws Exception {
        if (name == null) {
            throw new Exception("Name can't be null");
        } else if (name.isEmpty()) {
            throw new Exception("setAuthor(): Author can't empty");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return this.overview;
    }

    @Override
    public String print() {
        System.out.println(toString());//
        return null;
    }

    @Override
    public String toString() {
        String name = getName();
        Credits credits = getCredits();
        String overview = getOverview();

        return "Course:" + " " + "id=" + " " + id + " " + "Name=" + " " + name + "-" + "Credits=" + " " + credits + "-"
                + "Overview="
                + " " + overview;
    }

}
