/**
 * Nombre del Programa : NotificacionDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia de la notificacion.
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.Notificaciones;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Objeto de transferencia de la notificacion.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@SuppressWarnings("serial")
public class NotificacionDTO extends DocumentoDTO {
    private String lugar;
    private String motivo;
    private Notificaciones tipo = Notificaciones.FISICA;
    private String domicilio;
    private String lugarCitado;
    private String direccionCitado;
    private Date fechaCitado;
    private String penalidades;
    private String personaAutoriza;
    private String puestoAutoriza;
    private String consecutivoNotificacion;
    private String numeroExpedienteUsuarioNotif;
    private String numeroCasoAsociado;
    
    private Date fechaRecepcion;
    private ValorDTO estatus;
    private String folioNotificacion;
    
    /**
     * Intentos de la notificacion.
     */
    private List<IntentoDTO> intentos = new ArrayList<IntentoDTO>();

    /**
     * Método de acceso al campo intentos.
     * 
     * @return El valor del campo intentos
     */
    public List<IntentoDTO> getIntentos() {
        return intentos;
    }

    /**
     * Asigna el valor al campo intentos.
     * 
     * @param intentos
     *            el valor intentos a asignar
     */
    public void setIntentos(List<IntentoDTO> intentos) {
        this.intentos = intentos;
    }

    /**
     * Método de acceso al campo lugar.
     * 
     * @return El valor del campo lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Asigna el valor al campo lugar.
     * 
     * @param lugar
     *            el valor lugar a asignar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Método de acceso al campo motivo.
     * 
     * @return El valor del campo motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Asigna el valor al campo motivo.
     * 
     * @param motivo
     *            el valor motivo a asignar
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Método de acceso al campo tipo.
     * 
     * @return El valor del campo tipo
     */
    public Notificaciones getTipo() {
        return tipo;
    }

    /**
     * Asigna el valor al campo tipo.
     * 
     * @param tipo
     *            el valor tipo a asignar
     */
    public void setTipo(Notificaciones tipo) {
        this.tipo = tipo;
    }

    /**
     * Método de acceso al campo domicilio.
     * 
     * @return El valor del campo domicilio
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Asigna el valor al campo domicilio.
     * 
     * @param domicilio
     *            el valor domicilio a asignar
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Método de acceso al campo lugarCitado.
     * 
     * @return El valor del campo lugarCitado
     */
    public String getLugarCitado() {
        return lugarCitado;
    }

    /**
     * Asigna el valor al campo lugarCitado.
     * 
     * @param lugarCitado
     *            el valor lugarCitado a asignar
     */
    public void setLugarCitado(String lugarCitado) {
        this.lugarCitado = lugarCitado;
    }

    /**
     * Método de acceso al campo fechaCitado.
     * 
     * @return El valor del campo fechaCitado
     */
    public Date getFechaCitado() {
        return fechaCitado;
    }

    /**
     * Asigna el valor al campo fechaCitado.
     * 
     * @param fechaCitado
     *            el valor fechaCitado a asignar
     */
    public void setFechaCitado(Date fechaCitado) {
        this.fechaCitado = fechaCitado;
    }

    /**
     * Método de acceso al campo penalidades.
     * 
     * @return El valor del campo penalidades
     */
    public String getPenalidades() {
        return penalidades;
    }

    /**
     * Asigna el valor al campo penalidades.
     * 
     * @param penalidades
     *            el valor penalidades a asignar
     */
    public void setPenalidades(String penalidades) {
        this.penalidades = penalidades;
    }

    /**
     * Método de acceso al campo personaAutoriza.
     * 
     * @return El valor del campo personaAutoriza
     */
    public String getPersonaAutoriza() {
        return personaAutoriza;
    }

    /**
     * Asigna el valor al campo personaAutoriza.
     * 
     * @param personaAutoriza
     *            el valor personaAutoriza a asignar
     */
    public void setPersonaAutoriza(String personaAutoriza) {
        this.personaAutoriza = personaAutoriza;
    }

    /**
     * Método de acceso al campo puestoAutoriza.
     * 
     * @return El valor del campo puestoAutoriza
     */
    public String getPuestoAutoriza() {
        return puestoAutoriza;
    }

    /**
     * Asigna el valor al campo puestoAutoriza.
     * 
     * @param puestoAutoriza
     *            el valor puestoAutoriza a asignar
     */
    public void setPuestoAutoriza(String puestoAutoriza) {
        this.puestoAutoriza = puestoAutoriza;
    }

    /**
     * Método de acceso al campo consecutivoNotificacion.
     * 
     * @return El valor del campo consecutivoNotificacion
     */
    public String getConsecutivoNotificacion() {
        return consecutivoNotificacion;
    }

    /**
     * Asigna el valor al campo consecutivoNotificacion.
     * 
     * @param consecutivoNotificacion
     *            el valor consecutivoNotificacion a asignar
     */
    public void setConsecutivoNotificacion(String consecutivoNotificacion) {
        this.consecutivoNotificacion = consecutivoNotificacion;
    }

    /**
     * Método de acceso al campo fechaRecepcion.
     * @return El valor del campo fechaRecepcion
     */
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * Asigna el valor al campo fechaRecepcion.
     * @param fechaRecepcion el valor fechaRecepcion a asignar
     */
    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * Método de acceso al campo direccionCitado.
     * @return El valor del campo direccionCitado
     */
    public String getDireccionCitado() {
        return direccionCitado;
    }

    /**
     * Asigna el valor al campo direccionCitado.
     * @param direccionCitado el valor direccionCitado a asignar
     */
    public void setDireccionCitado(String direccionCitado) {
        this.direccionCitado = direccionCitado;
    }

    public String getNumeroExpedienteUsuarioNotif() {
        return numeroExpedienteUsuarioNotif;
    }

    public void setNumeroExpedienteUsuarioNotif(String numeroExpedienteUsuarioNotif) {
        this.numeroExpedienteUsuarioNotif = numeroExpedienteUsuarioNotif;
    }

	/**
	 * Regresa el valor de la propiedad numeroCasoAsociado
	 * @return the numeroCasoAsociado
	 */
	public String getNumeroCasoAsociado() {
		return numeroCasoAsociado;
	}

	/**
	 * Establece el valor de la propiedad numeroCasoAsociado
	 * @param numeroCasoAsociado valo numeroCasoAsociado a almacenar
	 */
	public void setNumeroCasoAsociado(String numeroCasoAsociado) {
		this.numeroCasoAsociado = numeroCasoAsociado;
	}

	public void setEstatus(ValorDTO estatus) {
		this.estatus = estatus;
	}

	public ValorDTO getEstatus() {
		return estatus;
	}

    /**
     * Método de acceso al campo folioNotificacion.
     * @return El valor del campo folioNotificacion
     */
    public String getFolioNotificacion() {
        return folioNotificacion;
    }

    /**
     * Asigna el valor al campo folioNotificacion.
     * @param folioNotificacion el valor folioNotificacion a asignar
     */
    public void setFolioNotificacion(String folioNotificacion) {
        this.folioNotificacion = folioNotificacion;
    }
}
