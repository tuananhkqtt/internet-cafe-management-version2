package view.mainContainerView.cardsContainerView.InvoiceManagement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.invoice.InvoiceController;
import dao.InvoiceDAO;
import model.BillStatus;
import model.Invoice;
import swing.Table;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class InvoiceContainer extends JPanel {
	private int width = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int height = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()
			- 64;
	private Table table;
	private JTextField textField;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public InvoiceContainer() {
		setBackground(Color.WHITE);
		setBounds(0, 0, (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(),
				(int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() - 64);
		setLayout(null);

		JLabel label_title = new JLabel("Invoice Management");
		label_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_title.setBounds(192, 32, 240, 48);
		add(label_title);

		JPanel panel_pagination = new JPanel();
		panel_pagination.setBackground(Color.WHITE);
		panel_pagination.setBounds(506, 640, 256, 48);
		add(panel_pagination);
		panel_pagination.setLayout(null);

		JButton button_previous = new JButton("Previous");
		button_previous.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_previous.setBounds(0, 0, 96, 48);
		button_previous.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_previous.setBackground(Color.decode("#831843"));
		button_previous.setForeground(Color.WHITE);
		button_previous.addMouseListener(new InvoiceController(this));
		panel_pagination.add(button_previous);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setText("1");
		textField.setEditable(false);
		textField.setBounds(104, 0, 48, 48);
		panel_pagination.add(textField);
		textField.setColumns(10);

		JButton button_next = new JButton("Next");
		button_next.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_next.setBounds(160, 0, 96, 48);
		button_next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_next.setBackground(Color.decode("#831843"));
		button_next.setForeground(Color.WHITE);
		button_next.addMouseListener(new InvoiceController(this));
		panel_pagination.add(button_next);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 112, width - 96, height - 256);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);

		String[] columnsName = new String[] { "Id", "Account Username", "Computer Name", "Total", "Created At",
				"Status", "CreatedBy" };
		table = new Table(columnsName, new InvoiceController(this));
		addRows();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(140);
		table.getColumnModel().getColumn(8).setPreferredWidth(140);

		scrollPane.setViewportView(table);

		JLabel label_sum = new JLabel();
		label_sum.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_sum.setBounds(width-288, height-112, 240, 48);
		label_sum.setForeground(Color.BLACK);
		label_sum.setText("TODAY REVENUE: "+InvoiceDAO.getInstance().getTodayRevenueSum());
		add(label_sum);
	}
	
	public void addRows() {
		int limit = 10;
		int offset = (Integer.parseInt(textField.getText()) - 1) * limit;
		TreeSet<Invoice> invoices = InvoiceDAO.getInstance().selectAllOrderByCreatedAtOffset(offset, limit);
		for (Invoice invoice : invoices) {
			table.addRow(invoice.toArray());
		}
	}

	public void reloadTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		addRows();
	}

	public void editOrDelete(MouseEvent e) {
		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
		Invoice invoice = new Invoice(id);
		invoice = InvoiceDAO.getInstance().selectById(invoice);
		if (table.getColumnCount() - 2 == (table.getSelectedColumn())) {
			edit(invoice);
		} else if (table.getColumnCount() - 1 == table.getSelectedColumn()) {
			delete(invoice);
		}
	}

	private void edit(Invoice invoice) {
		new EditInvoiceDialog(this, invoice).setVisible(true);
	}

	private void delete(Invoice invoice) {
		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this invoice?",
				"Confirmation", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			InvoiceDAO.getInstance().delete(invoice);
			reloadTable();
		}
	}

	public void previous() {
		if (!textField.getText().equalsIgnoreCase("1")) {
			textField.setText(Integer.parseInt(textField.getText()) - 1 + "");
			reloadTable();
		}
	}

	public void next() {
		textField.setText(Integer.parseInt(textField.getText()) + 1 + "");
		reloadTable();
	}
}
