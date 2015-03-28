/**
 * Nombre del Programa : IngresarVehiculoServiceImpl.java
 * Autor                            : Tattva-IT
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implentacion de metodos del servicio de Vehiculo
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.VehiculoDAO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vehiculo;
import mx.gob.segob.nsjp.service.objeto.IngresarVehiculoService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.VehiculoTransformer;

/**
 * Implentacion de metodos del servicio de Vehiculo
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarVehiculoServiceImpl implements IngresarVehiculoService {

    private final static Logger logger = Logger.getLogger(IngresarVehiculoServiceImpl.class);
    @Autowired
    private VehiculoDAO vehiculoDAO;
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;

    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarVehiculo(VehiculoDTO vehiculoDto)
            throws NSJPNegocioException {

        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento de vehiculo");
        }
        if(vehiculoDto == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long idVehiculo = null;
        if (vehiculoDto.getElementoId() == null || vehiculoDto.getElementoId() == 0) {
            idVehiculo = guardarVehiculo(vehiculoDto);
        } else {
            idVehiculo = actualizaVehiculo(vehiculoDto);
        }
        return idVehiculo;
    }

    /**
     * Cambios requeridos para que este servicio guarda o actualice.
     * Se llama este metodo cuando se va a crear un nuevo registro en la base.
     * @param vehiculoDto
     * @param idVehiculo
     * @return
     */
    private Long guardarVehiculo(VehiculoDTO vehiculoDto) throws NSJPNegocioException {

        if (vehiculoDto.getExpedienteDTO() == null ||
                vehiculoDto.getExpedienteDTO().getExpedienteId() == null) {
            logger.error("El vehiculo es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        Vehiculo veh = new Vehiculo();
        veh = VehiculoTransformer.transformarVehiculo(vehiculoDto);
        logger.debug("Verificando Calidad");
        if (vehiculoDto.getCalidadDTO() != null && vehiculoDto.getCalidadDTO().getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(vehiculoDto.getCalidadDTO().getDescripcionEstadoFisico());
        }
        if (vehiculoDto.getCalidadDTO() != null && vehiculoDto.getCalidadDTO().getCalidades() != null) {
            cal.setTipoCalidad(new Valor(vehiculoDto.getCalidadDTO().getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        veh.setCalidad(cal);
        veh.setValorByTipoObjetoVal(new Valor(Objetos.VEHICULO.getValorId()));
        veh.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        Long idVehiculo = vehiculoDAO.create(veh);
        vehiculoDto.setElementoId(idVehiculo);
        guardaOActualizaObjetos(vehiculoDto);
        logger.debug("Se genero el vehiculo con id: " + idVehiculo);
        return idVehiculo;
    }


    private Long actualizaVehiculo(VehiculoDTO vehiculoDto)
            throws NSJPNegocioException {
    	
        if(vehiculoDto == null || vehiculoDto.getElementoId() == null ||
                vehiculoDto.getElementoId() <= 0){
            logger.debug("Se requier un vehiculo y vehiculoDto.elementoid > 0 : " +
                    vehiculoDto);
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Vehiculo vehiculo = vehiculoDAO.read(vehiculoDto.getElementoId());
        Vehiculo actualizacion =
                VehiculoTransformer.transformarVehiculo(vehiculoDto);
        actualizaObjeto(actualizacion, vehiculo);
        guardaOActualizaObjetos(vehiculoDto);
        vehiculoDAO.merge(vehiculo);
        return vehiculo.getElementoId();
    }

    private void guardaOActualizaObjetos(VehiculoDTO vehiculoDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = vehiculoDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(vehiculoDto);
        }
    }

    /**
     * Inspeccciona el objeto {@code actualizacion} y busca aquellos atributos
     * distintos de null de aquellos metodos que empieza con get y con estos
     * campos actualiza los valores de {@code actualizable} invocando al metodo
     * set correspondiente.
     * @param actualizador El objeto del que se toman los valores distintos de
     * null.
     * @param actualizable El objeto al cual se le actualizan los valores.
     * @throws IllegalArgumenException en caso que los objetos que recibe no
     * sean del mismo tipo.
     */
    public static void actualizaObjeto(Object actualizador, Object actualizable){
        if (actualizador == null || actualizable == null) {
            return;
        }
        if (!actualizador.getClass().equals(actualizable.getClass())) {
            if (logger.isDebugEnabled()) {
                logger.debug("actualizador.getClass() = " + actualizador.getClass());
                logger.debug("actualizable.getClass() = " + actualizable.getClass());
            }
            throw new IllegalArgumentException("Los objetos que se pueden"
                    + " actualizar con este metodo deben ser de la misma clase");
        }
        // Buscamos los campos del objeto actualizacion.
        Method[] metodos = actualizador.getClass().getMethods();
        for (Method metodo : metodos) {
            metodo.setAccessible(true);
            try {
                if (metodo.getName().startsWith("get")) {
                    // Obtenemos el valor del campo
                    Object objetoEnActualizacion = metodo.invoke(actualizador);
                    // Si el valor del campo es distinto de null
                    if (objetoEnActualizacion != null) {
                        String nombreCampo = metodo.getName().substring(3);
                        Method setter = actualizable.getClass().
                                getMethod("set" + nombreCampo,
                                objetoEnActualizacion.getClass());
                        setter.invoke(actualizable, objetoEnActualizacion);
                    }
                }
            } catch (InvocationTargetException ex) {
                logger.debug(ex);
            } catch (NoSuchMethodException ex) {
                logger.debug(ex);
            } catch (SecurityException ex) {
                logger.debug(ex);
            } catch (IllegalArgumentException ex) {
                logger.debug(ex);
            } catch (IllegalAccessException ex) {
                logger.debug(ex);
            }
        }
    }
}
