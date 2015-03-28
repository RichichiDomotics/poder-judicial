<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Asignar Evidencia</title>
	
		<!--CSS DE LA PAGINA-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	
		<!--SCRIPTS DE LA PAGINA-->

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">

	var firstGridObjetosAsignarEvidencias = true;
	var firstGridPeritosAsignarEvidencias = true;

	var solicitudPericialId;
	var folioCadenaCustodia;
	
	$(document).ready(function() {

		folioCadenaCustodia='<%= request.getParameter("folioCadenaCustodia")%>';
		solicitudPericialId=<%= request.getParameter("solicitudPericialId")%>;

		//Crea las tabs del visor
		$("#tabsPrincipalAsignarEvidencia").tabs();
		//Carga el grid con las evidencias
		cargaGridObjetosAsignarEvidencias(folioCadenaCustodia,solicitudPericialId);
		cargaFechaCaptura();
		consultaDatosUsuario();
		consultaDatosDeSolicitud();
		//$("#btnAsignar").click(cerrarVentana);
	});
	
	function cerrarVentana(){
		parent.cerrarVentanaAsignacionEvidencia();
	}

	
////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA SOLICITANTE/////////////////////////////////////////////
	
	/*
	*Funcion que consulta la fecha actual 
	*/
	function cargaFechaCaptura(){
		
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#fechaElaboracionAsignarEvidencia').val($(xml).find('fechaActual').text());
    			
    		}
		});
    }

	/*
	*Funcion que consulta los datos de la solicitud, nombre del usuario solicitante, puesto y area administrativa
	*/
	function consultaDatosUsuario(){
		
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/consultarDatosUsuario.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    	    	var errorCode;
    	    	
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					//Nombre completo del funcionario solicitante
					var nombreUsuarioLogeado = $(xml).find('nombreFuncionario').first().text()+ " "
					 + $(xml).find('apellidoPaternoFuncionario').first().text()+ " "
					 + $(xml).find('apellidoMaternoFuncionario').first().text(); 
					$('#nombreUsuarioAsignarEvidencia').val(nombreUsuarioLogeado);
					//Puesto del usuario solicitante
					$('#puestoUsuarioAsignarEvidencia').val($(xml).find('puesto').find('valor').first().text());
					//Area administrativa
					$('#areaAdminAsignarEvidencia').val($(xml).find('departamento').find('area').find('nombre').first().text());					
    			}
    		}
		});
    }
////////////////////////////////////TERMINA FUNCIONALIDAD DE LA CEJA SOLICITANTE////////////////////////////////////////////////////////
	 
////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA ASIGNCION DE EVIDENCIA/////////////////////////////////////////////

	/*
	*Funcion que carga el grid con las evidencias
	*/
	function cargaGridObjetosAsignarEvidencias(folioCadenaCustodia,solicitudPericialId){

		if(firstGridObjetosAsignarEvidencias == true){
			
			jQuery("#gridObjetosAsignarEvidencias").jqGrid({
				
				url:'<%= request.getContextPath()%>/consultarEvidenciasDeCadenaDeCustodia.do?folioCadenaCustodia='+folioCadenaCustodia+'&solicitudPericialId='+solicitudPericialId+'', 
				datatype: "xml", 
				colNames:['N&uacute;mero de Evidencia','Tipo de Objeto','Objeto','Descripci&oacute;n','Código de Barras','Nombre del Perito' ], 
				colModel:[ 	
							{name:'NumeroDeEvidencia',index:'numeroDeEvidencia', width:130},
							{name:'TipoObjeto',index:'tipoObjeto', width:130},
				           	{name:'Objeto',index:'objeto', width:130},
				           	{name:'Descripcion',index:'descripcion', width:200},
				           	{name:'CodigoBarras',index:'codigoBarras', width:130},
				           	{name:'NombrePerito',index:'nombrePerito', width:200}
						],
				pager: jQuery('#pagerGridObjetosAsignarEvidencias'),
				rowNum:5,
				rowList:[10,20,30],
				autowidth: true,
				//autoheight:true,
				width:"100%",
				height:150,
				sortname: 'numeroDeEvidencia',
				viewrecords: true,
				sortorder: "desc",
				ondblClickRow: function(rowid) {
					mostrarListaDePeritos(rowid);
				}
			}).navGrid('#pagerGridObjetosAsignarEvidencias',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridObjetosAsignarEvidencias=false;
		}
		else{
			jQuery("#gridObjetosAsignarEvidencias").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarEvidenciasDeCadenaDeCustodia.do?folioCadenaCustodia='+folioCadenaCustodia+'&solicitudPericialId='+solicitudPericialId+'',datatype: "xml" });
			$("#gridObjetosAsignarEvidencias").trigger("reloadGrid");			
		}
	}
	

	//Funcion que despliega una ventana modal con la lista de peritos a los que les puede ser asignado el objeto seleccionado
	  function mostrarListaDePeritos(rowID){
		
		//obtenemos los identificadores de la cadena de custodia y del perito asignado
		//identificadores[0] = idCadenaDeCustodia
		//identificadores[1] = idPeritoAsignado
		//identificadores[2] = idEvidencia

		var identificadores = rowID.split(",");
		
		//verificamos si ya tiene un perito asignado
		if( identificadores[1]== ""){

			//Cargamos el grid de peritos y lanzamos la ventana modal
			cargaGridPeritosAsignarEvidencias(identificadores[0],solicitudPericialId);
			$("#divVentanaModalPeritosAsignarEvidencia").dialog("open");
			$("#divVentanaModalPeritosAsignarEvidencia").dialog({ autoOpen: true, 
				modal: true, 
			  	title: 'Seleccione un perito', 
			  	dialogClass: 'alert',
			  	position: [500,100],
			  	width: 475,
			  	height: 400,
			  	maxWidth: 500,
			  	buttons:{"Asignar":function() {

			  		//obtenemos el id del perito seleccionado en el grid de peritos
			  		var peritoId = jQuery("#gridPeritoAsignarEvidencias").jqGrid('getGridParam','selrow');
			  		if(peritoId){
			  		//guardamos la asociacion de la evidencia y el perito
			  			guardaAsignacionEvidencia(identificadores[2],peritoId,solicitudPericialId);
			  			$(this).dialog("close");							
				  	}
			  		else{
						alertDinamico("Seleccione un perito");
					}
			  	},
			  	"Cancelar":function() {
			  		$(this).dialog("close");
			  	}
			  }
			});
		}
		else {
			//Se muestra un mensaje indicando que esa evidencia ya tiene un perito designado 
			alertDinamico("La evidencia ya fue asignada a un perito");
		}	  	
	}

   /*
	*Funcion que carga el grid con las solicitudes periciales no atendidas
	*/
	function cargaGridPeritosAsignarEvidencias(idCadenaDeCustodia,solicitudPericialId){

		if(firstGridPeritosAsignarEvidencias == true){
			
			jQuery("#gridPeritoAsignarEvidencias").jqGrid({ 
				url:'<%= request.getContextPath()%>/consultarPeritosLibresDeEvidencia.do?folioCadenaCustodia='+idCadenaDeCustodia+'&solicitudPericialId='+solicitudPericialId+'', 
				datatype: "xml", 
				colNames:['Nombre','Especialidad','Institución' ], 
				colModel:[ 	{name:'Nombre',index:'nombre', width:250},
				           	{name:'Especialidad',index:'especialidad', width:150},
				           	{name:'Institucion',index:'institucion', width:150},
						],
				pager: jQuery('#pagerGridPeritoAsignarEvidencias'),
				rowNum:10,
				rowList:[10,20,30],
				//autowidth: true,
				//autoheight:true,
				width:450,
				height:240,
				sortname: 'nombre',
				viewrecords: true,
				sortorder: "desc"
			}).navGrid('#pagerGridPeritoAsignarEvidencias',{edit:false,add:false,del:false});

			//cambia la variable a falso para ya no dibujar el grid, solo recargarlo
			firstGridPeritosAsignarEvidencias=false;
		}
		else{
			jQuery("#gridPeritoAsignarEvidencias").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarPeritosLibresDeEvidencia.do?idCadenaDeCustodia='+idCadenaDeCustodia+'&solicitudPericialId='+solicitudPericialId+'',datatype: "xml" });
			$("#gridPeritoAsignarEvidencias").trigger("reloadGrid");			
		}
	}


	/*Funcion que guarda la asociacion del perito con la evidencia*/
	function guardaAsignacionEvidencia(evidenciaId,peritoId,solicitudPericialId){

		$.ajax({			
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/guardaAsignacionEvidencia.do?evidenciaId='+evidenciaId+'&peritoId='+peritoId+'&solicitudPericialId='+solicitudPericialId+'',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					//refrescamos el grid de evidencias para que aparesca el perito que fue ya designado
					jQuery("#gridObjetosAsignarEvidencias").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarEvidenciasDeCadenaDeCustodia.do?folioCadenaCustodia='+folioCadenaCustodia+'&solicitudPericialId='+solicitudPericialId+'',datatype: "xml" });
					$("#gridObjetosAsignarEvidencias").trigger("reloadGrid");
    			}
    		}
    	});
	}
////////////////////////////////////TERMINA FUNCIONALIDAD DE LA CEJA ASIGNCION DE EVIDENCIA////////////////////////////////////////////////////////////////////

////////////////////////////////////COMIENZA FUNCIONALIDAD DE LA CEJA AVISAR///////////////////////////////////////////////////////////////////////////////////

	/*
	*Funcion que consulta los datos de la solicitud, nombre del usuario solicitante, puesto y area administrativa
	*/
	function consultaDatosDeSolicitud(){
		
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/consultaDetalleSolicitudPericial.do?solicitudPericialId='+solicitudPericialId+'',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    	    	var errorCode;
    	    	
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					//Nombre completo del funcionario solicitante
					var nombreFuncionario = $(xml).find('usuarioSolicitante').find('nombreFuncionario').first().text()+ " "
					 + $(xml).find('usuarioSolicitante').find('apellidoPaternoFuncionario').first().text()+ " "
					 + $(xml).find('usuarioSolicitante').find('apellidoMaternoFuncionario').first().text(); 
					$('#avisarAsignarEvidencia').val(nombreFuncionario);									
    			}
    		}
		});
    }
////////////////////////////////////TERMINA FUNCIONALIDAD DE LA CEJA AVISAR///////////////////////////////////////////////////////////////////////////////////
    
	</script>
</head>
<body>

	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
	
<div id="tabsPrincipalAsignarEvidencia">
	
	<ul>
		<li><a href="#tabsAsignarEvidencia-0">Solicitante</a></li>
		<li><a href="#tabsAsignarEvidencia-1">Asignación de Evidencia</a></li>
		<li><a href="#tabsAsignarEvidencia-2">Dar Aviso A</a></li>
	</ul>
	
	<!--	Comienza tab Solicitante-->
	<div id="tabsAsignarEvidencia-0" style="height: 400">
	
		<table width="500" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="250">&nbsp;</td>
		    <td width="200">&nbsp;</td>
		    <td width="50">&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"><strong>Nombre del Servidor Público:</strong></td>
		    <td align="left"><input type="text" id="nombreUsuarioAsignarEvidencia" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"><strong>Cargo:</strong></td>
		    <td align="left"><input type="text" id="puestoUsuarioAsignarEvidencia" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"><strong>Área Administrativa:</strong></td>
		    <td align="left"><input type="text" id="areaAdminAsignarEvidencia" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"><strong>Fecha Elaboración:</strong></td>
		    <td align="left"><input type="text" id="fechaElaboracionAsignarEvidencia" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		</table>
			
	</div>
	<!--	Termina tab Solicitante-->
	
	<!--	Comienza tab Asignacion de evidencia-->
	<div id="tabsAsignarEvidencia-1">
	
		<table width="600" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="600"><strong>Evidencias:</strong></td>
		  </tr>
		  <tr>
		    <td>
		    	<!--Espacio para el grid con las solicitudes periciales terminadas o cerradas-->
				<div id="divGridObjetosAsignarEvidencias">
					<table id="gridObjetosAsignarEvidencias"></table>
					<div id="pagerGridObjetosAsignarEvidencias"></div>
				</div>		    
		    </td>
		  </tr>
		</table>
		
		<!--div para la ventana modal en donde se muestra la confirmacion de la asignacion-->
		<div id="divVentanaModalPeritosAsignarEvidencia" style="display: none">
			<table id="gridPeritoAsignarEvidencias"></table>
		    <div id="pagerGridPeritoAsignarEvidencias"></div>		
		</div>
	</div>
	<!--	Termina tab asignacion de evidencia-->
	
	<!--	Comienza tab Dar aviso a:-->
	<div id="tabsAsignarEvidencia-2" style="height: 400">
	
		<table width="500" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="250">&nbsp;</td>
		    <td width="200">&nbsp;</td>
		    <td width="50">&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="left">
		    	<strong>Avisar a:</strong>
		    	<input type="text" id="avisarAsignarEvidencia" style="width:250px; border: 0; background:#DDD;" readonly="readonly"/>
		    </td>
		    <td align="left"></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"></td>
		    <td align="left"></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"></td>
		    <td align="left"></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right"></td>
		    <td align="left"></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		</table>
		
	</div>
	<!--	Termina tab Dar aviso a:-->
</div>
<!--	Termina tab principal-->
</body>
</html>