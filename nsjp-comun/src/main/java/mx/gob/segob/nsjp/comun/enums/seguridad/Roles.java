/**
* Nombre del Programa : Roles.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Enumeración de los roles disponibles en el sistema
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
package mx.gob.segob.nsjp.comun.enums.seguridad;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración de los roles disponibles en el sistema
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public enum Roles {
	
	REGINICIAL(1L), 
	ADMINAT(2L), 
	ATPENAL(3L), 
	VISITADOR(4L), 
	FACILITADOR(5L), 
	COORDINADORJAR(6L), 
	AGENTEMP(7L), 
	COORDINADORAMP(8L), 
	POLICIAMINISTER(9L), 
	UAVD(10L), 
	ALMACENV(11L), 
	PERITOAMP(12L), 
	COORDINADORDEF(13L), 
	DEFENSOR(14L), 
	DEFENSORATE(15L), 
	ENCARGADOCAUSA(16L), 
	ENCARGADOSALA(17L), 
	ATENCIONPUBLICO(18L), 
	ENCARGADOINF(19L), 
	NOTIFICADORV(20L), 
	ENCARGADOSEGIN(21L), 
	TRANSCRIPTORPJ(22L), 
	ADMONPJ(23L), 
	JUEZPJ(24L), 
	ENCARGADODGEPMC(25L), 
	OFEJECUCION(26L), 
	ADMINEJECUCION(27L), 
	SSPPOLICIA(28L), 
	SSPMEDICO(29L), 
	SSPAGENTEMP(30L), 
	SSPPROCESAL(31L), 
	SSPDIRPROC(32L), 
	SSPDIRCEDE(33L), 
	SERVICIOSPERICI(34L), 
	COORDINADORPER(35L),
	ADMINISTRADOR(36L),
	PERITODEF(37L),
	COORDPERFIS(38L),
	UNIDADCAPTURA(39L),
	JUEZEJECUCIONPJ(40L),
	SSPEPRS(41L),
	COORDINADORVIS(42L),
	UNIDADCAPTURAPG(43L),
	UAVDATNPSICOLOGICA(44L),
	UAVDJURIDICO(46L),
	UAVDTRABAJOSOCIAL(47L),
	AGENTEMPSISTRAD(48L);
	
	private Long valorId;

    private final static Map<Long, Roles> hash = new HashMap<Long, Roles>();

    static {
    	Roles[] acts = Roles.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private Roles(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static Roles getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
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
