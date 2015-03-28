/**
 * Nombre del Programa : IngresarFuncionarioAudienciaServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 6 Sep 2011
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;
import mx.gob.segob.nsjp.service.audiencia.IngresarFuncionarioAudienciaService;
import mx.gob.segob.nsjp.service.funcionario.IngresarFuncionarioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class IngresarFuncionarioAudienciaServiceImpl
        implements
            IngresarFuncionarioAudienciaService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger.getLogger(IngresarFuncionarioAudienciaServiceImpl.class);
    @Autowired
    private IngresarFuncionarioService ingresarPeritoService;
    @Autowired
    private FuncionarioAudienciaDAO funAudDao;
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.audiencia.IngresarFuncionarioAudienciaService
     * #ingresarFuncionarioAudiencia
     * (mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO,
     * mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO)
     */
    @Override
    public void ingresarFuncionarioAudiencia(FuncionarioDTO funcionario,
            AudienciaDTO audiencia) throws NSJPNegocioException {
       Long cveFun = 0L;
       logger.info("Inicia -  ingresarFuncionarioAudiencia(...)");
       logger.debug("funcionario.getClaveFuncionario() :: " + funcionario.getClaveFuncionario());
       logger.debug(" audiencia.getId() :: " + audiencia.getId());
      if (funcionario.getClaveFuncionario()==null) {
          cveFun = this.ingresarPeritoService.ingresarFuncionario(funcionario).getClaveFuncionario();
      } else {
          cveFun = funcionario.getClaveFuncionario();
      }
      FuncionarioAudiencia relacion = new FuncionarioAudiencia();
      FuncionarioAudienciaId relId = new FuncionarioAudienciaId();
      
      relId.setClaveFuncionario(cveFun);
      relId.setAudienciaId(audiencia.getId());
      
      relacion.setId(relId);
      
      this.funAudDao.create(relacion);
      logger.info("Fin -  ingresarFuncionarioAudiencia(...)");
      
    }

}
