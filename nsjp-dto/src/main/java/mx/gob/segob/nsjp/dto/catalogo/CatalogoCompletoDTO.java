/**
 * Nombre del Programa : CatalogoCompletoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dto.catalogo;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class CatalogoCompletoDTO extends GenericDTO {
    private List<String> columnas = new ArrayList<String>();
    private List<CatalogoDTO> valores = new ArrayList<CatalogoDTO>();
    private Boolean esSistema;
    /**
     * Método de acceso al campo valores.
     * 
     * @return El valor del campo valores
     */
    public List<CatalogoDTO> getValores() {
        return valores;
    }
    /**
     * Asigna el valor al campo valores.
     * 
     * @param valores
     *            el valor valores a asignar
     */
    public void setValores(List<CatalogoDTO> valores) {
        this.valores = valores;
    }
    /**
     * Método de acceso al campo columnas.
     * @return El valor del campo columnas
     */
    public List<String> getColumnas() {
        return columnas;
    }
    /**
     * Asigna el valor al campo columnas.
     * @param columnas el valor columnas a asignar
     */
    public void setColumnas(List<String> columnas) {
        this.columnas = columnas;
    }
    /**
     * Método de acceso al campo esSistema.
     * @return El valor del campo esSistema
     */
    public Boolean getEsSistema() {
        return esSistema;
    }
    /**
     * Asigna el valor al campo esSistema.
     * @param esSistema el valor esSistema a asignar
     */
    public void setEsSistema(Boolean esSistema) {
        this.esSistema = esSistema;
    }
}
