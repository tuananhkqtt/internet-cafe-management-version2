package controller.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.AccountManagement.EditAccountDialog;

public class EditAccountController implements ActionListener{
	private EditAccountDialog editAccountDialog;

	public EditAccountController(EditAccountDialog editAccountDialog) {
		super();
		this.editAccountDialog = editAccountDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "OK") {
			editAccountDialog.ok();
		}else if(e.getActionCommand() == "Cancel") {
			editAccountDialog.cancel();
		}
	}
	
}
