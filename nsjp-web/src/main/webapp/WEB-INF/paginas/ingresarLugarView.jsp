    <!--CSS, modificada para las tabs del domicilio -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estiloTab.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	    
	<script type="text/javascript">	 
	jQuery().ready(	
		function () {
			onSelectChangeLugar();
			ocultaDomicilioNotificaciones();
			//$("#cbxTipoLugar").change(onSelectChangeLugar);
	});

	function onSelectChangeLugar(){
		var selected = $("#cbxTipoLugar option:selected");
		
		if (parseInt(selected.val()) == 1 ){
			
			$("#tabs-2").css("display", "block");
			$("#domicilio").css("display", "block");
			$("#imediacionLugar").css("display", "none");
			$("#puntoCarretero").css("display", "none");
			$("#areaGeografica").css("display", "none");
			$("#espacioMaritimo").css("display", "none");
			$("#espacioAereo").css("display", "none");
		}else if (parseInt(selected.val()) == 2 ){
			$("#tabs-2").css("display", "none");
			$("#domicilio").css("display", "none");
			$("#imediacionLugar").css("display", "block");
			$("#puntoCarretero").css("display", "none");
			$("#areaGeografica").css("display", "none");
			$("#espacioMaritimo").css("display", "none");
			$("#espacioAereo").css("display", "none");
		}else if (parseInt(selected.val()) == 3 ){
			$("#tabs-2").css("display", "none");
			$("#domicilio").css("display", "none");
			$("#imediacionLugar").css("display", "none");
			$("#puntoCarretero").css("display", "block");
			$("#areaGeografica").css("display", "none");
			$("#espacioMaritimo").css("display", "none");
			$("#espacioAereo").css("display", "none");
		}else if (parseInt(selected.val()) == 4 ){
			$("#tabs-2").css("display", "none");
			$("#domicilio").css("display", "none");
			$("#imediacionLugar").css("display", "none");
			$("#puntoCarretero").css("display", "none");
			$("#areaGeografica").css("display", "block");
			$("#espacioMaritimo").css("display", "none");
			$("#espacioAereo").css("display", "none");
		}else if (parseInt(selected.val()) == 5 ){
			$("#tabs-2").css("display", "none");
			$("#domicilio").css("display", "none");
			$("#imediacionLugar").css("display", "none");
			$("#puntoCarretero").css("display", "none");
			$("#areaGeografica").css("display", "none");
			$("#espacioMaritimo").css("display", "block");
			$("#espacioAereo").css("display", "none");
		}else if (parseInt(selected.val()) == 6 ){
			$("#tabs-2").css("display", "none");
			$("#domicilio").css("display", "none");
			$("#imediacionLugar").css("display", "none");
			$("#puntoCarretero").css("display", "none");
			$("#areaGeografica").css("display", "none");
			$("#espacioMaritimo").css("display", "none");
			$("#espacioAereo").css("display", "block");
		}else if (parseInt(selected.val()) == 0 ){
			$("#tabs-2").css("display", "none");
			$("#domicilio").css("display", "none");
			$("#imediacionLugar").css("display", "none");
			$("#puntoCarretero").css("display", "none");
			$("#areaGeografica").css("display", "none");
			$("#espacioMaritimo").css("display", "none");
			$("#espacioAereo").css("display", "none");
		}
	}

	function obtenerDatos(){
		var parametros='';
		var selected = $("#cbxTipoLugar option:selected");
		parametros='&tipoLugar='+selected.val();
		if (parseInt(selected.val()) == 1 ){
			parametros += obtenerParametrosDomicilio();
		}else if (parseInt(selected.val()) == 2 ){

		}
		return parametros;
	}
		
	function seteaLugar(xml)
	{
		//Seteamos la informacion cuando el lugar es un DOMICILIO
		$(xml).find('hechoDTO').find('lugar').each(function(){
			$('#cbxTipoLugar').find("option[value='1']").attr("selected","selected");
			onSelectChangeLugar();
			pintaDatosDomicilioHecho(xml);
			bloqueaCamposDomicilioHecho(0);
	      });	
	}
	
	function bloqueaAllCamposLugaresHecho(bandera)
	{
		//bloqueamos o desbloqueamos los campos para el lugar Domicilio
		bloqueaCamposDomicilioHecho(bandera);

	}
	
    </script>
    
    <table cellpadding="0" cellspacing="0" >
    	<tr height="10%">
    		<td>
    			<select id="cbxTipoLugar" style="width:200px;" onchange="onSelectChangeLugar()">
              		<option value="1" >-Domicilio-</option>
              		<!-- <option value="-1">-Seleccione-</option>
              		<option value="2">-Imediación del Lugar-</option>
              		<option value="3">-Punto Carretero-</option>
              		<option value="4">-Area Geografica-</option>
              		<option value="5">-Espacio Maritimo-</option>
              		<option value="6">-Espacio Aereo-</option> -->
            	</select>
            </td>
    	</tr>
    	<tr height="90%">
    		<td>
    			<div id="domicilio" style="display:none ;" ><jsp:include page="ingresarDomicilioView.jsp"/></div>
    			<div id="imediacionLugar" style="display: none;" >imediacion del lugar</div>
    			<div id="puntoCarretero" style="display: none;" ><jsp:include page="/WEB-INF/paginas/ingresarPuntoCarretero.jsp" /></div>
    			<div id="areaGeografica" style="display: none;" >area geografica</div>
    			<div id="espacioMaritimo" style="display: none;" >espacio maritimo</div>
    			<div id="espacioAereo" style="display: none;" >espacio aereo</div>
    		</td>
    	</tr>
    </table>
    
    