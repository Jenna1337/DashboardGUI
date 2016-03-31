package dashboard.display;

/**
 * 
 * @author jonah.sloan
 * @see java.util.Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)
 */
public abstract class ScheduledTask implements Runnable, AutoCloseable
{
	private final java.util.Timer timer = new java.util.Timer(true);
	private final ScheduledTask task = this;
	public ScheduledTask(long firstTime, long period)
	{
		timer.scheduleAtFixedRate(new java.util.TimerTask(){
			public void run()
			{
				task.run();
			}
		}, firstTime, period);
	}
	/**
	 * The action to be performed by this timer task.
	 * @see java.util.TimerTask#run()
	 * @see java.lang.Thread#run()
	 */
	public abstract void run();
	/**
	 * @see java.util.Timer#cancel()
	 */
	public void cancel()
	{
		timer.cancel();
	}
	public void close()
	{
		this.cancel();
	}
}
