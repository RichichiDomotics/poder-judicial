/**
 * Nombre del Programa : AsociarDelitoResponsableVictimaServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 07/07/2011
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
package mx.gob.segob.nsjp.service.delito.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.delito.AsociarDelitoResponsableVictimaService;
import mx.gob.segob.nsjp.service.forma.impl.ConsultarFormaPlantillaServiceImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class AsociarDelitoResponsableVictimaServiceImpl implements
		AsociarDelitoResponsableVictimaService {

	public final static Logger logger = Logger
			.getLogger(ConsultarFormaPlantillaServiceImpl.class);

	@Autowired
	private DelitoPersonaDAO dpDao;
	@Autowired
	private DelitoDAO delitoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.delito.AsociarDelitoResponsableVictimaService
	 * #asociarDelitoResponsableVictima
	 * (mx.gob.segob.nsjp.dto.expediente.DelitoDTO,
	 * mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO,
	 * mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO, java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public Long asociarDelitoResponsableVictima(Long asociacionID,
			DelitoDTO delitoDTO, InvolucradoDTO responsableDTO,
			InvolucradoDTO victimaDTO, Long formaParticipacion,
			Long bienTutelado, Long idClasificacion, Long idLugar,
			Long idModalidad,Long idModus, Long idCausa) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASOCIAR (SAVEorUPDATE) UN DELITO CON SU PROBABLE RESPONSABLE Y, SI TIENE, CON LA VICTIMA Y CALIDAD DE TUTELADO ****/");

		/* Verificación de parámetros */
		if (delitoDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (delitoDTO.getDelitoId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if (responsableDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (responsableDTO.getElementoId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
//		if (formaParticipacion == null)
//			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		Delito delito = delitoDAO.read(delitoDTO.getDelitoId());
		
		DelitoPersona delPer = new DelitoPersona();
		delPer.setDelitoPersonaId(asociacionID);
		delPer.setDelito(delito);
		delPer.setProbableResponsable(new Involucrado(responsableDTO
				.getElementoId()));
		if (victimaDTO != null)
			delPer.setVictima(new Involucrado(victimaDTO.getElementoId()));
		delPer.setFormaParticipacion(new Valor(formaParticipacion));
		if(bienTutelado!=null){
		delPer.setBienTutelado(new Valor(bienTutelado));}
		
		delPer.setCatDelitoClasificacionId(idClasificacion);
		delPer.setCatDelitoLugarId(idLugar);
		delPer.setCatDelitoModalidadId(idModalidad);
		delPer.setCatDelitoModusId(idModus);
		delPer.setCatDelitoCausaId(idCausa);
		
		//Inicialmente se registra inicialmente en 1
		delPer.setEsActivo(true);
		
		Long id;
		if(asociacionID==null){
			id= dpDao.create(delPer);
		}
		else{
			id=asociacionID;
			dpDao.update(delPer);
		}
		
		return id;
	}
	
	@Override
	public Boolean existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(Long idDelito, Long idProbableResponsable, Long idVictima, Long idFormaParticipacion)  throws NSJPNegocioException {
		Boolean existeRelacion = false;

		if (idDelito == null || idDelito < 0 || idProbableResponsable == null
				|| idProbableResponsable < 0 || idVictima == null
				|| idVictima < 0 || idFormaParticipacion == null
				|| idFormaParticipacion < 0) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		existeRelacion = dpDao.existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(
				idDelito, idProbableResponsable, idVictima, idFormaParticipacion);
		
		return existeRelacion; 
	}
}
