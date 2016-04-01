package dashboard;

import java.awt.Color;
import java.awt.Font;
import java.io.PrintStream;

public final class CommonConsts
{
	//TODO Read some of these in from a config file
	public static final long
	/*3600000ms = 1h; 900000ms = 15min*/
	ZERO   = 0,
	SECOND = 1000,
	MINUTE = SECOND*60,
	HOUR   = MINUTE*60,
	DAY    = HOUR*24,
	WEEK   = DAY*7,
	wsyncfailed    = CommonConsts.MINUTE*5,
	wsyncsucceeded = CommonConsts.HOUR/2,
	tsyncfailed    = CommonConsts.MINUTE*15,
	tsyncsucceeded = CommonConsts.HOUR,
	tsynccheck = gcf(tsyncfailed, tsyncsucceeded),
	tupdateint = SECOND,
	wupdateint = MINUTE*5,
	aupdateint = WEEK;
	
	public static final Font
	FontClock   = Font.decode("Courier New"),
	FontWeather = Font.decode("Times New Roman");
	
	public static final Color
	COLORbg  = Color.BLACK,
	COLORfgC = Color.LIGHT_GRAY,
	COLORbgC = Color.BLACK,
	COLORfgW = Color.LIGHT_GRAY,
	COLORbgW = Color.BLACK;
	
	public static final String
	timeserver = "time.nist.gov",
	weatherdir = "ftp://tgftp.nws.noaa.gov/data/observations/metar/decoded/",
	masterurl  = "https://github.com/JonahSloan/DashboardGUI/raw/master/",
	myfilepath = getFilePath();
	
	public static final PrintStream
	log = new PrintStream(System.out,true);
	
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
	private static long gcf(long a, long b)
	{
		while (b > 0)
		{
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	private static String getFilePath()
	{
		try
		{
			return CommonConsts.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		}
		catch (Exception e)
		{
			//This should never happen
			e.printStackTrace();
			return "Error";
		}
	}
}
