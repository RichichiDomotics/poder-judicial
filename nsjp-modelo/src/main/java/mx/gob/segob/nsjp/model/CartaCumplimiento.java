/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CartaCumplimiento")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "CartaCumplimiento_id")
public class CartaCumplimiento extends Documento {

	
	//Fields
	private Date fechaLectura;
	private Funcionario funcionario;
	private Boolean esLeido;
	
	public CartaCumplimiento(){
		super();
	}
	
	/**
	 * @param documentoId
	 * @param tipoDocumento
	 * @param archivoDigital
	 * @param nombreDocumento
	 * @param fechaCreacion
	 * @param responsableDocumento
	 * @param actividad
	 * @param esLeido
	 */
	public CartaCumplimiento(Long documentoId, Valor tipoDocumento,
			ArchivoDigital archivoDigital, String nombreDocumento,
			Date fechaCreacion, Funcionario responsableDocumento,
			Actividad actividad, Boolean esLeido, Date fechaLectura, Funcionario juezLectura) {
		super(documentoId, tipoDocumento, archivoDigital, nombreDocumento,
				fechaCreacion, responsableDocumento, actividad, null);
		if(juezLectura!=null)
			this.funcionario=juezLectura;
		if(fechaLectura!=null)
			this.fechaLectura=fechaLectura;
		this.esLeido=false;
		if(esLeido!=null)
			this.esLeido=esLeido;
	}
	/**
	 * @return the fechaLectura
	 */
	@Column(name = "dFechaLectura", length = 23)
	public Date getFechaLectura() {
		return fechaLectura;
	}
	/**
	 * @return the funcionario
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	/**
	 * @return the esLeido
	 */
	@Column(name = "bEsLeido", precision = 1, scale = 0)
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
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * @param esLeido the esLeido to set
	 */
	public void setEsLeido(Boolean esLeido) {
		this.esLeido = esLeido;
	}
	
	
}
