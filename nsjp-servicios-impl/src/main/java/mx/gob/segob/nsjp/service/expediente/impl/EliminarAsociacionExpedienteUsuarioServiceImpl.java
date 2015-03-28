/**
* Nombre del Programa : EliminarAsociacionExpedienteUsuarioServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01 Ago 2011
* Marca de cambio        : N/A
* Descripcion General    : Servicio para eliminar asociacion Expediente Usuario
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.expediente.EliminarAsociacionExpedienteUsuarioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para eliminar asociacion expediente usuario
 * @version 1.0
 * @author rgama
 *
 */

@Service
@Transactional
public class EliminarAsociacionExpedienteUsuarioServiceImpl implements
   EliminarAsociacionExpedienteUsuarioService {

    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;    

    private static final Logger logger = Logger.getLogger(EliminarAsociacionExpedienteUsuarioServiceImpl.class);
	
	@Override
	public void eliminarAsociacionExpedienteUsuario(ExpedienteDTO expedienteDTO, Long idFuncionario)
			throws NSJPNegocioException {
		logger.debug("SERVICIO QUE PERMITE ELIMINAR LA ASOCIACION EXPEDIENTE USUARIO");	
		if(expedienteDTO == null || expedienteDTO.getNumeroExpedienteId()==null 
				|| idFuncionario ==  null || idFuncionario <= 0 )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		logger.debug("ELIMINANDO ASOCIACION EXPEDIENTE");
		logger.debug(">>>> NumeroExpedienteId: " + expedienteDTO.getNumeroExpedienteId());		
		logger.debug(">>>> idFuncionario: " + idFuncionario);
		
		NumeroExpediente loNumExp= this.numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());
		if(loNumExp.getFuncionario() != null && loNumExp.getFuncionario().getClaveFuncionario() == idFuncionario){
			loNumExp.setFuncionario(null);
		}else{
			logger.debug("No se puede eliminar la relación ya que no es el responsable actual");
			throw new NSJPNegocioException(null,null);
			
		}
		logger.debug("Se elimino la asociacion con exito");		

	}

}
