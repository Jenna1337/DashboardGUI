package display.clock;

public final class TimeConstant
{
	public static final long
	/*3600000ms = 1h; 900000ms = 15min*/
	ZERO = 0,
	SECOND = 1000,
	MINUTE = SECOND*60,
	HOUR = MINUTE*60,
	DAY = HOUR*24,
	WEEK = DAY*7
	;
}
