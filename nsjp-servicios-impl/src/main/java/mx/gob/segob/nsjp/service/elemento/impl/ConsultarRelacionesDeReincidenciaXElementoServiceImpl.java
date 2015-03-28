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
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.RelacionReincidenciaDAO;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.model.RelacionReincidencia;
import mx.gob.segob.nsjp.service.elemento.ConsultarRelacionesDeReincidenciaXElementoService;
import mx.gob.segob.nsjp.service.elemento.impl.transform.RelacionReincidenciaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultarRelacionesDeReincidenciaXElementoServiceImpl implements
ConsultarRelacionesDeReincidenciaXElementoService {

	private static final Logger logger  = Logger.getLogger(RegistrarRelacionDeReincidenciaServiceImpl.class);

	@Autowired
	private RelacionReincidenciaDAO relacionReincidenciaDAO;

	public List<RelacionReincidenciaDTO> consultarReincidenciasXElemento(Long idElemento) throws NSJPNegocioException {
		
		logger.debug("SERVICIO QUE PERMITE CONSULTAR RELACIONES ENTRE ELEMENTO - CASO(s)");	
		
		if(idElemento==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		List<RelacionReincidencia> llRelaciones = relacionReincidenciaDAO.consultarRelacionesDeReincidencia(idElemento);
		List<RelacionReincidenciaDTO> llRelacionesDTO = new ArrayList<RelacionReincidenciaDTO>();
		
		for (RelacionReincidencia rowRelacionReincidencia : llRelaciones) {
			llRelacionesDTO.add(RelacionReincidenciaTransformer.transformarRelacion(rowRelacionReincidencia));
		}
		
		logger.info(":: Se obtuvieron " + llRelacionesDTO.size() + " relaciones");
		
		return llRelacionesDTO;
	}

}
