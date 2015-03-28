package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Intento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Intento")
public class Intento implements java.io.Serializable {

    // Fields
    private Long intentoId;
    private Date fechaIntento;
    private String consecutivoIntento;
    private Notificacion notificacion;



    // Constructors
    
    /** default constructor */
    public Intento() {
    }

	// Property accessors
    @Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Intento_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getIntentoId() {
		return this.intentoId;
	}

	public void setIntentoId(Long intentoId) {
		this.intentoId = intentoId;
	}
    @Column(name = "dFechaIntento", length = 23, nullable = true)
	public Date getFechaIntento() {
		return fechaIntento;
	}

	public void setFechaIntento(Date fechaIntento) {
		this.fechaIntento = fechaIntento;
	}
	@Column(name = "cConsecutivoIntento", length = 10)
	public String getConsecutivoIntento() {
		return consecutivoIntento;
	}

	public void setConsecutivoIntento(String consecutivoIntento) {
		this.consecutivoIntento = consecutivoIntento;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iIdentificadorNotificacion", nullable = true)
	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}		
   
	
	
}
