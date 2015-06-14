package com.baggagesystem.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.baggagesystem.BaggageSystem;
import com.baggagesystem.dto.BaggageSystemInputs;
import com.baggagesystem.dto.OptimizedRoute;
import com.baggagesystem.entities.Bag;
import com.baggagesystem.entities.Route;
import com.baggagesystem.entities.Terminal;
import com.baggagesystem.service.BaggageService;

public class TestBaggageSystem 
{
	BaggageSystemInputs inputs = new BaggageSystemInputs();
	
	  @Test
	  public void testExcute() 
	  {  
		  BaggageService service = new BaggageService(inputs);
		  List<OptimizedRoute> optRoutes = service.processBags();
		  
		  assertNotNull(optRoutes);
		  
		  assertEquals("0001", optRoutes.get(0).getBagId());
		  assertEquals("0002", optRoutes.get(1).getBagId());
		  assertEquals("0003", optRoutes.get(2).getBagId());
		  assertEquals("0004", optRoutes.get(3).getBagId());
		  assertEquals("0005", optRoutes.get(4).getBagId());
		  
		  assertEquals(11, optRoutes.get(0).getDuration()); 
		  assertEquals(9, optRoutes.get(1).getDuration()); 
		  assertEquals(1, optRoutes.get(2).getDuration()); 
		  assertEquals(6, optRoutes.get(3).getDuration()); 
		  assertEquals(12, optRoutes.get(4).getDuration()); 

		  assertEquals("Concourse_A_Ticketing A5 A1".trim(), optRoutes.get(0).getRoute().trim());
		  assertEquals("A5 A1 A2 A3 A4".trim(), optRoutes.get(1).getRoute().trim());
		  assertEquals("A2 A1".trim(), optRoutes.get(2).getRoute().trim());
		  assertEquals("A8 A9 A10 A5".trim(), optRoutes.get(3).getRoute().trim());
		  assertEquals("A7 A8 A9 A10 A5 BaggageClaim".trim(), optRoutes.get(4).getRoute().trim());

		  
	  }
	  
	  @Before
	  public void populateTestData()
	  {
		  Set<Terminal> terminals = new HashSet<Terminal>();
		  for(int i=1;i<11;i++)
		  {
			  terminals.add(new Terminal("A"+i));
		  }
		  terminals.add(new Terminal("Concourse_A_Ticketing"));
		  terminals.add(new Terminal("BaggageClaim"));
		  
		  Set<Bag> bags = new LinkedHashSet<Bag>();
		  bags.add(new Bag("0001", "Concourse_A_Ticketing", "UA12"));
		  bags.add(new Bag("0002", "A5", "UA17"));
		  bags.add(new Bag("0003", "A2", "UA10"));
		  bags.add(new Bag("0004", "A8", "UA18"));
		  bags.add(new Bag("0005", "A7", "ARRIVAL"));
		  
		  Set<Route> routes = new HashSet<Route>();
		  routes.add(new Route(new Terminal("Concourse_A_Ticketing"),new Terminal("A5"),5));
		  routes.add(new Route(new Terminal("A5"),new Terminal("BaggageClaim"),5));
		  routes.add(new Route(new Terminal("A5"),new Terminal("A10"),4));
		  routes.add(new Route(new Terminal("A5"),new Terminal("A1"),6));
		  routes.add(new Route(new Terminal("A1"),new Terminal("A2"),1));
		  routes.add(new Route(new Terminal("A2"),new Terminal("A3"),1));
		  routes.add(new Route(new Terminal("A3"),new Terminal("A4"),1));
		  routes.add(new Route(new Terminal("A10"),new Terminal("A9"),1));
		  routes.add(new Route(new Terminal("A9"),new Terminal("A8"),1));
		  routes.add(new Route(new Terminal("A8"),new Terminal("A7"),1));
		  routes.add(new Route(new Terminal("A7"),new Terminal("A6"),1));

		  Map<String,Terminal> departureMap = new HashMap<String,Terminal>();
		  departureMap.put("UA10", new Terminal("A1"));
		  departureMap.put("UA11", new Terminal("A1"));
		  departureMap.put("UA12", new Terminal("A1"));
		  departureMap.put("UA13", new Terminal("A2"));
		  departureMap.put("UA14", new Terminal("A2"));
		  departureMap.put("UA15", new Terminal("A2"));
		  departureMap.put("UA16", new Terminal("A3"));
		  departureMap.put("UA17", new Terminal("A4"));
		  departureMap.put("UA18", new Terminal("A5"));
		  
		  inputs.setBags(bags);
		  inputs.setDepartureMap(departureMap);
		  inputs.setRoutes(routes);
		  inputs.setTerminals(terminals);
	  }
	} 
