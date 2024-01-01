package model;

import java.sql.Date;

public class Product implements Comparable<Product>{
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", imageUrl="
				+ imageUrl + ", createdAt=" + createdAt + "]";
	}

	private int id;
	private String name;
	private int price;
	private int quantity;
	private String imageUrl;
	private Date createdAt = new Date(new java.util.Date().getTime());
	public Product() {
		super();
	}
	public Product(int id) {
		super();
		this.id = id;
	}
	public Product(int id, String name, int price, int quantity, String imageUrl, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imageUrl = imageUrl;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Object[] toArray() {
		return new Object[] {
				id, name, price, quantity, imageUrl, createdAt
		};
	}
	
	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		if(this.id == o.id)
			return 0;
		return 1;
	}
	
	
}
