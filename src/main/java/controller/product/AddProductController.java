package controller.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.ProductManagement.AddProductDialog;

public class AddProductController implements ActionListener{
	private AddProductDialog addProductDialog;

	public AddProductController(AddProductDialog addProductDialog) {
		super();
		this.addProductDialog = addProductDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			addProductDialog.ok();
		}else if(e.getActionCommand() == "Cancel") {
			addProductDialog.cancel();
		}
	}
	
}
