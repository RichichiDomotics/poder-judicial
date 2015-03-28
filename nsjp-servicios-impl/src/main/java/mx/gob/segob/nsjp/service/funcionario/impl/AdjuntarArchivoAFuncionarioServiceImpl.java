/**
 * 
 */
package mx.gob.segob.nsjp.service.funcionario.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.funcionario.AdjuntarArchivoAFuncionarioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alfonso
 *
 */
@Service
@Transactional
public class AdjuntarArchivoAFuncionarioServiceImpl implements
		AdjuntarArchivoAFuncionarioService {

	private static final Logger logger = Logger
    .getLogger(AdjuntarArchivoAFuncionarioServiceImpl.class);

	@Autowired
	private FuncionarioDAO funcionarioDAO; 

	 @Autowired
	 private ArchivoDigitalDAO archivoDigitalDAO;

	 
	@Override
	public void adjuntarArchivoAFuncionario(Long idfuncionario,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException {
		 logger.debug("SERVICIO QUE ACTUALIZA LA FOTO DE UN FUNCIONARIO : " + idfuncionario);
		 
		 if(idfuncionario==null || adjunto==null )
			 throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		 
		Funcionario funcionarioBD = funcionarioDAO.read(idfuncionario);
		ArchivoDigital ad = ArchivoDigitalTransformer
				.transformarArchivoDigitalDTO(adjunto);						
				
		if(funcionarioBD.getArchivoDigital()==null){
			Long idAD = archivoDigitalDAO.create(ad);
			ad.setArchivoDigitalId(idAD);
			funcionarioBD.setArchivoDigital(ad);				
		}
		else{
			ArchivoDigital ad1= archivoDigitalDAO.read(funcionarioBD.getArchivoDigital().getArchivoDigitalId().longValue());
			ad1.setContenido(ad.getContenido());
			ad1.setNombreArchivo(ad.getNombreArchivo());
			ad1.setTipoArchivo(ad.getTipoArchivo());
			archivoDigitalDAO.update(ad1);
			funcionarioBD.setArchivoDigital(ad1);				
		}
	}
}
