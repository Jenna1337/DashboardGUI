package tools.colors;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NamedColor extends Color
{
	private static final ArrayList<NamedColor> colors = getColors();
	/**
	 * Gets all of the Color fields from NamedColor
	 */
	private static ArrayList<NamedColor> getColors()
	{
		Field[] fields = NamedColorConstants.class.getDeclaredFields();
		ArrayList<NamedColor> arr = new java.util.ArrayList<NamedColor>();
		for(Field f : fields)
		{
			if(NamedColor.class.equals(f.getType()))
			{
				try{
					arr.add(new NamedColor(f.getName(), (NamedColor)f.get(null)));
				}catch(Exception e){}
			}
		}
		return arr;
	}
	private final String name;
	public NamedColor(String name, Color color)
	{
		super(getARGB(color), true);
		this.name=name;
	}
	public static int getARGB(java.awt.Color c)
	{
		return (c.getAlpha()<<24) | (c.getRed()<<16) | (c.getGreen()<<8) | (c.getBlue()<<0); 
	}
	public static int getARGB(javafx.scene.paint.Color c)
	{
		return getARGB(new Color((float)c.getRed(), (float)c.getGreen(), (float)c.getBlue(), (float)c.getOpacity())); 
	}
	@SuppressWarnings("unused")
	private static Color blend(Color... colorlist)
	{
		final boolean bitmask = true;
		long sum1=0,sum2=0;
		int alpha=0;
		int size=colorlist.length;
		if(size<=0)
			return null;
		for (Color var : colorlist)
		{
			int color=getARGB(var);
			alpha+=(color<<24);
			if(var.getAlpha()>0)
			{
				sum1+= color    &0x00FF00FF;
				sum2+=(color>>8)&0x00FF00FF;
			}
			if(var.getAlpha()==0)
				size-=1;
		}
		alpha=bitmask ? ((alpha==0)?0:0xFF) : (alpha/size);
		int value=0;
		try{
			value|=(((sum1&0xFFFF)/(size))&0xFF);}catch(ArithmeticException ae){}try{
				value|=(((sum2&0xFFFF)/(size))&0xFF)<<8;}catch(ArithmeticException ae){}try{
					value|=((((sum1>>=16)&0xFFFF)/(size))&0xFF)<<16;}catch(ArithmeticException ae){}
				return new Color(value|=alpha<<24, true);
	}
	public static Color getColorForName(String name)
	{
		for(NamedColor color : colors)
			if(color.name.equalsIgnoreCase(name))
				return color;
		return null;
	}
	public String getName()
	{
		return this.name;
	}
	@Override
	public String toString()
	{
		return getClass().getName()+"[name=\""+getName()+"\",a="+getAlpha()+",r="+getRed()+",g="+getGreen()+",b="+getBlue()+"]";
	}
}
