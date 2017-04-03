package tools.data;

import java.io.IOException;
import io.BufferedNetFileReader;
import tools.locations.Coords;

/**
 * Figures out approximately where the user is in the world.
 * @author jonah.sloan
 * @author jenna3715
 *
 */
public class WhereAmI
{
	private WhereAmI(){}
	private static Coords mycoords = getLocalCoords1();
	/**
	 * Get the coords from the Internet and store them in memory.
	 * @return The approximate location of the computer.
	 */
	private static Coords getLocalCoords1()
	{
		try
		{
			BufferedNetFileReader nfr = new BufferedNetFileReader("http://www.bing.com/search?q=where+am+i");
			String html = nfr.readAllLines();
			nfr.close();
			String findurl = "http://platform.bing.com/geo/REST/v1/Imagery/Map/Road/";
			int indx = html.indexOf(findurl)+findurl.length();
			String cds = html.substring(indx, html.indexOf("/", indx));
			//System.out.println(cds);
			return new Coords(cds);
		}
		catch(IOException e)
		{
			return null;
		}
	}
	/**
	 * @return The approximate location of the computer.
	 */
	public static Coords getLocalCoords()
	{
		return mycoords;
	}
}
