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
public class IngresarIndividuoForm extends GenericForm{

	/** Default Serial version */
	private static final long serialVersionUID = -8825062910690544728L;
	private Long idIndividuo;
	private String numExpediente;
	private Long idOrganizacion;
	private String idUsuario;
	
	//para quien detuvo
	private String strDetenidos;
	
	//Datos de control
	private Short calidadDelIndividuo;
	private Boolean esDesconocido;
	private Boolean anular;
	
	//Probable responsable fisico
	/** Nombre de la calidad*/
	private String idCondicion;
	private Boolean esMenorDeEdad;
	private Boolean estaDetenido;
	//Datos generales
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String curp;
	private String rfc;
	private String fechaIngreso;
	private String idioma;
	private String religion;
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
	private Boolean esVivo;
	//Generales de detenido
	private String fechaInicioLapso;
	private String horaInicioLapso;
	private String fechaFinLapso;
	private String horaFinLapso;

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
	private String latitudNNotif;
	private String latitudGradosNotif;
	private String latitudMinutosNotif;
	private String latitudSegundosNotif;
	private String longitudENotif;
	private String longitudGradosNotif;
	private String longitudMinutosNotif;
	private String longitudSegundosNotif;

	//medios de contacto
	private String medioContactoTelefono;
	private String medioContactoCorreo;
	//documento de identificacion;
	private String docIdentificacion;
	private String folioDoc;
	
	//Datos de victima
	private Boolean esServidorPublico;
	private Boolean esProtegido;

	
	//Datos de denunciante
	private Boolean esAnonimo;
	private Boolean esPersonaMoral;
	private Boolean esVictimayDenunciante;
	private String motivoComparecencia;

	//Datos de Media Filiacion
	private String tamanoBoca;
	private String tipoCara;
	private String formaMenton;
	private String tipoMenton;
	private String tez;
	private String inclinacionMenton;
	private String colorCabello;
	private String formaCabello;
	private String calvieTipo;
	private String cabelloImplantacion;
	private String cantidadCabello;
	private String tamanoOreja;
	private String lobuloParticularidad;
	private String lobuloDimension;
	private String lobuloAdherencia;
	private String helixAnterior;
	private String helixPosterior;
	private String helixContorno;
	private String helixAdherencia;
	private String formaOreja;
	private String tamanoOjos;
	private String colorOjos;
	private String formaOjos;
	//cejas
	private String implantacionCejas;
	private String formaCejas;
	private String tamanoCejas;
	//SEñas particulares
	private String cicatricesSenas;
	private String cicatricesSenasText;
	private String protesisSenas;
	private String protesisSenasText;
	private String tatuajeSenas;
	private String tatuajeSenasText;
	private String otraSenasText;
	private String lunaresSenas;
	private String lunaresSenasText;
	private String defectosSenas;
	private String defectosSenasText;
	//otros
	private String factorrhOtros;
	private String lentesOtros;
	private String tipoSangreOtros;
	private String barbaOtros;
	private String pesoOtros;
	private String bigoteOtros;
	private String estauraOtros;
	private String complexion;
	private String direccionCeja;
	private String helixOriginal;
	private String orejaLobContorno;
	  
	
        // Jacob
        private String alturaNasoLabial;
        private String comisuras;
        private String espesorLabioInferior;
        private String espesorLabioSuperior;
        private String prominencia;
        private String anchoNariz;
        private String alturaNariz;
        private String baseNariz;
        private String raizNariz;
        private String frenteAltura;
        private String frenteAncho;
        private String inclinacionFrente;
        // Fin Jacob
	
	/**
	 * Método de acceso al campo motivoComparecencia.
	 * @return El valor del campo motivoComparecencia
	 */
	public String getMotivoComparecencia() {
		return motivoComparecencia;
	}
	/**
	 * Asigna el valor al campo motivoComparecencia.
	 * @param motivoComparecencia el valor motivoComparecencia a asignar
	 */
	public void setMotivoComparecencia(String motivoComparecencia) {
		this.motivoComparecencia = motivoComparecencia;
	}
	
	//Datos de Traductor
	private Boolean esTraductor;
	
	//Datos para persona moral
	private String nombreOrg;
	private String nombreCortoOrg;
	private String dirInternet;
	private String tipoOrganizacion;
	
	//ORGANICAZIÓN
	//Organización formal
	private String tipoOrgFormal; 
	private String noActaOrgFormal;
	private String rfcOrgFormal;
	private String giroOrgFormal;
	private String datosRepOrgFormal;
	//Organización no formal
	private String areaOrgNoFormal; 
	private String giroOrgNoFormal;
	//Organización delictiva
	private String descOrgDelictiva;
	//Organización del sector público
	private String nivelDepOrgSectorPublico;
	private String tipoDepOrgSectorPublico;
	private String orgsSecPubOrgSectorPublico;
	private String areaOrgSectorPublico;
	//Organización virtual
	private String tipoOrgVirtual;
	private String dirElectronicaOrgVirtual;
	private String propositoOrgVirtual;
	
	private Long tipoServidorPublico;
	//servidor publico interno
	private String areaServPublico; 
	private String deptoServPublico;
	private String nombreServPublicoInt;
	private String puestoServPublicoInt;
	//servidor publico externo
	private String nivelDepServPublico; 
	private String tipoDepServPublico;
	private String dependenciaServPublico;
	private String puestoServPublico;
	private String numEpmleadoServPublico;
	private String nombreServPublicoExt;
	private String appaternoServPublicoExt;
	private String apmaternoServPublico;
	
	/**
	 * @return the tipoServidorPublico
	 */
	public Long getTipoServidorPublico() {
		return tipoServidorPublico;
	}
	/**
	 * @param tipoServidorPublico the tipoServidorPublico to set
	 */
	public void setTipoServidorPublico(Long tipoServidorPublico) {
		this.tipoServidorPublico = tipoServidorPublico;
	}
	
	/**
	 * @return the areaServPublico
	 */
	public String getAreaServPublico() {
		return areaServPublico;
	}
	/**
	 * @param areaServPublico the areaServPublico to set
	 */
	public void setAreaServPublico(String areaServPublico) {
		this.areaServPublico = areaServPublico;
	}
	/**
	 * @return the deptoServPublico
	 */
	public String getDeptoServPublico() {
		return deptoServPublico;
	}
	/**
	 * @param deptoServPublico the deptoServPublico to set
	 */
	public void setDeptoServPublico(String deptoServPublico) {
		this.deptoServPublico = deptoServPublico;
	}
	/**
	 * @return the nombreServPublicoInt
	 */
	public String getNombreServPublicoInt() {
		return nombreServPublicoInt;
	}
	/**
	 * @param nombreServPublicoInt the nombreServPublicoInt to set
	 */
	public void setNombreServPublicoInt(String nombreServPublicoInt) {
		this.nombreServPublicoInt = nombreServPublicoInt;
	}
	/**
	 * @return the puestoServPublicoInt
	 */
	public String getPuestoServPublicoInt() {
		return puestoServPublicoInt;
	}
	/**
	 * @param puestoServPublicoInt the puestoServPublicoInt to set
	 */
	public void setPuestoServPublicoInt(String puestoServPublicoInt) {
		this.puestoServPublicoInt = puestoServPublicoInt;
	}
	/**
	 * @return the nombreServPublicoExt
	 */
	public String getNombreServPublicoExt() {
		return nombreServPublicoExt;
	}
	/**
	 * @param nombreServPublicoExt the nombreServPublicoExt to set
	 */
	public void setNombreServPublicoExt(String nombreServPublicoExt) {
		this.nombreServPublicoExt = nombreServPublicoExt;
	}
	/**
	 * @return the appaternoServPublicoExt
	 */
	public String getAppaternoServPublicoExt() {
		return appaternoServPublicoExt;
	}
	/**
	 * @param appaternoServPublicoExt the appaternoServPublicoExt to set
	 */
	public void setAppaternoServPublicoExt(String appaternoServPublicoExt) {
		this.appaternoServPublicoExt = appaternoServPublicoExt;
	}
	/**
	 * @return the apmaternoServPublico
	 */
	public String getApmaternoServPublico() {
		return apmaternoServPublico;
	}
	/**
	 * @param apmaternoServPublico the apmaternoServPublico to set
	 */
	public void setApmaternoServPublico(String apmaternoServPublico) {
		this.apmaternoServPublico = apmaternoServPublico;
	}
	/**
	 * Método de acceso al campo strDetenidos.
	 * @return El valor del campo strDetenidos
	 */
	public String getStrDetenidos() {
		return strDetenidos;
	}
	/**
	 * Asigna el valor al campo strDetenidos.
	 * @param strDetenidos el valor strDetenidos a asignar
	 */
	public void setStrDetenidos(String strDetenidos) {
		this.strDetenidos = strDetenidos;
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
	 * Método de acceso al campo idCondicion.
	 * @return El valor del campo idCondicion
	 */
	public String getIdCondicion() {
		return idCondicion;
	}
	/**
	 * Asigna el valor al campo idCondicion.
	 * @param idCondicion el valor idCondicion a asignar
	 */
	public void setIdCondicion(String idCondicion) {
		this.idCondicion = idCondicion;
	}
	/**
	 * Método de acceso al campo esMenorDeEdad.
	 * @return El valor del campo esMenorDeEdad
	 */
	public Boolean getEsMenorDeEdad() {
		return esMenorDeEdad;
	}
	/**
	 * Asigna el valor al campo esMenorDeEdad.
	 * @param esMenorDeEdad el valor esMenorDeEdad a asignar
	 */
	public void setEsMenorDeEdad(Boolean esMenorDeEdad) {
		this.esMenorDeEdad = esMenorDeEdad;
	}
	/**
	 * Método de acceso al campo estaDetenido.
	 * @return El valor del campo estaDetenido
	 */
	public Boolean getEstaDetenido() {
		return estaDetenido;
	}
	/**
	 * Asigna el valor al campo estaDetenido.
	 * @param estaDetenido el valor estaDetenido a asignar
	 */
	public void setEstaDetenido(Boolean estaDetenido) {
		this.estaDetenido = estaDetenido;
	}
	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método de acceso al campo apellidoPaterno.
	 * @return El valor del campo apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * Asigna el valor al campo apellidoPaterno.
	 * @param apellidoPaterno el valor apellidoPaterno a asignar
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * Método de acceso al campo apellidoMaterno.
	 * @return El valor del campo apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * Asigna el valor al campo apellidoMaterno.
	 * @param apellidoMaterno el valor apellidoMaterno a asignar
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * Método de acceso al campo curp.
	 * @return El valor del campo curp
	 */
	public String getCurp() {
		return curp;
	}
	/**
	 * Asigna el valor al campo curp.
	 * @param curp el valor curp a asignar
	 */
	public void setCurp(String curp) {
		this.curp = curp;
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
	 * Método de acceso al campo fechaIngreso.
	 * @return El valor del campo fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * Asigna el valor al campo fechaIngreso.
	 * @param fechaIngreso el valor fechaIngreso a asignar
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	/**
	 * Método de acceso al campo idioma.
	 * @return El valor del campo idioma
	 */
	public String getIdioma() {
		return idioma;
	}
	/**
	 * Asigna el valor al campo idioma.
	 * @param idioma el valor idioma a asignar
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	/**
	 * Método de acceso al campo religion.
	 * @return El valor del campo religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * Asigna el valor al campo religion.
	 * @param religion el valor religion a asignar
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * Método de acceso al campo escolaridad.
	 * @return El valor del campo escolaridad
	 */
	public String getEscolaridad() {
		return escolaridad;
	}
	/**
	 * Asigna el valor al campo escolaridad.
	 * @param escolaridad el valor escolaridad a asignar
	 */
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}
	/**
	 * Método de acceso al campo estadoCivil.
	 * @return El valor del campo estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}
	/**
	 * Asigna el valor al campo estadoCivil.
	 * @param estadoCivil el valor estadoCivil a asignar
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	/**
	 * Método de acceso al campo sexo.
	 * @return El valor del campo sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * Asigna el valor al campo sexo.
	 * @param sexo el valor sexo a asignar
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * Método de acceso al campo fechaNacimiento.
	 * @return El valor del campo fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * Asigna el valor al campo fechaNacimiento.
	 * @param fechaNacimiento el valor fechaNacimiento a asignar
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * Método de acceso al campo edadAproximada.
	 * @return El valor del campo edadAproximada
	 */
	public Short getEdadAproximada() {
		return edadAproximada;
	}
	/**
	 * Asigna el valor al campo edadAproximada.
	 * @param edadAproximada el valor edadAproximada a asignar
	 */
	public void setEdadAproximada(Short edadAproximada) {
		this.edadAproximada = edadAproximada;
	}
	/**
	 * Método de acceso al campo pais.
	 * @return El valor del campo pais
	 */
	public String getPais() {
		return pais;
	}
	/**
	 * Asigna el valor al campo pais.
	 * @param pais el valor pais a asignar
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	/**
	 * Método de acceso al campo codigoPostal.
	 * @return El valor del campo codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}
	/**
	 * Asigna el valor al campo codigoPostal.
	 * @param codigoPostal el valor codigoPostal a asignar
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	/**
	 * Método de acceso al campo entidadFederativa.
	 * @return El valor del campo entidadFederativa
	 */
	public String getEntidadFederativa() {
		return entidadFederativa;
	}
	/**
	 * Asigna el valor al campo entidadFederativa.
	 * @param entidadFederativa el valor entidadFederativa a asignar
	 */
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
	/**
	 * Método de acceso al campo ciudad.
	 * @return El valor del campo ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * Asigna el valor al campo ciudad.
	 * @param ciudad el valor ciudad a asignar
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * Método de acceso al campo delegacionMunicipio.
	 * @return El valor del campo delegacionMunicipio
	 */
	public String getDelegacionMunicipio() {
		return delegacionMunicipio;
	}
	/**
	 * Asigna el valor al campo delegacionMunicipio.
	 * @param delegacionMunicipio el valor delegacionMunicipio a asignar
	 */
	public void setDelegacionMunicipio(String delegacionMunicipio) {
		this.delegacionMunicipio = delegacionMunicipio;
	}
	/**
	 * Método de acceso al campo asentamientoColonia.
	 * @return El valor del campo asentamientoColonia
	 */
	public String getAsentamientoColonia() {
		return asentamientoColonia;
	}
	/**
	 * Asigna el valor al campo asentamientoColonia.
	 * @param asentamientoColonia el valor asentamientoColonia a asignar
	 */
	public void setAsentamientoColonia(String asentamientoColonia) {
		this.asentamientoColonia = asentamientoColonia;
	}
	/**
	 * Método de acceso al campo tipoAsentamiento.
	 * @return El valor del campo tipoAsentamiento
	 */
	public String getTipoAsentamiento() {
		return tipoAsentamiento;
	}
	/**
	 * Asigna el valor al campo tipoAsentamiento.
	 * @param tipoAsentamiento el valor tipoAsentamiento a asignar
	 */
	public void setTipoAsentamiento(String tipoAsentamiento) {
		this.tipoAsentamiento = tipoAsentamiento;
	}
	/**
	 * Método de acceso al campo tipoCalle.
	 * @return El valor del campo tipoCalle
	 */
	public String getTipoCalle() {
		return tipoCalle;
	}
	/**
	 * Asigna el valor al campo tipoCalle.
	 * @param tipoCalle el valor tipoCalle a asignar
	 */
	public void setTipoCalle(String tipoCalle) {
		this.tipoCalle = tipoCalle;
	}
	/**
	 * Método de acceso al campo calle.
	 * @return El valor del campo calle
	 */
	public String getCalle() {
		return calle;
	}
	/**
	 * Asigna el valor al campo calle.
	 * @param calle el valor calle a asignar
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	/**
	 * Método de acceso al campo numExterior.
	 * @return El valor del campo numExterior
	 */
	public String getNumExterior() {
		return numExterior;
	}
	/**
	 * Asigna el valor al campo numExterior.
	 * @param numExterior el valor numExterior a asignar
	 */
	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}
	/**
	 * Método de acceso al campo numInterior.
	 * @return El valor del campo numInterior
	 */
	public String getNumInterior() {
		return numInterior;
	}
	/**
	 * Asigna el valor al campo numInterior.
	 * @param numInterior el valor numInterior a asignar
	 */
	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}
	/**
	 * Método de acceso al campo referencias.
	 * @return El valor del campo referencias
	 */
	public String getReferencias() {
		return referencias;
	}
	/**
	 * Asigna el valor al campo referencias.
	 * @param referencias el valor referencias a asignar
	 */
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	/**
	 * Método de acceso al campo entreCalle.
	 * @return El valor del campo entreCalle
	 */
	public String getEntreCalle() {
		return entreCalle;
	}
	/**
	 * Asigna el valor al campo entreCalle.
	 * @param entreCalle el valor entreCalle a asignar
	 */
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}
	/**
	 * Método de acceso al campo ycalle.
	 * @return El valor del campo ycalle
	 */
	public String getYcalle() {
		return ycalle;
	}
	/**
	 * Asigna el valor al campo ycalle.
	 * @param ycalle el valor ycalle a asignar
	 */
	public void setYcalle(String ycalle) {
		this.ycalle = ycalle;
	}
	/**
	 * Método de acceso al campo aliasDomicilio.
	 * @return El valor del campo aliasDomicilio
	 */
	public String getAliasDomicilio() {
		return aliasDomicilio;
	}
	/**
	 * Asigna el valor al campo aliasDomicilio.
	 * @param aliasDomicilio el valor aliasDomicilio a asignar
	 */
	public void setAliasDomicilio(String aliasDomicilio) {
		this.aliasDomicilio = aliasDomicilio;
	}
	/**
	 * Método de acceso al campo edificio.
	 * @return El valor del campo edificio
	 */
	public String getEdificio() {
		return edificio;
	}
	/**
	 * Asigna el valor al campo edificio.
	 * @param edificio el valor edificio a asignar
	 */
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	/**
	 * Método de acceso al campo mismoDomicilioNotificaciones.
	 * @return El valor del campo mismoDomicilioNotificaciones
	 */
	public String getMismoDomicilioNotificaciones() {
		return mismoDomicilioNotificaciones;
	}
	/**
	 * Asigna el valor al campo mismoDomicilioNotificaciones.
	 * @param mismoDomicilioNotificaciones el valor mismoDomicilioNotificaciones a asignar
	 */
	public void setMismoDomicilioNotificaciones(String mismoDomicilioNotificaciones) {
		this.mismoDomicilioNotificaciones = mismoDomicilioNotificaciones;
	}
	/**
	 * Método de acceso al campo paisNotif.
	 * @return El valor del campo paisNotif
	 */
	public String getPaisNotif() {
		return paisNotif;
	}
	/**
	 * Asigna el valor al campo paisNotif.
	 * @param paisNotif el valor paisNotif a asignar
	 */
	public void setPaisNotif(String paisNotif) {
		this.paisNotif = paisNotif;
	}
	/**
	 * Método de acceso al campo codigoPostalNotif.
	 * @return El valor del campo codigoPostalNotif
	 */
	public String getCodigoPostalNotif() {
		return codigoPostalNotif;
	}
	/**
	 * Asigna el valor al campo codigoPostalNotif.
	 * @param codigoPostalNotif el valor codigoPostalNotif a asignar
	 */
	public void setCodigoPostalNotif(String codigoPostalNotif) {
		this.codigoPostalNotif = codigoPostalNotif;
	}
	/**
	 * Método de acceso al campo entidadFederativaNotif.
	 * @return El valor del campo entidadFederativaNotif
	 */
	public String getEntidadFederativaNotif() {
		return entidadFederativaNotif;
	}
	/**
	 * Asigna el valor al campo entidadFederativaNotif.
	 * @param entidadFederativaNotif el valor entidadFederativaNotif a asignar
	 */
	public void setEntidadFederativaNotif(String entidadFederativaNotif) {
		this.entidadFederativaNotif = entidadFederativaNotif;
	}
	/**
	 * Método de acceso al campo ciudadNotif.
	 * @return El valor del campo ciudadNotif
	 */
	public String getCiudadNotif() {
		return ciudadNotif;
	}
	/**
	 * Asigna el valor al campo ciudadNotif.
	 * @param ciudadNotif el valor ciudadNotif a asignar
	 */
	public void setCiudadNotif(String ciudadNotif) {
		this.ciudadNotif = ciudadNotif;
	}
	/**
	 * Método de acceso al campo delegacionMunicipioNotif.
	 * @return El valor del campo delegacionMunicipioNotif
	 */
	public String getDelegacionMunicipioNotif() {
		return delegacionMunicipioNotif;
	}
	/**
	 * Asigna el valor al campo delegacionMunicipioNotif.
	 * @param delegacionMunicipioNotif el valor delegacionMunicipioNotif a asignar
	 */
	public void setDelegacionMunicipioNotif(String delegacionMunicipioNotif) {
		this.delegacionMunicipioNotif = delegacionMunicipioNotif;
	}
	/**
	 * Método de acceso al campo asentamientoColoniaNotif.
	 * @return El valor del campo asentamientoColoniaNotif
	 */
	public String getAsentamientoColoniaNotif() {
		return asentamientoColoniaNotif;
	}
	/**
	 * Asigna el valor al campo asentamientoColoniaNotif.
	 * @param asentamientoColoniaNotif el valor asentamientoColoniaNotif a asignar
	 */
	public void setAsentamientoColoniaNotif(String asentamientoColoniaNotif) {
		this.asentamientoColoniaNotif = asentamientoColoniaNotif;
	}
	/**
	 * Método de acceso al campo tipoAsentamientoNotif.
	 * @return El valor del campo tipoAsentamientoNotif
	 */
	public String getTipoAsentamientoNotif() {
		return tipoAsentamientoNotif;
	}
	/**
	 * Asigna el valor al campo tipoAsentamientoNotif.
	 * @param tipoAsentamientoNotif el valor tipoAsentamientoNotif a asignar
	 */
	public void setTipoAsentamientoNotif(String tipoAsentamientoNotif) {
		this.tipoAsentamientoNotif = tipoAsentamientoNotif;
	}
	/**
	 * Método de acceso al campo tipoCalleNotif.
	 * @return El valor del campo tipoCalleNotif
	 */
	public String getTipoCalleNotif() {
		return tipoCalleNotif;
	}
	/**
	 * Asigna el valor al campo tipoCalleNotif.
	 * @param tipoCalleNotif el valor tipoCalleNotif a asignar
	 */
	public void setTipoCalleNotif(String tipoCalleNotif) {
		this.tipoCalleNotif = tipoCalleNotif;
	}
	/**
	 * Método de acceso al campo calleNotif.
	 * @return El valor del campo calleNotif
	 */
	public String getCalleNotif() {
		return calleNotif;
	}
	/**
	 * Asigna el valor al campo calleNotif.
	 * @param calleNotif el valor calleNotif a asignar
	 */
	public void setCalleNotif(String calleNotif) {
		this.calleNotif = calleNotif;
	}
	/**
	 * Método de acceso al campo numExteriorNotif.
	 * @return El valor del campo numExteriorNotif
	 */
	public String getNumExteriorNotif() {
		return numExteriorNotif;
	}
	/**
	 * Asigna el valor al campo numExteriorNotif.
	 * @param numExteriorNotif el valor numExteriorNotif a asignar
	 */
	public void setNumExteriorNotif(String numExteriorNotif) {
		this.numExteriorNotif = numExteriorNotif;
	}
	/**
	 * Método de acceso al campo numInteriorNotif.
	 * @return El valor del campo numInteriorNotif
	 */
	public String getNumInteriorNotif() {
		return numInteriorNotif;
	}
	/**
	 * Asigna el valor al campo numInteriorNotif.
	 * @param numInteriorNotif el valor numInteriorNotif a asignar
	 */
	public void setNumInteriorNotif(String numInteriorNotif) {
		this.numInteriorNotif = numInteriorNotif;
	}
	/**
	 * Método de acceso al campo referenciasNotif.
	 * @return El valor del campo referenciasNotif
	 */
	public String getReferenciasNotif() {
		return referenciasNotif;
	}
	/**
	 * Asigna el valor al campo referenciasNotif.
	 * @param referenciasNotif el valor referenciasNotif a asignar
	 */
	public void setReferenciasNotif(String referenciasNotif) {
		this.referenciasNotif = referenciasNotif;
	}
	/**
	 * Método de acceso al campo entreCalleNotif.
	 * @return El valor del campo entreCalleNotif
	 */
	public String getEntreCalleNotif() {
		return entreCalleNotif;
	}
	/**
	 * Asigna el valor al campo entreCalleNotif.
	 * @param entreCalleNotif el valor entreCalleNotif a asignar
	 */
	public void setEntreCalleNotif(String entreCalleNotif) {
		this.entreCalleNotif = entreCalleNotif;
	}
	/**
	 * Método de acceso al campo ycalleNotif.
	 * @return El valor del campo ycalleNotif
	 */
	public String getYcalleNotif() {
		return ycalleNotif;
	}
	/**
	 * Asigna el valor al campo ycalleNotif.
	 * @param ycalleNotif el valor ycalleNotif a asignar
	 */
	public void setYcalleNotif(String ycalleNotif) {
		this.ycalleNotif = ycalleNotif;
	}
	/**
	 * Método de acceso al campo aliasDomicilioNotif.
	 * @return El valor del campo aliasDomicilioNotif
	 */
	public String getAliasDomicilioNotif() {
		return aliasDomicilioNotif;
	}
	/**
	 * Asigna el valor al campo aliasDomicilioNotif.
	 * @param aliasDomicilioNotif el valor aliasDomicilioNotif a asignar
	 */
	public void setAliasDomicilioNotif(String aliasDomicilioNotif) {
		this.aliasDomicilioNotif = aliasDomicilioNotif;
	}
	/**
	 * Método de acceso al campo edificioNotif.
	 * @return El valor del campo edificioNotif
	 */
	public String getEdificioNotif() {
		return edificioNotif;
	}
	/**
	 * Asigna el valor al campo edificioNotif.
	 * @param edificioNotif el valor edificioNotif a asignar
	 */
	public void setEdificioNotif(String edificioNotif) {
		this.edificioNotif = edificioNotif;
	}
	/**
	 * Método de acceso al campo medioContactoTelefono.
	 * @return El valor del campo medioContactoTelefono
	 */
	public String getMedioContactoTelefono() {
		return medioContactoTelefono;
	}
	/**
	 * Asigna el valor al campo medioContactoTelefono.
	 * @param medioContactoTelefono el valor medioContactoTelefono a asignar
	 */
	public void setMedioContactoTelefono(String medioContactoTelefono) {
		this.medioContactoTelefono = medioContactoTelefono;
	}
	/**
	 * Método de acceso al campo medioContactoCorreo.
	 * @return El valor del campo medioContactoCorreo
	 */
	public String getMedioContactoCorreo() {
		return medioContactoCorreo;
	}
	/**
	 * Asigna el valor al campo medioContactoCorreo.
	 * @param medioContactoCorreo el valor medioContactoCorreo a asignar
	 */
	public void setMedioContactoCorreo(String medioContactoCorreo) {
		this.medioContactoCorreo = medioContactoCorreo;
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
	/**
	 * Método de acceso al campo calidadDelIndividuo.
	 * @return El valor del campo calidadDelIndividuo
	 */
	public Short getCalidadDelIndividuo() {
		return calidadDelIndividuo;
	}
	/**
	 * Asigna el valor al campo calidadDelIndividuo.
	 * @param calidadDelIndividuo el valor calidadDelIndividuo a asignar
	 */
	public void setCalidadDelIndividuo(Short calidadDelIndividuo) {
		this.calidadDelIndividuo = calidadDelIndividuo;
	}
	/**
	 * Método de acceso al campo esServidorPublico.
	 * @return El valor del campo esServidorPublico
	 */
	public Boolean getEsServidorPublico() {
		return esServidorPublico;
	}
	/**
	 * Asigna el valor al campo esServidorPublico.
	 * @param esServidorPublico el valor esServidorPublico a asignar
	 */
	public void setEsServidorPublico(Boolean esServidorPublico) {
		this.esServidorPublico = esServidorPublico;
	}
	/**
	 * Método de acceso al campo esAnonimo.
	 * @return El valor del campo esAnonimo
	 */
	public Boolean getEsAnonimo() {
		return esAnonimo;
	}
	/**
	 * Asigna el valor al campo esAnonimo.
	 * @param esAnonimo el valor esAnonimo a asignar
	 */
	public void setEsAnonimo(Boolean esAnonimo) {
		this.esAnonimo = esAnonimo;
	}
	/**
	 * Método de acceso al campo esPersonaMoral.
	 * @return El valor del campo esPersonaMoral
	 */
	public Boolean getEsPersonaMoral() {
		return esPersonaMoral;
	}
	/**
	 * Asigna el valor al campo esPersonaMoral.
	 * @param esPersonaMoral el valor esPersonaMoral a asignar
	 */
	public void setEsPersonaMoral(Boolean esPersonaMoral) {
		this.esPersonaMoral = esPersonaMoral;
	}
	/**
	 * Método de acceso al campo esVictimayDenunciante.
	 * @return El valor del campo esVictimayDenunciante
	 */
	public Boolean getEsVictimayDenunciante() {
		return esVictimayDenunciante;
	}
	/**
	 * Asigna el valor al campo esVictimayDenunciante.
	 * @param esVictimayDenunciante el valor esVictimayDenunciante a asignar
	 */
	public void setEsVictimayDenunciante(Boolean esVictimayDenunciante) {
		this.esVictimayDenunciante = esVictimayDenunciante;
	}
	/**
	 * Método de acceso al campo esDesconocido.
	 * @return El valor del campo esDesconocido
	 */
	public Boolean getEsDesconocido() {
		return esDesconocido;
	}
	/**
	 * Asigna el valor al campo esDesconocido.
	 * @param esDesconocido el valor esDesconocido a asignar
	 */
	public void setEsDesconocido(Boolean esDesconocido) {
		this.esDesconocido = esDesconocido;
	}
	/**
	 * Método de acceso al campo alias.
	 * @return El valor del campo alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * Asigna el valor al campo alias.
	 * @param alias el valor alias a asignar
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * Método de acceso al campo ocupacion.
	 * @return El valor del campo ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}
	/**
	 * Asigna el valor al campo ocupacion.
	 * @param ocupacion el valor ocupacion a asignar
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	/**
	 * Método de acceso al campo nacionalidad.
	 * @return El valor del campo nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * Asigna el valor al campo nacionalidad.
	 * @param nacionalidad el valor nacionalidad a asignar
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * Método de acceso al campo longitud.
	 * @return El valor del campo longitud
	 */
	public String getLongitud() {
		return longitud;
	}
	/**
	 * Asigna el valor al campo longitud.
	 * @param longitud el valor longitud a asignar
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	/**
	 * Método de acceso al campo latitud.
	 * @return El valor del campo latitud
	 */
	public String getLatitud() {
		return latitud;
	}
	/**
	 * Asigna el valor al campo latitud.
	 * @param latitud el valor latitud a asignar
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	/**
	 * Método de acceso al campo longitudNotif.
	 * @return El valor del campo longitudNotif
	 */
	public String getLongitudNotif() {
		return longitudNotif;
	}
	/**
	 * Asigna el valor al campo longitudNotif.
	 * @param longitudNotif el valor longitudNotif a asignar
	 */
	public void setLongitudNotif(String longitudNotif) {
		this.longitudNotif = longitudNotif;
	}
	/**
	 * Método de acceso al campo latitudNotif.
	 * @return El valor del campo latitudNotif
	 */
	public String getLatitudNotif() {
		return latitudNotif;
	}
	/**
	 * Asigna el valor al campo latitudNotif.
	 * @param latitudNotif el valor latitudNotif a asignar
	 */
	public void setLatitudNotif(String latitudNotif) {
		this.latitudNotif = latitudNotif;
	}
	/**
	 * Método de acceso al campo esTraductor.
	 * @return El valor del campo esTraductor
	 */
	public Boolean getEsTraductor() {
		return esTraductor;
	}
	/**
	 * Asigna el valor al campo esTraductor.
	 * @param esTraductor el valor esTraductor a asignar
	 */
	public void setEsTraductor(Boolean esTraductor) {
		this.esTraductor = esTraductor;
	}
	/**
	 * Método de acceso al campo nombreOrg.
	 * @return El valor del campo nombreOrg
	 */
	public String getNombreOrg() {
		return nombreOrg;
	}
	/**
	 * Asigna el valor al campo nombreOrg.
	 * @param nombreOrg el valor nombreOrg a asignar
	 */
	public void setNombreOrg(String nombreOrg) {
		this.nombreOrg = nombreOrg;
	}
	/**
	 * Método de acceso al campo nombreCortoOrg.
	 * @return El valor del campo nombreCortoOrg
	 */
	public String getNombreCortoOrg() {
		return nombreCortoOrg;
	}
	/**
	 * Asigna el valor al campo nombreCortoOrg.
	 * @param nombreCortoOrg el valor nombreCortoOrg a asignar
	 */
	public void setNombreCortoOrg(String nombreCortoOrg) {
		this.nombreCortoOrg = nombreCortoOrg;
	}
	/**
	 * Método de acceso al campo dirInternet.
	 * @return El valor del campo dirInternet
	 */
	public String getDirInternet() {
		return dirInternet;
	}
	/**
	 * Asigna el valor al campo dirInternet.
	 * @param dirInternet el valor dirInternet a asignar
	 */
	public void setDirInternet(String dirInternet) {
		this.dirInternet = dirInternet;
	}
	/**
	 * Método de acceso al campo tipoOrganizacion.
	 * @return El valor del campo tipoOrganizacion
	 */
	public String getTipoOrganizacion() {
		return tipoOrganizacion;
	}
	/**
	 * Asigna el valor al campo tipoOrganizacion.
	 * @param tipoOrganizacion el valor tipoOrganizacion a asignar
	 */
	public void setTipoOrganizacion(String tipoOrganizacion) {
		this.tipoOrganizacion = tipoOrganizacion;
	}
	/**
	 * Método de acceso al campo tipoOrgFormal.
	 * @return El valor del campo tipoOrgFormal
	 */
	public String getTipoOrgFormal() {
		return tipoOrgFormal;
	}
	/**
	 * Asigna el valor al campo tipoOrgFormal.
	 * @param tipoOrgFormal el valor tipoOrgFormal a asignar
	 */
	public void setTipoOrgFormal(String tipoOrgFormal) {
		this.tipoOrgFormal = tipoOrgFormal;
	}
	/**
	 * Método de acceso al campo noActaOrgFormal.
	 * @return El valor del campo noActaOrgFormal
	 */
	public String getNoActaOrgFormal() {
		return noActaOrgFormal;
	}
	/**
	 * Asigna el valor al campo noActaOrgFormal.
	 * @param noActaOrgFormal el valor noActaOrgFormal a asignar
	 */
	public void setNoActaOrgFormal(String noActaOrgFormal) {
		this.noActaOrgFormal = noActaOrgFormal;
	}
	/**
	 * Método de acceso al campo rfcOrgFormal.
	 * @return El valor del campo rfcOrgFormal
	 */
	public String getRfcOrgFormal() {
		return rfcOrgFormal;
	}
	/**
	 * Asigna el valor al campo rfcOrgFormal.
	 * @param rfcOrgFormal el valor rfcOrgFormal a asignar
	 */
	public void setRfcOrgFormal(String rfcOrgFormal) {
		this.rfcOrgFormal = rfcOrgFormal;
	}
	/**
	 * Método de acceso al campo giroOrgFormal.
	 * @return El valor del campo giroOrgFormal
	 */
	public String getGiroOrgFormal() {
		return giroOrgFormal;
	}
	/**
	 * Asigna el valor al campo giroOrgFormal.
	 * @param giroOrgFormal el valor giroOrgFormal a asignar
	 */
	public void setGiroOrgFormal(String giroOrgFormal) {
		this.giroOrgFormal = giroOrgFormal;
	}
	/**
	 * Método de acceso al campo datosRepOrgFormal.
	 * @return El valor del campo datosRepOrgFormal
	 */
	public String getDatosRepOrgFormal() {
		return datosRepOrgFormal;
	}
	/**
	 * Asigna el valor al campo datosRepOrgFormal.
	 * @param datosRepOrgFormal el valor datosRepOrgFormal a asignar
	 */
	public void setDatosRepOrgFormal(String datosRepOrgFormal) {
		this.datosRepOrgFormal = datosRepOrgFormal;
	}
	/**
	 * Método de acceso al campo areaOrgNoFormal.
	 * @return El valor del campo areaOrgNoFormal
	 */
	public String getAreaOrgNoFormal() {
		return areaOrgNoFormal;
	}
	/**
	 * Asigna el valor al campo areaOrgNoFormal.
	 * @param areaOrgNoFormal el valor areaOrgNoFormal a asignar
	 */
	public void setAreaOrgNoFormal(String areaOrgNoFormal) {
		this.areaOrgNoFormal = areaOrgNoFormal;
	}
	/**
	 * Método de acceso al campo giroOrgNoFormal.
	 * @return El valor del campo giroOrgNoFormal
	 */
	public String getGiroOrgNoFormal() {
		return giroOrgNoFormal;
	}
	/**
	 * Asigna el valor al campo giroOrgNoFormal.
	 * @param giroOrgNoFormal el valor giroOrgNoFormal a asignar
	 */
	public void setGiroOrgNoFormal(String giroOrgNoFormal) {
		this.giroOrgNoFormal = giroOrgNoFormal;
	}
	/**
	 * Método de acceso al campo descOrgDelictiva.
	 * @return El valor del campo descOrgDelictiva
	 */
	public String getDescOrgDelictiva() {
		return descOrgDelictiva;
	}
	/**
	 * Asigna el valor al campo descOrgDelictiva.
	 * @param descOrgDelictiva el valor descOrgDelictiva a asignar
	 */
	public void setDescOrgDelictiva(String descOrgDelictiva) {
		this.descOrgDelictiva = descOrgDelictiva;
	}
	/**
	 * Método de acceso al campo nivelDepOrgSectorPublico.
	 * @return El valor del campo nivelDepOrgSectorPublico
	 */
	public String getNivelDepOrgSectorPublico() {
		return nivelDepOrgSectorPublico;
	}
	/**
	 * Asigna el valor al campo nivelDepOrgSectorPublico.
	 * @param nivelDepOrgSectorPublico el valor nivelDepOrgSectorPublico a asignar
	 */
	public void setNivelDepOrgSectorPublico(String nivelDepOrgSectorPublico) {
		this.nivelDepOrgSectorPublico = nivelDepOrgSectorPublico;
	}
	/**
	 * Método de acceso al campo tipoDepOrgSectorPublico.
	 * @return El valor del campo tipoDepOrgSectorPublico
	 */
	public String getTipoDepOrgSectorPublico() {
		return tipoDepOrgSectorPublico;
	}
	/**
	 * Asigna el valor al campo tipoDepOrgSectorPublico.
	 * @param tipoDepOrgSectorPublico el valor tipoDepOrgSectorPublico a asignar
	 */
	public void setTipoDepOrgSectorPublico(String tipoDepOrgSectorPublico) {
		this.tipoDepOrgSectorPublico = tipoDepOrgSectorPublico;
	}
	/**
	 * Método de acceso al campo orgsSecPubOrgSectorPublico.
	 * @return El valor del campo orgsSecPubOrgSectorPublico
	 */
	public String getOrgsSecPubOrgSectorPublico() {
		return orgsSecPubOrgSectorPublico;
	}
	/**
	 * Asigna el valor al campo orgsSecPubOrgSectorPublico.
	 * @param orgsSecPubOrgSectorPublico el valor orgsSecPubOrgSectorPublico a asignar
	 */
	public void setOrgsSecPubOrgSectorPublico(String orgsSecPubOrgSectorPublico) {
		this.orgsSecPubOrgSectorPublico = orgsSecPubOrgSectorPublico;
	}
	/**
	 * Método de acceso al campo areaOrgSectorPublico.
	 * @return El valor del campo areaOrgSectorPublico
	 */
	public String getAreaOrgSectorPublico() {
		return areaOrgSectorPublico;
	}
	/**
	 * Asigna el valor al campo areaOrgSectorPublico.
	 * @param areaOrgSectorPublico el valor areaOrgSectorPublico a asignar
	 */
	public void setAreaOrgSectorPublico(String areaOrgSectorPublico) {
		this.areaOrgSectorPublico = areaOrgSectorPublico;
	}
	/**
	 * Método de acceso al campo tipoOrgVirtual.
	 * @return El valor del campo tipoOrgVirtual
	 */
	public String getTipoOrgVirtual() {
		return tipoOrgVirtual;
	}
	/**
	 * Asigna el valor al campo tipoOrgVirtual.
	 * @param tipoOrgVirtual el valor tipoOrgVirtual a asignar
	 */
	public void setTipoOrgVirtual(String tipoOrgVirtual) {
		this.tipoOrgVirtual = tipoOrgVirtual;
	}
	/**
	 * Método de acceso al campo dirElectronicaOrgVirtual.
	 * @return El valor del campo dirElectronicaOrgVirtual
	 */
	public String getDirElectronicaOrgVirtual() {
		return dirElectronicaOrgVirtual;
	}
	/**
	 * Asigna el valor al campo dirElectronicaOrgVirtual.
	 * @param dirElectronicaOrgVirtual el valor dirElectronicaOrgVirtual a asignar
	 */
	public void setDirElectronicaOrgVirtual(String dirElectronicaOrgVirtual) {
		this.dirElectronicaOrgVirtual = dirElectronicaOrgVirtual;
	}
	/**
	 * Método de acceso al campo propositoOrgVirtual.
	 * @return El valor del campo propositoOrgVirtual
	 */
	public String getPropositoOrgVirtual() {
		return propositoOrgVirtual;
	}
	/**
	 * Asigna el valor al campo propositoOrgVirtual.
	 * @param propositoOrgVirtual el valor propositoOrgVirtual a asignar
	 */
	public void setPropositoOrgVirtual(String propositoOrgVirtual) {
		this.propositoOrgVirtual = propositoOrgVirtual;
	}
	
	/**
	 * @return the idIndividuo
	 */
	public Long getIdIndividuo() {
		return idIndividuo;
	}
	/**
	 * @param idIndividuo the idIndividuo to set
	 */
	public void setIdIndividuo(Long idIndividuo) {
		this.idIndividuo = idIndividuo;
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
	 * @return the idOrganizacion
	 */
	public Long getIdOrganizacion() {
		return idOrganizacion;
	}
	/**
	 * @param idOrganizacion the idOrganizacion to set
	 */
	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
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
	 * @param entFederativaNacimiento the entFederativaNacimiento to seta
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
	 * @param municipioNacimiento the municipioNcimiento to set
	 */
	public void setMunicipioNacimiento(String municipioNacimiento) {
		this.municipioNacimiento = municipioNacimiento;
	}
	/**
	 * Método de acceso al campo tamanoBoca.
	 * @return El valor del campo tamanoBoca
	 */
	public String getTamanoBoca() {
		return tamanoBoca;
	}
	/**
	 * Asigna el valor al campo tamanoBoca.
	 * @param tamanoBoca el valor tamanoBoca a asignar
	 */
	public void setTamanoBoca(String tamanoBoca) {
		this.tamanoBoca = tamanoBoca;
	}
	/**
	 * Método de acceso al campo tipoCara.
	 * @return El valor del campo tipoCara
	 */
	public String getTipoCara() {
		return tipoCara;
	}
	/**
	 * Asigna el valor al campo tipoCara.
	 * @param tipoCara el valor tipoCara a asignar
	 */
	public void setTipoCara(String tipoCara) {
		this.tipoCara = tipoCara;
	}
	/**
	 * Método de acceso al campo formaMenton.
	 * @return El valor del campo formaMenton
	 */
	public String getFormaMenton() {
		return formaMenton;
	}
	/**
	 * Asigna el valor al campo formaMenton.
	 * @param formaMenton el valor formaMenton a asignar
	 */
	public void setFormaMenton(String formaMenton) {
		this.formaMenton = formaMenton;
	}
	/**
	 * Método de acceso al campo tipoMenton.
	 * @return El valor del campo tipoMenton
	 */
	public String getTipoMenton() {
		return tipoMenton;
	}
	/**
	 * Asigna el valor al campo tipoMenton.
	 * @param tipoMenton el valor tipoMenton a asignar
	 */
	public void setTipoMenton(String tipoMenton) {
		this.tipoMenton = tipoMenton;
	}
	/**
	 * Método de acceso al campo tez.
	 * @return El valor del campo tez
	 */
	public String getTez() {
		return tez;
	}
	/**
	 * Asigna el valor al campo tez.
	 * @param tez el valor tez a asignar
	 */
	public void setTez(String tez) {
		this.tez = tez;
	}
	/**
	 * Método de acceso al campo inclinacionMenton.
	 * @return El valor del campo inclinacionMenton
	 */
	public String getInclinacionMenton() {
		return inclinacionMenton;
	}
	/**
	 * Asigna el valor al campo inclinacionMenton.
	 * @param inclinacionMenton el valor inclinacionMenton a asignar
	 */
	public void setInclinacionMenton(String inclinacionMenton) {
		this.inclinacionMenton = inclinacionMenton;
	}
	/**
	 * Método de acceso al campo colorCabello.
	 * @return El valor del campo colorCabello
	 */
	public String getColorCabello() {
		return colorCabello;
	}
	/**
	 * Asigna el valor al campo colorCabello.
	 * @param colorCabello el valor colorCabello a asignar
	 */
	public void setColorCabello(String colorCabello) {
		this.colorCabello = colorCabello;
	}
	/**
	 * Método de acceso al campo formaCabello.
	 * @return El valor del campo formaCabello
	 */
	public String getFormaCabello() {
		return formaCabello;
	}
	/**
	 * Asigna el valor al campo formaCabello.
	 * @param formaCabello el valor formaCabello a asignar
	 */
	public void setFormaCabello(String formaCabello) {
		this.formaCabello = formaCabello;
	}
	/**
	 * Método de acceso al campo calvieTipo.
	 * @return El valor del campo calvieTipo
	 */
	public String getCalvieTipo() {
		return calvieTipo;
	}
	/**
	 * Asigna el valor al campo calvieTipo.
	 * @param calvieTipo el valor calvieTipo a asignar
	 */
	public void setCalvieTipo(String calvieTipo) {
		this.calvieTipo = calvieTipo;
	}
	/**
	 * Método de acceso al campo cabelloImplantacion.
	 * @return El valor del campo cabelloImplantacion
	 */
	public String getCabelloImplantacion() {
		return cabelloImplantacion;
	}
	/**
	 * Asigna el valor al campo cabelloImplantacion.
	 * @param cabelloImplantacion el valor cabelloImplantacion a asignar
	 */
	public void setCabelloImplantacion(String cabelloImplantacion) {
		this.cabelloImplantacion = cabelloImplantacion;
	}
	/**
	 * Método de acceso al campo cantidadCabello.
	 * @return El valor del campo cantidadCabello
	 */
	public String getCantidadCabello() {
		return cantidadCabello;
	}
	/**
	 * Asigna el valor al campo cantidadCabello.
	 * @param cantidadCabello el valor cantidadCabello a asignar
	 */
	public void setCantidadCabello(String cantidadCabello) {
		this.cantidadCabello = cantidadCabello;
	}
	/**
	 * Método de acceso al campo tamanoOreja.
	 * @return El valor del campo tamanoOreja
	 */
	public String getTamanoOreja() {
		return tamanoOreja;
	}
	/**
	 * Asigna el valor al campo tamanoOreja.
	 * @param tamanoOreja el valor tamanoOreja a asignar
	 */
	public void setTamanoOreja(String tamanoOreja) {
		this.tamanoOreja = tamanoOreja;
	}
	/**
	 * Método de acceso al campo lobuloParticularidad.
	 * @return El valor del campo lobuloParticularidad
	 */
	public String getLobuloParticularidad() {
		return lobuloParticularidad;
	}
	/**
	 * Asigna el valor al campo lobuloParticularidad.
	 * @param lobuloParticularidad el valor lobuloParticularidad a asignar
	 */
	public void setLobuloParticularidad(String lobuloParticularidad) {
		this.lobuloParticularidad = lobuloParticularidad;
	}
	/**
	 * Método de acceso al campo lobuloDimension.
	 * @return El valor del campo lobuloDimension
	 */
	public String getLobuloDimension() {
		return lobuloDimension;
	}
	/**
	 * Asigna el valor al campo lobuloDimension.
	 * @param lobuloDimension el valor lobuloDimension a asignar
	 */
	public void setLobuloDimension(String lobuloDimension) {
		this.lobuloDimension = lobuloDimension;
	}
	/**
	 * Método de acceso al campo lobuloAdherencia.
	 * @return El valor del campo lobuloAdherencia
	 */
	public String getLobuloAdherencia() {
		return lobuloAdherencia;
	}
	/**
	 * Asigna el valor al campo lobuloAdherencia.
	 * @param lobuloAdherencia el valor lobuloAdherencia a asignar
	 */
	public void setLobuloAdherencia(String lobuloAdherencia) {
		this.lobuloAdherencia = lobuloAdherencia;
	}
	/**
	 * Método de acceso al campo helixAnterior.
	 * @return El valor del campo helixAnterior
	 */
	public String getHelixAnterior() {
		return helixAnterior;
	}
	/**
	 * Asigna el valor al campo helixAnterior.
	 * @param helixAnterior el valor helixAnterior a asignar
	 */
	public void setHelixAnterior(String helixAnterior) {
		this.helixAnterior = helixAnterior;
	}
	/**
	 * Método de acceso al campo helixPosterior.
	 * @return El valor del campo helixPosterior
	 */
	public String getHelixPosterior() {
		return helixPosterior;
	}
	/**
	 * Asigna el valor al campo helixPosterior.
	 * @param helixPosterior el valor helixPosterior a asignar
	 */
	public void setHelixPosterior(String helixPosterior) {
		this.helixPosterior = helixPosterior;
	}
	/**
	 * Método de acceso al campo helixContorno.
	 * @return El valor del campo helixContorno
	 */
	public String getHelixContorno() {
		return helixContorno;
	}
	/**
	 * Asigna el valor al campo helixContorno.
	 * @param helixContorno el valor helixContorno a asignar
	 */
	public void setHelixContorno(String helixContorno) {
		this.helixContorno = helixContorno;
	}
	/**
	 * Método de acceso al campo helixAdherencia.
	 * @return El valor del campo helixAdherencia
	 */
	public String getHelixAdherencia() {
		return helixAdherencia;
	}
	/**
	 * Asigna el valor al campo helixAdherencia.
	 * @param helixAdherencia el valor helixAdherencia a asignar
	 */
	public void setHelixAdherencia(String helixAdherencia) {
		this.helixAdherencia = helixAdherencia;
	}
	/**
	 * Método de acceso al campo formaOreja.
	 * @return El valor del campo formaOreja
	 */
	public String getFormaOreja() {
		return formaOreja;
	}
	/**
	 * Asigna el valor al campo formaOreja.
	 * @param formaOreja el valor formaOreja a asignar
	 */
	public void setFormaOreja(String formaOreja) {
		this.formaOreja = formaOreja;
	}
	/**
	 * Método de acceso al campo tamanoOjos.
	 * @return El valor del campo tamanoOjos
	 */
	public String getTamanoOjos() {
		return tamanoOjos;
	}
	/**
	 * Asigna el valor al campo tamanoOjos.
	 * @param tamanoOjos el valor tamanoOjos a asignar
	 */
	public void setTamanoOjos(String tamanoOjos) {
		this.tamanoOjos = tamanoOjos;
	}
	/**
	 * Método de acceso al campo colorOjos.
	 * @return El valor del campo colorOjos
	 */
	public String getColorOjos() {
		return colorOjos;
	}
	/**
	 * Asigna el valor al campo colorOjos.
	 * @param colorOjos el valor colorOjos a asignar
	 */
	public void setColorOjos(String colorOjos) {
		this.colorOjos = colorOjos;
	}
	/**
	 * Método de acceso al campo formaOjos.
	 * @return El valor del campo formaOjos
	 */
	public String getFormaOjos() {
		return formaOjos;
	}
	/**
	 * Asigna el valor al campo formaOjos.
	 * @param formaOjos el valor formaOjos a asignar
	 */
	public void setFormaOjos(String formaOjos) {
		this.formaOjos = formaOjos;
	}

    public String getAlturaNariz() {
        return alturaNariz;
    }

    public void setAlturaNariz(String alturaNariz) {
        this.alturaNariz = alturaNariz;
    }

    public String getAlturaNasoLabial() {
        return alturaNasoLabial;
    }

    public void setAlturaNasoLabial(String alturaNasoLabial) {
        this.alturaNasoLabial = alturaNasoLabial;
    }

    public String getAnchoNariz() {
        return anchoNariz;
    }

    public void setAnchoNariz(String anchoNariz) {
        this.anchoNariz = anchoNariz;
    }

    public String getBaseNariz() {
        return baseNariz;
    }

    public void setBaseNariz(String baseNariz) {
        this.baseNariz = baseNariz;
    }

    public String getComisuras() {
        return comisuras;
    }

    public void setComisuras(String comisuras) {
        this.comisuras = comisuras;
    }

    public String getEspesorLabioInferior() {
        return espesorLabioInferior;
    }

    public void setEspesorLabioInferior(String espesorLabioInferior) {
        this.espesorLabioInferior = espesorLabioInferior;
    }

    public String getEspesorLabioSuperior() {
        return espesorLabioSuperior;
    }

    public void setEspesorLabioSuperior(String espesorLabioSuperior) {
        this.espesorLabioSuperior = espesorLabioSuperior;
    }

    public String getFrenteAltura() {
        return frenteAltura;
    }

    public void setFrenteAltura(String frenteAltura) {
        this.frenteAltura = frenteAltura;
    }

    public String getFrenteAncho() {
        return frenteAncho;
    }

    public void setFrenteAncho(String frenteAncho) {
        this.frenteAncho = frenteAncho;
    }

    public String getInclinacionFrente() {
        return inclinacionFrente;
    }

    public void setInclinacionFrente(String inclinacionFrente) {
        this.inclinacionFrente = inclinacionFrente;
    }

    public String getProminencia() {
        return prominencia;
    }

    public void setProminencia(String prominencia) {
        this.prominencia = prominencia;
    }

    public String getRaizNariz() {
        return raizNariz;
    }

    public void setRaizNariz(String raizNariz) {
        this.raizNariz = raizNariz;
    }
    
    /**
	 * @return the implantacionCejas
	 */
	public String getImplantacionCejas() {
		return implantacionCejas;
	}
	/**
	 * @param implantacionCejas the implantacionCejas to set
	 */
	public void setImplantacionCejas(String implantacionCejas) {
		this.implantacionCejas = implantacionCejas;
	}
	/**
	 * @return the formaCejas
	 */
	public String getFormaCejas() {
		return formaCejas;
	}
	/**
	 * @param formaCejas the formaCejas to set
	 */
	public void setFormaCejas(String formaCejas) {
		this.formaCejas = formaCejas;
	}
	/**
	 * @return the tamanoCejas
	 */
	public String getTamanoCejas() {
		return tamanoCejas;
	}
	/**
	 * @param tamanoCejas the tamanoCejas to set
	 */
	public void setTamanoCejas(String tamanoCejas) {
		this.tamanoCejas = tamanoCejas;
	}
	/**
	 * @return the cicatricesSenas
	 */
	public String getCicatricesSenas() {
		return cicatricesSenas;
	}
	/**
	 * @param cicatricesSenas the cicatricesSenas to set
	 */
	public void setCicatricesSenas(String cicatricesSenas) {
		this.cicatricesSenas = cicatricesSenas;
	}
	/**
	 * @return the cicatricesSenasText
	 */
	public String getCicatricesSenasText() {
		return cicatricesSenasText;
	}
	/**
	 * @param cicatricesSenasText the cicatricesSenasText to set
	 */
	public void setCicatricesSenasText(String cicatricesSenasText) {
		this.cicatricesSenasText = cicatricesSenasText;
	}
	/**
	 * @return the protesisSenas
	 */
	public String getProtesisSenas() {
		return protesisSenas;
	}
	/**
	 * @param protesisSenas the protesisSenas to set
	 */
	public void setProtesisSenas(String protesisSenas) {
		this.protesisSenas = protesisSenas;
	}
	/**
	 * @return the protesisSenasText
	 */
	public String getProtesisSenasText() {
		return protesisSenasText;
	}
	/**
	 * @param protesisSenasText the protesisSenasText to set
	 */
	public void setProtesisSenasText(String protesisSenasText) {
		this.protesisSenasText = protesisSenasText;
	}
	/**
	 * @return the tatuajeSenas
	 */
	public String getTatuajeSenas() {
		return tatuajeSenas;
	}
	/**
	 * @param tatuajeSenas the tatuajeSenas to set
	 */
	public void setTatuajeSenas(String tatuajeSenas) {
		this.tatuajeSenas = tatuajeSenas;
	}
	/**
	 * @return the tatuajeSenasText
	 */
	public String getTatuajeSenasText() {
		return tatuajeSenasText;
	}
	/**
	 * @param tatuajeSenasText the tatuajeSenasText to set
	 */
	public void setTatuajeSenasText(String tatuajeSenasText) {
		this.tatuajeSenasText = tatuajeSenasText;
	}
	/**
	 * @return the otraSenasText
	 */
	public String getOtraSenasText() {
		return otraSenasText;
	}
	/**
	 * @param otraSenasText the otraSenasText to set
	 */
	public void setOtraSenasText(String otraSenasText) {
		this.otraSenasText = otraSenasText;
	}
	/**
	 * @return the lunaresSenas
	 */
	public String getLunaresSenas() {
		return lunaresSenas;
	}
	/**
	 * @param lunaresSenas the lunaresSenas to set
	 */
	public void setLunaresSenas(String lunaresSenas) {
		this.lunaresSenas = lunaresSenas;
	}
	/**
	 * @return the lunaresSenasText
	 */
	public String getLunaresSenasText() {
		return lunaresSenasText;
	}
	/**
	 * @param lunaresSenasText the lunaresSenasText to set
	 */
	public void setLunaresSenasText(String lunaresSenasText) {
		this.lunaresSenasText = lunaresSenasText;
	}
	/**
	 * @return the defectosSenas
	 */
	public String getDefectosSenas() {
		return defectosSenas;
	}
	/**
	 * @param defectosSenas the defectosSenas to set
	 */
	public void setDefectosSenas(String defectosSenas) {
		this.defectosSenas = defectosSenas;
	}
	/**
	 * @return the defectosSenasText
	 */
	public String getDefectosSenasText() {
		return defectosSenasText;
	}
	/**
	 * @param defectosSenasText the defectosSenasText to set
	 */
	public void setDefectosSenasText(String defectosSenasText) {
		this.defectosSenasText = defectosSenasText;
	}
	/**
	 * @return the factorrhOtros
	 */
	public String getFactorrhOtros() {
		return factorrhOtros;
	}
	/**
	 * @param factorrhOtros the factorrhOtros to set
	 */
	public void setFactorrhOtros(String factorrhOtros) {
		this.factorrhOtros = factorrhOtros;
	}
	/**
	 * @return the lentesOtros
	 */
	public String getLentesOtros() {
		return lentesOtros;
	}
	/**
	 * @param lentesOtros the lentesOtros to set
	 */
	public void setLentesOtros(String lentesOtros) {
		this.lentesOtros = lentesOtros;
	}
	/**
	 * @return the tipoSangreOtros
	 */
	public String getTipoSangreOtros() {
		return tipoSangreOtros;
	}
	/**
	 * @param tipoSangreOtros the tipoSangreOtros to set
	 */
	public void setTipoSangreOtros(String tipoSangreOtros) {
		this.tipoSangreOtros = tipoSangreOtros;
	}
	
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
	
	/**
	 * @return the helixOriginal
	 */
	public String getHelixOriginal() {
		return helixOriginal;
	}
	/**
	 * @param helixOriginal the helixOriginal to set
	 */
	public void setHelixOriginal(String helixOriginal) {
		this.helixOriginal = helixOriginal;
	}
	
	/**
	 * @return the barbaOtros
	 */
	public String getBarbaOtros() {
		return barbaOtros;
	}
	/**
	 * @param barbaOtros the barbaOtros to set
	 */
	public void setBarbaOtros(String barbaOtros) {
		this.barbaOtros = barbaOtros;
	}
	/**
	 * @return the pesoOtros
	 */
	public String getPesoOtros() {
		return pesoOtros;
	}
	/**
	 * @param pesoOtros the pesoOtros to set
	 */
	public void setPesoOtros(String pesoOtros) {
		this.pesoOtros = pesoOtros;
	}
	/**
	 * @return the bigoteOtros
	 */
	public String getBigoteOtros() {
		return bigoteOtros;
	}
	/**
	 * @param bigoteOtros the bigoteOtros to set
	 */
	public void setBigoteOtros(String bigoteOtros) {
		this.bigoteOtros = bigoteOtros;
	}
	/**
	 * @return the estauraOtros
	 */
	public String getEstauraOtros() {
		return estauraOtros;
	}
	/**
	 * @param estauraOtros the estauraOtros to set
	 */
	public void setEstauraOtros(String estauraOtros) {
		this.estauraOtros = estauraOtros;
	}
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
	/**
	 * @return the fechaInicioLapso
	 */
	public String getFechaInicioLapso() {
		return fechaInicioLapso;
	}
	/**
	 * @param fechaInicioLapso the fechaInicioLapso to set
	 */
	public void setFechaInicioLapso(String fechaInicioLapso) {
		this.fechaInicioLapso = fechaInicioLapso;
	}
	/**
	 * @return the horaInicioLapso
	 */
	public String getHoraInicioLapso() {
		return horaInicioLapso;
	}
	/**
	 * @param horaInicioLapso the horaInicioLapso to set
	 */
	public void setHoraInicioLapso(String horaInicioLapso) {
		this.horaInicioLapso = horaInicioLapso;
	}
	/**
	 * @return the fechaFinLapso
	 */
	public String getFechaFinLapso() {
		return fechaFinLapso;
	}
	/**
	 * @param fechaFinLapso the fechaFinLapso to set
	 */
	public void setFechaFinLapso(String fechaFinLapso) {
		this.fechaFinLapso = fechaFinLapso;
	}
	/**
	 * @return the horaFinLapso
	 */
	public String getHoraFinLapso() {
		return horaFinLapso;
	}
	/**
	 * @param horaFinLapso the horaFinLapso to set
	 */
	public void setHoraFinLapso(String horaFinLapso) {
		this.horaFinLapso = horaFinLapso;
	}

	/**
	 * @return the latitudNNotif
	 */
	public String getLatitudNNotif() {
		return latitudNNotif;
	}
	/**
	 * @param latitudNNotif the latitudNNotif to set
	 */
	public void setLatitudNNotif(String latitudNNotif) {
		this.latitudNNotif = latitudNNotif;
	}
	/**
	 * @return the latitudGradosNotif
	 */
	public String getLatitudGradosNotif() {
		return latitudGradosNotif;
	}
	/**
	 * @param latitudGradosNotif the latitudGradosNotif to set
	 */
	public void setLatitudGradosNotif(String latitudGradosNotif) {
		this.latitudGradosNotif = latitudGradosNotif;
	}
	/**
	 * @return the latitudMinutosNotif
	 */
	public String getLatitudMinutosNotif() {
		return latitudMinutosNotif;
	}
	/**
	 * @param latitudMinutosNotif the latitudMinutosNotif to set
	 */
	public void setLatitudMinutosNotif(String latitudMinutosNotif) {
		this.latitudMinutosNotif = latitudMinutosNotif;
	}
	/**
	 * @return the latitudSegundosNotif
	 */
	public String getLatitudSegundosNotif() {
		return latitudSegundosNotif;
	}
	/**
	 * @param latitudSegundosNotif the latitudSegundosNotif to set
	 */
	public void setLatitudSegundosNotif(String latitudSegundosNotif) {
		this.latitudSegundosNotif = latitudSegundosNotif;
	}
	/**
	 * @return the longitudENotif
	 */
	public String getLongitudENotif() {
		return longitudENotif;
	}
	/**
	 * @param longitudENotif the longitudENotif to set
	 */
	public void setLongitudENotif(String longitudENotif) {
		this.longitudENotif = longitudENotif;
	}
	/**
	 * @return the longitudGradosNotif
	 */
	public String getLongitudGradosNotif() {
		return longitudGradosNotif;
	}
	/**
	 * @param longitudGradosNotif the longitudGradosNotif to set
	 */
	public void setLongitudGradosNotif(String longitudGradosNotif) {
		this.longitudGradosNotif = longitudGradosNotif;
	}
	/**
	 * @return the longitudMinutosNotif
	 */
	public String getLongitudMinutosNotif() {
		return longitudMinutosNotif;
	}
	/**
	 * @param longitudMinutosNotif the longitudMinutosNotif to set
	 */
	public void setLongitudMinutosNotif(String longitudMinutosNotif) {
		this.longitudMinutosNotif = longitudMinutosNotif;
	}
	/**
	 * @return the longitudSegundosNotif
	 */
	public String getLongitudSegundosNotif() {
		return longitudSegundosNotif;
	}
	/**
	 * @param longitudSegundosNotif the longitudSegundosNotif to set
	 */
	public void setLongitudSegundosNotif(String longitudSegundosNotif) {
		this.longitudSegundosNotif = longitudSegundosNotif;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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
	 * @return the direccionCeja
	 */
	public String getDireccionCeja() {
		return direccionCeja;
	}
	/**
	 * @param direccionCeja the direccionCeja to set
	 */
	public void setDireccionCeja(String direccionCeja) {
		this.direccionCeja = direccionCeja;
	}
	/**
	 * @return the orejaLobContorno
	 */
	public String getOrejaLobContorno() {
		return orejaLobContorno;
	}
	/**
	 * @param orejaLobContorno the orejaLobContorno to set
	 */
	public void setOrejaLobContorno(String orejaLobContorno) {
		this.orejaLobContorno = orejaLobContorno;
	}
	public Boolean getEsVivo() {
		return esVivo;
	}
	public void setEsVivo(Boolean esVivo) {
		this.esVivo = esVivo;
	}
	public Boolean getEsProtegido() {
		return esProtegido;
	}
	public void setEsProtegido(Boolean esProtegido) {
		this.esProtegido = esProtegido;
	}
    
}