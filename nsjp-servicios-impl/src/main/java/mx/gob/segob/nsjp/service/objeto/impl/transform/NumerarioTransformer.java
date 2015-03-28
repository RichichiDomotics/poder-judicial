/**
* Nombre del Programa : NumerarioTransformer.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transfomarcion de objetos Numerario
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

import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Numerario;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Clase para la transfomarcion de objetos Numerario
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class NumerarioTransformer {
	private final static Logger logger = Logger.getLogger(NumerarioTransformer.class);
	
	  

	public static Numerario transformarNumerario(NumerarioDTO numerarioDTO){
		Numerario numr = new Numerario();
		numr.setFolioElemento(numerarioDTO.getFolioElemento());
		numr.setFechaDeposito(numerarioDTO.getFechaDeposito());
		numr.setCtaTesoreria(numerarioDTO.getCtaTesoreria());
		numr.setCantidad(numerarioDTO.getCantidad());
		numr.setMoneda(numerarioDTO.getMoneda());
		numr.setDescripcion(numerarioDTO.getDescripcion());

		
		if(numerarioDTO.getValorByCondicionFisicaVal()!=null && numerarioDTO.getValorByCondicionFisicaVal().getIdCampo()!=null)
			numr.setValorByCondicionFisicaVal(new Valor(numerarioDTO.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(numerarioDTO.getExpedienteDTO()!=null){
			numr.setExpediente(new Expediente(numerarioDTO.getExpedienteDTO().getExpedienteId()));
			numr.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en NumerarioDTO");

		numr.setEsActivo(numerarioDTO.getEsActivo());
		return numr;
		
	}
	
	
	public static NumerarioDTO transformarNumerario(Numerario src){
		NumerarioDTO dest = null;
		if(src != null){
			dest = new NumerarioDTO();
			dest.setFechaDeposito(src.getFechaDeposito());
			dest.setCtaTesoreria(src.getCtaTesoreria());
			dest.setCantidad(src.getCantidad());
			dest.setMoneda(src.getMoneda());
			dest.setEsActivo(src.getEsActivo());					
		}
		return dest;
	}
}
