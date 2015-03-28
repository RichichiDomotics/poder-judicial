/**
* Nombre del Programa : ConsultarFormaPlantillaServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/06/2011
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
package mx.gob.segob.nsjp.service.forma.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.forma.CamposFormaDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.model.CamposForma;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.service.forma.ConsultarFormaPlantillaService;
import mx.gob.segob.nsjp.service.forma.impl.transform.CamposFormaTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarFormaPlantillaServiceImpl implements
		ConsultarFormaPlantillaService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarFormaPlantillaServiceImpl.class);
	@Autowired
	private CamposFormaDAO camposFormaDAO;
	@Autowired
	private FormaDAO formaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.forma.ConsultarFormaPlantillaService#consultarFormaPlantilla(java.lang.String)
	 */
	@Override
	public List<FormaDTO> consultarFormaPlantilla(String tipoDocumento)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR PLANTILLAS POR TIPO DE DOCUMENTO ****/");
		
		/*Verificación de parámetros*/
		if (tipoDocumento==null || tipoDocumento.equals(""))
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Forma> plantillas = formaDAO.consultarFormaPlantilla(tipoDocumento);
		
		/*Transformación*/
		List<FormaDTO> plantillasDTO = new ArrayList<FormaDTO>();
		for (Forma forma : plantillas) {
			plantillasDTO.add(FormaTransformer.transformarForma(forma));
			
		}
		
		return plantillasDTO;
	}

	@Override
	public List<FormaDTO> consultarPlantillaPorTipo(Long tipoForma)
			throws NSJPNegocioException {
		List<Forma> plantillas = formaDAO.consultarPlantillaPorTipo(tipoForma);
		List<FormaDTO> plantillasDTO = new ArrayList<FormaDTO>();
		for (Forma forma : plantillas) {
			plantillasDTO.add(FormaTransformer.transformarForma(forma));
			
		}
		return plantillasDTO;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.forma.ConsultarFormaPlantillaService#consultarCamposForma()
	 */
	@Override
	public List<CamposFormaDTO> consultarCamposForma()
			throws NSJPNegocioException {
		List<CamposFormaDTO> resultado = new ArrayList<CamposFormaDTO>();
		for(CamposForma campo:camposFormaDAO.obtenerCamposForma()){
			resultado.add(CamposFormaTransformer.transformarCamposForma(campo));
		}
		return resultado;
		
	}

}
