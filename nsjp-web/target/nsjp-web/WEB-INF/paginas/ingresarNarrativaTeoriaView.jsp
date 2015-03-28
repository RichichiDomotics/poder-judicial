<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar narrativa</title>
	   
		<!-- <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" rel="stylesheet">-->
		<!-- <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" rel="stylesheet">-->
		<!-- <link type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" rel="stylesheet">-->
		
		<!--Scripts necesarios para la ejecucion del editor-->
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	   <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	   <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	   
	   <script type="text/javascript">
			
			function guarda() {
				var recuperaTextoNarrativa=$('.jquery_ckeditor').val();
				window.parent.$("#textNarrativa").val(recuperaTextoNarrativa);
			}
			
			function recuperaNarrativa()
			{
				return $('.jquery_ckeditor').val();
			}
			
			function recuperaNarrativaBD()
			{
				var lsNarrativa="";
				lsNarrativa="gsNarrativa="+$('.jquery_ckeditor').val();
	  			return lsNarrativa;
			}
			
			//Funcion que refleja los datos de textoNarrativa a la ventana padre
		 	function espejoDatos(){
		     	
		 		var valorCampoTextoNarrativa;
		 		
		 		valorCampoTextoNarrativa=$("#txtDescripcion").val();
		 		        		
		 		//llama a la funcion que escribe los datos en la ventana padre
		 		window.parent.imprimeDatosPadreNarrativa(valorCampoTextoNarrativa);
		 	}    
				
			/*
			*Funcion que habilita los botones
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
			    if(textoSeleccionado!="")
			    {
				   customAlert(textoSeleccionado);
				}
		    }  

		    function closeIt(){
		        window.close();
		        }
		</script>
		
		<!-- Estilo para el cuerpo del documento-->
		<style>
		   body{
		       font-size: 10px;
		   }
		</style>
</head>
<body>
	<br/><br/>
      <textarea class="jquery_ckeditor" cols="100" id="editor1" name="editor1" rows="10" ></textarea>
</body>
</html>

<script type="text/javascript">
var config = {					
		toolbar:
		[
			['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
			['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
			['NumberedList','BulletedList','-','Outdent','Indent'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['Table','HorizontalRule'],
			'/',
			['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
		],
		height:'500px',
		width:'640px'

	};			
	if(window.parent.banderaNarrativa==1)
		{
			$("#editor1").val('<%= request.getSession().getAttribute("narrativa")%>');
		}
	
		//cargaTiposNarrativa();
    	//enableControls('0');
       
		$('.jquery_ckeditor').ckeditor(config);
		
		
		$('#guardarNarraTiva').click(guarda);	
		// $("#cbxTipoNarrativa").change(onSelectChangeTipo);
	    $("#rdbIdentificarElem").change(onSelectChangeRdbs);
	    $("#rdbGenerarFicha").change(onSelectChangeRdbs);
	    $("#btnIdentificarElem").click(getTextSub);
	    $("#btnIdentificarElem").attr('disabled', 'disabled');
	    $("#btnGenerarFicha").attr('disabled', 'disabled');
	    $("#btnGuardarNarrativa").click(guardarNarrativa);
</script>