package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Relacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Relacion")
public class Relacion implements java.io.Serializable {

    // Fields

    private Long relacionId;
    private CatRelacion catRelacion;
    private Elemento elementoByComplementoId;
    private Elemento elementoBySujetoId;
    private Short tipoRelacion;
    private Boolean esActivo;

    // Constructors

    /** default constructor */
    public Relacion() {
    }

    /** minimal constructor */
    public Relacion(Long reacionId, CatRelacion catRelacion,
            Elemento elementoByComplementoId, Elemento elementoBySujetoId) {
        this.relacionId = reacionId;
        this.catRelacion = catRelacion;
        this.elementoByComplementoId = elementoByComplementoId;
        this.elementoBySujetoId = elementoBySujetoId;
    }

    /** full constructor */
    public Relacion(Long reacionId, CatRelacion catRelacion,
            Elemento elementoByComplementoId, Elemento elementoBySujetoId,
            Short tipoRelacion) {
        this.relacionId = reacionId;
        this.catRelacion = catRelacion;
        this.elementoByComplementoId = elementoByComplementoId;
        this.elementoBySujetoId = elementoBySujetoId;
        this.tipoRelacion = tipoRelacion;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Relacion_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getRelacionId() {
        return this.relacionId;
    }

    public void setRelacionId(Long reacionId) {
        this.relacionId = reacionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CatRelacion_id", nullable = false)
    public CatRelacion getCatRelacion() {
        return this.catRelacion;
    }

    public void setCatRelacion(CatRelacion catRelacion) {
        this.catRelacion = catRelacion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Complemento_id", nullable = false)
    public Elemento getElementoByComplementoId() {
        return this.elementoByComplementoId;
    }

    public void setElementoByComplementoId(Elemento elementoByComplementoId) {
        this.elementoByComplementoId = elementoByComplementoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sujeto_id", nullable = false)
    public Elemento getElementoBySujetoId() {
        return this.elementoBySujetoId;
    }

    public void setElementoBySujetoId(Elemento elementoBySujetoId) {
        this.elementoBySujetoId = elementoBySujetoId;
    }

    @Column(name = "iTipoRelacion", precision = 4, scale = 0)
    public Short getTipoRelacion() {
        return this.tipoRelacion;
    }

    public void setTipoRelacion(Short tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

	/**
	 * Método de acceso al campo esActivo.
	 * @return El valor del campo esActivo
	 */
    @Column(name = "bEsActivo", precision = 1, scale = 0)
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
}
