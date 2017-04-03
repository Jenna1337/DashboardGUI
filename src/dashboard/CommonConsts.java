package dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager2;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import io.BufferedFileReader;
import io.BufferedFileWriter;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.text.JTextComponent;
import utils.colors.NamedColor;
import tools.colors.ScheduledColorChange;
import tools.data.AirportData;
import tools.data.AirportInfo;
import tools.data.WhereAmI;

/**
 * 
 * @author jonah.sloan
 * @author jenna3715
 *
 */
public final class CommonConsts
{
	public  static final String myfilepath = getFilePath();
	private static final boolean validExec = new java.io.File(myfilepath).isFile();
	private static final boolean writeconfig = true;//is this really needed?
	private static final String configfile = "."+getFileName()+".properties";
	private static final Properties config = getConfigData();
	
	public static final Properties locale = getLocale(getProperty("lang", "en").toLowerCase());
	
	/*3600000ms = 1h; 900000ms = 15min*/
	/**Zero.*/
	public static final long ZERO   = 0;
	/**Milliseconds in a second.*/
	public static final long SECOND = 1000;
	/**Milliseconds in a minute.*/
	public static final long MINUTE = SECOND*60;
	/**Milliseconds in a hour.*/
	public static final long HOUR   = MINUTE*60;
	/**Milliseconds in a day.*/
	public static final long DAY    = HOUR*24;
	/**Milliseconds in a week.*/
	public static final long WEEK   = DAY*7;
	/**Time to wait (in milliseconds) after a failure to get the weather data before retrying.*/
	public static final long wsyncfailed    = getProperty("wsyncfailed",    CommonConsts.MINUTE*5);
	/**Time to wait (in milliseconds) after succeeding to get the weather data before repeating.*/
	public static final long wsyncsucceeded = getProperty("wsyncsucceeded", CommonConsts.HOUR/2);
	/**Time to wait (in milliseconds) after a failure to get the server time before retrying.*/
	public static final long tsyncfailed    = getProperty("tsyncfailed",    CommonConsts.MINUTE*15);
	/**Time to wait (in milliseconds) after succeeding to get the server time before repeating.*/
	public static final long tsyncsucceeded = getProperty("tsyncsucceeded", CommonConsts.HOUR);
	/**Time (in milliseconds) between each attempted time synchronization.*/
	public static final long tsynccheck = gcf(tsyncfailed, tsyncsucceeded);
	/**How often (in milliseconds) the clock display is updated.*/
	public static final long tupdateint = getProperty("tupdateint", SECOND);
	/**How often (in milliseconds) the weather display is updated.*/
	public static final long wupdateint = getProperty("wupdateint", MINUTE*5);
	/**How often (in milliseconds) to check for software updates.*/
	public static final long aupdateint = getProperty("aupdateint", WEEK);
	/**How often (in milliseconds) to record data during a benchmark test.*/
	public static final long benchint   = getProperty("benchint", MINUTE);
	/**The time offset (in milliseconds) for the clock display.*/
	public static final long timeoffset = getProperty("timeoffset", ZERO);
	
	public static final Font
	FontClock   = getProperty("FontClock",   Font.decode("Courier New")),
	FontWeather = getProperty("FontWeather", Font.decode("Times New Roman"));
	
	/**Color of main window background**/
	public static final Color COLORbg  = getProperty("COLORbg ", NamedColor.getColorForName("Black"));
	/**Color of Clock text**/
	public static final Color COLORfgC = getProperty("COLORfgC", NamedColor.getColorForName("LightGray"));
	/**Color of Clock background**/
	public static final Color COLORbgC = getProperty("COLORbgC", NamedColor.getColorForName("Black"));
	/**Color of Weather text**/
	public static final Color COLORfgW = getProperty("COLORfgW", NamedColor.getColorForName("LightGrey"));
	/**Color of Weather background  **/
	public static final Color COLORbgW = getProperty("COLORbgW", NamedColor.getColorForName("Black"));
	/**Color of hidden areas**/
	public static final Color COLORNUL = java.awt.Color.MAGENTA;
	
	/**The NTP server to get the time from.*/
	public static final String timeserver = getProperty("timeserver", "time.nist.gov");
	//TODO work change towards using the smaller raw METAR files: "ftp://tgftp.nws.noaa.gov/data/observations/metar/stations/"
	/**The weather server to get the weather file from.*/
	public static final String weatherdir = getProperty("weatherdir", "http://sloan4.com/database/weather.php?decoded=true&station=");
	/**The weather station.*/
	public static final String wstation   = getProperty("wstation",   "KFAR");
	/**The format for displaying the time.*/
	public static final String timeformat = getProperty("timeformat", "h:mm a");
	/**The URL to check for updates.*/
	public static final String masterurl  = "https://github.com/Jenna3715/DashboardGUI/raw/master/";
	
	/**The airport approximately closest to the IP address of the executing computer.*/
	public static final AirportInfo
	localAirport  = AirportData.getClosestAirport(WhereAmI.getLocalCoords());//TODO implement this constant
	
	/**Whether to automatically update the software.*/
	public static final boolean autoupdate = getProperty("autoupdate", true);
	/**Whether to keep the computer awake.*/
	public static final boolean keepawake  = getProperty("keepawake", true);
	/**Whether to log benchmark test data.*/
	public static final boolean benchtest  = getProperty("benchtest", false);
	
	/**The layout manager to use for the main dashboard.*/
	public static final LayoutManager2 DashboardLayout = getProperty("DashboardLayout", new BorderLayout());
	
	/**The layout constraint to use for the clock panel.*/
	public static final Object LayoutClock   = getProperty("LayoutClock", new LayoutConstraint("java.awt.BorderLayout.NORTH")).getObject();
	/**The layout constraint to use for the weather panel.*/
	public static final Object LayoutWeather = getProperty("LayoutWeather", new LayoutConstraint("java.awt.BorderLayout.SOUTH")).getObject();
	
	/**The {@link PrintStream} to which debug data is logged.*/
	public static final PrintStream log = new PrintStream(System.out,true);
	
	//TODO add stuff to add to colorschedule variable
	public static final java.util.ArrayList<ScheduledColorChange> colorschedule = new ArrayList<ScheduledColorChange>();
	
	/**
	 * Gets the biggest font that will fit in the text component.
	 * @param c the {@link JTextComponent} for which to get the biggest font.
	 * @return The biggest {@link Font} for JTextComponent c
	 */
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
	/**Whether there is a configuration file.*/
	private static boolean flag_NoConfigFile = false;
	/**
	 * Retrieves the configuration data from the config file.
	 * @return The configuration properties loaded from the config file.
	 */
	private static Properties getConfigData()
	{
		Properties configs = new Properties();
		try
		{
			BufferedFileReader reader = new BufferedFileReader(configfile);
			configs.load(reader);
			reader.close();
			return configs;
		}
		catch(java.io.FileNotFoundException fnfe)
		{
			flag_NoConfigFile=true;
			if(writeconfig)
			{
				try
				{
					System.out.println("Failed to locate config file. Creating a new one.");
					new java.io.File(configfile).createNewFile();
					BufferedFileReader reader = new BufferedFileReader(configfile);
					configs.load(reader);
					reader.close();
					return configs;
				}
				// this shouldn't fail twice
				catch (Exception e)
				{
					System.out.println(e);
					throw new Error(e);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Failed to load configuration file "+configfile+".\n");
		}
		return configs;
	}
	/**
	 * Loads the specified locale file.
	 * @param lang the name of the desired locale.
	 * @return The properties for the requested locale.
	 */
	private static Properties getLocale(String lang)
	{
		Properties loc=new Properties();
		try
		{
			BufferedFileReader reader = new BufferedFileReader(configfile);
			loc.load(reader);
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			throw new InternalError("Failed to locate locale file for language \""+lang+"\"", e);
		}
		catch(IOException e)
		{
			try
			{
				BufferedFileReader reader = new BufferedFileReader(configfile);
				loc.load(reader);
				reader.close();
			}
			catch(IOException e1)
			{
				throw new InternalError(e1);
			}
		}
		return loc;
	}
	/**Reads the value of specified property from the config file. <br>
	 * 
	 *@param <T> - The type of the Object being returned as specified by {@code def}.
	 * @param propname - The name of the property to get.
	 * @param def - The default value.
	 * @return The value of {@code propname} in the type defined by {@code def},
	 * assuming the value obtained is not equal to {@code null} and is valid for
	 * the type, otherwise returns the default value {@code def}.
	 */
	@SuppressWarnings("unchecked")/**Casts are checked**/
	static <T> T getProperty(String propname, T def)
	{
		if(def==null)
			throw new NullPointerException("Unable to determine the type of variable \""+propname+"\"");
		String prop = config.getProperty(propname);
		if(flag_NoConfigFile && writeconfig)
		{
			String text="";
			try
			{
				try
				{
					java.io.BufferedReader configreader = new BufferedFileReader(configfile);
					String next="";
					while((next=configreader.readLine())!=null)
						text+=next+System.lineSeparator();
					configreader.close();
				}
				catch(java.io.FileNotFoundException fnfe)
				{
					//ignore
				}
				String defname=def.toString();
				if(def.getClass().equals(NamedColor.class))
					defname = ""+((NamedColor)def).getRGB();
				if(def.getClass().equals(Font.class))
				{
					//TODO ((Font)def).getStyle()  should be one of "PLAIN", "BOLD", "BOLDITALIC", or "ITALIC"
					defname = ((Font)def).getFontName()+"-"+((Font)def).getStyle();
				}
				if(LayoutManager2.class.isAssignableFrom(def.getClass()))
					defname = def.getClass().getName();
				java.io.BufferedWriter configwriter = new BufferedFileWriter(configfile);
				configwriter.write(text+propname+"= "+defname+System.lineSeparator());
				configwriter.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if(prop!=null)
		{
			/*Supported types:
			 * String
			 * Color
			 * Font
			 * Boolean
			 * Long
			 * LayoutConstraint
			 * LayoutManager2
			 */
			if(def.getClass().equals(String.class))
				return (T)prop;
			if(def.getClass().equals(NamedColor.class))
				return (T)NamedColor.parse(prop);
			if(def.getClass().equals(Font.class))
				return (T)Font.getFont(prop, (Font)def);
			if(def.getClass().equals(Boolean.class))
				return (T)new Boolean(prop);
			if(def.getClass().equals(Long.class))
				return (T)new Long(prop);
			if(def.getClass().equals(LayoutConstraint.class))
			{
				try
				{
					return (T)new LayoutConstraint(prop);
				}
				catch (Exception e)
				{
					try
					{
						return (T)((LayoutConstraint)def).getObject();
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			}
			if(LayoutManager2.class.isAssignableFrom(def.getClass()))
			{
				try
				{
					return (T)Class.forName(prop).newInstance();
				}
				catch (Exception e)
				{
					return def;
				}
			}
			return def;
		}
		return def;
	}
	/**
	 * Greatest common factor
	 * @param a //TODO figure out how to explain these variables 
	 * @param b 
	 * @return The greatest common factor of {@code a} and {@code b}.
	 */
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
	/**
	 * Gets the location of the executing jar file.
	 * @return the path to the executing jar file.
	 */
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
	/**
	 * @return The local file name, without the extension
	 */
	private static String getFileName()
	{
		try
		{
			if(CommonConsts.validExec)
				return new java.io.File(CommonConsts.myfilepath).getName().split("\\.")[0];
			return "DashboardGui";
		}
		catch (Exception e)
		{
			//This should never happen
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
}
/**Masking type for layout constraints**/
class LayoutConstraint
{
	String data;
	public LayoutConstraint(String data)
	{
		this.data=data;
	}
	public Object getObject()
	{
		try
		{
			return Class.forName(data.substring(0, data.lastIndexOf("."))).getField(data.substring(data.lastIndexOf(".")+1)).get(new Object());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
	public String toString()
	{
		return data;
	}
}
