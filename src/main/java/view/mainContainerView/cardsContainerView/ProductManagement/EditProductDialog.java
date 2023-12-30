package view.mainContainerView.cardsContainerView.ProductManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.product.EditProductController;
import dao.ProductDAO;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EditProductDialog extends JDialog {
	private ProductContainer productContainer;
	private Product product;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_price;
	private JTextField textField_quantity;
	private JTextField textField_createdAt;
	
	private File imageFile;
	private String imageParentPath;
	private JLabel label_fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditProductDialog dialog = new EditProductDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditProductDialog(ProductContainer productContainer, Product product) {
		this.productContainer = productContainer;
		this.product = product;
		
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
				JLabel lblEditProduct = new JLabel("Edit Product");
				lblEditProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel_jLabel.add(lblEditProduct);
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
				JLabel lable_quantity = new JLabel("Quantity");
				panel_jLabels.add(lable_quantity);
			}
			{
				JLabel lable_image = new JLabel("Image");
				panel_jLabels.add(lable_image);
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
				textField_price = new JTextField();
				panel_jTextFields.add(textField_price);
				textField_price.setColumns(10);
			}
			{
				textField_quantity = new JTextField();
				textField_quantity.setColumns(10);
				panel_jTextFields.add(textField_quantity);
			}
			{
				JPanel panel = new JPanel();
				panel_jTextFields.add(panel);
				{
					JButton btnChooseImage = new JButton("Choose Image");
					btnChooseImage.addActionListener(new EditProductController(this));
					panel.setLayout(new BorderLayout(0, 0));
					panel.add(btnChooseImage, BorderLayout.WEST);
				}
				{
					label_fileName = new JLabel("");
					panel.add(label_fileName, BorderLayout.CENTER);
				}
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
				okButton.addActionListener(new EditProductController(this));
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(Color.decode("#831843"));
				cancelButton.setForeground(Color.WHITE);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new EditProductController(this));
			}
		}
		setLocationRelativeTo(null);
		showProductInfor();
	}
	
	private void showProductInfor() {
		textField_id.setText(product.getId()+"");
		textField_id.setEditable(false);
		
		textField_name.setText(product.getName());
		
		textField_price.setText(product.getPrice()+"");
		
		textField_quantity.setText(product.getQuantity()+"");
		
		URL fullImageUrl = getClass().getResource(product.getImageUrl());
		imageFile = new File(fullImageUrl.getFile());
		imageParentPath = imageFile.getParent();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createdAt = simpleDateFormat.format(product.getCreatedAt());
		textField_createdAt.setText(createdAt);
		textField_createdAt.setEditable(false);
	}
	
	public void ok() {
		if(textField_name.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter name.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(textField_price.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter price.", "", JOptionPane.INFORMATION_MESSAGE);
		} else if(textField_quantity.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter quantity.", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String name = textField_name.getText();
			int price = Integer.parseInt(textField_price.getText());
			int quantity = Integer.parseInt(textField_quantity.getText());
			product.setName(name);
			product.setPrice(price);
			product.setQuantity(quantity);
			if(!label_fileName.getText().equals("")) {
				saveImage(imageFile, imageParentPath, "product"+product.getId()+"."+getFileExtension(imageFile));
				product.setImageUrl("/images/"+"product"+product.getId()+"."+getFileExtension(imageFile));
			}
			ProductDAO.getInstance().update(product);
			productContainer.reloadTable();
			JOptionPane.showMessageDialog(this, "The product edited successfully", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void cancel() {
		dispose();
	}
	
	public void chooseImage() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Image");
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = fileChooser.getSelectedFile();
            if (isImageFile(selectedFile)) {
            	imageFile = selectedFile;
            	label_fileName.setText(imageFile.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "This file is not an image!");
            }
        }
        
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
	}
	
	private void saveImage(File sourceFile, String fileParentPath, String fileName) {
        try {
            Path sourcePath = sourceFile.toPath();
            Path destinationPath = Path.of(fileParentPath, fileName);
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            destinationPath = Path.of("src/main/resources/images", fileName);
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private boolean isImageFile(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            return image != null;
        } catch (IOException e) {
            return false;
        }
    }
	
	private String getFileExtension(File file) {
        String extension = null;
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            extension = fileName.substring(lastDotIndex + 1);
        }
        return extension;
    }
}
