<%@page import="mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.institucion.Instituciones"%>
<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
<%@page import="mx.gob.segob.nsjp.web.catalogo.form.CatalogosForm"%>
<%@page import="mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	 <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css"/>	
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>   	
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
    
   	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>    
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">
		var contextPath = '<%=request.getContextPath()%>';
		var banderaModificar = false;
		var modificaDistritos = false;
		var estadoAccion = 0;
		var varIDCat = parent.varIDCat;
		var primerclave;
		var clave;
		
<%  CatalogosForm forma = (CatalogosForm)request.getAttribute("CatalogosForm");
	String descripion = forma.getCatalogo().getValor();
	boolean esNuevo = (Boolean)request.getAttribute("esNuevo");
	boolean esSistema = forma.getCatalogo().getEsSistema();
	Long idCatalogo = forma.getIdCatalogo();
	Map<String, List<CatalogoDTO>> mapaCatalogos = (Map<String, List<CatalogoDTO>>)request.getAttribute("mapaCatalogos"); %>
	$(document).ready(function() {		
		<%if(!esNuevo){%>
			disableAll();
		<%}%>
		validarSoloNumerosAgenciaPGJ();
		acotarLongitud();
	});

	/*
	*Funcion para validar que solo se escriban numeros en el campo de texto clave de agencias
	*/
	function validarSoloNumerosAgenciaPGJ(){
		
		var idCatalogoNuevo = validarClaveAgenciaPGJ();
				
		if(idCatalogoNuevo == '<%=Catalogos.AGENCIAS.ordinal()%>'){
			$("#valorExtra-0-Clave").keypress(function(event) {
				 var unicode = event.charCode ? event.charCode : event.keyCode;

				    // if the key is backspace, tab, or numeric
				    if (unicode == 8 || unicode == 9 || (unicode >= 48 && unicode <= 57)) {
				        // we allow the key press
				        return true;
				    }
				    else {
				        // otherwise we don't
				        return false;
				    }
			});
			//oculta la tag de descripcion
			$("#descripcionTag").hide();
		}
		else{			
			//oculta la tag de clave de agencia
			$("#claveTag").hide();
		}
	}

	function acotarLongitud(){
		
		var idCatalogoNuevo = validarClaveAgenciaPGJ();		
		if(idCatalogoNuevo == '<%=Catalogos.DISTRITOS.ordinal()%>'){			
			$("#valorExtra-0-Clave").keypress(function(event) {
				longitud = $("#valorExtra-0-Clave").val();
				if(longitud.length > 2){
					valor = longitud.substring(0,2);
					alertDinamico("La longitud máxima debe ser de dos caracteres");
				}
			});
		}else if(idCatalogoNuevo == '<%=Catalogos.AGENCIAS.ordinal()%>'){			
			$("#valorExtra-0-Clave").keypress(function(event) {
				longitud = $("#valorExtra-0-Clave").val();
				if(longitud.length > 3){
					valor = longitud.substring(0,3);
					alertDinamico("La longitud máxima debe ser de tres caracteres");
				}
			});
		}
	}
		
		function modificarValor(){
			$("#claveCatalogo").removeAttr('disabled');
			$("#idCampoCatalogo").removeAttr('disabled');
			$("#valorCatalogo").removeAttr('disabled');					
			
			var valoresExtra =	document.getElementsByName("valorExtra");
			var id = "";
			for(i=0;i<valoresExtra.length;i ++){							
				if (valoresExtra[i].id == "valorExtra-0"+"-"+"TipoJerarquia") {
					valoresExtra[i].disabled=true;
				} else if (valoresExtra[i].id == "valorExtra-0"+"-"+"JerarquiaOrgResponsable") {
					valoresExtra[i].disabled=true;
				}
				else {
					valoresExtra[i].disabled=false;
				}																
			}
			$("#btnModificar").hide();
			$("#btnGuardar").show();

			if(<%=idCatalogo%>=='<%=Catalogos.AGENCIAS.ordinal()%>'){ 
				
			banderaModificar = true;
			}

			if(<%=idCatalogo%>=='<%=Catalogos.DISTRITOS.ordinal()%>'){ 
				
			modificaDistritos =true;

			}
		}
		
		function disableAll(){
			$("#claveCatalogo").attr('disabled','disabled');
			$("#idCampoCatalogo").attr('disabled','disabled');
			$("#valorCatalogo").attr('disabled','disabled');
			var valoresExtra =	document.getElementsByName("valorExtra");
			var id = "";
			for(i=0;i<valoresExtra.length;i ++){
				//id = "#"+valoresExtra[i].id;
				//$(id).attr('disabled','disabled');
				valoresExtra[i].disabled=true;
			}
		}
		
		function guardarValor(){
			var valoresExtra =	document.getElementsByName("valorExtra");
			var valorExtra = "";
			var idCampo = "", nbCampo = "", valor = "";
			for(i=0;i<valoresExtra.length;i ++){
				idCampo =  valoresExtra[i].id.split("-")[1];
				nbCampo =  valoresExtra[i].id.split("-")[2];
				valor = valoresExtra[i].value;
				if (valor=="-1") {					
					var mensaje = "Debe seleccionar un valor del catalogo padre";
					alertDinamico(mensaje);
					return;
				} else {
					valorExtra +=idCampo+"#"+nbCampo+"#"+valor+"|";
				}						
			}
			var accion = contextPath+"/GuardarValorEnCatalogo.do";
			var params = "idCatalogo=<%=idCatalogo%>";
			params += "&clave="+$("#claveCatalogo").val();
			params += "&idCampo="+$("#idCampoCatalogo").val();
				
			//Solo para agencias y distritos				 
			idCat = validarClaveAgenciaPGJ();
						
			//Si el catalogo es de agencias o distritos
			if(idCat == '<%=Catalogos.AGENCIAS.ordinal()%>' || idCat == '<%=Catalogos.DISTRITOS.ordinal()%>'){
				if(idCat == '<%=Catalogos.AGENCIAS.ordinal()%>'){
					var claveAgencia = $("#valorExtra-0-Clave").val();
					//Valida que el distrito sea obligatorio
					var idDistrito=$("#valorExtra-0-Distrito option:selected").val();
					var nombre = $("#valorExtra-0-Nombre").val();
					//valida la longitud para agencias
					if(claveAgencia.length != 3 || parseInt(idDistrito)==-1 || nombre.length == 0){
						var mensaje = "";
						if(nombre.length == 0)
							mensaje = mensaje + "El nombre es obligatorio.\n";
						if(claveAgencia.length != 3)
							mensaje = mensaje + "La clave debe ser de tres dígitos.\n";
						if(parseInt(idDistrito)==-1)
							mensaje = mensaje + "Debe de seleccionar un Distrito.";
						alertDinamico(mensaje);
						return;
					}
					else{
						params += "&valor="+$("#valorExtra-0-Clave").val();					
					}
				}
				if(idCat == '<%=Catalogos.DISTRITOS.ordinal()%>'){
					
					var claveDistrito = $("#valorExtra-0-ClaveDistrito").val();
					//valida la longitud para distritos
					if(claveDistrito.length != 2 ){
						alertDinamico("La clave del distrito debe ser de dos dígitos");
						return;
					}
					else{
						params += "&valor="+$("#valorExtra-0-ClaveDistrito").val();
					}
				}				
			}else
				params += "&valor="+$("#valorCatalogo").val();
			
			
			if(!isEmpty(valorExtra)){
				params += "&valorExtra="+valorExtra.substring(0,valorExtra.length-1);
			}
			peticionSincronaAjaxXML(accion, params, cerrarVentana);
		}
		
		function eliminarValor(){			
			
			if(<%=idCatalogo%>=='<%=Catalogos.AGENCIAS.ordinal()%>'  || <%=idCatalogo%>=='<%=Catalogos.DISTRITOS.ordinal()%>' ){
				if($("#claveCatalogo").val() != 0)
					eliminarRegistroDeCatalogo(<%=idCatalogo%>, $("#claveCatalogo").val());	
			}else{
				var accion = contextPath+"/EliminarValorDeCatalogo.do";
				
				var params = "clave="+$("#claveCatalogo").val();
				params += "&idCatalogo="+<%=idCatalogo%>;
				peticionSincronaAjaxXML(accion, params, cerrarVentana);
			}
		}
		
				
		function cerrarVentana(xml){
			var textoAlert;
			
			if(estadoAccion == 0)
			{textoAlert="El registr\u00F3 se elimin\u00F3 correctamente en el cat\u00E1logo";}
			
			if(estadoAccion == 1)
				{textoAlert="La informaci\u00F3n se registr\u00F3 correctamente en el cat\u00E1logo";}
			
			if(estadoAccion == 2)
				{textoAlert="La informaci\u00F3n se actualiz\u00F3 correctamente en el cat\u00E1logo";}

			if($(xml).text() == "0"){
				textoAlert = "Ocurri\u00F3 un problema al registrar el cat\u00E1logo";
			}
			
			if($(xml).text() == "fallo"){
				textoAlert = "La operaci\u00F3n no puede ser completada, verifique que el registro no est\u00F3 en uso";
			}
			
			alertDinamicoCerrar(textoAlert);
		}
		
		//Función para alertDinamicoCerrar
		function alertDinamicoCerrar(textoAlert){						
			$("#divAlertTextoCerrar").html(textoAlert);
		    $( "#dialog-AlertCerrar" ).dialog({
				autoOpen: true,
				resizable: false,
				modal: true,
				buttons: {				
					
					"Aceptar": function() {						
						parent.cerrarVentanaVerValor();
						$( this ).dialog( "close" );
						$("#divAlertTextoCerrar").html("");					
					}				
				}
			});    
		 }
		
		function validarClaveAgenciaPGJ(){	
			 <%
				CatalogosForm formaCat = (CatalogosForm)request.getAttribute("CatalogosForm");
				int idCat = forma.getIdCatalogo().intValue();
			%>
			return <%=idCat%>
		}
	
		function validaClaveIgual(){
			var existe=false;
			var idancat = <%=idCat%>;
			clave = $("#valorExtra-0-Clave").val();
	        
	        if (banderaModificar==true&&primerclave==clave&&idancat=='<%=Catalogos.AGENCIAS.ordinal()%>'){
	            			
				var params = "idCatalogo="+varIDCat; 
				params += "&clave="+$("#valorExtra-0-Clave").val();
				params += "&idCampo="+$("#valorExtra-0-Distrito").val();
				params += "&nombre="+$("#valorExtra-0-Nombre").val();
				
				$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/modificarCatDiscriminante.do',
		    		data: params,
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){ 	
		    			estadoAccion=2;
		    			//alertDinamico("La informaci\u00F3n se actualiz\u00F3 correctamente en el cat\u00E1logo");
		    			//parent.iframeConsultaCatalogoLoad();
	    				cerrarVentana(xml);		    			
		    		}
				});
	            
	            
			}else if (banderaModificar==true&&idancat=='<%=Catalogos.AGENCIAS.ordinal()%>'){

	            clave = $("#valorExtra-0-Clave").val();
	            param ='idClaveCatalogo='+idancat;
		    	
	            $.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarValoresCatalogoAgencias.do',
		    		data: param,
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){ 
		    			var option;
		    			$(xml).find('catalogoCompletoDTO').find('valores').find('catalogoDTO').find('valoresExras').find('valorDTO').each(function(){
		    				$(xml).find('catalogoCompletoDTO').find('valores').find('catalogoDTO').find('valoresExras');
		    				option = $(this).find('valor').text();
		    				
		    				if(clave==option){	    					
		    					existe =true;
	    					}
		    			});

		    			if(existe==true){
		    				alertDinamico("La Clave ya Existe");
	                    }else{
			    			var params = "idCatalogo="+varIDCat; 
			    			params += "&clave="+$("#valorExtra-0-Clave").val();
			    			params += "&idCampo="+$("#valorExtra-0-Distrito").val();
			    			params += "&nombre="+$("#valorExtra-0-Nombre").val();
			    				
			    			$.ajax({
			   		    		type: 'POST',
			   		    		url: '<%=request.getContextPath()%>/modificarCatDiscriminante.do',
			   		    		data: params,
			   		    		dataType: 'xml',
		    		    		async: false,
		    		    		success: function(xml){ 		
		    		    			//alertDinamico("La informaci\u00F3n se actualiz\u00F3 correctamente en el cat\u00E1logo");
		    		    			estadoAccion=2;
		    		    			
		    		    			cerrarVentana(xml);
			    		    	}
	                        });
				    	}
		    		}
		    	});		
	            
	        }else if(idancat=='<%=Catalogos.AGENCIAS.ordinal()%>'){
				
				clave = $("#valorExtra-0-Clave").val();
				param ='idClaveCatalogo='+idancat;
			    				
	            $.ajax({
			    	type: 'POST',
			    	url: '<%=request.getContextPath()%>/consultarValoresCatalogoAgencias.do',
			    	data: param,
			    	dataType: 'xml',
			    	async: false,
			    	success: function(xml){ 
			    		var option;
			    		$(xml).find('catalogoCompletoDTO').find('valores').find('catalogoDTO').find('valoresExras').find('valorDTO').each(function(){
			    			$(xml).find('catalogoCompletoDTO').find('valores').find('catalogoDTO').find('valoresExras');
			    			option = $(this).find('valor').text();
			    				
			    			if(clave==option){  
			    				existe =true;
			    			}
			    		});

			    			if(existe==true){
			    				alertDinamico("La Clave ya existe");

				    			}else{
				    				estadoAccion=2;
				    				guardarValor();
				    				parent.iframeConsultaCatalogoLoad();
					    			 }
			    		}
			    	});
			    
	            
				
			}else{
				clave = $("#valorExtra-0-ClaveDistrito").val();
				if(modificaDistritos==true&&primerclave==clave&&idancat=='<%=Catalogos.DISTRITOS.ordinal()%>'){

					estadoAccion=2;
					guardarValor();
					parent.iframeConsultaCatalogoLoad();


				}else if(idancat=='<%=Catalogos.DISTRITOS.ordinal()%>'){
					
					clave = $("#valorExtra-0-ClaveDistrito").val();
					var param;
					param ='idClaveCatalogo='+idancat;
				    
	                $.ajax({
				    	type: 'POST',
				    	url: '<%=request.getContextPath()%>/consultarValoresCatalogoAgencias.do',
				    	data: param,
				    	dataType: 'xml',
				    	async: false,
				    	success: function(xml){
				    		var option;
				    		$(xml).find('catalogoCompletoDTO').find('valores').find('catalogoDTO').find('valoresExras').find('valorDTO').each(function(){
				    			$(xml).find('catalogoCompletoDTO').find('valores').find('catalogoDTO').find('valoresExras');
				    				option = $(this).find('valor').text();
				    				
				    				if(clave==option){
				    					existe =true;
	                                }
				    		});

				    			if(existe==true){
				    				
				    				alertDinamico("La Clave ya existe");

					    		}else{          	
					    			estadoAccion=1;
	                                guardarValor();	                             
					    		 	parent.iframeConsultaCatalogoLoad();
						    	}
				   		}
				    });
				
	            }else{						
	            	estadoAccion=1;
	                guardarValor();
	                parent.iframeConsultaCatalogoLoad();
				}
			}
		}
		
		function eliminarRegistroDeCatalogo(idCatalogo, idValor){
			$.ajax({
				type: 'POST',
				url: '<%=request.getContextPath()%>/eliminarRegistroDeCatalogo.do',
				data: 'idCatalogo='+ idCatalogo + '&idValor='+ idValor, 
				async: false,
				dataType: 'xml',
				success: function(xml){
					
					var mensaje = $(xml).find('body').find('respuesta').text();
					if(mensaje == "2"){
						alertDinamicoCerrar("El registro no se encuentra actualmente en base de datos");
					}
					else{
						if(mensaje == "0"){
							alertDinamicoCerrar("El registro se elimin\u00F3 correctamente en el cat\u00E1logo");							
						}else{
							
							alertDinamicoCerrar("La operaci\u00F3n no puede ser completada, verifique que el registro no est\u00E9 en uso");
						}
					}
				}
			});
		}
		
		
			 
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
<!-- div para el alert dinamico antes de cerrar ventana -->
<div id="dialog-AlertCerrar" style="display: none">
	<table align="center">
		<tr>
        	<td align="center">
            	<span id="divAlertTextoCerrar"></span>
            </td>
        </tr>
     </table>              
</div>    
<html:form action="/GuardarValorEnCatalogo" styleId="CatalogosForm" method="POST" >
<bean:define id="cat" name="CatalogosForm" property="catalogo"/>
<html:hidden name="cat" property="clave" styleId="claveCatalogo"/>
<html:hidden name="cat" property="campoId" styleId="idCampoCatalogo"/>
<table>
	<logic:notEqual name="cat" property="valor" value="HIDE_CAMPO">
	<tr>
		<td width="5%">
			<span id="descripcionTag">Descripción:</span>
			<span id="claveTag">Clave Agencia:</span>
		</td>
		<td width="50%">
			<html:text name="cat" property="valor" styleId="valorCatalogo"></html:text>
		</td>
	</tr>
	</logic:notEqual>
<logic:iterate id="valor" name="cat" property="valoresExras">
<bean:define id="idValor" name="valor" property="idCampo"/>
<bean:define id="nombreC" name="valor" property="nombreCampo"/>
<bean:define id="idValorPadre" name="valor" property="valor"/>
	<tr>
		<td width="50%">
			<bean:write name="valor" property="nombreCampo"/>:
		</td>
		<td width="100%">
		<logic:present name="valor" property="valorPadre">	
			<select name="valorExtra" id="valorExtra-<%=idValor+"-"+nombreC%>">
				<option value="-1">Seleccione una opcion</option>
	
		
<%
List<CatalogoDTO> opciones = mapaCatalogos.get(nombreC);
for(CatalogoDTO opcion : opciones){
	if(idValorPadre!= null && idValorPadre.equals(""+opcion.getClave())){ %>
					<option value="<%=opcion.getClave()%>" selected="selected"><%=opcion.getValor()%></option>
<%	}else{ %>
					<option value="<%=opcion.getClave()%>"><%=opcion.getValor()%></option>
<%	}
}			%>
			</select>
		</logic:present>
		<logic:notPresent name="valor" property="valorPadre">
			<input type="text" size="100" name="valorExtra" id="valorExtra-<%=idValor+"-"+nombreC%>" value="<bean:write name="valor" property="valor"/>"/>
		</logic:notPresent>
		</td>
	</tr>
</logic:iterate>
<%  if(!esNuevo){%>
	<tr>
		<td colspan="2">
			<input type="button" onclick="modificarValor()" id="btnModificar" value="Modificar" class="btn_Generico"/>
			<input type="button" onclick="validaClaveIgual()" 	id="btnGuardar" value="Guardar" class="btn_Generico" style="display:none"/>
			<%if(!esSistema){ %>
			<input type="button" onclick="eliminarValor()" id="btnEliminar" value="Eliminar" class="btn_Generico"/>
			<%} %>
		</td>
	</tr>
<%	}else{%>
	<tr>
		<td colspan="2">
			<input type="button" onclick="modificarValor()" id="btnModificar" value="Modificar" class="btn_Generico" style="display:none"/>
			<input type="button" onclick="validaClaveIgual()" 	id="btnGuardar" value="Guardar" class="btn_Generico"/>
			<input type="button" onclick="eliminarValor()" id="btnEliminar" value="Eliminar" class="btn_Generico"/>
		</td>
	</tr>

<%	}%>
</table>
</html:form>

<script type="text/javascript">

if(<%=idCatalogo%>=='<%=Catalogos.DISTRITOS.ordinal()%>'){ 
	
	primerclave=$("#valorExtra-0-ClaveDistrito").val();
	//$("#btnEliminar").css("display", "none");

}


if(<%=idCatalogo%>=='<%=Catalogos.AGENCIAS.ordinal()%>'){ 
	
	primerclave = $("#valorExtra-0-Clave").val();
	//$("#btnEliminar").css("display", "none");

}

</script>
</body>
</html>