/**
* Nombre del Programa : AdministrarCatalogoDelegate.java
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
package mx.gob.segob.nsjp.delegate.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoCompletoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public interface AdministrarCatalogoDelegate {
 CatalogoDTO obtenerValor(CatalogoDTO idValor)  throws NSJPNegocioException;
    
    Long registrarValor(CatalogoDTO input) throws NSJPNegocioException;
    
    void actualizarValor(CatalogoDTO input) throws NSJPNegocioException;
    
    void eliminarValor(CatalogoDTO input) throws NSJPNegocioException;
    
    CatalogoCompletoDTO obtenerCatalogo(Long idCatalogo) throws NSJPNegocioException;
    
    List<CatalogoDTO> obtenerListaCatalogos()throws NSJPNegocioException;
    
    CatalogoDTO obtenerDefinicion(Long idCatalogo)  throws NSJPNegocioException;
}
