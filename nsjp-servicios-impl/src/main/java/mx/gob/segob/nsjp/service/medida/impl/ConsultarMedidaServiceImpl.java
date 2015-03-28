/**
* Nombre del Programa : ConsultarMedidaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11/08/2011
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
package mx.gob.segob.nsjp.service.medida.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.medida.CompromisoPeriodicoDAO;
import mx.gob.segob.nsjp.dao.medida.IncidenciaDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.model.CompromisoPeriodico;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Incidencia;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.medida.ConsultarMedidaService;
import mx.gob.segob.nsjp.service.medida.impl.transform.MedidaAlternaTransformer;
import mx.gob.segob.nsjp.service.medida.impl.transform.MedidaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementacion del Servicio de Consultar Medidas 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ConsultarMedidaServiceImpl implements ConsultarMedidaService{

	private final static Logger logger = Logger.getLogger(ConsultarMedidaServiceImpl.class);
	 
	@Autowired
	private MedidaDAO medidaDAO;
	@Autowired
	private MedidaAlternaDAO medidaAlternaDAO;
	@Autowired
	private IncidenciaDAO incidenciaDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;	
	@Autowired
	private ConsultarIndividuoService consultarIndividuoService;
	@Autowired
	private CompromisoPeriodicoDAO compromisoPeriodicoDAO;
	
	
	public MedidaDTO consultarMedida(Long idMedida, Boolean esMedidaAlterna) throws NSJPNegocioException{
		if(idMedida==null || idMedida<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		MedidaDTO  medidaDTO  = new MedidaDTO() ;
		Medida medidaBD = null;
		
		if(esMedidaAlterna)
			medidaBD = medidaAlternaDAO.read(idMedida);
		else  
			medidaBD = medidaDAO.read(idMedida);
		
		if(medidaBD==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		//Consultar las incidencias asociadas a la Medida
		List<Incidencia> incidencias = incidenciaDAO.consultarIncidenciaPorMedida(medidaBD.getDocumentoId());
		medidaBD.setIncidencias(incidencias);
		
		//Consultar CompromisoPeriodico
		CompromisoPeriodico compromisoPeriodico = compromisoPeriodicoDAO.consultarCompromisoPeriodicoPorMedida(medidaBD.getDocumentoId());
		if(compromisoPeriodico!= null)
			medidaBD.setCompromisoPeriodico(compromisoPeriodico);
		
		if(medidaBD instanceof MedidaAlterna )
			medidaDTO =MedidaAlternaTransformer.transformarMedidaAlterna((MedidaAlterna) medidaBD);
		else
			medidaDTO =MedidaTransformer.transformarMedida(medidaBD);

		//Consultar involucrado 
		logger.info("Involucrado:"+ medidaDTO.getInvolucrado().getElementoId());
		if(medidaDTO.getInvolucrado()!= null && medidaDTO.getInvolucrado().getElementoId()!= null){
			InvolucradoDTO involucradoDTO = consultarIndividuoService.obtenerIndividuoDTO(new InvolucradoDTO(medidaDTO.getInvolucrado().getElementoId()));
			medidaDTO.setInvolucrado(involucradoDTO);
			logger.info("Involucrado:"+ medidaDTO.getInvolucrado().getElementoId());
		}
		
		//Consultar Domicilio
		if(medidaBD.getDomicilio()!= null && medidaBD.getDomicilio().getElementoId()!= null){
			Domicilio domicilio = domicilioDAO.read(medidaBD.getDomicilio().getElementoId());
			//Domicilio domicilio = domicilioDAO.obtenerDomicilioById(medidaBD.getDomicilio().getElementoId());
			DomicilioDTO domicilioDTO = DomicilioTransformer.transformarDomicilio(domicilio);
			medidaDTO.setDomicilio(domicilioDTO);
			logger.info("Domicilio:"+ medidaDTO.getDomicilio().getElementoId());
		}			
		
		
		return medidaDTO;
	}
}
