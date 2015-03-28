/**
 * Nombre del Programa : ConsultarIndicesEstructuradosServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jul-2011
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarIndicesEstructuradosService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author rgama
 */
public class ConsultarIndicesEstructuradosServiceImplTest
    extends BaseTestServicios<ConsultarIndicesEstructuradosService> {
	
	List<String> elementosIndiceEstructurado = new ArrayList<String>();

    public void testConsultarIndicesEstructuradosService(){
        try {
            logger.info("Probando el servicio de: ConsultarIndicesEstructuradosService");
            Long tipoOficio = 5L;
            List<IndiceEstructuradoDTO> loIndices = service.consultarIndicesEstructuradosXTipoOficio(tipoOficio);
            
            for (IndiceEstructuradoDTO indiceEstructuradoDTO : loIndices) {
//            	System.out.println("ID: " + indiceEstructuradoDTO.getIndiceEstructuradoId());
//            	System.out.println("Nombre:" + indiceEstructuradoDTO.getNombreEtiqueta());
//            	System.out.println("Texto: " + indiceEstructuradoDTO.getTextoEtiqueta());
//            	System.out.println("Nivel: " + indiceEstructuradoDTO.getNivel());            	
//            	System.out.println("TipoOficio: " + indiceEstructuradoDTO.getTipoOficio().getIdCampo() +
//            			indiceEstructuradoDTO.getTipoOficio().getValor());
//            	if(indiceEstructuradoDTO.getIndiceEstructuradoPadre() != null)
//            		System.out.println("IndiceEstructuradoPadre: " + indiceEstructuradoDTO.getIndiceEstructuradoPadre().getIndiceEstructuradoId());
            	System.out.println("-------------IndiceEstructruado ID: "+ indiceEstructuradoDTO.getIndiceEstructuradoId() +"--------------------------------------");
            	
            	
            	obtenElementosConFormato(indiceEstructuradoDTO);
            	ListIterator iter = elementosIndiceEstructurado.listIterator(elementosIndiceEstructurado.size());                

            	while (iter.hasPrevious())
            	      System.out.println(iter.previous());			
            	System.out.println("---------------------------------------------------");
            	elementosIndiceEstructurado = new ArrayList<String>();
			}
            
            System.out.println("---------------------------------------------------");
            System.out.println("::: El total de objetos recuperados " + loIndices.size()+ ":::");

        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarIndicesEstructuradosService");
        }
    }

	private void obtenElementosConFormato(
			IndiceEstructuradoDTO indiceEstructuradoDTO) {
		String cadenaIndiceEstructurado = "";		
		for(int i=1; i<= indiceEstructuradoDTO.getNivel(); i++)
			cadenaIndiceEstructurado=  cadenaIndiceEstructurado + "\t";
		cadenaIndiceEstructurado+= indiceEstructuradoDTO.getIndiceEstructuradoId() + " "  + indiceEstructuradoDTO.getNombreEtiqueta() + " "  + indiceEstructuradoDTO.getTextoEtiqueta();
		elementosIndiceEstructurado.add(cadenaIndiceEstructurado);		
		if(indiceEstructuradoDTO.getIndiceEstructuradoPadre() != null && indiceEstructuradoDTO.getIndiceEstructuradoPadre().getIndiceEstructuradoId()!= null)
			obtenElementosConFormato(indiceEstructuradoDTO.getIndiceEstructuradoPadre());
	}
   
}
