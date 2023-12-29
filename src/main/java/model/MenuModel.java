package model;

import java.util.Arrays;
import java.util.EventListener;

import javax.swing.Icon;

public class MenuModel {
	private Icon icon;
	private String menuName;
	private MenuModel[] subMenus;
	private EventListener eventListener;
	
	public MenuModel(Icon icon, String menuName, MenuModel[] subMenus, EventListener eventListener) {
		super();
		this.icon = icon;
		this.menuName = menuName;
		this.subMenus = subMenus;
		this.eventListener = eventListener;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public MenuModel[] getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(MenuModel[] subMenus) {
		this.subMenus = subMenus;
	}

	public EventListener getEventListener() {
		return eventListener;
	}

	public void setEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

	@Override
	public String toString() {
		return "MenuModel [icon=" + icon + ", menuName=" + menuName + ", subMenus=" + Arrays.toString(subMenus)
				+ ", eventListener=" + eventListener + "]";
	}
	
}
