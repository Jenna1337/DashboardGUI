package tools.data;

import java.io.IOException;
import tools.bufferedFileIO.BufferedNetFileReader;
import tools.locations.Coords;

public class WhereAmI
{
	private WhereAmI(){}
	private static Coords mycoords = getLocalCoords1();
	private static Coords getLocalCoords1()//get the coords from Bing.com and store them in memory
	{
		try
		{
			BufferedNetFileReader nfr = new BufferedNetFileReader("http://www.bing.com/search?q=where+am+i");
			String html = nfr.readAllLines();
			nfr.close();
			String findurl = "http://platform.bing.com/geo/REST/v1/Imagery/Map/Road/";
			int indx = html.indexOf(findurl)+findurl.length();
			String cds = html.substring(indx, html.indexOf("/", indx));
			System.out.println(cds);
			return new Coords(cds);
		}
		catch(IOException e)
		{
			return null;
		}
	}
	public static Coords getLocalCoords()
	{
		return mycoords;
	}
}
