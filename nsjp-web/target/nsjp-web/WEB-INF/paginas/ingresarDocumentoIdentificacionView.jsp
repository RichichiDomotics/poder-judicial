<script type="text/javascript">

   /*
	*Funcion que dispara el Action para consultar el tipo de Narrativas
	*/
   function cargaTiposNarrativa() {
         $('#documentoIdentificacion').empty();
	      $.ajax({
		      type: 'POST',
	    	  url: '<%= request.getContextPath()%>/ingresarTipoDeDocumentoDeIdentificacion.do',
	    	  data: '',
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
		    	  $('#documentoIdentificacion').empty();
		    	  $('#documentoIdentificacion').append('<option value="-1">- Seleccione -</option>');
	    		  $(xml).find('catTipoIdentificacion').each(function(){
					$('#documentoIdentificacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				   });
	    	  }
	    	});
		}	
   
   /*
   *Funcion para recuperar los datos del tipo de documento de identificacion
   */
   function recuperaDatosTipoDocIdentificacion(idCalidad)
   {
	   var lsDatosTipoDocIdent="";
	   lsDatosTipoDocIdent="&docIdentificacion="+$("#documentoIdentificacion option:selected").val();
	   lsDatosTipoDocIdent+="&folioDoc="+$("#folioDocumentoIdentificacion").val();
	   return lsDatosTipoDocIdent;
   }

   /*
    *Funcion para pintar los datos mediante la recuperacion del xml del tipo de documento de identificacion
    */
   function pintaDatosTipoDocIdentificacion(xml){
	   $('#folioDocumentoIdentificacion').val($(xml).find('folioIdentificacion').text());
	   var id=$(xml).find('valorIdIdentificaion').find('idCampo').text();
	   $('#documentoIdentificacion').find("option[value='"+id+"']").attr("selected","selected");
	}

   function desavilitarDatosIdentificacion(){
	   $('#folioDocumentoIdentificacion').attr("disabled","disabled");
	   $('#documentoIdentificacion').attr("disabled","disabled");
//	   var $widget = $("select").multiselect();
//		var	state = false; 
//	   state = !state; 
//	   $widget.multiselect(state ? 'disable' : 'enable');
	}

   function avilitarDatosIdentificacion(){
	   $('#folioDocumentoIdentificacion').attr("disabled","");
	   $('#documentoIdentificacion').attr("disabled","");
//	   var $widget = $("select").multiselect();
//		var	state = true; 
//	   state = !state; 
//	   $widget.multiselect(state ? 'disable' : 'enable');
	}
   
   //funcion para limpiar los campos de captura
   function limpiarCmpsCptrDocId()
   {
	   $('#folioDocumentoIdentificacion').val("");
	   $('#documentoIdentificacion').find("option[value='-1']").attr("selected","selected");
   }
   
</script>

<table border="0" width="100%" class="fondoClaroAp">
	<tr>
		<td>&nbsp;</td>
		<td>Tipo de documento de identificación</td>
		<td>Folio de documento de identificación</td>
	</tr>
	<tr>
		<td>Documento tipo 1</td>
		<td align="center">
			<select id="documentoIdentificacion" name="documentoIdentificacion">
				<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			</select>
		</td>
		<td><input type="text" id="folioDocumentoIdentificacion" name="folioDocumentoIdentificacion" maxlength="30"/></td>
	</tr>
</table>

<script type="text/javascript">
   	    cargaTiposNarrativa();

//   	 $("#documentoIdentificacion").multiselect({ 
//			multiple: false, 
//			header: "Seleccione", 
//			position: { 
//				my: 'bottomr', 
//				at: 'bottom' 
//			},
//			selectedList: 1 
//		});
</script>