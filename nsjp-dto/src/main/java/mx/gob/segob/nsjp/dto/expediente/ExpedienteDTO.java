/**
 * Nombre del Programa : ExpedienteDTO.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para la transferencia de parametros de Expediente entre la vista y servicios.
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
package mx.gob.segob.nsjp.dto.expediente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;


/**
 * DTO para la transferencia de parametros de Expediente entre la vista y
 * servicios.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class ExpedienteDTO extends GenericDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 76740324788091271L;

    /**
     * Id del expediente maestro (s�lo sistema).
     */
    private Long expedienteId;
    /**
     * N�mero que se muestra al usuario.
     */
    private String numeroExpediente;
    /**
     * Id del numero de expediente, usado para solicitudes y audiencias
     * principalmente (s�lo sistema).
     */
    private Long numeroExpedienteId;
    /**
     * Cuando �ste (<code>this</code>) expediente representa una TOCA la
     * propiedad representa la causa;
     */
    private ExpedienteDTO causaPadre;

    private Date fechaApertura;
    private Date fechaAperturaNumeroExp;

    private Date fechaCierre;
    private CasoDTO casoDTO;
    private String narrativa;
    private List<ElementoDTO> elementosDTO = new ArrayList<ElementoDTO>();
    private List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>();
    private List<ObjetoDTO> objetosDTO = new ArrayList<ObjetoDTO>();

    private String strFechaApertura;
    private String strHoraApertura;

    private String strFechaCierre;
    private String strHoraCierre;

    private List<DelitoDTO> delitos = new ArrayList<DelitoDTO>();

    private HechoDTO hechoDTO;

    private ActividadDTO actividadActual;

    private DelitoDTO delitoPrincipal;

    private AreaDTO area;

    private ValorDTO estatus;
    private List<DocumentoDTO> documentosDTO = new ArrayList<DocumentoDTO>();

    private ValorDTO etapa;

    private ValorDTO origen;
    /**
     * Usado en PJ para saber si es Causa, TOCA o Carpeta de Ejecuci�n.<br>
     * Se usa en el registro tambi�n para pasar el tipo del nuevo que se tiene
     * que crear.
     */
    private ValorDTO tipoExpediente;

    private List<ExpedienteDTO> numExpHijos = new ArrayList<ExpedienteDTO>();

    private List<AvisoDesignacionDTO> avisosDesignacion = new ArrayList<AvisoDesignacionDTO>();;

    private InvolucradoDTO inputado;

    private InvolucradoDTO solicitante;

    private boolean esEscritura;
    private boolean esPropietario;
    private Date fechaLimitePermiso;
    private ValorDTO estatusExpedientePadre;

    /* ****** usados en vista ****** */

    private boolean consulta;

    /* ****** usados en consultas ****** */
    private boolean medidasCautelaresSolicitadas;
    private boolean involucradosSolicitados;
    private boolean domicliosInvolucradoSolicitados;
    private boolean objetosSolicitados;
    private boolean cadenasCustodiaSolicitadas;
    private boolean documentosSolicitados;
    private boolean hechoSolicitado;
    private boolean avisosDesignacionSolicitados;
    /* ****** usados en consultas ****** */

    /* ** Utilizado para saber a que instituci�n pertenece ** */
    private ConfInstitucionDTO pertenceConfInst;

    private AlmacenDTO almacenDTO;

    private List<AudienciaDTO> audiencias;
    /**
     * Etapas (de Defensor�a) por las que ha pasado el expediente.
     * 
     */
    private List<EtapasExpediente> etapasPasadas = new ArrayList<EtapasExpediente>();
    
    /**
     * Usado para ubicar expedientes por agencia
     */
    private CatDiscriminanteDTO discriminante;
    
    //Es utilizado para indicar si el Expediente fue replicado en las instituciones
    private Boolean esReplicado;
    
    /**
     * M�todo de acceso al campo objetosDTO.
     * 
     * @return El valor del campo objetosDTO
     */
    public List<ObjetoDTO> getObjetosDTO() {
        return objetosDTO;
    }

    /**
     * Asigna el valor al campo objetosDTO.
     * 
     * @param objetosDTO
     *            el valor objetosDTO a asignar
     */
    public void setObjetosDTO(List<ObjetoDTO> objetosDTO) {
        this.objetosDTO = objetosDTO;
    }
    /**
	 * 
	 */
    public ExpedienteDTO() {
        super();
    }

    /**
     * @param expedienteId
     */
    public ExpedienteDTO(Long expedienteId) {
        super();
        this.expedienteId = expedienteId;
    }

    public ExpedienteDTO(Long expedienteId, String noExp) {
        super();
        this.expedienteId = expedienteId;
        this.numeroExpediente = noExp;
    }
    /**
     * 
     * @param expedienteId
     * @param noExpId
     * @param noExp
     */
    public ExpedienteDTO(Long expedienteId, Long noExpId, String noExp) {
        super();
        this.expedienteId = expedienteId;
        this.numeroExpedienteId = noExpId;
        this.numeroExpediente = noExp;
    }

    /**
     * 
     * @param expedienteId
     * @param noExp
     * @param caso
     */
    public ExpedienteDTO(Long expedienteId, String noExp, CasoDTO caso) {
        super();
        this.expedienteId = expedienteId;
        this.numeroExpediente = noExp;
        this.casoDTO = caso;
    }

    /**
     * M�todo de acceso al campo expedienteId.
     * 
     * @return El valor del campo expedienteId
     */
    public Long getExpedienteId() {
        return expedienteId;
    }
    /**
     * Asigna el valor al campo expedienteId.
     * 
     * @param expedienteId
     *            el valor expedienteId a asignar
     */
    public void setExpedienteId(Long expedienteId) {
        this.expedienteId = expedienteId;
    }
    /**
     * M�todo de acceso al campo numeroExpediente.
     * 
     * @return El valor del campo numeroExpediente
     */
    public String getNumeroExpediente() {
        return numeroExpediente;
    }
    /**
     * Asigna el valor al campo numeroExpediente.
     * 
     * @param numeroExpediente
     *            el valor numeroExpediente a asignar
     */
    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }
    /**
     * M�todo de acceso al campo fechaApertura.
     * 
     * @return El valor del campo fechaApertura
     */
    public Date getFechaApertura() {

        if (fechaApertura == null) {
            setFechaApertura(DateUtils.obtenerNulleable(strFechaApertura,
                    strHoraApertura));
        }

        return fechaApertura;
    }
    /**
     * Asigna el valor al campo fechaApertura.
     * 
     * @param fechaApertura
     *            el valor fechaApertura a asignar
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * M�todo de acceso al campo fechaCierre.
     * 
     * @return El valor del campo fechaCierre
     */
    public Date getFechaCierre() {
        if (fechaCierre == null) {
            setFechaApertura(DateUtils.obtenerNulleable(strFechaCierre,
                    strHoraCierre));
        }
        return fechaCierre;
    }
    /**
     * Asigna el valor al campo fechaCierre.
     * 
     * @param fechaCierre
     *            el valor fechaCierre a asignar
     */
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    /**
     * M�todo de acceso al campo horaCierre.
     * 
     * @return El valor del campo horaCierre
     */

    /**
     * M�todo de acceso al campo casoDTO.
     * 
     * @return El valor del campo casoDTO
     */
    public CasoDTO getCasoDTO() {
        return casoDTO;
    }
    /**
     * Asigna el valor al campo casoDTO.
     * 
     * @param casoDTO
     *            el valor casoDTO a asignar
     */
    public void setCasoDTO(CasoDTO casoDTO) {
        this.casoDTO = casoDTO;
    }
    /**
     * M�todo de acceso al campo elementosDTO.
     * 
     * @return El valor del campo elementosDTO
     */
    public List<ElementoDTO> getElementosDTO() {
        return elementosDTO;
    }
    /**
     * Asigna el valor al campo elementosDTO.
     * 
     * @param elementosDTO
     *            el valor elementosDTO a asignar
     */
    public void setElementosDTO(List<ElementoDTO> elementosDTO) {
        this.elementosDTO = elementosDTO;
    }

    /**
     * M�todo de acceso al campo involucradosDTO.
     * 
     * @return El valor del campo involucradosDTO
     */
    public List<InvolucradoDTO> getInvolucradosDTO() {
        return involucradosDTO;
    }

    /**
     * Asigna el valor al campo involucradosDTO.
     * 
     * @param involucradosDTO
     *            el valor involucradosDTO a asignar
     */
    public void setInvolucradosDTO(List<InvolucradoDTO> involucradosDTO) {
        this.involucradosDTO = involucradosDTO;
    }

    /**
     * Obtiene los involucrado de acuerdo a una calidad especifica
     * 
     * @param calidad
     * @return
     */
    public List<InvolucradoDTO> getInvolucradoByCalidad(Calidades calidad) {
        final List<InvolucradoDTO> involucradosByCalidad = new ArrayList<InvolucradoDTO>();
        for (InvolucradoDTO involucradoDTO : involucradosDTO) {
            if (involucradoDTO.getCalidadDTO().getValorIdCalidad().getIdCampo()
                    .equals(calidad.getValorId())) {
                involucradosByCalidad.add(involucradoDTO);
            }
        }
        return involucradosByCalidad;
    }
    /**
     * Retorna una lista de objetos del tipo Indicado como par�metro
     * 
     * @param tipoObjeto
     *            Tipo de objeto deseado
     * @return Lista de objetos
     */
    public List<ObjetoDTO> getObjetosByTipo(Objetos tipoObjeto) {
        final List<ObjetoDTO> objetosByTipo = new ArrayList<ObjetoDTO>();
        for (ObjetoDTO obj : objetosDTO) {
            if (obj.getTipoObjeto().equals(tipoObjeto)) {
                objetosByTipo.add(obj);
            }
        }
        return objetosByTipo;
    }

    /**
     * Agrega un objeto a la lista.
     * 
     * @param obj
     */
    public void addObjeto(ObjetoDTO obj) {
        if (this.objetosDTO == null) {
            this.objetosDTO = new ArrayList<ObjetoDTO>();
        }
        this.objetosDTO.add(obj);
    }

    /**
     * Agrega un involucrado a las lista de involucrados del expediente
     * 
     * @param involucradoDTO
     */
    public void addInvolucradoDTO(InvolucradoDTO involucradoDTO) {
        this.involucradosDTO.add(involucradoDTO);
    }

    /**
     * M�todo de acceso al campo strFechaApertura.
     * 
     * @return El valor del campo strFechaApertura
     */
    public String getStrFechaApertura() {
        if (strFechaApertura != null) {
            return strFechaApertura;
        }
        return (fechaApertura != null
                ? DateUtils.formatear(fechaApertura)
                : null);
    }

    /**
     * Asigna el valor al campo strFechaApertura.
     * 
     * @param strFechaApertura
     *            el valor strFechaApertura a asignar
     */
    public void setStrFechaApertura(String strFechaApertura) {
        this.strFechaApertura = strFechaApertura;
    }

    /**
     * M�todo de acceso al campo strHoraApertura.
     * 
     * @return El valor del campo strHoraApertura
     */
    public String getStrHoraApertura() {
        if (strHoraApertura != null) {
            return strHoraApertura;
        }
        return (fechaApertura != null
                ? DateUtils.formatearHora(fechaApertura)
                : null);
    }

    /**
     * Asigna el valor al campo strHoraApertura.
     * 
     * @param strHoraApertura
     *            el valor strHoraApertura a asignar
     */
    public void setStrHoraApertura(String strHoraApertura) {
        this.strHoraApertura = strHoraApertura;
    }

    /**
     * M�todo de acceso al campo strFechaCierre.
     * 
     * @return El valor del campo strFechaCierre
     */
    public String getStrFechaCierre() {
        if (strFechaCierre != null) {
            return strFechaCierre;
        }
        return (fechaCierre != null ? DateUtils.formatear(fechaCierre) : null);
    }

    /**
     * Asigna el valor al campo strFechaCierre.
     * 
     * @param strFechaCierre
     *            el valor strFechaCierre a asignar
     */
    public void setStrFechaCierre(String strFechaCierre) {
        this.strFechaCierre = strFechaCierre;
    }

    /**
     * M�todo de acceso al campo strHoraCierre.
     * 
     * @return El valor del campo strHoraCierre
     */
    public String getStrHoraCierre() {
        if (strHoraCierre != null) {
            return strHoraCierre;
        }
        return (fechaCierre != null
                ? DateUtils.formatearHora(fechaCierre)
                : null);
    }

    /**
     * Asigna el valor al campo strHoraCierre.
     * 
     * @param strHoraCierre
     *            el valor strHoraCierre a asignar
     */
    public void setStrHoraCierre(String strHoraCierre) {
        this.strHoraCierre = strHoraCierre;
    }

    /**
     * M�todo de acceso al campo narrativa.
     * 
     * @return El valor del campo narrativa
     */
    public String getNarrativa() {
        return narrativa;
    }

    /**
     * Asigna el valor al campo narrativa.
     * 
     * @param narrativa
     *            el valor narrativa a asignar
     */
    public void setNarrativa(String narrativa) {
        this.narrativa = narrativa;
    }

    /**
     * M�todo de acceso al campo actividadActual.
     * 
     * @return El valor del campo actividadActual
     */
    public ActividadDTO getActividadActual() {
        return actividadActual;
    }

    /**
     * Asigna el valor al campo actividadActual.
     * 
     * @param actividadActual
     *            el valor actividadActual a asignar
     */
    public void setActividadActual(ActividadDTO actividadActual) {
        this.actividadActual = actividadActual;
    }

    /**
     * M�todo de acceso al campo delitos.
     * 
     * @return El valor del campo delitos
     */
    public List<DelitoDTO> getDelitos() {
        return delitos;
    }

    /**
     * Asigna el valor al campo delitos.
     * 
     * @param delitos
     *            el valor delitos a asignar
     */
    public void setDelitos(List<DelitoDTO> delitos) {
        this.delitos = delitos;
    }

    /**
     * M�todo de acceso al campo delitoPrincipal.
     * 
     * @return El valor del campo delitoPrincipal
     */
    public DelitoDTO getDelitoPrincipal() {
        return delitoPrincipal;
    }

    /**
     * Asigna el valor al campo delitoPrincipal.
     * 
     * @param delitoPrincipal
     *            el valor delitoPrincipal a asignar
     */
    public void setDelitoPrincipal(DelitoDTO delitoPrincipal) {
        this.delitoPrincipal = delitoPrincipal;
    }

    /**
     * M�todo de acceso al campo area.
     * 
     * @return El valor del campo area
     */
    public AreaDTO getArea() {
        return area;
    }

    /**
     * Asigna el valor al campo area.
     * 
     * @param area
     *            el valor area a asignar
     */
    public void setArea(AreaDTO area) {
        this.area = area;
    }

    /**
     * M�todo de acceso al campo consulta.
     * 
     * @return El valor del campo consulta
     */
    public boolean isConsulta() {
        return consulta;
    }

    /**
     * Asigna el valor al campo consulta.
     * 
     * @param consulta
     *            el valor consulta a asignar
     */
    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    /**
     * M�todo de acceso al campo estatus.
     * 
     * @return El valor del campo estatus
     */
    public ValorDTO getEstatus() {
        return estatus;
    }

    /**
     * Asigna el valor al campo estatus.
     * 
     * @param estatus
     *            el valor estatus a asignar
     */
    public void setEstatus(ValorDTO estatus) {
        this.estatus = estatus;
    }

    /**
     * M�todo de acceso al campo numeroExpedienteId.
     * 
     * @return El valor del campo numeroExpedienteId
     */
    public Long getNumeroExpedienteId() {
        return numeroExpedienteId;
    }

    /**
     * Asigna el valor al campo numeroExpedienteId.
     * 
     * @param numeroExpedienteId
     *            el valor numeroExpedienteId a asignar
     */
    public void setNumeroExpedienteId(Long numeroExpedienteId) {
        this.numeroExpedienteId = numeroExpedienteId;
    }

    /**
     * M�todo de acceso al campo hechoDTO.
     * 
     * @return El valor del campo hechoDTO
     */
    public HechoDTO getHechoDTO() {
        return hechoDTO;
    }

    /**
     * Asigna el valor al campo hechoDTO.
     * 
     * @param hechoDTO
     *            el valor hechoDTO a asignar
     */
    public void setHechoDTO(HechoDTO hechoDTO) {
        this.hechoDTO = hechoDTO;
    }

    /**
     * Asigna el valor al campo documentosDTO.
     * 
     * @param documentosDTO
     *            el valor documentosDTO a asignar
     */
    public void setDocumentosDTO(List<DocumentoDTO> documentosDTO) {
        this.documentosDTO = documentosDTO;
    }

    /**
     * M�todo de acceso al campo documentosDTO.
     * 
     * @return El valor del campo documentosDTO
     */
    public List<DocumentoDTO> getDocumentosDTO() {
        return documentosDTO;
    }

    /**
     * M�todo de acceso al campo etapa.
     * 
     * @return El valor del campo etapa
     */
    public ValorDTO getEtapa() {
        return etapa;
    }

    /**
     * Asigna el valor al campo etapa.
     * 
     * @param etapa
     *            el valor etapa a asignar
     */
    public void setEtapa(ValorDTO etapa) {
        this.etapa = etapa;
    }

    /**
     * M�todo de acceso al campo causaPadre.
     * 
     * @return El valor del campo causaPadre
     */
    public ExpedienteDTO getCausaPadre() {
        return causaPadre;
    }

    /**
     * Asigna el valor al campo causaPadre.
     * 
     * @param causaPadre
     *            el valor causaPadre a asignar
     */
    public void setCausaPadre(ExpedienteDTO causaPadre) {
        this.causaPadre = causaPadre;
    }

    /**
     * M�todo de acceso al campo origen.
     * 
     * @return El valor del campo origen
     */
    public ValorDTO getOrigen() {
        return origen;
    }

    /**
     * Asigna el valor al campo origen.
     * 
     * @param origen
     *            el valor origen a asignar
     */
    public void setOrigen(ValorDTO origen) {
        this.origen = origen;
    }

    /**
     * M�todo de acceso al campo tipoExpediente.
     * 
     * @return El valor del campo tipoExpediente
     */
    public ValorDTO getTipoExpediente() {
        return tipoExpediente;
    }

    /**
     * Asigna el valor al campo tipoExpediente.
     * 
     * @param tipoExpediente
     *            el valor tipoExpediente a asignar
     */
    public void setTipoExpediente(ValorDTO tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    /**
     * M�todo de acceso al campo numExpHijos.
     * 
     * @return El valor del campo numExpHijos
     */
    public List<ExpedienteDTO> getNumExpHijos() {
        return numExpHijos;
    }

    /**
     * Asigna el valor al campo numExpHijos.
     * 
     * @param numExpHijos
     *            el valor numExpHijos a asignar
     */
    public void setNumExpHijos(List<ExpedienteDTO> numExpHijos) {
        this.numExpHijos = numExpHijos;
    }

    /**
     * M�todo de acceso al campo medidasCautealresSolicitadas.
     * 
     * @return El valor del campo medidasCautealresSolicitadas
     */
    public boolean isMedidasCautelaresSolicitadas() {
        return medidasCautelaresSolicitadas;
    }

    /**
     * Asigna el valor al campo medidasCautealresSolicitadas.
     * 
     * @param medidasCautealresSolicitadas
     *            el valor medidasCautealresSolicitadas a asignar
     */
    public void setMedidasCautelaresSolicitadas(
            boolean medidasCautealresSolicitadas) {
        this.medidasCautelaresSolicitadas = medidasCautealresSolicitadas;
    }

    /**
     * M�todo de acceso al campo pertenceConfInst.
     * 
     * @return El valor del campo pertenceConfInst
     */
    public ConfInstitucionDTO getPertenceConfInst() {
        return pertenceConfInst;
    }

    /**
     * Asigna el valor al campo pertenceConfInst.
     * 
     * @param pertenceConfInst
     *            el valor pertenceConfInst a asignar
     */
    public void setPertenceConfInst(ConfInstitucionDTO pertenceConfInst) {
        this.pertenceConfInst = pertenceConfInst;
    }

    /**
     * M�todo de acceso al campo almacenDTO.
     * 
     * @return El valor del campo almacenDTO
     */
    public AlmacenDTO getAlmacenDTO() {
        return almacenDTO;
    }

    /**
     * Regresa el valor de la propiedad avisosDesignacion
     * 
     * @return the avisosDesignacion
     */
    public List<AvisoDesignacionDTO> getAvisosDesignacion() {
        return avisosDesignacion;
    }

    /**
     * Establece el valor de la propiedad avisosDesignacion
     * 
     * @param avisosDesignacion
     *            valo avisoDesignacion a almacenar
     */
    public void setAvisosDesignacion(List<AvisoDesignacionDTO> avisosDesignacion) {
        this.avisosDesignacion = avisosDesignacion;
    }

    /**
     * Asigna el valor al campo almacenDTO.
     * 
     * @param almacenDTO
     *            el valor almacenDTO a asignar
     */
    public void setAlmacenDTO(AlmacenDTO almacenDTO) {
        this.almacenDTO = almacenDTO;
    }

    /**
     * Regresa el valor de la propiedad audiencias
     * 
     * @return the audiencias
     */
    public List<AudienciaDTO> getAudiencias() {
        return audiencias;
    }

    /**
     * Establece el valor de la propiedad audiencias
     * 
     * @param audiencias
     *            valo audiencias a almacenar
     */
    public void setAudiencias(List<AudienciaDTO> audiencias) {
        this.audiencias = audiencias;
    }

    /**
     * M�todo de acceso al campo inputado.
     * 
     * @return El valor del campo inputado
     */
    public InvolucradoDTO getInputado() {
        return inputado;
    }

    /**
     * Asigna el valor al campo inputado.
     * 
     * @param inputado
     *            el valor inputado a asignar
     */
    public void setInputado(InvolucradoDTO inputado) {
        this.inputado = inputado;
    }

    /**
     * Establece el valor de la propiedad solicitante
     * 
     * @param solicitante
     *            valo solicitante a almacenar
     */
    public void setSolicitante(InvolucradoDTO solicitante) {
        this.solicitante = solicitante;
    }

    /**
     * Regresa el valor de la propiedad solicitante
     * 
     * @return the solicitante
     */
    public InvolucradoDTO getSolicitante() {
        return solicitante;
    }

    /**
     * M�todo de acceso al campo involucradosSolicitados.
     * 
     * @return El valor del campo involucradosSolicitados
     */
    public boolean isInvolucradosSolicitados() {
        return involucradosSolicitados;
    }

    /**
     * Asigna el valor al campo involucradosSolicitados.
     * 
     * @param involucradosSolicitados
     *            el valor involucradosSolicitados a asignar
     */
    public void setInvolucradosSolicitados(boolean involucradosSolicitados) {
        this.involucradosSolicitados = involucradosSolicitados;
    }

    /**
     * M�todo de acceso al campo domicliosInvolucradoSolicitados.
     * 
     * @return El valor del campo domicliosInvolucradoSolicitados
     */
    public boolean isDomicliosInvolucradoSolicitados() {
        return domicliosInvolucradoSolicitados;
    }

    /**
     * Asigna el valor al campo domicliosInvolucradoSolicitados.
     * 
     * @param domicliosInvolucradoSolicitados
     *            el valor domicliosInvolucradoSolicitados a asignar
     */
    public void setDomicliosInvolucradoSolicitados(
            boolean domicliosInvolucradoSolicitados) {
        this.domicliosInvolucradoSolicitados = domicliosInvolucradoSolicitados;
    }

    /**
     * M�todo de acceso al campo objetosSolicitados.
     * 
     * @return El valor del campo objetosSolicitados
     */
    public boolean isObjetosSolicitados() {
        return objetosSolicitados;
    }

    /**
     * Asigna el valor al campo objetosSolicitados.
     * 
     * @param objetosSolicitados
     *            el valor objetosSolicitados a asignar
     */
    public void setObjetosSolicitados(boolean objetosSolicitados) {
        this.objetosSolicitados = objetosSolicitados;
    }

    /**
     * M�todo de acceso al campo cadenasCustodiaSolicitadas.
     * 
     * @return El valor del campo cadenasCustodiaSolicitadas
     */
    public boolean isCadenasCustodiaSolicitadas() {
        return cadenasCustodiaSolicitadas;
    }

    /**
     * Asigna el valor al campo cadenasCustodiaSolicitadas.
     * 
     * @param cadenasCustodiaSolicitadas
     *            el valor cadenasCustodiaSolicitadas a asignar
     */
    public void setCadenasCustodiaSolicitadas(boolean cadenasCustodiaSolicitadas) {
        this.cadenasCustodiaSolicitadas = cadenasCustodiaSolicitadas;
    }

    /**
     * M�todo de acceso al campo hechoSolicitado.
     * 
     * @return El valor del campo hechoSolicitado
     */
    public boolean isHechoSolicitado() {
        return hechoSolicitado;
    }

    /**
     * Asigna el valor al campo hechoSolicitado.
     * 
     * @param hechoSolicitado
     *            el valor hechoSolicitado a asignar
     */
    public void setHechoSolicitado(boolean hechoSolicitado) {
        this.hechoSolicitado = hechoSolicitado;
    }

    /**
     * M�todo de acceso al campo documentosSolicitados.
     * 
     * @return El valor del campo documentosSolicitados
     */
    public boolean isDocumentosSolicitados() {
        return documentosSolicitados;
    }

    /**
     * Asigna el valor al campo documentosSolicitados.
     * 
     * @param documentosSolicitados
     *            el valor documentosSolicitados a asignar
     */
    public void setDocumentosSolicitados(boolean documentosSolicitados) {
        this.documentosSolicitados = documentosSolicitados;
    }

    /**
     * M�todo de acceso al campo avisosDesignacionSolicitados.
     * 
     * @return El valor del campo avisosDesignacionSolicitados
     */
    public boolean isAvisosDesignacionSolicitados() {
        return avisosDesignacionSolicitados;
    }

    /**
     * Asigna el valor al campo avisosDesignacionSolicitados.
     * 
     * @param avisosDesignacionSolicitados
     *            el valor avisosDesignacionSolicitados a asignar
     */
    public void setAvisosDesignacionSolicitados(
            boolean avisosDesignacionSolicitados) {
        this.avisosDesignacionSolicitados = avisosDesignacionSolicitados;
    }

    /**
     * M�todo de acceso al campo fechaAperturaNumeroExp.
     * 
     * @return El valor del campo fechaAperturaNumeroExp
     */
    public Date getFechaAperturaNumeroExp() {
        return fechaAperturaNumeroExp;
    }

    /**
     * Asigna el valor al campo fechaAperturaNumeroExp.
     * 
     * @param fechaAperturaNumeroExp
     *            el valor fechaAperturaNumeroExp a asignar
     */
    public void setFechaAperturaNumeroExp(Date fechaAperturaNumeroExp) {
        this.fechaAperturaNumeroExp = fechaAperturaNumeroExp;
    }

    /**
     * @return the esEscritura
     */
    public boolean isEsEscritura() {
        return esEscritura;
    }

    /**
     * @param esEscritura
     *            the esEscritura to set
     */
    public void setEsEscritura(boolean esEscritura) {
        this.esEscritura = esEscritura;
    }

    /**
     * @return the esPropietario
     */
    public boolean isEsPropietario() {
        return esPropietario;
    }

    /**
     * @param esPropietario
     *            the esPropietario to set
     */
    public void setEsPropietario(boolean esPropietario) {
        this.esPropietario = esPropietario;
    }

    /**
     * @return the fechaLimitePermiso
     */
    public Date getFechaLimitePermiso() {
        return fechaLimitePermiso;
    }

    /**
     * @param fechaLimitePermiso
     *            the fechaLimitePermiso to set
     */
    public void setFechaLimitePermiso(Date fechaLimitePermiso) {
        this.fechaLimitePermiso = fechaLimitePermiso;
    }

    /**
     * M�todo de acceso al campo etapasPasadas.
     * 
     * @return El valor del campo etapasPasadas
     */
    public List<EtapasExpediente> getEtapasPasadas() {
        return etapasPasadas;
    }

    /**
     * Asigna el valor al campo etapasPasadas.
     * 
     * @param etapasPasadas
     *            el valor etapasPasadas a asignar
     */
    public void setEtapasPasadas(List<EtapasExpediente> etapasPasadas) {
        this.etapasPasadas = etapasPasadas;
    }

	public ValorDTO getEstatusExpedientePadre() {
		return estatusExpedientePadre;
	}

	public void setEstatusExpedientePadre(ValorDTO estatusExpedientePadre) {
		this.estatusExpedientePadre = estatusExpedientePadre;
	}

	/**
	 * @return the discriminante
	 */
	public CatDiscriminanteDTO getDiscriminante() {
		return discriminante;
	}

	/**
	 * @param discriminante the discriminante to set
	 */
	public void setDiscriminante(CatDiscriminanteDTO discriminante) {
		this.discriminante = discriminante;
	}

	/**
	 * @return the esReplicado
	 */
	public Boolean getEsReplicado() {
		return esReplicado;
	}

	/**
	 * @param esReplicado the esReplicado to set
	 */
	public void setEsReplicado(Boolean esReplicado) {
		this.esReplicado = esReplicado;
	}
	
	//Implementado para ser utilizado en Servicio de consultarExpedienteActividadAreaAnio en BuscarExpedienteServiceImpl
    public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof ExpedienteDTO))
			return false;
		ExpedienteDTO expedienteTemp = (ExpedienteDTO) obj;

		return ((this.getExpedienteId()  == expedienteTemp
				.getExpedienteId()));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getExpedienteId() == null ? 0 : this
						.getExpedienteId().hashCode());
		return result;
	}
	
}
