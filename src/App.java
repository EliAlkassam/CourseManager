import java.util.List;

import javax.swing.SwingUtilities;

import Service.CourseManager;
import gui.AppFrame;
import enums.Credits;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

public class App {
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // CourseManager courseManager = new CourseManager();
                // new AppFrame(courseManager).setVisible(true);
                new AppFrame().setVisible(true);

                // CampusCourse campus = new CampusCourse("java", Credits.FIFTEEN, "This
                // course");
                // OnlineCourse onlineCourse = new OnlineCourse("java II", Credits.THIRTY, "Tome
                // for the final");

                // // Course c = new Course();
                // // c.addCourse(campus);

                // CourseManager c = new CourseManager();
                // c.addCourse(campus);
                // c.addCourse(onlineCourse);

                // List<Course> list = c.getCoursesList();

                // System.out.println(list);

            }
        });

    }
}
