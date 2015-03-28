/**
* Nombre del Programa : EventoCitaTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Convierte de Tarea a TareaDTO
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
package mx.gob.segob.nsjp.service.tarea.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.model.Tarea;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Convierte de Tarea a TareaDTO.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class TareaTransformer {

	/**
	 * 
	 * @param tareaDTO
	 * @return
	 */
	public static Tarea transfromarTarea(TareaDTO tareaDTO) {
		Tarea tarea = new Tarea();
		
		tarea.setNtiempoReal(tareaDTO.getNtiempoReal());
		tarea.setValor(new Valor(tareaDTO.getValor().getIdCampo()));
		
		if (tareaDTO.getEventoCita()!=null)
			tarea.setEventoCita(EventoCitaTransformer.transformarEventoCita(tareaDTO.getEventoCita()));
		return tarea;
	}

	/**
	 * 
	 * @param tarea
	 * @return
	 */
	public static TareaDTO transfromarTarea(Tarea tarea) {
		TareaDTO tareaDTO = new TareaDTO();
		if(tarea != null){
			tareaDTO.setTareaId(tarea.getTareaId());
			tareaDTO.setNtiempoReal(tarea.getNtiempoReal());
			tareaDTO.setValor(new ValorDTO(tarea.getValor().getValorId(), tarea.getValor().getValor()));
			
			if (tarea.getEventoCita()!=null) {
				tareaDTO.setEventoCita(EventoCitaTransformer.transformarEventoCita(tarea.getEventoCita()));
			}
			
		}
		
		return tareaDTO;
	}

}
