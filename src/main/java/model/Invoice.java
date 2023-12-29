package model;

import java.sql.Timestamp;

import dao.AccountDAO;
import dao.ComputerDAO;
import dao.EmployeeDAO;

public class Invoice implements Comparable<Invoice>{
	private int id;
	private int accountId;
	private int computerId;
	private int total;
	private Timestamp createdAt = new Timestamp(new java.util.Date().getTime());;
	private BillStatus status;
	private int createdBy;
	public Invoice() {
		super();
	}
	
	public Invoice(int id) {
		super();
		this.id = id;
	}
	
	public Invoice(int id, int total, BillStatus status) {
		super();
		this.id = id;
		this.total = total;
		this.status = status;
	}

	public Invoice(int id, int accountId, int computerId, int total, Timestamp createdAt, BillStatus status,
			int createdBy) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.computerId = computerId;
		this.total = total;
		this.createdAt = createdAt;
		this.status = status;
		this.createdBy = createdBy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getComputerId() {
		return computerId;
	}
	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public BillStatus getStatus() {
		return status;
	}
	public void setStatus(BillStatus status) {
		this.status = status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Account user = new Account(accountId);
		user = AccountDAO.getInstance().selectById(user);
		Computer computer = new Computer(computerId);
		computer = ComputerDAO.getInstance().selectById(computer);
		Employee employee = new Employee(createdBy);
		employee = EmployeeDAO.getInstance().selectById(employee);
		
		return new Object[] {
				id, user.getUsername(), computer.getName(), total, createdAt, status, employee.getName()
		};
	}
	
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", accountId=" + accountId + ", computerId=" + computerId + ", total=" + total
				+ ", createdAt=" + createdAt + ", status=" + status + ", createdBy=" + createdBy + "]";
	}
	
	@Override
	public int compareTo(Invoice o) {
		// TODO Auto-generated method stub
		if(this.id == o.id)
			return 0;
		return 1;
	}
	
	
}
