/**
* Nombre del Programa : GenerarRelacionDocumentoElementoServiceImpl.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Jul 2011
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
package mx.gob.segob.nsjp.service.documento.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.RelacionDocumentoElementoDAO;
import mx.gob.segob.nsjp.model.CatRelacion;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.RelacionDocumentoElemento;
import mx.gob.segob.nsjp.service.documento.GenerarRelacionDocumentoElementoService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
@Service
@Transactional
public class GenerarRelacionDocumentoElementoServiceImpl
        implements
            GenerarRelacionDocumentoElementoService {

    @Autowired
    private RelacionDocumentoElementoDAO relacionDao;
    
    @Override
    public Long generarRelacion(Documento doc, Long elementoId,
            Relaciones relacion) throws NSJPNegocioException {
        
        RelacionDocumentoElemento entity = new RelacionDocumentoElemento();
        
        entity.setDocumento(doc);
        entity.setElemento(new Elemento(elementoId));
        entity.setCatRelacion(new CatRelacion(new Long(relacion.ordinal())));
        
        return this.relacionDao.create(entity);
    }

}
