/**
* Nombre del Programa : DetencionDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto detencion.
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

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto detencion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class DetencionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2020824489435891517L;
	
	private Long detencionId;	
	private String strFechaInicioDetencion;
	private Date fechaInicioDetencion;
	private String horaInicioDetencion;
	private String strFechaFinDetencion;
	private Date fechaFinDetencion;
	private String horaFinDetencion;
	private String motivoDetencion;
	private String strFechaRecepcionDetencion;
	/**
	 * Regitro de cuando se recibe al imputado.
	 */
	private Date fechaRecepcionDetencion;
	private String horaRecepcionDetencion;
	private String observaciones;
	private InvolucradoDTO involucradoDTO;
	private LugarDTO lugarDetencionDTO;
	private FuncionarioDTO funcionarioByFuncionarioDetiene;
	private String lugarDetencionProvicional;
	
	private AvisoDetencionDTO avisoDetencion;
	
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
	public DetencionDTO(Long detencionId, String fechaInicioDetencion,
			String horaInicioDetencion, String fechaFinDetencion,
			String horaFinDetencion, String motivoDetencion,
			String fechaRecepcionDetencion, String horaRecepcionDetencion,
			String observaciones, InvolucradoDTO involucradoDTO) {
		super();
		this.detencionId = detencionId;
		this.strFechaInicioDetencion = fechaInicioDetencion;
		this.horaInicioDetencion = horaInicioDetencion;
		this.strFechaFinDetencion = fechaFinDetencion;
		this.horaFinDetencion = horaFinDetencion;
		this.motivoDetencion = motivoDetencion;
		this.strFechaRecepcionDetencion = fechaRecepcionDetencion;
		this.horaRecepcionDetencion = horaRecepcionDetencion;
		this.observaciones = observaciones;
		this.involucradoDTO = involucradoDTO;
	}

	public DetencionDTO(Long detencionId) {
        super();
        this.detencionId = detencionId;
    }
	
	/**
	 * 
	 */
	public DetencionDTO() {
		super();
	}

	/**
	 * Método de acceso al campo detencionId.
	 * @return El valor del campo detencionId
	 */
	public Long getDetencionId() {
		return detencionId;
	}

	/**
	 * Asigna el valor al campo detencionId.
	 * @param detencionId el valor detencionId a asignar
	 */
	public void setDetencionId(Long detencionId) {
		this.detencionId = detencionId;
	}

	/**
	 * Método de acceso al campo fechaInicioDetencion.
	 * @return El valor del campo fechaInicioDetencion
	 */
	public String getStrFechaInicioDetencion() {
		if(this.strFechaInicioDetencion!=null){
			return strFechaInicioDetencion;
		}
        return (this.fechaInicioDetencion != null
                ? DateUtils.formatear(fechaInicioDetencion)
                : null);

	}
	
	/**
	 * Asigna el valor al campo fechaInicioDetencion.
	 * @param fechaInicioDetencion el valor fechaInicioDetencion a asignar
	 */
	public void setFechaInicioDetencion(String fechaInicioDetencion) {
		this.strFechaInicioDetencion = fechaInicioDetencion;
	}

	/**
	 * Método de acceso al campo fechaFinDetencion.
	 * @return El valor del campo fechaFinDetencion
	 */
	public String getstrfechaFinDetencion() {
		if(this.strFechaFinDetencion!=null){
			return strFechaFinDetencion;
		}
        return (this.fechaFinDetencion != null
                ? DateUtils.formatear(fechaFinDetencion)
                : null);

	}

	/**
	 * Asigna el valor al campo fechaFinDetencion.
	 * @param fechaFinDetencion el valor fechaFinDetencion a asignar
	 */
	public void setFechaFinDetencion(String fechaFinDetencion) {
		this.strFechaFinDetencion = fechaFinDetencion;
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
	 * Método de acceso al campo fechaRecepcionDetencion.
	 * @return El valor del campo fechaRecepcionDetencion
	 */
	public String getStrFechaRecepcionDetencion() {
		if(this.strFechaRecepcionDetencion!=null){
			return strFechaRecepcionDetencion;
		}
        return (this.fechaRecepcionDetencion != null
                ? DateUtils.formatear(fechaRecepcionDetencion)
                : null);
	}

	/**
	 * Asigna el valor al campo fechaRecepcionDetencion.
	 * @param fechaRecepcionDetencion el valor fechaRecepcionDetencion a asignar
	 */
	public void setFechaRecepcionDetencion(String fechaRecepcionDetencion) {
		this.strFechaRecepcionDetencion = fechaRecepcionDetencion;
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
	 * Método de acceso al campo involucradoDTO.
	 * @return El valor del campo involucradoDTO
	 */
	public InvolucradoDTO getInvolucradoDTO() {
		return involucradoDTO;
	}

	/**
	 * Asigna el valor al campo involucradoDTO.
	 * @param involucradoDTO el valor involucradoDTO a asignar
	 */
	public void setInvolucradoDTO(InvolucradoDTO involucradoDTO) {
		this.involucradoDTO = involucradoDTO;
	}
	
	/**
	 * M?todo de acceso al campo horaInicioDetencion.
	 * @return El valor del campo horaInicioDetencion
	 */
	public String getHoraInicioDetencion() {
		if(this.horaInicioDetencion!=null)
			return this.horaInicioDetencion;
		return (this.fechaInicioDetencion!=null
				? DateUtils.formatearHora(this.fechaInicioDetencion)
				: null);

	}

	/**
	 * Asigna el valor al campo horaInicioDetencion.
	 * @param horaInicioDetencion el valor horaInicioDetencion a asignar
	 */
	public void setHoraInicioDetencion(String horaInicioDetencion) {
		this.horaInicioDetencion = horaInicioDetencion;
	}

	/**
	 * M?todo de acceso al campo horaFinDetencion.
	 * @return El valor del campo horaFinDetencion
	 */
	public String getHoraFinDetencion() {
		if(this.horaFinDetencion!=null)
			return this.horaFinDetencion;
		return (this.fechaFinDetencion!=null
				? DateUtils.formatearHora(this.fechaFinDetencion)
				: null);

	}

	/**
	 * Asigna el valor al campo horaFinDetencion.
	 * @param horaFinDetencion el valor horaFinDetencion a asignar
	 */
	public void setHoraFinDetencion(String horaFinDetencion) {
		this.horaFinDetencion = horaFinDetencion;
	}

	/**
	 * M?todo de acceso al campo horaRecepcionDetencion.
	 * @return El valor del campo horaRecepcionDetencion
	 */
	public String getHoraRecepcionDetencion() {
		if(this.horaRecepcionDetencion!=null)
			return this.horaRecepcionDetencion;
		return (this.fechaRecepcionDetencion!=null
				? DateUtils.formatearHora(this.fechaRecepcionDetencion)
				: null);
	}

	/**
	 * M�todo de acceso al campo fechaInicioDetencion.
	 * @return El valor del campo fechaInicioDetencion
	 */
	public Date getFechaInicioDetencion() {
        if (fechaInicioDetencion == null) {
        	setFechaInicioDetencion(DateUtils.obtenerNulleable(this.strFechaInicioDetencion,
                    this.horaInicioDetencion));
        }
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
	 * M�todo de acceso al campo fechaFinDetencion.
	 * @return El valor del campo fechaFinDetencion
	 */
	public Date getFechaFinDetencion() {
        if (fechaFinDetencion == null) {
        	setFechaFinDetencion(DateUtils.obtenerNulleable(this.strFechaFinDetencion,
                    this.horaFinDetencion));
        }

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
	 * M�todo de acceso al campo fechaRecepcionDetencion.
	 * @return El valor del campo fechaRecepcionDetencion
	 */
	public Date getFechaRecepcionDetencion() {
        if (fechaRecepcionDetencion == null) {
        	setFechaFinDetencion(DateUtils.obtenerNulleable(this.strFechaRecepcionDetencion,
                    this.horaRecepcionDetencion));
        }
		
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
	 * Asigna el valor al campo horaRecepcionDetencion.
	 * @param horaRecepcionDetencion el valor horaRecepcionDetencion a asignar
	 */
	public void setHoraRecepcionDetencion(String horaRecepcionDetencion) {
		this.horaRecepcionDetencion = horaRecepcionDetencion;
	}

	/**
	 * M�todo de acceso al campo lugarDetencionDTO.
	 * @return El valor del campo lugarDetencionDTO
	 */
	public LugarDTO getLugarDetencionDTO() {
		return lugarDetencionDTO;
	}

	/**
	 * Asigna el valor al campo lugarDetencionDTO.
	 * @param lugarDetencionDTO el valor lugarDetencionDTO a asignar
	 */
	public void setLugarDetencionDTO(LugarDTO lugarDetencionDTO) {
		this.lugarDetencionDTO = lugarDetencionDTO;
	}

	/**
	 * M�todo de acceso al campo funcionarioByFuncionarioDetiene.
	 * @return El valor del campo funcionarioByFuncionarioDetiene
	 */
	public FuncionarioDTO getFuncionarioByFuncionarioDetiene() {
		return funcionarioByFuncionarioDetiene;
	}

	/**
	 * Asigna el valor al campo funcionarioByFuncionarioDetiene.
	 * @param funcionarioByFuncionarioDetiene el valor funcionarioByFuncionarioDetiene a asignar
	 */
	public void setFuncionarioByFuncionarioDetiene(
			FuncionarioDTO funcionarioByFuncionarioDetiene) {
		this.funcionarioByFuncionarioDetiene = funcionarioByFuncionarioDetiene;
	}

	/**
	 * @return the lugarDetencionProvicional
	 */
	public String getLugarDetencionProvicional() {
		return lugarDetencionProvicional;
	}

	/**
	 * @param lugarDetencionProvicional the lugarDetencionProvicional to set
	 */
	public void setLugarDetencionProvicional(String lugarDetencionProvicional) {
		this.lugarDetencionProvicional = lugarDetencionProvicional;
	}

	/**
	 * @return the avisoDetencion
	 */
	public AvisoDetencionDTO getAvisoDetencion() {
		return avisoDetencion;
	}

	/**
	 * @param avisoDetencion the avisoDetencion to set
	 */
	public void setAvisoDetencion(AvisoDetencionDTO avisoDetencion) {
		this.avisoDetencion = avisoDetencion;
	}
	
	
	
	
}
	