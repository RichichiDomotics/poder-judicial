/**
* Nombre del Programa : TipoDefensoria.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Enumeracion para los tipos de defensoria
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
 * Enumeracion para los tipos de defensoria
 * @version 1.0
 * @author Tattva-IT
 *
 */
public enum TipoDefensoria {
	INTEGRACION(2068L), TECNICA(2006L), RESTAURATIVA(2008L), EXTERNA(2010L), ASESORIA(2743L);

	private Long valorId;
	
	private final static Map<Long, TipoDefensoria> hash = new HashMap<Long, TipoDefensoria>();
	

	static{
		TipoDefensoria[] acts = TipoDefensoria.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }

	}
	
    private TipoDefensoria(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static TipoDefensoria getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
	
	public Long getValorId() {
		return valorId;
	}

}
