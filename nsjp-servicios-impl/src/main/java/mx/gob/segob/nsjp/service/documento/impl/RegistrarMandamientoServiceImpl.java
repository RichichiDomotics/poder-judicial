/**
 * Nombre del Programa : RegistrarMandamientoServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Sep 2011
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.documento.MandamientoDAO;
import mx.gob.segob.nsjp.dto.documento.MandamientoWSDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Mandamiento;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.RegistrarMandamientoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementaci�n del servicio para registrar el mandamiento en SSP enviado por
 * PJ.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service("registrarMandamientoService")
@Transactional
public class RegistrarMandamientoServiceImpl
        implements
            RegistrarMandamientoService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RegistrarMandamientoServiceImpl.class);
    @Autowired
    private ArchivoDigitalDAO archivoDigitalDAO;
    @Autowired
    private MandamientoDAO mandamientoDao;
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.documento.RegistrarMandamientoService#
     * registrarMandamiento(mx.gob.segob.nsjp.dto.documento.MandamientoWSDTO)
     */
    @Override
    public void registrarMandamiento(MandamientoWSDTO manda)
            throws NSJPNegocioException {
        logger.debug("Recibiendo Mandamiento :: " + manda.getFolioDocumento());
        Mandamiento mandamientoPojo = new Mandamiento();

        ArchivoDigital archivo = new ArchivoDigital();
        archivo.setNombreArchivo(manda.getArchivoDigital().getNombreArchivo());
        archivo.setTipoArchivo(manda.getArchivoDigital().getTipoArchivo());
        archivo.setContenido(manda.getArchivoDigital().getContenido());
        archivo.setArchivoDigitalId(archivoDigitalDAO.create(archivo));

        mandamientoPojo.setArchivoDigital(archivo);
        mandamientoPojo.setNombreDocumento(manda.getNombreDocumento());
        mandamientoPojo.setEstatus(new Valor(manda.getIdEstatus()));
        mandamientoPojo.setTipoMandamiento(new Valor(manda
                .getIdTipoMandamiento()));
        mandamientoPojo.setFechaCreacion(new Date());
        mandamientoPojo.setNombreDocumento(manda.getNombreDocumento());
        mandamientoPojo.setFolioDocumento(manda.getFolioDocumento());
        mandamientoPojo.setTipoDocumento(new Valor(manda
                .getTipoDocumentoDTO()));
        mandamientoPojo.setForma(new Forma(manda.getFormaId()));
        Domicilio domicilio = new Domicilio();
        domicilio.setElementoId(59L);
        mandamientoPojo.setDomicilio(domicilio);
        Involucrado involucrado = new Involucrado();
        involucrado.setElementoId(92L);
        mandamientoPojo.setInvolucrado(involucrado);
        
        this.mandamientoDao.create(mandamientoPojo);

    }

}
