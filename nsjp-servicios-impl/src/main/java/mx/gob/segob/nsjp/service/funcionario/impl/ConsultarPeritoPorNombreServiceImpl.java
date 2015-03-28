/**
 * Nombre del Programa : ConsultarPeritoPorNombreServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jun-2011
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
package mx.gob.segob.nsjp.service.funcionario.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.funcionario.ConsultarPeritoPorNombreService;
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
public class ConsultarPeritoPorNombreServiceImpl implements ConsultarPeritoPorNombreService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarPeritoPorNombreServiceImpl.class);

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    @Override
    public List<FuncionarioDTO> consultarPeritosPorNombre(FuncionarioDTO criterios)
            throws NSJPNegocioException {
        List<FuncionarioDTO> resultado = Collections.EMPTY_LIST;
        if (criterios != null) {
            // Transformamos el DTO en un tipo basico y consultamos los peritos
            // que coincidan con el nombre recibido como parametro por medio del
            // DAO.
            List<Funcionario> peritosXNombre = funcionarioDAO. // consultamos los peritos por nombre
                    consultarPeritosPorNombre(
                    // transformamos los criterios DTO a un tipo basico.
                    FuncionarioTransformer.transformarFuncionario(criterios));
            if (!peritosXNombre.isEmpty()) {
                resultado = new LinkedList<FuncionarioDTO>();
                for (Funcionario funcionario : peritosXNombre) {
                    //...de los peritos encontrados por el DAO los tranformamos en
                    // DTO's y los agregamos al resultado.
                    resultado.add(FuncionarioTransformer.transformarFuncionario(funcionario));
                }
            }
        }
        return resultado;

    }
   
}
