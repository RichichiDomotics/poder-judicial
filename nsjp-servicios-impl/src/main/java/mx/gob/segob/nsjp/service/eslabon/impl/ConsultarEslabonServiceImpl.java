/**
* Nombre del Programa : ConsultarEslabonServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del (de los) servicio(s) para consultar los eslabones asociados a las evidencias.
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
package mx.gob.segob.nsjp.service.eslabon.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.eslabon.EslabonDAO;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.service.eslabon.ConsultarEslabonService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del (de los) servicio(s) para consultar los eslabones 
 * asociados a las evidencias.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ConsultarEslabonServiceImpl implements ConsultarEslabonService {

	 @Autowired
	 private EslabonDAO eslabonDAO;
	 @Autowired
	 private EvidenciaDAO evidenciaDAO;
	 @Autowired
	 private ObjetoDAO objetoDAO;

	 private final static Logger logger = Logger
     .getLogger(ConsultarEslabonServiceImpl.class);
	
	 
	public List<EslabonDTO> consultarEslabonesPorEvidencia(EvidenciaDTO evidenciaDTO) throws NSJPNegocioException{
		List<EslabonDTO> eslabonesDTO = new ArrayList<EslabonDTO>();

		if(evidenciaDTO==null || evidenciaDTO.getEvidenciaId()==null || evidenciaDTO.getEvidenciaId()<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Eslabon> eslabones = eslabonDAO.consultaEslabonesPorEvidencia(evidenciaDTO.getEvidenciaId());
		
		for (Eslabon eslabon : eslabones) {
			logger.info (eslabon.getFechaEntrega());
			eslabonesDTO.add( EslabonTransformer.transformarEslabon(eslabon));
		}
		return eslabonesDTO;
	}
	
	public Boolean tieneEslabonPorEvidenciaYObjeto(Long idObjeto)  throws NSJPNegocioException{
		Boolean tieneEslabon = false;
		
		logger.info("SERVICIO tieneEslabonPorEvidenciaYObjeto");
		if(idObjeto==null || idObjeto < 0L){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Evidencia evidencia = evidenciaDAO.consultarEvidenciaXObjetoId(idObjeto);
		if(evidencia !=null){
			logger.info("EvidenciaID:"+ evidencia.getEvidenciaId());
			List<EslabonDTO>  listaEslabones = this.consultarEslabonesPorEvidencia(new EvidenciaDTO(evidencia.getEvidenciaId()));
			logger.info("Número de Eslabones :"+ listaEslabones.size());
			tieneEslabon = !(listaEslabones.isEmpty());
		}
		
		return tieneEslabon;
	}
	
	public Boolean esEvidenciaNoTieneEslabon(Long idObjeto)  throws NSJPNegocioException{
		Boolean esEvidenciaEsEslabon = false;
		
		logger.info("SERVICIO esEvidenciaNoTieneEslabon");
		if(idObjeto==null || idObjeto < 0L){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Objeto objeto = objetoDAO.read(idObjeto);
		if (objeto!=null && objeto.getEsActivo()){
			Evidencia evidencia = evidenciaDAO.consultarEvidenciaXObjetoId(idObjeto);
			if(evidencia != null){
				logger.info("EvidenciaID:"+ evidencia.getEvidenciaId());
				List<EslabonDTO>  listaEslabones = this.consultarEslabonesPorEvidencia(new EvidenciaDTO(evidencia.getEvidenciaId()));
				logger.info("Número de Eslabones :"+ listaEslabones.size());
				esEvidenciaEsEslabon = listaEslabones.isEmpty();
			}
		}
		return esEvidenciaEsEslabon;
	}
	
}
