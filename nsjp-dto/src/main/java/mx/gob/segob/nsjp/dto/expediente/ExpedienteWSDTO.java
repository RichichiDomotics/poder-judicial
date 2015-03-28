/**
 * Nombre del Programa : ExpedienteWSDTO.java
 * Autor                            : GustavoBP                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 22/07/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Expediente.  
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

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.caso.CasoWSDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoWSDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionWSDTO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveWSDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaWSDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialWSDTO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionWSDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoWSDTO;
import mx.gob.segob.nsjp.dto.objeto.InmuebleWSDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaWSDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ObraArteWSDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaWSDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaWSDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalWSDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoWSDTO;


/**
 * DTO de intercambio entre sistemas para transportar los datos b?sicos de un Expediente.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class ExpedienteWSDTO extends GenericWSDTO {

	private static final long serialVersionUID = 7996969483907407947L;
	/**
     * Número que se muestra al usuario y que sirve solo de referencia. 
     */
    private String numeroExpediente;
    /**
     * Id del numero de expediente, usado para solicitudes y audiencias
     * Para ser recuperado de la BD de Procuraduria
     */
    private Long numeroExpedienteId;
    
    private Date fechaApertura;
    private Date fechaCierre;
    private String narrativa;
    private List<InvolucradoWSDTO> involucradosDTO = new ArrayList<InvolucradoWSDTO>();
    
    private List<ObjetoWSDTO> objetosDTO = new ArrayList<ObjetoWSDTO>();
    //Las siguientes lineas son requeridas para generar los WSDTO de los Objetos
    private AeronaveWSDTO aeronaveWSDTO;
    private AnimalWSDTO animalWSDTO;
    private ArmaWSDTO armaWSDTO;
    private DocumentoOficialWSDTO documentoOficialWSDTO;
    private EmbarcacionWSDTO embarcacionWSDTO;
    private EquipoComputoWSDTO equipoComputoWSDTO;
    private ExplosivoWSDTO explosivoWSDTO;
    private InmuebleWSDTO inmuebleWSDTO;
    private JoyaWSDTO joyaWSDTO;
    private NumerarioWSDTO numerarioWSDTO;
    private ObraArteWSDTO obraArteWSDTO;
    private SustanciaWSDTO sustanciaWSDTO;
    private TelefoniaWSDTO telefoniaWSDTO;
    private VegetalWSDTO vegetalWSDTO;
    private VehiculoWSDTO vehiculoWSDTO;
    private CasoWSDTO casoWSDTO;
    
    
    private String strFechaApertura;
    private String strHoraApertura;
    
    private String strFechaCierre;
    private String strHoraCierre;
    
    private List<DelitoWSDTO> delitos = new ArrayList<DelitoWSDTO>();
    private HechoWSDTO hechoDTO;
    private DelitoWSDTO delitoPrincipal;
    private List<DocumentoWSDTO> documentosDTO = new ArrayList<DocumentoWSDTO>();
    
    private Long pertenceConfInst;
    
    /**
	 * 
	 */
    public ExpedienteWSDTO() {
        super();
    }

    /**
     * 
     * @param noExp
     */
    public ExpedienteWSDTO( String noExp) {
        super();
        this.numeroExpediente = noExp;
    }

    /**
     * Método de acceso al campo numeroExpediente.
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
     * Método de acceso al campo fechaApertura.
     * 
     * @return El valor del campo fechaApertura
     */
    public Date getFechaApertura() {
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
     * Método de acceso al campo fechaCierre.
     * 
     * @return El valor del campo fechaCierre
     */
    public Date getFechaCierre() {
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
     * Método de acceso al campo horaCierre.
     * 
     * @return El valor del campo horaCierre
     */

    /**
     * Método de acceso al campo involucradosDTO.
     * 
     * @return El valor del campo involucradosDTO
     */
    public List<InvolucradoWSDTO> getInvolucradosDTO() {
        return involucradosDTO;
    }

    /**
     * Asigna el valor al campo involucradosDTO.
     * 
     * @param involucradosDTO
     *            el valor involucradosDTO a asignar
     */
    public void setInvolucradosDTO(List<InvolucradoWSDTO> involucradosDTO) {
        this.involucradosDTO = involucradosDTO;
    }

    /**
     * Agrega un involucrado a las lista de involucrados del expediente
     * 
     * @param involucradoWSDTO
     */
    public void addInvolucradoWSDTO(InvolucradoWSDTO involucradoWSDTO) {
        this.involucradosDTO.add(involucradoWSDTO);
    }

    /**
     * Método de acceso al campo strFechaApertura.
     * 
     * @return El valor del campo strFechaApertura
     */
    public String getStrFechaApertura() {
    	return strFechaApertura;
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
     * Método de acceso al campo strHoraApertura.
     * 
     * @return El valor del campo strHoraApertura
     */
    public String getStrHoraApertura() {
    	return strHoraApertura;
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
     * Método de acceso al campo strFechaCierre.
     * 
     * @return El valor del campo strFechaCierre
     */
    public String getStrFechaCierre() {
    	return strFechaCierre;
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
     * Método de acceso al campo strHoraCierre.
     * 
     * @return El valor del campo strHoraCierre
     */
    public String getStrHoraCierre() {
    	return strHoraCierre;
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
     * Método de acceso al campo narrativa.
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
     * Método de acceso al campo delitos.
     * 
     * @return El valor del campo delitos
     */
    public List<DelitoWSDTO> getDelitos() {
        return delitos;
    }

    /**
     * Asigna el valor al campo delitos.
     * 
     * @param delitos
     *            el valor delitos a asignar
     */
    public void setDelitos(List<DelitoWSDTO> delitos) {
        this.delitos = delitos;
    }

    /**
     * Método de acceso al campo delitoPrincipal.
     * 
     * @return El valor del campo delitoPrincipal
     */
    public DelitoWSDTO getDelitoPrincipal() {
        return delitoPrincipal;
    }

    /**
	 * Método de acceso al campo hechoDTO.
	 * @return El valor del campo hechoDTO
	 */
	public HechoWSDTO getHechoDTO() {
		return hechoDTO;
	}

	/**
	 * Asigna el valor al campo hechoDTO.
	 * @param hechoDTO el valor hechoDTO a asignar
	 */
	public void setHechoDTO(HechoWSDTO hechoDTO) {
		this.hechoDTO = hechoDTO;
	}

	/**
     * Asigna el valor al campo delitoPrincipal.
     * 
     * @param delitoPrincipal
     *            el valor delitoPrincipal a asignar
     */
    public void setDelitoPrincipal(DelitoWSDTO delitoPrincipal) {
        this.delitoPrincipal = delitoPrincipal;
    }

    /**
     * Método de acceso al campo numeroExpedienteId.
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
     * Asigna el valor al campo documentosDTO.
     * 
     * @param documentosDTO
     *            el valor documentosDTO a asignar
     */
    public void setDocumentosDTO(List<DocumentoWSDTO> documentosDTO) {
        this.documentosDTO = documentosDTO;
    }

    /**
     * Método de acceso al campo documentosDTO.
     * 
     * @return El valor del campo documentosDTO
     */
    public List<DocumentoWSDTO> getDocumentosDTO() {
        return documentosDTO;
    }

	/**
	 * Método de acceso al campo objetosDTO.
	 * @return El valor del campo objetosDTO
	 */
	public List<ObjetoWSDTO> getObjetosDTO() {
		return objetosDTO;
	}

	/**
	 * Asigna el valor al campo objetosDTO.
	 * @param objetosDTO el valor objetosDTO a asignar
	 */
	public void setObjetosDTO(List<ObjetoWSDTO> objetosDTO) {
		this.objetosDTO = objetosDTO;
	}

	/**
	 * Método de acceso al campo pertenceConfInst.
	 * @return El valor del campo pertenceConfInst
	 */
	public Long getPertenceConfInst() {
		return pertenceConfInst;
	}

	/**
	 * Asigna el valor al campo pertenceConfInst.
	 * @param pertenceConfInst el valor pertenceConfInst a asignar
	 */
	public void setPertenceConfInst(Long pertenceConfInst) {
		this.pertenceConfInst = pertenceConfInst;
	}

	
	
	public AeronaveWSDTO getAeronaveWSDTO() {
		return aeronaveWSDTO;
	}

	public void setAeronaveWSDTO(AeronaveWSDTO aeronaveWSDTO) {
		this.aeronaveWSDTO = aeronaveWSDTO;
	}

	public AnimalWSDTO getAnimalWSDTO() {
		return animalWSDTO;
	}

	public void setAnimalWSDTO(AnimalWSDTO animalWSDTO) {
		this.animalWSDTO = animalWSDTO;
	}

	public ArmaWSDTO getArmaWSDTO() {
		return armaWSDTO;
	}

	public void setArmaWSDTO(ArmaWSDTO armaWSDTO) {
		this.armaWSDTO = armaWSDTO;
	}

	public DocumentoOficialWSDTO getDocumentoOficialWSDTO() {
		return documentoOficialWSDTO;
	}

	public void setDocumentoOficialWSDTO(DocumentoOficialWSDTO documentoOficialWSDTO) {
		this.documentoOficialWSDTO = documentoOficialWSDTO;
	}

	public EmbarcacionWSDTO getEmbarcacionWSDTO() {
		return embarcacionWSDTO;
	}

	public void setEmbarcacionWSDTO(EmbarcacionWSDTO embarcacionWSDTO) {
		this.embarcacionWSDTO = embarcacionWSDTO;
	}

	public EquipoComputoWSDTO getEquipoComputoWSDTO() {
		return equipoComputoWSDTO;
	}

	public void setEquipoComputoWSDTO(EquipoComputoWSDTO equipoComputoWSDTO) {
		this.equipoComputoWSDTO = equipoComputoWSDTO;
	}

	public ExplosivoWSDTO getExplosivoWSDTO() {
		return explosivoWSDTO;
	}

	public void setExplosivoWSDTO(ExplosivoWSDTO explosivoWSDTO) {
		this.explosivoWSDTO = explosivoWSDTO;
	}

	public InmuebleWSDTO getInmuebleWSDTO() {
		return inmuebleWSDTO;
	}

	public void setInmuebleWSDTO(InmuebleWSDTO inmuebleWSDTO) {
		this.inmuebleWSDTO = inmuebleWSDTO;
	}

	public JoyaWSDTO getJoyaWSDTO() {
		return joyaWSDTO;
	}

	public void setJoyaWSDTO(JoyaWSDTO joyaWSDTO) {
		this.joyaWSDTO = joyaWSDTO;
	}

	public NumerarioWSDTO getNumerarioWSDTO() {
		return numerarioWSDTO;
	}

	public void setNumerarioWSDTO(NumerarioWSDTO numerarioWSDTO) {
		this.numerarioWSDTO = numerarioWSDTO;
	}

	public ObraArteWSDTO getObraArteWSDTO() {
		return obraArteWSDTO;
	}

	public void setObraArteWSDTO(ObraArteWSDTO obraArteWSDTO) {
		this.obraArteWSDTO = obraArteWSDTO;
	}

	public SustanciaWSDTO getSustanciaWSDTO() {
		return sustanciaWSDTO;
	}

	public void setSustanciaWSDTO(SustanciaWSDTO sustanciaWSDTO) {
		this.sustanciaWSDTO = sustanciaWSDTO;
	}

	public TelefoniaWSDTO getTelefoniaWSDTO() {
		return telefoniaWSDTO;
	}

	public void setTelefoniaWSDTO(TelefoniaWSDTO telefoniaWSDTO) {
		this.telefoniaWSDTO = telefoniaWSDTO;
	}

	public VegetalWSDTO getVegetalWSDTO() {
		return vegetalWSDTO;
	}

	public void setVegetalWSDTO(VegetalWSDTO vegetalWSDTO) {
		this.vegetalWSDTO = vegetalWSDTO;
	}

	public VehiculoWSDTO getVehiculoWSDTO() {
		return vehiculoWSDTO;
	}

	public void setVehiculoWSDTO(VehiculoWSDTO vehiculoWSDTO) {
		this.vehiculoWSDTO = vehiculoWSDTO;
	}

	/**
	 * Método de acceso al campo casoWSDTO.
	 * @return El valor del campo casoWSDTO
	 */
	public CasoWSDTO getCasoWSDTO() {
		return casoWSDTO;
	}

	/**
	 * Asigna el valor al campo casoWSDTO.
	 * @param casoWSDTO el valor casoWSDTO a asignar
	 */
	public void setCasoWSDTO(CasoWSDTO casoWSDTO) {
		this.casoWSDTO = casoWSDTO;
	}
}
