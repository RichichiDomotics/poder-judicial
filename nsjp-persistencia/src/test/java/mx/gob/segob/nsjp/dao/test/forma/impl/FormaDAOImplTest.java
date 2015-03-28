package mx.gob.segob.nsjp.dao.test.forma.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Forma;

public class FormaDAOImplTest extends BaseTestPersistencia<FormaDAO> {

    // public void testRecuperarConsultarForma() {
    //
    // Forma forma = daoServcice.read(new Long(1));
    // assertFalse("El catálogo no debería estar vacío", forma == null);
    // logger.info("Cuerpo de la forma: " + forma.getCuerpo());
    // }
    //
    public void testConsultarPlantillaPorTipo() {
        Long tipoForma = TipoForma.RESOLUCION.getValorId();
        List<Forma> plantilla = daoServcice
                .consultarPlantillaPorTipo(tipoForma);

        for (Forma forma : plantilla) {
            logger.info("[" + forma.getFormaId() + " : " + forma.getNombre()
                    + "]");
        }

    }

    public void ConsultarFormaPorNombre() {
        Forma forma = daoServcice.consultarFormaPorNombre("Blanco");
        assertNotNull(forma);
        logger.info("id forma: " + forma.getFormaId());
    }

    /**
     * Prueba para generar el insert de las formas
     */
    public void testConsultarTodas() {
        List<Forma> formas = super.daoServcice.findAll("formaId", true);
        final StringBuffer inserts = new StringBuffer();
        for (Forma ff : formas) {
            inserts.append("insert into Forma(cNombre,TipoForma_val,cCuerpo,cDescForma) values ('");
            inserts.append(ff.getNombre());
            inserts.append("', ");
            inserts.append(ff.getTipoForma().getValorId());
            inserts.append(", '");
            inserts.append(ff.getCuerpo().replaceAll("\n", ""));
            inserts.append("', '");
            inserts.append(ff.getDescForma());
            inserts.append("')\n");
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        File f = new File("c:/logs/bd/formaInserts-pg-" + sdf.format(new Date())
                + ".sql");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write(inserts.toString());
            logger.info("Script [OK]");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                logger.warn(e.getMessage());
            }
        }
    }

}
