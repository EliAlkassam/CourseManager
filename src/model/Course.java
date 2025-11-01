package model;

import java.util.concurrent.atomic.AtomicInteger;

import enums.Credits;
import exception.CourseException;
import gui.Printable;

/**
 * Abstract base class representing a course
 * Implements the interface "Printable"
 * 
 * @author elal2203
 * @version 0.1
 * @since 2025-10-31
 */
public abstract class Course implements Printable {

    protected int id;
    protected Credits credits;
    protected String courseName;
    protected String overview;

    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public Course() {
        this.id = idGenerator.getAndIncrement();
    }

    public Course(String courseName, Credits credits, String overview) {
        this.id = idGenerator.getAndIncrement();
        this.credits = credits;
        this.courseName = courseName;
        this.overview = overview;
    }

    public int getId() {
        return this.id;
    }

    public void setCredits(Credits credits) throws CourseException {
        if (credits == null) {
            throw new CourseException("Credits can't be null");
        }
        this.credits = credits;
    }

    public Credits getCredits() {
        return this.credits;
    }

    public void setCourseName(String name) throws CourseException {
        if (name == null || name.isEmpty()) {
            throw new CourseException("Name can't be null or empty");
        }
        this.courseName = name;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setOverview(String overview) throws CourseException {
        if (overview == null || overview.isEmpty()) {
            throw new CourseException("Overview can't be null or empty");
        }
        this.overview = overview;
    }

    public String getOverview() {
        return this.overview;
    }

    @Override
    public String print() {
        return null;
    }

    @Override
    public String toString() {
        String courseName = getCourseName();
        Credits credits = getCredits();
        String overview = getOverview();

        return "Course:" + " " + "id=" + " " + id + " " + "Name=" + " " + courseName + "-" + "Credits=" + " " + credits
                + "-"
                + "Overview="
                + " " + overview;
    }

    @Override
    public String toStringType(Course c) {
        if (c instanceof CampusCourse) {
            return "Campus course";
        } else if (c instanceof OnlineCourse) {
            return "Online course";
        } else {
            return "Unknown course type";
        }
    }

}
