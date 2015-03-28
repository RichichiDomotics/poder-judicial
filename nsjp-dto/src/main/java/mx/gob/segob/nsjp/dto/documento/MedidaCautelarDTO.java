/**
 * Nombre del Programa : MedidaCautelarDTO.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 6 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase que define el objeto de Medida Cautelar
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
package mx.gob.segob.nsjp.dto.documento;

import java.util.Date;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;

/**
 * Clase que define el objeto de Medida Cautelar
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class MedidaCautelarDTO extends MedidaDTO {

    private FuncionarioDTO funcionario;
    private ExpedienteDTO expediente;
    private ValorDTO valorPeriodicidad;
    private InvolucradoDTO involucrado;
    private ValorDTO valorTipoMedida;
    private Date fechaInicio;
    private Date fechaFin;
    private String seguimiento;
    private Boolean esActivo;
    private DomicilioDTO domicilio;

    private boolean guardadoDefinitivo;

    private String strFechaInicio;
    private String strFechaFin;

    private String descripcion;
    private ValorDTO estatus;
    private String numeroCausa;
    private String numeroCarpetaEjecucion;
    private String juezOrdena;
    private String numeroCaso;
    

    /**
     * Método de acceso al campo funcionario.
     * 
     * @return El valor del campo funcionario
     */
    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }
    /**
     * Asigna el valor al campo funcionario.
     * 
     * @param funcionario
     *            el valor funcionario a asignar
     */
    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }
    /**
     * Método de acceso al campo expediente.
     * 
     * @return El valor del campo expediente
     */
    public ExpedienteDTO getExpediente() {
        return expediente;
    }
    /**
     * Asigna el valor al campo expediente.
     * 
     * @param expediente
     *            el valor expediente a asignar
     */
    public void setExpediente(ExpedienteDTO expediente) {
        this.expediente = expediente;
    }
    /**
     * Método de acceso al campo valorPeriodicidad.
     * 
     * @return El valor del campo valorPeriodicidad
     */
    public ValorDTO getValorPeriodicidad() {
        return valorPeriodicidad;
    }
    /**
     * Asigna el valor al campo valorPeriodicidad.
     * 
     * @param valorPeriodicidad
     *            el valor valorPeriodicidad a asignar
     */
    public void setValorPeriodicidad(ValorDTO valorPeriodicidad) {
        this.valorPeriodicidad = valorPeriodicidad;
    }
    /**
     * Método de acceso al campo involucrado.
     * 
     * @return El valor del campo involucrado
     */
    public InvolucradoDTO getInvolucrado() {
        return involucrado;
    }
    /**
     * Asigna el valor al campo involucrado.
     * 
     * @param involucrado
     *            el valor involucrado a asignar
     */
    public void setInvolucrado(InvolucradoDTO involucrado) {
        this.involucrado = involucrado;
    }
    /**
     * Método de acceso al campo valorTipoMedida.
     * 
     * @return El valor del campo valorTipoMedida
     */
    public ValorDTO getValorTipoMedida() {
        return valorTipoMedida;
    }
    /**
     * Asigna el valor al campo valorTipoMedida.
     * 
     * @param valorTipoMedida
     *            el valor valorTipoMedida a asignar
     */
    public void setValorTipoMedida(ValorDTO valorTipoMedida) {
        this.valorTipoMedida = valorTipoMedida;
    }
    /**
     * Método de acceso al campo fechaInicio.
     * 
     * @return El valor del campo fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Asigna el valor al campo fechaInicio.
     * 
     * @param fechaIni
     *            el valor fechaInicio a asignar
     */
    public void setFechaInicio(Date fechaIni) {
        this.fechaInicio = fechaIni;
        this.setStrFechaInicio(DateUtils.formatear(fechaIni));
    }
    /**
     * Método de acceso al campo fechaFin.
     * 
     * @return El valor del campo fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }
    /**
     * Asigna el valor al campo fechaFin.
     * 
     * @param fechaF
     *            el valor fechaFin a asignar
     */
    public void setFechaFin(Date fechaF) {
        this.fechaFin = fechaF;
        this.setStrFechaFin(DateUtils.formatear(fechaF));
    }
    /**
     * Método de acceso al campo seguimiento.
     * 
     * @return El valor del campo seguimiento
     */
    public String getSeguimiento() {
        return seguimiento;
    }
    /**
     * Asigna el valor al campo seguimiento.
     * 
     * @param seguimiento
     *            el valor seguimiento a asignar
     */
    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }
    /**
     * Método de acceso al campo esActivo.
     * 
     * @return El valor del campo esActivo
     */
    public Boolean getEsActivo() {
        return esActivo;
    }
    /**
     * Asigna el valor al campo esActivo.
     * 
     * @param esActivo
     *            el valor esActivo a asignar
     */
    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }
    /**
     * Método de acceso al campo domicilio.
     * 
     * @return El valor del campo domicilio
     */
    public DomicilioDTO getDomicilio() {
        return domicilio;
    }
    /**
     * Asigna el valor al campo domicilio.
     * 
     * @param domicilio
     *            el valor domicilio a asignar
     */
    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }
    /**
     * Método de acceso al campo guardadoDefinitivo.
     * 
     * @return El valor del campo guardadoDefinitivo
     */
    public boolean isGuardadoDefinitivo() {
        return guardadoDefinitivo;
    }
    /**
     * Asigna el valor al campo guardadoDefinitivo.
     * 
     * @param guardadoDefinitivo
     *            el valor guardadoDefinitivo a asignar
     */
    public void setGuardadoDefinitivo(boolean guardadoDefinitivo) {
        this.guardadoDefinitivo = guardadoDefinitivo;
    }

    /**
     * Asigna el valor al campo strFechaInicio.
     * @param strFechaInicio el valor strFechaInicio a asignar
     */
    public void setStrFechaInicio(String strFechaInicio) {
        this.strFechaInicio = strFechaInicio;
    }
    /**
     * Asigna el valor al campo strFechaFin.
     * @param strFechaFin el valor strFechaFin a asignar
     */
    public void setStrFechaFin(String strFechaFin) {
        this.strFechaFin = strFechaFin;
    }
    /**
     * Método de acceso al campo strFechaInicio.
     * @return El valor del campo strFechaInicio
     */
    public String getStrFechaInicio() {
        return strFechaInicio;
    }
    /**
     * Método de acceso al campo strFechaFin.
     * @return El valor del campo strFechaFin
     */
    public String getStrFechaFin() {
        return strFechaFin;
    }
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ValorDTO getEstatus() {
		return estatus;
	}
	public void setEstatus(ValorDTO estatus) {
		this.estatus = estatus;
	}
	public String getNumeroCausa() {
		return numeroCausa;
	}
	public void setNumeroCausa(String numeroCausa) {
		this.numeroCausa = numeroCausa;
	}
	public String getNumeroCarpetaEjecucion() {
		return numeroCarpetaEjecucion;
	}
	public void setNumeroCarpetaEjecucion(String numeroCarpetaEjecucion) {
		this.numeroCarpetaEjecucion = numeroCarpetaEjecucion;
	}
	public String getJuezOrdena() {
		return juezOrdena;
	}
	public void setJuezOrdena(String juezOrdena) {
		this.juezOrdena = juezOrdena;
	}
	public String getNumeroCaso() {
		return numeroCaso;
	}
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}
    
    //Implementado para ser utilizado en Servicio de ConsultarBandejaVisitaduria
    public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof MedidaCautelarDTO))
			return false;
		MedidaCautelarDTO funcionTemp = (MedidaCautelarDTO) obj;

		return ((this.getDocumentoId() == funcionTemp
				.getDocumentoId()));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getDocumentoId() == null ? 0 : this
						.getDocumentoId().hashCode());
		return result;
	}


}
