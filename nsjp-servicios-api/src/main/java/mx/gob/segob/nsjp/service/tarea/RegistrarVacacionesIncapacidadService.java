/**
 * Nombre del Programa  : RegistrarVacacionesIncapacidadService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un periodo de vacaciones o incapacidad para 
 * 						  un funcionario
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.tarea;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

public interface RegistrarVacacionesIncapacidadService {

    /**
     * Registra un periodo de vacaciones o incapacidad para el funcionario definido por <code>funcionario</code>
     * @param funcionario Representa al funcionario al cual se le va a asociar el periodo 
     * 		 			  de vacacioines o incapacidad, es obligatorio que el atributo <code>claveFuncionario</code>
     * 					  contenga un valor diferente de <code>null</code>.
     * @param periodo Objeto de tipo EventoCitaDTO que describe el periodo de vacaciones o incapadiad que
     * 				  será registrado para el funcionario, los aributos obligatorios son 
     * 				  <code>fechaInicioEvento</code> Fecha en que inicia el periódo
     * 				  <code>fechaFinEvento</code> Fecha en que termina el periódo
     * 				  <code>tipoEvento</code> Tipo de Evento que registra el periódo Vacaiones o Incapacidad
     *  			  <code>nombreEvento</code> Nombre del periódo
     * @return identifador conn el que se registro el periodo en la BDD
     * @throws NSJPNegocioException
     */
	public void registrarVacacionesIncapacidad(FuncionarioDTO funcionario, EventoCitaDTO periodo, UsuarioDTO usuario)throws NSJPNegocioException;
}
