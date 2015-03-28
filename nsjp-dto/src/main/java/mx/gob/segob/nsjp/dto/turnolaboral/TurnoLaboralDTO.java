package mx.gob.segob.nsjp.dto.turnolaboral;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class TurnoLaboralDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private Long turnoLaboralId;
	 private String nombreTurno;
	 private Date horaInicioTurno;
	 private Date horaFinTurno;
	
	 
	 public Long getTurnoLaboralId() {
		return turnoLaboralId;
	}
	public void setTurnoLaboralId(Long turnoLaboralId) {
		this.turnoLaboralId = turnoLaboralId;
	}
	public String getNombreTurno() {
		return nombreTurno;
	}
	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}
	public Date getHoraInicioTurno() {
		return horaInicioTurno;
	}
	public void setHoraInicioTurno(Date horaInicioTurno) {
		this.horaInicioTurno = horaInicioTurno;
	}
	public Date getHoraFinTurno() {
		return horaFinTurno;
	}
	public void setHoraFinTurno(Date horaFinTurno) {
		this.horaFinTurno = horaFinTurno;
	}
	 
	 
	
}
