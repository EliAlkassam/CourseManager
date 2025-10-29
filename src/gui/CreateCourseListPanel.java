package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Service.CourseManager;
import gui.button.ButtonManager;
import model.Course;

// generates courses and displays in ui 
public class CreateCourseListPanel extends JPanel {

    // private JPanel jPanel = new JPanel();
    // private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));

    private final CourseManager courseManager;
    private ButtonManager buttonManager;
    // private CreateCoursePanel createCoursePanel;
    private Course selectedCourse;
    private JPanel selectedRow;

    public CreateCourseListPanel(CourseManager courseManager, ButtonManager buttonManager) {
        this.courseManager = courseManager;
        this.buttonManager = buttonManager;
        // this.createCoursePanel = createCoursePanel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    // public JPanel getCourseJPanel() {
    // return jPanel;
    // }

    public void createCourseElement() {
        removeAll();
        // JPanel courseElement = new JPanel();
        // courseElement.setLayout(new BoxLayout(courseElement, BoxLayout.Y_AXIS));

        for (Course c : courseManager.getCoursesList()) {

            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
            row.setBackground(Color.GREEN);

            row.add(new JLabel("Course name:" + c.getCourseName()));
            row.add(new JLabel(c.getCredits().toString()));
            row.add(new JLabel(c.toStringType()));
            row.add(new JLabel(c.getOverview()));

            add(row);
            // row.add(addMouseListener(MouseEvent.MOUSE_CLICKED));

            row.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    // avmarkera
                    if (selectedRow != null) {
                        selectedRow.setBackground(Color.GREEN);
                    }

                    row.setBackground(Color.LIGHT_GRAY);
                    selectedRow = row;
                    selectedCourse = c;
                    buttonManager.setEditBtnStatus(true);
                    buttonManager.setDeleteBtnStatus(true);
                    // System.err.println("Klickad på:" + selectedCourse);
                }
            });

            // editBtn.addActionListener(new ActionListener() {
            // @Override
            // public void actionPerformed(ActionEvent e) {
            // System.err.println("hej");

            // JLabel courseNameLbl = new JLabel(c.getCourseName());
            // JLabel creditLbl = new JLabel(c.getCredits().toString());
            // JLabel typeLbl = new JLabel(c.getClass().getSimpleName());
            // JLabel courseOverviewLbl = new JLabel(c.getOverview());

            // jPanel.add(courseNameLbl);
            // jPanel.add(creditLbl);
            // jPanel.add(typeLbl);
            // jPanel.add(courseOverviewLbl);

            // form.add(jPanel);
        }
        revalidate();
        repaint();

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
