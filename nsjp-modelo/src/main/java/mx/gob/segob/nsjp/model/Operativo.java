package mx.gob.segob.nsjp.model;

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

/**
 * Operativo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Operativo")
public class Operativo implements java.io.Serializable {

    // Fields

    private Long operativoId;
    private InformePolicialHomologado informePolicialHomologado;
    private String nombre;
    /**
     * Representa el nombre del comandante del operativo.
     */
    private String nombreComte;
    /**
     * Representa el nombre del comandante de agrupamiento
     */
    private String nombreComteAgrupto;

    // Constructors

    /** default constructor */
    public Operativo() {
    }

    /** full constructor */
    public Operativo(Long operativoId,
            InformePolicialHomologado informePolicialHomologado,
            String cnombre, String cnombreComte, String cnombreComteAgrupto) {
        this.operativoId = operativoId;
        this.informePolicialHomologado = informePolicialHomologado;
        this.nombre = cnombre;
        this.nombreComte = cnombreComte;
        this.nombreComteAgrupto = cnombreComteAgrupto;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Operativo_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getOperativoId() {
        return this.operativoId;
    }

    public void setOperativoId(Long operativoId) {
        this.operativoId = operativoId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InformePolicialHomologado_id", nullable = false)
    public InformePolicialHomologado getInformePolicialHomologado() {
        return this.informePolicialHomologado;
    }

    public void setInformePolicialHomologado(
            InformePolicialHomologado informePolicialHomologado) {
        this.informePolicialHomologado = informePolicialHomologado;
    }

    @Column(name = "cNombre", nullable = false, length = 100)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String cnombre) {
        this.nombre = cnombre;
    }

    @Column(name = "cNombreComte", nullable = false, length = 150)
    public String getNombreComte() {
        return this.nombreComte;
    }

    public void setNombreComte(String cnombreComte) {
        this.nombreComte = cnombreComte;
    }

    @Column(name = "cNombreComteAgrupto", nullable = false, length = 150)
    public String getNombreComteAgrupto() {
        return this.nombreComteAgrupto;
    }

    public void setNombreComteAgrupto(String cnombreComteAgrupto) {
        this.nombreComteAgrupto = cnombreComteAgrupto;
    }

}