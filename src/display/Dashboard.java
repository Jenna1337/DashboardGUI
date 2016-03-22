package display;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Dashboard extends JFrame
{
	public Dashboard()
	{
		super();
		this.initialize();
	}
	public Dashboard(String str)
	{
		super(str);
		this.initialize();
	}
	private void initialize()
	{
		this.setUndecorated(true);
		
	}
}
