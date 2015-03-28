/**
* Nombre del Programa : ConsultarElementosXIdExpedienteCatRelacionImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Clase de prueba de servicios relacionados a consultar los elementos q estan relacionados entre si.
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
package mx.gob.segob.nsjp.service.test.relacion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXIdExpedienteCatRelacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de prueba de servicios relacionados a consultar los elementos q estan relacionados
 * entre si.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarElementosXIdExpedienteCatRelacionServiceImplTest 
	extends BaseTestServicios<ConsultarElementosXIdExpedienteCatRelacionService> {

	/*id Sujeto   Complemento
	 * 1 Persona - Persona 
	 * 2 Persona - Objeto 
	 * 3 Persona - Lugar 
	 * 4 Persona - Organización 
	 * 5 Objeto - Objeto 
	 * 6 Objeto - Lugar 
	 * 7 Objeto - Organización 
	 * 8 Lugar - Lugar 
	 * 9 Organización - Lugar 
	 * 10 Organización - Organización
	 */
	
	public void testConsultarElementosXIdExpediente(){
		//Long idExpediente = 35L;
		String numeroExpediente = "NSJYUCFG010022012334VA";
		logger.info("Probando el servicio de: testConsultarElementosXIdExpediente");
		try{
	        List<RelacionDTO> relacionesDto = service.consultarElementosXIdExpediente(numeroExpediente);
	        assertNotNull(" ElementosDto", relacionesDto);
            logger.debug(" ElementosDto.size() = " + relacionesDto.size());
            imprimirRelaciones(relacionesDto);
            
    	} catch (NSJPNegocioException ex) {
            fail("Ocurrio una excepcion al ejecutar el test consultarElementosXIdExpedienteCatRelacion");
        }
		
	}
	
	    public void testConsultarElementosXIdExpedienteCatRelacion(){
	    	
	    	logger.info("Probando el servicio de: testConsultarElementosXIdExpedienteCatRelacion");
	        //Persona 1 Organizacion 4  
//	    	Long idExpediente = 891L;
	    	//Objeto 5
	    	Long idExpediente = 35L;
	    	
	        Long idCatCategoriaRelacion = 1L;
	        Boolean esSujeto=false;
	        
	    	try{
		        List<ElementoDTO> elementosDto = service.consultarElementosXIdExpedienteCatRelacion(idExpediente, idCatCategoriaRelacion, esSujeto);
		        assertNotNull(" ElementosDto", elementosDto);
	            logger.debug(" ElementosDto.size() = " + elementosDto.size());
	            for (ElementoDTO elementoDTO : elementosDto) {
	            	logger.info("");
					logger.info("ElementoID:"+elementoDTO.getValorIdElemento() );
					logger.info("Calidad:"+elementoDTO.getCalidadDTO().getValorIdCalidad() );
//					logger.info("Calidad:"+elementoDTO.getCalidadDTO().getValorIdCalidad().getValor() );
//					logger.info("Calidad:"+elementoDTO.getCalidadDTO().getValorIdCalidad().getNombreCampo() );
					logger.info("Calidad:"+elementoDTO.getCalidadDTO().getValorIdCalidad().getIdCampo() );
					logger.info("Calidad:"+elementoDTO.getCalidadDTO().getCalidadId() );
					logger.info("StrFechaCreacion:"+elementoDTO.getStrFechaCreacion());
					logger.info("FechaCreacionElemento:"+elementoDTO.getFechaCreacionElemento() );
					logger.info("Usuario:"+elementoDTO.getUsuario());
					logger.info("Elemento:"+elementoDTO);
				}
	    	} catch (NSJPNegocioException ex) {
	            fail("Ocurrio una excepcion al ejecutar el test consultarElementosXIdExpedienteCatRelacion");
	        }
	    }
	    
	 public void _testRegulares(){
		 logger.info("Valor: "+  Relaciones.REPRESENTANTE_LEGAL.ordinal());
	  //Prueba de expresion Regular
    	String cadena = "Persona - Persona";// - Persona";
    	logger.info(cadena.indexOf('-') );
    	logger.info(cadena.matches("\\w+-\\w+") );
    	logger.info(cadena.replace(" ", "").matches("\\w+-\\w+") );
    	logger.info(cadena.trim().matches("\\w+-\\w+") );
    	logger.info(cadena.matches("\\S-\\S") );
    }
	 
	 private void imprimirRelaciones(List<RelacionDTO> relacionesDTO){
         for (RelacionDTO relacionDTO : relacionesDTO) {
         	logger.info("");
				logger.info("Relacion - RelacionId:"+relacionDTO.getRelacionId());
				logger.info("Relacion - TipoRelacion:"+relacionDTO.getTipoRelacion() +  " -"+ Relaciones.values()[relacionDTO.getTipoRelacion() ]);
				logger.info("Relacion - ElementoByComplementoId:"+relacionDTO.getElementoByComplementoId());
				logger.info("Relacion - ElementoBySujetoId:"+relacionDTO.getElementoBySujetoId());
				if(relacionDTO.getCatRelacion()!=null){
					logger.info("Relacion - CatRelacion():"+relacionDTO.getCatRelacion().getDescripcionRelacion());
					logger.info("Relacion - CatRelacion():"+relacionDTO.getCatRelacion().getDesCategoriaRelacion());
				}
				if(relacionDTO.getElementoBySujetoId()!= null)
					logger.info(" Descripción Sujeto: " +relacionDTO.getElementoBySujetoId().getStrDescripcionRelacionarElemento());
				if(relacionDTO.getElementoByComplementoId()!= null)
					logger.info(" Descripción Complemento: " +relacionDTO.getElementoByComplementoId().getStrDescripcionRelacionarElemento());
				
			}
	   }
	 
	 
	 

	   public void consultarRelacionesByComplementoIdAndTipoRelacion(){
	    	
	    	logger.info("Probando el servicio de: testConsultarElementosXIdExpedienteCatRelacion");	
	    	logger.info("Abogado:" + Relaciones.ABOGADO.ordinal());	

	    	try{
		        List<RelacionDTO> elementosDto = service.consultarRelacionesByComplementoIdAndTipoRelacion(3L,(long) Relaciones.ABOGADO.ordinal());
		        assertNotNull(" ElementosDto", elementosDto);
	            logger.debug(" ElementosDto.size() = " + elementosDto.size());
	            for (RelacionDTO elementoDTO : elementosDto) {
	            	logger.info("ElementoID:"+elementoDTO.getRelacionId());					
	            	logger.info("ID Sujeto:" + elementoDTO.getElementoBySujetoId());					
					logger.info("Nombre Sujeto:" + elementoDTO.getElementoBySujetoId());					
				}
	    	} catch (NSJPNegocioException ex) {
	            fail("Ocurrio una excepcion al ejecutar el test consultarElementosXIdExpedienteCatRelacion");
	        }
	    }
}
