package mx.gob.segob.nsjp.comun.constants;
/**
 * Clase abstracta de constantes generales de la aplicación
 * @author Emigdio
 *
 */
public abstract class ConstantesGenerales {
    /**
     * Tipo de contenido de la respuesta al emitir un reporte/documento en PDF
     */
    public static final String CONTENT_TYPE_PDF="application/pdf";
    /**
     * Tipo de contenido de la respuesta al emitir un XML
     */
    public static final String CONTENT_TYPE_XML = "text/xml";
    /**
     * Header de HTML para el content disposition
     */
    public static final String ENCABEZADO_CONTENT_DISPOSITION = "Content-Disposition";
    /**
     * Header de HTML para el archivo adjunto
     */
    public static final String ENCABEZADO_ATTACH_FILE_NAME ="attachment; filename=";
    public static final String ENCABEZADO_INLINE_FILE_NAME ="inline; filename=";
    /**
     * Header de html para el control de cache
     */
    public static final String ENCABEZADO_CACHE_CONTROL ="Cache-Control";
    /**
     * Header de HTML para el pragma
     */
    public static final String ENCABEZADO_PRAGMA ="pragma";
    /**
     * Header de HTML para el no-cache
     */
    public static final String ENCABEZADO_NOCACHE ="no-cache";
    /**
     * Extensión de un archivo PDF
     */
    public static final String EXTENSION_PDF =".pdf";


    //	Ultima version
    /** arreglo de cadena para normalizara a html */
    public final static String[] CARACTERES_HTML = {
            "!",     "&Aacute;", "&aacute;", "&Acirc;", "&acirc;", "&acute;", "&AElig;", "&aelig;", "&Agrave;", "&agrave;", "&Alpha;", "&alpha;", "&amp;", "&and;", "&ang;", "&Aring;", "&aring;", "&asymp;", "&Atilde;", "&atilde;", "&Auml;", "&auml;", "&bdquo;", "&Beta;", "&beta;", "&brvbar;", "&bull;", "&cap;", "&Ccedil;", "&ccedil;", "&cedil;", "&cent;", "&Chi;", "&chi;", "&circ;", "&clubs;", "&cong;", "&copy;", "&crarr;", "&cup;", "&curren;", "&dagger;", "&Dagger;", "&darr;", "&deg;", "&Delta;", "&delta;", "&diams;", "&divide;", "&Eacute;", "&eacute;", "&Ecirc;", "&ecirc;", "&Egrave;", "&egrave;", "&empty;", "&emsp;", "&ensp;", "&Epsilon;", "&epsilon;", "&equiv;", "&Eta;", "&eta;", "&ETH;", "&eth;", "&Euml;", "&euml;", "&euro;", "&exist;", "&fnof;", "&forall;", "&frac12;", "&frac14;", "&frac34;", "&Gamma;", "&gamma;", "&ge;", "&gt;", "&harr;", "&hearts;", "&hellip;", "&Iacute;", "&iacute;", "&Icirc;", "&icirc;", "&iexcl;", "&Igrave;", "&igrave;", "&infin;", "&int;", "&Iota;", "&iota;", "&iquest;", "&isin;", "&Iuml;", "&iuml;", "&Kappa;", "&kappa;", "&Lambda;", "&lambda;", "&laquo;", "&larr;", "&lceil;", "&ldquo;", "&le;", "&lfloor;", "&lowast;", "&loz;", "&lrm;", "&lsaquo;", "&lsquo;", "&lt;", "&macr;", "&mdash;", "&micro;", "&middot;", "&minus;", "&Mu;", "&mu;", "&nabla;", "&nbsp;", "&nbsp;", "&ndash;", "&ne;", "&ni;", "&not;", "&notin;", "&nsub;", "&Ntilde;", "&ntilde;", "&Nu;", "&nu;", "&Oacute;", "&oacute;", "&Ocirc;", "&ocirc;", "&OElig;", "&oelig;", "&Ograve;", "&ograve;", "&oline;", "&Omega;", "&omega;", "&Omicron;", "&omicron;", "&oplus;", "&or;", "&ordf;", "&ordm;", "&Oslash;", "&oslash;", "&Otilde;", "&otilde;", "&otimes;", "&Ouml;", "&ouml;", "&para;", "&part;", "&permil;", "&perp;", "&Phi;", "&phi;", "&Pi;", "&pi;", "&piv;", "&plusmn;", "&pound;", "&prime;", "&Prime;", "&prod;", "&prop;", "&Psi;", "&psi;", "&quot;", "&radic;", "&raquo;", "&rarr;", "&rceil;", "&rdquo;", "&reg;", "&rfloor;", "&Rho;", "&rho;", "&rlm;", "&rsaquo;", "&rsquo;", "&sbquo;", "&Scaron;", "&scaron;", "&sdot;", "&sect;", "&shy;", "&Sigma;", "&sigma;", "&sigmaf;", "&sim;", "&spades;", "&sub;", "&sube;", "&sum;", "&sup;", "&sup1;", "&sup2;", "&sup3;", "&supe;", "&szlig;", "&Tau;", "&tau;", "&there4;", "&Theta;", "&theta;", "&thetasym;", "&thinsp;", "&THORN;", "&thorn;", "&tilde;", "&times;", "&trade;", "&Uacute;", "&uacute;", "&uarr;", "&Ucirc;", "&ucirc;", "&Ugrave;", "&ugrave;", "&uml;", "&upsih;", "&Upsilon;", "&upsilon;", "&Uuml;", "&uuml;", "&Xi;", "&xi;", "&Yacute;", "&yacute;", "&yen;", "&yuml;", "&Yuml;", "&Zeta;", "&zeta;", "&zwj;", "&zwnj;", "?", "^", "`", "¡", "¦", "¨", "¯", "´", "¸", "¿", "±", "«", "»", "×", "÷", "¢", "£", "¤", "¥", "§", "©", "¬", "®", "°", "µ", "¶", "·", "€", "¼", "½", "¾", "¹", "²", "³",
            "ª", "Á", "á", "À", "à", "Â", "â", "Ä", "ä", "Ã", "ã", "Å", "å", "Æ", "æ", "Ç", "ç", "É", "é", "È", "è", "Ê", "ê", "Ë", "ë", "Í", "í", "Ì", "ì", "Î", "î", "Ï", "ï", "Ñ", "ñ", "º", "Ó", "ó", "Ò", "ò", "Ô", "ô", "Ö", "ö", "Õ", "õ", "Ø", "ø", "Ú", "ú", "Ù", "ù", "Û", "û", "Ü", "ü", "Ý", "ý", "ÿ", "Ÿ",
            "ā",
            "Ă",
            "ă",
            "Ą",
            "ą",
            "Ǟ",
            "ǟ",
            "Ǻ",
            "ǻ",
            "Æ",
            "&AElig;",
            "æ",
            "&aelig;",
            "Ǽ",
            "ǽ", "Ḃ", "ḃ", "Ć", "ć", "Ç", "&Ccedil;", "ç", "&ccedil;", "Č", "č",  "ĉ", "Ċ", "ċ",
            "ḑ", "Ď", "ď", "Ḋ", "ḋ", "Đ", "đ", "Ð", "&ETH;", "ð", "&eth;",

            "Ǳ", "ǲ", "ǳ", "Ǆ", "ǅ", "ǆ", "Ě", "ě", "Ē", "ē", "Ĕ", "ĕ", "Ę", "ę", "Ė",
            "ė", "Ʒ", "ʒ", "Ǯ", "ǯ", "Ḟ", "ḟ", "ƒ", "ﬁ", "ﬂ", "Ǵ", "ǵ",

            "Ģ", "ģ", "Ǧ", "ǧ", "Ĝ", "ĝ", "Ğ", "ğ", "Ġ", "ġ", "Ǥ", "ǥ", "Ĥ", "ĥ", "Ħ",
            "ħ", "Ĩ", "ĩ",

            "Ī", "ī", "Ĭ", "ĭ", "Į", "į", "İ", "ı", "Ĳ", "ĳ", "Ĵ", "ĵ", "Ḱ", "ḱ", "Ķ",
            "Ķ", "Ǩ", "ǩ", "ĸ", "Ĺ", "ĺ", "Ļ", "ļ", "Ľ", "ľ", "Ŀ", "ŀ", "Ł", "ł",

            "Ǉ", "ǈ", "ǉ", "Ṁ", "ṁ", "Ń", "ń", "Ņ", "ņ", "	Ň", "ň", "Ñ", "&Ntilde;", "ñ", "&ntilde;",
            "ŉ", "Ŋ", "ŋ", "Ǌ", "ǋ", "ǌ", "Ō", "ō", "Ŏ", "ŏ", "Ø", "&Oslash;",

            "ø", "&oslash;", "Ő", "ő", "Ǿ", "ǿ", "Œ", "&OElig;", "œ", "&oelig;", "Ṗ", "ṗ", "Ŕ", "ŕ", "Ŗ",
            "ŗ", "Ř", "ř", "ɼ", "Ś", "ś", "Ş", "ş", "Š", "š", "Ŝ", "ŝ", "Ṡ", "ṡ", "ſ",
            "Ţ", "ţ", "Ť", "ť",

            "Ṫ", "ṫ", "Ŧ", "ŧ", "Ũ", "ũ", "Ů", "ů", "Ū", "ū", "Ŭ", "ŭ", "Ų", "ų", "Ű",
            "ű", "Ẁ", "ẁ", "Ẃ", "ẃ", "Ŵ", "ŵ", "Ẅ", "ẅ", "Ỳ", "ỳ", "Ŷ", "ŷ", "Ź", "ź",
            "Ž", "ž", "Ż", "ż"
    };

    /** arreglo de cadena para normalizara a html */
    public final static String[] CARACTERES_UNICODE = {
            "&#33;", "&#193;",   "&#225;",   "&#194;", "&#226;", "&#180;", "&#198;", "&#230;", "&#192;", "&#224;", "&#913;", "&#945;", "&#38;", "&#8743;", "&#8736;", "&#197;", "&#229;", "&#8776;", "&#195;", "&#227;", "&#196;", "&#228;", "&#8222;", "&#914;", "&#946;", "&#166;", "&#8226;", "&#8745;", "&#199;", "&#231;", "&#184;", "&#162;", "&#935;", "&#967;", "&#710;", "&#9827;", "&#8773;", "&#169;", "&#8629;", "&#8746;", "&#164;", "&#8224;", "&#8225;", "&#8595;", "&#176;", "&#916;", "&#948;", "&#9830;", "&#247;", "&#201;", "&#233;", "&#202;", "&#234;", "&#200;", "&#232;", "&#8709;", "&#8195;", "&#8194;", "&#917;", "&#949;", "&#8801;", "&#919;", "&#951;", "&#208;", "&#240;", "&#203;", "&#235;", "&#8364;", "&#8707;", "&#402;", "&#8704;", "&#189;", "&#188;", "&#190;", "&#915;", "&#947;", "&#8805;", "&#62;", "&#8596;", "&#9829;", "&#8230;", "&#205;", "&#237;", "&#206;", "&#238;", "&#161;", "&#204;", "&#236;", "&#8734;", "&#8747;", "&#921;", "&#953;", "&#191;", "&#8712;", "&#207;", "&#239;", "&#922;", "&#954;", "&#923;", "&#955;", "&#171;", "&#8592;", "&#8968;", "&#8220;", "&#8804;", "&#8970;", "&#8727;", "&#9674;", "&#8206;", "&#8249;", "&#8216;", "&#60;", "&#175;", "&#8212;", "&#181;", "&#183;", "&#8722;", "&#924;", "&#956;", "&#8711;", "&#160;", "&#32;", "&#8211;", "&#8800;", "&#8715;", "&#172;", "&#8713;", "&#8836;", "&#209;", "&#241;", "&#925;", "&#957;", "&#211;", "&#243;", "&#212;", "&#244;", "&#338;", "&#339;", "&#210;", "&#242;", "&#8254;", "&#937;", "&#969;", "&#927;", "&#959;", "&#8853;", "&#8744;", "&#170;", "&#186;", "&#216;", "&#248;", "&#213;", "&#245;", "&#8855;", "&#214;", "&#246;", "&#182;", "&#8706;", "&#8240;", "&#8869;", "&#934;", "&#966;", "&#928;", "&#960;", "&#982;", "&#177;", "&#163;", "&#8242;", "&#8243;", "&#8719;", "&#8733;", "&#936;", "&#968;", "&#34;", "&#8730;", "&#187;", "&#8594;", "&#8969;", "&#8221;", "&#174;", "&#8971;", "&#929;", "&#961;", "&#8207;", "&#8250;", "&#8217;", "&#8218;", "&#352;", "&#353;", "&#8901;", "&#167;", "&#173;", "&#931;", "&#963;", "&#962;", "&#8764;", "&#9824;", "&#8834;", "&#8838;", "&#8721;", "&#8835;", "&#185;", "&#178;", "&#179;", "&#8839;", "&#223;", "&#932;", "&#964;", "&#8756;", "&#920;", "&#952;", "&#977;", "&#8201;", "&#222;", "&#254;", "&#732;", "&#215;", "&#8482;", "&#218;", "&#250;", "&#8593;", "&#219;", "&#251;", "&#217;", "&#249;", "&#168;", "&#978;", "&#933;", "&#965;", "&#220;", "&#252;", "&#926;", "&#958;", "&#221;", "&#253;", "&#165;", "&#255;", "&#376;", "&#918;", "&#950;", "&#8205;", "&#8204;", "&#63;", "&#94;", "&#96;", "&#161;", "&#166;", "&#168;", "&#175;", "&#180;", "&#184;", "&#191;", "&#177;", "&#171;", "&#187;", "&#215;", "&#247;", "&#162;", "&#163;", "&#164;", "&#165;", "&#167;", "&#169;", "&#172;", "&#174;", "&#176;", "&#181;", "&#182;", "&#183;", "&#8364;", "&#188;", "&#189;", "&#190;", "&#185;", "&#178;", "&#179;", "&#170;", "&#193;", "&#225;", "&#192;", "&#224;", "&#194;", "&#226;", "&#196;", "&#228;", "&#195;", "&#227;", "&#197;", "&#229;", "&#198;", "&#230;", "&#199;", "&#231;", "&#201;", "&#233;", "&#200;", "&#232;", "&#202;", "&#234;", "&#203;", "&#235;", "&#205;", "&#237;", "&#204;", "&#236;", "&#206;", "&#238;", "&#207;", "&#239;", "&#209;", "&#241;", "&#186;", "&#211;", "&#243;", "&#210;", "&#242;", "&#212;", "&#244;", "&#214;", "&#246;", "&#213;", "&#245;", "&#216;", "&#248;", "&#218;", "&#250;", "&#217;", "&#249;", "&#219;", "&#251;", "&#220;", "&#252;", "&#221;", "&#253;", "&#255;", "&#376;",

            "&#257;",
            "&#258;",
            "&#259;",
            "&#260;",
            "&#261;",
            "&#478;",
            "&#479;",
            "&#506;",
            "&#507;",
            "&#198;",
            "&#198;",
            "&#230;",
            "&#230;",
            "&#508;",
            "&#509;", "&#7682;", "&#7683;", "&#262;", "&#263;", "&#199;", "&#199;", "&#231;", "&#231;", "&#268;", "&#269;", "&#265;", "&#266;", "&#267;",
            "&#7697;", "&#270;", "&#271;", "&#7690;", "&#7691;", "&#272;", "&#273;", "&#208;", "&#208;", "&#240;", "&#240;",

            "&#497;", "&498;", "&#499;", "&#452;", "&#453;", "&#454;", "&#282", "&#283;", "&#274;", "&#275;", "&#276;", "&#277;", "&#280;", "&#281;", "&#278;",
            "&#279;", "&#439;", "&#658;", "&#494;", "&#495;", "&#7710;", "&#7711;", "&#402;", "&#64257;", "&#64258;", "&#500;", "&#501;",

            "&#290;",  "&#291;",  "&#486;",  "&#487;",  "&#284;",  "&#285;",  "&#286;",  "&#287;",  "&#288;",  "&#289;",  "&#484;",  "&#485;",  "&#292;",  "&#293;",  "&#294;",
            "&#295;",  "&#296;",  "&#297;",

            "&#298;", "&#299;", "&#300;", "&#301;", "&#302;", "&#303;", "&#304;", "&#305;", "&#306;", "&#307;", "&#308;", "&#309;", "&#7728;", "&#7729;", "&#310;",
            "&#310;", "&#488;", "&#489;", "&#312;", "&#313;", "&#314;", "&#315;", "&#316;", "&#317;", "&#318;", "&#319;", "&#320;", "&#321;", "&#322;",

            "&#455;", "&#456;", "&#457;", "&#7744;", "&#7745;", "&#323;", "&#324;", "&#325;", "&#326;", "&#327;", "&#328;", "&#209;", "&#209;", "&#241;", "&#241;",
            "&#329;", "&#330;", "&#331;", "&#458;", "&#459;", "&#460;", "&#332;", "&#333;", "&#334;", "&#335;", "&#216;", "&#216;",

            "&#248;", "&#248;", "&#336;", "&#337;", "&#510;", "&#511;", "&#338;", "&#338;", "&#339;", "&#339;", "&#7766;", "&#7767;", "&#340;", "&#341;", "&#342;",
            "&#343;", "&#344;", "&#345;", "&#636;", "&#346;", "&#347;", "&#350;", "&#351;", "&#352;", "&#353;", "&#348;", "&#349;", "&#7776;", "&#7777;", "&#383;",
            "&#354;", "&#355;", "&#356;", "&#357;",

            "&#7786;", "&#7787;", "&#358;", "&#359;", "&#360;", "&#361;", "&#366;", "&#367;", "&#362;", "&#363;", "&#364;", "&#365;", "&#370;", "&#371;", "&#368;",
            "&#369;", "&#7808;", "&#7809;", "&#7810;", "&#7811;", "&#372;", "&#373;", "&#7812;", "&#7813;", "&#7922;", "&#7923;", "&#374;", "&#375;", "&#377;", "&#378;",
            "&#381;", "&#382;", "&#379;", "&#380;"
    };

    public final static String BODY_TAG_APERTURA = "<body>";

    public final static String BODY_TAG_CIERRE = "</body>";

    public final static String C_DATA_OPEN = "<![CDATA[";

    public final static String C_DATA_CLOSE = "]]>";

    public final static String APERTURA_CAMPO_FORMATO = "&lt;";

    public final static String CIERRE_CAMPO_FORMATO = "&gt;";

    public final static String[] HORAS_AUDIENCIA = new String[]{
            "00:00","00:30","01:00","01:30","02:00","02:30",
            "03:00","03:30","04:00","04:30","05:00","05:30",
            "06:00","06:30","07:00","07:30","08:00","08:30",
            "09:00","09:30","10:00","10:30","11:00","11:30",
            "12:00","12:30","13:00","13:30","14:00","14:30",
            "15:00","15:30","16:00","16:30","17:00","17:30",
            "18:00","18:30","19:00","19:30","20:00","20:30",
            "21:00","21:30","22:00","22:30","23:00","23:30"};



    //Audiencias
    /**
     * Número de jueces necesarios para una audiencia del tipo Juicio Oral
     */
    public final static int JUECES_AUTOMATICOS_JUICIO_ORAL = 3;
    /**
     * Número de jueces necesarios para una audiencia que no sea del tipo Juicio Oral
     */
    public final static int JUECES_AUTOMATICOS_PREDETERMINADO = 1;
    /**
     * Número de jueces sustitutos para la asignación automática
     */
    public final static int JUECES_AUTOMATICOS_SUSTITUTOS = 1;

    public final static int DIAS_ATRAS_CONSULTAS_HISTORICAS=120;

    // Constantes de retorno del Servidor Web de JAVS - Agendar audiencias

    public final static int FALLO_CONEXION_WEB_SERVICE_JAVS=100;
    public final static int EXITO_AGENDAR_JAVS=1;
    public final static int EXITO_AGENDAR_JAVS_SIN_ARCH_OUT=2;
    public final static int EXITO_AGENDAR_JAVS_SIN_ARCH_IN=3;
    public final static int EXITO_AGENDAR_JAVS_SIN_ARCH_INOUT=4;
    public final static int ERROR_AGENDAR_JAVS=0;
    public final static int ERROR_PARAMETROS_CONEXION=5;
    public final static int ERROR_ELEMENTOS_INSUFICIENTES=6;
    public final static int ERROR_CREDENCIALES=7;

    // Constantes de retorno del Servidor Web de JAVS - Consultar estado audiencias

    public final static int AUDIENCIA_NO_ACTIVA=8;
    public final static int FALLO_GENERAL=11;
    public final static int FALLO_GENERAL_JAVS=12;
    public final static int ERROR_CREDENCIALES_CONSULTA=13;
    public final static int AUDIENCIA_PROCESO=21;
    public final static int AUDIENCIA_TERMINO=20;

    public final static int NO_ES_JAVS=24;

    // Constantes de retorno del Servidor Web de JAVS - Eliminar audiencias

    public final static int ERROR_ELIMINACION=27;
    public final static int EXITO_ELIMINACION=26;
    public final static int NO_HAY_AUDIENCIAS=25;
    public final static int ERROR_SERVICIO_ELIMINACION=28;

    // Constantes de retorno del Servidor Web de JAVS - Consultar audiencias    8, 11, 12, 13, 25

    public final static int RESOLUTIVOS_ACTUALIZADOS=9;
    public final static int AUDIENCIA_ACTUALIZADA=10;

//	Prueba para imprimir  caracteres
//	public static void main(String  args[]){
//		for(int cont=0; cont < CARACTERES_HTML.length; cont++){
//		System.out.println(" "+ CARACTERES_HTML[cont] + "-"+ CARACTERES_UNICODE[cont]);
//		}
//	}
}
