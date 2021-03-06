package entity;

import org.apache.taglibs.standard.tag.rt.core.IfTag;

// 商品类
public class Items {
	
	private int id;
	private String name;
	private String city;
	private int price;
	private int number;	// 库存
	private String picture;
	
	public Items(){
		// 无参的构造函数
	}
	
	public Items(int id, String name, String city, int price, int number,
			String picture) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.price = price;
		this.number = number;
		this.picture = picture;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String toString() {
		return "商品编号：" + this.id + ",商品名称： " + this.name;
	}

	// 针对相同商品的处理而重写的方法
	public int hashCode() {
		return this.getId() + this.getName().hashCode();
	}
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}
		
		if(obj instanceof Items) {
			
			Items i = (Items) obj;
			if(i.getId() == this.getId() && i.getName().endsWith(this.getName())) {
				return true; 
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
