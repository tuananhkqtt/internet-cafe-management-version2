package view.mainContainerView.cardsContainerView.ProductManagement;

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

import controller.product.ProductController;
import dao.ProductDAO;
import model.Product;
import swing.Table;

import java.awt.Cursor;

public class ProductContainer extends JPanel {
	private int width = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
	private int height = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64;
	private JTextField textField;
	private Table table;
	private String[] columnsName;
	/**
	 * Create the panel.
	 */
	public ProductContainer() {
		setBackground(Color.WHITE);
		setBounds(0, 0, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()-64);
		setLayout(null);
		
		JLabel label_title = new JLabel("Product Management");
		label_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_title.setBounds(192, 32, 240, 48);
		add(label_title);
		
		JLabel label_search = new JLabel("Search by Id or Name");
		label_search.setBounds(width-848, 32, 160, 48);
		label_search.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label_search);
		
		textField = new JTextField();
		textField.setBounds(width-672, 32, 432, 48);
		textField.setMargin(new Insets(8, 24, 8, 24));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.addKeyListener(new ProductController(this));
		add(textField);
		
		JButton button_add = new JButton();
		button_add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_add.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_add.setBounds(width-192, 32, 144, 48);
		button_add.setBackground(Color.decode("#831843"));
		button_add.setForeground(Color.WHITE);
		button_add.setIconTextGap(12);
		button_add.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
		button_add.setText("Add Product");
		button_add.addMouseListener(new ProductController(this));
		add(button_add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 112, width-96, height-144);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		columnsName = new String[] {"Id", "Name", "Price", "Quantity", "Image", "Created At"};
		JButton button = new JButton();
		button.setIcon(new ImageIcon(getClass().getResource("/icons/dot.png")));
		button.addMouseListener(new ProductController(this));
		table = new Table(columnsName, new ProductController(this), 4, button);
		addRows();
        scrollPane.setViewportView(table);
	}
	
	public void addRows() {
		TreeSet<Product> products = ProductDAO.getInstance().selectAll();
		for (Product product : products) {
			table.addRow(product.toArray());
		}
		table.setImageCell(new ImageIcon(ProductContainer.class.getResource("/icons/dot.png")), 4);
	}
	
	public void reloadTable() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
        addRows();
	}
	
	public void click(MouseEvent e) {
		if(((AbstractButton) e.getComponent()).getText() == "Add Product") {
			addProduct();
		}else {
			Object[] rowData = new Object[table.getModel().getColumnCount()];
	        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
	            rowData[i] = table.getModel().getValueAt(table.getSelectedRow(), i);
	        }
	        
	        int id = (int) rowData[0];
	        Product product = new Product(id);
	        product = ProductDAO.getInstance().selectById(product);
			if(table.getColumnCount()-2 == (table.getSelectedColumn())) {
				edit(product);
			} else if(table.getColumnCount()-1 == table.getSelectedColumn()) {
				delete(product);
			} else if(4 == table.getSelectedColumn()) {
				showImage(product);
			}
		}
	}
	
	private void addProduct() {
		new AddProductDialog(this).setVisible(true);
	}
	
	private void edit(Product product) {
        new EditProductDialog(this, product).setVisible(true);
	}
	
	private void delete(Product product) {
		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
            ProductDAO.getInstance().delete(product);
            reloadTable();
        }
	}
	
	private void showImage(Product product) {
		new ImageDialog(product.getImageUrl()).setVisible(true);;
	}
	
	public void search() {
		String condition = "id LIKE '%"+textField.getText()+"%' OR name LIKE '%"+textField.getText()+"%'";
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
		TreeSet<Product> products = ProductDAO.getInstance().selectByCondition(condition);
		for (Product product : products) {
			table.addRow(product.toArray());
		}
	}
}