/**
 * Nombre del Programa : JerarquiaOrganizacionalTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-jun-2011
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
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public class JerarquiaOrganizacionalTransformer {

    /**
     * 
     * @param area
     * @return
     */
    public static AreaDTO transformarJerarquiaOrganizacionalArea(
            JerarquiaOrganizacional area) {
        if (area != null) {
            AreaDTO areaDto = new AreaDTO(area.getJerarquiaOrganizacionalId(),
                    area.getNombre());
            return areaDto;
        }
        return null;
    }
    
    public static JerarquiaOrganizacionalDTO transformarJerarquiaOrganizacional(JerarquiaOrganizacional jerarquiaOrganizacional){
    	if (jerarquiaOrganizacional==null)
    		return null;
    	
    	JerarquiaOrganizacionalDTO jerarquiaOrganizacionalDTO = new JerarquiaOrganizacionalDTO();
    	jerarquiaOrganizacionalDTO.setJerarquiaOrganizacionalId(jerarquiaOrganizacional.getJerarquiaOrganizacionalId());
    	jerarquiaOrganizacionalDTO.setNombre( jerarquiaOrganizacional.getNombre());
    	
    	return jerarquiaOrganizacionalDTO;
    }

    public static JerarquiaOrganizacional transformarJerarquiaOrganizacional(JerarquiaOrganizacionalDTO jerarquiaOrganizacionalDTO){
    	if (jerarquiaOrganizacionalDTO==null)
    		return null;
    	
    	JerarquiaOrganizacional jerarquiaOrganizacional = new JerarquiaOrganizacional();
    	jerarquiaOrganizacional.setJerarquiaOrganizacionalId(jerarquiaOrganizacionalDTO.getJerarquiaOrganizacionalId());
    	jerarquiaOrganizacional.setNombre( jerarquiaOrganizacionalDTO.getNombre());
    	
    	return jerarquiaOrganizacional;
    }
    
}
