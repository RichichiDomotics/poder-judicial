/**
 * Nombre del Programa : AsociarNotaADocumentoServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 04-jul-2011
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.Date;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.NotaDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Nota;
import mx.gob.segob.nsjp.service.documento.AsociarNotaADocumentoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.documento.impl.tranform.NotaTransformer;
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
public class AsociarNotaADocumentoServiceImpl
        implements AsociarNotaADocumentoService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(AsociarNotaADocumentoServiceImpl.class);

    @Autowired
    private NotaDAO notaDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void asociarNotaADocumento(NotaDTO notaDto,
            DocumentoDTO documentoDto) throws NSJPNegocioException {
    	if (logger.isDebugEnabled())
			logger.debug("/SERVICIO PARA ASOCIAR NOTA A DOCUMENTO/");
    	
        if(notaDto == null || documentoDto == null ||
                documentoDto.getDocumentoId() == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if(notaDto.getIdNota() != null){
            throw new NSJPNegocioException(
                    CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
        }
        Nota nota = NotaTransformer.transformarNota(notaDto);
        nota.setFechaActualizacion(new Date());
        nota.setFechaCreacion(new Date());
        Documento documento = DocumentoTransformer.
                transformarDocumentoDTO(documentoDto);
        notaDao.asociarNotaADocumento(nota, documento);
    }
   
}
