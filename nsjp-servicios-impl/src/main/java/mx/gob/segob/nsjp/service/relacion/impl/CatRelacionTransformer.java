/**
 * Nombre del Programa : CatRelacionTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
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
package mx.gob.segob.nsjp.service.relacion.impl;

import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;
import mx.gob.segob.nsjp.model.CatRelacion;

/**
 * Realiza las funciones de conversion entre CatRelacion y CatRelacionDTO.
 * @version 1.0
 * @author Jacob Lobaco
 */
public class CatRelacionTransformer {

    /**
     * Transforma un CatRelacion en un CatRelacionDTO.
     * @param catRelacion Un CatRelacion basico a tranformar.
     * @return Un CatRelacionDTO.
     */
    public static CatRelacionDTO transformarCatRelacion(CatRelacion catRelacion){
        CatRelacionDTO catRelacionDTO = new CatRelacionDTO();
        CatCategoriaRelacion catCategoriaRelacion = catRelacion.getCatCategoriaRelacion();
        if (catCategoriaRelacion != null) {
            catRelacionDTO.setCatCategoriaRelacionId(
                    catCategoriaRelacion.getCatCategoriaRelacionId());
            catRelacionDTO.setDesCategoriaRelacion(catRelacion.getCatCategoriaRelacion().getDesCategoriaRelacion());
        }
        catRelacionDTO.setCatRelacionId(catRelacion.getCatRelacionId());
        catRelacionDTO.setDescripcionRelacion(catRelacion.getDescripcionRelacion());
        return catRelacionDTO;
    }

    /**
     * Transforma un CatRelacionDTO en un CatRelacion basico.
     * @param catRelacionDTO El DTO a transformar.
     * @return Un objeto de tipo CatRelacion
     */
    public static CatRelacion transformarCatRelacion(CatRelacionDTO catRelacionDTO){
        CatRelacion catRelacion = new CatRelacion();
        catRelacion.setCatRelacionId(catRelacionDTO.getCatRelacionId());
        return catRelacion;
    }
}
