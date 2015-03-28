/**
* Nombre del Programa : ConsultarTipoSolicitudAudienciaServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/10/2011
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
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.service.solicitud.ConsultarTipoSolicitudAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de Preubas Unitarias para los servicios de Consultar Tipo de
 * Solicitudes de Audiencias.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarTipoSolicitudAudienciaServiceImplTest extends
	BaseTestServicios<ConsultarTipoSolicitudAudienciaService>{

	public void testConsultarTipoSolicitudAudienciaSiguientes(){
		
		//GrupoA
//		TipoAudiencia audienciaActual = TipoAudiencia.IMPUTACION;
		//GrupoB
//		TipoAudiencia audienciaActual = TipoAudiencia.ABREVIADO;
		//GrupoC
		TipoAudiencia audienciaActual = TipoAudiencia.INTERMEDIA;
		//GrupoD
//		TipoAudiencia audienciaActual = TipoAudiencia.JUICIO_ORAL;
		//GrupoE
//		TipoAudiencia audienciaActual = TipoAudiencia.LECTURA;
		//GrupoF
//		TipoAudiencia audienciaActual = TipoAudiencia.EJECUCION; //Asset Valido no regresa la lista vacia
		//GrupoG
//		TipoAudiencia audienciaActual = TipoAudiencia.CATEO;
//		TipoAudiencia audiencia = TipoAudiencia.getByValor(1715L);
//		logger.info("TipoAudiencia: "+ audiencia);
		
		List<CatalogoDTO> tiposSolicitudes;
		try {
			tiposSolicitudes = service.consultarTipoSolicitudAudienciaSiguientes(audienciaActual);
			assertFalse("La lista debe de contener al menos un dato.", tiposSolicitudes.isEmpty());
			logger.info(" Numero de Tipos: "+ tiposSolicitudes.size());
			for (CatalogoDTO catalogoDTO : tiposSolicitudes) {
				logger.info(" catalogoDTO:"+ catalogoDTO);
			}
			
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(),e);
		}
		
		
	}
}
