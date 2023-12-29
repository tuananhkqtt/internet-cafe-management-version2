package swing;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MenuPanelController;
import model.MenuModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridLayout;

public class MenuPanel extends JPanel {
	JPanel panel_items;
	private int width = 336;
	private int height = 400;
	
	/**
	 * Create the panel.
	 */
	
	public MenuPanel(MenuModel[] menus) {
		setBounds(0, 0, width, height);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(null);
		setBackground(Color.decode("#831843"));
		
		panel_items = new JPanel();
		panel_items.setBounds(0, 0, 336, 0);
		add(panel_items);
		panel_items.setLayout(new GridLayout(0, 1, 0, 0));
		
		int i=0;
		for (MenuModel menu : menus) {
			addItem(menu, 0, i++);
		}
	}
	
	private void addItem(MenuModel menu, int level, int index) {
		MenuButton menuButton = new MenuButton();
		menuButton.setMargin(new Insets(12, 12*(level+1), 12, 0));
		menuButton.setBackground(Color.decode("#831843"));
		menuButton.setForeground(Color.WHITE);
		menuButton.setMenuModel(menu);
		menuButton.setLevel(level);
		menuButton.addMouseListener(new MenuPanelController(this));
		menuButton.addActionListener((ActionListener) menu.getEventListener());
		panel_items.add(menuButton, index);
		panel_items.revalidate();
		panel_items.repaint();
		
		resetHeight();
	}
	
	private void removeItem(Component item) {
		panel_items.remove(item);
		panel_items.revalidate();
		panel_items.repaint();
		resetHeight();
	}
	
	public void resetHeight() {
		panel_items.setBounds(panel_items.getX(), panel_items.getY(), panel_items.getWidth(), 48*panel_items.getComponentCount());
	}
	
	public void enter(MouseEvent e) {
		((JComponent) e.getComponent()).setOpaque(true);
		Color backgroundColor = Color.decode("#901A4A");
		e.getComponent().setBackground(backgroundColor);
	}
	
	public void exit(MouseEvent e) {
		Color backgroundColor = Color.decode("#831843");
		e.getComponent().setBackground(backgroundColor);
	}
	
	public void click(MouseEvent e) {
		MenuButton item = (MenuButton) e.getComponent();
		int index = panel_items.getComponentZOrder(item);
		
		MenuButton nextItem = null;
		if(panel_items.getComponentCount() != index+1)
			nextItem = (MenuButton) panel_items.getComponent(panel_items.getComponentZOrder(item)+1);
		
		if(nextItem==null || item.getLevel() >= nextItem.getLevel()){
			if(item.getMenuModel().getSubMenus() != null) {
				for (MenuModel subMenu : item.getMenuModel().getSubMenus()) {
					addItem(subMenu, item.getLevel()+1, ++index);
				}
			}
		} else {
			while(item.getLevel()<nextItem.getLevel()) {
				removeItem(nextItem);
				nextItem = null;
				if(panel_items.getComponentCount() != index+1)
					nextItem = (MenuButton) panel_items.getComponent(panel_items.getComponentZOrder(item)+1);
				if(nextItem == null) break;
			}
		}
	}
}
