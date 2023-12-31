package view.mainContainerView.cardsContainerView.EmployeeManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.employee.AddEmployeeController;
import dao.AccountDAO;
import dao.EmployeeDAO;
import model.Account;
import model.Employee;
import model.Role;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class AddEmployeeDialog extends JDialog {
	private EmployeeContainer employeeContainer;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_username;
	private JPasswordField passwordField;
	private JTextField textField_email;
	private JTextField textField_phoneNumber;
	private JTextField textField_address;
	private JTextField textField_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEmployeeDialog dialog = new AddEmployeeDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEmployeeDialog(EmployeeContainer employeeContainer) {
		this.employeeContainer = employeeContainer;
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(30, 5));
		{
			JPanel panel_jLabel = new JPanel();
			contentPanel.add(panel_jLabel, BorderLayout.NORTH);
			{
				JLabel label_header = new JLabel("Add a new Employee");
				label_header.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(label_header);
			}
		}
		{
			JPanel panel_jLabels = new JPanel();
			contentPanel.add(panel_jLabels, BorderLayout.WEST);
			panel_jLabels.setLayout(new GridLayout(0, 1, 0, 5));
			{
				JLabel lable_name = new JLabel("Name");
				panel_jLabels.add(lable_name);
			}
			{
				JLabel lable_username = new JLabel("Username");
				panel_jLabels.add(lable_username);
			}
			{
				JLabel label_password = new JLabel("Password");
				panel_jLabels.add(label_password);
			}
			{
				JLabel label_email = new JLabel("Email");
				panel_jLabels.add(label_email);
			}
			{
				JLabel label_phoneNumber = new JLabel("Phone Number");
				panel_jLabels.add(label_phoneNumber);
			}
			{
				JLabel label_address = new JLabel("Address");
				panel_jLabels.add(label_address);
			}
		}
		{
			JPanel panel_jTextFields = new JPanel();
			contentPanel.add(panel_jTextFields, BorderLayout.CENTER);
			panel_jTextFields.setLayout(new GridLayout(0, 1, 0, 5));
			{
				textField_name = new JTextField();
				textField_name.setColumns(10);
				panel_jTextFields.add(textField_name);
			}
			{
				textField_username = new JTextField();
				textField_username.setColumns(10);
				panel_jTextFields.add(textField_username);
			}
			{
				passwordField = new JPasswordField();
				panel_jTextFields.add(passwordField);
			}
			{
				textField_email = new JTextField();
				textField_email.setColumns(10);
				panel_jTextFields.add(textField_email);
			}
			{
				textField_phoneNumber = new JTextField();
				textField_phoneNumber.setColumns(10);
				panel_jTextFields.add(textField_phoneNumber);
			}
			{
				textField_address = new JTextField();
				textField_address.setColumns(10);
				panel_jTextFields.add(textField_address);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setVgap(10);
			fl_buttonPane.setHgap(20);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.setBackground(Color.decode("#831843"));
				okButton.setForeground(Color.WHITE);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new AddEmployeeController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new AddEmployeeController(this));
			}
		}
		setLocationRelativeTo(null);
	}
	
	public void ok() {
		String name = textField_name.getText();
		String username = textField_username.getText();
		String password = passwordField.getText();
		String email = textField_email.getText();
		String phoneNumber = textField_phoneNumber.getText();
		String address = textField_address.getText();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter name.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(username.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter username.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(password.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter password.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(email.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter email.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(phoneNumber.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter phoneNumber.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(address.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter address.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(AccountDAO.getInstance().selectByUsername(username) != null) {
			JOptionPane.showMessageDialog(this, "User already exists. Please try another username.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Account account = new Account(username, password);
			account.setRole(Role.employee);
			account.setBalance(0);
			AccountDAO.getInstance().insert(account);
			account = AccountDAO.getInstance().selectByUsername(username);
			
			Employee employee = new Employee();
			employee.setName(name);
			employee.setAccountId(account.getId());
			employee.setEmail(email);
			employee.setPhoneNumber(phoneNumber);
			employee.setAddress(address);
			
			EmployeeDAO.getInstance().insert(employee);
			employeeContainer.reloadTable();
			JOptionPane.showMessageDialog(this, "New employee added successfully", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void cancel() {
		dispose();
	}
}
