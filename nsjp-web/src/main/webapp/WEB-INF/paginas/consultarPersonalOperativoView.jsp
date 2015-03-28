<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Personal Operativo</title>
</head>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		$("#btnBuscarPersonal").bind("click",buscarPersonal);
		cargaComboInstitucion();		//Funcion que carga el combo de las Instituciones
		cargaPuestos();
		cargaEspecialidad();
		$('#instituciones').change(enSeleccionInstitucion);
	});	
	
	//Funcion que carga el combo de instituciones
	function cargaComboInstitucion() {
     $.ajax({
    	  type: 'POST',
    	  url: '<%= request.getContextPath()%>/consultarCatalogoInstituciones.do',
    	  data: '',
    	  async: false,
    	  dataType: 'xml',
    	  success: function(xml){
	    	 	$(xml).find('instituciones').each(function(){
				$('#instituciones').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');

				   });
    	  }
    	});
     }
	
	/*
	* Funcion para deshabilitar combo areas
	* Permite hacer cargar las Areas por Id de la Institucion
	*/
	function enSeleccionInstitucion() {
	  	var selected = $("#instituciones option:selected");
         $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/consultarCatalogoAreas.do',
	    	  data: 'glCatInstitucionId=' + selected.val(),	//Parametro para hacer la consulta de Areas por Id de la Institucion
	    	  async: false,
	    	  dataType: 'xml',
	    	  success: function(xml){
	    			$('#areas').empty();
	    			$('#areas').append( '<option value="1">-Seleccione-</option>');		    			
		    	 	$(xml).find('areas').each(function(){
					$('#areas').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					   });
	    	  }
	    	});
			
	}


	/*
	*Funcion que dispara el Action para consultar Puestos
	*/	
	function cargaPuestos(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoPuestos.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				var option;
				$(xml).find('puestos').each(function(){
					$('#puestos').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	/*w
	*Funcion que dispara el Action para consultar Especialidad
	*/	
	function cargaEspecialidad() {
		$.ajax({
			async: false,
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoEspecialidadSinTipo.do',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('especialidad').each(function(){
					$('#especialidad').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}
	
	  //Llena el grid con los resultados de la busqueda, pasa como parametros el No de caso
	  var banderaCargarORecargar=0;
	  function buscarPersonal(){
		  var nombre = $("#nombre").val();				  
		  var idPO = $("#idPO").val();				  
		  var apaterno = $("#apaterno").val();				  
		  var amaterno = $("#amaterno").val();				  
		  var curp = $("#curp").val();				  
		  var especialidad = $("#especialidad option:selected").val();
		  var rfc = $("#rfc").val();				  
		  var puesto = $("#puestos option:selected").val();
		  var correo = $("#correo").val();				  
		  var cedula = $("#cedula").val();				  
		  var organizacion = $("#organizacion").val();				  
		  var institucion = $("#instituciones option:selected").val();
		  var area = $("#areas option:selected").val();
		  
			if(banderaCargarORecargar==0){
				jQuery("#tablaBuscarPersonalOperativo").jqGrid({
					url:'<%=request.getContextPath()%>/consultarPersonalOperativoAction.do?nombre='+ nombre +
					'&idPO='+ idPO +'&apellidoPaterno='+ apaterno + '&apellidoMaterno='+ amaterno +'&especialidad='+ especialidad +'&rfc='+ rfc +'&correo='+ correo +
					'&puesto='+ puesto +'&cedula='+ cedula +'&organizacion='+ organizacion +'&institucion='+ institucion +'&area='+ area +'&curp='+ curp +				
							'', 
					datatype: "xml",  							
					colNames:['Nombre','Especialidad','Curp','Rfc','Correo','Cedula','Cargo','Area','Institucion'], 
					colModel:[  {name:'nombre',index:'nombre', width:100},
								{name:'especialidad',index:'especialidad', width:100},
								{name:'curp',index:'curp', width:80}, 
								{name:'rfc',index:'rfc', width:80},
								{name:'email',index:'email', width:100},
								{name:'cedula',index:'cedula', width:90},
								{name:'puesto',index:'puesto', width:50},
								{name:'departamento',index:'departamento', width:50},
								{name:'institucion',index:'institucion', width:50}
							],
							autowidth: true, 
							rowNum:10,
							rowList:[10,20,30],
							pager: jQuery('#pager1'), 
							sortname: 'id', 
							rownumbers: true,
							gridview: true, 
							viewrecords: true, 
							sortorder: "desc", 
							height: "50%",
							caption:"Resultado de la B&uacute;squeda" 												
							});
	        	$("#tablaBuscarPersonalOperativo").trigger("reloadGrid");
	        	banderaCargarORecargar=1;
	    }else{
	    	jQuery("#tablaBuscarPersonalOperativo").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarPersonalOperativoAction.do?nombre='+ nombre +
				'&idPO='+ idPO +'&apellidoPaterno='+ apaterno + '&apellidoMaterno='+ amaterno +'&especialidad='+ especialidad +'&rfc='+ rfc +'&correo='+ correo +
				'&puesto='+ puesto +'&cedula='+ cedula +'&organizacion='+ organizacion +'&institucion='+ institucion +'&area='+ area +'&curp='+ curp +				
						'',datatype: "xml" });
			$("#tablaBuscarPersonalOperativo").trigger("reloadGrid");		
	    }	            	
		//Fin grid
		  
	}//Cierra buscarPersonal


</script>

	<p>Consultar Personal Operativo</p>
	<table width="840" cellspacing="0" cellpadding="0">
  <tr>
    <td>Id personal operativo:</td>
    <td><input type="text" size="20px" id="idPO"/></td>
    <td>Nombre:</td>
    <td><input type="text" size="20px" id="nombre"/></td>
  </tr>
  <tr>
    <td width="142">Apellido Paterno:</td>
    <td width="252"><input type="text" size="20px" id="apaterno"/></td>
    <td width="239">Apellido Materno:</td>
    <td width="205"><input type="text" size="20px" id="amaterno"/></td>
  </tr>
  <tr>
    <td>RFC:</td>
    <td><input type="text" size="20px" id="rfc"/></td>
    <td>CURP:</td>
    <td><input type="text" size="20px"id="curp"/></td>
  </tr>
  <tr>
    <td>C&eacute;dula Prof.:</td>
    <td><input type="text" size="20px" id="cedula"/></td>
    <td>Correo electronico:</td>
    <td><input type="text" size="20px" id="correo"/></td>
  </tr>
  <tr>
    <td>Instituci&oacute;n:</td>
    <td><select name="instituciones" id="instituciones">
            <option value="-1" selected="selected">-Seleccione-</option>
    </select></td>
    <td>Area:</td>
    <td><select name="area" id="areas">
            <option value="-1" selected="selected">-Seleccione-</option>
    </select>
    </td>
  </tr>
  <tr>
    <td>Cargo:</td>
    <td><select name="puestos" id="puestos">
      <option value="-1" selected="selected">-Seleccione-</option>
    </select></td>
    <td>Organizacion a la que
      peretenece
    :</td>
    <td><input type="text" size="20px" id="organizacion"/></td>
  </tr>
  <tr>
    <td>Especialidad:</td>
    <td colspan="3"><select name="especialidad" id="especialidad" >
      <option value="-1" selected="selected">-Seleccione-</option>
    </select></td>
    </tr>
  <tr>
    <td colspan="4" align="center"><input type="button" name="btnBuscarPersonal" id="btnBuscarPersonal" value="Buscar" class="btn_Generico"></td>
    </tr>
    </table>

<table align="center" id="tablaBuscarPersonalOperativo" width="800px">
</table>
<div id="pager1"></div>
<br/>