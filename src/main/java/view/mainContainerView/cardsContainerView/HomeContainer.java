package view.mainContainerView.cardsContainerView;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import controller.home.HomeController;
import dao.AccountDAO;
import dao.ComputerDAO;
import model.Account;
import model.Computer;
import swing.IconButton;
import view.mainContainerView.SidebarContainerView;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomeContainer extends JPanel {
	private int width = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int height = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64;
	private JPanel panel_main;
	private JPopupMenu popupMenu;
	/**
	 * Create the panel.
	 */
	public HomeContainer() {
		setBackground(Color.WHITE);
		setBounds(0, 0, width, height);
		setLayout(null);
		
		JLabel label_title = new JLabel("Quickly Workstation Management");
		label_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_title.setBounds(192, 32, 480, 48);
		add(label_title);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 112, width-96, height-148);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		panel_main = new JPanel();
		panel_main.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
		panel_main.setBackground(Color.WHITE);
		showWorkstation();
		scrollPane.setViewportView(panel_main);
		
		popupMenu = new JPopupMenu();
		JMenuItem menuItem_turnOffComputer = new JMenuItem("Turn off computer");
		menuItem_turnOffComputer.setEnabled(false);
		popupMenu.add(menuItem_turnOffComputer);
		JMenuItem menuItem_detail = new JMenuItem("Detail");
		menuItem_detail.setEnabled(false);
		popupMenu.add(menuItem_detail);
		JMenuItem menuItem_message = new JMenuItem("Message");
		menuItem_message.setEnabled(false);
		popupMenu.add(menuItem_message);
		this.add(popupMenu);
	}

	private void showWorkstation() {
		TreeSet<Computer> computers = ComputerDAO.getInstance().selectAll();
		for (Computer computer : computers) {
			IconButton iconButton = new IconButton();
			iconButton.setLayout(new FlowLayout(FlowLayout.CENTER));
			iconButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			iconButton.setHorizontalTextPosition(SwingConstants.CENTER);
			iconButton.setIconTextGap(12);
			iconButton.setText(computer.getName());
			iconButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			iconButton.setSize(new Dimension(100, 100));
			iconButton.setIcon(new ImageIcon(getClass().getResource("/images/computerOff.png")));
			iconButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			iconButton.setBackground(Color.WHITE);
			iconButton.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					showPopup(e);
				}
			});
			panel_main.add(iconButton);
			
			
			
			
		}
		panel_main.setPreferredSize(new Dimension(width-114, 172*computers.size()/10));
	}
	
	public void showPopup(MouseEvent e) {
		popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
}
