package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import Service.CourseManager;
import exception.CourseException;
import gui.button.ButtonManager;
import model.Course;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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

    private CreateCoursePanel createCoursePanel;
    private CreateCourseListPanel createCourseListPanel;

    public AppFrame() {
        initialize();
    }

    public void setMinimumFrame(Dimension minumumSize) {
        super.setMinimumSize(minumumSize);
    }

    private void initialize() {
        setupFrameProperties();

        ButtonManager buttonManager = new ButtonManager();
        add(buttonManager.getButtonPanel(), BorderLayout.SOUTH);

        createCourseListPanel = new CreateCourseListPanel(courseManager, buttonManager);
        createCoursePanel = new CreateCoursePanel(courseManager, createCourseListPanel, buttonManager);

        this.add(createCoursePanel, BorderLayout.WEST);
        this.add(createCourseListPanel, BorderLayout.CENTER);

        setupMenuBar();
        setUpButtonActions(buttonManager);

        createCourseListPanel.createCourseElement();
    }

    private void setupFrameProperties() {
        setTitle(APP_NAME);
        setSize(900, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

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
                String courseName = selectedCourse.getCourseName();
                if (selectedCourse != null) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to delete course:" + " " + courseName + "?",
                            "Delete course",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        // createCourseListPanel.createCourseElement();
                        buttonManager.setDeleteBtnStatus(false);
                        buttonManager.setEditBtnStatus(false);
                        createCoursePanel.exitEditMode();

                        courseManager.deleteCourse(selectedCourse);
                        JOptionPane.showMessageDialog(this, "The Course:" + " " + courseName + " "
                                + "has been succesfully deleted");
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
