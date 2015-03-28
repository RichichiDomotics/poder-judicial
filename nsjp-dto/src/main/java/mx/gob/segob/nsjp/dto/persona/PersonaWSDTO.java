/**
*
* Nombre del Programa : PersonaWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de una Persona.                      
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

package mx.gob.segob.nsjp.dto.persona;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de una Persona
 * @author GustavoBP
 * @version 1.0
 */
public class PersonaWSDTO extends ElementoWSDTO {

    private Long esVivo;
    private String folioIdentificacion;
    private Long valorIdIdentificaion;
    private DomicilioWSDTO domicilio;
    private DomicilioWSDTO domicilioNotificacion;
    private List<NombreDemograficoWSDTO> nombresDemograficos = new ArrayList<NombreDemograficoWSDTO>();
    private List<TelefonoWSDTO> telefonos = new ArrayList<TelefonoWSDTO>();
    private List<CorreoElectronicoWSDTO> correos = new ArrayList<CorreoElectronicoWSDTO>();

    /**
     * @param esVivo
     * @param folioIdentificacion
     * @param valorIdIdentificaion
     * @param nombresDemograficoDTO
     * @param mediosDeContactoDTO
     */
    public PersonaWSDTO(Long esVivo, String folioIdentificacion,
            Long valorIdIdentificaion,
            List<NombreDemograficoWSDTO> nombresDemograficoWSDTO) {
        super();
        this.esVivo = esVivo;
        this.folioIdentificacion = folioIdentificacion;
        this.valorIdIdentificaion = valorIdIdentificaion;
        this.nombresDemograficos = nombresDemograficoWSDTO;
    }
    /**
     * Regresa el nombre de la persona.
     * 
     * @return El nombre completo (nombre + apellido patarno + apellido materno)
     *         del unico nombre demografico, si tiene más de uno busca el
     *         verdadero ó <code>null</code> en caso de no tener ningún nombre
     *         demografico.
     */
    public String getNombreCompleto() {
        if (this.nombresDemograficos != null
                && !this.nombresDemograficos.isEmpty()) {
            if (this.nombresDemograficos.size() == 1) {
                return this.nombresDemograficos.get(0).getNombreCompleto();
            } else {
                for (NombreDemograficoWSDTO intento : this.nombresDemograficos) {
                    if (intento.getEsVerdadero() != null
                            && intento.getEsVerdadero().booleanValue()) {
                        return intento.getNombreCompleto();
                    }
                }
            }
        }
        return null;
    }
    
    public NombreDemograficoWSDTO getPrimerNombreDemografico(){
    	 if (this.nombresDemograficos != null
                 && !this.nombresDemograficos.isEmpty()) {
             if (this.nombresDemograficos.size() == 1) {
                 return this.nombresDemograficos.get(0);
             } else {
                 for (NombreDemograficoWSDTO intento : this.nombresDemograficos) {
                     if (intento.getEsVerdadero() != null
                             && intento.getEsVerdadero().booleanValue()) {
                         return intento;
                     }
                 }
             }
         }
         return null;
    }

    /**
     * 
     * @param nombre
     */
    public void addNombreDemografico(NombreDemograficoWSDTO nombre) {
        if (nombresDemograficos == null) {
            nombresDemograficos = new ArrayList<NombreDemograficoWSDTO>();
        }
        nombresDemograficos.add(nombre);
    }

    /**
	 * 
	 */
    public PersonaWSDTO() {
        super();
    }

	/**
     * @return the esVivo
     */
    public Long getEsVivo() {
        return esVivo;
    }

    /**
     * @param esVivo
     *            the esVivo to set
     */
    public void setEsVivo(Long esVivo) {
        this.esVivo = esVivo;
    }

    /**
     * Método de acceso al campo folioIdentificacion.
     * 
     * @return El valor del campo folioIdentificacion
     */
    public String getFolioIdentificacion() {
        return folioIdentificacion;
    }

    /**
     * Asigna el valor al campo folioIdentificacion.
     * 
     * @param folioIdentificacion
     *            el valor folioIdentificacion a asignar
     */
    public void setFolioIdentificacion(String folioIdentificacion) {
        this.folioIdentificacion = folioIdentificacion;
    }

    /**
     * Método de acceso al campo valorIdIdentificaion.
     * 
     * @return El valor del campo valorIdIdentificaion
     */
    public Long getValorIdIdentificaion() {
        return valorIdIdentificaion;
    }

    /**
     * Asigna el valor al campo valorIdIdentificaion.
     * 
     * @param valorIdIdentificaion
     *            el valor valorIdIdentificaion a asignar
     */
    public void setValorIdIdentificaion(Long valorIdIdentificaion) {
        this.valorIdIdentificaion = valorIdIdentificaion;
    }

    /**
     * Método de acceso al campo nombresDemografico.
     * 
     * @return El valor del campo nombresDemografico
     */
    public List<NombreDemograficoWSDTO> getNombresDemograficos() {
        return nombresDemograficos;
    }

    /**
     * Asigna el valor al campo nombresDemografico.
     * 
     * @param nombresDemografico
     *            el valor nombresDemografico a asignar
     */
    public void setNombresDemograficos(
            List<NombreDemograficoWSDTO> nombresDemografico) {
        this.nombresDemograficos = nombresDemografico;
    }

    /**
     * Método de acceso al campo telefonos.
     * 
     * @return El valor del campo telefonos
     */
    public List<TelefonoWSDTO> getTelefonos() {
        return telefonos;
    }

    /**
     * Asigna el valor al campo telefonos.
     * 
     * @param telefonos
     *            el valor telefonos a asignar
     */
    public void setTelefonos(List<TelefonoWSDTO> telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * Método de acceso al campo correos.
     * 
     * @return El valor del campo correos
     */
    public List<CorreoElectronicoWSDTO> getCorreos() {
        return correos;
    }

    /**
     * Asigna el valor al campo correos.
     * 
     * @param correos
     *            el valor correos a asignar
     */
    public void setCorreos(List<CorreoElectronicoWSDTO> correos) {
        this.correos = correos;
    }

    /**
     * MÃ©todo de acceso al campo domicilio.
     * 
     * @return El valor del campo domicilio
     */
    public DomicilioWSDTO getDomicilio() {
        return domicilio;
    }

    /**
     * Asigna el valor al campo domicilio.
     * 
     * @param domicilio
     *            el valor domicilio a asignar
     */
    public void setDomicilio(DomicilioWSDTO domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * MÃ©todo de acceso al campo domicilioNotificacion.
     * 
     * @return El valor del campo domicilioNotificacion
     */
    public DomicilioWSDTO getDomicilioNotificacion() {
        return domicilioNotificacion;
    }

    /**
     * Asigna el valor al campo domicilioNotificacion.
     * 
     * @param domicilioNotificacion
     *            el valor domicilioNotificacion a asignar
     */
    public void setDomicilioNotificacion(DomicilioWSDTO domicilioNotificacion) {
        this.domicilioNotificacion = domicilioNotificacion;
    }
}
