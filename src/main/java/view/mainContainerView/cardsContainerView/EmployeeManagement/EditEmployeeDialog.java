package view.mainContainerView.cardsContainerView.EmployeeManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.employee.EditEmployeeController;
import dao.EmployeeDAO;
import model.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class EditEmployeeDialog extends JDialog {
	private EmployeeContainer employeeContainer;
	private Employee employee;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_id;
	private JPasswordField passwordField;
	private JTextField textField_balance;
	private JTextField textField_createdAt;
	private JTextField textField_username;
	private JTextField textField_name;
	private JTextField textField_accountId;
	private JTextField textField_email;
	private JTextField textField_phoneNumber;
	private JTextField textField_address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditEmployeeDialog dialog = new EditEmployeeDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditEmployeeDialog(EmployeeContainer employeeContainer, Employee employee) {
		this.employeeContainer = employeeContainer;
		this.employee = employee;
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(10, 5));
		{
			JPanel panel_jLabel = new JPanel();
			contentPanel.add(panel_jLabel, BorderLayout.NORTH);
			{
				JLabel lblEditEmployee = new JLabel("Edit Employee");
				lblEditEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(lblEditEmployee);
			}
		}
		{
			JPanel panel_jLabels = new JPanel();
			contentPanel.add(panel_jLabels, BorderLayout.WEST);
			panel_jLabels.setLayout(new GridLayout(0, 1, 0, 5));
			{
				JLabel lable_name = new JLabel("Id");
				panel_jLabels.add(lable_name);
			}
			{
				JLabel lable_name = new JLabel("Name");
				panel_jLabels.add(lable_name);
			}
			{
				JLabel lable_accountId = new JLabel("Account Id");
				panel_jLabels.add(lable_accountId);
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
				textField_id = new JTextField();
				textField_id.setColumns(10);
				panel_jTextFields.add(textField_id);
			}
			{
				textField_name = new JTextField();
				textField_name.setColumns(10);
				panel_jTextFields.add(textField_name);
			}
			{
				textField_accountId = new JTextField();
				textField_accountId.setColumns(10);
				panel_jTextFields.add(textField_accountId);
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
				okButton.addActionListener(new EditEmployeeController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new EditEmployeeController(this));
			}
		}
		setLocationRelativeTo(null);
		showEmployeeInfor();
	}
	
	private void showEmployeeInfor() {
		textField_id.setText(employee.getId()+"");
		textField_id.setEditable(false);
		
		textField_name.setText(employee.getName());
		
		textField_accountId.setText(employee.getAccountId()+"");
		textField_accountId.setEditable(false);
		
		textField_email.setText(employee.getEmail());
		
		textField_phoneNumber.setText(employee.getPhoneNumber());
		
		textField_address.setText(employee.getAddress());
	}
	
	public void ok() {
		String name = textField_name.getText();
		String email = textField_email.getText();
		String phoneNumber = textField_phoneNumber.getText();
		String address = textField_address.getText();
		
		if(name == "") {
			JOptionPane.showMessageDialog(this, "Please enter name.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(email == "") {
			JOptionPane.showMessageDialog(this, "Please enter email.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(phoneNumber == "") {
			JOptionPane.showMessageDialog(this, "Please enter phoneNumber.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(address == "") {
			JOptionPane.showMessageDialog(this, "Please enter address.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Employee employee = new Employee();
			employee.setName(name);
			employee.setEmail(email);
			employee.setPhoneNumber(phoneNumber);
			employee.setAddress(address);
			EmployeeDAO.getInstance().update(employee);
			employeeContainer.reloadTable();
			JOptionPane.showMessageDialog(this, "The employee edited successfully", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void cancel() {
		dispose();
	}
}
