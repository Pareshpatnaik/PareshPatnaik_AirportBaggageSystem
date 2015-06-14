package com.baggagesystem.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baggagesystem.entities.Route;
import com.baggagesystem.entities.Terminal;

public class PathFinder
{
	private Set<Terminal> knownTerminalsSet ,unknownTerminalsSet;
	private Map<Terminal,Integer> timeToTerminalMap;
	private Map<Terminal, Terminal> predecessors;
	private Set<Route> routes;
	
	
	public void execute(Terminal source ,Set<Terminal> terminals,Set<Route> routes)
	{
		knownTerminalsSet = new HashSet<Terminal>();
		unknownTerminalsSet =  new HashSet<Terminal>();
		timeToTerminalMap = new HashMap<Terminal,Integer>();
		predecessors = new HashMap<Terminal, Terminal>();
		
		this.routes=routes;
		for(Terminal node : terminals)
		{
			if(node.equals(source))
				timeToTerminalMap.put(node, 0);
			else
				timeToTerminalMap.put(node, Integer.MAX_VALUE);
		}
		
		unknownTerminalsSet.add(source);
		while(unknownTerminalsSet.size() > 0)
		{
			Terminal terminal = getTerminalWithShortestDuration(unknownTerminalsSet);
			knownTerminalsSet.add(terminal);
			unknownTerminalsSet.remove(terminal);
			computeOptimalTime(terminal);	
		}	
	}
	
		
	public Terminal getTerminalWithShortestDuration(Set terminalSet)
	{
		Terminal closestTerminal = null;
		
		Iterator<Terminal> it = terminalSet.iterator();
		while(it.hasNext())
		{
			Terminal term = it.next();
			if(closestTerminal==null)
				closestTerminal=term;
		
			if(timeToTerminalMap.get(term) < timeToTerminalMap.get(closestTerminal))
				closestTerminal=term;		
		}
		
		return closestTerminal;
	}
	
	public boolean isKnown(Terminal terminal)
	{
		return knownTerminalsSet.contains(terminal);
	}
	
	
	public void computeOptimalTime(Terminal terminal )
	{
		List<Terminal> adjTerminals;
		int time;
		adjTerminals = getAdjacentTerminals(terminal);
			
		for(int index=0 ;index<adjTerminals.size() ;index++)
		{
			Terminal terminal1 = adjTerminals.get(index);
			time = timeToTerminalMap.get(terminal)+getTime(terminal1, terminal);
	
			if(timeToTerminalMap.get(terminal1) > time)
			{
				timeToTerminalMap.put(terminal1, time);
				predecessors.put(terminal1, terminal);
				unknownTerminalsSet.add(terminal1);
			}				
		}		
	}
	
	public int getTime(Terminal terminal1 ,Terminal terminal2)
	{
		Iterator<Route> it = routes.iterator();
		while(it.hasNext())
		{
			Route route = it.next();
			if((route.getTerminal1().equals(terminal1) && route.getTerminal2().equals(terminal2)) || (route.getTerminal1().equals(terminal2) && route.getTerminal2().equals(terminal1)))
					return route.getDuration();			
		}
		throw new RuntimeException("Application error ");
	}


	public List<Terminal> getAdjacentTerminals(Terminal terminal)
	{
		List<Terminal> adjacentTerminals = new ArrayList<Terminal>();
		Iterator<Route> it = routes.iterator();
		
		while(it.hasNext())
		{
			Route route = it.next();
			if(route.getTerminal1().equals(terminal) && !isKnown(route.getTerminal2()))
			{
				adjacentTerminals.add(route.getTerminal2());			
			}
			else if(route.getTerminal2().equals(terminal) && !isKnown(route.getTerminal1()))
			{
				adjacentTerminals.add(route.getTerminal1());			
			}
		}
		return adjacentTerminals;		
	}
	
	public LinkedList<Terminal> getPath(Terminal target) 
	{
	    LinkedList<Terminal> optimalRoute = new LinkedList<Terminal>();
	    Terminal terminal = target;
	   
	    if (predecessors.get(terminal) == null)
	    	return null;
	    
	    optimalRoute.add(terminal);
	    
	    while (predecessors.get(terminal) != null)
	    {
	      terminal = predecessors.get(terminal);
	      optimalRoute.add(terminal);
	    }
	    
	    
	    Collections.reverse(optimalRoute);
	    return optimalRoute;
	  }


	public int getOptimalTime(Terminal destination)
	{
		return timeToTerminalMap.get(destination);
	}

}
