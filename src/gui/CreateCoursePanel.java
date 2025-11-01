package gui;

import enums.Credits;
import exception.CourseException;
import gui.button.ButtonManager;
import Service.CourseManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

/**
 * Panel for creating a course with input fields, radiobuttons and create/save
 * changes button
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-10-30
 */
public class CreateCoursePanel extends JPanel {

    private final JLabel createCourseLbl = new JLabel("Create Course");
    // private final JLabel subtitle = new JLabel("Create and manage academic
    // courses");

    private Course course;
    private Course editingCourse;

    private final JTextField tfName = new JTextField("JAVA");
    private final JComboBox<Credits> cbCredits = new JComboBox<>(Credits.values());

    private JRadioButton campusRdBtn = new JRadioButton("Campus");
    private JRadioButton onlineRdBtn = new JRadioButton("Online");

    private final JTextField tfOverView = new JTextField("Dags för årets första java kurs...");

    private ImageIcon createIcon = new ImageIcon("src\\icons\\plus.png");
    private JButton createBtn;

    private CourseManager courseManager;
    private CreateCourseListPanel createCourseListPanel;
    private ButtonManager buttonManager;
    // private OnClickListener<CreateCoursePanel> onClickListener;

    /**
     * initialize the course form and layout.
     */
    public CreateCoursePanel(CourseManager courseManager, CreateCourseListPanel createCourseListPanel,
            ButtonManager buttonManager) {
        this.courseManager = courseManager;
        this.createCourseListPanel = createCourseListPanel;
        this.buttonManager = buttonManager;

        setBackground(Color.lightGray);
        // setLayout(new BorderLayout(8, 8));

        // JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        // setSize(300, 500);
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
        // form.setAlignmentX(Component.LEFT_ALIGNMENT);
        createCourseLbl.setFont(new Font("SanssSerif", Font.BOLD, 25));
        form.add(createCourseLbl);
        form.add(Box.createVerticalStrut(10));

        form.add(new JLabel("Course name:"), BorderLayout.WEST);
        form.add(tfName, BorderLayout.WEST);
        form.add(Box.createVerticalStrut(10));

        form.add(new JLabel("Credits"));
        form.add(cbCredits, BorderLayout.WEST);
        form.add(Box.createVerticalStrut(10));

        // Course type and radio buttons
        JLabel selectCourseTypeLbl = new JLabel("Select Course type");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(campusRdBtn);
        buttonGroup.add(onlineRdBtn);

        form.add(selectCourseTypeLbl);
        form.add(campusRdBtn);
        form.add(onlineRdBtn);
        form.add(Box.createVerticalStrut(10));

        form.add(new JLabel("Course overview"));
        form.add(tfOverView);

        createBtn = new JButton("Create Course", createIcon);
        form.add(createBtn);

        this.add(form, BorderLayout.WEST);

        // Button functionality
        handleCreateAndSaveBtn();
    }

    /**
     * Decides if a new course should be created or an existing course should be
     * updated
     */
    private void handleCreateAndSaveBtn() {
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (editingCourse == null) {
                        createCourse();
                    } else {
                        saveSelectedCourseChanges();
                    }
                } catch (CourseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CreateCoursePanel.this, " Error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Updates the selected course with the new user input values
     */
    private void saveSelectedCourseChanges() throws CourseException {
        try {

            Credits selectedCredit = (Credits) cbCredits.getSelectedItem();
            editingCourse.setCourseName(tfName.getText());
            editingCourse.setCredits(selectedCredit);
            editingCourse.setOverview(tfOverView.getText());

            courseManager.updateCourse(editingCourse);
            createCourseListPanel.createCourseElement();
            exitEditMode();

        } catch (CourseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new course object and adds it to the course list.
     */
    public void createCourse() {

        String name = tfName.getText();
        Credits credit = (Credits) cbCredits.getSelectedItem();
        String overview = tfOverView.getText();

        if (name.isBlank() || name == null) {
            JOptionPane.showMessageDialog(this, "Course must have a name");
            return;
        }
        if (campusRdBtn.isSelected()) {
            course = new CampusCourse(name, credit, overview);
        } else if (onlineRdBtn.isSelected()) {
            course = new OnlineCourse(name, credit, overview);
        } else {
            JOptionPane.showMessageDialog(this, " Select course type");
            return;
        }
        if (overview.isBlank() || overview == null) {
            JOptionPane.showMessageDialog(this, "Course must have overview information");
            return;
        }

        courseManager.addCourseToList(course);
        createCourseListPanel.createCourseElement();
        resetForm();
    }

    /**
     * Enters edit mode for the selected course.
     */
    public void enterEditMode(Course c) {
        tfName.setText(c.getCourseName());
        cbCredits.setSelectedItem(c.getCredits());
        tfOverView.setText(c.getOverview());
        this.editingCourse = c;
        createBtn.setText("Save changes");

        revalidate();
        repaint();
    }

    /**
     * Resets the input fields to default values.
     */
    public void exitEditMode() {
        this.editingCourse = null;
        resetForm();
        createBtn.setText("Create course");
        buttonManager.setEditBtnStatus(false);
        buttonManager.setDeleteBtnStatus(false);

    }

    private void resetForm() {
        tfName.setText("");
        campusRdBtn.setSelected(true);
        onlineRdBtn.setSelected(false);
        tfOverView.setText("");
    }
}
