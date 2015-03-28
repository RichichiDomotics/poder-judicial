/**
* Nombre del Programa : AdministrarCatalogoDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.catalogo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.catalogo.AdministrarCatalogoDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoCompletoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
@Service
public class AdministrarCatalogoDelegateImpl
        implements
            AdministrarCatalogoDelegate {
    @Autowired
    private AdministrarCatalogoService service;
    /* (non-Javadoc)
     * @see mx.gob.segob.nsjp.delegate.catalogo.AdministrarCatalogoDelegate#obtenerValor(java.lang.Long)
     */
    @Override
    public CatalogoDTO obtenerValor(CatalogoDTO idValor) throws NSJPNegocioException {
        return service.obtenerValor(idValor);
    }

    /* (non-Javadoc)
     * @see mx.gob.segob.nsjp.delegate.catalogo.AdministrarCatalogoDelegate#registrarValor(mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO)
     */
    @Override
    public Long registrarValor(CatalogoDTO input) throws NSJPNegocioException {
        return service.registrarValor(input);
    }

    /* (non-Javadoc)
     * @see mx.gob.segob.nsjp.delegate.catalogo.AdministrarCatalogoDelegate#actualizarValor(mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO)
     */
    @Override
    public void actualizarValor(CatalogoDTO input) throws NSJPNegocioException {
        service.actualizarValor(input);

    }

    /* (non-Javadoc)
     * @see mx.gob.segob.nsjp.delegate.catalogo.AdministrarCatalogoDelegate#eliminarValor(mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO)
     */
    @Override
    public void eliminarValor(CatalogoDTO input) throws NSJPNegocioException {
        service.eliminarValor(input);

    }

    /* (non-Javadoc)
     * @see mx.gob.segob.nsjp.delegate.catalogo.AdministrarCatalogoDelegate#obtenerCatalogo(java.lang.Long)
     */
    @Override
    public CatalogoCompletoDTO obtenerCatalogo(Long idCatalogo)
            throws NSJPNegocioException {
        return service.obtenerCatalogo(idCatalogo);
    }

    @Override
    public  List<CatalogoDTO> obtenerListaCatalogos() throws NSJPNegocioException {
        return service.obtenerListaCatalogos();
    }

    @Override
    public CatalogoDTO obtenerDefinicion(Long idCatalogo)
            throws NSJPNegocioException {
        return service.obtenerDefinicion(idCatalogo);
    }

}
