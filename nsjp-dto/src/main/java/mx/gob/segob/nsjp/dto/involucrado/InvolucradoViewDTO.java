/**
* Nombre del Programa : InvolucradoViewDTO.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 13/05/2011
* Marca de cambio     : N/A
* Descripcion General : Clase que encapsula los datos de un individuo que se mostraran en la vista
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
package mx.gob.segob.nsjp.dto.involucrado;

import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;

/**
 * Clase que encapsula los datos de un individuo que se mostraran en la vista 
 * @version 1.0
 * @author Arturo Leon Galicia - Ultrasist 
 */
public class InvolucradoViewDTO extends GenericDTO {

    private static final long serialVersionUID = -3342151012609615692L;

    private Long involucradoId;
    private String nombreCompleto = ""; 
    private String nombre = "";
	private String apellidoPaterno = "";
	private String apellidoMaterno = "";
	private String nombreInstitucion = "";
	private String calidad = "";
	private Long idCalidadTipoEspecialidad = 0L;
	private String delitos = "";
	private String detenido = "";
	private boolean funcionario = false;
	private boolean esVictima;
	private ValorDTO tipoEspecialidad;
	private List<DelitoDTO> delitosCometidos;
	private Boolean esActivo; 
	//AGENCIAS
	private CatDiscriminanteDTO discriminante;
    private Boolean esProtegido;

	
	/**
	 * 
	 */
	public InvolucradoViewDTO() {}
	/**
	 * Método de acceso al campo involucradoId.
	 * @return El valor del campo involucradoId
	 */
	public Long getInvolucradoId() {
		return involucradoId;
	}
	/**
	 * Asigna el valor al campo involucradoId.
	 * @param involucradoId el valor involucradoId a asignar
	 */
	public void setInvolucradoId(Long involucradoId) {
		this.involucradoId = involucradoId;
	}
	/**
	 * Método de acceso al campo nombreCompleto.
	 * @return El valor del campo nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	/**
	 * Asigna el valor al campo nombreCompleto.
	 * @param nombreCompleto el valor nombreCompleto a asignar
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
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
	 * Método de acceso al campo delitos.
	 * @return El valor del campo delitos
	 */
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}
	/**
	 * Método de acceso al campo delitos.
	 * @return El valor del campo delitos
	 */
	public void setDelitos(String delitos) {
		this.delitos = delitos;
	}
	/**
	 * Asigna el valor al campo delitos.
	 * @param apellidoMaterno el valor delitos a asignar
	 */
	public String getDelitos() {
		return delitos;
	}

	public void setDetenido(String detenido) {
		this.detenido = detenido;
	}

	public String getDetenido() {
		return detenido;
	}
	
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public String getCalidad() {
		return calidad;
	}
	/**
	 * Método de acceso al campo idCalidadTipoEspecialidad.
	 * @return El valor del campo idCalidadTipoEspecialidad
	 */
	public Long getIdCalidadTipoEspecialidad() {
		return idCalidadTipoEspecialidad;
	}
	/**
	 * Asigna el valor al campo idCalidadTipoEspecialidad.
	 * @param idCalidadTipoEspecialidad el valor idCalidadTipoEspecialidad a asignar
	 */
	public void setIdCalidadTipoEspecialidad(Long idCalidadTipoEspecialidad) {
		this.idCalidadTipoEspecialidad = idCalidadTipoEspecialidad;
	}
	/**
	 * 
	 * @param funcionario
	 */
	public void setFuncionario(boolean funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isFuncionario() {
		return funcionario;
	}
	

	/**
	 * @return the esVictima
	 */
	public boolean isEsVictima() {
		return esVictima;
	}
	/**
	 * @param esVictima the esVictima to set
	 */
	public void setEsVictima(boolean esVictima) {
		this.esVictima = esVictima;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
	    sb.append("["+involucradoId+",");
	    sb.append(" "+nombre+"");
		sb.append(" "+apellidoPaterno+"");
		sb.append(" "+apellidoMaterno+",");
		sb.append(" "+calidad+",");
		sb.append(" "+idCalidadTipoEspecialidad+",");
		sb.append(" "+nombreInstitucion+",");
		sb.append(" "+delitos+",");
		sb.append(" "+detenido+"]");
		return sb.toString();
	}
	/**
	 * Método de acceso al campo tipoEspecialidad.
	 * @return El valor del campo tipoEspecialidad
	 */
	public ValorDTO getTipoEspecialidad() {
		return tipoEspecialidad;
	}
	/**
	 * Asigna el valor al campo tipoEspecialidad.
	 * @param tipoEspecialidad el valor tipoEspecialidad a asignar
	 */
	public void setTipoEspecialidad(ValorDTO tipoEspecialidad) {
		this.tipoEspecialidad = tipoEspecialidad;
	}
	/**
	 * Método de acceso al campo delitosCometidos.
	 * @return El valor del campo delitosCometidos
	 */
	public List<DelitoDTO> getDelitosCometidos() {
		return delitosCometidos;
	}
	/**
	 * Asigna el valor al campo delitosCometidos.
	 * @param delitosCometidos el valor delitosCometidos a asignar
	 */
	public void setDelitosCometidos(List<DelitoDTO> delitosCometidos) {
		this.delitosCometidos = delitosCometidos;
	}
	/**
	 * Método de acceso al campo esActivo.
	 * @return El valor del campo esActivo
	 */
	public Boolean getEsActivo() {
		return esActivo;
	}
	/**
	 * Asigna el valor al campo esActivo.
	 * @param esActivo el valor esActivo a asignar
	 */
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	public CatDiscriminanteDTO getDiscriminante() {
		return discriminante;
	}
	public void setDiscriminante(CatDiscriminanteDTO discriminante) {
		this.discriminante = discriminante;
	}
	

	/**
	 * Permite indicar si se trata de un testigo protegido.
	 * @return esProtegido
	 */
	public Boolean getEsProtegido() {
		return esProtegido;
	}

	public void setEsProtegido(Boolean esProtegido) {
		this.esProtegido = esProtegido;
	}

}