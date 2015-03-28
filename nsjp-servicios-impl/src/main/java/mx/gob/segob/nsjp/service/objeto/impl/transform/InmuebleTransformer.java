/**
* Nombre del Programa : InmuebleTransformer.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/06/2011
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
package mx.gob.segob.nsjp.service.objeto.impl.transform;

import java.util.Date;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.objeto.InmuebleDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Inmueble;
import mx.gob.segob.nsjp.model.Valor;

/**
 * 
 * Clase de transformación de la entidad Inmueble
 * @version 1.0
 * @author Emigdio
 *
 */
public class InmuebleTransformer {

	/**
	 * Transforma un objeto del tipo <code>InmuebleDTO</code> en su correspondiente objeto de entidad
	 * @param src Datos origen
	 * @return DTO transformado
	 */
	public static Inmueble transformarInmueble(InmuebleDTO src){
		Inmueble dest = null;
		if(src != null){
			dest = new Inmueble();
			
			dest.setSuperficie(src.getSuperficie());
			dest.setMconstruccion(src.getmConstruccion());
			dest.setElementoId(src.getElementoId());
			if(src.getTipoInmueble() != null){
				dest.setTipoInmueble(new Valor(src.getTipoInmueble().getIdCampo()));
			}
			if(src.getTipoConstruccion() != null){
				dest.setTipoConstruccion(new Valor(src.getTipoConstruccion().getIdCampo()));
			}
			if(src.getUnidadMedidaTerreno() != null){
				dest.setUnidadMterreno(new Valor(src.getUnidadMedidaTerreno().getIdCampo()));
			}
			if(src.getUnidadMedidaConstruccion() != null){
				dest.setUnidadMconstruccion(new Valor(src.getUnidadMedidaConstruccion().getIdCampo()));
			}
			if(src.getValorByCondicionFisicaVal()!=null && src.getValorByCondicionFisicaVal().getIdCampo()!=null)
				dest.setValorByCondicionFisicaVal(new Valor(src.getValorByCondicionFisicaVal().getIdCampo()));
			
			dest.setDescripcion(src.getDescripcion());
			
			if(src.getExpedienteDTO()!=null){
				dest.setExpediente(new Expediente(src.getExpedienteDTO().getExpedienteId()));
				dest.setFechaCreacionElemento(new Date());
			}
		}

		dest.setEsActivo(src.getEsActivo());
		
		return dest;
	}
	/**
	 * Transforma un objeto del tipo <code>Inmueble</code> en su correspondiente DTO
	 * @param src Datos origen
	 * @return DTO transformado
	 */
	public static InmuebleDTO transformarInmueble(Inmueble src){
		InmuebleDTO dest = null;
		if(src != null){
			dest = new InmuebleDTO();
			
			dest.setSuperficie(src.getSuperficie());
			dest.setmConstruccion(src.getMconstruccion());
			
			if(src.getTipoInmueble() != null){
				dest.setTipoInmueble(new ValorDTO(src.getTipoInmueble().getValorId(),src.getTipoInmueble().getValor()));
			}
			if(src.getTipoConstruccion() != null){
				dest.setTipoConstruccion(new ValorDTO(src.getTipoConstruccion().getValorId(),src.getTipoConstruccion().getValor()));
			}
			if(src.getUnidadMterreno() != null){
				dest.setUnidadMedidaTerreno(new ValorDTO(src.getUnidadMterreno().getValorId(),src.getUnidadMterreno().getValor()));
			}
			if(src.getUnidadMconstruccion() != null){
				dest.setUnidadMedidaConstruccion(new ValorDTO(src.getUnidadMconstruccion().getValorId(),src.getUnidadMconstruccion().getValor()));
			}
			dest.setEsActivo(src.getEsActivo());
		}
		return dest;
	}
}
