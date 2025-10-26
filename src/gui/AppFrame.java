package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;

public class AppFrame extends JFrame {

    public static final String APP_NAME = "Course Manager";
    private Container c = this.getContentPane();

    // JPanel createFrame;

    CreateCoursePanel createCoursePanel;

    public AppFrame() {
        initialize();
    }

    public void setMinumumFrame(Dimension minumumSize) {
        super.setMinimumSize(minumumSize);
    }

    private void initialize() {

        setSize(900, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(APP_NAME);
        setLayout(new BorderLayout());

        // Rubrik
        JLabel title = new JLabel("Course manager");
        JLabel subtitle = new JLabel("Create and manage academic courses");

        JTextField tfName = new JTextField("Enter course name");

        createCoursePanel = new CreateCoursePanel();
        // createFrame.setBackground(Color.BLUE);

        createCoursePanel.setPreferredSize(new Dimension(450, 350));

        // JLabel label = new JLabel("Create and manage your academic courses");
        // add(label, BorderLayout.CENTER);

        // JLabel courseNameLbl = new JLabel("Course Name");

        // createCoursePanel.add(courseNameLbl, BorderLayout.WEST);
        this.add(createCoursePanel, BorderLayout.WEST);

        // setBackground(Color.BLACK);
        // label.setVerticalTextPosition(JLabel.CENTER);

    }

    @FunctionalInterface
    public interface OnClickListener<T> {
        public void onClick(T listener);
    }

}
