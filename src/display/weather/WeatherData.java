package display.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import display.clock.TimeConstant;

public class WeatherData
{
	private final static String server = "ftp://tgftp.nws.noaa.gov/data/observations/metar/decoded/";
	volatile String data="";
	private final String ws;
	public WeatherData(String station)
	{
		ws=station;
		Thread wupdater = new Thread(
			new Runnable(){
				public void run(){
					try{
						String check = getWeatherData(ws);
						if(check!=null)
						{
							data=check;
							Thread.sleep(TimeConstant.HOUR);
						}
						else
							Thread.sleep(TimeConstant.MINUTE*5);
					}
					catch(InterruptedException ie){}
					this.run();
				}
			});
		wupdater.setDaemon(true);
		wupdater.start();
	}
	protected static String getWeatherData(final String station)
	{
		String locdata="";
		try {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					new java.net.URL(server + station + ".TXT").openConnection().getInputStream()));
			
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				locdata+=inputLine+System.lineSeparator();
			in.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("Invalid weather station \""+station+"\"");
			System.exit(0);
		}catch (Exception e) {
			return null;
		}
		return locdata;
	}
	public String toString()
	{
		return data;
	}
	public static void main(String[] args) throws Exception
	{
		WeatherData kfar = new WeatherData("KFAR");
		Thread.sleep(1000);
		System.out.println(kfar);
	}
}
