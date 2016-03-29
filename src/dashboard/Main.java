package dashboard;

import java.io.File;
import dashboard.Dashboard;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Dashboard dash = new Dashboard();
		dash.setVisible(true);
		/*
		Thread.sleep(10000);
		dash.destroy();
		System.gc();
		updateversion();
		/**/
	}
	private static long updateversion() throws Exception
	{
		File f=new File("src/version.txt");
		long ver=0;
		if(f.canRead())
		{
			java.io.BufferedReader reader=new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(f)));
			ver=Long.parseLong(reader.readLine());
			reader.close();
		}
		if(f.canWrite())
		{
			java.io.BufferedWriter writer=new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(f)));
			writer.write((ver+1)+"");
			writer.close();
		}
		if(!f.exists())
			System.err.println("Can't find version file");
		return ver;
	}
}
