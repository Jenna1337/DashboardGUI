package tools.colors;

import dashboard.Dashboard;
import dashboard.Main;
import dashboard.display.ScheduledTask;

public class ScheduledColorChange extends ScheduledTask
{
	private static final java.util.Timer timer = new java.util.Timer(true);
	private final ScheduledTask task = this;
	private final Color c;
	
	public ScheduledColorChange() {
		long firstTime, long period;
		timer.scheduleAtFixedRate(new java.util.TimerTask(){
			public void run()
			{
				task.run();
			}
		}, firstTime, period);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		Main.dash.panw.setBackground(c);
		// TODO Auto-generated method stub
		
	}
}
