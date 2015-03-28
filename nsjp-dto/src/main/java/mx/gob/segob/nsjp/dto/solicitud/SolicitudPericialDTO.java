/**
 * Nombre del Programa : SolicitudPericialDTO.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 3 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para el traslado de atributos de SolicitudPericial, entre la vista y los servicios
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
package mx.gob.segob.nsjp.dto.solicitud;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * DTO para el traslado de atributos de SolicitudPericial, entre la vista y los
 * servicios.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class SolicitudPericialDTO extends SolicitudDTO {

    private static final long serialVersionUID = 518802694367084184L;
    private ValorDTO especialidad;
    private Boolean esEntregaNotificacionFisica;
    private String puestoUsuarioSolicitante;
    
    private List<FuncionarioDTO> peritosDesignados;
    
    
    private List<SolicitudPericialDTO> solicitudesHijas;
    /**
     * @deprecated Usar destinatario de solicitudDTO
     */
    private FuncionarioDTO peritoAsignado;
    
    private Date fechaInicioPrestamo;
    private Date fechaFinPrestamo;
    
    
    private String fechaInicioPrestamoStr;
    private String fechaFinPrestamoStr;
    
    
    private boolean dictamenTerminado;
    
    List<DictamenDTO> dictamenes = new ArrayList<DictamenDTO>();
    
    public SolicitudPericialDTO() {

    }

    public SolicitudPericialDTO(Long id) {
        setDocumentoId(id);
    }
    /**
     * Agrega un dictamen a la lista.
     * @param docto
     */
    public void addDictamen(DictamenDTO docto) {
        if (this.dictamenes == null) {
            this.dictamenes = new ArrayList<DictamenDTO>();
        }
        this.dictamenes.add(docto);
    }
    
    /**
     * Método de acceso al campo especialidad.
     * 
     * @return El valor del campo especialidad
     */

    public ValorDTO getEspecialidad() {
        return especialidad;
    }
    /**
     * Asigna el valor al campo especialidad.
     * 
     * @param especialidad
     *            el valor especialidad a asignar
     */
    public void setEspecialidad(ValorDTO especialidad) {
        this.especialidad = especialidad;
    }
    /**
     * Método de acceso al campo esEntregaNotificacionFisica.
     * 
     * @return El valor del campo esEntregaNotificacionFisica
     */
    public Boolean getEsEntregaNotificacionFisica() {
        return esEntregaNotificacionFisica;
    }
    /**
     * Asigna el valor al campo esEntregaNotificacionFisica.
     * 
     * @param esEntregaNotificacionFisica
     *            el valor esEntregaNotificacionFisica a asignar
     */
    public void setEsEntregaNotificacionFisica(
            Boolean esEntregaNotificacionFisica) {
        this.esEntregaNotificacionFisica = esEntregaNotificacionFisica;
    }
    /**
     * Método de acceso al campo puestoUsuarioSolicitante.
     * 
     * @return El valor del campo puestoUsuarioSolicitante
     */
    public String getPuestoUsuarioSolicitante() {
        return puestoUsuarioSolicitante;
    }
    /**
     * Asigna el valor al campo puestoUsuarioSolicitante.
     * 
     * @param puestoUsuarioSolicitante
     *            el valor puestoUsuarioSolicitante a asignar
     */
    public void setPuestoUsuarioSolicitante(String puestoUsuarioSolicitante) {
        this.puestoUsuarioSolicitante = puestoUsuarioSolicitante;
    }

    /**
     * Método de acceso al campo peritoAsignado.
     * @return El valor del campo peritoAsignado
     */
    public FuncionarioDTO getPeritoAsignado() {
        return peritoAsignado;
    }

    /**
     * Asigna el valor al campo peritoAsignado.
     * @param peritoAsignado el valor peritoAsignado a asignar
     */
    public void setPeritoAsignado(FuncionarioDTO peritoAsignado) {
        this.peritoAsignado = peritoAsignado;
    }

	/**
	 * Método de acceso al campo peritosDesignados.
	 * @return El valor del campo peritosDesignados
	 */
	public List<FuncionarioDTO> getPeritosDesignados() {
		return peritosDesignados;
	}

	/**
	 * Asigna el valor al campo peritosDesignados.
	 * @param peritosDesignados el valor peritosDesignados a asignar
	 */
	public void setPeritosDesignados(List<FuncionarioDTO> peritosDesignados) {
		this.peritosDesignados = peritosDesignados;
	}

    /**
     * Método de acceso al campo fechaInicioPrestamo.
     * @return El valor del campo fechaInicioPrestamo
     */
    public Date getFechaInicioPrestamo() {
        return fechaInicioPrestamo;
    }

    /**
     * Asigna el valor al campo fechaInicioPrestamo.
     * @param fechaInicioPrestamoP el valor fechaInicioPrestamo a asignar
     */
    public void setFechaInicioPrestamo(Date fechaInicioPrestamoP) {
        this.setFechaInicioPrestamoStr(DateUtils.formatear(fechaInicioPrestamoP));
        this.fechaInicioPrestamo = fechaInicioPrestamoP;
    }

    /**
     * Método de acceso al campo fechaFinPrestamo.
     * @return El valor del campo fechaFinPrestamo
     */
    public Date getFechaFinPrestamo() {
        return fechaFinPrestamo;
    }

    /**
     * Asigna el valor al campo fechaFinPrestamo.
     * @param fechaFinPrestamoP el valor fechaFinPrestamo a asignar
     */
    public void setFechaFinPrestamo(Date fechaFinPrestamoP) {
        this.setFechaFinPrestamoStr(DateUtils.formatear(fechaFinPrestamoP));
        this.fechaFinPrestamo = fechaFinPrestamoP;
    }

    /**
     * Método de acceso al campo fechaInicioPrestamoStr.
     * @return El valor del campo fechaInicioPrestamoStr
     */
    public String getFechaInicioPrestamoStr() {
        return fechaInicioPrestamoStr;
    }

    /**
     * Asigna el valor al campo fechaInicioPrestamoStr.
     * @param fechaInicioPrestamoStr el valor fechaInicioPrestamoStr a asignar
     */
    public void setFechaInicioPrestamoStr(String fechaInicioPrestamoStr) {
        this.fechaInicioPrestamoStr = fechaInicioPrestamoStr;
    }

    /**
     * Método de acceso al campo fechaFinPrestamoStr.
     * @return El valor del campo fechaFinPrestamoStr
     */
    public String getFechaFinPrestamoStr() {
        return fechaFinPrestamoStr;
    }

    /**
     * Asigna el valor al campo fechaFinPrestamoStr.
     * @param fechaFinPrestamoStr el valor fechaFinPrestamoStr a asignar
     */
    public void setFechaFinPrestamoStr(String fechaFinPrestamoStr) {
        this.fechaFinPrestamoStr = fechaFinPrestamoStr;
    }

	/**
	 * Método de acceso al campo solicitudesHijas.
	 * @return El valor del campo solicitudesHijas
	 */
	public List<SolicitudPericialDTO> getSolicitudesHijas() {
		return solicitudesHijas;
	}

	/**
	 * Asigna el valor al campo solicitudesHijas.
	 * @param solicitudesHijas el valor solicitudesHijas a asignar
	 */
	public void setSolicitudesHijas(List<SolicitudPericialDTO> solicitudesHijas) {
		this.solicitudesHijas = solicitudesHijas;
	}

    /**
     * Método de acceso al campo dictamenTerminado.
     * @return El valor del campo dictamenTerminado
     */
    public boolean isDictamenTerminado() {
        return dictamenTerminado;
    }

    /**
     * Asigna el valor al campo dictamenTerminado.
     * @param dictamenTerminado el valor dictamenTerminado a asignar
     */
    public void setDictamenTerminado(boolean dictamenTerminado) {
        this.dictamenTerminado = dictamenTerminado;
    }

    /**
     * Método de acceso al campo dictamenes.
     * @return El valor del campo dictamenes
     */
    public List<DictamenDTO> getDictamenes() {
        return dictamenes;
    }

    /**
     * Asigna el valor al campo dictamenes.
     * @param dictamenes el valor dictamenes a asignar
     */
    public void setDictamenes(List<DictamenDTO> dictamenes) {
        this.dictamenes = dictamenes;
    }

}
