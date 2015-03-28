/**
* Nombre del Programa : BitacoraMovimientoDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para la transferencia de parametros de Bitacora Movimiento entre la vista y servicios.
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
package mx.gob.segob.nsjp.dto.bitacora;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * DTO para la transferencia de parametros de Bitacora Movimiento entre la vista y
 * servicios.
 * @version 1.0
 * @author GustavoBP
 */
public class BitacoraMovimientoDTO extends GenericDTO {

	private Long bitacoraMovimientoId;
	private Date fechaMovimiento;
	private String grupoDatos;
	private String accion;
	private String campo;
	private String valorAnterior;
	private String valorNuevo;
	
	private String numeroExpediente;
	private Long numeroExpedienteId;
	private UsuarioDTO usuario;

	private Long elemento;
	private CategoriaElementoDTO categoriaElemento;

	public BitacoraMovimientoDTO(){
	}

	/**
	 * Método de acceso al campo bitacoraMovimientoId.
	 * @return El valor del campo bitacoraMovimientoId
	 */
	public Long getBitacoraMovimientoId() {
		return bitacoraMovimientoId;
	}

	/**
	 * Asigna el valor al campo bitacoraMovimientoId.
	 * @param bitacoraMovimientoId el valor bitacoraMovimientoId a asignar
	 */
	public void setBitacoraMovimientoId(Long bitacoraMovimientoId) {
		this.bitacoraMovimientoId = bitacoraMovimientoId;
	}

	/**
	 * Método de acceso al campo fechaMovimiento.
	 * @return El valor del campo fechaMovimiento
	 */
	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	/**
	 * Asigna el valor al campo fechaMovimiento.
	 * @param fechaMovimiento el valor fechaMovimiento a asignar
	 */
	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	/**
	 * Método de acceso al campo grupoDatos.
	 * @return El valor del campo grupoDatos
	 */
	public String getGrupoDatos() {
		return grupoDatos;
	}

	/**
	 * Asigna el valor al campo grupoDatos.
	 * @param grupoDatos el valor grupoDatos a asignar
	 */
	public void setGrupoDatos(String grupoDatos) {
		this.grupoDatos = grupoDatos;
	}

	/**
	 * Método de acceso al campo accion.
	 * @return El valor del campo accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Asigna el valor al campo accion.
	 * @param accion el valor accion a asignar
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Método de acceso al campo campo.
	 * @return El valor del campo campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Asigna el valor al campo campo.
	 * @param campo el valor campo a asignar
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * Método de acceso al campo valorAnterior.
	 * @return El valor del campo valorAnterior
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 * Asigna el valor al campo valorAnterior.
	 * @param valorAnterior el valor valorAnterior a asignar
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
	 * Método de acceso al campo valorNuevo.
	 * @return El valor del campo valorNuevo
	 */
	public String getValorNuevo() {
		return valorNuevo;
	}

	/**
	 * Asigna el valor al campo valorNuevo.
	 * @param valorNuevo el valor valorNuevo a asignar
	 */
	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * Método de acceso al campo numeroExpedienteId.
	 * @return El valor del campo numeroExpedienteId
	 */
	public Long getNumeroExpedienteId() {
		return numeroExpedienteId;
	}

	/**
	 * Asigna el valor al campo numeroExpedienteId.
	 * @param numeroExpedienteId el valor numeroExpedienteId a asignar
	 */
	public void setNumeroExpedienteId(Long numeroExpedienteId) {
		this.numeroExpedienteId = numeroExpedienteId;
	}

	/**
	 * Método de acceso al campo usuario.
	 * @return El valor del campo usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * Asigna el valor al campo usuario.
	 * @param usuario el valor usuario a asignar
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método de acceso al campo elemento.
	 * @return El valor del campo elemento
	 */
	public Long getElemento() {
		return elemento;
	}

	/**
	 * Asigna el valor al campo elemento.
	 * @param elemento el valor elemento a asignar
	 */
	public void setElemento(Long elemento) {
		this.elemento = elemento;
	}

	/**
	 * Método de acceso al campo categoriaElemento.
	 * @return El valor del campo categoriaElemento
	 */
	public CategoriaElementoDTO getCategoriaElemento() {
		return categoriaElemento;
	}

	/**
	 * Asigna el valor al campo categoriaElemento.
	 * @param categoriaElemento el valor categoriaElemento a asignar
	 */
	public void setCategoriaElemento(CategoriaElementoDTO categoriaElemento) {
		this.categoriaElemento = categoriaElemento;
	}
}
