package tools;

import java.util.Calendar;

public abstract class TimeCal
{
	private TimeCal(){}
	private static long get(long date, int par)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date);
		return c.get(par);
	}
	public static long getEra(long date)
	{
		return get(date, Calendar.ERA);
	}
	public static long getYearOfEra(long date)
	{
		return get(date, Calendar.YEAR);
	}
	public static long getMonthOfYear(long date)
	{
		return get(date, Calendar.MONTH);
	}
	public static long getWeekOfYear(long date)
	{
		return get(date, Calendar.WEEK_OF_YEAR);
	}
	public static long getWeekOfMonth(long date)
	{
		return get(date, Calendar.WEEK_OF_MONTH);
	}
	public static long getDayOfYear(long date)
	{
		return get(date, Calendar.DAY_OF_YEAR);
	}
	public static long getDayOfMonth(long date)
	{
		return get(date, Calendar.DAY_OF_MONTH);
	}
	public static long getDayOfWeek(long date)
	{
		return get(date, Calendar.DAY_OF_WEEK);
	}
	public static long getDayOfWeekInMonth(long date)
	{
		return get(date, Calendar.DAY_OF_WEEK_IN_MONTH);
	}
	public static long getHoursOfDay(long date)
	{
		return get(date, Calendar.HOUR_OF_DAY);
	}
	public static long getMinutesOfHour(long date)
	{
		return get(date, Calendar.MINUTE);
	}
	public static long getSecondsOfMinute(long date)
	{
		return get(date, Calendar.SECOND);
	}
	public static long getMillisOfSecond(long date)
	{
		return get(date, Calendar.MILLISECOND);
	}
}
