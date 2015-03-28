/**
 * 
 */
package mx.gob.segob.nsjp.service.test.usuario.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.RolService;


/**
 * @author LuisMG
 * 
 */
public class RolServiceImplTest extends BaseTestServicios<RolService> {

	public void testActualizarModulosRol() {
		RolDTO rolDTO = new RolDTO(51L);
		try {
			rolDTO = service.consultarRol(rolDTO);
			List<ModuloDTO> lstModulos = new ArrayList<ModuloDTO>();
			// lstModulos.add(new ModuloDTO(1L));
			// lstModulos.add(new ModuloDTO(2L));
			rolDTO.setModulos(lstModulos);
			if (service.actualizaModulosRol(rolDTO)) {
				System.out.println("Exito al actualizar");
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

	public void testConsultaRolCompleto() {
		RolDTO rolDTO = new RolDTO(7L);
		try {
			rolDTO = service.consultarRolCompleto(rolDTO);
			if (rolDTO != null) {
				System.out.println("==== Información de RolDTO ====");
				System.out.println("Id Rol: " + rolDTO.getRolId());
				System.out.println("Nombre: " + rolDTO.getNombreRol());
				System.out.println("Decripción: " + rolDTO.getDescripcionRol());
				if (rolDTO.getEsPrincipal() != null) {
					if ((rolDTO.getEsPrincipal())) {
						System.out.println("Es el rol principal");
					}
				}
				if (rolDTO.getModulos() != null) {
					System.out.println("==== Información de ModuloDTO ====");
					if (rolDTO.getModulos().size() > 0) {
						for (int i = 0; i < rolDTO.getModulos().size(); i++) {
							System.out.println("== Módulo Id: "
									+ rolDTO.getModulos().get(i).getModuloId());
							System.out.println("== Nombre: "
									+ rolDTO.getModulos().get(i)
											.getNombreModulo());
							System.out.println("== Descripción: "
									+ rolDTO.getModulos().get(i)
											.getDescripcionModulo());
							if (rolDTO.getModulos().get(i).getFunciones() != null) {
								System.out
										.println("==== Información de FuncionDTO ====");
								if (rolDTO.getModulos().get(i).getFunciones()
										.size() > 0) {
									for (int j = 0; i < rolDTO.getModulos()
											.get(i).getFunciones().size(); j++) {
										System.out.println("== == Función Id: "
												+ rolDTO.getModulos().get(i)
														.getFunciones().get(j)
														.getFuncionId());
										System.out.println("== == Nombre: "
												+ rolDTO.getModulos().get(i)
														.getFunciones().get(j)
														.getNombreFuncion());
										System.out
												.println("== == Descripción: "
														+ rolDTO.getModulos()
																.get(i)
																.getFunciones()
																.get(j)
																.getDescripcionFuncion());
									}
								} else {
									System.out.println("== == No Funciones: 0");
								}
							}else{
								System.out.println("== == SIN FUNCIONES");
							}
						}
					} else {
						System.out.println("== == No Modulos: 0");
					}
				}else {
					System.out.println("== == SIN MODULOS");
				}

			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}

	}
	public void testConsultaRolMedio() {
		RolDTO rolDTO = new RolDTO(7L);
		try {
			rolDTO = service.consultarRol(rolDTO);
			if (rolDTO != null) {
				System.out.println("==== Información de RolDTO ====");
				System.out.println("Id Rol: " + rolDTO.getRolId());
				System.out.println("Nombre: " + rolDTO.getNombreRol());
				System.out.println("Decripción: " + rolDTO.getDescripcionRol());
				if (rolDTO.getEsPrincipal() != null) {
					if ((rolDTO.getEsPrincipal())) {
						System.out.println("Es el rol principal");
					}
				}
				if (rolDTO.getModulos() != null) {
					System.out.println("==== Información de ModuloDTO ====");
					if (rolDTO.getModulos().size() > 0) {
						for (int i = 0; i < rolDTO.getModulos().size(); i++) {
							System.out.println("== Módulo Id: "
									+ rolDTO.getModulos().get(i).getModuloId());
							System.out.println("== Nombre: "
									+ rolDTO.getModulos().get(i)
											.getNombreModulo());
							System.out.println("== Descripción: "
									+ rolDTO.getModulos().get(i)
											.getDescripcionModulo());
							if (rolDTO.getModulos().get(i).getFunciones() != null) {
								System.out
										.println("==== Información de FuncionDTO ====");
								if (rolDTO.getModulos().get(i).getFunciones()
										.size() > 0) {
									for (int j = 0; i < rolDTO.getModulos()
											.get(i).getFunciones().size(); j++) {
										System.out.println("== == Función Id: "
												+ rolDTO.getModulos().get(i)
														.getFunciones().get(j)
														.getFuncionId());
										System.out.println("== == Nombre: "
												+ rolDTO.getModulos().get(i)
														.getFunciones().get(j)
														.getNombreFuncion());
										System.out
												.println("== == Descripción: "
														+ rolDTO.getModulos()
																.get(i)
																.getFunciones()
																.get(j)
																.getDescripcionFuncion());
									}
								} else {
									System.out.println("== == No Funciones: 0");
								}
							}else{
								System.out.println("== == SIN FUNCIONES");
							}
						}
					} else {
						System.out.println("== == No Modulos: 0");
					}
				}else {
					System.out.println("== == SIN MODULOS");
				}

			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}

	}
	public void testConsultaRolMinimo() {
		RolDTO rolDTO = new RolDTO(7L);
		try {
			rolDTO = service.consultarRolMinimo(rolDTO);
			if (rolDTO != null) {
				System.out.println("==== Información de RolDTO ====");
				System.out.println("Id Rol: " + rolDTO.getRolId());
				System.out.println("Nombre: " + rolDTO.getNombreRol());
				System.out.println("Decripción: " + rolDTO.getDescripcionRol());
				if (rolDTO.getEsPrincipal() != null) {
					if ((rolDTO.getEsPrincipal())) {
						System.out.println("Es el rol principal");
					}
				}
				if (rolDTO.getModulos() != null) {
					System.out.println("==== Información de ModuloDTO ====");
					if (rolDTO.getModulos().size() > 0) {
						for (int i = 0; i < rolDTO.getModulos().size(); i++) {
							System.out.println("== Módulo Id: "
									+ rolDTO.getModulos().get(i).getModuloId());
							System.out.println("== Nombre: "
									+ rolDTO.getModulos().get(i)
											.getNombreModulo());
							System.out.println("== Descripción: "
									+ rolDTO.getModulos().get(i)
											.getDescripcionModulo());
							if (rolDTO.getModulos().get(i).getFunciones() != null) {
								System.out
										.println("==== Información de FuncionDTO ====");
								if (rolDTO.getModulos().get(i).getFunciones()
										.size() > 0) {
									for (int j = 0; i < rolDTO.getModulos()
											.get(i).getFunciones().size(); j++) {
										System.out.println("== == Función Id: "
												+ rolDTO.getModulos().get(i)
														.getFunciones().get(j)
														.getFuncionId());
										System.out.println("== == Nombre: "
												+ rolDTO.getModulos().get(i)
														.getFunciones().get(j)
														.getNombreFuncion());
										System.out
												.println("== == Descripción: "
														+ rolDTO.getModulos()
																.get(i)
																.getFunciones()
																.get(j)
																.getDescripcionFuncion());
									}
								} else {
									System.out.println("== == No Funciones: 0");
								}
							}else{
								System.out.println("== == SIN FUNCIONES");
							}
						}
					} else {
						System.out.println("== == No Modulos: 0");
					}
				}else {
					System.out.println("== == SIN MODULOS");
				}

			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}

	}



}
