/**
* Nombre del Programa : GraficaDenunciaVSTipoDelitoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para obtener la informacion de la Grafica DenunciaVSTipoDelito
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
package mx.gob.segob.nsjp.service.graficacion.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.service.graficacion.GraficaDenunciaVSTipoDelitoService;

/**
 * Implementacion del servicio para obtener la informacion de la Grafica DenunciaVSTipoDelito.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Transactional
@Service
public class GraficaDenunciaVSTipoDelitoServiceImpl implements
		GraficaDenunciaVSTipoDelitoService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaDenunciaVSTipoDelitoServiceImpl.class);
	
	@Autowired
	ExpedienteDAO expedienteDAO;
	@Autowired
	DelitoDAO delitoDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.graficacion.GraficaDenunciaVSTipoDelitoService#expedientesPorMes(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Object[]> expedientesPorMes(Date fechaInicial, Date fechaFin) {
		
		List<Object[]> result = expedienteDAO.obtenerExpedientesPorMes(fechaInicial, fechaFin); 						
		
		for (Object[] objects : result) {
			convierteMes(objects);
		}
		
		return result;
	}

	@Override
	public List<Object[]> delitosTipoPorMes(Date fechaInicial, Date fechaFin,
			boolean tipo) {
		
		List<Object[]> result = delitoDAO.obtenerTipoDelitoPorMes(fechaInicial, fechaFin, tipo);
		
		for (Object[] objects : result) {
			convierteMes(objects);
		}
		
		return result;
	}
	
	public Object[] convierteMes (Object[] objects) {
		switch ((Integer) objects[0]) {
		case 1:
			objects[0]="enero";
			break;
		case 2:
			objects[0]="febrero";
			break;
		case 3:
			objects[0]="marzo";
			break;
		case 4:
			objects[0]="abril";
			break;
		case 5:
			objects[0]="mayo";
			break;
		case 6:
			objects[0]="junio";
			break;
		case 7:
			objects[0]="julio";
			break;
		case 8:
			objects[0]="agosto";
			break;
		case 9:
			objects[0]="septiembre";
			break;
		case 10:
			objects[0]="octubre";
			break;
		case 11:
			objects[0]="noviembre";
			break;
		case 12:
			objects[0]="diciembre";
			break;
							
		default:
			break;
		}
		return objects;	
	}
	
	

}
