package dashboard.display;

import java.util.Timer;

/**
 * All instances of this class will have the same {@link Timer}.
 * @author jonah.sloan
 * @author jenna3715
 * @see java.util.Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)
 */
public abstract class ScheduledTask implements Runnable
{
	private static volatile Timer timer = new Timer(true);
	public ScheduledTask(long firstTime, long period)
	{
		timer.scheduleAtFixedRate(new java.util.TimerTask(){
			public void run()
			{
				ScheduledTask.this.run();
			}
		}, firstTime, period);
	}
	/**
	 * The action to be performed by this timer task.
	 * @see java.util.TimerTask#run()
	 */
	public abstract void run();
	/**
	 * @see java.util.Timer#cancel()
	 */
	public void cancel()
	{
		timer.cancel();
	}
}
