/**
 * Nombre del Programa : CadenaDeCustodiaTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jun-2011
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
package mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaWSDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

import org.apache.log4j.Logger;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public class CadenaDeCustodiaTransformer {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(CadenaDeCustodiaTransformer.class);

    /**
     * Transforma una CadenaDeCustodia en una CadenaDeCustodiaDTO.
     * 
     * @param cadenaDeCustodia
     *            Una CadenaDeCustodia basica a tranformar.
     * @return Una CadenaDeCustodiaDTO.
     */
    public static CadenaDeCustodiaDTO transformarCadenaDeCustodia(
            CadenaDeCustodia cadenaDeCustodia) {
        if (cadenaDeCustodia == null) {
            return null;
        }
        CadenaDeCustodiaDTO cadenaDeCustodiaDTO = transformarCadenaDeCustodiaBasico(cadenaDeCustodia);
        /*
         * Correccion del error al consultar Evidencia por medio de Cadena
         * Custodia...
         */
        if (cadenaDeCustodia.getEvidencias() != null) {
            List<EvidenciaDTO> evidenciasDTO = new ArrayList<EvidenciaDTO>();
            for (Evidencia evidencia : cadenaDeCustodia.getEvidencias()) {
                CadenaDeCustodia cadenasimple = new CadenaDeCustodia();
                cadenasimple.setCadenaDeCustodiaId(cadenaDeCustodia
                        .getCadenaDeCustodiaId());
                evidencia.setCadenaDeCustodia(cadenasimple);
                evidenciasDTO.add(EvidenciaTransformer
                        .transformarEvidencia(evidencia, true));
            }
            Collections.sort(evidenciasDTO);
            cadenaDeCustodiaDTO.setEvidencias(evidenciasDTO);
        }
        if (cadenaDeCustodia.getExpediente() != null)
            cadenaDeCustodiaDTO.setExpedienteDTO(ExpedienteTransformer
                    .transformarExpedienteBasico(cadenaDeCustodia
                            .getExpediente()));

        return cadenaDeCustodiaDTO;
    }

    /**
     * Transforma una CadenaDeCustodia en una CadenaDeCustodiaDTO.
     * 
     * @param cadenaDeCustodia
     *            Una CadenaDeCustodia basica a tranformar.
     * @return Una CadenaDeCustodiaDTO.
     */
    public static CadenaDeCustodiaDTO transformarCadenaDeCustodiaBasico(
            CadenaDeCustodia cadenaDeCustodia) {
        if (cadenaDeCustodia == null) {
            return null;
        }
        final CadenaDeCustodiaDTO cadenaDeCustodiaDTO = new CadenaDeCustodiaDTO(
                cadenaDeCustodia.getCadenaDeCustodiaId());
        logger.debug("cadenaDeCustodia.getCadenaDeCustodiaId() :: "
                + cadenaDeCustodia.getCadenaDeCustodiaId());
        cadenaDeCustodiaDTO.setFechaIntercambio(cadenaDeCustodia
                .getFechaIntercambio());
        cadenaDeCustodiaDTO.setFechaLevantamiento(cadenaDeCustodia
                .getFechaLevantamiento());
        cadenaDeCustodiaDTO.setObservacion(cadenaDeCustodia.getObservacion());
        cadenaDeCustodiaDTO.setQuienEntrega(cadenaDeCustodia.getQuienEntrega());
        cadenaDeCustodiaDTO.setQuienRecibe(cadenaDeCustodia.getQuienRecibe());
        logger.debug("cadenaDeCustodia.getFolio() :: "
                + cadenaDeCustodia.getFolio());
        cadenaDeCustodiaDTO.setFolio(cadenaDeCustodia.getFolio());
        cadenaDeCustodiaDTO.setQuienEmbala(cadenaDeCustodia.getQuienEmbala());
        cadenaDeCustodiaDTO.setQuienTransporta(cadenaDeCustodia
                .getQuienTransporta());
		cadenaDeCustodiaDTO.setInstitucionEmbalaje(cadenaDeCustodia
				.getInstitucionEmbalaje());
		cadenaDeCustodiaDTO.setInstitucionTraslado(cadenaDeCustodia
				.getInstitucionTraslado());
		
        if (cadenaDeCustodia.getExpediente() != null)
            cadenaDeCustodiaDTO.setExpedienteDTO(ExpedienteTransformer
                    .transformarExpedienteBasico(cadenaDeCustodia
                            .getExpediente()));

        return cadenaDeCustodiaDTO;
    }

    /**
     * Transforma una CadenaDeCustodiaDTO en una CadenaDeCustodia basica.
     * 
     * @param cadenaDeCustodiaDTO
     *            El DTO a transformar.
     * @return Un objeto de tipo CadenaDeCustodia
     */
    public static CadenaDeCustodia transformarCadenaDeCustodia(
            CadenaDeCustodiaDTO cadenaDeCustodiaDTO) {
        CadenaDeCustodia cadenaDeCustodia = new CadenaDeCustodia();
        cadenaDeCustodia.setCadenaDeCustodiaId(cadenaDeCustodiaDTO
                .getCadenaDeCustodiaId());
        // el DTO no trae evidencias
        // cadenaDeCustodia.setEvidencias(cadenaDeCustodiaDTO.g);
        cadenaDeCustodia.setFechaIntercambio(cadenaDeCustodiaDTO
                .getFechaIntercambio());
        cadenaDeCustodia.setFechaLevantamiento(cadenaDeCustodiaDTO
                .getFechaLevantamiento());
        if (cadenaDeCustodiaDTO.getFolio() != null)
            cadenaDeCustodia.setFolio(cadenaDeCustodiaDTO.getFolio());
        cadenaDeCustodia.setObservacion(cadenaDeCustodiaDTO.getObservacion());

        cadenaDeCustodia.setQuienEntrega(cadenaDeCustodiaDTO.getQuienEntrega());
        cadenaDeCustodia.setQuienRecibe(cadenaDeCustodiaDTO.getQuienRecibe());
        cadenaDeCustodia.setQuienEmbala(cadenaDeCustodiaDTO.getQuienEmbala());
        cadenaDeCustodia.setQuienTransporta(cadenaDeCustodiaDTO
                .getQuienTransporta());
		cadenaDeCustodia.setInstitucionEmbalaje(cadenaDeCustodiaDTO
				.getInstitucionEmbalaje());
		cadenaDeCustodia.setInstitucionTraslado(cadenaDeCustodiaDTO
				.getInstitucionTraslado());
        return cadenaDeCustodia;
    }

    public static CadenaDeCustodiaDTO transformarCadenaDeCustodia(
            CadenaDeCustodiaWSDTO input) {
        CadenaDeCustodiaDTO cadena = new CadenaDeCustodiaDTO();

        cadena.setFechaIntercambio(input.getFechaIntercambio());
        cadena.setFechaLevantamiento(input.getFechaLevantamiento());
        cadena.setFolio(input.getFolio());
        cadena.setObservacion(input.getObservacion());
        cadena.setQuienEmbala(input.getQuienEmbala());
        cadena.setQuienEntrega(input.getQuienEntrega());
        cadena.setQuienRecibe(input.getQuienRecibe());
        cadena.setQuienTransporta(input.getQuienTransporta());
        //TODO Genera el WSDTO
//		cadena.setInstitucionEmbalaje(input
//				.getInstitucionEmbalaje());
//		cadena.setInstitucionTraslado(input
//				.getInstitucionTraslado());
        return cadena;
    }
}
