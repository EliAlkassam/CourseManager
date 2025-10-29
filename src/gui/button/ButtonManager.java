package gui.button;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Service.CourseManager;
import gui.CreateCoursePanel;

public class ButtonManager {
    private JButton editButton;
    private JPanel buttonPanel = new JPanel();

    private CourseManager courseManager;
    private CreateCoursePanel createCoursePanel;

    public ButtonManager() {
        editButton = new JButton("Edit course");
        editButton.setEnabled(false);
        buttonPanel.add(editButton);
        System.out.println(" inside Button ctor");
        // this.courseManager = courseManager;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setEditBtnEnabled(boolean status) {
        editButton.setEnabled(status);
    }

    public JButton getEditButton() {
        return editButton;
    }

    private ActionListener loadCourseInfoAction() {
        return al -> {

        };
    }

    private void changeCreatebtnToSaveChangesBtn() {
        JButton btn = createCoursePanel.getCreateBtn();

    }

}
