/**
 * Nombre del Programa : DocumentoDTO.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para el traslado de atributos de Documento, entre la vista y los servicios
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


import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * DTO para el traslado de atributos de Docuento, entre la vista y los servicios. * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class DocumentoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1353128892087156435L;

	private Long documentoId;
	private ValorDTO tipoDocumentoDTO;
	private FormaDTO formaDTO;
	private String folioDocumento;
	private String nombreDocumento;
	private Date fechaCreacion;
	private String strFechaCreacion;
	private String strHoraCreacion;
	private Boolean esGuardadoParcial;
	private String strEsGuardadoParcial;
	private Double version;
	private String numeroFojas;
	private FuncionarioDTO responsableDocumento;
	private Short origenDocumento;
	private String textoParcial;
	private ArchivoDigitalDTO archivoDigital;
	
	private ExpedienteDTO expedienteDTO;
	
	private List<ElementoDTO> elementos = new ArrayList<ElementoDTO>();
	private OficioEstructuradoDTO oficioEstructuradoDTO;
    private ActividadDTO actividadDTO;

    private ConfInstitucionDTO confInstitucion;
    
    private Long catDiscriminanteOrigen;
    private String claveDiscriminanteOrigen;
    private Long idFuncionarioSolicitante;
    private String descripcion;
    private Long jerarquiaOrganizacional;

	
	public DocumentoDTO(){}
	public DocumentoDTO(Long documentoID){
		this.documentoId = documentoID;
	}
	public DocumentoDTO(FormaDTO forma){
		this.formaDTO = forma;
	}
	
	public ArchivoDigitalDTO getArchivoDigital() {
		return archivoDigital;
	}
	public void setArchivoDigital(ArchivoDigitalDTO archivoDigital) {
		this.archivoDigital = archivoDigital;
	}
	/**
	 * Agrega un elemento a la colección de elementos.
	 * @param elementito
	 */
	public void addElemento(ElementoDTO elementito) {
	    if (this.elementos==null){
	        this.elementos = new ArrayList<ElementoDTO>();
	    }
	    this.elementos.add(elementito);
	}
	
	/**
	 * @return the textoParcial
	 */
	public String getTextoParcial() {
		return textoParcial;
	}
	/**
	 * @param textoParcial the textoParcial to set
	 */
	public void setTextoParcial(String textoParcial) {
		this.textoParcial = textoParcial;
	}
	
	/**
	 * @return esGuardadoParcial
	 */
    public Boolean getEsGuardadoParcial() {
        return esGuardadoParcial;
    }
    
    /**
	 * @param esGuardadoParcial to set
	 */	
    public void setEsGuardadoParcial(Boolean esGuardadoParcial) {
        if (esGuardadoParcial == null)
            this.esGuardadoParcial = true;
        else
            this.esGuardadoParcial = esGuardadoParcial;
    }

	/**
	 * Utilizado para ser mostrado en el Gri de Documentos
	 * @return the strEsGuardadoParcial
	 */
	public String getStrEsGuardadoParcial() {
		return this.esGuardadoParcial!=null && this.esGuardadoParcial ? "Parcial": "Definitivo";
	}
	/**
	 * @param strEsGuardadoParcial the strEsGuardadoParcial to set
	 */
	public void setStrEsGuardadoParcial(String strEsGuardadoParcial) {
		this.strEsGuardadoParcial = strEsGuardadoParcial;
	}
	/**
	 * Método de acceso al campo documentoId.
	 * @return El valor del campo documentoId
	 */
	public Long getDocumentoId() {
		return documentoId;
	}
	/**
	 * Asigna el valor al campo documentoId.
	 * @param documentoId el valor documentoId a asignar
	 */
	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
	}
	/**
	 * Método de acceso al campo tipoDocumentoDTO.
	 * @return El valor del campo tipoDocumentoDTO
	 */
	public ValorDTO getTipoDocumentoDTO() {
		return tipoDocumentoDTO;
	}
	/**
	 * Asigna el valor al campo tipoDocumentoDTO.
	 * @param tipoDocumentoDTO el valor tipoDocumentoDTO a asignar
	 */
	public void setTipoDocumentoDTO(ValorDTO tipoDocumentoDTO) {
		this.tipoDocumentoDTO = tipoDocumentoDTO;
	}
	/**
	 * Método de acceso al campo formaDTO.
	 * @return El valor del campo formaDTO
	 */
	public FormaDTO getFormaDTO() {
		return formaDTO;
	}
	/**
	 * Asigna el valor al campo formaDTO.
	 * @param formaDTO el valor formaDTO a asignar
	 */
	public void setFormaDTO(FormaDTO formaDTO) {
		this.formaDTO = formaDTO;
	}
	/**
	 * Método de acceso al campo folioDocumento.
	 * @return El valor del campo folioDocumento
	 */
	public String getFolioDocumento() {
		return folioDocumento;
	}
	/**
	 * Asigna el valor al campo folioDocumento.
	 * @param folioDocumento el valor folioDocumento a asignar
	 */
	public void setFolioDocumento(String folioDocumento) {
		this.folioDocumento = folioDocumento;
	}
	/**
	 * Método de acceso al campo nombreDocumento.
	 * @return El valor del campo nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	/**
	 * Asigna el valor al campo nombreDocumento.
	 * @param nombreDocumento el valor nombreDocumento a asignar
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	/**
	 * Método de acceso al campo fechaCreacion.
	 * @return El valor del campo fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * Asigna el valor al campo fechaCreacion.
	 * @param fechaCreacion el valor fechaCreacion a asignar
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * Método de acceso al campo version.
	 * @return El valor del campo version
	 */
	public Double getVersion() {
		return version;
	}
	/**
	 * Asigna el valor al campo version.
	 * @param version el valor version a asignar
	 */
	public void setVersion(Double version) {
		this.version = version;
	}
	/**
	 * Método de acceso al campo numeroFojas.
	 * @return El valor del campo numeroFojas
	 */
	public String getNumeroFojas() {
		return numeroFojas;
	}
	/**
	 * Asigna el valor al campo numeroFojas.
	 * @param numeroFojas el valor numeroFojas a asignar
	 */
	public void setNumeroFojas(String numeroFojas) {
		this.numeroFojas = numeroFojas;
	}
	/**
	 * Método de acceso al campo responsableDocumento.
	 * @return El valor del campo responsableDocumento
	 */
	public FuncionarioDTO getResponsableDocumento() {
		return responsableDocumento;
	}
	/**
	 * Asigna el valor al campo responsableDocumento.
	 * @param responsableDocumento el valor responsableDocumento a asignar
	 */
	public void setResponsableDocumento(FuncionarioDTO responsableDocumento) {
		this.responsableDocumento = responsableDocumento;
	}
	/**
	 * Método de acceso al campo origenDocumento.
	 * @return El valor del campo origenDocumento
	 */
	public Short getOrigenDocumento() {
		return origenDocumento;
	}
	/**
	 * Asigna el valor al campo origenDocumento.
	 * @param origenDocumento el valor origenDocumento a asignar
	 */
	public void setOrigenDocumento(Short origenDocumento) {
		this.origenDocumento = origenDocumento;
	}
	/**
	 * Método de acceso al campo strFechaCreacion.
	 * @return El valor del campo strFechaCreacion
	 */
	public String getStrFechaCreacion() {
		return strFechaCreacion;
	}
	/**
	 * Asigna el valor al campo strFechaCreacion.
	 * @param strFechaCreacion el valor strFechaCreacion a asignar
	 */
	public void setStrFechaCreacion(String strFechaCreacion) {
		this.strFechaCreacion = strFechaCreacion;
	}
	/**
	 * Método de acceso al campo strHoraCreacion.
	 * @return El valor del campo strHoraCreacion
	 */
	public String getStrHoraCreacion() {
		return strHoraCreacion;
	}
	/**
	 * Asigna el valor al campo strHoraCreacion.
	 * @param strHoraCreacion el valor strHoraCreacion a asignar
	 */
	public void setStrHoraCreacion(String strHoraCreacion) {
		this.strHoraCreacion = strHoraCreacion;
	}
	/**
	 * Asigna el valor al campo expedienteDTO.
	 * @param expedienteDTO el valor expedienteDTO a asignar
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}
	/**
	 * Método de acceso al campo expedienteDTO.
	 * @return El valor del campo expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}
    /**
     * Método de acceso al campo elementos.
     * @return El valor del campo elementos
     */
    public List<ElementoDTO> getElementos() {
        return elementos;
    }
    /**
     * Asigna el valor al campo elementos.
     * @param elementos el valor elementos a asignar
     */
    public void setElementos(List<ElementoDTO> elementos) {
        this.elementos = elementos;
    }
	/**
	 * @param oficioEstructuradoDTO the oficioEstructuradoDTO to set
	 */
	public void setOficioEstructuradoDTO(OficioEstructuradoDTO oficioEstructuradoDTO) {
		this.oficioEstructuradoDTO = oficioEstructuradoDTO;
	}
	/**
	 * @return the oficioEstructuradoDTO
	 */
	public OficioEstructuradoDTO getOficioEstructuradoDTO() {
		return oficioEstructuradoDTO;
	}
	/**
	 * Método de acceso al campo actividadDTO.
	 * @return El valor del campo actividadDTO
	 */
	public ActividadDTO getActividadDTO() {
		return actividadDTO;
	}
	/**
	 * Asigna el valor al campo actividadDTO.
	 * @param actividadDTO el valor actividadDTO a asignar
	 */
	public void setActividadDTO(ActividadDTO actividadDTO) {
		this.actividadDTO = actividadDTO;
	}
	/**
	 * Establece el valor de la propiedad confInstitucion
	 * @param confInstitucion valo confInstitucion a almacenar
	 */
	public void setConfInstitucion(ConfInstitucionDTO confInstitucion) {
		this.confInstitucion = confInstitucion;
	}
	/**
	 * Regresa el valor de la propiedad confInstitucion
	 * @return the confInstitucion
	 */
	public ConfInstitucionDTO getConfInstitucion() {
		return confInstitucion;
	}
	public Long getCatDiscriminanteOrigen() {
		return catDiscriminanteOrigen;
	}
	public void setCatDiscriminanteOrigen(Long catDiscriminanteOrigen) {
		this.catDiscriminanteOrigen = catDiscriminanteOrigen;
	}
	public String getClaveDiscriminanteOrigen() {
		return claveDiscriminanteOrigen;
	}
	public void setClaveDiscriminanteOrigen(String claveDiscriminanteOrigen) {
		this.claveDiscriminanteOrigen = claveDiscriminanteOrigen;
	}
	public Long getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}
	public void setIdFuncionarioSolicitante(Long idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the jerarquiaOrganizacional
	 */
	public Long getJerarquiaOrganizacional() {
		return jerarquiaOrganizacional;
	}
	/**
	 * @param jerarquiaOrganizacional the jerarquiaOrganizacional to set
	 */
	public void setJerarquiaOrganizacional(Long jerarquiaOrganizacional) {
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
	}
	
	
	
}
