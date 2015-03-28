/**
* Nombre del Programa : CambiarExpedienteDeAlmacenServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio pra realizar el cambio de almacen a un expediente
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
package mx.gob.segob.nsjp.service.almacen.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.almacen.AlmacenDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.almacen.CambiarExpedienteDeAlmacenService;

/**
 * Implementacion del servicio pra realizar el cambio de almacen a un expediente.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class CambiarExpedienteDeAlmacenServiceImpl implements
		CambiarExpedienteDeAlmacenService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(CambiarExpedienteDeAlmacenServiceImpl.class);
	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private AlmacenDAO almacenDAO;
	
	@Override
	public void cambiaExpedienteDeAlmacen(AlmacenDTO almacenActual,
			AlmacenDTO almacenNuevo, ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REALIZAR EL CAMBIO DE ALMACEN DE UN EXPEDIENTE ****/");
	
		if (almacenActual.getIdentificadorAlmacen()==null ||almacenNuevo.getIdentificadorAlmacen()==null
				|| expedienteDTO.getNumeroExpedienteId()==null) 
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		//Se obtiene el numeroExpediente a mover
		NumeroExpediente numExpediente = numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());
		
		Almacen almActualBD = almacenDAO.read(almacenActual.getIdentificadorAlmacen());
		almActualBD.setNumeroExpediente(null);
		almacenDAO.update(almActualBD);
		
		Almacen almNuevoBD = almacenDAO.read(almacenNuevo.getIdentificadorAlmacen());
		almNuevoBD.setNumeroExpediente(numExpediente);
		almacenDAO.update(almNuevoBD);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** EL CAMBIO DE ALMACEN DEL EXPEDIENTE FINALIZO ****/");
	}

}
