/**
 * Nombre del Programa : ConsultarDisponibilidadSalasServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.DiaDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.EspacioCalendarioDTO;
import mx.gob.segob.nsjp.dto.audiencia.MesDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SemanaDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.catalogo.DiaInhabilDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.service.audiencia.ConsultarDisponibilidadSalasService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.SalaAudienciaTransformer;
import mx.gob.segob.nsjp.service.catalogo.DiaInhabilService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarDisponibilidadSalasServiceImpl
        implements
            ConsultarDisponibilidadSalasService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(ConsultarDisponibilidadSalasServiceImpl.class);
//    TODO Meter a parametro de cofiguración estos 3 datos
    private final static int HORA_INICIAL_ATENCION = 9;
    private final static int HORA_FINAL_ATENCION = 15;
    private final static int ESPACIO = 30;

    @Autowired
    private SalaAudienciaDAO salaDao;
    @Autowired
    private DiaInhabilService diaInhabilService;

    @Override
    public MesDisponibilidadDTO consultarDisponibilidadSalas(
            MesDisponibilidadDTO filtro) throws NSJPNegocioException {

        Long limite = new Long(
                ((HORA_FINAL_ATENCION - HORA_INICIAL_ATENCION) * 60));

        List<Long> salasIds = salaDao.findAllId();

        if (logger.isDebugEnabled()) {
            logger.debug("filtro :: " + filtro.getMes() + ", "
                    + filtro.getAnio());
        }
        final MesDisponibilidadDTO resp = new MesDisponibilidadDTO();
        final Calendar diaActual  = Calendar.getInstance();
        diaActual.set(Calendar.HOUR_OF_DAY, 0);
        diaActual.set(Calendar.MINUTE, 1);
        final Calendar piv = Calendar.getInstance();
        piv.set(Calendar.HOUR_OF_DAY, 0);
        piv.set(Calendar.MINUTE, 1);
        piv.set(Calendar.MONTH, filtro.getMes().ordinal());
        piv.set(Calendar.YEAR, filtro.getAnio());
        piv.set(Calendar.DATE, 1);

        Calendar finMes = Calendar.getInstance();
        finMes.set(Calendar.MONTH, filtro.getMes().ordinal());
        finMes.set(Calendar.DATE, 1);
        finMes.add(Calendar.MONTH, 1);
        finMes.add(Calendar.DATE, -1);

        int diaSemana = getDiaSemanaInsititucional(piv
                .get(Calendar.DAY_OF_WEEK));
        int diasPorRellenarIzq = diaSemana - 1;
        logger.debug("pivote :: " + piv.getTime());
        logger.debug("diaSemana :: " + diaSemana);
        logger.debug("diasPorRellenarIzq :: " + diasPorRellenarIzq);

        // lo regresa al lunes
        piv.add(Calendar.DATE, -diasPorRellenarIzq);

        logger.debug("pivote :: " + piv.getTime());
        
        logger.debug("buscando dias inhabiles :: ");
        List<DiaInhabilDTO> diasInhabiles = diaInhabilService.consultarDiasInhabilesPorMes((short)(filtro.getMes().ordinal() + 1));
        logger.debug("se encontraron :: "+(diasInhabiles!=null?diasInhabiles.size()+" dias inhabiles :: ":" ninguno - lista nula"));

        int contSeman = 1;
        SemanaDisponibilidadDTO sem = new SemanaDisponibilidadDTO();
        DiaDisponibilidadDTO dia = null;
        while ((piv.get(Calendar.MONTH) <= filtro.getMes().ordinal() && piv
                .get(Calendar.YEAR) == filtro.getAnio())
                || ((piv.get(Calendar.MONTH) > filtro.getMes().ordinal() && piv
                        .get(Calendar.YEAR) < filtro.getAnio()))) {
            dia = new DiaDisponibilidadDTO();
            dia.setDia(piv.get(Calendar.DATE));

            if (piv.get(Calendar.MONTH) < filtro.getMes().ordinal()
                    || piv.get(Calendar.YEAR) < filtro.getAnio()) {
                dia.setHabil(false);
            } else {
            	Boolean bandera = false;
            	if(diasInhabiles != null && !diasInhabiles.isEmpty()){
            		logger.debug("diaSemana :: " + dia.getDia());
            		for(DiaInhabilDTO d: diasInhabiles){
            			if(d.getDia() == dia.getDia().shortValue()){
            				//el dia se encuentra en la lista
            				bandera = true;
            				logger.debug("es diaInhabil");
            			}
            		}
            	}
            	if(bandera == true){
            		//el dia esta en la lista de dias inhabiles
            		dia.setHabil(false);
            	}else{
            		// todos los dias son habiles
	                    dia.setHabil(true);
	                if (piv.equals(diaActual) || piv.after(diaActual)){    
	                    for (Long salaID : salasIds) {
	                        // consulta la disponibilidad de cada sala
	                        if (salaDao.existeDisponibilidad(salaID, piv.getTime(),
	                                limite)) {
	                            dia.setDisponible(true);
	                            break;
	                        }
	                    }
	                } else {
	                    dia.setHabil(false);
	                }
            	}
            }

            sem.addDia(dia);
            if (this.getDiaSemanaInsititucional(piv.get(Calendar.DAY_OF_WEEK)) == 7) {
                sem.setNoSemana(contSeman);
                resp.addSemana(sem);
                sem = new SemanaDisponibilidadDTO();
                contSeman++;
            }
            piv.add(Calendar.DATE, 1);// avanza el dia
        }
        logger.debug("sem.getPrimerDia() :: " + sem.getPrimerDia());
        if (sem.getPrimerDia() != 0) {
            sem.setNoSemana(contSeman);
            this.asignaDiasPosteriores(sem, filtro);
            resp.addSemana(sem);
        }
        return resp;
    }  
    @Override
    public DiaDisponibilidadDTO consultarDisponibilidadDiaSalas(
            DiaDisponibilidadDTO filtro) throws NSJPNegocioException {
        final DiaDisponibilidadDTO resp = new DiaDisponibilidadDTO();
        List<SalaAudiencia> salasFromBD = salaDao.consultarSalasMinimo();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, filtro.getMes().getAnio());
        cal.set(Calendar.MONTH, filtro.getMes().getMes().ordinal());
        cal.set(Calendar.DATE, filtro.getDia().intValue());

        Long limite = new Long(
                ((HORA_FINAL_ATENCION - HORA_INICIAL_ATENCION) * 60));
        long noCeldas = limite / ESPACIO;
        logger.debug("noCeldas :: " + noCeldas);
        List<Audiencia> auds = null;

        SalaAudienciaDTO sal = null;
        EspacioCalendarioDTO ev = null;
        EspacioCalendarioDTO prev = null;
        EspacioCalendarioDTO vacio = null;
        Calendar calAux = Calendar.getInstance();
        for (SalaAudiencia salaBD : salasFromBD) {
            sal = new SalaAudienciaDTO();
            sal.setSalaAudienciaId(salaBD.getSalaAudienciaId());
            sal.setNombreSala(salaBD.getNombreSala());
            auds = this.salaDao.consultarHoras(salaBD.getSalaAudienciaId(), cal.getTime());
            for (Audiencia au : auds) {
                ev = new EspacioCalendarioDTO();
                calAux.setTime(au.getFechaAudiencia());
                prev = sal.getUltimoEvento();
                int horaAud = calAux.get(Calendar.HOUR_OF_DAY);
                int minAud = calAux.get(Calendar.MINUTE);
                if (prev == null) {
                    if (HORA_INICIAL_ATENCION != horaAud
                            || (HORA_INICIAL_ATENCION == horaAud && minAud != 0)) {
                        vacio = new EspacioCalendarioDTO();
                        cal.set(Calendar.HOUR_OF_DAY, HORA_INICIAL_ATENCION);
                        cal.set(Calendar.MINUTE, 0);

                        long minRelIni = (cal.get(Calendar.HOUR_OF_DAY) * 60)
                                + cal.get(Calendar.MINUTE);
                        long minRelAud = (calAux.get(Calendar.HOUR_OF_DAY) * 60)
                                + calAux.get(Calendar.MINUTE);

                        long tamanioVacio = (minRelAud - minRelIni) / ESPACIO;
                        vacio.setHoraInicio(HORA_INICIAL_ATENCION);
                        // cal.get(Calendar.m)
                        vacio.setMinutoInicio(0);
                        vacio.setTamanio((int) tamanioVacio);
                        sal.addEvento(vacio);
                    }
                } else {

                    Calendar finEvAnt = Calendar.getInstance();
                    finEvAnt.set(Calendar.HOUR_OF_DAY, prev.getHoraInicio());
                    finEvAnt.set(Calendar.MINUTE, prev.getMinutoInicio());
                    finEvAnt.add(Calendar.MINUTE, prev.getDuracion());

                    long minRelAnt = (finEvAnt.get(Calendar.HOUR_OF_DAY) * 60)
                            + finEvAnt.get(Calendar.MINUTE);
                    long minRelAud = (calAux.get(Calendar.HOUR_OF_DAY) * 60)
                            + calAux.get(Calendar.MINUTE);

                    if (minRelAnt != minRelAud) {
                        vacio = new EspacioCalendarioDTO();
                        long tamanioVacio = (minRelAud - minRelAnt) / ESPACIO;
                        vacio.setHoraInicio(finEvAnt.get(Calendar.HOUR_OF_DAY));
                        vacio.setMinutoInicio(finEvAnt.get(Calendar.MINUTE));
                        vacio.setTamanio((int) tamanioVacio);
                        sal.addEvento(vacio);
                    }
                }

                ev.setHoraInicio(calAux.get(Calendar.HOUR_OF_DAY));
                ev.setMinutoInicio(calAux.get(Calendar.MINUTE));
                ev.setDuracion(au.getDuracionEstimada()!=null?au.getDuracionEstimada():0);
                ev.setTamanio((au.getDuracionEstimada()!=null?au.getDuracionEstimada():0)/ ESPACIO);
                ev.setId(au.getAudienciaId());
                sal.addEvento(ev);
            } // end for audiencias

            int espaciosLlenados = sal.getTotalEspacios();
            logger.debug("espaciosLlenados :: " + espaciosLlenados);

            if (espaciosLlenados == 0) {
                vacio = new EspacioCalendarioDTO();
                vacio.setHoraInicio(HORA_INICIAL_ATENCION);
                // cal.get(Calendar.m)
                vacio.setMinutoInicio(0);
                vacio.setTamanio((int) noCeldas);
                sal.addEvento(vacio);
            } else {
                if (espaciosLlenados < noCeldas) {
                    int vacioRestante = (int) noCeldas - espaciosLlenados;

                    Calendar finEvAnt = Calendar.getInstance();
                    finEvAnt.set(Calendar.HOUR_OF_DAY, ev.getHoraInicio());
                    finEvAnt.set(Calendar.MINUTE, ev.getMinutoInicio());
                    finEvAnt.add(Calendar.MINUTE, ev.getDuracion());

                    vacio = new EspacioCalendarioDTO();
                    vacio.setHoraInicio(finEvAnt.get(Calendar.HOUR_OF_DAY));
                    vacio.setMinutoInicio(finEvAnt.get(Calendar.MINUTE));
                    vacio.setTamanio((int) vacioRestante);
                    sal.addEvento(vacio);

                }
            }
            resp.addSala(sal);
        } // end for salas
        return resp;
    }
    /**
     * Convierte lo dias a la semana L-M-M-J-V-S-D donde Lunes = 1.
     * 
     * @param diaCalendar
     * @return
     */
    private int getDiaSemanaInsititucional(int diaCalendar) {
        int aux = diaCalendar - 1;
        if (aux > 0) {
            return aux;
        }
        return 7;
    }

    /**
     * 
     * @param sem
     * @param huecos
     * @param filtro
     */
    private void asignaDiasPosteriores(SemanaDisponibilidadDTO sem,
            MesDisponibilidadDTO filtro) {
        int pos = 1;
        DiaDisponibilidadDTO vacio = null;
        Calendar aux = Calendar.getInstance();
        aux.set(Calendar.MONTH, filtro.getMes().ordinal());
        aux.add(Calendar.MONTH, 1);
        aux.add(Calendar.DATE, -1);
        logger.debug("aux :: " + aux.getTime());
        int huecos = 7 - sem.getDias().size();
        while (pos <= huecos) {
            vacio = new DiaDisponibilidadDTO();
            vacio.setHabil(false);
            vacio.setDia(pos);
            sem.addDia(vacio);
            pos++;
        }
    }
    @Override
    public List<SalaAudienciaDTO> obtenerNombresSalas(UsuarioDTO usr)
            throws NSJPNegocioException {
    	
    	Long catDiscriminante=0L;
    	
		if (usr != null
				&& usr.getFuncionario() != null
				&& usr.getFuncionario().getDiscriminante() != null
				&& usr.getFuncionario().getDiscriminante()
						.getCatDiscriminanteId() != null) {
			
			catDiscriminante = usr.getFuncionario().getDiscriminante()
					.getCatDiscriminanteId();
		}
		
		List<SalaAudiencia> listaAud = salaDao.consultarNombresSalas(catDiscriminante);
		
		List<SalaAudienciaDTO> salaAudienciaDTO = new ArrayList<SalaAudienciaDTO>(); 
		
		for(SalaAudiencia salAud : listaAud){
			salaAudienciaDTO.add(SalaAudienciaTransformer.transformarSalaAudienciaDTOBasico(salAud));
		}
	
        return salaAudienciaDTO;
    }
}
