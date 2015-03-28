package mx.gob.segob.nsjp.service.familiar.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.FamiliarDAO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.Familiar;
import mx.gob.segob.nsjp.service.familiar.ConsultarFamiliaresPorIdEntrevistaComplementariaService;
import mx.gob.segob.nsjp.service.sesion.impl.transform.FamiliarTransformer;
import mx.gob.segob.nsjp.service.sesion.impl.transform.SesionTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rgama
 *
 */
@Service
@Transactional
public class ConsultarFamiliaresPorIdEntrevistaComplementariaServiceImpl implements ConsultarFamiliaresPorIdEntrevistaComplementariaService {
		
	public final static Logger logger = 
		Logger.getLogger(ConsultarFamiliaresPorIdEntrevistaComplementariaServiceImpl.class);
	
	@Autowired
	private FamiliarDAO familiarDAO;

	@Override
	public List<FamiliarDTO> consultarFamiliaresPorIdEntrevistaComplementaria(
			EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar Familiares ****/");
		
		if (aoEntrevistaComplementariaDTO == null || aoEntrevistaComplementariaDTO.getSesionId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<FamiliarDTO> loFamiliares = new ArrayList<FamiliarDTO>();
		List<Familiar> llFamiliaresBD = familiarDAO.consultarFamiliaresPorIdEntrevistaComplementaria(
				(EntrevistaComplementaria) SesionTransformer.transformarSesionDTO(aoEntrevistaComplementariaDTO));
		
		if(llFamiliaresBD != null && llFamiliaresBD.size() > 0)
			for (Familiar sesionBD : llFamiliaresBD) {
				loFamiliares.add(FamiliarTransformer.transformarFamiliar(sesionBD));				
			}
		return loFamiliares;
	}

}
