package display.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import display.CommonConsts;

public final class TimeKeeper
{
	/**auto increments by 1 second per second**/
	volatile Date t;
	private long lastsync=0;
	private long offset=0;
	private boolean syncsucceeded=false;
	/**Formats
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
					t=new Date(t.getTime()+CommonConsts.SECOND);
				}
			}, CommonConsts.ZERO, CommonConsts.SECOND);
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
		if(lastsync+(syncsucceeded?Math.max(CommonConsts.tsyncsucceeded,CommonConsts.HOUR):
			Math.max(CommonConsts.tsyncfailed,CommonConsts.MINUTE*15)) < System.currentTimeMillis()+offset)
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