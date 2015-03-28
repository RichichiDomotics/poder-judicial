/**
* Nombre del Programa : MedidaDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dto.medida;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;


/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto Medida.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public  class MedidaDTO extends DocumentoDTO {
	
	private Date fechaInicio;
	private Date fechaFin;
	private String descripcionMedida;
	
	//Relaciones
	private InvolucradoDTO involucrado;
	private FuncionarioDTO funcionario;
	private ValorDTO valorPeriodicidad;
	private ValorDTO valorTipoMedida;
	private DomicilioDTO domicilio;
	private CompromisoPeriodicoDTO  compromisoPeriodico;
	private List<IncidenciaDTO> incidencias;
	private String seguimiento; 
	private ValorDTO estatus;
	
	private String fechaInicioStr;
	private String fechaFinStr;

    /**
     * Numero de la Causa en PJ. Campo usando en SSP.
     */
    private String numeroCausa;
    /**
     * Numero de la carpeta de ejecución en PJ. Campo usando en SSP.
     */
    private String numeroCarpetaEjecucion;
    /**
     * Nombre del juez de PJ. Campo usando en SSP.
     */
    private String juezOrdena;
	
    /**
     * Nombre de caso en PJ. Campo usando en SSP.
     */
    private String numeroCaso;
	/**
	 * Método de acceso al campo fechaInicio.
	 * @return El valor del campo fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * Asigna el valor al campo fechaInicio.
	 * @param fechaInicio el valor fechaInicio a asignar
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * Método de acceso al campo fechaFin.
	 * @return El valor del campo fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * Asigna el valor al campo fechaFin.
	 * @param fechaFin el valor fechaFin a asignar
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * Método de acceso al campo descripcionMedida.
	 * @return El valor del campo descripcionMedida
	 */
	public String getDescripcionMedida() {
		return descripcionMedida;
	}
	/**
	 * Asigna el valor al campo descripcionMedida.
	 * @param descripcionMedida el valor descripcionMedida a asignar
	 */
	public void setDescripcionMedida(String descripcionMedida) {
		this.descripcionMedida = descripcionMedida;
	}
	/**
	 * Método de acceso al campo involucrado.
	 * @return El valor del campo involucrado
	 */
	public InvolucradoDTO getInvolucrado() {
		return involucrado;
	}
	/**
	 * Asigna el valor al campo involucrado.
	 * @param involucrado el valor involucrado a asignar
	 */
	public void setInvolucrado(InvolucradoDTO involucrado) {
		this.involucrado = involucrado;
	}
	/**
	 * Método de acceso al campo funcionario.
	 * @return El valor del campo funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}
	/**
	 * Asigna el valor al campo funcionario.
	 * @param funcionario el valor funcionario a asignar
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * Método de acceso al campo valorPeriodicidad.
	 * @return El valor del campo valorPeriodicidad
	 */
	public ValorDTO getValorPeriodicidad() {
		return valorPeriodicidad;
	}
	/**
	 * Asigna el valor al campo valorPeriodicidad.
	 * @param valorPeriodicidad el valor valorPeriodicidad a asignar
	 */
	public void setValorPeriodicidad(ValorDTO valorPeriodicidad) {
		this.valorPeriodicidad = valorPeriodicidad;
	}
	/**
	 * Método de acceso al campo valorTipoMedida.
	 * @return El valor del campo valorTipoMedida
	 */
	public ValorDTO getValorTipoMedida() {
		return valorTipoMedida;
	}
	/**
	 * Asigna el valor al campo valorTipoMedida.
	 * @param valorTipoMedida el valor valorTipoMedida a asignar
	 */
	public void setValorTipoMedida(ValorDTO valorTipoMedida) {
		this.valorTipoMedida = valorTipoMedida;
	}
	/**
	 * Método de acceso al campo domicilio.
	 * @return El valor del campo domicilio
	 */
	public DomicilioDTO getDomicilio() {
		return domicilio;
	}
	/**
	 * Asigna el valor al campo domicilio.
	 * @param domicilio el valor domicilio a asignar
	 */
	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}
	/**
	 * Método de acceso al campo compromisoPeriodico.
	 * @return El valor del campo compromisoPeriodico
	 */
	public CompromisoPeriodicoDTO getCompromisoPeriodico() {
		return compromisoPeriodico;
	}
	/**
	 * Asigna el valor al campo compromisoPeriodico.
	 * @param compromisoPeriodico el valor compromisoPeriodico a asignar
	 */
	public void setCompromisoPeriodico(CompromisoPeriodicoDTO compromisoPeriodico) {
		this.compromisoPeriodico = compromisoPeriodico;
	}
	/**
	 * Método de acceso al campo incidencias.
	 * @return El valor del campo incidencias
	 */
	public List<IncidenciaDTO> getIncidencias() {
		return incidencias;
	}
	/**
	 * Asigna el valor al campo incidencias.
	 * @param incidencias el valor incidencias a asignar
	 */
	public void setIncidencias(List<IncidenciaDTO> incidencias) {
		this.incidencias = incidencias;
	}
	/**
	 * Método de acceso al campo seguimiento.
	 * @return El valor del campo seguimiento
	 */
	public String getSeguimiento() {
		return seguimiento;
	}
	/**
	 * Asigna el valor al campo seguimiento.
	 * @param seguimiento el valor seguimiento a asignar
	 */
	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}
	public void setEstatus(ValorDTO estatus) {
		this.estatus = estatus;
	}
	public ValorDTO getEstatus() {
		return estatus;
	}
    /**
     * Método de acceso al campo numeroCausa.
     * @return El valor del campo numeroCausa
     */
    public String getNumeroCausa() {
        return numeroCausa;
    }
    /**
     * Asigna el valor al campo numeroCausa.
     * @param numeroCausa el valor numeroCausa a asignar
     */
    public void setNumeroCausa(String numeroCausa) {
        this.numeroCausa = numeroCausa;
    }
    /**
     * Método de acceso al campo numeroCarpetaEjecucion.
     * @return El valor del campo numeroCarpetaEjecucion
     */
    public String getNumeroCarpetaEjecucion() {
        return numeroCarpetaEjecucion;
    }
    /**
     * Asigna el valor al campo numeroCarpetaEjecucion.
     * @param numeroCarpetaEjecucion el valor numeroCarpetaEjecucion a asignar
     */
    public void setNumeroCarpetaEjecucion(String numeroCarpetaEjecucion) {
        this.numeroCarpetaEjecucion = numeroCarpetaEjecucion;
    }
    /**
     * Método de acceso al campo juezOrdena.
     * @return El valor del campo juezOrdena
     */
    public String getJuezOrdena() {
        return juezOrdena;
    }
    /**
     * Asigna el valor al campo juezOrdena.
     * @param juezOrdena el valor juezOrdena a asignar
     */
    public void setJuezOrdena(String juezOrdena) {
        this.juezOrdena = juezOrdena;
    }
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}
	public String getNumeroCaso() {
		return numeroCaso;
	}
	/**
	 * @return the fechaInicioStr
	 */
	public String getFechaInicioStr() {
		return fechaInicioStr;
	}
	/**
	 * @param fechaInicioStr the fechaInicioStr to set
	 */
	public void setFechaInicioStr(String fechaInicioStr) {
		this.fechaInicioStr = fechaInicioStr;
	}
	/**
	 * @return the fechaFinStr
	 */
	public String getFechaFinStr() {
		return fechaFinStr;
	}
	/**
	 * @param fechaFinStr the fechaFinStr to set
	 */
	public void setFechaFinStr(String fechaFinStr) {
		this.fechaFinStr = fechaFinStr;
	}
	
}
