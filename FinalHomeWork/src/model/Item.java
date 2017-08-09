package model;

import java.time.LocalDateTime;

public class Item {

	private int id;
	private String title;
	private int code;
	private String producer;
	private LocalDateTime dateOfLastUpdate;
	
	
	public Item() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getProducer() {
		return producer;
	}


	public void setProducer(String producer) {
		this.producer = producer;
	}


	public LocalDateTime getDateOfLastUpdate() {
		return dateOfLastUpdate;
	}


	public void setDateOfLastUpdate(LocalDateTime dateOfLastUpdate) {
		this.dateOfLastUpdate = dateOfLastUpdate;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", code=" + code + ", producer=" + producer
				+ ", dateOfLastUpdate=" + dateOfLastUpdate + "]";
	}
	
	
	
	
	
	
}
