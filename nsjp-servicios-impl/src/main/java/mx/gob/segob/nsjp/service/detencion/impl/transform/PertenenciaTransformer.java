/**
 * Nombre del Programa : PertenenciaTransformer.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Transformador para la entidad Pertenencia
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
package mx.gob.segob.nsjp.service.detencion.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.InventarioPertenencia;
import mx.gob.segob.nsjp.model.Pertenencia;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Transformador para la entidad Pertenencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class PertenenciaTransformer {

    public static Pertenencia transformarDto(PertenenciaDTO dto) {
        if (dto == null) {
            return null;
        }
        final Pertenencia pojo = new Pertenencia();
        pojo.setPertenenciaId(dto.getPertenenciaId());
        pojo.setCantidad(dto.getCantidad());
        if (dto.getCondicionFisica() != null
                && dto.getCondicionFisica().getIdCampo() != null) {
            pojo.setCondicionFisica(new Valor(dto.getCondicionFisica()
                    .getIdCampo()));
        }
        pojo.setDescripcion(dto.getDescripcion());
        if (dto.getDetencion() != null
                && dto.getDetencion().getDetencionId() != null) {
            pojo.setDetencion(new Detencion(dto.getDetencion().getDetencionId()));
        }
        pojo.setEsDevuelto(dto.getEsDevuelto());
        pojo.setFechaDevolucion(dto.getFechaDevolucion());
        if (dto.getTipoPertenencia() != null
                && dto.getTipoPertenencia().getIdCampo() != null) {
            pojo.setTipoPertenencia(new Valor(dto.getTipoPertenencia()
                    .getIdCampo()));
        }
        
        if (dto.getUnidadMedida() != null && dto.getUnidadMedida().getIdCampo() != null){
        	pojo.setUnidadMedida(new Valor(dto.getUnidadMedida().getIdCampo()));
        }
        
        if (dto.getInventarioPertenenciaDTO() != null && 
        		dto.getInventarioPertenenciaDTO().getInventarioPertenenciaId() != null){
        	InventarioPertenencia invPer = new InventarioPertenencia();
        	invPer.setInventarioPertenenciaId(dto.getInventarioPertenenciaDTO()
        			.getInventarioPertenenciaId());
        	pojo.setInventarioPertenencia(invPer);
        }

        return pojo;
    }
    public static PertenenciaDTO transformarEntity(Pertenencia pojo) {
        if (pojo == null) {
            return null;
        }
        final PertenenciaDTO dto = new PertenenciaDTO();
        dto.setPertenenciaId(pojo.getPertenenciaId());
        dto.setCantidad(pojo.getCantidad());
        if (pojo.getCondicionFisica() != null) {
            dto.setCondicionFisica(new ValorDTO(pojo.getCondicionFisica()
                    .getValorId(),pojo.getCondicionFisica()
                    .getValor()));
        }
        dto.setDescripcion(pojo.getDescripcion());
        if (pojo.getDetencion() != null) {
            dto.setDetencion(new DetencionDTO(pojo.getDetencion().getDetencionId()));
        }
        dto.setEsDevuelto(pojo.getEsDevuelto());
        dto.setFechaDevolucion(pojo.getFechaDevolucion());
        if (pojo.getTipoPertenencia() != null) {
            dto.setTipoPertenencia(new ValorDTO(pojo.getTipoPertenencia()
                    .getValorId(),pojo.getTipoPertenencia()
                    .getValor()));
        }
        if (pojo.getUnidadMedida() != null) {
            dto.setUnidadMedida(new ValorDTO(pojo.getUnidadMedida()
                    .getValorId(),pojo.getUnidadMedida()
                    .getValor()));
        }

        return dto;
    }
}
