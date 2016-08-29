package tools.locations;

enum CardinalDirection
{
	N,S,E,W;
}
class DegreesUnit
{
	final double h,m,s;
	CardinalDirection cd;
	public DegreesUnit(String hms)
	{
		double temp;
		try{
			temp=Double.parseDouble(hms.split("-")[0]);
		}catch(NumberFormatException nfe){
			temp=0;
		}
		this.h=temp;
		try{
			temp=Double.parseDouble(hms.split("-")[1]);
		}catch(NumberFormatException nfe){
			temp=0;
		}
		this.m=temp;
		try{
			temp=Double.parseDouble(hms.split("-")[2]);
		}catch(NumberFormatException nfe){
			temp=0;
		}
		this.s=temp;
		this.cd=CardinalDirection.valueOf(hms.substring(hms.lastIndexOf("-")+1));
	}
	public String toString()
	{
		return String.format("%1$02.0f-%2$02.0f-%3$02.0f%4$s", h,m,s,cd);
	}
}
class Latitude extends DegreesUnit
{
	public Latitude(String hms)
	{
		super(hms);
	}
}
class Longitude extends DegreesUnit
{
	public Longitude(String hms)
	{
		super(hms);
	}
}
public class Coords {
	private Latitude lat;
	private Longitude lon;
	public Coords(String latlon)
	{
		this(new Latitude(latlon.split(", ")[0]), new Longitude(latlon.split(", ")[1]));
	}
	public Coords(Latitude lat, Longitude lon)
	{
		this.lat=lat;
		this.lon=lon;
	}
	public Latitude getLatitude()
	{
		return lat;
	}
	public Longitude getLongitude()
	{
		return lon;
	}
	public String toString()
	{
		return lat.toString()+" "+lon.toString();
	}
}
