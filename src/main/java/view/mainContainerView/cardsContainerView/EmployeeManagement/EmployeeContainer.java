package view.mainContainerView.cardsContainerView.EmployeeManagement;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.employee.EmployeeController;
import dao.AccountDAO;
import dao.EmployeeDAO;
import model.Account;
import model.Employee;
import model.Role;
import swing.Table;

import java.awt.Cursor;

public class EmployeeContainer extends JPanel {
	private int width = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int height = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64;
	private JTextField textField;
	private Table table;
	/**
	 * Create the panel.
	 */
	public EmployeeContainer() {
		setBackground(Color.WHITE);
		setBounds(0, 0, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64);
		setLayout(null);
		
		JLabel label_title = new JLabel("Employee Management");
		label_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_title.setBounds(192, 32, 240, 48);
		add(label_title);
		
		JLabel label_search = new JLabel("Search by Id or Name");
		label_search.setBounds(width-896, 32, 160, 48);
		label_search.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label_search);
		
		textField = new JTextField();
		textField.setBounds(width-720, 32, 432, 48);
		textField.setMargin(new Insets(8, 24, 8, 24));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.addKeyListener(new EmployeeController(this));
		add(textField);
		
		JButton button_add = new JButton();
		button_add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_add.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_add.setBounds(width-240, 32, 192, 48);
		button_add.setBackground(Color.decode("#831843"));
		button_add.setForeground(Color.WHITE);
		button_add.setIconTextGap(12);
		button_add.setIcon(new ImageIcon(EmployeeContainer.class.getResource("/icons/notification.png")));
		button_add.setText("Add Employee");
		button_add.addActionListener(new EmployeeController(this));
		add(button_add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 112, width-96, height-144);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		String[] columnsName = new String[] {"Id", "Name", "Account Username", "Email", "PhoneNumber", "Address"};		
		table = new Table(columnsName, new EmployeeController(this), null);
		table.setCenterColumn(0);
		addRows();
        scrollPane.setViewportView(table);
	}
	
	public void addRows() {
		TreeSet<Employee> employees = EmployeeDAO.getInstance().selectAll();
		for (Employee employee : employees) {
			table.addRow(employee.toArray());
		}
	}
	
	public void reloadTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        addRows();
	}
	
	public void addEmployee() {
		new AddEmployeeDialog(this).setVisible(true);
	}
	
	public void edit() {
        int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
        Employee employee = new Employee(id);
        employee = EmployeeDAO.getInstance().selectById(employee);
        new EditEmployeeDialog(this, employee).setVisible(true);
	}
	
	public void delete() {
		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
        Employee employee = new Employee(id);
        employee = EmployeeDAO.getInstance().selectById(employee);
        
		Account account = new Account(employee.getAccountId());
		account = AccountDAO.getInstance().selectById(account);
		if(account.getRole()==Role.admin)
			JOptionPane.showMessageDialog(this, "You can't delete this because this is admin.", "", JOptionPane.INFORMATION_MESSAGE);
		else {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 10));
			
			JLabel jLabel = new JLabel("Are you sure you want to delete this employee?");
			panel.add(jLabel);
			
			JCheckBox checkBox = new JCheckBox("Delete employee account on account management");
			panel.add(checkBox);
			
			int result = JOptionPane.showConfirmDialog(this, panel, "Confirmation", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if(checkBox.isSelected()) {
					AccountDAO.getInstance().delete(account);
				} else {
					EmployeeDAO.getInstance().delete(employee);
				}
	            reloadTable();
	        }
		}
	}
	
	public void search() {
		String condition = "id LIKE '%"+textField.getText()+"%' OR name LIKE '%"+textField.getText()+"%'";
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
		TreeSet<Employee> employees = EmployeeDAO.getInstance().selectByCondition(condition);
		for (Employee employee : employees) {
			table.addRow(employee.toArray());
		}
	}
}
