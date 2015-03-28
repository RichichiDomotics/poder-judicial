/**
* Nombre del Programa 	: ReinsercionSocialForm.java
* Autor					: EdgarTE
* Compania             	: Ultrasist
* Proyecto              : NSJP							Fecha: 21/02/2012
* Marca de cambio       : N/A
* Descripcion General 	: Describir el objetivo de la clase de manera breve
* Programa Dependiente 	: N/A
* Programa Subsecuente 	: N/A
* Cond. de ejecucion 	: N/A
* Dias de ejecucion 	: N/A                      		Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 				: N/A
* Compania				: N/A
* Proyecto 				: N/A                    		Fecha: N/A
* Modificacion 			: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.reinsercionsocial.form;

import java.util.List;

import mx.gob.segob.nsjp.dto.programas.CatProgramaDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Clase que modela la forma utilizada por struts, para las pantallas de datos 
 * generales de Reinserción Social.
 * @version 1.0
 * @author EdgarTE.
 *
 */
public class DatosGeneralesReinsercionForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5279400528089193428L;

	private String nus;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String edad;
	private String lesionado;
	private String caso;
	private String causa;
	private String carpeta;
	private String tipoSentencia;
	private String fechaInicioPenaSTR;
	private String fechaFinPenaSTR;
	private List<String> lstMedidasAlternativas;
	private String encarcelado;
	private String motivo;
	private String preliberacion;
	private String reparacionDanio;
	private String montoDanioReparado;
	private String remisionParcial;
	private List<String> lstProgramas;
	private int puntosAcumulados;
	private int puntosTotales;
	private int porcentajeCumplido;
	private int porcentajeCubiertoPena;
	private String candidatoBeneficio;
	private String beneficio;
	private String actuacionSeleccionada;
	private String expedienteId;
	private String numeroExpedienteId;
	private String sentenciaId;
	

	/**
	 * Método de acceso al campo nus.
	 * @return El valor del campo nus
	 */
	public String getNus() {
		return nus;
	}

	/**
	 * Asigna el valor al campo nus.
	 * @param nus el valor nus a asignar
	 */
	public void setNus(String nus) {
		this.nus = nus;
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
	 * Método de acceso al campo edad.
	 * @return El valor del campo edad.
	 */
	public String getEdad() {
		return edad;
	}

	/**
	 * Asigna el valor al campo edad.
	 * @param edad la edad a asignar.
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}

	/**
	 * Método de acceso al campo lesionado.
	 * @return El valor del campo lesionado.
	 */
	public String getLesionado() {
		return lesionado;
	}

	/**
	 * Asigna el valor al campo lesionado.
	 * @param lesionado el valor del campo lesionado a asignar.
	 */
	public void setLesionado(String lesionado) {
		this.lesionado = lesionado;
	}
	
	/**
	 * Método de acceso al campo caso.
	 * @return El valor del campo caso
	 */
	public String getCaso() {
		return caso;
	}

	/**
	 * Asigna el valor al campo caso.
	 * @param caso el valor caso a asignar
	 */
	public void setCaso(String caso) {
		this.caso = caso;
	}

	/**
	 * Método de acceso al campo causa.
	 * @return El valor del campo causa
	 */
	public String getCausa() {
		return causa;
	}

	/**
	 * Asigna el valor al campo causa.
	 * @param causa el valor causa a asignar
	 */
	public void setCausa(String causa) {
		this.causa = causa;
	}

	/**
	 * Método de acceso al campo carpeta.
	 * @return El valor del campo carpeta
	 */
	public String getCarpeta() {
		return carpeta;
	}

	/**
	 * Asigna el valor al campo carpeta.
	 * @param carpeta el valor carpeta a asignar
	 */
	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	/**
	 * Método de acceso al campo tipoSentencia.
	 * @return El valor del campo tipoSentencia.
	 */
	public String getTipoSentencia() {
		return tipoSentencia;
	}

	/**
	 * Asigna el valor al campo tipoSentencia.
	 * @param tipoSentencia el tipoSentencia a asignar.
	 */
	public void setTipoSentencia(String tipoSentencia) {
		this.tipoSentencia = tipoSentencia;
	}

	/**
	 * Método de acceso al campo fechaInicioPenaSTR.
	 * @return El valor del campo fechaInicioPenaSTR
	 */
	public String getFechaInicioPenaSTR() {
		return fechaInicioPenaSTR;
	}

	/**
	 * Asigna el valor al campo fechaInicioPenaSTR.
	 * @param fechaInicioSTR el valor fechaInicioPenaSTR a asignar
	 */
	public void setFechaInicioPenaSTR(String fechaInicioPenaSTR) {
		this.fechaInicioPenaSTR = fechaInicioPenaSTR;
	}

	/**
	 * Método de acceso al campo fechaFinPenaSTR.
	 * @return El valor del campo fechaFinPenaSTR
	 */
	public String getFechaFinPenaSTR() {
		return fechaFinPenaSTR;
	}

	/**
	 * Asigna el valor al campo fechaFinPenaSTR.
	 * @param fechaFinPenaSTR el valor fechaFinPenaSTR a asignar
	 */
	public void setFechaFinPenaSTR(String fechaFinPenaSTR) {
		this.fechaFinPenaSTR = fechaFinPenaSTR;
	}

	/**
	 * Método de acceso a la lista de medidas alternativas.
	 * @return El valor de la lista de medidas alternativas.
	 */
	public List<String> getLstMedidasAlternativas() {
		return lstMedidasAlternativas;
	}

	/**
	 * Asigna el valor a la lista de medidas alternativas.
	 * @param lstMedidasAlternativas el valor lstMedidasAlternativas a asignar.
	 */
	public void setLstMedidasAlternativas(List<String> lstMedidasAlternativas) {
		this.lstMedidasAlternativas = lstMedidasAlternativas;
	}

	/**
	 * Método de acceso al campo encarcelado.
	 * @return El valor del campo encarcelado
	 */
	public String getEncarcelado() {
		return encarcelado;
	}

	/**
	 * Asigna el valor al campo encarcelado.
	 * @param encarcelado el valor encarcelado a asignar
	 */
	public void setEncarcelado(String encarcelado) {
		this.encarcelado = encarcelado;
	}

	/**
	 * Método de acceso al campo motivo.
	 * @return El valor del campo motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Asigna el valor al campo motivo.
	 * @param motivo el valor motivo a asignar
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Método de acceso al campo preliberacion.
	 * @return El valor del campo preliberacion
	 */
	public String getPreliberacion() {
		return preliberacion;
	}

	/**
	 * Asigna el valor al campo preliberacion.
	 * @param preliberacion el valor preliberacion a asignar
	 */
	public void setPreliberacion(String preliberacion) {
		this.preliberacion = preliberacion;
	}
	
	/**
	 * Método de acceso al campo reparacionDanio.
	 * @return El valor del campo reparacionDanio
	 */
	public String getReparacionDanio() {
		return reparacionDanio;
	}

	/**
	 * Asigna el valor al campo reparacionDanio.
	 * @param reparacionDanio el valor reparacionDanio a asignar
	 */
	public void setReparacionDanio(String reparacionDanio) {
		this.reparacionDanio = reparacionDanio;
	}
	
	/**
	 * Método de acceso al campo montoDanioReparado.
	 * @return El valor del campo montoDanioReparado
	 */
	public String getMontoDanioReparado() {
		return montoDanioReparado;
	}

	/**
	 * Asigna el valor al campo montoDanioReparado.
	 * @param montoDanioReparado el valor montoDanioReparado a asignar
	 */
	public void setMontoDanioReparado(String montoDanioReparado) {
		this.montoDanioReparado = montoDanioReparado;
	}

	/**
	 * Método de acceso al campo remisionParcial.
	 * @return El valor del campo remisionParcial
	 */
	public String getRemisionParcial() {
		return remisionParcial;
	}

	/**
	 * Asigna el valor al campo remisionParcial.
	 * @param remisionParcial el valor remisionParcial a asignar
	 */
	public void setRemisionParcial(String remisionParcial) {
		this.remisionParcial = remisionParcial;
	}

	/**
	 * Método de acceso al campo lstProgramas.
	 * @return El valor del campo lstProgramas
	 */
	public List<String> getLstProgramas() {
		return lstProgramas;
	}

	/**
	 * Asigna el valor al campo lstProgramas.
	 * @param lstProgramas el valor lstProgramas a asignar
	 */
	public void setLstProgramas(List<String> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	/**
	 * Método de acceso al campo puntosAcumulados.
	 * @return El valor del campo puntosAcumulados
	 */
	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}

	/**
	 * Asigna el valor al campo puntosAcumulados.
	 * @param puntosAcumulados el valor puntosAcumulados a asignar
	 */
	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}

	/**
	 * Método de acceso al campo puntosTotales.
	 * @return El valor del campo puntosTotales
	 */
	public int getPuntosTotales() {
		return puntosTotales;
	}

	/**
	 * Asigna el valor al campo puntosTotales.
	 * @param puntosTotales el valor puntosTotales a asignar
	 */
	public void setPuntosTotales(int puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	/**
	 * Método de acceso al campo porcentajeCumplido.
	 * @return El valor del campo porcentajeCumplido
	 */
	public int getPorcentajeCumplido() {
		return porcentajeCumplido;
	}

	/**
	 * Asigna el valor al campo porcentajeCumplido.
	 * @param porcentajeCumplido el valor porcentajeCumplido a asignar
	 */
	public void setPorcentajeCumplido(int porcentajeCumplido) {
		this.porcentajeCumplido = porcentajeCumplido;
	}

	/**
	 * Método de acceso al campo porcentajeCubiertoPena.
	 * @return El valor del campo porcentajeCubiertoPena
	 */
	public int getPorcentajeCubiertoPena() {
		return porcentajeCubiertoPena;
	}

	/**
	 * Asigna el valor al campo porcentajeCubiertoPena.
	 * @param porcentajeCubiertoPena el valor porcentajeCubiertoPena a asignar
	 */
	public void setPorcentajeCubiertoPena(int porcentajeCubiertoPena) {
		this.porcentajeCubiertoPena = porcentajeCubiertoPena;
	}

	/**
	 * Método de acceso al campo candidatoBeneficio.
	 * @return El valor del campo candidatoBeneficio
	 */
	public String getCandidatoBeneficio() {
		return candidatoBeneficio;
	}

	/**
	 * Asigna el valor al campo candidatoBeneficio.
	 * @param candidatoBeneficio el valor candidatoBeneficio a asignar
	 */
	public void setCandidatoBeneficio(String candidatoBeneficio) {
		this.candidatoBeneficio = candidatoBeneficio;
	}

	/**
	 * Método de acceso al campo beneficio.
	 * @return El valor del campo beneficio
	 */
	public String getBeneficio() {
		return beneficio;
	}

	/**
	 * Asigna el valor al campo beneficio.
	 * @param beneficio el valor beneficio a asignar
	 */
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}

	/**
	 * Método de acceso al campo actuacionSeleccionada.
	 * @return El valor del campo actuacionSeleccionada
	 */
	public String getActuacionSeleccionada() {
		return actuacionSeleccionada;
	}

	/**
	 * Asigna el valor al campo actuacionSeleccionada.
	 * @param actuacionSeleccionada el valor actuacionSeleccionada a asignar
	 */
	public void setActuacionSeleccionada(String actuacionSeleccionada) {
		this.actuacionSeleccionada = actuacionSeleccionada;
	}

	/**
	 * @return the expedienteId
	 */
	public String getExpedienteId() {
		return expedienteId;
	}

	/**
	 * @param expedienteId the expedienteId to set
	 */
	public void setExpedienteId(String expedienteId) {
		this.expedienteId = expedienteId;
	}

	/**
	 * @return the numeroExpedienteId
	 */
	public String getNumeroExpedienteId() {
		return numeroExpedienteId;
	}

	/**
	 * @param numeroExpedienteId the numeroExpedienteId to set
	 */
	public void setNumeroExpedienteId(String numeroExpedienteId) {
		this.numeroExpedienteId = numeroExpedienteId;
	}

	/**
	 * @return the sentenciaId
	 */
	public String getSentenciaId() {
		return sentenciaId;
	}

	/**
	 * @param sentenciaId the sentenciaId to set
	 */
	public void setSentenciaId(String sentenciaId) {
		this.sentenciaId = sentenciaId;
	}

}
