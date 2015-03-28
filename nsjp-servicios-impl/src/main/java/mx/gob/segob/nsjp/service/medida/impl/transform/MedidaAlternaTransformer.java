/**
* Nombre del Programa : MedidaAlternaTransformer.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/08/2011
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
package mx.gob.segob.nsjp.service.medida.impl.transform;

import org.apache.commons.lang.StringUtils;

import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.model.MedidaAlterna;

/**
 *  Transforma el objeto de MedidaAlterna a MedidaAlternaDTO, y viceversa.
 *  
 * @version 1.0
 * @author GustavoBP
 *
 */
public class MedidaAlternaTransformer {
	
	public static MedidaAlternaDTO transformarMedidaAlterna(MedidaAlterna medidaAlterna){
		MedidaAlternaDTO medidaAlternaDTO = null;
		
		//Datos de la Medida - Este Casting 'hacia abajo' es posible porq en el método se crea la instancia adecuada.
		medidaAlternaDTO = (MedidaAlternaDTO)MedidaTransformer.transformarMedida(medidaAlterna);
		
		medidaAlternaDTO.setAnios(medidaAlterna.getAnios());
		medidaAlternaDTO.setMeses(medidaAlterna.getMeses());
		
		if (StringUtils.isBlank(medidaAlterna.getTextoParcial()))
			medidaAlternaDTO.setGuardadoDefinitivo(true);
		 
		return medidaAlternaDTO;
	}

	public static MedidaAlterna transformarMedidaAlterna(MedidaAlternaDTO medidaAlternaDTO){
		MedidaAlterna medidaAlterna ;//= new MedidaAlterna();
		
		//Datos de la Medida - Este Casting 'hacia abajo' es posible porq en el método se crea la instancia adecuada. 
		medidaAlterna  = (MedidaAlterna)MedidaTransformer.transformarMedida(medidaAlternaDTO);;
		
		medidaAlterna.setAnios(medidaAlternaDTO.getAnios());
		medidaAlterna.setMeses(medidaAlternaDTO.getMeses());
		
		return medidaAlterna;
	}
}
