package tools.locations;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import tools.bufferedFileIO.BufferedFileReader;
import tools.bufferedFileIO.BufferedFileWriter;
import tools.bufferedFileIO.BufferedNetFileReader;

public class ICAOCoordGetter {
	public static void getFromWiki() throws Exception {
		String murl = "https://en.wikipedia.org/wiki/";
		int i=0;
		BufferedFileWriter writer = new BufferedFileWriter("ICAO_Locations");
		String[] ICAOCodes = getICAOCodes();
		final int len = ICAOCodes.length;
		for(String code : ICAOCodes)
		{
			System.out.println(++i+"/"+len);
			writer.write(code);
			try
			{
				BufferedNetFileReader bnfr = new BufferedNetFileReader(murl+code);
				String html = bnfr.readAllLines();
				String title = html.split("<title>")[1].split("</title>")[0];
				writer.write("\t"+title.replace(" - Wikipedia, the free encyclopedia", ""));
				//if(title.contains("Airport"))
				{
					try{
						String latitude = html.split("<span class=\"latitude\">")[1].split("</span>")[0].replace("\u00C2\u00B0", "-").replace("\u00E2\u20AC\u00B2", "-").replace("\u00E2\u20AC\u00B3", "-");
						String longitude = html.split("<span class=\"longitude\">")[1].split("</span>")[0].replace("\u00C2\u00B0", "-").replace("\u00E2\u20AC\u00B2", "-").replace("\u00E2\u20AC\u00B3", "-");
						writer.write("\t"+new Coords(latitude+", "+longitude));
					}catch(IndexOutOfBoundsException ioobe){}
				}
				bnfr.close();
			}
			catch(MalformedURLException | FileNotFoundException muefnfe)
			{
			}
			writer.newLine();
		}
		writer.close();
	}
	public static String[] getICAOCodes() throws Exception
	{
		BufferedFileReader reader = new BufferedFileReader("/src/tools/locations/stations/StationNames.txt");
		String text="", line;
		while((line=reader.readLine()) != null)
			text+=line+System.lineSeparator();
		reader.close();
		String[] outa = text.split(System.lineSeparator());
		return outa;
	}
	
}

