package controller.product;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import swing.ButtonCellEditor;
import swing.Table;
import view.mainContainerView.cardsContainerView.ProductManagement.ProductContainer;

public class ProductController implements ActionListener, KeyListener{
	ProductContainer productContainer;

	public ProductController(ProductContainer productContainer) {
		super();
		this.productContainer = productContainer;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() instanceof JTextField) {
			productContainer.search();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("Add Product")) {
			productContainer.addProduct();
		} else {
			Table table = (Table) ((Component) e.getSource()).getParent();
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			if(table.getColumnName(column).equalsIgnoreCase("Edit")) {
				productContainer.edit();
			} else if(table.getColumnName(column).equalsIgnoreCase("Delete")) {
				productContainer.delete();
			} else if(table.getColumnName(column).equalsIgnoreCase("Image")) {
				productContainer.showImage();
			}
			((ButtonCellEditor) table.getCellEditor(row, column)).fireEditingStopped();
		}
	}
}
