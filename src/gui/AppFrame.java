package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import Service.CourseManager;
import enums.Credits;
import gui.button.ButtonManager;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

public class AppFrame extends JFrame {

    public static final String APP_NAME = "Course Manager";
    private Container c = this.getContentPane();

    private CreateCoursePanel createCoursePanel;
    private CreateCourseListPanel createCourseListPanel;

    private final CourseManager courseManager = new CourseManager();
    // private CourseManager courseManager;

    public AppFrame() {
        initialize();
        // this.courseManager = courseManager;
    }

    public void setMinimumFrame(Dimension minumumSize) {
        super.setMinimumSize(minumumSize);
    }

    private void initialize() {

        setSize(900, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(APP_NAME);

        setLayout(new BorderLayout());
        // setLayout(new GridLayout(2, 2));

        // Lägg till testkurser
        // courseManager.addCourse(new CampusCourse("Java Development", Credits.FIFTEEN,
        // "Programming with Java"));
        // courseManager.addCourse(new OnlineCourse("Java Development", Credits.THIRTY,
        // "Continue with Java"));

        ButtonManager buttonManager = new ButtonManager();
        add(buttonManager.getButtonPanel(), BorderLayout.SOUTH);

        // create panels
        createCourseListPanel = new CreateCourseListPanel(courseManager, buttonManager);
        createCoursePanel = new CreateCoursePanel(courseManager, createCourseListPanel, buttonManager);
        // createCoursePanel.setPreferredSize(new Dimension(450, 350));

        // add panels
        this.add(createCoursePanel, BorderLayout.WEST);
        this.add(createCourseListPanel, BorderLayout.CENTER);

        // this.add(createCoursePanel, BorderLayout.WEST);
        // add(createCourseListPanel.getCourseJPanel(), BorderLayout.CENTER);

        // createCourseListPanel.createCourseElement();

        MenuManager menuManager = new MenuManager(this, createCoursePanel, courseManager);
        setJMenuBar(menuManager.getMenu());

        // // ???
        // createCourseListPanel.createCourseElement();

        JButton editBtn = buttonManager.getEditBtn();
        editBtn.addActionListener(e -> {

            try {
                Course selectedCourse = createCourseListPanel.getSelectedCourse();
                if (selectedCourse != null) {
                    createCoursePanel.getCourseForEditMode(selectedCourse);
                    // buttonManager.setEditBtnEnabled(true);
                    System.err.println("inne i AppFrame");
                    // revalidate();
                    // repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "You have to choose a course to edit first");
                }

            } catch (Exception ex) {
                ex.getMessage();
                ex.printStackTrace();
            }

        });

    }

    // @FunctionalInterface
    // public interface OnClickListener<T> {
    // public void onClick(T listener);
    // }

}
