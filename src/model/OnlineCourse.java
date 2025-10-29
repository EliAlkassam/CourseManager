package model;

import enums.Credits;

public class OnlineCourse extends Course {
    public OnlineCourse(String courseName, Credits credits, String overview) {
        super(courseName, credits, overview);
    }

    @Override
    public String toString() {
        return "Course:" + " " + "id=" + " " + id + " " + "Course Name=" + " " + courseName + "-" + "Credits=" + " "
                + credits
                + "-" + "Overview=" + " " + overview;
    }

}
