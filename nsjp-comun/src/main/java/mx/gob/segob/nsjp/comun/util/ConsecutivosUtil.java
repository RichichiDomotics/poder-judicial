/**
 * Nombre del Programa : ConsecutivosUtil.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Genera los consecutivos para Expediente y Caso en base a un conjunto de caracteres
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.comun.util;

import java.util.HashMap;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Genera los consecutivos para Expediente y Caso en base a un conjunto de
 * caracteres.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ConsecutivosUtil {
    private static final Logger logger = Logger
            .getLogger(ConsecutivosUtil.class);
    /**
     * Constante CARACTERES_CUC.
     */
    private static final char[] CARACTERES_CUC = {'3', '4', '7', '9', 'A', 'C',
            'D', 'E', 'F', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T',
            'U', 'V', 'W', 'X', 'Y'};
    //private static final char[] CARACTERES_CUC = {'3', '4', '7', '9'};

    /**
     * Constante DOMINIO_CARACTERES_CUC.
     */
    private static final Map DOMINIO_CARACTERES_CUC = new HashMap();

    /**
     * Constante CUC_PARAMETRO_INICIAL.
     */
    private static final String CUC_PARAMETRO_INICIAL = "00000";
    /**
     * Constante BASE_INDICE_ARREGLOS.
     */
    private static final int BASE_INDICE_ARREGLOS = 0;
    
    /**
     * Constante para identificar el maximo valor -1 del consecutivo del numero de expediente
     */
    private static final int CONSECUTIVO_NUMERICO = 100000;

    /**
     * Constante para identificar la longitud del valor numerico del consecutivo del numero de expediente
     */
    private static final int LONGITUD_CONSECUTIVO_NUMERICO = 5;
    
    public static final int LONGITUD_PREFIJO_SIN_FECHA = 13; 
    
    public static final String CONSECUTIVO_INICIAL = "A-0000";
    
    public static final Character SEPARADOR_CONSECUTIVO= new Character('-');
    
    public static final Character SEPARADOR_PREFIJOS= new Character('/');
    
    public static final Character CARACTER_LIBRE= new Character('0');
    
    static {
        for (int i = 0; i < CARACTERES_CUC.length; i++) {
            DOMINIO_CARACTERES_CUC.put("" + CARACTERES_CUC[i], i);
        }
    }

    /**
     * Transforma �ltimo consecutivo increment&aacute;ndolo en uno. Esta
     * incrementaci&oacute;n es &uacute;nicamente sobre los �ltimos
     * d&iacute;gitos del expediente o caso por asignaci�n de n�mero para caso o
     * expediente.<br>
     * La generaci�n del sconsecutivo se hace en un conjunto de 24 caracteres
     * conformado por 4 n&uacute;meros y 20 letras.<br>
     * El dominio de caracteres definido es
     * {<i>3,4,7,9,A,C,D,E,F,H,J,K,L,M,N,P,Q,R,T,U,V,W,X,Y</i>} . <br>
     * EJEMPLO DE USO: suponiendo que el dominio fuera de 3 caracteres
     * {<i>A,B,C</i>}:<br>
     * 
     * AAA -- Valor Inicial <br>
     * AAB -- Despu&eacute;s de incrementarle una unidad<br>
     * AAC-- Despu&eacute;s de incrementarle una unidad <br>
     * ABA-- Despu&eacute;s de incrementarle una unidad ...
     * 
     * @param noAnterior
     *            Parametro que contiene el �ltimo consecutivo generado
     * @return String Siguiente consecutivo.
     */
    public static String incrementarConsecutivo(String noAnterior,
            int longitudInc) {
        logger.debug("noAnterior :: " + noAnterior);
        logger.debug("longitudInc :: " + longitudInc);
        if (noAnterior == null
                || noAnterior.equalsIgnoreCase(CUC_PARAMETRO_INICIAL)
                || noAnterior.equals("")) {
            return StringUtils.leftPad("", longitudInc, "3");
        }
        StringBuffer cucIncrementado = new StringBuffer("");
        char[] arregloCaracteresCUC = noAnterior.toCharArray();
        boolean aumentarSiguienteCaracter = false;
        int i = arregloCaracteresCUC.length - 1;
        do {
            final String llaveCUC = String.valueOf(arregloCaracteresCUC[i]);
            Object valorLlaveCUC = DOMINIO_CARACTERES_CUC.get(llaveCUC);

            final String cadenaPosicion = valorLlaveCUC.toString();
            int valorPosicion = Integer.valueOf(cadenaPosicion);
            if (i == arregloCaracteresCUC.length - 1
                    || aumentarSiguienteCaracter) {
                valorPosicion += 1;
            }
            if (valorPosicion < CARACTERES_CUC.length) {
                arregloCaracteresCUC[i] = CARACTERES_CUC[valorPosicion];
                aumentarSiguienteCaracter = false;
            } else {
                arregloCaracteresCUC[i] = CARACTERES_CUC[BASE_INDICE_ARREGLOS];
                aumentarSiguienteCaracter = true;
            }
            i--;
        } while (i > -1 && aumentarSiguienteCaracter);

        if (!aumentarSiguienteCaracter) {
            cucIncrementado.append(arregloCaracteresCUC);

        } else {
            cucIncrementado = new StringBuffer(StringUtils.leftPad("",
                    longitudInc, "3"));
        }

        return cucIncrementado.toString();
    }

    /**
     * Incremente el n�mero consecutivo de los �ltimos 6 valores del numero de expediente.
     * Ejemplo: 000/PR/15/RBO/2011/C-12345
     * El metodo recibe el numero que es consecutivo "C-12345", donde su longitud y formato no cambia.
     * Pero se incrementa el consecutivo en 1.
     * Reglas:
     * 	El consecutivo debe de estar en el formato X-DDDDD  donde X es un caracter alfabetico en may�scula 
     * y D son digitos {0-9}.
     * 
     * Parte del n�mero de expediente que no se podr� cambiar en cuanto a la longitud: 
     *     - 6 caracteres para el consecutivo:
     *     		+ 1 Letra      
     *     		+ 5 d�gitos para el consecutivo.
     * @param ultimoConsecutivo   ultimo valor a que se aplicar� un incremento.
     * @return
     */
    public static String incrementarConsecutivoNumeroExpediente ( String ultimoConsecutivo) throws NSJPNegocioException{
    	String consecutivo = ultimoConsecutivo.substring(ultimoConsecutivo.indexOf( SEPARADOR_CONSECUTIVO ) + 1);
    	Long consecutivoLong = null;
		try {
			consecutivoLong = Long.parseLong(consecutivo);
		}catch (Exception e){
			throw new NSJPNegocioException(CodigoError.FORMATO);
		}
		Character letra = (ultimoConsecutivo.substring(ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO)-1, ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO) )).charAt(0);
		consecutivoLong = (consecutivoLong + 1) % CONSECUTIVO_NUMERICO;

		//Se llega al limite maximo
		if( consecutivoLong == 0){
			if (letra.equals('Z')) //Reiniciar letra
				letra = new Character('A');
			else
				letra = new Character((char)( letra +1));
		}
		String consecutivoNuevo = new String(consecutivoLong.toString());
        //Rellenar con ceros a la izquierda
		consecutivoNuevo=StringUtils.leftPad(consecutivoNuevo, LONGITUD_CONSECUTIVO_NUMERICO, '0');
    	return letra.toString() + SEPARADOR_CONSECUTIVO +  consecutivoNuevo;
    }
    
    /**
     * Incremente el n�mero consecutivo de los �ltimos 7 valores del numero de caso
     * Ejemplo: 01/02/XX/RBO/2011/CC-12345
     * El metodo recibe el numero que es consecutivo "CC-12345", donde su longitud y formato no cambia.
     * Pero se incrementa el consecutivo en 1.
     * Reglas:
     * 	El consecutivo debe de estar en el formato XX-DDDDD  donde X es un caracter alfabetico en may�scula 
     * y D son digitos {0-9}.
     * 
     * Parte del n�mero de caso que no se podr� cambiar en cuanto a la longitud: 
     *     - 7 caracteres para el consecutivo:
     *     		+ 2 Letra      
     *     		+ 5 d�gitos para el consecutivo.
     * @param ultimoConsecutivo   ultimo valor a que se aplicar� un incremento.
     * 		  01/02/XX/RBO/2011/CC-12345
     * @return
     */
    public static String incrementarConsecutivoNumeroCaso( String ultimoConsecutivo) throws NSJPNegocioException{
    	//String consecutivo = ultimoConsecutivo.substring(ultimoConsecutivo.indexOf( SEPARADOR_CONSECUTIVO ) + 1);
        String consecutivo = ultimoConsecutivo.substring(8);
    	int consecutivoLong = 0;
		/*try {
			consecutivoLong = Long.parseLong(consecutivo);
		}catch (Exception e){
			throw new NSJPNegocioException(CodigoError.FORMATO);
		}*/
		//Character letra = (ultimoConsecutivo.substring(ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO)-2, ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO)-1 )).charAt(0);
		//Character letra2 = (ultimoConsecutivo.substring(ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO)-1, ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO) )).charAt(0);
        logger.debug(consecutivo);
        //consecutivoLong = (consecutivoLong + 1) % CONSECUTIVO_NUMERICO;
        consecutivoLong = Integer.parseInt(consecutivo)+1;
        logger.debug(consecutivoLong+" numero consecutivo convertido a int");
        String consecutivoNuevo2 = "";
        String longCadena= new String(Integer.toString(consecutivoLong));
        for(int i=0; i<(consecutivo.length()-longCadena.length()); i++){
            consecutivoNuevo2=consecutivoNuevo2+0;
        }

        /*for(char x: consecutivo.toCharArray()){
           if(x=='0'){
                consecutivoNuevo2=consecutivoNuevo2+x;
           }
        }*/
            //Se llega al limite maximo
		/*if( consecutivoLong == 0){
			if (letra2.equals('Z') && letra.equals('Z')){
				letra = new Character('A');
				letra2 = new Character('A');				
			}				
			else
				if (letra2.equals('Z') && !letra.equals('Z')){
					letra = new Character((char)( letra +1));
					letra2 = new Character('A');
				}else
					letra2 = new Character((char)( letra2 +1));
					
		}*/
		//String consecutivoNuevo = new String(consecutivoLong.toString());
        //String consecutivoNuevo3 = new String( Integer.toString(consecutivoLong));
        consecutivoNuevo2 = consecutivoNuevo2 + longCadena;
        logger.debug(consecutivoNuevo2);
        //String consecutivoNuevo=consecutivoNuevo2;
        //Rellenar con ceros a la izquierda
		//consecutivoNuevo=StringUtils.leftPad(consecutivoNuevo, LONGITUD_CONSECUTIVO_NUMERICO, '0');
		//logger.debug(letra.toString() + letra2.toString() + SEPARADOR_CONSECUTIVO +  consecutivoNuevo);
    	//return letra.toString() + letra2.toString() + SEPARADOR_CONSECUTIVO +  consecutivoNuevo;
        //consecutivoNuevo= Integer.toString(consecutioInt);
        return  consecutivoNuevo2;
    }
    /* Funcion para incrementr el consecutivo del RAC R.H.R. 06-ene-2015 DOMOTICS */
    public static String incrementarConsecutivoNumeroExpediente2( String ultimoConsecutivo) throws NSJPNegocioException{
        //String consecutivo = ultimoConsecutivo.substring(ultimoConsecutivo.indexOf( SEPARADOR_CONSECUTIVO ) + 1);
        String consecutivo = ultimoConsecutivo.substring(16);
        int consecutivoLong = 0;
		/*try {
			consecutivoLong = Long.parseLong(consecutivo);
		}catch (Exception e){
			throw new NSJPNegocioException(CodigoError.FORMATO);
		}*/
        //Character letra = (ultimoConsecutivo.substring(ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO)-2, ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO)-1 )).charAt(0);
        //Character letra2 = (ultimoConsecutivo.substring(ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO)-1, ultimoConsecutivo.indexOf(SEPARADOR_CONSECUTIVO) )).charAt(0);
        logger.debug(consecutivo);
        //consecutivoLong = (consecutivoLong + 1) % CONSECUTIVO_NUMERICO;
        consecutivoLong = Integer.parseInt(consecutivo)+1;
        logger.debug(consecutivoLong+" numero consecutivo convertido a int");
        String consecutivoNuevo2 = "";
        String longCadena= new String(Integer.toString(consecutivoLong));
        for(int i=0; i<(consecutivo.length()-longCadena.length()); i++){
            consecutivoNuevo2=consecutivoNuevo2+0;
        }

        /*for(char x: consecutivo.toCharArray()){
           if(x=='0'){
                consecutivoNuevo2=consecutivoNuevo2+x;
           }
        }*/
        //Se llega al limite maximo
		/*if( consecutivoLong == 0){
			if (letra2.equals('Z') && letra.equals('Z')){
				letra = new Character('A');
				letra2 = new Character('A');
			}
			else
				if (letra2.equals('Z') && !letra.equals('Z')){
					letra = new Character((char)( letra +1));
					letra2 = new Character('A');
				}else
					letra2 = new Character((char)( letra2 +1));

		}*/
        //String consecutivoNuevo = new String(consecutivoLong.toString());
        //String consecutivoNuevo3 = new String( Integer.toString(consecutivoLong));
        consecutivoNuevo2 = consecutivoNuevo2 + longCadena;
        logger.debug(consecutivoNuevo2);
        //String consecutivoNuevo=consecutivoNuevo2;
        //Rellenar con ceros a la izquierda
        //consecutivoNuevo=StringUtils.leftPad(consecutivoNuevo, LONGITUD_CONSECUTIVO_NUMERICO, '0');
        //logger.debug(letra.toString() + letra2.toString() + SEPARADOR_CONSECUTIVO +  consecutivoNuevo);
        //return letra.toString() + letra2.toString() + SEPARADOR_CONSECUTIVO +  consecutivoNuevo;
        //consecutivoNuevo= Integer.toString(consecutioInt);
        return  consecutivoNuevo2;
    }
}
