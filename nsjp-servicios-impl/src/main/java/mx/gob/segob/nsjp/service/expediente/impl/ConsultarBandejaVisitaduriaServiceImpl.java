/**
* Nombre del Programa : ConsultarBandejaVisitaduriaServiceImpl.java
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.RelNumExpedienteAuditoriaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.RelNumExpedienteAuditoriaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.ConsultarBandejaVisitaduriaService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.RelNumExpedienteAuditoriaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicios utilizados para hacer consulta de los Números de Expedientes
 * Auditados. Es decir, la Bandeja de Visitaduría. 
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Service
@Transactional
public class ConsultarBandejaVisitaduriaServiceImpl implements ConsultarBandejaVisitaduriaService {
	 @Autowired
	 private RelNumExpedienteAuditoriaDAO auditoriaDAO;
	 @Autowired
	 private FuncionarioDAO funcionarioDAO;
	 @Autowired
	 private  BuscarExpedienteService buscarExpediente;
	 @Autowired
	 private  DelitoDAO delitoDAO;
	 @Autowired
	 private ExpedienteDAO expedienteDAO;
	 @Autowired
	 private UsuarioService usuarioService;
	 
	 
	 private final static Logger logger = Logger
		.getLogger(ConsultarBandejaVisitaduriaServiceImpl.class);
	    
	public List<RelNumExpedienteAuditoriaDTO> consultarNumeroVisitaduriaPorFiltro(RelNumExpedienteAuditoriaDTO filtroDTO )  
		throws NSJPNegocioException{
		
		if(filtroDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/************************************************************************************************/
        //Para agencias 
		/*
		 * Usado para setear la agencia y consultar turnos para agencias de PGJ
		 */
//		long agenciaId = 0L;
//
//		ConfInstitucion confInstitucionPG = expedienteDAO
//				.consultarInsitucionActual();
//		if (confInstitucionPG.getConfInstitucionId().equals(
//				Instituciones.PGJ.getValorId())) {
//			if (filtroDTO.getNumeroAuditoria().getUsuario() != null
//					&& filtroDTO.getNumeroAuditoria().getUsuario()
//							.getFuncionario() != null
//					&& filtroDTO.getNumeroAuditoria().getUsuario()
//							.getFuncionario().getAgencia() != null
//					&& filtroDTO.getNumeroAuditoria().getUsuario()
//							.getFuncionario().getAgencia().getIdCampo() != null) {
//
//				agenciaId = filtroDTO.getNumeroAuditoria().getUsuario()
//						.getFuncionario().getAgencia().getIdCampo();
//				
//			} else if (filtroDTO.getNumeroAuditoria().getUsuario()
//					.getFuncionario().getAgencia() == null
//					&& filtroDTO.getNumeroAuditoria().getUsuario()
//							.getFuncionario().getClaveFuncionario() != null) {
//
//				UsuarioDTO usuario = usuarioService
//						.consultarUsuarioPorClaveFuncionario(filtroDTO
//								.getNumeroAuditoria().getUsuario()
//								.getFuncionario().getClaveFuncionario());
//				if (usuario != null
//						&& usuario.getFuncionario() != null
//						&& usuario.getFuncionario().getAgencia() != null
//						&& usuario.getFuncionario().getAgencia().getIdCampo() != null) {
//
//					agenciaId = usuario.getFuncionario().getAgencia()
//							.getIdCampo();
//				}
//
//			}
//			else{
//				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
//			}
//		}
		/************************************************************************************************/ 
		
		List<RelNumExpedienteAuditoriaDTO> relaciones = new ArrayList<RelNumExpedienteAuditoriaDTO>();
		
		
		RelNumExpedienteAuditoria filtro = RelNumExpedienteAuditoriaTransformer.transformar(filtroDTO);
		ExpedienteDTO  expedienteAuditadoDTO = new ExpedienteDTO();
		ExpedienteDTO  carpetaAuditoriaDTO = new ExpedienteDTO();
		
		if(filtroDTO.getNumeroExpediente()!= null)
			expedienteAuditadoDTO = filtroDTO.getNumeroExpediente();
		
		if(filtroDTO.getNumeroAuditoria()!= null)
			carpetaAuditoriaDTO = filtroDTO.getNumeroAuditoria();

		//Obtener los parametros para el filtro
		NumeroExpediente numExpedienteAuditado = new NumeroExpediente();
		numExpedienteAuditado.setEstatus(null);
		
		NumeroExpediente numCarpetaAuditoria = new NumeroExpediente();
		numCarpetaAuditoria.setEstatus(null);
		
		//Parametros de Expediente Auditado
		if(expedienteAuditadoDTO!= null ){
			if(expedienteAuditadoDTO.getNumeroExpedienteId()!= null  )
				numExpedienteAuditado.setNumeroExpedienteId(expedienteAuditadoDTO.getNumeroExpedienteId());
			if(expedienteAuditadoDTO.getArea()!= null  &&
					expedienteAuditadoDTO.getArea().getAreaId()!= null )
				numExpedienteAuditado.setJerarquiaOrganizacional(new JerarquiaOrganizacional(expedienteAuditadoDTO.getArea().getAreaId()));
			if(expedienteAuditadoDTO.getUsuario()!= null && 
					expedienteAuditadoDTO.getUsuario().getFuncionario()!= null &&
					expedienteAuditadoDTO.getUsuario().getFuncionario().getClaveFuncionario()!= null)
				//numExpedienteAuditado.setFuncionario(new Funcionario(expedienteAuditadoDTO.getUsuario().getFuncionario().getClaveFuncionario()));
					
					numExpedienteAuditado.setFuncionario(FuncionarioTransformer.transformarFuncionario(expedienteAuditadoDTO.getUsuario().getFuncionario()));
			if(expedienteAuditadoDTO.getEstatus()!= null  &&
					expedienteAuditadoDTO.getEstatus().getIdCampo()!= null )
				numExpedienteAuditado.setEstatus(new Valor(expedienteAuditadoDTO.getEstatus().getIdCampo()));
		}
		
		//Parametros de Numero Carpeta de Auditoria
		if (carpetaAuditoriaDTO!= null ){
			if(carpetaAuditoriaDTO.getNumeroExpedienteId()!= null  )
				numCarpetaAuditoria.setNumeroExpedienteId(carpetaAuditoriaDTO.getNumeroExpedienteId());
			if(carpetaAuditoriaDTO.getArea()!= null  &&
					carpetaAuditoriaDTO.getArea().getAreaId()!= null )
				numCarpetaAuditoria.setJerarquiaOrganizacional(new JerarquiaOrganizacional(carpetaAuditoriaDTO.getArea().getAreaId()));
			if(carpetaAuditoriaDTO.getUsuario()!= null && 
					carpetaAuditoriaDTO.getUsuario().getFuncionario()!= null &&
					carpetaAuditoriaDTO.getUsuario().getFuncionario().getClaveFuncionario()!= null)
				//numCarpetaAuditoria.setFuncionario(new Funcionario(carpetaAuditoriaDTO.getUsuario().getFuncionario().getClaveFuncionario()));
				numCarpetaAuditoria.setFuncionario(FuncionarioTransformer.transformarFuncionario(carpetaAuditoriaDTO.getUsuario().getFuncionario()));
			if(carpetaAuditoriaDTO.getEstatus()!= null  &&
					carpetaAuditoriaDTO.getEstatus().getIdCampo()!= null )
				numCarpetaAuditoria.setEstatus(new Valor(carpetaAuditoriaDTO.getEstatus().getIdCampo()));
		}
		
		List<RelNumExpedienteAuditoria> relacionesBD = auditoriaDAO.consultarRelNumeroExpedienteAuditoriaPorFiltro(
				filtro, numExpedienteAuditado, numCarpetaAuditoria);
		
		//Obtener los datos para llenar el DTO;
		for (RelNumExpedienteAuditoria relNumExpedienteAuditoria : relacionesBD) {
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			RelNumExpedienteAuditoriaDTO relacionDTO  = RelNumExpedienteAuditoriaTransformer.transformar(relNumExpedienteAuditoria);
			
			//Delito Principal
			if(relNumExpedienteAuditoria.getNumeroExpediente()!= null && relNumExpedienteAuditoria.getNumeroExpediente().getExpediente()!= null
					&& relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getExpedienteId()!= null){
				 List<Delito> listDelitos = delitoDAO
						.obtenerDelitoPorExpediente(relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getExpedienteId());
				for (Delito delito : listDelitos) {
					if (delito.getEsPrincipal() == true) {
						DelitoDTO delitoDTO = DelitoTransfromer
								.transformarDelito(delito);
						expedienteDTO.setDelitoPrincipal(delitoDTO);
						relacionDTO.getNumeroExpediente().setDelitoPrincipal(delitoDTO);			
					}
				}	
			}
			//Origen
			if(relNumExpedienteAuditoria.getNumeroExpediente()!= null && relNumExpedienteAuditoria.getNumeroExpediente().getExpediente()!= null
					&& relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getOrigen()!= null){
				Valor origen = relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getOrigen();
				relacionDTO.getNumeroExpediente().setOrigen(new ValorDTO(origen.getValorId(), origen.getValor()));
			}
			
			//Completar datos para Numero Expediente Auditado
			Funcionario funcionario = funcionarioDAO.
									  consultarFuncionarioXNumeroExpYTipo(relacionDTO.getNumeroExpediente().getNumeroExpediente(), Areas.UNIDAD_INVESTIGACION.parseLong());
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setFuncionario(FuncionarioTransformer.transformarFuncionarioBasico(funcionario));
			relacionDTO.getNumeroExpediente().setUsuario(usuarioDTO);
			
			//Completar datos para Numero de Auditoria
			expedienteDTO.setNumeroExpedienteId(relNumExpedienteAuditoria.getNumeroAuditoriaId());
			expedienteDTO = buscarExpediente.obtenerExpediente(expedienteDTO);
			
			funcionario = funcionarioDAO.
										consultarFuncionarioXNumeroExpYTipo(expedienteDTO.getNumeroExpediente(), Areas.UNIDAD_INVESTIGACION.parseLong());
			
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setFuncionario(FuncionarioTransformer.transformarFuncionarioBasico(funcionario));
			expedienteDTO.setUsuario(usuarioDTO);
			
			//Settear los valores
			expedienteDTO.setDelitoPrincipal(relacionDTO.getNumeroExpediente().getDelitoPrincipal());
			expedienteDTO.setOrigen(relacionDTO.getNumeroExpediente().getOrigen());
//			if (expedienteDTO.getEstatus()!= null && expedienteDTO.getEstatus().getIdCampo()!= null)
//				expedienteDTO.setEstatus(new ValorDTO(idCampo, valor))
			
			relacionDTO.setNumeroAuditoria(expedienteDTO);
			
			relaciones.add(relacionDTO);
		}
		return relaciones;
	}
	
	public List<FuncionarioDTO> consultarFuncionariosPorEstatusDeparamento(Long idEstatus, Long idDepartamento) 
		throws NSJPNegocioException{
		
		Set<FuncionarioDTO> funcionariosDTO = new HashSet<FuncionarioDTO>();

		RelNumExpedienteAuditoria filtro = new RelNumExpedienteAuditoria();
		filtro.setNumeroExpediente(new NumeroExpediente() );
		
		
		//Obtener los parametros para el filtro
		NumeroExpediente numExpedienteAuditado = new NumeroExpediente();
		numExpedienteAuditado.setEstatus(null);
		
		NumeroExpediente numCarpetaAuditoria = new NumeroExpediente();
		numCarpetaAuditoria.setEstatus(null);

		//Parametros de Expediente Auditado
		if(idDepartamento != null )
			numExpedienteAuditado.setJerarquiaOrganizacional(new JerarquiaOrganizacional(idDepartamento));
		
		//Parametros de Numero Carpeta de Auditoria
		if(idEstatus != null )
			numCarpetaAuditoria.setEstatus(new Valor(idEstatus));
		
		
		List<RelNumExpedienteAuditoria> relacionesBD = auditoriaDAO.consultarRelNumeroExpedienteAuditoriaPorFiltro(
				null, numExpedienteAuditado, numCarpetaAuditoria);
		
		//Recorrer la lista de las relaciones para obtener los funcionarios Visitadores
		for (RelNumExpedienteAuditoria relNumExpedienteAuditoria : relacionesBD) {
			//Completar datos para Numero de Auditoria
			ExpedienteDTO expedienteDTO = new ExpedienteDTO(); 
			expedienteDTO.setNumeroExpedienteId(relNumExpedienteAuditoria.getNumeroAuditoriaId());
			expedienteDTO = buscarExpediente.obtenerExpediente(expedienteDTO);
			
			Funcionario funcionario = funcionarioDAO.consultarFuncionarioXExpediente(expedienteDTO.getNumeroExpediente());
			FuncionarioDTO funcionarioDTO = FuncionarioTransformer.transformarFuncionarioBasico(funcionario);
			
			funcionariosDTO.add(funcionarioDTO);
		}
		
		return new ArrayList<FuncionarioDTO>(funcionariosDTO);
	}

	@Override
	public List<RelNumExpedienteAuditoriaDTO> consultarNumeroVisitaduriaUIE(
			RelNumExpedienteAuditoriaDTO relNumExpedienteAuditoriaDTO)
			throws NSJPNegocioException {
		
		if (relNumExpedienteAuditoriaDTO.getNumeroExpediente()==null || relNumExpedienteAuditoriaDTO.getNumeroExpediente().getArea()==null
				|| relNumExpedienteAuditoriaDTO.getNumeroExpediente().getArea().getAreaId()==null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		if (relNumExpedienteAuditoriaDTO.getNumeroExpediente().getEstatus()==null
				|| relNumExpedienteAuditoriaDTO.getNumeroExpediente().getEstatus().getIdCampo()==null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Long idUIE = relNumExpedienteAuditoriaDTO.getNumeroExpediente().getArea().getAreaId();
		Long idEstats = relNumExpedienteAuditoriaDTO.getNumeroExpediente().getEstatus().getIdCampo();
		
		List<RelNumExpedienteAuditoria> respRelNumExp = auditoriaDAO.consultarNumeroVisitaduriaUIE(idUIE, idEstats);
		
		if (respRelNumExp==null || respRelNumExp.isEmpty()) {
			return null;
		}
		
		List<RelNumExpedienteAuditoriaDTO> retornoRelNumExp = new ArrayList<RelNumExpedienteAuditoriaDTO>();
		
		for (RelNumExpedienteAuditoria relNumExpedienteAuditoria : respRelNumExp) {
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			RelNumExpedienteAuditoriaDTO relacionDTO  = RelNumExpedienteAuditoriaTransformer.transformar(relNumExpedienteAuditoria);
			
			//Delito Principal
			if(relNumExpedienteAuditoria.getNumeroExpediente()!= null && relNumExpedienteAuditoria.getNumeroExpediente().getExpediente()!= null
					&& relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getExpedienteId()!= null){
				 List<Delito> listDelitos = delitoDAO
						.obtenerDelitoPorExpediente(relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getExpedienteId());
				for (Delito delito : listDelitos) {
					if (delito.getEsPrincipal() == true) {
						DelitoDTO delitoDTO = DelitoTransfromer
								.transformarDelito(delito);
						expedienteDTO.setDelitoPrincipal(delitoDTO);
						relacionDTO.getNumeroExpediente().setDelitoPrincipal(delitoDTO);			
					}
				}	
			}
			//Origen
			if(relNumExpedienteAuditoria.getNumeroExpediente()!= null && relNumExpedienteAuditoria.getNumeroExpediente().getExpediente()!= null
					&& relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getOrigen()!= null){
				Valor origen = relNumExpedienteAuditoria.getNumeroExpediente().getExpediente().getOrigen();
				relacionDTO.getNumeroExpediente().setOrigen(new ValorDTO(origen.getValorId(), origen.getValor()));
			}
			
			//Completar datos para Numero Expediente Auditado
			Funcionario funcionario = funcionarioDAO.
									  consultarFuncionarioXNumeroExpYTipo(relacionDTO.getNumeroExpediente().getNumeroExpediente(), Areas.UNIDAD_INVESTIGACION.parseLong());
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setFuncionario(FuncionarioTransformer.transformarFuncionarioBasico(funcionario));
			relacionDTO.getNumeroExpediente().setUsuario(usuarioDTO);
			
			//Completar datos para Numero de Auditoria
			expedienteDTO.setNumeroExpedienteId(relNumExpedienteAuditoria.getNumeroAuditoriaId());
			expedienteDTO = buscarExpediente.obtenerExpediente(expedienteDTO);
			
			funcionario = funcionarioDAO.
										consultarFuncionarioXNumeroExpYTipo(expedienteDTO.getNumeroExpediente(), Areas.UNIDAD_INVESTIGACION.parseLong());
			
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setFuncionario(FuncionarioTransformer.transformarFuncionarioBasico(funcionario));
			expedienteDTO.setUsuario(usuarioDTO);
			
			//Settear los valores
			expedienteDTO.setDelitoPrincipal(relacionDTO.getNumeroExpediente().getDelitoPrincipal());
			expedienteDTO.setOrigen(relacionDTO.getNumeroExpediente().getOrigen());
//			if (expedienteDTO.getEstatus()!= null && expedienteDTO.getEstatus().getIdCampo()!= null)
//				expedienteDTO.setEstatus(new ValorDTO(idCampo, valor))
			
			relacionDTO.setNumeroAuditoria(expedienteDTO);
			
			retornoRelNumExp.add(relacionDTO);
		}
		
		return retornoRelNumExp;
	}
}
