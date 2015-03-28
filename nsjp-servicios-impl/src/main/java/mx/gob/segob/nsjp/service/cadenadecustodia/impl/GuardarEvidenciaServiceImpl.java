/**
 * Nombre del Programa : GuardarEvidenciaServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19/07/2011
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
package mx.gob.segob.nsjp.service.cadenadecustodia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.evidencia.GuardarEvidenciaService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.forma.impl.ConsultarFormaPlantillaServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class GuardarEvidenciaServiceImpl implements GuardarEvidenciaService {

	public final static Logger logger = Logger
			.getLogger(ConsultarFormaPlantillaServiceImpl.class);

	@Autowired
	private EvidenciaDAO evidenciaDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.evidencia.GuardarEvidenciaService#guardarEvidencia
	 * (mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO)
	 */
	@Override
	public Long guardarEvidencia(EvidenciaDTO evidenciaDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR EVIDENCIA A UNA CADENA DE CUSTODIA ****/");

		/* Verificación de parámetros */
		if (evidenciaDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (evidenciaDTO.getCadenaDeCustodia() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/* Operación */
		Evidencia evidencia = EvidenciaTransformer.transformarEvidencia(evidenciaDTO);
		Long idEvidencia = evidencia.getEvidenciaId();
		if (evidencia.getEvidenciaId() == null){
			List<Evidencia> evidencias = evidenciaDAO.consultarEvidenciasXCadenaCustodia(evidencia.getCadenaDeCustodia());
			evidencia.setNumeroEvidencia(evidencias.size()+1L);
			idEvidencia = evidenciaDAO.create(evidencia);
		}else{
			evidenciaDAO.update(evidencia);
		}

		return idEvidencia;
	}

}
