/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dao.documento.RelacionDocumentoElementoDAO;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasXEstatusService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

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
public class ConsultarEvidenciasXEstatusServiceImpl implements
		ConsultarEvidenciasXEstatusService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarEvidenciasXEstatusServiceImpl.class);
	
	@Autowired
	private EvidenciaDAO evidenciaDAO;
	
	@Autowired
	SolicitudDAO solicitudDAO;
	
	@Autowired 
	CadenaDeCustodiaDAO cadenaDAO;
	
	@Autowired
	RelacionDocumentoElementoDAO relacionDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasXEstatusService#consultarEvidenciasXCadenaCustodiaYEstatus(mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO, java.lang.Long)
	 */
	@Override
	public List<EvidenciaDTO> consultarEvidenciasXCadenaCustodiaYEstatus(
			CadenaDeCustodiaDTO custodiaDTO, Long estatus)throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR EVIDENCIAS POR CADENA Y ESTATUS ****/");
		
		/*Verificación de parámetros*/
		if(custodiaDTO==null||estatus==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(custodiaDTO.getCadenaDeCustodiaId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		
		/*Operación*/
		List<Evidencia> evidencias = evidenciaDAO.consultarEvidenciasXCadenaYEstatus(custodiaDTO.getCadenaDeCustodiaId(), estatus);
		List<EvidenciaDTO> evidenciasDTO=new ArrayList<EvidenciaDTO>();
		for (Evidencia row : evidencias) {
			evidenciasDTO.add(EvidenciaTransformer.transformarEvidencia(row, true));
		}
				
		return evidenciasDTO;
	}

    @Override
    public EvidenciaDTO consultaEvidencia(Long idEvidencia)throws NSJPNegocioException {
        Evidencia evidencia = evidenciaDAO.read(idEvidencia);
        EvidenciaDTO evidenciaDto = evidencia == null ? null :
            EvidenciaTransformer.transformarEvidencia(evidencia, true);
        return evidenciaDto;
    }

	@Override
	public List<EvidenciaDTO> consultarEvidenciasXIdSolicitud(
			Long idSolicitud) {
		
		List<EvidenciaDTO> evidenciaDto = new ArrayList<EvidenciaDTO>();
		List<Elemento> evidencias = relacionDAO.consultarElementosPorDocId(idSolicitud);
		
		for(Elemento row:evidencias)
		{
			Evidencia evi = evidenciaDAO.consultarEvidenciaXObjetoId(row.getElementoId());
			EvidenciaDTO ev = new EvidenciaDTO();			
			ev.setEvidenciaId(evi.getEvidenciaId());
			ev.setNumeroEvidencia(evi.getNumeroEvidencia());
			if(evi.getCadenaDeCustodia()!=null)
				ev.setCadenaDeCustodia(CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(evi.getCadenaDeCustodia()));
			if(evi.getObjeto()!=null)
				ev.setObjeto(ObjetoTransformer.transformarObjeto(evi.getObjeto()));
			if(evi.getDescripcion()!=null)
				ev.setDescripcion(evi.getDescripcion());
			if(evi.getFechaLevantamiento()!=null)
				ev.setFechaLevantamiento(evi.getFechaLevantamiento());
			if(evi.getOrigenEvidencia()!=null)
				ev.setOrigenEvidencia(evi.getOrigenEvidencia());
			if(evi.getCodigoBarras()!=null)
				ev.setCodigoBarras(evi.getCodigoBarras());
			if(evi.getFuncionario()!=null)
				ev.setFuncionario(FuncionarioTransformer.transformarFuncionario(evi.getFuncionario()));
			if(evi.getEstatus()!=null)
				ev.setEstatus(new ValorDTO(evi.getEstatus().getValorId()));
			if(evi.getDestinoLegal()!=null)
				ev.setDestinoLegal(new ValorDTO(evi.getDestinoLegal().getValorId()));
			if(evi.getFuncionarioBaja()!=null)
				ev.setFuncionarioBaja(FuncionarioTransformer.transformarFuncionario(evi.getFuncionarioBaja()));
			if(evi.getMotivoBaja()!=null)
				ev.setMotivoBaja(evi.getMotivoBaja());
			evidenciaDto.add(ev);
		}
		
		return evidenciaDto;		
	}
}
