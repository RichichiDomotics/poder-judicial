/**

 * Nombre del Programa : FuncionarioDAOImplTest.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
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
package mx.gob.segob.nsjp.dao.test.funcionario.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.funcionario.Especialidades;
import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.model.Valor;

/**
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class FuncionarioDAOImplTest
        extends BaseTestPersistencia<FuncionarioDAO> {

	
	public void testConsultarCoordinadorDefensores() {
		List<Funcionario> loFuncionarios = 
			daoServcice.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_DEFENSORES.getValorId());		
		assertFalse("Se regresa una lista vacia de funcionarios", loFuncionarios.isEmpty());
		
		for (Funcionario funcionario : loFuncionarios) {
			logger.info("Nombre funcionario: " + funcionario.getNombreFuncionario() + " " 
					+ funcionario.getApellidoPaternoFuncionario()+ " "
					+ funcionario.getApellidoMaternoFuncionario());
			logger.info("Puesto del funcionario: " + funcionario.getPuesto().getValor());	
		}		
	}
	
	public void testConsultarFuncionariosPorRolMultiRol() {
		List<Funcionario> loFuncionarios = 
			daoServcice.consultarFuncionariosPorRolMultiRol(Roles.UAVD.getValorId());		
		assertFalse("Se regresa una lista vacia de funcionarios", loFuncionarios.isEmpty());
		
		
		for (Funcionario funcionario : loFuncionarios) {
			logger.info("Nombre funcionario: " + funcionario.getNombreFuncionario() + " " 
					+ funcionario.getApellidoPaternoFuncionario()+ " "
					+ funcionario.getApellidoMaternoFuncionario());
			logger.info("Puesto del funcionario: " + funcionario.getPuesto().getValor());	
			logger.info("Roles: " + funcionario.getUsuario().getUsuarioRoles());
			if(funcionario.getUsuario().getUsuarioRoles()!=null && !funcionario.getUsuario().getUsuarioRoles().isEmpty()){
				Set<UsuarioRol> UsuarioRoles  = funcionario.getUsuario().getUsuarioRoles();
				for (UsuarioRol usuarioRol : UsuarioRoles) {
					logger.info("Roles: " + usuarioRol.getRol());
				}
			}
		}
		logger.info("Total "+ loFuncionarios.size());
	}
	
	public void testConsultarCoordinadorServiciosPericiales() {
		List<Funcionario> loFuncionarios = 
			daoServcice.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES.getValorId());		
		assertFalse("Se regresa una lista vacia de funcionarios", loFuncionarios.isEmpty());
		
		for (Funcionario funcionario : loFuncionarios) {
			logger.info("Nombre funcionario: " + funcionario.getNombreFuncionario() + " " 
					+ funcionario.getApellidoPaternoFuncionario()+ " "
					+ funcionario.getApellidoMaternoFuncionario());
			logger.info("Puesto del funcionario: " + funcionario.getPuesto().getValor());	
		}
	}
	
    public void testConsultaJuecesDisponibles() {
        try {
            Date fechaConsulta = DateUtils.obtener("25/04/2012", "09:00");
            Integer duracion = 60;
            Funcionario funcionario = new Funcionario();
            CatDiscriminante catDiscriminante = new CatDiscriminante();
            catDiscriminante.setCatDiscriminanteId(2L);
            funcionario.setDiscriminante(catDiscriminante);
            
            
            List<Funcionario> jueces = daoServcice
                    .consultarFuncionariosPorEspecialidadYPuestoDisponiblesParaFechaYHoraAudiencia(
                    fechaConsulta, duracion,
                    TipoEspecialidad.JUEZ,
                    Puestos.ABOGADO_DEFENSOR,
                    null,
                    funcionario);
            for (Funcionario j : jueces) {
                logger.debug("Juez Disponible	: " + j.getClaveFuncionario());
                logger.debug("Nombre			: " + j.getNombreCompleto());
                logger.debug("Discriminante		: " + j.getDiscriminante().getCatDiscriminanteId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            assertTrue("Excepción: ", false);
        }

    }


    public void testInsertarFuncionarioJuez() {
        Funcionario funcionario = new Funcionario();

        // funcionario.setClaveFuncionario(1L);
        funcionario.setNombreFuncionario("Paulino");
        funcionario.setApellidoPaternoFuncionario("Fernandez");
        funcionario.setApellidoMaternoFuncionario("Ramos");
        
        JerarquiaOrganizacional jo = new JerarquiaOrganizacional();
        
        jo.setJerarquiaOrganizacionalId(10L);
        funcionario.setArea(jo);
        funcionario.setTipoEspecialidad(new Valor(TipoEspecialidad.JUEZ.getValorId()));
        funcionario.setEspecialidad(new Valor(Especialidades.JUEZ_AUDIENCIA.getValorId()));

        Long resultado = daoServcice.create(funcionario);
        assertTrue("El resultado debe ser mayor a 0 : ", resultado > 0);
    }

    public void testInsertarFuncionarioDefensor() {
        Funcionario funcionario = new Funcionario();

        // funcionario.setClaveFuncionario(1L);
        funcionario.setNombreFuncionario("Rodrigo");
        funcionario.setApellidoPaternoFuncionario("Roa");
        funcionario.setApellidoMaternoFuncionario("Ramos");
        
        JerarquiaOrganizacional jo = new JerarquiaOrganizacional();
        
        jo.setJerarquiaOrganizacionalId(10L);
        funcionario.setArea(jo);
        funcionario.setTipoEspecialidad(new Valor(TipoEspecialidad.DEFENSOR.getValorId()));
        funcionario.setPuesto(new Valor(Puestos.ABOGADO_DEFENSOR.getValorId()));

        Long resultado = daoServcice.create(funcionario);
        assertTrue("El resultado debe ser mayor a 0 : ", resultado > 0);
    }
    
    
    public void testConsultarFuncionarioXExpediente() {
        Funcionario funcionario = daoServcice.consultarFuncionarioXExpediente("NSJYUCPG20114433333");
        assert funcionario != null;
        logger.info("id func: "+funcionario.getClaveFuncionario());
        logger.info("funcionario.getApellidoMaternoFuncionario() = " + funcionario.getApellidoMaternoFuncionario());
//        logger.info("funcionario.getApellidoPaternoFuncionario() = " + funcionario.getApellidoPaternoFuncionario());
    }
    
    public void testConsultarDefensores(){
        List<Funcionario> resultado = daoServcice.consultarDefensores();
        assertTrue("El resultado debe ser mayor a 0 : ", resultado.size() > 0);
        logger.info(resultado);
    }
    
    public void testConsultarDefensorPorTipoDefensa(){
        List<Funcionario> resultado = daoServcice.consultarDefensorPorTipoDefensa(TipoDefensoria.EXTERNA.getValorId());
        assertTrue("El resultado debe ser mayor a 0 : ", resultado.size() > 0);
        logger.info(resultado);
    }
    
    public void testConsultarFuncionarioPorDepartamento(){
    	Long idDepartamento=1L;
		List<Funcionario> funcionarios = daoServcice.consultarFuncionarioPorDepartamento(idDepartamento);
		logger.info("Existen "+funcionarios.size()+" funcionarios");
		for (Funcionario func : funcionarios) {
			logger.info("------------------------------------------");
			logger.info("Nombre :"+func.getNombreFuncionario());
			logger.info("Apellido Paterno :"+func.getApellidoPaternoFuncionario());
			logger.info("Apellido Materno :"+func.getApellidoMaternoFuncionario()); 
			String puesto=(func.getPuesto()==null)?"Sin Puesto":func.getPuesto().getValor();
			logger.info("Puesto administrativo de la persona :"+puesto);
			logger.info("Dirección de correo electrónico :"+func.getEmail());
		}
    }
    
    public void testConsultarFuncionarioXFiltro(){
    	Funcionario filtro=filtroFuncionario();
    	List<Funcionario> funcionarios = daoServcice.consultarFuncionarioXFiltro(filtro);
		logger.info("Existen "+funcionarios.size()+" funcionarios");
		for (Funcionario func : funcionarios) {
			logger.info("------------------------------------------");
			logger.info("Nombre :"+func.getNombreFuncionario());
			logger.info("Apellido Paterno :"+func.getApellidoPaternoFuncionario());
			logger.info("Apellido Materno :"+func.getApellidoMaternoFuncionario()); 
			String puesto=(func.getPuesto()==null)?"Sin Puesto":func.getPuesto().getValor();
			logger.info("Puesto administrativo de la persona :"+puesto);
			logger.info("Dirección de correo electrónico :"+func.getEmail());
			if(func.getArchivoDigital()!=null){
				logger.info("El nombre del archivo digital es: " + func.getArchivoDigital().getNombreArchivo());
			}
		}
    }

	private Funcionario filtroFuncionario() {
		Funcionario fucn=new Funcionario();
		fucn.setClaveFuncionario(242L);
		return fucn;
	}
	
	public void testObtenerDefensorAsignadoAExpediente() {
		Funcionario respuesta = daoServcice.obtenerDefensorAsignadoAExpediente(40L);
		
		assertNotNull("El funcionario no puede ser nulo : ", respuesta);
		logger.info("El funcionario no puede ser nulo : "+ respuesta);
	}


	public void testObtenerFuncionariosConUsuario() throws NSJPNegocioException{
//    	Funcionario filtro=filtroFuncionario();
    	List<Funcionario> funcionarios = daoServcice.obtenerFuncionariosConUsuario();
		logger.info("Existen "+funcionarios.size()+" funcionarios");
		for (Funcionario func : funcionarios) {
			logger.info("------------------------------------------");
			logger.info("Usuario :"+func.getUsuario().getClaveUsuario());
			logger.info("Nombre :"+func.getNombreFuncionario()+" "+func.getApellidoPaternoFuncionario()+" "+func.getApellidoMaternoFuncionario()); 
		}
	}
	
	public void testObtenerFuncionarioXNumeroEmpleado(){
		Funcionario funcionario = daoServcice.obtenerFuncionarioXNumeroEmpleado("2345");
		assertNotNull(funcionario);
		logger.info("Funcionario es: "+funcionario.getClaveFuncionario());
	}

	public void testConsultarAreasXAgencia(){
		List<JerarquiaOrganizacional> areas = daoServcice.consultarAreasXAgencia(1L);
		logger.info("Existen "+ areas.size()+" areas");
		for (JerarquiaOrganizacional area : areas) {
			logger.info("------------------------------");
			logger.info("ID: "+area.getJerarquiaOrganizacionalId());
			logger.info("Nombre: "+area.getNombre());
			logger.info("Abreviatura: "+area.getAbreviatura());
		}
	}
	
	public void testConsultarFuncionariosPorAreaYPuesto(){
		
		Long area=9L;
		Long puesto=Puestos.FISCAL.getValorId();
			List<Funcionario> funcionarios = daoServcice.consultarFuncionariosPorAreaYPuesto(area, puesto);
			assertNotNull(funcionarios);
			logger.info("Existen "+funcionarios.size()+" funcionarios");
			for (Funcionario fun : funcionarios) {
				logger.info("---------------------");
				logger.info("Clave: "+fun.getClaveFuncionario());
				logger.info("NombreCompleto: "+fun.getNombreCompleto());
				logger.info("Area: "+fun.getArea().getNombre());
				logger.info("DiscriminanteID: "+fun.getDiscriminante().getCatDiscriminanteId());
			}
	}
	
	public void testConsultarFuncionariosPorDiscriminante() throws NSJPNegocioException{
		Long catDiscriminanteId = 1L;
    	List<Funcionario> funcionarios = daoServcice.consultarFuncionariosPorDiscriminante(catDiscriminanteId,null);
		logger.info("Existen "+funcionarios.size()+" funcionarios");
		for (Funcionario func : funcionarios) {
			logger.info("------------------------------------------");
			logger.info("Usuario :"+func.getUsuario().getClaveUsuario());
			logger.info("Nombre :"+func.getNombreFuncionario()+" "+func.getApellidoPaternoFuncionario()+" "+func.getApellidoMaternoFuncionario()); 
		}
	}
	
	public void testConsultarFuncionariosXCriterios(){
		CriterioConsultaFuncionarioExternoDTO criterio = new CriterioConsultaFuncionarioExternoDTO();
		//Rol
		RolDTO rol = new RolDTO();
		rol.setNombreRol("cer");
		criterio.setRolDTO(rol);
		
		//Numero de expediente y n&uacute;mero de caso
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpediente("NSJYUCFG01001201233333");
		CasoDTO caso = new CasoDTO();
		caso.setNumeroGeneralCaso("YUC/FG/XX/PGE/2012/AA-00000");
		expediente.setCasoDTO(caso);
		criterio.setExpendienteDTO(expediente);
		
		//Discriminante
		FuncionarioDTO funcionario = new FuncionarioDTO();
		CatDiscriminanteDTO discriminante = new CatDiscriminanteDTO();
		discriminante.setNombre("Sin agencia ni tribunal");
		funcionario.setDiscriminante(discriminante);
		criterio.setFuncionarioDTO(funcionario);
		try {
			List<Funcionario> funcionarios = daoServcice.consultarFuncionariosXCriterio(criterio);
			if (funcionarios != null && !funcionarios.isEmpty()){
				for (Funcionario func : funcionarios) {
					logger.info("------------------------------------------");
					logger.info("Clave :"+func.getClaveFuncionario());
					logger.info("Nombre :"+func.getNombreFuncionario()+" "+func.getApellidoPaternoFuncionario()+" "+func.getApellidoMaternoFuncionario());
					logger.info("Area :"+func.getArea().getNombre());
					logger.info("Puesto :"+func.getPuesto().getValor());
					logger.info("Email :"+func.getEmail());
				}
				logger.info("------------------------------------------");
				logger.info("Total de funcionarios: " +funcionarios.size());
			}else{
				System.out.println("No se regresaron resultados de la consulta");
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}