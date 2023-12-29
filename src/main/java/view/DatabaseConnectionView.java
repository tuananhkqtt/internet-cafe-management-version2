package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DatabaseConnectionController;
import database.DatabaseInfor;
import database.JDBCUtil;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Font;

public class DatabaseConnectionView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_dbms;
	private JTextField textField_databaseName;
	private JTextField textField_port;
	private JTextField textField_serverName;
	private JTextField textField_username;
	private JPasswordField passwordField_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseConnectionView frame = new DatabaseConnectionView();
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
	public DatabaseConnectionView() {
		DatabaseConnectionController databaseConnectionController = new DatabaseConnectionController(this);
		
		setTitle("Connect to Database");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(30, 10));
		
		JPanel panel_jLabel = new JPanel();
		contentPane.add(panel_jLabel, BorderLayout.NORTH);
		
		JLabel label_header = new JLabel("Connect to Database");
		label_header.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_jLabel.add(label_header);
		
		JPanel panel_jLabels = new JPanel();
		contentPane.add(panel_jLabels, BorderLayout.WEST);
		panel_jLabels.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblDbms = new JLabel("DBMS");
		panel_jLabels.add(lblDbms);
		
		JLabel label_serverName = new JLabel("Server name");
		panel_jLabels.add(label_serverName);
		
		JLabel label_databaseName = new JLabel("Database name");
		panel_jLabels.add(label_databaseName);
		
		JLabel label_port = new JLabel("Port");
		panel_jLabels.add(label_port);
		
		JLabel label_username = new JLabel("Username");
		panel_jLabels.add(label_username);
		
		JLabel label_password = new JLabel("Password");
		panel_jLabels.add(label_password);
		
		JPanel panel_jTextFields = new JPanel();
		contentPane.add(panel_jTextFields, BorderLayout.CENTER);
		panel_jTextFields.setLayout(new GridLayout(0, 1, 0, 5));
		
		textField_dbms = new JTextField();
		textField_dbms.setText("sqlserver");
		textField_dbms.setEditable(false);
		panel_jTextFields.add(textField_dbms);
		textField_dbms.setColumns(10);
		
		textField_serverName = new JTextField();
		textField_serverName.setText("localhost");
		textField_serverName.setColumns(10);
		panel_jTextFields.add(textField_serverName);
		
		textField_databaseName = new JTextField();
		textField_databaseName.setText("INTERNETCAFEMANAGEMENT");
		textField_databaseName.setColumns(10);
		panel_jTextFields.add(textField_databaseName);
		
		textField_port = new JTextField();
		textField_port.setText("1434");
		textField_port.setColumns(10);
		panel_jTextFields.add(textField_port);
		
		textField_username = new JTextField();
		textField_username.setText("sa");
		textField_username.setColumns(10);
		panel_jTextFields.add(textField_username);
		
		passwordField_password = new JPasswordField();
		passwordField_password.setText("123");
		panel_jTextFields.add(passwordField_password);
		
		JPanel panel_jButtons = new JPanel();
		FlowLayout fl_panel_jButtons = (FlowLayout) panel_jButtons.getLayout();
		fl_panel_jButtons.setHgap(20);
		contentPane.add(panel_jButtons, BorderLayout.SOUTH);
		
		JButton button_connect = new JButton("Connect");
		panel_jButtons.add(button_connect);
		button_connect.addActionListener(databaseConnectionController);
		
		JButton button_cancel = new JButton("Cancel");
		panel_jButtons.add(button_cancel);
		button_cancel.addActionListener(databaseConnectionController);
	}

	public void connect() {
		// TODO Auto-generated method stub
		DatabaseInfor databaseInfor = new DatabaseInfor(textField_dbms.getText(), textField_serverName.getText(), textField_port.getText(), textField_databaseName.getText(), textField_username.getText(), passwordField_password.getText());
		JDBCUtil.setDatabaseInfor(databaseInfor);
		Connection connection = null;
		try {
			connection = JDBCUtil.getConnection();
			this.dispose();
			new LoginView().setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMessage = "";
			String[] eMessages = e.getMessage().split("\\.");
			for (String eMessage : eMessages) {
				errorMessage = errorMessage + eMessage.trim() + "\n";
			}
			JOptionPane.showMessageDialog(this, errorMessage, "Connect to Database", JOptionPane.ERROR_MESSAGE);
		} finally {
			JDBCUtil.closeConnection(connection);
		}
	}

	public void cancel() {
		// TODO Auto-generated method stub
		this.dispose();
	}

}