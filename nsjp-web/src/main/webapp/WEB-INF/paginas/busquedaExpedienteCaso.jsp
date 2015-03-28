<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<% ( (mx.gob.segob.nsjp.dto.usuario.UsuarioDTO)request.getSession().getAttribute("KEY_SESSION_USUARIO_FIRMADO") ).setAreaActual(new mx.gob.segob.nsjp.dto.institucion.AreaDTO(mx.gob.segob.nsjp.comun.enums.institucion.Areas.ATENCION_TEMPRANA_DEFENSORIA));%>
	<script type="text/javascript">

	var numeroExpedienteId = "";
	var numeroExpedienteSt = "";
	function muestrasolicitud(){
		parent.resBuscarCaso();
	}

	function muestraSolicitudAsociada(){	
		parent.registrarSolicitudAsociada(numeroExpedienteId,numeroExpedienteSt);
	}

	function muestrabusqueda(){
		if($('#rbCaso').is(':checked')){
			$(".window-container .window-closeButton").click();
			buscarCaso();
		}else if($('#rbExpediente').is(':checked')){
			$(".window-container .window-closeButton").click();
			buscarExpediente();
		}
	}
		
	function buscarExpediente() {
		$.newWindow({id:"iframewindowBuscarExpediente", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Expediente", type:"iframe"});
		$.updateWindowContent("iframewindowBuscarExpediente",'<iframe src="<%= request.getContextPath() %>/buscarExpediente.do?tipo=1" width="653" height="400" />');		
	}
		
	function buscarCaso() {
		$.newWindow({id:"iframewindowBuscarCaso", statusBar: true, posx:255,posy:110,width:653,height:400,title:"Buscar Caso", type:"iframe"});
		$.updateWindowContent("iframewindowBuscarCaso",'<iframe src="<%= request.getContextPath() %>/buscarCaso.do" width="653" height="400" />');		
	}
	
	function cerrarEtapa(idExp,numExp){
		idExpediente=idExp;	
		numExpediente=numExp;	
		$.closeWindow('iframewindowBuscarExpediente');
		jQuery("#gridDetalleExpediente").jqGrid({ 
		    url: '<%= request.getContextPath()%>/consultarProbablesResponsables.do?idExpediente='+idExpediente+'',
			datatype: "xml", 
			colNames:['Expediente','Nombre'], 
			colModel:[ {name:'expediente',index:'expediente', width:300, align:"center"},
					   {name:'nombre',index:'nombre', width:400, align:"center"}  
				    ],
		     rowNum:10, 
		     rowList:[10,20,30],
		     autowidth: true,
			 width:"100%",
			 height:38,
		     pager: '#pagerExpediente',
		     sortname: 'nombre',
		     viewrecords: true,
		     gridview: true, 
		     caption: "Número de expediente del defensor asociado  " +numExpediente, 
		     sortorder: "desc"	,
		     onSelectRow: function (rowid){		
		    	 var def = jQuery("#gridDetalleExpediente").jqGrid('getRowData',rowid);
		    	 numeroExpedienteId = rowid;
		    	 numeroExpedienteSt = def.expediente;
		    	 $("#registrarSolicitudAsociada").removeAttr('disabled');
		     },
			loadComplete: function(xml){
	    		var noAsignado = $(xml).find('string').text();
			     if(noAsignado == "El expediente aún no tiene defensor designado, favor de verificar"){
			    	 customAlert(noAsignado);
			     }
			}   	    
		});
		$('#gridDetalleExpediente').trigger('reloadGrid');						   
	}	

</script>
</head>
<body bgcolor="#DDDDDD">
<div>
	</br>
	</br>
	</br>
	<table align="center">
	<tr align="center">
		<td colspan="4" style=" width : 665px;">&nbsp;</td>
	</tr>
	<tr align="center">
		<td colspan="4" style=" width : 665px;">&nbsp;</td>
	</tr>
	<tr align="center">
  		<td colspan="4" style=" width : 665px;">Seleccione un tipo de b&uacute;squeda para relacionar informaci&oacute;n  de un caso o expediente a la solicitud ciudadana: </br>
    	</br>
    	</br></td>
	</tr>
		<tr align="center">
  		<td colspan="4" style=" width : 665px;">&nbsp;</td>
	</tr>
	<tr>
		<td width="25%" align="right">Caso</td> <td width="25%"><input type="radio" name="rbBusqueda" id="rbCaso" onchange=" muestrabusqueda();" align="left"></td><td width="25%" align="right">Expediente</td><td width="25%"> <input type="radio" name="rbBusqueda" id="rbExpediente" align="left"  onchange=" muestrabusqueda()" ></td>
	</tr>
	<tr>
  		<td colspan="4" align="right">&nbsp;</td>
  	</tr>
	<tr>
  		<td colspan="4" align="center">
  			<table id="gridDetalleExpediente"></table >
  			<div id="pagerExpediente"></div>
  		</td>
	</tr>
	<tr>
  		<td colspan="4" align="center"><input type="button" id="registrarSolicitudAsociada" value="Registrar solicitud ciudadana asociada al expediente o caso seleccionado" onclick="muestraSolicitudAsociada()" disabled="disabled" class="btn_Generico"></td>
	</tr>
	<tr>
  		<td colspan="4" align="right">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4" align="center"><span>En caso de no contar con informaci&oacute;n de un caso o expediente, puede registrar directamente la solicitud: </span></br>
    	</br>
    	</br>
    	<input type="button" id="registrarNuevaSolicitudCiudadana" value="Registrar nueva solicitud ciudadana de defensor" onclick="muestrasolicitud()" class="btn_Generico"></td>
	</tr>
	</table>
	<center>
	</br>
	</br>
	</center>
</div>
</body>
</html>