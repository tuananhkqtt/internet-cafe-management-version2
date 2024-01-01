package controller.computer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import swing.ButtonCellEditor;
import swing.Table;
import view.mainContainerView.cardsContainerView.computerManagement.ComputerContainer;

public class ComputerController implements ActionListener, KeyListener{
	private ComputerContainer computerContainer;

	public ComputerController(ComputerContainer computerContainer) {
		super();
		this.computerContainer = computerContainer;
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
			computerContainer.search();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("Add Computer")) {
			computerContainer.addComputer();
		} else {
			Table table = (Table) ((Component) e.getSource()).getParent();
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			if(table.getColumnName(column).equalsIgnoreCase("Edit")) {
				computerContainer.edit();
			} else if(table.getColumnName(column).equalsIgnoreCase("Delete")) {
				computerContainer.delete();
			}
			((ButtonCellEditor) table.getCellEditor(row, column)).fireEditingStopped();
		}
	}
	
}
