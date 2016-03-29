package dashboard.display.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import dashboard.CommonConsts;
import dashboard.display.Destroyable;

public class WeatherData implements Destroyable
{
	private final static String server = "ftp://tgftp.nws.noaa.gov/data/observations/metar/decoded/";
	volatile String data="";
	private final String ws;
	private long lastsync=0;
	private Thread wupdater;
	public WeatherData(String station)
	{
		ws=station;
		
		wupdater = new Thread(
			new Runnable(){
				public void run(){
					while(true)
					{
						try
						{
							String check = getWeatherData(ws);
							if(check!=null)
							{
								data=check;
								Thread.sleep(CommonConsts.wsyncsucceeded);
							}
							else
								Thread.sleep(CommonConsts.wsyncfailed);
						}
						catch(InterruptedException ie)
						{
							Thread.currentThread().interrupt();
							return;
						}
					}
				}
			});
		wupdater.setDaemon(true);
		String check = getWeatherData(ws);
		if(check!=null)
			data=check;
		wupdater.start();
	}
	private String getWeatherData(final String station)
	{
		String locdata=null;
		//hard coded minimum 5 minutes
		if(lastsync+CommonConsts.MINUTE*5>System.currentTimeMillis())
			return data;
		try {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					new java.net.URL(server + station + ".TXT").openConnection().getInputStream()));
			lastsync=System.currentTimeMillis();
			locdata="";
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				locdata+=inputLine+System.lineSeparator();
			in.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("Invalid weather station \""+station+"\"");
			wupdater.interrupt();
			data="No station "+station;
			return null;
		}catch (Exception e) {
			return null;
		}
		return locdata;
	}
	public String toString()
	{
		return data;
	}
	@Override
	public void destroy()
	{
		wupdater.interrupt();
	}
}
