/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.evidencia.EstatusEvidencia;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.eslabon.EslabonDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasNoDevueltasService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;

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
public class ConsultarEvidenciasNoDevueltasServiceImpl implements
		ConsultarEvidenciasNoDevueltasService {

	public final static Logger logger = Logger
			.getLogger(ConsultarEvidenciasNoDevueltasServiceImpl.class);
	@Autowired
	private EslabonDAO eslabonDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasNoDevueltasService
	 * #consultarEvidenciasNoDevueltas(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO)
	 */
	@Override
	public List<EvidenciaDTO> consultarEvidenciasNoDevueltas(
			UsuarioDTO usuarioDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS EVIDENCIAS PRESTADAS NO DEVUELTAS ****/");

		if(usuarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(usuarioDTO.getFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(usuarioDTO.getFuncionario().getClaveFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Busca a que almacen le corresponde al Funcionario*/
		Almacen almacen = funcionarioDAO.obtenerAlmacenXFuncionario(usuarioDTO.getFuncionario().getClaveFuncionario());
		if(almacen==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		else if(almacen.getIdentificadorAlmacen()==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		List<Evidencia> evidencias=eslabonDAO.consultarEvidenciasNoDevueltas(almacen.getIdentificadorAlmacen(),EstatusEvidencia.EN_PRESTAMO.getValorId(),new Date());
		List<EvidenciaDTO> evidsDTO=new ArrayList<EvidenciaDTO>();
		for (Evidencia evi : evidencias) {
			if(evi.getEslabones().size()>0){
				Eslabon eslab = ultimoEslabon(evi.getEslabones().iterator());
				Set<Eslabon> eslabon = new HashSet<Eslabon>(0);
				eslabon.add(eslab);
				evi.setEslabones(eslabon);
				evidsDTO.add(EvidenciaTransformer.transformarEvidencia(evi, true));
			}
		}
		
		return evidsDTO;
	}

	private Eslabon ultimoEslabon(Iterator<Eslabon> iterator) {
		Eslabon resp = new Eslabon();
		Long id = -1L;
		while (iterator.hasNext()) {
			Eslabon eslabon = (Eslabon) iterator.next();
			if (eslabon.getEslabonId() > id)
				resp = eslabon;
		}
		return resp;
	}

}
