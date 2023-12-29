package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import dao.AccountDAO;
import model.Account;
import model.Role;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class LoginView extends JFrame {
	private JPanel contentPane;
	private JTextField textField_username;
	private JPasswordField passwordField_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		LoginController loginController = new LoginController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
//		contentPane = new BackgroundPanel("/images/loginBackground.jpg");
		contentPane.setBackground(Color.decode("#831843"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		contentPane.add(Box.createGlue());
		
		JPanel panel_container = new JPanel();
		panel_container.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_container.setMaximumSize(new Dimension(10000, 300));
		contentPane.add(panel_container);
		
		panel_container.setLayout(new BorderLayout(30, 30));
		
		JPanel panel_jLabel = new JPanel();
		panel_container.add(panel_jLabel, BorderLayout.NORTH);
		
		JLabel label_header = new JLabel("Login");
		label_header.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_jLabel.add(label_header);
		
		JPanel panel_jLabels = new JPanel();
		panel_container.add(panel_jLabels, BorderLayout.WEST);
		panel_jLabels.setLayout(new GridLayout(0, 1, 0, 50));
		
		JLabel label_username = new JLabel("Username");
		label_username.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_jLabels.add(label_username);
		
		JLabel label_password = new JLabel("Password");
		label_password.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_jLabels.add(label_password);
		
		JPanel panel_jTextFields = new JPanel();
		panel_container.add(panel_jTextFields, BorderLayout.CENTER);
		panel_jTextFields.setLayout(new GridLayout(0, 1, 0, 50));
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		panel_jTextFields.add(textField_username);
		
		passwordField_password = new JPasswordField();
		panel_jTextFields.add(passwordField_password);
		
		JPanel panel_jButtons = new JPanel();
		FlowLayout fl_panel_jButtons = (FlowLayout) panel_jButtons.getLayout();
		fl_panel_jButtons.setHgap(20);
		panel_container.add(panel_jButtons, BorderLayout.SOUTH);
		
		JButton button_connect = new JButton("Login");
		button_connect.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_connect.setBackground(Color.decode("#831843"));
		button_connect.setForeground(Color.WHITE);
		panel_jButtons.add(button_connect);
		button_connect.addActionListener(loginController);
		
		JButton button_cancel = new JButton("Exit");
		button_cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_cancel.setBackground(Color.decode("#831843"));
		button_cancel.setForeground(Color.WHITE);
		panel_jButtons.add(button_cancel);
		button_cancel.addActionListener(loginController);
		
		contentPane.add(Box.createGlue());
	}

	public void login() {
		// TODO Auto-generated method stub
		Account account = AccountDAO.getInstance().selectByUsername(textField_username.getText());
			if(account.getRole()==Role.admin && textField_username.getText().equals(account.getUsername()) && passwordField_password.getText().equals(account.getPassword())) {
			this.dispose();
			new MainView(account).setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "The username or password you entered is incorrect. Please try again.", null, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void exit() {
		// TODO Auto-generated method stub
		this.dispose();
		new DatabaseConnectionView().setVisible(true);
	}
}
