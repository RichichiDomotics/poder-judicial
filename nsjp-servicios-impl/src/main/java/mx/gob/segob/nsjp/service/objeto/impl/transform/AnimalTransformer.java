/**
* Nombre del Programa : AnimalTransformer.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase que transforma los objeto de tipo Animal
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
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.model.Animal;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Clase que transforma los objeto de tipo Animal
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class AnimalTransformer {
	private final static Logger logger = Logger.getLogger(AnimalTransformer.class);
	
	public static Animal transformarAnimal(AnimalDTO animalDto){
		Animal anl = new Animal();
		anl.setFolioElemento(animalDto.getFolioElemento());
		if(animalDto.getTipoAnimal()!=null && animalDto.getTipoAnimal().getIdCampo()!=null )
			anl.setTipoAnimal(new Valor(animalDto.getTipoAnimal().getIdCampo()));
	
		anl.setEstadoAnimal(animalDto.getEstadoAnimal());
		anl.setRazaAnimal(animalDto.getRazaAnimal());
		
		anl.setDescripcion(animalDto.getDescripcion());

		
		if(animalDto.getValorByCondicionFisicaVal()!=null && animalDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			anl.setValorByCondicionFisicaVal(new Valor(animalDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(animalDto.getExpedienteDTO()!=null){
			anl.setExpediente(new Expediente(animalDto.getExpedienteDTO().getExpedienteId()));
			anl.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en AnimalDTO");

		anl.setEsActivo(animalDto.getEsActivo());
		return anl;

	}
	/**
	 * Transforma un objeto del modelo del tipo <code>Animal</code> a su correspondiente DTO
	 * @param src Datos fuente
	 * @return DTO transformado
	 */
	public static AnimalDTO transformarAnimal(Animal src){
		AnimalDTO dest = null;
		if(src != null){
			dest = new AnimalDTO();
			if(src.getTipoAnimal()!=null){
				dest.setTipoAnimal(new ValorDTO(src.getTipoAnimal().getValorId(),src.getTipoAnimal().getValor()));
			}
			dest.setEstadoAnimal(src.getEstadoAnimal());
			dest.setRazaAnimal(src.getRazaAnimal());
			dest.setEsActivo(src.getEsActivo());		
		}
		return dest;
	}
}
