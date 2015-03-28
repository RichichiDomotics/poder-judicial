/**
* Nombre del Programa : CalcularParidadNumeroExpedienteServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/07/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.expediente.CalcularParidadNumeroExpedienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para el cálculo de paridad de un expediente
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class CalcularParidadNumeroExpedienteServiceImpl implements
		CalcularParidadNumeroExpedienteService {
	
	@Autowired
	NumeroExpedienteDAO numeroExpedienteDAO;
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.expediente.CalcularParidadNumeroExpedienteService#consultarParidadDeNumeroExpediente(java.lang.Long)
	 */
	@Override
	public Boolean consultarParidadDeNumeroExpediente(Long numeroExpedienteId) throws NSJPNegocioException {
		Boolean esPar = null;
		NumeroExpediente numExp = numeroExpedienteDAO.read(numeroExpedienteId);
		if(numExp != null){
			
			//verificar la paridad del numero de expediente
			esPar = numExp.getEsPar();
			if(esPar == null){
				//si no tiene paridad, se busca la paridad del último numero de expediente que tenga asignada paridad
				esPar = numeroExpedienteDAO.consultarUltimaParidadAsignadaDeNumeroExpediente();
				//si no se tiene la paridad última asignada entonces se inicializa la paridad con PAR
				esPar = esPar!=null?!esPar.booleanValue():Boolean.TRUE;
				numExp.setEsPar(esPar);
				numeroExpedienteDAO.saveOrUpdate(numExp);
			}
			
		}
		
		return esPar;
	}

}
