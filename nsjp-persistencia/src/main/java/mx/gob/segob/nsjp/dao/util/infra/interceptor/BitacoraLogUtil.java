/**
 * Nombre del Programa : BitacoraLogUtil.java
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.CategoriasElementos;
import mx.gob.segob.nsjp.comun.enums.elemento.TipoElemento;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.bitacora.BitacoraConsultaDAO;
import mx.gob.segob.nsjp.dao.bitacora.BitacoraMovimientoDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Aeronave;
import mx.gob.segob.nsjp.model.Animal;
import mx.gob.segob.nsjp.model.AreaGeografica;
import mx.gob.segob.nsjp.model.Arma;
import mx.gob.segob.nsjp.model.ArmaBlanca;
import mx.gob.segob.nsjp.model.BitacoraConsulta;
import mx.gob.segob.nsjp.model.BitacoraMovimiento;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.CategoriaElemento;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.DocumentoOficial;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Embarcacion;
import mx.gob.segob.nsjp.model.EquipoComputo;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.EspacioAereo;
import mx.gob.segob.nsjp.model.EspacioMaritimo;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Explosivo;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Joya;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Numerario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.ObraArte;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.Persona;
import mx.gob.segob.nsjp.model.PuntoCarretero;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.model.Sustancia;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vegetal;
import mx.gob.segob.nsjp.model.Vehiculo;

import org.apache.log4j.Logger;
import org.hibernate.type.Type;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author GustavoBP
 * 
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BitacoraLogUtil {
	private final static Logger logger = Logger
			.getLogger(BitacoraInterceptor.class);
	
	private static String SEPARADOR = " - "; 

	/**
	 * Método que registra los multiples movimientos de las actualizaciones y prepara los logs
	 * que se registrarán en la bitácora
	 * @param entity
	 * @param id
	 * @param currentState
	 * @param previousState
	 * @param propertyNames
	 * @param types
	 * @return
	 */
	public static Set<BitacoraLog> obtenerMovimientoBitacora(Object entity,
			Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (logger.isDebugEnabled())
			logger.info("*** obtenerMovimientoBitacora ***" + entity + "-ID:"
					+ id);

		Set<BitacoraLog> lBitLog = new HashSet<BitacoraLog>();

		// Determinar si cambio el objeto
		for (int i = 0; i < currentState.length; i++) {
			logger.info(" # " + i + " Prop: " + propertyNames[i] + " Actual:"
					+ previousState[i] + " Nuevo:" + currentState[i]);

			// Verificar si es de un tipo Primitivo, Wrapper o Valor
			if (esTipoBasicoOValor(types[i])) {
				logger.info(" # OK Validar si ha cambiado!");
				// Casos posibles para cambios
				if (previousState[i] == null && currentState[i] != null
						|| // Caso 1 P = null C!= null
						previousState[i] != null
						&& currentState[i] == null
						|| // Caso 2 P = null C!= null
						(previousState[i] != null && currentState[i] != null && !previousState[i]
								.equals(currentState[i]))) {

					// Se guarda temporalmente
					BitacoraLog bit = new BitacoraLog(entity, id,
							currentState[i], previousState[i],
							propertyNames[i], types[i]);
					
					bit.setAccion(AccionesBitacora.ACTUALIZAR);
					if(propertyNames[i].equals("esActivo"))
						bit.setAccion(AccionesBitacora.ANULAR);
					
					lBitLog.add(bit);
					logger.info("#### Se guarda temporalmente en BitLog:" + bit);
				}
			}
		}// Fin por cada atributo del objeto
			// imprimirParametros(entity, id, currentState, previousState,
			// propertyNames, types);
		logger.info("*** FIN registrarMovimientoBitacora ***" + entity + "-ID:"
				+ id);
		return lBitLog;
	}

	public static void registrarMovimientosBitacora(
			Set<BitacoraLog> bitacoraMovimientos) {
		if (logger.isDebugEnabled())
			logger.info("*** registrarMovimientosBitacora *** #"
					+ bitacoraMovimientos.size());

		// Se obtiene el bean de bitacoraConsultaDAO
		BitacoraMovimientoDAO bitacoraMovimientoDAO = (BitacoraMovimientoDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
				.getBean("bitacoraMovimientoDAO");
		//SCRZ Si no se tenia paginación
//		// Se obtiene el bean de numeroExpedienteDAO
//		NumeroExpedienteDAO numeroExpedienteDAO = (NumeroExpedienteDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//				.getBean("numeroExpedienteDAO");
//		// Se obtiene el bean de usuarioDAO
//		UsuarioDAO usuarioDAO = (UsuarioDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//				.getBean("usuarioDAO");
		
		// Obtener el usuario de la paginación
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		Long usuarioId = 1L;
		if (pag != null) {
			logger.info("PAGINA - USUARIO ID : " + pag.getUsuarioId());
			usuarioId = pag.getUsuarioId();
			PaginacionThreadHolder.set(pag);
		}
		Usuario usuario=new Usuario(usuarioId);
		NumeroExpediente numeroExpediente=new NumeroExpediente(1L);

		for (BitacoraLog bitacoraLog : bitacoraMovimientos) {
			// Obtener los valores

//			//SCRZ Si no se tenia paginacion
//			Long ExpId = obtenerExpedienteId(bitacoraLog.getEntity(),
//					bitacoraLog.getId());
//			NumeroExpediente numeroExpediente = numeroExpedienteDAO
//					.obtenerNumeroExpedienteXExpediente(ExpId);
//			logger.info("*** numeroExpediente: "+numeroExpediente.getNumeroExpedienteId());
//			Usuario usuario = usuarioDAO
//					.consultarUsuarioPorClaveFuncionario(numeroExpediente
//							.getFuncionario().getClaveFuncionario());
//			logger.info("*** Usuario: "+usuario );

			String accion = bitacoraLog.getAccion().getNombre();
			String campo = null;
			if (bitacoraLog.getPropertyNames() != null)
				campo = bitacoraLog.getPropertyNames();
			String valorNuevo = null;
			if (bitacoraLog.getCurrentState() != null)
				valorNuevo = bitacoraLog.getCurrentState() instanceof Valor ? ((Valor) bitacoraLog
						.getCurrentState()).getValorId().toString()
						: bitacoraLog.getCurrentState().toString();
			String valorAnterior = null;
			if (bitacoraLog.getPreviousState() != null)
				valorAnterior = bitacoraLog.getPreviousState() instanceof Valor ? ((Valor) bitacoraLog
						.getPreviousState()).getValorId().toString()
						: bitacoraLog.getPreviousState().toString();

			Long elemento = (Long) bitacoraLog.getId();
			logger.info("*** elemento bitacora: "+elemento );
			if(elemento== null){
				elemento=1L;
			}
			// CategoriaElemento categoriaElemento = new CategoriaElemento(1L);
			// String grupoDatos="grupoDatos";
			CategoriaElemento categoriaElemento = new CategoriaElemento(
					obtenerCategoriaElemento(bitacoraLog.getEntity()));
			String grupoDatos = obtenerGrupoDatos(bitacoraLog.getEntity());

			// Datos Registrar la bitacora de movimientos
			BitacoraMovimiento bitacoraMovimiento = new BitacoraMovimiento(
					new Date(), grupoDatos, accion, campo, valorAnterior,
					valorNuevo, numeroExpediente, usuario, elemento,
					categoriaElemento);
			
			Long idBitacoraMovimiento = bitacoraMovimientoDAO
					.create(bitacoraMovimiento);
			logger.info(" $$$$$%%%A Registrar a Bitacora Movimiento -  id: "
					+ idBitacoraMovimiento);
		}
	}

	/**
	 * Función que determina el grupoDatos
	 * @param entity
	 * @return
	 */
	private static String obtenerGrupoDatos(Object entity) {
		if (entity instanceof Elemento) {
			return generarCadenaElemento((Elemento) entity);
		}else{
			return entity.getClass().getCanonicalName();
		}
	}

	/**
	 * Función que determina la categoria del elemento
	 * 
	 * @param entity
	 * @return
	 */
	private static Long obtenerCategoriaElemento(Object entity) {
		Long categoria = 1L;
		
		// Se obtiene el bean de nombreDemograficoDAO
		NombreDemograficoDAO nombreDemograficoDAO = (NombreDemograficoDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
				.getBean("nombreDemograficoDAO");

		if (entity instanceof Hecho || entity instanceof Tiempo) {
			return CategoriasElementos.HECHO.getValorId();
		} else if (entity instanceof CadenaDeCustodia
				|| entity instanceof Evidencia || entity instanceof Eslabon) {
			return CategoriasElementos.CADENA_DE_CUSTODIA.getValorId();
		} else if (entity instanceof Delito || entity instanceof DelitoPersona) {
			return CategoriasElementos.DELITO.getValorId();
		} else if (entity instanceof NombreDemografico) {
			NombreDemografico demog=(NombreDemografico) entity;
			Persona persona = nombreDemograficoDAO.obtenerPersonaXDemografico(demog.getNombreDemograficoId());
			if(persona.getCalidad().getTipoCalidad().getValorId().equals(Calidades.DENUNCIANTE.getValorId()))
				return CategoriasElementos.DENUNCIANTE.getValorId();
			if(persona.getCalidad().getTipoCalidad().getValorId().equals(Calidades.VICTIMA_PERSONA.getValorId()))
				return CategoriasElementos.VICTIMA.getValorId();
			if(persona.getCalidad().getTipoCalidad().getValorId().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()))
				return CategoriasElementos.PROBABLE_RESPONSABLE.getValorId();
			if(persona.getCalidad().getTipoCalidad().getValorId().equals(Calidades.TESTIGO.getValorId()))
				return CategoriasElementos.TESTIGO.getValorId();
			if(persona.getCalidad().getTipoCalidad().getValorId().equals(Calidades.TRADUCTOR.getValorId()))
				return CategoriasElementos.TRADUCTOR_INTERPRETE.getValorId();
		}else if (entity instanceof Elemento) {
			return CategoriasElementos.OBJETOS_Y_EVIDENCIAS.getValorId();
		} 

		return categoria;
	}

	//SCRZ si no se tenia paginación
//	/**
//	 * Función que obtiene el número de expediente de un objeto
//	 * 
//	 * @param entity
//	 * @param id
//	 * @return
//	 */
//	private static Long obtenerExpedienteId(Object entity, Serializable id) {
//		Long numExp = 1L;
//
//		if (entity instanceof Hecho) {
//			// Se obtiene el bean de hechoDAO
//			HechoDAO hechoDAO = (HechoDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//					.getBean("hechoDAO");
//			Hecho hecho = hechoDAO.read((Long) id);
//			return hecho.getExpediente().getExpedienteId();
//		} else if (entity instanceof Elemento) {
//			// Se obtiene el bean de elementoDAO
//			ElementoDAO elementoDAO = (ElementoDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//					.getBean("elementoDAO");
//			Elemento elemento = elementoDAO.read((Long) id);
//			return elemento.getExpediente().getExpedienteId();
//		}
//		// CadenaDeCustodia
//		else if (entity instanceof CadenaDeCustodia) {
//			// Se obtiene el bean de cadenaDeCustodiaDAO
//			CadenaDeCustodiaDAO cadenaDeCustodiaDAO = (CadenaDeCustodiaDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//					.getBean("cadenaDeCustodiaDAO");
//			CadenaDeCustodia cadena = cadenaDeCustodiaDAO.read((Long) id);
//			return cadena.getExpediente().getExpedienteId();
//		}
//		// Delito
//		if (entity instanceof Delito) {
//			// Se obtiene el bean de delitoDAO
//			DelitoDAO delitoDAO = (DelitoDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//					.getBean("delitoDAO");
//			Delito delito = delitoDAO.read((Long) id);
//			return delito.getExpediente().getExpedienteId();
//		}
//
//		// Tiempo
//		// Evidencia
//		// Eslabon
//		// DelitoPersona
//		// NombreDemografico
//		return numExp;
//	}

	/**
	 * Método que permite identificar si el objeto operado es deseado en la
	 * bitácora
	 * 
	 * @param objeto
	 * @return
	 */
	public static Boolean esElementoABitacora(Object objeto) {
		Boolean permitido = false;

		// Objetos y Evidencias
		if (objeto instanceof Vehiculo || objeto instanceof Telefono
				|| objeto instanceof EquipoComputo
				|| objeto instanceof ArmaBlanca || objeto instanceof Arma)
			permitido = true;

		// Hecho
		if (objeto instanceof Hecho || objeto instanceof Lugar
				|| objeto instanceof Tiempo)
			permitido = true;

		// Cadena de Custodia
		if (objeto instanceof CadenaDeCustodia || objeto instanceof Eslabon
				|| objeto instanceof Evidencia)
			permitido = true;

		// Delito
		if (objeto instanceof Delito || objeto instanceof DelitoPersona)
			permitido = true;

		// Involucrados
		if (objeto instanceof Involucrado
				|| objeto instanceof NombreDemografico
				|| objeto instanceof Domicilio)
			permitido = true;

		return permitido;
	}

	/**
	 * Método que determina si el valor es de tipo básico
	 * 
	 * @param objetoEnActualizacion
	 * @return
	 */
	private static boolean esTipoBasicoOValor(Type objetoEnActualizacion) {
		// El valor de comparación es definida por el metodo de Type.getName el
		// cual regresa
		// el tipo primitivo o en su defecto el nombre de la entidad.
		return objetoEnActualizacion == null ? false : objetoEnActualizacion
				.getName().equals("string")
				|| objetoEnActualizacion.getName().equals("short")
				|| objetoEnActualizacion.getName().equals("int")
				|| objetoEnActualizacion.getName().equals("long")
				|| objetoEnActualizacion.getName().equals("float")
				|| objetoEnActualizacion.getName().equals("long")
				|| objetoEnActualizacion.getName().equals("double")
				|| objetoEnActualizacion.getName().equals("boolean")
				|| objetoEnActualizacion.getName().equals("char")
				|| objetoEnActualizacion.getName().equals("timestamp")
				|| objetoEnActualizacion.getName().equals(
						Valor.class.getCanonicalName());
	}

	// private static void imprimirParametros(Object entity, Serializable id,
	// Object[] currentState,
	// Object[] previousState, String[] propertyNames, Type[] types){
	// logger.info(" Imprimir Parametros " +
	// "Entity:" + entity + " -ID:" + id +
	// " -currentState:" + (currentState==null? currentState :
	// currentState.length )+
	// " -previousState:"+ (previousState==null? previousState:
	// previousState.length )+
	// " -propertyNames:" + (propertyNames==null? propertyNames:
	// propertyNames.length )+
	// " -types:" + (types==null? types: types.length ) );
	// if( currentState!= null )
	// for (Object objeto : currentState) {
	// logger.info("***CurrentState:"+ objeto);
	// if(objeto != null && objeto.getClass()!= null)
	// logger.info("***State-ClassName:"+ objeto.getClass().getName());
	// }
	// if(previousState!= null)
	// for (Object objeto : previousState) {
	// logger.info("***PreviousState:"+ objeto);
	// if(objeto != null && objeto.getClass()!= null)
	// logger.info("***State-ClassName:"+ objeto.getClass().getName());
	// }
	// if( propertyNames!= null)
	// for (String property : propertyNames) {
	// logger.info("***propertyNames:"+ property);
	// }
	// if(types!=null)
	// for (Type typ : types) {
	// logger.info("***types:"+ typ.getName());
	// logger.info("***types:"+ typ.getReturnedClass());
	// }
	// }

	/**
	 * Operación que registra las consultas de Numero Expediente
	 * 
	 * @param bitacorasConsulta
	 */
	public static void registrarBitacoraINQ(
			Map<NumeroExpediente, BitacoraConsulta> bitacorasConsulta) {
		if (logger.isDebugEnabled())
			logger.info("*** REGISTRAR LA BITACORA INQ***");

		List<Long> ids = new ArrayList<Long>();

		// Se obtiene el bean de bitacoraConsultaDAO
		BitacoraConsultaDAO bitacoraConsultaDAO = (BitacoraConsultaDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
				.getBean("bitacoraConsultaDAO");
		
		//SCRZ si no se tenía de paginacion
//		// Se obtiene el bean de numeroExpedienteDAO
//		NumeroExpedienteDAO numeroExpedienteDAO = (NumeroExpedienteDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//				.getBean("numeroExpedienteDAO");
//		// Se obtiene el bean de usuarioDAO
//		UsuarioDAO usuarioDAO = (UsuarioDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
//				.getBean("usuarioDAO");

		Set<Entry<NumeroExpediente, BitacoraConsulta>> set = bitacorasConsulta
				.entrySet();
		for (Entry<NumeroExpediente, BitacoraConsulta> entry : set) {
			BitacoraConsulta bitacora = entry.getValue();
			
			//SCRZ Si no se tenia de paginación
//			if (bitacora.getUsuario().getUsuarioId().equals(0L)) {
//				NumeroExpediente NExp = numeroExpedienteDAO.read(bitacora
//						.getNumeroExpedienteID());
//				bitacora.setUsuario(usuarioDAO
//						.consultarUsuarioPorClaveFuncionario(NExp
//								.getFuncionario().getClaveFuncionario()));
//			}
			ids.add(bitacoraConsultaDAO.create(bitacora));
		}

		if (logger.isDebugEnabled()) {
			for (Long id : ids) {
				logger.info("*** Se registró: " + id);
			}
		}
	}
	
	private static String generarCadenaElemento(Elemento elemento){
		List<NombreDemografico> lNombreDemografico = null;
		String cadena = "";
		
		// Se obtiene el bean de nombreDemograficoDAO
		NombreDemograficoDAO nombreDemograficoDAO = (NombreDemograficoDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
				.getBean("nombreDemograficoDAO");
		

		   //Settear Calidad
		  if(elemento.getCalidad()!=null && elemento.getCalidad().getTipoCalidad()!= null)
			  cadena = elemento.getCalidad().getTipoCalidad().getValor() + SEPARADOR ;
		  
		  if ( elemento.getTipoElemento().getValorId().equals(TipoElemento.PERSONA.getValorId())  )
		  {
			  logger.info("********  PERSONA ********");
		  
			  lNombreDemografico = nombreDemograficoDAO.consutarNombresByInvolucrado(elemento.getElementoId()); 
			  if (lNombreDemografico!= null && !lNombreDemografico.isEmpty() ){
				  cadena = cadena + lNombreDemografico.get(0).getNombre() + " " 
				  		 + lNombreDemografico.get(0).getApellidoPaterno() + " "
				  		 + lNombreDemografico.get(0).getApellidoMaterno();
			  }
			  
		  }else if(elemento.getTipoElemento().getValorId().equals(TipoElemento.ORGANIZACION.getValorId())){
			  logger.info("********  ORGANIZACION ********");
			// Se obtiene el bean de organizacionDAO
				OrganizacionDAO organizacionDAO = (OrganizacionDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
						.getBean("organizacionDAO");
				// Se obtiene el bean de relacionDAO
				RelacionDAO relacionDAO = (RelacionDAO) mx.gob.segob.nsjp.dao.util.infra.impl.ApplicationContextAwareNSJPImpl.springApplicationContext
						.getBean("relacionDAO");
				
			  Organizacion organizacion=  organizacionDAO.read( elemento.getElementoId() );
			  if ( organizacion != null){
				  logger.info("Nombre Organizacion" + organizacion.getNombreOrganizacion() );
				  cadena = organizacion.getNombreOrganizacion() + SEPARADOR +  cadena + " ";
				  
				  //Obtener el representante Legal
				  //BD 30	Representante Legal
//				  cadena = SEPARADOR
				  List<Relacion> relaciones  = relacionDAO.obtenerRelacionSimple(organizacion.getElementoId(), new Long(Relaciones.REPRESENTANTE_LEGAL.ordinal()));
				  //Se toma un solo representante legal
				  if(relaciones!= null && !relaciones.isEmpty()){
					  logger.info(" Representante Legal " + relaciones.get(0).getRelacionId() );
					  logger.info(" Representante Legal " + relaciones.get(0).getElementoByComplementoId().getElementoId() );
					  logger.info(" Representante Legal " + relaciones.get(0).getElementoBySujetoId().getElementoId());
					  
					  //Obtner el representante legal.
					  lNombreDemografico = nombreDemograficoDAO.consutarNombresByInvolucrado(relaciones.get(0).getElementoByComplementoId().getElementoId()); 
		    		  if (lNombreDemografico!= null && !lNombreDemografico.isEmpty() ){
		    			  cadena = cadena +  lNombreDemografico.get(0).getNombre() + " " 
		    			  		 + lNombreDemografico.get(0).getApellidoPaterno() + " "
		    			  		 + lNombreDemografico.get(0).getApellidoMaterno();
		    			  logger.info(" Calidad: " + relaciones.get(0).getElementoBySujetoId().getCalidad().getTipoCalidad().getValor());
		    		  }
				  }
			  }
		  }else if (elemento.getTipoElemento().getValorId().equals(TipoElemento.LUGAR.getValorId())){
			  logger.info("********  LUGAR ********");
			  cadena = "";
			  if(elemento instanceof Domicilio){
				  Domicilio loObjeto = (Domicilio)elemento;
				  cadena = cadena + "Domicilio" + SEPARADOR;
				  cadena = (loObjeto.getCalle() != null ? cadena + loObjeto.getCalle() + SEPARADOR:cadena);
				  cadena = (loObjeto.getNumeroExterior() != null ? cadena + loObjeto.getNumeroExterior() + SEPARADOR:cadena);
				  cadena = (loObjeto.getNumeroInterior() != null ? cadena + loObjeto.getNumeroInterior() + SEPARADOR:cadena);
				  if(loObjeto.getAsentamiento() != null){
					  cadena = (loObjeto.getAsentamiento().getNombreAsentamiento() != null ? cadena + loObjeto.getAsentamiento().getNombreAsentamiento() + SEPARADOR:cadena);
					  cadena = (loObjeto.getAsentamiento().getCodigoPostal() != null ? cadena + loObjeto.getAsentamiento().getCodigoPostal() + SEPARADOR:cadena);        			  
				  }    			 
			  }
			  if(elemento instanceof PuntoCarretero){
				  PuntoCarretero loObjeto = (PuntoCarretero)elemento;
				  cadena = cadena + "Punto Carretero" + SEPARADOR;
				  cadena = (loObjeto.getNombreCarretera() != null ? cadena + loObjeto.getNombreCarretera() + SEPARADOR:cadena);
				  cadena = (loObjeto.getKilometro() != null ? cadena + loObjeto.getKilometro() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof AreaGeografica){
				  AreaGeografica loObjeto = (AreaGeografica)elemento;   			  
				  cadena = cadena + "Área Geográfica" + SEPARADOR;
				  cadena = (loObjeto.getNombre() != null ? cadena + loObjeto.getNombre() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof EspacioMaritimo){
				  EspacioMaritimo loObjeto = (EspacioMaritimo)elemento;
				  cadena = cadena + "Espacio Marítimo" + SEPARADOR;
				  cadena = (loObjeto.getZonaCostera() != null ? cadena + loObjeto.getZonaCostera() + SEPARADOR:cadena);
				  cadena = (loObjeto.getPuerto() != null ? cadena + loObjeto.getPuerto() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof EspacioAereo){
				  EspacioAereo loObjeto = (EspacioAereo)elemento;
				  cadena = cadena + "Espacio Aéreo" + SEPARADOR;
				  cadena = (loObjeto.getLineaAerea() != null ? cadena + loObjeto.getLineaAerea() + SEPARADOR:cadena);
				  cadena = (loObjeto.getRuta() != null ? cadena + loObjeto.getRuta() + SEPARADOR:cadena);
			  }
			  if(cadena.lastIndexOf(SEPARADOR) != -1)
				  cadena = cadena.substring(0, cadena.lastIndexOf(SEPARADOR));
		  }else { 
			  logger.info("********  OBJETO ********");
			  cadena = "";
			  if(elemento instanceof Vehiculo){
				  Vehiculo loObjeto = (Vehiculo)elemento;
				  cadena = cadena + "Vehículo" + SEPARADOR;
				  cadena = (loObjeto.getValorByTipoVehiculo() != null ? cadena + loObjeto.getValorByTipoVehiculo().getValor() + SEPARADOR:cadena);
				  cadena = (loObjeto.getPlaca() != null ? cadena + loObjeto.getPlaca() + SEPARADOR:cadena);    			  
			  }
			  if(elemento instanceof EquipoComputo){
				  EquipoComputo loObjeto = (EquipoComputo)elemento;
				  cadena = cadena + "Equipo de Cómputo" + SEPARADOR;
				  cadena = (loObjeto.getTipoEquipo() != null ? cadena + loObjeto.getTipoEquipo().getValor() + SEPARADOR:cadena);    			  
			  }
			  if(elemento instanceof Arma){
				  Arma loObjeto = (Arma)elemento;   			  
				  cadena = cadena + "Arma" + SEPARADOR;
				  cadena = (loObjeto.getTipoArma() != null ? cadena + loObjeto.getTipoArma().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Explosivo){
				  Explosivo loObjeto = (Explosivo)elemento;
				  cadena = cadena + "Explosivo" + SEPARADOR;
				  cadena = (loObjeto.getTipoExplosivo() != null ? cadena + loObjeto.getTipoExplosivo().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Aeronave){
				  Aeronave loObjeto = (Aeronave)elemento;    			  
				  cadena = cadena + "Aeronave" + SEPARADOR;
				  cadena = (loObjeto.getTipoAeroNave() != null ? cadena + loObjeto.getTipoAeroNave().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Animal){
				  Animal loObjeto = (Animal)elemento;
				  cadena = cadena + "Animal" + SEPARADOR;
				  cadena = (loObjeto.getTipoAnimal() != null ? cadena + loObjeto.getTipoAnimal().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof DocumentoOficial){
				  DocumentoOficial loObjeto = (DocumentoOficial)elemento;
				  cadena = cadena + "Documento Oficial" + SEPARADOR;
				  cadena = (loObjeto.getTipoDocumento() != null ? cadena + loObjeto.getTipoDocumento().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Embarcacion){
				  Embarcacion loObjeto = (Embarcacion)elemento;
				  cadena = cadena + "Embarcación" + SEPARADOR;
				  cadena = (loObjeto.getTipoEmbarcacion() != null ? cadena + loObjeto.getTipoEmbarcacion().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Joya){
				  Joya loObjeto = (Joya)elemento;
				  cadena = cadena + "Joya" + SEPARADOR;
				  cadena = (loObjeto.getTipoJoya() != null ? cadena + loObjeto.getTipoJoya().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Numerario){
				  Numerario loObjeto = (Numerario)elemento;
				  cadena = cadena + "Numerario" + SEPARADOR;
				  cadena = (loObjeto.getMoneda() != null ? cadena + loObjeto.getMoneda() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof ObraArte){
				  ObraArte loObjeto = (ObraArte)elemento;    			  
				  cadena = cadena + "Obra de Arte" + SEPARADOR;
				  cadena = (loObjeto.getTipoObraArte() != null ? cadena + loObjeto.getTipoObraArte().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Sustancia){
				  Sustancia loObjeto = (Sustancia)elemento;
				  cadena = cadena + "Sustancia" + SEPARADOR;
				  cadena = (loObjeto.getTipoSustancia() != null ? cadena + loObjeto.getTipoSustancia().getValor() + SEPARADOR:cadena);
			  }
			  if(elemento instanceof Vegetal){
				  Vegetal loObjeto = (Vegetal)elemento;
				  cadena = cadena + "Vegetal" + SEPARADOR;
				  cadena = (loObjeto.getTipoVegetal() != null ? cadena + loObjeto.getTipoVegetal().getValor() + SEPARADOR:cadena);
			  }    		  
			  if(cadena.lastIndexOf(SEPARADOR) != -1)
				  cadena = cadena.substring(0, cadena.lastIndexOf(SEPARADOR));
		  }
		   return cadena;
		}

}
