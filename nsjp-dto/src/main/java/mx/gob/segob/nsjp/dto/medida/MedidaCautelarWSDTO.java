package mx.gob.segob.nsjp.dto.medida;

public class MedidaCautelarWSDTO extends MedidaWSDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean activo;

	/**
	 * Regresa el valor de la propiedad activo
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Establece el valor de la propiedad activo
	 * @param activo valo activo a almacenar
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
