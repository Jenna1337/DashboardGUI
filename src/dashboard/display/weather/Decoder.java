package dashboard.display.weather;

/**
 * Decodes METAR files as per regulations specified by FM 15–XV METAR in the
 * <a href="http://library.wmo.int/opac/index.php?lvl=notice_display&id=13617">
 * Manual on Codes</a><br>
 * Manual on Codes is available online at:
 * <a href="http://library.wmo.int/pmb_ged/wmo_306-v1-1-2015_en.pdf"> 
 * http://library.wmo.int/pmb_ged/wmo_306-v1-1-2015_en.pdf</a>
 * @author jonah.sloan
 */
public class Decoder {
/*
CODE FORM:
                                                               
METAR}                                      {KT }              
or   } COR CCCC YYGGggZ NIL AUTO dddffGfmfm {or } dndndnVdxdxdx
SPECI}                                      {MPS}              
                                                               

                                      {NsNsNshshshs}
                                      {or          }
                                      {VVhshshs    }
{VVVV VNVNVNVNDv RDRDR/VRVRVRVRi w´w´ {or          }
{or                                   {NSC         }
{CAVOK                                {or          }
                                      {NCD         }

                             {WS RDRDR   {(WTsTs/SS´)    } 
T´T´/T´dT´d QPHPHPHPH REw´w´ {or         {or             } (RDRDR/ERCReReRBRBR)
                             {WS ALL RWY {(WTsTs/HHsHsHs)} 

                                            {NsNsNshshshs
                          {KT  {VVVV  {w´w´ {or          
{(TTTTT TTGGgg dddffGfmfm {or  {or    {or   {VVhshshs    
{or                       {MPS {CAVOK {NSW  {or          
{NOSIG)                                     {NSC         

(RMK . . . . . . . . . . )

	 */
/**
<b>CODE FORM</b>:
<pre>                                                               
<b>METAR</b>}                                     {<b>KT </b>}              
or   } <b>COR</b> CCCC YYGGgg<b>Z NIL AUTO</b> dddff<b>G</b>f<sub>m</sub>f<sub>m</sub> {or } d<sub>n</sub>d<sub>n</sub>d<sub>n</sub><b>V</b>d<sub>x</sub>d<sub>x</sub>d<sub>x</sub>
<b>SPECI</b>}                                     {<b>MPS</b>}              
                                                               

                                   {N<sub>s</sub>N<sub>s</sub>N<sub>s</sub>h<sub>s</sub>h<sub>s</sub>h<sub>s</sub>}
                                   {or        }
                                   {<b>VV</b>h<sub>s</sub>h<sub>s</sub>h<sub>s</sub>   }
{VVVV V<sub>N</sub>V<sub>N</sub>V<sub>N</sub>V<sub>N</sub>D<sub>v</sub> <b>R</b>D<sub>R</sub>D<sub>R</sub>/V<sub>R</sub>V<sub>R</sub>V<sub>R</sub>V<sub>R</sub>i w´w´ {or        }
{or                                {<b>NSC</b>       }
{<b>CAVOK</b>                             {or        }
                                   {<b>NCD</b>       }

                           {<b>WS R</b>D<sub>R</sub>D<sub>R</sub>    {(<b>W</b>T<sub>s</sub>T<sub>s</sub>/<b>S</b>S´)    } 
T´T´/T´<sub>d</sub>T´<sub>d</sub> <b>Q</b>P<sub>H</sub>P<sub>H</sub>P<sub>H</sub>P<sub>H</sub> <b>RE</b>w´w´ {or         {or             } (<b>R</b>D<sub>R</sub>D<sub>R</sub>/E<sub>R</sub>C<sub>R</sub>e<sub>R</sub>e<sub>R</sub>B<sub>R</sub>B<sub>R</sub>)
                           {<b>WS ALL RWY</b> {(<b>W</b>T<sub>s</sub>T<sub>s</sub>/<b>H</b>H<sub>s</sub>H<sub>s</sub>H<sub>s</sub>)} 

                                            {N<sub>s</sub>N<sub>s</sub>N<sub>s</sub>h<sub>s</sub>h<sub>s</sub>h<sub>s</sub>
                          {<b>KT</b>  {VVVV  {w´w´ {or          
{(TTTTT TTGGgg dddff<b>G</b>f<sub>m</sub>f<sub>m</sub> {or  {or    {or   {<b>VV</b>h<sub>s</sub>h<sub>s</sub>h<sub>s</sub>    
{or                       {<b>MPS</b> {<b>CAVOK</b> {<b>NSW</b>  {or          
{<b>NOSIG</b>)                                     {<b>NSC</b>         

(<b>RMK</b> . . . . . . . . . . )
</pre>
 */
	String data;
/**Station Name<br>15.2
 * <dl><dt>Group  CCCC</dt>
 * <dd>      The identification of the reporting station in each individual report shall be indicated by means of the ICAO location indicator.
 * </dd></dl>
 */
	String stationName;
/**DateTime<br>15.3
 * <dl><dt>Group  YYGGggZ</dt>
 * <dd>      The day of the month(YY) and the time of observation in hours(GG) and minutes(gg) UTC followed, without a space, by the letter indicator Z shall be included in each individual METAR report.
 * </dd></dl>
 */
	String DateTime;
/**AUTO<br>15.4
 * <dl><dt>Code word AUTO</dt>
 * <dd>      The optional code word AUTO shall be inserted before the wind group when a report contains fully automated observations without human intervention. The ICAO requirement is that all of the specified elements shall be reported. However, if any element cannot be observed, the group in which it would have been encoded shall be replaced by the appropriate number of solidi. The number of solidi depends on the number of symbolic letters for the specific group which is not able to be reported; i.e. four for the visibility group, two for the present weather group and three or six for the cloud group, as appropriate.
 * </dd></dl>
 */
	String AUTO;
/**Wind<br>15.5
 * <dl><dt><pre>                     {KT }
 * Groups   dddffGfmfm {or } dndndnVdxdxdx
 *                     {MPS}</pre></dt>
 * <dd>       The mean true direction in degrees rounded off to the nearest 10 degrees from which the wind is blowing and the mean speed of the wind over the 10-minute period immediately preceding the observation shall be reported for dddff followed, without a space, by one of the abbreviations KT or MPS, to specify the unit used for reporting wind speed.
 *       Values of wind direction less than 100° shall be preceded by 0 and a wind from true north shall be reported as 360.
 *       Values of wind speed less than 10 units shall be preceded by 0.
 *       However, when the 10-minute period includes a marked discontinuity in the wind characteristics, only data after the discontinuity shall be used for obtaining mean wind speed and maximum gust values, and mean wind direction and variations of the wind direction, hence the time interval in these circumstances shall be correspondingly reduced.<br>
 *       <br>
 *       Notes:<br>
 *       (1) KT and MPS are the standard ICAO abbreviations for knots and metres per second, respectively.<br>
 *       (2) The primary unit prescribed in ICAO Annex 5 for wind speed is the metre per second (MPS), with the knot (KT) permitted for use as a non-SI alternative unit until a termination date is decided.<br>
 *       (3) A marked discontinuity occurs when there is an abrupt and sustained change in wind direction of 30° or more, with a wind speed of 5 m s–1 (10 KT) or more before or after the change, or a change in wind speed of 5 m s–1 (10 KT) or more, lasting at least two minutes.<br>
 *       <br>
 *       In the case of variable wind direction, ddd shall be encoded as VRB when the mean wind speed is less than 1.5 m s–1 (3 knots).
 *       A variable wind at higher speeds shall be reported only when the variation of wind direction is 180° or more or when it is impossible to determine a single wind direction, for example when a thunderstorm passes over the aerodrome.<br>
 *       <br>
 *       If, during the 10-minute period preceding the observation, the total variation in wind direction is 60° or more but less than 180 ° and the mean wind speed is 1.5 m s–1  (3 knots) or more, the observed two extreme directions between which the wind has varied shall be given for dndndnVdxdxdx in clockwise order.
 *       Otherwise this group shall not be included.<br>
 *       <br>
 *       “Calm” shall be coded as 00000 followed immediately, without a space, by one of the abbreviations KT or MPS to specify the unit, used normally for reporting wind.<br>
 *       <br>
 *       If, during the 10-minute period preceding the observation, the maximum wind gust speed exceeds the mean speed by 5 m s–1 (10 knots) or more, this maximum speed shall be reported as Gfmfm immediately after dddff, followed immediately, without a space, by one of the abbreviations KT or MPS to specify the units used for reporting wind speed.
 *       Otherwise the element Gfmfm shall not be included.<br>
 *       <br>
 *       Note: It is recommended that the wind measuring systems should be such that peak gusts should represent a three-second average<br>
 *       <br>
 *       For wind speeds of 100 units or greater, the exact number of wind speed units shall  be given in lieu of the two-figure code ff or fmfm.
 *       When the wind speed is 50 m s–1  (100 knots) or more, the groups ff and fmfm shall be preceded by the letter indicator P and reported as P49MPS (P99KT).
 *       </dd></dl>
 */
	String wind;
/**Visibility<br>15.6
 * <dl><dt>Groups  VVVV  VNVNVNVNDv</dt>
 * <dd>Note: The coding of visibility is based on the use of the metre and kilometre, in accordance with the units specified in ICAO Annex 5.<br>
 *  <br>
 * The group VVVV shall be used to report prevailing visibility.
 * When the horizontal visibility is not the same in different directions and when the visibility is fluctuating rapidly and the prevailing visibility cannot be determined, the group VVVV shall be used to report the lowest visibility.<br>
 * <br>
 * <i>Directional variation in visibility</i> VNVNVNVNDv<br>
 * When the horizontal visibility is not the same in different directions and when the minimum visibility is different from the prevailing visibility, and less than 1 500 metres or less than 50% of the prevailing visibility, and less than 5 000 metres, the group VNVNVNVNDv shall also be used to report the minimum visibility and, when possible, its general direction in relation to the aerodrome reference point indicated by reference to one of the eight points of the compass.
 * If the minimum visibility is observed in more than one direction, the Dv shall represent the most operationally significant direction.<br> 
 * <br>
 * Visibility shall be reported using the following reporting steps:<br>
 * (a) Up to 800 metres rounded down to the nearest 50 metres;<br>
 * (b) Between 800 and 5 000 metres rounded down to the nearest 100 metres;<br>
 * (c) Between 5 000 metres up to 9 999 metres rounded down to the nearest  1 000 metres;<br>
 * (d) With 9999 indicating 10 km and above. <br>
 * <br>
 * <i>Code word</i> CAVOK</dt>
 * <dd>
 * Regulation 15.10 shall apply.
 * </dd></dl>
 */
	String CAVOK;
	
	//TODO add rest of regulation info
	
	
	/**
	 * 
	 */
	public Decoder(String data)
	{
		this.data=data;
		// TODO Auto-generated constructor stub
	}
}
