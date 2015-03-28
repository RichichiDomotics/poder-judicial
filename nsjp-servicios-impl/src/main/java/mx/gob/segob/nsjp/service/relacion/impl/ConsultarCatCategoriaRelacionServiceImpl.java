/**
* Nombre del Programa : ConsultarCatCategoriaRelacionServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.service.relacion.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.relacion.CatCategoriaRelacionDAO;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;
import mx.gob.segob.nsjp.service.forma.impl.ConsultarFormaPlantillaServiceImpl;
import mx.gob.segob.nsjp.service.relacion.ConsultarCatCategoriaRelacionService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarCatCategoriaRelacionServiceImpl implements
		ConsultarCatCategoriaRelacionService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarFormaPlantillaServiceImpl.class);
	
	@Autowired
	private CatCategoriaRelacionDAO catCatRelDao;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.relacion.ConsultarCatCategoriaRelacionService#consultarCatCategoriaRelacionSiDocumento(java.lang.Boolean)
	 */
	@Override
	public List<CatCategoriaRelacionDTO> consultarCatCategoriaRelacionSiDocumento(
			Boolean esDocumento) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR CATEGORIAS DE RELACIONES QUE SEAN O NO DOCUMENTOS ****/");
		
		/*Verificación de parámetros*/
		if(esDocumento==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		List<CatCategoriaRelacion> catRelaciones=catCatRelDao.consultarCatCategoriaRelacionSiDocumento(esDocumento);
		List<CatCategoriaRelacionDTO> ccRDTOs = new ArrayList<CatCategoriaRelacionDTO>();
		for (CatCategoriaRelacion ccRel : catRelaciones) {
			ccRDTOs.add(new CatCategoriaRelacionDTO(ccRel.getCatCategoriaRelacionId(), ccRel.getDesCategoriaRelacion(), ccRel.getEsDocumento()));
		}
		
		return ccRDTOs;
	}

}
