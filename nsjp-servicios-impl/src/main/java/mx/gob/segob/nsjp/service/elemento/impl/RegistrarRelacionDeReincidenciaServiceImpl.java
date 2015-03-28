/**
 * Nombre del Programa  : RegistrarRelacionDeReincidenciaServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 23 Ago 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Permite relacionar un elemento con varios casos, con el objetivo
 * 						  de conocer las reincidencias dentro de un expediente, 
 * 						  ya sea de una persona o un vehiculo.
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */

package mx.gob.segob.nsjp.service.elemento.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.RelacionReincidenciaDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.elemento.RegistrarRelacionDeReincidenciaService;
import mx.gob.segob.nsjp.service.elemento.impl.transform.RelacionReincidenciaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarRelacionDeReincidenciaServiceImpl implements
	RegistrarRelacionDeReincidenciaService {

	private static final Logger logger  = Logger.getLogger(RegistrarRelacionDeReincidenciaServiceImpl.class);

	@Autowired
	private RelacionReincidenciaDAO relacionReincidenciaDAO;

	public List<RelacionReincidenciaDTO> registrarReinicidencias(Long idElemento,
			List<Long> idCasos, Long idFuncionario) throws NSJPNegocioException {
		
		logger.debug("SERVICIO QUE PERMITE REGISTRAR RELACIONES ENTRE ELEMENTO - CASO(s)");	
		
		if(idElemento==null || idCasos==null || idCasos.isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		List<RelacionReincidenciaDTO> llRelacioesDTO = new ArrayList<RelacionReincidenciaDTO>();
		
		for (Long idCaso : idCasos) {
			RelacionReincidenciaDTO loRelacionDTO = new RelacionReincidenciaDTO();
			loRelacionDTO.setCaso(new CasoDTO(idCaso));
			loRelacionDTO.setFechaRelacion(new Date());
			loRelacionDTO.setFuncionario(new FuncionarioDTO(idFuncionario));
			loRelacionDTO.setElemento(new ElementoDTO(idElemento));
			llRelacioesDTO.add(loRelacionDTO);			
			
		}

		List<RelacionReincidenciaDTO> llRespDTO = new ArrayList<RelacionReincidenciaDTO>();
		for (RelacionReincidenciaDTO relacionReincidenciaDTO : llRelacioesDTO) {
			Long idObjeto = relacionReincidenciaDAO.create(RelacionReincidenciaTransformer.transformarRelacion(relacionReincidenciaDTO));
			RelacionReincidenciaDTO loResp = new RelacionReincidenciaDTO();
			loResp.setRelacionReincidenciaId(idObjeto);
			llRespDTO.add(loResp);
		}
		
		
		logger.info(":: Las relaciones se registraron con exito :: ");
		
		return llRespDTO;
	}

}
