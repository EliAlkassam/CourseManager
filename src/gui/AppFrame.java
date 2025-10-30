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

        MenuManager menuManager = new MenuManager(this, createCoursePanel, createCourseListPanel, courseManager);
        setJMenuBar(menuManager.getMenu());

        // // ???
        // createCourseListPanel.createCourseElement();
        // Course selectedCourse = createCourseListPanel.getSelectedCourse();

        JButton editBtn = buttonManager.getEditBtn();
        editBtn.addActionListener(e -> {
            try {
                Course selectedCourse = createCourseListPanel.getSelectedCourse();
                if (selectedCourse != null) {
                    createCoursePanel.enterEditMode(selectedCourse);
                    buttonManager.setEditBtnStatus(false);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "You have to choose a course to edit first");
                ex.getMessage();
                ex.printStackTrace();
            }
        });

        JButton deleteBtn = buttonManager.getDeleteBtn();
        deleteBtn.addActionListener(e -> {
            try {
                Course selectedCourse = createCourseListPanel.getSelectedCourse();
                if (selectedCourse != null) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to delete course:" + " " + selectedCourse.getCourseName() + "?",
                            "Delete course",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        createCourseListPanel.createCourseElement();
                        buttonManager.setDeleteBtnStatus(false);
                        buttonManager.setEditBtnStatus(false);
                        createCoursePanel.exitEditMode();

                        JOptionPane.showMessageDialog(this, "The Course:" + " " + selectedCourse.getCourseName() + " "
                                + "has been succesfully deleted");
                        courseManager.deleteCourse(selectedCourse);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Could not delete course", "Failed to delete",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        createCourseListPanel.createCourseElement();
    }

    // @FunctionalInterface
    // public interface OnClickListener<T> {
    // public void onClick(T listener);
    // }

}
