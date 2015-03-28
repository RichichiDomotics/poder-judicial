package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *@author IgnacioFO
 */
@Entity
@Table(name = "ofUser", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username")})
public class OfUser implements java.io.Serializable {

	// Fields

	private String username;
	private String plainPassword;
	private String encryptedPassword;
	private String name;
	private String email;
	private String creationDate;
	private String modificationDate;
	
	
	// Constructors

	/** default constructor */
	public OfUser() {
	}

	/** minimal constructor */
	public OfUser(String username,String creationDate,String modificationDate) {
		this.username = username;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}

	/** minimal constructor */
	public OfUser(String username,String plainPassword,String creationDate,String modificationDate) {
		this.username = username;
		this.plainPassword = plainPassword;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}
	
	
	/** full constructor */
	public OfUser(String username,String plainPassword,String encryptedPassword,String name,String email,String creationDate,String modificationDate) {
		this.username = username;
		this.plainPassword = plainPassword;
		this.creationDate = encryptedPassword;
		this.modificationDate = name;
		this.creationDate = email;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}

	/**
	 * @return the username
	 */
	// Property accessors
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 64)
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the plainPassword
	 */
	@Column(name = "plainPassword",nullable = true, length = 32)
	public String getPlainPassword() {
		return plainPassword;
	}

	/**
	 * @param plainPassword the plainPassword to set
	 */
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	/**
	 * @return the encryptedPassword
	 */
	@Column(name = "encryptedPassword", nullable = true, length = 255)
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * @param encryptedPassword the encryptedPassword to set
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	/**
	 * @return the name
	 */
	@Column(name = "name", nullable = true, length = 100)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	@Column(name = "email", nullable = true, length = 100)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the creationDate
	 */
	@Column(name = "creationDate", nullable = false, length = 15)
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the modificationDate
	 */
	@Column(name = "modificationDate", nullable = false, length = 15)
	public String getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate the modificationDate to set
	 */
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}



}