/**
* Nombre del Programa : MandamientoJudicialServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24/08/2011
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMandamiento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.enums.solicitud.TipoMandamiento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.ResolutivoDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.documento.MandamientoDAO;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.MandamientoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.Resolutivo;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.ResolutivoTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatalogoTransformer;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.documento.MandamientoJudicialService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.documento.impl.tranform.MandamientoJudicialTransformer;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.expediente.AdministrarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.infra.SSPClienteService;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para la manipulación de mandamientos judiciales
 * @version 1.0
 * @author Emigdio
 *
 */
@Service
@Transactional
public class MandamientoJudicialServiceImpl implements
		MandamientoJudicialService {
	
	private final static Logger logger = Logger
		.getLogger(MandamientoJudicialServiceImpl.class);
			
	@Autowired
	private MandamientoDAO mandamientoDAO;
	@Autowired
	private ResolutivoDAO resolutivoDAO;
	@Autowired
	private SSPClienteService sspClientService;
	@Autowired
	private GenerarFolioSolicitudService folioService;
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	@Autowired
	private AudienciaDAO audienciaDAO;
	@Autowired
	private DomicilioDAO domicilioDAO; 
	@Autowired
	private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
	@Autowired
	private AdministrarNumeroExpedienteService administrarNumeroExpedienteService;
	@Autowired
	private DocumentoDAO documentoDAO;
	@Autowired
	private GuardarDocumentoService documentoService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.MandamientoJudicialService#registrarMandamientoJudicial(mx.gob.segob.nsjp.dto.documento.MandamientoDTO)
	 */
	@Override
	public MandamientoDTO registrarMandamientoJudicial(
			MandamientoDTO mandamiento) throws NSJPNegocioException {
		
		logger.info(" Service - registrarMandamientoJudicial:: " + mandamiento);
		
		if(mandamiento==null || mandamiento.getResolutivo()==null || mandamiento.getResolutivo().getResolutivoId()==null
				|| mandamiento.getInvolucrado()==null || mandamiento.getInvolucrado().getElementoId()==null
				|| mandamiento.getInvolucrado().getElementoId()<0 
				|| mandamiento.getDomicilio()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		logger.info(" Tipo de Mandamiento: "+ mandamiento.getTipoMandamiento());
		
		Resolutivo resol = resolutivoDAO.read(mandamiento.getResolutivo().getResolutivoId());
		if(resol==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		if(resol.getDocumento() != null){
			//ya tiene mandamiento
			logger.info(" Documento: "+ resol.getDocumento());
			mandamiento.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigitalBasico(resol.getDocumento().getArchivoDigital()));
			mandamiento.setResolutivo(ResolutivoTransformer.transformarResolutivo(resol));
			mandamiento.setDocumentoId(resol.getDocumento().getDocumentoId());
		}else{
			logger.info(" mandamiento.getTipoMandamiento().getIdCampo():"+ mandamiento.getTipoMandamiento().getIdCampo());
			logger.info(" TipoMandamiento.SENTENCIA.getValorId():"+ TipoMandamiento.SENTENCIA.getValorId());
			logger.info(" EQUALS"+mandamiento.getTipoMandamiento().getIdCampo().equals(TipoMandamiento.SENTENCIA.getValorId()));
			
			
			Mandamiento mandamientoBD = MandamientoJudicialTransformer.transformarMandamientoDTO(mandamiento);
			String folio= folioService.generarFoliodDocumento();
			logger.info(" FolioDocumento:"+ folio);
			mandamientoBD.setFolioDocumento(folio);
			mandamientoBD.setResolutivo(resol);
			mandamientoBD.setEstatus(new Valor(EstatusMandamiento.EN_PROCESO.getValorId()));
			
			
			//Involucrado
			logger.info(" Sentenciado: "+ mandamiento.getInvolucrado().getElementoId());
			mandamientoBD.setInvolucrado(new Involucrado(mandamiento.getInvolucrado().getElementoId()));
			
			//Domicilio
			//Obtener el id_Expediente de la audiencia:
			Audiencia audiencia  = audienciaDAO.consultarAudienciaById(resol.getAudiencia().getAudienciaId());
			Long expedienteId = audiencia.getExpediente().getExpedienteId();
			logger.info("expedienteId:"+ expedienteId);
			mandamiento.getDomicilio().setExpedienteDTO(new ExpedienteDTO(expedienteId));
			
			Long domicilioId = ingresarDomicilioService.ingresarDomicilio( mandamiento.getDomicilio());
			logger.info(" IDomicilio:"+ domicilioId);
			Domicilio domicilio =domicilioDAO.read(domicilioId);
			mandamientoBD.setDomicilio(domicilio);
			
			Long mandamientoId = mandamientoDAO.create(mandamientoBD);
			logger.info(" MandamientoID:"+ mandamientoId);
			
			mandamientoBD.setDocumentoId(mandamientoId);
			mandamiento.setDocumentoId(mandamientoBD.getDocumentoId());
			
			//Crear la relacion de Resolutivo con Mandamiento
			resol.setDocumento(mandamientoBD);
			
			logger.info(" EQUALSIN "+mandamiento.getTipoMandamiento().getIdCampo().equals(TipoMandamiento.SENTENCIA.getValorId()));
			//Si es una sentencia se debe de crear un nuevo expediente = Carpeta de ejecucion 
			if(mandamiento.getTipoMandamiento().getIdCampo().equals(TipoMandamiento.SENTENCIA.getValorId())){
				logger.info("asignarNumeroExpedienteCarpetaEjecucion sobre:"+ expedienteId);
				
				//Generar la Carpeta de Ejecucion
				ExpedienteDTO carpetaEjecucion = asignarNumeroExpedienteService.asignarNumeroExpedienteCarpetaEjecucion(expedienteId);
				
				logger.info("El involucrado a Copiar es:"+ mandamiento.getInvolucrado().getElementoId());
				InvolucradoDTO sentenciado = new InvolucradoDTO(mandamiento.getInvolucrado().getElementoId());
				sentenciado.setValorSituacionJuridica(new ValorDTO(SituacionJuridica.SENTENCIADO.getValorId()));
				
				//Obtener el involucrado
				InvolucradoDTO sentenciadoDTO = administrarNumeroExpedienteService.guardarInvolucradoEnExpediente(
						new ExpedienteDTO(carpetaEjecucion.getExpedienteId()),
						sentenciado, 
						null);
				
				//Obtener el documento de la sentencia
				logger.info("Documento de la sentencia **:"+ mandamientoId);
				Documento documento = documentoDAO.consultarDocumentoPorId(mandamientoId);
				DocumentoDTO documentoDTO = DocumentoTransformer.transformarDocumento(documento);
				Long idDocumento= documentoService.guardarDocumento(documentoDTO,new ExpedienteDTO( carpetaEjecucion.getExpedienteId()));
				logger.info("Sentencia - Documento generado :" + idDocumento);
			}
		}
		return mandamiento;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.MandamientoJudicialService#consultarMandamientosPorNumeroExpediente(java.lang.String)
	 */
	@Override
	public List<MandamientoDTO> consultarMandamientosPorNumeroExpediente(
			String numeroExpediente,UsuarioDTO usuario) throws NSJPNegocioException {
		
		/*
		* Usado para obtener el discriminante Id
		*/
		long discriminanteId = 0L; 
		
		if (usuario != null
				&& usuario.getFuncionario() != null
				&& usuario.getFuncionario().getDiscriminante() != null
				&& usuario.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {

			discriminanteId = usuario.getFuncionario().getDiscriminante()
					.getCatDiscriminanteId();
		}
	
		List<Mandamiento> mandamientosBD = mandamientoDAO.consultarMandamientosPorNumeroExpediente(numeroExpediente,discriminanteId);	
		List<MandamientoDTO> resultado = new ArrayList<MandamientoDTO>();
		for(Mandamiento mandamiento:mandamientosBD){
			resultado.add(MandamientoJudicialTransformer.transformarMandamiento(mandamiento));
		}
		return resultado;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.MandamientoJudicialService#consultarMandamientosPorNumeroExpediente(java.lang.String)
	 */
	@Override
	public List<MandamientoDTO> consultarMandamientoPorFiltro(
			MandamientoDTO mandamientoDTO, String numeroExpediente) throws NSJPNegocioException {
		Mandamiento mandamientoBD = MandamientoJudicialTransformer.transformarMandamientoDTO(mandamientoDTO);
		List<Mandamiento> mandamientosBD = mandamientoDAO.consultarMandamientoPorFiltro(mandamientoBD, numeroExpediente);
		List<MandamientoDTO> resultado = new ArrayList<MandamientoDTO>();
		for(Mandamiento mandamiento:mandamientosBD){
			resultado.add(MandamientoJudicialTransformer.transformarMandamiento(mandamiento));
		}
		return resultado;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.MandamientoJudicialService#actualizarMandamiento(mx.gob.segob.nsjp.dto.documento.MandamientoDTO)
	 */
	@Override
	public void actualizarMandamiento(MandamientoDTO mandamiento) {

		Mandamiento mandamientoBD = mandamientoDAO.read(mandamiento.getDocumentoId());
		if(mandamientoBD != null){
			mandamientoBD.setEstatus(CatalogoTransformer.transformValor(mandamiento.getEstatus()));
			mandamientoDAO.saveOrUpdate(mandamientoBD);
		}
		
		
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.MandamientoJudicialService#enviarMandamientoJudicial(java.lang.Long)
	 */
	@Override
	public void enviarMandamientoJudicial(Long mandamientoId) throws NSJPNegocioException {
		Mandamiento mandamiento = mandamientoDAO.read(mandamientoId);
		if(mandamiento == null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		sspClientService.enviarMandamiento(mandamiento);
	}

}
