package controller.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import view.mainContainerView.cardsContainerView.AccountManagement.AddAccountDialog;

public class AddAccountController implements ActionListener{
	private AddAccountDialog addAccountDialog;

	public AddAccountController(AddAccountDialog addAccountDialog) {
		super();
		this.addAccountDialog = addAccountDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			addAccountDialog.ok();
		}else if(e.getActionCommand() == "Cancel") {
			addAccountDialog.cancel();
		}
	}
	
}
