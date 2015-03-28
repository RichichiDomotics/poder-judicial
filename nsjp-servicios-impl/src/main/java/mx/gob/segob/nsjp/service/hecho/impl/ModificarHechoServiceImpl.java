/**
* Nombre del Programa : ModificarHechoServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/07/2011
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
package mx.gob.segob.nsjp.service.hecho.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.domicilio.ModificarDomicilioServicio;
import mx.gob.segob.nsjp.service.hecho.IngresarTiempoService;
import mx.gob.segob.nsjp.service.hecho.ModificarHechoService;
import mx.gob.segob.nsjp.service.hecho.ModificarTiempoService;
import mx.gob.segob.nsjp.service.hecho.impl.transform.HechoTransformer;

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
public class ModificarHechoServiceImpl implements ModificarHechoService {

	public final static Logger logger = 
		Logger.getLogger(ModificarHechoServiceImpl.class);
	
	@Autowired
	private HechoDAO hechoDao;
	@Autowired
	private ModificarDomicilioServicio modificarDomicilioServicio;
	@Autowired
	private IngresarDomicilioService ingresarDomicilioService;
	@Autowired
	private IngresarTiempoService ingresarTiempoService;
	@Autowired
	private ModificarTiempoService modificarTiempoService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.hecho.ModificarHechoService#modificarHecho(mx.gob.segob.nsjp.dto.hecho.HechoDTO)
	 */
	@Override
	public HechoDTO modificarHecho(HechoDTO hechoDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR PLANTILLAS POR TIPO DE DOCUMENTO ****/");
		
		/*Verificación de parámetros*/
		if(hechoDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(hechoDTO.getHechoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		logger.info(" hechoDTO - Tiempo" + hechoDTO.getTiempo());
		logger.info(" hechoDTO - Lugar" + hechoDTO.getLugar());
		logger.info(" hechoDTO - Expediente" + hechoDTO.getExpediente());
		
		/*Operación*/
		logger.info("Se modificará el hecho "+hechoDTO.getHechoId());
		Hecho hecho = HechoTransformer.transformarHecho(hechoDTO);
		
		//Verificar si se tiene el ID de Lugar
		if(hechoDTO.getLugar()!= null){
			//Se considera que el Lugar siempre será domicilio
			if (hechoDTO.getLugar() instanceof DomicilioDTO) {
				if(hechoDTO.getLugar().getElementoId()== null){
					Long nuevoLugar = ingresarDomicilioService.ingresarDomicilio((DomicilioDTO)hechoDTO.getLugar());
					hechoDTO.getLugar().setElementoId(nuevoLugar);
					hecho.setLugar(new Lugar(nuevoLugar));
				}
				else
					modificarDomicilioServicio.modificarDomicilio((DomicilioDTO)hechoDTO.getLugar());
			}
		}
		if(hechoDTO.getTiempo()!= null){
			TiempoDTO tiempoDTO = null;
			if(hechoDTO.getTiempo().getTiempoId()== null )
				tiempoDTO = ingresarTiempoService.ingresarTiempo(hechoDTO.getTiempo());
			else
				tiempoDTO = modificarTiempoService.modificarTiempo(hechoDTO.getTiempo());
			hecho.setTiempo(new Tiempo(tiempoDTO.getTiempoId()));
			hechoDTO.setTiempo(tiempoDTO);
		}
		hechoDao.update(hecho);
		return hechoDTO;
	}

}
