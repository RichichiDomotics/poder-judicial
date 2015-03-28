/**
* Nombre del Programa : GuardarNotificacionServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 04/10/2011
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
package mx.gob.segob.nsjp.service.test.notificacion.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.notificacion.GuardarNotificacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class GuardarNotificacionServiceImplTest extends BaseTestServicios<GuardarNotificacionService>  {
	
	public void testGuardarYEnviarNotificacionAMismaInstitucion(){
		
		Long numeroExpedienteId = 407L; 
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(numeroExpedienteId);
//		expedienteDTO.setArea(new AreaDTO(Areas.VISITADURIA));
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setAreaActual(new AreaDTO(Areas.VISITADURIA));
		FuncionarioDTO funcionario = new FuncionarioDTO(42L);
		funcionario.setPuesto(new ValorDTO(2110L));
		usuario.setFuncionario(funcionario);
		expedienteDTO.setUsuario(usuario );
		
		DocumentoDTO documentoDTO = new DocumentoDTO();
		documentoDTO.setTextoParcial("Texto parcial");
		documentoDTO.setFechaCreacion(new Date());
		documentoDTO.setFormaDTO(new  FormaDTO(4L));
		documentoDTO.setNombreDocumento("NOTIFICACION DE AUDIENCIA");
		documentoDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.NOTIFICACION.getValorId()));
		//Archivo digital pendiente
		
		
		try {
			Long idNotificacion = service.guardarYEnviarNotificacionAMismaInstitucion(expedienteDTO, documentoDTO);
			logger.info(" idNotificacion:"+ idNotificacion);
			
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
}
