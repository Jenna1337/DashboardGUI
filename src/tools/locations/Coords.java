package tools.locations;

public class Coords implements Comparable<Coords>
{
	private double lat, lon;
	
	public Coords(String latlon)
	{
		this(Double.parseDouble(latlon.split(",")[0].trim()),
				Double.parseDouble(latlon.split(",")[1].trim()));
	}
	public Coords(String lat, String lon)
	{
		this(Double.parseDouble(lat.trim()),
				Double.parseDouble(lon.trim()));
	}
	public Coords(double lat, double lon)
	{
		if(lat > 90.0 || lat < -90.0 || lon > 180.0 || lon < -180.0)
			throw new IllegalArgumentException(lat + ", " + lon);
		this.lat = lat;
		this.lon = lon;
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
		return lat + "," + lon;
	}
	public double distanceTo(Coords other)
	{
		double lat1 = Math.toRadians(lat), lat2 = Math.toRadians(other.lat),
				lon1 = Math.toRadians(lon), lon2 = Math.toRadians(other.lon);
		double sinlat1 = Math.sin(lat1), sinlat2 = Math.sin(lat2),
				coslat1 = Math.cos(lat1), coslat2 = Math.cos(lat2),
				deltalon = Math.abs(lon1 - lon2);
		double sindeltalon = Math.sin(deltalon),
				cosdeltalon = Math.cos(deltalon);
		return Math.abs(Math.atan(Math
				.sqrt(Math.pow(coslat2 * sindeltalon, 2)
						+ Math.pow(coslat1 * sinlat2 - sinlat1 * coslat2, 2))
				/ (sinlat1 * sinlat2 + coslat1 * coslat2 * cosdeltalon)));
	}
	/**
	 * 
	 * @param d a {@code double} with a value int [-1, 1)
	 * @return {@code d} as a double;
	 */
	private static int doubleToInt(double d)
	{
		if(d==Double.POSITIVE_INFINITY)
			return Integer.MAX_VALUE;
		if(d==Double.NEGATIVE_INFINITY)
			return Integer.MIN_VALUE;
		return (int)(d*Integer.MAX_VALUE);
	}
	@Override
	public int compareTo(Coords o)
	{
		return doubleToInt(2*this.distanceTo(o)/Math.PI);
	}
}
