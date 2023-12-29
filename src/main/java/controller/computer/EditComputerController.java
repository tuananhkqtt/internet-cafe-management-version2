package controller.computer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainContainerView.cardsContainerView.computerManagement.EditComputerDialog;

public class EditComputerController implements ActionListener{
	private EditComputerDialog editComputerDialog;

	public EditComputerController(EditComputerDialog editComputerDialog) {
		super();
		this.editComputerDialog = editComputerDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "OK") {
			editComputerDialog.ok();
		} else if (e.getActionCommand() == "Cancel") {
			editComputerDialog.cancel();
		}
	}
}
