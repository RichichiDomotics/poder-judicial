package mx.gob.segob.nsjp.model;

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
 * Telefono entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Telefono")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Telefono_id")
public class Telefono extends MedioDeContacto {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1614064791056008249L;
	
	// Fields

    private Valor valor;
    private String codigoPais;
    private String codigoArea;
    private String numeroTelefonico;

    // Constructors

    /** default constructor */
    public Telefono() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoTelefono_val", nullable = false)
    public Valor getValor() {
        return this.valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }   

    /**
	 * Método de acceso al campo codigoPais.
	 * @return El valor del campo codigoPais
	 */
    @Column(name = "iCodigoPais", length = 20)
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Asigna el valor al campo codigoPais.
	 * @param codigoPais el valor codigoPais a asignar
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Método de acceso al campo codigoArea.
	 * @return El valor del campo codigoArea
	 */
	@Column(name = "iCodigoArea", length = 20)
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * Asigna el valor al campo codigoArea.
	 * @param codigoArea el valor codigoArea a asignar
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	@Column(name = "cNumeroTelefonico", length = 15)
    public String getNumeroTelefonico() {
        return this.numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

}
