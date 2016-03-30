package dashboard.updater;

import java.io.File;
import java.io.IOException;
import dashboard.CommonConsts;

public class AutoUpdater
{
	static final String versionURLString = CommonConsts.masterurl+"src/version.txt";
	static final String jarURLString =     CommonConsts.masterurl+"Releases/DashboardGui.jar";
	public AutoUpdater()
	{
		try
		{
			String fpath = AutoUpdater.class.getProtectionDomain().getCodeSource().getLocation().toURI().toASCIIString();
			String fname = "DashboardGui.jar";
			String ffull = fpath+fname;
			System.out.println(ffull);
			File floc = new File(ffull);
			String response = floc.getName();
			System.out.println(response);
			//TODO
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void update()
	{
		
	}
	public static boolean updateAvailable() throws IOException
	{
		try
		{
			BufferedFileReader versionreader = new BufferedFileReader(versionURLString);
			long remoteversion = Long.parseLong(versionreader.readLine());
			versionreader.close();
			return remoteversion>getversion();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public static long getversion() throws Exception
	{
		File f=new File("src/version.txt");
		long ver=0;
		if(f.canRead())
		{
			java.io.BufferedReader reader=new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(f)));
			ver=Long.parseLong(reader.readLine());
			reader.close();
		}
		if(!f.exists())
			System.err.println("Can't find version file");
		return ver;
	}
}
