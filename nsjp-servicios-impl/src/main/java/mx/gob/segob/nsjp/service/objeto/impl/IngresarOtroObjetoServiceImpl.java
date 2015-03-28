/**
 * 
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarOtroObjetoService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

/**
 * Implementacion del servicios para ingresar un objeto de tipo Otro.
 * 
 * @version 1.0
 * @author GustavoBP
 */

@Service
@Transactional
public class IngresarOtroObjetoServiceImpl implements IngresarOtroObjetoService {

	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(IngresarOtroObjetoServiceImpl.class);
	
	@Autowired
	private CalidadDAO calidadDAO;
	@Autowired
	private ObjetoDAO objetoDAO;
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;
    
    
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.objeto.IngresarOtroObjetoService#ingresarOtroObjeto(mx.gob.segob.nsjp.dto.objeto.ObjetoDTO)
	 */
	@Override
	public Long ingresarOtroObjeto(ObjetoDTO objetoDto)
			throws NSJPNegocioException {
		if (objetoDto == null) 
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        
        if (objetoDto.getElementoId() == null || objetoDto.getElementoId() <= 0) {
            return guarda(objetoDto);
        } else {
            return actualiza(objetoDto);
        }
	}

	 private Long guarda(ObjetoDTO objetoDto) throws NSJPNegocioException {

		if (logger.isDebugEnabled()) 
			logger.debug("Inicia almacenamiento del Objeto:"+ objetoDto);
	
		if (objetoDto == null) {
			logger.error("El objeto es requerido para el guardado: "+ objetoDto);
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
	    if (objetoDto.getExpedienteDTO() == null || objetoDto.getExpedienteDTO().getExpedienteId() == null) {
	        logger.error("El objeto requiere de asignarle un Expediente para ser guardado: "+ objetoDto);
	        throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	    }
	    
	    if(objetoDto.getCalidadDTO() == null || objetoDto.getCalidadDTO().getCalidades()==null || objetoDto.getCalidadDTO().getCalidades().getValorId()==null ){
	    	logger.error("El objeto requiere de asignarle una Calidad para ser guardado: "+ objetoDto);
	        throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	    }
	    
	    Objeto objeto = ObjetoTransformer.transformarObjeto(objetoDto);
	    
	    Calidad cal = new Calidad();
	    cal.setTipoCalidad(new Valor(objetoDto.getCalidadDTO().getCalidades().getValorId()));
	
	    if (objetoDto.getCalidadDTO().getDescripcionEstadoFisico() != null) 
	        cal.setDescripcionEstadoFisico(objetoDto.getCalidadDTO().getDescripcionEstadoFisico());
	
	    Long idCalidad = this.calidadDAO.create(cal);
	    logger.debug("Insertando Calidad " + idCalidad);
	
	    objeto.setCalidad(this.calidadDAO.read(idCalidad));
	    objeto.setValorByTipoObjetoVal(new Valor(Objetos.OTRO.getValorId()));
	    
	    objeto.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
	    Long idObjeto = this.objetoDAO.create(objeto);
	    objetoDto.setElementoId(idObjeto);
	    
	    guardaOActualizaObjetos(objetoDto);
	    
	    logger.debug("Se genero el objeto Otro con id: " + idObjeto);
	    return idObjeto;
	}

    private Long actualiza(ObjetoDTO objetoDto) throws NSJPNegocioException {
        Long elementoId = objetoDto.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Objeto objetoBD = objetoDAO.read(elementoId);
        
        Objeto objeto = ObjetoTransformer.transformarObjeto(objetoDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(objeto, objetoBD);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(ObjetoDTO obraDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = obraDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(obraDto);
        }
    }
}
