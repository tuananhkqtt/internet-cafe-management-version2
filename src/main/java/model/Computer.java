package model;

import java.sql.Date;

public class Computer implements Comparable<Computer>{
	private int id;
	private String name;
	private int price;
	private Date createdAt = new Date(new java.util.Date().getTime());
	public Computer() {
		super();
	}
	
	public Computer(int id) {
		super();
		this.id = id;
	}



	public Computer(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Computer(int id, String name, int price, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.createdAt = createdAt;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Object[] toArray() {
		return new Object[] {
			id, name, price, createdAt
		};
	}
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", price=" + price + ", createdAt=" + createdAt + "]";
	}

	@Override
	public int compareTo(Computer o) {
		// TODO Auto-generated method stub
		if(this.id == o.id)
			return 0;
		return 1;
	}
}