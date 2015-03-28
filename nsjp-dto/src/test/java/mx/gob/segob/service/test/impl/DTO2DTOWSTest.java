/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.segob.service.test.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 *
 * @author sawbona
 */
public class DTO2DTOWSTest extends TestCase{

    public void testConversion(){
        List<Class> clasesExploradas = new LinkedList<Class>();
        Class<ExpedienteDTO> claseExpedienteDto = ExpedienteDTO.class;
        imprime(claseExpedienteDto, clasesExploradas);
    }

    private void imprime(Class clase, List<Class> clasesExploradas){
        if (clasesExploradas.contains(clase)) {
            return;
        }
        Field[] camposDeLaClase = clase.getDeclaredFields();
        String claseFormada = "public class " + clase.getSimpleName() + "{\n";
//        System.out.println("camposDeLaClase.length = " + camposDeLaClase.length);
        for (Field campo : camposDeLaClase) {
            campo.setAccessible(true);
            Type tipoDelCampo = campo.getGenericType();
            String nombreDelTipoDelCampo = tipoDelCampo.toString();
            claseFormada += "private " + tipoDelCampo + " " + campo.getName() + ";\n";
        }
        claseFormada += "}\n";
        System.out.println("" + claseFormada);
    }

}
