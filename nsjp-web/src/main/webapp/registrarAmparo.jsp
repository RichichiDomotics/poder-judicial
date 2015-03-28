
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.gob.segob.nsjp.comun.enums.institucion.Instituciones"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registrar Amparo</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
        <script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
        <script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>                
        <script type="text/javascript" 	src="<%=request.getContextPath()%>/js/comun.js"></script>
        
        <script type="text/javascript">
       
         var CONTEXT_ROOT = '<%= request.getContextPath()%>'; //nsjp-web
         var idExpediente='<%= request.getParameter("idExpedienteSoli")%>';
         var idNumeroExpediente='<%= request.getParameter("idNumeroExpediente")%>';
         var numeroGeneralCaso='<%= request.getParameter("numeroGeneralCaso")%>';

         var success='<%= request.getParameter("success")%>';
         
         $(document).ready(function() {
        	 //alert("idExpediente: " + idExpediente)
        	 //alert("idNumeroExpediente: " + idNumeroExpediente)
        	 consultarInvolucradosPorExpediente();        	 
         	//Permite mandar un mensaje despues de registrar un amparo
         	//dado que el action redirige de nuevo a este mismo jsp
         	//alert(success)
         	if(success != "null"){
         		if(parseInt(success) == 1)	
             		alert("El amparo se registró de forma correcta")
             	else
             		alert("Error al registrar el amparo")	
             	window.parent.cierraVentanaRegistrarAmparo();
         	}         	
         	
         	$("#btnGuardar").click(registraAmparo);
         });
         
         function registraAmparo(){
     		var idsSeleccionados = jQuery("#gridInvolucradosPorExpediente").jqGrid('getGridParam','selarrrow');
        	//alert(idsSeleccionados);
        	 //Recupera los id´s de los involucrados a asociar.
        	if(validadDatosObligatorios()){
        		adjuntarArchivoActuaciones();
        	}else{
        		
        	}
         }
         
         function validadDatosObligatorios(){ 
      		var idsSeleccionados = jQuery("#gridInvolucradosPorExpediente").jqGrid('getGridParam','selarrrow');
        	 if($('#txtFolio').val().trim() == "" || $('#fileAdjuntarArchivo').val().trim() == "" || parseInt(idsSeleccionados.length) <= 0){
        		 var msjDeError = "";
        		 msjDeError = "Los siguientes campos son requeridos: \n";
        		 if($('#txtFolio').val().trim() == "") 
        		 	msjDeError += " - Folio \n";
        		 if($('#fileAdjuntarArchivo').val().trim() == "") 
         		 	msjDeError += " - Archivo adjunto. \n";
        		 if(parseInt(idsSeleccionados.length) <= 0)
        			 msjDeError += " - Involucrado(s) que ampara";       		 
        		 alert(msjDeError);
        		 return false;
        	 }else
        		 return true;
         }
         
         function consultarInvolucradosPorExpediente(){
	               jQuery("#gridInvolucradosPorExpediente").jqGrid({
	                    url:'<%=request.getContextPath()%>'+'/consultarInvolucradosPorExpediente.do?idExpediente='+ idExpediente,
	                    datatype: "xml",
	                    colNames:['Nombre completo','Calidad'],
	                    colModel:[
	                        {name:'nombre',index:'1',  sortable:false, width:200},
	                        {name:'calidad',index:'2',  sortable:true, width:200},
	                    ],
	                    pager: jQuery('#paginadorInvolucradosPorExpediente'),
	                    rowNum:10,
	                    rowList:[10,20,30],
	                    caption:"* Involucrado(s) que ampara",
	                    multiselect: true,
	                    sortname: '2',
	                    viewrecords: true,
	                    sortorder: "asc"
	                }).navGrid('#paginadorInvolucradosPorExpediente',{edit:false,add:false,del:false});
					$("#gridInvolucradosPorExpediente").trigger("reloadGrid");	
			}
     	
         //manda archivo al accion para asociarlo al expediente
 		function adjuntarArchivoActuaciones(){
 			    //alert('Debe de mandar el archivo al expediente')
 			    
 			    var folio = $('#txtFolio').val().trim();
 			    var descripcion = $('#txtDescripcion').val().trim();
 			    var idsSeleccionados = jQuery("#gridInvolucradosPorExpediente").jqGrid('getGridParam','selarrrow');
			    
 				document.adjuntarDocForm.tipo.value = 1;
 				document.adjuntarDocForm.expedienteId.value = idExpediente;
 				document.adjuntarDocForm.folio.value = folio;
 				document.adjuntarDocForm.descripcion.value = descripcion;
 				document.adjuntarDocForm.idsSeleccionados.value = idsSeleccionados;
 				document.adjuntarDocForm.numeroGeneralCaso.value = numeroGeneralCaso;
		 		forma = document.adjuntarDocForm;
 				forma.submit();
 		}
         		
        </script>
    </head>
<body>

<table width="436" border="0" summary="Tabla para registrar un amparo">
  <tr>
     <td width="139" align="right"><label for="txtFolio">* Folio:</label></td>
     <td width="287"><input type="text" name="txtFolio" id="txtFolio" tabindex="1" maxlength="20" size="20">
</td>
  </tr>
   <tr>
     <td align="right"><label for="txtDescripcion">Descripci&oacute;n:</label></td>
     <td><p>
       <textarea name="txtDescripcion" id="txtDescripcion" cols="45" rows="5" tabindex="2"></textarea>
       </p>
   </tr>
   <tr>
     <td align="right">* Archivo adjunto:</td>
     <td>
	     <label for="fileAdjuntarArchivo"></label>
	     
	     <form id="adjuntarDocForm" name="adjuntarDocForm" action="<%= request.getContextPath() %>/adjuntarAmparoAExpediente.do" method="post" enctype="multipart/form-data" >
								<input id="fileAdjuntarArchivo" type="file" name="archivoAdjunto" value="" tabindex="3" >
								<input type="hidden" name="expedienteId" />
								<input type="hidden" name="tipo" />
								<input type="hidden" name="folio" />
								<input type="hidden" name="descripcion" />
								<input type="hidden" name="idsSeleccionados" />
								<input type="hidden" name="numeroGeneralCaso" />								
								
		 </form>
	     
     </td>
   </tr>
   <tr>
     <td colspan="2" align="center">
	    <div id="divGridInvolucradosPorExpediente"> 
	    	<table id="gridInvolucradosPorExpediente"></table>
	        <div id="paginadorInvolucradosPorExpediente"></div>
	    <div>
	</td>
   </tr>
   <tr>
     <td colspan="2" align="right">       
     	<br/>
     	<input type="button" name="btnGuardar" id="btnGuardar" value="Guardar" tabindex="4" class="btn_Generico">
     </td>
   </tr>
</table>

</body>
</html>