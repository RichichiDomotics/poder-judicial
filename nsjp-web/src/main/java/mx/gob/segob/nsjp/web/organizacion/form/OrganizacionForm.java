/**
* Nombre del Programa 		: OrganizacionForm.java
* Autor                     : ArmandoCT
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha:21 Junio 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Organizacion.
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion         : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                     : N/A
* Compania               	: N/A
* Proyecto                 	: N/A                                 Fecha: N/A
* Modificacion           	: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.organizacion.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma para el objeto Organizacion.
 * @version 1.0
 * @author ArmandoCT
 *
 */
public class OrganizacionForm extends GenericForm{

	/**
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	//ORGANIZACION *************************************************************************************************************
	/*
	 * identificadores para los campos generales de una organizacion
	 */
	private String nombreOrg;
	private String nombreCortoOrg;
	private String orgDirInternet;
	
	/*
	 * identificadores para los campos de una comunidad virtual
	 */
	private String tipoOrgVirtual;
	private String dirElectronicaOrgVirtual;
	private String propositoOrgVirtual;
	
	/*
	 * identificadores para los campos de una organizacion delictiva
	 */
	private String descOrgDelictiva;
	
	/*
	 * identificadores para los campos de una organizacion formal
	 */
	private String tipoOrgFormal;
	private String noActaOrgFormal;
	private String rfcOrgFormal;
	private String giroOrgFormal;
	
	/*
	 * identificadores para los campos de una organizacion No formal
	 */
	private String areaOrgNoFormal;
	private String giroOrgNoFormal;
	
	/*
	 * identificadores para los campos de una organizacion sector publico
	 */
	private String nivelDepOrgSectorPublico;
	private String tipoDepOrgSectorPublico;
	private String orgsSecPubOrgSectorPublico;
	private String areaOrgSectorPublico;
	
	//ids para los campos de Domicilio
	private String pais;
	private String codigoPostal;
	private String entidadFederativa;
	private String ciudad;
	private String delegacionMunicipio;
	private String asentamientoColonia;
	private String tipoAsentamiento;
	private String tipoCalle;
	private String calle;
	private String numExterior;
	private String numInterior;
	private String referencias;
	private String entreCalle;
	private String ycalle;
	private String aliasDomicilio;
	private String edificio;
	private String longitud;
	private String latitud;
	private String mismoDomicilioNotificaciones;
	
	//Domicilio para notificaciones
	private String paisNotif;
	private String codigoPostalNotif;
	private String entidadFederativaNotif;
	private String ciudadNotif;
	private String delegacionMunicipioNotif;
	private String asentamientoColoniaNotif;
	private String tipoAsentamientoNotif;
	private String tipoCalleNotif;
	private String calleNotif;
	private String numExteriorNotif;
	private String numInteriorNotif;
	private String referenciasNotif;
	private String entreCalleNotif;
	private String ycalleNotif;
	private String aliasDomicilioNotif;
	private String edificioNotif;
	private String longitudNotif;
	private String latitudNotif;
	
	//medios de contacto
	private String medioContactoTelefono;
	private String medioContactoCorreo;
	
	private String esPersonaMoral;
	private String tipoOrg;
	
	//id de numero de Expediente y id de usuario
	private String numeroExpediente;
	private String idUsuario;
	private String calidadPersonaMoral;
	
	//id para datos de Servidor publico
	 private String nivelDepServPublico; 
	 private String tipoDepServPublico;
	 private String dependenciaServPublico;
	 private String puestoServPublico;
	 private String numEpmleadoServPublico;
	 
	 private String idIndividuo;
	 private String idOrganizacion;
	 private Boolean anular;
	
	//FIN ORGANIZACION *************************************************************************************************************

	 /**
	 * @return the anular
	 */
	 public Boolean getAnular() {
		return anular;
	}

	 /**
	 * @param anular the anular to set
	 */
	public void setAnular(Boolean anular) {
		this.anular = anular;
	}

	/**
	 * @return the idIndividuo
	 */
	public String getIdIndividuo() {
		return idIndividuo;
	}

	/**
	 * @param idIndividuo the idIndividuo to set
	 */
	public void setIdIndividuo(String idIndividuo) {
		this.idIndividuo = idIndividuo;
	}

	/**
	 * @return the idOrganizacion
	 */
	public String getIdOrganizacion() {
		return idOrganizacion;
	}

	/**
	 * @param idOrganizacion the idOrganizacion to set
	 */
	public void setIdOrganizacion(String idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	/**
	 * @return the nivelDepServPublico
	 */
	public String getNivelDepServPublico() {
		return nivelDepServPublico;
	}

	/**
	 * @param nivelDepServPublico the nivelDepServPublico to set
	 */
	public void setNivelDepServPublico(String nivelDepServPublico) {
		this.nivelDepServPublico = nivelDepServPublico;
	}

	/**
	 * @return the tipoDepServPublico
	 */
	public String getTipoDepServPublico() {
		return tipoDepServPublico;
	}

	/**
	 * @param tipoDepServPublico the tipoDepServPublico to set
	 */
	public void setTipoDepServPublico(String tipoDepServPublico) {
		this.tipoDepServPublico = tipoDepServPublico;
	}

	/**
	 * @return the dependenciaServPublico
	 */
	public String getDependenciaServPublico() {
		return dependenciaServPublico;
	}

	/**
	 * @param dependenciaServPublico the dependenciaServPublico to set
	 */
	public void setDependenciaServPublico(String dependenciaServPublico) {
		this.dependenciaServPublico = dependenciaServPublico;
	}

	/**
	 * @return the puestoServPublico
	 */
	public String getPuestoServPublico() {
		return puestoServPublico;
	}

	/**
	 * @param puestoServPublico the puestoServPublico to set
	 */
	public void setPuestoServPublico(String puestoServPublico) {
		this.puestoServPublico = puestoServPublico;
	}

	/**
	 * @return the numEpmleadoServPublico
	 */
	public String getNumEpmleadoServPublico() {
		return numEpmleadoServPublico;
	}

	/**
	 * @param numEpmleadoServPublico the numEpmleadoServPublico to set
	 */
	public void setNumEpmleadoServPublico(String numEpmleadoServPublico) {
		this.numEpmleadoServPublico = numEpmleadoServPublico;
	}

	/**
	 * @return the calidadPersonaMoral
	 */
	public String getCalidadPersonaMoral() {
		return calidadPersonaMoral;
	}

	/**
	 * @param calidadPersonaMoral the calidadPersonaMoral to set
	 */
	public void setCalidadPersonaMoral(String calidadPersonaMoral) {
		this.calidadPersonaMoral = calidadPersonaMoral;
	}

	/**
	 * @return the numExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * @param numExpediente the numExpediente to set
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the tipoOrg
	 */
	public String getTipoOrg() {
		return tipoOrg;
	}

	/**
	 * @param tipoOrg the tipoOrg to set
	 */
	public void setTipoOrg(String tipoOrg) {
		this.tipoOrg = tipoOrg;
	}

	/**
	 * @return the esPersonaMoral
	 */
	public String getEsPersonaMoral() {
		return esPersonaMoral;
	}

	/**
	 * @param esPersonaMoral the esPersonaMoral to set
	 */
	public void setEsPersonaMoral(String esPersonaMoral) {
		this.esPersonaMoral = esPersonaMoral;
	}

	/**
	 * @return the nombreOrg
	 */
	public String getNombreOrg() {
		return nombreOrg;
	}

	/**
	 * @param nombreOrg the nombreOrg to set
	 */
	public void setNombreOrg(String nombreOrg) {
		this.nombreOrg = nombreOrg;
	}

	/**
	 * @return the nombreCortoOrg
	 */
	public String getNombreCortoOrg() {
		return nombreCortoOrg;
	}

	/**
	 * @param nombreCortoOrg the nombreCortoOrg to set
	 */
	public void setNombreCortoOrg(String nombreCortoOrg) {
		this.nombreCortoOrg = nombreCortoOrg;
	}

	/**
	 * @return the orgDirInternet
	 */
	public String getOrgDirInternet() {
		return orgDirInternet;
	}

	/**
	 * @param orgDirInternet the orgDirInternet to set
	 */
	public void setOrgDirInternet(String orgDirInternet) {
		this.orgDirInternet = orgDirInternet;
	}

	/**
	 * @return the tipoOrgVirtual
	 */
	public String getTipoOrgVirtual() {
		return tipoOrgVirtual;
	}

	/**
	 * @param tipoOrgVirtual the tipoOrgVirtual to set
	 */
	public void setTipoOrgVirtual(String tipoOrgVirtual) {
		this.tipoOrgVirtual = tipoOrgVirtual;
	}

	/**
	 * @return the dirElectronicaOrgVirtual
	 */
	public String getDirElectronicaOrgVirtual() {
		return dirElectronicaOrgVirtual;
	}

	/**
	 * @param dirElectronicaOrgVirtual the dirElectronicaOrgVirtual to set
	 */
	public void setDirElectronicaOrgVirtual(String dirElectronicaOrgVirtual) {
		this.dirElectronicaOrgVirtual = dirElectronicaOrgVirtual;
	}

	/**
	 * @return the propositoOrgVirtual
	 */
	public String getPropositoOrgVirtual() {
		return propositoOrgVirtual;
	}

	/**
	 * @param propositoOrgVirtual the propositoOrgVirtual to set
	 */
	public void setPropositoOrgVirtual(String propositoOrgVirtual) {
		this.propositoOrgVirtual = propositoOrgVirtual;
	}

	/**
	 * @return the descOrgDelictiva
	 */
	public String getDescOrgDelictiva() {
		return descOrgDelictiva;
	}

	/**
	 * @param descOrgDelictiva the descOrgDelictiva to set
	 */
	public void setDescOrgDelictiva(String descOrgDelictiva) {
		this.descOrgDelictiva = descOrgDelictiva;
	}

	/**
	 * @return the tipoOrgFormal
	 */
	public String getTipoOrgFormal() {
		return tipoOrgFormal;
	}

	/**
	 * @param tipoOrgFormal the tipoOrgFormal to set
	 */
	public void setTipoOrgFormal(String tipoOrgFormal) {
		this.tipoOrgFormal = tipoOrgFormal;
	}

	/**
	 * @return the noActaOrgFormal
	 */
	public String getNoActaOrgFormal() {
		return noActaOrgFormal;
	}

	/**
	 * @param noActaOrgFormal the noActaOrgFormal to set
	 */
	public void setNoActaOrgFormal(String noActaOrgFormal) {
		this.noActaOrgFormal = noActaOrgFormal;
	}

	/**
	 * @return the rfcOrgFormal
	 */
	public String getRfcOrgFormal() {
		return rfcOrgFormal;
	}

	/**
	 * @param rfcOrgFormal the rfcOrgFormal to set
	 */
	public void setRfcOrgFormal(String rfcOrgFormal) {
		this.rfcOrgFormal = rfcOrgFormal;
	}

	/**
	 * @return the giroOrgFormal
	 */
	public String getGiroOrgFormal() {
		return giroOrgFormal;
	}

	/**
	 * @param giroOrgFormal the giroOrgFormal to set
	 */
	public void setGiroOrgFormal(String giroOrgFormal) {
		this.giroOrgFormal = giroOrgFormal;
	}

	/**
	 * @return the areaOrgNoFormal
	 */
	public String getAreaOrgNoFormal() {
		return areaOrgNoFormal;
	}

	/**
	 * @param areaOrgNoFormal the areaOrgNoFormal to set
	 */
	public void setAreaOrgNoFormal(String areaOrgNoFormal) {
		this.areaOrgNoFormal = areaOrgNoFormal;
	}

	/**
	 * @return the giroOrgNoFormal
	 */
	public String getGiroOrgNoFormal() {
		return giroOrgNoFormal;
	}

	/**
	 * @param giroOrgNoFormal the giroOrgNoFormal to set
	 */
	public void setGiroOrgNoFormal(String giroOrgNoFormal) {
		this.giroOrgNoFormal = giroOrgNoFormal;
	}

	/**
	 * @return the nivelDepOrgSectorPublico
	 */
	public String getNivelDepOrgSectorPublico() {
		return nivelDepOrgSectorPublico;
	}

	/**
	 * @param nivelDepOrgSectorPublico the nivelDepOrgSectorPublico to set
	 */
	public void setNivelDepOrgSectorPublico(String nivelDepOrgSectorPublico) {
		this.nivelDepOrgSectorPublico = nivelDepOrgSectorPublico;
	}

	/**
	 * @return the tipoDepOrgSectorPublico
	 */
	public String getTipoDepOrgSectorPublico() {
		return tipoDepOrgSectorPublico;
	}

	/**
	 * @param tipoDepOrgSectorPublico the tipoDepOrgSectorPublico to set
	 */
	public void setTipoDepOrgSectorPublico(String tipoDepOrgSectorPublico) {
		this.tipoDepOrgSectorPublico = tipoDepOrgSectorPublico;
	}

	/**
	 * @return the orgsSecPubOrgSectorPublico
	 */
	public String getOrgsSecPubOrgSectorPublico() {
		return orgsSecPubOrgSectorPublico;
	}

	/**
	 * @param orgsSecPubOrgSectorPublico the orgsSecPubOrgSectorPublico to set
	 */
	public void setOrgsSecPubOrgSectorPublico(String orgsSecPubOrgSectorPublico) {
		this.orgsSecPubOrgSectorPublico = orgsSecPubOrgSectorPublico;
	}

	/**
	 * @return the areaOrgSectorPublico
	 */
	public String getAreaOrgSectorPublico() {
		return areaOrgSectorPublico;
	}

	/**
	 * @param areaOrgSectorPublico the areaOrgSectorPublico to set
	 */
	public void setAreaOrgSectorPublico(String areaOrgSectorPublico) {
		this.areaOrgSectorPublico = areaOrgSectorPublico;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the entidadFederativa
	 */
	public String getEntidadFederativa() {
		return entidadFederativa;
	}

	/**
	 * @param entidadFederativa the entidadFederativa to set
	 */
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the delegacionMunicipio
	 */
	public String getDelegacionMunicipio() {
		return delegacionMunicipio;
	}

	/**
	 * @param delegacionMunicipio the delegacionMunicipio to set
	 */
	public void setDelegacionMunicipio(String delegacionMunicipio) {
		this.delegacionMunicipio = delegacionMunicipio;
	}

	/**
	 * @return the asentamientoColonia
	 */
	public String getAsentamientoColonia() {
		return asentamientoColonia;
	}

	/**
	 * @param asentamientoColonia the asentamientoColonia to set
	 */
	public void setAsentamientoColonia(String asentamientoColonia) {
		this.asentamientoColonia = asentamientoColonia;
	}

	/**
	 * @return the tipoAsentamiento
	 */
	public String getTipoAsentamiento() {
		return tipoAsentamiento;
	}

	/**
	 * @param tipoAsentamiento the tipoAsentamiento to set
	 */
	public void setTipoAsentamiento(String tipoAsentamiento) {
		this.tipoAsentamiento = tipoAsentamiento;
	}

	/**
	 * @return the tipoCalle
	 */
	public String getTipoCalle() {
		return tipoCalle;
	}

	/**
	 * @param tipoCalle the tipoCalle to set
	 */
	public void setTipoCalle(String tipoCalle) {
		this.tipoCalle = tipoCalle;
	}

	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * @return the numExterior
	 */
	public String getNumExterior() {
		return numExterior;
	}

	/**
	 * @param numExterior the numExterior to set
	 */
	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}

	/**
	 * @return the numInterior
	 */
	public String getNumInterior() {
		return numInterior;
	}

	/**
	 * @param numInterior the numInterior to set
	 */
	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}

	/**
	 * @return the referencias
	 */
	public String getReferencias() {
		return referencias;
	}

	/**
	 * @param referencias the referencias to set
	 */
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	/**
	 * @return the entreCalle
	 */
	public String getEntreCalle() {
		return entreCalle;
	}

	/**
	 * @param entreCalle the entreCalle to set
	 */
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}

	/**
	 * @return the ycalle
	 */
	public String getYcalle() {
		return ycalle;
	}

	/**
	 * @param ycalle the ycalle to set
	 */
	public void setYcalle(String ycalle) {
		this.ycalle = ycalle;
	}

	/**
	 * @return the aliasDomicilio
	 */
	public String getAliasDomicilio() {
		return aliasDomicilio;
	}

	/**
	 * @param aliasDomicilio the aliasDomicilio to set
	 */
	public void setAliasDomicilio(String aliasDomicilio) {
		this.aliasDomicilio = aliasDomicilio;
	}

	/**
	 * @return the edificio
	 */
	public String getEdificio() {
		return edificio;
	}

	/**
	 * @param edificio the edificio to set
	 */
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the mismoDomicilioNotificaciones
	 */
	public String getMismoDomicilioNotificaciones() {
		return mismoDomicilioNotificaciones;
	}

	/**
	 * @param mismoDomicilioNotificaciones the mismoDomicilioNotificaciones to set
	 */
	public void setMismoDomicilioNotificaciones(String mismoDomicilioNotificaciones) {
		this.mismoDomicilioNotificaciones = mismoDomicilioNotificaciones;
	}

	/**
	 * @return the paisNotif
	 */
	public String getPaisNotif() {
		return paisNotif;
	}

	/**
	 * @param paisNotif the paisNotif to set
	 */
	public void setPaisNotif(String paisNotif) {
		this.paisNotif = paisNotif;
	}

	/**
	 * @return the codigoPostalNotif
	 */
	public String getCodigoPostalNotif() {
		return codigoPostalNotif;
	}

	/**
	 * @param codigoPostalNotif the codigoPostalNotif to set
	 */
	public void setCodigoPostalNotif(String codigoPostalNotif) {
		this.codigoPostalNotif = codigoPostalNotif;
	}

	/**
	 * @return the entidadFederativaNotif
	 */
	public String getEntidadFederativaNotif() {
		return entidadFederativaNotif;
	}

	/**
	 * @param entidadFederativaNotif the entidadFederativaNotif to set
	 */
	public void setEntidadFederativaNotif(String entidadFederativaNotif) {
		this.entidadFederativaNotif = entidadFederativaNotif;
	}

	/**
	 * @return the ciudadNotif
	 */
	public String getCiudadNotif() {
		return ciudadNotif;
	}

	/**
	 * @param ciudadNotif the ciudadNotif to set
	 */
	public void setCiudadNotif(String ciudadNotif) {
		this.ciudadNotif = ciudadNotif;
	}

	/**
	 * @return the delegacionMunicipioNotif
	 */
	public String getDelegacionMunicipioNotif() {
		return delegacionMunicipioNotif;
	}

	/**
	 * @param delegacionMunicipioNotif the delegacionMunicipioNotif to set
	 */
	public void setDelegacionMunicipioNotif(String delegacionMunicipioNotif) {
		this.delegacionMunicipioNotif = delegacionMunicipioNotif;
	}

	/**
	 * @return the asentamientoColoniaNotif
	 */
	public String getAsentamientoColoniaNotif() {
		return asentamientoColoniaNotif;
	}

	/**
	 * @param asentamientoColoniaNotif the asentamientoColoniaNotif to set
	 */
	public void setAsentamientoColoniaNotif(String asentamientoColoniaNotif) {
		this.asentamientoColoniaNotif = asentamientoColoniaNotif;
	}

	/**
	 * @return the tipoAsentamientoNotif
	 */
	public String getTipoAsentamientoNotif() {
		return tipoAsentamientoNotif;
	}

	/**
	 * @param tipoAsentamientoNotif the tipoAsentamientoNotif to set
	 */
	public void setTipoAsentamientoNotif(String tipoAsentamientoNotif) {
		this.tipoAsentamientoNotif = tipoAsentamientoNotif;
	}

	/**
	 * @return the tipoCalleNotif
	 */
	public String getTipoCalleNotif() {
		return tipoCalleNotif;
	}

	/**
	 * @param tipoCalleNotif the tipoCalleNotif to set
	 */
	public void setTipoCalleNotif(String tipoCalleNotif) {
		this.tipoCalleNotif = tipoCalleNotif;
	}

	/**
	 * @return the calleNotif
	 */
	public String getCalleNotif() {
		return calleNotif;
	}

	/**
	 * @param calleNotif the calleNotif to set
	 */
	public void setCalleNotif(String calleNotif) {
		this.calleNotif = calleNotif;
	}

	/**
	 * @return the numExteriorNotif
	 */
	public String getNumExteriorNotif() {
		return numExteriorNotif;
	}

	/**
	 * @param numExteriorNotif the numExteriorNotif to set
	 */
	public void setNumExteriorNotif(String numExteriorNotif) {
		this.numExteriorNotif = numExteriorNotif;
	}

	/**
	 * @return the numInteriorNotif
	 */
	public String getNumInteriorNotif() {
		return numInteriorNotif;
	}

	/**
	 * @param numInteriorNotif the numInteriorNotif to set
	 */
	public void setNumInteriorNotif(String numInteriorNotif) {
		this.numInteriorNotif = numInteriorNotif;
	}

	/**
	 * @return the referenciasNotif
	 */
	public String getReferenciasNotif() {
		return referenciasNotif;
	}

	/**
	 * @param referenciasNotif the referenciasNotif to set
	 */
	public void setReferenciasNotif(String referenciasNotif) {
		this.referenciasNotif = referenciasNotif;
	}

	/**
	 * @return the entreCalleNotif
	 */
	public String getEntreCalleNotif() {
		return entreCalleNotif;
	}

	/**
	 * @param entreCalleNotif the entreCalleNotif to set
	 */
	public void setEntreCalleNotif(String entreCalleNotif) {
		this.entreCalleNotif = entreCalleNotif;
	}

	/**
	 * @return the ycalleNotif
	 */
	public String getYcalleNotif() {
		return ycalleNotif;
	}

	/**
	 * @param ycalleNotif the ycalleNotif to set
	 */
	public void setYcalleNotif(String ycalleNotif) {
		this.ycalleNotif = ycalleNotif;
	}

	/**
	 * @return the aliasDomicilioNotif
	 */
	public String getAliasDomicilioNotif() {
		return aliasDomicilioNotif;
	}

	/**
	 * @param aliasDomicilioNotif the aliasDomicilioNotif to set
	 */
	public void setAliasDomicilioNotif(String aliasDomicilioNotif) {
		this.aliasDomicilioNotif = aliasDomicilioNotif;
	}

	/**
	 * @return the edificioNotif
	 */
	public String getEdificioNotif() {
		return edificioNotif;
	}

	/**
	 * @param edificioNotif the edificioNotif to set
	 */
	public void setEdificioNotif(String edificioNotif) {
		this.edificioNotif = edificioNotif;
	}

	/**
	 * @return the longitudNotif
	 */
	public String getLongitudNotif() {
		return longitudNotif;
	}

	/**
	 * @param longitudNotif the longitudNotif to set
	 */
	public void setLongitudNotif(String longitudNotif) {
		this.longitudNotif = longitudNotif;
	}

	/**
	 * @return the latitudNotif
	 */
	public String getLatitudNotif() {
		return latitudNotif;
	}

	/**
	 * @param latitudNotif the latitudNotif to set
	 */
	public void setLatitudNotif(String latitudNotif) {
		this.latitudNotif = latitudNotif;
	}

	/**
	 * @return the medioContactoTelefono
	 */
	public String getMedioContactoTelefono() {
		return medioContactoTelefono;
	}

	/**
	 * @param medioContactoTelefono the medioContactoTelefono to set
	 */
	public void setMedioContactoTelefono(String medioContactoTelefono) {
		this.medioContactoTelefono = medioContactoTelefono;
	}

	/**
	 * @return the medioContactoCorreo
	 */
	public String getMedioContactoCorreo() {
		return medioContactoCorreo;
	}

	/**
	 * @param medioContactoCorreo the medioContactoCorreo to set
	 */
	public void setMedioContactoCorreo(String medioContactoCorreo) {
		this.medioContactoCorreo = medioContactoCorreo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}