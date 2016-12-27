package tools.decoders;

public abstract class MetarFormat
{
/**<pre>
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
</pre>*/
		String data;

/**<pre>General 
</pre>*/
Object r_15_1;

/**<pre>The code name METAR or SPECI shall be included at the beginning of each individual report. 
</pre>*/
Object r_15_1_1;

/**<pre>When a deterioration of one weather element is accompanied by an improvement in another element (for example, lowering of clouds and an improvement in visibility), a single SPECI report shall be issued. 
</pre>*/
Object r_15_1_2;

/**<pre>Group  CCCC
     The identification of the reporting station in each individual report shall be indicated by means of the ICAO location indicator. 
</pre>*/
Object r_15_2;

/**<pre>Group  YYGGggZ 
</pre>*/
Object r_15_3;

/**<pre>The day of the month and the time of observation in hours and minutes UTC followed, without a space, by the letter indicator Z shall be included in each individual METAR report. 
</pre>*/
Object r_15_3_1;

/**<pre>This group shall be included in each individual SPECI report. In SPECI reports, this group shall indicate the time of occurrence of the change(s) which justified the issue of the report. 
</pre>*/
Object r_15_3_2;

/**<pre>Code word AUTO
     The optional code word AUTO shall be inserted before the wind group when a report contains fully automated observations without human intervention. The ICAO requirement is that all of the specified elements shall be reported. However, if any element cannot be observed, the group in which it would have been encoded shall be replaced by the appropriate number of solidi. The number of solidi depends on the number of symbolic letters for the specific group which is not able to be reported; i.e. four for the visibility group, two for the present weather group and three or six for the cloud group, as appropriate.  
                         {KT }
</pre>*/
Object r_15_4;

/**<pre>Groups   dddffGfmfm {or } dndndnVdxdxdx
                         {MPS} 
</pre>*/
Object r_15_5;

/**<pre>The mean true direction in degrees rounded off to the nearest 10 degrees from which the wind is blowing and the mean speed of the wind over the 10-minute period immediately preceding the observation shall be reported for dddff followed, without a space, by one of the abbreviations KT or MPS, to specify the unit used for reporting wind speed. Values of wind direction less than 100° shall be preceded by 0 and a wind from true north shall be reported as 360. Values of wind speed less than 10 units shall be preceded by 0. However, when the 10-minute period includes a marked discontinuity in the wind characteristics, only data after the discontinuity shall be used for obtaining mean wind speed and maximum gust values, and mean wind direction and variations of the wind direction, hence the time interval in these circumstances shall be correspondingly reduced.
      Notes: 
      (1) KT and MPS are the standard ICAO abbreviations for knots and metres per second, respectively. 
      (2) The primary unit prescribed in ICAO Annex 5 for wind speed is the metre per second (MPS), with the knot (KT) permitted for use as a non-SI alternative unit until a termination date is decided. 
      (3) A marked discontinuity occurs when there is an abrupt and sustained change in wind direction of 30° or more, with a wind speed of 5 m s–1 (10 KT) or more before or after the change, or a change in wind speed of 5 m s–1 (10 KT) or more, lasting at least two minutes. 
</pre>*/
Object r_15_5_1;

/**<pre>In the case of variable wind direction, ddd shall be encoded as VRB when the mean wind speed is less than 1.5 m s–1 (3 knots). A variable wind at higher speeds shall be reported only when the variation of wind direction is 180° or more or when it is impossible to determine a single wind direction, for example when a thunderstorm passes over the aerodrome. 
</pre>*/
Object r_15_5_2;

/**<pre>If, during the 10-minute period preceding the observation, the total variation in wind direction is 60° or more but less than 180 ° and the mean wind speed is 1.5 m s–1  (3 knots) or more, the observed two extreme directions between which the wind has varied shall be given for dndndnVdxdxdx in clockwise order. Otherwise this group shall not be included. 
</pre>*/
Object r_15_5_3;

/**<pre>“Calm” shall be coded as 00000 followed immediately, without a space, by one of the abbreviations KT or MPS to specify the unit, used normally for reporting wind. 
</pre>*/
Object r_15_5_4;

/**<pre>If, during the 10-minute period preceding the observation, the maximum wind gust speed exceeds the mean speed by 5 m s–1 (10 knots) or more, this maximum speed shall be reported as Gfmfm immediately after dddff, followed immediately, without a space, by one of the abbreviations KT or MPS to specify the units used for reporting wind speed.  Otherwise the element Gfmfm shall not be included.
       Note: It is recommended that the wind measuring systems should be such that peak gusts should represent a three-second average. 
</pre>*/
Object r_15_5_5;

/**<pre>For wind speeds of 100 units or greater, the exact number of wind speed units shall  be given in lieu of the two-figure code ff or fmfm. When the wind speed is 50 m s–1  (100 knots) or more, the groups ff and fmfm shall be preceded by the letter indicator P and reported as P49MPS (P99KT).
       Note: There is no aeronautical requirement to report surface wind speeds of 50 m s–1 (100 KT) or more; however, provision has been made for reporting wind speeds up to 99 m s–1 (199 KT) for nonaeronautical purposes, as necessary. 
</pre>*/
Object r_15_5_6;

/**<pre>Groups  VVVV  VNVNVNVNDv
       Note: The coding of visibility is based on the use of the metre and kilometre, in accordance with the units specified in ICAO Annex 5. 
</pre>*/
Object r_15_6;

/**<pre>The group VVVV shall be used to report prevailing visibility. When the horizontal visibility is not the same in different directions and when the visibility is fluctuating rapidly and the prevailing visibility cannot be determined, the group VVVV shall be used to report the lowest visibility.  
</pre>*/
Object r_15_6_1;

/**<pre>Directional variation in visibility VNVNVNVNDv
       When the horizontal visibility is not the same in different directions and when the minimum visibility is different from the prevailing visibility, and less than 1 500 metres or less than 50% of the prevailing visibility, and less than 5 000 metres, the group VNVNVNVNDv shall also be used to report the minimum visibility and, when possible, its general direction in relation to the aerodrome reference point indicated by reference to one of the eight points of the compass. If the minimum visibility is observed in more than one direction, the Dv shall represent the most operationally significant direction. 
</pre>*/
Object r_15_6_2;

/**<pre>Visibility shall be reported using the following reporting steps: 
       (a) Up to 800 metres rounded down to the nearest 50 metres; 
       (b) Between 800 and 5 000 metres rounded down to the nearest 100 metres; 
       (c) Between 5 000 metres up to 9 999 metres rounded down to the nearest  1 000 metres; 
       (d) With 9999 indicating 10 km and above. 
</pre>*/
Object r_15_6_3;

/**<pre>Code word CAVOK
       Regulation 15_10 shall apply. 
</pre>*/
Object r_15_6_4;

/**<pre>Group  RDRDR/VRVRVRVRi
     Note: The coding of runway visual range is based on the use of the metre in accordance with the unit specified in ICAO Annex 5. 
</pre>*/
Object r_15_7;

/**<pre>During periods when either the horizontal visibility reported in the group VVVV or the runway visual range for one or more runways available for landing is observed to be less than 1 500 metres, one or more groups under Regulation 15_7 shall be included in the report. The letter indicator R followed immediately, without a space, by the runway designator DRDR shall always precede the RVR reports. 
</pre>*/
Object r_15_7_1;

/**<pre>The groups shall be repeated to report runway visual range values for each runway, up to a maximum of four, which is available for landing and for which runway visual range is determined. 
</pre>*/
Object r_15_7_2;

/**<pre>Runway designator  DRDR
       The designator of each runway for which runway visual range is reported shall be indicated by DRDR. Parallel runways should be distinguished by appending to DRDR letters L, C or R indicating the left, central or right parallel runway, respectively. The letter(s) shall be appended to DRDR as necessary in accordance with the standard practice for runway designation, as laid down by ICAO in Annex 14 – Aerodromes, Volume I – Aerodrome design and operations, paragraphs 5_2_2_4 and 5_2_2_5. 
</pre>*/
Object r_15_7_3;

/**<pre>Mean value and tendency of runway visual range over the 10-minute period immediately preceding the observation  VRVRVRVRi 
</pre>*/
Object r_15_7_4;

/**<pre>The runway visual range values to be reported shall be representative of the touchdown zone of the active landing runway(s) up to a maximum of four. 
</pre>*/
Object r_15_7_4_1;

/**<pre>The mean value of the runway visual range over the 10-minute period immediately preceding the observation shall be reported for VRVRVRVR. However, when the 10-minute period includes a marked discontinuity in the RVR (for example, sudden advection of fog, rapid onset or cessation of an obscuring snow shower), only data after the discontinuity shall be used for obtaining mean RVR values, hence the time interval in these circumstances shall be correspondingly reduced.
         Notes: 
         (1) The extreme values of the runway visual range are indicated in accordance with Regulation 15_7_5 and the trend is indicated in accordance with Regulation 15_7_4_3. 
         (2) Any observed value which does not fit the reporting scale in use should be rounded down to the nearest lower step in the scale. 
         (3) A marked discontinuity occurs when there is an abrupt and sustained change in runway visual range, lasting at least two minutes and during which it reaches or passes 800, 550, 300 and 175 m.
</pre>*/
Object r_15_7_4_2;

/**<pre>If the runway visual range values during the 10-minute period preceding the observation show a distinct upward or downward tendency such that the mean during the first five minutes varies by 100 metres or more from the mean during the second five minutes of the period, this shall be indicated by i = U for upward and i = D for downward tendency of runway visual range values. When no distinct change in runway visual range is observed, i = N shall be used. When it is not possible to determine the tendency, i shall be omitted. 
</pre>*/
Object r_15_7_4_3;

/**<pre>Extreme values of runway visual range When actual RVR values are outside the measuring range of the observing system in use, the following procedure shall apply: 
       (a) When the RVR, to be reported in accordance with the Technical Regulations, is greater than the maximum value which can be assessed with the system in use, the group VRVRVRVR shall be preceded by the letter indicator P (PVRVRVRVR) in which VRVRVRVR is the highest value which can be assessed. When the RVR is assessed to be more than 2 000 metres, it shall be reported as P2000; 
       (b) When the RVR is below the minimum value which can be assessed with the system in use, the group VRVRVRVR shall be preceded by the letter indicator M (MVRVRVRVR) in which VRVRVRVR is the lowest value which can be assessed. When the RVR is assessed to be less than 50 metres, it shall be reported as M0050. 
</pre>*/
Object r_15_7_5;

/**<pre>Group  w´w´ 
</pre>*/
Object r_15_8;

/**<pre>One or more groups w´w´, but not more than three, shall be used to report all present weather phenomena observed at or near the aerodrome and of significance to aeronautical operations in accordance with Code table 4678.
       Appropriate intensity indicators and letter abbreviations (Code table 4678) shall be combined in groups of two to nine characters to indicate present weather phenomena. 
</pre>*/
Object r_15_8_1;

/**<pre>If the observed present weather cannot be reported by use of Code table 4678, the group w´w´ shall be omitted from the report. 
</pre>*/
Object r_15_8_2;

/**<pre>The w´w´ groups shall be ordered as follows: 
       (a) First, if appropriate, the qualifier for intensity or for proximity, followed without a space by; 
       (b) If appropriate, the abbreviation for the descriptor followed without a space by; 
       (c) The abbreviation for the observed weather phenomenon or combinations thereof. 
</pre>*/
Object r_15_8_3;

/**<pre>Intensity shall be indicated only with precipitation, precipitation associated with showers and/or thunderstorms, funnel cloud, duststorm or sandstorm. If the intensity of the phenomena reported in the group is either light or heavy, this shall be indicated by the appropriate sign (see Code table 4678 and specially Note (5)). No indicator shall be included in the group when the intensity of the reported phenomenon is moderate. 
</pre>*/
Object r_15_8_4;

/**<pre>The intensity of present weather phenomena reported in the group w´w´ shall be determined by the intensity at the time of observation. 
</pre>*/
Object r_15_8_5;

/**<pre>If more than one significant weather phenomenon is observed, separate w´w´ groups shall be included in the report in accordance with Code table 4678. However, if more than one form of precipitation is observed, the appropriate letter abbreviations shall be combined in a single group with the dominant type of precipitation being reported first. In such a single group, the intensity shall refer to the total precipitation and be reported with one or no indicator as appropriate. 
       When an automatic observing system is used and when the type of the precipitation cannot be identified by this system, the abbreviation UP shall be used for precipitation. The abbreviation UP may be combined, as necessary, with the following characteristics of present weather: FZ, SH and TS. 
</pre>*/
Object r_15_8_6;

/**<pre>The qualifier SH shall be used to indicate precipitation of the shower type. When associated with the indicator VC, the type and intensity of precipitation shall not be specified.
       Note: Showers are produced by convective clouds. They are characterized by their abrupt beginning and end and by the generally rapid and sometimes great variations in the intensity of the precipitation. Drops and solid particles falling in a shower are generally larger than those falling in non-showery precipitation. Between showers, openings may be observed unless stratiform clouds fill the intervals between the cumuliform clouds. 
</pre>*/
Object r_15_8_7;

/**<pre>The qualifier TS shall be used whenever thunder is heard or lightning is detected at the aerodrome within the 10-minute period preceding the time of observation. When appropriate, TS shall be followed immediately, without a space, by relevant letter abbreviations to indicate any precipitation observed. The letter abbreviation TS on its own shall be used when thunder is heard or lightning detected at the aerodrome but no precipitation observed.
       Note: A thunderstorm shall be regarded as being at the aerodrome from the time thunder is first heard, whether or not lightning is seen or precipitation is observed at the aerodrome. A thunderstorm shall be regarded as having ceased or being no longer at the aerodrome at the time thunder is last heard, and the cessation is confirmed if thunder is not heard for 10 minutes after this time. 
</pre>*/
Object r_15_8_8;

/**<pre>The qualifier FZ shall be used only to indicate supercooled water droplets or supercooled precipitation.
       Notes: 
       (1) Any fog consisting predominantly of water droplets at temperatures below 0°C shall be reported as freezing fog (FZFG) whether it is depositing rime ice or not. 
       (2) Whether or not the supercooled precipitation is of the shower type shall not be specified. 
</pre>*/
Object r_15_8_9;

/**<pre>The qualifier VC shall be used to indicate the following significant weather phenomena observed in the vicinity of the aerodrome: TS, DS, SS, FG, FC, SH, PO, BLDU, BLSA,  BLSN and VA. Regulations referring to the combination of VC and FG are given in Regulation 15_8_16.
        Notes: 
        (1) Such weather phenomena should be reported with the qualifier VC only when observed between approximately 8 km and 16 km from the aerodrome reference point. The actual range for which the qualifier VC is to be applied will be determined locally, in consultation with aeronautical authorities. 
        (2) See Regulation 15_8_7. 
</pre>*/
Object r_15_8_10;

/**<pre>The letter abbreviation GR shall be used to report hail only when the diameter of the largest hailstones observed is 5 mm or more. The letter abbreviation GS shall be used to report small hail (diameter of the hailstones less than 5 mm) and/or snow pellets. 
</pre>*/
Object r_15_8_11;

/**<pre>The letter abbreviations FU, HZ, DU and SA (except DRSA) shall be used only when the obstruction to vision consists predominantly of lithometeors and the visibility is reduced by the reported phenomenon to 5 000 metres or less. 
</pre>*/
Object r_15_8_12;

/**<pre>The letter abbreviation BR shall be used when the obstruction to vision consists of water droplets or ice crystals. For w´w´= BR to be reported, the visibility reported in the group VVVV shall be at least 1 000 metres but not more than 5 000 metres. 
</pre>*/
Object r_15_8_13;

/**<pre>The letter abbreviation FG shall be used when the obstruction to vision consists of water droplets or ice crystals (fog or ice fog). For w´w´= FG to be reported without the qualifiers MI, BC, PR or VC, the visibility reported in the group VVVV shall be less than  1 000 metres. 
</pre>*/
Object r_15_8_14;

/**<pre>For w´w´= MIFG to be reported, the visibility at two metres above ground level shall  be 1 000 metres or more and the apparent visibility in the fog layer shall be less than 1 000 metres. 
</pre>*/
Object r_15_8_15;

/**<pre>The letter abbreviation VCFG shall be used to report any type of fog observed in the vicinity of the aerodrome. 
</pre>*/
Object r_15_8_16;

/**<pre>The letter abbreviation BCFG shall be used to report fog patches and the letter abbreviation PRFG to report fog covering part of the aerodrome; the apparent visibility in the fog patch or bank shall be less than 1 000 metres, the fog extending to at least  2 metres above ground level.
        Note: BCFG should be used only when the visibility in parts of the aerodrome is 1 000 metres or more although, when the fog is close to the observing point, the minimum visibility reported by VNVNVNVNDv will be less than 1 000 metres. 
</pre>*/
Object r_15_8_17;

/**<pre>The letter abbreviation SQ shall be used to report squalls when a sudden increase  in wind speed is observed of at least 8 m s–1 (16 knots), the speed rising to 11 m s–1  (22 knots) or more and lasting for at least one minute. 
</pre>*/
Object r_15_8_18;

/**<pre>When an automatic observing system is used and the present weather cannot be observed, the present weather group shall be replaced by //. 
</pre>*/
Object r_15_8_19;

/**<pre>Regulation 15_10 shall apply. 
           {NsNsNshshshs
           {or
           {VVhshshs
</pre>*/
Object r_15_8_20;

/**<pre>Group {or
           {NSC
           {or
           {NCD 
</pre>*/
Object r_15_9;

/**<pre>Cloud amount and cloud height  NsNsNshshshs 
</pre>*/
Object r_15_9_1;

/**<pre>Cloud amount, cloud type and height of cloud base shall be reported to describe only  the clouds of operational significance, i.e., clouds with the height of base below  1 500 meters (5 000 ft) or below the highest minimum sector altitude, whichever is greater, or cumulonimbus or towering cumulus at any height. The cloud amount NsNsNs shall be reported as few (1 to 2 oktas), scattered (3 to 4 oktas), broken (5 to 7 oktas) or overcast (8 oktas), using the three-letter abbreviations FEW, SCT, BKN and OVC followed, without a space, by the height of the base of the cloud layer (mass) hshshs. If there are no clouds below 1 500 m (5 000 ft) or below the highest minimum sector altitude, whichever is greater, no cumulonimbus and no towering cumulus and no restriction on vertical visibility, and the abbreviations CAVOK is not appropriate, then the abbreviation NSC shall be used. When an automatic observing system is used and no clouds are detected by that system, the abbreviation NCD shall be used. 
</pre>*/
Object r_15_9_1_1;

/**<pre>The amount of each cloud layer (mass) shall be determined as if no other clouds were existing. 
</pre>*/
Object r_15_9_1_2;

/**<pre>The cloud group shall be repeated to report different layers or masses of cloud. The number of groups shall not exceed three, except that significant convective clouds, when observed, shall always be reported.
         Note:  The following clouds shall be reported as significant convective clouds: 
         (a) Cumulonimbus cloud (CB); 
         (b) Cumulus congestus of great vertical extent (TCU). The contraction TCU, taken from the term “towering cumulus”, is an ICAO abbreviation used in aeronautical meteorology to describe this cloud. 
</pre>*/
Object r_15_9_1_3;

/**<pre>The selection of layers or masses of cloud to be reported shall be made in accordance with the following criteria: 
         1st group: the lowest individual layer (mass) of any amount, to be reported as FEW, SCT, BKN or OVC; 
         2nd group: the next individual layer (mass) covering more than two oktas, to be reported as SCT, BKN or OVC; 
         3rd group: the next higher individual layer (mass) covering more than four oktas, to be reported as BKN or OVC; 
         Additional groups: significant convective clouds (CB or TCU) when observed and not already reported in one of the three groups above. 
         The order of reporting the groups shall be from lower to higher levels. 
</pre>*/
Object r_15_9_1_4;

/**<pre>The height of cloud base shall be reported in steps of 30 m (100 ft) up to 3 000 m (10 000 ft). Any observed value which does not fit the reporting scale in use shall be rounded down to the nearest lower step in the scale. 
</pre>*/
Object r_15_9_1_5;

/**<pre>When cumulonimbus clouds or towering cumulus clouds are detected by the automatic observing system and the cloud amount and/or the height of cloud base cannot be observed, the cloud amount and/or the height of cloud base elements should be replaced by ///. 
</pre>*/
Object r_15_9_1_6;

/**<pre>Types of cloud other than significant convective clouds shall not be identified. Significant convective clouds, when observed, shall be identified by appending the letter abbreviations CB (cumulonimbus) or TCU (cumulus congestus of great vertical extent), as appropriate, to the cloud group without a space. When an automatic observing system is used and the cloud type cannot be observed by that system, the cloud type in each cloud group shall be replaced by ///.
         Note: When an individual layer (mass) of cloud is composed of cumulonimbus and towering cumulus clouds with a common cloud base, the type of cloud should be reported as cumulonimbus only and the amount of clouds shall be encoded as the sum of the CB and TCU amounts. 
</pre>*/
Object r_15_9_1_7;

/**<pre>Vertical visibility VVhshshs 
       When the sky is obscured and information on vertical visibility is available, the group VVhshshs shall be reported, where hshshs is the vertical visibility in units of 30 metres (hundreds of feet). When information on vertical visibility is not available due to a temporary failure of a sensor or system, the group shall read VV///.
       Notes: 
       (1) The vertical visibility is defined as the vertical visual range into an obscuring medium. 
       (2) See Note (2) to Regulation 15_7_4_2. 
</pre>*/
Object r_15_9_2;

/**<pre>Regulation 15_10 shall apply. 
</pre>*/
Object r_15_9_3;

/**<pre>Code word CAVOK
      The code word CAVOK shall be included in place of the groups under Regulations 15_6, 15_8 and 15_9, when the following conditions occur simultaneously at the time of observation: 
      (a) Visibility reported in the group VVVV is 10 km or more and criteria for inclusion of the group VNVNVNVNDV are not met; 
      (b) No cloud below 1 500 metres (5 000 ft) or below the highest minimum sector altitude, whichever is greater, and no cumulonimbus and no towering cumulus; 
      (c) No significant weather phenomena (see Code table 4678).
      Note: Highest minimum sector altitude is defined in ICAO PANS-OPS, Part 1 – Definitions, as the lowest altitude which may be used under emergency conditions which will provide a minimum clearance of 300 metres (1 000 ft) above all objects located in an area contained within a sector of a circle of 46 km (25 nautical miles) radius centred on a radio aid to navigation. 
</pre>*/
Object r_15_10;

/**<pre>Group  T´T´/T´dT´d 
</pre>*/
Object r_15_11;

/**<pre>The observed air temperature and dew-point temperature rounded to the nearest whole degree Celsius shall be given for T´T´/T´dT´d. Observed values involving 0.5°C shall be rounded up to the next higher Celsius degree. 
</pre>*/
Object r_15_11_1;

/**<pre>Rounded whole degree values of air temperature and dew-point temperature of –9°C to +9°C shall be preceded by 0; for example, +9°C shall be reported as 09. 
</pre>*/
Object r_15_11_2;

/**<pre>Temperatures below 0°C shall be immediately preceded by M, that is minus; for example, –9°C shall be reported as M09 and –0.5°C shall be reported as M00. 
</pre>*/
Object r_15_11_3;

/**<pre>Group  QPHPHPHPH 
</pre>*/
Object r_15_12;

/**<pre>The observed QNH value rounded down to the nearest whole hectopascal shall be given for PHPHPHPH preceded, without a space, by the letter indicator Q. 
</pre>*/
Object r_15_12_1;

/**<pre>If the value of QNH is less than 1 000 hPa, it shall be preceded by 0; for example, QNH 995.6 shall be reported as Q0995.
        Notes: 
        (1) When the first digit following the letter indicator Q is either 0 or 1, the QNH value is reported in the unit hectopascal (hPa). 
        (2) The unit prescribed by ICAO Annex 5 for pressure is the hectopascal. 
</pre>*/
Object r_15_12_2;

/**<pre>Supplementary information – groups
             {WS RDRDR   {(WTsTs/SS´)    }
      REw´w´ {or         {or             }(RDRDR/ERCReReRBRBR)
             {WS ALL RWY {(WTsTs/HHsHsHs)}
</pre>*/
Object r_15_13;

/**<pre>For international dissemination, the section on supplementary information shall be used only to report recent weather phenomena of operational significance, available information on wind shear in the lower layers and, subject to regional air navigation agreement, sea-surface temperature and state of the sea or significant wave height, and also subject to regional air navigation agreement, the state of the runway. 
</pre>*/
Object r_15_13_1;

/**<pre>Recent weather phenomena of operational significance REw´w´ 
</pre>*/
Object r_15_13_2;

/**<pre>Up to three groups of information on recent weather shall be given by the indicator letters RE followed, without a space, by the appropriate abbreviations, in accordance with Regulation 15_8 (but no intensity of the recent weather phenomena shall be indicated) if the following weather phenomena were observed during the period since the last routine report, or last hour, whichever is shorter, but not at the time of observation:
          – Freezing precipitation; 
          – Moderate or heavy drizzle, rain or snow;
          – Moderate or heavy: ice pellets, hail, small hail and/or snow pellets;
          – Blowing snow;
          – Sandstorm or duststorm;
          – Thunderstorm;
          – Funnel cloud(s) (tornado or waterspout);
          – Volcanic ash.
          When an automatic observing system is used and when the type of the precipitation cannot be identified by this system, the abbreviation REUP shall be used for recent precipitation. It may be combined with the characteristics of the present weather in accordance with Regulation 15_8_6.
          Note: The meteorological authority, in consultation with users, may agree not to provide recent weather information where SPECI are issued.
                                       {WS RDRDR 
</pre>*/
Object r_15_13_2_1;

/**<pre>Wind shear in the lower layers {
                                       {WS ALL RWY 
        Information on the existence of wind shear along the take-off path or approach path between one runway level and 500 metres (1 600 ft) significant to aircraft operations shall be reported whenever available and if local circumstances so warrant, using the group set WS RDRDR repeated as necessary. If the wind shear along the take-off path or approach path is affecting all runways in the airport, WS ALL RWY shall be used.
        Note: Concerning runway designator DRDR, Regulation 15_7_3 applies. 
</pre>*/
Object r_15_13_3;

/**<pre>Supplementary information other than specified by Regulations 15_13_2 and 15_13_3 shall be added only in accordance with regional decision. 
</pre>*/
Object r_15_13_4;

/**<pre>Sea-surface temperature and the state of the sea (WTsTs/SS') or sea-surface temperature and the significant wave height (WTsTs/HHsHsHs) 
</pre>*/
Object r_15_13_5;

/**<pre>The sea-surface temperature shall, by regional agreement, be reported according to the regional ICAO Regulation 15_11. The state of the sea shall be reported in accordance with Code table 3700. The significant wave height shall be reported in decimetres. 
</pre>*/
Object r_15_13_5_1;

/**<pre>State of the runway (RDRDR/ERCReReRBRBR) 
</pre>*/
Object r_15_13_6;

/**<pre>Subject to regional air navigation agreement, information on the state of the runway provided by the appropriate airport authority shall be included. The runway deposits ER, the extent of runway contamination CR, the depth of deposit eReR and the estimated surface friction BRBR shall be indicated in accordance with code tables 0919, 0519, 1079 and 0366, respectively. The state of the runway group shall be replaced by the abbreviation R/SNOCLO when the aerodrome is closed due to extreme deposit of snow. If contaminations on a single runway or on all runways at an aerodrome have ceased to exist, this should be reported by replacing the last six digits of the group by CLRD//.
          Note: Concerning runway designator DRDR, Regulation 15_7_3 applies. Additional code figures 88 and 99 are reported in accordance with the European Air Navigation Plan, FASID, Part III-AOP, Attachment A: Code figure 88 indicates “all runways”; code figure 99 shall be used if a new runway state report is not available in time for dissemination of the appropriate METAR message, in which case the previous runway state report will be repeated. 
</pre>*/
Object r_15_13_6_1;

/**<pre>Trend forecasts
      Note: The governing criteria for issuing trend forecasts are specified in the Technical Regulations  (WMO-No. 49), Volume II, Parts I and II. 
</pre>*/
Object r_15_14;

/**<pre>When included in METAR or SPECI reports, the trend forecasts shall be in coded form. 
</pre>*/
Object r_15_14_1;

/**<pre>When a change, required to be indicated in accordance with the governing criteria for significant changes, is expected for one or several of the observed elements – wind, horizontal visibility, present weather, clouds or vertical visibility – one of the following change indicators shall be used for TTTTT: BECMG or TEMPO.
        Note: Where possible, values corresponding to the local operating minima should be selected to indicate changes. 
</pre>*/
Object r_15_14_2;

/**<pre>The time group GGgg, preceded without a space by one of the letter indicators TT = FM (from), TL (until) or AT (at), shall be used as appropriate, to indicate the beginning (FM) or the end (TL) of a forecast change, or the time (AT) at which specific forecast condition(s) is (are) expected. 
</pre>*/
Object r_15_14_3;

/**<pre>The change indicator BECMG shall be used to describe expected changes to meteorological conditions which reach or pass specified threshold criteria at either a regular or irregular rate. 
</pre>*/
Object r_15_14_4;

/**<pre>Changes in meteorological conditions which reach or pass specified threshold criteria for trend forecasts shall be indicated as follows:  
        (a) When the change is forecast to begin and end wholly within the trend forecast period: by the change indicator BECMG followed by the letter indicators FM and TL respectively with their associated time groups, to indicate the beginning and end of the change (for example, for a trend forecast period from 1000 to 1200 UTC in the form: BECMG FM1030 TL1130); 
        (b) When the change is forecast to occur from the beginning of the trend forecast period and be completed before the end of that period: by the change indicator BECMG followed only by the letter indicator TL and its associated time group (the letter indicator FM and its associated time group being omitted), to indicate the end of the change (for example: BECMG TL1100); 
        (c) When the change is forecast to begin during the trend forecast period and be completed at the end of that period: by the change indicator BECMG followed only by the letter indicator FM and its associated time group (the letter indicator TL and its associated time group being omitted), to indicate the beginning of the change (for example: BECMG FM1100); 
        (d) When it is possible to specify a time for the change to occur during the trend forecast period: by the change indicator BECMG followed by the letter indicator AT and its associated time group, to indicate the time of the change (for example: BECMG AT1100); 
        (e) When changes are forecast to take place at midnight UTC, the time shall be indicated:  
            (i) By 0000 when associated with FM and AT; 
            (ii) By 2400 when associated with TL. 
</pre>*/
Object r_15_14_5;

/**<pre>When the change is forecast to commence at the beginning of the trend forecast period and be completed by the end of that period, or when the change is forecast to occur within the trend forecast period but the time of the change is uncertain (possibly shortly after the beginning of the trend forecast period, or midway or near the end of that period), the change shall be indicated by only the change indicator BECMG (letter indicator(s) FM and TL or AT and associated time group(s) being omitted). 
</pre>*/
Object r_15_14_6;

/**<pre>The change indicator TEMPO shall be used to describe expected temporary fluctuations to meteorological conditions which reach or pass specified threshold criteria and last for a period of less than one hour in each instance and in the aggregate cover less than half of the forecast period during which the fluctuations are expected to occur. 
</pre>*/
Object r_15_14_7;

/**<pre>Periods of temporary fluctuations to meteorological conditions which reach or pass specified threshold criteria shall be indicated as follows: 
        (a) When the period of temporary fluctuations is forecast to begin and end wholly within the trend forecast period: by the change indicator TEMPO followed by the letter indicators FM and TL respectively with their associated time groups, to indicate the beginning and end of the fluctuations (for example, for a trend forecast period from 1000 to 1200 UTC in the form: TEMPO FM1030 TL1130); 
        (b) When the period of temporary fluctuations is forecast to occur from the beginning of the trend forecast period but cease before the end of that period: by the change indicator TEMPO followed only by the letter indicator TL and its associated time group (the letter indicator FM and its associated time group being omitted), to indicate the cessation of the fluctuations (for example: TEMPO TL1130); 
        (c) When the period of temporary fluctuations is forecast to begin during the trend forecast period and cease by the end of that period: by the change indicator TEMPO followed only by the letter indicator FM and its associated time group (the letter indicator TL and its associated time group being omitted), to indicate the beginning of the fluctuation (for example: TEMPO FM1030).  
</pre>*/
Object r_15_14_8;

/**<pre>When the period of temporary fluctuations to meteorological conditions is forecast to occur from the beginning of the trend forecast period and cease by the end of that period, the temporary fluctuations shall be indicated by only the change indicator TEMPO (letter indicators FM and TL and associated time groups being omitted). 
</pre>*/
Object r_15_14_9;

/**<pre>Following the change groups TTTTT TTGGgg, only the group(s) referring to the element(s) which is (are) forecast to change significantly shall be included. However, in the case of significant changes of the clouds, all cloud groups, including any significant layer(s) or masses not expected to change, shall be given. 
</pre>*/
Object r_15_14_10;

/**<pre>Regulation 15_5_6 shall apply. 
</pre>*/
Object r_15_14_11;

/**<pre>Inclusion of significant forecast weather w’w’, using the appropriate abbreviations in accordance with Regulation 15_8, shall be restricted to indicate: 
         (1) The onset, cessation or change in intensity of the following weather phenomena:  
             – Freezing precipitation; 
             – Moderate or heavy precipitation (including showers); 
             – Duststorm; 
             – Sandstorm  
             – Thunderstorm (with precipitation); 
             – Other weather phenomena – given in Code table 4678 as agreed by the meteorological authority and air traffic services authority and operators concerned. 
         (2) The onset or cessation of the following weather phenomena: 
             – Freezing fog;
             – Low drifting dust, sand or snow; 
             – Blowing dust, sand or snow;
             – Thunderstorm (without precipitation);
             – Squall;
             – Funnel cloud (tornado or waterspout). 
</pre>*/
Object r_15_14_12;

/**<pre>To indicate the end of significant weather phenomena w´w´, the abbreviation NSW (Nil Significant Weather) shall replace the group w´w´. 
</pre>*/
Object r_15_14_13;

/**<pre>When no cloud below 1 500 metres (5 000 ft) or the highest minimum sector altitude, whichever is greater, and no cumulonimbus and no towering cumulus are forecast, and CAVOK is not appropriate, the abbreviation NSC shall be used. 
</pre>*/
Object r_15_14_14;

/**<pre>When none of the elements listed in Regulation 15_14_2 is expected to change significantly as to require a change to be indicated, this shall be indicated by the code word NOSIG. NOSIG (no significant change) shall be used to indicate meteorological conditions which do not reach or pass specified threshold criteria. 
</pre>*/
Object r_15_14_15;

/**<pre>Group  (RMK . . . . . . . . . . )
      The indicator RMK denotes the beginning of a section containing information included by national decision which shall not be disseminated internationally. </pre>*/
Object r_15_15;

}
