<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Asociar Individuo</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		
		<script type="text/javascript">
			var reloadGridAlias = false;
			jQuery().ready(			
				function () {
					llenaGridAlias();
					cargaCatalogos();
					cargaCatalogos2();
				});



			//Llena el grid con los resultados de la busqueda, pasa como parametros el
			//alias ingresado
			  function llenaGridAlias(){
				  var alias = 'hola';//Alias	
				  var con = true;
				  var esIg = true;
				  		
				  if (alias==''){
					customAlert ("Existen datos invalidos");	
				  }else  if((con==false) && (esIg==false)){
					  		customAlert ("Debes seleccionar una de las dos opciones para buscar el alias");
				  }else{
					 
					   if (reloadGridAlias) {
							  jQuery("#detalleIndividuos").jqGrid('setGridParam', {postData:{alias: alias}});
							  $("#detalleIndividuos").trigger("reloadGrid"); 
						  } else {
							  reloadGridAlias = true;
							  jQuery("#detalleIndividuos").jqGrid(
										{ url:'<%= request.getContextPath() %>/buscarExpedientePorAlias.do', 
											data: 'alias='+alias,						
											datatype: "xml", 
											mtype: 'POST',						
											postData: {
												alias: function()     { return alias; }
											},
											colNames:['Expediente','Nombre','A. Paterno', 'A. Materno'], 
											colModel:[ {name:'expediente',index:'expediente', width:100, sortable:false},
												{name:'nombre',index:'nombre', width:150, sortable:false}, 
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
			}

			  /*
				*Funcion que dispara el Action para consultar catalogos
				*/
			   function cargaCatalogos() {
			         $('#idDelitosCaso').empty();
				      $.ajax({
					      type: 'POST',
				    	  url: '<%= request.getContextPath()%>/consultaCatalogosCaso.do',
				    	  data: '',
				    	  dataType: 'xml',
				    	  success: function(xml){
					    	  $('#idDelitosCaso').empty();
					    	  $('#idDelitosCaso').append('<option value="-1">- Seleccione -</option>');
				    		  $(xml).find('catCatalogo').each(function(){
								$('#idDelitosCaso').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
							   });
				    	  }
				    	});
					}

			   /*
				*Funcion que dispara el Action para consultar catalogos
				*/
			   function cargaCatalogos2() {
			         $('#idFormasParticipacion').empty();
				      $.ajax({
					      type: 'POST',
				    	  url: '<%= request.getContextPath()%>/consultaCatalogosCaso.do',
				    	  data: '',
				    	  dataType: 'xml',
				    	  success: function(xml){
					    	  $('#idFormasParticipacionv').empty();
					    	  $('#idFormasParticipacion').append('<option value="-1">- Seleccione -</option>');
				    		  $(xml).find('catCatalogo').each(function(){
								$('#idFormasParticipacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
							   });
				    	  }
				    	});
					}
				
		</script>
	</head>
<body>
	<table border="0" width="100%">
		<tr valign="top">
			<td>
				<table border="1" width="85%" height="33%">
					<tr>
						<td width="30%">Calidad:</td>
						<td><bean:message key="probableResponsable"/></td>
						
					</tr>
					<tr>
						<table id="detalleIndividuos" width="85%"></table>
					</tr>	
				</table>
			</td>
			
		</tr>
		<tr>
			<td>
				<table border="1" width="85%" height="33%">
					<tr>
						<td width="30%">Delitos del caso sin relación persona:</td>
						<td><select id="idDelitosCaso" name="idDelitosCaso">		
							</select>
						</td>
					</tr>	
				</table>
			</td>
			
		</tr>
		<tr>
			<td>
				<table border="1" width="85%" height="33%">
					<tr>
						<td width="30%">Formas de participación</td>
						<td><select id="idFormasParticipacion" name="idFormasParticipacion">
							</select>
						</td>
					</tr>	
				</table>
			</td>
		</tr>
	</table>


</body>
</html>