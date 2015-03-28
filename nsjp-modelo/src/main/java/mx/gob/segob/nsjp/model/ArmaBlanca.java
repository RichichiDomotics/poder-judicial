package mx.gob.segob.nsjp.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * ArmaBlanca entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ArmaBlanca")
@PrimaryKeyJoinColumn(name = "ArmaBlanca_id")
public class ArmaBlanca extends Objeto {

	// Fields


	// Constructors

	/** default constructor */
	public ArmaBlanca() {
	}

	
}