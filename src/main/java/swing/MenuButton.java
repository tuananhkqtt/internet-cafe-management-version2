package swing;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.MenuModel;

public class MenuButton extends JButton{
	private int level;
	private MenuModel menuModel;
	
	public MenuButton() {
		setMargin(new Insets(8, 24, 8, 24));
		setBorderPainted(false);
		setHorizontalAlignment(SwingConstants.LEFT);;
		setIconTextGap(24);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		setMargin(new Insets(getMargin().top, getMargin().left*(level+1), getMargin().bottom, getMargin().right));
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
		setIcon(menuModel.getIcon());
		setText(menuModel.getMenuName());
	}
}
