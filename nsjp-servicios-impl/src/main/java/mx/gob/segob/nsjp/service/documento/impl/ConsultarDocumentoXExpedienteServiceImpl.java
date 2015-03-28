/**
 * Nombre del Programa : ConsultarDocumentoXExpedienteServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-jul-2011
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

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoXExpedienteService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service
@Transactional
public class ConsultarDocumentoXExpedienteServiceImpl
        implements ConsultarDocumentoXExpedienteService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarDocumentoXExpedienteServiceImpl.class);

    @Autowired
    private DocumentoDAO documentoDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentoDTO consultarDocumentoXExpediente(ExpedienteDTO expedienteDto,
            Long tipoDocumento) throws NSJPNegocioException {
        if(expedienteDto == null || tipoDocumento == null ||
                expedienteDto.getNumeroExpediente() == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Expediente expediente =
                ExpedienteTransformer.transformarExpediente(expedienteDto);
        Documento documento = documentoDao.
                consultarDocumentoXExpediente(expediente, tipoDocumento);
        return documento == null ? null :
            DocumentoTransformer.transformarDatosLista(documento);
    }
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.service.documento.ConsultarDocumentoXExpedienteService#consultarDocumentosPorExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
     */
	@Override
	public List<DocumentoDTO> consultarDocumentosPorNumeroExpediente(
			ExpedienteDTO expediente) {
		List<DocumentoDTO> resultado = new ArrayList<DocumentoDTO>();
		List<Documento> documentos = documentoDao.consultarDocumentosPorNumeroExpedienteId(expediente.getNumeroExpedienteId());
		for(Documento doc:documentos){
			resultado.add(DocumentoTransformer.transformarDocumento(doc));
		}
		
		return resultado;
	}


   
}
