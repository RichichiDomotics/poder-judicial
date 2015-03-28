/**
 * Nombre del Programa  : AdjuntarArchivoASolicitudServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Guarda un archivo omo un ArchivoDijital y lo asocia a 
 * 						  una solicitud como archivo adjunto.
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */

package mx.gob.segob.nsjp.service.elemento.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.elemento.AdjuntarArchivoAElementoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdjuntarArchivoAElementoServiceImpl
        implements
            AdjuntarArchivoAElementoService {

    private static final Logger logger = Logger
            .getLogger(AdjuntarArchivoAElementoServiceImpl.class);

    @Autowired
    private ElementoDAO elementoDAO;

    @Autowired
    private ArchivoDigitalDAO archivoDigitalDAO;

    @Override
    public void adjuntarArchivoAElemento(ElementoDTO elemento,
            ArchivoDigitalDTO adjunto) throws NSJPNegocioException {

        logger.debug("SERVICIO QUE ACTUALIZA LA FOTO DE UN ELEMENTO : "
                + elemento.getClass().getName());
        if (elemento != null) {
            logger.info("ElementoId:" + elemento.getElementoId());
        }
        logger.info("Adjunto:" + adjunto);

        if (elemento == null || elemento.getElementoId() == null
                || adjunto == null)
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        Elemento loElementoBD = elementoDAO.read(ElementoTransformer
                .transformarElemento(elemento).getElementoId());
        ArchivoDigital ad = ArchivoDigitalTransformer
                .transformarArchivoDigitalDTO(adjunto);

        Long idAD = archivoDigitalDAO.create(ad);
        ad.setArchivoDigitalId(idAD);
        loElementoBD.setArchivoDigital(ad);
        logger.info("ARCHIVO DIGITAL INSERTADO CON ID :: " + idAD);
    }

}
