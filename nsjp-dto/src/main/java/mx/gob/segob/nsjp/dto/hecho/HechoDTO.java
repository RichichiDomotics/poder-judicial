/**
* Nombre del Programa : HechoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de Transferencia del Hecho
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
package mx.gob.segob.nsjp.dto.hecho;

import java.util.Date;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Objeto de Transferencia del Hecho.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class HechoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2361322546739489036L;

	private Long hechoId;
    private TiempoDTO tiempo;
    private ExpedienteDTO expediente;
    private LugarDTO lugar;
    private String descNarrativa;
    private DomicilioDTO domicilio;
    private AvisoHechoDelictivoDTO avisoHechoDelictivo;
    private Date fechaDeArribo;
    private String strfechaDeArribo;
    private String strHoraDeArribo;
    
    
    
    /**
     * 
     * @param hechoId
     */
	public HechoDTO(Long hechoId) {
		this.hechoId = hechoId;
	}
		
	public HechoDTO() {
		
	}


	/**
	 * Método de acceso al campo hechoId.
	 * @return El valor del campo hechoId
	 */
	public Long getHechoId() {
		return hechoId;
	}
	/**
	 * Asigna el valor al campo hechoId.
	 * @param hechoId el valor hechoId a asignar
	 */
	public void setHechoId(Long hechoId) {
		this.hechoId = hechoId;
	}
	/**
	 * Método de acceso al campo tiempo.
	 * @return El valor del campo tiempo
	 */
	public TiempoDTO getTiempo() {
		return tiempo;
	}
	/**
	 * Asigna el valor al campo tiempo.
	 * @param tiempo el valor tiempo a asignar
	 */
	public void setTiempo(TiempoDTO tiempo) {
		this.tiempo = tiempo;
	}
	/**
	 * Método de acceso al campo expediente.
	 * @return El valor del campo expediente
	 */
	public ExpedienteDTO getExpediente() {
		return expediente;
	}
	/**
	 * Asigna el valor al campo expediente.
	 * @param expediente el valor expediente a asignar
	 */
	public void setExpediente(ExpedienteDTO expediente) {
		this.expediente = expediente;
	}
	/**
	 * Método de acceso al campo lugar.
	 * @return El valor del campo lugar
	 */
	public LugarDTO getLugar() {
		return lugar;
	}
	/**
	 * Asigna el valor al campo lugar.
	 * @param lugar el valor lugar a asignar
	 */
	public void setLugar(LugarDTO lugar) {
		this.lugar = lugar;
	}
	/**
	 * Método de acceso al campo descNarrativa.
	 * @return El valor del campo descNarrativa
	 */
	public String getDescNarrativa() {
		return descNarrativa;
	}
	/**
	 * Asigna el valor al campo descNarrativa.
	 * @param descNarrativa el valor descNarrativa a asignar
	 */
	public void setDescNarrativa(String descNarrativa) {
		this.descNarrativa = descNarrativa;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the domicilio
	 */
	public DomicilioDTO getDomicilio() {
		return domicilio;
	}

    /**
     * Método de acceso al campo avisoHechoDelictivo.
     * @return El valor del campo avisoHechoDelictivo
     */
    public AvisoHechoDelictivoDTO getAvisoHechoDelictivo() {
        return avisoHechoDelictivo;
    }

    /**
     * Asigna el valor al campo avisoHechoDelictivo.
     * @param avisoHechoDelictivo el valor avisoHechoDelictivo a asignar
     */
    public void setAvisoHechoDelictivo(AvisoHechoDelictivoDTO avisoHechoDelictivo) {
        this.avisoHechoDelictivo = avisoHechoDelictivo;
    }

	public Date getFechaDeArribo() {
		return fechaDeArribo;
	}

	public void setFechaDeArribo(Date fechaDeArribo) {
		this.fechaDeArribo = fechaDeArribo;
		this.strHoraDeArribo = DateUtils.formatearHoraAm(fechaDeArribo);
		this.strfechaDeArribo = DateUtils.formatear(fechaDeArribo);
	}
    
	public String getStrfechaDeArribo() {
		return strfechaDeArribo;
	}
    
	public void setStrfechaDeArribo(String strfechaDeArribo) {
		this.strfechaDeArribo = strfechaDeArribo;
}

	public String getStrHoraDeArribo() {
		return strHoraDeArribo;
	}

	public void setStrHoraDeArribo(String strHoraDeArribo) {
		this.strHoraDeArribo = strHoraDeArribo;
	}
    
}
