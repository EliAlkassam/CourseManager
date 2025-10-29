package Service;

import java.util.ArrayList;
import java.util.List;

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
        return courses;
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

    public Course updateCourse(Course course) {
        // String name = course.setCourseName(null);
        return this.course;
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
    }
}
