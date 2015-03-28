/**
* Nombre del Programa : CentroDetencionTransformer
* Autor                            : Cuauhtemoc Paredes
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/09/2011
* Marca de cambio        : N/A
* Descripcion General    : Transforma el objeto CentroDetencion a CentroDetencionDTO
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

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.detencion.DireccionDTO;
import mx.gob.segob.nsjp.model.CentroDetencion;

public class CentroDetencionTransformer {

	public static CentroDetencionDTO transformar(CentroDetencion centroDetencion) {
		
		CentroDetencionDTO centro = new CentroDetencionDTO();
		
		centro.setCentroDetencionId(centroDetencion.getCentroDetencionId());
		DireccionDTO direccion = DireccionTransformer.transformar(centroDetencion.getDireccion());
		centro.setDireccion(direccion);
		centro.setNombre(centroDetencion.getNombre());
		centro.setNombreDirector(centroDetencion.getNombreDirector());
		centro.setTipo(new ValorDTO(centroDetencion.getTipo().getValorId(), centroDetencion.getTipo().getValor()));
		
		return centro;
	}

	/**
	 * M&eacute;todo que <strong>SOLO</strong> recupera el id del centro de deten&oacute; del DTO al Entity.
	 * @param centroDetencionDTO
	 * @return
	 */
	
	public static CentroDetencion transformar(CentroDetencionDTO centroDetencionDTO) {
		CentroDetencion centro = new CentroDetencion();		
		centro.setCentroDetencionId(centroDetencionDTO.getCentroDetencionId());
		return centro;
}
	
}
