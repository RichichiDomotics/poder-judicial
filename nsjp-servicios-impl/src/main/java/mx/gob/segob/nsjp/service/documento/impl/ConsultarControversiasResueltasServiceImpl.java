/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.CartaCumplimientoDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.model.CartaCumplimiento;
import mx.gob.segob.nsjp.service.documento.ConsultarControversiasResueltasService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;

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
public class ConsultarControversiasResueltasServiceImpl implements
		ConsultarControversiasResueltasService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarControversiasResueltasServiceImpl.class);
	@Autowired
	private ActividadDAO actividadDAO;
	@Autowired
	private CartaCumplimientoDAO cumplimientoDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.ConsultarControversiasResueltasService#consultarControversiasResueltas(java.lang.Long)
	 */
	@Override
	public List<CartaCumplimientoDTO> consultarControversiasResueltas(
			Long idTipoDocumento) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR CONTROVERSIAS ENVIADAS DESDE JUSTICIA RESTAURATIVA A PODER JUDICIAL ****/");
		
		/*Verificación de parámetros*/
		if (idTipoDocumento==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<CartaCumplimiento> cartas=cumplimientoDAO.consultarControversiasResueltas();
		List<CartaCumplimientoDTO> documentosDTO=new ArrayList<CartaCumplimientoDTO>();
		for (CartaCumplimiento doc : cartas) {
			doc.setActividad(actividadDAO.consultarActividadXExpedienteYDocumento(null, doc.getDocumentoId()));
			documentosDTO.add(DocumentoTransformer.transformarControversia(doc));
		}
		return documentosDTO;
	}

}
