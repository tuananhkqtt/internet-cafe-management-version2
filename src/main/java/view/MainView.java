package view;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import model.Account;
import swing.IconButton;
import view.mainContainerView.HeaderContainerView;
import view.mainContainerView.CardsContainerView;
import view.mainContainerView.SidebarContainerView;

import java.awt.Color;

public class MainView extends JFrame {

	private JPanel contentPane;
	private HeaderContainerView headerContainerView;
	private SidebarContainerView sidebarContainerView;
	private JLayeredPane layeredPane_cards;
	private CardsContainerView cardsContainerView;
	private JButton button_hamburger;
	private JPanel panel_transparent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView(Account account) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#831843"));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		headerContainerView = new HeaderContainerView(account);
		contentPane.add(headerContainerView);	
		
		layeredPane_cards = new JLayeredPane();
		layeredPane_cards.setBounds(0, 64, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64);
		layeredPane_cards.addMouseListener(new MainController(this));
		contentPane.add(layeredPane_cards);
		
		cardsContainerView = new CardsContainerView(account);
		cardsContainerView.setBounds(0, 0, 1536, 752);
		layeredPane_cards.add(cardsContainerView, Integer.valueOf(0));
		
		panel_transparent = new JPanel();
		panel_transparent.setBounds(cardsContainerView.getBounds());
		panel_transparent.setOpaque(false);
		panel_transparent.setVisible(false);
		panel_transparent.addMouseListener(new MainController(this));
		layeredPane_cards.add(panel_transparent, Integer.valueOf(1));
		
		button_hamburger = new IconButton();
		button_hamburger.setBounds(32, 32, 32, 32);
		button_hamburger.setBackground(Color.WHITE);
		button_hamburger.setIcon(new ImageIcon(MainView.class.getResource("/icons/hamburger.png")));
		button_hamburger.addMouseListener(new MainController(this));
		layeredPane_cards.add(button_hamburger, Integer.valueOf(2));
        
        sidebarContainerView = new SidebarContainerView(new MainController(this), account);
		contentPane.add(sidebarContainerView);
		sidebarContainerView.setVisible(false);
	}
	
	public void zoom(MouseEvent e) {
		if(sidebarContainerView.isVisible()) {
			sidebarContainerView.setVisible(false);
			layeredPane_cards.setBounds(0, 64, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64);
			panel_transparent.setVisible(false);
		} else if(!sidebarContainerView.isVisible() && e.getSource()==button_hamburger){
			sidebarContainerView.setVisible(true);
			layeredPane_cards.setBounds(400, 64, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-128);
			panel_transparent.setVisible(true);
		}
	}
	
	public void showCard(ActionEvent e) {
		cardsContainerView.show(e);
	}
	
	public void logout() {
		this.dispose();
		new LoginView().setVisible(true);
	}
}
