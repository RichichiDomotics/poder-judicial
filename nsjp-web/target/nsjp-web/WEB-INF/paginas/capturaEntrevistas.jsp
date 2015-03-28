<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar narrativa</title>
	   
	   <script src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" rel="stylesheet">
       <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" rel="stylesheet">
       <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	   <script type="text/javascript">
			$(document).ready(function() {
			var config = {					
				toolbar:
				[
					['Source','-','Save','NewPage','Preview','-','Templates'],
					['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
					['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
					['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
					'/',
					['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
					['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
					['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
					['BidiLtr', 'BidiRtl' ],
					['Link','Unlink','Anchor'],
					['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
					'/',
					['Styles','Format','Font','FontSize'],
					['TextColor','BGColor'],
					['Maximize', 'ShowBlocks','-']
				]

			};			
			
					$("#editor1").val('<p>Entrevista:</p><p>N&uacute;mero de Expediente:</p><p>Lugar, Fecha y Hora:</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>Datos del Servidor Publico:</p>');
				
			//cargaTiposNarrativa();
		    //enableControls('0');
				$('.jquery_ckeditor').ckeditor(config);
				$('#guardar').click(guarda);	
			// $("#cbxTipoNarrativa").change(onSelectChangeTipo);
			    $("#rdbIdentificarElem").change(onSelectChangeRdbs);
			    $("#rdbGenerarFicha").change(onSelectChangeRdbs);
			    $("#btnIdentificarElem").click(getTextSub);
			    $("#btnGuardarNarrativa").click(guardarNarrativa);
			    //$("#txtDescripcion").bind("keypress",habilitaControl);			
			});
			function guarda() {
				var recuperaTextoNarrativa=$('.jquery_ckeditor').val(); 
				window.parent.$("#textNarrativa").val(recuperaTextoNarrativa);
			}
			 //Funcion que refleja los datos de textoNarrativa a la ventana padre
		 	function espejoDatos(){
		     	
		 		var valorCampoTextoNarrativa;
		 		
		 		valorCampoTextoNarrativa=$("#txtDescripcion").val();
		 		        		
		 		//llama a la funcion que escribe los datos en la ventana padre
		 		window.parent.imprimeDatosPadreNarrativa(valorCampoTextoNarrativa);
		 	}    
				
			/*
			*Funcion que habilita los radio buttons
			*/
			function onSelectChangeRdbs()
			{
			  if($("#rdbGenerarFicha").attr('checked'))
			  {
			  	$("#btnIdentificarElem").attr('disabled','disabled');
			  	$("#btnGenerarFicha").attr('disabled','');
			  }
			  else if($("#rdbIdentificarElem").attr('checked'))
			  {
			     $("#btnIdentificarElem").attr('disabled','');
			  	 $("#btnGenerarFicha").attr('disabled','disabled');
			  }
			}
					
			/*
			* Funcion que manda llamar el action que atiende el guardado de una Narrativa
			*/
			function guardarNarrativa() {
			      $.ajax({
			    	  type: 'POST',
			    	  url: '<%= request.getContextPath()%>/GuardarNarrativa.do',
			    	  data: 'tipoNarrativa='+$("#cbxTipoNarrativa option:selected").val()+'&texto='+$("#txtDescripcion").val()+'&numeroExpediente=000001',
			    	  dataType: 'xml',
			    	  success: function(xml){
				    	  var option;
			    		  $(xml).find('tipos').each(function(){
							$('#cbxTipoNarrativa').append('<option value="' + $(this).find('cNombre').text() + '">' + $(this).find('cDescripcion').text() + '</option>');
						   });
			    	  }
			    	});
				}
				
		
			
		    /**
		    *Funcion que obtiene el texto seleccionado del textarea de descripcion
		    */    
		    function getTextSub() 
		    { 
		       var textoSeleccionado="";
		       //IE support
				if (document.selection)
				{
				   $("#txtDescripcion").focus();
				   sel = document.selection.createRange();
				   textoSeleccionado=sel.text;
			    }
		        //MOZILLA/NETSCAPE support
		        else if ($("#txtDescripcion")[0].selectionStart || $("#txtDescripcion")[0].selectionStart == "0")
		        {
		 		    var startPos = $("#txtDescripcion")[0].selectionStart;
		 			var endPos = $("#txtDescripcion")[0].selectionEnd;
					textoSeleccionado=$("#txtDescripcion").val().substr(startPos, endPos - startPos);
			    }			   
		    }  

		    function closeIt(){
		        window.close();
		        }
		</script>
	   <style>
	   .sinborde{
	   border: 0;
	   
	   }
            body{
                font-size: 10px;
            }
        </style>
</head>
<body>
<table width="100%" border="0">
  <tr>
    <td width="78">No. Expediente:</td>
    <td width="144">
      
      <input type="text" name="nExpediente" id="nExpediente" maxlength="30" class="sinborde"/>
   </td>
    <td width="66">Entrevista: </td>
    <td width="149">
      <input type="checkbox" name="checkEntrevistado" id="checkEntrevistado" />
     
     </td>
  </tr>
  <tr>
    <td>Entrevistado:</td>
    <td><input type="text" name="entrevistado" id="entrevistado" class="sinborde"/></td>
    <td>Complementario:</td>
    <td>
      <input type="checkbox" name="checkComplementario" id="checkComplementario" />
     </td>
  </tr>
  <tr>
    <td>Servidor P&uacute;blico</td>
    <td><input type="checkbox" name="checkServidor" id="checkServidor" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
      <textarea class="jquery_ckeditor" cols="80" id="editor1" name="editor1" rows="15" ></textarea>
   
   <input type="button" id="guardar" name="guardar" value="Guardar" class="btn_Generico"/>
</body>
</html>