package model;

public class Registration {

	private int id;
	private String name;
	private String address;
	private double contact;
	private int age;
	private String confirmationNo;
	private double rate;
	
	
	public Registration() {
	}
	

	public Registration(String name, String address, double contact, int age, String confirmationNo, double rate) {

		this.name = name;
		this.address = address;
		this.contact = contact;
		this.age = age;
		this.confirmationNo = confirmationNo;
		this.rate = rate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getContact() {
		return contact;
	}
	public void setContact(double contact) {
		this.contact = contact;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getConfirmationNo() {
		return confirmationNo;
	}
	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}

	public String toString() {
		return "Registration [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact + ", age="
				+ age + ", confirmationNo=" + confirmationNo + ", rate=" + rate + "]";
	}
	
	
}
