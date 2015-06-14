package com.baggagesystem.dto;

public class OptimizedRoute 
{
	private String bagId;
	private String route;
	private int duration;
	
	public OptimizedRoute(String bagId, String route, int duration) {
		super();
		this.bagId = bagId;
		this.route = route;
		this.duration = duration;
	}
	
	public String getBagId() {
		return bagId;
	}
	
	public String getRoute() {
		return route;
	}
	
	public int getDuration() {
		return duration;
	}
	
	
	
	
}
