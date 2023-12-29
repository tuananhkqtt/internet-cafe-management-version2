package view.mainContainerView.cardsContainerView.computerManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.computer.EditComputerController;
import dao.ComputerDAO;
import model.Computer;

public class EditComputerDialog extends JDialog {
	private ComputerContainer computerContainer;
	private Computer computer;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_id;
	private JTextField textField_price;
	private JTextField textField_createdAt;
	private JTextField textField_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditComputerDialog dialog = new EditComputerDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditComputerDialog(ComputerContainer computerContainer, Computer computer) {
		this.computerContainer = computerContainer;
		this.computer = computer;
		
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
				JLabel lblEditComputer = new JLabel("Edit Computer");
				lblEditComputer.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(lblEditComputer);
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
				JLabel lable_name = new JLabel("Name");
				panel_jLabels.add(lable_name);
			}
			{
				JLabel label_price = new JLabel("Price");
				panel_jLabels.add(label_price);
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
				textField_name = new JTextField();
				textField_name.setColumns(10);
				panel_jTextFields.add(textField_name);
			}
			{
				String[] roles = {"Role", "Admin", "Employee", "User"};
			}
			{
				textField_price = new JTextField();
				
				panel_jTextFields.add(textField_price);
				textField_price.setColumns(10);
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
				okButton.addActionListener(new EditComputerController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new EditComputerController(this));
			}
		}
		setLocationRelativeTo(null);
		showComputerInfor();
	}
	
	private void showComputerInfor() {
		textField_id.setText(computer.getId()+"");
		textField_id.setEditable(false);
		
		textField_name.setText(computer.getName());
		
		textField_price.setText(computer.getPrice()+"");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createdAt = simpleDateFormat.format(computer.getCreatedAt());
		textField_createdAt.setText(createdAt);
		textField_createdAt.setEditable(false);
	}
	
	public void ok() {
		String name = textField_name.getText();
		int price = Integer.parseInt(textField_price.getText());
		
		if(price == 0) {
			JOptionPane.showMessageDialog(this, "Please enter price.", "", JOptionPane.INFORMATION_MESSAGE);
		}{
			computer.setName(name);
			computer.setPrice(price);
			ComputerDAO.getInstance().update(computer);
			computerContainer.reloadTable();
			JOptionPane.showMessageDialog(this, "The computer edited successfully", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void cancel() {
		dispose();
	}
}
