/**
* Nombre del Programa 		: InventarioPertenenciasForm.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 29 Mar 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.reinsercionsocial.form;

import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class InventarioPertenenciasForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5218117325557374926L;

	private long sentenciaId;
	private String fechaFinPena;
	private String fechaInicioPena;
	private String nombreSentenciado;
	private String nus;
	private String tipoSentencia;
	private String nombreCereso;
	private String descPert;
	
	private long puntosPorAcumular;
	private long puntosAcumulados;
	private long tipoPertId;
	private long unidadPertId;
	private long condicionPertId;
	private long pertenenciaId;
	
	private short cantidadPert;
	
	private List<CatalogoDTO> lstCatTipoPertDTO;
	private List<CatalogoDTO> lstCatUnidadPertDTO;
	private List<CatalogoDTO> lstCatCondicionPertDTO;
	
	private boolean definitivo;

	/**
	 * Método de acceso al campo sentenciaId.
	 * @return El valor del campo sentenciaId
	 */
	public long getSentenciaId() {
		return sentenciaId;
	}

	/**
	 * Asigna el valor al campo sentenciaId.
	 * @param sentenciaId el valor sentenciaId a asignar
	 */
	public void setSentenciaId(long sentenciaId) {
		this.sentenciaId = sentenciaId;
	}

	/**
	 * Método de acceso al campo fechaFinPena.
	 * @return El valor del campo fechaFinPena
	 */
	public String getFechaFinPena() {
		return fechaFinPena;
	}

	/**
	 * Asigna el valor al campo fechaFinPena.
	 * @param fechaFinPena el valor fechaFinPena a asignar
	 */
	public void setFechaFinPena(String fechaFinPena) {
		this.fechaFinPena = fechaFinPena;
	}

	/**
	 * Método de acceso al campo fechaInicioPena.
	 * @return El valor del campo fechaInicioPena
	 */
	public String getFechaInicioPena() {
		return fechaInicioPena;
	}

	/**
	 * Asigna el valor al campo fechaInicioPena.
	 * @param fechaInicioPena el valor fechaInicioPena a asignar
	 */
	public void setFechaInicioPena(String fechaInicioPena) {
		this.fechaInicioPena = fechaInicioPena;
	}

	/**
	 * Método de acceso al campo nombreSentenciado.
	 * @return El valor del campo nombreSentenciado
	 */
	public String getNombreSentenciado() {
		return nombreSentenciado;
	}

	/**
	 * Asigna el valor al campo nombreSentenciado.
	 * @param nombreSentenciado el valor nombreSentenciado a asignar
	 */
	public void setNombreSentenciado(String nombreSentenciado) {
		this.nombreSentenciado = nombreSentenciado;
	}

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
	 * Método de acceso al campo tipoSentencia.
	 * @return El valor del campo tipoSentencia
	 */
	public String getTipoSentencia() {
		return tipoSentencia;
	}

	/**
	 * Asigna el valor al campo tipoSentencia.
	 * @param tipoSentencia el valor tipoSentencia a asignar
	 */
	public void setTipoSentencia(String tipoSentencia) {
		this.tipoSentencia = tipoSentencia;
	}

	/**
	 * Método de acceso al campo nombreCereso.
	 * @return El valor del campo nombreCereso
	 */
	public String getNombreCereso() {
		return nombreCereso;
	}

	/**
	 * Asigna el valor al campo nombreCereso.
	 * @param nombreCereso el valor nombreCereso a asignar
	 */
	public void setNombreCereso(String nombreCereso) {
		this.nombreCereso = nombreCereso;
	}

	/**
	 * Método de acceso al campo descPert.
	 * @return El valor del campo descPert
	 */
	public String getDescPert() {
		return descPert;
	}

	/**
	 * Asigna el valor al campo descPert.
	 * @param descPert el valor descPert a asignar
	 */
	public void setDescPert(String descPert) {
		this.descPert = descPert;
	}

	/**
	 * Método de acceso al campo puntosPorAcumular.
	 * @return El valor del campo puntosPorAcumular
	 */
	public long getPuntosPorAcumular() {
		return puntosPorAcumular;
	}

	/**
	 * Asigna el valor al campo puntosPorAcumular.
	 * @param puntosPorAcumular el valor puntosPorAcumular a asignar
	 */
	public void setPuntosPorAcumular(long puntosPorAcumular) {
		this.puntosPorAcumular = puntosPorAcumular;
	}

	/**
	 * Método de acceso al campo puntosAcumulados.
	 * @return El valor del campo puntosAcumulados
	 */
	public long getPuntosAcumulados() {
		return puntosAcumulados;
	}

	/**
	 * Asigna el valor al campo puntosAcumulados.
	 * @param puntosAcumulados el valor puntosAcumulados a asignar
	 */
	public void setPuntosAcumulados(long puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}

	/**
	 * Método de acceso al campo tipoPertId.
	 * @return El valor del campo tipoPertId
	 */
	public long getTipoPertId() {
		return tipoPertId;
	}

	/**
	 * Asigna el valor al campo tipoPertId.
	 * @param tipoPertId el valor tipoPertId a asignar
	 */
	public void setTipoPertId(long tipoPertId) {
		this.tipoPertId = tipoPertId;
	}

	/**
	 * Método de acceso al campo unidadPertId.
	 * @return El valor del campo unidadPertId
	 */
	public long getUnidadPertId() {
		return unidadPertId;
	}

	/**
	 * Asigna el valor al campo unidadPertId.
	 * @param unidadPertId el valor unidadPertId a asignar
	 */
	public void setUnidadPertId(long unidadPertId) {
		this.unidadPertId = unidadPertId;
	}

	/**
	 * Método de acceso al campo condicionPertId.
	 * @return El valor del campo condicionPertId
	 */
	public long getCondicionPertId() {
		return condicionPertId;
	}

	/**
	 * Asigna el valor al campo condicionPertId.
	 * @param condicionPertId el valor condicionPertId a asignar
	 */
	public void setCondicionPertId(long condicionPertId) {
		this.condicionPertId = condicionPertId;
	}

	/**
	 * Método de acceso al campo pertenenciaId.
	 * @return El valor del campo pertenenciaId
	 */
	public long getPertenenciaId() {
		return pertenenciaId;
	}

	/**
	 * Asigna el valor al campo pertenenciaId.
	 * @param pertenenciaId el valor pertenenciaId a asignar
	 */
	public void setPertenenciaId(long pertenenciaId) {
		this.pertenenciaId = pertenenciaId;
	}

	/**
	 * Método de acceso al campo cantidadPert.
	 * @return El valor del campo cantidadPert
	 */
	public short getCantidadPert() {
		return cantidadPert;
	}

	/**
	 * Asigna el valor al campo cantidadPert.
	 * @param cantidadPert el valor cantidadPert a asignar
	 */
	public void setCantidadPert(short cantidadPert) {
		this.cantidadPert = cantidadPert;
	}

	/**
	 * Método de acceso al campo lstCatTipoPertDTO.
	 * @return El valor del campo lstCatTipoPertDTO
	 */
	public List<CatalogoDTO> getLstCatTipoPertDTO() {
		return lstCatTipoPertDTO;
	}

	/**
	 * Asigna el valor al campo lstCatTipoPertDTO.
	 * @param lstCatTipoPertDTO el valor lstCatTipoPertDTO a asignar
	 */
	public void setLstCatTipoPertDTO(List<CatalogoDTO> lstCatTipoPertDTO) {
		this.lstCatTipoPertDTO = lstCatTipoPertDTO;
	}

	/**
	 * Método de acceso al campo lstCatUnidadPertDTO.
	 * @return El valor del campo lstCatUnidadPertDTO
	 */
	public List<CatalogoDTO> getLstCatUnidadPertDTO() {
		return lstCatUnidadPertDTO;
	}

	/**
	 * Asigna el valor al campo lstCatUnidadPertDTO.
	 * @param lstCatUnidadPertDTO el valor lstCatUnidadPertDTO a asignar
	 */
	public void setLstCatUnidadPertDTO(List<CatalogoDTO> lstCatUnidadPertDTO) {
		this.lstCatUnidadPertDTO = lstCatUnidadPertDTO;
	}

	/**
	 * Método de acceso al campo lstCatCondicionPertDTO.
	 * @return El valor del campo lstCatCondicionPertDTO
	 */
	public List<CatalogoDTO> getLstCatCondicionPertDTO() {
		return lstCatCondicionPertDTO;
	}

	/**
	 * Asigna el valor al campo lstCatCondicionPertDTO.
	 * @param lstCatCondicionPertDTO el valor lstCatCondicionPertDTO a asignar
	 */
	public void setLstCatCondicionPertDTO(List<CatalogoDTO> lstCatCondicionPertDTO) {
		this.lstCatCondicionPertDTO = lstCatCondicionPertDTO;
	}

	/**
	 * Método de acceso al campo definitivo.
	 * @return El valor del campo definitivo
	 */
	public boolean isDefinitivo() {
		return definitivo;
	}

	/**
	 * Asigna el valor al campo definitivo.
	 * @param definitivo el valor definitivo a asignar
	 */
	public void setDefinitivo(boolean definitivo) {
		this.definitivo = definitivo;
	}
	
}
