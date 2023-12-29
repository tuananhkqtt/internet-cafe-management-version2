package view.mainContainerView.cardsContainerView;

import javax.swing.JPanel;

import model.Account;
import model.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.personalInformation.PersonalInformationController;
import dao.AccountDAO;
import dao.EmployeeDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.FlowLayout;

public class PersonalInformationContainer extends JPanel {
	private Account account;
	private Employee employee;
	private int width = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int height = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()
			- 64;
	private JTextField textField_id;
	private JTextField textField_accountId;
	private JTextField textField_username;
	private JTextField textField_name;
	private JTextField textField_email;
	private JTextField textField_phoneNumber;
	private JTextField textField_adress;
	private JPasswordField passwordField_password;
	private JTextField textField_createdAt;
	private JTextField textField_balance;
	private JPasswordField passwordField_newPassword;
	private JPasswordField passwordField_retypeNewPassword;

	/**
	 * Create the panel.
	 */
	public PersonalInformationContainer(Account account) {
		this.account = account;
		employee = new Employee();
		employee.setAccountId(account.getId());
		employee = EmployeeDAO.getInstance().selectByAccountId(employee);

		setBackground(Color.WHITE);
		setBounds(0, 0, width, height);
		setBorder(new EmptyBorder(50, 200, 50, 200));
		setLayout(new BorderLayout(100, 20));

		JPanel panel_jLabel = new JPanel();
		panel_jLabel.setBackground(Color.WHITE);
		add(panel_jLabel, BorderLayout.NORTH);

		JLabel lblPersonalInformation = new JLabel("PERSONAL INFORMATION");
		lblPersonalInformation.setBackground(Color.WHITE);
		lblPersonalInformation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_jLabel.add(lblPersonalInformation);

		JPanel panel_jLabels = new JPanel();
		panel_jLabels.setBackground(Color.WHITE);
		add(panel_jLabels, BorderLayout.WEST);
		panel_jLabels.setLayout(new GridLayout(0, 1, 0, 5));

		JLabel lblId = new JLabel("ID");
		panel_jLabels.add(lblId);

		JLabel lblAccountId = new JLabel("ACCOUNT ID");
		panel_jLabels.add(lblAccountId);

		JLabel lblNewLabel = new JLabel("USERNAME");
		panel_jLabels.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CURRENT PASSWORD");
		panel_jLabels.add(lblNewLabel_1);

		JLabel lblNewLabel_4 = new JLabel("NEW PASSWORD");
		panel_jLabels.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("RE-TYPE NEW PASSWORD");
		panel_jLabels.add(lblNewLabel_5);

		JLabel lblNewLabel_3 = new JLabel("Balance");
		panel_jLabels.add(lblNewLabel_3);

		JLabel lblName = new JLabel("NAME");
		panel_jLabels.add(lblName);

		JLabel lblEmail = new JLabel("EMAIL");
		panel_jLabels.add(lblEmail);

		JLabel lblPhoneNumber = new JLabel("PHONE NUMBER");
		panel_jLabels.add(lblPhoneNumber);

		JLabel lblAddress = new JLabel("ADDRESS");
		panel_jLabels.add(lblAddress);

		JLabel lblNewLabel_2 = new JLabel("Created At");
		panel_jLabels.add(lblNewLabel_2);

		JPanel panel_jTextFields = new JPanel();
		panel_jTextFields.setBackground(Color.WHITE);
		add(panel_jTextFields, BorderLayout.CENTER);
		panel_jTextFields.setLayout(new GridLayout(0, 1, 0, 5));

		Insets insets = new Insets(8, 48, 8, 48);

		textField_id = new JTextField();
		textField_id.setMargin(insets);
		panel_jTextFields.add(textField_id);
		textField_id.setColumns(10);

		textField_accountId = new JTextField();
		textField_accountId.setColumns(10);
		textField_accountId.setMargin(insets);
		panel_jTextFields.add(textField_accountId);

		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setMargin(insets);
		panel_jTextFields.add(textField_username);

		passwordField_password = new JPasswordField();
		passwordField_password.setMargin(insets);
		passwordField_password.addKeyListener(new PersonalInformationController(this));
		panel_jTextFields.add(passwordField_password);

		passwordField_newPassword = new JPasswordField();
		passwordField_newPassword.setMargin(insets);
		panel_jTextFields.add(passwordField_newPassword);

		passwordField_retypeNewPassword = new JPasswordField();
		passwordField_retypeNewPassword.setMargin(insets);
		panel_jTextFields.add(passwordField_retypeNewPassword);

		textField_balance = new JTextField();
		textField_balance.setMargin(insets);
		panel_jTextFields.add(textField_balance);
		textField_balance.setColumns(10);

		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setMargin(insets);
		panel_jTextFields.add(textField_name);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setMargin(insets);
		panel_jTextFields.add(textField_email);

		textField_phoneNumber = new JTextField();
		textField_phoneNumber.setColumns(10);
		textField_phoneNumber.setMargin(insets);
		panel_jTextFields.add(textField_phoneNumber);

		textField_adress = new JTextField();
		textField_adress.setColumns(10);
		textField_adress.setMargin(insets);
		panel_jTextFields.add(textField_adress);

		textField_createdAt = new JTextField();
		panel_jTextFields.add(textField_createdAt);
		textField_createdAt.setMargin(insets);
		textField_createdAt.setColumns(10);

		JPanel panel_jButtons = new JPanel();
		panel_jButtons.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_jButtons.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(50);
		add(panel_jButtons, BorderLayout.SOUTH);

		JButton button_save = new JButton("Save");
		button_save.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_save.setBackground(Color.decode("#831843"));
		button_save.setForeground(Color.WHITE);
		button_save.addActionListener(new PersonalInformationController(this));
		panel_jButtons.add(button_save);

		JButton button_cancel = new JButton("Reset");
		button_cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_cancel.setBackground(Color.decode("#831843"));
		button_cancel.setForeground(Color.WHITE);
		button_cancel.addActionListener(new PersonalInformationController(this));
		panel_jButtons.add(button_cancel);

		showAccountInfor();
	}

	private void showAccountInfor() {
		textField_id.setText(employee.getId() + "");
		textField_id.setEditable(false);

		textField_accountId.setText(account.getId() + "");
		textField_accountId.setEditable(false);

		textField_username.setText(account.getUsername());
		textField_username.setEditable(false);
		
		passwordField_password.setText("");
		
		passwordField_newPassword.setText("");
		passwordField_newPassword.setEditable(false);
		
		passwordField_retypeNewPassword.setText("");
		passwordField_retypeNewPassword.setEditable(false);

		textField_balance.setText(account.getBalance() + "");
		textField_balance.setEditable(false);

		textField_name.setText(employee.getName());

		textField_email.setText(employee.getEmail());

		textField_phoneNumber.setText(employee.getPhoneNumber());

		textField_adress.setText(employee.getAddress());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createdAt = simpleDateFormat.format(account.getCreatedAt());
		textField_createdAt.setText(createdAt);
		textField_createdAt.setEditable(false);
	}

	public void save() {
		String password = passwordField_password.getText();
		String newPassword = passwordField_newPassword.getText();
		String retypeNewPassword = passwordField_retypeNewPassword.getText();
		String name = textField_name.getText();
		String email = textField_email.getText();
		String phoneNumber = textField_phoneNumber.getText();
		String address = textField_adress.getText();

		if (!password.equalsIgnoreCase("")) {
			if (newPassword.equalsIgnoreCase(""))
				JOptionPane.showMessageDialog(this, "Enter your new passwrod please.", "",
						JOptionPane.INFORMATION_MESSAGE);
			else if (retypeNewPassword.equalsIgnoreCase(""))
				JOptionPane.showMessageDialog(this, "Enter your re-type new passwrod please.", "",
						JOptionPane.INFORMATION_MESSAGE);
			else if (!newPassword.equalsIgnoreCase(retypeNewPassword))
				JOptionPane.showMessageDialog(this, "New password does not match. Enter new password again here.", "",
						JOptionPane.INFORMATION_MESSAGE);
			else if (!password.equalsIgnoreCase(account.getPassword()))
				JOptionPane.showMessageDialog(this, "Current password is incorrect. Please try again.", "",
						JOptionPane.INFORMATION_MESSAGE);
			else if (name.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Enter your name please.", "", JOptionPane.INFORMATION_MESSAGE);
			} else if (email.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Enter your email please.", "", JOptionPane.INFORMATION_MESSAGE);
			} else if (phoneNumber.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Enter your phoneNumber please.", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (address.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Enter your address please.", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				account.setPassword(newPassword);
				employee.setName(name);
				employee.setEmail(email);
				employee.setPhoneNumber(phoneNumber);
				employee.setAddress(address);
				AccountDAO.getInstance().update(account);
				EmployeeDAO.getInstance().update(employee);
				reset();
				JOptionPane.showMessageDialog(this, "Changes have been saved", "", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (name.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Enter your name please.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if (email.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Enter your email please.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if (phoneNumber.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Enter your phoneNumber please.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if (address.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Enter your address please.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			account.setPassword(newPassword);
			employee.setName(name);
			employee.setEmail(email);
			employee.setPhoneNumber(phoneNumber);
			employee.setAddress(address);
			AccountDAO.getInstance().update(account);
			EmployeeDAO.getInstance().update(employee);
			reset();
			JOptionPane.showMessageDialog(this, "Changes have been saved", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void reset() {
		showAccountInfor();
	}

	public void setNewPasswordEditable() {
		if (!passwordField_password.getText().equals("")) {
			passwordField_newPassword.setEditable(true);
			passwordField_retypeNewPassword.setEditable(true);
		} else {
			passwordField_newPassword.setEditable(false);
			passwordField_retypeNewPassword.setEditable(false);
		}
	}
}
