package dashboard.display;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import dashboard.CommonConsts;
import dashboard.display.clock.TimeKeeper;
import dashboard.interfaces.Destroyable;

/**
 * A JPanel that displays the time.
 * @author jonah.sloan
 * @author jenna3715
 *
 */
@SuppressWarnings("serial")
public class PanelClock extends JPanel implements Destroyable
{
	private TimeKeeper datetime;
	private JTextPane text;
	private ScheduledTask textupdater;
	public PanelClock()
	{
		this.initialize();
	}
	public PanelClock(LayoutManager arg0)
	{
		super(arg0);
		this.initialize();
	}
	public PanelClock(boolean arg0)
	{
		super(arg0);
		this.initialize();
	}
	public PanelClock(LayoutManager arg0, boolean arg1)
	{
		super(arg0, arg1);
		this.initialize();
	}
	private void initialize()
	{
		datetime = new TimeKeeper(CommonConsts.timeformat);
		text = new JTextPane();
		text.setBorder(null);
		text.setFont(CommonConsts.FontClock);
		text.setEditable(false);
		text.setForeground(CommonConsts.COLORfgC);
		text.setBackground(CommonConsts.COLORbgC);
		textupdater=new ScheduledTask(CommonConsts.ZERO, CommonConsts.SECOND){
			public void run(){
				text.setText(datetime.toString());
			}
		};
		this.setBackground(CommonConsts.COLORNUL);
		this.setLayout(new BorderLayout());
		this.add(text, BorderLayout.CENTER);
	}
	@Override
	public void paint(java.awt.Graphics g)
	{
		text.setFont(CommonConsts.biggestFont(text));
		super.paint(g);
	}
	@Override
	public void destroy()
	{
		textupdater.cancel();
		datetime.destroy();
	}
	
}
