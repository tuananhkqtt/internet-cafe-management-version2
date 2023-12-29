package controller.invoice;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;

import view.mainContainerView.cardsContainerView.InvoiceManagement.InvoiceContainer;

public class InvoiceController implements MouseListener{
	private InvoiceContainer invoiceContainer;

	public InvoiceController(InvoiceContainer invoiceContainer) {
		super();
		this.invoiceContainer = invoiceContainer;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (((AbstractButton)e.getComponent()).getText()) {
		case "Previous":
			invoiceContainer.previous();
			break;
		case "Next":
			invoiceContainer.next();
			break;
		default:
			invoiceContainer.editOrDelete(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
