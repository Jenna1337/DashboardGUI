package dashboard.updater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import dashboard.CommonConsts;
import dashboard.display.Destroyable;
import dashboard.display.ScheduledTask;

public class AutoUpdater implements Destroyable
{
	static final String versionURLString = CommonConsts.masterurl+"src/version.txt";
	static final String jarURLString =     CommonConsts.masterurl+"Releases/DashboardGui.jar";
	private static ScheduledTask updateTimer;
	public AutoUpdater()
	{
		updateTimer=new ScheduledTask(CommonConsts.ZERO, CommonConsts.aupdateint){
			public void run()
			{
				try
				{
					if(updateAvailable())
						update();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};
	}
	public static void update() throws Exception
	{
		String path = AutoUpdater.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		if(!path.endsWith(".jar"))
			return;
		java.nio.channels.ReadableByteChannel ch = java.nio.channels.Channels.newChannel(new java.net.URL(jarURLString).openStream());
		FileOutputStream fos = new FileOutputStream(path);
		fos.getChannel().transferFrom(ch, 0, Long.MAX_VALUE);
		fos.close();
	}
	public static boolean updateAvailable() throws IOException
	{
		try
		{
			BufferedFileReader versionreader = new BufferedFileReader(versionURLString);
			long remoteversion = Long.parseLong(versionreader.readLine());
			versionreader.close();
			return remoteversion!=getversion();
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
	@Override
	public void destroy()
	{
		updateTimer.cancel();
	}
}
