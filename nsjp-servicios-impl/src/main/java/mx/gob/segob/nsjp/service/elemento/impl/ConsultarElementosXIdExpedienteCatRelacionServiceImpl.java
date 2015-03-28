/**
* Nombre del Programa : ConsultarElementosXIdExpedienteCatRelacionServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.service.elemento.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.elemento.TipoElemento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dao.relacion.CatCategoriaRelacionDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.model.Aeronave;
import mx.gob.segob.nsjp.model.Animal;
import mx.gob.segob.nsjp.model.AreaGeografica;
import mx.gob.segob.nsjp.model.Arma;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;
import mx.gob.segob.nsjp.model.DocumentoOficial;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Embarcacion;
import mx.gob.segob.nsjp.model.EquipoComputo;
import mx.gob.segob.nsjp.model.EspacioAereo;
import mx.gob.segob.nsjp.model.EspacioMaritimo;
import mx.gob.segob.nsjp.model.Explosivo;
import mx.gob.segob.nsjp.model.Joya;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Numerario;
import mx.gob.segob.nsjp.model.ObraArte;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.PuntoCarretero;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.model.Sustancia;
import mx.gob.segob.nsjp.model.Vegetal;
import mx.gob.segob.nsjp.model.Vehiculo;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXIdExpedienteCatRelacionService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.relacion.impl.transform.RelacionTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarElementosXIdExpedienteCatRelacionServiceImpl 
			implements ConsultarElementosXIdExpedienteCatRelacionService{
    /**
     * Logger de la clase.
     */
   private final static Logger logger = Logger
           .getLogger(ConsultarElementosXActividadServiceImpl.class);

   @Autowired
   private CatCategoriaRelacionDAO catCategoriaRelacionDAO;
   @Autowired
   private ElementoDAO elementoDAO;
   @Autowired
   private NombreDemograficoDAO nombreDemograficoDAO;
   @Autowired
   private OrganizacionDAO organizacionDAO;   
   @Autowired
   private RelacionDAO relacionDAO;
   @Autowired
   private BuscarExpedienteService buscarExpedienteService;
   
   private String SEPARADOR = " - "; 
   /**
    * {@inheritDoc}
    */
   @Override
   public List<ElementoDTO> consultarElementosXIdExpedienteCatRelacion(Long idExpediente, Long idCatCategoriaRelacion, Boolean esSujeto)
   		throws NSJPNegocioException{
	   
	   List<ElementoDTO> elementosDto = new ArrayList<ElementoDTO>();
	 	   
	   if (idExpediente == null || 
			   idExpediente <= 0 ||
			   idCatCategoriaRelacion == null || 
			   idCatCategoriaRelacion <= 0 ||
			   esSujeto == null) 
           throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	   
	   //Obtener la Descripcion de la Categoria Relacion.
	   CatCategoriaRelacion	catCategoriaRelacion = catCategoriaRelacionDAO.read(idCatCategoriaRelacion);
	   
	   logger.info("**** catCategoriaRelacion:"+ catCategoriaRelacion +" ***");
	   
	   String descripcion = catCategoriaRelacion.getDesCategoriaRelacion();
	   if (descripcion == null || descripcion.trim().isEmpty() || 
			   descripcion.indexOf('-')<0 )
		   throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	   
	   //Se divide la descripcion en dos: Sujeto - Complemento
	   descripcion = descripcion.replace(" ", "");
	   String cadenas[] = descripcion.split("-");
	   
	   String sujetoOcomplemento = (esSujeto?cadenas[0]:cadenas[1]); 
	  
	   //consultar los elementos de acuerdo al tipo de valor
	   List<Elemento> elementos = elementoDAO.consultarElementoXIdExpedienteTipoValor(idExpediente, sujetoOcomplemento);
	   
	   elementosDto = generarCadenaSeparador(elementos);
	   
	   return elementosDto;
   }
   
	
      
   public List<RelacionDTO> consultarElementosXIdExpediente(String numeroExpediente)
		throws NSJPNegocioException {

	   if (numeroExpediente == null || numeroExpediente.trim().isEmpty()) 
           throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

 	   List<RelacionDTO> relacionesDTOTotal = new ArrayList<RelacionDTO>();

 	   //Obtener el Id Expediente
 	  ExpedienteDTO expedienteDTO = buscarExpedienteService.obtenerExpedientePorNumeroExpediente(numeroExpediente);
 	  
 	  if(expedienteDTO==null || expedienteDTO.getExpedienteId()==null )
 		 throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
 	   
 	   //Consultar los elementos por Expediente
	   List<Elemento> elementos = elementoDAO.findElementosByExpedienteId(expedienteDTO.getExpedienteId());
	   logger.info("********  TOTAL Elementos:"+ elementos.size() + "********");
	   
	   //Obtener los Id de los elementos para posteriormente obtener las relaciones 
	   //que presenten cada uno de los elementos, sin repetirse tanto en sujeto como en elemento
	   List<Long> idElementos = new ArrayList<Long>();
	   for (Elemento elemento : elementos) 
		   idElementos.add(elemento.getElementoId());

	   List<Relacion> relaciones  = new ArrayList<Relacion>();;
	   
	   //Consultar las relaciones de los elementos asociados al expediente
	   if(!idElementos.isEmpty())
		   relaciones = relacionDAO.consultarRelacionByIdElementoAndTipoRelacion(idElementos, TipoRelacion.EXPLICITA.getValorId());

	   String cadena = "";
	   //Recorrer las relaciones para determinar la cadena de los elementos
	   for (Relacion relacion : relaciones) {
		   
		   //Verifica si los dos elementos son activos
		   if(relacion.getElementoByComplementoId().getEsActivo() && relacion.getElementoBySujetoId().getEsActivo() ){
		   
			   RelacionDTO relacionDTO = new RelacionDTO();
			   relacionDTO =  RelacionTransformer.transformarRelacion(relacion);
			   //Obtener la descripcion del Complemento
			   cadena = generarCadenaElemento(relacion.getElementoByComplementoId());
			   relacionDTO.getElementoByComplementoId().setStrDescripcionRelacionarElemento(cadena);
			   //Obtener la descripcion del Sujeto
			   cadena = generarCadenaElemento(relacion.getElementoBySujetoId());
			   relacionDTO.getElementoBySujetoId().setStrDescripcionRelacionarElemento(cadena);
	
			   relacionesDTOTotal.add(relacionDTO);
		   }
	   }
	   logger.info("Relaciones TOTALES:"+relacionesDTOTotal.size());
	   return relacionesDTOTotal;
   }
   
   public List<RelacionDTO> consultarRelacionesByComplementoIdAndTipoRelacion(Long idComplemento, Long idCatRelacion)
	throws NSJPNegocioException {
	   
	   if (idComplemento == null || idCatRelacion == null) 
           throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	   
	 List<RelacionDTO> relacionesDTOTotal = new ArrayList<RelacionDTO>();
 
	 List<Relacion> relaciones  = relacionDAO.obtenerRelacionSimple(idComplemento, idCatRelacion); 
	  for (Relacion relacion : relaciones) {
		  RelacionDTO relacionDTO = new RelacionDTO();
		  relacionDTO =  RelacionTransformer.transformarRelacion(relacion);
		  relacionesDTOTotal.add(relacionDTO);
	  }
	  logger.info("Relaciones TOTALES:"+relacionesDTOTotal.size());
	  return relacionesDTOTotal;
	}

   

   
   private List<ElementoDTO> generarCadenaSeparador(List<Elemento> elementos) throws NSJPNegocioException{ 
		  
	   List<ElementoDTO> elementosDto = new ArrayList<ElementoDTO>();
	   
	   if (elementos == null || elementos.isEmpty()) 
		   return elementosDto;
	    
	   String cadena = "";
       for (Elemento elemento : elementos) {
    	   cadena = generarCadenaElemento(elemento);
    	   //logger.info("********  Cadena: "+cadena +" ********");    	  
    	   ElementoDTO elementoDto = ElementoTransformer.transformarElemento(elemento);
    	   elementoDto.setStrDescripcionRelacionarElemento(cadena);
           elementosDto.add(elementoDto);
       }
       
       return elementosDto;
	}


private String generarCadenaElemento(Elemento elemento){
	List<NombreDemografico> lNombreDemografico = null;
	String cadena = "";
    //| 
	   logger.info("********  "+elemento.getElementoId() +  " Calidad (" + 
			   elemento.getCalidad().getCalidadId() + "):"+elemento.getCalidad().getTipoCalidad().getValor()
	   + "TIPO DE ELEMENTO" +elemento.getTipoElemento().getValor());
	   
//	   logger.info("********  "+ elemento.getTipoElemento().getValor() +
//			   "********  "+ elemento.getTipoElemento().getValorId());

	   //Settear Calidad
	  if(elemento.getCalidad()!=null && elemento.getCalidad().getTipoCalidad()!= null)
		  cadena = elemento.getCalidad().getTipoCalidad().getValor() + SEPARADOR ;
	  
	  if ( elemento.getTipoElemento().getValorId().equals(TipoElemento.PERSONA.getValorId())  )
	  {
		  logger.info("********  PERSONA ********");
	  
		  lNombreDemografico = nombreDemograficoDAO.consutarNombresByInvolucrado(elemento.getElementoId()); 
		  if (lNombreDemografico!= null && !lNombreDemografico.isEmpty() ){
			  cadena = cadena + lNombreDemografico.get(0).getNombre() + " " 
			  		 + lNombreDemografico.get(0).getApellidoPaterno() + " "
			  		 + lNombreDemografico.get(0).getApellidoMaterno();
		  }
		  
	  }else if(elemento.getTipoElemento().getValorId().equals(TipoElemento.ORGANIZACION.getValorId())){
		  logger.info("********  ORGANIZACION ********");
		  Organizacion organizacion=  organizacionDAO.read( elemento.getElementoId() );
		  if ( organizacion != null){
			  logger.info("Nombre Organizacion" + organizacion.getNombreOrganizacion() );
			  cadena = organizacion.getNombreOrganizacion() + SEPARADOR +  cadena + " ";
			  
			  //Obtener el representante Legal
			  //BD 30	Representante Legal
//			  cadena = SEPARADOR
			  List<Relacion> relaciones  = relacionDAO.obtenerRelacionSimple(organizacion.getElementoId(), new Long(Relaciones.REPRESENTANTE_LEGAL.ordinal()));
			  //Se toma un solo representante legal
			  if(relaciones!= null && !relaciones.isEmpty()){
				  logger.info(" Representante Legal " + relaciones.get(0).getRelacionId() );
				  logger.info(" Representante Legal " + relaciones.get(0).getElementoByComplementoId().getElementoId() );
				  logger.info(" Representante Legal " + relaciones.get(0).getElementoBySujetoId().getElementoId());
				  
				  //Obtner el representante legal.
				  lNombreDemografico = nombreDemograficoDAO.consutarNombresByInvolucrado(relaciones.get(0).getElementoByComplementoId().getElementoId()); 
	    		  if (lNombreDemografico!= null && !lNombreDemografico.isEmpty() ){
	    			  cadena = cadena +  lNombreDemografico.get(0).getNombre() + " " 
	    			  		 + lNombreDemografico.get(0).getApellidoPaterno() + " "
	    			  		 + lNombreDemografico.get(0).getApellidoMaterno();
	    			  logger.info(" Calidad: " + relaciones.get(0).getElementoBySujetoId().getCalidad().getTipoCalidad().getValor());
	    		  }
			  }
		  }
	  }else if (elemento.getTipoElemento().getValorId().equals(TipoElemento.LUGAR.getValorId())){
		  logger.info("********  LUGAR ********");
		  cadena = "";
		  if(elemento instanceof Domicilio){
			  Domicilio loObjeto = (Domicilio)elemento;
			  cadena = cadena + "Domicilio" + SEPARADOR;
			  cadena = (loObjeto.getCalle() != null ? cadena + loObjeto.getCalle() + SEPARADOR:cadena);
			  cadena = (loObjeto.getNumeroExterior() != null ? cadena + loObjeto.getNumeroExterior() + SEPARADOR:cadena);
			  cadena = (loObjeto.getNumeroInterior() != null ? cadena + loObjeto.getNumeroInterior() + SEPARADOR:cadena);
			  if(loObjeto.getAsentamiento() != null){
				  cadena = (loObjeto.getAsentamiento().getNombreAsentamiento() != null ? cadena + loObjeto.getAsentamiento().getNombreAsentamiento() + SEPARADOR:cadena);
				  cadena = (loObjeto.getAsentamiento().getCodigoPostal() != null ? cadena + loObjeto.getAsentamiento().getCodigoPostal() + SEPARADOR:cadena);        			  
			  }    			 
		  }
		  if(elemento instanceof PuntoCarretero){
			  PuntoCarretero loObjeto = (PuntoCarretero)elemento;
			  cadena = cadena + "Punto Carretero" + SEPARADOR;
			  cadena = (loObjeto.getNombreCarretera() != null ? cadena + loObjeto.getNombreCarretera() + SEPARADOR:cadena);
			  cadena = (loObjeto.getKilometro() != null ? cadena + loObjeto.getKilometro() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof AreaGeografica){
			  AreaGeografica loObjeto = (AreaGeografica)elemento;   			  
			  cadena = cadena + "Área Geográfica" + SEPARADOR;
			  cadena = (loObjeto.getNombre() != null ? cadena + loObjeto.getNombre() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof EspacioMaritimo){
			  EspacioMaritimo loObjeto = (EspacioMaritimo)elemento;
			  cadena = cadena + "Espacio Marítimo" + SEPARADOR;
			  cadena = (loObjeto.getZonaCostera() != null ? cadena + loObjeto.getZonaCostera() + SEPARADOR:cadena);
			  cadena = (loObjeto.getPuerto() != null ? cadena + loObjeto.getPuerto() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof EspacioAereo){
			  EspacioAereo loObjeto = (EspacioAereo)elemento;
			  cadena = cadena + "Espacio Aéreo" + SEPARADOR;
			  cadena = (loObjeto.getLineaAerea() != null ? cadena + loObjeto.getLineaAerea() + SEPARADOR:cadena);
			  cadena = (loObjeto.getRuta() != null ? cadena + loObjeto.getRuta() + SEPARADOR:cadena);
		  }
		  if(cadena.lastIndexOf(SEPARADOR) != -1)
			  cadena = cadena.substring(0, cadena.lastIndexOf(SEPARADOR));
	  }else { 
		  logger.info("********  OBJETO ********");
		  cadena = "";
		  if(elemento instanceof Vehiculo){
			  Vehiculo loObjeto = (Vehiculo)elemento;
			  cadena = cadena + "Vehículo" + SEPARADOR;
			  cadena = (loObjeto.getValorByTipoVehiculo() != null ? cadena + loObjeto.getValorByTipoVehiculo().getValor() + SEPARADOR:cadena);
			  cadena = (loObjeto.getPlaca() != null ? cadena + loObjeto.getPlaca() + SEPARADOR:cadena);    			  
		  }
		  if(elemento instanceof EquipoComputo){
			  EquipoComputo loObjeto = (EquipoComputo)elemento;
			  cadena = cadena + "Equipo de Cómputo" + SEPARADOR;
			  cadena = (loObjeto.getTipoEquipo() != null ? cadena + loObjeto.getTipoEquipo().getValor() + SEPARADOR:cadena);    			  
		  }
		  if(elemento instanceof Arma){
			  Arma loObjeto = (Arma)elemento;   			  
			  cadena = cadena + "Arma" + SEPARADOR;
			  cadena = (loObjeto.getTipoArma() != null ? cadena + loObjeto.getTipoArma().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Explosivo){
			  Explosivo loObjeto = (Explosivo)elemento;
			  cadena = cadena + "Explosivo" + SEPARADOR;
			  cadena = (loObjeto.getTipoExplosivo() != null ? cadena + loObjeto.getTipoExplosivo().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Aeronave){
			  Aeronave loObjeto = (Aeronave)elemento;    			  
			  cadena = cadena + "Aeronave" + SEPARADOR;
			  cadena = (loObjeto.getTipoAeroNave() != null ? cadena + loObjeto.getTipoAeroNave().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Animal){
			  Animal loObjeto = (Animal)elemento;
			  cadena = cadena + "Animal" + SEPARADOR;
			  cadena = (loObjeto.getTipoAnimal() != null ? cadena + loObjeto.getTipoAnimal().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof DocumentoOficial){
			  DocumentoOficial loObjeto = (DocumentoOficial)elemento;
			  cadena = cadena + "Documento Oficial" + SEPARADOR;
			  cadena = (loObjeto.getTipoDocumento() != null ? cadena + loObjeto.getTipoDocumento().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Embarcacion){
			  Embarcacion loObjeto = (Embarcacion)elemento;
			  cadena = cadena + "Embarcación" + SEPARADOR;
			  cadena = (loObjeto.getTipoEmbarcacion() != null ? cadena + loObjeto.getTipoEmbarcacion().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Joya){
			  Joya loObjeto = (Joya)elemento;
			  cadena = cadena + "Joya" + SEPARADOR;
			  cadena = (loObjeto.getTipoJoya() != null ? cadena + loObjeto.getTipoJoya().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Numerario){
			  Numerario loObjeto = (Numerario)elemento;
			  cadena = cadena + "Numerario" + SEPARADOR;
			  cadena = (loObjeto.getMoneda() != null ? cadena + loObjeto.getMoneda() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof ObraArte){
			  ObraArte loObjeto = (ObraArte)elemento;    			  
			  cadena = cadena + "Obra de Arte" + SEPARADOR;
			  cadena = (loObjeto.getTipoObraArte() != null ? cadena + loObjeto.getTipoObraArte().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Sustancia){
			  Sustancia loObjeto = (Sustancia)elemento;
			  cadena = cadena + "Sustancia" + SEPARADOR;
			  cadena = (loObjeto.getTipoSustancia() != null ? cadena + loObjeto.getTipoSustancia().getValor() + SEPARADOR:cadena);
		  }
		  if(elemento instanceof Vegetal){
			  Vegetal loObjeto = (Vegetal)elemento;
			  cadena = cadena + "Vegetal" + SEPARADOR;
			  cadena = (loObjeto.getTipoVegetal() != null ? cadena + loObjeto.getTipoVegetal().getValor() + SEPARADOR:cadena);
		  }    		  
		  if(cadena.lastIndexOf(SEPARADOR) != -1)
			  cadena = cadena.substring(0, cadena.lastIndexOf(SEPARADOR));
	  }
	   return cadena;
	}



@Override
public String generarCadenaElemento(ElementoDTO elementoDTO) {
	Elemento elemento =ElementoTransformer.transformarElemento(elementoDTO);
	return this.generarCadenaElemento(elemento);
}
}
