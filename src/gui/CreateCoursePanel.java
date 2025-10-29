package gui;

import enums.Credits;
import gui.button.ButtonManager;
import Service.CourseManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.CipherInputStream;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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

// Panel for creating a course with fields, radiobuttons and create/save changes button
public class CreateCoursePanel extends JPanel {

    // private final JLabel title = new JLabel("Course manager");
    // private final JLabel subtitle = new JLabel("Create and manage academic
    // courses");

    private Course course;
    private Course editingCourse;

    private final JTextField tfName = new JTextField("JAVA");

    private final JComboBox<Credits> cbCredits = new JComboBox<>(Credits.values());

    JRadioButton campusRdBtn = new JRadioButton("Campus");
    JRadioButton onlineRdBtn = new JRadioButton("Online");

    private final JTextField tfOverView = new JTextField("Dags för årets första java kurs...");
    private JButton createBtn = new JButton("Create Course");

    private CourseManager courseManager;
    private CreateCourseListPanel createCourseListPanel;
    private ButtonManager buttonManager;
    // private OnClickListener<CreateCoursePanel> onClickListener;

    public CreateCoursePanel(CourseManager courseManager, CreateCourseListPanel createCourseListPanel,
            ButtonManager buttonManager) {
        this.courseManager = courseManager;
        this.createCourseListPanel = createCourseListPanel;
        this.buttonManager = buttonManager;

        setBackground(Color.LIGHT_GRAY);
        // setLayout(new BorderLayout(8, 8));

        // JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        // setSize(300, 500);
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
        // form.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(new JLabel("Course name:"), BorderLayout.WEST);
        form.add(tfName, BorderLayout.WEST);

        form.add(new JLabel("Credits"));
        form.add(cbCredits, BorderLayout.WEST);

        JLabel selectCourseTypeLbl = new JLabel("Select Course type");
        ButtonGroup buttonGroup = new ButtonGroup();

        // buttonGroup.( new BorderLayout());
        buttonGroup.add(campusRdBtn);
        buttonGroup.add(onlineRdBtn);

        // selectCourseTypeLbl.setLabelFor(buttonGroup);
        form.add(selectCourseTypeLbl);
        // form.add(new JLabel ("Select Course type"));
        form.add(campusRdBtn);
        form.add(onlineRdBtn);

        form.add(new JLabel("Course overview"));
        form.add(tfOverView);

        form.add(createBtn);

        this.add(form, BorderLayout.WEST);

        // createBtn.addActionListener(e -> createCourse());

        handleCreateAndSaveBtn();

    }

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
                } catch (Exception ex) {
                    ex.getMessage();
                    ex.printStackTrace();
                }
            }
        });
    }

    private void saveSelectedCourseChanges() throws Exception {
        try {
            editingCourse.setCourseName(tfName.getText());
            // editingCourse.setCredits(cbCredits.getSelectedItem());
            editingCourse.setOverview(tfOverView.getText());

            courseManager.updateCourse(editingCourse);
            createCourseListPanel.createCourseElement();
            exitEditMode();

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void createCourse() {

        String name = tfName.getText();
        Credits credit = (Credits) cbCredits.getSelectedItem();
        String overview = tfOverView.getText();

        // Course course;
        if (campusRdBtn.isSelected()) {
            course = new CampusCourse(name, credit, overview);
        } else if (onlineRdBtn.isSelected()) {
            course = new OnlineCourse(name, credit, overview);
        } else {
            JOptionPane.showMessageDialog(this, " Select course type");
            return;
        }
        courseManager.addCourseToList(course);
        createCourseListPanel.createCourseElement();

        resetForm();
    }

    public void getCourseForEditMode(Course c) {
        tfName.setText(c.getCourseName());
        cbCredits.setSelectedItem(c.getCredits());
        tfOverView.setText(c.getOverview());
        editingCourse = c;
        createBtn.setText("Save changes");
        revalidate();
        repaint();
        // System.out.println("tfname:" + tfName.getText());
        // System.out.println("cbCredits är: " + cbCredits.getSelectedItem());
        // System.out.println("tfOverview:" + tfOverView.getSelectedText());

    }

    public void exitEditMode() {
        // resets
        editingCourse = null;
        resetForm();
        createBtn.setText("Create course");
        buttonManager.setEditBtnStatus(false);
        buttonManager.setDeleteBtnStatus(false);

    }

    private void resetForm() {
        tfName.setText("");
        campusRdBtn.setSelected(false);
        onlineRdBtn.setSelected(false);
        tfOverView.setText("");
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public Course getCourse() {
        return this.course;
    }

}
