/**
* Nombre del Programa : ConsultarUltimoAcuseService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultas de Documento
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato del servicio de negocio para Generar un Numero de Acuse de Atencion
 * @version 1.0
 * @author rgama
 *
 */
public interface GenerarNumeroDeAcuseDeAtencionService {
	
	/**
	 * Metodo que permite Generar un numero de acuse de atencion, ademas permite
	 * asociar una instituacion al acuse
	 * @param idInstitucion
	 * @return numero de folio asignado al acuse registrado
	 * @throws NSJPNegocioException
	 */
	public Long generarNumeroDeAcuse(Long idInstitucion,Long idSolicitud) throws NSJPNegocioException;

	/**
	 * Metodo que permite consultar el Identificador del Ultimo Acuse de recibo
	 * @return Identificador del Ultimo Acuse de recibo
	 * o 1 en caso de no haber registros de Acuse de recibo.
	 * @throws NSJPNegocioException
	 */
	public Long consultarUltimoAcuse() throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de asignar el siguiente número de acuse.
	 * Registra la fecha actual en el número de Acuse que genera.
	 * 
	 * Recibe el número anterior para generar el consecutivo.
	 * @return El Identificador del Acuse de Recibo registrado
	 * @throws NSJPNegocioException
	 */
	public Long asignarNumeroAcuse(Long folioAnterior,Long idSolicitud) throws NSJPNegocioException;

	/**
	 * Metodo que realiza la funcionalidad de asociar la Institución al acuse de recibo que se ha asignado. 
	 * @param idInstitucion 
	 * @param idAcuseDeRecibo
	 * @throws NSJPNegocioException
	 */
	public void asociarInstitucion(Long idInstitucion, Long idAcuseDeRecibo) throws NSJPNegocioException;

}
