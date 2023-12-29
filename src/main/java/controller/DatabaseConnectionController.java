package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.DatabaseConnectionView;

public class DatabaseConnectionController implements ActionListener {
	private DatabaseConnectionView databaseConnectionView;

	public DatabaseConnectionController(DatabaseConnectionView databaseConnectionView) {
		super();
		this.databaseConnectionView = databaseConnectionView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Connect":
			this.databaseConnectionView.connect();
			break;
		case "Cancel":
			this.databaseConnectionView.cancel();
			break;
		}
	}

}