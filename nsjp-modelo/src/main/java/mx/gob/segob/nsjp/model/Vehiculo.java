package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Vehiculo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Vehiculo" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Vehiculo_id")
public class Vehiculo extends Objeto {

	// Fields

	private Valor valorByPaisOrigenVal;
	private Valor valorByColorVal;
	private Valor valorByMarcaVal;
	private Valor valorBySubmarcaVal;
	private Valor valorByTipoVehiculo;
	private Short modelo;
	private String placa;
	private String noSerie;
	private String noMotor;
	private String nrfv;
	private Boolean esBlindado;
	private Short noPuertas;
	private Short noCilindros;
	private String propietario;
	private Valor relacionHechoVal;



	// Constructors

	/** default constructor */
	public Vehiculo() {
	}



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PaisOrigen_val")
	public Valor getValorByPaisOrigenVal() {
		return this.valorByPaisOrigenVal;
	}

	public void setValorByPaisOrigenVal(Valor valorByPaisOrigenVal) {
		this.valorByPaisOrigenVal = valorByPaisOrigenVal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Color_val")
	public Valor getValorByColorVal() {
		return this.valorByColorVal;
	}

	public void setValorByColorVal(Valor valorByColorVal) {
		this.valorByColorVal = valorByColorVal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Marca_val")
	public Valor getValorByMarcaVal() {
		return this.valorByMarcaVal;
	}

	public void setValorByMarcaVal(Valor valorByMarcaVal) {
		this.valorByMarcaVal = valorByMarcaVal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Submarca_val")
	public Valor getValorBySubmarcaVal() {
		return this.valorBySubmarcaVal;
	}

	public void setValorBySubmarcaVal(Valor valorBySubmarcaVal) {
		this.valorBySubmarcaVal = valorBySubmarcaVal;
	}


	@Column(name = "iModelo", precision = 4, scale = 0)
	public Short getModelo() {
		return this.modelo;
	}

	public void setModelo(Short imodelo) {
		this.modelo = imodelo;
	}

	@Column(name = "cPlaca", length = 10)
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String cplaca) {
		this.placa = cplaca;
	}

	@Column(name = "cNserie", length = 20)
	public String getNoSerie() {
		return this.noSerie;
	}

	public void setNoSerie(String cnserie) {
		this.noSerie = cnserie;
	}

	@Column(name = "cNmotor", length = 20)
	public String getNoMotor() {
		return this.noMotor;
	}

	public void setNoMotor(String cnmotor) {
		this.noMotor = cnmotor;
	}

	@Column(name = "cNRFV", length = 20)
	public String getNrfv() {
		return this.nrfv;
	}

	public void setNrfv(String cnrfv) {
		this.nrfv = cnrfv;
	}

	@Column(name = "bEsBlindado", precision = 1, scale = 0)
	public Boolean getEsBlindado() {
		return this.esBlindado;
	}

	public void setEsBlindado(Boolean besBlindado) {
		this.esBlindado = besBlindado;
	}

	@Column(name = "iNpuertas", precision = 4, scale = 0)
	public Short getNoPuertas() {
		return this.noPuertas;
	}

	public void setNoPuertas(Short inpuertas) {
		this.noPuertas = inpuertas;
	}

	@Column(name = "iNcilindros", precision = 4, scale = 0)
	public Short getNoCilindros() {
		return this.noCilindros;
	}

	public void setNoCilindros(Short incilindros) {
		this.noCilindros = incilindros;
	}



    /**
     * Método de acceso al campo valorByTipoVehiculo.
     * @return El valor del campo valorByTipoVehiculo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoVehiculo_val")	
    public Valor getValorByTipoVehiculo() {
        return valorByTipoVehiculo;
    }



    /**
     * Asigna el valor al campo valorByTipoVehiculo.
     * @param valorByTipoVehiculo el valor valorByTipoVehiculo a asignar
     */
    public void setValorByTipoVehiculo(Valor valorByTipoVehiculo) {
        this.valorByTipoVehiculo = valorByTipoVehiculo;
    }



	/**
	 * Método de acceso al campo propietario.
	 * @return El valor del campo propietario
	 */
	@Column(name = "cPropietario", length = 100)
	public String getPropietario() {
		return propietario;
	}



	/**
	 * Asigna el valor al campo propietario.
	 * @param propietario el valor propietario a asignar
	 */	
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}



	/**
	 * Método de acceso al campo relacionHechoVal.
	 * @return El valor del campo relacionHechoVal
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RelacionHecho_val")	
	public Valor getRelacionHechoVal() {
		return relacionHechoVal;
	}



	/**
	 * Asigna el valor al campo relacionHechoVal.
	 * @param relacionHechoVal el valor relacionHechoVal a asignar
	 */
	public void setRelacionHechoVal(Valor relacionHechoVal) {
		this.relacionHechoVal = relacionHechoVal;
	}


	
	

}