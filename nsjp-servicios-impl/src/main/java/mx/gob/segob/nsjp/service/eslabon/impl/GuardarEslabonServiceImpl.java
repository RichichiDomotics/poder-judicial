/**
 * 
 */
package mx.gob.segob.nsjp.service.eslabon.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.eslabon.EslabonDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.eslabon.GuardarEslabonService;

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
public class GuardarEslabonServiceImpl implements GuardarEslabonService {

	public final static Logger logger = Logger
			.getLogger(GuardarEslabonServiceImpl.class);

	@Autowired
	private EslabonDAO eslabonDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.eslabon.GuardarEslabonService#guardarEslabon
	 * (mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO,
	 * mx.gob.segob.nsjp.dto.evidencia.EslabonDTO)
	 */
	@Override
	public Long guardarEslabon(EvidenciaDTO evidenciaDTO, EslabonDTO eslabonDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR/ACTUALIZAR UN ESLABON PARA UNA EVIDENCIA****/");
		/* Revisión de parámetros */
		if (evidenciaDTO == null || eslabonDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else
			revisarParametrosGuardarEslabon(evidenciaDTO, eslabonDTO);

		/* Operación */
		Eslabon eslabon = EslabonTransformer.transformarEslabon(eslabonDTO);
		eslabon.setEvidencia(new Evidencia(evidenciaDTO.getEvidenciaId()));
		Long idEslabon = eslabon.getEslabonId();
		if (eslabon.getEslabonId() == null) {
			Eslabon ultimoEslabon = eslabonDAO
					.consultaUltimoEslabonXEvidencia(new Evidencia(evidenciaDTO
							.getEvidenciaId()));
			if (ultimoEslabon != null)
				eslabon.setNumeroEslabon(ultimoEslabon.getNumeroEslabon() + 1);
			else
				eslabon.setNumeroEslabon(1);
			idEslabon = eslabonDAO.create(eslabon);
		} else {
			eslabonDAO.update(eslabon);
		}
		
		return idEslabon;
	}
	
	@Override
	public void guardarEslabonCarpetaInvestigacion(EvidenciaDTO evidenciaDTO, EslabonDTO eslabonDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR/ACTUALIZAR UN ESLABON PARA UNA EVIDENCIA****/");
		/* Revisión de parámetros */
		if (evidenciaDTO == null || eslabonDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else
			revisarParametrosGuardarEslabon(evidenciaDTO, eslabonDTO);

		/* Operación */
		Eslabon eslabon = EslabonTransformer.transformarEslabon(eslabonDTO);
		eslabon.setEvidencia(new Evidencia(evidenciaDTO.getEvidenciaId()));
		eslabonDAO.create(eslabon);
	}

	private void revisarParametrosGuardarEslabon(EvidenciaDTO evidenciaDTO,
			EslabonDTO eslabonDTO) throws NSJPNegocioException {
		/*Obliga de evidencia*/
		if (evidenciaDTO.getEvidenciaId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Obliga de eslabon*/
		if (eslabonDTO.getFechaInicioMovimiento() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if (eslabonDTO.getFechaFinMovimiento() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
//		if (eslabonDTO.getFuncionariEntrega() == null)
//			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
//		if (eslabonDTO.getFuncionariRecibe() == null)
//			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if (eslabonDTO.getTipoEslabon() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	}

	@Override
	public Long asociarDocumentoAEslabon(EslabonDTO eslabonDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR/ACTUALIZAR UN ESLABON PARA UNA EVIDENCIA****/");
		/* Revisión de parámetros */
		if (eslabonDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (eslabonDTO.getEslabonId() == null || eslabonDTO.getDocumentoDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (eslabonDTO.getDocumentoDTO().getDocumentoId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Eslabon eslabon = eslabonDAO.read(eslabonDTO.getEslabonId());
		Documento docu=new Documento();
		docu.setDocumentoId(eslabonDTO.getDocumentoDTO().getDocumentoId());
		eslabon.setDocumento(docu);
		eslabonDAO.update(eslabon);
		
		return eslabon.getEslabonId();
	}

}
