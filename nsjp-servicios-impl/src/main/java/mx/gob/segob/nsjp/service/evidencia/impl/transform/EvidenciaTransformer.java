/**
 * Nombre del Programa : EvidenciaTransformer.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.evidencia.impl.transform;

import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.evidencia.EstatusEvidencia;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonWSDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;
import mx.gob.segob.nsjp.service.caso.impl.transform.CasoTransformer;
import mx.gob.segob.nsjp.service.eslabon.impl.EslabonTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

import org.apache.log4j.Logger;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public class EvidenciaTransformer {

    private static final Logger logger = Logger
            .getLogger(EvidenciaTransformer.class);
    /**
     * 
     * @param evidencia
     * @param tranformarObjeto bandera para indicar si es necesario transformar el objeto.
     * @return
     */
    public static EvidenciaDTO transformarEvidencia(Evidencia evidencia,
            boolean tranformarObjeto) {
        EvidenciaDTO evidenciaDto = new EvidenciaDTO();
        if (evidencia == null) {
            return null;
        }
        CadenaDeCustodia cadenaDeCustodia = evidencia.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            evidenciaDto.setCadenaDeCustodia(CadenaDeCustodiaTransformer
                    .transformarCadenaDeCustodiaBasico(cadenaDeCustodia));
        }
        evidenciaDto.setCodigoBarras(evidencia.getCodigoBarras());
        evidenciaDto.setDescripcion(evidencia.getDescripcion());
        evidenciaDto.setEvidenciaId(evidencia.getEvidenciaId());
        evidenciaDto.setFechaLevantamiento(evidencia.getFechaLevantamiento());
        evidenciaDto.setNumeroEvidencia(evidencia.getNumeroEvidencia());
        if (tranformarObjeto) {
            Objeto objeto = evidencia.getObjeto();
            if (objeto != null) {
                evidenciaDto.setObjeto(ObjetoTransformer
                        .transformarObjeto(objeto));
            }
        }
        evidenciaDto.setOrigenEvidencia(evidencia.getOrigenEvidencia());
        Funcionario funcionario = evidencia.getFuncionario();
        if (funcionario != null) {
            FuncionarioDTO funcionarioDto = FuncionarioTransformer
                    .transformarFuncionario(funcionario);
            evidenciaDto.setFuncionario(funcionarioDto);
        }
        Funcionario funcionarioBaja = evidencia.getFuncionarioBaja();
        if (funcionarioBaja != null) {
            evidenciaDto.setFuncionarioBaja(FuncionarioTransformer
                    .transformarFuncionario(funcionario));
        }
        evidenciaDto.setMotivoBaja(evidencia.getMotivoBaja());
        Set<Eslabon> eslabones = evidencia.getEslabones();
        if (logger.isDebugEnabled()) {
            logger.debug("eslabones = " + eslabones.size());
        }
        Eslabon ultimoAsociado = null;
        int numEslabones = 0;
        for (Eslabon eslabon : eslabones) {
            if (numEslabones == 0) {
                ultimoAsociado = eslabon;
                numEslabones++;
            } else {
                if (eslabon.getNumeroEslabon() > ultimoAsociado
                        .getNumeroEslabon()) {
                    ultimoAsociado = eslabon;
                }
            }
            EslabonDTO eslabonDto = EslabonTransformer
                    .transformarEslabon(eslabon);
            evidenciaDto.getEslabones().add(eslabonDto);
        }
        if (ultimoAsociado != null) {
            evidenciaDto.setUltimoEslabon(EslabonTransformer
                    .transformarEslabon(ultimoAsociado));
        }
        evidenciaDto.setCantidad(0);
        if (evidencia.getEstatus() != null) {
            ValorDTO valorDTO = new ValorDTO(evidencia.getEstatus()
                    .getValorId());
            valorDTO.setValor(evidencia.getEstatus().getValor());
            evidenciaDto.setEstatus(valorDTO);

            if (evidencia.getEstatus().getValorId()
                    .equals(EstatusEvidencia.EN_ALMACEN.getValorId()))
                evidenciaDto.setCantidad(1);
        }
        if (evidencia.getDestinoLegal() != null) {
            ValorDTO valorDTO = new ValorDTO(evidencia.getDestinoLegal()
                    .getValorId());
            valorDTO.setValor(evidencia.getDestinoLegal().getValor());
            evidenciaDto.setDestinoLegal(valorDTO);
        }

        return evidenciaDto;
    }

    /**
     * 
     * @param evidenciaDto
     * @return
     */
    public static Evidencia transformarEvidencia(EvidenciaDTO evidenciaDto) {
        Evidencia evidencia = new Evidencia();
        // evidencia.setAudienciaEvidencias(evidenciaDto.getau);
        CadenaDeCustodiaDTO cadenaDeCustodia = evidenciaDto
                .getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            evidencia.setCadenaDeCustodia(CadenaDeCustodiaTransformer
                    .transformarCadenaDeCustodia(cadenaDeCustodia));
        }
        evidencia.setCodigoBarras(evidenciaDto.getCodigoBarras());
        //evidencia.setDescripcion(evidenciaDto.getDescripcion());
        evidencia.setDescripcion("-");
        // Transformamos los eslabones
        Set<EslabonDTO> eslabonesDto = evidenciaDto.getEslabones();
        if (eslabonesDto != null) {
            Set<Eslabon> eslabones = evidencia.getEslabones();
            for (EslabonDTO eslabonDTO : eslabonesDto) {
                Eslabon eslabon = EslabonTransformer
                        .transformarEslabon(eslabonDTO);
                eslabones.add(eslabon);
            }
            evidencia.setEslabones(eslabones);
        }
        evidencia.setEvidenciaId(evidenciaDto.getEvidenciaId());
        evidencia.setFechaLevantamiento(evidenciaDto.getFechaLevantamiento());
        evidencia.setNumeroEvidencia(evidenciaDto.getNumeroEvidencia());
        ObjetoDTO objetoDto = evidenciaDto.getObjeto();
        if (objetoDto != null) {
            evidencia.setObjeto(ObjetoTransformer.transformarObjeto(objetoDto));
        }
        evidencia.setOrigenEvidencia(evidenciaDto.getOrigenEvidencia());
        FuncionarioDTO funcionarioDTO = evidenciaDto.getFuncionario();
        if (funcionarioDTO != null) {
            Funcionario funcionario = FuncionarioTransformer
                    .transformarFuncionario(funcionarioDTO);
            evidencia.setFuncionario(funcionario);
        }
        FuncionarioDTO funcionarioBaja = evidenciaDto.getFuncionarioBaja();
        if (funcionarioBaja != null) {
            evidencia.setFuncionarioBaja(FuncionarioTransformer
                    .transformarFuncionario(funcionarioBaja));
        }
        evidencia.setMotivoBaja(evidenciaDto.getMotivoBaja());
        if (evidenciaDto.getEstatus() != null)
            evidencia.setEstatus(new Valor(evidenciaDto.getEstatus()
                    .getIdCampo()));
        if (evidenciaDto.getDestinoLegal() != null)
            evidencia.setDestinoLegal(new Valor(evidenciaDto.getDestinoLegal()
                    .getIdCampo()));

        return evidencia;
    }

    /**
     * 
     * @param fromBd
     * @return
     */
    public static EvidenciaDTO tranformarBasico(Eslabon row) {
        final EvidenciaDTO dto = new EvidenciaDTO();
        EslabonDTO eDto = null;

        ObjetoDTO obj = new ObjetoDTO();
        ExpedienteDTO exp = new ExpedienteDTO();
        exp.setCasoDTO(CasoTransformer.transformarCasoBasico(row.getEvidencia()
                .getCadenaDeCustodia().getExpediente().getCaso()));
        obj.setExpedienteDTO(exp);
        eDto = new EslabonDTO();
        eDto.setFuncionariEntrega(FuncionarioTransformer
                .transformarFuncionarioBasico(row.getFuncionarioEntrega()));
        CadenaDeCustodiaDTO ccdto = new CadenaDeCustodiaDTO();
        ccdto.setCadenaDeCustodiaId(row.getEvidencia().getCadenaDeCustodia()
                .getCadenaDeCustodiaId());
        ccdto.setFolio(row.getEvidencia().getCadenaDeCustodia().getFolio());
        dto.setCadenaDeCustodia(ccdto);
        if (row.getEvidencia().getObjeto().getAlmacen() != null) {
            AlmacenDTO almacendto = new AlmacenDTO();
            almacendto.setIdentificadorAlmacen(row.getEvidencia().getObjeto()
                    .getAlmacen().getIdentificadorAlmacen());
            obj.setAlmacen(almacendto);
        }
        dto.setObjeto(obj);

        return dto;
    }

    public static EvidenciaDTO transformarEvidencia(EvidenciaWSDTO input) {
        EvidenciaDTO evidencia = new EvidenciaDTO();

        evidencia.setCadenaDeCustodia(CadenaDeCustodiaTransformer
                .transformarCadenaDeCustodia(input.getCadenaDeCustodia()));
        evidencia.setCantidad(input.getCantidad());
        evidencia.setCodigoBarras(input.getCodigoBarras());
        evidencia.setDescripcion(input.getDescripcion());
        if (input.getIdDestinoLegal() != null) {
            evidencia.setDestinoLegal(new ValorDTO(input.getIdDestinoLegal()));
        }
        if (input.getEslabones() != null) {
            for (EslabonWSDTO eslabon : input.getEslabones()) {
                evidencia.getEslabones().add(
                        EslabonTransformer.transformarEslabon(eslabon));
            }
        }
        if (input.getIdEstatus() != null) {
            evidencia.setEstatus(new ValorDTO(input.getIdEstatus()));
        }
        evidencia.setFechaLevantamiento(input.getFechaLevantamiento());
        evidencia.setStrFuncionario(input.getFuncionario());
        evidencia.setStrFuncionarioBaja(input.getFuncionarioBaja());
        evidencia.setMotivoBaja(input.getMotivoBaja());
        evidencia.setNumeroEvidencia(input.getNumeroEvidencia());
        evidencia.setOrigenEvidencia(input.getOrigenEvidencia());
        if (input.getUltimoEslabon() != null) {
            evidencia.setUltimoEslabon(EslabonTransformer
                    .transformarEslabon(input.getUltimoEslabon()));
        }
        return evidencia;
    }

}
