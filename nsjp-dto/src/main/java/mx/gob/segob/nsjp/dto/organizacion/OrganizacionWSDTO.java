/**
* Nombre del Programa : OrganizacionWSDTO.java
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012
* Marca de cambio        : N/A
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de una Organizacion.
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
package mx.gob.segob.nsjp.dto.organizacion;

import mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoWSDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de una Organizacion.
 * @author GustavoBP
 * @version 1.0
 */
public class OrganizacionWSDTO extends ElementoWSDTO{

	private String nombreOrganizacion;
	private String nombreCorto;
	private String rfc;
	private String giro;
	private String numeroActaConstitutiva;
	private String areaDeInfluencia;
	private String direccionInternet;
	private String tipoCiberespacio;
	private String propositoCiberespacio;
	private String descripcionDelictiva;
	private Long valorBySectorGubernamentalVal;
	private Long valorByOrganizacionFormalVal;
	private Long valorByComunidadVirtualVal;
	private Long valorByTipoOrganizacionVal;	
	private DomicilioWSDTO domicilioWSDTO;
	private InvolucradoWSDTO representanteLegal;
	
	
	/**
	 * 
	 */
	public OrganizacionWSDTO() {
		super();		
	}
	/**
	 * Método de acceso al campo nombreOrganizacion.
	 * @return El valor del campo nombreOrganizacion
	 */
	public String getNombreOrganizacion() {
		return nombreOrganizacion;
	}
	/**
	 * Asigna el valor al campo nombreOrganizacion.
	 * @param nombreOrganizacion el valor nombreOrganizacion a asignar
	 */
	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
	}
	/**
	 * Método de acceso al campo nombreCorto.
	 * @return El valor del campo nombreCorto
	 */
	public String getNombreCorto() {
		return nombreCorto;
	}
	/**
	 * Asigna el valor al campo nombreCorto.
	 * @param nombreCorto el valor nombreCorto a asignar
	 */
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	/**
	 * Método de acceso al campo rfc.
	 * @return El valor del campo rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * Asigna el valor al campo rfc.
	 * @param rfc el valor rfc a asignar
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * Método de acceso al campo giro.
	 * @return El valor del campo giro
	 */
	public String getGiro() {
		return giro;
	}
	/**
	 * Asigna el valor al campo giro.
	 * @param giro el valor giro a asignar
	 */
	public void setGiro(String giro) {
		this.giro = giro;
	}
	/**
	 * Método de acceso al campo numeroActaConstitutiva.
	 * @return El valor del campo numeroActaConstitutiva
	 */
	public String getNumeroActaConstitutiva() {
		return numeroActaConstitutiva;
	}
	/**
	 * Asigna el valor al campo numeroActaConstitutiva.
	 * @param numeroActaConstitutiva el valor numeroActaConstitutiva a asignar
	 */
	public void setNumeroActaConstitutiva(String numeroActaConstitutiva) {
		this.numeroActaConstitutiva = numeroActaConstitutiva;
	}
	/**
	 * Método de acceso al campo areaDeInfluencia.
	 * @return El valor del campo areaDeInfluencia
	 */
	public String getAreaDeInfluencia() {
		return areaDeInfluencia;
	}
	/**
	 * Asigna el valor al campo areaDeInfluencia.
	 * @param areaDeInfluencia el valor areaDeInfluencia a asignar
	 */
	public void setAreaDeInfluencia(String areaDeInfluencia) {
		this.areaDeInfluencia = areaDeInfluencia;
	}
	/**
	 * Método de acceso al campo direccionInternet.
	 * @return El valor del campo direccionInternet
	 */
	public String getDireccionInternet() {
		return direccionInternet;
	}
	/**
	 * Asigna el valor al campo direccionInternet.
	 * @param direccionInternet el valor direccionInternet a asignar
	 */
	public void setDireccionInternet(String direccionInternet) {
		this.direccionInternet = direccionInternet;
	}
	/**
	 * Método de acceso al campo tipoCiberespacio.
	 * @return El valor del campo tipoCiberespacio
	 */
	public String getTipoCiberespacio() {
		return tipoCiberespacio;
	}
	/**
	 * Asigna el valor al campo tipoCiberespacio.
	 * @param tipoCiberespacio el valor tipoCiberespacio a asignar
	 */
	public void setTipoCiberespacio(String tipoCiberespacio) {
		this.tipoCiberespacio = tipoCiberespacio;
	}
	/**
	 * Método de acceso al campo propositoCiberespacio.
	 * @return El valor del campo propositoCiberespacio
	 */
	public String getPropositoCiberespacio() {
		return propositoCiberespacio;
	}
	/**
	 * Asigna el valor al campo propositoCiberespacio.
	 * @param propositoCiberespacio el valor propositoCiberespacio a asignar
	 */
	public void setPropositoCiberespacio(String propositoCiberespacio) {
		this.propositoCiberespacio = propositoCiberespacio;
	}	
	/**
	 * Método de acceso al campo domicilio.
	 * @return El valor del campo domicilio
	 */
	public DomicilioWSDTO getDomicilioWSDTO() {
		return domicilioWSDTO;
	}
	/**
	 * Asigna el valor al campo domicilio.
	 * @param domicilioWSDTO el valor domicilio a asignar
	 */
	public void setDomicilioWSDTO(DomicilioWSDTO domicilioWSDTO) {
		this.domicilioWSDTO = domicilioWSDTO;
	}
	/**
	 * Método de acceso al campo valorBySectorGubernamentalVal.
	 * @return El valor del campo valorBySectorGubernamentalVal
	 */
	public Long getValorBySectorGubernamentalVal() {
		return valorBySectorGubernamentalVal;
	}
	/**
	 * Asigna el valor al campo valorBySectorGubernamentalVal.
	 * @param valorBySectorGubernamentalVal el valor valorBySectorGubernamentalVal a asignar
	 */
	public void setValorBySectorGubernamentalVal(
			Long valorBySectorGubernamentalVal) {
		this.valorBySectorGubernamentalVal = valorBySectorGubernamentalVal;
	}
	/**
	 * Método de acceso al campo valorByOrganizacionFormalVal.
	 * @return El valor del campo valorByOrganizacionFormalVal
	 */
	public Long getValorByOrganizacionFormalVal() {
		return valorByOrganizacionFormalVal;
	}
	/**
	 * Asigna el valor al campo valorByOrganizacionFormalVal.
	 * @param valorByOrganizacionFormalVal el valor valorByOrganizacionFormalVal a asignar
	 */
	public void setValorByOrganizacionFormalVal(
			Long valorByOrganizacionFormalVal) {
		this.valorByOrganizacionFormalVal = valorByOrganizacionFormalVal;
	}
	/**
	 * Método de acceso al campo valorByComunidadVirtualVal.
	 * @return El valor del campo valorByComunidadVirtualVal
	 */
	public Long getValorByComunidadVirtualVal() {
		return valorByComunidadVirtualVal;
	}
	/**
	 * Asigna el valor al campo valorByComunidadVirtualVal.
	 * @param valorByComunidadVirtualVal el valor valorByComunidadVirtualVal a asignar
	 */
	public void setValorByComunidadVirtualVal(Long valorByComunidadVirtualVal) {
		this.valorByComunidadVirtualVal = valorByComunidadVirtualVal;
	}
	/**
	 * Método de acceso al campo valorByTipoOrganizacionVal.
	 * @return El valor del campo valorByTipoOrganizacionVal
	 */
	public Long getValorByTipoOrganizacionVal() {
		return valorByTipoOrganizacionVal;
	}
	/**
	 * Asigna el valor al campo valorByTipoOrganizacionVal.
	 * @param valorByTipoOrganizacionVal el valor valorByTipoOrganizacionVal a asignar
	 */
	public void setValorByTipoOrganizacionVal(Long valorByTipoOrganizacionVal) {
		this.valorByTipoOrganizacionVal = valorByTipoOrganizacionVal;
	}
	/**
	 * Método de acceso al campo representanteLegal.
	 * @return El valor del campo representanteLegal
	 */
	public InvolucradoWSDTO getRepresentanteLegal() {
		return representanteLegal;
	}
	/**
	 * Asigna el valor al campo representanteLegal.
	 * @param representanteLegal el valor representanteLegal a asignar
	 */
	public void setRepresentanteLegal(InvolucradoWSDTO representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	
	/**
	 * Método de acceso al campo descripcionDelictiva.
	 * @return El valor del campo descripcionDelictiva
	 */
	public String getDescripcionDelictiva() {
		return descripcionDelictiva;
	}
	/**
	 * Asigna el valor al campo descripcionDelictiva.
	 * @param descripcionDelictiva el valor descripcionDelictiva a asignar
	 */
	public void setDescripcionDelictiva(String descripcionDelictiva) {
		this.descripcionDelictiva = descripcionDelictiva;
	}	
	
	
}
