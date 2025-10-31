package model;

import enums.Credits;

/**
 * Represents a course that takes place on campus
 * Inherits from the abstract class "Course"
 * 
 * @author elal2203
 * @version 0.1
 * @since 2025-10-31
 */

public class CampusCourse extends Course {

    public CampusCourse(String courseName, Credits credits, String overview) {
        super(courseName, credits, overview);
    }

}
