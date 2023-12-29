package view.mainContainerView.cardsContainerView.EmployeeManagement;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
		button_add.addMouseListener(new EmployeeController(this));
		add(button_add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 112, width-96, height-144);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		String[] columnsName = new String[] {"Id", "Name", "Account Username", "Email", "PhoneNumber", "Address"};		
		table = new Table(columnsName, new EmployeeController(this));
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
	
	public void click(MouseEvent e) {
		if(((AbstractButton) e.getComponent()).getText() == "Add Employee") {
			addEmployee();
		}else {
			Object[] rowData = new Object[table.getModel().getColumnCount()];
	        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
	            rowData[i] = table.getModel().getValueAt(table.getSelectedRow(), i);
	        }
	        
	        int id = (int) rowData[0];
	        String name = (String) rowData[1];
	        
	        Account account = AccountDAO.getInstance().selectByUsername((String) rowData[2]);
	        int accountId = account.getId();
	        
	        String email = (String) rowData[3];
	        String phoneNumber = (String) rowData[4];
	        String address = (String) rowData[5];
	        Employee employee = new Employee(id, name, accountId, email, phoneNumber, address);
			if(table.getColumnCount()-2 == (table.getSelectedColumn())) {
				edit(employee);
			} else if(table.getColumnCount()-1 == table.getSelectedColumn()) {
				delete(employee);
			}
		}
	}
	
	private void addEmployee() {
		new AddEmployeeDialog(this).setVisible(true);
	}
	
	private void edit(Employee employee) {
        new EditEmployeeDialog(this, employee).setVisible(true);
	}
	
	private void delete(Employee employee) {
		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
            EmployeeDAO.getInstance().delete(employee);
            reloadTable();
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
