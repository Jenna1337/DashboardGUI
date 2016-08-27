package tools.decoders;

/**
 * Decodes METAR files as per regulations specified by FM 15–XV METAR in the
 * <a href="http://library.wmo.int/opac/index.php?lvl=notice_display&id=13617">
 * Manual on Codes</a><br>
 * Manual on Codes is available online at:
 * <a href="http://library.wmo.int/pmb_ged/wmo_306-v1-1-2015_en.pdf"> 
 * http://library.wmo.int/pmb_ged/wmo_306-v1-1-2015_en.pdf</a>
 * @author jonah.sloan
 */
public class MetarDecoder extends MetarFormat
{
	/**
	 * 
	 */
	public MetarDecoder(String data)
	{
		this.data=data;
		// TODO Auto-generated constructor stub
	}
}
