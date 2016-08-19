package dashboard;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import tools.colors.ColorInts;
import tools.colors.NamedColor;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		/*String fs=System.getProperty("file.separator");
		PrintStream stream = new PrintStream("src"+fs+"tools"+fs+"colors"+fs+"ColorAliases.lst");
		for(int i=0; i<0xFFFFFFFF; ++i)
		{
			stream.print("x"+Integer.toUnsignedString(i, 16)+" = ");
			
			stream.println();
		}
		stream.close();
		
		System.exit(0);/**/
		
		ArrayList<NamedColor> colors = new ArrayList<NamedColor>();
		for(java.lang.reflect.Field f : ColorInts.class.getDeclaredFields())
			colors.add(new NamedColor(f.getName(), new Color((int)f.get(null), true)));
		printcolors2(colors, System.out);
		
		//*updateNamedColorConstants();
		System.exit(0);/**/
		//printcolorenum();System.exit(0);
		Dashboard dash = new Dashboard();
		dash.setVisible(true);
		/*
		Thread.sleep(10000);
		dash.destroy();
		System.gc();
		/**/
		Thread.sleep(1);
		if(CommonConsts.benchtest)
			benchmark();
	}
	@Deprecated
	@SuppressWarnings("unused")
	private static void updateNamedColorConstants() throws FileNotFoundException
	{
		String fs=System.getProperty("file.separator");
		PrintStream stream = new PrintStream("src"+fs+"tools"+fs+"colors"+fs+"NamedColorConstants.java");
		printcolors(stream);
		stream.close();
	}
	@Deprecated
	private static void printcolors(PrintStream stream)
	{
		ArrayList<NamedColor> colors=new ArrayList<NamedColor>();
		colors.addAll(getNamedColors(javafx.scene.paint.Color.class));
		colors.addAll(getNamedColors(java.awt.Color.class));
		colors.addAll(getNamedColors(com.sun.prism.paint.Color.class));
		colors.trimToSize();
		colors = NamedColor.sortByNameAcending(colors);
		printcolors(colors, stream);
	}
	@Deprecated
	private static void printcolors(ArrayList<NamedColor> colors, PrintStream stream)
	{
		ArrayList<String> printed=new ArrayList<String>();
		ArrayList<String> printed2=new ArrayList<String>();
		final String fstr = "\t/**A color with an ARGB value of %3$s" + 
				"<div style=\"border:1px solid black;width:40px;height:20px;background-color:%1$s;float:right;margin: 0 10px 0 0\">"+
				"</div><br/><br/>*/%n\t@Deprecated public final static NamedColor %2$s= new NamedColor(\"%2$s\", new java.awt.Color(%3$s, true));%n";
		String ls=System.lineSeparator();
		DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		dateformat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
		String date = dateformat.format(java.util.Calendar.getInstance().getTime());
		stream.println("package tools.colors;"+ls+ls+"@javax.annotation.Generated(value = {\"dashboard.Main\"}, date = \""
				+ date + "\")"+ls+"@Deprecated public final class NamedColorConstants"+ls+"{"+ls+"\tprivate NamedColorConstants(){}");
		for(NamedColor c : colors)
		{
			String name=c.getName().toUpperCase();
			String val ="0x"+Integer.toUnsignedString(NamedColor.getARGB(c), 16);
			String wval =val.replaceFirst("0xff", "#");
			if(name.equalsIgnoreCase("transparent"))
			{
				val =val.replaceFirst("0xff", "0x00");
				wval = "rgba(0,0,0,0.0)";
			}
			
			if(printed2.contains(name) && !printed.contains(val))
				name=name+"2";
			if(!printed2.contains(name))
			{
				printed2.add(name);
				printed.add(val);
				stream.printf(fstr, wval, name, val);
				name=name.toLowerCase();
				stream.printf(fstr, wval, name, val);
				printed.sort(new Comparator<String>()
				{
					public int compare(String o1, String o2)
					{
						return o1.compareTo(o2);
					}
				});
			}
		}
		stream.println("}");
	}
	@Deprecated
	private static void printcolors2(ArrayList<NamedColor> colors, PrintStream stream)
	{
		ArrayList<String> printed=new ArrayList<String>();
		ArrayList<String> printed2=new ArrayList<String>();
		final String fstr = "\t@Deprecated/**A color with an ARGB value of %3$s" + 
				"<div style=\"border:1px solid black;width:40px;height:20px;background-color:%1$s;float:right;margin: 0 10px 0 0\">"+
				"</div><br/><br/>*/%n\tpublic final static NamedColor %2$s%4$s = new NamedColor(\"%2$s\",%4$s new java.awt.Color(%3$s, true));%n";
		String ls=System.lineSeparator();
		DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		dateformat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
		String date = dateformat.format(java.util.Calendar.getInstance().getTime());
		stream.println("package tools.colors;"+ls+ls+"@javax.annotation.Generated(value = {\"dashboard.Main\"}, date = \""
				+ date + "\")"+ls+"@Deprecated public final class NamedColorConstants"+ls+"{"+ls+"\tprivate NamedColorConstants(){}");
		int mlen=0, malphlen=0;
		for(NamedColor c : colors)
		{
			mlen = Math.max(mlen, c.getName().length());
			malphlen = Math.max(malphlen, (c.getAlpha()/255.0+"").length());
		}
		for(NamedColor c : colors)
		{
			String padding="";
			while(c.getName().length()+padding.length()<mlen)
				padding+=" ";
			String name=c.getName();
			String val =Integer.toUnsignedString(NamedColor.getARGB(c), 16);
			while(val.length()<8)
				val="0"+val;
			val="0x"+val;
			
			String rstr=""+c.getRed(), gstr=""+c.getGreen(), bstr=""+c.getBlue(), astr=""+c.getAlpha()/255.0;
			while(rstr.length()<3)
				rstr="0"+rstr;
			while(gstr.length()<3)
				gstr="0"+gstr;
			while(bstr.length()<3)
				bstr="0"+bstr;
			while(astr.length()<malphlen)
				astr+="0";
			
			String wval = "rgba("+rstr+","+gstr+","+bstr+","+astr+")";//TODO
			
			if(printed2.contains(name.toUpperCase()) && !printed.contains(val))
				name=name+"2";
			if(!printed2.contains(name.toUpperCase()))
			{
				printed2.add(name.toUpperCase());
				printed.add(val);
				stream.printf(fstr, wval, name, val, padding);
				/*
				name=name.toUpperCase();
				stream.printf(fstr, wval, name, val, padding);
				name=name.toLowerCase();
				stream.printf(fstr, wval, name, val, padding);/**/
			}
		}
		stream.println("}");
	}
	@Deprecated
	private static <T extends Object> ArrayList<NamedColor> getNamedColors(Class<T> clazz)
	{
		ArrayList<NamedColor> arr = new java.util.ArrayList<NamedColor>();
		for(java.lang.reflect.Field f : clazz.getDeclaredFields())
		{
			if(clazz.equals(f.getType()))
			{
				try{
					T color=clazz.cast(f.get(null));
					int argb;
					switch(clazz.getName())
					{
						case "java.awt.Color":
							argb=NamedColor.getARGB((java.awt.Color)color);
							break;
						case "javafx.scene.paint.Color":
							argb=NamedColor.getARGB((javafx.scene.paint.Color)color);
							break;
						case "com.sun.prism.paint.Color":
							argb=NamedColor.getARGB((com.sun.prism.paint.Color)color);
							break;
						default:
							argb=0;
							break;
					}
					arr.add(new NamedColor(f.getName(), new java.awt.Color(argb)));
				}catch(Exception e){}
			}
		}
		return arr;
	}
	private static void benchmark() throws Exception
	{
		//TO-DO write the file if it doesn't exist
		//or just ignore the errors
		java.io.File f=new java.io.File("benchmarktest.txt");
		/*if(!f.exists())
		{
			System.err.println("Can't find benchmark file");
		}
		if(f.canWrite())/**/
		{
			@SuppressWarnings("resource")
			final java.io.BufferedWriter writer=new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(f)));
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
				public void run(){
					try
					{
						writer.close();
					}
					catch (Exception e)
					{
					}
				}
			}));
			while(Thread.activeCount()>1)//This should be the only thread at the end
			{
				//System.out.println("Free: "+Runtime.getRuntime().freeMemory());
				//System.out.println("Total:"+Runtime.getRuntime().totalMemory());
				try
				{
					long usedmemory = java.lang.management.ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
					writer.write(""+usedmemory+"\n");
					writer.flush();
				}
				catch(java.io.IOException ioe)
				{
					
				}
				Thread.sleep(CommonConsts.benchint);
			}
		}
	}
}
