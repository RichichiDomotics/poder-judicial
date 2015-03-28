/**
* Nombre del Programa : AvisoDetencionServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Jul 2011
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
package mx.gob.segob.nsjp.service.test.avisodetencion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.avisodetencion.AvisoDetencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias para los metodos del servici de Aviso Detencion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AvisoDetencionServiceImplTest extends
		BaseTestServicios<AvisoDetencionService> {

	public void testConsultarAvisosDetencionHistoricoByEstatus () {
		try {
			List<AvisoDetencionDTO> respuesta = service.consultarAvisosDetencionHistoricoByEstatus(EstatusNotificacion.ATENDIDA);
			assertTrue("La lista dbe retornar minimo un registro : ", respuesta.size()>0);
			logger.info("La lista dbe retornar minimo un registro : "+ respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testAtenderAvisoDetencion () {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setFuncionario(new FuncionarioDTO(1L));
		
		AvisoDetencionDTO avisoDetencionDTO = new AvisoDetencionDTO();
		avisoDetencionDTO.setDocumentoId(50L);
		avisoDetencionDTO.setUsuario(usuarioDTO);
		
		try {
			AvisoDetencionDTO respuesta = service.atenderAvisoDetencion(avisoDetencionDTO);
			assertNotNull("El avisto detencion no puede ser nulo : ", respuesta);
			logger.info("-----------------------------");
			logger.info("Identificaro Avido : "+respuesta.getDocumentoId());
			logger.info("Identificaro Expediente : "+respuesta.getExpedienteDTO().getExpedienteId());
			logger.info("-----------------------------");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}
