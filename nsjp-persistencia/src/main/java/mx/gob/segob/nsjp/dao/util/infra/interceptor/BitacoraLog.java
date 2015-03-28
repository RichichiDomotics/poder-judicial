/**
* Nombre del Programa : BitacoraLog.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/09/2011
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

import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.type.Type;

/**
 * Esta Clase permite recopilar la información que será almacenada en la bitacora.
 * El objetivo es almacenar temporalmente, durante toda la sessión de la transacción
 * que será almacenada en la Bitacora, con el objetivo de hacer demasiados accesos a BD.
 * 
 * @version 1.0
 * @author GustavoBP
 */
public class BitacoraLog implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Object entity;
	Serializable id;
	Object currentState;
	Object previousState;
	String propertyNames;
	Type types;
	private AccionesBitacora accion;
	
	/**
	 * @param entity
	 * @param id
	 * @param currentState
	 * @param previousState
	 * @param propertyNames
	 * @param types
	 */
	public BitacoraLog(Object entity, Serializable id, Object currentState,
			Object previousState, String propertyNames, Type types) {
		super();
		this.entity = entity;
		this.id = id;
		this.currentState = currentState;
		this.previousState = previousState;
		this.propertyNames = propertyNames;
		this.types = types;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			 return false;
		if (obj == this)
			return true;
		if (!(obj instanceof BitacoraLog))
			return false;
		BitacoraLog bitLog = (BitacoraLog) obj;
		
		if( !bitLog.getId().equals(this.id) || 
				!bitLog.getClass().getName().equals( this.getClass().getName()) ||
				!bitLog.getPropertyNames().equals( this.getPropertyNames())
				)
			return false;
			
		return true;
	}


	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + Integer.parseInt(this.id.toString());
		result = 31 * result + (  this.getClass()!= null? this.getClass().getName().hashCode() : 0 );  
		result = 31 * result + (  this.getPropertyNames()!= null? this.getPropertyNames().hashCode() : 0 );
		return result;
	}

	@Override
	public String toString() {

		return  " BitacoraLog.toString(): " +
				"Entity:" + entity + " -ID:" + id + 
				" -currentState:" + (currentState instanceof Valor ? ((Valor)currentState).getValorId() : currentState )  +
				" -previousState:"+ (previousState instanceof Valor ? ((Valor)previousState ).getValorId() : previousState )+ 
				" -propertyNames:" + propertyNames +
				" -types:" +  (types!= null?  types.getName(): types) ; 
	}




	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	public Serializable getId() {
		return id;
	}
	public void setId(Serializable id) {
		this.id = id;
	}
	public Object getCurrentState() {
		return currentState;
	}
	public void setCurrentState(Object currentState) {
		this.currentState = currentState;
	}
	public Object getPreviousState() {
		return previousState;
	}
	public void setPreviousState(Object previousState) {
		this.previousState = previousState;
	}
	public String getPropertyNames() {
		return propertyNames;
	}
	public void setPropertyNames(String propertyNames) {
		this.propertyNames = propertyNames;
	}
	public Type getTypes() {
		return types;
	}
	public void setTypes(Type types) {
		this.types = types;
	}

	public void setAccion(AccionesBitacora accion) {
		this.accion = accion;
	}

	public AccionesBitacora getAccion() {
		return accion;
	}

}
