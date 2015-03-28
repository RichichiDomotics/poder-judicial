/**
* Nombre del Programa : RegistrarSolicitudTransferenciaImputadoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria del servicio RegistrarSolicitudTransferenciaImputadoServiceImpl
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudTransferenciaImputadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria del servicio RegistrarSolicitudTransferenciaImputadoServiceImpl.
 * @author cesarAgustin
 *
 */
public class RegistrarSolicitudTransferenciaImputadoServiceImplTest extends
		BaseTestServicios<RegistrarSolicitudTransferenciaImputadoService> {

	public void testRegistrarSolicitudTrasladoImputado() {
		SolicitudTrasladoImputadoDTO solDTO = new SolicitudTrasladoImputadoDTO();
		
		solDTO.setInvolucrado(new InvolucradoDTO(new Long(4)));
		solDTO.setAudiencia(new AudienciaDTO(new Long(103)));
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(new Long(1));
		solDTO.setExpedienteDTO(expedienteDTO);
		solDTO.setUsuarioSolicitante(new FuncionarioDTO(new Long(10)));
		solDTO.setInstitucion(new ConfInstitucionDTO(new Long(Instituciones.PJ.getValorId())));
		solDTO.setNumeroCasoAsociado("YUC/PJ/XX/PGE/2011/AA-00000");
		solDTO.setMotivo("Prueba unitaria");
		solDTO.setFechaLimite(new Date());
		solDTO.setFechaModificacion(new Date());
		solDTO.setFechaCierre(new Date());
		solDTO.setNombreSolicitante("Cesar Agustin Soto");
		solDTO.setEsUrgente(false);
		solDTO.setFolioSolicitud("0001");
		solDTO.setFechaTraslado(new Date());
		solDTO.setTiempo(23.12);
		
		try {
			SolicitudTrasladoImputadoDTO respuesta = service.registrarSolicitudTrasladoImputado(solDTO);
			
			assertTrue("El id de la solicitud debe ser mayor a cero :: ",respuesta.getDocumentoId()>0);
			logger.info("El id de la solicitud debe ser mayor a cero :: "+respuesta.getDocumentoId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
