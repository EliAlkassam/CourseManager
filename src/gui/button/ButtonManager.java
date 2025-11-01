package gui.button;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Manages edit and delete buttons for course functions.
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-09-27
 */

public class ButtonManager {
    private ImageIcon editIcon = new ImageIcon("src\\icons\\pen.png");
    private ImageIcon deleteIcon = new ImageIcon("src\\icons\\bin.png");

    private JButton editButton;
    private JButton deleteButton;
    private JPanel buttonPanel;

    public ButtonManager() {
        initButtons();
        // this.courseManager = courseManager;
    }

    private void initButtons() {
        buttonPanel = new JPanel();
        editButton = new JButton("Edit course", editIcon);
        editButton.setEnabled(false);

        deleteButton = new JButton("Delete course", deleteIcon);
        deleteButton.setEnabled(false);

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JButton getEditBtn() {
        return editButton;
    }

    public void setEditBtnStatus(boolean status) {
        editButton.setEnabled(status);
    }

    public JButton getDeleteBtn() {
        return deleteButton;
    }

    public void setDeleteBtnStatus(boolean status) {
        deleteButton.setEnabled(status);
    }

    private ActionListener loadCourseInfoAction() {
        return al -> {
            // TODO
        };
    }

    private void changeCreatebtnToSaveChangesBtn() {
        // TODO
    }

}
