package com.baggagesystem.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.baggagesystem.dto.BaggageSystemInputs;
import com.baggagesystem.entities.Bag;
import com.baggagesystem.entities.Route;
import com.baggagesystem.entities.Terminal;

public class InputParser
{

	private Set<Terminal> terminals = new HashSet<Terminal>();
	private Map<String,Terminal> departureMap = new HashMap<String,Terminal>();
	private Set<Bag> bags = new LinkedHashSet<Bag>();
	private Set<Route> routes = new HashSet<Route>();
	private final String done="DONE";

	public BaggageSystemInputs parseInputs()
	{

		System.out.println("");	
		Scanner in = null;
		String input=null;
		boolean isConveyorInput=false,isDepartureInput=false,isBagInput=false ,isDone=false;
		try
		{
			in = new Scanner(System.in);

			while(true)
			{			
				System.out.print("Do you want to enter Inputs (Y/N) : ");
				if(in.nextLine().equalsIgnoreCase("Y"))
				{	
					System.out.println("");
					System.out.println("1. Please provide inputs corresponding to the 3 sections - Conveyor System ,Departures and Bags ");
					System.out.println("2. Ensure that inputs sections are provided in the same sequence as above");
					System.out.println("3. Each new input section should start with ' # Section: <Section-Name>' heading");
					System.out.println("4. Once you have provided all the inputs ,input 'DONE' ");
					System.out.println("");

					while(in.hasNextLine())
					{
						input = in.nextLine();
						if(input.startsWith("# Section:"))
						{
							isConveyorInput=false;
							isDepartureInput=false;
							isBagInput=false;

							if(input.contains("Conveyor System"))
							{	
								isConveyorInput = true;
							}else if(input.contains("Departures"))
							{	
								isDepartureInput = true;
							}else if(input.contains("Bags"))
							{
								isBagInput = true;
							}
						}
						else
						{	
							if(isConveyorInput)
							{
								processConveyorInputs(input);
							}
							else if(isDepartureInput)
							{
								processDeparturesInputs(input);
							}
							else if(isBagInput)
							{	
								if(input.equalsIgnoreCase(done))
								{	
									isDone=true;
									isBagInput=false;
									break;
								}
								processBagInputs(input);
							}else
							{
								System.out.println("Please enter valid inputs in sections");
							}
						}
					}
					if(isDone)
						break;
				}

			}	
		}finally
		{
			if(in != null)
				in.close();
		}
		BaggageSystemInputs baggageInputs = new BaggageSystemInputs();
		baggageInputs.setTerminals(terminals);
		baggageInputs.setBags(bags);
		baggageInputs.setRoutes(routes);
		baggageInputs.setDepartureMap(departureMap);

		return baggageInputs;
	}

	public void processConveyorInputs(String input)
	{
		String conveyorInput=null;
		String[] conveyorInputs;
		Terminal t1,t2;

		conveyorInput = input;
		conveyorInputs = conveyorInput.split("\\s");
		if(conveyorInputs.length != 3)
		{	System.out.println("Please re-enter input in proper format : <Node 1> <Node 2> <travel_time>");
		return;
		}
		try
		{
			Float.parseFloat(conveyorInputs[2]);
		}catch(NumberFormatException nfe)
		{
			System.out.println("Please re-enter input with proper travel_time");
			return;
		}
		t1 = new Terminal(conveyorInputs[0]);
		t2 = new Terminal(conveyorInputs[1]);
		getTerminals().add(t1);
		getTerminals().add(t2);
		Route route = new Route(t1 ,t2,Integer.parseInt(conveyorInputs[2]));	
		getRoutes().add(route);

	}

	public void processDeparturesInputs(String input)
	{
		String departureInput=null ,flight_id=null ,flight_gate=null;
		String[] departureInputs;
		Terminal terminal = null;

		departureInput=input;
		departureInputs = departureInput.split("\\s");			
		if(departureInputs.length != 4)
		{	System.out.println("Please re-enter input in proper format : <flight_id> <flight_gate> <destination> <flight_time>");
		return;
		}

		flight_id = departureInputs[0];
		flight_gate = departureInputs[1];
		terminal = new Terminal(flight_gate);

		getDepartureMap().put(flight_id, terminal);
	}

	public void processBagInputs(String input)
	{
		String bagInput=null;
		String[] bagInputs;
		Bag bag;

		bagInput=input;
		bagInputs = bagInput.split("\\s");
		if(bagInputs.length != 3)
		{	
			System.out.println("Please re-enter input in proper format : <bag_number> <entry_point> <flight_id>");
			return;
		}
		bag = new Bag(bagInputs[0], bagInputs[1], bagInputs[2]);
		getBags().add(bag);
	}

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
