/**
 * 
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.almacen.AlmacenDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.service.objeto.AsociarObjetoAlmacenService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

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
public class AsociarObjetoAlmacenServiceImpl implements
		AsociarObjetoAlmacenService {
	
	public final static Logger logger = 
		Logger.getLogger(AsociarObjetoAlmacenServiceImpl.class);
	
	@Autowired
	private ObjetoDAO objetoDAO;
	@Autowired
	private AlmacenDAO almacenDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.objeto.AsociarObjetoAlmacenService#asociarObjetoAlmacen(mx.gob.segob.nsjp.dto.objeto.ObjetoDTO, mx.gob.segob.nsjp.dto.almacen.AlmacenDTO)
	 */
	@Override
	public ObjetoDTO asociarObjetoAlmacen(ObjetoDTO objetoDTO,
			AlmacenDTO almacenDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASOCIAR UN OBJETO A UN ALMACEN ****/");
		
		if(objetoDTO==null||almacenDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(objetoDTO.getElementoId()==null||almacenDTO.getIdentificadorAlmacen()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Objeto objeto = objetoDAO.read(objetoDTO.getElementoId());
		Almacen almacen =almacenDAO.read(almacenDTO.getIdentificadorAlmacen());
		objeto.setAlmacen(almacen);
		
		objetoDAO.update(objeto);
		
		return ObjetoTransformer.tranformarComoEvidenciaBasico(objeto);
	}

}
