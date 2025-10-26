package gui;

import java.awt.*;

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

import gui.AppFrame.OnClickListener;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

public class CreateCoursePanel extends JPanel {

    // private final JLabel title = new JLabel("Course manager");
    // private final JLabel subtitle = new JLabel("Create and manage academic
    // courses");

    private final JTextField tfName = new JTextField();

    private final JComboBox<Credits> cbCredits = new JComboBox<>(Credits.values());

    JRadioButton campusRdBtn = new JRadioButton("Campus");
    JRadioButton onlineRdBtn = new JRadioButton("Online");

    private final JTextField tfOverView = new JTextField();
    private final JButton createBtn = new JButton("Create Course");

    // private OnClickListener<CreateCoursePanel> onClickListener;

    public CreateCoursePanel() {
        setBackground(Color.LIGHT_GRAY);
        // setLayout(new BorderLayout(8, 8));

        // JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(new JLabel("Name:"), BorderLayout.WEST);
        form.add(tfName, BorderLayout.WEST);

        form.add(new JLabel("Credits"));
        form.add(cbCredits, BorderLayout.WEST);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(campusRdBtn);
        buttonGroup.add(onlineRdBtn);
        form.add(new JLabel("Course type"));
        form.add(campusRdBtn);
        form.add(onlineRdBtn);

        form.add(new JLabel("Course overview"));
        form.add(tfOverView);

        form.add(createBtn);

        this.add(form, BorderLayout.WEST);

        createBtn.addActionListener(e -> createCourse());

    }

    private void createCourse() {

        String name = tfName.getText();
        Credits credit = (Credits) cbCredits.getSelectedItem();
        String overview = tfOverView.getText();

        Course course;
        if (campusRdBtn.isSelected()) {
            course = new CampusCourse(name, credit, overview);
        } else if (onlineRdBtn.isSelected()) {
            course = new OnlineCourse(name, credit, overview);
        } else {
            JOptionPane.showMessageDialog(this, " Select course type");
            return;
        }
    }

}
