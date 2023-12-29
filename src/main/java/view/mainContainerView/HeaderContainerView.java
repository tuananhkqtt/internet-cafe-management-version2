package view.mainContainerView;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import swing.IconButton;

import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import model.Account;

import java.awt.Color;
import java.awt.Cursor;

public class HeaderContainerView extends JPanel {
	private int maxWidth = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int iconSize = 32;

	/**
	 * Create the panel.
	 */
	public HeaderContainerView(Account account) {
		setBounds(0, 0, this.maxWidth, 64);
		setLayout(new FlowLayout(FlowLayout.RIGHT, 16, 16));
		setBackground(Color.decode("#831843"));
		
		IconButton iconButton_notification = new IconButton();
		iconButton_notification.setBackground(Color.decode("#831843"));
		iconButton_notification.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iconButton_notification.setSize(new Dimension(iconSize, iconSize));
//		iconButton_notification.setIcon(new ImageIcon(getClass().getResource("/icons/notification.png")));
		add(iconButton_notification);
		
		JSeparator jseparator = new JSeparator();
		jseparator.setOrientation(SwingConstants.VERTICAL);
		jseparator.setPreferredSize(new Dimension(1, iconSize));
		add(jseparator);
		
		JPanel panel_name = new JPanel();
		panel_name.setBackground(Color.decode("#831843"));
		panel_name.setLayout(new GridLayout(0, 1, 0, 0));
		add(panel_name);
		
		JLabel label_username = new JLabel();
		label_username.setBackground(Color.decode("#831843"));
		label_username.setForeground(Color.WHITE);
		label_username.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_username.setText(account.getUsername());
		panel_name.add(label_username);
		
		JLabel label_Role = new JLabel();
		label_Role.setBackground(Color.decode("#831843"));
		label_Role.setForeground(Color.WHITE);
		label_Role.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_Role.setHorizontalAlignment(SwingConstants.TRAILING);
		label_Role.setText(account.getRole().getStringRole());
		panel_name.add(label_Role);
		
		IconButton iconButton_avatar = new IconButton();
		iconButton_avatar.setBackground(Color.decode("#831843"));
		iconButton_avatar.setSize(new Dimension(iconSize, iconSize));
		iconButton_avatar.setIcon(new ImageIcon(HeaderContainerView.class.getResource("/images/avatar.png")));
		add(iconButton_avatar);
	}

}
