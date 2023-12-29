package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;

public class LoginController implements ActionListener {
	private LoginView loginView;

	public LoginController(LoginView loginView) {
		super();
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Login":
			this.loginView.login();
			break;
		case "Exit":
			this.loginView.exit();
			break;
		}
	}

}