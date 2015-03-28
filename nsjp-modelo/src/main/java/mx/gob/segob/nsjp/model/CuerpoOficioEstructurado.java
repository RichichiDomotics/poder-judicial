package mx.gob.segob.nsjp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Intento entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CuerpoOficioEstructurado")
public class CuerpoOficioEstructurado implements java.io.Serializable {

    // Fields
    private Long cuerpoOficioEstructuradoId;
    private String texto;
    private Integer secuencia;
    private Integer numeracion;    
    private OficioEstructurado oficioEstructurado;
    private IndiceEstructurado indiceEstructurado;
    private Boolean interesa;

    // Constructors
    
    /** default constructor */
    public CuerpoOficioEstructurado() {
    }

    
	public CuerpoOficioEstructurado(Long cuerpoOficioEstructuradoId,
			String texto, Integer secuencia, Integer numeracion,
			OficioEstructurado oficioEstructurado,
			IndiceEstructurado indiceEstructurado) {
		super();
		this.cuerpoOficioEstructuradoId = cuerpoOficioEstructuradoId;
		this.texto = texto;
		this.secuencia = secuencia;
		this.numeracion = numeracion;
		this.oficioEstructurado = oficioEstructurado;
		this.indiceEstructurado = indiceEstructurado;
	}


	/**
	 * Método de acceso al campo cuerpoOficioEstructuradoId.
	 * @return El valor del campo cuerpoOficioEstructuradoId
	 */
 // Property accessors
    @Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CuerpoOficioEstructurado_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCuerpoOficioEstructuradoId() {
		return cuerpoOficioEstructuradoId;
	}

	/**
	 * Asigna el valor al campo cuerpoOficioEstructuradoId.
	 * @param cuerpoOficioEstructuradoId el valor cuerpoOficioEstructuradoId a asignar
	 */
	public void setCuerpoOficioEstructuradoId(Long cuerpoOficioEstructuradoId) {
		this.cuerpoOficioEstructuradoId = cuerpoOficioEstructuradoId;
	}
	
	/**
	 * Método de acceso al campo texto.
	 * @return El valor del campo texto
	 */
	@Column(name = "cTexto")
	public String getTexto() {
		return texto;
	}


	/**
	 * Asigna el valor al campo texto.
	 * @param texto el valor texto a asignar
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Método de acceso al campo numeracion.
	 * @return El valor del campo numeracion
	 */
	@Column(name = "iNumeracion", nullable = false, precision = 4, scale = 0)
	public Integer getNumeracion() {
		return numeracion;
	}

	/**
	 * Asigna el valor al campo numeracion.
	 * @param numeracion el valor numeracion a asignar
	 */
	public void setNumeracion(Integer numeracion) {
		this.numeracion = numeracion;
	}

	/**
	 * Método de acceso al campo secuencia.
	 * @return El valor del campo secuencia
	 */
	@Column(name = "iSecuencia", nullable = false, precision = 4, scale = 0)
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * Asigna el valor al campo secuencia.
	 * @param secuencia el valor secuencia a asignar
	 */
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}


	/**
	 * Método de acceso al campo oficioEstructurado.
	 * @return El valor del campo oficioEstructurado
	 */
	
    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
    @JoinColumn(name = "OficioEstructurado_id")
	public OficioEstructurado getOficioEstructurado() {
		return oficioEstructurado;
	}

	/**
	 * Asigna el valor al campo oficioEstructurado.
	 * @param oficioEstructurado el valor oficioEstructurado a asignar
	 */
	public void setOficioEstructurado(OficioEstructurado oficioEstructurado) {
		this.oficioEstructurado = oficioEstructurado;
	}

	/**
	 * Método de acceso al campo indiceEstructurado.
	 * @return El valor del campo indiceEstructurado
	 */
    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
    @JoinColumn(name = "IndiceEstructurado_id")
	public IndiceEstructurado getIndiceEstructurado() {
		return indiceEstructurado;
	}

	/**
	 * Asigna el valor al campo indiceEstructurado.
	 * @param indiceEstruturado el valor indiceEstruturado a asignar
	 */
	public void setIndiceEstructurado(IndiceEstructurado indiceEstructurado) {
		this.indiceEstructurado = indiceEstructurado;
	}


	/**
	 * @param interesa the interesa to set
	 */
	public void setInteresa(Boolean interesa) {
		this.interesa = interesa;
	}


	/**
	 * @return the interesa
	 */
	@Transient
	public Boolean getInteresa() {
		return interesa;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result= false;
		//Verifico que sea del mismo tipo
	    if (obj instanceof CuerpoOficioEstructurado) return false;

	    //Valido que el objeto no sea null
	    if (obj==null) return false;
	    
	    //Valido que el id no sea null
	    if(((CuerpoOficioEstructurado)obj).cuerpoOficioEstructuradoId==null) return false;

	    //Verifico si el objeto actual es igual al que recibo por parámetro
	    if (this.cuerpoOficioEstructuradoId.equals(((CuerpoOficioEstructurado)obj).cuerpoOficioEstructuradoId)) result= true;
		
		return result;
		
	}
    
}
