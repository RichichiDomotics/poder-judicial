package mx.gob.segob.nsjp.dto.catalogo;

import java.util.Date;

import mx.gob.segob.nsjp.comun.util.DateUtils;

public class DiaInhabilDTO {
	
	private Long diaInhabilId;
	private String descripcion;
	private Short dia;
	private Short mes;

	/**
	 * @return the diaInhabilId
	 */
	public Long getDiaInhabilId() {
		return diaInhabilId;
	}

	/**
	 * @param diaInhabilId the diaInhabilId to set
	 */
	public void setDiaInhabilId(Long diaInhabilId) {
		this.diaInhabilId = diaInhabilId;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the dia
	 */
	public Short getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(Short dia) {
		this.dia = dia;
	}

	/**
	 * @return the mes
	 */
	public Short getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(Short mes) {
		this.mes = mes;
	}

	public String getFecha(){
		return this.dia+"/"+this.mes;
	}
	
	public void setFecha(Date fecha){
		this.dia = (short) fecha.getDate();
		this.mes = (short) (fecha.getMonth() + 1);
	}
}
