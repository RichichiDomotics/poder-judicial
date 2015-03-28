/**
* Nombre del Programa : OrganizacionDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto organizacion
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

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class OrganizacionDTO extends ElementoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1719818438792152051L;

	private Long organizacionId;	
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
	private ValorDTO valorBySectorGubernamentalVal;
	private ValorDTO valorByOrganizacionFormalVal;
	private ValorDTO valorByComunidadVirtualVal;
	private ValorDTO valorByTipoOrganizacionVal;	
	private DomicilioDTO domicilioDTO;
	private InvolucradoDTO representanteLegal;
	private PersonaDTO contacto;
	
	
	/**
	 * 
	 */
	public OrganizacionDTO() {
		super();		
	}
	/**
	 * 
	 * @param elementoId
	 */
	public OrganizacionDTO(Long elementoId) {
		super();
		setElementoId(elementoId);
	}
	
	/**
	 * Método de acceso al campo organizacionId.
	 * @return El valor del campo organizacionId
	 */
	public Long getOrganizacionId() {
		return organizacionId;
	}
	/**
	 * Asigna el valor al campo organizacionId.
	 * @param organizacionId el valor organizacionId a asignar
	 */
	public void setOrganizacionId(Long organizacionId) {
		this.organizacionId = organizacionId;
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
	 * Método de acceso al campo domicilioDTO.
	 * @return El valor del campo domicilioDTO
	 */
	public DomicilioDTO getDomicilioDTO() {
		return domicilioDTO;
	}
	/**
	 * Asigna el valor al campo domicilioDTO.
	 * @param domicilioDTO el valor domicilioDTO a asignar
	 */
	public void setDomicilioDTO(DomicilioDTO domicilioDTO) {
		this.domicilioDTO = domicilioDTO;
	}
	/**
	 * Método de acceso al campo valorBySectorGubernamentalVal.
	 * @return El valor del campo valorBySectorGubernamentalVal
	 */
	public ValorDTO getValorBySectorGubernamentalVal() {
		return valorBySectorGubernamentalVal;
	}
	/**
	 * Asigna el valor al campo valorBySectorGubernamentalVal.
	 * @param valorBySectorGubernamentalVal el valor valorBySectorGubernamentalVal a asignar
	 */
	public void setValorBySectorGubernamentalVal(
			ValorDTO valorBySectorGubernamentalVal) {
		this.valorBySectorGubernamentalVal = valorBySectorGubernamentalVal;
	}
	/**
	 * Método de acceso al campo valorByOrganizacionFormalVal.
	 * @return El valor del campo valorByOrganizacionFormalVal
	 */
	public ValorDTO getValorByOrganizacionFormalVal() {
		return valorByOrganizacionFormalVal;
	}
	/**
	 * Asigna el valor al campo valorByOrganizacionFormalVal.
	 * @param valorByOrganizacionFormalVal el valor valorByOrganizacionFormalVal a asignar
	 */
	public void setValorByOrganizacionFormalVal(
			ValorDTO valorByOrganizacionFormalVal) {
		this.valorByOrganizacionFormalVal = valorByOrganizacionFormalVal;
	}
	/**
	 * Método de acceso al campo valorByComunidadVirtualVal.
	 * @return El valor del campo valorByComunidadVirtualVal
	 */
	public ValorDTO getValorByComunidadVirtualVal() {
		return valorByComunidadVirtualVal;
	}
	/**
	 * Asigna el valor al campo valorByComunidadVirtualVal.
	 * @param valorByComunidadVirtualVal el valor valorByComunidadVirtualVal a asignar
	 */
	public void setValorByComunidadVirtualVal(ValorDTO valorByComunidadVirtualVal) {
		this.valorByComunidadVirtualVal = valorByComunidadVirtualVal;
	}
	/**
	 * Método de acceso al campo valorByTipoOrganizacionVal.
	 * @return El valor del campo valorByTipoOrganizacionVal
	 */
	public ValorDTO getValorByTipoOrganizacionVal() {
		return valorByTipoOrganizacionVal;
	}
	/**
	 * Asigna el valor al campo valorByTipoOrganizacionVal.
	 * @param valorByTipoOrganizacionVal el valor valorByTipoOrganizacionVal a asignar
	 */
	public void setValorByTipoOrganizacionVal(ValorDTO valorByTipoOrganizacionVal) {
		this.valorByTipoOrganizacionVal = valorByTipoOrganizacionVal;
	}
	/**
	 * Método de acceso al campo representanteLegal.
	 * @return El valor del campo representanteLegal
	 */
	public InvolucradoDTO getRepresentanteLegal() {
		return representanteLegal;
	}
	/**
	 * Asigna el valor al campo representanteLegal.
	 * @param representanteLegal el valor representanteLegal a asignar
	 */
	public void setRepresentanteLegal(InvolucradoDTO representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	/**
	 * Método de acceso al campo contacto.
	 * @return El valor del campo contacto
	 */
	public PersonaDTO getContacto() {
		return contacto;
	}
	/**
	 * Asigna el valor al campo contacto.
	 * @param contacto el valor contacto a asignar
	 */
	public void setContacto(PersonaDTO contacto) {
		this.contacto = contacto;
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
