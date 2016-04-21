package dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager2;
import java.io.PrintStream;

public final class CommonConsts
{
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
	aupdateint = getProperty("aupdateint", WEEK);
	
	public static final Font
	FontClock   = getProperty("FontClock",   Font.decode("Courier New")),
	FontWeather = getProperty("FontWeather", Font.decode("Times New Roman"));
	
	public static final Color
	/**Color of main window**/
	COLORbg  = getProperty("COLORbg ", Color.BLACK),
	/**Color of Clock text**/
	COLORfgC = getProperty("COLORfgC", Color.LIGHT_GRAY),
	/**Color of Clock bg**/
	COLORbgC = getProperty("COLORbgC", Color.BLACK),
	/**Color of Weather text**/
	COLORfgW = getProperty("COLORfgW", Color.LIGHT_GRAY),
	/**Color of Weather bg  **/
	COLORbgW = getProperty("COLORbgW", Color.BLACK),
	/**Color of hidden areas**/
	COLORNUL = Color.MAGENTA;
	
	public static final String
	timeserver = getProperty("timeserver", "time.nist.gov"),
	weatherdir = getProperty("weatherdir", "ftp://tgftp.nws.noaa.gov/data/observations/metar/decoded/"),
	wstation   = getProperty("wstation",   "KFAR"),
	timeformat = getProperty("timeformat", "h:mm a"),
	masterurl  = "https://github.com/JonahSloan/DashboardGUI/raw/master/",
	myfilepath = getFilePath(),
	configfile = ".DashboardGui.properties";//TODO have the "DashboardGui" part be whatever the local file name is
	
	public static final boolean
	autoupdate = getProperty("autoupdate", true),
	keepawake  = getProperty("keepawake", true),
	writeconfig= true;//is this really needed?
	
	public static final LayoutManager2
	DashboardLayout = getProperty("DashboardLayout", new BorderLayout());
	
	public static final Object
	LayoutClock   = getProperty("LayoutClock", new ClassField("java.awt.BorderLayout.NORTH")),
	LayoutWeather = getProperty("LayoutWeather", new ClassField("java.awt.BorderLayout.SOUTH"));
	
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
			configs.load(new java.io.BufferedReader(new java.io.FileReader(configfile)));
			return configs;
		}
		catch(java.io.FileNotFoundException fnfe)
		{
			flag_NoConfigFile=true;
			if(writeconfig)
			{
				try
				{
					log.print("Failed to locate config file. Creating a new one.");
					configs.load(new java.io.BufferedReader(new java.io.FileReader(configfile)));
					return configs;
				}
				// this shouldn't fail twice
				catch (Exception e)
				{
					new Exception(e);
				}
			}
		}
		catch(Exception e)
		{
			log.println("Failed to load configuration file "+configfile+".\n");
		}
		return configs;
	}
	/**Reads the value of specified property from the config file. <br>
	 * 
	 * @param <T> - The type of the Object being returned as specified by {@code def}.
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
					java.io.BufferedReader configreader = new java.io.BufferedReader(new java.io.FileReader(configfile));
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
				if(def.getClass().equals(Color.class))
					defname = ""+((Color)def).getRGB();
				if(def.getClass().equals(Font.class))
					defname = ((Font)def).getFontName()+"-"+((Font)def).getStyle();
				if(LayoutManager2.class.isAssignableFrom(def.getClass()))
					defname = def.getClass().getName();
				java.io.BufferedWriter configwriter = new java.io.BufferedWriter(new java.io.FileWriter(configfile));
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
			 * ClassField
			 * LayoutManager2
			 */
			if(def.getClass().equals(String.class))
				return (T)prop;
			if(def.getClass().equals(Color.class))
			{
				try
				{
					return (T)Color.decode(prop);
				}
				catch(NumberFormatException nfe)
				{
					return (T)Color.getColor(prop, ((Color)def).getRGB());
				}
			}
			if(def.getClass().equals(Font.class))
				return (T)Font.getFont(prop, (Font)def);
			if(def.getClass().equals(Boolean.class))
				return (T)new Boolean(prop);
			if(def.getClass().equals(Long.class))
				return (T)new Long(prop);
			if(def.getClass().equals(ClassField.class))
			{
				try
				{
					return (T)new ClassField(prop).getObject();
				}
				catch (Exception e)
				{
					try
					{
						return (T)((ClassField)def).getObject();
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
}
/**Masking type for layout constraints**/
class ClassField
{
	String data;
	public ClassField(String data)
	{
		this.data=data;
	}
	public String toString()
	{
		return data.toString();
	}
	public Object getObject() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException
	{
		return Class.forName(data.toString().substring(0, data.toString().lastIndexOf("."))).getField(data.toString().substring(data.toString().lastIndexOf(".")+1)).get(new Object());
	}
}
