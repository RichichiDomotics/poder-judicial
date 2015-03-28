/**
* Nombre del Programa : ConsultarNumerosExpedientesPorEtapaServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para Consultar Expedientes por etapa
* en base al area y al usuario logueado en el sistema
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
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoMovimiento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.bitacora.RegistrarBitacoraService;
import mx.gob.segob.nsjp.service.expediente.ConsultarExpedientesPorEtapaService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para Consultar Expedientes por etapa
 * en base al area y al usuario logueado en el sistema
 * @version 1.0
 * @author rgama
 *
 */
@Service
@Transactional
public class ConsultarExpedientesPorEtapaServiceImpl implements
	ConsultarExpedientesPorEtapaService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(ConsultarExpedientesPorEtapaServiceImpl.class);

	@Autowired
	private ExpedienteDAO expedienteDAO;	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private DefensaInvolucradoDAO defensaInvolucradoDAO;
	@Autowired
	private RegistrarBitacoraService registrarBitacoraService;
	
	@Override
	public List<ExpedienteDTO> consultarExpedientesPorEtapa(EtapasExpediente etapa, Long usuarioId,Long areaId)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR EXPEDIENTES POR ETAPA ****/");
		
		if (etapa.getValorId()==null || usuarioId ==null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);			
		}	
		
		List<NumeroExpediente> numerosExpediente = expedienteDAO.consultarExpedientesPorEtapa(etapa, usuarioId, areaId);
		logger.info("Expedientes recuperados : "+numerosExpediente.size());
		
		List<ExpedienteDTO> listRetorno = new ArrayList<ExpedienteDTO>();
		List<Involucrado> lista = null;
		ExpedienteDTO expediente =null;
		for (NumeroExpediente numeroExpediente : numerosExpediente) {
			expediente = ExpedienteTransformer.transformarExpedienteDefensoria(numeroExpediente);
			lista = involucradoDAO.consultarInvolucradosByExpediente(numeroExpediente.getExpediente().getExpedienteId());
			for(Involucrado inv : lista){
				expediente.addInvolucradoDTO(InvolucradoTransformer.transformarInvolucradoBasico(inv));
			}
            Involucrado defendido = defensaInvolucradoDAO.consultarInvolucradoNumeroExpedienteDefensoria(expediente.getNumeroExpedienteId());
            if(defendido != null){
            	expediente.setInputado(InvolucradoTransformer.transformarInvolucrado(defendido));
            }
			listRetorno.add(expediente);
		}
				
		return listRetorno;
	}


	public EtapasExpediente consultarEtapaDelExpediente(Long numeroExpedienteId) throws NSJPNegocioException {
		NumeroExpediente expediente = numeroExpedienteDAO.read(numeroExpedienteId);
		EtapasExpediente etapa = EtapasExpediente.getByValor(expediente.getEtapa().getValorId());
//		if(etapa == EtapasExpediente.EJECUCION){
//			throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
//		}
		return etapa;
	}
	
	public void cambiarEtapaDelExpediente(Long numeroExpedienteId, EtapasExpediente etapa) throws NSJPNegocioException{
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REALIZAR EL CAMBIO DE ETAPA DEL EXPEDIENTE ****/");
		
		if (numeroExpedienteId==null || etapa==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES); 
		
		NumeroExpediente expediente = numeroExpedienteDAO.read(numeroExpedienteId);
    	Long idEstatus = 0L;
    	switch(etapa){
			case ASESORIA:
				idEstatus = EstatusExpediente.ABIERTO.getValorId();
				break;
	    	case CONCILIACION_Y_MEDIACION:
				idEstatus = EstatusExpediente.ABIERTO_RESTAURATIVA.getValorId();
				break;
			case INTEGRACION:
				idEstatus = EstatusExpediente.ABIERTO_INTEGRACION.getValorId();
				break;
			case TECNICA:
				idEstatus = EstatusExpediente.ABIERTO_TECNICA_SIN_CARPETA.getValorId();
				break;
			case EJECUCION:
				idEstatus = EstatusExpediente.ABIERTO_EJECUCION.getValorId();
				break;
		}
		expediente.setEtapa(new Valor(etapa.getValorId()));
		expediente.setEstatus(new Valor(idEstatus));
		if (logger.isDebugEnabled()) {
		    logger.debug("Etapa :: " + etapa.getValorId() + ", Estatus :: " + idEstatus) ;
		   
		}
		numeroExpedienteDAO.update(expediente);
		
		RegistroBitacora regBitEta = new RegistroBitacora();
		
		regBitEta.setFechaInicio(new Date());
		regBitEta.setTipoMovimiento(new Valor(TipoMovimiento.CAMBIO_DE_ETAPA_DE_EXPEDIENTE.getValorId()));
		regBitEta.setNuevo(String.valueOf(expediente.getEtapa().getValorId()));
		regBitEta.setNumeroExpediente(expediente);

		registrarBitacoraService.registrarMovimientoDeExpedienteEnBitacora(regBitEta);
		
		RegistroBitacora regBitEst = new RegistroBitacora();
		
		regBitEst.setFechaInicio(new Date());
		regBitEst.setTipoMovimiento(new Valor(TipoMovimiento.CAMBIO_DE_ESTATUS_DE_EXPEDIENTE.getValorId()));
		regBitEst.setNuevo(String.valueOf(idEstatus));
		regBitEst.setNumeroExpediente(expediente);

		registrarBitacoraService.registrarMovimientoDeExpedienteEnBitacora(regBitEst);
	}



}
