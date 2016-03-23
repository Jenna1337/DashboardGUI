package display.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public final class TimeKeeper
{
	/**auto increments by 1 second per second**/
	volatile Date t;
	private long lastsync=0;
	private long offset=0;
	private boolean syncsucceeded=false;
	/*3600000ms = 1h; 900000ms = 15min*/
	long timesyncfailed  = 900000,
		timesyncsucceeded=3600000;
	/**Formats
	 * <br/>G  Era designator  Text  AD  
	 * <br/>y  Year  Year  1996; 96  
	 * <br/>Y  Week year  Year  2009; 09  
	 * <br/>M  Month in year (context sensitive)  Month  July; Jul; 07  
	 * <br/>L  Month in year (standalone form)  Month  July; Jul; 07  
	 * <br/>D  Day in year  Number  189  
	 * <br/>d  Day in month  Number  10  
	 * <br/>E  Day name in week  Text  Tuesday; Tue  
	 * <br/>a  Am/pm marker  Text  PM  
	 * <br/>H  Hour in day (0-23)  Number  0  
	 * <br/>k  Hour in day (1-24)  Number  24  
	 * <br/>K  Hour in am/pm (0-11)  Number  0  
	 * <br/>h  Hour in am/pm (1-12)  Number  12  
	 * <br/>m  Minute in hour  Number  30  
	 * <br/>s  Second in minute  Number  55  
	 * <br/>z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00  
	 */
	String dateshort= "d/m/y",
		   datelong = "EEEE, MMMMM d, yyyy",
		   timeshort= "h:mm a",
		   timelong = "h:mm:ss a";
	public TimeKeeper()
	{
		t=new Date();
		synctime();
		new Timer(true).scheduleAtFixedRate(
			new TimerTask(){
				public void run(){
					synctime();
					t=new Date(t.getTime()+1000);
				}
			}, (long)0, (long)1000);
	}
	public String toString()
	{
		return this.getDateLong()+" at "+this.getTimeLong();
	}
	public String getDateShort()
	{
		return new SimpleDateFormat(dateshort).format(t);
	}
	public String getDateLong()
	{
		return new SimpleDateFormat(datelong).format(t);
	}
	public String getTimeShort()
	{
		return new SimpleDateFormat(timeshort).format(t);
	}
	public String getTimeLong()
	{
		return new SimpleDateFormat(timelong).format(t);
	}
	/**<b>Don't ask the server too often</b>
	 **/
	private void synctime()
	{
		//hard coded minimum times 1h, 15m
		if(lastsync+(syncsucceeded?Math.max(timesyncsucceeded,3600000):Math.max(timesyncfailed,900000)) < System.currentTimeMillis()+offset)
		{
			try
			{
				long locoffset = System.currentTimeMillis()+(offset = SntpClient.getSystemTimeOffset());
				this.t.setTime(locoffset);
				lastsync=locoffset;
				syncsucceeded=true;
			}
			catch(Exception e)
			{
				syncsucceeded=false;
			}
		}
	}
}//TimeKeeper