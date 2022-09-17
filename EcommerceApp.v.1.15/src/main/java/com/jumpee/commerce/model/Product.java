package com.jumpee.commerce.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="product")
public class Product 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	private String manufacturer;
	private String model;
	private float screenSize;
	private String screen;
	private String cpu;
	private String ram;
	private String storage;
	private String gpu;
	private String operatingSystem;
	private int qty;
	private BigDecimal price;
	
	Product(){}

	public Product(int id, String manufacturer, String model, float screenSize, String screen, String cpu,
			String ram, String storage, String gpu, String operatingSystem, int qty, BigDecimal price) 
	{
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.screenSize = screenSize;
		this.screen = screen;
		this.cpu = cpu;
		this.ram = ram;
		this.storage = storage;
		this.gpu = gpu;
		this.operatingSystem = operatingSystem;
		this.qty = qty;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(float screenSize) {
		this.screenSize = screenSize;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
