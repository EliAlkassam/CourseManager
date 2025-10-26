import java.util.ArrayList;
import java.util.List;

import model.Course;

public class CourseManager {

    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course c) {
        if (c != null) {
            courses.add(c);
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
}
