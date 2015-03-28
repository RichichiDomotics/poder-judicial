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
 * EquipoComputo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EquipoComputo" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "EquipoComputo_id")
public class EquipoComputo extends Objeto {

	// Fields

	private String noSerie;
	
	    private Valor color;
	    private Valor Marca;
	    private Valor tipoEquipo;
	    private String modelo;

	// Constructors

	/** default constructor */
	public EquipoComputo() {
	}




	@Column(name = "cNserie", length = 20)
	public String getNoSerie() {
		return this.noSerie;
	}

	public void setNoSerie(String cnserie) {
		this.noSerie = cnserie;
	}




    /**
     * Método de acceso al campo color.
     * @return El valor del campo color
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Color_val")	
    public Valor getColor() {
        return color;
    }




    /**
     * Asigna el valor al campo color.
     * @param color el valor color a asignar
     */
    public void setColor(Valor color) {
        this.color = color;
    }




    /**
     * Método de acceso al campo marca.
     * @return El valor del campo marca
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Marca_val")    
    public Valor getMarca() {
        return Marca;
    }




    /**
     * Asigna el valor al campo marca.
     * @param marca el valor marca a asignar
     */
    public void setMarca(Valor marca) {
        Marca = marca;
    }




    /**
     * Método de acceso al campo tipoEquipo.
     * @return El valor del campo tipoEquipo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoEquipo_val")    
    public Valor getTipoEquipo() {
        return tipoEquipo;
    }




    /**
     * Asigna el valor al campo tipoEquipo.
     * @param tipoEquipo el valor tipoEquipo a asignar
     */
    public void setTipoEquipo(Valor tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }




    /**
     * Método de acceso al campo modelo.
     * @return El valor del campo modelo
     */
    @Column(name = "cModelo", length = 25)
    public String getModelo() {
        return modelo;
    }




    /**
     * Asigna el valor al campo modelo.
     * @param modelo el valor modelo a asignar
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}