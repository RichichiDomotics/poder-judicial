/**
* Nombre del Programa : TiempoTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Convierte objetos TiempoDTO a Tiempo y viceversa
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
package mx.gob.segob.nsjp.service.hecho.impl.transform;

import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Convierte objetos TiempoDTO a Tiempo y viceversa.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class TiempoTransformer {

	public static Tiempo transformarTiempo (TiempoDTO tiempoDTO) {
		Tiempo tiempo = new Tiempo();

		tiempo.setTiempoId(tiempoDTO.getTiempoId());
		tiempo.setDescripcion(tiempoDTO.getDescripcion());
		tiempo.setFechaInicio(tiempoDTO.getFechaInicio());
		tiempo.setFechaFin(tiempoDTO.getFechaFin());
		
		if (tiempoDTO.getTipoRegistro()!=null) 
			tiempo.setTipoRegistro(new Valor(tiempoDTO.getTipoRegistro().getIdCampo()));
			
		return tiempo;
	}
	
	public static Tiempo transformarTiempoUpdate (Tiempo tiempoBD, Tiempo tiempoMod ) {
		//Id es consultado de BD: TiempoBD
		tiempoBD.setDescripcion(tiempoMod.getDescripcion());
		tiempoBD.setFechaInicio(tiempoMod.getFechaInicio());
		tiempoBD.setFechaFin(tiempoMod.getFechaFin());
		
		if (tiempoMod.getTipoRegistro()!=null) 
			tiempoBD.setTipoRegistro(tiempoMod.getTipoRegistro());
			
		return tiempoBD;
	}
	
}
