package tools.data;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import tools.bufferedFileIO.BufferedFileReader;
import tools.locations.Coords;

public class AirportData
{
	private static final AirportInfoSet thedata = new AirportInfoSet();
	@SuppressWarnings("unused")
	private static final AirportData THIS = new AirportData();
	private AirportData()
	{
		try
		{
			BufferedFileReader reader = new BufferedFileReader("src/tools/locations/airports/all.tsv");
			String line=reader.readLine();
			while((line=reader.readLine())!=null)
			{
				thedata.add(line.split("\t"));
			}
			reader.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	public static AirportInfo getClosestAirport(Coords nearHere)
	{
		if(nearHere==null)
			return null;
		AirportInfo min = null;
		double mindist = Double.POSITIVE_INFINITY, temp;
		for(AirportInfo airport : thedata)
			if((temp=greatCircleDistance(airport.getCoords(), nearHere))<mindist /*&& 
					new File(CommonConsts.weatherdir+airport.getAirportCode()).exists()*/)
			{
				min=airport;
				mindist=temp;
			}
		return min;
	}
	private static double greatCircleDistance(Coords c1, Coords c2)
	{
		double lat1 = Math.toRadians(c1.getLatitude()), lon1 = Math.toRadians(c1.getLongitude()),
				lat2 = Math.toRadians(c2.getLatitude()), lon2 = Math.toRadians(c2.getLongitude());
		double centralAngle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(Math.abs(lon1 - lon2)));
		//double radiusOfEarth = ;// not needed for distance comparisons
		return centralAngle;
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
			else
				for(int i=0;i<tval.length;++i)
					if(tval[i].equals(key))
						return val;
		}
		return null;
	}
}
