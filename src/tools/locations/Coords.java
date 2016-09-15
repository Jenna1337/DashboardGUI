package tools.locations;

public class Coords {
	private double lat, lon;
	public Coords(String latlon)
	{
		this(Double.parseDouble(latlon.split(",")[0].trim()), Double.parseDouble(latlon.split(",")[1].trim()));
	}
	public Coords(double lat, double lon)
	{
		this.lat=lat;
		this.lon=lon;
	}
	public double getLatitude()
	{
		return lat;
	}
	public double getLongitude()
	{
		return lon;
	}
	public String toString()
	{
		return lat+","+lon;
	}
}
