package tools.colors;
import java.awt.Color;

public class ColorParser
{
	public static Color parse(String text)
	{
		
		Color nc = NamedColor.getColorForName(text);
		if(nc!=null)
			return nc;
		try
		{
			//Note: Integer.decode(String) throws an error if the String represents a negative integer.
			//For example, Integer.decode("0xFFFFFFFF") will throw an error.
			return new NamedColor(null, new Color(Long.decode(text).intValue(), true));
		}
		catch(NumberFormatException nfe)
		{
			throw new IllegalArgumentException("\""+text+"\" is not a valid color.");
		}
	}
}
