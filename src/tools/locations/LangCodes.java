package tools.locations;
/*
 * NOTE:
 * This file contains UNICODE CHARACTERS
 * The text in this file may appear strange.
 */
/**
 * An enum of the different locales supported.
 * @author jonah.sloan
 * @author jenna3715
 *
 */
public enum LangCodes
{
	//TODO Add more languages to locale
	//aa("Afaraf");//Afar
	//ab("аҧсуа бызшәа", "аҧсшәа");//Abkhaz
	//ae("avesta");//Avestan
	//af("Afrikaans");//Afrikaans
	//ak("Akan");//Akan
	//am("አማርኛ");//Amharic
	//an("aragonés");//Aragonese
	//ar("العربية");//Arabic
	//as("অসমীয়া");//Assamese
	//av("авар мацӀ", "магӀарул мацӀ");//Avaric
	//ay("aymar aru");//Aymara
	//az("azərbaycan dili");//Azerbaijani
	//ba("بلوچی");//Balochi
	//ba("башҡорт теле");//Bashkir
	//be("беларуская мова");//Belarusian
	//bg("български език");//Bulgarian
	//bh("भोजपुरी");//Bihari
	//bi("Bislama");//Bislama
	//bm("bamanankan");//Bambara
	//bn("বাংলা");//Bengali, Bangla
	//bo("བོད་ཡིག");//Tibetan Standard, Tibetan, Central
	//br("brezhoneg");//Breton
	//bs("bosanski jezik");//Bosnian
	//ca("català");//Catalan
	//ce("нохчийн мотт");//Chechen
	//ch("Chamoru");//Chamorro
	//co("corsu", "lingua corsa");//Corsican
	//cr("ᓀᐦᐃᔭᐍᐏᐣ");//Cree
	//cs("čeština", "český jazyk");//Czech
	//cu("ѩзыкъ словѣньскъ");//Old Church Slavonic, Church Slavonic, Old Bulgarian
	//cv("чӑваш чӗлхи");//Chuvash
	//cy("Cymraeg");//Welsh
	//da("dansk");//Danish
	//de("Deutsch");//German
	//dv("ދިވެހި");//Divehi
	//dz("རྫོང་ཁ");//Dzongkha
	//ee("Eʋegbe");//Ewe
	//el("ελληνικά");//Greek (modern)
	en("English");//English
	//es("español");//Spanish
	//et("eesti", "eesti keel");//Estonian
	//eu("euskara", "euskera");//Basque
	//fa("فارسی");//Persian (Farsi)
	//ff("Fulfulde", "Pulaar", "Pular");//Fula, Fulah, Pulaar, Pular
	//fi("suomi", "suomen kieli");//Finnish
	//fj("vosa Vakaviti");//Fijian
	//fo("føroyskt");//Faroese
	//fr("français", "langue française");//French
	//fy("Frysk");//Western Frisian
	//ga("Gaeilge");//Irish
	//gd("Gaelic", "Gaelic Gàidhlig");//Scottish
	//gl("galego");//Galician
	//gn("Avañe'ẽ");//Guaraní
	//gu("ગુજરાતી");//Gujarati
	//gv("Gaelg", "Gailck");//Manx
	//ha("هَوُسَ");//Hausa
	//he("עברית");//Hebrew (modern)
	//hi("हिन्दी", "हिंदी");//Hindi
	//ho("Hiri Motu");//Hiri Motu
	//hr("hrvatski jezik");//Croatian
	//ht("Kreyòl ayisyen");//Haitian, Haitian Creole
	//hu("magyar");//Hungarian
	//hy("Հայերեն");//Armenian
	//hz("Otjiherero");//Herero
	//id("Bahasa Indonesia");//Indonesian
	//ig("Asụsụ Igbo");//Igbo
	//ii("ꆈꌠ꒿ Nuosuhxop");//Nuosu
	//ik("Iñupiaq", "Iñupiatun");//Inupiaq
	//is("Íslenska");//Icelandic
	//it("italiano");//Italian
	//iu("ᐃᓄᒃᑎᑐᑦ");//Inuktitut
	//ja("日本語 (にほんご)");//Japanese
	//jv("ꦧꦱꦗꦮ");//Javanese
	//ka("ქართული");//Georgian
	//kg("Kikongo");//Kongo
	//ki("Gĩkũyũ");//Kikuyu, Gikuyu
	//kj("Kuanyama");//Kwanyama, Kuanyama
	//kk("қазақ тілі");//Kazakh
	//kl("kalaallisut", "kalaallit oqaasii");//Kalaallisut, Greenlandic
	//km("ខ្មែរ", "ខេមរភាសា", "ភាសាខ្មែរ");//Khmer
	//kn("ಕನ್ನಡ");//Kannada
	//ko("한국어", "조선어");//Korean
	//kr("Kanuri");//Kanuri
	//ks("कश्मीरी", "كشميري‎");//Kashmiri
	//ku("Kurdî", "كوردی‎");//Kurdish
	//kv("коми кыв");//Komi
	//kw("Kernewek");//Cornish
	//ky("Кыргызча", "Кыргыз тили");//Kyrgyz
	//la("latine", "lingua latina");//Latin
	//lb("Lëtzebuergesch");//Luxembourgish
	//lg("Luganda");//Ganda
	//li("Ligures");//Zeneize, Gallo, Ligure
	//ln("Lingála");//Lingala
	//lo("ພາສາລາວ");//Lao
	//lt("lietuvių kalba");//Lithuanian
	//lu("Tshiluba");//Luba-Katanga
	//lv("latviešu valoda");//Latvian
	//mg("fiteny malagasy");//Malagasy
	//mh("Kajin M̧ajeļ");//Marshallese
	//mi("te reo Māori");//Māori
	//mk("македонски јазик");//Macedonian
	//ml("മലയാളം");//Malayalam
	//mn("Монгол хэл");//Mongolian
	//mr("मराठी");//Marathi (Marāṭhī)
	//ms("bahasa Melayu", "بهاس ملايو‎");//Malay
	//mt("Malti");//Maltese
	//my("ဗမာစာ");//Burmese
	//na("Dorerin Naoero");//Nauruan
	//nd("isiNdebele");//Northern Ndebele
	//ne("नेपाली");//Nepali
	//ng("Owambo");//Ndonga
	//nl("Nederlands", "Vlaams");//Dutch
	//no("Norsk");//Norwegian
	//nr("isiNdebele");//Southern Ndebele
	//nv("Diné bizaad");//Navajo, Navaho
	//ny("chiCheŵa", "chinyanja");//Chichewa, Chewa, Nyanja
	//oc("occitan", "lenga d'òc");//Occitan
	//oj("ᐊᓂᔑᓈᐯᒧᐎᓐ");//Ojibwe, Ojibwa
	//om("Afaan Oromoo");//Oromo
	//or("ଓଡ଼ିଆ");//Oriya
	//os("ирон æвзаг");//Ossetian, Ossetic
	//pa("ਪੰਜਾਬੀ");//Eastern Punjabi, Eastern Panjabi
	//pi("पाऴि");//Pāli
	//pl("język polski", "polszczyzna");//Polish
	//ps("پښتو");//Pashto, Pushto
	//pt("português");//Portuguese
	//qu("Runa Simi", "Kichwa");//Quechua
	//rm("rumantsch grischun");//Romansh
	//rn("Ikirundi");//Kirundi
	//ro("Română");//Romanian
	//ru("Русский");//Russian
	//rw("Ikinyarwanda");//Kinyarwanda
	//sa("संस्कृतम्");//Sanskrit (Saṁskṛta)
	//sc("sardu");//Sardinian
	//sd("सिन्धी", "سنڌي، سندھی‎");//Sindhi
	//se("Davvisámegiella");//Northern Sami
	//sg("yângâ tî sängö");//Sango
	//si("සිංහල");//Sinhalese, Sinhala
	//sk("slovenčina", "slovenský jazyk");//Slovak
	//sl("slovenski jezik", "slovenščina");//Slovene
	//sm("gagana fa'a Samoa");//Samoan
	//sn("chiShona");//Shona
	//so("Soomaaliga", "af Soomaali");//Somali
	//sq("Shqip");//Albanian
	//sr("српски језик");//Serbian
	//ss("SiSwati");//Swati
	//st("Sesotho");//Southern Sotho
	//su("Basa Sunda");//Sundanese
	//sv("svenska");//Swedish
	//sw("Kiswahili");//Swahili
	//ta("தமிழ்");//Tamil
	//te("తెలుగు");//Telugu
	//tg("тоҷикӣ", "toçikī", "تاجیکی‎");//Tajik
	//th("ไทย");//Thai
	//ti("ትግርኛ");//Tigrinya
	//tk("Türkmen", "Түркмен");//Turkmen
	//tl("Wikang Tagalog");//Tagalog
	//tn("Setswana");//Tswana
	//to("faka Tonga");//Tonga
	//tr("Türkçe");//Turkish
	//ts("Xitsonga");//Tsonga
	//tt("татар теле", "tatar tele");//Tatar
	//tw("Twi");//Twi
	//ty("Reo Tahiti");//Tahitian
	//ug("ئۇيغۇرچە‎", "Uyghurche");//Uyghur
	//uk("Українська");//Ukrainian
	//ur("اردو");//Urdu
	//uz("Oʻzbek", "Ўзбек", "أۇزبېك‎");//Uzbek
	//ve("Tshivenḓa");//Venda
	//vi("Tiếng Việt");//Vietnamese
	//wa("walon");//Walloon
	//wo("Wollof");//Wolof
	//xh("isiXhosa");//Xhosa
	//yi("ייִדיש");//Yiddish
	//yo("Yorùbá");//Yoruba
	//za("Saɯ cueŋƅ", "Saw cuengh");//Zhuang, Chuang
	//zh("中文 (Zhōngwén)", "汉语", "漢語");//Chinese, Mandarin
	//zu("isiZulu");//Zulu
	
	/**The name(s) of the language in that language.*/
	private final String[] names;
	
	/**
	 * @param locnames The name(s) of the language in that language.
	 */
	private LangCodes(String... locnames)
	{
		this.names=locnames;
	}
	/**
	 * @return a non-mutable array of the native name(s) for this language. 
	 */
	public String[] getNames()
	{
		return java.util.Arrays.copyOf(names, names.length);
	}
}
