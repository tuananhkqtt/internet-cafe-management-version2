package controller.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.ProductManagement.EditProductDialog;

public class EditProductController implements ActionListener{
	private EditProductDialog editProductDialog;

	public EditProductController(EditProductDialog editProductDialog) {
		super();
		this.editProductDialog = editProductDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			editProductDialog.ok();
		} else if(e.getActionCommand() == "Cancel") {
			editProductDialog.cancel();
		} else if(e.getActionCommand() == "Choose Image") {
			editProductDialog.chooseImage();
		}
	}
	
}
