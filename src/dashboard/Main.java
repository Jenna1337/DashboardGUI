package dashboard;

import java.lang.reflect.Field;
import java.util.List;
import dashboard.display.weather.WeatherData;
import tools.bufferedFileIO.BufferedFileReader;
import tools.bufferedFileIO.BufferedFileWriter;
import tools.data.AirportData;
import tools.data.AirportInfo;
import tools.locations.Coords;
import tools.locations.CountryCodes;

public class Main
{
	public static Dashboard dash;
	
	public static void main(String[] args) throws Exception
	{
		generatecoorddatabase();
		System.exit(0);
		
		System.out.println(new WeatherData(AirportData.getLocalAirport().getAirportCode()));
		System.exit(0);
		
		dash = new Dashboard();
		dash.setVisible(true);
		/*
		Thread.sleep(10000);
		dash.destroy();
		System.gc();
		/**/
		Thread.sleep(1);
		if(CommonConsts.benchtest)
			benchmark();
	}
	private static void generatecoorddatabase() throws Exception
	{
		BufferedFileWriter fwr = new BufferedFileWriter("src/tools/locations/airports/all_coords.csv");
		Field f_airportnames = AirportData.class.getDeclaredField("airportnames");
		Field f_airports = AirportData.class.getDeclaredField("airports");
		f_airportnames.setAccessible(true);
		f_airports.setAccessible(true);
		
		@SuppressWarnings("unchecked")
		List<String> airportnames = (List<String>) f_airportnames.get(null);
		AirportInfo[] airports = (AirportInfo[]) f_airports.get(null);
		
		System.out.println("Working...");
		for(AirportInfo airport : airports){
			if(airportnames.contains(airport.getAirportCode())){
				Coords geo = airport.getCoords();
				fwr.write(airport.getAirportCode() + "," + geo.getLatitude() +
						"," + geo.getLongitude() + ",\n");
			}
		}
		System.out.println("Done");
		fwr.close();
	}
	@SuppressWarnings("unused")
	private static void mergeairportdata() throws Exception
	{
		BufferedFileWriter fwr = new BufferedFileWriter("src/tools/locations/airports/all.tsv");
		boolean frs = true;
		int i=1,l=CountryCodes.values().length;
		for(CountryCodes cco : CountryCodes.values())
		{
			System.out.println(i+++"/"+l);
			BufferedFileReader fre = new BufferedFileReader("src/tools/locations/airports/countries/"+cco.name()+".csv");
			String fln = fre.readLine();
			if(frs)
			{
				fwr.write(fln);
				frs=false;
			}
			String nxl;
			while((nxl=fre.readLine()) != null)
				fwr.write("\n"+formatairportdata(nxl));
			fre.close();
		}
		fwr.close();
		System.out.println("Done.");
	}
	private static String formatairportdata(String txt)
	{
		String[] aarr = new String[8];
		for(int i=0;i<aarr.length;++i)
			aarr[i]="";
		boolean qt=false, bk=false;
		int mi=0;
		flp:
		for(char c : txt.toCharArray())
		{
			switch(c)
			{
				case '\"':
					if(!bk)
						qt=!qt;
					break;
				case '{':
					bk=true;
					break;
				case '}':
					bk=false;
					break;
				case ',':
					if(qt)
						aarr[mi]+=c;
					else
						if(++mi>=aarr.length)
							break flp;
					break;
				default:
					aarr[mi]+=c;
			}
		}
		String str = "";
		for(int i=0;i<aarr.length;++i)
			str += aarr[i] + "\t";
		return str;
	}
	private static void benchmark() throws Exception
	{
		final BufferedFileWriter writer=new BufferedFileWriter("benchmarktest.txt");
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				try
				{
					writer.close();
				}
				catch (Exception e){}//no need to do anything with error because runtime is shutting down
			}
		});
		while(Thread.activeCount()>1)//This should be the only thread at the end
		{
			//System.out.println("Free: "+Runtime.getRuntime().freeMemory());
			//System.out.println("Total:"+Runtime.getRuntime().totalMemory());
			try
			{
				long usedmemory = java.lang.management.ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
				writer.write(usedmemory+"\n");
				writer.flush();
			}
			catch(java.io.IOException ioe){}
			Thread.sleep(CommonConsts.benchint);
		}
	}
}
