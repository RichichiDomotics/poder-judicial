/**
 * Nombre del Programa : InspeccionTransformer.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Oct 2011
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

import mx.gob.segob.nsjp.comun.enums.inspeccion.EstatusInspeccion;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Inspeccion;
import mx.gob.segob.nsjp.model.MultaSancion;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class InspeccionTransformer {

    public static Inspeccion transformarEntity(InspeccionDTO input) {
        if (input == null) {
            return null;
        }
        final Inspeccion pojo = new Inspeccion();
        pojo.setDescripcion(input.getDescripcion());
        pojo.setEstatus(new Valor(EstatusInspeccion.EN_PROCESO.getValorId()));
        pojo.setFechaRegistro(new Date());
        pojo.setFuncionarioInspeccionado(new Funcionario(input
                .getFuncionarioInspeccionado().getClaveFuncionario()));
        pojo.setFuncionarioRegistra(new Funcionario(input
                .getFuncionarioRegistra().getClaveFuncionario()));
        pojo.setMotivo(input.getMotivo());
        if (input.getExpediente() != null) {
            pojo.setNumeroExpediente(new NumeroExpediente(input.getExpediente()
                    .getNumeroExpedienteId()));
        }
        if (input.getMultaSancion()!=null&&input.getMultaSancion().getMultaSancionId()!=null) {
            pojo.setMultaSancion(new MultaSancion(input.getMultaSancion().getMultaSancionId()));
        }
        pojo.setDescripcion(input.getDescripcion());
        return pojo;
    }
    
    /**
     * 
     * @param pojo
     * @return
     */
    public static InspeccionDTO transformarDTO(Inspeccion input) {
        if (input == null) {
            return null;
        }
        final InspeccionDTO dto = new InspeccionDTO();
        dto.setInspeccionId(input.getInspeccionId());
        dto.setFolioInspeccion(input.getFolioInspeccion());
        dto.setDescripcion(input.getDescripcion());
        dto.setEstatus(new ValorDTO(input.getEstatus().getValorId(), input
                .getEstatus().getValor()));
        dto.setFechaRegistro(new Date());
        dto.setFuncionarioInspeccionado(new FuncionarioDTO(input
                .getFuncionarioInspeccionado().getClaveFuncionario()));
        final FuncionarioDTO funReg = new FuncionarioDTO(input
                .getFuncionarioRegistra().getClaveFuncionario());
        funReg.setNombreFuncionario(input
                .getFuncionarioRegistra().getNombreFuncionario());
        funReg.setApellidoPaternoFuncionario(input
                .getFuncionarioRegistra().getApellidoPaternoFuncionario());
        funReg.setApellidoMaternoFuncionario(input
                .getFuncionarioRegistra().getApellidoMaternoFuncionario());
        dto.setFuncionarioRegistra(funReg);
        dto.setMotivo(input.getMotivo());
        dto.setDescripcion(input.getDescripcion());
        if (input.getNumeroExpediente() != null) {
            dto.setExpediente(new ExpedienteDTO(input.getNumeroExpediente()
                    .getExpediente().getExpedienteId(), input
                    .getNumeroExpediente().getNumeroExpedienteId(), input
                    .getNumeroExpediente().getNumeroExpediente()));
        }
        if (input.getMultaSancion() != null) {
            dto.setMultaSancion(MultaSancionTransformer
                    .transformarDTOBasico(input.getMultaSancion()));
        }
        return dto;
    }

}
