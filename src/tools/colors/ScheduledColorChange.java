package tools.colors;

import java.awt.Color;

import dashboard.CommonConsts;
import dashboard.Main;
import dashboard.display.ScheduledTask;

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

	@Override
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
}
