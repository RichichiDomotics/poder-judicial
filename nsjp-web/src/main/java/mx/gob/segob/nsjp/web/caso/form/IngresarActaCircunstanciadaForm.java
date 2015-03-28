/**
* Nombre del Programa : IngresarIndividuoForm.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 23/02/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Form para Ingresar Individuo
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma Asociada a la pantalla Ingresar individuo
 * 
 * @version 1.0
 * @author Arturo Leon Galicia - Ultrasist
 * 
 * 
 */
public class IngresarActaCircunstanciadaForm extends GenericForm{

	/** Default Serial version */
	private static final long serialVersionUID = -8825062910690544728L;
	private Long idActaCircunstancial;
	private String numExpediente;
	private String idUsuario;
	
	private Long idHecho;
	private Long idDomicilio;
	
	//documento de identificacion;
	private String docIdentificacion;
	private String folioDoc;	
		
	public Long getIdHecho() {
		return idHecho;
	}

	public void setIdHecho(Long idHecho) {
		this.idHecho = idHecho;
	}

	public Long getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(Long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	/**
	 * Método de acceso al campo docIdentificacion.
	 * @return El valor del campo docIdentificacion
	 */
	public String getDocIdentificacion() {
		return docIdentificacion;
	}
	
	/**
	 * Asigna el valor al campo docIdentificacion.
	 * @param docIdentificacion el valor docIdentificacion a asignar
	 */
	public void setDocIdentificacion(String docIdentificacion) {
		this.docIdentificacion = docIdentificacion;
	}
	/**
	 * Método de acceso al campo folioDoc.
	 * @return El valor del campo folioDoc
	 */
	public String getFolioDoc() {
		return folioDoc;
	}
	/**
	 * Asigna el valor al campo folioDoc.
	 * @param folioDoc el valor folioDoc a asignar
	 */
	public void setFolioDoc(String folioDoc) {
		this.folioDoc = folioDoc;
	}
	
	//Probable responsable fisico
	//Datos generales
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String curp;
	private String rfc;
	private String fechaIngreso;
	private String idioma;
	private String escolaridad;
	private String estadoCivil;
	private String sexo;
	private String fechaNacimiento;
	private Short edadAproximada;
	private String alias;
	private String ocupacion;
	private String nacionalidad;
	private String paisNacimiento;
	private String entFederativaNacimiento;
	private String municipioNacimiento;

	//Domicilio
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
	private String latitudN;
	private String latitudGrados;
	private String latitudMinutos;
	private String latitudSegundos;
	private String longitudE;
	private String longitudGrados;
	private String longitudMinutos;
	private String longitudSegundos;

	/**
	 * @return the latitudN
	 */
	public String getLatitudN() {
		return latitudN;
	}

	/**
	 * @param latitudN the latitudN to set
	 */
	public void setLatitudN(String latitudN) {
		this.latitudN = latitudN;
	}

	/**
	 * @return the latitudGrados
	 */
	public String getLatitudGrados() {
		return latitudGrados;
	}

	/**
	 * @param latitudGrados the latitudGrados to set
	 */
	public void setLatitudGrados(String latitudGrados) {
		this.latitudGrados = latitudGrados;
	}

	/**
	 * @return the latitudMinutos
	 */
	public String getLatitudMinutos() {
		return latitudMinutos;
	}

	/**
	 * @param latitudMinutos the latitudMinutos to set
	 */
	public void setLatitudMinutos(String latitudMinutos) {
		this.latitudMinutos = latitudMinutos;
	}

	/**
	 * @return the latitudSegundos
	 */
	public String getLatitudSegundos() {
		return latitudSegundos;
	}

	/**
	 * @param latitudSegundos the latitudSegundos to set
	 */
	public void setLatitudSegundos(String latitudSegundos) {
		this.latitudSegundos = latitudSegundos;
	}

	/**
	 * @return the longitudE
	 */
	public String getLongitudE() {
		return longitudE;
	}

	/**
	 * @param longitudE the longitudE to set
	 */
	public void setLongitudE(String longitudE) {
		this.longitudE = longitudE;
	}

	/**
	 * @return the longitudGrados
	 */
	public String getLongitudGrados() {
		return longitudGrados;
	}

	/**
	 * @param longitudGrados the longitudGrados to set
	 */
	public void setLongitudGrados(String longitudGrados) {
		this.longitudGrados = longitudGrados;
	}

	/**
	 * @return the longitudMinutos
	 */
	public String getLongitudMinutos() {
		return longitudMinutos;
	}

	/**
	 * @param longitudMinutos the longitudMinutos to set
	 */
	public void setLongitudMinutos(String longitudMinutos) {
		this.longitudMinutos = longitudMinutos;
	}

	/**
	 * @return the longitudSegundos
	 */
	public String getLongitudSegundos() {
		return longitudSegundos;
	}

	/**
	 * @param longitudSegundos the longitudSegundos to set
	 */
	public void setLongitudSegundos(String longitudSegundos) {
		this.longitudSegundos = longitudSegundos;
	}

	//medios de contacto
	private String medioContactoTelefono;
	private String medioContactoCorreo;
	
	//motivo de comparecencia
	private String motivoComparecencia;


	/**
	 * @return the idActaCircunstancial
	 */
	public Long getIdActaCircunstancial() {
		return idActaCircunstancial;
	}

	/**
	 * @param idActaCircunstancial the idActaCircunstancial to set
	 */
	public void setIdActaCircunstancial(Long idActaCircunstancial) {
		this.idActaCircunstancial = idActaCircunstancial;
	}

	/**
	 * @return the numExpediente
	 */
	public String getNumExpediente() {
		return numExpediente;
	}

	/**
	 * @param numExpediente the numExpediente to set
	 */
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * @param curp the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the escolaridad
	 */
	public String getEscolaridad() {
		return escolaridad;
	}

	/**
	 * @param escolaridad the escolaridad to set
	 */
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	/**
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the edadAproximada
	 */
	public Short getEdadAproximada() {
		return edadAproximada;
	}

	/**
	 * @param edadAproximada the edadAproximada to set
	 */
	public void setEdadAproximada(Short edadAproximada) {
		this.edadAproximada = edadAproximada;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}

	/**
	 * @param ocupacion the ocupacion to set
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return the paisNacimiento
	 */
	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	/**
	 * @param paisNacimiento the paisNacimiento to set
	 */
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	/**
	 * @return the entFederativaNacimiento
	 */
	public String getEntFederativaNacimiento() {
		return entFederativaNacimiento;
	}

	/**
	 * @param entFederativaNacimiento the entFederativaNacimiento to set
	 */
	public void setEntFederativaNacimiento(String entFederativaNacimiento) {
		this.entFederativaNacimiento = entFederativaNacimiento;
	}

	/**
	 * @return the municipioNacimiento
	 */
	public String getMunicipioNacimiento() {
		return municipioNacimiento;
	}

	/**
	 * @param municipioNacimiento the municipioNacimiento to set
	 */
	public void setMunicipioNacimiento(String municipioNacimiento) {
		this.municipioNacimiento = municipioNacimiento;
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
	 * @return the motivoComparecencia
	 */
	public String getMotivoComparecencia() {
		return motivoComparecencia;
	}

	/**
	 * @param motivoComparecencia the motivoComparecencia to set
	 */
	public void setMotivoComparecencia(String motivoComparecencia) {
		this.motivoComparecencia = motivoComparecencia;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
   	
    
}