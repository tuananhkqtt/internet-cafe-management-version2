package view.mainContainerView;

import java.awt.GraphicsEnvironment;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import swing.IconButton;
import swing.MenuPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.plaf.basic.BasicButtonUI;

import model.Account;
import model.MenuModel;

import java.awt.Component;
import java.awt.Cursor;

public class SidebarContainerView extends JPanel {
	private EventListener eventListener;
	/**
	 * Create the panel.
	 */
	public SidebarContainerView(EventListener eventListener, Account account) {
		this.eventListener = eventListener;
		
		setBounds(0, 64, 336, (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() - 64);
		setLayout(new BorderLayout(0, 12));
		setBackground(Color.decode("#831843"));

		JPanel panel_header = new JPanel();
		add(panel_header, BorderLayout.NORTH);
		panel_header.setLayout(new BoxLayout(panel_header, BoxLayout.Y_AXIS));
		panel_header.setBackground(Color.decode("#831843"));

		int verticalStrutHeader = 12;
		Component horizontalStrut = Box.createHorizontalStrut(16);
		panel_header.add(horizontalStrut);

		IconButton iconButton_avatar = new IconButton();
		iconButton_avatar.setSize(new Dimension(64, 64));
		iconButton_avatar.setIcon(new ImageIcon(SidebarContainerView.class.getResource("/images/avatar.png")));
		iconButton_avatar.setUI(new BasicButtonUI());
		iconButton_avatar.setBackground(Color.decode("#831843"));
		iconButton_avatar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_header.add(iconButton_avatar);
		panel_header.add(Box.createVerticalStrut(verticalStrutHeader));

		JLabel label_username = new JLabel("User Name");
		label_username.setForeground(Color.WHITE);
		label_username.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_username.setBackground(Color.decode("#831843"));
		label_username.setText(account.getUsername());
		panel_header.add(label_username);
		panel_header.add(Box.createVerticalStrut(verticalStrutHeader));

		JLabel label_role = new JLabel("Role");
		label_role.setForeground(Color.WHITE);
		label_role.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_role.setBackground(Color.decode("#831843"));
		label_role.setText(account.getRole().getStringRole());
		panel_header.add(label_role);
		panel_header.add(Box.createVerticalStrut(verticalStrutHeader));
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 1));
		panel_header.add(separator);

		JScrollPane scrollPane_main = new JScrollPane(new MenuPanel(createMenuModel()));
		scrollPane_main.setBorder(null);
		add(scrollPane_main);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(16);
		add(horizontalStrut_1, BorderLayout.WEST);
	}

	private MenuModel[] createMenuModel() {
		return new MenuModel[] {
				new MenuModel(new ImageIcon(getClass().getResource("/icons/home.png")), "Home", null, eventListener),
				new MenuModel(new ImageIcon(getClass().getResource("/icons/management.png")), "Management", new MenuModel[] {
						new MenuModel(new ImageIcon(getClass().getResource("/icons/account.png")), "Account Management", null, eventListener),
						new MenuModel(new ImageIcon(getClass().getResource("/icons/product.png")), "Product Management", null, eventListener),
						new MenuModel(new ImageIcon(getClass().getResource("/icons/employee.png")), "Employee Management", null, eventListener),
						new MenuModel(new ImageIcon(getClass().getResource("/icons/invoice.png")), "Invoice Management", null, eventListener),
						new MenuModel(new ImageIcon(getClass().getResource("/icons/computer.png")), "Computer Management", null, eventListener),
				}, null),
				new MenuModel(new ImageIcon(getClass().getResource("/icons/information.png")), "Personal Information", null, eventListener),
				new MenuModel(new ImageIcon(getClass().getResource("/icons/logout.png")), "Log out", null, eventListener),
		};
	}

}
