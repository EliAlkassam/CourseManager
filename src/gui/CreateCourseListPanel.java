package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Service.CourseManager;
import gui.button.ButtonManager;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

/**
 * Panel that displays all created courses dynamically
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-01-30
 */
public class CreateCourseListPanel extends JPanel {

    private final CourseManager courseManager;
    private ButtonManager buttonManager;

    private Course selectedCourse;
    private JPanel selectedRow;

    private JPanel contentPanel;
    private JScrollPane scrollPane;

    private Predicate<Course> courseFilter; // All courses

    private JComboBox<String> comboBox;
    private String[] courses = { "All", "Campus", "Online" };

    public CreateCourseListPanel(CourseManager courseManager, ButtonManager buttonManager) {
        this.courseManager = courseManager;
        this.buttonManager = buttonManager;
        courseFilter = s -> true;

        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        comboBox = new JComboBox<>(courses);
        comboBox.setSelectedItem(courses[0]);
        this.add(comboBox, BorderLayout.NORTH);
        comboBox.setEnabled(false);

    }

    public void createCourseElement() {
        contentPanel.removeAll();

        List<Course> streamCourses = courseManager.getCoursesList();
        if (streamCourses.size() <= 0) {
            comboBox.setEnabled(false);
        }

        streamCourses.stream().filter(courseFilter)
                .forEach(s -> contentPanel.add(createCourseRow(s)));
        contentPanel.add(Box.createVerticalStrut(10));

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Creates a row for every course, with mouse selection listner.
     */
    private JPanel createCourseRow(Course c) {

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setLayout(new BoxLayout(row, BoxLayout.PAGE_AXIS));

        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 3, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 25)));

        row.setOpaque(true);
        row.setBackground(new Color(245, 245, 245));

        JLabel courseNameLbl = new JLabel(String.valueOf(c.getId()) + "." + " " + c.getCourseName());
        courseNameLbl.setFont(new Font("SanssSerif", Font.BOLD, 17));
        row.add(courseNameLbl);

        row.add(new JLabel(c.getCredits().toString() + " " + "credits"));

        row.add(new JLabel(c.toStringType(c)));

        row.add(new JLabel(c.getOverview()));

        row.setPreferredSize(new Dimension(320, 100));
        row.setMaximumSize(new Dimension(320, 100));
        row.setMinimumSize(new Dimension(320, 100));

        contentPanel.add(row);

        row.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // unmark row
                if (selectedRow != null) {
                    selectedRow.setBackground((new Color(245, 245, 245)));
                }

                row.setBackground(Color.LIGHT_GRAY);
                selectedRow = row;
                selectedCourse = c;
                buttonManager.setEditBtnStatus(true);
                buttonManager.setDeleteBtnStatus(true);
            }
        });
        comboBox.setEnabled(true);
        initFilterComboBox();
        return row;

    }

    private void initFilterComboBox() {

        comboBox.addActionListener(event -> {
            buttonManager.setDeleteBtnStatus(false);
            buttonManager.setEditBtnStatus(false);
            selectedCourse = null;
            selectedRow = null;

            JComboBox<String> cb = (JComboBox<String>) event.getSource();
            String selectedOption = (String) cb.getSelectedItem();

            if (selectedOption.equals("Campus")) {
                courseFilter = s -> s instanceof CampusCourse;
            } else if (selectedOption.equals("Online")) {
                courseFilter = s -> s instanceof OnlineCourse;
            } else {
                courseFilter = s -> true;
            }
            createCourseElement();

        });
    }

    public void resetCourseListPanel() {
        contentPanel.removeAll();
        comboBox.setEnabled(false);
        buttonManager.setDeleteBtnStatus(false);
        buttonManager.setEditBtnStatus(false);

        selectedCourse = null;
        selectedRow = null;
        courseManager.setCourses(new ArrayList<>());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }
}
