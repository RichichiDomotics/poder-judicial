/**
 * Nombre del Programa  : RegistrarResolutivoAudienciaServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un resolutivo en la audiencia
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.ResolutivoDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.model.Resolutivo;
import mx.gob.segob.nsjp.service.audiencia.RegistrarResolutivoAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.ResolutivoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementación para el registro un resolutivo en la audiencia
 * @version 1.0
 * @author vaguirre
 *
 */
@Service
@Transactional
public class RegistrarResolutivoAudienciaServiceImpl implements
		RegistrarResolutivoAudienciaService {

	@Autowired
	private ResolutivoDAO resolutivoDAO;
	
	@Override
	public Long registrarResolutivoAudiencia(ResolutivoDTO resolutivo) throws NSJPNegocioException {
		Resolutivo resolut = ResolutivoTransformer.transformarResolutivo(resolutivo);
		return resolutivoDAO.create(resolut);
	}

	@Override
	public void modificarResolutivoAudiencia(ResolutivoDTO resolutivo) {
		Resolutivo res = resolutivoDAO.read(resolutivo.getResolutivoId());
		res.setDetalle(resolutivo.getDetalle());
		res.setTemporizador(resolutivo.getTemporizador());
		resolutivoDAO.saveOrUpdate(res);
	}

	@Override
	public void eliminarResolutivoAudiencia(ResolutivoDTO resolutivo) {
		Resolutivo res = resolutivoDAO.read(resolutivo.getResolutivoId());
		resolutivoDAO.delete(res);
	}

}
