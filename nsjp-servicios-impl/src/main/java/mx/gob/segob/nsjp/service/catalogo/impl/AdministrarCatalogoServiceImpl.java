/**
 * Nombre del Programa : AdministrarCatalogoServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
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
package mx.gob.segob.nsjp.service.catalogo.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Transient;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.actividad.ConfActividadDocumentoDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaTemporalDAO;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoCausaDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoClasificacionDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoLugarDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoModalidadDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDelitoModusDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDistritoDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatUIEspecializadaDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatalogoDAO;
import mx.gob.segob.nsjp.dao.catalogo.RegistroDAO;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dao.catalogo.impl.CatDiscriminateDAOImpl;
import mx.gob.segob.nsjp.dao.domicilio.AsentamientoDAO;
import mx.gob.segob.nsjp.dao.domicilio.CatTipoAsentamientoDAO;
import mx.gob.segob.nsjp.dao.domicilio.CiudadDAO;
import mx.gob.segob.nsjp.dao.domicilio.EntidadFederativaDAO;
import mx.gob.segob.nsjp.dao.domicilio.MunicipioDAO;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrganizacionalDAO;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoCompletoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.CampoCatalogo;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.CatDelitoCausa;
import mx.gob.segob.nsjp.model.CatDelitoClasificacion;
import mx.gob.segob.nsjp.model.CatDelitoLugar;
import mx.gob.segob.nsjp.model.CatDelitoModalidad;
import mx.gob.segob.nsjp.model.CatDelitoModus;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.CatDistrito;
import mx.gob.segob.nsjp.model.CatUIEspecializada;
import mx.gob.segob.nsjp.model.Catalogo;
import mx.gob.segob.nsjp.model.Ciudad;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.EntidadFederativa;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Municipio;
import mx.gob.segob.nsjp.model.Registro;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService;
import mx.gob.segob.nsjp.service.catalogo.CatalogoService;
import mx.gob.segob.nsjp.service.infra.ClienteGeneralService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
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
public class AdministrarCatalogoServiceImpl
        implements
            AdministrarCatalogoService {
    /**
     * 
     */
    private final static Logger logger = Logger
            .getLogger(AdministrarCatalogoServiceImpl.class);

    private List<String> columnasReflex = new ArrayList<String>();

    @Autowired
    private ValorDAO valDao;
    @Autowired
    private CatalogoDAO catDao;
    @Autowired
    private CatalogoService carService;
    @Autowired
    private RegistroDAO regDao;

    // dao para catalogos en otras entidades
    @Autowired
    private EntidadFederativaDAO entidadFedDAO = null;
    @Autowired
    private MunicipioDAO mpioDAO = null;
    @Autowired
    private CatTipoAsentamientoDAO tipoAsentamientoDAO = null;
    @Autowired
    private CiudadDAO cdDAO = null;
    @Autowired
    private AsentamientoDAO asentamientoDAO = null;
    @Autowired
    private CatDelitoDAO delitoDao = null;
    @Autowired
    private ConfInstitucionDAO confInstiDao;
    @Autowired
    private JerarquiaOrganizacionalDAO jOrgDao;
    @Autowired
    private ElementoDAO eleDao;
    
    @Autowired
    private ConfActividadDocumentoDAO confActDocDao;
    
    @Autowired
    private FormaDAO formaDao;
    @Autowired
    private FuncionarioDAO funcionarioDao;
    @Autowired
    private CatDiscriminateDAO catDiscriminanteDao;
    
    @Autowired
    private ParametroDAO paramDao;
    
    //Administracion de catalogos Agencias-Distritos
    @Autowired
    private CatDistritoDAO distritoDao= null;
    @Autowired
    private CatDiscriminateDAO discriminanteDao= null;
    @Autowired
    private CatDistritoDAO distDao;
    
    @Autowired
    private CatDelitoCausaDAO catDelitoCausaDao;
    
    @Autowired
    private CatDelitoClasificacionDAO catDelitoClasificacionDAO;
    
    @Autowired
    private CatDelitoLugarDAO catDelitoLugarDAO;
    
    @Autowired
    private CatDelitoModalidadDAO catDelitoModalidadDAO;
    
    @Autowired
    private CatDelitoModusDAO catDelitoModusDAO;
    

    @Autowired
    private  ClienteGeneralService clienteGeneralService;
    
    @Autowired
    private CatUIEspecializadaDAO catUIEspecializadaDAO;
    
    @Autowired
    private SalaTemporalDAO salaTemporalDAO;
    @Autowired
    private SalaAudienciaDAO salaAudienciaDAO;
    @Autowired
    private CiudadDAO ciudadDAO;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService#obtenerValor
     * (java.lang.Long)
     */
    @Override
    public CatalogoDTO obtenerValor(CatalogoDTO idValor)
            throws NSJPNegocioException {
        logger.debug("idValor.idCat :: " + idValor.getIdCatalogo());
        logger.debug("idValor.clave :: " + idValor.getClave());
        CatalogoDTO out = null;
        Catalogos cat = Catalogos.values()[idValor.getIdCatalogo().intValue()];
        switch (cat) {
            case VACIO :
            case INSTITUCION_CON_NSJP :
                out = this.obtenerValorFromExternalEntity(confInstiDao,
                        idValor.getClave());
                break;
            case DELITO :
                out = this.obtenerValorFromExternalEntity(delitoDao,
                        idValor.getClave());
                break;
            case DISTRITOS :
                out = this.obtenerValorFromExternalEntity(distritoDao,
                        idValor.getClave());
                break;
            case AGENCIAS :
                out = this.obtenerValorFromExternalEntity(discriminanteDao,
                        idValor.getClave());
                break;
            case ACTUACIONES :
                out = this.obtenerValorFromExternalEntity(confActDocDao,
                        idValor.getClave());
                break;    
            case PARAMETROS :
                out = this.obtenerValorFromExternalEntity(paramDao,
                        idValor.getClave());
                break;
            case CAUSA_DELITO :            	
            	out = this.obtenerValorFromExternalEntity(catDelitoCausaDao,
                        idValor.getClave());
            	break;
            case CLASIFICACION_DELITO:            	
            	out = this.obtenerValorFromExternalEntity(catDelitoClasificacionDAO,
                        idValor.getClave());
            	break;
            case LUGAR_DELITO :
            	out = this.obtenerValorFromExternalEntity(catDelitoLugarDAO,
                        idValor.getClave());
            	break;
            case MODALIDAD_DELITO :
            	out = this.obtenerValorFromExternalEntity(catDelitoModalidadDAO,
                        idValor.getClave());
            	break;
            case MODUS_DELITO :
            	out = this.obtenerValorFromExternalEntity(catDelitoModusDAO,
                        idValor.getClave());
            	break;
            case TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA :
            	out = this.obtenerValorFromExternalEntity(catUIEspecializadaDAO,
                        idValor.getClave());
                break;    
            case DEPARTAMENTO :
            	out = this.obtenerValorFromExternalEntity(jOrgDao, idValor.getClave());
            	break;
            case AREA :
            	out = this.obtenerValorFromExternalEntity(jOrgDao, idValor.getClave());
            	break;
            case INSTITUCION :
            	out = this.obtenerValorFromExternalEntity(jOrgDao, idValor.getClave());
            	break;
//            case SALA_TEMPORAL :
//            	out = this.obtenerValorFromExternalEntity(salaTemporalDAO, idValor.getClave());
//            	break;
            case SALA_AUDIENCIA :
            	out = this.obtenerValorFromExternalEntity(salaAudienciaDAO, idValor.getClave());
            	break;
            case CIUDAD :
            	out = this.obtenerValorFromExternalEntity(ciudadDAO, idValor.getClave());
            	break;
            case ENTIDAD_FEDERATIVA :
            	out = this.obtenerValorFromExternalEntity(entidadFedDAO, idValor.getClave());
            	break;
            case DELEGACION_MUNICIPIO :
            	out = this.obtenerValorFromExternalEntity(mpioDAO, idValor.getClave());
            	break;
            default :
                out = obtenerValorFromValor(idValor);
                break;

        } // end switch
        out.setIdCatalogo(idValor.getIdCatalogo());
        out.setEsSistema(this.catDao.read(idValor.getIdCatalogo())
                .getEsSistema());
        return out;

    }

    /**
     * @param idValor
     * @return
     */
    private CatalogoDTO obtenerValorFromValor(CatalogoDTO idValor) {
        Valor val = this.valDao.read(idValor.getClave());

        if (val == null) {
            return null;
        }
        final CatalogoDTO out = new CatalogoDTO(val.getValorId(),
                val.getValor());

        if (val.getRegistro().getValors().size() > 1) {
            List<ValorDTO> extras = new ArrayList<ValorDTO>();
            ValorDTO exDto = null;
            for (Valor exPojo : val.getRegistro().getValors()) {
                if (!exPojo.getCampoCatalogo().getEsLlave()) {
                    exDto = new ValorDTO(exPojo.getCampoCatalogo()
                            .getAmpoCatalogoId(), exPojo.getValor());
                    exDto.setNombreCampo(exPojo.getCampoCatalogo()
                            .getNombreCampo());
                    if (exPojo.getCampoCatalogo().getEsRecursivo()) {
                        Valor valorPojoPadre = this.valDao.read(Long
                                .parseLong(exPojo.getValor()));
                        ValorDTO valorDtoPadre = new ValorDTO(valorPojoPadre
                                .getCampoCatalogo().getAmpoCatalogoId(),
                                valorPojoPadre.getValor());
                        exDto.setCatalogoPadre(valorPojoPadre
                                .getCampoCatalogo().getCatalogo()
                                .getCatalogoId());
                        exDto.setValorPadre(valorDtoPadre);
                    }
                    extras.add(exDto);
                }
            }
            Collections.sort(extras);
			Collections.reverse(extras); 
            out.setValoresExras(extras);
        }
        return out;
    }
    
    private CatalogoDTO obtenerValorFromExternalEntity(GenericDao dao,
            Long idReg) {
        CatalogoDTO out = new CatalogoDTO();
        this.columnasReflex = new ArrayList<String>();
        logger.debug("idReg :: " + idReg);
        try {
            Object row = dao.read(idReg);

            Method[] metodos = row.getClass().getMethods();
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                {
                    if (!metodo.getName().startsWith("getClass")
                            && metodo.getName().startsWith("get")
                            && metodo.getParameterTypes().length == 0
                            && metodo.isAnnotationPresent(Id.class)
                            && !metodo.isAnnotationPresent(Transient.class)) {
                        Object objetoObtenido = metodo.invoke(row);
                        // Si el valor del campo es distinto de null
                        if (objetoObtenido != null) {
                            String nombreCampo = metodo.getName().substring(3);
                            // this.columnasReflex.add(nombreCampo);
                            out = new CatalogoDTO(Long.decode(String
                                    .valueOf(objetoObtenido)), "HIDE_CAMPO");
                            out.setCampoId(0L);
                            break;
                        }
                    }

                }
            } // for

            List<ValorDTO> extras = new ArrayList<ValorDTO>();
            ValorDTO exDto = null;
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                {
                    if (!metodo.getName().startsWith("getClass")
                            && metodo.getName().startsWith("get")
                            && metodo.getParameterTypes().length == 0
                            && !metodo.isAnnotationPresent(Id.class)
                            && !metodo.isAnnotationPresent(Transient.class)) {
                        Object objetoObtenido = metodo.invoke(row);
                        // Si el valor del campo es distinto de null
                        String nombreCampo = metodo.getName().substring(3);
                        if(nombreCampo.equals("CatDiscriminante")){
                        	nombreCampo = "Tribunal";
                        }
                        if (!(objetoObtenido instanceof Collection)) {
                            if (objetoObtenido != null) {
                                this.columnasReflex.add(nombreCampo);
                                exDto = new ValorDTO(0L);
                                exDto.setNombreCampo(nombreCampo);
                                if (nombreCampo.equalsIgnoreCase("grupoActividad")) {
                                    obtenerPadreValor(
                                            exDto,
                                            new Valor(Long.decode(String
                                                    .valueOf(objetoObtenido))));
                                } else {
                                    if (esTipoBasico(objetoObtenido)) {
                                        exDto.setValor(String
                                                .valueOf(objetoObtenido));
                                    } else {
                                        if (objetoObtenido instanceof Valor) {
                                            obtenerPadreValor(exDto,
                                                    objetoObtenido);
                                        } else {
                                            if (objetoObtenido instanceof JerarquiaOrganizacional) {
                                                obtenerPadreJerOrg(exDto,
                                                        objetoObtenido);
                                            } else {
                                                if (objetoObtenido instanceof Forma) {
                                                    obtenerPadreForma(exDto,
                                                            objetoObtenido);
                                                  } else {
                                                    if (objetoObtenido instanceof CatDistrito) {
                                                        obtenerPadreDistrito(exDto,
                                                                objetoObtenido);
                                                    }else{
                                                        if (objetoObtenido instanceof CatUIEspecializada) {
                                                        	obtenerCatUIEspecializada(exDto,objetoObtenido);
                                                        }else{
                                                        	if(objetoObtenido instanceof Funcionario){
                                                            	obtenerPadreFun(exDto,
                                                                        objetoObtenido);
                                                            }
                                                        	else{
                                                            	if(objetoObtenido instanceof CatDiscriminante){
                                                            	obtenerPadreCatDis(exDto,
                                                                        objetoObtenido);
                                                            	}
                                                            	else{
                                                            		if (objetoObtenido instanceof EntidadFederativa) {
                                                            			obtenerPadreEntFed(exDto, objetoObtenido);
                                                            		} else {
                                                            			logger.warn("Objeto no identificado "
                                                            					+ objetoObtenido
                                                            					.getClass());
                                                            		}
                                                            	}   
                                                        	}
                                                        }
                                                    }
                           
                                                }
                                            }
                                        }
                                    }
                                }
                                //Anular la columna de Clasificacion 
                                if(!nombreCampo.equals("SalaJAVS")){                               
                                	if(dao instanceof CatDiscriminateDAOImpl && !nombreCampo.equals("Clasificacion") && !nombreCampo.equals("CatDiscriminate")){
                                		extras.add(exDto);}
                                	else{
                                	if(!(dao instanceof CatDiscriminateDAOImpl)){
                                		extras.add(exDto);}
                                	}
                                }
                            }
                        }
                    }

                }
            } // for
            out.setValoresExras(extras);
            return out;

        } catch (NumberFormatException e) {
            logger.warn(e.getMessage(), e);
        } catch (SecurityException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.warn(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param exDto
     * @param objetoObtenido
     */
    private void obtenerPadreJerOrg(ValorDTO exDto, Object objetoObtenido) {
        JerarquiaOrganizacional valCat = this.jOrgDao
                .read(((JerarquiaOrganizacional) objetoObtenido)
                        .getJerarquiaOrganizacionalId());
        ValorDTO valorDtoPadre = new ValorDTO(0L, valCat.getNombre());
        exDto.setValor(String.valueOf(valCat.getJerarquiaOrganizacionalId()));
        exDto.setCatalogoPadre(new Long(Catalogos.AREA.ordinal()));
        exDto.setValorPadre(valorDtoPadre);
    }
    
    /**
     * Permite consultar el Distrito asociado a Agencias
     * @param exDto
     * @param objetoObtenido
     */
    private void obtenerPadreDistrito(ValorDTO exDto, Object objetoObtenido) {
        CatDistrito valCat = this.distDao
                .read(((CatDistrito) objetoObtenido)
                        .getCatDistritoId());
        ValorDTO valorDtoPadre = new ValorDTO(0L, valCat.getNombreDist());
        exDto.setValor(String.valueOf(valCat.getCatDistritoId()));
        exDto.setCatalogoPadre(new Long(Catalogos.DISTRITOS.ordinal()));
        exDto.setValorPadre(valorDtoPadre);
    }
    /**
     * @param exDto
     * @param objetoObtenido
     */
    private void obtenerPadreForma(ValorDTO exDto, Object objetoObtenido) {
        Forma valCat = this.formaDao
                .read(((Forma) objetoObtenido)
                        .getFormaId());
        ValorDTO valorDtoPadre = new ValorDTO(0L, valCat.getNombre());
        exDto.setValor(String.valueOf(valCat.getFormaId()));
        exDto.setCatalogoPadre(new Long(Catalogos.PLANTILLAS.ordinal()));
        exDto.setValorPadre(valorDtoPadre);
    }
    /**
     * @param funDto
     * @param objetoObtenido
     */
    private void obtenerPadreFun(ValorDTO exDto, Object objetoObtenido) {
        Funcionario valCat = this.funcionarioDao
                .read(((Funcionario) objetoObtenido)
                        .getClaveFuncionario());
        ValorDTO valorDtoPadre = new ValorDTO(0L, valCat.getNombreCompleto());
        exDto.setValor(String.valueOf(valCat.getClaveFuncionario()));
        exDto.setCatalogoPadre(new Long(Catalogos.SALA_AUDIENCIA.ordinal()));
        exDto.setValorPadre(valorDtoPadre);
    }
    /**
     * @param catDiscriminanteDto
     * @param objetoObtenido
     */
    private void obtenerPadreCatDis(ValorDTO exDto, Object objetoObtenido) {
        CatDiscriminante valCat = this.catDiscriminanteDao
                .read(((CatDiscriminante) objetoObtenido)
                        .getCatDiscriminanteId());
        ValorDTO valorDtoPadre = new ValorDTO(0L, valCat.getNombre());
        exDto.setValor(String.valueOf(valCat.getCatDiscriminanteId()));
        exDto.setCatalogoPadre(new Long(Catalogos.SALA_AUDIENCIA.ordinal()));
        exDto.setValorPadre(valorDtoPadre);
    }
    private void obtenerPadreEntFed(ValorDTO exDto, Object objetoObtenido) {
    	EntidadFederativa valCat = this.entidadFedDAO.read(((EntidadFederativa) objetoObtenido).getEntidadFederativaId());
    	ValorDTO valorDtoPadre = new ValorDTO(0L, valCat.getNombre());
    	exDto.setValor(String.valueOf(valCat.getEntidadFederativaId()));
    	exDto.setCatalogoPadre(new Long(Catalogos.ENTIDAD_FEDERATIVA.ordinal()));
    	exDto.setValorPadre(valorDtoPadre);
    }
    /**
     * @param exDto
     * @param objetoObtenido
     */
    private void obtenerPadreValor(ValorDTO exDto, Object objetoObtenido) {
        Valor valCat = this.valDao.read(((Valor) objetoObtenido).getValorId());
        ValorDTO valorDtoPadre = new ValorDTO(valCat.getCampoCatalogo()
                .getAmpoCatalogoId(), valCat.getValor());
        exDto.setCatalogoPadre(valCat.getCampoCatalogo().getCatalogo()
                .getCatalogoId());
        exDto.setValorPadre(valorDtoPadre);
        exDto.setValor(String.valueOf(valCat.getValorId()));
    }
    /**
     * Validad los tipos basicos que se procesaran del objeto origen hacia
     * destino.
     * 
     * @param objetoEnActualizacion
     * @return
     */
    private boolean esTipoBasico(Object objetoEnActualizacion) {
        return objetoEnActualizacion.getClass().equals(String.class)
                || objetoEnActualizacion.getClass().equals(Integer.class)
                || objetoEnActualizacion.getClass().equals(Long.class)
                || objetoEnActualizacion.getClass().equals(Boolean.class)
                || objetoEnActualizacion.getClass().equals(Double.class)
                || objetoEnActualizacion.getClass().equals(Short.class)
                || objetoEnActualizacion.getClass().equals(Date.class);
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService#registrarValor
     * (mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO)
     */
    @Override
    public Long registrarValor(CatalogoDTO input) throws NSJPNegocioException {

        Catalogos cat = Catalogos.values()[input.getIdCatalogo().intValue()];
        switch (cat) {
            case VACIO :
            case DELITO :
                this.registarExternal(delitoDao, new CatDelito(), input);
                break;
            case DISTRITOS:
                this.registarExternal(distritoDao, new CatDistrito(), input);
                break;
            case AGENCIAS:
                this.registarExternal(discriminanteDao, new CatDiscriminante(), input);
                break;
            case CAUSA_DELITO :
            	this.registarExternal(catDelitoCausaDao, new CatDelitoCausa(), input);
            	break;
            case CLASIFICACION_DELITO:
            	this.registarExternal(catDelitoClasificacionDAO, new CatDelitoClasificacion(), input);
            	break;
            case LUGAR_DELITO :
            	this.registarExternal(catDelitoLugarDAO, new CatDelitoLugar(), input);
            	break;
            case MODALIDAD_DELITO :
            	this.registarExternal(catDelitoModalidadDAO, new CatDelitoModalidad(), input);
            	break;
            case MODUS_DELITO :
            	this.registarExternal(catDelitoModusDAO, new CatDelitoModus(), input);
            	break;
            case TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA :
            	this.registarExternal(catUIEspecializadaDAO, new CatUIEspecializada(), input);
            	break;
            case AREA :
            	this.registarExternal(jOrgDao, new CatUIEspecializada(), input);
            	break;
            case DEPARTAMENTO :
            	this.registarExternal(jOrgDao, new CatUIEspecializada(), input);
            	break;
            case INSTITUCION :
            	this.registarExternal(jOrgDao, new CatUIEspecializada(), input);
            	break;
            case CIUDAD :
            	this.registarExternal(ciudadDAO, new Ciudad(), input);
            	break;
            case ENTIDAD_FEDERATIVA :
            	this.registarExternal(entidadFedDAO, new EntidadFederativa(), input);
            	break;
            case DELEGACION_MUNICIPIO :
            	this.registarExternal(mpioDAO, new Municipio(), input);
            	break;
            /*case SALA_TEMPORAL :
            	this.registarExternal(salaTemporalDAO, new SalaTemporal(), input);
            	break;
            case SALA_AUDIENCIA :
            	this.registarExternal(salaAudienciaDAO, new SalaAudiencia(), input);
            	break;*/
            default :
                return registrarValorIntoValor(input);

        } // end switch
        return 0L;

    }

    /**
     * @param input
     * @return
     */
    private Long registrarValorIntoValor(CatalogoDTO input) {
        Registro reg = new Registro();
        reg.setRegistroId(this.regDao.getNext());
        reg.setEsActivo(Boolean.TRUE);
        this.regDao.create(reg);
        Valor valPri = new Valor();
        valPri.setValorId(this.valDao.getNext());
        valPri.setValor(input.getValor());
        valPri.setCampoCatalogo(new CampoCatalogo(input.getCampoId()));
        valPri.setRegistro(reg);
        Long idPrincipal = this.valDao.create(valPri);

        for (ValorDTO valEx : input.getValoresExras()) {
            Valor vv = new Valor();
            vv.setValorId(this.valDao.getNext());
            vv.setValor(valEx.getValor());
            vv.setCampoCatalogo(new CampoCatalogo(valEx.getIdCampo()));
            vv.setRegistro(reg);
            this.valDao.create(vv);
        }

        return idPrincipal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService#actualizarValor
     * (mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO)
     */
    @Override
    public void actualizarValor(CatalogoDTO input) throws NSJPNegocioException {
        logger.debug("idValor.idCat :: " + input.getIdCatalogo());
        logger.debug("idValor.clave :: " + input.getClave());
        Catalogos cat = Catalogos.values()[input.getIdCatalogo().intValue()];

        switch (cat) {
            case VACIO :
                break;
            case INSTITUCION_CON_NSJP :

                this.actualizarExternal(confInstiDao, input);

                break;
            case DELITO :
                this.actualizarExternal(delitoDao, input);
                break;
            case DISTRITOS :
                this.actualizarExternal(distritoDao, input);
                break;
            case AGENCIAS :
                this.actualizarExternal(discriminanteDao, input);
                break;
            case ACTUACIONES :
               this.actualizarExternal(confActDocDao,
                        input);
                break;     
            case PARAMETROS :
                this.actualizarExternal(paramDao,
                         input);
                 break;
            case CAUSA_DELITO :
            	this.actualizarExternal(catDelitoCausaDao,input);
            	break;
            case CLASIFICACION_DELITO:
            	this.actualizarExternal(catDelitoClasificacionDAO, input);
            	break;
            case LUGAR_DELITO :
            	this.actualizarExternal(catDelitoLugarDAO, input);
            	break;
            case MODALIDAD_DELITO :
            	this.actualizarExternal(catDelitoModalidadDAO, input);
            	break;
            case MODUS_DELITO :
            	this.actualizarExternal(catDelitoModusDAO, input);
            	break;
            case TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA :
            	this.actualizarExternal(catUIEspecializadaDAO, input);
            	break;
            case AREA :
            	this.actualizarExternal(jOrgDao, input);
            	break;
            case DEPARTAMENTO :
            	this.actualizarExternal(jOrgDao, input);
            	break;
            case INSTITUCION :
            	this.actualizarExternal(jOrgDao, input);
            	break;	         
            case CIUDAD :
            	this.actualizarExternal(ciudadDAO, input);
            	break;
            case ENTIDAD_FEDERATIVA :
            	this.actualizarExternal(entidadFedDAO, input);
            	break;
            case DELEGACION_MUNICIPIO :
            	this.actualizarExternal(mpioDAO, input);
            	break;
            /*case SALA_TEMPORAL :
            	this.actualizarExternal(salaTemporalDAO, input);
            	break;
            case SALA_AUDIENCIA :
            	this.actualizarExternal(salaAudienciaDAO, input);
            	break;*/
            default :
                actualizarValorIntoValor(input);
                break;
        } // end switch

    }

    private void registarExternal(GenericDao dao, Object row, CatalogoDTO input) {

        try {

            asignarValores(row, input);

            dao.create(row);
        } catch (SecurityException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.warn(e.getMessage(), e);
        }

    }

    /**
     * @param row
     * @param input
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void asignarValores(Object row, CatalogoDTO input)
            throws IllegalAccessException, InvocationTargetException {
        for (ValorDTO valExt : input.getValoresExras()) {
        	logger.info("Nombre Valor Extra :: "+valExt.getNombreCampo());
            valExt.setNombreCampo(StringUtils.uncapitalize(valExt
                    .getNombreCampo()));
            String temp = valExt.getValor();
            if (temp.equalsIgnoreCase("true") || temp.equalsIgnoreCase("false")) {
                BeanUtils.setProperty(row, valExt.getNombreCampo(),
                        Boolean.valueOf(temp));
            } else {
                if (valExt.getNombreCampo().indexOf("erarquiaOrg") > 0) {
                    BeanUtils.setProperty(
                            row,
                            valExt.getNombreCampo(),
                            new JerarquiaOrganizacional(Long.parseLong(valExt
                                    .getValor())));
                } else {
                    if (valExt.getNombreCampo().indexOf("orma") > 0) {
                        BeanUtils.setProperty(row, valExt.getNombreCampo(),
                                new Forma(Long.parseLong(valExt.getValor())));
                    } else {
                    	if (valExt.getNombreCampo().indexOf("istrit") > 0) {
                    		//Permite ver si se trata de una instancia de CatDiscriminante
                    		if(row instanceof CatDiscriminante){
                                BeanUtils.setProperty(
                                        row,
                                        valExt.getNombreCampo(),
                                        new CatDistrito(Long.parseLong(valExt
                                                .getValor())));                    			
                    		} else{
                    		     BeanUtils.setProperty(row, valExt.getNombreCampo(),
 	                                    valExt.getValor());
                    		}
                    			

                    		
                    	}else{
                    		if (valExt.getNombreCampo().indexOf("tipo") > -1
	                                || valExt.getNombreCampo().equals(
	                                        "estadoCambioExpediente")
	                                || valExt.getNombreCampo().equals(
	                                        "categoriaActividad")) {
	                            BeanUtils
	                                    .setProperty(
	                                            row,
	                                            valExt.getNombreCampo(),
	                                            new Valor(Long.parseLong(valExt
	                                                    .getValor())));
	                        } else if (valExt.getNombreCampo().indexOf("ntidadFederativa")>0) {
	                        	logger.info("/**** Entidadfederativa");
                    			BeanUtils.setProperty(row, valExt.getNombreCampo(), new EntidadFederativa(
                    					Long.parseLong(valExt.getValor())));
                    		} else if (valExt.getNombreCampo().indexOf("ais")>0) {
                    			 BeanUtils.setProperty(row, valExt.getNombreCampo(),
 	                                    new Valor(Long.parseLong(valExt.getValor())));
                    		} else {
	                            BeanUtils.setProperty(row, valExt.getNombreCampo(),
	                                    valExt.getValor());
	                        }

                    	}
	              }
                }
            }

        }
    }

    private void actualizarExternal(GenericDao dao, CatalogoDTO input) {

        try {
            Object row = dao.read(input.getClave());

            asignarValores(row, input);

            dao.update(row);
        } catch (SecurityException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    /**
     * @param input
     */
    private void actualizarValorIntoValor(CatalogoDTO input) {
        Valor valPri = this.valDao.read(input.getClave());
        valPri.setValor(input.getValor());
        this.valDao.update(valPri);

        Set<Valor> valorsPojo = valPri.getRegistro().getValors();

        Map<Long, Valor> mapValorsBD = new HashMap<Long, Valor>();

        for (Valor val : valorsPojo) {
            mapValorsBD.put(val.getCampoCatalogo().getAmpoCatalogoId(), val);
        }

        for (ValorDTO valEx : input.getValoresExras()) {
            Valor vv = mapValorsBD.get(valEx.getIdCampo());
            vv.setValor(valEx.getValor());
            this.valDao.update(vv);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService#eliminarValor
     * (mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO)
     */
    @Override
    public void eliminarValor(CatalogoDTO input) throws NSJPNegocioException {
    	
    	 if (input == null || input.getIdCatalogo() == null || input.getClave()==null) {
             throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
         }
    	 
    	Long idCatalogo = 0L;
    	idCatalogo=input.getIdCatalogo();
    	
    	logger.debug("idCatalogo :: " + idCatalogo);
    	Catalogos cat = Catalogos.values()[idCatalogo.intValue()];
        
        switch (cat) {

	        case CAUSA_DELITO :
	        	CatDelitoCausa catDelitoCausa = this.catDelitoCausaDao.read(input.getClave());
	        	if(catDelitoCausa != null){
	        		this.catDelitoCausaDao.delete(catDelitoCausa);
	        	}
	        	break;
	        case CLASIFICACION_DELITO:
	        	CatDelitoClasificacion catDelitoClasif = this.catDelitoClasificacionDAO.read(input.getClave());
	        	if(catDelitoClasif != null){
	        		this.catDelitoClasificacionDAO.delete(catDelitoClasif);
	        	}
	        	break;
	        case LUGAR_DELITO:
	        	CatDelitoLugar catDelitoLugar = this.catDelitoLugarDAO.read(input.getClave());
	        	if(catDelitoLugar != null){
	        		this.catDelitoLugarDAO.delete(catDelitoLugar);
	        	}
	        	break;
	        case MODALIDAD_DELITO:
	        	CatDelitoModalidad catDelitoModalidad = this.catDelitoModalidadDAO.read(input.getClave());
	        	if(catDelitoModalidad != null){
	        		this.catDelitoModalidadDAO.delete(catDelitoModalidad);
	        	}
	        	break;
	        case MODUS_DELITO :
	        	CatDelitoModus catDelitoModus = this.catDelitoModusDAO.read(input.getClave());
	        	if(catDelitoModus != null){
	        		this.catDelitoModusDAO.delete(catDelitoModus);
	        	}
	        	break;
	        case TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA:
	        	CatUIEspecializada catUIEspecializada = this.catUIEspecializadaDAO.read(input.getClave());
	        	if(catUIEspecializada != null){
	        		this.catUIEspecializadaDAO.delete(catUIEspecializada);
	        	}
	        	break;
	        default :
        Valor temp = this.valDao.read(input.getClave());
        Registro reg = temp.getRegistro();
        reg.setEsActivo(Boolean.FALSE);
        this.regDao.update(reg);
	            break;
	    } // end switch
    }

    @Override
    public CatalogoCompletoDTO obtenerCatalogo(Long idCatalogo)
            throws NSJPNegocioException {
        logger.debug("idCatalogo :: " + idCatalogo);
        CatalogoCompletoDTO resp = new CatalogoCompletoDTO();

        Catalogos cat = Catalogos.values()[idCatalogo.intValue()];
        List<CatalogoDTO> sencillo = this.carService.recuperarCatalogo(cat);

        List<CatalogoDTO> completo = new ArrayList<CatalogoDTO>();

        switch (cat) {
            case VACIO :
            case TIPO_ASENTAMIENTO :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(
                            tipoAsentamientoDAO, catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
            case INSTITUCION_CON_NSJP :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(
                            confInstiDao, catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
            case INSTITUCION :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(jOrgDao,
                            catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
            case AREA :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(jOrgDao,
                            catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
            case DEPARTAMENTO :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(jOrgDao,
                            catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
            case DELITO :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(delitoDao,
                            catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
                
            case DISTRITOS :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(distritoDao,
                            catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
                
            case AGENCIAS :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(discriminanteDao,
                            catSencillo.getClave()));
                }
                List<String> columnas = this.columnasReflex;
                columnas.remove(3);
                logger.info("Las nuevas columnas by gama " + columnas);
                resp.setColumnas(columnas);
                break;
            case ACTUACIONES :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(confActDocDao,
                            catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;
            case PARAMETROS :
                for (CatalogoDTO catSencillo : sencillo) {
                    completo.add(this.obtenerValorFromExternalEntity(paramDao,
                            catSencillo.getClave()));
                }
                resp.setColumnas(this.columnasReflex);
                break;                
            case CAUSA_DELITO :
            	for (CatalogoDTO catSencillo : sencillo){
            		completo.add(this.obtenerValorFromExternalEntity(catDelitoCausaDao,catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case CLASIFICACION_DELITO:
            	for (CatalogoDTO catSencillo : sencillo){
            		completo.add(this.obtenerValorFromExternalEntity(catDelitoClasificacionDAO,catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case LUGAR_DELITO :
            	for (CatalogoDTO catSencillo : sencillo){
            		completo.add(this.obtenerValorFromExternalEntity(catDelitoLugarDAO,catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case MODALIDAD_DELITO :
            	for (CatalogoDTO catSencillo : sencillo){
            		completo.add(this.obtenerValorFromExternalEntity(catDelitoModalidadDAO,catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case MODUS_DELITO :
            	for (CatalogoDTO catSencillo : sencillo){
            		completo.add(this.obtenerValorFromExternalEntity(catDelitoModusDAO,catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA :
            	for (CatalogoDTO catSencillo : sencillo){
            		completo.add(this.obtenerValorFromExternalEntity(catUIEspecializadaDAO,catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
//            case SALA_TEMPORAL:
//            	for (CatalogoDTO catSencillo : sencillo){
//            		completo.add(this.obtenerValorFromExternalEntity(salaTemporalDAO,catSencillo.getClave()));
//            	}
//            	resp.setColumnas(this.columnasReflex);
//            	break;
            case SALA_AUDIENCIA:
            	for (CatalogoDTO catSencillo : sencillo){
            		completo.add(this.obtenerValorFromExternalEntity(salaAudienciaDAO,catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case CIUDAD:
            	for (CatalogoDTO catSencillo : sencillo) {
            		completo.add(this.obtenerValorFromExternalEntity(ciudadDAO, catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case ENTIDAD_FEDERATIVA:
            	for (CatalogoDTO catSencillo : sencillo) {
            		completo.add(this.obtenerValorFromExternalEntity(entidadFedDAO, catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            case DELEGACION_MUNICIPIO:
            	for (CatalogoDTO catSencillo : sencillo) {
            		completo.add(this.obtenerValorFromExternalEntity(mpioDAO, catSencillo.getClave()));
            	}
            	resp.setColumnas(this.columnasReflex);
            	break;
            default :
                resp.setColumnas(this.valDao.obtenerNombresColumnas(idCatalogo));
                for (CatalogoDTO catSencillo : sencillo) {
                    catSencillo.setIdCatalogo(idCatalogo);
                    completo.add(this.obtenerValor(catSencillo));
                }
                break;
        } // end switch
        logger.debug("completo.size() :: " + completo.size());
        logger.debug("resp.getColumnas().size() :: "
                + resp.getColumnas().size());
        resp.setValores(completo);
        return resp;
    }

    @Override
    public List<CatalogoDTO> obtenerListaCatalogos()
            throws NSJPNegocioException {
    	
        List<Catalogo> temp = this.catDao.obtenerTodos();
        List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        CatalogoDTO catDto = null;
        
        	ConfInstitucion confInstitucion = this.catDao.consultarInsitucionActual();
        	if(!confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId()) ){
        		//Si la institucion es PJ entonces permite administrar el Catalogo de Tribunales
        		if(confInstitucion.getConfInstitucionId().equals(Instituciones.PJ.getValorId())){ 
        		  for (Catalogo cat : temp) {
           			 if(cat.getCatalogoId() != null && cat.getCatalogoId() == Catalogos.AGENCIAS.ordinal())
           				 catDto = new CatalogoDTO(cat.getCatalogoId(), "Tribunales");
           			 else
           				 catDto = new CatalogoDTO(cat.getCatalogoId(), cat.getNombre());
           			 catDto.setEsSistema(cat.getEsSistema());
           			 resp.add(catDto);
           		   }        			
        		}else{//Si se trata de SSP y Defensoria se omite el catalogo de agencias
        			for (Catalogo cat : temp) {
              			 if(cat.getCatalogoId() != null && cat.getCatalogoId() != Catalogos.AGENCIAS.ordinal()){
              				 catDto = new CatalogoDTO(cat.getCatalogoId(), cat.getNombre());
                 			 catDto.setEsSistema(cat.getEsSistema());
                 			 resp.add(catDto);
              			 }
              		 }     
        		}
        			
        	}
        	else{//Si la institucion es PGJ entonces permite administrar el Catalogo de Agencias
        		 for (Catalogo cat : temp) {
        			 if(cat.getCatalogoId() != null && cat.getCatalogoId() == Catalogos.AGENCIAS.ordinal())
        				 catDto = new CatalogoDTO(cat.getCatalogoId(), "Agencias");
        			 else
        				 catDto = new CatalogoDTO(cat.getCatalogoId(), cat.getNombre());
        			 catDto.setEsSistema(cat.getEsSistema());
        			 resp.add(catDto);
        		 }
        	}
        return resp;
    }

    @Override
    public CatalogoDTO obtenerDefinicion(Long idCatalogo)
            throws NSJPNegocioException {

        logger.debug("idCatalogo :: " + idCatalogo);
        Catalogos cat = Catalogos.values()[idCatalogo.intValue()];
        CatalogoDTO resp = null;
        switch (cat) {
            case VACIO :
                break;
            case DELITO :
                resp = this.obtenerDefinicionFromExternal(delitoDao);
                break;
            case DISTRITOS :
                resp = this.obtenerDefinicionFromExternal(distritoDao);
                break;
            case AGENCIAS :
                resp = this.obtenerDefinicionFromExternal(discriminanteDao);
                break;
            case CAUSA_DELITO :            	
            	resp = this.obtenerDefinicionFromExternal(catDelitoCausaDao);
            	break;
            case CLASIFICACION_DELITO:            	
            	resp = this.obtenerDefinicionFromExternal(catDelitoClasificacionDAO);
            	break;
            case LUGAR_DELITO :
            	resp = this.obtenerDefinicionFromExternal(catDelitoLugarDAO);
            	break;
            case MODALIDAD_DELITO :
            	resp = this.obtenerDefinicionFromExternal(catDelitoModalidadDAO);
            	break;
            case MODUS_DELITO :
            	resp = this.obtenerDefinicionFromExternal(catDelitoModusDAO);
            	break;
            case TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA :
            	resp = this.obtenerDefinicionFromExternal(catUIEspecializadaDAO);
            	break;  
//            case SALA_TEMPORAL :
//            	resp = this.obtenerDefinicionFromExternal(salaTemporalDAO);
//            	break;
            case SALA_AUDIENCIA :
            	resp = this.obtenerDefinicionFromExternal(salaAudienciaDAO);
            	break;
            case CIUDAD :
            	resp = this.obtenerDefinicionFromExternal(ciudadDAO);
            	break;
            case ENTIDAD_FEDERATIVA :
            	resp = this.obtenerDefinicionFromExternal(entidadFedDAO);
            	break;
            case DELEGACION_MUNICIPIO :
            	resp = this.obtenerDefinicionFromExternal(mpioDAO);
            	break;
            default :
                resp = obtenerDefinicionFromValor(idCatalogo);
                break;
        } // end switch

        if(resp != null){
        	resp.setEsSistema(Boolean.FALSE);
        	resp.setIdCatalogo(idCatalogo);
        }
        return resp;
    }

    /**
     * 
     * @param dao
     * @return
     */
    private CatalogoDTO obtenerDefinicionFromExternal(GenericDao dao) {
        CatalogoDTO out = null;
        this.columnasReflex = new ArrayList<String>();
        try {
            List temp = dao.findAll(null, false);
            Object row = null;

            if (temp == null || temp.isEmpty()) {
                return null;
            }
            row = temp.get(0);
            Method[] metodos = row.getClass().getMethods();
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                {
                    if (!metodo.getName().startsWith("getClass")
                            && metodo.getName().startsWith("get")
                            && metodo.getParameterTypes().length == 0
                            && metodo.isAnnotationPresent(Id.class)) {
                        Object objetoObtenido = metodo.invoke(row);
                        // Si el valor del campo es distinto de null
                        if (objetoObtenido != null) {
                            String nombreCampo = metodo.getName().substring(3);
                            // this.columnasReflex.add(nombreCampo);
                            out = new CatalogoDTO(0L, "HIDE_CAMPO");
                            out.setCampoId(0L);
                            break;
                        }
                    }

                }
            } // for

            List<ValorDTO> extras = new ArrayList<ValorDTO>();
            ValorDTO exDto = null;
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                {
                    if (!metodo.getName().startsWith("getClass")
                            && metodo.getName().startsWith("get")
                            && metodo.getParameterTypes().length == 0
                            && !metodo.isAnnotationPresent(Id.class)) {
                        Object objetoObtenido = metodo.invoke(row);
                        // Si el valor del campo es distinto de null
                        String nombreCampo = metodo.getName().substring(3);
                        if (!(objetoObtenido instanceof Collection)) {
                            this.columnasReflex.add(nombreCampo);
                            if (objetoObtenido != null) {
                                exDto = new ValorDTO(0L);
                                exDto.setNombreCampo(nombreCampo);
                                if (esTipoBasico(objetoObtenido)) {
                                    exDto.setValor(String
                                            .valueOf(objetoObtenido));
                                } else {
                                    if (objetoObtenido instanceof Valor) {
                                        obtenerPadreValor(exDto, objetoObtenido);
                                    } else {
                                        if (objetoObtenido instanceof JerarquiaOrganizacional) {
                                            obtenerPadreJerOrg(exDto,
                                                    objetoObtenido);
                                        } else {
                                        	if (objetoObtenido instanceof CatDistrito) {
                                        		obtenerPadreDistrito(exDto,
                                                        objetoObtenido);
                                        	}else{
                                        		if (objetoObtenido instanceof EntidadFederativa) {
                                        			obtenerPadreEntFed(exDto, objetoObtenido);
                                        		} else {
                                        			logger.warn("Objeto no identificado "
                                        				+ objetoObtenido.getClass());
                                        		}
                                        	}
                                        }
                                    }
                                }
                                exDto.setValor("");
                              //Anular la columna de Clasificacion 
                                if(dao instanceof CatDiscriminateDAOImpl){
                                	if(!nombreCampo.equals("Clasificacion")){
                                		extras.add(exDto);
                                		logger.info("Se agrega la columna para la insercion/actualizacion by gama " + nombreCampo);
                                	}
                                		
                                	else
                                		logger.info("No se agrega la columna para la insercion/actualizacion by gama");
                                }
                                else{                                	
                                	extras.add(exDto);
                                }
                            }
                        }
                    }

                }
            } // for
            out.setValoresExras(extras);
            return out;

        } catch (NumberFormatException e) {
            logger.warn(e.getMessage(), e);
        } catch (SecurityException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.warn(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param idCatalogo
     * @return
     */
    private CatalogoDTO obtenerDefinicionFromValor(Long idCatalogo) {
        Catalogo aux = catDao.read(idCatalogo);
        for (CampoCatalogo llave : aux.getCampoCatalogos()) {
            if (llave.getEsLlave()) {
                for (Valor val : llave.getValors()) {
                    final CatalogoDTO out = new CatalogoDTO(0L, "");
                    out.setEsSistema(Boolean.FALSE);
                    out.setCampoId(llave.getAmpoCatalogoId());
                    if (val.getRegistro().getValors().size() > 1) {
                        List<ValorDTO> extras = new ArrayList<ValorDTO>();
                        ValorDTO exDto = null;
                        for (Valor exPojo : val.getRegistro().getValors()) {
                            if (!exPojo.getCampoCatalogo().getEsLlave()) {
                                exDto = new ValorDTO(exPojo.getCampoCatalogo()
                                        .getAmpoCatalogoId());
                                exDto.setNombreCampo(exPojo.getCampoCatalogo()
                                        .getNombreCampo());
                                exDto.setValor("");
                                if (exPojo.getCampoCatalogo().getEsRecursivo()) {
                                    Valor valorPojoPadre = this.valDao
                                            .read(Long.parseLong(exPojo
                                                    .getValor()));
                                    ValorDTO valorDtoPadre = new ValorDTO(
                                            valorPojoPadre.getCampoCatalogo()
                                                    .getAmpoCatalogoId());
                                    exDto.setCatalogoPadre(valorPojoPadre
                                            .getCampoCatalogo().getCatalogo()
                                            .getCatalogoId());
                                    exDto.setValorPadre(valorDtoPadre);
                                }
                                extras.add(exDto);
                            }
                        }
                        out.setValoresExras(extras);
                    }

                    return out;
                } // for
            } // es llave
        }
        return null;
    }

    @Override
    public List<FuncionarioDTO> consultarFuncionariosXTribunal(
      		 Long catDiscriminanteId,Instituciones target) throws NSJPNegocioException{
    	return clienteGeneralService.consultarFuncionariosXTribunal(catDiscriminanteId, target);
    }

    @Override
    public List<CatDiscriminanteDTO> consultarTribunalesPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException {
    	return clienteGeneralService.consultarTribunalesPorDistrito(distritoId, target);
    }
    
    @Override
    public List<CatDiscriminanteDTO> consultarAgenciasPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException {
    	return clienteGeneralService.consultarAgenciasPorDistrito(distritoId, target);
    }
    
    /**
     * Permite consultar el CatalogoUIE
     * @param exDto
     * @param objetoObtenido
     */
    private void obtenerCatUIEspecializada(ValorDTO exDto, Object objetoObtenido) {
    	CatUIEspecializada valCat = this.catUIEspecializadaDAO
                .read(((CatUIEspecializada) objetoObtenido)
                        .getCatUIEId());
        ValorDTO valorDtoPadre = new ValorDTO(0L, valCat.getNombreUIE());
        exDto.setValor(String.valueOf(valCat.getCatUIEId()));
        exDto.setCatalogoPadre(new Long(Catalogos.TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA.ordinal()));
        exDto.setValorPadre(valorDtoPadre);
    }
}
