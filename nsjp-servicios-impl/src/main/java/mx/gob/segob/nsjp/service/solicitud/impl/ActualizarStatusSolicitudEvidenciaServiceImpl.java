package mx.gob.segob.nsjp.service.solicitud.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.solicitud.ActualizarStatusSolicitudEvidenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ActualizarStatusSolicitudEvidenciaServiceImpl implements
		ActualizarStatusSolicitudEvidenciaService {
	
	@Autowired 
	SolicitudDAO solicitudDAO;
	@Autowired
	ValorDAO valorDAO;
	@Autowired
	FormaDAO formaDao;
	
	@Override
	public SolicitudDTO actualizarStatusSolicitudEvidencia(
			SolicitudDTO solicitud) throws NSJPNegocioException {

		Solicitud solPojo =  solicitudDAO.read(solicitud.getDocumentoId());
		
		solPojo.setEstatus(new Valor(solicitud.getEstatus().getIdCampo()));
		if(solicitud.getMotivo()!=null)
			solPojo.setMotivo(solicitud.getMotivo());		
		if(solicitud.getFechaLimite()!=null)
		solPojo.setFechaLimite(solicitud.getFechaLimite());
		
		if(solicitud.getDestinatario()!=null)
			solPojo.setDestinatario(FuncionarioTransformer.transformarFuncionario(solicitud.getDestinatario()));
		
		solicitudDAO.update(solPojo);
		return solicitud;
	}

}
