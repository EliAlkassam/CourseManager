package gui;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Service.CourseManager;
import util.FileHandler;
import model.Course;

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
	private Menu menu;

	// private Predicate<Shape> circleFilter = s -> s instanceof Circle;
	// private Predicate<Shape> rectangleFilter = s -> s instanceof Rectangle;
	// private Predicate<Shape> allFilter = s -> s instanceof Shape;

	private CourseManager courseManager;

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
		// createEditMenu();
		// createFilterMenu();
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

	// private void createEditMenu() {
	// String sEdit = "Edit";
	// String sDrawing = "Drawing";
	// menu.addJMenu(sEdit);
	// menu.addSubJMenu(sEdit, sDrawing);
	// menu.getJMenu(1).setMnemonic(KeyEvent.VK_E);

	// // menu.addJMenuItem(sEdit, "Undo", createUndoAction(),
	// KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
	// // menu.addJMenuItem(sDrawing, "Name...", createChangeNameAction());
	// // menu.addJMenuItem(sDrawing, "Author...", createChangeAuthorAction());

	// /*
	// * Denna rad, som du inte får ta bort, kommer skapa ett NullException.
	// * Du måste hantera denna situation i Menu-klassen. I vanliga fall
	// * hade det varit rimligt att ett Exception kastades (klienten bör
	// * i vanliga fall göras medveten om att den försöker skapa ett
	// * JMenuItem till en JMenu som inte existerar), men nu räcker
	// * det med att ingenting alls händer i det läget man anropar
	// * addJMenuItem med en sträng som inte kan hittas.
	// */
	// menu.addJMenuItem("This JMenu doesn't exist", "abc");
	// }

	// private void createFilterMenu() {

	// JRadioButton allButton = new JRadioButton("All");
	// allButton.setSelected(true);
	// JRadioButton circleButton = new JRadioButton("Circle(s)");
	// circleButton.setSelected(true);
	// JRadioButton rectangleButton = new JRadioButton("Rectangle(s)");
	// rectangleButton.setSelected(true);

	// createCoursePanel.setShapeFilter(allFilter);

	// // register listner for the radio buttons
	// allButton.addActionListener(event -> {
	// createCoursePanel.setShapeFilter(allFilter);
	// });

	// rectangleButton.addActionListener(event -> {
	// createCoursePanel.setShapeFilter(rectangleFilter);
	// });
	// circleButton.addActionListener(event -> {
	// createCoursePanel.setShapeFilter(circleFilter);
	// });

	// @SuppressWarnings("serial")
	// List<JRadioButton> radioButtons = new ArrayList<JRadioButton>() {
	// {
	// add(allButton);
	// add(circleButton);
	// add(rectangleButton);
	// }
	// };

	// JMenu jMenu = new JMenu("Filter");
	// ButtonGroup group = new ButtonGroup();

	// for (var rb : radioButtons) {
	// jMenu.add(rb);
	// group.add(rb);
	// }
	// menu.add(jMenu);
	// }

	/*
	 * Flera av metoderna nedan kommer anropa JOptionPane.showInputDialog(...).
	 * Denna metod returnerar en String. Tänk på att om användaren trycker på
	 * "Cancel" så kommer null att returneras. När en användare trycker på "Cancel"
	 * så ska givetvis ingenting alls hända; inget felmeddelande till användaren,
	 * inget ändring av det grafiska gränssnittets tillstånd (en teckning ska
	 * inte plötsligt få namnet "null"). Jag har sett många inlämningar där
	 * "Cancel" har hanterats på tämligen oväntade sätt. Så håll det i åtanke,
	 * att Cancel/Avbryt innebär just den saken.
	 * 
	 */

	// private ActionListener createNewDrawingAction() {
	// return al -> {

	// frame.repaint();
	// Course newDrawing = new Course();

	// String drawingName = JOptionPane.showInputDialog(createCoursePanel, "Enter
	// name of the DRAWAING");

	// try {
	// if (drawingName == null) {
	// return; // cancel click
	// }
	// while (drawingName.isEmpty() || drawingName == null) {
	// JOptionPane.showMessageDialog(createCoursePanel, " A drawing MUST have a
	// name");
	// drawingName = JOptionPane.showInputDialog(createCoursePanel, "Enter name of
	// the DRAWAING");
	// }
	// newDrawing.setName(drawingName);

	// } catch (Exception e) {
	// JOptionPane.showMessageDialog(createCoursePanel, e.getMessage());
	// e.printStackTrace();
	// }

	// String authorName = JOptionPane.showInputDialog(createCoursePanel, "Enter
	// authorname");
	// try {
	// if (authorName == null) {
	// return;
	// }
	// while (authorName.isEmpty() || authorName == null) {
	// JOptionPane.showMessageDialog(createCoursePanel, " A author MUST have a
	// name");
	// authorName = JOptionPane.showInputDialog(createCoursePanel, " Enter
	// authorname");
	// }
	// newDrawing.setAuthor(authorName);
	// createCoursePanel.setDrawing(newDrawing);
	// frame.setDrawingTitle(drawingName, authorName);

	// } catch (DrawingException e) {
	// JOptionPane.showMessageDialog(createCoursePanel, e.getMessage());
	// e.printStackTrace();

	// }
	// };
	// }

	// private ActionListener createChangeNameAction() {
	// return al -> {

	// try {
	// Drawing currentDrawing = createCoursePanel.getDrawing();

	// String newDrawingName = JOptionPane.showInputDialog(createCoursePanel,
	// "Change name of the current drawing name:" + currentDrawing.getName());
	// if (newDrawingName == null) {
	// return;
	// }
	// currentDrawing.setName(newDrawingName);
	// frame.setDrawingTitle(newDrawingName, currentDrawing.getAuthor());
	// } catch (DrawingException e) {
	// JOptionPane.showMessageDialog(createCoursePanel, e.getMessage());
	// e.printStackTrace();
	// }
	// };

	// }

	// private ActionListener createChangeAuthorAction() {
	// return al -> {

	// try {
	// Drawing currentDrawing = createCoursePanel.getDrawing();

	// String newAuthorName = JOptionPane.showInputDialog(createCoursePanel,
	// "Change name of the current author: " + currentDrawing.getAuthor());
	// if (newAuthorName == null) {
	// return;
	// }

	// currentDrawing.setAuthor(newAuthorName);
	// frame.setDrawingTitle(currentDrawing.getName(), newAuthorName);

	// } catch (DrawingException e) {
	// JOptionPane.showMessageDialog(createCoursePanel, e.getMessage());
	// e.printStackTrace();
	// }
	// };
	// }

	// private ActionListener createUndoAction() {
	// return al -> {
	// try {
	// Drawing currentDrawing = createCoursePanel.getDrawing();
	// createCoursePanel.removeShape(currentDrawing.getSize() - 1);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// };
	// }

	// private ActionListener showInfoAction() {
	// return al -> {
	// try {
	// Drawing currentDrawing = createCoursePanel.getDrawing();

	// int amountOfShapes = currentDrawing.getSize();
	// double area = currentDrawing.getTotalArea();
	// double circumference = currentDrawing.getTotalArea();

	// String message = getInfoName() + " " + "\n" +
	// "Number of Shapes:" + " " + amountOfShapes + "\n" +
	// "Total Area:" + " " + area + "\n" +
	// "Total circumference:" + " " + circumference;

	// JOptionPane.showMessageDialog(createCoursePanel, message, "Info",
	// JOptionPane.INFORMATION_MESSAGE);
	// } catch (DrawingException e) {
	// JOptionPane.showMessageDialog(createCoursePanel, e.getMessage());
	// e.printStackTrace();
	// }
	// };
	// }

	// private String getInfoName() {

	// String finalString = "";
	// try {
	// Drawing currentDrawing = createCoursePanel.getDrawing();
	// String name = currentDrawing.getName();
	// String author = currentDrawing.getAuthor();

	// String n = name.trim();
	// String a = author.trim();

	// if (n.isEmpty() && a.isEmpty()) {
	// return finalString = "[Unnamed Drawing]";
	// }
	// if (!n.isEmpty() && a.isEmpty()) {
	// return finalString = n;
	// }
	// if (n.isEmpty() && !a.isEmpty()) {
	// return finalString = "[Unnamed Drawing]" + " " + "by" + " " + a;
	// } else {
	// return finalString = n + " " + "by" + " " + a;
	// }
	// } catch (DrawingException e) {
	// JOptionPane.showMessageDialog(createCoursePanel, e.getMessage());
	// e.printStackTrace();
	// }
	// return finalString;
	// }

	private ActionListener createLoadAction() {
		return al -> {

			String projectMap = System.getProperty("user.dir") + "\\src\\created_files";
			JFileChooser chooser = new JFileChooser(projectMap);

			// creates a filter that only accepts text files
			FileFilter txtFilter = new FileNameExtensionFilter("Text files", "txt");
			chooser.addChoosableFileFilter(txtFilter);

			// Remove/comment the below row if you also want the default 'All files' filter
			chooser.setAcceptAllFileFilterUsed(false);

			int option = chooser.showOpenDialog(frame);

			if (option == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();

				try {
					List<Course> courselLists = FileHandler.load(selectedFile.getName());
					courseManager.setCourses(courselLists);

					createCourseListPanel.createCourseElement();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			frame.repaint();
		};
	}

	private ActionListener SaveAsAction() {
		return al -> {

			try {
				String projectMap = System.getProperty("user.dir") + "\\src\\created_files";
				JFileChooser fc = new JFileChooser(projectMap);
				// fc.setDialogTitle("Save course as a file");

				System.err.println("är:" + projectMap);

				int option = fc.showSaveDialog(frame);
				File selectedFile = fc.getSelectedFile();
				String fileName = selectedFile.getName().trim();

				if (option == JFileChooser.APPROVE_OPTION) {

					FileHandler.save(courseManager.getCoursesList(), fileName);
					JOptionPane.showMessageDialog(frame, "Succesfully saved the file:" + " " + fileName);

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Failed to save the file:" + " " + e.getMessage());
				e.printStackTrace();
			}

		};
	}

}
