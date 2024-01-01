package controller.account;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import swing.ButtonCellEditor;
import swing.Table;
import view.mainContainerView.cardsContainerView.AccountManagement.AccountContainer;

public class AccountController implements ActionListener, KeyListener{
	AccountContainer accountContainer;
	public AccountController(AccountContainer accountContainer) {
		super();
		this.accountContainer = accountContainer;
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
			accountContainer.search();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("Add Account")) {
			accountContainer.addAccount();
		} else {
			Table table = (Table) ((Component) e.getSource()).getParent();
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			if(table.getColumnName(column).equalsIgnoreCase("Edit")) {
				accountContainer.edit();
			} else if(table.getColumnName(column).equalsIgnoreCase("Delete")) {
				accountContainer.delete();
			}
			((ButtonCellEditor) table.getCellEditor(row, column)).fireEditingStopped();
		}
	}
}
