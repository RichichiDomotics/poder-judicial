<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Ingresar Otros</title>
        <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
        <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
        <script src="<%=request.getContextPath()%>/js/prettify.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
        
        <script src="<%=request.getContextPath()%>/js/comun.js"></script>
        
        <script type="text/javascript">
        
	    	var idOtros="";
	    	var tipoObjeto="";
	    	var numeroExpediente="";
	    	var cadenaCustodia="";
	    	var fechaLevCadCus="";
	    	var origenEvdCadCus="";
	    	var deshabilitarCampos = window.parent.deshabilitarCamposPM;
	    	var ocultaAnularObjetoCadCus=null;
	    	
	    	var contextoPagina = "${pageContext.request.contextPath}";
    	
            jQuery().ready(
            function () {
            	//Instruccion pensada solo para el caso de policia ministerial
    			if(deshabilitarCampos == true){
    				$(":enabled").attr('disabled','disabled');
    			}	
                numeroExpediente='<%= request.getParameter("numeroExpediente")%>';
        		idOtros='<%= request.getParameter("idOtros")%>';
    			cadenaCustodia='<%= request.getParameter("cadenaCustodia")%>';
    			fechaLevCadCus='<%= request.getParameter("fechaLevCadCus")%>';
    			origenEvdCadCus='<%= request.getParameter("origenEvdCadCus")%>';
    			
    			//lineas para ocultar la opcion de anular objeto alconsultar desde una cadena de custodia
    			ocultaAnularObjetoCadCus='<%= request.getParameter("anularConsultaCadCus")%>';
    			if(ocultaAnularObjetoCadCus!=null && ocultaAnularObjetoCadCus!=0)
    			{
    				$("#anularElemento").hide("");
    				$("#anularInv").hide("");
    			}
    			
    			if (idOtros != null && idOtros != 0)
    				$("#imgConFoto").attr("src",'<%=request.getContextPath()%>/obtenImagenDeElemento.do?elementoID=<%= request.getParameter("idOtros")%>');
    			else{
    				$("#imgConFoto").attr("src","<%=request.getContextPath()%>/resources/images/Foto.JPG");
    			}
    			
    			if(idOtros=='null')
    			{
    				idOtros=0;	
    			}
    			tipoObjeto='<%= request.getParameter("tipoObjeto")%>';
    			var num=parent.num;
    			//revisamos si es una consulta
    			if(idOtros!=null && idOtros!=0)
    			{
    				consultaOtros();
    				//condicional para no mostrar el boton de anular objeto cuando entramos desde cadena de custodia
    				if(ocultaAnularObjetoCadCus!='null' && ocultaAnularObjetoCadCus!=0)
    				{
    					//alert("1");
    					$("#anularElemento").hide("");
    					$("#anularInv").hide("");
    				}
    				else
    				{
    					//alert("2");
    					$("#anularElemento").show("");
    					$("#anularInv").show("");
    				}
    			}
    			else
    			{
	    			if(num!=null && num!="0"){
	    				$("#anularElemento").hide();
	    				$("#anularInv").hide();
	    			}
    			}
            });

            
        	/*
        	*Funcion para mandar consultar el objeto
        	*/
        	function consultaOtros()
        	{
        		$.ajax({
            		type: 'POST',
            		url: '<%=request.getContextPath()%>/ConsultaObjetoPorId.do',
            		data: 'idObjeto='+idOtros,
            		dataType: 'xml',
            		async: false,
            		success: function(xml){
            			if(parseInt($(xml).find('code').text())==0)
        	    		{
            				//seteamos la informacion del hecho
            				$(xml).find('ObjetoDTO').each(function(){
            						seteaDatosOtros($(this));
        		    	      });
        	    		}
            		}	
            	});
        	}

        	function seteaDatosOtros(xml)
        	{
        		$('#txtNombre').val($(xml).find('nombreObjeto').text());
        		if($(xml).find('almacen'))
        			$(xml).find('almacen').remove();
        		$('#txtBoxDescOtros').val($(xml).find('descripcion').text());
        	}
        	
        	/*Funcion que permite guardar una imagen y la asocia a un elemento*/
			function enviaImagenDeElemento(idElemento){
					document.frmImagenElemento.elementoID.value = idElemento;
			    	document.frmImagenElemento.submit();
			}	
        	
            //	COMIENZAN FUNCIONES PARA EL GUARDADO DE LOS DATOS
            function obtenerValoresOtros(){		
                var paramOtros = "idOtros="+idOtros;  
                paramOtros += '&gcNombre=' + $("#txtNombre").val();
                paramOtros += '&gcDescripcion=' + $("#txtBoxDescOtros").val();
                
        		if(cadenaCustodia!='null')
        		{
        			paramOtros += '&cadenaCustodia=' + cadenaCustodia;
        			paramOtros += '&origenEvdCadCus=' + origenEvdCadCus;
        			paramOtros += '&fechaLevCadCus=' + fechaLevCadCus;
        		}
                
                $.ajax({
                    async: false,
                    type: 'POST',
                    url: '<%= request.getContextPath()%>/ingresarOtros.do?numeroExpediente='+numeroExpediente +'',
                    data: paramOtros,
                    dataType: 'xml',
                    success: function(xml){
      				  var id = $(xml).find('OtrosForm').find('idOtros').text();
      				  if(idOtros==0 && id>0)
      				  {	
      					window.parent.alertDinamicoEvCadCus("Se guardó correctamente la información");					  
      					  //Para guardar la imagen
      					enviaImagenDeElemento(id);
      					  
      				  }
      				  else if(idOtros==0 && id==0)
      				  {
      					window.parent.alertDinamicoEvCadCus("Favor de revisar la información capturada");
      				  }
      				  else
      				  {
      					enviaImagenDeElemento(id);
      					window.parent.alertDinamicoEvCadCus("La información se actualizó correctamente");
      					  //window.parent.cargaOtros(id,$("#txtNombre").val());
      				  }
      				  window.parent.cargaOtros("","");
                    }
                });
            }
            
            function validaCamposObligatorios(){
            	var nombreOtros = $("#txtNombre").val();
            	var mensaje = "";
        		//Primera validacion por cada campo obligatorio		
        		if(nombreOtros == ""){
        			mensaje += "<br />- Nombre";
        		}
        		//Comienza segunda validacion para validacion de consistencia de expresiones regulares
        		if(mensaje != ""){
        			//mensaje += "\n\nEs necesario...."; FUTURAS VALIDACIONES
        			window.parent.alertDinamico("<p align='left'>Favor de revisar los siguientes campos obligatorios: <br />"+mensaje+"</p>");
        		}else{			
        			obtenerValoresOtros();
        		}
            }
            
            /*
        	*Funcion que manda a eliminar logicamente el objeto en la BD
        	*/
        	function anularObjeto(){
        		var paramOtros="idObjeto="+idOtros;
        		$.ajax({		
        			async: false,
        			type: 'POST',
        			url: '<%= request.getContextPath()%>/anularObjetoPorId.do',
        			data: paramOtros,
        			dataType: 'xml',
        			success: function(xml){
        				//procederemos a tratar de eliminar la evidencia
        				if(parseInt($(xml).find('bandera').text())==1)
        				{
        					//se anulo exitosamente el objeto , actualizamos el grid de menu intermedio y cerramos la ventana
        					window.parent.cargaOtros("","");
        					window.parent.cerrarVentanaOtros();
        				}
        				if(cadenaCustodia=='null'){
        					//alert("cadCus::"+cadenaCustodia);
        					window.parent.alertDinamico($(xml).find('mensajeOp').text());
        				}
        			}
        		});

        	}
        	
        	/*
        	*Funcion para anular el objeto
        	*/
        	function solicitarAnlrObjeto(){
        		//revisamos si es una consulta
        		if(idOtros!=null && idOtros!=0)
        		{
        			//procederemos a tratar de eliminar la evidencia
        			customConfirm ("¿Está seguro que desea anular el objeto?", "", validarObjEvdncNoEslbns);
        		}
        	}
        	
        	/*
        	*Funcion que validara si el objeto es evidencia y NO tiene eslabones, de ser asi
        	*se debe confirmar que se desea eliminar dado que el objeto ya esta en una cadena de custdia
        	*/
        	function validarObjEvdncNoEslbns()
        	{
        		var paramOtros="idObjeto="+idOtros;
        		$.ajax({		
        			async: false,
        			type: 'POST',
        			url: '<%= request.getContextPath()%>/validarObjPorIdEvdncNoEslbns.do',
        			data: paramOtros,
        			dataType: 'xml',
        			success: function(xml){
        				//procederemos a tratar de eliminar la evidencia
        				if(parseInt($(xml).find('bandera').text())==1)
        				{
        					//debemos mostrar un confirm
        					customConfirm ("El objeto es ya una evidencia en alguna cadena de custodia <br/> ¿Está seguro que desea anular el objeto?", "", anularObjeto);
        				}
        				else if(parseInt($(xml).find('bandera').text())==2)
        				{
        					//se puede eliminar el objeto sin problemas
        					anularObjeto();
        				}
        				else
        				{
        					window.parent.alertDinamico($(xml).find('mensajeOp').text());
        				}
        			}
        		});
        	}
        	
    		window.onload = function(){
    			var selects = document.getElementsByTagName("textarea");
    			for (var i = 0; i < selects.length; i++) { 
    				if(selects[i].getAttribute("maxlength") > 0){
    					selects[i].onkeydown = function(){
    		                            if (this.value.length > this.getAttribute("maxlength")) 
    		                                this.value = this.value.substring(0, this.getAttribute("maxlength"));
    		                        }
    		                        selects[i].onblur = function(){
    		                            if (this.value.length > this.getAttribute("maxlength")) 
    		                                this.value = this.value.substring(0, this.getAttribute("maxlength"));
    		                        }
    				}
    			}
    		}
        </script>
    </head>
    <body>
                    <table width="400px"  height="210px" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr><td>&nbsp;</td></tr>
                    	<tr height="12.5%">
                            <td width="26%" id="anularInv"></td>
                            <td width="26%" align="right">
                            	<input type="button" value="Anular objeto" class="btn_Generico"id="anularElemento" onclick="solicitarAnlrObjeto()"/>&nbsp;&nbsp;
                            	<input type="button" id="btnGuardarOtros" value="Guardar" onclick="validaCamposObligatorios();" class="btn_Generico"/>
                            </td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr height="12.5%">
                            <td width="100%" colspan="2" align="center">Nombre: <input type="text" maxlength="30" style="width: 330px;" id="txtNombre"/></td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr height="12.5%">
                            <td width="26%" align="center">Descripci&oacute;n:</td>
                            <td width="26%" align="center">Fotografía:</td>
                        </tr >
                        <tr height="12.5%">
                            <td width="26%" rowspan="6" align="center" valign="top">
                                <textarea cols="29" rows="9" id="txtBoxDescOtros" maxlength="200"></textarea>
                            </td>
                            <td width="26%" rowspan="6" align="center" valign="middle">
                                <img id="imgConFoto" src="<%=request.getContextPath()%>/resources/images/Foto.JPG" alt="foto del objeto" width="185" height="185" />
                            </td>
                        </tr>                        
                    </table>
        <table width="401px" border="0" cellpadding="0" cellspacing="0">
        <tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<form id="frmImagenElemento" name="frmImagenElemento" action="<%= request.getContextPath() %>/ingresarImagenDelElementoObraDeArte.do" method="post" enctype="multipart/form-data" align="left">
				         		<input type="hidden" name="elementoID"/>
				                <input type="file" name="archivo" id="uploadArchivo" width="350px;">
	        	</form>
			</td>
		</tr>			
		</table>
    </body>
</html>