/**
 * Nombre del Programa : CentroDetencionServiceImpl.java
 * Autor                            : Cuauhtemoc Paredes Serrano
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-Sept-2011
 * Marca de cambio        : N/A
 * Descripcion General    : Consulta los centros de detencion
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.detencion.impl;

import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.detencion.CentroDetencionDAO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.model.CentroDetencion;
import mx.gob.segob.nsjp.service.detencion.CentroDetencionService;
import mx.gob.segob.nsjp.service.detencion.transform.CentroDetencionTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CentroDetencionServiceImpl implements CentroDetencionService{

	@Autowired
	private CentroDetencionDAO centroDetencionDAO;
	
	@Override
	public CentroDetencionDTO consultarCentroDetencion(Long centroDetencionId)
			throws NSJPNegocioException {
			if(centroDetencionId == null){
	            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	        }
	        CentroDetencion centroDetencion = centroDetencionDAO.read(centroDetencionId);
		return CentroDetencionTransformer.transformar(centroDetencion);
	}
	
	@Override
	public List<CentroDetencionDTO> consultarCentrosDetencionPorTipo(Long tipoCentroDetencion) throws NSJPNegocioException{
		if(tipoCentroDetencion == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
		List<CentroDetencionDTO> respuesta = new LinkedList<CentroDetencionDTO>();
		List<CentroDetencion> centro = centroDetencionDAO.consultarCentrosDetencionPorTipo(tipoCentroDetencion);
		for (CentroDetencion centroDetencion : centro) {
			respuesta.add(CentroDetencionTransformer.transformar(centroDetencion));
		}
		return respuesta;
	}
}
