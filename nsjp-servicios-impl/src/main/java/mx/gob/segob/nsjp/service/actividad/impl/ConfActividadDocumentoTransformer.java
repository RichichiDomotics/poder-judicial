/**
 * Nombre del Programa : ConfActividadDocumentoTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 06-jul-2011
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
package mx.gob.segob.nsjp.service.actividad.impl;

import mx.gob.segob.nsjp.dto.ConfActividadDocumentoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.ConfActividadDocumento;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;

/**
 * Realiza las funciones de conversion entre ConfActividadDocumento y ConfActividadDocumentoDTO.
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConfActividadDocumentoTransformer {

    /**
     * Transforma un ConfActividadDocumento en un ConfActividadDocumentoDTO.
     * @param confActividadDocumento Un ConfActividadDocumento basico a tranformar.
     * @return Un ConfActividadDocumentoDTO.
     */
    public static ConfActividadDocumentoDTO transformarConfActividadDocumento(ConfActividadDocumento confActividadDocumento){
        ConfActividadDocumentoDTO confActividadDocumentoDTO = new ConfActividadDocumentoDTO();
        
        confActividadDocumentoDTO.setConfActividadDocumentoId(confActividadDocumento.getConfActividadDocumentoId());
        confActividadDocumentoDTO.setMuestraEnCombo(confActividadDocumento.getMuestraEnCombo());
        confActividadDocumentoDTO.setAccion(confActividadDocumento.getAccion());
        confActividadDocumentoDTO.setGrupoActividad(confActividadDocumento.getGrupoActividad());
        confActividadDocumentoDTO.setNombreActividad(confActividadDocumento.getTipoActividad().getValor());
        if(confActividadDocumento.getTipoDocumento()!=null)
        	confActividadDocumentoDTO.setNombreDocumento(confActividadDocumento.getTipoDocumento().getValor());
        confActividadDocumentoDTO.setTipoActividadId(confActividadDocumento.getTipoActividad().getValorId());
        if(confActividadDocumento.getTipoDocumento()!=null)
        	confActividadDocumentoDTO.setTipoDocumentoId(confActividadDocumento.getTipoDocumento().getValorId());
        
        if(confActividadDocumento.getEstadoCambioExpediente()!= null)
        	confActividadDocumentoDTO.setEstadoCambioExpediente(new ValorDTO(
        			confActividadDocumento.getEstadoCambioExpediente().getValorId(), 
        			confActividadDocumento.getEstadoCambioExpediente().getValor()));
        
        if(confActividadDocumento.getForma()!= null)
        	confActividadDocumentoDTO.setForma(FormaTransformer.transformarForma(confActividadDocumento.getForma()));
        
        confActividadDocumentoDTO.setUsaEditor(confActividadDocumento.getUsaEditor());
        return confActividadDocumentoDTO;
    }

    /**
     * Transforma un ConfActividadDocumentoDTO en un ConfActividadDocumento basico.
     * @param confActividadDocumentoDTO El DTO a transformar.
     * @return Un objeto de tipo ConfActividadDocumento
     */
    public static ConfActividadDocumento transformarConfActividadDocumento(ConfActividadDocumentoDTO confActividadDocumentoDTO){
        ConfActividadDocumento confActividadDocumento = new ConfActividadDocumento();
        
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
