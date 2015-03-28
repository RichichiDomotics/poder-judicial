/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "QuejaConfirmada")
public class QuejaConfirmada implements Serializable {
	
	private Long quejaConfirmadaId;
	private List<Nota> notas;//TODO VAP Construir la lista o set de notas
	private Funcionario funcionarioDenunciado;
	private Expediente expediente;
	   private QuejaCiudadana quejaCiudadana;
	public QuejaConfirmada() {
		super();
	}
	/**
	 * @param quejaConfirmadaId
	 */
	public QuejaConfirmada(Long quejaConfirmadaId) {
		super();
		this.quejaConfirmadaId = quejaConfirmadaId;
	}
	/**
	 * @return the quejaConfirmadaId
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "QuejaConfirmada_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getQuejaConfirmadaId() {
		return quejaConfirmadaId;
	}
	/**
	 * @return the notas
	 */
	@Transient
	//TODO VAP Construir la lista o set de notas
	public List<Nota> getNotas() {
		return notas;
	}
	/**
	 * @return the funcionarioDenunciado
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable = false)
	public Funcionario getFuncionarioDenunciado() {
		return funcionarioDenunciado;
	}
	/**
	 * @return the expediente
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", nullable = false)
	public Expediente getExpediente() {
		return expediente;
	}
	/**
	 * @param quejaConfirmadaId the quejaConfirmadaId to set
	 */
	public void setQuejaConfirmadaId(Long quejaConfirmadaId) {
		this.quejaConfirmadaId = quejaConfirmadaId;
	}
	/**
	 * @param notas the notas to set
	 */
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	/**
	 * @param funcionarioDenunciado the funcionarioDenunciado to set
	 */
	public void setFuncionarioDenunciado(Funcionario funcionarioDenunciado) {
		this.funcionarioDenunciado = funcionarioDenunciado;
	}
	/**
	 * @param expediente the expediente to set
	 */
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QuejaCiudadana_id", nullable = false)
    public QuejaCiudadana getQuejaCiudadana() {
        return this.quejaCiudadana;
    }

    public void setQuejaCiudadana(QuejaCiudadana quejaCiudadana) {
        this.quejaCiudadana = quejaCiudadana;
    }	

}
