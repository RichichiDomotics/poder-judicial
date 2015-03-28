/**
 * Nombre del Programa : ConsultarDetalleFormaServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 22/06/2011
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
package mx.gob.segob.nsjp.service.forma.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.service.forma.ConsultarDetalleFormaService;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarDetalleFormaServiceImpl implements
		ConsultarDetalleFormaService {

	public final static Logger logger = Logger
			.getLogger(ConsultarFormaPlantillaServiceImpl.class);

	@Autowired
	private FormaDAO formaDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.forma.ConsultarDetalleFormaService#
	 * consultarDetalleForma(long)
	 */
	@Override
	public FormaDTO consultarDetalleForma(Long idForma)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR DETALLE DE UNA PLANTILLAS ****/");

		/* Verificación de parámetros */
		if (idForma < 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		Forma lForma = formaDAO.read(idForma);

		/* Transformación */
		return FormaTransformer.transformarForma(lForma);
	}

}
