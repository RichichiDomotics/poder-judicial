/**
* Nombre del Programa 				: AsignarProgramaReinsercionForm.java
* Autor                            	: EdgarTE
* Compania                    		: Ultrasist
* Proyecto                      	: NSJP 						Fecha: 6 Mar 2012
* Marca de cambio        			: N/A
* Descripcion General    			: Describir el objetivo de la clase de manera breve
* Programa Dependiente  			: N/A
* Programa Subsecuente 				: N/A
* Cond. de ejecucion        		: N/A
* Dias de ejecucion          		: N/A 						Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       		: N/A
* Compania               			: N/A
* Proyecto                 			: N/A 						Fecha: N/A
* Modificacion           			: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.reinsercionsocial.form;

import java.util.List;

import mx.gob.segob.nsjp.dto.programas.CatCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTrabajoDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class AsignarProgramaReinsercionForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 541138832737915520L;
	
	private String nombreSentenciado;
	private String nus;
	private String tipoSentencia;
	private String fechaInicioPena;
	private String fechaFinPena;
	private String nombreCereso;
	private long puntosPorAcumular;
	private long puntosAcumulados;
	private long idProgramaPorAsignar;
	private long sentenciaId;
	
	//Atributos del CatalogoPrograma que se consulta
	private String nombreProgDisponible;
	private String tipoProgDisponible;
	private String descProgDisponible;
	private String fchIniProgDisp;
	private String fchFinProgDisp;
	
	private String idsProgramasConcatenados;
	
	private int totalPuntosDisp;
	
	
	
	private List <CatProgramaDTO> lstProgramasDisponibles;
	//TODO se debe de cambiar el tipo de la lista a un ProgramaDTO.
	private List <CatProgramaDTO> lstProgramasAsignados;
	
	private List <CatCursoDTO> lstCursosDisponibles;
	//TODO se debe de cambiar el tipo de la lista a un CursoDTO
	private List <CatCursoDTO> lstCursosAsignados;
	
	private List <CatTrabajoDTO> lstTrabajosDisponibles;
	//TODO se debe de cambiar a una lista de TrabajoDTO.
	private List <CatTrabajoDTO> lstTrabajosAsignados;
	
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
	 * Método de acceso al campo idProgramaPorAsignar.
	 * @return El valor del campo idProgramaPorAsignar
	 */
	public long getIdProgramaPorAsignar() {
		return idProgramaPorAsignar;
	}
	/**
	 * Asigna el valor al campo idProgramaPorAsignar.
	 * @param idProgramaPorAsignar el valor idProgramaPorAsignar a asignar
	 */
	public void setIdProgramaPorAsignar(long idProgramaPorAsignar) {
		this.idProgramaPorAsignar = idProgramaPorAsignar;
	}
	public long getSentenciaId() {
		return sentenciaId;
	}
	public void setSentenciaId(long sentenciaId) {
		this.sentenciaId = sentenciaId;
	}
	/**
	 * Método de acceso al campo nombreProgDisponible.
	 * @return El valor del campo nombreProgDisponible
	 */
	public String getNombreProgDisponible() {
		return nombreProgDisponible;
	}
	/**
	 * Asigna el valor al campo nombreProgDisponible.
	 * @param nombreProgDisponible el valor nombreProgDisponible a asignar
	 */
	public void setNombreProgDisponible(String nombreProgDisponible) {
		this.nombreProgDisponible = nombreProgDisponible;
	}
	/**
	 * Método de acceso al campo tipoProgDisponible.
	 * @return El valor del campo tipoProgDisponible
	 */
	public String getTipoProgDisponible() {
		return tipoProgDisponible;
	}
	/**
	 * Asigna el valor al campo tipoProgDisponible.
	 * @param tipoProgDisponible el valor tipoProgDisponible a asignar
	 */
	public void setTipoProgDisponible(String tipoProgDisponible) {
		this.tipoProgDisponible = tipoProgDisponible;
	}
	/**
	 * Método de acceso al campo descProgDisponible.
	 * @return El valor del campo descProgDisponible
	 */
	public String getDescProgDisponible() {
		return descProgDisponible;
	}
	/**
	 * Asigna el valor al campo descProgDisponible.
	 * @param descProgDisponible el valor descProgDisponible a asignar
	 */
	public void setDescProgDisponible(String descProgDisponible) {
		this.descProgDisponible = descProgDisponible;
	}
	/**
	 * Método de acceso al campo fchIniProgDisp.
	 * @return El valor del campo fchIniProgDisp
	 */
	public String getFchIniProgDisp() {
		return fchIniProgDisp;
	}
	/**
	 * Asigna el valor al campo fchIniProgDisp.
	 * @param fchIniProgDisp el valor fchIniProgDisp a asignar
	 */
	public void setFchIniProgDisp(String fchIniProgDisp) {
		this.fchIniProgDisp = fchIniProgDisp;
	}
	/**
	 * Método de acceso al campo fchFinProgDisp.
	 * @return El valor del campo fchFinProgDisp
	 */
	public String getFchFinProgDisp() {
		return fchFinProgDisp;
	}
	/**
	 * Asigna el valor al campo fchFinProgDisp.
	 * @param fchFinProgDisp el valor fchFinProgDisp a asignar
	 */
	public void setFchFinProgDisp(String fchFinProgDisp) {
		this.fchFinProgDisp = fchFinProgDisp;
	}
	/**
	 * @param idsProgramasConcatenados the idsProgramasConcatenados to set
	 */
	public void setIdsProgramasConcatenados(String idsProgramasConcatenados) {
		this.idsProgramasConcatenados = idsProgramasConcatenados;
	}
	/**
	 * @return the idsProgramasConcatenados
	 */
	public String getIdsProgramasConcatenados() {
		return idsProgramasConcatenados;
	}
	/**
	 * Método de acceso al campo totalPuntosDisp.
	 * @return El valor del campo totalPuntosDisp
	 */
	public int getTotalPuntosDisp() {
		return totalPuntosDisp;
	}
	/**
	 * Asigna el valor al campo totalPuntosDisp.
	 * @param totalPuntosDisp el valor totalPuntosDisp a asignar
	 */
	public void setTotalPuntosDisp(int totalPuntosDisp) {
		this.totalPuntosDisp = totalPuntosDisp;
	}
	/**
	 * Método de acceso al campo lstProgramasDisponibles.
	 * @return El valor del campo lstProgramasDisponibles
	 */
	public List<CatProgramaDTO> getLstProgramasDisponibles() {
		return lstProgramasDisponibles;
	}
	/**
	 * Asigna el valor al campo lstProgramasDisponibles.
	 * @param lstProgramasDisponibles el valor lstProgramasDisponibles a asignar
	 */
	public void setLstProgramasDisponibles(
			List<CatProgramaDTO> lstProgramasDisponibles) {
		this.lstProgramasDisponibles = lstProgramasDisponibles;
	}
	/**
	 * Método de acceso al campo lstProgramasAsignados.
	 * @return El valor del campo lstProgramasAsignados
	 */
	public List<CatProgramaDTO> getLstProgramasAsignados() {
		return lstProgramasAsignados;
	}
	/**
	 * Asigna el valor al campo lstProgramasAsignados.
	 * @param lstProgramasAsignados el valor lstProgramasAsignados a asignar
	 */
	public void setLstProgramasAsignados(List<CatProgramaDTO> lstProgramasAsignados) {
		this.lstProgramasAsignados = lstProgramasAsignados;
	}
	/**
	 * Método de acceso al campo lstCursosDisponibles.
	 * @return El valor del campo lstCursosDisponibles
	 */
	public List<CatCursoDTO> getLstCursosDisponibles() {
		return lstCursosDisponibles;
	}
	/**
	 * Asigna el valor al campo lstCursosDisponibles.
	 * @param lstCursosDisponibles el valor lstCursosDisponibles a asignar
	 */
	public void setLstCursosDisponibles(List<CatCursoDTO> lstCursosDisponibles) {
		this.lstCursosDisponibles = lstCursosDisponibles;
	}
	/**
	 * Método de acceso al campo lstCursosAsignados.
	 * @return El valor del campo lstCursosAsignados
	 */
	public List<CatCursoDTO> getLstCursosAsignados() {
		return lstCursosAsignados;
	}
	/**
	 * Asigna el valor al campo lstCursosAsignados.
	 * @param lstCursosAsignados el valor lstCursosAsignados a asignar
	 */
	public void setLstCursosAsignados(List<CatCursoDTO> lstCursosAsignados) {
		this.lstCursosAsignados = lstCursosAsignados;
	}
	/**
	 * Método de acceso al campo lstTrabajosDisponibles.
	 * @return El valor del campo lstTrabajosDisponibles
	 */
	public List<CatTrabajoDTO> getLstTrabajosDisponibles() {
		return lstTrabajosDisponibles;
	}
	/**
	 * Asigna el valor al campo lstTrabajosDisponibles.
	 * @param lstTrabajosDisponibles el valor lstTrabajosDisponibles a asignar
	 */
	public void setLstTrabajosDisponibles(List<CatTrabajoDTO> lstTrabajosDisponibles) {
		this.lstTrabajosDisponibles = lstTrabajosDisponibles;
	}
	/**
	 * Método de acceso al campo lstTrabajosAsignados.
	 * @return El valor del campo lstTrabajosAsignados
	 */
	public List<CatTrabajoDTO> getLstTrabajosAsignados() {
		return lstTrabajosAsignados;
	}
	/**
	 * Asigna el valor al campo lstTrabajosAsignados.
	 * @param lstTrabajosAsignados el valor lstTrabajosAsignados a asignar
	 */
	public void setLstTrabajosAsignados(List<CatTrabajoDTO> lstTrabajosAsignados) {
		this.lstTrabajosAsignados = lstTrabajosAsignados;
	}
	
	
}
