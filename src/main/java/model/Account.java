package model;

import java.sql.Date;

public class Account implements Comparable<Account>{
	private int id;
	private String username;
	private String password;
	private Role role;
	private int balance;
	private Date createdAt = new Date(new java.util.Date().getTime());
	public Account() {
		super();
	}
	
	
	public Account(int id) {
		super();
		this.id = id;
	}


	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Account(String username, String password, Role role, int balance) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.balance = balance;
	}
	public Account(int id, String username, String password, Role role, int balance, Date createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.balance = balance;
		this.createdAt = createdAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Object[] toArray() {
		return new Object[] {
				id, username, password, role, balance, createdAt
		};
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", balance=" + balance + ", createdAt=" + createdAt + "]";
	}
	@Override
	public int compareTo(Account o) {
		// TODO Auto-generated method stub
		if(this.id == o.id)
			return 0;
		else if(this.username.equals(o.username))
			return 0;
		return 1;
	}
}
