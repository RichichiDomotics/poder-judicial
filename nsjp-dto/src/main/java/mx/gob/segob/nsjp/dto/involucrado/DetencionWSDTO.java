/**
* Nombre del Programa : DetencionWSDTO.java
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de Detencion.  
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dto.involucrado;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarWSDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de Detencion.
 * @author GustavoBP
 * @version 1.0
 */
public class DetencionWSDTO extends GenericWSDTO {
	
	private String strFechaInicioDetencion;
	private Date fechaInicioDetencion;
	private String horaInicioDetencion;
	private String strFechaFinDetencion;
	private Date fechaFinDetencion;
	private String horaFinDetencion;
	private String motivoDetencion;
	private String strFechaRecepcionDetencion;
	private Date fechaRecepcionDetencion;
	private String horaRecepcionDetencion;
	private String observaciones;
	private LugarWSDTO lugarDetencionWSDTO;
	private String lugarDetencionProvicional;
	private FuncionarioWSDTO quienDetuvoAsFun;
	private InvolucradoWSDTO quienDetuvoAsInv;
	
	/**
	 * @param detencionId
	 * @param fechaInicioDetencion
	 * @param horaInicioDetencion
	 * @param fechaFinDetencion
	 * @param horaFinDetencion
	 * @param motivoDetencion
	 * @param fechaRecepcionDetencion
	 * @param horaRecepcionDetencion
	 * @param observaciones
	 * @param involucradoDTO
	 */
	public DetencionWSDTO(String fechaInicioDetencion,
			String horaInicioDetencion, String fechaFinDetencion,
			String horaFinDetencion, String motivoDetencion,
			String fechaRecepcionDetencion, String horaRecepcionDetencion,
			String observaciones) {
		super();
		this.strFechaInicioDetencion = fechaInicioDetencion;
		this.horaInicioDetencion = horaInicioDetencion;
		this.strFechaFinDetencion = fechaFinDetencion;
		this.horaFinDetencion = horaFinDetencion;
		this.motivoDetencion = motivoDetencion;
		this.strFechaRecepcionDetencion = fechaRecepcionDetencion;
		this.horaRecepcionDetencion = horaRecepcionDetencion;
		this.observaciones = observaciones;
	}

	/**
	 * 
	 */
	public DetencionWSDTO() {
		super();
	}

	/**
	 * Método de acceso al campo strFechaInicioDetencion.
	 * @return El valor del campo strFechaInicioDetencion
	 */
	public String getStrFechaInicioDetencion() {
		return strFechaInicioDetencion;
	}

	/**
	 * Asigna el valor al campo strFechaInicioDetencion.
	 * @param strFechaInicioDetencion el valor strFechaInicioDetencion a asignar
	 */
	public void setStrFechaInicioDetencion(String strFechaInicioDetencion) {
		this.strFechaInicioDetencion = strFechaInicioDetencion;
	}

	/**
	 * Método de acceso al campo fechaInicioDetencion.
	 * @return El valor del campo fechaInicioDetencion
	 */
	public Date getFechaInicioDetencion() {
		return fechaInicioDetencion;
	}

	/**
	 * Asigna el valor al campo fechaInicioDetencion.
	 * @param fechaInicioDetencion el valor fechaInicioDetencion a asignar
	 */
	public void setFechaInicioDetencion(Date fechaInicioDetencion) {
		this.fechaInicioDetencion = fechaInicioDetencion;
	}

	/**
	 * Método de acceso al campo horaInicioDetencion.
	 * @return El valor del campo horaInicioDetencion
	 */
	public String getHoraInicioDetencion() {
		return horaInicioDetencion;
	}

	/**
	 * Asigna el valor al campo horaInicioDetencion.
	 * @param horaInicioDetencion el valor horaInicioDetencion a asignar
	 */
	public void setHoraInicioDetencion(String horaInicioDetencion) {
		this.horaInicioDetencion = horaInicioDetencion;
	}

	/**
	 * Método de acceso al campo strFechaFinDetencion.
	 * @return El valor del campo strFechaFinDetencion
	 */
	public String getStrFechaFinDetencion() {
		return strFechaFinDetencion;
	}

	/**
	 * Asigna el valor al campo strFechaFinDetencion.
	 * @param strFechaFinDetencion el valor strFechaFinDetencion a asignar
	 */
	public void setStrFechaFinDetencion(String strFechaFinDetencion) {
		this.strFechaFinDetencion = strFechaFinDetencion;
	}

	/**
	 * Método de acceso al campo fechaFinDetencion.
	 * @return El valor del campo fechaFinDetencion
	 */
	public Date getFechaFinDetencion() {
		return fechaFinDetencion;
	}

	/**
	 * Asigna el valor al campo fechaFinDetencion.
	 * @param fechaFinDetencion el valor fechaFinDetencion a asignar
	 */
	public void setFechaFinDetencion(Date fechaFinDetencion) {
		this.fechaFinDetencion = fechaFinDetencion;
	}

	/**
	 * Método de acceso al campo horaFinDetencion.
	 * @return El valor del campo horaFinDetencion
	 */
	public String getHoraFinDetencion() {
		return horaFinDetencion;
	}

	/**
	 * Asigna el valor al campo horaFinDetencion.
	 * @param horaFinDetencion el valor horaFinDetencion a asignar
	 */
	public void setHoraFinDetencion(String horaFinDetencion) {
		this.horaFinDetencion = horaFinDetencion;
	}

	/**
	 * Método de acceso al campo motivoDetencion.
	 * @return El valor del campo motivoDetencion
	 */
	public String getMotivoDetencion() {
		return motivoDetencion;
	}

	/**
	 * Asigna el valor al campo motivoDetencion.
	 * @param motivoDetencion el valor motivoDetencion a asignar
	 */
	public void setMotivoDetencion(String motivoDetencion) {
		this.motivoDetencion = motivoDetencion;
	}

	/**
	 * Método de acceso al campo strFechaRecepcionDetencion.
	 * @return El valor del campo strFechaRecepcionDetencion
	 */
	public String getStrFechaRecepcionDetencion() {
		return strFechaRecepcionDetencion;
	}

	/**
	 * Asigna el valor al campo strFechaRecepcionDetencion.
	 * @param strFechaRecepcionDetencion el valor strFechaRecepcionDetencion a asignar
	 */
	public void setStrFechaRecepcionDetencion(String strFechaRecepcionDetencion) {
		this.strFechaRecepcionDetencion = strFechaRecepcionDetencion;
	}

	/**
	 * Método de acceso al campo fechaRecepcionDetencion.
	 * @return El valor del campo fechaRecepcionDetencion
	 */
	public Date getFechaRecepcionDetencion() {
		return fechaRecepcionDetencion;
	}

	/**
	 * Asigna el valor al campo fechaRecepcionDetencion.
	 * @param fechaRecepcionDetencion el valor fechaRecepcionDetencion a asignar
	 */
	public void setFechaRecepcionDetencion(Date fechaRecepcionDetencion) {
		this.fechaRecepcionDetencion = fechaRecepcionDetencion;
	}

	/**
	 * Método de acceso al campo horaRecepcionDetencion.
	 * @return El valor del campo horaRecepcionDetencion
	 */
	public String getHoraRecepcionDetencion() {
		return horaRecepcionDetencion;
	}

	/**
	 * Asigna el valor al campo horaRecepcionDetencion.
	 * @param horaRecepcionDetencion el valor horaRecepcionDetencion a asignar
	 */
	public void setHoraRecepcionDetencion(String horaRecepcionDetencion) {
		this.horaRecepcionDetencion = horaRecepcionDetencion;
	}

	/**
	 * Método de acceso al campo observaciones.
	 * @return El valor del campo observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Asigna el valor al campo observaciones.
	 * @param observaciones el valor observaciones a asignar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Método de acceso al campo lugarDetencionWSDTO.
	 * @return El valor del campo lugarDetencionWSDTO
	 */
	public LugarWSDTO getLugarDetencion() {
		return lugarDetencionWSDTO;
	}

	/**
	 * Asigna el valor al campo lugarDetencionWSDTO.
	 * @param lugarDetencionWSDTO el valor lugarDetencionWSDTO a asignar
	 */
	public void setLugarDetencion(LugarWSDTO lugarDetencionWSDTO) {
		this.lugarDetencionWSDTO = lugarDetencionWSDTO;
	}

	/**
	 * Método de acceso al campo lugarDetencionProvicional.
	 * @return El valor del campo lugarDetencionProvicional
	 */
	public String getLugarDetencionProvicional() {
		return lugarDetencionProvicional;
	}

	/**
	 * Asigna el valor al campo lugarDetencionProvicional.
	 * @param lugarDetencionProvicional el valor lugarDetencionProvicional a asignar
	 */
	public void setLugarDetencionProvicional(String lugarDetencionProvicional) {
		this.lugarDetencionProvicional = lugarDetencionProvicional;
	}

    /**
     * Método de acceso al campo lugarDetencionWSDTO.
     * @return El valor del campo lugarDetencionWSDTO
     */
    public LugarWSDTO getLugarDetencionWSDTO() {
        return lugarDetencionWSDTO;
    }

    /**
     * Asigna el valor al campo lugarDetencionWSDTO.
     * @param lugarDetencionWSDTO el valor lugarDetencionWSDTO a asignar
     */
    public void setLugarDetencionWSDTO(LugarWSDTO lugarDetencionWSDTO) {
        this.lugarDetencionWSDTO = lugarDetencionWSDTO;
    }

    /**
     * Método de acceso al campo quienDetuvoAsFun.
     * @return El valor del campo quienDetuvoAsFun
     */
    public FuncionarioWSDTO getQuienDetuvoAsFun() {
        return quienDetuvoAsFun;
    }

    /**
     * Asigna el valor al campo quienDetuvoAsFun.
     * @param quienDetuvoAsFun el valor quienDetuvoAsFun a asignar
     */
    public void setQuienDetuvoAsFun(FuncionarioWSDTO quienDetuvoAsFun) {
        this.quienDetuvoAsFun = quienDetuvoAsFun;
    }

    /**
     * Método de acceso al campo quienDetuvoAsInv.
     * @return El valor del campo quienDetuvoAsInv
     */
    public InvolucradoWSDTO getQuienDetuvoAsInv() {
        return quienDetuvoAsInv;
    }

    /**
     * Asigna el valor al campo quienDetuvoAsInv.
     * @param quienDetuvoAsInv el valor quienDetuvoAsInv a asignar
     */
    public void setQuienDetuvoAsInv(InvolucradoWSDTO quienDetuvoAsInv) {
        this.quienDetuvoAsInv = quienDetuvoAsInv;
    }
		
}
	