package display.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import display.CommonConsts;

/**
 * All instances of this class will contain the same time.
 * @author jonah.sloan
 *
 */
public final class TimeKeeper
{
	/**
	 * This is consistant across all 
	 * auto increments by 1 second per second
	 */
	protected static volatile Date t=null;
	private long lastsync=0;
	private long offset=0;
	private static boolean syncsucceeded=false;
	private final static Timer timer=new Timer(true);
	private static boolean timerset=false;
	public String strformat;
	private TimeKeeper()
	{
		t=new Date();
		synctime();
		if(!timerset)
		{
			timer.scheduleAtFixedRate(
				new TimerTask(){
					public void run(){
						synctime();
					}
				}, CommonConsts.ZERO, CommonConsts.tsynccheck);
			while(t==null)
				/*wait for t*/;
			timer.scheduleAtFixedRate(
				new TimerTask(){
					public void run(){
						t=new Date(t.getTime()+CommonConsts.tupdateint);
					}
				}, CommonConsts.ZERO, CommonConsts.tupdateint);
			timerset=true;
		}
	}
	/**Constructs a new TimeKeeper object with the specified format
	 * Formats
	 * <pre>
	 * | Letter | Description                       | Type              | Example                               |
	 * +--------+-----------------------------------+-------------------+---------------------------------------+
	 * |   G    | Era designator                    | Text              | AD                                    |
	 * |   y    | Year                              | Year              | 1996; 96                              |
	 * |   Y    | Week year                         | Year              | 2009; 09                              |
	 * |   M    | Month in year (context sensitive) | Month             | July; Jul; 07                         |
	 * |   L    | Month in year (standalone form)   | Month             | July; Jul; 07                         |
	 * |   D    | Day in year                       | Number            | 189                                   |
	 * |   d    | Day in month                      | Number            | 10                                    |
	 * |   E    | Day name in week                  | Text              | Tuesday; Tue                          |
	 * |   a    | Am/pm marker                      | Text              | PM                                    |
	 * |   H    | Hour in day (0-23)                | Number            | 0                                     |
	 * |   k    | Hour in day (1-24)                | Number            | 24                                    |
	 * |   K    | Hour in am/pm (0-11)              | Number            | 0                                     |
	 * |   h    | Hour in am/pm (1-12)              | Number            | 12                                    |
	 * |   m    | Minute in hour                    | Number            | 30                                    |
	 * |   s    | Second in minute                  | Number            | 55                                    |
	 * |   z    | Time zone                         | General time zone | Pacific Standard Time; PST; GMT-08:00 |
	 * </pre>
	 *
	 * Examples:<br><pre>
	 * Format String         | Output
	 * ----------------------+-
	 * "d/m/y"               | 25/3/16
	 * "EEEE, MMMMM d, yyyy" | Friday, March 25, 2016
	 * "h:mm a"              | 3:57 PM
	 * "h:mm:ss a"           | 3:57:26 PM
	 * </pre>
	 */
	public TimeKeeper(String format)
	{
		this();
		this.strformat=format;
	}
	public String toString()
	{
		return new SimpleDateFormat(strformat).format(t);
	}
	/**<b>Don't ask the server too often</b>
	 **/
	private void synctime()
	{
		//hard coded minimum times 1h, 15m
		if(lastsync+(syncsucceeded?Math.max(CommonConsts.tsyncsucceeded,CommonConsts.HOUR):
			Math.max(CommonConsts.tsyncfailed,CommonConsts.MINUTE*15)) < System.currentTimeMillis()+offset)
		{
			try
			{
				long locoffset = System.currentTimeMillis()+(offset = SntpClient.getSystemTimeOffset());
				t.setTime(locoffset);
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