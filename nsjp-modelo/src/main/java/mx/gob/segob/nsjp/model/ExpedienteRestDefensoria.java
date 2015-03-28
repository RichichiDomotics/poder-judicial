package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * ExpedienteRestDefensoria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ExpedienteRestDefensoria")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "ExpedienteRestDefensoria_id")
public class ExpedienteRestDefensoria extends Expediente {

    // Fields

    private String relacionImputadoSolicitante;
    private String direccionDondeSolicitanDefensor;
    private Date fechaDeCita;

    // Constructors

    /** default constructor */
    public ExpedienteRestDefensoria() {
    }

    // Property accessors

    @Column(name = "cRelacionImputadoSolicitante", length = 30)
    public String getRelacionImputadoSolicitante() {
        return this.relacionImputadoSolicitante;
    }

    public void setRelacionImputadoSolicitante(
            String relacionImputadoSolicitante) {
        this.relacionImputadoSolicitante = relacionImputadoSolicitante;
    }

    @Column(name = "cDireccionDondeSolicitanDefensor", length = 100)
    public String getDireccionDondeSolicitanDefensor() {
        return this.direccionDondeSolicitanDefensor;
    }

    public void setDireccionDondeSolicitanDefensor(
            String direccionDondeSolicitanDefensor) {
        this.direccionDondeSolicitanDefensor = direccionDondeSolicitanDefensor;
    }

    @Column(name = "dFechaDeCita", nullable = false, length = 23)
    public Date getFechaDeCita() {
        return this.fechaDeCita;
    }

    public void setFechaDeCita(Date fechaDeCita) {
        this.fechaDeCita = fechaDeCita;
    }

}