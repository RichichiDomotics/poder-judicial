package mx.gob.segob.nsjp.service.objeto.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 *
 * @author Jacob Lobaco
 */
public interface GuardaOActualizaEvidencia {

    /**
     * Dada una CadenaDeCustodiaDTO distinta de null y con un id existente
     * en la base de datos, toma la evidencia asociada a este DTO y
     * la registra o actualiza en la base de datos.
     * El registro se hace si EvidenciaDto.id == null o si EvidenciaDTO.id == 0,
     * en caso contrario se realiza una actualizacion.
     * Para registrar una evidencia se requieren:
     * <ol>
     * <li> evidencia.CodigoBarras
     * <li> evidencia.Descripcion
     * <li> evidencia.NumeroEvidencia
     * </ol>
     * @param cadenaDeCustodiaDTO La cadena de custodia de la que se hara
     * una asociacion con evidencia.
     * @throws NSJPNegocioException En caso que pase alguna de las siguientes
     * condiciones:
     * <ol>
     * <li> {@code cadenaDeCustodiaDTO == null}
     * <li> {@code cadenaDeCustodiaDTO.getCadenaDeCustodiaId() == null}
     * <li> {@code cadenaDeCustodiaDTO.getCadenaDeCustodiaId() == null}
     * <li> {@code cadenaDeCustodiaDTO.getEvidencia() == null}
     * </ol>
     */
    void guardaOActualizaEvidencia(ObjetoDTO objetoDto) throws NSJPNegocioException;

}
