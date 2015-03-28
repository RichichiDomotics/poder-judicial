package mx.gob.segob.nsjp.dto.actos;


/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class IndiciplinaDTO extends ActoDTO
{

/**
	 * 
	 */
	private static final long serialVersionUID = -1867169472535678309L;
private TipoIndiciplinaDTO tipoIndiciplinaDTO;

/**
 * Método de acceso al campo tipoIndiciplinaDTO.
 * @return El valor del campo tipoIndiciplinaDTO
 */
public TipoIndiciplinaDTO getTipoIndiciplinaDTO() {
	return tipoIndiciplinaDTO;
}

/**
 * Asigna el valor al campo tipoIndiciplinaDTO.
 * @param tipoIndiciplinaDTO el valor tipoIndiciplinaDTO a asignar
 */
public void setTipoIndiciplinaDTO(TipoIndiciplinaDTO tipoIndiciplinaDTO) {
	this.tipoIndiciplinaDTO = tipoIndiciplinaDTO;
}

}
