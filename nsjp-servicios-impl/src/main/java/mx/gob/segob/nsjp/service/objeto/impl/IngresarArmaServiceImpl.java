/**
* Nombre del Programa : IngresarArmaServiceImpl.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 6 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementacion del servicios para ingresar arma                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.service.objeto.impl;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.ArmaDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.model.Arma;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarArmaService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ArmaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicios para ingresar arma
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
@Transactional
public class IngresarArmaServiceImpl implements IngresarArmaService {

	private final static Logger logger = Logger.getLogger(IngresarArmaServiceImpl.class);
	
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    
    @Autowired
    private ArmaDAO armaDAO;

    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;
    /**
     * {@inheritDoc}
     */
    @Override
	public Long ingresarArma(ArmaDTO armaDto) throws NSJPNegocioException {
            if(armaDto == null){
                throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
            }
            if(armaDto.getElementoId() == null || armaDto.getElementoId() == 0){
                return guardar(armaDto);
            }else{
                return actualiza(armaDto);
            }
	}

    private Long guardar(ArmaDTO armaDto) throws NSJPNegocioException {
        Arma arma = new Arma();
        Long idArma = 0L;
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del Arma");
        }
        if (armaDto == null) {
            logger.error("El arma es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = armaDto.getExpedienteDTO();
        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null) {
            logger.error("El arma es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("ARMA    !!!!!!!");
        arma = ArmaTransformer.transformarArma(armaDto);
        CalidadDTO calidadDTO = armaDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        arma.setCalidad(cal);
        arma.setValorByTipoObjetoVal(new Valor(Objetos.ARMA.getValorId()));
        arma.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idArma = this.armaDAO.create(arma);
        armaDto.setElementoId(idArma);
        guardaOActualizaObjetos(armaDto);
        logger.debug("Se genero el arma con id: " + idArma);
        return idArma;
    }

    private Long actualiza(ArmaDTO armaDto) throws NSJPNegocioException {
        if(armaDto.getElementoId() < 0){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Arma actualizable = armaDAO.read(armaDto.getElementoId());
        Arma actualizador = ArmaTransformer.transformarArma(armaDto);
        guardaOActualizaObjetos(armaDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return armaDto.getElementoId();

    }

    private void guardaOActualizaObjetos(ArmaDTO armaDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = armaDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(armaDto);
        }
    }

}
