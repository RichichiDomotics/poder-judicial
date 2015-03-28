<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Visor Medidas Alternativas</title>

	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>

	<!--para controlar las ventanas-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript">
	var opOculta="";
	numeroCausa = "";
	$(document).ready(function() {
		opOculta='<%= request.getParameter("op")%>';
		numeroCausa = '<%= request.getParameter("numeroCausa")%>';

		//Carga el grid  de Involucrados por Causa
		cargaGridInvolucradosCausaPJENC(numeroCausa);

		$("#tabsprincipalconsulta" ).tabs();
	});
	

	var cargaGridInvolucradosMedidas=true;
	/**
	*Funcion que consulta los involucrados por causa
	*/
	function cargaGridInvolucradosCausaPJENC(numeroCausa){

		if(cargaGridInvolucradosMedidas == true){

			jQuery("#gridInvolucradosCausaPJENC").jqGrid(
					{ url:'<%= request.getContextPath() %>/consultarMedidasAlternativasPorNumeroExpediente.do?numeroExpedieteId='+numeroCausa+'', 						
						datatype: "xml",
						mtype: 'POST',						
						colNames:['Nombre','Medida Cautelar', 'Descripci&oacute;n','Periodo de Imposici&oacute;n','Periodicidad','Encargado Seguimiento'], 
						colModel:[ 	{name:'nombre',index:'nombre', width:200}, 
									{name:'medidaCautelar',index:'medidaCautelar', width:100}, 
									{name:'descripcionMedida',index:'descripcionMedida', width:150},
									{name:'periodoAplicacion',index:'periodoAplicacion', width:150}, 
									{name:'periodicidad',index:'periodicidad', width:150},
									{name:'encargadoSeguimiento',index:'encargadoSeguimiento', width:150},
								],
							autowidth: false,
							width:924,
							height: 200,
							pager: jQuery('#paginadorInvolucradosCausa'), 
							sortname: 'nombre', 
							rownumbers: false,
							gridview: false, 
							viewrecords: true, 
							sortorder: "desc",
							caption:"Administrar Medidas Alternativas",
							ondblClickRow: function(rowid) {
								mostrarVentanaMedidasAlternativas(rowid);
							} 
					}).navGrid('#paginadorInvolucradosCausa',{edit:false,add:false,del:false});
			
			cargaGridInvolucradosMedidas=false;
		}
		else{
			jQuery("#gridInvolucradosCausaPJENC").jqGrid('setGridParam', {url:'<%= request.getContextPath() %>/consultarMedidasAlternativasPorNumeroExpediente.do?numeroExpedieteId='+numeroCausa+'',datatype: "xml" });
			$("#gridInvolucradosCausaPJENC").trigger("reloadGrid");
		}
		
		
		$('#divGridInvolucradosCausaPJENC').show();
	}

	//Variable para controlar el id de la ventana
	var ventanaConsultaMedidaAlternativaPJADE=1;
	
	/*
	*Funcion que abre la ventana de medidas cautelares
	*/
	function mostrarVentanaMedidasAlternativas(rowid){

		ventanaConsultaMedidaAlternativaPJADE++;
		$.newWindow({id:"iframewindowMedidasAlternativas"+ventanaConsultaMedidaAlternativaPJADE, statusBar: true, posx:70,posy:20,width:850,height:420,title:"Ingresar/Modificar Medidas Alternativas", type:"iframe"});
    	$.updateWindowContent("iframewindowMedidasAlternativas"+ventanaConsultaMedidaAlternativaPJADE,'<iframe src="<%=request.getContextPath()%>/ingresarMedidaAlternativaPJADE.do?numeroExpedienteId='+numeroCausa+'&rowid=' + rowid +'" width="850" height="420" />'); 
	}

	
	//Variable para controlar el id de la ventana
	var ventanaMedidaAlternativaPJADE=1;

	/*
	*Funcion que abre la ventana de medidas alternativas
	*/
	function nuevaVentanaMedidasAlternativas(idInvolucrado){

		//var idInvolucrado =   rowid.split(",")[0];
		ventanaMedidaAlternativaPJADE++;
		var idVentana ="iframewindowMedidasAlternativas"+ventanaMedidaAlternativaPJADE; 
		
		var rowid= idInvolucrado+',';
		$.newWindow({id:"iframewindowMedidasAlternativas"+ventanaMedidaAlternativaPJADE, statusBar: true, posx:70,posy:20,width:850,height:420,title:"Ingresar/Modificar Medidas Alternativas", type:"iframe"});
    	$.updateWindowContent("iframewindowMedidasAlternativas"+ventanaMedidaAlternativaPJADE,'<iframe src="<%=request.getContextPath()%>/ingresarMedidaAlternativaPJADE.do?numeroExpedienteId='+numeroCausa+'&rowid=' + rowid +'&opOculta='+opOculta+'&idVentana='+idVentana+'" width="850" height="420" />'); 
	}

	/*
	*Funcion que es llamada en la ventana hijo para cerrarse
	*/
	function cerrarVentanaMedidasAlternativas(idVentana){
		$.closeWindow(idVentana);
	}


	/*
	*Funcion que valida que se haya seleccionado un imputado al cual
	*agregarle la medidad alternativa
	*/
	function validaSeleccion(){
		
		var rowid = jQuery("#gridAgregarMedidaAlternativa").jqGrid('getGridParam','selrow');
		
		if (rowid) {
			$("#divAgregarMedidaAlternativa").dialog("close");
			nuevaVentanaMedidasAlternativas(rowid);
		}else{
			alert("Por favor seleccione un renglon");
		} 
	}


	/*
	*Funcion que abre la modal para agregar una medida cautelar a un imputado
	*/
	function agregarMedidaAlternativa(){
		
		cargaGridImputados();
		$("#divAgregarMedidaAlternativa").dialog("open");
		$("#divAgregarMedidaAlternativa").dialog({ autoOpen: true, modal: true, 
		title: 'Agregar medida caultelar', 
		dialogClass: 'alert', position: [350,50],
		width: 800, height:480, maxWidth: 800, maxHeight:550,
		buttons:{
			"Agregar":function() {			  		
				validaSeleccion();
			},
			"Cancelar":function() {
				confirmar=confirm("¿Realmente desea salir?");
				if (confirmar){
						$(this).dialog("close");
				}				  		
			}
		}
		});
	}


	
	var banderaImputados = true;
	/**
	*Funcion que carga el grid con los imputados del expediente
	*/
	function cargaGridImputados(){

		if(banderaImputados == true){
			jQuery("#gridAgregarMedidaAlternativa").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarImputadosPorNumExpediente.do?numeroExpediente='+numeroCausa+'',
				data:'',
				datatype: "xml",
				colNames:['Nombre del Imputado'], 
				colModel:[ 					
				           	{name:'nombreImputado',index:'nombreImputado',width:350, align:'center'}, 
						],
				pager: jQuery('#pagerGridAgregarMedidaAlternativa'),
				rowNum:10,
				autoWidth:false,
				width:750,
				height:250,
				rowList:[25,50,100],
				sortname: 'nombreImputado',
				viewrecords: true,
				sortorder: "desc",
				caption:"Imputados"
			}).navGrid('#gridAgregarMedidaAlternativa',{edit:false,add:false,del:false});
			banderaImputados = false;
		}
		else{
			jQuery("#gridAgregarMedidaAlternativa").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarImputadosPorNumExpediente.do?numeroExpediente='+numeroCausa+'',datatype: "xml" });
			$("#gridAgregarMedidaAlternativa").trigger("reloadGrid");
		}
	}

	
	</script>
</head>
<body>

	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Medidas Alternativas</a></li>
		</ul>
		<div id="tabsconsultaprincipal-1">
			<div id="divGridInvolucradosCausaPJENC">
				<table id="gridInvolucradosCausaPJENC"></table>
				<div id="paginadorInvolucradosCausa"></div>
			</div>
			<div id="divBotonNuevaMedidaCautelar">
				<input type="button" align="right" value="Agregar Medida Alternativa" id="crearMedida" onclick="agregarMedidaAlternativa();" class="btn_Generico"/>
			</div>
		</div>
	</div>
	
	<!--Comienza div Relacionar Medios de Prueba-->
	<div id="divAgregarMedidaAlternativa" style="display: none">
		<table width="700" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="150">&nbsp;</td>
		    <td width="300">&nbsp;</td>
		    <td colspan="2">&nbsp;</td>
		  </tr>
		  <tr>
		    <td height="250" colspan="4">
		    	<table  id="gridAgregarMedidaAlternativa" width="100%"></table>
				<div id="pagerGridAgregarMedidaAlternativa"></div>
		    </td>
		  </tr>
		</table>
	</div>
	<!--Relacionar div Relacionar Medios de Prueba-->
</body>
</html>