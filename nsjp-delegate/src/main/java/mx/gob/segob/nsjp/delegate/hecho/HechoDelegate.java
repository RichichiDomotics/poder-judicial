/**
* Nombre del Programa : HechoDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del Delegate para los servicios relacionados con Hecho
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
package mx.gob.segob.nsjp.delegate.hecho;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;

/**
 * Contrato del Delegate para los servicios relacionados con Hecho.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface HechoDelegate {

	/**
	 * Ingresa un hecho para ligarlo a un expedinte y lugar.
	 * @param hechoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public HechoDTO ingresarHecho (HechoDTO hechoDTO) throws NSJPNegocioException;
	/**
	 * Modifica un hecho previamente registrado identificándolo por su ID
	 * @param hechoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public HechoDTO modificarHecho(HechoDTO hechoDTO)throws NSJPNegocioException;
	/**
	 * Permite consultar un hecho por ID o por Expediente o por Lugar o por Tiempo
	 * @param hechoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<HechoDTO> consultarHechos(HechoDTO hechoDTO)throws NSJPNegocioException;
}
