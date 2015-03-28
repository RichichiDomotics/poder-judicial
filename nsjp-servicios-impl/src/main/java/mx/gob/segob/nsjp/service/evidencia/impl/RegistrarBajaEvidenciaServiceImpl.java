/**
 * Nombre del Programa : RegistrarBajaEvidenciaServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-ago-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
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
package mx.gob.segob.nsjp.service.evidencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.evidencia.EstatusEvidencia;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrganizacionalDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.evidencia.RegistrarBajaEvidenciaService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RegistrarBajaEvidenciaServiceImpl implements RegistrarBajaEvidenciaService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(RegistrarBajaEvidenciaServiceImpl.class);

    @Autowired
    private EvidenciaDAO evidenciaDAO;

    @Autowired
    private FuncionarioDAO funcionarioDao;

    @Autowired
    private JerarquiaOrganizacionalDAO jerarquiaOrganizacionalDao;
    
    @Autowired
    private ObjetoDAO objetoDAO;

    @Override
    public void registrarBajaEvidencia(List<EvidenciaDTO> evidenciasDto) throws NSJPNegocioException {
        if (evidenciasDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if (!evidenciasDto.isEmpty()) {
            for (EvidenciaDTO evidenciaDTO : evidenciasDto) {
                if (evidenciaDTO.getFuncionarioBaja() == null) {
                    // La evidencia tiene que tener asociado un funcionario
                    // que de de baja
                    throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
                }
                Evidencia actualiza = EvidenciaTransformer.transformarEvidencia(evidenciaDTO);
                Evidencia evidencia = evidenciaDAO.read(evidenciaDTO.getEvidenciaId());
                evidencia.setEstatus(new Valor(EstatusEvidencia.BAJA.getValorId()));
                FuncionarioDTO funcionarioDto = consultaFuncionarioPorNombreInstitucionYPuesto(evidenciaDTO.getFuncionarioBaja());
                evidencia.setFuncionarioBaja(new Funcionario(funcionarioDto.getClaveFuncionario()));
                evidencia.setMotivoBaja(actualiza.getMotivoBaja());
            }
        }
    }

    @Override
    public FuncionarioDTO consultaFuncionarioPorNombreInstitucionYPuesto(FuncionarioDTO criterioDto) throws NSJPNegocioException{
        if (criterioDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if (criterioDto.getClaveFuncionario() != null) {
            return FuncionarioTransformer.transformarFuncionario(
                    funcionarioDao.read(criterioDto.getClaveFuncionario()));
        }
        List<JerarquiaOrganizacional> instituciones = jerarquiaOrganizacionalDao.consultarCatalogoSencilloInstituciones();
        JerarquiaOrganizacional area = null;
        if (criterioDto.getNombreInstitucion() != null) {
            for (JerarquiaOrganizacional institucion : instituciones) {
                if (institucion.getNombre().equals(criterioDto.getNombreInstitucion())) {
                    area = institucion;
                }
            }
            if (area != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Institucion encontrada por nombre = " + area);
                }
            }
        }
        if (area == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("No se encontro una institucion con el nombre indicado = " + criterioDto.getNombreInstitucion());
            }
            return null;
        }
        Funcionario criterio = FuncionarioTransformer.transformarFuncionario(criterioDto);
        criterio.setArea(area);
        Funcionario funcionario = funcionarioDao.consultaFuncionarioPorNombreInstitucionPuesto(criterio);
        return funcionario == null ? null: FuncionarioTransformer.transformarFuncionario(funcionario);
    }
   
    @Override
    @Transactional
    public Boolean eliminarEvidencia(Long idEvidencia) throws NSJPNegocioException{
    	Boolean esEliminado = false;
    	
    	logger.info("SERVICIO eliminarEvidencia");
		if(idEvidencia==null || idEvidencia  < 0L){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Evidencia evidenciaBD = evidenciaDAO.read(idEvidencia);
		if(evidenciaBD==null){
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		}
		
		Objeto objeto = evidenciaBD.getObjeto();
		if(objeto!=null){
			objeto.setEvidencia(null);
			objeto.setEsActivo(false);
			objetoDAO.update(objeto);
		}
//		evidenciaBD.setObjeto(null);
//		evidenciaBD.setCadenaDeCustodia(null);
		
		evidenciaDAO.delete(evidenciaBD);
		esEliminado = true;
		
		return esEliminado;
    }
    
}
