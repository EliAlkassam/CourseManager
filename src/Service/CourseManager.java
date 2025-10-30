package Service;

import java.util.ArrayList;
import java.util.List;

import exception.CourseException;
import model.Course;

public class CourseManager {

    private List<Course> courses = new ArrayList<>();
    private Course course;

    public void addCourseToList(Course c) {
        try {
            if (c != null) {
                courses.add(c);
            }

        } catch (Exception e) {

        }
    }

    public List<Course> getCoursesList() {
        return this.courses;
    }

    public Course findById(int id) {
        Course course = null;

        for (Course c : courses) {
            if (c.getId() == id) {
                c = course;
                break;
            }
        }
        return course;
    }

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
        if (course == null) {
            throw new CourseException("Could not find course to delete");
        }
        courses.remove(course);
    }
}
