/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.segob.nsjp.service.test;

import java.util.Date;
import java.util.HashSet;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase para crear los usuarios y demases utilidades para los test. DRY
 * @author sawbona
 */
public final class TestUtilServiceImpl {

    public static Funcionario nuevoFuncionarioPerito(){
        Funcionario peritoUno = new Funcionario();
        peritoUno.setNombreFuncionario("Cosme");
        peritoUno.setApellidoPaternoFuncionario("fulanito");
        peritoUno.setApellidoMaternoFuncionario("Simpson");
        peritoUno.setArea(new JerarquiaOrganizacional(1L));
        peritoUno.setTipoEspecialidad(new Valor(TipoEspecialidad.PERICIAL.getValorId()));
        return peritoUno;
    }

    public static Expediente nuevoExpediente(){
        Expediente expedienteCosme = new Expediente();
        expedienteCosme.setFechaCreacion(new Date());
        expedienteCosme.setFechaCierre(new Date());
        expedienteCosme.setDescNarrativa("monos a mi izquierda, monos a mi derecha...");
        expedienteCosme.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
        HashSet<NumeroExpediente> numeroExpedientes = new HashSet<NumeroExpediente>();
        
        expedienteCosme.setNumeroExpedientes(numeroExpedientes);
        return expedienteCosme;
    }

    public static String nuevoExpedienteUnico() {
        return "NSJYUCPROC20113333J";
    }

    public static String folioCadenaDeCustodiaConEvidencias() {
        return "cosme";
    }

}
