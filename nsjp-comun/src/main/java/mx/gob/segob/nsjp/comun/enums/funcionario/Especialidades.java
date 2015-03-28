/**
* Nombre del Programa : Especialidades.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Enum para los valores de especialidades de un funcionario
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
package mx.gob.segob.nsjp.comun.enums.funcionario;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum para los valores de especialidades de un funcionario
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public enum Especialidades {
	JUEZ_AUDIENCIA(2004L), 
	MECANICA_FORENSE(1968L), 
	MEDICINA_FORENSE(1970L),
	MINISTERIO_PUBLICO(2002L),
	DEFENSA_TECNICA(2006L),
	DEFENSA_RESTAURATIVA(2008L),
	DEFENSA_EXTERNA(2010L),
	DEFENSA_INTEGRACION(2068L),
	
	//ESPECIALIDADES PARA PERITO
	ATENCION_MEDICA(3976L),
	ATENCION_PSICOLOGICA(3978L),
	ATENCION_DE_SERVICIO_SOCIAL(3980L);
	
	
    private Long valorId;
    private final static Map<Long, Especialidades> hash = new HashMap<Long, Especialidades>();

    static {
    	Especialidades[] acts = Especialidades.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private Especialidades(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static Especialidades getByValor(Long valorIdPredefinido) {
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
