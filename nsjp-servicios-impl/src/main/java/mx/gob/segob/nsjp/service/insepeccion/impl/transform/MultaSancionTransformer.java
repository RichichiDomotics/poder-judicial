/**
 * Nombre del Programa : MultaSansionTransformer.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.insepeccion.impl.transform;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.inspeccion.EstatusMultaSancion;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO;
import mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Inspeccion;
import mx.gob.segob.nsjp.model.MultaSancion;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class MultaSancionTransformer {

    public static MultaSancionDTO transformarDTOBasico(MultaSancion multaSancion) {
        final MultaSancionDTO dto = new MultaSancionDTO();

        dto.setMultaSancionId(multaSancion.getMultaSancionId());
        dto.setFolioMultaSancion(multaSancion.getFolioMultaSancion());
        dto.setMotivo(multaSancion.getMotivo());
        dto.setDescripcion(multaSancion.getDescripcion());
        dto.setEstatus(new ValorDTO(multaSancion.getEstatus().getValorId(),
                multaSancion.getEstatus().getValor()));
        dto.setFechaRegistro(multaSancion.getFechaRegistro());
        return dto;
    }
    public static MultaSancionDTO transformarDTO(MultaSancion pojo) {
        if (pojo == null) {
            return null;
        }
        MultaSancionDTO dto = transformarDTOBasico(pojo);
        dto.setFuncionarioMultado(new FuncionarioDTO(pojo.getFuncionarioMultado().getClaveFuncionario()));
        if (pojo.getInspeccion()!=null) {
            final InspeccionDTO insDto = new InspeccionDTO();
            insDto.setInspeccionId(pojo.getInspeccion().getInspeccionId());
            insDto.setFolioInspeccion(pojo.getInspeccion().getFolioInspeccion());
            dto.setInspeccion(insDto);
        }
        return dto;
    }

    public static MultaSancion transformarEntity(MultaSancionDTO input) {
        if (input == null) {
            return null;
        }
        MultaSancion pojo = new MultaSancion();
        pojo.setDescripcion(input.getDescripcion());
        pojo.setFechaRegistro(new Date());
        pojo.setEstatus(new Valor(EstatusMultaSancion.PENDIENTE.getValorId()));
        pojo.setFuncionarioMultado(new Funcionario(input
                .getFuncionarioMultado().getClaveFuncionario()));
        pojo.setFuncionarioRegistra(new Funcionario(input
                .getFuncionarioRegistra().getClaveFuncionario()));
        pojo.setMotivo(input.getMotivo());
        if (input.getInspeccion() != null
                && input.getInspeccion().getInspeccionId() != null) {
            pojo.setInspeccion(new Inspeccion(input.getInspeccion()
                    .getInspeccionId()));
        }
        return pojo;
    }

}
