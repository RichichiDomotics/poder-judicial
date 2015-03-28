package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * ExpedienteTecnico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ExpedienteTecnico")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "ExpedienteTecnico_id")
public class ExpedienteTecnico extends Expediente {

    // Fields


    // Constructors

    /** default constructor */
    public ExpedienteTecnico() {
    }

  

}