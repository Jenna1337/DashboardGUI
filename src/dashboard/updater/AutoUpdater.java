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
					else
						CommonConsts.log.println("No new updates available");
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
		File file=new File(CommonConsts.myfilepath);
		if(
			(!file.exists()) ||
			(!file.isFile()) || 
			(!file.getName().endsWith(".jar"))
			)
		{
			CommonConsts.log.println("Failed to locate local jar file.");
			return;
		}
		java.nio.channels.ReadableByteChannel ch = java.nio.channels.Channels.newChannel(new java.net.URL(jarURLString).openStream());
		CommonConsts.log.println("Downloading update...");
		FileOutputStream fos = new FileOutputStream(file.getPath());
		fos.getChannel().transferFrom(ch, 0, Long.MAX_VALUE);
		fos.close();
		CommonConsts.log.println("Successfully updated");
		CommonConsts.log.println("Restarting program...");
		ProcessBuilder pb = new ProcessBuilder(file.getParent(), "-jar", file.getName());
		pb.directory(file);
		pb.start();
		System.exit(0);
	}
	public static boolean updateAvailable() throws IOException
	{
		CommonConsts.log.println("Searching for updates...");
		try
		{
			BufferedFileReader versionreader = new BufferedFileReader(versionURLString);
			long remoteversion = Long.parseLong(versionreader.readLine());
			versionreader.close();
			CommonConsts.log.println("Update found...");
			return remoteversion!=getversion();
		}
		catch(Exception e)
		{
			CommonConsts.log.println("Failed to find update");
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
			CommonConsts.log.println("Can't find version file");
		return ver;
	}
	@Override
	public void destroy()
	{
		updateTimer.cancel();
	}
}
