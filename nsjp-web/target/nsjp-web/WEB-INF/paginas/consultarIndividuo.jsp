<!-- 
 Nombre del Programa 	: consultarIndividuo.jsp                                   
 Autor               	: Alejandro Galavíz                                           
 Compania            	: Ultrasist                                                

 Proyecto            	: NSJP                    Fecha: 07/03/2011 
 Marca de cambio        : N/A                                                     
 Descripcion General    : JSP que contiene la funcionalidad para consultar un individuo
 						  de acuerdo a su calidad y numero de expediente                      
 Programa Dependiente 	: N/A                                                      
 Programa Subsecuente 	: N/A                                                      
 Cond. de ejecucion     : N/A                                                      
 Dias de ejecucion      : N/A                             Horario: N/A       
                              MODIFICACIONES                                       
------------------------------------------------------------------------------           
 Autor                  : N/A                                                           
 Compania               : N/A                                                           
 Proyecto               : N/A                                   Fecha: N/A       
 Modificacion           : N/A                                                           
------------------------------------------------------------------------------
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consultar Individuo</title>

<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/css/jquery/jquery-ui-1.8.10.custom.css" rel="stylesheet" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/css/jquery/jqgrid/ui.jqgrid.css" />
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/consultarVictima.css" rel="stylesheet" />

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>

<!--Agregando libreria de JQuery-->
<script src="<%=request.getContextPath()%>/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js" type="text/javascript"></script>
<script src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<link href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" rel="stylesheet" type="text/css"/>


<script type="text/javascript">

	var reloadGrid = false;
	var $dialogo;
	
	//Carga las funciones correspondientes al iniciar la pagina
	$(document).ready(function(){
		cargaCalidades();
		
		PageInit(); 
		$("#consultar").bind("click",llenaGrid);
		
	});

	
	//Carga los tipos de calidad al iniciar la pagina
	function cargaCalidades() {
		 
		
	  $.ajax({
		  type: 'POST',
		  url: '<%= request.getContextPath()%>/consultarIndividuo.do',
		  data: '',
		  dataType: 'xml',
		  success: function(xml){
	    	  var option;
			  $(xml).find('calidades').each(function(){
				$('#calidadIndividuo').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcDescripcion').text() + '</option>');
			   });
		  }
		});
	}


	//Lena el grid con la consulta, pasa como parametros el numero de caso y 
	//la calidad del individuo
	  function llenaGrid(){
		  var noExpediente = $("#noExpediente").val();				//No de expediente
		  var selected = $("#calidadIndividuo option:selected");	//Calidad del individuo	  

		  if (reloadGrid) {
			  jQuery("#tblConsultaIndividuo").jqGrid('setGridParam', {postData:{noExpediente: noExpediente,calidadIndividuo:selected.val()}});
			  $("#tblConsultaIndividuo").trigger("reloadGrid"); 
		  } else {
			  reloadGrid = true;
			  jQuery("#tblConsultaIndividuo").jqGrid(
						{ url:'<%= request.getContextPath() %>/consultarCalidadIndividuo.do', 						
							datatype: "xml", 
							mtype: 'POST',						
							postData: {
								noExpediente: function()     { return noExpediente; },
								calidadIndividuo: function() { return selected.val(); }
							},
							colNames:['Nombre','A. Paterno', 'A. Materno'], 
							colModel:[ {name:'nombre',index:'nombre', width:150, sortable:false}, 
								{name:'apaterno',index:'apaterno', width:150, sortable:false}, 
								{name:'amaterno',index:'amaterno', width:150, sortable:false} 
								], 
								autowidth: true, 
								pager: jQuery('#pager'), 
								sortname: 'id', 
								rownumbers: true,
								gridview: true, 
								viewrecords: true, 
								sortorder: "desc", 
								height: "60%",
								caption:"Resultado de la B&uacute;squeda" 
						});
		  }
	  }

	//Funcion que realizara la llamada al metodo correspondiente
	//pasando como parametro el id del individuo seleccionado
	  function consultaDetalleIndividuo(idIndividuo,idNombreDemografico){
		  
		  $dialogo.dialog('option', 'modal', false).dialog('open'); 
		  pintarDatos(idIndividuo,idNombreDemografico); 
		  }

	  function PageInit() { 
		  $dialogo = $("#dialog").dialog({autoOpen: false,title: 'Consultar Víctima',
			  width: 700,
			  minWidth: 650,
			  maxWidth: 700,
			  height: 700,
			  show: "fold",
			  hide: "scale",
			  modal: false});
		  cargaInicialPagina();
		
		} 

	  function cargaInicialPagina()
		{
		    /*Inician llamados para creación de tabs de consultar victima*/
		    creaTab('ConsultaVictima','ConsultaVictima');
		}
	  function pintarDatos(idIndividuo,idNombreDemografico)
		{
			 $.ajax({
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/consultarVictima.do',
		    	  data: 'idIndividuo='+idIndividuo+'&idNombreDemografico='+idNombreDemografico,  
		    	  dataType: 'xml'
		    	});
			
		}
		
	  
</script>
</head>
<body>
<table width="650" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="650" height="550">
			<table width="650" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="650" height="20" class="celda"
						style="background: #000; text-align: center; font-weight: bold; color: #FFF;">Consultar
					Individuo <a onclick=""></a></td>
				</tr>
			</table>
			<table width="650" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="130">
					<table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center" width="600">
								<form>
									<hr></hr>
									No. Expediente <input name="noExpediente" id="noExpediente" type="text" size="10" />
									<br></br>
									<hr></hr>
									<select id="calidadIndividuo">
										<option value="seleccione">-Seleccione la Calidad del Individuo-</option>
									</select>
									<hr></hr>
									<br></br>
									<input name="consultar" id="consultar" type="button" value="Consultar" size="30" class="btn_Generico"/>
								</form>
								<table id="tblConsultaIndividuo"></table>
								<div id="pager" class="scroll" style="text-align:center;"></div>  
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div id="dialog">
	<jsp:include page="consultarVictima.jsp" />
</div>
</body>
</html>