package display.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeKeeper
{
	volatile Date t;
	long lastsync=0;
	boolean syncsucceeded=false;
	public TimeKeeper()
	{
		t=new Date(System.currentTimeMillis());
		new Timer(true).scheduleAtFixedRate(
			new TimerTask(){
				public void run(){
					synctime();
					t=new Date(t.getTime()+1000);
				}
			}, (long)1000, (long)1000);
	}
	public String toString()
	{
		return new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(t);
	}
	/**<b>Don't ask the server too often</b>
	 **/
	private void synctime()
	{
		/*3600000ms = 1h; 900000ms = 15min*/
		if(lastsync+(syncsucceeded?3600000:900000)<System.currentTimeMillis())
		{
			try
			{
				this.t.setTime(SntpClient.getNistTime());
				lastsync=System.currentTimeMillis();
				syncsucceeded=true;
			}
			catch(Exception e)
			{
				syncsucceeded=false;
			}
		}
	}
}//TimeKeeper