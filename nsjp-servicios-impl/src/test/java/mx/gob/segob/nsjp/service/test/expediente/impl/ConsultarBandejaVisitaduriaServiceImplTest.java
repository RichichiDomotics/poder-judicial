/**
* Nombre del Programa : ConsultarBandejaVisitaduriaServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/09/2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.RelNumExpedienteAuditoriaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.ConsultarBandejaVisitaduriaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias de los Servicios para consultar la Bandeja de Visitaduria 
 * @version 1.0
 * @author GustavoBP
 */
public class ConsultarBandejaVisitaduriaServiceImplTest extends BaseTestServicios<ConsultarBandejaVisitaduriaService> {


	public void testConsultarNumeroVisitaduriaPorFiltro(){
		RelNumExpedienteAuditoriaDTO filtroDTO = new RelNumExpedienteAuditoriaDTO();
//		filtroDTO.setRelNumExpedienteAuditoriaId(4L);
		
		//ADVERTENCIA: Si se instancia el objeto nuevo de NumeroExpediente, se settea en estatus Abierto por default.
		//DATOS DE PRUEBA NUMERO EXPEDIENTE AUDITADO
		ExpedienteDTO  numExpedienteAuditado =  new ExpedienteDTO();
//		numExpedienteAuditado.setEstatus(new ValorDTO(EstatusExpediente.ABIERTO.getValorId()));
		numExpedienteAuditado.setEstatus(null);
//		numExpedienteAuditado.setNumeroExpedienteId(10L);
		AreaDTO jerarquiaOrganizacional = new AreaDTO(44L);
		numExpedienteAuditado.setArea(jerarquiaOrganizacional);
		
//		UsuarioDTO usuario = new UsuarioDTO();
//		FuncionarioDTO funcionario = new FuncionarioDTO(10L);
//		usuario.setFuncionario(funcionario);
//		numExpedienteAuditado.setUsuario(usuario );
		
		//DATOS DE PRUEBA NUMERO EXPEDIENTE CARPETA DE AUDITORIA
		ExpedienteDTO numCarpetaAuditoria = new ExpedienteDTO(); 
		numCarpetaAuditoria.setEstatus(new ValorDTO(EstatusExpediente.ABIERTO.getValorId()));
//		numCarpetaAuditoria.setEstatus(null);
//		numCarpetaAuditoria.setNumeroExpedienteId(187L);
//		AreaDTO jerarquiaOrganizacional = new AreaDTO(16L);
//		numCarpetaAuditoria.setArea(jerarquiaOrganizacional);
		
		UsuarioDTO usuario = new UsuarioDTO();
		FuncionarioDTO funcionario = new FuncionarioDTO(4L);
//		usuario.setFuncionario(funcionario);
//		numCarpetaAuditoria.setUsuario(usuario );
		
		filtroDTO.setNumeroExpediente(numExpedienteAuditado);
		filtroDTO.setNumeroAuditoria(numCarpetaAuditoria);
		
//		filtroDTO.setEstatus(new ValorDTO(EstatusExpediente.ABIERTO.getValorId()));
		
		try {
			List<RelNumExpedienteAuditoriaDTO> resultados = service.consultarNumeroVisitaduriaPorFiltro(filtroDTO);
			assertTrue("La lista debe tener minimo un registro : ", resultados.size()>0);
			logger.info("Número de registros : " + resultados.size());
			for (RelNumExpedienteAuditoriaDTO relNumExpedienteAuditoriaDTO : resultados) {
				logger.info(" ********************");
				logger.info(" RelNumExpedienteAuditoriaId:"+ relNumExpedienteAuditoriaDTO.getRelNumExpedienteAuditoriaId());
				logger.info(" NumeroExpediente:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente());
				if(relNumExpedienteAuditoriaDTO.getNumeroExpediente()!= null){
					logger.info(" ExpedienteId:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getExpedienteId());
					logger.info(" NumeroExpedienteId:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getNumeroExpedienteId());
					logger.info(" NumeroExpediente:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getNumeroExpediente());
					logger.info(" Usuario:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getUsuario());
					if(relNumExpedienteAuditoriaDTO.getNumeroExpediente().getUsuario()!=null){
						logger.info(" Funcionario:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getUsuario().getFuncionario());
						logger.info(" Funcionario -ClaveFuncionario:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getUsuario().getFuncionario()!=null? 
								relNumExpedienteAuditoriaDTO.getNumeroExpediente().getUsuario().getFuncionario().getClaveFuncionario():"NA");
					}
					
					logger.info(" DelitoPrincipal:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getDelitoPrincipal());
					logger.info(" Estatus:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getEstatus());
					logger.info(" Origen:"+ relNumExpedienteAuditoriaDTO.getNumeroExpediente().getOrigen());
				}
				logger.info(" NumeroAuditoria:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria());
				if(relNumExpedienteAuditoriaDTO.getNumeroAuditoria()!= null){
					logger.info(" AUNumeroExpedienteId:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getExpedienteId());
					logger.info(" AUNumeroExpedienteId:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getNumeroExpedienteId());
					logger.info(" AUNumeroExpediente:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getNumeroExpediente());
					logger.info(" AUUsuario:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getUsuario());
					if(relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getUsuario()!=null){
						logger.info(" AUUFuncionario:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getUsuario().getFuncionario());
						logger.info(" AUUFuncionario -ClaveFuncionario:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getUsuario().getFuncionario()!=null? 
								relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getUsuario().getFuncionario().getClaveFuncionario():"NA");
					}
					logger.info(" DelitoPrincipal:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getDelitoPrincipal());
					logger.info(" Estatus:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getEstatus());
					logger.info(" Origen:"+ relNumExpedienteAuditoriaDTO.getNumeroAuditoria().getOrigen());
				}
			}
			logger.info("Número de registros : " + resultados.size());
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	public void testConsultarFuncionariosPorEstatusDeparamento(){
		Long idEstatus = 1712L;
		Long idDepartamento = null; //10L;
		
		try {
			List<FuncionarioDTO> resultados = service.consultarFuncionariosPorEstatusDeparamento(idEstatus, idDepartamento);
			assertTrue("La lista debe tener minimo un registro : ", resultados.size()>0);
			logger.info("Número de registros : " + resultados.size());
			for (FuncionarioDTO funcionarioDTO : resultados) {
				logger.info("Funcionario: "+ funcionarioDTO.getClaveFuncionario());
				logger.info("Funcionario: "+ funcionarioDTO.getNombreCompleto());
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
