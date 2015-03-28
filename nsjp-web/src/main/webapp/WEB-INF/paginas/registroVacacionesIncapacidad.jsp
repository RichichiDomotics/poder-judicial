<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro de Vacaciones e Incapacidad</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estiloBoton.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript">

	var idFuncionario = null;
	var validaFecha = false;
	
		$(document).ready(function(){
						
			  jQuery("#gridDefensoresRegistrados").jqGrid({ 
				    url: '<%= request.getContextPath()%>/consultarDefensoresActivos.do',
					datatype: "xml", 
					colNames:['Nombre','Apellido Paterno','Apellido Materno'], 
					colModel:[ {name:'nombre',index:'nombre', width:200, align:"center"},
							   {name:'aPaterno',index:'aPaterno', width:200, align:"center"},
							   {name:'aMaterno',index:'aMaterno', width:300, align:"center"}
							  					     
							    ],
				     rowNum:10, 
				     rowList:[10,20,30],
				     autowidth: true,
				     pager: '#pagered',
				     sortname: 'id',
				     viewrecords: true,
				     gridview: true, 
				     caption: "Lista de Defensores", 
				     sortorder: "desc"	,
				     onSelectRow: function (idrow){

					        registraActividad(idrow);

					     }		     			     
				    
					  });

			// $('#enviarAviso').click(enviarAvisoDetencion);
			  
			  $("#fechaInicio").datepicker({
					dateFormat: 'dd/mm/yy',
					yearRange: '1900:2100',
					changeMonth: true,
					changeYear: true,
					showOn: "button",
					buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
					buttonImageOnly: true			
				});	

			  $("#fechaFin").datepicker({
				  	dateFormat: 'dd/mm/yy',
					yearRange: '1900:2100',
					changeMonth: true,
					changeYear: true,
					showOn: "button",
					buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
					buttonImageOnly: true
				});				  
			  
			});


		function registraActividad(idrow){

			limpiaCamposActividad();

			$("#divFechas").dialog("open");
			$("#divFechas").dialog({ autoOpen: true, 
				modal: true, 
			  	title: 'Agregar Registro', 
			  	dialogClass: 'alert',
			  	position: [312,40],
			  	width: 440,
			  	height: 240,
			  	buttons:{"Agregar":function() {

			  				validaCamposFecha();	
					  							  		
			  				if(validaFecha==true){

			  					var params = 'fechaInicio=' + $('#fechaInicio').val();
			  					params += '&fechaFin=' + $("#fechaFin").val();
			  					params += '&tipoRegistro=' + $("#tipoRegistro").val();

			  					if($("#tipoRegistro").val()== "1"){			  					
			  						params += '&nombreEvento=' + "Vacaciones";
				  					}else{
				  						params += '&nombreEvento=' + "Incapacidad";
					  					}
			  							  					
			  					params += '&claveFuncionario=' + idrow;	

			  					//alert(params);
				  				
			  					$.ajax({
			  			    		type: 'POST',
			  			    	    url: '<%=request.getContextPath()%>/registrarVacacionesIncapacidad.do',
			  			    	    data: params,
			  			    	    dataType: 'xml',
			  			    	    success: function(xml){
			  			    	    	customAlert($(xml).find('body').find('mensaje').text());				  							
			  			    		}
								});		
			  					$(this).dialog("close");
					  		}	  					
			  		},
			  		"Cancelar":function() {
			  			$(this).dialog("close");
			  		}
			  	}
			});	  	
		
			}

		function limpiaCamposActividad(){
			$('#fechaInicio').val("");
			$("#fechaFin").val("");
		}

		//Funcion que valida si los campos estan llenos al enviar 
		function validaCamposFecha() {

			if ($('#fechaInicio').val() == '' || $('#fechaFin').val() == '') {
				customAlert("Debe ingresar la fecha de inicio y fecha de fin");
				validaFecha = false;
			} else {

				var fechaIniVal = $('#fechaInicio').val();
				var fechaFinVal = $('#fechaFin').val();

				var inicio = fechaIniVal.split("/");
				var fin = fechaFinVal.split("/");

				if(fin[2]>inicio[2]){
					validaFecha=true;
				}
				else{
					if(fin[2]<inicio[2]){
						validaFecha=false;
					}
					else{
						if(fin[1]>inicio[1]){
							validaFecha=true;
						}	
						else{
							if(fin[1]<inicio[1]){
								validaFecha=false;
							}
							else{
								if(fin[0]>=inicio[0]){
									validaFecha=true;
								}
								else{
									validaFecha=false;
								}
							}
						}
					}
				}
					
				if(validaFecha==false){	
					customAlert("La fecha final debe de ser mayor a la fecha inicial");
				}
				
			}	
		}
					
</script>
</head>

	<body>
	<table width="80%" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="40">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td width="36">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="center"><table id="gridDefensoresRegistrados"></table>
			<div id="pagered" width="300"></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="201" align="center">&nbsp;</td>
    <td width="221" align="center">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="center">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<div id="divFechas" style="display: none">
<table width="400" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="23" rowspan="3">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td width="25" rowspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td align="right"><strong>Fecha de Inicio:</strong></td>
    <td><input id="fechaInicio" type="text"  /></td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong>Fecha de Fin:</strong></td>
    <td><input id="fechaFin" type="text"  /></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong>Tipo:</strong></td>
    <td><select id="tipoRegistro">
      <option value="1">Vacaciones</option>
      <option value="2">Incapacidad</option>
    </select></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</div>

</body>
	
</html>
