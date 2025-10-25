import java.util.ArrayList;

import gui.Printable;

// abstract superclass that implements the interface

public abstract class Course implements Printable {

    protected int id;
    protected int credits;
    protected String name;
    protected String overview;
    // protected ArrayList<Teacher> teachers;

    public Course(int id, int credits, String name, String overview) {
        this.id = id;
        this.credits = credits;
        this.name = name;
        this.overview = overview;
    }

    public int getId() {
        return this.id;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setName(String name) {
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
        System.out.println(toString());// TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        String name = getName();
        int credits = getCredits();
        String overview = getOverview();

        return "Course[" + "Name=" + name + ";" + "Credits =" + credits + ";" + "Overview=" + overview;
    }

}
