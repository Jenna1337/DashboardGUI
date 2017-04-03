package tools.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import io.BufferedFileReader;
import tools.locations.Coords;

/**
 * 
 * @author jonah.sloan
 * @author jenna3715
 *
 */
public class AirportData
{
	private static final List<String> airportnames = getAirportNames();
	private static final AirportInfoSet thedata = getTheData();
	private static final AirportInfo[] airports = thedata
			.toArray(new AirportInfo[thedata.size()]);
	
	private AirportData()
	{
	}
	private static AirportInfoSet getTheData()
	{
		AirportInfoSet infodata = new AirportInfoSet();
		try
		{
			BufferedFileReader reader = new BufferedFileReader(
					"src/tools/locations/airports/all.tsv");
			String line = reader.readLine();
			while((line = reader.readLine()) != null)
			{
				infodata.add(line.split("\t"));
			}
			reader.close();
		}
		catch(IOException e)
		{
			throw new InternalError(e);
		}
		return infodata;
	}
	private static List<String> getAirportNames()
	{
		try
		{
			ArrayList<String> names = new ArrayList<String>(10000);
			BufferedFileReader reader = new BufferedFileReader(
					"src/tools/locations/stations/StationNames.txt");
			String buffer;
			while((buffer = reader.readLine()) != null)
				names.add(buffer);
			reader.close();
			names.trimToSize();
			return names;
		}
		catch(Exception e)
		{
			throw new InternalError(e);
		}
	}
	public static AirportInfo getClosestAirport(Coords nearHere)
	{
		if(nearHere == null)
			return null;
		AirportInfo min = null;
		double smallest = Double.POSITIVE_INFINITY;
		for(AirportInfo airport : airports)
		{
			int comparevalue = nearHere.compareTo(airport.getCoords());
			if(comparevalue < smallest
					&& airportnames.contains(airport.getAirportCode()))
			{
				min = airport;
				smallest = comparevalue;
			}
		}
		return min;
	}
	private static final AirportInfo closestairport = getClosestAirport(WhereAmI.getLocalCoords());
	public static AirportInfo getLocalAirport()
	{
		return closestairport;
	}
}

class AirportInfoSet extends HashSet<tools.data.AirportInfo>
{
	public void add(String[] aa)
	{
		this.add(new AirportInfo(aa));
	}
	public AirportInfo get(Object key)
	{
		Iterator<AirportInfo> it = this.iterator();
		while(it.hasNext())
		{
			AirportInfo val = it.next();
			if(key.equals(val))
				return val;
			String[] tval = val.getArgs();
			if(key.equals(tval))
				return val;
			for(int i = 0; i < tval.length; ++i)
				if(tval[i].equals(key))
					return val;
		}
		return null;
	}
}
