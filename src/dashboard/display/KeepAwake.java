package dashboard.display;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import dashboard.CommonConsts;

public class KeepAwake implements Runnable
{
	private final Robot rob;
	private volatile boolean running = false;
	private Thread t=new Thread(this);
	public KeepAwake() throws Exception
	{
		rob = new Robot();
		t.setDaemon(true);
	}
	public void run()
	{
		while(running && CommonConsts.keepawake)
		{
			Point p1 = MouseInfo.getPointerInfo().getLocation();
			rob.mouseMove(p1.x-1, p1.y-1);
			Point p2 = MouseInfo.getPointerInfo().getLocation();
			rob.mouseMove(p2.x+1, p2.y+1);
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				return;
			}
		}
	}
	public void setActive(boolean active)
	{
		if(active && !running)
		{
			running=active;
			t.start();
		}
		running=active;
	}
}
