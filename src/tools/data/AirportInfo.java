package tools.data;

import tools.locations.Coords;
import tools.locations.CountryCodes;

/**
 * 
 * @author jonah.sloan
 * @author jenna3715
 *
 */
public final class AirportInfo
{
	/**
	 * The ICAO Identifier code.
	 */
	private final String airportCode;
	/**
	 * The name of the airport.
	 */
	private final String airportName;
	/**
	 * The name of the city the airport is located in.
	 */
	private final String cityName;
	/**
	 * The 3 letter ISO country code. 
	 */
	private final CountryCodes countryCode;
	/**
	 * The geographic coordinates of the weather station. 
	 */
	private final Coords coords;
	private final String[] args;
	public AirportInfo(String[] aa)
	{
		/*"airportCode","airportName","cityName","countryName","countryCode","latitude","longitude","geometry"*/
		airportCode = aa[0];
		airportName = aa[1];
		cityName    = aa[2];
		countryCode = CountryCodes.valueOf(aa[4]);
		coords      = new Coords(aa[5], aa[6]);
		args=aa;
	}
	@Override
	public String toString()
	{
		return this.getClass().getName()+java.util.Arrays.toString(args);
	}
	public String getAirportCode()
	{
		return airportCode;
	}
	public String getAirportName()
	{
		return airportName;
	}
	public String getCityName()
	{
		return cityName;
	}
	public CountryCodes getCountryCode()
	{
		return countryCode;
	}
	public Coords getCoords()
	{
		return coords;
	}
	public String[] getArgs()
	{
		return args;
	}
}
