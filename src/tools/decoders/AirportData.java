package tools.decoders;

import java.io.IOException;
import java.util.Properties;
import tools.bufferedFileIO.BufferedFileReader;
import tools.data.DataEntrySet;

public class AirportData
{
	public static final DataEntrySet thedata = new DataEntrySet();
	private static final AirportData THIS = new AirportData();
	private AirportData()
	{
		try
		{
			BufferedFileReader reader = new BufferedFileReader("src/tools/locations/airports/all.tsv");
			String line=reader.readLine();
			while((line=reader.readLine())!=null)
			{
				thedata.add(line.split("\t"));
			}
			reader.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	//TODO
}
