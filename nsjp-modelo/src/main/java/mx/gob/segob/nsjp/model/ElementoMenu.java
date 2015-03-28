/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * @author LuisMG
 * 
 */
@Entity
@Table(name = "ElementoMenu", uniqueConstraints = { @UniqueConstraint(columnNames = "ElementoMenu_id") })
public class ElementoMenu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4309533966493817339L;
	// Atributos
	private Long elementoMenuId;
	private String cNombre;
	private String cComando;
	private String cIdHTML;
	private String cClassHTML;
	private Integer iOrden;
	private ElementoMenu elementoMenuPadre;
	private Funcion funcion;
	private List<Rol> roles;
	private List<ElementoMenu> elementoMenuHijos;

	/**
	 * Constructor por default
	 */
	public ElementoMenu() {
	}

	/**
	 * Constructor Mínimo
	 * 
	 * @return
	 */
	public ElementoMenu(Long elementoMenuId) {
		this.elementoMenuId = elementoMenuId;
	}

	/**
	 * Constructor Completo ElementoMenuPadre_id Funcion_id cNombre iOrden
	 * cComando
	 * 
	 * @return
	 */
	public ElementoMenu(ElementoMenu elementoMenuPadre, Funcion funcion,
			String cNombre, Integer iOrden, String cComando) {
		this.elementoMenuPadre = elementoMenuPadre;
		this.funcion = funcion;
		this.cNombre = cNombre;
		this.iOrden = iOrden;
		this.cComando = cComando;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ElementoMenu_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getElementoMenuId() {
		return elementoMenuId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ElementoMenuPadre_id", nullable = true)
	public ElementoMenu getElementoMenuPadre() {
		return elementoMenuPadre;
	}

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "elementoMenuPadre", orphanRemoval = true)
	public List<ElementoMenu> getElementoMenuHijos() {
		return elementoMenuHijos;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Funcion_id", nullable = true)
	public Funcion getFuncion() {
		return funcion;
	}
	
	

	@Column(name = "cNombre")
	public String getcNombre() {
		return cNombre;
	}

	@Column(name = "cComando")
	public String getcComando() {
		return cComando;
	}

	@Column(name = "iOrden")
	public Integer getiOrden() {
		return iOrden;
	}
	
	/**
	 * @return the cIdHTML
	 */
	@Column(name = "cIdHTML")
	public String getcIdHTML() {
		return cIdHTML;
	}

	/**
	 * @return the cClassHTML
	 */
	@Column(name = "cClassHTML")
	public String getcClassHTML() {
		return cClassHTML;
	}
	
	/**
	 * @return the roles
	 */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RolElementoMenu",
    joinColumns = { @JoinColumn(name = "ElementoMenu_id", updatable = true) },
    inverseJoinColumns = { @JoinColumn(name = "Rol_id", updatable = true) })
	public List<Rol> getRoles() {
		return roles;
	}
	
	public void setElementoMenuId(Long elementoMenuId) {
		this.elementoMenuId = elementoMenuId;
	}

	public void setElementoMenuPadre(ElementoMenu elementoMenuPadre) {
		this.elementoMenuPadre = elementoMenuPadre;
	}

	public void setElementoMenuHijos(List<ElementoMenu> elementoMenuHijos) {
		this.elementoMenuHijos = elementoMenuHijos;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public void setcComando(String cComando) {
		this.cComando = cComando;
	}

	public void setiOrden(Integer iOrden) {
		this.iOrden = iOrden;
	}

	/**
	 * @param cIdHTML the cIdHTML to set
	 */
	public void setcIdHTML(String cIdHTML) {
		this.cIdHTML = cIdHTML;
	}

	/**
	 * @param cClassHTML the cClassHTML to set
	 */
	public void setcClassHTML(String cClassHTML) {
		this.cClassHTML = cClassHTML;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}


	public static final Comparator COMPARA_NOMBRE = new Comparator() {

		public int compare(Object o1, Object o2) {
			if (o1 == o2 || !(o1 instanceof ElementoMenu) || !(o2 instanceof ElementoMenu)) {
				return 0;
			} else {
				ElementoMenu r1 = (ElementoMenu) o1;
				ElementoMenu r2 = (ElementoMenu) o2;
				return r1.getcNombre().compareTo(r2.getcNombre());
			}
		}
	};

}
