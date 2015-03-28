/**
 * 
 */
package mx.gob.segob.nsjp.dao.util.infra.interceptor;

import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;

/**
 * @author adrian
 *
 */
public enum AccionesBitacora {
	
	INGRESAR("Ingresar"), ACTUALIZAR("Actualizar"), ANULAR("Anular");
	
	private String nombre;

    private AccionesBitacora(String nom) {
        this.nombre = nom;
    }

    /**
     * Método de acceso al campo nombre.
     * 
     * @return El valor del campo nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public Short getShort(){
        return Short.valueOf((short) ordinal());
    }
    
    /**
     * Obtiene el nombre de mes.
     * 
     * @param ordinal
     * @return
     */
    public static String getNombre(int ordinal) {
        return EstatusCaso.values()[ordinal].getNombre();
    }

}
