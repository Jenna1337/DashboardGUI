package dashboard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import bufferedFileIO.BufferedNetFileReader;
import dashboard.display.ScheduledTask;
import dashboard.interfaces.Destroyable;

public class AutoUpdater implements Destroyable
{
	static final String versionURLString = CommonConsts.masterurl+"src/version.txt";
	static final String jarURLString =     CommonConsts.masterurl+"Releases/DashboardGui.jar";
	private static ScheduledTask updateTimer;
	public AutoUpdater()
	{
		if(CommonConsts.autoupdate)
		updateTimer=new ScheduledTask(CommonConsts.ZERO, CommonConsts.aupdateint){
			public void run()
			{
				try
				{
					if(updateAvailable())
					{
						CommonConsts.log.println("Update available.");
						update();
					}
					else
						CommonConsts.log.println("No new updates available");
				}
				catch (Exception e)
				{
					CommonConsts.log.println("Failed to update successfully. Will try again in "+CommonConsts.aupdateint+" milliseconds.");
					e.printStackTrace(CommonConsts.log);
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
			file.isDirectory()
			)
		{
			CommonConsts.log.println("Failed to locate local executable.");
			return;
		}
		if(!file.canRead())
		{
			CommonConsts.log.println("Error: local executable in not readable.");
			return;
		}
		if(!file.canExecute())
		{
			CommonConsts.log.println("Error: local executable is not executable.");
			CommonConsts.log.println("Nice going.");
			return;
		}
		if(!file.canWrite())
		{
			CommonConsts.log.println("Error: local executable is not writable");
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
			BufferedNetFileReader versionreader = new BufferedNetFileReader(versionURLString);
			long remoteversion = Long.parseLong(versionreader.readLine());
			versionreader.close();
			CommonConsts.log.println("Remote file found...");
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
		String f="src/version.txt";
		long ver=0;
		if(new File(f).canRead())
		{
			CommonConsts.log.println("Comparing remote to local version...");
			java.io.BufferedReader reader=new bufferedFileIO.BufferedFileReader(f);
			ver=Long.parseLong(reader.readLine());
			reader.close();
		}
		if(!new File(f).exists())
			CommonConsts.log.println("Can't find version file");
		return ver;
	}
	@Override
	public void destroy()
	{
		updateTimer.cancel();
	}
}
