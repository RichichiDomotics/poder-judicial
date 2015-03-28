/**
 * 
 */
package mx.gob.segob.nsjp.service.cadenadecustodia.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaCustodiaXAlmacenService;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarCadenaCustodiaXAlmacenServiceImpl implements
		ConsultarCadenaCustodiaXAlmacenService {
	
	private final static Logger logger = Logger
    .getLogger(ConsultarCadenaCustodiaXAlmacenServiceImpl.class);
	
	@Autowired
	private CadenaDeCustodiaDAO cadenaDeCustodiaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaCustodiaXAlmacenService#consultarCadenaCustodiaXAlmacen(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO)
	 */
	@Override
	public List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXAlmacen(
			AlmacenDTO almacenDTO, CasoDTO casoDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled()) 
            logger.debug("/**   SERVICIO PARA CONSULTAR LAS CADENAS DE CUSTODIA POR ALMACEN  **/");
		
		if(almacenDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(almacenDTO.getIdentificadorAlmacen()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Long idCaso=null;
		if(casoDTO!=null)
			idCaso=casoDTO.getCasoId();
		
		List<CadenaDeCustodia> cadenas = cadenaDeCustodiaDAO.consultarCadenaCustodiaXAlmacen(almacenDTO.getIdentificadorAlmacen(),idCaso);
        List<CadenaDeCustodiaDTO> cadenasDTO=new ArrayList<CadenaDeCustodiaDTO>();
        for (CadenaDeCustodia cad : cadenas) {
        	cadenasDTO.add(CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(cad));
		}
        return cadenasDTO;
	}

}
