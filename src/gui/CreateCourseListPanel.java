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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Service.CourseManager;
import gui.button.ButtonManager;
import model.Course;

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

    public CreateCourseListPanel(CourseManager courseManager, ButtonManager buttonManager) {
        this.courseManager = courseManager;
        this.buttonManager = buttonManager;
        // this.createCoursePanel = createCoursePanel;

        // ny
        // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

    }

    // public JPanel getCourseJPanel() {
    // return jPanel;
    // }

    public void createCourseElement() {
        // removeAll();
        contentPanel.removeAll();
        // JPanel courseElement = new JPanel();
        // courseElement.setLayout(new BoxLayout(courseElement, BoxLayout.Y_AXIS));

        for (Course c : courseManager.getCoursesList()) {
            JPanel row = createCourseRow(c);
            contentPanel.add(row);
            contentPanel.add(Box.createVerticalStrut(10));

        }
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
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true), // tunn grå ram med rundade hörn
                BorderFactory.createEmptyBorder(10, 15, 10, 15))); // inre padding

        row.setOpaque(true);
        row.setBackground(new Color(245, 245, 245));

        row.add(new JLabel(String.valueOf(c.getId()) + "."));

        JLabel courseNameLbl = new JLabel(c.getCourseName());
        courseNameLbl.setFont(new Font("SanssSerif", Font.BOLD, 17));
        row.add(courseNameLbl);

        row.add(new JLabel(c.getCredits().toString() + "points"));

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
                    selectedRow.setBackground(Color.GREEN);
                }

                row.setBackground(Color.LIGHT_GRAY);
                selectedRow = row;
                selectedCourse = c;
                buttonManager.setEditBtnStatus(true);
                buttonManager.setDeleteBtnStatus(true);
            }
        });
        return row;
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
