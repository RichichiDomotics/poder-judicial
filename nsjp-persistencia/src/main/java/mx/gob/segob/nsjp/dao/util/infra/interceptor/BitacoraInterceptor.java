/**
 * Nombre del Programa : BitacoraInterceptor.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 01/09/2011
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
package mx.gob.segob.nsjp.dao.util.infra.interceptor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.BitacoraConsulta;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.model.Usuario;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 * 
 * 
 * @version 1.0
 * @author GustavoBP
 * 
 */
@SuppressWarnings("serial")
public class BitacoraInterceptor extends EmptyInterceptor {

	private final static Logger logger = Logger
			.getLogger(BitacoraInterceptor.class);

	private Set<BitacoraLog> lBitacoraLog = new HashSet<BitacoraLog>();
	private Map<NumeroExpediente, BitacoraConsulta> bitacorasConsulta = new HashMap<NumeroExpediente, BitacoraConsulta>();

	/**
	 * Función que opera cuando se realiza una CONSULTA en la Base de Datos
	 * Se empleará sólo para Numero de Expediente INQ
	 */
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		// Consulta de Bitacora Consulta sobre expediente
		if (entity instanceof NumeroExpediente) {
			if (logger.isDebugEnabled())
				logger.debug("/**** onLoad ****/");

			// Datos para la bitacora
			NumeroExpediente numExp = (NumeroExpediente) entity;
			Date fechaConsulta = new Date();
			Boolean esPermitida = true;

			// Obtener el usuario de la paginación
			final PaginacionDTO pag = PaginacionThreadHolder.get();
			//Para el caso de que no se tenga el usuario en la paginación
			//o en su defecto en caso de que se hagan pruebas unitarias.
			Long usuarioId = 1L;	
			if (pag != null) {
				logger.info("PAGINA - USUARIO ID : " + pag.getUsuarioId());
				usuarioId = pag.getUsuarioId();
			}

			BitacoraConsulta bitacora = new BitacoraConsulta(fechaConsulta,
					esPermitida, numExp, new Usuario(usuarioId));
			bitacora.setNumeroExpedienteID((Long) id);
			bitacorasConsulta.put(numExp, bitacora);
		}
		return false;
	}

	/**
	 * Función que opera cuando se realiza una ACTUALIZACIÓN en Base de Datos
	 * Se empleará para IAM
	 */
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		Boolean esElementoABitacora = BitacoraLogUtil
				.esElementoABitacora(entity);
		if (esElementoABitacora) {
			if (logger.isDebugEnabled())
				logger.info("***onFlushDirty***");
			lBitacoraLog.addAll(BitacoraLogUtil.obtenerMovimientoBitacora(
					entity, id, currentState, previousState, propertyNames,
					types));
		}

		return false;
	}

	/**
	 * Función que opera cuando se realiza un GUARDADO en Base de datos
	 * Se empleará para IAM
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// Verificar si se va a guardar un elemento a revisar
		Boolean esElementoABitacora = BitacoraLogUtil
				.esElementoABitacora(entity);
		if (esElementoABitacora) {
			if (logger.isDebugEnabled())
				logger.info("***onSave***");
			
			 BitacoraLog bitacora=new BitacoraLog(entity, -1L, null,
			 null, "completo", null);
			 bitacora.setAccion(AccionesBitacora.INGRESAR);
			 lBitacoraLog.add(bitacora);
		}

		return false;
	}

	/**
	 * Función que opera después de un FLUSH y trae las entidades que han movido en la transacción
	 * Captura y determina que objetos de los nuevos ahora ya se tiene el identificador necesario y 
	 * llena todos antes de registrar en bitácora
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void postFlush(Iterator entities) {
		if(!lBitacoraLog.isEmpty()){
			logger.info("##### ENTRAMOS A POSTFLUSH ######");
			Set<BitacoraLog> bitacoraNuevos = obtenerNuevos(true);
			Set<BitacoraLog> bitacoraNoNuevos = obtenerNuevos(false);
			
			while (entities.hasNext()) {
				Object object = (Object) entities.next();
				if(!bitacoraNoNuevos.equals(object)){
					for (BitacoraLog bitLog : bitacoraNuevos) {
						if(bitLog.getId().equals(-1L)){
							Object entidad = bitLog.getEntity();
							if(object.getClass().equals(entidad.getClass())){
								lBitacoraLog.removeAll(bitacoraNuevos);
								Long id=1L;
								if(object instanceof Elemento){
									Elemento ele=(Elemento) object;
									id=ele.getElementoId();
								}else if(object instanceof Hecho){
									Hecho prop=(Hecho) object;
									id=prop.getHechoId();
								}else if(object instanceof Tiempo){
									Tiempo prop=(Tiempo) object;
									id=prop.getTiempoId();
								}else if(object instanceof CadenaDeCustodia){
									CadenaDeCustodia prop=(CadenaDeCustodia) object;
									id=prop.getCadenaDeCustodiaId();
								}else if(object instanceof Eslabon){
									Eslabon prop=(Eslabon) object;
									id=prop.getEslabonId();
								}else if(object instanceof Evidencia){
									Evidencia prop=(Evidencia) object;
									id=prop.getEvidenciaId();
								}else if(object instanceof Delito){
									Delito prop=(Delito) object;
									id=prop.getDelitoId();
								}else if(object instanceof DelitoPersona){
									DelitoPersona prop=(DelitoPersona) object;
									id=prop.getDelitoPersonaId();
								}else if(object instanceof NombreDemografico){
									NombreDemografico prop=(NombreDemografico) object;
									id=prop.getNombreDemograficoId();
								}
								
								bitLog.setId(id);
								BitacoraLog bitacora = new BitacoraLog(object,id, null, null,"completo", null);
								bitacora.setAccion(AccionesBitacora.INGRESAR);
								lBitacoraLog.add(bitacora);
							}
						}
					}
				}
			}
		}
	}

	private Set<BitacoraLog> obtenerNuevos(boolean quiereNuevos) {
		Set<BitacoraLog> result = new HashSet<BitacoraLog>();
		if (!lBitacoraLog.isEmpty()) {
			for (BitacoraLog bitLog : lBitacoraLog) {
				if (quiereNuevos) {
					if (bitLog.getAccion().equals(AccionesBitacora.INGRESAR)) {
						result.add(bitLog);
					}
				} else {
					if (!bitLog.getAccion().equals(AccionesBitacora.INGRESAR)) {
						result.add(bitLog);
					}
				}
			}
		}

		return result;
	}

	/**
	 * Función que opera cuando se ha concluido la transacción
	 * Registra las bitácoras
	 */
	public void afterTransactionCompletion(Transaction tx) {
		if (tx.wasCommitted()) {
			if (!lBitacoraLog.isEmpty()){
				logger.info("***afterTransactionCompletion***");
				logger.info("****Creations: wasCommitted");
				logger.info("****IAM");
				BitacoraLogUtil.registrarMovimientosBitacora(lBitacoraLog);
			}
			else if (!bitacorasConsulta.isEmpty()) {
				logger.info("***afterTransactionCompletion***");
				logger.info("****Creations: wasCommitted");
				logger.info("****INQ");
				BitacoraLogUtil.registrarBitacoraINQ(bitacorasConsulta);
			}
		}
	}
}
