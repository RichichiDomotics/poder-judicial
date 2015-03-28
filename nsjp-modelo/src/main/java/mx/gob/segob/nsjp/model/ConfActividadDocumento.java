package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Actividad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ConfActividadDocumento")
public class ConfActividadDocumento implements java.io.Serializable {

    private Long confActividadDocumentoId;
    private Valor tipoActividad;
    private JerarquiaOrganizacional jerarquiaOrganizacional; // se agrega para reflejar el modelo
    private Boolean muestraEnCombo;
    private String accion;
    private Valor tipoDocumento;
    private Valor estadoCambioExpediente;
    private Boolean usaEditor;
    private Integer grupoActividad;
    private Forma forma;
    private Valor categoriaActividad;
	private Set<Rol> rols = new HashSet<Rol>(0);

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ConfActividadDocumento_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getConfActividadDocumentoId() {
        return confActividadDocumentoId;
    }

    public void setConfActividadDocumentoId(Long confActividadDocumentoId) {
        this.confActividadDocumentoId = confActividadDocumentoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoActividad_val", nullable = false)
    public Valor getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(Valor tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoDocumento_val", nullable = true)
    public Valor getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Valor tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Column(name = "bUsaEditor", precision = 1, scale = 0)
    public Boolean getUsaEditor() {
        return usaEditor;
    }

    public void setUsaEditor(Boolean usaEditor) {
        this.usaEditor = usaEditor;
    }

    @Column(name = "iGrupoActividad", precision = 4, scale = 0)
    public Integer getGrupoActividad() {
        return grupoActividad;
    }

    public void setGrupoActividad(Integer grupoActividad) {
        this.grupoActividad = grupoActividad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JerarquiaOrganizacional_id", nullable = true)
    public JerarquiaOrganizacional getJerarquiaOrganizacional() {
        return jerarquiaOrganizacional;
    }

    public void setJerarquiaOrganizacional(JerarquiaOrganizacional jerarquiaOrganizacional) {
        this.jerarquiaOrganizacional = jerarquiaOrganizacional;
    }

    @Column(name = "bmuestracombo", precision = 1, scale = 0)
    public Boolean getMuestraEnCombo() {
        return muestraEnCombo;
    }

    public void setMuestraEnCombo(Boolean muestraEnCombo) {
        this.muestraEnCombo = muestraEnCombo;
    }

    @Column(name = "cAccion", length=100)
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstadoCambioExpediente_val", nullable = true)
    public Valor getEstadoCambioExpediente() {
        return estadoCambioExpediente;
    }

    public void setEstadoCambioExpediente(Valor estadoCambioExpediente) {
        this.estadoCambioExpediente = estadoCambioExpediente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Forma_id", nullable = false)
	public Forma getForma() {
		return forma;
	}

	public void setForma(Forma forma) {
		this.forma = forma;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoriaActividad_val", nullable = false)
	public Valor getCategoriaActividad() {
		return categoriaActividad;
	}

	public void setCategoriaActividad(Valor categoriaActividad) {
		this.categoriaActividad = categoriaActividad;
	}
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "confActividadDocumentos")
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}
}
