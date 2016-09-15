package tools.decoders;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface AbstractConverter
{
	Charset chst = StandardCharsets.UTF_8;
	public default String encode(byte[] bts)
	{
		return encode(new String(bts,chst));
	}
	public default String decode(byte[] bts)
	{
		return decode(new String(bts,chst));
	}
	public abstract String encode(char[] chs);
	public abstract String decode(char[] chs);
	public default String encode(String str)
	{
		return encode(str.toCharArray());
	}
	public default String decode(String str)
	{
		return decode(str.toCharArray());
	}
}
