package mx.gob.segob.nsjp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FaltaAdministrativaIph entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FaltaAdministrativaIPH")
public class FaltaAdministrativaIph implements java.io.Serializable {

    // Fields

    private FaltaAdministrativaIphId id;
    private CatFaltaAdministrativa catFaltaAdministrativa;
    private InformePolicialHomologado informePolicialHomologado;

    // Constructors

    /** default constructor */
    public FaltaAdministrativaIph() {
    }

    /** full constructor */
    public FaltaAdministrativaIph(FaltaAdministrativaIphId id,
            CatFaltaAdministrativa catFaltaAdministrativa,
            InformePolicialHomologado informePolicialHomologado) {
        this.id = id;
        this.catFaltaAdministrativa = catFaltaAdministrativa;
        this.informePolicialHomologado = informePolicialHomologado;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "informePolicialHomologadoId", column = @Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)),
            @AttributeOverride(name = "catFaltaAdministrativaId", column = @Column(name = "CatFaltaAdministrativa_id", nullable = false, precision = 18, scale = 0))})
    public FaltaAdministrativaIphId getId() {
        return this.id;
    }

    public void setId(FaltaAdministrativaIphId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CatFaltaAdministrativa_id", nullable = false, insertable = false, updatable = false)
    public CatFaltaAdministrativa getCatFaltaAdministrativa() {
        return this.catFaltaAdministrativa;
    }

    public void setCatFaltaAdministrativa(
            CatFaltaAdministrativa catFaltaAdministrativa) {
        this.catFaltaAdministrativa = catFaltaAdministrativa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InformePolicialHomologado_id", nullable = false, insertable = false, updatable = false)
    public InformePolicialHomologado getInformePolicialHomologado() {
        return this.informePolicialHomologado;
    }

    public void setInformePolicialHomologado(
            InformePolicialHomologado informePolicialHomologado) {
        this.informePolicialHomologado = informePolicialHomologado;
    }

}