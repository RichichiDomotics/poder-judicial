/**
* Nombre del Programa : InformePHForm.java
* Autor                            : Tattva-IT
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Aug 2011
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
package mx.gob.segob.nsjp.web.seguridadPublica.form;

import java.util.Date;

import mx.gob.segob.nsjp.web.base.form.GenericForm;;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class InformePHForm extends GenericForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//-----Datos generales-----
	String folioIPH;	
	String fechaEvento;
	String horaEvento;
	String transporteOficialNum;
	String asunto;			
	String numeroEmpleado;
	Long turnoLaboralId;
	Long tipoParticipacionId;
	//-----Operativo-----
	String nombreOperativo;
	String comandanteAgrupamiento;
	String comandanteOperativo;	
	//-----Datos del domicilio-----
	Long idPais;
	String codigoPostal;
	Long entidadFederativa;
	Long ciudad;
	Long delegacionMunicipio;
	Long asentamientoColonia;
	Long tipoAsentamiento;
	Long tipoCalle;
	String calle;
	String numExterior;
	String numInterior;	
	String entreCalle;
	String ycalle;
	String aliasDomicilio;
	String edificio;
	String referencias;
	//-----Punto Carretero-----
	Long tipoCarretera;
	Long entidadFederativaPC;
	String numeroCarretera;
	String nomCarretera;
	String numeroKilometro;
	String nombreParajeZona;
	String nombreTramoCarretero;
	String poblacionesVecinas;
	String longitud;
	String latitud;
	//-----Descripción-----
	
	String descripcionEvento;
	public String getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	public String getHoraEvento() {
		return horaEvento;
	}
	public void setHoraEvento(String horaEvento) {
		this.horaEvento = horaEvento;
	}
	public String getTransporteOficialNum() {
		return transporteOficialNum;
	}
	public void setTransporteOficialNum(String transporteOficialNum) {
		this.transporteOficialNum = transporteOficialNum;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}
	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}
	public Long getTurnoLaboralId() {
		return turnoLaboralId;
	}
	public void setTurnoLaboralId(Long turnoLaboralId) {
		this.turnoLaboralId = turnoLaboralId;
	}
	public Long getTipoParticipacionId() {
		return tipoParticipacionId;
	}
	public void setTipoParticipacionId(Long tipoParticipacionId) {
		this.tipoParticipacionId = tipoParticipacionId;
	}
	public String getNombreOperativo() {
		return nombreOperativo;
	}
	public void setNombreOperativo(String nombreOperativo) {
		this.nombreOperativo = nombreOperativo;
	}
	public String getComandanteAgrupamiento() {
		return comandanteAgrupamiento;
	}
	public void setComandanteAgrupamiento(String comandanteAgrupamiento) {
		this.comandanteAgrupamiento = comandanteAgrupamiento;
	}
	public String getComandanteOperativo() {
		return comandanteOperativo;
	}
	public void setComandanteOperativo(String comandanteOperativo) {
		this.comandanteOperativo = comandanteOperativo;
	}
	public Long getIdPais() {
		return idPais;
	}
	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Long getEntidadFederativa() {
		return entidadFederativa;
	}
	public void setEntidadFederativa(Long entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
	public Long getCiudad() {
		return ciudad;
	}
	public void setCiudad(Long ciudad) {
		this.ciudad = ciudad;
	}
	public Long getDelegacionMunicipio() {
		return delegacionMunicipio;
	}
	public void setDelegacionMunicipio(Long delegacionMunicipio) {
		this.delegacionMunicipio = delegacionMunicipio;
	}
	public Long getAsentamientoColonia() {
		return asentamientoColonia;
	}
	public void setAsentamientoColonia(Long asentamientoColonia) {
		this.asentamientoColonia = asentamientoColonia;
	}
	public Long getTipoAsentamiento() {
		return tipoAsentamiento;
	}
	public void setTipoAsentamiento(Long tipoAsentamiento) {
		this.tipoAsentamiento = tipoAsentamiento;
	}
	public Long getTipoCalle() {
		return tipoCalle;
	}
	public void setTipoCalle(Long tipoCalle) {
		this.tipoCalle = tipoCalle;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumExterior() {
		return numExterior;
	}
	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}
	public String getNumInterior() {
		return numInterior;
	}
	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}
	public String getEntreCalle() {
		return entreCalle;
	}
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}
	public String getYcalle() {
		return ycalle;
	}
	public void setYcalle(String ycalle) {
		this.ycalle = ycalle;
	}
	public String getAliasDomicilio() {
		return aliasDomicilio;
	}
	public void setAliasDomicilio(String aliasDomicilio) {
		this.aliasDomicilio = aliasDomicilio;
	}
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	public String getReferencias() {
		return referencias;
	}
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	public Long getTipoCarretera() {
		return tipoCarretera;
	}
	public void setTipoCarretera(Long tipoCarretera) {
		this.tipoCarretera = tipoCarretera;
	}
	public Long getEntidadFederativaPC() {
		return entidadFederativaPC;
	}
	public void setEntidadFederativaPC(Long entidadFederativaPC) {
		this.entidadFederativaPC = entidadFederativaPC;
	}
	public String getNumeroCarretera() {
		return numeroCarretera;
	}
	public void setNumeroCarretera(String numeroCarretera) {
		this.numeroCarretera = numeroCarretera;
	}
	public String getNomCarretera() {
		return nomCarretera;
	}
	public void setNomCarretera(String nomCarretera) {
		this.nomCarretera = nomCarretera;
	}
	public String getNumeroKilometro() {
		return numeroKilometro;
	}
	public void setNumeroKilometro(String numeroKilometro) {
		this.numeroKilometro = numeroKilometro;
	}
	public String getNombreParajeZona() {
		return nombreParajeZona;
	}
	public void setNombreParajeZona(String nombreParajeZona) {
		this.nombreParajeZona = nombreParajeZona;
	}
	public String getNombreTramoCarretero() {
		return nombreTramoCarretero;
	}
	public void setNombreTramoCarretero(String nombreTramoCarretero) {
		this.nombreTramoCarretero = nombreTramoCarretero;
	}
	public String getPoblacionesVecinas() {
		return poblacionesVecinas;
	}
	public void setPoblacionesVecinas(String poblacionesVecinas) {
		this.poblacionesVecinas = poblacionesVecinas;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getDescripcionEvento() {
		return descripcionEvento;
	}
	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}
	
	public String getFolioIPH() {
		return folioIPH;
	}
	public void setFolioIPH(String folioIPH) {
		this.folioIPH = folioIPH;
	}
	
	
}
