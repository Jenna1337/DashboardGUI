package tools.locations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import tools.bufferedFileIO.BufferedFileWriter;
import tools.bufferedFileIO.BufferedNetFileReader;

/**
 * Retrieves the ICAO code, airport name, city name, country name, country code, latitude, and longitude
 * @author jonah.sloan
 * @version 1.0.0
 * @since DashboardGui v28 
 */
public class IntAirLoc
{
	//TODO use Aerodrome Location Indicators as published in ICAO DOC7910
	private static final String apiurl="https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/airports/locations/international-list?api_key=%APIKEY%&format=csv";
	private static final String apikey="8f08e9a0-6dab-11e6-8705-791b10ba6b27";
	private static final String outdir="src/tools/locations/airports/";
	private static final String outnme="AirportData.csv";
	public static final String outfloc=outdir+outnme;
	private IntAirLoc(){}
	/**
	 * <br><br>
	 * Note: the first line of the resulting file should contain the format for the entries; example:<br>
	 * "airportCode","airportName","cityName","countryName","countryCode","latitude","longitude","geometry"
	 * <br><br>
	 * Note: this service is currently in beta (8/29/2016)<br>
	 * See <a href="http://www.icao.int/safety/iStars/Pages/API-Data-Service.aspx">http://www.icao.int/safety/iStars/Pages/API-Data-Service.aspx</a>
	 * for details.<br>
	 * <b>Note: The API only allows up to 200 accesses per month.</b><br>
	 * <br><pre>
	 * airportCode, string, ICAO 4-letter code of the airport 
	 * airportName, string, Name of the airport, searchable 
	 * cityName,    string, Name of the city, searchable 
	 * countryName, string, Name of the Country 
	 * countryCode, string, ISO 3-Letter Code of the Country <!--What would one use this for?-->
	 * latitude,    number, Latitude of the airport in decimal format 
	 * longitude,   number, Longitude of the airport in decimal format 
	 * geometry,    string, GEOJSON geometry Point object <!--Not really important in Java-->
	 * </pre>
	 */
	public static void copyAirportData()
	{
		try {
			File locfile = new File(outfloc);
			if(!locfile.exists())
				if(!locfile.createNewFile())
					throw new InternalError();
			BufferedFileWriter writer = new BufferedFileWriter(outfloc);
			InputStream urlis = new URL(apiurl.replace("%APIKEY%", apikey)).openStream();
			BufferedNetFileReader reader = new BufferedNetFileReader(urlis);
			writer.write(reader.readAllLines());
			reader.close();
			writer.close();
		} catch (IOException e) {
			throw new Error(e);
		}
	}
	public static void main(String args[])
	{
		System.out.println("Copying airport location file...");
		//copyAirportData();
		System.out.println("Done.");
	}
}
