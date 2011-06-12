package com.kervinramen.spotfinder.facebookapp.helpers;

import java.util.ArrayList;
import java.util.List;

import com.kervinramen.spotfinder.base.model.Spot;

public class SpotHelper {

	public static List<Spot> getSpots()
	{
		List<Spot> spots = new ArrayList<Spot>();
		Spot spot1 = new Spot("Metropolis", "");
		
		spots.add(spot1);
		
		
		
		return spots;
	}
}
