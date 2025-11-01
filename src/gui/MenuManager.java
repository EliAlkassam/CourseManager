package gui;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JFileChooser;

import javax.swing.JOptionPane;

import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Service.CourseManager;
import gui.button.ButtonManager;
import util.FileHandler;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

/**
 * Manages the items in Menu.
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-09-27
 */
public class MenuManager {

	private AppFrame frame;
	private CreateCoursePanel createCoursePanel;
	private CreateCourseListPanel createCourseListPanel;
	private CourseManager courseManager;
	private Menu menu;

	String folderPath = "\\src\\created_files";

	public MenuManager(AppFrame frame, CreateCoursePanel createCoursePanel, CreateCourseListPanel createCourseListPanel,
			CourseManager courseManager) {
		this.frame = frame;
		this.createCoursePanel = createCoursePanel;
		this.createCourseListPanel = createCourseListPanel;
		this.courseManager = courseManager;
		this.menu = new Menu();
		createMenu();
	}

	public Menu getMenu() {
		return menu;
	}

	private void createMenu() {
		createFileMenu();
	}

	private void createFileMenu() {
		String sFile = "File";
		menu.addJMenu(sFile);
		menu.getJMenu(0).setMnemonic(KeyEvent.VK_F);

		// menu.addJMenuItem(sFile, "New...", createNewDrawingAction(),
		// KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		menu.addJMenuItem(sFile, "Load...", createLoadAction(),
				KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
		menu.addJMenuItem(sFile, "Save As...", SaveAsAction(),
				KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		// menu.addJMenuItem(sFile, "Info", showInfoAction());

		menu.getJMenu(0).addSeparator();
		menu.addJMenuItem(sFile, "Exit", al -> System.exit(0));

	}

	private ActionListener createLoadAction() {
		return al -> {

			String projectMap = System.getProperty("user.dir") + folderPath;
			JFileChooser chooser = new JFileChooser(projectMap);

			// creates a filter that only accepts text files
			FileFilter txtFilter = new FileNameExtensionFilter("Text files", "txt");
			chooser.addChoosableFileFilter(txtFilter);

			// Remove/comment the below row if you also want the default 'All files' filter
			chooser.setAcceptAllFileFilterUsed(false);

			int option = chooser.showOpenDialog(frame);

			if (option == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				String filePath = selectedFile.getAbsolutePath();

				try {
					List<Course> courselLists = FileHandler.load(filePath);
					courseManager.setCourses(courselLists);
					createCourseListPanel.createCourseElement();
					frame.setTitle(selectedFile.getName());

				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(frame, "File not found:" + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "File not found:" + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
			frame.repaint();
		};
	}

	private ActionListener SaveAsAction() {
		return al -> {

			List<Course> courseList = courseManager.getCoursesList();
			String projectMap = System.getProperty("user.dir") + folderPath;
			if (courseList.size() <= 0) {
				JOptionPane.showMessageDialog(frame, "You have to create a course before saving it" + " ");
				return;
			}
			try {
				JFileChooser fc = new JFileChooser(projectMap);

				int option = fc.showSaveDialog(frame);
				File selectedFile = fc.getSelectedFile();
				String filePath = selectedFile.getAbsolutePath();
				if (option == JFileChooser.APPROVE_OPTION) {

					FileHandler.save(courseList, filePath);
					createCourseListPanel.resetCourseListPanel();
					JOptionPane.showMessageDialog(frame, "Succesfully saved the file:" + " " + selectedFile.getName());
				}

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frame, "Failed to save the file:" + " " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "Failed to save the file:" + " " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

		};
	}

}
