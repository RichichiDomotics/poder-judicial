/**
 * Nombre del Programa : NotaExpedienteTransformer.java
 * Autor                            : ricardog
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 07-jul-2011
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.NotaExpedienteDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NotaExpediente;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;

/**
 * Realiza las funciones de conversion entre NotaExpediente y NotaExpedienteDTO.
 * @version 1.0
 * @author ricardog
 */
public class NotaExpedienteTransformer {

    /**
     * Transforma una NotaExpediente en una NotaExpedienteDTO.
     * @param nota Un NotaExpediente basico a tranformar.
     * @return Un NotaExpedienteDTO.
     */
    public static NotaExpedienteDTO transformarNotaExpediente(NotaExpediente nota){
        NotaExpedienteDTO notaDTO = new NotaExpedienteDTO();
        
        notaDTO.setIdNota(nota.getIdNota());
        notaDTO.setNombreNota(nota.getNombreNota());
        notaDTO.setDescripcion(nota.getDescripcion());
        notaDTO.setFechaCreacion(nota.getFechaCreacion());
        notaDTO.setFechaActualizacion(nota.getFechaActualizacion());   
        if(nota.getExpediente()!= null && nota.getExpediente().getExpedienteId()!= null)
        	notaDTO.setExpediente(new ExpedienteDTO(nota.getExpediente().getExpedienteId()));
        return notaDTO;
    }

    /**
     * Transforma un NotaExpedienteDTO en un NotaExpediente basico.
     * @param notaDTO El DTO a transformar.
     * @return Un objeto de tipo NotaExpediente
     */
    public static NotaExpediente transformarNotaExpediente(NotaExpedienteDTO notaDTO){
        NotaExpediente nota = new NotaExpediente();
    	        
        if(notaDTO.getIdNota() != null){
        	nota.setIdNota(notaDTO.getIdNota());  
        	//Fecha actualizacion
            if(notaDTO.getFechaActualizacion()!= null)
            	nota.setFechaActualizacion(notaDTO.getFechaActualizacion());
            else
            	nota.setFechaActualizacion(new Date());
        }        
        //Fecha creacion
        if(notaDTO.getFechaCreacion()!= null)
        	nota.setFechaCreacion(notaDTO.getFechaCreacion());
        else
        	nota.setFechaCreacion(new Date());  	
        if(notaDTO.getNombreNota()!=null)
        	nota.setNombreNota(notaDTO.getNombreNota());
        else
        	nota.setNombreNota("Nota");
        nota.setDescripcion(notaDTO.getDescripcion());
        
        ExpedienteDTO expedienteDTO = notaDTO.getExpediente();
        if(expedienteDTO != null && expedienteDTO.getExpedienteId()!= null){            
            nota.setExpediente(new Expediente(expedienteDTO.getExpedienteId()));
        }
        return nota;
    }
}
