<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Elaborar Solicitud</title>
	<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
	<!--iframe que crea una nueva peticion para imprimir un PDF-->
	<iframe id="framePdf" src="" width="0" height="0"></iframe>
	
	<!--		Hojas de estilos asociadas-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

	<script type="text/javascript">
	
	var idWindowPdf = 1;
	var esconderArbol = <%=request.getParameter("esconderArbol")!=null?"true":"false"%>;
	var numeroUnicoExpediente = '<%=request.getParameter("numeroUnicoExpediente")!=null?request.getParameter("numeroUnicoExpediente"):"Sin numero"%>';
	var idResolutivo= '<%=request.getParameter("idResolutivo")!=null?request.getParameter("idResolutivo"):"Sin Resolutivo"%>';
	var idAudiencia= '<%=request.getParameter("idAudiencia")!=null?request.getParameter("idAudiencia"):"Sin Audiencia"%>';
	var numExpIdGlobal=0;
	var idWindowIngresarDenunciante = 1;
	var idWindowIngresarVictima = 1;
	var idWindowIngresarProbResponsable = 1;
	var idWindowIngresarTestigo = 1;
	var idWindowIngresarTraductor = 1;
	var idWindowIngresarQuienDetuvo = 1;
	
	var idTipoSolicitud=0;
	var idExpedienteOp="";
	var idRelacionDelito="";
	
	var idSolicitud="";
	var opcEnvio=0;
	var nombreAMPRemitente="";
	
		jQuery().ready(function () {
			$('#guardarNarraTiva').hide();
			cargaFechaCaptura();
			cargaHoraCaptura();
			$('#imprimirNarraTiva').click(crearPdf);
			$('#guardadoParcialNarrativa').click(guardadoParcial);
			cargarDocumento();
			cargarDatosExpediente();
						
			//Cambios para la pantalla de seleccionar destinatario
			//cargaComboInstitucion();		//Funcion que carga el combo de las Instituciones
			//$('#instituciones').change(enSeleccionInstitucion);
			//cargaCompoDepartamentos();
			//$('#areas').change(cargaCompoFuncionarios);
			//$('#areas').change(cargaCompoDepartamentos);
			//$('#departamentos').change(cargaCompoFuncionarios);
			
			$('#seleccionarDestinatario').hide();
			$('#menuSeleccionarDestinatario').click($('#seleccionarDestinatario').show());
			$('#tblUsuarioExterno').hide();
			$('#tblUsuarioSistema').hide();
			//$('#divGridUsuariosExt').hide();
			//$('#divGridUsuarios').hide();
			//cargaGridUsuarios();
			//cargaGridUsuariosExternos();
			//$('#relacionarElementos').click(abreVentanaRelacionarElementos);
		    var numeroExpedienteId=<%= request.getParameter("numeroExpedienteId")%>;
		    idTipoSolicitud=<%= request.getParameter("idTipoSolicitud")%>;
		    idRelacionDelito='<%= request.getParameter("idRelacionDelito")%>';
		    idSolicitud=<%= request.getParameter("idSolicitud")%>;
		    //alert("idSolicitud del generador de documento="+idSolicitud);
		    opcEnvio=<%= request.getParameter("opcEnvio")%>;
			numExpIdGlobal=numeroExpedienteId;
			llenaGridUsuarios();
		});
	
		function llenaGridUsuarios()
		{
			jQuery("#gridUsuarios").jqGrid({
				url:'<%= request.getContextPath()%>/consultaFuncionarioRemitenteSolucitudPorIdGridUAVD.do?idSolicitud='+idSolicitud+'', 
				datatype: "xml",
				height: 110,
				colNames:['ID','Nombre','Puesto', 'Correo','Principal','Copia','areaId'],
				colModel:[	
						  {name:'id',index:'id', width:60, sorttype:"int"},
				          {name:'nombre',index:'nombre', width:200},
				          {name:'puesto',index:'puesto', width:200},
				          {name:'direccion',index:'direccion', width:200},
				          {name:'principal',index:'principal', width:60},
				          {name:'copia',index:'copia', width:60},
				          {name:'areaId',index:'areaId', width:60, hidden:true}
			    ],
			    rowNum:10,
				autowidth: false,
				rowList:[10,20,30],
				pager: jQuery('#pager1'),
				viewrecords: true,								
				multiselect: false,
				hiddengrid: false,
			    caption: "Destinatario(s) Usuarios del sistema" 
		    });
			
			$.ajax({
		    	type: 'POST',
		    	url: '<%=request.getContextPath()%>/consultaFuncionarioRemitenteSolucitudPorIdXMLUAVD.do?idSolicitud='+idSolicitud+'',
		    	dataType: 'xml',
		    	success: function(xml){
		    		nombreAMPRemitente=$(xml).find('nombreFuncionario').text() + " "+$(xml).find('apellidoPaternoFuncionario').text()+ " "+$(xml).find('apellidoMaternoFuncionario').text();
		    	},
		    	error: function(xml){
		    		//alert("Guardado exitoso");
		    		
		    	}
			});
		}
		
		
		function guardadoParcial(){
			var recuperaTexto=$('.jquery_ckeditor').val();
			$.ajax({
		    	type: 'POST',
		    	url: '<%=request.getContextPath()%>/GenerarDocumento.do',
		    	data: 'parcial=true&formaId=<%=request.getParameter("formaId") %>&numeroUnicoExpediente='+numeroUnicoExpediente+
		    	'&documentoId=<%=request.getParameter("documentoId")!=null?request.getParameter("documentoId"):""%>&texto='+
		    			escape(recuperaTexto),
		    	dataType: 'xml',
		    	success: function(xml){
		    	},
		    	error: function(xml){
		    		//alert("Guardado exitoso");
		    		
		    	}
			});
			validadDatosSolicitud();
		}
		function cargarDocumento(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%=request.getContextPath()%>/CargarDocumento.do?idAudiencia='+idAudiencia+'&idResolutivo='+idResolutivo+'',
		    	data: 'formaId=<%=request.getParameter("formaId")%>&numeroUnicoExpediente='+numeroUnicoExpediente+
		    	'&documentoId=<%=request.getParameter("documentoId")!=null?request.getParameter("documentoId"):""%>',
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

		function crearDenunciante(){
			idWindowIngresarDenunciante++;
			$.newWindow({id:"iframewindowIngresarDenunciante" + idWindowIngresarDenunciante, statusBar: true, posx:150,posy:20,width:1040,height:570,title:"Ingresar Denunciante", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarDenunciante" + idWindowIngresarDenunciante,'<iframe src="<%= request.getContextPath() %>/IngresarDenunciante.do?numeroExpediente='+numeroUnicoExpediente +'&calidadInv=DENUNCIANTE&elemento=si" width="1040" height="570" />');		
		}

		function creaNuevaVictima() {
			idWindowIngresarVictima++;
			$.newWindow({id:"iframewindowIngresarVictima" + idWindowIngresarVictima, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Víctima", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarVictima" + idWindowIngresarVictima,'<iframe src="<%= request.getContextPath() %>/IngresarVictima.do?numeroExpediente='+numeroUnicoExpediente +'&elemento=si" width="1050" height="600" />');		
		}

		function creaNuevoProbResponsable() {
			idWindowIngresarProbResponsable++;
			$.newWindow({id:"iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable, statusBar: true, posx:250,posy:150,width:1050,height:620,title:"Ingresar Probable Responsable", type:"iframe"});
			$.updateWindowContent("iframewindowIngresarProbResponsable" + idWindowIngresarProbResponsable,'<iframe src="<%= request.getContextPath() %>/IngresarProbResponsable.do?numeroExpediente='+numeroUnicoExpediente +'&calidadInv=PROBABLE_RESPONSABLE&elemento=si" width="1050" height="620" />');		
		}

		//Crea una nueva ventana de testigo
		function creaNuevoTestigo() {
			idWindowIngresarTestigo++;
			$.newWindow({id:"iframewindowIngresarTestigo" + idWindowIngresarTestigo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Testigo", type:"iframe"});
		    $.updateWindowContent("iframewindowIngresarTestigo" + idWindowIngresarTestigo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigo.do?numeroExpediente='+numeroUnicoExpediente +'&elemento=si" width="1050" height="600" />');		
		}

		function creaNuevoTraductor() {
			idWindowIngresarTraductor++;
			$.newWindow({id:"iframewindow" + idWindowIngresarTraductor, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Traductor", type:"iframe"});
	    	$.updateWindowContent("iframewindow" + idWindowIngresarTraductor,'<iframe src="<%= request.getContextPath() %>/IngresarTraductor.do?numeroExpediente='+numeroUnicoExpediente +'&elemento=si" width="1050" height="600" />');		
		}

		function creaQuienDetuvo() {
			idWindowIngresarQuienDetuvo++;
		$.newWindow({id:"iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar Quién detuvo", type:"iframe"});
	    $.updateWindowContent("iframewindowQuienDetuvo" + idWindowIngresarQuienDetuvo,'<iframe src="<%= request.getContextPath() %>/IngresarQuienDetuvo.do?elemento='+0+'&numeroExpediente='+numeroUnicoExpediente+'" width="1050" height="600" />');
		}	

		function refresca(){
			$("#accordionDatosExpediente").empty();
			cargarDatosExpediente();
		}
		
		function escribirInvolucrados(xml){
			resultado = "<li><span class='folder'>Involucrados</span>"+
						"<ul>";			
						var op=true;
						$(xml).find("denunciantes").find("involucradoDTO").each(function (){
			    			op=false;
			    		});	
			    		if(op){
			    			resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Denunciantes","denunciantes","Denunciante","crearDenunciante()");
					    }else{
					    	resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Denunciantes","denunciantes","Denunciante","");
						}			
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Víctimas Persona","victimasPersona","Víctima Persona","creaNuevaVictima()");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Probables Responsables Personas ","probablesResponsablesPersona","Probable Responsable","creaNuevoProbResponsable()");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Testigos","testigos","Testigo","creaNuevoTestigo()");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Traductores","traductores","Traductor","creaNuevoTraductor()");
						resultado +=escribeHtmlSeccionTipoInvolucrado(xml,"Quienes Detuvieron","quienDetuvo","Quien Detuvo","");
			
		
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
		
		function escribeHtmlSeccionTipoInvolucrado(xml,tituloSeccion,idInvolucrado,tituloInvolucrado,funcion){
			resultado = "<li  class='opened'><span class='folder'>"+tituloInvolucrado+"<img onclick='"+funcion+"' src='<%= request.getContextPath() %>/resources/images/add.png'></span>";
        				
    		
    		$(xml).find(idInvolucrado).find("involucradoDTO").each(function (){
    			
    			
    			resultado += escribirHtmlInvolucradto(this,tituloInvolucrado);
    			
    		});		
    		
    		resultado += "</li>";
    		//resultado += "</li><input type='button' value='Ingresar'/>";
    		
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



		function obtenIdsDestinatariosInternos(){		
			var ids = "";
			var lista = jQuery("#gridUsuarios").getDataIDs();
			for(i=0;i<lista.length;i++){
					rowData= jQuery("#gridUsuarios").jqGrid('getRowData',lista[i]);
					ids = ids + rowData.id + ","; 
			 }
			return ids;			
		}

		function obtenerAreaDestino(){
			
			var areaDestino = "";
			var lista = jQuery("#gridUsuarios").getDataIDs();
			
			for(i=0;i<lista.length;i++){
				
				rowData= jQuery("#gridUsuarios").jqGrid('getRowData',lista[i]);
				
				if(rowData){
					areaDestino =  rowData.areaId;
				}
				break;
			 }
			 
			return areaDestino; 
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
			
			var texto=$('.jquery_ckeditor').val();

			$.ajax({
				async: false,
	    		type: 'POST',
	    	    url: '<%=request.getContextPath()%>/GenerarDocumento.do',
	    	    data: 'texto='+$('.jquery_ckeditor').val(),
	    	    dataType: 'xml',
	    	    success: function(xml){
	    			//$('#idHoraDate').val($(xml).find('horaActual').text());
	    			//alert("documento impreso");
	    		}
			});
		}
		
		
		/*
		*Funcion que recuepera el texto del editor, y lo envia como una nueva peticion 
		*para que se imprima con formato PDF
		*/
		function crearPdf(){
			if(validadDatosSolicitud() == 1){
				//mostramos los divs en el padre de la pestaña de Acciones.
				try{window.parent.muestraDIVSCanalizacion();}catch(e){}
				var recuperaTexto=$('.jquery_ckeditor').val();
				document.frmDoc.parcial.value = "";
				document.frmDoc.texto.value = recuperaTexto;
				document.frmDoc.submit();
			}
		}
		
		function validadDatosSolicitud(){
			totalDestinartario = jQuery('#gridUsuarios').jqGrid('getGridParam','records') + jQuery('#gridUsuarios').jqGrid('getGridParam','records');
			if(totalDestinartario >0 ){
				enviaDatosSolicitud();
				//imprime();
				return 1;
			}else{
				alert('Debe de agregar al menos un destinatario');
				return 0;
			}			
		}
		
		function enviaDatosSolicitud(){

			var textoMotivo = $('.jquery_ckeditor').val(); 
			
			var params = '&institucionSolicitante=' + 1;
			params += '&solicitante=' + "";
			params += '&numeroExpediente=' + numeroUnicoExpediente;
			params += '&idsFuncionariosSolicitantes=' + obtenIdsDestinatariosInternos();
			params += '&idSolicitud=' + $('#idSolicitud').val();
			params += '&idTipoSolicitud=' + idTipoSolicitud;
			params += '&motivo=' + escape(textoMotivo);
			params += '&areaDestino=' + obtenerAreaDestino();
			
		    $.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/registrarSolicitud.do',
				data: params, 
				async: false,
				dataType: 'xml',
				success: function(xml){
				
					if(parseInt($(xml).find('SolicitudDTO').find('documentoId').text())>0){
					
						if($('#idSolicitud').val() == 0){
						
							if(opcEnvio==1)//ENVIAR_ESTUDIO_SOCIOECONOMICO
							{
								alert("Se mando correctamente el oficio del estudio socioeconómico al Agente del Ministerio Público:\n"+nombreAMPRemitente);
							}
							else if(opcEnvio==2)//SEGUIMIENTO_A_ESTUDIO_SOCIOECONOMICO
							{
								alert("Se mando correctamente el estudio socioeconómico al Agente del Ministerio Público:\n"+nombreAMPRemitente);
							}
							else if(opcEnvio==3)//PROPORCIONAR_APOYO_LEGAL
							{
								 alert("Se mando correctamente el oficio del apoyo legal al Agente del Ministerio Público:\n"+nombreAMPRemitente);
							}
							else if(opcEnvio==4)//SUSPENDER AYUDA
							{
								 alert("Se mando correctamente el oficio para suspeder de ayuda al Agente del Ministerio Público:\n"+nombreAMPRemitente);
							}
							else if(opcEnvio==5)//CONCLUIR_SATISFACTORIAMENTE
							{
								 alert("Se mando correctamente el oficio de conclusión satisfactoria al Agente del Ministerio Público:\n"+nombreAMPRemitente);
							}
							else if(opcEnvio==6)//REINICIAR_AYUDA
							{
								 alert("Se mando correctamente el oficio para reiniciar la ayuda al Agente del Ministerio Público:\n"+nombreAMPRemitente);
							}
						}
						else{
							alert("La solicitud se actualiz\u00F3 correctamente.");
						}
						$('#idSolicitud').val(parseInt($(xml).find('SolicitudDTO').find('documentoId').text()));
					}
					else{
						alert('Error al intentar guardar la solictud, inténtelo mas tarde');
					}
						  
				}
			});		   		
		}
		</script>
</head>



<body>
	<!-- ETIQUETAS NECESARIAS PARA LOS CAMPOS DEL ENCABEZADO -->
	<table align="center" border="0" width="820px" height="50%">
		<tr><!-- MENU -->
			<td colspan="4">
				<ul class="toolbar">
					<div id="menu_head">
						<li id="imprimirNarraTiva"><span></span>Enviar</li>						
						<!-- <li id="tbarBtnConsultarTurnoAtencion" class="first">Salir</li> -->
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
			<td width=""><input type="text" title="Puesto" size="40" id="iPuesto" disabled="disabled" style=" border:0; background-color:#EEEEEE;"/></td>
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
		
	<!-- ETIQUETAS PARA LA SECCION DE LOS ELEMENTOS DEL EXPEDIENTE -->	
	<table align="center" width="1024px" border="0">
		<tr>			
			<td width="300px" valign="top" id="tdArbolExp">
				<h3><a href="#" id="idExpedientes">Elementos del Expediente</a></h3>
				
				<div style="height: 800px; 
						width: 300px;
						overflow: auto;
						border: 1px solid #666;
						padding: 0px;" id="marcoArbolExpediente">
						<ul id="accordionDatosExpediente" class="filetree"></ul>
				</div>
			</td>
			<td width="800px" valign="top" align="center">
				<div id="divGridUsuarios">				
					<table align="center" id="gridUsuarios" width="800px"></table>
					<div id="pager1"></div>				
					<br>
				</div>
				<div id="divGridUsuariosExt">
					<table align="center" id="gridUsuariosExt" width="800px"></table>
					<div id="pager2"></div>
				</div>
				<div style="margin-top: 0; margin-bottom: auto; vertical-align: top;margin-right: auto; margin-left: auto">
				<br>	
						
				<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
				<input type="hidden" size="20px" id="idSolicitud" value="0"/>
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
					
				</form>					
			</td>
		</tr>
	</table>
</body>

</html>