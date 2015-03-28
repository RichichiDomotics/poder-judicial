/**

 * Nombre del Programa : AlmacenDTO.java                                    
 * Autor                            : Tattva-IT                                              
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 3 May 2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : IntegraciÃ³n xxxxxxxxxxx                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.dto.almacen;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto
 * Almacen.
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class AlmacenDTO extends GenericDTO {

    private Long identificadorAlmacen;
    private Boolean estatusActivo;
    private Set<ObjetoDTO> objetos = new HashSet<ObjetoDTO>(0);
    private String nombreAlmacen;
    private String descripcion;
    private Boolean esVirtual = Boolean.FALSE;
    private Date fechaAlta;
    private DomicilioDTO domicilio;
    private FuncionarioDTO funcionarioAlta;
    private FuncionarioDTO funcionarioAutoriza;
    private String numeroExpediente;
    private Boolean resguardaEvidencias;

    public AlmacenDTO(Long identificadorAlmacen) {
        this.identificadorAlmacen = identificadorAlmacen;
    }

    public AlmacenDTO() {
    }

    /**
     * Mï¿½todo de acceso al campo identificadorAlmacen.
     * 
     * @return El valor del campo identificadorAlmacen
     */
    public Long getIdentificadorAlmacen() {
        return identificadorAlmacen;
    }

    /**
     * Asigna el valor al campo identificadorAlmacen.
     * 
     * @param identificadorAlmacen
     *            el valor identificadorAlmacen a asignar
     */
    public void setIdentificadorAlmacen(Long identificadorAlmacen) {
        this.identificadorAlmacen = identificadorAlmacen;
    }

    /**
     * Mï¿½todo de acceso al campo estatusActivo.
     * 
     * @return El valor del campo estatusActivo
     */
    public Boolean getEstatusActivo() {
        return estatusActivo;
    }

    /**
     * Asigna el valor al campo estatusActivo.
     * 
     * @param estatusActivo
     *            el valor estatusActivo a asignar
     */
    public void setEstatusActivo(Boolean estatusActivo) {
        this.estatusActivo = estatusActivo;
    }

    /**
     * Mï¿½todo de acceso al campo objetos.
     * 
     * @return El valor del campo objetos
     */
    public Set<ObjetoDTO> getObjetos() {
        return objetos;
    }

    /**
     * Asigna el valor al campo objetos.
     * 
     * @param objetos
     *            el valor objetos a asignar
     */
    public void setObjetos(Set<ObjetoDTO> objetos) {
        this.objetos = objetos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public FuncionarioDTO getFuncionarioAlta() {
        return funcionarioAlta;
    }

    public void setFuncionarioAlta(FuncionarioDTO funcionarioAlta) {
        this.funcionarioAlta = funcionarioAlta;
    }

    public FuncionarioDTO getFuncionarioAutoriza() {
        return funcionarioAutoriza;
    }

    public void setFuncionarioAutoriza(FuncionarioDTO funcionarioAutoriza) {
        this.funcionarioAutoriza = funcionarioAutoriza;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public Boolean getEsVirtual() {
        return esVirtual;
    }

    public void setEsVirtual(Boolean esVirtual) {
        this.esVirtual = esVirtual;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

	/**
	 * Método de acceso al campo resguardaEvidencias.
	 * @return El valor del campo resguardaEvidencias
	 */
	public Boolean getResguardaEvidencias() {
		return resguardaEvidencias;
	}

	/**
	 * Asigna el valor al campo resguardaEvidencias.
	 * @param resguardaEvidencias el valor resguardaEvidencias a asignar
	 */
	public void setResguardaEvidencias(Boolean resguardaEvidencias) {
		this.resguardaEvidencias = resguardaEvidencias;
	}
    
    
}
