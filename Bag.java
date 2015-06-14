package com.baggagesystem.entities;

public class Bag 
{
	private String bag_number;
	private String entry_point;
	private String flight_id;
	
	public Bag(String bag_number, String entry_point, String flight_id)
	{
		this.bag_number = bag_number;
		this.entry_point = entry_point;
		this.flight_id = flight_id;
	}

	public String getBag_number() {
		return bag_number;
	}
	public void setBag_number(String bag_number) {
		this.bag_number = bag_number;
	}
	public String getEntry_point() {
		return entry_point;
	}
	public void setEntry_point(String entry_point) {
		this.entry_point = entry_point;
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	@Override
	public String toString() {
		return "Bag [bag_number=" + bag_number + ", entry_point=" + entry_point
				+ ", flight_id=" + flight_id + "]";
	}
	
}
