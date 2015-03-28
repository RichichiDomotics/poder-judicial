/**
* Nombre del Programa : GuardarArchivoDigitalServiceImpl.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/05/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del servicio de negocio para realizar el guardado de un archivo digital
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
package mx.gob.segob.nsjp.service.archivo.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.service.archivo.GuardarArchivoDigitalService;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para realizar el guardado de un archivo digital
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class GuardarArchivoDigitalServiceImpl implements GuardarArchivoDigitalService {

	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO;
	
	private final static Logger logger = Logger.getLogger(GuardarArchivoDigitalServiceImpl.class);
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.archivo.GuardarArchivoDigitalService#guardarArchivoDigital(mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO)
	 */
	@Override
	public Long guardarArchivoDigital(ArchivoDigitalDTO archivo) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/SERVICIO PARA GUARDAR UN ARCHIVO DIGITAL/");
		
		if(archivo==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		return archivoDigitalDAO.create(ArchivoDigitalTransformer.transformarArchivoDigitalDTO(archivo));
		
	}

}
