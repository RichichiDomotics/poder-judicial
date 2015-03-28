/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.CuerpoOficioEstructuradoDAO;
import mx.gob.segob.nsjp.dao.documento.IndiceEstructuradoDAO;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.model.CuerpoOficioEstructurado;
import mx.gob.segob.nsjp.model.IndiceEstructurado;
import mx.gob.segob.nsjp.service.documento.ConsultarCuerpoOficioService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.CuerpoOficioEstructuradoTransformer;

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
public class ConsultarCuerpoOficioServiceImpl implements
		ConsultarCuerpoOficioService {

	public final static Logger logger = Logger
			.getLogger(ConsultarCuerpoOficioServiceImpl.class);
	@Autowired
	private CuerpoOficioEstructuradoDAO estructuradoDAO;
	@Autowired
	private IndiceEstructuradoDAO indiceDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.documento.ConsultarCuerpoOficioService#
	 * consultarCuerpoOficio(java.lang.Long)
	 */
	@Override
	public CuerpoOficioEstructuradoDTO consultarCuerpoOficio(
			CuerpoOficioEstructuradoDTO cuerpoOficioDTO)
			throws NSJPNegocioException {
		CuerpoOficioEstructuradoDTO cuerpoOEDTO = null;
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR PLANTILLAS UN CUERPO DE OFICIO ****/");

		/* Verificación de parámetros */
		if (cuerpoOficioDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		CuerpoOficioEstructurado cuerpo = new CuerpoOficioEstructurado();
		/* Operación */
		if (cuerpoOficioDTO.getCuerpoOficioEstructuradoId() != null){
			cuerpo = estructuradoDAO.read(cuerpoOficioDTO
					.getCuerpoOficioEstructuradoId());
			if(cuerpo!= null){
				cuerpoOEDTO = CuerpoOficioEstructuradoTransformer.transformarCuerpo(cuerpo);
				cuerpoOEDTO.setTexto(cuerpo.getTexto());
			}
		}
		else{
			if(cuerpoOficioDTO.getIndiceEstructurado()==null)
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			else if(cuerpoOficioDTO.getIndiceEstructurado().getIndiceEstructuradoId()==null)
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			else{
				IndiceEstructurado indice = indiceDAO.read(cuerpoOficioDTO.getIndiceEstructurado().getIndiceEstructuradoId());
				cuerpo=new CuerpoOficioEstructurado();
				cuerpo.setIndiceEstructurado(indice);
				cuerpoOEDTO= CuerpoOficioEstructuradoTransformer.transformarCuerpo(cuerpo);
			}
		}

		return cuerpoOEDTO;
	}

}
