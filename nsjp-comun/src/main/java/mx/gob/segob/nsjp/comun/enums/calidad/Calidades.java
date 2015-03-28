/**
 * Nombre del Programa : Calidades.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeracion para el manejo de las calidades.
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
package mx.gob.segob.nsjp.comun.enums.calidad;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeracion para el manejo de las calidades..
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum Calidades {
    SIN_CALIDAD_JURIDICA(212L),
    // persona,
    PROBABLE_RESPONSABLE_PERSONA(213L),
    // persona,
    VICTIMA_PERSONA(214L),
    // persona
    DENUNCIANTE(215L),
    // persona
    TESTIGO(216L),
    // persona
    REPRESENTANTE_LEGAL(217L),
    // persona
    CONTACTO_ORGANIZACION(218L),
    // persona
    TUTOR(219L),
    // persona
    TRADUCTOR(220L),
    // persona
    QUIEN_DETUVO(221L),
    // organizacion,
    PROBABLE_RESPONSABLE_ORGANIZACION(222L),
    // organizacion,
    DENUNCIANTE_ORGANIZACION(223L),
    // objeto
    EVIDENCIA(224L),
    // objeto
    PRUEBA(225L),
    // objeto
    PERTENENCIA(226L),
    // objeto
    INSTRUMENTO(227L),
    // Lugar
    LUGAR_HECHOS(228L),
    // Lugar
    DOMICILIO(229L),
    // Persona
    SOLICITANTE(1584l),
    //Defensor
    DEFENSOR_PUBLICO(2508L),
    DEFENSOR_PRIVADO(2507L), 
	DEFENDIDO(2848L);


    private final static Map<Long, Calidades> hash = new HashMap<Long, Calidades>();
    
    private Long valorId;

    static {
    	Calidades[] objs = Calidades.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
	 public static Calidades getByValor(Long valorIdPredefinido) {
	        return hash.get(valorIdPredefinido);
	    }
    private Calidades(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    /**
     * Método de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en le BD.
     */
    public Long getValorId() {
        return valorId;
    }
}
