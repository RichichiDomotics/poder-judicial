package mx.gob.segob.nsjp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *@author IgnacioFO
 */
@Entity
@Table(name = "ofGroupUser")
public class OfGroupUser implements java.io.Serializable {


	// Fields

	private OfGroupUserId id;

	// Constructors

	/** default constructor */
	public OfGroupUser() {
	}

	/** full constructor */
	public OfGroupUser(OfGroupUserId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "groupName", column = @Column(name = "groupName", nullable = false)),
			@AttributeOverride(name = "username", column = @Column(name = "username", nullable = false)),
			@AttributeOverride(name = "administrator", column = @Column(name = "administrator", nullable = false)) })
	public OfGroupUserId getId() {
		return this.id;
	}

	public void setId(OfGroupUserId id) {
		this.id = id;
	}

}