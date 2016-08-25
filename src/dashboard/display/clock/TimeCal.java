package dashboard.display.clock;

import java.util.Calendar;
import dashboard.CommonConsts;

public abstract class TimeCal {

	private TimeCal(){}
	private static long get(int par)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(TimeKeeper.getTime());
		return c.get(par);
	}
	
	public static long getYearOfEra(long date)
	{
		return get(Calendar.YEAR);
	}
	/*TODO*/public abstract long getDaysOfYearOfEra(long date);
	/*TODO*/public abstract long getHoursOfYearOfEra(long date);
	/*TODO*/public abstract long getMinutesOfYearOfEra(long date);
	/*TODO*/public abstract long getSecondsOfYearOfEra(long date);
	/*TODO*/public abstract long getMillisOfYearOfEra(long date);
	public static long getMonthOfYear(long date)
	{
		return get(Calendar.MONTH);
	}
	public static long getWeekOfYear(long date)
	{
		return get(Calendar.WEEK_OF_YEAR);
	}
	public static long getWeekOfMonth(long date)
	{
		return get(Calendar.WEEK_OF_MONTH);
	}
	/*TODO*/public abstract long getDaysOfWeekOfYear(long date);
	/*TODO*/public abstract long getHoursOfWeekOfYear(long date);
	/*TODO*/public abstract long getMinutesOfWeekOfYear(long date);
	/*TODO*/public abstract long getSecondsOfWeekOfYear(long date);
	/*TODO*/public abstract long getMillisOfWeekOfYear(long date);
	/*TODO*/public abstract long getDaysOfWeekOfMonth(long date);
	/*TODO*/public abstract long getHoursOfWeekOfMonth(long date);
	/*TODO*/public abstract long getMinutesOfWeekOfMonth(long date);
	/*TODO*/public abstract long getSecondsOfWeekOfMonth(long date);
	/*TODO*/public abstract long getMillisOfWeekOfMonth(long date);
	
	public static long getDaysOfYear(long date)
	{
		return get(Calendar.DAY_OF_YEAR);
	}
	public static long getDaysOfMonth(long date)
	{
		return get(Calendar.DAY_OF_MONTH);
	}
	public static long getDaysOfWeek(long date)
	{
		return get(Calendar.DAY_OF_WEEK);
	}
	public static long getDaysOfWeekInMonth(long date)
	{
		return get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}
	/*TODO*/public abstract long getHoursOfDaysOfYear(long date);
	/*TODO*/public abstract long getMinutesOfDaysOfYear(long date);
	/*TODO*/public abstract long getSecondsOfDaysOfYear(long date);
	/*TODO*/public abstract long getMillisOfDaysOfYear(long date);
	/*TODO*/public abstract long getHoursOfDaysOfMonth(long date);
	/*TODO*/public abstract long getMinutesOfDaysOfMonth(long date);
	/*TODO*/public abstract long getSecondsOfDaysOfMonth(long date);
	/*TODO*/public abstract long getMillisOfDaysOfMonth(long date);
	/*TODO*/public abstract long getHoursOfDaysOfWeek(long date);
	/*TODO*/public abstract long getMinutesOfDaysOfWeek(long date);
	/*TODO*/public abstract long getSecondsOfDaysOfWeek(long date);
	/*TODO*/public abstract long getMillisOfDaysOfWeek(long date);
	/*TODO*/public abstract long getHoursOfDaysOfWeekInMonth(long date);
	/*TODO*/public abstract long getMinutesOfDaysOfWeekInMonth(long date);
	/*TODO*/public abstract long getSecondsOfDaysOfWeekInMonth(long date);
	/*TODO*/public abstract long getMillisOfDaysOfWeekInMonth(long date);
	public static long getHoursOfDay(long date)
	{
		return get(Calendar.HOUR_OF_DAY);
	}
	public static long getMinutesOfDay(long date)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(TimeKeeper.getTime());
		long tod = 0;
		tod += getHoursOfDay(date) * 60;
		tod += getMinutesOfHour(date);
		return tod;
	}
	public static long getSecondsOfDay(long date)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(TimeKeeper.getTime());
		long tod = 0;
		tod += getHoursOfDay(date) * 60 * 60;
		tod += getSecondsOfHour(date);
		return tod;
	}
	public static long getMillisOfDay(long date)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(TimeKeeper.getTime());
		long tod = 0;
		tod += getHoursOfDay(date) * CommonConsts.HOUR;
		tod += getMillisOfHour(date);
		return tod;
	}
	public static long getMinutesOfHour(long date)
	{
		return get(Calendar.MINUTE);
	}
	public static long getSecondsOfHour(long date)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(TimeKeeper.getTime());
		long tod = 0;
		tod += getMinutesOfHour(date) * 60;
		tod += getSecondsOfMinute(date);
		return tod;
	}
	public static long getMillisOfHour(long date)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(TimeKeeper.getTime());
		long tod = 0;
		tod += getMinutesOfHour(date) * CommonConsts.MINUTE;
		tod += getMillisOfMinute(date);
		return tod;
	}
	public static long getSecondsOfMinute(long date)
	{
		return get(Calendar.SECOND);
	}
	public static long getMillisOfMinute(long date)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(TimeKeeper.getTime());
		long tod = 0;
		tod += getSecondsOfMinute(date) * CommonConsts.SECOND;
		tod += getMillisOfSecond(date);
		return tod;
	}
	public static long getMillisOfSecond(long date)
	{
		return get(Calendar.MILLISECOND);
	}
}
