package com.baggagesystem.entities;

public class Route 
{
	private Terminal terminal1,terminal2;
	private int duration;
	
	public Route(Terminal terminal1, Terminal terminal2, int duration) 
	{
		this.terminal1 = terminal1;
		this.terminal2 = terminal2;
		this.duration = duration;
	}
	public Terminal getTerminal1() {
		return terminal1;
	}
	public void setTerminal1(Terminal terminal1) {
		this.terminal1 = terminal1;
	}
	public Terminal getTerminal2() {
		return terminal2;
	}
	@Override
	public String toString() {
		return "Route [terminal1=" + terminal1 + ", terminal2=" + terminal2
				+ ", duration=" + duration + "]";
	}
	public void setTerminal2(Terminal terminal2) {
		this.terminal2 = terminal2;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
