/**
 * Nombre del Programa : RelacionReincidenciaTransformer.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-Ago-2011
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
package mx.gob.segob.nsjp.service.elemento.impl.transform;

import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.model.RelacionReincidencia;
import mx.gob.segob.nsjp.service.caso.impl.transform.CasoTransformer;
import mx.gob.segob.nsjp.service.elemento.impl.ElementoTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * Realiza las funciones de conversion entre RelacionReincidencia y RelacionReincidenciaDTO.
 * @version 1.0
 * @author rgama
 */
public class RelacionReincidenciaTransformer {

    /**
     * Transforma un RelacionReincidencia en una RelacionReincidenciaDTO.
     * @param aoRelacionReincidencia Una RelacionReincidencia basico a tranformar.
     * @return Una RelacionReincidenciaDTO.
     */
    public static RelacionReincidenciaDTO transformarRelacion(RelacionReincidencia aoRelacionReincidencia){
        RelacionReincidenciaDTO loRelacionReincidenciaDTO = new RelacionReincidenciaDTO();

        loRelacionReincidenciaDTO.setRelacionReincidenciaId(aoRelacionReincidencia.getRelacionReincidenciaId());
        if (aoRelacionReincidencia.getCaso() != null) {
        	loRelacionReincidenciaDTO.setCaso(
        			CasoTransformer.transformarCasoBasico(aoRelacionReincidencia.getCaso()));
        }

        if (aoRelacionReincidencia.getFuncionario() != null) {
        	loRelacionReincidenciaDTO.setFuncionario(
        			FuncionarioTransformer.transformarFuncionario(aoRelacionReincidencia.getFuncionario()));
        }   
        
        if (aoRelacionReincidencia.getElemento() != null) {
        	loRelacionReincidenciaDTO.setElemento(
        			ElementoTransformer.transformarElemento(aoRelacionReincidencia.getElemento()));
        }
        
        loRelacionReincidenciaDTO.setFechaRelacion(aoRelacionReincidencia.getFechaRelacion());

        return loRelacionReincidenciaDTO;
    }
    
    /**
     * Transforma una RelacionReincidenciaDTO en una RelacionReincidencia.
     * @param aoRelacionReincidenciaDTO Una RelacionReincidenciaDTO
     * @return Una RelacionReincidencia.
     */
    public static RelacionReincidencia transformarRelacion(RelacionReincidenciaDTO aoRelacionReincidenciaDTO){
        RelacionReincidencia loRelacionReincidencia = new RelacionReincidencia();

        loRelacionReincidencia.setRelacionReincidenciaId(aoRelacionReincidenciaDTO.getRelacionReincidenciaId());
        if (aoRelacionReincidenciaDTO.getCaso() != null) {
        	loRelacionReincidencia.setCaso(
        			CasoTransformer.transformarCasoBasico(aoRelacionReincidenciaDTO.getCaso()));
        }

        if (aoRelacionReincidenciaDTO.getFuncionario() != null) {
        	loRelacionReincidencia.setFuncionario(
        			FuncionarioTransformer.transformarFuncionario(aoRelacionReincidenciaDTO.getFuncionario()));
        }   
        
        if (aoRelacionReincidenciaDTO.getElemento() != null) {
        	loRelacionReincidencia.setElemento(
        			ElementoTransformer.transformarElemento(aoRelacionReincidenciaDTO.getElemento()));
        }
        
        loRelacionReincidencia.setFechaRelacion(aoRelacionReincidenciaDTO.getFechaRelacion());

        return loRelacionReincidencia;
    }

}
