    <!--CSS, modificada para las tabs del domicilio -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estiloTab.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
	    
	<script type="text/javascript">	 

	/**
	* se realiza la consulta de entidades federativas
	*/ 	
	function cargaEntidadesPC() {
		  
		$.ajax({
			async: false,								
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarEntFederativas.do',
			data: 'glCatPaisId=10',	//Parametro para hacer la consulta de Entidades por Id del País 10 para Mexico
			dataType: 'xml',
			success: function(xml){
				$(xml).find('catEntidadesFed').each(function(){
					$('#cbxEntFederativaPC').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	 /*
	  *Funcion que realiza la carga del combo de tipo de carretera,
	  */
	  function cargaTipoCarretera() {
		
		$.ajax({
		  type: 'POST',
		  url: '<%= request.getContextPath()%>/consultaCatalogoTipoCarretera.do',
		  data: '',
		  dataType: 'xml',
		  success: function(xml){
			var option;
			$(xml).find('catalogo').each(function(){
				$('#cbxTipoCarretera').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				
					 });			
		  }
		});
	  }

	function obtenerDatosPuntoCarretero(){
		var parametros = '&tipoCarretera='+$('#cbxTipoCarretera option:selected').val();  	  	
		parametros += '&entidadFederativaPC='+$('#cbxEntFederativaPC option:selected').val();  	  	

		parametros += '&numeroCarretera='+$('#ubicacionCmpNumeroCarretera').val();  	  	
		parametros += '&nomCarretera='+$('#ubicacionCmpNombreCarretera').val();  	  	
		parametros += '&numeroKilometro='+$('#ubicacionCmpNumeroKilometro').val();  	  	
		parametros += '&nombreParajeZona='+$('#ubicacionCmpNombreParajeZona').val();  	  	
		parametros += '&nombreTramoCarretero='+$('#ubicacionCmpNombreTramoCarretero').val();  	  	
		parametros += '&poblacionesVecinas='+$('#ubicacionCmpPoblacionesVecinas').val();  	  	
		
		parametros += '&' + recuperaDatosCoordenadasGPS( );

        return parametros;
	}

    </script>	

 <div id="tabs-puntocarretero">
     <ul>
        <li id="liIngresarPuntoCarretero"><a href="#tabs-ingresarPuntoCarretero" id="hrefIngresarPuntoCarretero">Punto Carretero</a></li>
     </ul>
     <div id="tabs-ingresarPuntoCarretero">

	      <table border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td height="12" align="right"><strong>Tipo de Carretera:</strong></td>
	          <td height="12">
	            <select id="cbxTipoCarretera" style="width:182px;">
	              <option value="-1">-Seleccione-</option>
	            </select>
	          </td>  
	        </tr>
	        <tr>
	          <td height="25" align="right"><strong>Entidad Federativa:</strong></td>
	          <td height="25">
	            <select id="cbxEntFederativaPC" style="width:182px;">
	                <option value="-1">-Seleccione-</option>
	            </select>
	          </td>
	          <td colspan="2">&nbsp;
	          </td>
	        </tr>
	        <tr>
				<td align="right" width="52%"><strong>N&uacute;mero de Carretera:</strong></td>
				<td><input type="text" style="width: 180px;" maxlength="50" id="ubicacionCmpNumeroCarretera"/></td>
				<td>&nbsp;</td>
	        </tr>  
	        <tr>
				<td align="right" width="52%"><strong>Nombre de la Carretera:</strong></td>
				<td><input type="text" style="width: 180px;" maxlength="50" id="ubicacionCmpNombreCarretera"/></td>
				<td>&nbsp;</td>
	        </tr>  
	        <tr>
				<td align="right" width="52%"><strong>N&uacute;mero de Kilometro:</strong></td>
				<td><input type="text" style="width: 180px;" maxlength="50" id="ubicacionCmpNumeroKilometro"/></td>
				<td>&nbsp;</td>
	        </tr>  
	        <tr>
				<td align="right" width="52%"><strong>Nombre del paraje o zona:</strong></td>
				<td><input type="text" style="width: 180px;" maxlength="50" id="ubicacionCmpNombreParajeZona"/></td>
				<td>&nbsp;</td>
	        </tr>  
	        <tr>
				<td align="right" width="52%"><strong>Nombre del tramo Carretero:</strong></td>
				<td><input type="text" style="width: 180px;" maxlength="50" id="ubicacionCmpNombreTramoCarretero"/></td>
				<td>&nbsp;</td>
	        </tr>  
	        <tr>
				<td align="right" width="52%"><strong>Poblaciones Vecinas:</strong></td>
				<td><input type="text" style="width: 180px;" maxlength="50" id="ubicacionCmpPoblacionesVecinas"/></td>
				<td>&nbsp;</td>
	        </tr>  
	        <tr>
	          <td height="25" rowspan="2" align="right" id="colCoordGPSpc"><strong>Coord. Geogr&aacute;ficas:</strong></td>
	          <td height="25" colspan="3" rowspan="2" id="rowCoordGPSpc">
	            <jsp:include page="/WEB-INF/paginas/datosCoordenadasGPSView.jsp" flush="true"></jsp:include>
	          </td>
	        </tr>
	      </table>
      </div>
</div>