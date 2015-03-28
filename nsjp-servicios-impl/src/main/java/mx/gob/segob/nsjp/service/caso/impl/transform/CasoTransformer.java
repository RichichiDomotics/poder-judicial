/**
 * Nombre del Programa : CasoTransformer.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Transformaciones del objeto caso.
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
package mx.gob.segob.nsjp.service.caso.impl.transform; 

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.model.Caso;

/**
 * Transformaciones del objeto caso.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class CasoTransformer {
    /**
     * Tansforma tomando en cuenta solo tres campos: casoId y numeroGeneralCaso y estatus.
     * 
     * @param fromBD
     * @return
     */
    public static List<CasoDTO> transformarCasoBasico(List<Caso> fromBD) {
        List<CasoDTO> resp = new ArrayList<CasoDTO>();
        CasoDTO dto = null;
        for (Caso row : fromBD) {
            dto = new CasoDTO();
            dto.setCasoId(row.getCasoId());
            dto.setNumeroGeneralCaso(row.getNumeroGeneralCaso());
            dto.setFechaApertura(row.getFechaApertura());
            resp.add(dto);
        }

        return resp;
    }
    
    /**
     * Tansforma tomando en cuenta solo tres campos: casoId y numeroGeneralCaso y estatus.
     * 
     * @param fromBD
     * @return
     */
    public static CasoDTO transformarCasoBasico(Caso caso) {
        CasoDTO dto = new CasoDTO();
            dto.setCasoId(caso.getCasoId());
            dto.setNumeroGeneralCaso(caso.getNumeroGeneralCaso());
            if(caso.getEstatus() != null){
            	dto.setEstatus(EstatusCaso.values()[caso.getEstatus()]);
            }
            dto.setFechaApertura(caso.getFechaApertura());
            dto.setImputado(caso.getImputado());
        return dto;
    }
    
    /**
     * Tansforma tomando en cuenta solo tres campos: casoId y numeroGeneralCaso y estatus.
     * 
     * @param fromBD
     * @return
     */
    public static Caso transformarCasoBasico(CasoDTO casoDTO) {
        Caso caso = new Caso();
	        caso.setCasoId(casoDTO.getCasoId());
	        if(casoDTO.getNumeroGeneralCaso()!=null)
	        	caso.setNumeroGeneralCaso(casoDTO.getNumeroGeneralCaso());
	        if(casoDTO.getEstatus()!= null)
	        	caso.setEstatus(casoDTO.getEstatus().getShort());
        return caso;
    }

}
