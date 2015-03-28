
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.base.action.GenericAction"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.institucion.Areas"%>


<script type="text/javascript">


//Variable para controlar la carga del grid de expedientes
var cargaPrimeraExpPorEstatus = true;

/*
*Funcion que consulta los expedientes de acuerdo a su estatus y la fecha inicial y final
*/

function cargaGridExpedientesPorEstatus(estatus,fechaIni,fechaFin){
	if(cargaPrimeraExpPorEstatus == true){
		
		jQuery("#gridExpedientesPorEstatus").jqGrid({ 
			url:'<%=request.getContextPath()%>/obtenerSentenciasGrid.do', 
			datatype: "xml", 
			colNames:['Número De Caso','Número De Causa', 'Carpeta Ejecución', 'Nombre Sentenciado','Fecha De Creación'], 
			colModel:[ 	{name:'noCaso',index:'1', width:140}, 
						{name:'noCausa',index:'2', width:70}, 
						{name:'carpeta',index:'3', width:140}, 
						{name:'nombreSentenciado',index:'4', width:140},
						{name:'fechaCreacion',index:'5', width:70},
					],
			pager: jQuery('#pagerGridExpedientesPorEstatus'),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autowidth: true,
			height:360,
			sortname: 'Sentencia_id',
			caption:"Sentencias",
			autowidth:true,
			shrinkToFit:true,
			viewrecords: true,
			onSelectRow: function(id){

				},
			ondblClickRow: function(id) {
				consultarDatosGenerales(id);
				
			},
			sortorder: "asc"
		}).navGrid('#pagerGridExpedientesPorEstatus',{edit:false,add:false,del:false});
	
	}else{
		jQuery("#gridExpedientesPorEstatus").jqGrid('setGridParam', {url:'local',datatype: "xml" });
		$("#gridExpedientesPorEstatus").trigger("reloadGrid");
	}

	//Solo para pruebas
	var mydata = [ 
	      {id:"ST8768768NLIUOI",Expediente:"ST8768768NLIUOI",Fecha:"19-01-2012",Denunciante:"Eduardo Alvarado Díaz",Delito:"Violacíon",Origen:"Denuncia"}
	    ]; 
    for(var i=0;i<=mydata.length;i++)
	    jQuery("#gridExpedientesPorEstatus").jqGrid('addRowData',i+1,mydata[i]);
	//Termina solo para pruebas

}

function consultarDatosGenerales(id){
	customVentana("idVentanaDatosGenerales", "Datos Generales", "/consultarDatosGeneralesRS.do", "?sentenciaId="+id);
}


function creaVentanaDetenidos(expediente) {
	$.newWindow({id:"iframewindowDetenidos", statusBar: true, posx:0,posy:0,width:$(document).width(),height:$(document).height(),title:"Reinserci&oacute;n Social", type:"iframe"});
	$.maximizeWindow("iframewindowDetenidos");
    $.updateWindowContent("iframewindowDetenidos",'<iframe src="<%= request.getContextPath() %>/reinsercionSocial.do" width="100%" height="100%" />');		   	
}

function creaVentanaExpedietesPenaPrivativa(expediente) {
	$.newWindow({id:"iframewindowExpedietesPenaPrivativa", statusBar: true, posx:0,posy:0,width:$(document).width(),height:$(document).height(),title:"Reinserci&oacute;n Social", type:"iframe"});
	$.maximizeWindow("iframewindowExpedietesPenaPrivativa");
    $.updateWindowContent("iframewindowExpedietesPenaPrivativa",'<iframe src="<%= request.getContextPath() %>/consultarRS.do" width="100%" height="100%" />');		  	
}

function crearVentana(idVentana, titulo, url, parametros) {
	customVentana(idVentana, titulo, url, parametros);
}

function creaVentanaInventarioPertenencias() {
	
	jQuery("#dialog-validarSentenciaIdIP").dialog({
		autoOpen: false,
		height: 'auto',
		width:'auto',
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
				$( "#dialog:ui-dialog" ).dialog( "destroy" );
			}
		},
		resizable: false
	});
	
	var sentenciaId = jQuery("#gridExpedientesPorEstatus").jqGrid('getGridParam','selrow'); 
	if (sentenciaId) { 
		customVentana("iframewindowExpedietesPenaPrivativa", "Inventario de pertenencias", "/inventarioPertenencias.do", "?sentenciaId="+sentenciaId);
	} else { 
		jQuery("#dialog-validarSentenciaIdIP" ).dialog("open");
	}
}

</script>


	<div class="ui-layout-west">

		<div class="header">&nbsp;</div>

		<div class="content">
			<div id="accordionmenuprincipal">
				<h3>
					<a href="javascript:void(0)" onclick="regresaGrid()"> <img
						src="<%=request.getContextPath()%>/resources/images/icn_carpprincipal.png"
						id="botpenal" width="15" height="15">&nbsp;Expedientes de
						Pena Privativa </a>
				</h3>
				<div>
					<ul id="seccion1tree" class="filetree">
						<!--<li><span class='file'><a onclick='cargaGridVisitadores("+<%=Areas.UNIDAD_INVESTIGACION.ordinal()%>+","+$(this).find('idCampo').text()+")'>"+$(this).find('valor').text()+"</a></span></li>-->

						<li><span class='file'> <a
								onclick='cargaGridExpedientesPorEstatus("estatus","fechaini","fechafin")'>Nuevas</a>
						</span></li>

						<!--Carpeta para los sub estados-->
						<ul id="seccion2tree" class="filetree">
							<li class="closed"><span id="casos_at_penal" class="folder"
								onclick="activaDos()">Proceso</span>
								<ul>
									<li><span class='file'> <a
											onclick='cargaGridExpedientesPorEstatus("estatus","fechaini","fechafin")'>Por
												atender</a> </span></li>

									<li><span class='file'> <a
											onclick='cargaGridExpedientesPorEstatus("estatus","fechaini","fechafin")'>Atendidas</a>
									</span></li>
								</ul></li>
						</ul>
						<li><span class='file'> <a
								onclick='cargaGridExpedientesPorEstatus("estatus","fechaini","fechafin")'>Libertad</a>
						</span></li>

					</ul>
				</div>
				<h3>
					<a href="javascript:void(0)" onclick="creaVentanaInventarioPertenencias()"> <img
						src="<%=request.getContextPath()%>/resources/images/icn_carpprincipal.png"
						id="botpenal" width="15" height="15">&nbsp;Inventario de Pertenencias</a>
				</h3>		
				<div></div>		
			</div>
		</div>
		<!-- div class="footer">&nbsp;</div-->
	</div>
		<div id="dialog-validarSentenciaIdIP" title="Error de validaci&oacute;n">
		<p>
		Para consultar el inventario de pertenencias de una sentencia, es necesario seleccionar <br/> 
		la sentencia de la cual se consultar&aacute; el inventario de pertenencias asociado. <br/>
		Por favor seleccione un registro de la tabla de sentencias.
		</p>
	</div>