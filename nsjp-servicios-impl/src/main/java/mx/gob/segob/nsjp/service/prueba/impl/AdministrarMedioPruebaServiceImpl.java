/**
* Nombre del Programa : AdministrarMedioPruebaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03/10/2011
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
package mx.gob.segob.nsjp.service.prueba.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.prueba.DatoPruebaDAO;
import mx.gob.segob.nsjp.dao.prueba.MedioPruebaDAO;
import mx.gob.segob.nsjp.dao.prueba.RelacionDatoMedioPruebaDAO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.DatoPrueba;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.MedioPrueba;
import mx.gob.segob.nsjp.model.RelacionDatoMedioPrueba;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.prueba.AdministrarMedioPruebaService;
import mx.gob.segob.nsjp.service.prueba.impl.transformer.DatoPruebaTransformer;
import mx.gob.segob.nsjp.service.prueba.impl.transformer.MedioPruebaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de los servicios para registrar, modificar, consultar y relacionar los
 * Medios de Prueba de una Audiencia
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class AdministrarMedioPruebaServiceImpl implements
		AdministrarMedioPruebaService {
	
	public final static Logger logger = 
		Logger.getLogger(AdministrarMedioPruebaServiceImpl.class);
	
	@Autowired
	private MedioPruebaDAO medioPruebaDAO;
	@Autowired
	private RelacionDatoMedioPruebaDAO relacionDatoMedioPruebaDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private DatoPruebaDAO datoPruebaDAO;
	@Autowired
	private DocumentoDAO documentoDAO;
	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO;
	@Autowired
	private FormaDAO formaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.prueba.AdministrarMedioPruebaService#registrarMedioPruebaConRelacionADatoPrueba(mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO, mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO)
	 */
	@Override
	public MedioPruebaDTO registrarMedioPruebaConRelacionADatoPrueba(
			MedioPruebaDTO medioPruebaDTO, DatoPruebaDTO datoPruebaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR UN MEDIO DE PRUEBA PARA UN DATO DE PRUEBA ****/");
		
		if(medioPruebaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(medioPruebaDTO.getNombreMedio()==null||medioPruebaDTO.getNombreMedio().trim().isEmpty()||
				medioPruebaDTO.getNumeroIdentificacion()==null||medioPruebaDTO.getNumeroIdentificacion().trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		MedioPrueba medio = MedioPruebaTransformer.transformarMedioPruebaDTO(medioPruebaDTO);
		Long idMedio=medio.getMedioPruebaId();
		if(medio.getMedioPruebaId()!=null && medio.getMedioPruebaId()>0){
			//Elemento
			if( medioPruebaDTO.getElementoMedioPrueba()!= null &&  medioPruebaDTO.getElementoMedioPrueba().getElementoId()!= null){
				medio.setElementoMedioPrueba( new Elemento(medioPruebaDTO.getElementoMedioPrueba().getElementoId()));
			}
			medioPruebaDAO.update(medio);
		}else{
			if(datoPruebaDTO==null)
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			else if(datoPruebaDTO.getDatoPruebaId()==null)
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

			//Elemento
			if( medioPruebaDTO.getElementoMedioPrueba()!= null &&  medioPruebaDTO.getElementoMedioPrueba().getElementoId()!= null){
				medio.setElementoMedioPrueba( new Elemento(medioPruebaDTO.getElementoMedioPrueba().getElementoId()));
			}
			
			//Documento y ArchivoDigital
			if( medioPruebaDTO.getDocumentoMedioPrueba()!= null){
				Documento documento =  DocumentoTransformer.transformarDocumentoDTO(medioPruebaDTO.getDocumentoMedioPrueba());
				if (documento.getArchivoDigital() != null) {
					Long idArchivo = archivoDigitalDAO.create(documento.getArchivoDigital());
					ArchivoDigital archD=new ArchivoDigital();
					archD.setArchivoDigitalId(idArchivo);
					documento.setArchivoDigital(archD);
				}
				// guardar el documento
				documento.setFechaCreacion(new Date());
				documento.setTipoDocumento(new Valor(TipoDocumento.ARCHIVO_ADJUNTADO.getValorId()));
				Forma forma = formaDAO.consultarFormaPorNombre("Blanco"); // Plantilla en Blanco
				documento.setForma(forma);
				Long idDocumento=  documentoDAO.create(documento);
				medio.setDocumentoMedioPrueba(new Documento(idDocumento));
			}
			//Se crea el Medio Prueba
			idMedio=medioPruebaDAO.create(medio);
			
			//Se crea la relación
			RelacionDatoMedioPrueba relacion=new RelacionDatoMedioPrueba();
			relacion.setMedioPrueba(new MedioPrueba(idMedio));
			relacion.setDatoPrueba(new DatoPrueba(datoPruebaDTO.getDatoPruebaId()));
			relacionDatoMedioPruebaDAO.create(relacion);
		}
		
		medioPruebaDTO.setMedioPruebaId(idMedio);
		return medioPruebaDTO;
	}
	
	@Override
	public Long aceptarCarncelarRelacionMedioPrueba(
			RelacionDatoMedioPruebaDTO relacionDatoMedioPruebaDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ACEPTAR O CANCELAR UNA RELACION DE DATO CON MEDIO DE PRUEBA ****/");
		
		if(relacionDatoMedioPruebaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(relacionDatoMedioPruebaDTO.getRelacionDatoMedioPruebaId()==null||relacionDatoMedioPruebaDTO.getEsAceptado()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		RelacionDatoMedioPrueba relacion = relacionDatoMedioPruebaDAO.read(relacionDatoMedioPruebaDTO.getRelacionDatoMedioPruebaId());
		relacion.setEsAceptado(relacionDatoMedioPruebaDTO.getEsAceptado());
		if(!relacionDatoMedioPruebaDTO.getEsAceptado()){
//			if(relacionDatoMedioPruebaDTO.getMotivoCancelacion()==null)
//				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
//			
			relacion.setTiempoCancelacion(new Date());
//			relacion.setMotivoCancelacion(new Valor(relacionDatoMedioPruebaDTO.getMotivoCancelacion().getIdCampo()));
			//TODO cargar el catalogo de motivos y pasar el parametro
		}
		
		relacionDatoMedioPruebaDAO.update(relacion);
		
		return relacion.getRelacionDatoMedioPruebaId();
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.prueba.AdministrarMedioPruebaService#relacionarMedioPruebaConDatoPrueba(mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO, java.util.List)
	 */
	@Override
	public List<MedioPruebaDTO> relacionarMedioPruebaConDatoPrueba(
			DatoPruebaDTO datoPruebaDTO,
			List<MedioPruebaDTO> listaMediosPruebaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA RELACIONAR UN DATO DE PRUEBA A VARIOS MEDIOS DE PRUEBA ****/");
		
		if(datoPruebaDTO==null||listaMediosPruebaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(datoPruebaDTO.getDatoPruebaId()==null||listaMediosPruebaDTO.isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		RelacionDatoMedioPrueba relacion=null;
		for (MedioPruebaDTO medPru : listaMediosPruebaDTO) {
			if(medPru.getMedioPruebaId()==null)
				throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
			
			relacion=new RelacionDatoMedioPrueba();
			relacion.setMedioPrueba(new MedioPrueba(medPru.getMedioPruebaId()));
			relacion.setDatoPrueba(new DatoPrueba(datoPruebaDTO.getDatoPruebaId()));
			relacionDatoMedioPruebaDAO.create(relacion);
		}
		
		return listaMediosPruebaDTO ;
	}

	@Override
	public List<MedioPruebaDTO> consultarMediosPruebaPorDatoPrueba(
			DatoPruebaDTO datoPruebaDTO, Boolean relacionados)throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA RELACIONAR UN DATO DE PRUEBA A VARIOS MEDIOS DE PRUEBA ****/");
		
		if(datoPruebaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(datoPruebaDTO.getExpediente()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(datoPruebaDTO.getExpediente().getNumeroExpediente()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if(relacionados!=null&&datoPruebaDTO.getDatoPruebaId()==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		Long expedienteId = expedienteDAO.consultarExpedienteIdPorNumeroExpediente(datoPruebaDTO.getExpediente().getNumeroExpediente());
		if (expedienteId == null||expedienteId < 0)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		List<MedioPrueba> mediosPrueba=new ArrayList<MedioPrueba>();
		if(relacionados==null){//consulta todos
			mediosPrueba=medioPruebaDAO.consultarMediosPruebaPorDatoPrueba(datoPruebaDTO.getDatoPruebaId(),expedienteId,relacionados);
		}else if(relacionados){//consulta con relación
			mediosPrueba=medioPruebaDAO.consultarMediosPruebaPorDatoPrueba(datoPruebaDTO.getDatoPruebaId(),expedienteId,relacionados);
		}else{//consulta no relacionados
			mediosPrueba=medioPruebaDAO.consultarMediosPruebaPorDatoPrueba(datoPruebaDTO.getDatoPruebaId(),expedienteId,null);
			List<MedioPrueba> mediosRelacionados = medioPruebaDAO.consultarMediosPruebaPorDatoPrueba(datoPruebaDTO.getDatoPruebaId(),expedienteId,true);
			mediosPrueba.removeAll(mediosRelacionados);
		}
		
		List<MedioPruebaDTO> mediosDTO=new ArrayList<MedioPruebaDTO>();
		for (MedioPrueba mp : mediosPrueba) {
			mediosDTO.add(MedioPruebaTransformer.transformarMedioPrueba(mp));
		}
		
		return mediosDTO;
	}

	@Override
	public MedioPruebaDTO consultarMedioPrueba(Long medioPruebaId)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UN MEDIO DE PRUEBA ****/");
		
		if(medioPruebaId==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		MedioPrueba medio = medioPruebaDAO.read(medioPruebaId);
		
		return MedioPruebaTransformer.transformarMedioPrueba(medio);
	}

	@Override
	public List<DatoPruebaDTO> consultarDatosPruebaXMedioPrueba(
			Long medioPruebaId, Boolean relacionados) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LOS DATOS DE PRUEBA ASOCIADOS CON UN MEDIO DE PRUEBA ****/");
		
		if(medioPruebaId==null||relacionados==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<DatoPrueba> datos=new ArrayList<DatoPrueba>();
		List<DatoPrueba> datosRel=new ArrayList<DatoPrueba>();
		if(relacionados)
			datos = medioPruebaDAO.consultarDatosPruebaXMedioPrueba(medioPruebaId);
		else{
			datosRel = medioPruebaDAO.consultarDatosPruebaXMedioPrueba(medioPruebaId);
			if(datosRel.isEmpty())
				throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
			DatoPrueba dato = datosRel.get(0);
			datos=datoPruebaDAO.buscarDatosDePrueba(dato.getExpediente().getExpedienteId());
			datos.removeAll(datosRel);
		}
			
		List<DatoPruebaDTO> datosDTO=new ArrayList<DatoPruebaDTO>();
		for (DatoPrueba dato : datos) {
			datosDTO.add(DatoPruebaTransformer.transformarDatoPruebaBasico(dato));
		}
		
		return datosDTO;
	}


}
