package controller.invoice;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import swing.ButtonCellEditor;
import swing.Table;
import view.mainContainerView.cardsContainerView.InvoiceManagement.InvoiceContainer;

public class InvoiceController implements ActionListener{
	private InvoiceContainer invoiceContainer;

	public InvoiceController(InvoiceContainer invoiceContainer) {
		super();
		this.invoiceContainer = invoiceContainer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equalsIgnoreCase("Previous")) {
			invoiceContainer.previous();
		} else if (e.getActionCommand().equalsIgnoreCase("Next")) {
			invoiceContainer.next();
		} else {
			Table table = (Table) ((Component) e.getSource()).getParent();
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			if(table.getColumnName(column).equalsIgnoreCase("Edit")) {
				invoiceContainer.edit();
			} else if(table.getColumnName(column).equalsIgnoreCase("Delete")) {
				invoiceContainer.delete();
			}
			((ButtonCellEditor) table.getCellEditor(row, column)).fireEditingStopped();
		}
	}
	
}
