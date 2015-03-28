/**
 * Nombre del Programa : AdministrarDatoPruebaServiceImpl.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30/09/2011
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.prueba.DatoPruebaDAO;
import mx.gob.segob.nsjp.dao.prueba.RelacionPruebaInvolucradoDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.model.DatoPrueba;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.RelacionDatoMedioPrueba;
import mx.gob.segob.nsjp.model.RelacionPruebaInvolucrado;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradoAudienciaService;
import mx.gob.segob.nsjp.service.prueba.AdministrarDatoPruebaService;
import mx.gob.segob.nsjp.service.prueba.impl.transformer.DatoPruebaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de los servicios para registrar, modificar, consultar y
 * relacionar los Datos de Preuba
 * 
 * @version 1.0
 * @author GustavoBP
 * 
 */
@Service
@Transactional
public class AdministrarDatoPruebaServiceImpl implements
		AdministrarDatoPruebaService {
	private final static Logger logger = Logger
			.getLogger(AdministrarDatoPruebaServiceImpl.class);
	@Autowired
	private DatoPruebaDAO datoPruebaDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private RelacionPruebaInvolucradoDAO pruebaInvolucradoDAO;
	@Autowired
	private ConsultarInvolucradoAudienciaService consultarInvolucradoAudienciaService;
	

	@Override
	public List<DatoPruebaDTO> consultarDatosPruebaPorFiltro(
			DatoPruebaDTO filtroDTO, String numeroExpediente)
			throws NSJPNegocioException {

		if (numeroExpediente == null || numeroExpediente.trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Long expedienteId = expedienteDAO
				.consultarExpedienteIdPorNumeroExpediente(numeroExpediente);
		if (expedienteId < 0 || expedienteId == null)
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		List<DatoPrueba> datosPrueba = datoPruebaDAO
				.buscarDatosDePrueba(expedienteId);

		List<DatoPruebaDTO> listaDatoPruebaDTO = new ArrayList<DatoPruebaDTO>();
		for (DatoPrueba datoPrueba : datosPrueba) {
			listaDatoPruebaDTO.add(DatoPruebaTransformer
					.transformarDatoPruebaBasico(datoPrueba));
		}

		return listaDatoPruebaDTO;
	}

	@Override
	public DatoPruebaDTO registrarActualizarDatoPrueba(
			DatoPruebaDTO datoPruebaDTO, String numeroExpediente)
			throws NSJPNegocioException {
		DatoPruebaDTO datoPruebaRegresoDTO = new DatoPruebaDTO();

		logger.info(" Servicio: registrarActualizarDatoPrueba ");
		logger.info(" datoPruebaDTO:" + datoPruebaDTO);

		// Solo se aplica al ingresar
		if (datoPruebaDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (datoPruebaDTO.getNombreDato() == null
				|| datoPruebaDTO.getNombreDato().trim().isEmpty()
				|| datoPruebaDTO.getNumeroIdentificacion() == null
				|| datoPruebaDTO.getNumeroIdentificacion().trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		// Determinar si se ingresa o actualiza
		if (datoPruebaDTO.getDatoPruebaId() != null
				&& datoPruebaDTO.getDatoPruebaId() > 0)
			datoPruebaRegresoDTO = this.actualizarDatoPrueba(datoPruebaDTO);
		else
			datoPruebaRegresoDTO = this.registrarDatoPrueba(datoPruebaDTO,
					numeroExpediente);
		return datoPruebaRegresoDTO;
	}

	private DatoPruebaDTO actualizarDatoPrueba(DatoPruebaDTO datoPruebaDTO)
			throws NSJPNegocioException {
		logger.info(" Servicio: actualizarDatoPrueba ");
		logger.info(" datoPruebaDTO -ID:" + datoPruebaDTO.getDatoPruebaId());

		// Consultar en BD
		DatoPrueba datoPruebaBD = datoPruebaDAO.read(datoPruebaDTO
				.getDatoPruebaId());
		if (datoPruebaBD == null)
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		// Se actualizan los valores en la entidad recuperada de BD
		datoPruebaBD = DatoPruebaTransformer.transformarDatoPruebaUpdate(
				datoPruebaBD, datoPruebaDTO);

		datoPruebaDAO.update(datoPruebaBD);

		return datoPruebaDTO;
	}

	private DatoPruebaDTO registrarDatoPrueba(DatoPruebaDTO datoPruebaDTO,
			String numeroExpediente) throws NSJPNegocioException {
		logger.info(" Servicio: registrarDatoPrueba ");
		datoPruebaDTO.setDatoPruebaId(null);

		// Transformar
		DatoPrueba datoPrueba = DatoPruebaTransformer
				.transformarDatoPrueba(datoPruebaDTO);

		if (numeroExpediente == null || numeroExpediente.trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Long expedienteId = expedienteDAO
				.consultarExpedienteIdPorNumeroExpediente(numeroExpediente);

		if (expedienteId < 0 || expedienteId == null)
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		datoPrueba.setExpediente(new Expediente(expedienteId));

		// Registrar en BD
		Long idDatoPrueba = datoPruebaDAO.create(datoPrueba);
		if (idDatoPrueba == null)
			throw new NSJPNegocioException(
					CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);

		datoPruebaDTO.setDatoPruebaId(idDatoPrueba);

		return datoPruebaDTO;
	}

	public DatoPruebaDTO aceptarCancelarDatoPrueba(DatoPruebaDTO datoPruebaDTO)
			throws NSJPNegocioException {
		logger.info(" Servicio: aceptarCancelarDatoPrueba ");
		if (datoPruebaDTO == null || datoPruebaDTO.getDatoPruebaId() == null
				|| datoPruebaDTO.getEsAceptado() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		DatoPrueba datoPrueba = datoPruebaDAO.read(datoPruebaDTO
				.getDatoPruebaId());
		datoPrueba.setEsAceptado(datoPruebaDTO.getEsAceptado());
		if (!datoPrueba.getEsAceptado()) {
			datoPrueba.setTiempoCancelacion(new Date());
//			if (datoPruebaDTO.getMotivoCancelacion() == null)
//				throw new NSJPNegocioException(
//						CodigoError.PARAMETROS_INSUFICIENTES);
//			else
//				datoPrueba.setMotivoCancelacion(new Valor(datoPruebaDTO
//						.getMotivoCancelacion().getIdCampo()));
			//TODO FALTA CARGAR EL CATALOGO DE MOTIVO Y PASAR EL VALOR
		}
		datoPruebaDAO.update(datoPrueba);

		return datoPruebaDTO;
	}

	@Override
	public Long relacionarPruebaAInvolucrado(DatoPruebaDTO datoPruebaDTO,
			List<InvolucradoDTO> listaResponsables) throws NSJPNegocioException {
		logger.info(" Servicio: relacionarPruebaAInvolucrado ");
		logger.info(" DatoPrueba: " + datoPruebaDTO.getDatoPruebaId());
		logger.info(" Responsables: " + listaResponsables);

		if (datoPruebaDTO == null || listaResponsables == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (datoPruebaDTO.getDatoPruebaId() == null
				|| listaResponsables.isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/*
		 * Revisar que el datoPrueba ya sea una Prueba, i.e.: Es aceptada y
		 * tiene al menos un medio de prueba aceptado
		 */
		DatoPrueba prueba = datoPruebaDAO.read(datoPruebaDTO.getDatoPruebaId());
		Boolean esPrueba = false;
		if (prueba.getEsAceptado() != null && prueba.getEsAceptado()) {
			for (RelacionDatoMedioPrueba relAMedio : prueba
					.getRelacionesDatosMedioPrueba()) {
				if (relAMedio.getEsAceptado()!= null &&  relAMedio.getEsAceptado()) {
					esPrueba = true;
					break;
				}
			}
		} else{
			logger.info("El Dato Prueba Id:"+ prueba.getDatoPruebaId() + " - No es Aceptado:"+prueba.getEsAceptado() );
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);
			}
		

		if (!esPrueba){
			logger.info("El Dato Prueba Id:"+ prueba.getDatoPruebaId() + " - No tiene ninguna relación con Medio Prueba Aceptada:");
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		}

		RelacionPruebaInvolucrado relacionInvolucrado = null;
		Long idRelacion = null;
		for (InvolucradoDTO resp : listaResponsables) {
			// TODO GBASURTO: Revisar si se requiere que sea clave compuesta de
			// la relación
			relacionInvolucrado = new RelacionPruebaInvolucrado();
			relacionInvolucrado.setDatoPrueba(new DatoPrueba(datoPruebaDTO
					.getDatoPruebaId()));
			relacionInvolucrado.setInvolucrado(new Involucrado(resp
					.getElementoId()));
			idRelacion = pruebaInvolucradoDAO.create(relacionInvolucrado);
		}

		// return datoPruebaDTO.getDatoPruebaId();
		return idRelacion;
	}
	
	@Override
	public List<DatoPruebaDTO> consultarDatosPruebaXAudiencia(
			List<InvolucradoDTO> listaResponsables, String numeroExpediente)
			throws NSJPNegocioException {

		logger.info(" Servicio: consultarDatosPruebaXAudiencia ");

		if (numeroExpediente == null || numeroExpediente.trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Long expedienteId = expedienteDAO
				.consultarExpedienteIdPorNumeroExpediente(numeroExpediente);
		if (expedienteId < 0 || expedienteId == null)
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		List<DatoPrueba> datosPrueba=new ArrayList<DatoPrueba>();
		if(listaResponsables==null||listaResponsables.isEmpty()){
			datosPrueba = datoPruebaDAO.buscarDatosDePrueba(expedienteId);
		}else{
			List<Long> involucradosId = new ArrayList<Long>();
			for (InvolucradoDTO involucradoDTO : listaResponsables)
				if(involucradoDTO.getElementoId()!=null)
					involucradosId.add(involucradoDTO.getElementoId());
			
			datosPrueba = datoPruebaDAO.buscarDatosDePruebaPorInvolucrados(involucradosId);
		}

		List<DatoPruebaDTO> listaDatoPruebaDTO = new ArrayList<DatoPruebaDTO>();
		for (DatoPrueba datoPrueba : datosPrueba) {
			listaDatoPruebaDTO.add(DatoPruebaTransformer
					.transformarDatoPruebaBasico(datoPrueba));
		}

		return listaDatoPruebaDTO;
	}

	@Override
	public DatoPruebaDTO consultarDatoPrueba(Long datoPruebaId)
			throws NSJPNegocioException {
		
		logger.info(" Servicio: consultarDatoPrueba ");

		if (datoPruebaId == null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		DatoPrueba dato = datoPruebaDAO.read(datoPruebaId);
		if(dato==null)
			return new DatoPruebaDTO();
		
		return DatoPruebaTransformer.transformarDatoPruebaCompleto(dato);
	}

	@Override
	public List<InvolucradoViewDTO> obtenerImputadosAudienciaSinRelacionConPrueba(
			AudienciaDTO audienciaDTO, DatoPruebaDTO datoPruebaDTO, String numeroExpediente) throws NSJPNegocioException {

		logger.info(" Servicio: obtenerImputadosAudienciaSinRelacionConPrueba ");

		if (audienciaDTO == null || audienciaDTO.getId()==null || audienciaDTO.getId()<0  || datoPruebaDTO==null 
				|| numeroExpediente ==null || numeroExpediente.trim().isEmpty() 
				|| datoPruebaDTO.getDatoPruebaId()== null || datoPruebaDTO.getDatoPruebaId() <0 )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Long expedienteId = expedienteDAO.consultarExpedienteIdPorNumeroExpediente(numeroExpediente);
		if (expedienteId < 0 || expedienteId == null)
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		//Consultar todos los imputados de la audiencia
		List<InvolucradoViewDTO> involucradosAU = consultarInvolucradoAudienciaService.obtenerImputadosAudiencia(audienciaDTO);
		
		//Consultar los involucrados con Dato Pruebas asociados al Expediente
		List<Involucrado> involucradosDP = datoPruebaDAO.consultarInvolucradoConRelacionADatoPrueba(
				expedienteId, datoPruebaDTO.getDatoPruebaId());
		
		//Recorrer la lista de involucradoDP para descartarlos de la lista de involucradosAU
		
		 for (Involucrado involucrado : involucradosDP) {
			Long idInvolucradoDP = involucrado.getElementoId();
			
			for (int cont = 0; cont < involucradosAU.size(); cont++) {
				InvolucradoViewDTO involucradoAU = involucradosAU.get(cont);
				if(involucradoAU.getInvolucradoId().equals(idInvolucradoDP)){
					involucradosAU.remove(cont);
					break;
				}
			}
		}
		return involucradosAU;
	}
	
	@Override
	public List<DatoPruebaDTO> consultarPruebasPorNumeroExpediente(String numeroExpediente)
			throws NSJPNegocioException {

		logger.info(" Servicio: consultarPruebasPorNumeroExpediente " + numeroExpediente);

		if (numeroExpediente == null || numeroExpediente.trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Long expedienteId = expedienteDAO
				.consultarExpedienteIdPorNumeroExpediente(numeroExpediente);
		if (expedienteId < 0 || expedienteId == null)
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		List<DatoPrueba> pruebas= datoPruebaDAO.buscarPruebasPorExpediente(expedienteId);

		List<DatoPruebaDTO> listaDatoPruebaDTO = new ArrayList<DatoPruebaDTO>();
		for (DatoPrueba datoPrueba : pruebas) {
			listaDatoPruebaDTO.add(DatoPruebaTransformer
					.transformarDatoPruebaCompleto(datoPrueba));
		}

		return listaDatoPruebaDTO;
	}
}
