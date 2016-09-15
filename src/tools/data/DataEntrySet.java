package tools.data;

import java.util.HashSet;
import java.util.Iterator;

public class DataEntrySet extends HashSet<tools.data.DataEntrySet.DataEntry>
{
	class DataEntry
	{
		//String airportCode, airportName, cityName, countryName, countryCode, latitude, longitude, geometry;
		private String[] args;
		public DataEntry(String[] aa)
		{
			args=aa;
		}
		public String[] getArgs()
		{
			return args;
		}
	}
	public void add(String[] aa)
	{
		this.add(new DataEntry(aa));
	}
	public DataEntry get(Object key)
	{
		Iterator<DataEntry> it = this.iterator();
		while(it.hasNext())
		{
			DataEntry val = it.next();
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
		// TODO Auto-generated method stub
		return null;
	}
}
