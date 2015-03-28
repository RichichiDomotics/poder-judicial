/**
* Nombre del Programa : NotaDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/07/2011
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
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.documento.NotaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Nota;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class NotaDAOImplTest extends BaseTestPersistencia<NotaDAO> {

	@Autowired
	private NotaDAO notaDAO;
	@Autowired
	private DocumentoDAO documentoDAO;
	
	public void testConsultarNotasPorDocumento(){
			Long idDocumento = 8L;
			List<Nota> lNotas = notaDAO.consultarNotasPorDocumento(idDocumento);	
			imprimirListaNotas(lNotas);
	}
	
	public void testConsultarNota(){
		Nota nota = notaDAO.read(1L);	
		imprimirNota(nota);
	}
	
	public void testRegistrarNota(){
		Nota nota = new Nota();
		//Consultar en BD que documento se le asociara la nota.
		Documento documento = documentoDAO.read(8L);
		nota.setDocumento(documento);
		
		nota.setFechaCreacion(new Date());
		nota.setDescripcion("Descripción de la nota....");
		nota.setNombreNota("Nombre de la nota");
		
		Long idNota = notaDAO.create(nota);
		logger.info(" Nueva nota " + idNota + " Asociada al documento con ID:" + documento.getDocumentoId());
	}
	
	
	private void imprimirListaNotas(List<Nota> lNotas){
		logger.info(" Lista de Notas #"+ lNotas.size());
		for (Nota nota : lNotas) {
			imprimirNota(nota);
		}
	}
	
	private void imprimirNota(Nota nota){
		logger.info(" Nota ID: "+ nota.getIdNota()+ 
				" nombre:" +nota.getNombreNota() +
				" Fecha Creacion:" + nota.getFechaCreacion() +
				" Fechar Actualizacion:" + nota.getFechaActualizacion() +
				" Descripcion:" + nota.getDescripcion() +
				" Documento: " + nota.getDocumento()
				);
		if ( nota.getDocumento()!= null)
			logger.info(" Documento id:"+nota.getDocumento().getDocumentoId()+
						" Documento Nombre:" +nota.getDocumento().getNombreDocumento()
			);
	}
}
