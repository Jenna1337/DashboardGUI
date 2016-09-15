package tools.data;

import tools.locations.Coords;
import tools.locations.CountryCodes;

public final class AirportInfo
{
	private final String airportCode, airportName, cityName;
	private final CountryCodes countryCode;
	private final Coords coords;
	private final String[] args;
	public AirportInfo(String[] aa)
	{
		/*"airportCode","airportName","cityName","countryName","countryCode","latitude","longitude","geometry"*/
		airportCode = aa[0];
		airportName = aa[1];
		cityName    = aa[2];
		countryCode = CountryCodes.valueOf(aa[4]);
		coords      = new Coords(aa[5]+","+aa[6]);
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
