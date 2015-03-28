/**
* Nombre del Programa : OrganizacionTransformer.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 27/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Integración xxxxxxxxxxx                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.service.organizacion.impl.transform;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

/**
 * Describir el objetivo de la clase
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class OrganizacionTransformer {
	
	/**
	 * 
	 * @param organizacion
	 * @return
	 */
	public static OrganizacionDTO transformarOrganizacion(Organizacion organizacion){
		if(organizacion == null){
			return null;
		}
		OrganizacionDTO organizacionDto = new OrganizacionDTO();
		CalidadDTO calidadDto = new CalidadDTO();
		Calidad calidad = new Calidad();
		
		
		organizacionDto.setElementoId(organizacion.getElementoId());
		organizacionDto.setAreaDeInfluencia(organizacion.getAreaDeInfluencia());
		organizacionDto.setDireccionInternet(organizacion.getDireccionInternet());
		//organizacionDto.setDomicilioDTO(organizacion.get);
		organizacionDto.setExpedienteDTO(ExpedienteTransformer.transformaExpediente(organizacion.getExpediente()));
		organizacionDto.setFechaCreacionElemento(organizacion.getFechaCreacionElemento());
		organizacionDto.setGiro(organizacion.getGiro());
		organizacionDto.setNombreCorto(organizacion.getNombreCorto());
		organizacionDto.setNombreOrganizacion(organizacion.getNombreOrganizacion());
		organizacionDto.setNumeroActaConstitutiva(organizacion.getNumeroActaConstitutiva());
		organizacionDto.setPropositoCiberespacio(organizacion.getPropositoCiberespacio());
		organizacionDto.setRfc(organizacion.getRfc());
		organizacionDto.setTipoCiberespacio(organizacion.getTipoCiberespacio());
		organizacionDto.setDescripcionDelictiva(organizacion.getDescripcionDelictiva());
		
		if( organizacion.getValorByComunidadVirtualVal()!= null && organizacion.getValorByComunidadVirtualVal().getValorId()!= null ) 
			organizacionDto.setValorByComunidadVirtualVal(new ValorDTO(organizacion.getValorByComunidadVirtualVal().getValorId()));
		if( organizacion.getValorByOrganizacionFormalVal()!= null && organizacion.getValorByOrganizacionFormalVal().getValorId()!= null )
			organizacionDto.setValorByOrganizacionFormalVal(new ValorDTO(organizacion.getValorByOrganizacionFormalVal().getValorId()));  
		if( organizacion.getValorBySectorGubernamentalVal()!= null && organizacion.getValorBySectorGubernamentalVal().getValorId()!= null )
			organizacionDto.setValorBySectorGubernamentalVal(new ValorDTO(organizacion.getValorBySectorGubernamentalVal().getValorId()));
		if( organizacion.getValorByTipoOrganizacionVal()!= null && organizacion.getValorByTipoOrganizacionVal().getValorId()!= null )
			organizacionDto.setValorByTipoOrganizacionVal(new ValorDTO(organizacion.getValorByTipoOrganizacionVal().getValorId()));		
    	
		if(organizacion.getCalidad()!=null && organizacion.getCalidad().getTipoCalidad()!=null) {
			calidad = organizacion.getCalidad();
			
			calidadDto.setCalidadId(calidad.getCalidadId());
			calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad().getValorId()));
			calidadDto.setDescripcionEstadoFisico(calidad.getDescripcionEstadoFisico());				
		}
		
					
		organizacionDto.setCalidadDTO(calidadDto);

		
		return organizacionDto;
	}
	
	/**
     * 
     * @param organizacion
     * @return
     */
    public static OrganizacionDTO transformarOrganizacionBasico(Organizacion organizacion){
        if(organizacion == null){
            return null;
        }
        OrganizacionDTO organizacionDto = new OrganizacionDTO();
        CalidadDTO calidadDto = new CalidadDTO();
        Calidad calidad = new Calidad();
        
        
        organizacionDto.setElementoId(organizacion.getElementoId());
        organizacionDto.setAreaDeInfluencia(organizacion.getAreaDeInfluencia());
        organizacionDto.setDireccionInternet(organizacion.getDireccionInternet());
        //organizacionDto.setDomicilioDTO(organizacion.get);
        organizacionDto.setExpedienteDTO(ExpedienteTransformer.transformaExpediente(organizacion.getExpediente()));
        organizacionDto.setFechaCreacionElemento(organizacion.getFechaCreacionElemento());
        organizacionDto.setGiro(organizacion.getGiro());
        organizacionDto.setNombreCorto(organizacion.getNombreCorto());
        organizacionDto.setNombreOrganizacion(organizacion.getNombreOrganizacion());
        organizacionDto.setNumeroActaConstitutiva(organizacion.getNumeroActaConstitutiva());
        organizacionDto.setPropositoCiberespacio(organizacion.getPropositoCiberespacio());
        organizacionDto.setRfc(organizacion.getRfc());
        organizacionDto.setTipoCiberespacio(organizacion.getTipoCiberespacio());
        organizacionDto.setDescripcionDelictiva(organizacion.getDescripcionDelictiva());
        
        if(organizacion.getCalidad()!=null && organizacion.getCalidad().getTipoCalidad()!=null) {
            calidad = organizacion.getCalidad();
            calidadDto.setCalidadId(calidad.getCalidadId());
            calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad().getValorId()));
            calidadDto.setDescripcionEstadoFisico(calidad.getDescripcionEstadoFisico());                
        }
        
                    
        organizacionDto.setCalidadDTO(calidadDto);

        
        return organizacionDto;
    }
    
	
	/**
	 * 
	 * @param organizacion
	 * @return
	 */
	public static Organizacion transformarOrganizacion(OrganizacionDTO organizacionDTO){
		Organizacion organizacion = new Organizacion();
		Calidad calidad = new Calidad();
	
		organizacion.setNombreOrganizacion(organizacionDTO.getNombreOrganizacion());
    	organizacion.setNombreCorto(organizacionDTO.getNombreCorto());
    	organizacion.setRfc(organizacionDTO.getRfc());
    	organizacion.setGiro(organizacionDTO.getGiro());
    	organizacion.setNumeroActaConstitutiva(organizacionDTO.getNumeroActaConstitutiva());
    	organizacion.setAreaDeInfluencia(organizacionDTO.getAreaDeInfluencia());
    	organizacion.setDireccionInternet(organizacionDTO.getDireccionInternet());
    	organizacion.setTipoCiberespacio(organizacionDTO.getTipoCiberespacio());
    	organizacion.setPropositoCiberespacio(organizacionDTO.getPropositoCiberespacio());
    	organizacion.setDescripcionDelictiva(organizacionDTO.getDescripcionDelictiva());
    	
    	if(organizacionDTO.getValorByComunidadVirtualVal()!=null && organizacionDTO.getValorByComunidadVirtualVal().getIdCampo()!=null)
    		organizacion.setValorByComunidadVirtualVal(new Valor(organizacionDTO.getValorByComunidadVirtualVal().getIdCampo()));        	
    	if (organizacionDTO.getValorByOrganizacionFormalVal()!=null &&organizacionDTO.getValorByOrganizacionFormalVal().getIdCampo()!=null)
    		organizacion.setValorByOrganizacionFormalVal(new Valor(organizacionDTO.getValorByOrganizacionFormalVal().getIdCampo()));
    	if (organizacionDTO.getValorBySectorGubernamentalVal()!=null &&organizacionDTO.getValorBySectorGubernamentalVal().getIdCampo()!=null)
    		organizacion.setValorBySectorGubernamentalVal(new Valor(organizacionDTO.getValorBySectorGubernamentalVal().getIdCampo()));
    	if (organizacionDTO.getValorByTipoOrganizacionVal()!=null && organizacionDTO.getValorByTipoOrganizacionVal().getIdCampo()!=null)
    		organizacion.setValorByTipoOrganizacionVal(new Valor(organizacionDTO.getValorByTipoOrganizacionVal().getIdCampo()));
		
    	//Atributos elemento        	 
    	organizacion.setTipoElemento(new Valor(Elementos.ORGANIZACION.getValorId()));    	
    	organizacion.setFechaCreacionElemento(organizacionDTO.getFechaCreacionElemento()!=null?organizacionDTO.getFechaCreacionElemento():new Date());
    	if(organizacionDTO.getExpedienteDTO()!=null)
    		organizacion.setExpediente(new Expediente(organizacionDTO.getExpedienteDTO().getExpedienteId()));
    	
    	if(organizacionDTO.getCalidadDTO()!=null && organizacionDTO.getCalidadDTO().getCalidades()!=null) {
    		calidad.setDescripcionEstadoFisico(organizacionDTO.getCalidadDTO().getDescripcionEstadoFisico());
    		calidad.setTipoCalidad(new Valor(organizacionDTO.getCalidadDTO().getCalidades().getValorId()));
    		organizacion.setCalidad(calidad);
    	}    		
    	
		return organizacion;
	}

	public static Organizacion transformarOrganizacionUpdate(
			Organizacion organizacion, Organizacion organizacionMod) {
		
		organizacion.setNombreOrganizacion(organizacionMod.getNombreOrganizacion());
    	organizacion.setNombreCorto(organizacionMod.getNombreCorto());
    	organizacion.setRfc(organizacionMod.getRfc());
    	organizacion.setGiro(organizacionMod.getGiro());
    	organizacion.setNumeroActaConstitutiva(organizacionMod.getNumeroActaConstitutiva());
    	organizacion.setAreaDeInfluencia(organizacionMod.getAreaDeInfluencia());
    	organizacion.setDireccionInternet(organizacionMod.getDireccionInternet());
    	organizacion.setTipoCiberespacio(organizacionMod.getTipoCiberespacio());
    	organizacion.setPropositoCiberespacio(organizacionMod.getPropositoCiberespacio());
    	organizacion.setDescripcionDelictiva(organizacionMod.getDescripcionDelictiva());    	    	
    	organizacion.setValorByComunidadVirtualVal(organizacionMod.getValorByComunidadVirtualVal());        	    	
    	organizacion.setValorByOrganizacionFormalVal(organizacionMod.getValorByOrganizacionFormalVal());    	
    	organizacion.setValorBySectorGubernamentalVal(organizacionMod.getValorBySectorGubernamentalVal());    	
    	organizacion.setValorByTipoOrganizacionVal(organizacionMod.getValorByTipoOrganizacionVal());	
    	
    	organizacion.setEsActivo(organizacionMod.getEsActivo());
    	
    	//Atributos elemento        	 
    	organizacion.setTipoElemento(organizacionMod.getTipoElemento());    	
    	organizacion.setFechaCreacionElemento(organizacionMod.getFechaCreacionElemento());    	
    	organizacion.setExpediente(organizacionMod.getExpediente());    	      	
    	organizacion.setCalidad(organizacionMod.getCalidad());
    	 
		
		return organizacion;
	}

}
