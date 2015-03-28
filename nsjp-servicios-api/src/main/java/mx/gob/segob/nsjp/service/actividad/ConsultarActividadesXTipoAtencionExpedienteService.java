/**
 * Nombre del Programa 			: ConsultarActividadesXTipoAtencionExpedienteService
 * Autor                        : Alejandro Galaviz
 * Compania                     : Ultrasist
 * Proyecto                     : NSJP                    Fecha: 20-02-2012
 * Marca de cambio        		: N/A
 * Descripcion General    		: N/A
 * Programa Dependient    		:N/A
 * Programa Subsecuente  		:N/A
 * Cond. de ejecucion     		:N/A
 * Dias de ejecucion      		:N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           			:N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.actividad;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;


/**
 * @version 1.0
 * @author AlejandroGA
 *
 */
public interface ConsultarActividadesXTipoAtencionExpedienteService {
	
	/**
	 * Consulta si en la tabla de actividades se encuentra una actividad que corresponda con
	 * un Tipo de atencion (TiposAtencion) y con un expedienteId. Si encuentra alguna ocurrencia
	 * devuelte el numero de veces que se encontro, de no ser asi devuelve cero
	 */

	Long consultarNumeroDeActividadesXTipoAtencion(Long expedienteId,Long tipoAtencionId) throws  NSJPNegocioException;
}
