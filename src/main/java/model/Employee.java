package model;

import dao.AccountDAO;

public class Employee implements Comparable<Employee>{
	private int id;
	private String name;
	private int accountId;
	private String email;
	private String phoneNumber;
	private String address;
	public Employee() {
		super();
	}
	
	public Employee(int id) {
		super();
		this.id = id;
	}

	public Employee(int id, String name, int accountId, String email, String phoneNumber, String address) {
		super();
		this.id = id;
		this.name = name;
		this.accountId = accountId;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Object[] toArray() {
		Account account = new Account(accountId);
		account = AccountDAO.getInstance().selectById(account);
		return new Object[] {
				id, name, account.getUsername(), email, phoneNumber, address
		};
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", accountId=" + accountId + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}

	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		if(this.id == o.id)
			return 0;
		return 1;
	}
	
	
}
