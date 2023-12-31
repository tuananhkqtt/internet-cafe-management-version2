package view.mainContainerView.cardsContainerView.computerManagement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.computer.ComputerController;
import dao.ComputerDAO;
import model.Computer;
import swing.Table;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ComputerContainer extends JPanel {
	private int width = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int height = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64;
	private JTextField textField;
	private Table table;
	/**
	 * Create the panel.
	 */
	public ComputerContainer() {
		setBackground(Color.WHITE);
		setBounds(0, 0, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64);
		setLayout(null);
		
		JLabel label_title = new JLabel("Computer Management");
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
		textField.addKeyListener(new ComputerController(this));
		add(textField);
		
		JButton button_add = new JButton();
		button_add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_add.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_add.setBounds(width-240, 32, 192, 48);
		button_add.setBackground(Color.decode("#831843"));
		button_add.setForeground(Color.WHITE);
		button_add.setIconTextGap(12);
		button_add.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		button_add.setText("Add Computer");
		button_add.addActionListener(new ComputerController(this));
		add(button_add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 112, width-96, height-144);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		String[] columnsName = new String[] {"Id", "Name", "Price", "Create At"};
		table = new Table(columnsName, new ComputerController(this), null, true);
		table.setCenterColumn(0);
		addRows();
        scrollPane.setViewportView(table);
	}

	public void addRows() {
		TreeSet<Computer> computers = ComputerDAO.getInstance().selectAll();
		for (Computer computer : computers) {
			table.addRow(computer.toArray());
		}
	}
	
	public void reloadTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        addRows();
	}
	
	public void addComputer() {
		new AddComputerDialog(this).setVisible(true);
	}
	
	public void edit() {
		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
		Computer computer = new Computer(id);
		computer = ComputerDAO.getInstance().selectById(computer);
        new EditComputerDialog(this, computer).setVisible(true);
	}
	
	public void delete() {
		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this computer?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
			Computer computer = new Computer(id);
            ComputerDAO.getInstance().delete(computer);
            reloadTable();
        }
	}
	
	public void search() {
		String condition = "id LIKE '%"+textField.getText()+"%' OR name LIKE '%"+textField.getText()+"%'";
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
		TreeSet<Computer> computers = ComputerDAO.getInstance().selectByCondition(condition);
		for (Computer computer : computers) {
			table.addRow(computer.toArray());
		}
	}
}
