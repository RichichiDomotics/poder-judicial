/**
* Nombre del Programa : GenerarExpedienteJARServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 3 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para generar el expediente del area de Justicia Alterna Restaurativa
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

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.service.delito.RegistrarDelitoDenunciaService;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.GenerarExpedienteJARService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para generar el expediente del area de Justicia Alterna Restaurativa.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class GenerarExpedienteJARServiceImpl implements
		GenerarExpedienteJARService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(GenerarExpedienteJARServiceImpl.class);
	
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired 
	private DelitoDAO delitoDAO;
	
	@Autowired
	private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
	@Autowired
	private RegistrarDelitoDenunciaService registrarDelitoDenunciaService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.expediente.GenerarExpedienteJARService#generarExpJusticaAltRest(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public ExpedienteDTO generarExpJusticaAltRest(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GENERAR UN EXPEDIENTE AL AREA DE JUSTICA PENAL RESTAURATIVA ****/");
		
		Expediente expediente = expedienteDAO.read(expedienteDTO.getExpedienteId());
		expedienteDTO.setCasoDTO(new CasoDTO(expediente.getCaso().getCasoId()));
		expedienteDTO.setNarrativa(expediente.getDescNarrativa());
		expedienteDTO.setFechaApertura(new Date());
		expedienteDTO.setArea(new AreaDTO(Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA));
		
		ExpedienteDTO nuevoExpDTO = asignarNumeroExpedienteService.asignarNumeroExpediente(expedienteDTO);
		
		List<Delito> delitosExp = delitoDAO.obtenerDelitoPorExpediente(expediente.getExpedienteId());
		List<DelitoDTO> delitosDTO = new ArrayList<DelitoDTO>();
		for (Delito delito : delitosExp) {
			delito.setExpediente(new Expediente(nuevoExpDTO.getExpedienteId()));
			delitosDTO.add(DelitoTransfromer.transformarDelito(delito));
		}		
		registrarDelitoDenunciaService.registrarDelitoDenuncia(delitosDTO);
		
		List<Involucrado> involucradosExp = involucradoDAO.consultarInvolucradosByExpediente(expediente.getExpedienteId());
		for (Involucrado involucrado : involucradosExp) {
			
		}
		
		return nuevoExpDTO;
	}

}
