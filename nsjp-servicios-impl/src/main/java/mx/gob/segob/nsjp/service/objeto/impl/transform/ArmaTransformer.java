/**
* Nombre del Programa : ArmaTransformer.java
* Autor                            : Tattva-IT
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Transformer de los objetos Arma
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
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.model.Arma;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Transformer de los objetos Arma
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class ArmaTransformer {
	private final static Logger logger = Logger.getLogger(ArmaTransformer.class);
	
	public static Arma transformarArma(ArmaDTO armadto){
		Arma arm = new Arma();
		
		if(armadto.getMarca()!=null && armadto.getMarca().getIdCampo()!=null )
			arm.setMarca(new Valor(armadto.getMarca().getIdCampo()));
	
		if(armadto.getTipoArma()!=null && armadto.getTipoArma().getIdCampo()!=null)
			arm.setTipoArma(new Valor(armadto.getTipoArma().getIdCampo()));
		
		arm.setModelo(armadto.getModelo());
		arm.setMatricula(armadto.getMatricula());
		arm.setCalibre(armadto.getCalibre());
		arm.setDescripcion(armadto.getDescripcion());
//                // Al parecer es necesario transformar el tipo de calidad de un
//                // arma para poder persistirla correctamente.
//                CalidadDTO calidadDTO = armadto.getCalidadDTO();
//                if(calidadDTO != null){
//                    Calidad calidad = new Calidad();
//                    calidad.setTipoCalidad(new Valor(calidadDTO.getCalidadId()));
//                    arm.setCalidad(calidad);
//                }
		
		if(armadto.getValorByCondicionFisicaVal()!=null && armadto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			arm.setValorByCondicionFisicaVal(new Valor(armadto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(armadto.getExpedienteDTO()!=null){
			arm.setExpediente(new Expediente(armadto.getExpedienteDTO().getExpedienteId()));
			arm.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en ArmaDTO");

		arm.setEsActivo(armadto.getEsActivo());
		arm.setFolioElemento(armadto.getFolioElemento());
		return arm;
	}
	/**
	 * Transforma un objeto del tipo <code>Arma</code> del modelo en su correspondiente
	 * DTO
	 * @param src Datos de origen 
	 * @return DTO tranformado
	 */
	public static ArmaDTO  transformarArma(Arma src){
		ArmaDTO dest = null;
		if(src != null){
			dest = new ArmaDTO();
			if(src.getMarca() != null){
				dest.setMarca(new ValorDTO(src.getMarca().getValorId(),src.getMarca().getValor()));
			}
			
			if(src.getTipoArma() != null){
				dest.setTipoArma(new ValorDTO(src.getTipoArma().getValorId(),src.getTipoArma().getValor()));
			}
		
			
			dest.setModelo(src.getModelo());
			dest.setMatricula(src.getMatricula());
			dest.setCalibre(src.getCalibre());
			dest.setEsActivo(src.getEsActivo());			
		}
		return dest;
	}
}
