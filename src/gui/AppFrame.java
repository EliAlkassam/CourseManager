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
import exception.CourseException;
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

/**
 * Main frame for the Course Manager application
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-01-30
 */
public class AppFrame extends JFrame {

    public static final String APP_NAME = "Course Manager";

    private final CourseManager courseManager = new CourseManager();
    private Container c = this.getContentPane();

    private CreateCoursePanel createCoursePanel;
    private CreateCourseListPanel createCourseListPanel;

    // private CourseManager courseManager;

    public AppFrame() {
        initialize();
        // this.courseManager = courseManager;
    }

    public void setMinimumFrame(Dimension minumumSize) {
        super.setMinimumSize(minumumSize);
    }

    private void initialize() {
        setupFrameProperties();
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

        setupMenuBar();

        setUpButtonActions(buttonManager);

        // // ???
        // createCourseListPanel.createCourseElement();
        // Course selectedCourse = createCourseListPanel.getSelectedCourse();

        createCourseListPanel.createCourseElement();
    }

    private void setupFrameProperties() {
        setTitle(APP_NAME);
        setSize(900, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    }

    /**
     * Adds the menu bar items by using MenuManager.
     */
    private void setupMenuBar() {
        MenuManager menuManager = new MenuManager(this, createCoursePanel, createCourseListPanel, courseManager);
        setJMenuBar(menuManager.getMenu());
    }

    /**
     * Set up actions for edit and delete buttons.
     */
    private void setUpButtonActions(ButtonManager buttonManager) {
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
                        createCourseListPanel.createCourseElement();
                    }
                }
            } catch (CourseException ex) {
                JOptionPane.showMessageDialog(this, "Could not delete course", "Failed to delete",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}
