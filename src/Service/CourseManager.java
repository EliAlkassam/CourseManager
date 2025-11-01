package Service;

import java.util.ArrayList;
import java.util.List;
import exception.CourseException;
import model.Course;

/**
 * Handles logic for storing, updating and deleting courses
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-10-31
 */

public class CourseManager {

    private List<Course> courses = new ArrayList<>();
    private Course course;

    public void addCourseToList(Course c) {
        if (c != null) {
            courses.add(c);
        }
    }

    public List<Course> getCoursesList() {
        return this.courses;
    }

    public Course findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Replaces current list of courses with provided one.
     */
    public List<Course> setCourses(List<Course> courses) {
        return this.courses = courses;
    }

    public Course updateCourse(Course course) throws CourseException {
        if (course == null) {
            throw new CourseException("Could not find course");
        }
        return this.course;
    }

    public void deleteCourse(Course course) throws CourseException {
        if (course == null || !courses.remove(course)) {
            throw new CourseException("Could not find course to delete");
        }
        courses.remove(course);
    }

}
