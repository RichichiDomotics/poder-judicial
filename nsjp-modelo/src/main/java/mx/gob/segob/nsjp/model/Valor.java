package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Valor entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Valor")
public class Valor implements java.io.Serializable {

    // Fields

    private Long valorId;
    private Registro registro;
    private CampoCatalogo campoCatalogo;
    private String valor;
   
    
    // Constructors

    /** default constructor */
    public Valor() {
    }

    /** minimal constructor */
    public Valor(Long vaorId) {
        this.valorId = vaorId;
    }

    /** minimal constructor */
    public Valor(Long vaorId, String valor) {
        this.valorId = vaorId;
        this.valor = valor;
    }

    /** minimal constructor */
    public Valor(Long vaorId, String valor, Long idReg, String nombreCampo,
            Boolean esLlave) {
        this.valorId = vaorId;
        this.valor = valor;
        this.campoCatalogo = new CampoCatalogo();
        this.campoCatalogo.setNombreCampo(nombreCampo);
        this.campoCatalogo.setEsLlave(esLlave);
        this.registro = new Registro(idReg);
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			 return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Valor))
			return false;
		Valor valor = (Valor) obj;
		if(! valor.getValorId().equals(this.valorId))
			return false;
		return true;
	}

	public int hashCode() {
		return super.hashCode();
	}

	// Property accessors
    @Id
    @Column(name = "Valor_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getValorId() {
        return this.valorId;
    }

    public void setValorId(Long vaorId) {
        this.valorId = vaorId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Registro_id", nullable = false)
    public Registro getRegistro() {
        return this.registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CampoCatalogo_id", nullable = false)
    public CampoCatalogo getCampoCatalogo() {
        return this.campoCatalogo;
    }

    public void setCampoCatalogo(CampoCatalogo campoCatalogo) {
        this.campoCatalogo = campoCatalogo;
    }

    @Column(name = "cValor", nullable = false, length = 300)
    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
