package dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager2;
import java.io.PrintStream;
import tools.bufferedFileIO.BufferedFileReader;
import tools.bufferedFileIO.BufferedFileWriter;
import tools.colors.ColorParser;
import tools.colors.NamedColor;

public final class CommonConsts
{
	public  static final String myfilepath = getFilePath();
	private static final boolean validExec = new java.io.File(myfilepath).isFile();
	private static final boolean writeconfig = true;//is this really needed?
	private static final String configfile = "."+getFileName()+".properties";
	private static java.util.Properties config = getConfigData();
	
	public static final long
	/*3600000ms = 1h; 900000ms = 15min*/
	ZERO   = 0,
	SECOND = 1000,
	MINUTE = SECOND*60,
	HOUR   = MINUTE*60,
	DAY    = HOUR*24,
	WEEK   = DAY*7,
	wsyncfailed    = getProperty("wsyncfailed",    CommonConsts.MINUTE*5),
	wsyncsucceeded = getProperty("wsyncsucceeded", CommonConsts.HOUR/2),
	tsyncfailed    = getProperty("tsyncfailed",    CommonConsts.MINUTE*15),
	tsyncsucceeded = getProperty("tsyncsucceeded", CommonConsts.HOUR),
	tsynccheck = gcf(tsyncfailed, tsyncsucceeded),
	tupdateint = getProperty("tupdateint", SECOND),
	wupdateint = getProperty("wupdateint", MINUTE*5),
	aupdateint = getProperty("aupdateint", WEEK),
	benchint   = getProperty("benchint", MINUTE),
	timeoffset = getProperty("timeoffset", ZERO);
	
	public static final Font
	FontClock   = getProperty("FontClock",   Font.decode("Courier New")),
	FontWeather = getProperty("FontWeather", Font.decode("Times New Roman"));
	
	public static final Color
	/**Color of main window**/
	COLORbg  = getProperty("COLORbg ", NamedColor.getColorForName("BLACK")),
	/**Color of Clock text**/
	COLORfgC = getProperty("COLORfgC", NamedColor.getColorForName("LIGHT_GRAY")),
	/**Color of Clock bg**/
	COLORbgC = getProperty("COLORbgC", NamedColor.getColorForName("BLACK")),
	/**Color of Weather text**/
	COLORfgW = getProperty("COLORfgW", NamedColor.getColorForName("LIGHT_GRAY")),
	/**Color of Weather bg  **/
	COLORbgW = getProperty("COLORbgW", NamedColor.getColorForName("BLACK")),
	/**Color of hidden areas**/
	COLORNUL = java.awt.Color.MAGENTA;
	
	public static final String
	timeserver = getProperty("timeserver", "time.nist.gov"),
	weatherdir = getProperty("weatherdir", "ftp://tgftp.nws.noaa.gov/data/observations/metar/decoded/"),
	wstation   = getProperty("wstation",   "KFAR"),
	timeformat = getProperty("timeformat", "h:mm a"),
	masterurl  = "https://github.com/JonahSloan/DashboardGUI/raw/master/";
	
	public static final boolean
	autoupdate = getProperty("autoupdate", true),
	keepawake  = getProperty("keepawake", true),
	benchtest  = getProperty("benchtest", false);
	
	public static final LayoutManager2
	DashboardLayout = getProperty("DashboardLayout", new BorderLayout());
	
	public static final Object
	LayoutClock   = getProperty("LayoutClock", new LayoutConstraint("java.awt.BorderLayout.NORTH")).getObject(),
	LayoutWeather = getProperty("LayoutWeather", new LayoutConstraint("java.awt.BorderLayout.SOUTH")).getObject();
	
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
	private static boolean flag_NoConfigFile = false;
	private static java.util.Properties getConfigData()
	{
		java.util.Properties configs = new java.util.Properties();
		try
		{
			configs.load(new BufferedFileReader(configfile));
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
					configs.load(new BufferedFileReader(configfile));
					return configs;
				}
				// this shouldn't fail twice
				catch (Exception e)
				{
					System.out.println(e);
					new Exception(e);
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
					defname = ((Font)def).getFontName()+"-"+((Font)def).getStyle();
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
				return (T)ColorParser.parse(prop);
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
		else
			return def;
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
