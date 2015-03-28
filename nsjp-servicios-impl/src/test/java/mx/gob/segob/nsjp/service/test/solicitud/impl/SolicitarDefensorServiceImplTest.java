/**
* Nombre del Programa : SolicitarDefensorServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de solicitar defensor
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.solicitud.SolicitarDefensorService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de solicitar defensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class SolicitarDefensorServiceImplTest extends BaseTestServicios<SolicitarDefensorService> {

	public void testSolicitarDefensor () {
		SolicitudDefensorDTO solicitudDefensorDTO = new SolicitudDefensorDTO();
		
//		solicitudDefensorDTO.setEstatus("asignar");
		solicitudDefensorDTO.setNumeroCasoAsociado("YUCPROC2011000002");
		solicitudDefensorDTO.setTipoSolicitudDTO(new ValorDTO(100L));
		solicitudDefensorDTO.setAreaDestino(1L);
		
		try {
			SolicitudDefensorDTO respuesta = service.solicitarDefensor(solicitudDefensorDTO);
			assertTrue("El id de la respuesta no puede ser menor a 0 : ", respuesta.getDocumentoId()>=0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());			
		}
	}
	
	public void testConsultarSolDefensorAsignadas () {
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setClaveFuncionario(14L);
		
		try {
			List<SolicitudDefensorDTO> respuesta = service.consultarSolDefensorAsignadas(funcionarioDTO);
			logger.info("NUMERO DE SOLICITUDES :: "+respuesta.size());
			assertTrue("La lista debe tener minomo un registro : ", respuesta.size()>0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testReasignarDefensorAExpediente() {
		try {
			service.reasignarDefensorAExpediente(new ExpedienteDTO(40L), new FuncionarioDTO(9L));
		} catch (NSJPNegocioException e) {
			fail();
			logger.error(e.getMessage());
		}
	}
	
}
