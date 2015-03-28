/**
 * Nombre del Programa : IngresarDocumentoOficialServiceImpl.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios para ingresar un documento oficial
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
import mx.gob.segob.nsjp.dao.objeto.DocumentoOficialDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.DocumentoOficial;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarDocumentoOficialService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.DocumentoOficialTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicios para ingresar un documento oficial
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarDocumentoOficialServiceImpl implements
        IngresarDocumentoOficialService {

    private final static Logger logger = Logger.getLogger(IngresarDocumentoOficialServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private DocumentoOficialDAO documentoOficialDAO;

    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;
     /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarDocumentoOficial(DocumentoOficialDTO documentoOficialDto)
            throws NSJPNegocioException {
        if (documentoOficialDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = documentoOficialDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guarda(documentoOficialDto);
        }else{
            return actualiza(documentoOficialDto);
        }
    }

    private Long guarda(DocumentoOficialDTO documentoOficialDto) throws NSJPNegocioException {
        Long idDocumentoOficial = 0L;
        DocumentoOficial documentoOficial = new DocumentoOficial();
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del DocumentoOficial");
        }
        if (documentoOficialDto == null) {
            logger.error("El documentoOficial es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = documentoOficialDto.getExpedienteDTO();
        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null) {
            logger.error("El documentoOficial es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("DocumentoOficial    !!!!!!!");
        documentoOficial = DocumentoOficialTransformer.transformarDocumentoOficial(documentoOficialDto);
        CalidadDTO calidadDTO = documentoOficialDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        documentoOficial.setCalidad(this.calidadDAO.read(idCalidad));
        documentoOficial.setValorByTipoObjetoVal(new Valor(Objetos.DOCUMENTO_OFICIAL.getValorId()));
        documentoOficial.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idDocumentoOficial = this.documentoOficialDAO.create(documentoOficial);
        documentoOficialDto.setElementoId(idDocumentoOficial);
        guardaOActualizaObjetos(documentoOficialDto);
        logger.debug("Se genero el documentoOficial con id: " + idDocumentoOficial);
        return idDocumentoOficial;
    }

    private Long actualiza(DocumentoOficialDTO documentoOficialDto) throws NSJPNegocioException {
        Long elementoId = documentoOficialDto.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        DocumentoOficial actualizable = documentoOficialDAO.read(elementoId);
        DocumentoOficial actualizador = DocumentoOficialTransformer.transformarDocumentoOficial(documentoOficialDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(DocumentoOficialDTO documentoOficialDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = documentoOficialDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(documentoOficialDto);
        }
    }
}
