package display;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import display.clock.PanelClock;
import display.weather.PanelWeather;

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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		this.add(new PanelClock(), BorderLayout.NORTH);
		this.add(new PanelWeather(), BorderLayout.SOUTH);
		
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocation(0, 0);
	}
	public static Font biggestFont(javax.swing.text.JTextComponent c)
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
		return new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse);
	}
}
