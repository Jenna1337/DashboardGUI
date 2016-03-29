package dashboard.display.weather;

import java.io.IOException;

@SuppressWarnings("serial")
public final class WeatherInfo extends java.util.Properties
{
	/*Example wdata string:
HECTOR INTERNATIONAL AIRPORT , ND, United States (KFAR) 46-56N 096-49W 277M
Mar 23, 2016 - 10:53 PM EDT / 2016.03.24 0253 UTC
Wind: from the N (360 degrees) at 8 MPH (7 KT):0
Visibility: 10 mile(s):0
Sky conditions: clear
Temperature: 28.9 F (-1.7 C)
Windchill: 21 F (-6 C):1
Dew Point: 10.0 F (-12.2 C)
Relative Humidity: 44%
Pressure (altimeter): 30.1 in. Hg (1019 hPa)
Pressure tendency: 0.03 inches (1.0 hPa) higher than three hours ago
ob: KFAR 240253Z 36007KT 10SM CLR M02/M12 A3010 RMK AO2 SLP207 T10171122 50010
cycle: 3
	 */
	public WeatherInfo(WeatherData wdata)
	{
		super();
		String data=wdata.toString();
		if(data.isEmpty())
			return;
		final int lnbr = data.indexOf("\n");
		data = "Timestamp:"+data.substring(lnbr, data.indexOf("ob:"));
		data = data.replace("Sky conditions", "Sky\\ conditions");
		data = data.replace("Dew Point", "Dew\\ Point");
		data = data.replace("Relative Humidity", "Humidity");
		data = data.replace("Pressure (altimeter)", "Air pressure");
		data = "Station\\ Name:"+wdata.toString().substring(0, lnbr)+data;
		try
		{
			this.load(new java.io.StringReader(data));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			this.clear();
		}
	}
	public String getProperty(WeatherProperty p)
	{
		return super.getProperty(p.toString());
	}
	public String getProperty(String str)
	{
		return this.getProperty(WeatherProperty.valueOf(str));
	}
	enum WeatherProperty
	{
		Station_Name("Station Name"),
		Timestamp("Timestamp"),
		Wind("Wind"),
		Visibility("Visibility"),
		Sky_conditions("Sky conditions"),
		Temperature("Temperature"),
		Windchill("Windchill"),
		Dew_Point("Dew Point"),
		Humidity("Humidity"),
		Air_pressure("Air pressure");
		private final String str;
		WeatherProperty(String s)
		{
			this.str = s;
		}
		public String toString()
		{
			return str;
		}
	}
}
