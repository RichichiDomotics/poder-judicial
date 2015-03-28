/**
 * 
 */
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.expediente.ConsultarActaCircunstanciadaService;
import mx.gob.segob.nsjp.service.forma.impl.ConsultarFormaPlantillaServiceImpl;
import mx.gob.segob.nsjp.service.hecho.impl.transform.HechoTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarActaCircunstanciadaServiceImpl implements
		ConsultarActaCircunstanciadaService {

	public final static Logger logger = Logger
			.getLogger(ConsultarFormaPlantillaServiceImpl.class);
	@Autowired
	private HechoDAO hechoDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.expediente.ConsultarActaCircunstanciadaService
	 * #consultarActaCircunstaciada
	 * (mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public ActaCircunstanciadaDTO consultarActaCircunstaciada(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR PLANTILLAS POR TIPO DE DOCUMENTO ****/");

		/* Verificación de parámetros */
		if (expedienteDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (expedienteDTO.getExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		Hecho hecho = hechoDAO.consultarHechoByExpediente(expedienteDTO
				.getExpedienteId());
		List<Involucrado> ivolucrados = involucradoDAO
				.consultarInvolucradosByExpediente(expedienteDTO
						.getExpedienteId());

		InvolucradoDTO involucradoDTO = null;
		if (ivolucrados.size() > 0)
			for (Involucrado inv : ivolucrados) {
				if (inv.getCalidad().getTipoCalidad().getValorId()
						.equals(Calidades.DENUNCIANTE.getValorId())){
					Domicilio domic = domicilioDAO.obtenerDomicilioByRelacion(inv.getElementoId(), new Long(Relaciones.RESIDENCIA.ordinal()));
					involucradoDTO = InvolucradoTransformer
							.transformarInvolucrado(inv);
					if(domic!=null)
					involucradoDTO.setDomicilio(DomicilioTransformer.transformarDomicilio(domic));
				}
			}

		HechoDTO hechoDTO=null;
		if(hecho!=null)
		 hechoDTO= HechoTransformer.transformarHecho(hecho);
		return new ActaCircunstanciadaDTO(involucradoDTO, hechoDTO);
	}

}
