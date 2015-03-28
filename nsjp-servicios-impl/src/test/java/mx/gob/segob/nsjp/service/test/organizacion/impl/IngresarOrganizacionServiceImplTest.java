/**
* Nombre del Programa : IngresarOrganizacionServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jun 2011
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
package mx.gob.segob.nsjp.service.test.organizacion.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.service.organizacion.IngresarOrganizacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class IngresarOrganizacionServiceImplTest extends BaseTestServicios<IngresarOrganizacionService> {

	public void testIngresarDomicilio () {
		OrganizacionDTO organizacion = new OrganizacionDTO();
		DomicilioDTO domicilioOrganizacion = new DomicilioDTO();
		CalidadDTO calidadOrg = new CalidadDTO();
		CalidadDTO calidadDom = new CalidadDTO();
		
		ExpedienteDTO expediente = new ExpedienteDTO(1L);
		
		calidadOrg.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
		calidadDom.setCalidades(Calidades.DOMICILIO);
		
		domicilioOrganizacion.setCalle("calle organizacion 1");
		domicilioOrganizacion.setDescripcion("descripcion domicilio organizacion 1");
		domicilioOrganizacion.setFechaCreacionElemento(new Date());
		domicilioOrganizacion.setEntreCalle1("entre calle organizacion 1");
		domicilioOrganizacion.setEntreCalle2("entre calle organizacion 2");
		domicilioOrganizacion.setLatitud("205.15");
		domicilioOrganizacion.setLongitud("14.25");
		domicilioOrganizacion.setAsentamientoDTO(new AsentamientoDTO());
		domicilioOrganizacion.setExpedienteDTO(expediente);
		domicilioOrganizacion.setCalidadDTO(calidadDom);
		
		organizacion.setNombreCorto("ORG");
		organizacion.setNombreOrganizacion("ORGANIZACION 1");
		organizacion.setNumeroActaConstitutiva("1234568");
		organizacion.setDireccionInternet("www.org1.com");
		organizacion.setAreaDeInfluencia("Metropolitana");
		organizacion.setFechaCreacionElemento(new Date());
		organizacion.setGiro("Financiero");
		organizacion.setPropositoCiberespacio("Desconocido");
		organizacion.setDomicilioDTO(domicilioOrganizacion);
		organizacion.setValorByComunidadVirtualVal(new ValorDTO(228L));
		organizacion.setValorByOrganizacionFormalVal(new ValorDTO(229L));
		organizacion.setValorBySectorGubernamentalVal(new ValorDTO(230L));
		organizacion.setValorByTipoOrganizacionVal(new ValorDTO(231L));
		organizacion.setExpedienteDTO(expediente);
		organizacion.setCalidadDTO(calidadOrg);
		
		try {
			OrganizacionDTO respuesta = service.ingresarOrganizacion(organizacion);
			assertTrue("El id de la Organizacion generada debe ser mayor a cero : ", respuesta.getElementoId()>0);
			logger.info("El id de la Organizacion generada debe ser mayor a cero : " + respuesta.getElementoId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
