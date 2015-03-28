/**
 * 
 */
package mx.gob.segob.nsjp.service.funcionario.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.service.expediente.impl.transform.JerarquiaOrganizacionalTransformer;
import mx.gob.segob.nsjp.service.funcionario.ConsultarAreasAgenciaService;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarAreasAgenciaServiceImpl implements
		ConsultarAreasAgenciaService {
	
	private final static Logger logger = Logger.getLogger(ConsultarAreasAgenciaServiceImpl.class);
	
	@Autowired
	private FuncionarioDAO funcionarioDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionario.ConsultarAreasAgenciaService#consultarAreasXAgencia(java.lang.Long)
	 */
	@Override
	public List<JerarquiaOrganizacionalDTO> consultarAreasXAgencia(
			Long idAgencia) throws NSJPNegocioException {
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR AREAS POR AGENCIA ****/");
		
		if(idAgencia==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<JerarquiaOrganizacional> areas =funcionarioDAO.consultarAreasXAgencia(idAgencia);
		List<JerarquiaOrganizacionalDTO> areasDTO=new ArrayList<JerarquiaOrganizacionalDTO>();
		for (JerarquiaOrganizacional area : areas) {
			areasDTO.add(JerarquiaOrganizacionalTransformer.transformarJerarquiaOrganizacional(area));
		}
		
		return areasDTO;
	}

}
