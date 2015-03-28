/**
* Nombre del Programa : GuardarCadenaCustodiaServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18/07/2011
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
package mx.gob.segob.nsjp.service.cadenadecustodia.impl;

import java.text.DecimalFormat;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.cadenadecustodia.GuardarCadenaCustodiaService;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
@Transactional
public class GuardarCadenaCustodiaServiceImpl implements
		GuardarCadenaCustodiaService {
	
	public final static Logger logger = 
		Logger.getLogger(GuardarCadenaCustodiaServiceImpl.class);
	
	@Autowired
	private CadenaDeCustodiaDAO cadenaDao;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.cadenadecustodia.GuardarCadenaCustodiaService#guardarCadenaCustodia(mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO, mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public CadenaDeCustodiaDTO guardarCadenaCustodia(CadenaDeCustodiaDTO custodiaDTO,
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR Y ASOCIAR UNA CADENA DE CUSTODIA ****/");
		
		/*Verificación de parámetros*/
		if(custodiaDTO==null||expedienteDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		CadenaDeCustodia cadena = CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(custodiaDTO);
		cadena.setExpediente(new Expediente(expedienteDTO.getExpedienteId()));
		
		
		Long idCadena=cadena.getCadenaDeCustodiaId();
		if(cadena.getCadenaDeCustodiaId()==null){
			List<CadenaDeCustodia> cadenas = cadenaDao.consultarCadenaCustodiaXExpediente(cadena.getExpediente());
				cadena.setFolio(generarFolio(cadenas.size()));
			idCadena=cadenaDao.create(cadena);
			
		}else
			cadenaDao.update(cadena);
		
		CadenaDeCustodia result = cadenaDao.read(idCadena);
		
		return CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(result);
	}

	private String generarFolio(int size) {
		DecimalFormat df=new DecimalFormat("000");
		return "CadenaDeCustodia_"+df.format(size+1);
	}

}
