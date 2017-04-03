package tools.colors;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import dashboard.CommonConsts;
import dashboard.Main;
import dashboard.display.ScheduledTask;

/**
 * 
 * @author jonah.sloan
 * @author jenna3715
 *
 */
public class ScheduledColorChange extends ScheduledTask
{
	private final Color c;
	
	public ScheduledColorChange(String text)
	{
		this(getColor(text), getChangeTime(text), getRecurrance(text));
	}
	public ScheduledColorChange(Color color, long attime) 
	{
		this(color, attime, CommonConsts.DAY);
	}
	public ScheduledColorChange(Color color, long attime, long recurrance) 
	{
		super(getInitDelay(attime), recurrance);
		c=color;
		//TODO
	}
	public void run() {
		Main.dash.panw.setBackground(c);
	}
	private static long getInitDelay(long attime)
	{
		// TODO
		return 0;
	}
	private static long getRecurrance(String text) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static long getChangeTime(String text) {
		// TODO Auto-generated method stub
		return 0;
	}
	private static Color getColor(String text) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
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
	 * | Format String         | Output                 |
	 * +-----------------------+------------------------+
	 * | "d/m/y"               | 25/3/16                |
	 * | "EEEE, MMMMM d, yyyy" | Friday, March 25, 2016 |
	 * | "h:mm a"              | 3:57 PM                |
	 * | "h:mm:ss a"           | 3:57:26 PM             |
	 * </pre>
	 * @throws ParseException 
	 */
	private static Date parse(String format, String text) throws ParseException
	{
		return new SimpleDateFormat(format).parse(text);
	}
}
