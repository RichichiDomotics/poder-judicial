/**
 * Nombre del Programa : JerarquiaOrganizacionalDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 22 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DAO para JerarquiaOrganizacional
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
package mx.gob.segob.nsjp.dao.institucion;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;

/**
 * DAO para JerarquiaOrganizacional.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface JerarquiaOrganizacionalDAO
        extends
            GenericDao<JerarquiaOrganizacional, Long> {

    /**
     * Consulta las areas registradas en <code>JerarquiaOrganizacional</code> a
     * través del campo <code>esArea</code>.
     * 
     * @return
     */
    List<JerarquiaOrganizacional> consultarCatalogoSencilloInstituciones();
    /**
     * Consulta las areas registradas en <code>JerarquiaOrganizacional</code> a
     * través del campo <code>esArea</code>.
     * 
     * @return
     */
    List<JerarquiaOrganizacional> consultarCatalogoSencilloNoInstituciones();
    /**
     * Consulta las areas registradas en <code>JerarquiaOrganizacional</code> a
     * través del campo <code>esArea</code>.
     * 
     * @return
     */
    List<JerarquiaOrganizacional> consultarCatalogoSencilloAreas();

    /**
     * Consulta las areas registradas en <code>JerarquiaOrganizacional</code> a
     * través del campo <code>esArea</code>.
     * 
     * @return
     */
    List<JerarquiaOrganizacional> consultarCatalogoSencilloDepartamentos();

    /**
     * 
     * @param idJerOrgPadre
     * @return
     */
    List<JerarquiaOrganizacional> consultarAreasByPadre(Long idJerOrgPadre);

    /**
     * 
     * @param idJerOrgPadre
     * @return
     */
    List<JerarquiaOrganizacional> consultarDepartamentosByPadre(
            Long idJerOrgPadre);
    
    /**
     * 
     * @param idJerOrgPadre
     * @return
     */
    List<JerarquiaOrganizacional> consultarDepartamentosAgenciaDelMinisterioPublico(
            Long idJerOrgPadre);
    
    /**
     * Se consulta las Jerarquias Organizacionales Dependientes 
     * de acuerdo a un IdJerarquiaResponsable y, opcionalmente, una 
     * lista de ids de jerarquias que no van a ser consideradas.
     * 
     * @param jerarquiaResponsableId
     * @param idsJerarquiaOrgADescartar
     * @return
     */
    List<JerarquiaOrganizacional> consultarJerarquiaOrganizacionalDependienteExcepto(
			Long jerarquiaResponsableId, List<Long> idsJerarquiaOrgADescartar);
}
