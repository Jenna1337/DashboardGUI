package dashboard;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import tools.colors.NamedColor;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		/*updateNamedColorConstants();
		System.exit(0);/**/
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
	@SuppressWarnings("unused")
	@Deprecated
	private static void printcolorenum()
	{
		System.out.print("enum Colors{\n\t");
		ArrayList<NamedColor> arr = new java.util.ArrayList<NamedColor>();
		for(java.lang.reflect.Field f : NamedColor.class.getDeclaredFields())
		{
			if(NamedColor.class.equals(f.getType()))
			{
				try{
					arr.add(new NamedColor(f.getName(), (NamedColor)f.get(null)));
				}catch(Exception e){}
			}
		}
		for(NamedColor c:arr)
			System.out.print(c.getName()+"(NamedColor."+c.getName()+"), ");
		System.out.println("NULL(null);");
		System.out.println("\tNamedColor clr;\n\tColors(NamedColor c)\n\t{\n\t\tclr=c;\n\t}");
		System.out.println("} //enum Colors");
	}
	@SuppressWarnings("unused")
	private static void updateNamedColorConstants() throws FileNotFoundException
	{
		String fs=System.getProperty("file.separator");
		PrintStream stream = new PrintStream("src"+fs+"tools"+fs+"colors"+fs+"NamedColorConstants.java");
		printcolors(stream);
		stream.close();
	}
	private static void printcolors(PrintStream stream)
	{
		ArrayList<NamedColor> colors=new ArrayList<NamedColor>();
		colors.addAll(getNamedColors(javafx.scene.paint.Color.class));
		colors.addAll(getNamedColors(java.awt.Color.class));
		colors.sort(new Comparator<NamedColor>()
		{
			public int compare(NamedColor o1, NamedColor o2)
			{
				int c1=o1.getName().compareToIgnoreCase(o2.getName());
				return c1!=0?c1:((Integer)o1.getRGB()).compareTo(o2.getRGB());
			}
		});
		ArrayList<String> printed=new ArrayList<String>();
		ArrayList<String> printed2=new ArrayList<String>();
		final String fstr = "\t/**A color with an ARGB value of %3$s" + 
				"<div style=\"border:1px solid black;width:40px;height:20px;background-color:%1$s;float:right;margin: 0 10px 0 0\">"+
				"</div><br/><br/>*/%n\tpublic final static NamedColor %2$s= new NamedColor(\"%2$s\", new java.awt.Color(%3$s, true));%n";
		String ls=System.lineSeparator();
		DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		dateformat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
		String date = dateformat.format(java.util.Calendar.getInstance().getTime());
		stream.println("package tools.colors;"+ls+ls+"@javax.annotation.Generated(value = {\"dashboard.Main\"}, date = \""
				+ date + "\")"+ls+"public final class NamedColorConstants"+ls+"{"+ls+"\tprivate NamedColorConstants(){}");
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
