package dashboard.display;

public class TimerTaskThread extends java.util.TimerTask
{
	private Thread thread;
	public TimerTaskThread(Runnable runnable)
	{
		this.thread=new Thread(runnable);
		this.thread.setDaemon(true);
	}
	@Override
	public void run()
	{
		new Thread(thread).start();
	}
	
}
