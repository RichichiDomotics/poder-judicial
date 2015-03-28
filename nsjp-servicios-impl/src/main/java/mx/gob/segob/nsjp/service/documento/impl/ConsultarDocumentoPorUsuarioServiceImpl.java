/**
 * Nombre del Programa : ConsultarDocumentoPorUsuarioServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-jul-2011
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoPorUsuarioService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
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
public class ConsultarDocumentoPorUsuarioServiceImpl
        implements ConsultarDocumentoPorUsuarioService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarDocumentoPorUsuarioServiceImpl.class);

    @Autowired
    private DocumentoDAO documentoDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocumentoDTO> consultarDocumentoPorUsuario(
            UsuarioDTO usuarioDto, Long tipoDocumento)
            throws NSJPNegocioException {
        if(usuarioDto == null || usuarioDto.getFuncionario() == null ||
                usuarioDto.getFuncionario().getClaveFuncionario() == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("consultarDocumentoPorUsuario = " + usuarioDto);
        }
        Usuario usuario = UsuarioTransformer.transformarDTO(usuarioDto);
        List<Documento> documentosUsuario =
                documentoDao.consultarDocumentosPorUsuario(usuario, tipoDocumento);
        List<DocumentoDTO> documentosDto = Collections.EMPTY_LIST;
        // Si hubo documentos asociados al usuario, los transformamos en DTO's
        if(!documentosUsuario.isEmpty()){
            documentosDto = new LinkedList<DocumentoDTO>();
            for (Documento documento : documentosUsuario) {
                DocumentoDTO documentoDto = DocumentoTransformer.
                        transformarDatosLista(documento);
                documentosDto.add(documentoDto);
            }
        }
        return documentosDto;
    }
    
   
}
