/**
* Nombre del Programa : RegistrarUnidadIEspecializadaServiceImpl.java
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatUIEspecializadaDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.model.CatUIEspecializada;
import mx.gob.segob.nsjp.service.catalogo.RegistrarUnidadIEspecializadaService;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatUIEspecializadaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * Fecha: 06/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
@Service
@Transactional
public class RegistrarUnidadIEspecializadaServiceImpl implements
		RegistrarUnidadIEspecializadaService {
	
	public static final Logger logger = Logger.getLogger(RegistrarUnidadIEspecializadaServiceImpl.class);
	
	@Autowired
	private CatUIEspecializadaDAO especializadaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.RegistrarUnidadIEspecializadaService#registrarUnidadIEspecializada(mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO)
	 */
	@Override
	public CatUIEspecializadaDTO registrarUnidadIEspecializada(
			CatUIEspecializadaDTO especializadaDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR / MODIFICAR UNA UNIDAD INV ESPECIALIZADA****/");
		
		if(especializadaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		CatUIEspecializada uiEspecializada=CatUIEspecializadaTransformer.transformarCatUIEspecializadaDTO(especializadaDTO);
		
		if(especializadaDTO.getCatUIEId()==null){
			especializadaDTO.setCatUIEId(especializadaDAO.create(uiEspecializada));
		}else{
			especializadaDAO.update(uiEspecializada);
		}
				
		return especializadaDTO;
	}

}
