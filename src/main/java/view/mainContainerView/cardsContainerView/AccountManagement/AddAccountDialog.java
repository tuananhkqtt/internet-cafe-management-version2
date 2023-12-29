package view.mainContainerView.cardsContainerView.AccountManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.account.AddAccountController;
import dao.AccountDAO;
import model.Account;
import model.Role;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class AddAccountDialog extends JDialog {
	private AccountContainer accountContainer;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_username;
	private JTextField textField_balance;
	private JPasswordField passwordField;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddAccountDialog dialog = new AddAccountDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddAccountDialog(AccountContainer accountContainer) {
		this.accountContainer = accountContainer;
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(30, 10));
		{
			JPanel panel_jLabel = new JPanel();
			contentPanel.add(panel_jLabel, BorderLayout.NORTH);
			{
				JLabel label_header = new JLabel("Add a new Account");
				label_header.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(label_header);
			}
		}
		{
			JPanel panel_jLabels = new JPanel();
			contentPanel.add(panel_jLabels, BorderLayout.WEST);
			panel_jLabels.setLayout(new GridLayout(0, 1, 0, 5));
			{
				JLabel lable_username = new JLabel("Username");
				panel_jLabels.add(lable_username);
			}
			{
				JLabel label_password = new JLabel("Password");
				panel_jLabels.add(label_password);
			}
			{
				JLabel label_role = new JLabel("Role");
				panel_jLabels.add(label_role);
			}
			{
				JLabel label_balance = new JLabel("Balance");
				panel_jLabels.add(label_balance);
			}
		}
		{
			JPanel panel_jTextFields = new JPanel();
			contentPanel.add(panel_jTextFields, BorderLayout.CENTER);
			panel_jTextFields.setLayout(new GridLayout(0, 1, 0, 5));
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
				String[] roles = {"Role", "Admin", "Employee", "User"};
				comboBox = new JComboBox(roles);
				panel_jTextFields.add(comboBox);
			}
			{
				textField_balance = new JTextField();
				textField_balance.setColumns(10);
				panel_jTextFields.add(textField_balance);
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
				okButton.addActionListener(new AddAccountController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new AddAccountController(this));
			}
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void ok() {
		String username = textField_username.getText();
		String password = passwordField.getText();
		Role role = Role.admin;
		if(comboBox.getSelectedIndex() == 0) {
			role = null;
		} else if(comboBox.getSelectedIndex() == 1) {
			role = Role.admin;
		} else if(comboBox.getSelectedIndex() == 2) {
			role = Role.employee;
		}else if(comboBox.getSelectedIndex() == 3) {
			role = Role.user;
		}
		int balance = Integer.parseInt(textField_balance.getText());
		
		if(username == "") {
			JOptionPane.showMessageDialog(this, "Please enter username.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(password == "") {
			JOptionPane.showMessageDialog(this, "Please enter password.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(role == null) {
			JOptionPane.showMessageDialog(this, "Please select role.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(AccountDAO.getInstance().selectByUsername(username) != null) {
			JOptionPane.showMessageDialog(this, "User already exists. Please try another username.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Account account = new Account(username, password, role, balance);
			AccountDAO.getInstance().insert(account);
			accountContainer.reloadTable();
			JOptionPane.showMessageDialog(this, "New account added successfully", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void cancel() {
		dispose();
	}
}
