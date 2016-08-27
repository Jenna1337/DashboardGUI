package tools.decoders;

//TODO
/*
 * Useful URLs:
 * Huge PDF file: http://www.icao.int/safety/meteorology/Documents/FASID%20Table%20MET%202A%20-%20SUG%20Annex%201%20-%20Complete.pdf
 * 
 * https://en.wikipedia.org/wiki/International_Civil_Aviation_Organization_airport_code
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_A
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_B
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_C
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_D
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_E
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_F
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_G
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_H
 * <!--https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_I--> //UNUSED
 * <!--https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_J--> //UNUSED
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_K
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_L
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_M
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_N
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_O
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_P
 * <!--https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_Q--> //RESERVED
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_R
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_S
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_T
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_U
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_V
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_W
 * <!--https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_X--> //UNUSED
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_Y
 * https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_Z
 * 
 */
public class ICAODecoder {
	/**
	 * Note this needs a letter added to the end of it.
	 */
	String listurl = "https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_";
/**(from wikipedia)
Prefix code

Country



A – Western South Pacific


AG Solomon Islands 
AN Nauru 
AY Papua New Guinea 


B – Greenland, Iceland, and Kosovo

BG Greenland 
BI Iceland 
BK Kosovo 


C – Canada

C Canada 


D – Eastern parts of West Africa and Maghreb

DA Algeria 
DB Benin 
DF Burkina Faso 
DG Ghana 
DI Côte d'Ivoire 
DN Nigeria 
DR Niger 
DT Tunisia 
DX Togolese Republic 


E – Northern Europe

EB Belgium 
ED Germany (civil) 
EE Estonia 
EF Finland 
EG United Kingdom (and Crown dependencies) 
EH Netherlands 
EI Ireland 
EK Denmark and the Faroe Islands 
EL Luxembourg 
EN Norway 
EP Poland 
ES Sweden 
ET Germany (military) 
EV Latvia 
EY Lithuania 


F – Most of Central Africa and Southern Africa, and the Indian Ocean

FA South Africa 
FB Botswana 
FC Republic of the Congo 
FD Swaziland 
FE Central African Republic 
FG Equatorial Guinea 
FH Saint Helena, Ascension and Tristan da Cunha 
FI Mauritius 
FJ British Indian Ocean Territory 
FK Cameroon 
FL Zambia 
FM Comoros, France (Mayotte and Réunion), and Madagascar 
FN Angola 
FO Gabon 
FP São Tomé and Príncipe 
FQ Mozambique 
FS Seychelles 
FT Chad 
FV Zimbabwe 
FW Malawi 
FX Lesotho 
FY Namibia 
FZ Democratic Republic of the Congo 


G – Western parts of West Africa and Maghreb

GA Mali 
GB The Gambia 
GC Spain (Canary Islands) 
GE Spain (Ceuta and Melilla) 
GF Sierra Leone 
GG Guinea-Bissau 
GL Liberia 
GM Morocco 
GO Senegal 
GQ Mauritania 
GS Western Sahara 
GU Guinea 
GV Cape Verde 


H – East Africa and Northeast Africa

HA Ethiopia 
HB Burundi 
HC Somalia (including Somaliland) 
HD Djibouti 
HE Egypt 
HH Eritrea 
HK Kenya 
HL Libya 
HR Rwanda 
HS Sudan and South Sudan 
HT Tanzania 
HU Uganda 


K – Contiguous United States

K Contiguous United States 


L – Southern Europe, Israel and Turkey

LA Albania 
LB Bulgaria 
LC Cyprus 
LD Croatia 
LE Spain (mainland section and Balearic Islands) 
LF France (including Saint-Pierre and Miquelon) 
LG Greece 
LH Hungary 
LI Italy 
LJ Slovenia 
LK Czech Republic 
LL Israel 
LM Malta 
LN Monaco 
LO Austria 
LP Portugal (including the Azores and Madeira) 
LQ Bosnia and Herzegovina 
LR Romania 
LS Switzerland 
LT Turkey 
LU Moldova 
LV Palestinian territories 
LW Macedonia 
LX Gibraltar 
LY Serbia and Montenegro 
LZ Slovakia 


M – Central America, Mexico and northern/western parts of the Caribbean

MB Turks and Caicos Islands 
MD Dominican Republic 
MG Guatemala 
MH Honduras 
MK Jamaica 
MM Mexico 
MN Nicaragua 
MP Panama 
MR Costa Rica 
MS El Salvador 
MT Haiti 
MU Cuba 
MW Cayman Islands 
MY Bahamas 
MZ Belize 


N – Most of the South Pacific

NC Cook Islands 
NF Fiji, Tonga 
NG Kiribati (Gilbert Islands), Tuvalu 
NI Niue 
NL France (Wallis and Futuna) 
NS Samoa, United States (American Samoa) 
NT France (French Polynesia) 
NV Vanuatu 
NW France (New Caledonia) 
NZ New Zealand, Antarctica 


O – Pakistan, Afghanistan and most of Western Asia
 (excluding Cyprus, Israel, Turkey, and the South Caucasus)

OA Afghanistan 
OB Bahrain 
OE Saudi Arabia 
OI Iran 
OJ Jordan and the West Bank 
OK Kuwait 
OL Lebanon 
OM United Arab Emirates 
OO Oman 
OP Pakistan 
OR Iraq 
OS Syria 
OT Qatar 
OY Yemen 


P – Eastern North Pacific

PA USA (Alaska) (also PF, PO and PP) 
PB USA (Baker Island) 
PC Kiribati (Canton Airfield, Phoenix Islands) 
PF USA (Alaska) (also PA, PO and PP) 
PG USA (Guam, Northern Mariana Islands) 
PH USA (Hawaii) 
PJ USA (Johnston Atoll) 
PK Marshall Islands 
PL Kiribati (Line Islands) 
PM USA (Midway Island) 
PO USA (Alaska) (also PA, PF and PP) 
PP USA (Alaska) (also PA, PF and PO) 
PT Federated States of Micronesia, Palau 
PW USA (Wake Island) 


R – Republic of China/Korea/Philippines and Japan

RC Republic of China (Taiwan) 
RJ Japan (Mainland) 
RK Republic of Korea (South Korea) 
RO Japan (Okinawa) 
RP Philippines 


S – South America

SA Argentina 
SB Brazil (also SD, SI, SJ, SN, SS and SW) 
SC Chile (including Easter Island) (also SH) 
SD Brazil (also SB, SI, SJ, SN, SS and SW) 
SE Ecuador 
SF United Kingdom (Falkland Islands) 
SG Paraguay 
SH Chile (also SC) 
SI Brazil (also SB, SD, SJ, SN, SS and SW) 
SJ Brazil (also SB, SD, SI, SN, SS and SW) 
SK Colombia 
SL Bolivia 
SM Suriname 
SN Brazil (also SB, SD, SI, SJ, SS and SW) 
SO France (French Guiana) 
SP Peru 
SS Brazil (also SB, SD, SI, SJ, SN and SW) 
SU Uruguay 
SV Venezuela 
SW Brazil (also SB, SD, SI, SJ, SN and SS) 
SY Guyana 


T – Eastern and southern parts of the Caribbean

TA Antigua and Barbuda 
TB Barbados 
TD Dominica 
TF France (Guadeloupe, Martinique, Saint Barthélemy, Saint Martin) 
TG Grenada 
TI USA (U.S. Virgin Islands) 
TJ USA (Puerto Rico) 
TK Saint Kitts and Nevis 
TL Saint Lucia 
TN Caribbean Netherlands, Aruba, Curaçao, Sint Maarten 
TQ UK (Anguilla) 
TR UK (Montserrat) 
TT Trinidad and Tobago 
TU UK (British Virgin Islands) 
TV Saint Vincent and the Grenadines 
TX UK (Bermuda) 


U – Russia and Post-Soviet states, excluding the Baltic states and Moldova

U Russia (except UA, UB, UC, UD, UG, UK, UM and UT) 
UA Kazakhstan 
UB Azerbaijan 
UC Kyrgyzstan 
UD Armenia 
UG Georgia 
UK Ukraine 
UM Belarus and Russia (Kaliningrad Oblast) 
UT Tajikistan, Turkmenistan, Uzbekistan 


V – South Asia (except Afghanistan and Pakistan),
 mainland Southeast Asia, Hong Kong and Macau

VA India (also VE, VI and VO) 
VC Sri Lanka 
VD Cambodia 
VE India (also VA, VI and VO) 
VG Bangladesh 
VH Hong Kong 
VI India (also VA, VE and VO) 
VL Laos 
VM Macau 
VN Nepal 
VO India (also VA, VE and VI) 
VQ Bhutan 
VR Maldives 
VT Thailand 
VV Vietnam 
VY Myanmar 


W – Maritime Southeast Asia (except the Philippines)

WA Indonesia (also WI, WQ and WR) 
WB Brunei, Malaysia (East Malaysia) 
WI Indonesia (also WA, WQ and WR) 
WM Malaysia (Peninsular Malaysia) 
WP Timor-Leste 
WQ Indonesia (also WA, WI and WR) 
WR Indonesia (also WA, WI and WQ) 
WS Singapore 


Y – Australia

Y Australia (including Norfolk Island, Christmas Island and Cocos (Keeling) Islands) 


Z – East Asia (excluding Hong Kong, Japan, Macau, South Korea and Taiwan)

Z China (except ZK and ZM) 
ZK Democratic People's Republic of Korea (North Korea) 
ZM Mongolia 

 */
	public ICAODecoder() {
		// TODO Auto-generated constructor stub
	}

}
