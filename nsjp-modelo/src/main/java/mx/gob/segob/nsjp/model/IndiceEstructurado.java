package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IndiceEstruturado entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "IndiceEstructurado")
public class IndiceEstructurado implements java.io.Serializable {

    // Fields
	//consultarIndice

    private Long indiceEstructuradoId;
    private String nombreEtiqueta;
    private String textoEtiqueta;
    private Short nivel;
    private Valor tipoOficio;
    private IndiceEstructurado indiceEstructuradoPadre;
    private List<IndiceEstructurado> indicesEstructurados = new ArrayList<IndiceEstructurado>(0);

    
    // Constructors

    /** default constructor */
    public IndiceEstructurado() {
    }

    // Property accessors
    @Id
    @Column(name = "IndiceEstructurado_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getIndiceEstructuradoId() {
        return this.indiceEstructuradoId;
    }

    public void setIndiceEstructuradoId(Long indiceId) {
        this.indiceEstructuradoId = indiceId;
    }

	/**
	 * Método de acceso al campo nombreEtiqueta.
	 * @return El valor del campo nombreEtiqueta
	 */
    @Column(name = "cNombreEtiqueta", length = 100)
	public String getNombreEtiqueta() {
		return nombreEtiqueta;
	}

	/**
	 * Asigna el valor al campo nombreEtiqueta.
	 * @param nombreEtiqueta el valor nombreEtiqueta a asignar
	 */
	public void setNombreEtiqueta(String nombreEtiqueta) {
		this.nombreEtiqueta = nombreEtiqueta;
	}

	/**
	 * Método de acceso al campo textoEtiqueta.
	 * @return El valor del campo textoEtiqueta
	 */
	@Column(name = "cTextoEtiqueta")
	public String getTextoEtiqueta() {
		return textoEtiqueta;
	}

	/**
	 * Asigna el valor al campo textoEtiqueta.
	 * @param textoEtiqueta el valor textoEtiqueta a asignar
	 */
	public void setTextoEtiqueta(String textoEtiqueta) {
		this.textoEtiqueta = textoEtiqueta;
	}

	/**
	 * Método de acceso al campo nivel.
	 * @return El valor del campo nivel
	 */
	@Column(name = "iNivel", nullable = false, precision = 4, scale = 0)
	public Short getNivel() {
		return nivel;
	}

	/**
	 * Asigna el valor al campo nivel.
	 * @param nivel el valor nivel a asignar
	 */
	public void setNivel(Short nivel) {
		this.nivel = nivel;
	}

	/**
	 * Método de acceso al campo tipoOficio.
	 * @return El valor del campo tipoOficio
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoOficio_val")
	public Valor getTipoOficio() {
		return tipoOficio;
	}

	/**
	 * Asigna el valor al campo tipoOficio.
	 * @param tipoOficio el valor tipoOficio a asignar
	 */
	public void setTipoOficio(Valor tipoOficio) {
		this.tipoOficio = tipoOficio;
	}

	/**
	 * Método de acceso al campo indicesEstructurados.
	 * @return El valor del campo indicesEstructurados
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "indiceEstructuradoPadre")
	public List<IndiceEstructurado> getIndicesEstructurados() {
		return indicesEstructurados;
	}

	/**
	 * Método de acceso al campo indiceEstructuradoPadre.
	 * @return El valor del campo indiceEstructuradoPadre
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IndiceEstructuradoPadre_id")
	public IndiceEstructurado getIndiceEstructuradoPadre() {
		return indiceEstructuradoPadre;
	}

	/**
	 * Asigna el valor al campo indiceEstructuradoPadre.
	 * @param indiceEstructuradoPadre el valor indiceEstructuradoPadre a asignar
	 */
	public void setIndiceEstructuradoPadre(
			IndiceEstructurado indiceEstructuradoPadre) {
		this.indiceEstructuradoPadre = indiceEstructuradoPadre;
	}

	/**
	 * Asigna el valor al campo indicesEstructurados.
	 * @param indicesEstructurados el valor indicesEstructurados a asignar
	 */
	public void setIndicesEstructurados(
			List<IndiceEstructurado> indicesEstructurados) {
		this.indicesEstructurados = indicesEstructurados;
	}
	
}


