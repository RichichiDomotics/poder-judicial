/**
 * Nombre del Programa : RegistrarAlmacenServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-ago-2011
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
package mx.gob.segob.nsjp.service.almacen.impl;

import java.util.Date;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.almacen.AlmacenDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.almacen.RegistrarAlmacenService;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.domicilio.ModificarDomicilioServicio;
import mx.gob.segob.nsjp.service.objeto.impl.transform.AlmacenTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RegistrarAlmacenServiceImpl implements RegistrarAlmacenService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(RegistrarAlmacenServiceImpl.class);

    @Autowired
    private AlmacenDAO almacenDao;

    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDao;

    @Autowired
    private IngresarDomicilioService ingresarDomicilioService;
	@Autowired
	private ModificarDomicilioServicio modificarDomicilioServicio;	

    @Override
    public Long registrarAlmacen(AlmacenDTO almacenDto) throws NSJPNegocioException {
        if (almacenDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Almacen almacen = AlmacenTransformer.transformarAlmacen(almacenDto);        
    
        if(almacen.getIdentificadorAlmacen() != null && almacen.getIdentificadorAlmacen() > 0){
        	Almacen almacenBD = almacenDao.read(almacen.getIdentificadorAlmacen());     
        	almacenBD = AlmacenTransformer.transformarAlmacenUpdate(almacenBD, almacen);
        	almacenDao.update(almacenBD);     
        	if(almacenBD.getDomicilio() != null && almacenBD.getDomicilio().getElementoId() != null)
        		almacenDto.getDomicilio().setElementoId(almacenBD.getDomicilio().getElementoId());
        	modificarDomicilioServicio.modificarDomicilio(almacenDto.getDomicilio());
        	return almacen.getIdentificadorAlmacen();
        }
        else{
        	 
            if (almacenDto.getEsVirtual() && almacenDto.getNumeroExpediente() != null) {
                NumeroExpediente numeroExpedienteEntity =
                        numeroExpedienteDao.obtenerNumeroExpediente(almacenDto.getNumeroExpediente(),null);
                almacen.setNumeroExpediente(numeroExpedienteEntity);
            }
            almacen.setFechaAlta(new Date());
            almacen.setEstatusActivo(Boolean.TRUE);
            DomicilioDTO domicilio = almacenDto.getDomicilio();
            if (domicilio != null) {
                Long idDomicilio = ingresarDomicilioService.
                        ingresarDomicilio(almacenDto.getDomicilio());
                Domicilio domBD = new Domicilio();
                domBD.setElementoId(idDomicilio);
                almacen.setDomicilio(domBD);
            }
            return almacenDao.create(almacen);
        }
        	
    }

	@Override
	public AlmacenDTO asociarExpedienteAlmacen(AlmacenDTO almacenDTO,
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASOCIAR UN EXPEDIENTE A UN ALMACEN ****/");
		
		if (almacenDTO.getIdentificadorAlmacen()==null || expedienteDTO.getNumeroExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		NumeroExpediente numeroExpediente = numeroExpedienteDao.read(expedienteDTO.getNumeroExpedienteId());
		Almacen almacen = almacenDao.read((almacenDTO.getIdentificadorAlmacen()));
		numeroExpediente.setAlmacen(almacen);
		
		numeroExpedienteDao.update(numeroExpediente);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SE ASOCIO CORRECTAMENTE EL EXPEDIENTE AL ALMACEN ****/");
		return new AlmacenDTO(almacen.getIdentificadorAlmacen());
	}

	
   
}
