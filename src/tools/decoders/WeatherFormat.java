package tools.decoders;

/**
 * A class for METAR data.
 * @author jonah.sloan
 * @author jenna3715
 *
 */
public abstract class WeatherFormat
{
	//TODO code this
/**
 * https://www.weather.gov/media/okx/Aviation/TAF_Card.pdf
 */
		String data;

		/**Message type: TAF-routine or TAF AMD-amended forecast, METAR-hourly, SPECI-special or TESTM-non-commissioned ASOS report*/
		String msgType;//TAF		METAR
		/**ICAO location indicator*/
		String location;
		/**Issuance time: ALL times in UTC “Z”, 2-digit date, 4-digit time*/
		String time;//091730Z		091955Z
		/**Valid period, either 24 hours or 30 hours. The first two digits of EACH four digit number indicate the date of the valid period, the final two digits indicate the time (valid from 18Z on the 9th to 24Z on the 10th).*/
		String period;//0918/1024		
		/**In U.S. METAR: CORrected of; or AUTOmated ob for automated report with no human intervention; omitted when observer logs on.*/
		//		COR
		/**Wind: 3 digit true-north direction , nearest 10 degrees (or VaRiaBle); next 2-3 digits for speed and unit, KT (KMH or MPS); as needed, Gust and maximum speed; 00000KT for calm; for METAR, if direction varies 60 degrees or more, Variability appended, e.g., 180V260*/
		String wind;//15005KT		22015G25KT
		/**Prevailing visibility; in U.S., Statute Miles & fractions; above 6 miles in TAF Plus6SM. (Or, 4-digit minimum visibility in meters and as required, lowest value with direction)*/
		String visibility;//5SM		3/4SM
		/**Runway Visual Range: R; 2-digit runway designator Left, Center, or Right as needed; “/”, Minus or Plus in U.S., 4-digit value, FeeT in U.S., (usually meters elsewhere); 4-digit value Variability 4-digit value (and tendency Down, Up or No change)*/
		String runwayVisibility;//		R28L/2600FT
		/**Significant present, forecast and recent weather: see table (on back)*/
		String weather;////HZ		TSRA
		/**Cloud amount, height and type: SKy Clear 0/8, FEW >0/8-2/8, SCaTtered 3/8-4/8, BroKeN 5/8-7/8, OVerCast 8/8; 3-digit height in hundreds of ft; Towering CUmulus or CumulonimBus in METAR; in TAF, only CB. Vertical Visibility for obscured sky and height “VV004”. More than 1 layer may be reported or forecast. In automated METAR reports only, CLeaR for “clear below 12,000 feet”*/
		String clouds;//FEW020		OVC 010CB
		/**Temperature: degrees Celsius; first 2 digits, temperature “/” last 2 digits, dew-point temperature; Minus for below zero, e.g., M06*/
		int tempC;//		18/16
		/**Altimeter setting: indicator and 4 digits; in U.S., A-inches and hundredths; (Q-hectoPascals, e.g., Q1013)*/
		String altitude;//		A2992
		/**In U.S. TAF, non-convective low-level (≤2,000 ft) Wind Shear; 3-digit height (hundreds of ft); “/”; 3-digit wind direction and 2-3 digit wind speed above the indicated height, and unit, KT*/
		String windShear;//WS010/31022KT		
		/**In METAR, ReMarK indicator & remarks. For example: Sea- Level Pressure in hectoPascals & tenths, as shown: 1004.5 hPa; Temp/dew-point in tenths _C, as shown: temp. 18.2_C, dew-point 15.9_C*/
		String remarks;//		RMK SLP045 T01820159
		/**FroM: changes are expected at: 2-digit date, 2-digit hour, and 2-digit minute beginning time: indicates significant change. Each FM starts on a new line, indented 5 spaces*/
		String from;//FM091930		
		/**TEMPOrary: changes expected for <1 hour and in total, < half of the period between the 2-digit date and 2-digit hour beginning, and 2-digit date and 2-digit hour ending time*/
		String temporary;//TEMPO 0920/0922		
		/**PROBability and 2-digit percent (30 or 40): probable condition in the period between the 2-digit date & 2-digit hour beginning time, and the 2-digit date and 2-digit hour ending time*/
		String prob;//PROB30 1004/1007		
		/**BECoMinG: change expected in the period between the 2-digit date and 2-digit hour beginning time, and the 2-digit date and 2-digit hour ending time*/
		String becoming;//BECMG 1013/1015		
		
		static final class Weather
		{
			class Qualifiers{
				public static final String Light = "-";
				public static final String Moderate = "";
				public static final String Heavy = "+";
				public static final String Vicinity = "VC";
			}
			public static final String Patches = "BC";
			public static final String Blowing = "BL";
			public static final String Drifting = "DR";
			public static final String Freezing = "FZ";
			public static final String Shallow = "MI";
			public static final String Partial = "PR";
			public static final String Showers = "SH";
			public static final String Thunderstorm = "TS";
			public static final String Drizzle = "DZ";
			public static final String Hail = "GR";
			public static final String SmallHail_SnowPellets = "GS";
			public static final String IceCrystals = "IC";
			public static final String IcePellets = "PL";
			public static final String Rain = "RA";
			public static final String SnowGrains = "SG";
			public static final String Snow = "SN";
			/**(in automated observations)*/
			public static final String UnknownPrecipitation = "UP";
			/**(≥5/8SM)*/
			public static final String Mist = "BR";
			public static final String WidespreadDust = "DU";
			/**(<5/8SM)*/
			public static final String Fog = "FG";
			public static final String Smoke = "FU";
			public static final String Haze = "HZ";
			public static final String Spray = "PY";
			public static final String Sand = "SA";
			public static final String VolcanicAsh = "VA";
			public static final String DustStorm = "DS";
			public static final String FunnelCloud = "FC";
			public static final String Tornado_Waterspout = "+FC";
			public static final String WellDevelopedDust_SandWhirls = "PO";
			public static final String Squall = "SQ";
			public static final String Sandstorm = "SS";
		} 
}
