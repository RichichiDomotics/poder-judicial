/**
* Nombre del Programa : DatoPruebaTransformer.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/09/2011
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
package mx.gob.segob.nsjp.service.prueba.impl.transformer;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionPruebaInvolucradoDTO;
import mx.gob.segob.nsjp.model.DatoPrueba;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.RelacionDatoMedioPrueba;
import mx.gob.segob.nsjp.model.RelacionPruebaInvolucrado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.elemento.impl.ElementoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

/**
 * Clase para convertir objeto entidad: DatoPrueba a DTO: DatoPruebaDTO, y viseversa.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class DatoPruebaTransformer {
	
	/**
	 * Transforma un objeto de DatoPruebaDTO a una entidad de DatoPrueba; 
	 * @param datoPruebaDTO
	 * @return
	 */
	public static DatoPrueba transformarDatoPrueba(DatoPruebaDTO datoPruebaDTO){
		DatoPrueba datoPrueba = DatoPruebaTransformer.transformarDatoPruebaUpdate(null, datoPruebaDTO);
		return datoPrueba;
	}
	
	/**
	 * Transforma un objeto DatoPruebaDTO a una entidad DatoPrueba.  
	 * En caso de que el parámetro datoPrueba sea null, es una
	 * transformacion normal.
	 * Si es diferente de null, se trata de un objeto de fue 
	 * extraido de BD y contiene referencias a otras entidades. El 
	 * objetivo es setear los valores que viene de la interfaz 
	 * (presentación) y ser actualizado en BD. 
	 * @param datoPrueba
	 * @param datoPruebaDTO
	 * @return
	 */
	public static DatoPrueba transformarDatoPruebaUpdate(DatoPrueba datoPrueba, DatoPruebaDTO datoPruebaDTO){
		if(datoPrueba == null)
			datoPrueba = new DatoPrueba();
		
		if(datoPruebaDTO.getDatoPruebaId()!= null && datoPruebaDTO.getDatoPruebaId()>0)
			datoPrueba.setDatoPruebaId(datoPruebaDTO.getDatoPruebaId());
		
		datoPrueba.setNombreDato(datoPruebaDTO.getNombreDato());
		datoPrueba.setNumeroIdentificacion(datoPruebaDTO.getNumeroIdentificacion());
		datoPrueba.setDescripcion(datoPruebaDTO.getDescripcion());
		datoPrueba.setFolioCadenaCustodia(datoPruebaDTO.getFolioCadenaCustodia());
		//Se tienen tres posibles estados: null(Registrado), true(Aceptado) o false(Rechazado);
		datoPrueba.setEsAceptado( datoPruebaDTO.getEsAceptado());
		
		datoPrueba.setTiempoCancelacion(datoPruebaDTO.getTiempoCancelacion());
		
		if( datoPruebaDTO.getElementoPrueba()!= null && datoPruebaDTO.getElementoPrueba().getElementoId()!= null)
			datoPrueba.setElementoPrueba(new Elemento(datoPruebaDTO.getElementoPrueba().getElementoId()));
		
		if(datoPruebaDTO.getMotivoCancelacion()!= null && datoPruebaDTO.getMotivoCancelacion().getIdCampo()!= null)
				datoPrueba.setMotivoCancelacion(new Valor( datoPruebaDTO.getMotivoCancelacion().getIdCampo()));
		
		if(datoPruebaDTO.getExpediente()!= null && datoPruebaDTO.getExpediente().getExpedienteId()!= null)
			datoPrueba.setExpediente(new Expediente(datoPruebaDTO.getExpediente().getExpedienteId()));
		
		return datoPrueba;
	}

	public static DatoPruebaDTO transformarDatoPruebaBasico( DatoPrueba datoPrueba){
		DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
		if(datoPrueba==null )
			return datoPruebaDTO;
		
		datoPruebaDTO.setDatoPruebaId(datoPrueba.getDatoPruebaId());
		datoPruebaDTO.setNombreDato(datoPrueba.getNombreDato());
		datoPruebaDTO.setNumeroIdentificacion(datoPrueba.getNumeroIdentificacion());
		datoPruebaDTO.setDescripcion(datoPrueba.getDescripcion());
		datoPruebaDTO.setFolioCadenaCustodia(datoPrueba.getFolioCadenaCustodia());

		datoPruebaDTO.setEsAceptado( datoPrueba.getEsAceptado());
		datoPruebaDTO.setTiempoCancelacion(datoPrueba.getTiempoCancelacion());
		if(datoPrueba.getMotivoCancelacion()!= null)
			datoPruebaDTO.setMotivoCancelacion(new ValorDTO( 
					datoPrueba.getMotivoCancelacion().getValorId(), 
					datoPrueba.getMotivoCancelacion().getValor()));
		if(datoPrueba.getExpediente()!= null )
			datoPruebaDTO.setExpediente( ExpedienteTransformer.transformarExpedienteBasico(datoPrueba.getExpediente()));
		
		return datoPruebaDTO;
	}
	
	public static DatoPruebaDTO transformarDatoPruebaCompleto( DatoPrueba datoPrueba){
		DatoPruebaDTO dto = transformarDatoPruebaBasico(datoPrueba);
		
		if( datoPrueba.getElementoPrueba()!= null)
			dto.setElementoPrueba( ElementoTransformer.transformarElementoCualquiera(datoPrueba.getElementoPrueba()));
		
		if( datoPrueba.getRelacionesPruebaInvolucrado()!= null && !datoPrueba.getRelacionesPruebaInvolucrado().isEmpty()){
			List<RelacionPruebaInvolucradoDTO> relacionesInvDTO=new ArrayList<RelacionPruebaInvolucradoDTO>();
			 for (RelacionPruebaInvolucrado relInvol : datoPrueba.getRelacionesPruebaInvolucrado()) {
				 relacionesInvDTO.add(RelacionPruebaInvolucradoTransformer.transformarRelacionInvolucrado(relInvol));
			 }
			 dto.setRelacionesPruebaInvolucrado(relacionesInvDTO);
		}

		if( datoPrueba.getRelacionesDatosMedioPrueba()!= null && !datoPrueba.getRelacionesDatosMedioPrueba().isEmpty()){
			List<RelacionDatoMedioPruebaDTO> relacionesDTO=new ArrayList<RelacionDatoMedioPruebaDTO>();
			for (RelacionDatoMedioPrueba relacion : datoPrueba.getRelacionesDatosMedioPrueba()) {
				relacionesDTO.add(RelacionDatoMedioPruebaTransformer.transformarRelacionDatoMedio(relacion));
			}
			dto.setRelacionesDatosMedioPrueba(relacionesDTO); 
		}
		
		if(datoPrueba.getDocumentoPrueba()!= null)
			dto.setDocumentoPrueba(DocumentoTransformer.transformarDocumento(datoPrueba.getDocumentoPrueba()));
		
		return dto;
	}
}
