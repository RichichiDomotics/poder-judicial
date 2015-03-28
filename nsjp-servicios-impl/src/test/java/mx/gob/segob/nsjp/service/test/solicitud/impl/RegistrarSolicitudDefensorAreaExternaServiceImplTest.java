/**
* Nombre del Programa : RegistrarSolicitudDefensorAreaExternaServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/06/2011
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

import java.util.ArrayList;
import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaWSDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorWSDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudDefensorAreaExternaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias del servicio de negocio para el registro de solicitudes de defensor desde otras áreas
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class RegistrarSolicitudDefensorAreaExternaServiceImplTest extends BaseTestServicios<RegistrarSolicitudDefensorAreaExternaService>{

	public void testRegistroSolicitudSalaNormal(){
		
		SolicitudDefensorWSDTO solicitud = new SolicitudDefensorWSDTO();
		
		solicitud.setAreaSolicitanteId(4L);
		solicitud.setAudiencia(new AudienciaWSDTO());
		solicitud.setFechaLimite(new Date());
		solicitud.setNombreSolicitante("Regina Morales Salmerón");
		solicitud.setNombreImputado("Daniel");
		solicitud.setApellidoPaternoImputado("Jiménez");
		solicitud.setApellidoMaternoImputado("Ventura");
		solicitud.setNumeroCasoAsociado("YUC/PJ/XX/PGE/2011/AA-00004");
		solicitud.setSolicitanteExternoId(2L);
		solicitud.setFolioSolicitud("PJ/201100005");
		solicitud.getAudiencia().setFechaHoraAudiencia(DateUtils.obtenerNulleable("26/08/2011", "16:00"));
		
		solicitud.getAudiencia().setDomicilioSala("Insurgentes 850");
		solicitud.getAudiencia().setDuracionEstimada(30);
		solicitud.getAudiencia().setEstatusAudienciaId(EstatusAudiencia.EN_PROCESO.getValorId());
		solicitud.getAudiencia().setFechaAsignacionSala(new Date());
		solicitud.getAudiencia().setMotivo(null);
		solicitud.getAudiencia().setNombreSala("Sala 1");
		solicitud.getAudiencia().setSalaTemporal(false);
		solicitud.getAudiencia().setTipoAudienciaId(TipoAudiencia.CATEO.getValorId());
		solicitud.getAudiencia().setUbicacionSala("Piso 8");
		
		solicitud.setDelitos(new ArrayList<DelitoWSDTO>());
		DelitoWSDTO delito = new DelitoWSDTO();
		delito.setIdCatDelito(3L);
		solicitud.getDelitos().add(delito);
//		delito = new DelitoWSDTO();
//		delito.setIdCatDelito(2L);
//		solicitud.getDelitos().add(delito);
		
		try {
			solicitud = service.registrarSolicitudDefensor(	solicitud);
			assertNotNull(solicitud.getSolicitudId());
			logger.debug(solicitud);
		} catch (NSJPNegocioException e) {
			assertNotNull(null);
			e.printStackTrace();
		}
	}
	
	
}
