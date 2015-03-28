/**
 * Nombre del Programa : RegistrarActividadServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21/07/2011
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
package mx.gob.segob.nsjp.service.actividad.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.actividad.RegistrarActividadService;
import mx.gob.segob.nsjp.service.expediente.impl.AsociarDocumentoCausaTOCAServiceImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class RegistrarActividadServiceImpl implements RegistrarActividadService {
	private final static Logger logger = Logger
			.getLogger(AsociarDocumentoCausaTOCAServiceImpl.class);

	@Autowired
	private ActividadDAO actividadDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.actividad.RegistrarActividadService#
	 * registrarActividad(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO,
	 * java.lang.Long)
	 */
	@Override
	public Long registrarActividad(ExpedienteDTO expedienteDTO,
			FuncionarioDTO funcionarioDTO, Long tipoActividad)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASOCIAR UN DOCUMENTO AL EXPEDIENTE ****/");

		/* Verificación de parámetros */
		if(expedienteDTO==null || funcionarioDTO==null||tipoActividad==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(expedienteDTO.getExpedienteId()==null||funcionarioDTO.getClaveFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		Actividad act=new Actividad();
		act.setExpediente(new Expediente(expedienteDTO.getExpedienteId()));
		act.setFuncionario(new Funcionario(funcionarioDTO.getClaveFuncionario()));
		act.setTipoActividad(new Valor(tipoActividad));
		act.setFechaCreacion(new Date());
		
		Long idActividad=actividadDAO.create(act);
		
		return idActividad;
	}

}
