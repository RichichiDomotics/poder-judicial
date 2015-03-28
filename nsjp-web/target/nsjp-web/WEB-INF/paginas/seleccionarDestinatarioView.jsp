<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Seleccionar Destinatario</title>
		
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
		<link rel="stylesheet" type="text/css" media="screen"href="<%= request.getContextPath()%>/resources/css/estilos.css" />
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ingresarDestinatario.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script type="text/javascript">

		var	idWindowConsultarPersonalOperativo = 1;
		var reloadGridDestinatario = false;
		var validaDestinatario = false;
		var validaComboDependiente = false;
		 
		jQuery().ready(function () {			

			 cargaComboInstitucion();		//Funcion que carga el combo de las Instituciones
			 restableceControles('0');
			 restableceControlesDos('0');
			 restableceControlesTres('0');
			 inicializaSeleccionarDestinatario('<%= request.getContextPath()%>');
			$('#rdbUsuario').change(tipoUsuario);
			$('#rdbExterno').change(tipoUsuario);
			$('#instituciones').change(enSeleccionInstitucion);
			$('#responsableInstitucion').change(flujoResponsableUno);
			$('#responsableArea').change(flujoResponsableDos);
			$('#responsableDepartamento').change(flujoResponsableTres);
			$('#areas').change(enSeleccionArea);
			$('#departamentos').change(enSeleccionDepartamento);
			$('#responsableDepartamento').attr('disabled','disabled');
			$('#responsableArea').attr('disabled','disabled');
			$('#departamentos').change(validaCamposDestinatario);	
										
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
		* Funcion para llamar la funcion de reiniciar las areas
		*/
		function flujoResponsableUno() {

			if($('#responsableInstitucion').is(':checked')){
				$('#instituciones').val("1");
				$('#areas').val("1");
				$('#departamentos').val("1");
				$('#responsableDepartamento').attr('disabled','disabled');
				$('#responsableArea').attr('disabled','disabled');
				$("#consultarPersonalOperativo").show();
				$('#areas').empty();
				$('#areas').append('<option value="1">-Seleccione-</option>');
				$('#areas').val("1");
				$('#departamentos').empty();
				$('#departamentos').append('<option value="1">-Seleccione-</option>');
				$('#departamentos').val("1");
				$('#tablaGridDep').hide();
				
				
				validaComboDependiente=false;
				
			
				}else{

				$("#consultarPersonalOperativo").hide();
				
				}

					  	
		}
		/*
		* Funcion para deshabilitar combo institucion
		*/
		function flujoResponsableDos() {

			if($('#responsableArea').is(':checked')){
				$('#instituciones').val("1");
				$('#areas').val("1");
				$('#departamentos').val("1");
				$('#responsableDepartamento').attr('disabled','disabled');
				$('#responsableInstitucion').attr('disabled','disabled');
				$("#consultarPersonalOperativo").show();
				$('#areas').empty();
				$('#areas').append('<option value="1">-Seleccione-</option>');
				$('#areas').val("1");
				$('#departamentos').empty();
				$('#departamentos').append('<option value="1">-Seleccione-</option>');
				$('#departamentos').val("1");
				$('#tablaGridDep').hide();
				
				validaComboDependiente=false;
			
				}else{
				$('#instituciones').attr('disabled','');
				$('#instituciones').val("1");
				$('#areas').val("1");
				$('#departamentos').val("1");
				$('#responsableInstitucion').attr('disabled','');
				$('#responsableArea').attr('disabled','disabled');
				$('#responsableArea').attr('disabled','disabled');
				$('#consultarPersonalOperativo').hide();
				}

					  	
		}

		/*
		* Funcion para deshabilitar combo institucion
		*/
		function flujoResponsableTres() {

			if($('#responsableDepartamento').is(':checked')){
				$('#instituciones').val("1");
				$('#areas').val("1");
				$('#departamentos').val("1");
				$('#responsableArea').attr('disabled','disabled');
				$('#responsableInstitucion').attr('disabled','disabled');
				$("#consultarPersonalOperativo").show();
				$('#areas').empty();
				$('#areas').append('<option value="1">-Seleccione-</option>');
				$('#areas').val("1");
				$('#departamentos').empty();
				$('#departamentos').append('<option value="1">-Seleccione-</option>');
				$('#departamentos').val("1");
				$('#tablaGridDep').hide();
				
				validaComboDependiente=false;
			
				}else{
				$('#instituciones').attr('disabled','');
				$('#instituciones').val("1");
				$('#areas').val("1");
				$('#departamentos').val("1");
				$('#responsableInstitucion').attr('disabled','');
				$('#responsableArea').attr('disabled','disabled');
				$('#responsableDepartamento').attr('disabled','disabled');
				$('#consultarPersonalOperativo').hide();
				
				}
		}
		
		/*
		* Funcion para deshabilitar combo areas
		*/
		function enSeleccionInstitucion() {
			
		  	var selected = $("#instituciones option:selected");		
			restableceControles(selected.val());

			if (validaComboDependiente==true){
				
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
			
		}
		/*
		* Funcion para llamar la funcion de habilitar los departamentos
		*/
		function enSeleccionArea() {
		  	var selected = $("#areas option:selected");	
		  		
			restableceControlesDos(selected.val());
		
	         $.ajax({
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/consultarCatalogoDepartamentos.do',
		    	  data: 'glCatAreasId=' + selected.val(),	//Parametro para hacer la consulta de Departamentos Id de el Area
			      async: false,
		    	  dataType: 'xml',
		    	  success: function(xml){		    		   
			    	 	$(xml).find('departamentos').each(function(){
						$('#departamentos').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');

						   });
		    	  }
		    	});
		    	
		}
	 
			
		/*
		* Funcion para llamar la funcion de habilitar los departamentos
		*/
		function enSeleccionDepartamento() {
		  	var selected = $("#departamentos option:selected");		
			restableceControlesTres(selected.val());
		}
		
		/*
		*Funcion que habilita o deshabilita las areas
		*/
		function restableceControles(tipo)
		{
			if(parseInt(tipo) == 1){

					$('#responsableArea').attr('disabled','disabled');
					$('#responsableDepartamento').attr('disabled','disabled');
					$('#responsableArea').attr('checked',false);
					$('#responsableInstitucion').attr('disabled','');
					$('#responsableDepartamento').attr('checked',false);
					$('#areas').empty();
					$('#areas').append('<option value="1">-Seleccione-</option>');
					$('#areas').val("1");
					$('#departamentos').empty();
					$('#departamentos').append('<option value="1">-Seleccione-</option>');
					$('#departamentos').val("1");
					
					validaComboDependiente=false;
				
					}
				else {

					$('#responsableInstitucion').attr('disabled','');
					$('#responsableArea').attr('disabled','');
					$('#responsableInstitucion').attr('checked',false);
					$('#responsableArea').attr('checked',false);
					$('#responsableDepartamento').attr('checked',false);
					$('#consultarPersonalOperativo').hide();
					$('#responsableDepartamento').attr('disabled','disabled');
				

						validaComboDependiente=true;

				}
					  
		}
		/*
		*Funcion que habilita o deshabilita los departamentos
		*/
		function restableceControlesDos(tipoDos)
		{
			if(parseInt(tipoDos) == 1){

					$('#departamentos').val("1");
					$('#responsableDepartamento').attr('disabled','disabled');
					//$('#responsableArea').attr('disabled','disabled');
					
					}
				else {

					//$('#areas').attr('disabled','');
					$('#responsableDepartamento').attr('disabled','');
					$("#consultarPersonalOperativo").hide();
					$('#responsableInstitucion').attr('checked',false);
					//$('#responsableArea').attr('disabled','');

				}
		  
		}

		/*
		*Funcion que habilita o deshabilita los departamentos
		*/
		function restableceControlesTres(tipoTres)
		{
			if(parseInt(tipoTres) == 1){

					
					//$('#departamentos').val("1");
					//$('#responsableDepartamento').attr('disabled','disabled');
					//$('#responsableArea').attr('disabled','disabled');
					
					}
				else {

					//$('#areas').attr('disabled','');
					$('#responsableDepartamento').attr('disabled','');
					$("#consultarPersonalOperativo").hide();
					$('#responsableInstitucion').attr('checked',false);
					$('#responsableArea').attr('checked',false);
					$('#responsableDepartamento').attr('checked',false);
					//$('#responsableArea').attr('disabled','');
					$('#tablaGridDep').show();

				}
			
		  
		}
		
		
		/*
		*Funcion que habilita si es usuario o externo
		*/
		function tipoUsuario()
		{
		  if($("#rdbUsuario").attr('checked'))
		  {
		  	$("#usuarioSistema").show();		
		  	$("#usuarioExterno").hide();
		  	
		  }
		  else if($("#rdbExterno").attr('checked'))
		  {
		     $("#usuarioSistema").hide();
		     $("#usuarioExterno").show();
		  }
		}

		//Llena el grid con los resultados de la busqueda, pasa como parametros la evidencia y una a 5 palabras clave
		  function llenaGridDestinatario(){
			 	var departamento = $("#departamentos").val();				//departamento
			 	
						if (validaDestinatario==true){
							if (reloadGridDestinatario) {
								  jQuery("#tablaGridDepartamentos").jqGrid('setGridParam', {postData: {departamento: departamento}});
								  $("#tablaGridDepartamentos").trigger("reloadGrid"); 
							  } else {
								  reloadGridDestinatario = true;
								  jQuery("#tablaGridDepartamentos").jqGrid(
											{ url:'<%= request.getContextPath() %>/buscarExpedientePorEvidencia.do', 						
												datatype: "xml", 
												mtype: 'POST',						
												postData: {departamento: function()     {return departamento;}													
												},
												colNames:['Nombre','A. Paterno','A. Materno','Cargo','Correo'], 
												colModel:[{name:'nombre',index:'nombre', width:150, sortable:false},
												          {name:'apaterno',index:'apaterno', width:150, sortable:false},
												          {name:'amaterno',index:'amaterno', width:150, sortable:false},
												          {name:'puesto',index:'puesto', width:150, sortable:false},
												          {name:'correo',index:'correo', width:150, sortable:false}

												], 
													autowidth: true, 
													pager: jQuery('#pager'), 
													sortname: 'id', 
													rownumbers: true,
													gridview: true, 
													viewrecords: true, 
													sortorder: "desc", 
													height: "60%",
													onSelectRow: function(id){
														detEvi(id);
														},
													caption:"Resultado de la B&uacute;squeda" 
											});
							 			 }
							}			  
						}

		  function detEvi(tr){
				alert("id:"+tr);
		
			} 

		//Funcion que valida si los campos estan llenos al enviar 
			function validaCamposDestinatario(){

				if ($('#departamentos').val()=="1"){
						validaDestinatario=false;
					}else {
					validaDestinatario=true;
					llenaGridDestinatario();
					
					}		
												
			}		
		
		</script>
	</head>
<body>

<table width="100%" cellspacing="0" cellpadding="0" >
  <tr>
    <td width="4%">&nbsp;</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="8%"><input type="button" id="para" value="Para:" class="btn_Generico"/>
    &nbsp;</td>
    <td colspan="2"><table id="destinatariosAgregados" border="1" width="90%"><tr><td>&nbsp;</td></tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3"><input type="button" id="adjuntarOficio" value="Adjuntar Oficio:" class="btn_Generico"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td width="44%" align="right"><input type="button" id="enviarDoc" value="Enviar" class="btn_Generico"/></td>
    <td width="44%">&nbsp;</td>
  </tr>
</table>

<div id="seleccionUsu" style="display:none">
<table width="900" cellspacing="0" cellpadding="0" >
 <tr>
    <td width="224">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td align="center">Selecciona tu tipo de usuario:</td>
    <td width="83" align="left"><input type="radio" name="tipoUsuario" id="rdbUsuario" value="0"/>
      Usuario
    </td>
    <td width="387" align="left"><input type="radio" name="tipoUsuario" id="rdbExterno" value="1"/>
Externo</td>
    <td width="4">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="left">&nbsp;</td>
  </tr>


</table>

<div id="usuarioSistema" style="display:none">
<table width="900" cellspacing="0" cellpadding="0"  >
  <tr>
    <td colspan="3">&nbsp;</td>
    <td width="124">&nbsp;</td>
    <td width="523" rowspan="9"><div id="consultarPersonalOperativo" style="display:none"><jsp:include page="/WEB-INF/paginas/consultarPersonalOperativoView.jsp" flush="true"></jsp:include></div></td>
  </tr>
  <tr>
    <td width="35" align="left">&nbsp;</td>
    <td width="91" align="left">Institucion:</td>
    <td width="127" align="left"><select name="instituciones" id="instituciones">
      <option value="1">-Seleccione-</option>
      
    </select></td>
    <td><input type="checkbox" id="responsableInstitucion"/>
      Responsable</td>
    </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">Area:</td>
    <td align="left"><select name="areas" id="areas">
    <option value="1" >-Seleccione-</option>
          
    </select></td>
    <td><input type="checkbox" id="responsableArea"/>
      Responsable</td>
    </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">Departamento:</td>
    <td align="left"><select name="departamentos" id="departamentos">
      <option value="1">-Seleccione-</option>
      
    </select></td>
    <td><input type="checkbox" id="responsableDepartamento"/>
      Responsable</td>
    </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td align="center">&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td colspan="3" align="left">&nbsp;</td>
    <tr>
    <td colspan="4">Selecciona el tipo de Destinatario:&nbsp;
      <input type="radio" name="tipoDestinatario" id="principal2"/>
      Principal &nbsp;
  <input type="radio" name="tipoDestinatario" id="copia2"/>
      Copia</td>
    <tr>
    <td colspan="5">&nbsp;</td> 
    <tr>
      <td colspan="5"><div id="tablaGridDep"><table width="900" cellspacing="0" cellpadding="0" id="tablaGridDepartamentos"><tr><td>&nbsp;</td></tr></table></div></td>
      </tr>
    </table>
</div>
<div id="usuarioExterno" style="display: none">
  <table width="900" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="3">&nbsp;</td>
    
  </tr>
  <tr>
    <td width="40" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td width="129" align="left">Nombre:&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td width="196" align="left"><input type="text" name="nombreDestinatarioExt" id="nombreDestinatarioExt" size="30"/></td>
    <td align="left">Cargo:&nbsp;
    <input type="text" name="puestoDestinatarioExt" id="puestoDestinatarioExt" size="40"/></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">Apellido Paterno:&nbsp;</td>
    <td align="left"><input type="text" name="apPatDestinatarioExt" id="apPatDestinatarioExt" size="30"/></td>
    <td align="left">Correo:
      <input type="text" name="correoDestinatarioExt" id="correoDestinatarioExt" size="40"/></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td align="left">Apellido Materno:</td>
    <td align="left"><input type="text" name="apMatDestinatarioExt" id="apMatDestinatarioExt" size="30"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left">&nbsp;</td>
    <td colspan="3" align="left">Selecciona el tipo de Destinatario:
      <input type="radio" name="tipoDestinatario" id="principal" value="principal"/>
      Principal &nbsp;&nbsp;&nbsp;
  <input type="radio" name="tipoDestinatario" id="copia" value="copia"/>
      Copia</td>
    </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4"><div id="tablaGrid"><table width="900" cellspacing="0" cellpadding="0" id="gridDestinatarioExt"><tr><td>&nbsp;</td></tr></table></div></td>
  </tr>
</table>
</div>
</div>
<div id="seleccionarOficio" style="display:none">
<jsp:include page="consultarListaDeDocumentosDeExpediente.jsp"></jsp:include>
</div>
</body>
</html>