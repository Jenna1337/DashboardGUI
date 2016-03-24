package display;

public final class CommonConsts
{
	public static final long
	/*3600000ms = 1h; 900000ms = 15min*/
	ZERO = 0,
	SECOND = 1000,
	MINUTE = SECOND*60,
	HOUR = MINUTE*60,
	DAY = HOUR*24,
	WEEK = DAY*7,
	wsyncfailed = CommonConsts.MINUTE*5,
	wsyncsucceeded=CommonConsts.HOUR/2,
	tsyncfailed  = CommonConsts.MINUTE*15,
	tsyncsucceeded=CommonConsts.HOUR;
	;
}
