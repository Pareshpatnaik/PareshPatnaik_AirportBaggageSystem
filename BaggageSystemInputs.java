package com.baggagesystem.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.baggagesystem.entities.Bag;
import com.baggagesystem.entities.Departure;
import com.baggagesystem.entities.Route;
import com.baggagesystem.entities.Terminal;

public class BaggageSystemInputs {
	
	private Set<Terminal> terminals = new HashSet<Terminal>();
	//private Set<Departure> departures = new HashSet<Departure>();
	private Set<Bag> bags = new LinkedHashSet<Bag>();
	private Set<Route> routes = new HashSet<Route>();
	private Map<String,Terminal> departureMap = new HashMap<String,Terminal>();
	
	public Set<Terminal> getTerminals() {
		return terminals;
	}
	public void setTerminals(Set<Terminal> terminals) {
		this.terminals = terminals;
	}
	public Set<Bag> getBags() {
		return bags;
	}
	public void setBags(Set<Bag> bags) {
		this.bags = bags;
	}
	public Map<String, Terminal> getDepartureMap() {
		return departureMap;
	}
	public void setDepartureMap(Map<String, Terminal> departureMap) {
		this.departureMap = departureMap;
	}
	public Set<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}
	
	
}
