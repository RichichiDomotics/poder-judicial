/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.quejaciudadana.EstatusQueja;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.quejaciudadana.ImplicadoDAO;
import mx.gob.segob.nsjp.dao.quejaciudadana.QuejaCiudadanaDAO;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.QuejaCiudadana;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.quejaciudadana.RegistrarQuejaCiudadanaService;
import mx.gob.segob.nsjp.service.quejaciudadana.impl.transformer.QuejaCiudadanaTransformer;

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
public class RegistrarQuejaCiudadanaServiceImpl implements
		RegistrarQuejaCiudadanaService {

	public final static Logger logger = 
		Logger.getLogger(RegistrarQuejaCiudadanaServiceImpl.class);
	
	@Autowired
	private QuejaCiudadanaDAO quejaDAO;
	@Autowired
	private ImplicadoDAO implicadoDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.quejaciudadana.RegistrarQuejaCiudadanaService#registrarQuejaCiudadana(mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO)
	 */
	@Override
	public Long registrarQuejaCiudadana(QuejaCiudadanaDTO quejaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR QUEJA CIUDADANA INICIAL ****/");
		
		if(quejaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(quejaDTO.getDescripcionQueja()==null||quejaDTO.getTipoQuejaDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		QuejaCiudadana queja=QuejaCiudadanaTransformer.transformarQuejaDTO(quejaDTO);
		
		/*Obligatorios de la queja y automáticos*/
		List<Long> quejasID = quejaDAO.findAllId();
		DecimalFormat df=new DecimalFormat("00000");
		Calendar ahora=Calendar.getInstance();
		ahora.setTime(new Date());
		
		if (queja.getQuejoso()!=null) {
			implicadoDAO.create(queja.getQuejoso());
		}
		if (queja.getDenunciado()!=null) {
			implicadoDAO.create(queja.getDenunciado());
		}
		if (queja.getAfectado()!=null) {
			implicadoDAO.create(queja.getAfectado());
		}
		
		queja.setFolioQueja("QC-"+ahora.get(Calendar.YEAR)+df.format(quejasID.size()+1));
		queja.setFechaRegistro(ahora.getTime());
		queja.setFechaCreacion(ahora.getTime());
		queja.setForma(new Forma(Formas.RESPUESTA_CIUDADANA.getValorId()));
		queja.setNombreDocumento("Queja Ciudadana");
		queja.setTipoDocumento(new Valor(TipoDocumento.ACUSE.getValorId()));
		if(queja.getEstatusQueja()==null)
			queja.setEstatusQueja(new Valor(EstatusQueja.EN_PROCESO.getValorId()));
		queja.setDocumentoId(null);
		
		return quejaDAO.create(queja);
	}

}
