package view.mainContainerView.cardsContainerView.AccountManagement;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.account.AccountController;
import dao.AccountDAO;
import dao.EmployeeDAO;
import model.Account;
import model.Employee;
import swing.Table;

import java.awt.Cursor;

public class AccountContainer extends JPanel {
	private int width = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int height = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64;
	private JTextField textField;
	private Table table;
	/**
	 * Create the panel.
	 */
	public AccountContainer() {
		setBackground(Color.WHITE);
		setBounds(0, 0, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64);
		setLayout(null);
		
		JLabel label_title = new JLabel("Account Management");
		label_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_title.setBounds(192, 32, 240, 48);
		add(label_title);
		
		JLabel label_search = new JLabel("Search by Id or Username");
		label_search.setBounds(width-848, 32, 160, 48);
		label_search.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label_search);
		
		textField = new JTextField();
		textField.setBounds(width-672, 32, 432, 48);
		textField.setMargin(new Insets(8, 24, 8, 24));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.addKeyListener(new AccountController(this));
		add(textField);
		
		JButton button_add = new JButton();
		button_add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_add.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_add.setBounds(width-192, 32, 144, 48);
		button_add.setBackground(Color.decode("#831843"));
		button_add.setForeground(Color.WHITE);
		button_add.setIconTextGap(12);
		button_add.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		button_add.setText("Add Account");
		button_add.addActionListener(new AccountController(this));
		add(button_add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 112, width-96, height-144);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		String[] columnsName = new String[] {"Id", "Username", "Password", "Role", "Balance", "Create At"};
		table = new Table(columnsName, new AccountController(this), null, true);
		table.setCenterColumn(0);
		addRows();
        scrollPane.setViewportView(table);
	}
	
	public void addRows() {
		TreeSet<Account> accounts = AccountDAO.getInstance().selectAll();
		for (Account account : accounts) {
			table.addRow(account.toArray());
		}
	}
	
	public void reloadTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        addRows();
	}
	
	public void addAccount() {
		new AddAccountDialog(this).setVisible(true);
	}
	
	public void edit() {
		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
		Account account = new Account(id);
		account = AccountDAO.getInstance().selectById(account);
        new EditAccountDialog(this, account).setVisible(true);
	}
	
	public void delete() {
        int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
        Account account = new Account(id);
        
		Employee employee = new Employee();
		employee.setAccountId(account.getId());
		employee = EmployeeDAO.getInstance().selectByAccountId(employee);
		
		if(employee != null){
			JOptionPane.showMessageDialog(this, "You can't delete this account.", "", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this account?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
	            AccountDAO.getInstance().delete(account);
	            reloadTable();
	        }
		}
	}
	
	public void search() {
		String condition = "id LIKE '%"+textField.getText()+"%' OR username LIKE '%"+textField.getText()+"%'";
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
		TreeSet<Account> accounts = AccountDAO.getInstance().selectByCondition(condition);
		for (Account account : accounts) {
			table.addRow(account.toArray());
		}
	}
}
