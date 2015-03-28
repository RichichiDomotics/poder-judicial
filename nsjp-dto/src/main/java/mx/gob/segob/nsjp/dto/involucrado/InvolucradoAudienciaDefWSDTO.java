package mx.gob.segob.nsjp.dto.involucrado;

public class InvolucradoAudienciaDefWSDTO {

	private String nombre;
	private Long calidad;
	private String delito;
	private Boolean detenido;

	/**
	 * Regresa el valor de la propiedad nombre
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el valor de la propiedad nombre
	 * @param nombre valo nombre a almacenar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Regresa el valor de la propiedad calidad
	 * @return the calidad
	 */
	public Long getCalidad() {
		return calidad;
	}

	/**
	 * Establece el valor de la propiedad calidad
	 * @param calidad valo calidad a almacenar
	 */
	public void setCalidad(Long calidad) {
		this.calidad = calidad;
	}

	/**
	 * Regresa el valor de la propiedad delito
	 * @return the delito
	 */
	public String getDelito() {
		return delito;
	}

	/**
	 * Establece el valor de la propiedad delito
	 * @param delito valo delito a almacenar
	 */
	public void setDelito(String delito) {
		this.delito = delito;
	}

	/**
	 * Regresa el valor de la propiedad detenido
	 * @return the detenido
	 */
	public Boolean getDetenido() {
		return detenido;
	}

	/**
	 * Establece el valor de la propiedad detenido
	 * @param detenido valo detenido a almacenar
	 */
	public void setDetenido(Boolean detenido) {
		this.detenido = detenido;
	}

}
