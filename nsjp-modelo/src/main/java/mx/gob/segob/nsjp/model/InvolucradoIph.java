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
 * InvolucradoIph entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "InvolucradoIPH")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "InvolucradoIPH_id")
public class InvolucradoIph extends Involucrado {

    // Fields

    private InformePolicialHomologado informePolicialHomologado;
    private Date fechaDictamenMedico;

    private Valor situacionPolicialIndividuo;
    private Valor grupoEdad;
    private Valor grupoEtnico;
    private Valor estatusResidencial;

    // Constructors

    /** default constructor */
    public InvolucradoIph() {
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

    @Column(name = "dFechaDictamenMedico", length = 23)
    public Date getFechaDictamenMedico() {
        return this.fechaDictamenMedico;
    }

    public void setFechaDictamenMedico(Date dfechaDictamenMedico) {
        this.fechaDictamenMedico = dfechaDictamenMedico;
    }

    /**
     * Método de acceso al campo situacionPolicialIndividuo.
     * @return El valor del campo situacionPolicialIndividuo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SituacionPolicialIndividuo_val")
    public Valor getSituacionPolicialIndividuo() {
        return situacionPolicialIndividuo;
    }

    /**
     * Asigna el valor al campo situacionPolicialIndividuo.
     * @param situacionPolicialIndividuo el valor situacionPolicialIndividuo a asignar
     */
    public void setSituacionPolicialIndividuo(Valor situacionPolicialIndividuo) {
        this.situacionPolicialIndividuo = situacionPolicialIndividuo;
    }

    /**
     * Método de acceso al campo grupoEdad.
     * @return El valor del campo grupoEdad
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GrupoEdad_val")
    public Valor getGrupoEdad() {
        return grupoEdad;
    }

    /**
     * Asigna el valor al campo grupoEdad.
     * @param grupoEdad el valor grupoEdad a asignar
     */
    public void setGrupoEdad(Valor grupoEdad) {
        this.grupoEdad = grupoEdad;
    }

    /**
     * Método de acceso al campo grupoEtnico.
     * @return El valor del campo grupoEtnico
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GrupoEtnico_val")
    public Valor getGrupoEtnico() {
        return grupoEtnico;
    }

    /**
     * Asigna el valor al campo grupoEtnico.
     * @param grupoEtnico el valor grupoEtnico a asignar
     */
    public void setGrupoEtnico(Valor grupoEtnico) {
        this.grupoEtnico = grupoEtnico;
    }

    /**
     * Método de acceso al campo estatusResidencial.
     * @return El valor del campo estatusResidencial
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstatusResidencial_val")
    public Valor getEstatusResidencial() {
        return estatusResidencial;
    }

    /**
     * Asigna el valor al campo estatusResidencial.
     * @param estatusResidencial el valor estatusResidencial a asignar
     */
    public void setEstatusResidencial(Valor estatusResidencial) {
        this.estatusResidencial = estatusResidencial;
    }

}