/**

 * Nombre del Programa : IngresarAnimalServiceImpl.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios para ingresar animal
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
package mx.gob.segob.nsjp.service.objeto.impl;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.AnimalDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.model.Animal;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarAnimalService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.AnimalTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicios para ingresar animal
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarAnimalServiceImpl implements IngresarAnimalService {

    private final static Logger logger = Logger.getLogger(IngresarAnimalServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private AnimalDAO animalDAO;

    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarAnimal(AnimalDTO animalDto) throws NSJPNegocioException {
        if (animalDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = animalDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guarda(animalDto);
        }else{
            return actualiza(animalDto);
        }
    }

    private Long guarda(AnimalDTO animalDto) throws NSJPNegocioException {
        Long idAnimal = 0L;
        Animal anl = new Animal();
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del Animal");
        }
        if (animalDto == null) {
            logger.error("El animal es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        if (animalDto.getExpedienteDTO() == null ||
                animalDto.getExpedienteDTO().getExpedienteId() == null) {
            logger.error("El animal es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("Objeto Animal    !!!!!!!");
        anl = AnimalTransformer.transformarAnimal(animalDto);
        CalidadDTO calidadDTO = animalDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        anl.setCalidad(cal);
        anl.setValorByTipoObjetoVal(new Valor(Objetos.ANIMAL.getValorId()));
        anl.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idAnimal = this.animalDAO.create(anl);
        animalDto.setElementoId(idAnimal);
        guardaOActualizaObjetos(animalDto);
        logger.debug("Se genero el animal con id: " + idAnimal);
        return idAnimal;
    }

    private Long actualiza(AnimalDTO animalDto) throws NSJPNegocioException {
        Long elementoId = animalDto.getElementoId();
        if(elementoId < 0){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Animal actualizable = animalDAO.read(elementoId);
        Animal actualizador = AnimalTransformer.transformarAnimal(animalDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(AnimalDTO animalDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = animalDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(animalDto);
        }
    }
}
