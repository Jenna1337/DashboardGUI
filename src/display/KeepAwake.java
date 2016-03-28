package display;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;

public class KeepAwake implements Runnable
{
	private final Robot rob;
	private volatile boolean running = false;
	public KeepAwake() throws Exception
	{
		rob = new Robot();
	}
	public void run()
	{
		while(running)
		{
			Point p1 = MouseInfo.getPointerInfo().getLocation();
			rob.mouseMove(p1.x-1, p1.y-1);
			Point p2 = MouseInfo.getPointerInfo().getLocation();
			rob.mouseMove(p2.x+1, p2.y+1);
		}
	}
	public void setActive(boolean active)
	{
		running=active;
		if(running)
		{
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			rob.mouseMove(d.width, d.height);
			run();
		}
	}
}
