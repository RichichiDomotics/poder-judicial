/**
 * Nombre del Programa : NotaExpedienteDTO.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 07-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */package mx.gob.segob.nsjp.dto.expediente;

import java.util.Date;
import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto de transferencia para una nota asociada a un expediente.
 * @author rgama
 */
public class NotaExpedienteDTO extends GenericDTO {

    private Long idNota;
    private String nombreNota;
    private Date fechaCreacion;
    private String descripcion;
    private Date fechaActualizacion;
    private ExpedienteDTO expediente;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ExpedienteDTO getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteDTO expediente) {
        this.expediente = expediente;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdNota() {
        return idNota;
    }

    public void setIdNota(Long idNota) {
        this.idNota = idNota;
    }

    public String getNombreNota() {
        return nombreNota;
    }

    public void setNombreNota(String nombreNota) {
        this.nombreNota = nombreNota;
    }
}
