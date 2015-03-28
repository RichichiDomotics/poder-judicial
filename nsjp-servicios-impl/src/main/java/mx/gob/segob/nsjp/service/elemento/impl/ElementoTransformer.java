/**
 * Nombre del Programa : ElementoTransformer.java
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
package mx.gob.segob.nsjp.service.elemento.impl;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.lugar.impl.transform.LugarTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

/**
 * Realiza las funciones de conversion entre Elemento y ElementoDTO.
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ElementoTransformer {

    /**
     * Transforma un Elemento en un ElementoDTO.
     * @param elemento Un Elemento basico a tranformar.
     * @return Un ElementoDTO.
     */
    public static ElementoDTO transformarElemento(Elemento elemento){
        ElementoDTO elementoDTO = new ElementoDTO();
        Calidad calidad = elemento.getCalidad();
        if (calidad != null) {
            CalidadDTO calidadDto = new CalidadDTO();
            calidadDto.setCalidadId(calidad.getCalidadId());
            calidadDto.setCalidades(Calidades.getByValor(calidad.getTipoCalidad().getValorId()));
            calidadDto.setDescripcionEstadoFisico(calidad.getDescripcionEstadoFisico());
            calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad().getValorId()));
            elementoDTO.setCalidadDTO(calidadDto);
        }
        elementoDTO.setElementoId(elemento.getElementoId());
        Expediente expediente = elemento.getExpediente();
        if (expediente != null) {
            elementoDTO.setExpedienteDTO(ExpedienteTransformer.transformaExpediente(expediente));
        }
        elementoDTO.setFechaCreacionElemento(elemento.getFechaCreacionElemento());
        Valor tipoElemento = elemento.getTipoElemento();
        if (tipoElemento != null) {
            ValorDTO tipoElementoDto = new ValorDTO(tipoElemento.getValorId());
            tipoElementoDto.setValor(tipoElemento.getValor());
            elementoDTO.setValorIdElemento(tipoElementoDto);
        }
        return elementoDTO;
    }

    /**
     * Transforma un Elemento en un ElementoDTO.
     * @param elemento Un Elemento basico a tranformar.
     * @return Un ElementoDTO.
     */
    public static ElementoDTO transformarElementoCualquiera(Elemento elemento){
        if (elemento instanceof Objeto) {
            return ObjetoTransformer.transformarObjeto((Objeto) elemento);
        }
        
        if (elemento instanceof Lugar) {
            return LugarTransformer.transformarLugar((Lugar)elemento);
        }
        
        return ElementoTransformer.transformarElemento(elemento);
    }    
    
    /**
     * Transforma un ElementoDTO en un Elemento basico.
     * @param elementoDTO El DTO a transformar.
     * @return Un objeto de tipo Elemento
     */
    public static Elemento transformarElemento(ElementoDTO elementoDTO){
        Elemento elemento = new Elemento();
        elemento.setElementoId(elementoDTO.getElementoId());
        return elemento;
    }
}
