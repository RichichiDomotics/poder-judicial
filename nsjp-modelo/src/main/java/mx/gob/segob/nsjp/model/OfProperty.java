package mx.gob.segob.nsjp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *@author IgnacioFO
 */
@Entity
@Table(name = "ofProperty")
public class OfProperty implements java.io.Serializable {

	// Fields

	private String name;
	private String propValue;

	// Constructors

	/** default constructor */
	public OfProperty() {
	}

	/** full constructor */
	public OfProperty(String name, String propValue) {
		this.name = name;
		this.propValue = propValue;
	}

	// Property accessors
	@Id
	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "propValue", nullable = false)
	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

}