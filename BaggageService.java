package com.baggagesystem.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.baggagesystem.command.PathFinder;
import com.baggagesystem.dto.BaggageSystemInputs;
import com.baggagesystem.dto.OptimizedRoute;
import com.baggagesystem.entities.Bag;
import com.baggagesystem.entities.Route;
import com.baggagesystem.entities.Terminal;
import com.baggagesystem.exceptions.TerminalNotFoundException;

public class BaggageService 
{
	BaggageSystemInputs baggageInputs;
	private final String ARRIVAL="ARRIVAL";
	private final String BAGGAGE_CLAIM ="BaggageClaim";
	
	public BaggageService(BaggageSystemInputs baggageInputs) 
	{
		this.baggageInputs = baggageInputs;
	}
		
	public Terminal getTerminalForFlight(String flight_id)
	{
		Terminal terminal = null;
		if(flight_id!=null)
		{
			if(flight_id.equalsIgnoreCase(ARRIVAL))
				terminal = new Terminal(BAGGAGE_CLAIM);
			else
				terminal = baggageInputs.getDepartureMap().get(flight_id);
		}
		return terminal;
	}
	
	public List<OptimizedRoute> processBags()
	{
		Terminal source =null,destination=null;
		Set<Bag> bags = Collections.unmodifiableSet(baggageInputs.getBags());
		List<OptimizedRoute> routeList = new LinkedList<OptimizedRoute>();
		
		Iterator<Bag> it = bags.iterator();
		while(it.hasNext())
		{
			Bag bag = it.next();
			try
			{
				source = new Terminal(bag.getEntry_point());
				destination = getTerminalForFlight(bag.getFlight_id());
				
				if(destination == null)
					throw new TerminalNotFoundException("No destination Terminal found corresponding to "+bag.getFlight_id());
				if(! baggageInputs.getTerminals().contains(source))
					throw new TerminalNotFoundException("No conveyor is available from this entry point : "+source.getName());
				if(! baggageInputs.getTerminals().contains(destination))
					throw new TerminalNotFoundException("No conveyor is available to this destination point : "+destination.getName());
								
				routeList.add(getOptimalPath(bag.getBag_number(), source,destination,Collections.unmodifiableSet(baggageInputs.getTerminals()),Collections.unmodifiableSet(baggageInputs.getRoutes())));
						
			
			}catch(TerminalNotFoundException te)
			{
				System.out.println(te.getMessage());
			}
		}
		return routeList;
	}
	
	public OptimizedRoute getOptimalPath(String bagId , Terminal source , Terminal destination ,Set<Terminal> terminals , Set<Route> routes)
	{
		List<Terminal> optimalRoute;
		int duration;
		
		PathFinder pathFinder = new PathFinder();
		pathFinder.execute(source, terminals, routes);
		optimalRoute = pathFinder.getPath(destination);
		duration = pathFinder.getOptimalTime(destination);

		return new OptimizedRoute(bagId,getPathAsString(optimalRoute),duration);
	}
	
	public String getPathAsString(List<Terminal> optimalRoute)
	{
		StringBuilder builder = new StringBuilder();
		for(Terminal terminal :optimalRoute)
		{
			builder.append(terminal.getName()+" ");
		}
		return builder.toString().trim();
	}
}
