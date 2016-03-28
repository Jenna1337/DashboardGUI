package display;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Dashboard extends JFrame
{
	private KeepAwake ka;
	// Transparent 16 x 16 pixel cursor image.
	private static final BufferedImage cursorImg = new java.awt.image.BufferedImage(16, 16, java.awt.image.BufferedImage.TYPE_INT_ARGB);
	// Create a new blank cursor.
	protected static final Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setCursor(blankCursor);
		this.getContentPane().setBackground(CommonConsts.COLORbg);
		
		this.setLayout(new BorderLayout());
		this.add(new PanelClock(), BorderLayout.NORTH);
		this.add(new PanelWeather(), BorderLayout.SOUTH);
		
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocation(0, 0);
		
		try
		{
			ka=new KeepAwake();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Font biggestFont(final javax.swing.text.JTextComponent c)
	{
		Font labelFont = c.getFont();
		String labelText = c.getText();
		
		int stringWidth = c.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = c.getWidth();
		
		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;
		
		int newFontSize = (int)Math.floor(labelFont.getSize() * widthRatio);
		int componentHeight = c.getHeight();
		
		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		
		// Set the label's font size to the newly determined size.
		return new Font(labelFont.getName(), labelFont.getStyle(), fontSizeToUse);
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
}
