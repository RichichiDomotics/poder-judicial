<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Generar Documento</title>
	
	<!--iframe que crea una nueva peticion para imprimir un PDF-->
	<iframe id="framePdf" src="" width="0" height="0">
	</iframe>
	
	<!--		Hojas de estilos asociadas-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	<script type="text/javascript">
	
	var contextoPagina = "${pageContext.request.contextPath}";
	var idWindowPdf = 1;
	var esconderArbol = <%=request.getParameter("esconderArbol")!=null?"true":"false"%>;
	var numeroUnicoExpediente = '<%=request.getParameter("numeroUnicoExpediente")!=null?request.getParameter("numeroUnicoExpediente"):""%>';
	var idResolutivo= '<%=request.getParameter("idResolutivo")!=null?request.getParameter("idResolutivo"):""%>';
	var idAudiencia= '<%=request.getParameter("idAudiencia")!=null?request.getParameter("idAudiencia"):""%>';
	
	
		jQuery().ready(function () {
			$('#guardarNarraTiva').hide();
			cargaFechaCaptura();
			cargaHoraCaptura();
			$('#imprimirNarraTiva').click(crearPdf);
			$('#guardadoParcialNarrativa').click(guardadoParcial);
			
			

			cargarDocumento();
			cargarDatosExpediente();
			
			
		
			
			
		});
		function guardadoParcial(){
			
			
			
			var recuperaTexto=escape($('.jquery_ckeditor').val());
			
			$.ajax({
		    	type: 'POST',
		    	url: '<%=request.getContextPath()%>/GenerarDocumento.do',
    			data: 'parcial=true&formaId=<%=request.getParameter("formaId")%>&numeroUnicoExpediente='+numeroUnicoExpediente+'&audienciaId='+idAudiencia+'&documentoId=<%=request.getParameter("documentoId")!=null?request.getParameter("documentoId"):""%>&texto='+recuperaTexto+'',		    					    	
		    	dataType: 'xml',
		    	success: function(xml){
		    		$('#iNumeroOficio').val($(xml).find('long').first().text());			
					$('#iNumeroOficio').attr('disabled',true);
		    		alertDinamico("Guardado exitoso");		    				    		
		    	},
		    	error: function(xml){		    		
		    	}
			});
		}
		function cargarDocumento(){
			
			parametros ="";
			
			<%
			for(Object llave:request.getParameterMap().keySet()){
				
				%>
				parametros +=  '<%=llave.toString()%>=<%=request.getParameter(llave.toString())%>&';
				<%
			}
			
			%>
			
			
			
			$.ajax({
		    	type: 'POST',
		    	url: '<%=request.getContextPath()%>/CargarDocumento.do',
		    	data: parametros,
		    	dataType: 'xml',
		    	success: function(xml){
		    		
		    		$('.jquery_ckeditor').val( $(xml).find('body').text());
		    	}
			});
		}
		
		function cargarDatosExpediente(){
			
			if(!esconderArbol){
				$.ajax({
			    	type: 'POST',
			    	url: '<%=request.getContextPath()%>/CargarArbolExpedienteEnDocumento.do',
			    	data: 'numeroUnicoExpediente='+numeroUnicoExpediente,
			    	dataType: 'xml',
			    	success: function(xml){
			    		
			    		contenido = escribirDatosGenerales(xml);			
			    		contenido += escribirInvolucrados(xml);
			    		contenido += escribirListaObjetos(xml);
			    		contenido += escribirDelitos(xml);
			    		contenido += escribirHechos(xml);
			    		
			    		
			    		$("#accordionDatosExpediente").append(contenido);
			    		
			    		
			    		$("#accordionDatosExpediente").treeview();
			    	}
				});
				
			}else{
				$("#marcoArbolExpediente").css('display','none');
				$("#idExpedientes").css('display','none');
				$("#tdArbolExp").css('width','1px');
						
			}
			
			
		}
		
		function escribirHechos(xml){
			resultado = "<li><span class='folder'>Hechos</span>"+
			"<ul>" +		
			"<span class='nike'>"+$(xml).find("hechos").first().text()+"</span>"+
			"</ul>";
			
			return resultado;
			
		}
		
		function escribirInvolucrados(xml){
			resultado = "<li><span class='folder'>Involucrados</span>"+
						"<ul>";			
			    					
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Denunciantes","denunciantes","Denunciante");	
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Víctimas Persona","victimasPersona","Víctima Persona");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Probables Responsables Personas ","probablesResponsablesPersona","Probable Responsable");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Testigos","testigos","Testigo");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Traductores","traductores","Traductor");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Quienes Detuvieron","quienDetuvo","Quien Detuvo");
			
		
			    		//escribir Organizaciones
			    		
			    		resultado += "<li><span class='folder'>Organización</span>"+
			    						"<ul>";
		
			
							resultado += escribeHtmlOrganizacion(xml,"Denunciante","denunciantesOrganizacion");		
			
							resultado += escribeHtmlOrganizacion(xml,"Víctima","victimasOrganizacion");		
			
							resultado += escribeHtmlOrganizacion(xml,"Probable Responsable","probablesResponsablesOrganizacion");

						resultado += "</ul></li>";

				
				resultado+="</ul></li>";
			    						
			return resultado;
			    						
		}
		
		function escribirDatosGenerales(xml){
			
			resultado = "<li class='opened'><span class='folder'>Datos Generales</span>" +
    		"<ul><span class='nike' title='Número de Expediente'>"+$(xml).find('expedienteResumenDTO').find('numeroExpediente').first().text()+"</span></ul>"+
    		"<ul><span  class='nike' title='Ciudad de Expedición'>"+$(xml).find('expedienteResumenDTO').find('estado').text()+"</span></ul>"+
    		//"<ul><span  class='nike' title='Estado de Expedición'>Yucatán</span></ul>"+
    		"<ul><span  class='nike' title='Hora Apertura'>"+$(xml).find('expedienteResumenDTO').find('strHoraActual').first().text()+"</span></ul>"+
    		"<ul><span  class='nike' title='Fecha Apertura'>"+$(xml).find('expedienteResumenDTO').find('strFechaActual').first().text()+"</span></ul>"+
    		"<ul><span  class='nike' title='Delito Principal'>"+$(xml).find('expedienteResumenDTO').find('delitoPrincipal').find("nombreDelito").first().text()+"</span></ul>"+
				"</li>";
			
			return resultado;
			
		}
		
		function escribirListaObjetos(xml){
			resultado ="<li><span class='folder'>Objetos</span>"+
							"<ul>";
			
							$(xml).find("grupoObjetosExpediente").find("grupoObjetosExpedienteDTO").each(function (){
								
								resultado+= "<li><span class='folder'>"+$(this).find("descripcionGrupo").text()+"</span>";
									
								$(this).find("objetos").find("objetoResumenDTO").each(function(){
									
									resultado+= "<ul><span class='nike'>"+$(this).find("descripcionResumen").text()+"</span></ul>";
									
									
								});
								
								
								resultado+="</li>";
								
								
							});
			
			
			
			resultado+="</ul></li>";
			return resultado;
		}
		
		function escribeHtmlOrganizacion(xml,tituloSeccion,tagSeccion){
			resultado =  "<li><span class='folder'>"+tituloSeccion+"</span>"+
						"<ul>";
			
			$(xml).find(tagSeccion).find("involucradoDTO").each(function (){
    			
				nombre = $(this).find("organizacionDTO").find("representanteLegal").find("nombresDemograficoDTO").first();
    			
    			resultado += "<li><span class='nike'>"+$(this).find("organizacionDTO").find("nombreOrganizacion").text()+"</span>"+
    							"<ul><span class='nike'>"+
    							nombre.find("nombre").text() + " " + nombre.find("apellidoPaterno").text() + " " + nombre.find("apellidoMaterno").text()+
    							"<span></ul>" +
    						"</li>";
    			
    		});	
						
						
			resultado += "</ul></li>";	
			
			return resultado;
		}
		
		function escribirDelitos(xml){
			resultado = "<li><span class='folder'>Delitos</span>";
			
			try{
			$(xml).find("expedienteResumenDTO").find("delitos").find("delitoDTO").each(function (){
    			
    			
    			resultado += "<ul><span class='nike'>"+$(this).find("nombreDelito").text()+"</span></ul>";
    			
    		});	
			}catch(e){}
			
			
			
			resultado += "</li>";
		
			return resultado;
			
			
		}
		
		function escribeHtmlSeccionTipoInvolucrado(xml,tituloSeccion,idInvolucrado,tituloInvolucrado){
			resultado = "<li  class='opened'><span class='folder'>"+tituloInvolucrado+"</span>";
			
			
    		
    		$(xml).find(idInvolucrado).find("involucradoDTO").each(function (){
    			
    			
    			resultado += escribirHtmlInvolucradto(this,tituloInvolucrado);
    			
    		});		
    		
    		resultado += "</li>";
    		
    		return resultado;
		}
		
		function escribirHtmlInvolucradto(xml,tipoInvolucrado){
			htmlInvolucrado = "";
    			
    		objNombre = $(xml).find("nombresDemograficoDTO").find("nombreDemograficoDTO").first();
    			
    		
    		htmlInvolucrado = 
    		"<ul><span class='nike' title='Nombre Completo'>"+$(objNombre).find("nombre").text()+ " " + 
    		$(objNombre).find("apellidoPaterno").text() + " " + $(objNombre).find("apellidoMaterno").text()+"</span></ul>";
			
    		return htmlInvolucrado;
		}

		/*
		*Funcion que recuepera el texto del editor, y lo envia como una nueva peticion 
		*para que se imprima con formato PDF
		*/
		function crearPdf(){
			 if(confirm("¿Est\u00E1 seguro que quiere guardarlo definitivamente?")) {
				//mostramos los divs en el padre de la pestaña de Acciones.
					try{window.parent.muestraDIVSCanalizacion();}catch(e){}
					
					try{
						setTimeout("window.parent.documentoGenerado()",5000);
						setTimeout("deshabilitarBotonesGuardado()",500);
						}catch(e){}
					var recuperaTexto=$('.jquery_ckeditor').val();
					document.frmDoc.parcial.value = "";
					document.frmDoc.texto.value = recuperaTexto;

					document.frmDoc.iNumeroOficio.value = $("#iNumeroOficio").val();
					//alert(document.frmDoc.numeroUnicoExpediente.value);					

					document.frmDoc.submit();
					customAlert("El documento se ha generado exitosamente.", "", cerrarVentaDocumentoActualizarGrid);
					pintaChecksTipoAtencion();
					//setTimeout('pintaNumeroFolio()',3000);										
					
			 }			
		}
		
		function pintaNumeroFolio(){
			iNumeroOficio = '<%=request.getSession().getAttribute("documentoId")!=null?request.getSession().getAttribute("documentoId"):""%>';
			$('#iNumeroOficio').val(iNumeroOficio);			
			$('#iNumeroOficio').attr('disabled',true);
		}

		function cerrarVentaDocumentoActualizarGrid(){
			window.parent.documentos();
			window.parent.cerrarVentanaDocumento();
		}
		
		function pintaChecksTipoAtencion(){
			pintaTiposAtencion='<%=request.getParameter("pintaTiposAtencion")%>';
			if(pintaTiposAtencion != null && pintaTiposAtencion>0 ){
				try{window.parent.pintaCheckTipoAtencion(pintaTiposAtencion);}catch(e){}
			}
		}

		
		/**
		* Deshabilitar los botones de guardado parcial y guardado definitivo, esta función se invoca 
		* después del guardado parcial
		*/
		function deshabilitarBotonesGuardado(){
			
			$('#imprimirNarraTiva').unbind();
			$('#guardadoParcialNarrativa').unbind();
			
		}
		/*
		*Funcion que dispara el Action para consultar catalogos
		*/
		function cargaCatalogos() {
	
			$('#idDelitosCaso').empty();
		    $.ajax({
				type: 'POST',
		   	  	url: '<%=request.getContextPath()%>/consultaCatalogosCaso.do',
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
		    	url: '<%=request.getContextPath()%>/consultaCatalogosCaso.do',
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
	
	   
	   /*
		*Funcion que carga la fecha actual del sistema y la agrega en la pantalla al campo fecha
		*/
		function cargaFechaCaptura(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
		    	data: '',
		    	dataType: 'xml',
		    	success: function(xml){
		    		$('#generarDocumentoCmpFechaIngreso').val($(xml).find('fechaActual').text());
		    	}
			});
		}
	
	   
	   /*
		*Funcion que carga la hora actual del sistema y la agrega en la pantalla al campo hora
		*/
		function cargaHoraCaptura(){
	    	$.ajax({
	    		type: 'POST',
	    	    url: '<%=request.getContextPath()%>/ConsultarHoraCaptura.do',
	    	    data: '',
	    	    dataType: 'xml',
	    	    success: function(xml){
	    			$('#idHoraDate').val($(xml).find('horaActual').text());
	    		}
			});
	    }

		function imprime(){
			
			var texto=escape($('.jquery_ckeditor').val());
			
			$.ajax({
				async: false,
	    		type: 'POST',
	    	    url: '<%=request.getContextPath()%>/GenerarDocumento.do',
	    	    data: 'texto='+escape($('.jquery_ckeditor').val()),
	    	    dataType: 'xml',
	    	    success: function(xml){
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
	
	<table align="center" border="0" width="820px" height="50%">
		<tr>
			<td colspan="4">
				<ul class="toolbar">
					<div id="menu_head">
						
						<li id="guardadoParcialNarrativa" class="first"><span></span>Guardado Parcial</li>
					<!--<li id="tbarBtnHeaderZise" class="first"><span></span>Guardado Definitivo</li>-->
						<li id="imprimirNarraTiva"><span></span>Guardado Definitivo</li>
						
					</div>
				</ul>
			</td>
	  	</tr>
		<tr>
			<td width="20%">Nombre Servidor P&uacute;blico:</td>
			<td width=""><input type="text" title="Nombre del Servidor Publico" size="40" id="iNombreServidorPublico" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
			<td width="">Hora de Elaboraci&oacute;n:</td>
			<td width=""><input type="text" id="idHoraDate" disabled="disabled" size="30" style=" border:0; background-color:#EEEEEE;"/></td>
		</tr>
		<tr>
			<td width="20%">Cargo:</td>
			<td width=""><input type="text" title="Cargo" size="40" id="iPuesto" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
			<td width="">Fecha de Elaboraci&oacute;n:</td>
			<td width=""><input type="text" id="generarDocumentoCmpFechaIngreso" name="generarDocumentoCmpFechaIngreso" disabled="disabled" size="30" style=" border:0; background-color:#EEEEEE;"/></td>
		</tr>
		<tr>
			<td>&Aacute;rea Administrativa:</td>
			<td><input type="text" title="Area Administrativa" size="40" id="iAreaAdministrativa" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
			<td>Ciudad:</td>
			<td><input type="text" title="Ciudad" size="30" id="iCiudad" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
		</tr>
		<tr>
			<td width="20%">N&uacute;mero de Oficio:</td>
			<td width=""><input type="text" title="Numero de Oficio" size="40" id="iNumeroOficio"  /></td>
			<td>Estado:</td>
			<td><input type="text" title="Estado" size="30" id="iEstado" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
		</tr>
	</table>
		
	<table align="center" width="1024px" >
		<tr>
			
			<td width="300px" valign="top" id="tdArbolExp">
				

				
				<h3><a href="#" id="idExpedientes">Elementos del Expediente</a></h3>
				
				<div style="height: 800px; 
						width: 300px;
						overflow: auto;
						border: 1px solid #666;
						padding: 0px;" id="marcoArbolExpediente">
						<ul id="accordionDatosExpediente" class="filetree">
						</ul>


						
				
			</div>
			

		

			
				
				
			</td>
			<td width="1000px" valign="top" align="center">
				<div style="margin-top: 0; margin-bottom: auto; vertical-align: top;margin-right: auto; margin-left: auto"  >
				<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
				</div>
				<form name="frmDoc" action="<%= request.getContextPath() %>/GenerarDocumento.do" method="post">
					<input type="hidden" name="texto" value=""/>
					<input type="hidden" name="parcial" value=""/>
					<input type="hidden" name="formaId" value="<%=request.getParameter("formaId")!=null?request.getParameter("formaId"):"" %>"/>
					<input type="hidden" name="numeroUnicoExpediente" value="<%=request.getParameter("numeroUnicoExpediente")!=null?request.getParameter("numeroUnicoExpediente"):"" %>"/>
					<input type="hidden" name="documentoId" value="<%=request.getParameter("documentoId")!=null?request.getParameter("documentoId"):"" %>"/>
					<input type="hidden" name="tipoOperacion" value="<%=request.getParameter("tipoOperacion")!=null?request.getParameter("tipoOperacion"):"" %>"/>
					<input type="hidden" name="estatusSolicitud" value="<%=request.getParameter("estatusSolicitud")!=null?request.getParameter("estatusSolicitud"):"" %>"/>
					<input type="hidden" name="idResolutivo" value="<%=request.getParameter("idResolutivo")!=null?request.getParameter("idResolutivo"):"" %>"/>
					<input type="hidden" name="iNumeroOficio" value="<%=request.getParameter("iNumeroOficio")!=null?request.getParameter("iNumeroOficio"):"" %>"/>
				</form>
				
			</td>
		</tr>
	</table>
	
	
</body>
</html>