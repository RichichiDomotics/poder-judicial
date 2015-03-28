package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "EntrevistaInicial")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "EntrevistaInicial_id")
public class EntrevistaInicial extends Sesion {

	private Boolean esVictimaDirecta;
	private String motivoAtencion;
	
	public EntrevistaInicial(){		
	}

	public void setEsVictimaDirecta(Boolean esVictimaDirecta) {
		this.esVictimaDirecta = esVictimaDirecta;
	}
	
    @Column(name = "bEsVictimaDirecta", precision = 1, scale = 0)
	public Boolean getEsVictimaDirecta() {
		return esVictimaDirecta;
	}
	
	public void setMotivoAtencion(String motivoAtencion) {
		this.motivoAtencion = motivoAtencion;
	}
	
    @Column(name = "cMotivoAtencion")
	public String getMotivoAtencion() {
		return motivoAtencion;
	}


}
