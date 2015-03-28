package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CatFaltaAdministrativa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatFaltaAdministrativa")
public class CatFaltaAdministrativa implements java.io.Serializable {

    // Fields

    private Long catFaltaAdministrativaId;
    private String claveFalta;
    private String nombreFalta;
    private String descripcionFalta;

    // Constructors

    /** default constructor */
    public CatFaltaAdministrativa() {
    }

    /** full constructor */
    public CatFaltaAdministrativa(Long catFaltaAdministrativaId,
            String cclaveFalta, String cnombreFalta, String cdescripcionFalta) {
        this.catFaltaAdministrativaId = catFaltaAdministrativaId;
        this.claveFalta = cclaveFalta;
        this.nombreFalta = cnombreFalta;
        this.descripcionFalta = cdescripcionFalta;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatFaltaAdministrativa_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getCatFaltaAdministrativaId() {
        return this.catFaltaAdministrativaId;
    }

    public void setCatFaltaAdministrativaId(Long catFaltaAdministrativaId) {
        this.catFaltaAdministrativaId = catFaltaAdministrativaId;
    }

    @Column(name = "cClaveFalta", nullable = false, length = 10)
    public String getClaveFalta() {
        return this.claveFalta;
    }

    public void setClaveFalta(String cclaveFalta) {
        this.claveFalta = cclaveFalta;
    }

    @Column(name = "cNombreFalta", nullable = false, length = 150)
    public String getNombreFalta() {
        return this.nombreFalta;
    }

    public void setNombreFalta(String cnombreFalta) {
        this.nombreFalta = cnombreFalta;
    }

    @Column(name = "cDescripcionFalta", nullable = false, length = 300)
    public String getDescripcionFalta() {
        return this.descripcionFalta;
    }

    public void setDescripcionFalta(String cdescripcionFalta) {
        this.descripcionFalta = cdescripcionFalta;
    }

}