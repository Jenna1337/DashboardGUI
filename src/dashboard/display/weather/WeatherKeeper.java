package dashboard.display.weather;

import java.util.Timer;
import dashboard.CommonConsts;
import dashboard.display.Destroyable;
import dashboard.display.TimerTaskThread;
import dashboard.display.weather.WeatherInfo.WeatherProperty;

public final class WeatherKeeper implements Destroyable
{
	private WeatherData weather;
	private WeatherInfo info;
	private Timer timer;
	/**
	 * 
	 * @param station > For a list of stations go to 
	 * <a href="ftp://tgftp.nws.noaa.gov/data/observations/metar/decoded/">
	 * ftp://tgftp.nws.noaa.gov/data/observations/metar/decoded/</a>
	 */
	public WeatherKeeper(String station)
	{
		weather = new WeatherData(station);
		info = new WeatherInfo(weather);
		timer=new Timer(true);
		timer.scheduleAtFixedRate(
			new TimerTaskThread(new Runnable(){
				public void run(){
					info = new WeatherInfo(weather);
				}
			}), CommonConsts.wupdateint, CommonConsts.wupdateint);
	}
	
	public String getStation_Name(){
		return info.getProperty(WeatherProperty.Station_Name);}
	public String getTimestamp(){
		return info.getProperty(WeatherProperty.Timestamp);}
	public String getWind(){
		return info.getProperty(WeatherProperty.Wind);}
	public String getVisibility(){
		return info.getProperty(WeatherProperty.Visibility);}
	public String getSky_conditions(){
		return info.getProperty(WeatherProperty.Sky_conditions);}
	public String getTemperture(){
		return info.getProperty(WeatherProperty.Temperature);}
	public String getWindchill(){
		return info.getProperty(WeatherProperty.Windchill);}
	public String getDew_Point(){
		return info.getProperty(WeatherProperty.Dew_Point);}
	public String getHumidity(){
		return info.getProperty(WeatherProperty.Humidity);}
	public String getAir_pressure(){
		return info.getProperty(WeatherProperty.Air_pressure);}

	@Override
	public void destroy()
	{
		timer.cancel();
		weather.destroy();
	}
}