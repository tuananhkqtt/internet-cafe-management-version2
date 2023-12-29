package view.mainContainerView.cardsContainerView.ProductManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.product.AddProductController;
import dao.ProductDAO;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;

public class AddProductDialog extends JDialog {
	private ProductContainer productContainer;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_name;
	private JTextField textField_price;
	private JTextField textField_quantity;
	private JTextField textField_imageUrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddProductDialog dialog = new AddProductDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddProductDialog(ProductContainer productContainer) {
		this.productContainer = productContainer;
		
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
				JLabel label_header = new JLabel("Add a new Product");
				label_header.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(label_header);
			}
		}
		{
			JPanel panel_jLabels = new JPanel();
			contentPanel.add(panel_jLabels, BorderLayout.WEST);
			panel_jLabels.setLayout(new GridLayout(0, 1, 0, 5));
			{
				JLabel label_name = new JLabel("Name");
				panel_jLabels.add(label_name);
			}
			{
				JLabel label_price = new JLabel("Price");
				panel_jLabels.add(label_price);
			}
			{
				JLabel label_quantity = new JLabel("Quantity");
				panel_jLabels.add(label_quantity);
			}
			{
				JLabel label_imageUrl = new JLabel("Image Url");
				panel_jLabels.add(label_imageUrl);
			}
		}
		{
			JPanel panel_jTextFields = new JPanel();
			contentPanel.add(panel_jTextFields, BorderLayout.CENTER);
			panel_jTextFields.setLayout(new GridLayout(0, 1, 0, 5));
			{
				textField_name = new JTextField();
				textField_name.setColumns(10);
				panel_jTextFields.add(textField_name);
			}
			{
				textField_price = new JTextField();
				textField_price.setColumns(10);
				panel_jTextFields.add(textField_price);
			}
			{
				textField_quantity = new JTextField();
				textField_quantity.setColumns(10);
				panel_jTextFields.add(textField_quantity);
			}
			{
				textField_imageUrl = new JTextField();
				textField_imageUrl.setColumns(10);
				panel_jTextFields.add(textField_imageUrl);
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
				okButton.addActionListener(new AddProductController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new AddProductController(this));
			}
		}
		setLocationRelativeTo(null);
	}
	
	public void ok() {
		String name = textField_name.getText();
		int price = Integer.parseInt(textField_price.getText());
		int quantity = Integer.parseInt(textField_quantity.getText());
		String imageUrl = textField_imageUrl.getText();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter name.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(price == 0) {
			JOptionPane.showMessageDialog(this, "Please select price.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(quantity == 0) {
			JOptionPane.showMessageDialog(this, "Please select quantity.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(imageUrl.equals("")) {
			JOptionPane.showMessageDialog(this, "Please select imageUrl.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setImageUrl(imageUrl);
			ProductDAO.getInstance().insert(product);
			productContainer.reloadTable();
			JOptionPane.showMessageDialog(this, "New product added successfully", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void cancel() {
		dispose();
	}
}
