package dashboard.display;

public class TimerTaskThread extends java.util.TimerTask
{
	private Thread thread;
	public TimerTaskThread(Runnable runnable)
	{
		this.thread=new Thread(runnable);;
	}
	@Override
	public void run()
	{
		new Thread(thread).start();
	}
	
}
