/**
 * Nombre del Programa : AsignarJuezAudienciaServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24/06/2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;
import mx.gob.segob.nsjp.service.audiencia.AsignarJuezAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class AsignarJuezAudienciaServiceImpl implements
		AsignarJuezAudienciaService {

	private final static Logger logger = Logger
			.getLogger(AsignarJuezAudienciaServiceImpl.class);

	@Autowired
	private FuncionarioAudienciaDAO funcAudDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.audiencia.AsignarJuezAudienciaService#
	 * asignarJuezAudiencia(mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO,
	 * java.util.List)
	 */
	@Override
	public void asignarJuezAudiencia(AudienciaDTO audienciaDTO,
			List<FuncionarioDTO> juecesDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASIGNAR JUECES A UNA AUDIENCIA ****/");

		/* Verificación de parámetros */
		if (audienciaDTO == null || juecesDTO == null || juecesDTO.size() <= 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		List<FuncionarioAudiencia> funcionarios=new ArrayList<FuncionarioAudiencia>();
		FuncionarioAudiencia funcAudiencia;
		for (FuncionarioDTO juez : juecesDTO) {
			funcAudiencia=new FuncionarioAudiencia();
			funcAudiencia.setId(new FuncionarioAudienciaId(audienciaDTO.getId(), juez.getClaveFuncionario()));
			
			funcionarios.add(funcAudiencia);
		}
		funcAudDao.saveOrUpdateAll(funcionarios);		
	}
}
