/**
 * Nombre del Programa : AlmacenTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-jul-2011
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
package mx.gob.segob.nsjp.service.objeto.impl.transform;

import java.util.Set;

import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * Realiza las funciones de conversion entre Almacen y AlmacenDTO.
 * @version 1.0
 * @author Jacob Lobaco
 */
public class AlmacenTransformer {

    /**
     * Transforma un Almacen en un AlmacenDTO.
     * @param almacen Un Almacen basico a tranformar.
     * @return Un AlmacenDTO.
     */
    public static AlmacenDTO transformarAlmacen(Almacen almacen){
        AlmacenDTO almacenDto = new AlmacenDTO(almacen.getIdentificadorAlmacen());
        almacenDto.setDescripcion(almacen.getDescripcion());
        Domicilio domicilio = almacen.getDomicilio();
        if (domicilio != null) {
            almacenDto.setDomicilio(DomicilioTransformer.transformarDomicilio(domicilio));
        }
        almacenDto.setResguardaEvidencias(almacen.getResguardaEvidencias());
        almacenDto.setEsVirtual(almacen.getEsVirtual());
        almacenDto.setEstatusActivo(almacen.getEstatusActivo());
        almacenDto.setFechaAlta(almacen.getFechaAlta());
        Funcionario funcionarioAlta = almacen.getFuncionarioAlta();
        if (funcionarioAlta != null) {
            almacenDto.setFuncionarioAlta(
                FuncionarioTransformer.transformarFuncionario(funcionarioAlta));
        }
        Funcionario funcionarioAutoriza = almacen.getFuncionarioAutoriza();
        if (funcionarioAutoriza != null) {
            almacenDto.setFuncionarioAutoriza(
                FuncionarioTransformer.transformarFuncionario(funcionarioAlta));
        }
        almacenDto.setIdentificadorAlmacen(almacen.getIdentificadorAlmacen());
        almacenDto.setNombreAlmacen(almacen.getNombreAlmacen());
        NumeroExpediente numeroExpediente = almacen.getNumeroExpediente();
        if (numeroExpediente != null) {
            almacenDto.setNumeroExpediente(numeroExpediente.getNumeroExpediente());
        }
        // No transformamos los objetos ya que esto provoca un stackoverflow
//        Set<Objeto> objetos = almacen.getObjetos();
//        Set<ObjetoDTO> objetosDto = new HashSet<ObjetoDTO>();
//        for (Objeto objeto : objetos) {
//            ObjetoDTO objetoDto = ObjetoTransformer.transformarObjeto(objeto);
//            objetosDto.add(objetoDto);
//        }
//        almacenDto.setObjetos(objetosDto);
        return almacenDto;
    }

    /**
     * Transforma un almacen, pero los objetos que tiene el almacen no tienen
     * la relacion hacia el almacen mismo ya que esto provocaria un
     * stackoverflow.
     * @param almacen
     * @return
     */
    public static AlmacenDTO transformarAlmacenConObjetos(Almacen almacen){
        AlmacenDTO almacenDto = transformarAlmacen(almacen);
        Set<Objeto> objetos = almacen.getObjetos();
        for (Objeto objeto : objetos) {
            objeto.setAlmacen(null);
            ObjetoDTO objetoDto = ObjetoTransformer.transformarObjeto(objeto);
            almacenDto.getObjetos().add(objetoDto);
        }
        return almacenDto;
    }

    /**
     * Transforma un AlmacenDTO en un Almacen basico.
     * @param almacenDto El DTO a transformar.
     * @return Un objeto de tipo Almacen
     */
    public static Almacen transformarAlmacen(AlmacenDTO almacenDto){
        Almacen almacen = new Almacen();
        almacen.setDescripcion(almacenDto.getDescripcion());
        almacen.setNombreAlmacen(almacenDto.getNombreAlmacen());

        almacen.setEsVirtual(almacenDto.getEsVirtual());
        almacen.setResguardaEvidencias(almacenDto.getResguardaEvidencias());

        almacen.setEstatusActivo(almacenDto.getEstatusActivo());
        almacen.setFechaAlta(almacenDto.getFechaAlta());
        FuncionarioDTO funcionarioAltaDto = almacenDto.getFuncionarioAlta();
        if (funcionarioAltaDto != null) {
            almacen.setFuncionarioAlta(
                FuncionarioTransformer.transformarFuncionario(funcionarioAltaDto));
        }
        FuncionarioDTO funcionarioAutorizaDto = almacenDto.getFuncionarioAutoriza();
        if (funcionarioAutorizaDto != null) {
            almacen.setFuncionarioAutoriza(
                FuncionarioTransformer.transformarFuncionario(funcionarioAltaDto));
        }
        almacen.setIdentificadorAlmacen(almacenDto.getIdentificadorAlmacen());
        String numeroExpediente = almacenDto.getNumeroExpediente();
        if (numeroExpediente != null) {
            NumeroExpediente numeroExpedienteEntity = new NumeroExpediente();
            numeroExpedienteEntity.setNumeroExpediente(numeroExpediente);
            almacen.setNumeroExpediente(numeroExpedienteEntity);
        }
        almacen.setDomicilio(DomicilioTransformer.transformarDomicilio(almacenDto.getDomicilio()));
        return almacen;
    }

    public static Almacen tranformarAlmacenConObjetos(AlmacenDTO almacenDto){
        Almacen almacen = transformarAlmacen(almacenDto);
        Set<ObjetoDTO> objetosDto = almacenDto.getObjetos();
        for (ObjetoDTO objetoDto : objetosDto) {
            Objeto objeto = ObjetoTransformer.transformarEmptyObjeto(objetoDto);
            almacen.getObjetos().add(objeto);
        }
        return almacen;
    }
    
    /**
     * Transforma un Almacen en un AlmacenDTO.
     * @param almacen Un Almacen basico a tranformar.
     * @return Un AlmacenDTO.
     */
    public static AlmacenDTO transformarAlmacenBasico(Almacen almacen){
        AlmacenDTO almacenDto = new AlmacenDTO(almacen.getIdentificadorAlmacen());
        
        almacenDto.setDescripcion(almacen.getDescripcion());       
        almacenDto.setIdentificadorAlmacen(almacen.getIdentificadorAlmacen());
        almacenDto.setNombreAlmacen(almacen.getNombreAlmacen());
        
        if (almacen.getDomicilio()!=null)
        	almacenDto.setDomicilio(DomicilioTransformer.transformarDomicilio(almacen.getDomicilio()));
        	
        return almacenDto;
    }
    
    /**
     * Transforma un Almacen en un Almacen para la actualizacion.
     * @param Almacen El DTO a transformar.
     * @return Un objeto de tipo Almacen
     */
    public static Almacen transformarAlmacenUpdate(Almacen almacen, Almacen almacenMod){
        almacen.setDescripcion(almacenMod.getDescripcion());
        almacen.setNombreAlmacen(almacenMod.getNombreAlmacen());
        almacen.setEsVirtual(almacenMod.getEsVirtual());
        almacen.setEstatusActivo(almacenMod.getEstatusActivo());
        almacen.setResguardaEvidencias(almacenMod.getResguardaEvidencias());
        return almacen;
    }
}
