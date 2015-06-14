package com.baggagesystem;

import java.util.List;
import com.baggagesystem.dto.BaggageSystemInputs;
import com.baggagesystem.dto.OptimizedRoute;
import com.baggagesystem.parser.InputParser;
import com.baggagesystem.service.BaggageService;

public class BaggageSystem 
{
	
	public static void main(String args[])
	{
		InputParser parser = new InputParser();
		BaggageSystemInputs baggageInputs = parser.parseInputs();
		
		BaggageService service = new BaggageService(baggageInputs);

		List<OptimizedRoute> routeList = service.processBags();
	
		displayOptimalPath(routeList);
	}

		public static void displayOptimalPath(List<OptimizedRoute> routeList)
		{
			System.out.println("\nOutput");

			for (OptimizedRoute optimizedRoute : routeList) 
			{
				System.out.println(optimizedRoute.getBagId()+" "+optimizedRoute.getRoute()+" : "+optimizedRoute.getDuration());
			}
		}

}
