package view.mainContainerView.cardsContainerView.AccountManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.account.EditAccountController;
import dao.AccountDAO;
import model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class EditAccountDialog extends JDialog {
	private AccountContainer accountContainer;
	private Account account;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_id;
	private JPasswordField passwordField;
	private JTextField textField_balance;
	private JTextField textField_createdAt;
	private JTextField textField_username;
	private JTextField textField_topUp;
	private JTextField textField_role;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditAccountDialog dialog = new EditAccountDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditAccountDialog(AccountContainer accountContainer, Account account) {
		this.accountContainer = accountContainer;
		this.account = account;
		
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
				JLabel lblEditAccount = new JLabel("Edit Account");
				lblEditAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(lblEditAccount);
			}
		}
		{
			JPanel panel_jLabels = new JPanel();
			contentPanel.add(panel_jLabels, BorderLayout.WEST);
			panel_jLabels.setLayout(new GridLayout(0, 1, 0, 5));
			{
				JLabel lable_id = new JLabel("Id");
				panel_jLabels.add(lable_id);
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
				JLabel label_role = new JLabel("Role");
				panel_jLabels.add(label_role);
			}
			{
				JLabel label_balance = new JLabel("Balance");
				panel_jLabels.add(label_balance);
			}
			{
				JLabel label_topUp = new JLabel("Top Up");
				panel_jLabels.add(label_topUp);
			}
			{
				JLabel lblNewLabel = new JLabel("Created At");
				panel_jLabels.add(lblNewLabel);
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
				textField_username = new JTextField();
				textField_username.setColumns(10);
				panel_jTextFields.add(textField_username);
			}
			{
				passwordField = new JPasswordField();
				panel_jTextFields.add(passwordField);
			}
			{
				textField_role = new JTextField();
				panel_jTextFields.add(textField_role);
				textField_role.setColumns(10);
			}
			{
				textField_balance = new JTextField();
				panel_jTextFields.add(textField_balance);
				textField_balance.setColumns(10);
			}
			{
				textField_topUp = new JTextField();
				panel_jTextFields.add(textField_topUp);
				textField_topUp.setColumns(10);
			}
			{
				textField_createdAt = new JTextField();
				panel_jTextFields.add(textField_createdAt);
				textField_createdAt.setColumns(10);
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
				okButton.addActionListener(new EditAccountController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new EditAccountController(this));
			}
		}
		setLocationRelativeTo(null);
		showAccountInfor();
	}
	
	private void showAccountInfor() {
		textField_id.setText(account.getId()+"");
		textField_id.setEditable(false);
		
		textField_username.setText(account.getUsername());
		textField_username.setEditable(false);
		
		passwordField.setText(account.getPassword());
		
		textField_role.setText(account.getRole().getStringRole());
		textField_role.setEditable(false);
		
		textField_balance.setText(account.getBalance()+"");
		textField_balance.setEditable(false);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createdAt = simpleDateFormat.format(account.getCreatedAt());
		textField_createdAt.setText(createdAt);
		textField_createdAt.setEditable(false);
	}
	
	public void ok() {
		String password = passwordField.getText();
		int topUp = 0;
		if(password.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter password.", "", JOptionPane.INFORMATION_MESSAGE);
		} else{
			if(!textField_topUp.getText().equals("")) {
				topUp = Integer.parseInt(textField_topUp.getText());
			}
			account.setPassword(password);
			account.setBalance(account.getBalance()+topUp);
			AccountDAO.getInstance().update(account);
			accountContainer.reloadTable();
			JOptionPane.showMessageDialog(this, "The account edited successfully", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void cancel() {
		dispose();
	}
}
