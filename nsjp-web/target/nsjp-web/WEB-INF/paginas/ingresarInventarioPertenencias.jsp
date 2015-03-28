	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
 	<script type="text/javascript">

	var lstPertenencia = new Array();
	var idPertenencia = 0;
	var reta;	
	var pasa;

		$(document).ready(function(){
			
			  jQuery("#gridPertenencias").jqGrid({ 
					url: '<%= request.getContextPath()%>/consultarPertenencias.do',
					datatype: "xml", 
					colNames:['Categoría de la pertenencia','Descripción de la pertenencia','Condición física de la pertenencia'], 
					colModel:[ {name:'categoria',index:'categoria', width:220, editable:true, align:"center"},
							   {name:'descripcion',index:'descripcion', width:220, align:"right", editable:true,align:"center"},					    
					 		   {name:'condicion',index:'condicion', width:220, align:"right", editable:true,align:"center"}						    
					    
							    ],
				     rowNum:10, 
				     rowList:[10,20,30],
				     autowidth: true,
				     pager: '#pagered',
				     sortname: 'id',
				     //onSelectRow: function(){
							
					 //		},
				     viewrecords: true,
				     gridview: true, 
				     caption: "Ingresar inventario de pertenencias", 
				     sortorder: "desc", 
				     editurl: "http://localhost:8080/nsjp-web/administrarDiasInhabiles.jsp"
				    
					  }); 

			 $('#agregarPertenencia').click(registraPertenencia);
			 $('#eliminarPertenencia').click(eliminaPertenencia);

			 
			 cargaComboCondicion();
			 cargaComboCategoria();
			});	

		
		function registraPertenencia(){

			$("#divAgregarPertenencia").dialog("open");
			$("#divAgregarPertenencia").dialog({ autoOpen: true, 
				modal: true, 
			  	title: 'Agregar Pertenencia', 
			  	dialogClass: 'alert',
			  	position: [312,40],
			  	width: 504,
			  	height: 360,
			  	buttons:{"Agregar":function() {	

			  		//var diaInhabil = new DiaInhabil($('#fecha').val(),$('#diaInhabil').val());
		  			//lstPertenencia[idPertenencia] = diaInhabil;
		  			//idPertenencia++;
		  			
		  			//		jQuery("#gridPertenencias").jqGrid('addRowData',idPertenencia,diaInhabil);
		  			//		jQuery("#gridPertenencias").trigger("updateGrid");

		  			var tipoObjeto;
		  			
			  		if ($("#categoriaPertenencia").val()=="Arma"){
			  			tipoObjeto="ARMA";
					}
					if ($("#categoriaPertenencia").val()=="Documento oficial"){
						tipoObjeto="DOCUMENTO_OFICIAL";
					}
					if ($("#categoriaPertenencia").val()=="Equipo de cómputo"){
						tipoObjeto="EQUIPO_COMPUTO";
					}
					if ($("#categoriaPertenencia").val()=="Equipo telefonico"){
						tipoObjeto="EQUIPO_TELEFONICO";
					}
					if ($("#categoriaPertenencia").val()=="Explosivo"){
						tipoObjeto="EXPLOSIVO";
					}
					if ($("#categoriaPertenencia").val()=="Joya"){
						tipoObjeto="JOYA";
					}
					if ($("#categoriaPertenencia").val()=="Otro"){
						tipoObjeto="OTRO";
					}
					if ($("#categoriaPertenencia").val()=="Sustancia"){
						tipoObjeto="SUSTANCIA";
					}
					if ($("#categoriaPertenencia").val()=="Vegetal"){
						tipoObjeto="VEGETAL";
					}
		  			
		  					var params = 'categoria=' + tipoObjeto;
		  						params += '&condicion=' + $("#condicionFisica").val();
		  						params += '&descripcion=' + $("#descripcion").val();

		  					$.ajax({
		  			    		type: 'POST',
		  			    	    url: '<%=request.getContextPath()%>/ingresarPertenencias.do',
		  			    	    data: params,
		  			    	    dataType: 'xml',
		  			    	    success: function(xml){		  			    	    	
		  			    			
		  			    		}

		  					
	  			    		
		  					});			  			  			  					                                                         
		  					$(this).dialog("close");
		  					jQuery("#gridPertenencias").trigger("reloadGrid");
			  		},
			  		"Cancelar":function() {
			  			$(this).dialog("close");
			  		}
			  	}
			});	  	
		
			}

		 // function DiaInhabil(diaInhabil,descripcion){

		//	    this.diaInhabil = diaInhabil;
		//		this.descripcion = descripcion;	
											
		//	} 

		  function eliminaPertenencia(){
			   
			  var gr = jQuery("#gridPertenencias").jqGrid('getGridParam','selrow');
			  	      			  			  
			   if( gr != null ){

				   jQuery("#gridPertenencias").jqGrid('delGridRow',gr,{
					   reloadAfterSubmit:false,
					   onclickSubmit: function(){						   

						   var id = jQuery("#gridPertenencias").jqGrid('getGridParam','selrow');

						   var params = 'idPertenencia='+id;
						   
						   $.ajax({
		  			    		type: 'POST',
		  			    	    url: '<%=request.getContextPath()%>/eliminarPertenencias.do',
		  			    	    data: params,
		  			    	    dataType: 'xml',
		  			    	    success: function(xml){
		  			    			
		  			    		}
		  					});												
						}});		
						   
				   }
				   
			     else {

				     customAlert("Por favor selecciona un día a eliminar"); 

			     } 

		  }

			//Funcion que carga los combos de las categorias de las pertenencias
			function cargaComboCategoria() {
	         $.ajax({
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/consultarTiposObjeto.do',
		    	  data: '',
		    	  async: false,
		    	  dataType: 'xml',
		    	  success: function(xml){
			    	  $('#categoriaPertenencia').empty();
			    	  $('#categoriaPertenencia').append('<option value="-1">- Seleccione -</option>');
			    	  $(xml).find('catTipoObjetos').each(function(){
						$('#categoriaPertenencia').append('<option value="' + $(this).find('valor').text() + '">' + $(this).find('valor').text() + '</option>');
						//$('#categoriaPertenencia').multiselect('refresh');
						   });
		    	  }
		    	});
			}

			//Funcion que carga los combos de la condicion fisica de la pertenencia
			function cargaComboCondicion() {
	         $.ajax({
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/consultarCondicion.do',
		    	  data: '',
		    	  async: false,
		    	  dataType: 'xml',
		    	  success: function(xml){
			    	  $('#condicionFisica').empty();
			    	  $('#condicionFisica').append('<option value="-1">- Seleccione -</option>');
			    	  $(xml).find('catCondicion').each(function(){
						$('#condicionFisica').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						//$('#condicionFisica').multiselect('refresh');
						   });
		    	  }
		    	});
			}
</script>
</head>

	<body>
	<table width="600" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="40">&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td width="36">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="center"><table id="gridPertenencias"></table>
			<div id="pager4" width="300"></div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="201" align="center">&nbsp;</td>
    <td width="221" align="center">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><input type="button" value="Agregar" id="agregarPertenencia" class="btn_Generico"/></td>
    <td align="center"><input type="button" value="Eliminar"id="eliminarPertenencia" class="btn_Generico"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<div id="divAgregarPertenencia" style="display:none">
<br>
<br>
<table width="500" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="32">&nbsp;</td>
    <td width="242">&nbsp;</td>
    <td width="193">&nbsp;</td>
    <td width="31">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong>Categoría de la pertenencia:</strong></td>
    <td><select id="categoriaPertenencia"><option></option></select></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong> Condición física de la pertenencia:</strong></td>
    <td><select id="condicionFisica">
      <option></option>
    </select></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong>Descripción de la pertenencia::</strong></td>
    <td align="left"><textarea id="descripcion"></textarea></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" align="center">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

</div>
	
</body>
	
</html>