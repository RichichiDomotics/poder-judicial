/**
 * Nombre del Programa : CargarDocumentoServiceImpl.java
 * Autor                            : Emigdio Hernández
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26/05/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del servicio de negocio para la carga de documentos
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

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ParametrosDocumentoDTO;
import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService;
import mx.gob.segob.nsjp.service.documento.CargarDocumentoService;
import mx.gob.segob.nsjp.service.documento.ConsultarResumenExpedienteParaDocumentoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.forma.BuscarFormaService;
import mx.gob.segob.nsjp.service.forma.ConsultarCamposFormaService;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para la carga de documentos
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class CargarDocumentoServiceImpl implements CargarDocumentoService {
	
	@Autowired
	BuscarExpedienteService buscarExpedienteService;
	@Autowired
	BuscarFormaService buscarFormaService;
	@Autowired
	ActividadDAO actividadDAO;
	@Autowired
	ConsultarCamposFormaService consultarCamposFormaService;
	@Autowired
	ConsultarResumenExpedienteParaDocumentoService consultarResumenExpedienteParaDocumentoService;
	@Autowired
	DocumentoDAO documentoDAO;
	@Autowired
	ConsultarAudienciaService consultarAudienciaService;

	
	private static final Logger log  = Logger.getLogger(CargarDocumentoServiceImpl.class);
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.CargarDocumentoService#cargarDocumentoPorExpedienteYForma(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, mx.gob.segob.nsjp.dto.forma.FormaDTO)
	 */
	@Override
	public DocumentoDTO cargarDocumentoPorExpedienteYForma(
			ExpedienteDTO expediente, FormaDTO tipoForma)
			throws NSJPNegocioException {
		
		return cargarDocumentoPorExpedienteYForma(expediente, tipoForma, null);
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.CargarDocumentoService#cargarDocumentoPorAudienciaYForma(mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO, mx.gob.segob.nsjp.dto.forma.FormaDTO)
	 */
	@Override
	public DocumentoDTO cargarDocumentoPorAudienciaYForma(
			AudienciaDTO audiencia, FormaDTO tipoForma) throws NSJPNegocioException {
		FormaDTO formaActual = buscarFormaService.buscarForma(tipoForma.getFormaId());
		audiencia = consultarAudienciaService.obtenerAudiencia(audiencia);
		ExpedienteDTO expediente = audiencia.getExpediente();
		Actividad actividadActual = actividadDAO.obtenerActividadActual(expediente.getExpedienteId());
		DocumentoDTO documento = null;
		//buscar si en la actividadActual existe un documento con guardado parcial
		if(actividadActual != null && actividadActual.getDocumento() != null && 
				StringUtils.isNotBlank(actividadActual.getDocumento().getTextoParcial())
				&& actividadActual.getDocumento().getArchivoDigital() == null){
			//ent
			documento = DocumentoTransformer.transformarDocumento(actividadActual.getDocumento());
		}
		//si no existe un documento con guardado parcial entonces se crea un documento con el cuerpo de la
		//forma  que se va a emitir en ese momento
		if(documento == null){
			documento = new DocumentoDTO();
			documento.setFormaDTO(formaActual);
			ParametrosDocumentoDTO resumen = consultarResumenExpedienteParaDocumentoService.consultarResumenExpedienteParaDocumento(audiencia);
			if(resumen != null && formaActual != null){
				documento.setTextoParcial(reemplazarCamposForma(formaActual.getCuerpo(),resumen));
			}
		}
		return documento;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.CargarDocumentoService#cargarDocumentoPorResolutivoYForma(mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO, mx.gob.segob.nsjp.dto.forma.FormaDTO)
	 */
	@Override
	public DocumentoDTO cargarDocumentoPorResolutivoYForma(
			ResolutivoDTO resolutivo, FormaDTO tipoForma)
			throws NSJPNegocioException {
		FormaDTO formaActual = buscarFormaService.buscarForma(tipoForma.getFormaId());
		AudienciaDTO audiencia = consultarAudienciaService.obtenerAudiencia(resolutivo.getAudiencia());
		ExpedienteDTO expediente = audiencia.getExpediente();
		Actividad actividadActual = actividadDAO.obtenerActividadActual(expediente.getExpedienteId());
		DocumentoDTO documento = null;
		//buscar si en la actividadActual existe un documento con guardado parcial
		if(actividadActual != null && actividadActual.getDocumento() != null && 
				StringUtils.isNotBlank(actividadActual.getDocumento().getTextoParcial())
				&& actividadActual.getDocumento().getArchivoDigital() == null){
			//ent
			documento = DocumentoTransformer.transformarDocumento(actividadActual.getDocumento());
		}
		//si no existe un documento con guardado parcial entonces se crea un documento con el cuerpo de la
		//forma  que se va a emitir en ese momento
		if(documento == null){
			documento = new DocumentoDTO();
			documento.setFormaDTO(formaActual);
			ParametrosDocumentoDTO resumen = consultarResumenExpedienteParaDocumentoService.consultarResumenExpedienteParaDocumento(audiencia);
			if(resumen != null && formaActual != null){
				documento.setTextoParcial(reemplazarCamposForma(formaActual.getCuerpo(),resumen));
			}
		}
		return documento;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.CargarDocumentoService#cargarDocumentoPorIdDocumentoYForma(java.lang.Long, mx.gob.segob.nsjp.dto.forma.FormaDTO)
	 */
	@Override
	public DocumentoDTO cargarDocumentoPorIdDocumentoYForma(Long idDocumento, FormaDTO forma) throws NSJPNegocioException{
		Documento doc = documentoDAO.read(idDocumento);
		DocumentoDTO documento = new DocumentoDTO();
		ExpedienteDTO expediente = new ExpedienteDTO();
		documento = DocumentoTransformer.transformarDocumento(doc);
		Long numexp = doc.getExpediente().getNumeroExpedienteId();
		if(numexp == null){
			numexp = doc.getExpediente().getNumeroExpedientes().iterator().next().getNumeroExpedienteId();
		}
		expediente.setNumeroExpedienteId(numexp);
		//Si el documento no tiene forma entonces se emite con la forma enviada
		if(documento.getFormaDTO() == null){
			documento.setFormaDTO(forma);
		}
		ParametrosDocumentoDTO resumen = consultarResumenExpedienteParaDocumentoService.consultarResumenExpedienteParaDocumento(expediente);
		if(resumen != null && forma != null){
			documento.setTextoParcial(reemplazarCamposForma(forma.getCuerpo(),resumen));
		}
		return documento;
	}
	
	/**
	 * Obtiene la colección de campos forma y los reemplaza en el cuero de la forma actuaZl con los 
	 * valores del Expediente
	 * @param cuerpo Cuerpo original de la forma
	 * @param resumen Expediente con los datos a tomar
	 * @return cuerpo de la forma pre-llenado con los datos del expediente
	 */
	private String reemplazarCamposForma(String cuerpo,
			ParametrosDocumentoDTO resumen) {
		List<CamposFormaDTO> camposForma = consultarCamposFormaService.consultarCamposForma();
		String cuerpoFinal = cuerpo;
		String valorCampo = null;
		if(resumen != null){
			for (CamposFormaDTO camposFormaDTO : camposForma) {
				valorCampo = obtenerValorCampoExpediente(camposFormaDTO.getRutaDato(), resumen);
				cuerpoFinal = StringUtils.replace(cuerpoFinal, 
						ConstantesGenerales.APERTURA_CAMPO_FORMATO + camposFormaDTO.getNombreNegocio() + ConstantesGenerales.CIERRE_CAMPO_FORMATO
						, valorCampo);
			}
		}
		
		
		return cuerpoFinal;
	}
	
	/**
	 * Obtiene la representación en cadena de un valor de campo en un expediente
	 * @param rutaCampo Ruta para llegar al campo
	 * @param resumen Datos del expediente
	 * @return Representación en cadena del valor del campo
	 */
	private String obtenerValorCampoExpediente(String rutaCampo,ParametrosDocumentoDTO resumen){
		
			
			Object dato = null;
			String valor = StringUtils.EMPTY;;
			try {
				dato = PropertyUtils.getNestedProperty(resumen, rutaCampo);

				
				
				if(dato != null){

					/*if (dato instanceof java.util.Date) {
						 
						valor =  DateUtil.dateToString((Date)dato);
					}else if(dato instanceof Double){
						
						valor = NumberUtil.aplicarFormatoCifra2Decimales((Double)dato);
						
					}else  if(dato instanceof BigDecimal){
						valor = NumberUtil.aplicarFormatoCifra2Decimales((BigDecimal)dato);
					}else if(dato instanceof Integer){
						valor = NumberUtil.aplicarFormatoEntero(new Double(((Integer)dato).doubleValue()));
					}else{*/
						valor = dato.toString();
					/*}*/

				}


				
				return valor;
				
				
				
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (InvocationTargetException e) {
				log.error(e);
			} catch (NoSuchMethodException e) {
				log.error(e);
			} catch (NestedNullException e) {
				
				log.error(e);
			}
			return valor;
		
	}
	
	/**
	 * Obtiene el nombre completo de un involucrado de un expediente según la calidad del involucrado
	 * @param expediente Expediente fuente
	 * @param calidad Calidad del involucrado a buscar
	 * @return Nombre completo del involucrado
	 */
	private String obtenerNombreInvolucradoPorCalidad(ExpedienteDTO expediente,Calidades calidad){
    	
    	if(expediente != null){
    		List<InvolucradoDTO> listaInv = expediente.getInvolucradoByCalidad(calidad);
    		if(listaInv!=null && listaInv.size()>0){
    			List<NombreDemograficoDTO> nombres = listaInv.get(0).getNombresDemograficoDTO();
    			if(nombres != null && nombres.size()>0){
    				return nombres.get(0).getNombre() +" "+ nombres.get(0).getApellidoPaterno() +" "+ nombres.get(0).getApellidoMaterno();
    			}
    		}
    	}
    	
    	return "";
    }
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.CargarDocumentoService#cargarDocumentoPorId(java.lang.Long)
	 */
	@Override
	public DocumentoDTO cargarDocumentoPorId(Long documentoId)
			throws NSJPNegocioException {
		
		return cargarDocumentoPorId(documentoId,null);
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.CargarDocumentoService#cargarDocumentoPorId(java.lang.Long, mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public DocumentoDTO cargarDocumentoPorId(Long documentoId, ExpedienteDTO exp)
			throws NSJPNegocioException {
		DocumentoDTO documentoRestulado = null;
		Documento documentoActual = documentoDAO.read(documentoId);
		if(documentoActual != null){
			documentoRestulado = DocumentoTransformer.transformarDocumento(documentoActual);
			//Si no se ha emitido el archivo y no tiene texto parcial, llemar automáticamente la forma
			if(documentoRestulado.getArchivoDigital() == null && 
					StringUtils.isBlank(documentoRestulado.getTextoParcial())){
				Long idExp = exp !=null?exp.getExpedienteId():null;
				if(documentoActual instanceof Solicitud){
					Solicitud sol = ((Solicitud)documentoActual);
					idExp =(sol.getNumeroExpediente()!=null && sol.getNumeroExpediente().getExpediente() != null) ?
							sol.getNumeroExpediente().getExpediente().getExpedienteId():null;
				}
				ParametrosDocumentoDTO resumen = consultarResumenExpedienteParaDocumentoService.
				consultarResumenExpedienteParaDocumento(new ExpedienteDTO(idExp));
				if(resumen != null && documentoRestulado.getFormaDTO() != null){
					documentoRestulado.setTextoParcial(reemplazarCamposForma(documentoRestulado.getFormaDTO().getCuerpo(),resumen));
				}
				
			}
			
		}
		
		return documentoRestulado;
	}




	@Override
	public DocumentoDTO cargarDocumentoPorExpedienteYForma(
			ExpedienteDTO expediente, FormaDTO tipoForma,
			Map<String, Object> parametrosExtras) throws NSJPNegocioException {
	    DocumentoDTO documento = null;
	    FormaDTO formaActual = buscarFormaService.buscarForma(tipoForma.getFormaId());
	    if (expediente!=null) {
    	    log.debug("expediente.expedienteId :: " + expediente.getExpedienteId());
    		Actividad actividadActual = 
    			expediente!=null?actividadDAO.obtenerActividadActual(expediente.getExpedienteId()):null;
    		//buscar si en la actividadActual existe un documento con guardado parcial
    		if(actividadActual != null && actividadActual.getDocumento() != null && 
    				StringUtils.isNotBlank(actividadActual.getDocumento().getTextoParcial())
    				&& actividadActual.getDocumento().getArchivoDigital() == null){
    			//ent
    			documento = DocumentoTransformer.transformarDocumento(actividadActual.getDocumento());
    		}
	    }
		//si no existe un documento con guardado parcial entonces se crea un documento con el cuerpo de la
		//forma  que se va a emitir en ese momento
		if(documento == null){
			documento = new DocumentoDTO();
			documento.setFormaDTO(formaActual);
            if (expediente != null) {
                expediente.setInvolucradosSolicitados(true);
                expediente.setHechoSolicitado(true);
            }
			ParametrosDocumentoDTO resumen = consultarResumenExpedienteParaDocumentoService.consultarResumenExpedienteParaDocumento(expediente);
			if(resumen != null && formaActual != null){
				if(parametrosExtras != null){
					documento.setTextoParcial(reemplazarParametrosExtra(formaActual.getCuerpo(),parametrosExtras));
				}
				if (expediente!=null) {
				    documento.setTextoParcial(reemplazarCamposForma(documento.getFormaDTO().getCuerpo(),resumen));
				}
			}
		}
		return documento;
	}



	/**
	 * Reemplaza un conjunto de parámetros en el texto HTML del documento
	 * Los parámetros del mapa existen en la forma llave-valor
	 * La llave es el nombre del parámetro que se buscará en el texto como  "<nombre_parametro>"
	 * @param textoParcial Texto donde serán reemplazados los parámetros
	 * @param parametrosExtras Conjuntos de elementos llave-valor
	 * @return Texto actualizado
	 */
	private String reemplazarParametrosExtra(String textoParcial,
			Map<String, Object> parametrosExtras) {
		String textoFinal = new String(textoParcial!=null?textoParcial:"");
		String valor = null;
		if(parametrosExtras != null){
			for (String llave : parametrosExtras.keySet()) {
				
				if(parametrosExtras.get(llave) != null){
					valor = parametrosExtras.get(llave).toString();
				}else{
					valor = "";
				}
				
				textoFinal = StringUtils.replace(textoFinal, 
						ConstantesGenerales.APERTURA_CAMPO_FORMATO + llave + ConstantesGenerales.CIERRE_CAMPO_FORMATO
						, valor);
			}
		}
		return textoFinal;
	}

}
