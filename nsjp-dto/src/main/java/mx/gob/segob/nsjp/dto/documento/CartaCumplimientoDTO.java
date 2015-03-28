/**
 * 
 */
package mx.gob.segob.nsjp.dto.documento;

import java.util.Date;

import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public class CartaCumplimientoDTO extends DocumentoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3221438861166379185L;
	
	private Date fechaLectura;
	private FuncionarioDTO funcionario;
	private Boolean esLeido;
	/**
	 * @return the fechaLectura
	 */
	public Date getFechaLectura() {
		return fechaLectura;
	}
	/**
	 * @return the funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}
	/**
	 * @return the esLeido
	 */
	public Boolean getEsLeido() {
		return esLeido;
	}
	/**
	 * @param fechaLectura the fechaLectura to set
	 */
	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}
	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * @param esLeido the esLeido to set
	 */
	public void setEsLeido(Boolean esLeido) {
		this.esLeido = esLeido;
	}

	
}
