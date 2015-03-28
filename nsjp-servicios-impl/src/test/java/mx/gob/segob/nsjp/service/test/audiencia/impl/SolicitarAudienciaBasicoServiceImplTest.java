/**
* Nombre del Programa : SolicitarAudienciaBasicoServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24/06/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaWSDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.service.audiencia.SolicitarAudienciaBasicoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias para el servicio de registro de solicitudes ya sea internas o externas
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class SolicitarAudienciaBasicoServiceImplTest extends BaseTestServicios<SolicitarAudienciaBasicoService>{

	public void testSolicitarAudienciaInterna(){
		SolicitudAudienciaBasicoWSDTO solicitud = new SolicitudAudienciaBasicoWSDTO();
		
		
		try {
			solicitud.setAreaSolicitanteId(1L);
			solicitud.setAudiencia(new AudienciaWSDTO());
			solicitud.getAudiencia().setDuracionEstimada(120);
			solicitud.getAudiencia().setFechaHoraAudiencia(DateUtils.obtener("24/07/2011", "09:00"));
			solicitud.getAudiencia().setEstatusAudienciaId(EstatusAudiencia.SOLICITADA.getValorId());
			solicitud.setFechaLimite(DateUtils.obtener("30/08/2011"));
			solicitud.getAudiencia().setMotivo("Toma de declaración");
			solicitud.setNombreSolicitante("JUAN BARRAZA BARRAZA");
			solicitud.setNumeroCasoAsociado("YUCPROC2011333333");
			solicitud.setSolicitanteExternoId(1L);
			solicitud.getAudiencia().setTipoAudienciaId(TipoAudiencia.VINCULACION.getValorId());
			
			Long id = service.registrarSolicitudAudienciaBasico(solicitud);
			id = service.registrarSolicitudAudienciaBasico(solicitud);
			id = service.registrarSolicitudAudienciaBasico(solicitud);
			
			
			assertNotNull("ID de retorno nulo",id);
			
		} catch (NSJPNegocioException e) {
			logger.error(e);
			e.printStackTrace();
			assertNotNull("Excepción generada",null);
		}
		
		
		
	}
}
