/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.CartaCumplimientoDAO;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.model.CartaCumplimiento;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.documento.ActualizarControversiaService;

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
public class ActualizarControversiaServiceImpl implements
		ActualizarControversiaService {

	public final static Logger logger = Logger
			.getLogger(ActualizarControversiaServiceImpl.class);
	@Autowired
	private CartaCumplimientoDAO cumplimientoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.documento.ActualizarControversiaService#
	 * actualizarControversia
	 * (mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO)
	 */
	@Override
	public void actualizarControversia(CartaCumplimientoDTO cumplimientoDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA MARCAR LA LECTURA DE UNA CONTROVERSIA ****/");

		if (cumplimientoDTO.getDocumentoId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		

//		CartaCumplimiento cumplimientoLeido = new CartaCumplimiento();
		CartaCumplimiento cumplimientoLeido = cumplimientoDAO.read(cumplimientoDTO.getDocumentoId());
		cumplimientoLeido.setDocumentoId(cumplimientoDTO.getDocumentoId());
		cumplimientoLeido.setEsLeido(true);
		cumplimientoLeido.setFechaLectura(new Date());
		if (cumplimientoDTO.getFuncionario() != null)
			if (cumplimientoDTO.getFuncionario().getClaveFuncionario() != null)
				cumplimientoLeido
						.setFuncionario(new Funcionario(cumplimientoDTO
								.getFuncionario().getClaveFuncionario()));
		
		
		cumplimientoDAO.update(cumplimientoLeido);

	}

}
