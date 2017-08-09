package model;

import java.time.LocalDate;
import java.util.Arrays;

public class Customer {


	private int id;
	private String name;
	private LocalDate dateOfBirth;
	private String address;
	private String gender;
	private String phoneNumber;
	private int[] lastPurchases;
	private LocalDate dateOfLastPurchase;
	
	
	
	
	public Customer() {
		super();
	}


	public Customer(int id, String name, LocalDate dateOfBirth, String address, String gender, String phoneNumber,
			int[] lastPurchases, LocalDate dateOfLastPurchase) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.lastPurchases = lastPurchases;
		this.dateOfLastPurchase = dateOfLastPurchase;
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


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int[] getLastPurchases() {
		return lastPurchases;
	}


	public void setLastPurchases(int[] lastPurchases) {
		this.lastPurchases = lastPurchases;
	}


	public LocalDate getDateOfLastPurchase() {
		return dateOfLastPurchase;
	}


	public void setDateOfLastPurchase(LocalDate dateOfLastPurchase) {
		this.dateOfLastPurchase = dateOfLastPurchase;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", address=" + address
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", lastPurchases="
				+ Arrays.toString(lastPurchases) + ", dateOfLastPurchase=" + dateOfLastPurchase + "]";
	}
	
	
	
	
	
}
