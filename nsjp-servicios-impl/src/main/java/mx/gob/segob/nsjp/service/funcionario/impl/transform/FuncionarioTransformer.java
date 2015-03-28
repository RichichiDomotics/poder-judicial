/**
 * Nombre del Programa : FuncionarioTransformer.java                                    
 * Autor                            : Tattva-IT                                              
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 28/04/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Integración xxxxxxxxxxx                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.service.funcionario.impl.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.institucion.TipoJerarquia;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatDiscriminanteTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatUIEspecializadaTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;

import org.apache.log4j.Logger;

/**
 * Clase para la tranformacion de objetos funcionario a funcionariosDTO
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class FuncionarioTransformer {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(FuncionarioTransformer.class);
    /**
     * Metodo para transformar el objeto funcionario a funcionarioDTO
     * 
     * @param funcionario
     * @return
     */
    public static FuncionarioDTO transformarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            return null;
        }
        final FuncionarioDTO funcionarioDto = new FuncionarioDTO();
        funcionarioDto.setNumeroEmpleado(funcionario.getNumeroEmpleado());
        funcionarioDto.setNombreFuncionario(funcionario.getNombreFuncionario());
        funcionarioDto.setApellidoPaternoFuncionario(funcionario
                .getApellidoPaternoFuncionario());
        funcionarioDto.setApellidoMaternoFuncionario(funcionario
                .getApellidoMaternoFuncionario());
        funcionarioDto.setCedula(funcionario.getCedula());
        funcionarioDto.setClaveFuncionario(funcionario.getClaveFuncionario());
        funcionarioDto.setCurp(funcionario.getCurp());
        funcionarioDto.setEmail(funcionario.getEmail());
        funcionarioDto.setFechaNacimiento(funcionario.getFechaNacimiento());
        
        if (funcionario.getFechaIngreso() != null)
        	funcionarioDto.setFechaIngreso(funcionario.getFechaIngreso());
        
        funcionarioDto.setRfc(funcionario.getRfc());
        funcionarioDto.setSexo(funcionario.getSexo());
        funcionarioDto.setCargaTrabajo(funcionario.getCargaTrabajo());
        funcionarioDto.setEsPar(funcionario.getEsPar());

        if (funcionario.getArchivoDigital() != null)
        	funcionarioDto.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(funcionario.getArchivoDigital()));

        if (funcionario.getPuesto() != null)
            funcionarioDto.setPuesto(new ValorDTO(funcionario.getPuesto()
                    .getValorId(), funcionario.getPuesto().getValor()));
        if (funcionario.getEspecialidad() != null)
            funcionarioDto.setEspecialidad(new ValorDTO(funcionario
                    .getEspecialidad().getValorId(), funcionario
                    .getEspecialidad().getValor()));
        if (funcionario.getTipoEspecialidad() != null)
            funcionarioDto.setTipoEspecialidad(new ValorDTO(funcionario
                    .getTipoEspecialidad().getValorId(), funcionario
                    .getTipoEspecialidad().getValor()));

        if (funcionario.getArea() != null) {

            JerarquiaOrganizacionalDTO jo = new JerarquiaOrganizacionalDTO(
                    funcionario.getArea().getJerarquiaOrganizacionalId());
            funcionarioDto.setJerarquiaOrganizacional(jo);

            JerarquiaOrganizacional jerarq = funcionario.getArea();
            if (jerarq.getTipoJerarquia().getValorId()
                    .equals(TipoJerarquia.DEPARTAMENTO.getValorId())) {
                DepartamentoDTO depa = new DepartamentoDTO(
                        jerarq.getJerarquiaOrganizacionalId(),
                        jerarq.getNombre());
                AreaDTO area = new AreaDTO();
                area.setAreaId(jerarq.getJerarquiaOrgResponsable()
                        .getJerarquiaOrganizacionalId());
                area.setNombre(jerarq.getJerarquiaOrgResponsable().getNombre());
                InstitucionDTO instit = new InstitucionDTO(jerarq
                        .getJerarquiaOrgResponsable()
                        .getJerarquiaOrgResponsable()
                        .getJerarquiaOrganizacionalId(), jerarq
                        .getJerarquiaOrgResponsable()
                        .getJerarquiaOrgResponsable().getNombre());
                area.setInstitucion(instit);
                depa.setArea(area);
                funcionarioDto.setDepartamento(depa);
            } else if (jerarq.getTipoJerarquia().getValorId()
                    .equals(TipoJerarquia.AREA.getValorId())) {
                DepartamentoDTO depa = new DepartamentoDTO();
                AreaDTO aria = new AreaDTO();
                aria.setAreaId(jerarq.getJerarquiaOrganizacionalId());
                aria.setNombre(jerarq.getNombre());
                InstitucionDTO instit = new InstitucionDTO(jerarq
                        .getJerarquiaOrgResponsable()
                        .getJerarquiaOrganizacionalId(), jerarq
                        .getJerarquiaOrgResponsable().getNombre());
                aria.setInstitucion(instit);
                depa.setArea(aria);
                funcionarioDto.setDepartamento(depa);
            } else if (jerarq.getTipoJerarquia().getValorId()
                    .equals(TipoJerarquia.INSTITUCION.getValorId())) {
                DepartamentoDTO depa = new DepartamentoDTO();
                AreaDTO aria = new AreaDTO();
                InstitucionDTO instit = new InstitucionDTO(
                        jerarq.getJerarquiaOrganizacionalId(),
                        jerarq.getNombre());
                aria.setInstitucion(instit);
                depa.setArea(aria);
                funcionarioDto.setDepartamento(depa);
            }
            
            if (funcionario.getUsuario() != null) {
                funcionarioDto.setUsuario(UsuarioTransformer
                        .transformarUsuarioMinimo(funcionario.getUsuario()));
            }

            funcionarioDto.setNombreInstitucion(funcionario.getInstitucion()
                    .getNombre());
            
            if (funcionario.getMedioDeContactos() != null) {
            	MedioDeContactoDTO medioDTO = null;
            	for(MedioDeContacto medio : funcionario.getMedioDeContactos()){
            		if( medio instanceof Telefono){
            			medioDTO = MedioDeContactoTransformer.transformarTelefono((Telefono) medio);
            			funcionarioDto.getMediosContacto().add(medioDTO);
            		}
            		if( medio instanceof CorreoElectronico){
            			medioDTO = MedioDeContactoTransformer.transformarCorreo((CorreoElectronico) medio);
            			funcionarioDto.getMediosContacto().add(medioDTO);
            		}
            	}
            	    	
            	
            }
          //AGENCIAS
            if(funcionario.getDiscriminante()!=null)
            	funcionarioDto.setDiscriminante(CatDiscriminanteTransformer.transformarCatDiscriminante(funcionario.getDiscriminante()));
            
        }

      //UIE
        if(funcionario.getUnidadIEspecializada() !=null){
        	funcionarioDto.setUnidadIEspecializada(CatUIEspecializadaTransformer.transformarCatUIEspecializada(funcionario.getUnidadIEspecializada()));
        }
        
        return funcionarioDto;
    }

    /**
     * Metodo para transformar el objeto funcionario a funcionarioDTO de forma
     * muy basica (clave y nombres)
     * 
     * @param funcionario
     * @return
     */
    public static FuncionarioDTO transformarFuncionarioBasico(
            Funcionario funcionario) {
        if (funcionario == null) {
            return null;
        }
        logger.debug("funcionario.getClaveFuncionario() :: "
                + funcionario.getClaveFuncionario());
        FuncionarioDTO funcionarioDto = new FuncionarioDTO();
        funcionarioDto.setClaveFuncionario(funcionario.getClaveFuncionario());
        funcionarioDto.setApellidoMaternoFuncionario(funcionario
                .getApellidoMaternoFuncionario());
        funcionarioDto.setApellidoPaternoFuncionario(funcionario
                .getApellidoPaternoFuncionario());
        funcionarioDto.setCargaTrabajo(funcionario.getCargaTrabajo());
        
        if (funcionario.getFechaIngreso() != null)
        	funcionarioDto.setFechaIngreso(funcionario.getFechaIngreso());
        
        funcionarioDto.setNombreFuncionario(funcionario.getNombreFuncionario());

        if (funcionario.getArchivoDigital()!= null)
        	funcionarioDto.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(funcionario.getArchivoDigital()));

        if(funcionario != null && funcionario.getArea() != null && funcionario.getArea().getJerarquiaOrganizacionalId() != null)
        	funcionarioDto.setDepartamento(new DepartamentoDTO(funcionario.getArea().getJerarquiaOrganizacionalId(), funcionario.getArea().getNombre()));
      //AGENCIAS
        if(funcionario.getDiscriminante()!=null)
        	funcionarioDto.setDiscriminante(CatDiscriminanteTransformer.transformarCatDiscriminante(funcionario.getDiscriminante()));
       //UNIDAD DE INVESTIGACION ESPECIALIZADA
        if(funcionario.getUnidadIEspecializada() != null)
        	funcionarioDto.setUnidadIEspecializada(CatUIEspecializadaTransformer.transformarCatUIEspecializada(funcionario.getUnidadIEspecializada()));
        return funcionarioDto;

    }

    /**
     * Metodo para transformar el objeto funcionario a funcionarioDTO de forma
     * muy basica (transformarFuncionarioBasico, puesto, especilidad y jefe)
     * 
     * @param funcionario
     * @return
     */
    public static FuncionarioDTO transformarFuncionarioIntermedio(
            Funcionario funcionario) {
        if (funcionario == null) {
            return null;
        }

        FuncionarioDTO funcionarioDto = transformarFuncionarioBasico(funcionario);

        if (funcionario.getFechaIngreso() != null)
        	funcionarioDto.setFechaIngreso(funcionario.getFechaIngreso());
        if (funcionario.getPuesto() != null)
            funcionarioDto.setPuesto(new ValorDTO(funcionario.getPuesto()
                    .getValorId(),funcionario.getPuesto()
                    .getValor()));
        if (funcionario.getEspecialidad() != null)
            funcionarioDto.setEspecialidad(new ValorDTO(funcionario
                    .getEspecialidad().getValorId(),funcionario
                    .getEspecialidad().getValor()));
        if (funcionario.getTipoEspecialidad() != null)
            funcionarioDto.setTipoEspecialidad(new ValorDTO(funcionario
                    .getTipoEspecialidad().getValorId(),funcionario
                    .getTipoEspecialidad().getValor()));
        if (funcionario.getFuncionarioJefe() != null)
            funcionarioDto
                    .setFuncionarioJefe(transformarFuncionarioBasico(funcionario
                            .getFuncionarioJefe()));
        if (funcionario.getArea() != null) {
            funcionarioDto
                    .setJerarquiaOrganizacional(new JerarquiaOrganizacionalDTO(
                            funcionario.getArea()
                                    .getJerarquiaOrganizacionalId(),
                            funcionario.getArea().getNombre()));
        }

        funcionarioDto.setCargaTrabajo(funcionario.getCargaTrabajo());
        
        if (funcionario.getArchivoDigital()!=null)
        	funcionarioDto.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(funcionario.getArchivoDigital()));
        
        return funcionarioDto;
    }
    

	/**
	 * Metodo para transformar el objeto funcionarioDTO a funcionario
	 * 
	 * @param funcionario
	 * @return
	 */
	public static Funcionario transformarFuncionario(
			FuncionarioDTO funcionarioDTO) {

		if (funcionarioDTO == null) {
			return null;
		}

		Funcionario loFuncionario = new Funcionario();

		if (funcionarioDTO.getClaveFuncionario() != null) {
			loFuncionario.setClaveFuncionario(funcionarioDTO
					.getClaveFuncionario());
		}

		if (funcionarioDTO.getArchivoDigital() != null) {
			loFuncionario.setArchivoDigital(ArchivoDigitalTransformer
					.transformarArchivoDigitalDTO(funcionarioDTO
							.getArchivoDigital()));
		}

		if (funcionarioDTO.getNumeroEmpleado() != null) {
			loFuncionario.setNumeroEmpleado(funcionarioDTO.getNumeroEmpleado());
		}

		if (funcionarioDTO.getNombreFuncionario() != null) {
			loFuncionario.setNombreFuncionario(funcionarioDTO
					.getNombreFuncionario());
		}

		if (funcionarioDTO.getApellidoMaternoFuncionario() != null) {
			loFuncionario.setApellidoMaternoFuncionario(funcionarioDTO
					.getApellidoMaternoFuncionario());
		}

		if (funcionarioDTO.getApellidoPaternoFuncionario() != null) {
			loFuncionario.setApellidoPaternoFuncionario(funcionarioDTO
					.getApellidoPaternoFuncionario());
		}

		if (funcionarioDTO.getSexo() != null) {
			loFuncionario.setSexo(funcionarioDTO.getSexo());
		}

		if (funcionarioDTO.getRfc() != null) {
			loFuncionario.setRfc(funcionarioDTO.getRfc());
		}

		if (funcionarioDTO.getCurp() != null) {
			loFuncionario.setCurp(funcionarioDTO.getCurp());
		}

		if (funcionarioDTO.getFechaNacimiento() != null) {
			loFuncionario.setFechaNacimiento(funcionarioDTO
					.getFechaNacimiento());
		}

		if (funcionarioDTO.getFechaIngreso() != null)
			loFuncionario.setFechaIngreso(funcionarioDTO.getFechaIngreso());

		if (funcionarioDTO.getDepartamento() != null) {
			if (funcionarioDTO.getDepartamento().getArea() != null) {
				loFuncionario
						.setArea(new JerarquiaOrganizacional(funcionarioDTO
								.getDepartamento().getArea().getAreaId()));
			}
		}

		if (funcionarioDTO.getPuesto() != null) {
			Valor puesto = new Valor(funcionarioDTO.getPuesto().getIdCampo());
			puesto.setValor(funcionarioDTO.getPuesto().getValor());
			loFuncionario.setPuesto(puesto);
		}

		if (funcionarioDTO.getEspecialidad() != null)
			loFuncionario.setEspecialidad(new Valor(funcionarioDTO
					.getEspecialidad().getIdCampo()));

		if (funcionarioDTO.getTipoEspecialidad() != null)
			loFuncionario.setTipoEspecialidad(new Valor(funcionarioDTO
					.getTipoEspecialidad().getIdCampo()));

		if (funcionarioDTO.getFuncionarioJefe() != null)
			loFuncionario.setFuncionarioJefe(new Funcionario(funcionarioDTO
					.getFuncionarioJefe().getClaveFuncionario()));

		if (funcionarioDTO.getJerarquiaOrganizacional() != null) {
			loFuncionario.setArea(new JerarquiaOrganizacional(funcionarioDTO
					.getJerarquiaOrganizacional()
					.getJerarquiaOrganizacionalId()));
		}

		if (funcionarioDTO.getPersona() != null) {
			Set<MedioDeContacto> mediosDeContacto = new HashSet<MedioDeContacto>(
					0);
			if (funcionarioDTO.getPersona().getCorreosDTO() != null
					&& !funcionarioDTO.getPersona().getCorreosDTO().isEmpty()) {
				CorreoElectronico loCorreoElectronico;
				for (CorreoElectronicoDTO ce : funcionarioDTO.getPersona()
						.getCorreosDTO()) {
					loCorreoElectronico = new CorreoElectronico();
					loCorreoElectronico.setDireccionElectronica(ce
							.getDireccionElectronica());
					loCorreoElectronico.setFuncionario(loFuncionario);
					mediosDeContacto.add(loCorreoElectronico);
				}
			}

			if (funcionarioDTO.getPersona().getTelefonosDTO() != null
					&& !funcionarioDTO.getPersona().getTelefonosDTO().isEmpty()) {
				Telefono loTelefono;
				for (TelefonoDTO tel : funcionarioDTO.getPersona()
						.getTelefonosDTO()) {
					loTelefono = new Telefono();
					loTelefono.setValor(new Valor(tel.getValorTipoTelefono()
							.getIdCampo()));
					loTelefono.setNumeroTelefonico(tel.getNumeroTelefonico());
					if (tel.getCodigoArea() != null)
						loTelefono.setCodigoArea(tel.getCodigoArea());
					if (tel.getCodigoPais() != null)
						loTelefono.setCodigoPais(tel.getCodigoPais());
					loTelefono.setFuncionario(loFuncionario);
					mediosDeContacto.add(loTelefono);
				}
			}

			loFuncionario.setMedioDeContactos(mediosDeContacto);
		}

		// AGENCIAS
		if (funcionarioDTO.getDiscriminante() != null) {
			loFuncionario.setDiscriminante(CatDiscriminanteTransformer
					.transformarCatDiscriminanteDTO(funcionarioDTO
							.getDiscriminante()));
		}
		// UIE
		if (funcionarioDTO.getUnidadIEspecializada() != null)
			loFuncionario.setUnidadIEspecializada(CatUIEspecializadaTransformer
					.transformarCatUIEspecializadaDTO(funcionarioDTO
							.getUnidadIEspecializada()));
		
		return loFuncionario;
	}

    /**
     * 
     * @param lsfun
     * @return
     */
    public static List<FuncionarioDTO> transformarFuncionarios(
            List<Funcionario> lsfun) {
        List<FuncionarioDTO> resp = new ArrayList<FuncionarioDTO>();

        for (Funcionario f : lsfun) {
            FuncionarioDTO fundto = FuncionarioTransformer
                    .transformarFuncionario(f);
            resp.add(fundto);
        }

        return resp;
    }

    public static InvolucradoViewDTO transformarFuncionarioView(
            FuncionarioDTO funcionario) {
        InvolucradoViewDTO involucrado = new InvolucradoViewDTO();

        involucrado.setInvolucradoId(funcionario.getClaveFuncionario());
        involucrado.setNombre(funcionario.getNombreFuncionario());
        involucrado.setApellidoPaterno(funcionario
                .getApellidoPaternoFuncionario());
        involucrado.setApellidoMaterno(funcionario
                .getApellidoMaternoFuncionario());
        involucrado.setNombreInstitucion(funcionario.getNombreInstitucion());
        involucrado.setDelitos("");
        if (funcionario.getPuesto() != null) {
            involucrado.setCalidad(funcionario.getTipoEspecialidad().getIdCampo().toString());
            involucrado.setIdCalidadTipoEspecialidad(funcionario.getTipoEspecialidad().getIdCampo());
        } else {
            involucrado.setCalidad("");
            involucrado.setIdCalidadTipoEspecialidad(0L);
        }
        involucrado.setFuncionario(true);
        involucrado.setTipoEspecialidad(funcionario.getTipoEspecialidad());
      //AGENCIAS
        if (funcionario.getDiscriminante()!=null)
        	involucrado.setDiscriminante(funcionario.getDiscriminante());
        return involucrado;
    }
    /**
     * Metodo para transformar el objeto funcionarioDTO a Funcionario
     * En caso de que loFuncionario sea nulo, se crea una nueva instancia
     * 
     * @param funcionario
     * @return
     */
    public static Funcionario modificarDefensor(FuncionarioDTO funcionarioDTO, Funcionario loFuncionario ) {
    	
    	if(loFuncionario ==null)
    		loFuncionario = new Funcionario();
    	
        loFuncionario.setClaveFuncionario(funcionarioDTO.getClaveFuncionario());
        loFuncionario.setNumeroEmpleado(funcionarioDTO.getNumeroEmpleado());
        loFuncionario.setNombreFuncionario(funcionarioDTO
                .getNombreFuncionario());
        if(funcionarioDTO.getArchivoDigital()!=null)
        	loFuncionario.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigitalDTO(funcionarioDTO.getArchivoDigital()));
        loFuncionario.setApellidoMaternoFuncionario(funcionarioDTO
                .getApellidoMaternoFuncionario());
        loFuncionario.setApellidoPaternoFuncionario(funcionarioDTO
                .getApellidoPaternoFuncionario());
        loFuncionario.setSexo(funcionarioDTO.getSexo());
        loFuncionario.setRfc(funcionarioDTO.getRfc());
        loFuncionario.setCurp(funcionarioDTO.getCurp());
        loFuncionario.setFechaNacimiento(funcionarioDTO.getFechaNacimiento());
        if(funcionarioDTO.getFechaIngreso()!=null)
        	loFuncionario.setFechaIngreso(funcionarioDTO.getFechaIngreso());
        if (funcionarioDTO.getDepartamento() != null) {
            if (funcionarioDTO.getDepartamento().getArea() != null) {
                loFuncionario
                        .setArea(new JerarquiaOrganizacional(funcionarioDTO
                                .getDepartamento().getArea().getAreaId()));
            }
        }
        if (funcionarioDTO.getPuesto() != null)
            loFuncionario.setPuesto(new Valor(funcionarioDTO.getPuesto()
                    .getIdCampo()));

        if (funcionarioDTO.getJerarquiaOrganizacional() != null)
            loFuncionario.setArea(new JerarquiaOrganizacional(funcionarioDTO
                    .getJerarquiaOrganizacional()
                    .getJerarquiaOrganizacionalId()));
        if (funcionarioDTO.getTipoEspecialidad() != null)
            loFuncionario.setTipoEspecialidad(new Valor(funcionarioDTO
                    .getTipoEspecialidad().getIdCampo()));
        if (funcionarioDTO.getFuncionarioJefe() != null)
            loFuncionario.setFuncionarioJefe(new Funcionario(funcionarioDTO
                    .getFuncionarioJefe().getClaveFuncionario()));
        if (funcionarioDTO.getJerarquiaOrganizacional() != null) {
            loFuncionario.setArea(new JerarquiaOrganizacional(funcionarioDTO
                    .getJerarquiaOrganizacional()
                    .getJerarquiaOrganizacionalId()));
        }

        if (funcionarioDTO.getPersona() != null) {
            Set<MedioDeContacto> mediosDeContacto = new HashSet<MedioDeContacto>(
                    0);
            if (funcionarioDTO.getPersona().getCorreosDTO() != null
                    && !funcionarioDTO.getPersona().getCorreosDTO().isEmpty()) {
                CorreoElectronico loCorreoElectronico;
                for (CorreoElectronicoDTO ce : funcionarioDTO.getPersona()
                        .getCorreosDTO()) {
                    loCorreoElectronico = new CorreoElectronico();
                    loCorreoElectronico.setDireccionElectronica(ce
                            .getDireccionElectronica());
                    loCorreoElectronico.setFuncionario(loFuncionario);
                    mediosDeContacto.add(loCorreoElectronico);
                }
            }

            if (funcionarioDTO.getPersona().getTelefonosDTO() != null
                    && !funcionarioDTO.getPersona().getTelefonosDTO().isEmpty()) {
                Telefono loTelefono;
                for (TelefonoDTO tel : funcionarioDTO.getPersona()
                        .getTelefonosDTO()) {
                    loTelefono = new Telefono();
                    loTelefono.setValor(new Valor(tel.getValorTipoTelefono()
                            .getIdCampo()));
                    loTelefono.setNumeroTelefonico(tel.getNumeroTelefonico());
                    if (tel.getCodigoArea() != null)
                        loTelefono.setCodigoArea(tel.getCodigoArea());
                    if (tel.getCodigoPais() != null)
                        loTelefono.setCodigoPais(tel.getCodigoPais());
                    loTelefono.setFuncionario(loFuncionario);
                    mediosDeContacto.add(loTelefono);
                }
            }

            loFuncionario.setMedioDeContactos(mediosDeContacto);
        }

        // Especialidad
        if(funcionarioDTO.getEspecialidad()!= null && funcionarioDTO.getEspecialidad().getIdCampo()!= null)
        	loFuncionario.setEspecialidad(new Valor(funcionarioDTO.getEspecialidad().getIdCampo()));

        loFuncionario.setEsPar(funcionarioDTO.getEsPar());
        
        //AGENCIAS
        if (funcionarioDTO.getDiscriminante()!=null)
        	loFuncionario.setDiscriminante(CatDiscriminanteTransformer.transformarCatDiscriminanteDTO(funcionarioDTO.getDiscriminante()));
      //UIE
        if(funcionarioDTO.getUnidadIEspecializada()!=null){
        	loFuncionario.setUnidadIEspecializada(CatUIEspecializadaTransformer.transformarCatUIEspecializadaDTO(funcionarioDTO.getUnidadIEspecializada()));
        }else{
        	loFuncionario.setUnidadIEspecializada(null);
        }
        
        return loFuncionario;
    }
}
