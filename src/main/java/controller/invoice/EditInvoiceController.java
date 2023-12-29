package controller.invoice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.InvoiceManagement.EditInvoiceDialog;

public class EditInvoiceController implements ActionListener{
	private EditInvoiceDialog editInvoiceDialog;

	public EditInvoiceController(EditInvoiceDialog editInvoiceDialog) {
		super();
		this.editInvoiceDialog = editInvoiceDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			editInvoiceDialog.ok();
		}else if(e.getActionCommand() == "Cancel") {
			editInvoiceDialog.cancel();
		}
	}
	
}
