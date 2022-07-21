package com.nasa.client.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.nasa.client.app.models.AsteroidClose;
import com.nasa.client.app.models.AsteroidLarge;
import com.nasa.client.app.models.documents.DayNeos;
import com.nasa.client.app.models.json.CAD;
import com.nasa.client.app.models.json.EstimatedDiameter;
import com.nasa.client.app.models.json.Kilometers;
import com.nasa.client.app.models.json.MissDistance;
import com.nasa.client.app.models.json.NEO;
import com.nasa.client.app.models.json.NeoFeedResult;

public class Fixtures {

	//to test 1 largest by year
	private static Kilometers km1 = new Kilometers(2.5);
	private static Kilometers km2 = new Kilometers(2.6);
	private static Kilometers km3 = new Kilometers(2.7);
	private static Kilometers km4 = new Kilometers(2.8);
	//to test 10 closest by period
	private static MissDistance md1 = new MissDistance(1.1);	
	private static MissDistance md2 = new MissDistance(1.2);
	private static MissDistance md3 = new MissDistance(1.3);	
	private static MissDistance md4 = new MissDistance(1.4);
	private static MissDistance md5 = new MissDistance(2.1);	
	private static MissDistance md6 = new MissDistance(2.2);
	private static MissDistance md7 = new MissDistance(2.3);	
	private static MissDistance md8 = new MissDistance(2.4);
	private static MissDistance md9 = new MissDistance(2.5);	
	private static MissDistance md10 = new MissDistance(2.6);
	private static MissDistance md11 = new MissDistance(2.7);
	private static MissDistance md12 = new MissDistance(2.8);
	private static MissDistance md13 = new MissDistance(3.2);
	private static MissDistance md14 = new MissDistance(4.5);
	
	//by year
	private static EstimatedDiameter ed1 = new EstimatedDiameter(km1);
	private static EstimatedDiameter ed2 = new EstimatedDiameter(km2);
	private static EstimatedDiameter ed3 = new EstimatedDiameter(km3);
	private static EstimatedDiameter ed4 = new EstimatedDiameter(km4);
	private static NEO neo1 = new NEO("001","2003 UA1",0.1,ed4,null);
	private static NEO neo2 = new NEO("002","2006 AS5",0.1,ed2,null);
	private static NEO neo3 = new NEO("003","2011 GE2",0.1,ed3,null);
	private static NEO neo4 = new NEO("004","2001 UA2",0.1,ed1,null);
	private static AsteroidLarge a1 = new AsteroidLarge(neo1);
	private static AsteroidLarge a2 = new AsteroidLarge(neo2);
	private static AsteroidLarge a3 = new AsteroidLarge(neo3);
	private static AsteroidLarge a4 = new AsteroidLarge(neo4);
	
	//10 closest
	private static CAD cad1 = new CAD(md1);
	private static CAD cad2 = new CAD(md2);
	private static CAD cad3 = new CAD(md3);
	private static CAD cad4 = new CAD(md4);
	private static CAD cad5 = new CAD(md5);
	private static CAD cad6 = new CAD(md6);
	private static CAD cad7 = new CAD(md7);
	private static CAD cad8 = new CAD(md8);
	private static CAD cad9 = new CAD(md9);
	private static CAD cad10 = new CAD(md10);
	private static CAD cad11 = new CAD(md11);
	private static CAD cad12 = new CAD(md12);
	private static CAD cad13 = new CAD(md13);
	private static CAD cad14 = new CAD(md14);
	private static NEO neo01 = new NEO("z001","2003 UA1",0.1,ed1,null);
	private static NEO neo02 = new NEO("z002","2006 AS5",0.1,ed1,null);
	private static NEO neo03 = new NEO("z003","2011 GE2",0.1,ed1,null);
	private static NEO neo04 = new NEO("z004","2001 UA2",0.1,ed1,null);
	private static NEO neo05 = new NEO("z005","2003 UA1",0.1,ed1,null);
	private static NEO neo06 = new NEO("z006","2006 AS5",0.1,ed1,null);
	private static NEO neo07 = new NEO("z007","2011 GE2",0.1,ed1,null);
	private static NEO neo08 = new NEO("z008","2001 UA2",0.1,ed1,null);
	private static NEO neo09 = new NEO("z009","2003 UA1",0.1,ed1,null);
	private static NEO neo010 = new NEO("z010","2006 AS5",0.1,ed1,null);
	private static NEO neo011 = new NEO("z011","2008 FF9",0.1,ed1,null);
	
	public static List<AsteroidLarge> generateByYearListTest(){
		List<AsteroidLarge> list = new ArrayList<AsteroidLarge>();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		return list;
	}
	
	public static AsteroidLarge oneAsteroidForLargestByYearTest(){
		return a1;
	}
	
	public static List<AsteroidClose> generate10ClosestListTest(){
		List<CAD> cads1 = new ArrayList<>();
		cads1.add(cad1);
		cads1.add(cad2);
		cads1.add(cad3);
		List<CAD> cads2 = new ArrayList<>();
		cads2.add(cad4);
		List<CAD> cads3 = new ArrayList<>();
		cads3.add(cad5);
		List<CAD> cads4 = new ArrayList<>();
		cads4.add(cad6);
		List<CAD> cads5 = new ArrayList<>();
		cads5.add(cad7);
		List<CAD> cads6 = new ArrayList<>();
		cads6.add(cad8);
		List<CAD> cads7 = new ArrayList<>();
		cads7.add(cad9);
		List<CAD> cads8 = new ArrayList<>();
		cads8.add(cad10);
		List<CAD> cads9 = new ArrayList<>();
		cads9.add(cad11);
		List<CAD> cads10 = new ArrayList<>();
		cads10.add(cad12);
		cads10.add(cad13);
		List<CAD> cads11 = new ArrayList<>();
		cads11.add(cad14);
		
		neo01.setClose_approach_data(cads1);
		neo02.setClose_approach_data(cads2);
		neo03.setClose_approach_data(cads3);
		neo04.setClose_approach_data(cads4);
		neo05.setClose_approach_data(cads5);
		neo06.setClose_approach_data(cads6);
		neo07.setClose_approach_data(cads7);
		neo08.setClose_approach_data(cads8);
		neo09.setClose_approach_data(cads9);
		neo010.setClose_approach_data(cads10);
		
		AsteroidClose a01 = new AsteroidClose(neo01);
		AsteroidClose a02 = new AsteroidClose(neo02);
		AsteroidClose a03 = new AsteroidClose(neo03);
		AsteroidClose a04 = new AsteroidClose(neo04);
		AsteroidClose a05 = new AsteroidClose(neo05);
		AsteroidClose a06 = new AsteroidClose(neo06);
		AsteroidClose a07 = new AsteroidClose(neo07);
		AsteroidClose a08 = new AsteroidClose(neo08);
		AsteroidClose a09 = new AsteroidClose(neo09);
		AsteroidClose a010 = new AsteroidClose(neo010);
		List<AsteroidClose> list = new ArrayList<AsteroidClose>();
		list.add(a01);list.add(a02);list.add(a03);list.add(a04);list.add(a05);
		list.add(a06);list.add(a07);list.add(a08);list.add(a09);list.add(a010);
		return list;
	}	
	
	public static List<AsteroidClose> generate11for10ClosestListTest(){
		List<CAD> cads1 = new ArrayList<>();
		cads1.add(cad1);
		cads1.add(cad2);
		cads1.add(cad3);
		List<CAD> cads2 = new ArrayList<>();
		cads2.add(cad4);
		List<CAD> cads3 = new ArrayList<>();
		cads3.add(cad5);
		List<CAD> cads4 = new ArrayList<>();
		cads4.add(cad6);
		List<CAD> cads5 = new ArrayList<>();
		cads5.add(cad7);
		List<CAD> cads6 = new ArrayList<>();
		cads6.add(cad8);
		List<CAD> cads7 = new ArrayList<>();
		cads7.add(cad9);
		List<CAD> cads8 = new ArrayList<>();
		cads8.add(cad10);
		List<CAD> cads9 = new ArrayList<>();
		cads9.add(cad11);
		List<CAD> cads10 = new ArrayList<>();
		cads10.add(cad12);
		cads10.add(cad13);
		List<CAD> cads11 = new ArrayList<>();
		cads11.add(cad14);
		
		neo01.setClose_approach_data(cads1);
		neo02.setClose_approach_data(cads2);
		neo03.setClose_approach_data(cads3);
		neo04.setClose_approach_data(cads4);
		neo05.setClose_approach_data(cads5);
		neo06.setClose_approach_data(cads6);
		neo07.setClose_approach_data(cads7);
		neo08.setClose_approach_data(cads8);
		neo09.setClose_approach_data(cads9);
		neo010.setClose_approach_data(cads10);
		neo011.setClose_approach_data(cads11);
		
		AsteroidClose a01 = new AsteroidClose(neo01);
		AsteroidClose a02 = new AsteroidClose(neo02);
		AsteroidClose a03 = new AsteroidClose(neo03);
		AsteroidClose a04 = new AsteroidClose(neo04);
		AsteroidClose a05 = new AsteroidClose(neo05);
		AsteroidClose a06 = new AsteroidClose(neo06);
		AsteroidClose a07 = new AsteroidClose(neo07);
		AsteroidClose a08 = new AsteroidClose(neo08);
		AsteroidClose a09 = new AsteroidClose(neo09);
		AsteroidClose a010 = new AsteroidClose(neo010);
		AsteroidClose a011 = new AsteroidClose(neo011);
		List<AsteroidClose> list = new ArrayList<AsteroidClose>();
		list.add(a01);list.add(a02);list.add(a03);list.add(a04);list.add(a05);
		list.add(a06);list.add(a07);list.add(a08);list.add(a09);list.add(a010);
		list.add(a011);
		return list;
	}	
		
	public static NeoFeedResult neoFeed1995_01_02Test() {
		List<CAD> cads1 = new ArrayList<>();
		cads1.add(cad1);
		cads1.add(cad2);
		cads1.add(cad3);
		List<CAD> cads2 = new ArrayList<>();
		cads2.add(cad4);
		List<CAD> cads3 = new ArrayList<>();
		cads3.add(cad5);
		List<CAD> cads4 = new ArrayList<>();
		cads4.add(cad6);
		neo1.setClose_approach_data(cads1);
		neo02.setClose_approach_data(cads2);
		neo03.setClose_approach_data(cads3);
		neo04.setClose_approach_data(cads4);
		List<NEO> neos = new ArrayList<>();
		neos.add(neo1);neos.add(neo02);neos.add(neo03);neos.add(neo04);
		Map<String, List<NEO>> map1 = new HashMap<String, List<NEO>>();
		map1.put("1995-01-02", neos);
		NeoFeedResult nf = new NeoFeedResult(2,map1);
		return nf;
	}

	public static DayNeos dayNeos1995_01_01Test() {
		List<AsteroidClose> asteroids = generate11for10ClosestListTest();
		Gson gson = new Gson();
		String json = gson.toJson(asteroids);
		DayNeos dayNeos = new DayNeos("1995-01-01", json);
		return dayNeos;
	}
}
