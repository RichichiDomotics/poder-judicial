<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--Hojas de estilos-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

<!--Scripts-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		

	<script type="text/javascript">	
	
	var numeroExpediente = '<%= request.getParameter("numeroExpediente")%>';
	
	jQuery().ready(function (){
		
		if(numeroExpediente==null || numeroExpediente=="null"){
			numeroExpediente='<%= request.getSession().getAttribute("numExpConsul")%>';
		}

		cargaCatalogosDeCategoriasDeRelacion();
		cargarRelacionesRegistradas();
	});
	
	  //Llena el grid con los resultados de la busqueda, pasa como parametros el No de caso
	  var banderaCargarORecargar=0;	  
	  function cargarRelacionesRegistradas(){
		var rows=0;
		if(banderaCargarORecargar==0){
			jQuery("#tablaRelacionesDeElemento").jqGrid({
				url:'<%=request.getContextPath()%>/consultarRelacionesXExpediente.do?numeroExpediente='+ numeroExpediente + '', 
				datatype: "xml",  							
				colNames:['Categoría de relación','Tipo relación','Elemento 1','Elemento 2'], 
				colModel:[  {name:'catRel',index:'catRel', width:250},
							{name:'tipoRel',index:'tipoRel', width:150},
							{name:'elem1',index:'elem1', width:200}, 
							{name:'elem2',index:'elem2', width:200}								
				 		 ],
				pager: jQuery('#pager1tablaRelacionesDeElemento'),
				rowNum:10,
				rowList:[10,20,30,40,50],
				sortname: 'id',
				viewrecords: true,
				sortorder: "desc",	
				id: 'tablaRelacionesDeElemento',			
				multiselect: true,
				hiddengrid: false,
				caption:"Relacion(es) registrada(s)",
				loadComplete: function(){
					rows = jQuery("#tablaRelacionesDeElemento").getDataIDs().length;
					
					if(rows>0){
						$("#btnAnularRelaciones").show();
					}
					else{
						$("#btnAnularRelaciones").hide();
					}							
				}
			});
	    	$("#tablaRelacionesDeElemento").trigger("reloadGrid");
	        banderaCargarORecargar=1;
		}else{
	    	jQuery("#tablaRelacionesDeElemento").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarRelacionesXExpediente.do?numeroExpediente='+ numeroExpediente +				
						'',datatype: "xml" });
			$("#tablaRelacionesDeElemento").trigger("reloadGrid");		
	    }
	  }
	  
		/*
		* Funcion que permite cargar los Catalogos de Categorias de relacion:
		* p.e.: persona-persona, persona -objet, persona - lugar etc
		*/
		function cargaCatalogosDeCategoriasDeRelacion(){						
				$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarCatalogosDeCategoriasDeRelacion.do',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			$(xml).find('CatCategoriaRelacionDTO').each(function(){
		    				$('#cbxCategoriaRelacion').append('<option value="' + $(this).find('catCategoriaRelacionId').text() + '">'+ $(this).find('desCategoriaRelacion').text() + '</option>');
		    			});
		    		}
		    	});
	    }
		
		function limpiaCatalogosDependientes(){
			var idcbxCategoriaRelacion=$("#cbxCategoriaRelacion option:selected").val();		
			if(parseInt(idcbxCategoriaRelacion)!=-1){
				limpiaCombo("#cbxPrimerElemento");
				limpiaCombo("#cbxSegundoElemento");
			}else{
				limpiaCombo("#cbxTipoRelacion");
				limpiaCombo("#cbxPrimerElemento");
				limpiaCombo("#cbxSegundoElemento");
			}
		}
		
		
		/**
		* Funcion que permite limpiar un combo e ingresa la opcion de seleccione
		*/
		function limpiaCombo(idCombo){
			$(idCombo).empty();
			$(idCombo).append('<option value="-1">-Seleccione-</option>');			
		}
		
		/*
		* Funcion que permite cargar las categorias de relaciones
		* p.e: Abogado, Hermano, Abuela, etc.
		*/
	    function cargaTiposDeRelacion(){
			
			var idCategoriaRelacion = $("#cbxCategoriaRelacion option:selected").val();
			limpiaCombo("#cbxTipoRelacion");
			
			   $.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarCatRelacionesXCatCategoria.do?idCategoriaRelacion='+ idCategoriaRelacion+'',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			$(xml).find('CatRelacionDTO').each(function(){
		    				$('#cbxTipoRelacion').append('<option value="' + $(this).find('catRelacionId').text() + '">'+ $(this).find('descripcionRelacion').text() + '</option>');
		    			});
		    		}
		    	});	
	    }
		

		/**
		* Funcion que permite consultar los elementos asociados a un expediente
		* p.e: personar, objetos, lugares
		*/
		
		function cargaPrimerElemento(){			
			//Se limpia el catalogo
			var idCategoriaRelacion = $("#cbxCategoriaRelacion option:selected").val();			
			var esSujeto = "true";
			limpiaCombo("#cbxPrimerElemento");
			
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarElementosPorExpediente.do?idCategoriaRelacion='+ idCategoriaRelacion + '&numeroExpediente='+ numeroExpediente +'&esSujeto='+ esSujeto +'',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			$(xml).find('ElementoDTO').each(function(){
		        			tipoElemento=$(this).find('valorIdElemento').find('valor').text();		        			
		    				$('#cbxPrimerElemento').append('<option value="' + $(this).find('elementoId').text() + '">'+ $(this).find('strDescripcionRelacionarElemento').text() + '</option>');
		    			});
		    		}
		    	});	
		  }
	    
		function cargaSegundoElemento(){			
			var idCategoriaRelacion = $("#cbxCategoriaRelacion option:selected").val();
			var esSujeto = "false"

			//Se limpia el catalogo
			limpiaCombo("#cbxSegundoElemento");			
		    	$.ajax({
		    		type: 'POST',
		    		url: '<%=request.getContextPath()%>/consultarElementosPorExpediente.do?idCategoriaRelacion='+ idCategoriaRelacion + '&numeroExpediente='+ numeroExpediente +'&esSujeto='+ esSujeto +'',
		    		data: '',
		    		dataType: 'xml',
		    		async: false,
		    		success: function(xml){
		    			$(xml).find('ElementoDTO').each(function(){
		    				$('#cbxSegundoElemento').append('<option value="' + $(this).find('elementoId').text() + '">'+ $(this).find('strDescripcionRelacionarElemento').text() + '</option>');
		    			});
		    		}
		    	});	
		  }
		    
			/*
			*Funcion que permite validar una Relacion en la base de dataos
			*http://localhost:8082/nsjp-web/validarRelacion.do?idCatRelacion=3&idElementoSujeto=2&idElementoComplemento=1
			*/
			function validarRelacion()
			{					
				var idCatRelacion=$("#cbxTipoRelacion option:selected").val();
				var idElementoSujeto=$("#cbxPrimerElemento option:selected").val();
				var idElementoComplemento=$("#cbxSegundoElemento option:selected").val();							
				
				if(parseInt(idCatRelacion)!=-1 && parseInt(idElementoSujeto)!=-1 && parseInt(idElementoComplemento)!=-1)
				{					
					//validamos la relacion	
					if (idElementoSujeto!=idElementoComplemento) {
						$.ajax({
							type: 'POST',		
				    		url: '<%= request.getContextPath()%>/validarRelacion.do?idCatRelacion=' + idCatRelacion + '&idElementoSujeto='+ idElementoSujeto + '&idElementoComplemento='+ idElementoComplemento +'',
							data: '',
							dataType: 'xml',
							async: false,
							success: function(xml){
								if(parseInt($(xml).find('code').text())==0)
					    		{
									$(xml).find('body').each(function(){									
										if($(xml).find('boolean').text() == "false"){
											registrarRelacion(idCatRelacion,idElementoSujeto,idElementoComplemento);
										}										
										else{
											alertDinamico('La relaci\u00F3n de elementos ya existe');										
										}
									});
					    		}
							}
						});							
					} else {
						alertDinamico('Los elementos a relacionar no pueden ser el mismo');
					}			    					
				}
				else
				{
					alertDinamico("No hay los suficientes elementos para crear la relación");
				}
			}
			
			function registrarRelacion(idCatRelacion,idElementoSujeto,idElementoComplemento){				
				//Guardamos la relacion
					$.ajax({
						type: 'POST',		
			    		url: '<%= request.getContextPath()%>/registrarRelacion.do?idCatRelacion=' + idCatRelacion + '&idElementoSujeto='+ idElementoSujeto + '&idElementoComplemento='+ idElementoComplemento +'',
						data: '',
						dataType: 'xml',
						async: false,
						success: function(xml){
							cargarRelacionesRegistradas();
							if(parseInt($(xml).find('code').text())==0){
								alertDinamico("Se registr\u00F3 con éxito la relaci\u00F3n");
							}				    		
				    		else{
				    			alertDinamico("No se registr\u00F3 la relaci\u00F3n")
				    		}
						}
					});	
			}
			
			function anularRelaciones(){
				var idsRelacionesSeleccionados = jQuery("#tablaRelacionesDeElemento").jqGrid('getGridParam','selarrrow');
				
				//Guardamos la relacion
					$.ajax({
						type: 'POST',		
			    		url: '<%= request.getContextPath()%>/anularRelacionDeElementos.do?idsRelacionesSeleccionados=' + idsRelacionesSeleccionados + '',
						data: '',
						dataType: 'xml',
						async: false,
						success: function(xml){
							cargarRelacionesRegistradas();
							if(parseInt($(xml).find('code').text())==0){
								alertDinamico("Se anularon con éxito la(s) relaci\u00F3n(es)");							
							}				    		
				    		else{
				    			alertDinamico("No se anularon con éxito la(s) relaci\u00F3n(es)")
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
  
<table border="0"  id="tblRelacionarElementos">
	<tr><td>&nbsp;</td></tr>
	<tr>
    	<td align="right">Categor&iacute;a de relaci&oacute;n:</td>
    	<td>
    		<select name="cbxCategoriaRelacion" id="cbxCategoriaRelacion" onChange="cargaTiposDeRelacion();limpiaCatalogosDependientes()">
      			<option value="-1" selected="selected">-Seleccione-</option>
    		</select>
    	</td>
  	</tr>
  	<tr>
    	<td align="right">Tipo de relación:</td>
    	<td>
    		<select name="cbxTipoRelacion" id="cbxTipoRelacion" onChange="cargaPrimerElemento();cargaSegundoElemento()">
      			<option value="-1" selected="selected">-Seleccione-</option>
    		</select>
    	</td>
  	</tr>
  	<tr>
	    <td align="right" width="168"> Elemento 1: </td>
	    <td width="222">
	    	<select name="cbxPrimerElemento" id="cbxPrimerElemento" onFocus="cargaPrimerElemento()">
    			<option value="-1" selected="selected">-Seleccione-</option>
    		</select>
    	</td>
  	</tr>
  	<tr>
    	<td align="right"> Elemento 2: </td>
    	<td>
    		<select name="cbxSegundoElemento" id="cbxSegundoElemento" onFocus="cargaSegundoElemento()">
      			<option value="-1" selected="selected">-Seleccione-</option>
    		</select>
    	</td>
  	</tr>
  	<tr><td>&nbsp;</td></tr>
    <tr>
  		<td colspan="2" align="center">        
    		<input type="button" value="Relacionar Elementos" id="btnRelacionarElementos" onClick="validarRelacion()" class="btn_Generico"/>
    	</td>
  	</tr>
   	<tr><td>&nbsp;</td></tr>  
</table>
<table align="center" id="tablaRelacionesDeElemento" width="850px">
</table>
<div id="pager1tablaRelacionesDeElemento"></div>
<table align="center" id="tablaRelacionesDeElemento">
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td>
			<div align="center">
				<input type="button" value="Anular Relaciones" id="btnAnularRelaciones" onClick="anularRelaciones()" class="btn_Generico"/>
			</div>
		</td>
	</tr>
</table>
</body>
</html>	