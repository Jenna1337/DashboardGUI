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
		double smallest=Double.POSITIVE_INFINITY;
		AirportInfo[] airports = thedata.toArray(new AirportInfo[thedata.size()]);
		for(int i=0; i<airports.length; ++i)
		{
			AirportInfo airport = airports[i];
			int comparevalue = nearHere.compareTo(airport.getCoords());
			if(comparevalue<smallest /*&& 
					new File(CommonConsts.weatherdir+airport.getAirportCode()).exists()*/)
			{
				min=airport;
				smallest=comparevalue;
			}
		}
		System.out.println(smallest);
		return min;
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
