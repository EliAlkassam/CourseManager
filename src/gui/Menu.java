package gui;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * This class handles all the JMenuBar items.
 *
 * @author elal2203
 * @version 1.0
 */

public class Menu extends JMenuBar {

	public void addJMenu(String name) {

		JMenu menu = new JMenu(name);
		menu.setName(name);

		this.add(menu);
	}

	public void addJMenuItem(String parantName, String itemName) {

		try {
			JComponent parentMenu = getComponentByName(parantName);

			if (parentMenu != null && parentMenu instanceof JMenu) {

				JMenu menu = (JMenu) parentMenu;
				JMenuItem itemInMenu = new JMenuItem(itemName);

				menu.add(itemInMenu);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addJMenuItem(String parentName, String itemName, ActionListener al) {

		addJMenuItem(parentName, itemName);
		JComponent parentInMenu = getComponentByName(parentName);
		JComponent childInMenu = getComponentByName(itemName);

		if (childInMenu != null && childInMenu instanceof JMenuItem) {
			JMenuItem jItemMenu = (JMenuItem) childInMenu;

			jItemMenu.addActionListener(al);
			parentInMenu.add(childInMenu);
		}
	}

	public void addJMenuItem(String parentName, String itemName, ActionListener al, KeyStroke keyStroke) {

		this.addJMenuItem(parentName, itemName, al);

		JComponent parentMenu = getComponentByName(parentName);
		JMenuItem childInMenu = (JMenuItem) getComponentByName(itemName);

		if (parentMenu != null && parentMenu instanceof JMenu) {

			childInMenu.setAccelerator(keyStroke);
		}
	}

	public void addSubJMenu(String parentName, String subMenuName) {

		JComponent parentMenu = getComponentByName(parentName);

		if (parentMenu != null && parentMenu instanceof JMenu) {

			JMenu menu = (JMenu) parentMenu;
			JMenu subJMenuItem = new JMenu(subMenuName);
			menu.add(subJMenuItem);
		}
	}

	public JMenu getJMenu(int index) {
		return super.getMenu(index);
	}

	private JComponent getComponentByName(String name) {

		Component[] components = this.getComponents();
		for (Component c : components) {
			if (c instanceof JMenuItem) {

				JMenuItem jMenuItem = (JMenuItem) c;
				String jMenuText = jMenuItem.getText();

				if (name.equals(jMenuText)) {
					return jMenuItem;
				}

				Component[] menuComponements = ((JMenu) jMenuItem).getMenuComponents();

				for (Component mc : menuComponements) {
					if (mc instanceof JMenuItem) {

						String mcText = ((JMenuItem) mc).getText();
						if (mcText.equals(name)) {
							return (JMenuItem) mc;
						}
						if (mc instanceof JMenu) {

							JMenu jSubMenu = (JMenu) mc;

							for (Component sSubMenuComp : jSubMenu.getMenuComponents()) {
								if (sSubMenuComp instanceof JMenuItem) {

									JMenuItem sSubItem = (JMenuItem) sSubMenuComp;
									if (sSubItem.getText().equals(name)) {
										return sSubItem;
									}
								}
							}
						}
					}

				}
			}
		}
		return null;

	}

}
