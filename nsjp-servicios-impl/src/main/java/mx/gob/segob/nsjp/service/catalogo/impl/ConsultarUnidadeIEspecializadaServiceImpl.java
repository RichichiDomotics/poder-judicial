/**
* Nombre del Programa : ConsultarUnidadeIEspecializadaServiceImpl.java
* Autor                            : AdrianECH
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/03/2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.catalogo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatUIEspecializadaDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.model.CatUIEspecializada;
import mx.gob.segob.nsjp.service.catalogo.ConsultarUnidadeIEspecializadaService;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatUIEspecializadaTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * Fecha: 06/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
@Service
@Transactional
public class ConsultarUnidadeIEspecializadaServiceImpl implements
		ConsultarUnidadeIEspecializadaService {
	
	public static final Logger logger = Logger.getLogger(ConsultarUnidadeIEspecializadaServiceImpl.class);
	
	@Autowired
	private CatUIEspecializadaDAO especializadaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.ConsultarUnidadeIEspecializadaService#consultarUnidadesIEspecializadas()
	 */
	@Override
	public List<CatUIEspecializadaDTO> consultarUnidadesIEspecializadas()
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR TODOS LAS UNIDADES INV ESPECIALIZADAS****/");
		
		List<CatUIEspecializada> catUIEspecializadas=especializadaDAO.findAll("catUIEId", true);
		List<CatUIEspecializadaDTO> uiesDto= new ArrayList<CatUIEspecializadaDTO>();
		for (CatUIEspecializada uie : catUIEspecializadas) {
			uiesDto.add(CatUIEspecializadaTransformer.transformarCatUIEspecializada(uie));
		}
				
		return uiesDto;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.ConsultarUnidadeIEspecializadaService#consultarUnidadIEspecializada(java.lang.Long)
	 */
	@Override
	public CatUIEspecializadaDTO consultarUnidadIEspecializada(
			Long especializadaID) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UNA UNIDAD INV ESPECIALIZADA****/");
		
		if(especializadaID==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		CatUIEspecializada uiEspecializada=especializadaDAO.read(especializadaID);
		return CatUIEspecializadaTransformer.transformarCatUIEspecializada(uiEspecializada);
	}

}
