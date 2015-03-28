/**
 * Nombre del Programa : ActualizarConvenioServiceImpl.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 09-09-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.convenio.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.medida.FechaCompromisoDAO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.service.convenio.ActualizarFechaCompromisoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author rgama
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ActualizarFechaCompromisoServiceImpl implements ActualizarFechaCompromisoService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ActualizarFechaCompromisoServiceImpl.class);

    @Autowired
    private FechaCompromisoDAO compromisoDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarFechaCompromido(FechaCompromisoDTO loFechaCompromisoDTO) throws NSJPNegocioException {
    	if (logger.isDebugEnabled())
			logger.debug("/SERVICIO PARA ACTUALIZAR FECHA COMPROMISO/");
    	
        if(loFechaCompromisoDTO == null || loFechaCompromisoDTO.getFechaCompromisoId() == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }        
        
        FechaCompromiso loFecha = compromisoDAO.read(loFechaCompromisoDTO.getFechaCompromisoId());
        if(loFechaCompromisoDTO.getDescripcionCompromiso() != null && !loFechaCompromisoDTO.getDescripcionCompromiso().equals(""))        
        	loFecha.setDescripcionCompromiso(loFechaCompromisoDTO.getDescripcionCompromiso());
        if(loFechaCompromisoDTO.getObservaciones() != null)        
        	loFecha.setObservaciones(loFechaCompromisoDTO.getObservaciones());
        if(loFechaCompromisoDTO.getMonto() != null && !loFechaCompromisoDTO.getMonto().equals(""))        
        	loFecha.setMonto(loFechaCompromisoDTO.getMonto());        
        loFecha.setFechaCumplimiento(loFechaCompromisoDTO.getFechaCumplimiento());
        loFecha.setFechaCompromiso(loFechaCompromisoDTO.getFechaCompromiso());
        compromisoDAO.update(loFecha);
    }
   
}
