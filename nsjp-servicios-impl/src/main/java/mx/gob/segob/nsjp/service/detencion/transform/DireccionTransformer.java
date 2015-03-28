/**
* Nombre del Programa : DireccionTransformer
* Autor                            : Cuauhtemoc Paredes
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/09/2011
* Marca de cambio        : N/A
* Descripcion General    : Transforma el objeto Direccion a DireccionDTO
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
package mx.gob.segob.nsjp.service.detencion.transform;

import mx.gob.segob.nsjp.dto.detencion.DireccionDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.model.Direccion;
import mx.gob.segob.nsjp.service.lugar.impl.transform.AsentamientoTransformer;

public class DireccionTransformer {
	
	public static DireccionDTO transformar(Direccion direccion) {
		DireccionDTO direccionDTO = new DireccionDTO();
		AsentamientoDTO asentamiento = AsentamientoTransformer.transformarAsentamiento(direccion.getAsentamiento());		
		direccionDTO.setAsentamiento(asentamiento);
		direccionDTO.setCalle(direccion.getCalle());
		direccionDTO.setDireccionId(direccion.getDireccionId());
		direccionDTO.setEntreCalle1(direccion.getEntreCalle1());
		direccionDTO.setEntreCalle2(direccion.getEntreCalle2());
		direccionDTO.setNumeroExterior(direccion.getNumeroExterior());
		direccionDTO.setNumeroInterior(direccion.getNumeroInterior());
		direccionDTO.setNumeroLote(direccion.getNumeroLote());
		direccionDTO.setReferencias(direccion.getReferencias());		
				
		return direccionDTO;
	}

}
