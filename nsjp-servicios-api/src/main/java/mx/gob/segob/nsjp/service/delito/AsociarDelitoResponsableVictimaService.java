/**
* Nombre del Programa : AsociarDelitoResponsableVictimaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/07/2011
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
package mx.gob.segob.nsjp.service.delito;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface AsociarDelitoResponsableVictimaService {

	/**
	 * Realiza la asociación de un delito con el probable responsable y la víctima que afecta.
	 * Puede no existir víctima a registrar, como se puede no indicar si es "bien Tutelado"
	 * 
	 * @param delito: Identificador del delito
	 * @param responsable: Identificador del involucrado como probable responsable
	 * @param victima: Identificador del involucrado como víctima
	 * @param formaParticipacion:
	 * @param bienTutelado:
	 * @throws NSJPNegocioException
	 */
	Long asociarDelitoResponsableVictima(Long asociacionID,DelitoDTO delito,
			InvolucradoDTO responsable, InvolucradoDTO victima,
			Long formaParticipacion, Long bienTutelado, Long idClasificacion, Long idLugar,
			Long idModalidad,Long idModus, Long idCausa)throws NSJPNegocioException;

	/**
	 * Servicio que se encarga de verificar si existe, en BD, la relación
	 * -Con el delito
	 * -El probable responsable
	 * -La víctima
	 * -La forma de participación 
	 * 
	 * @param idDelito
	 * @param idProbableResponsable
	 * @param idVictima
	 * @param idFormaParticipacion
	 * @return
	 * @throws NSJPNegocioException
	 */
	Boolean existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(Long idDelito,
			Long idProbableResponsable, Long idVictima, Long idFormaParticipacion)
			throws NSJPNegocioException;
	
}
