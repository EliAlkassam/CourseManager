package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

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

    // private JPanel jPanel = new JPanel();
    // private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));

    private final CourseManager courseManager;
    private ButtonManager buttonManager;
    // private CreateCoursePanel createCoursePanel;
    private Course selectedCourse;
    private JPanel selectedRow;
    // private ArrayList<Course> courseList;
    // ny
    private JPanel contentPanel;
    private JScrollPane scrollPane;

    private Predicate<Course> courseFilter; // All courses

    private JComboBox<String> comboBox;
    String[] courses = { "All", "Campus", "Online" };

    public CreateCourseListPanel(CourseManager courseManager, ButtonManager buttonManager) {
        this.courseManager = courseManager;
        this.buttonManager = buttonManager;
        // this.createCoursePanel = createCoursePanel;
        courseFilter = s -> true;
        // ny
        // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

    // public JPanel getCourseJPanel() {
    // return jPanel;
    // }

    public void createCourseElement() {
        // removeAll();
        contentPanel.removeAll();
        // JPanel courseElement = new JPanel();
        // courseElement.setLayout(new BoxLayout(courseElement, BoxLayout.Y_AXIS));
        // JPanel row;
        List<Course> streamCourses = courseManager.getCoursesList();
        if (streamCourses.size() <= 0) {
            comboBox.setEnabled(false);
        }

        streamCourses.stream().filter(courseFilter)
                .forEach(s -> contentPanel.add(createCourseRow(s)));
        contentPanel.add(Box.createVerticalStrut(10));

        // for (Course c : courseManager.getCoursesList()) {
        // // JPanel row = createCourseRow(c);
        // // contentPanel.add(row);
        // contentPanel.add(Box.createVerticalStrut(10));

        // }
        // revalidate();
        // repaint();

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Creates a row for every course, with mouse selection listner.
     */
    private JPanel createCourseRow(Course c) {

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setLayout(new BoxLayout(row, BoxLayout.PAGE_AXIS));
        // row.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // topp,
        // vänster, botten, höger
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 3, true), // tunn grå ram med rundade hörn
                BorderFactory.createEmptyBorder(10, 15, 10, 25))); // inre padding

        row.setOpaque(true);
        row.setBackground(new Color(245, 245, 245));

        // row.add(new JLabel(String.valueOf(c.getId()) + "."));

        JLabel courseNameLbl = new JLabel(String.valueOf(c.getId()) + "." + " " + c.getCourseName());
        courseNameLbl.setFont(new Font("SanssSerif", Font.BOLD, 17));
        row.add(courseNameLbl);

        row.add(new JLabel(c.getCredits().toString() + " " + "credits"));

        row.add(new JLabel(c.toStringType(c)));

        row.add(new JLabel(c.getOverview()));

        row.setPreferredSize(new Dimension(320, 100));
        row.setMaximumSize(new Dimension(320, 100));
        row.setMinimumSize(new Dimension(320, 100));

        // add(row);
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

    public void initFilterComboBox() {

        comboBox.addActionListener(event -> {
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
        // private Predicate<CampusCourse> campusFilter = s -> s instanceof
        // CampusCourse;
        // private Predicate<OnlineCourse> onlineFilter = s -> s instanceof
        // OnlineCourse;
        // private Predicate<Course> allFilter = s -> s instanceof Course;

        // private JComboBox<Predicate> comboBox;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }
    // class CustomMouseAdapter extends MouseAdapter {
    // @Override
    // public void mousePressed(MouseEvent e) {
    // if (e == null) {

    // }
    // // selectedCourse =
    // }
    // }
}
