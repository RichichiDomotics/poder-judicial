/**
* Nombre del Programa : RegistrarUnidadIEspecializadaService.java
* Autor                            : AdrianECH
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/03/2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.catalogo;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * Fecha: 06/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
public interface RegistrarUnidadIEspecializadaService {

	/**
	 * Metodo que permite registrar / modificar una unidad de investigacion especializada
	 * @param especializadaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	CatUIEspecializadaDTO registrarUnidadIEspecializada(
			CatUIEspecializadaDTO especializadaDTO)throws NSJPNegocioException;

}
