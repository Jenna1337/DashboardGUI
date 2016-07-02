package dashboard;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import dashboard.display.KeepAwake;
import dashboard.display.PanelClock;
import dashboard.display.PanelWeather;
import dashboard.interfaces.Destroyable;

@SuppressWarnings("serial")
public class Dashboard extends JFrame implements Destroyable, KeyListener
{
	private KeepAwake ka;
	// Transparent 16 x 16 pixel cursor image.
	private static final BufferedImage cursorImg = new java.awt.image.BufferedImage(16, 16, java.awt.image.BufferedImage.TYPE_INT_ARGB);
	// Create a new blank cursor.
	protected static final Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	private static final AutoUpdater autoupdater = new AutoUpdater();
	
	private PanelWeather panw;
	private PanelClock panc;
	
	
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setCursor(blankCursor);
		this.getContentPane().setBackground(CommonConsts.COLORbg);
		
		this.setLayout(CommonConsts.DashboardLayout);//TODO figure out why some layout configurations aren't working
		this.add(panc=new PanelClock(), CommonConsts.LayoutClock);
		this.add(panw=new PanelWeather(), CommonConsts.LayoutWeather);
		
		this.pack();
		this.setFocusable(true);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocation(0, 0);
		this.addKeyListener(this);
		this.getContentPane().addKeyListener(this);
		this.panc.addKeyListener(this);
		this.panw.addKeyListener(this);
		
		try
		{
			ka=new KeepAwake();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void setVisible(boolean visible)
	{
		if(visible)
		{
			super.setVisible(visible);
			ka.setActive(visible);
		}
		else
		{
			ka.setActive(visible);
			super.setVisible(visible);
		}
	}
	@Override
	public void paint(java.awt.Graphics g)
	{
		super.paint(g);
		System.gc();
	}
	@Override
	public void dispose()
	{
		//Destroys window to stop JVM
		this.destroy();
		super.dispose();
	}
	@Override
	/**
	 * Destroys the Dashboard object
	 */
	public void destroy()
	{
		this.setVisible(false);
		panc.destroy();
		panw.destroy();
		autoupdater.destroy();
		Thread[] threads=new Thread[Thread.activeCount()];
		while(java.util.Arrays.deepToString(threads).contains("AWT-EventQueue"))
		{
			Thread.enumerate(threads);
			for(Thread thr : threads)
				if(thr.getName().contains("AWT-EventQueue"))
					thr.interrupt();
			threads=new Thread[Thread.activeCount()];
		}
		super.dispose();
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.ALT_DOWN_MASK & KeyEvent.VK_F4:
			case KeyEvent.VK_ESCAPE:
				this.destroy();
				break;
			default:
				break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}
