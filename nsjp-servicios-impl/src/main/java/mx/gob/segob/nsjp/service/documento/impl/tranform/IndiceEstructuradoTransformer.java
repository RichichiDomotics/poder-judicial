/**
 * Nombre del Programa : IndiceEstructuradoTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jul-2011
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
package mx.gob.segob.nsjp.service.documento.impl.tranform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;
import mx.gob.segob.nsjp.model.IndiceEstructurado;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Realiza las funciones de conversion entre IndiceEstructurado y IndiceEstructuradoDTO
 * @version 1.0
 * @author rgama
 */
public class IndiceEstructuradoTransformer {

    /**
     * Transforma un IndiceEstructurado a IndiceEstructuradoDTO
     * @param indiceEstructurado: Un indiceEstructurado basico a tranformar.
     * @return Un IndiceEstructuradoDTO.
     */
    public static IndiceEstructuradoDTO transformarIndiceEstructurado(IndiceEstructurado indiceEstructurado){
    	IndiceEstructuradoDTO objetoDTO = new IndiceEstructuradoDTO();
        
        objetoDTO.setIndiceEstructuradoId(indiceEstructurado.getIndiceEstructuradoId());
        objetoDTO.setNombreEtiqueta(indiceEstructurado.getNombreEtiqueta());
        objetoDTO.setTextoEtiqueta(indiceEstructurado.getTextoEtiqueta());
        objetoDTO.setNivel(indiceEstructurado.getNivel());
        objetoDTO.setTipoOficio(new ValorDTO(indiceEstructurado.getTipoOficio()
				.getValorId(), indiceEstructurado.getTipoOficio().getValor()));
        if(indiceEstructurado.getIndiceEstructuradoPadre() != null 
        		&& indiceEstructurado.getIndiceEstructuradoPadre().getIndiceEstructuradoId() != null)
        	objetoDTO.setIndiceEstructuradoPadre(IndiceEstructuradoTransformer.transformarIndiceEstructurado(indiceEstructurado.getIndiceEstructuradoPadre()));
        return objetoDTO;
    }

    /**
     * Transforma un IndiceEstructuradoDTO a IndiceEstructurado
     * @param indiceEstructuradoDTO: Un indiceEstructurado basico a tranformar.
     * @return Un IndiceEstructurado.
     */
    public static IndiceEstructurado transformarIndiceEstructurado(IndiceEstructuradoDTO indiceEstructuradoDTO){
    	IndiceEstructurado objeto = new IndiceEstructurado();
        
        objeto.setIndiceEstructuradoId(indiceEstructuradoDTO.getIndiceEstructuradoId());
        objeto.setNombreEtiqueta(indiceEstructuradoDTO.getNombreEtiqueta());
        objeto.setTextoEtiqueta(indiceEstructuradoDTO.getTextoEtiqueta());
        objeto.setNivel(indiceEstructuradoDTO.getNivel());
        objeto.setTipoOficio(new Valor(indiceEstructuradoDTO.getTipoOficio().getIdCampo()));        
        return objeto;
    }
}
