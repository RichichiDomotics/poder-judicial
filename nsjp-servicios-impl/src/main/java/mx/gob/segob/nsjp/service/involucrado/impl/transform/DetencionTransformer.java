/**
 * Nombre del Programa : DetencionTransformer.java                                    
 * Autor                            : Tattva-IT                                              
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 29/04/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Tranformer de los objetos Detencion                  
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.service.involucrado.impl.transform;

import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.lugar.impl.transform.LugarTransformer;

/**
 * Tranformer de los objetos Detencion
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class DetencionTransformer {
    /**
     * <b>Sin</b> datos del involucrado.
     * @param detencion
     * @return
     */
	public static DetencionDTO transformarDetencionBasico(Detencion detencion) {
	    if (detencion==null) {
	        return null;
	    }
        DetencionDTO detencionDto = new DetencionDTO();
        detencionDto.setDetencionId(detencion.getDetencionId());
        if (detencion.getFechaFinDetencion() != null) {
        	detencionDto.setFechaFinDetencion(detencion.getFechaFinDetencion());
            detencionDto.setFechaFinDetencion(detencion.getFechaFinDetencion()
                    .toString());
        }
        if (detencion.getFechaInicioDetencion() != null) {
        	detencionDto.setFechaInicioDetencion(detencion.getFechaInicioDetencion());
            detencionDto.setFechaInicioDetencion(detencion
                    .getFechaInicioDetencion().toString());
        }
        if (detencion.getFechaRecepcionDetencion() != null) {
            detencionDto.setFechaRecepcionDetencion(detencion
                    .getFechaRecepcionDetencion().toString());
        }
        detencionDto.setMotivoDetencion(detencion.getMotivoDetencion());
        detencionDto.setObservaciones(detencion.getObservaciones());
        detencionDto.setLugarDetencionProvicional(detencion.getLugarDetencionProvisional());
        if(detencion.getLugar() != null){
        	detencionDto.setLugarDetencionDTO(LugarTransformer.transformarLugar(detencion.getLugar()));
        }
        
        return detencionDto;
    }

	/**
	 * <b>Con</b> datos del involucrado.
	 * @param detencion
	 * @return
	 */
    public static DetencionDTO transformarDetencion(Detencion detencion) {
        if (detencion==null) {
            return null;
        }
        DetencionDTO detencionDto = transformarDetencionBasico(detencion);
        
        detencionDto.setInvolucradoDTO(InvolucradoTransformer.transformarInvolucradoBasico(detencion.getInvolucrado()));

        return detencionDto;
    }	
	
    public static Detencion transformarDetencion(DetencionDTO detencionDTO) {
        Detencion detencion = new Detencion();

        if (detencionDTO.getDetencionId() != null && detencionDTO.getDetencionId() > 0){
            detencion.setDetencionId(detencionDTO.getDetencionId());
        }

        detencion.setFechaFinDetencion(detencionDTO.getFechaFinDetencion());
        detencion.setFechaInicioDetencion(detencionDTO.getFechaInicioDetencion());
        detencion.setMotivoDetencion(detencionDTO.getMotivoDetencion());
        detencion.setObservaciones(detencionDTO.getObservaciones());
        detencion.setLugarDetencionProvisional(detencionDTO.getLugarDetencionProvicional());
        
        if (detencionDTO.getFechaRecepcionDetencion() != null){
            detencion.setFechaRecepcionDetencion(detencionDTO.getFechaRecepcionDetencion());
        }
        
        if (detencionDTO.getLugarDetencionDTO() != null) {
            detencion.setLugar(LugarTransformer.transformarLugar(detencionDTO.getLugarDetencionDTO()));
        }

        if (detencionDTO.getFuncionarioByFuncionarioDetiene() != null) {
//            final Funcionario funcionario = new Funcionario();
//            funcionario.setClaveFuncionario(detencionDTO.getFuncionarioByFuncionarioDetiene().getClaveFuncionario());
//            detencion.setFuncionarioByFuncionarioDetiene(funcionario);
            //FIXME vaguirre se queda el código de arriba o el de aqui abajo?
            detencion.setFuncionarioByFuncionarioDetiene(FuncionarioTransformer.transformarFuncionario(detencionDTO.getFuncionarioByFuncionarioDetiene()));
        }
        
        if(detencionDTO.getInvolucradoDTO() != null){
        	detencion.setInvolucrado(InvolucradoTransformer.transformarInvolucrado(detencionDTO.getInvolucradoDTO()));
        }
        
        //TODO Falta agregar la parte de IPH
        
        return detencion;
    }

}
