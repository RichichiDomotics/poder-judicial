<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consultar Leyes y Codigos</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>	
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>	
	<script type="text/javascript">
	
		$(document).ready(function(){

			$('#cmbNormas').change(onSelectChangeTipo);

			//Funcion que carga los combos de las instituciones solicitantes
							
		     $.ajax({
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/consultarNormas.do',
		    	  data: '',
		    	  async: false,
		    	  dataType: 'xml',
		    	  success: function(xml){
			    	  $('#cmbNormas').empty();
			    	  $('#cmbNormas').append('<option value="-1">- Seleccione -</option>');
			    	  $(xml).find('norma').each(function(){
						$('#cmbNormas').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						
						   });
		    	  }
		    	});
			
		});


			 /*
			  * Funcion para llamar la funcion de habilitar los elementos de la pantalla
			  */
			  function onSelectChangeTipo() {
			    	var selected = $("#cmbNormas option:selected");		
			  	enableControls(selected.val());
			  }

			  /*
			  *Funcion que habilita o deshabilita los elementos de la pagina dependiento del tipo 
			  * de busqueda
			  */
			  function enableControls(tipo){
			  		
			   		$("#constitucionPolitica").hide();
			  		$("#leyesGenerales").hide();
			  		$("#tratadosInternacionales").hide();
			  		$("#codigos").hide();
			  		$("#acuerdos").hide();
			  		$("#circulares").hide();
			  		$("#manuales").hide();
			  		$("#instructivos").hide();
			  		
			  		if (parseInt(tipo) == 2070){
			  			limpiaCampos();
			  			$("#constitucionPolitica").show();			  			
			  				 			
			  			}
			  		else{if (parseInt(tipo) == 2){
			  					limpiaCampos();
			  					$("#leyesGenerales").show();
			  					
			  				}
			  				else{if (parseInt(tipo) == 3){
			  							limpiaCampos();
			  							$("#tratadosInternacionales").show();
			  			   			 }

			  				   	else{if (parseInt(tipo) == 2072){
			  						    	limpiaCampos();
			  						    	$("#codigos").show();
			  						    	
			  							}else{if (parseInt(tipo) == 2075){
			  						    		limpiaCampos();
			  						    		$("#acuerdos").show();
			  						    		
			  									}else{if (parseInt(tipo) == 2076){
					  						    		limpiaCampos();
					  						    		$("#circulares").show();
					  						    		
						  								}else{if (parseInt(tipo) == 7){
						  						    		limpiaCampos();
						  						    		$("#manuales").show();
								  								}else{if (parseInt(tipo) == 8){
								  						    		limpiaCampos();
								  						    		$("#instructivos").show();
								  								}  							
			  							
			  												}  				  		  							
			  							
			  											}  				  							
			  							
			  										}  				  							
			  							
			  									}  							
			  							
			  								}
			  							}
			  		
					  				}
			  		
					  			}

				function limpiaCampos(){

					}
				
			
	</script>
</head>
<body>
<table width="720" cellspacing="0" cellpadding="0">
  <tr>
    <td width="31">&nbsp;</td>
    <td width="314">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="32">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><strong>Selecciona Norma a Consultar:</strong></td>
    <td><select id="cmbNormas">
    
    </select></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2"><div id="constitucionPolitica" style="display: none;"><table align="center"><tr><td>Constitución Política</td></tr></table></div>
    	<div id="leyesGenerales" style="display: none;"><table align="center"><tr><td>Leyes Generales</td></tr></table></div>
    	<div id="tratadosInternacionales" style="display: none;"><table align="center"><tr><td>Tratados Internacionales</td></tr></table></div>
    	<div id="codigos" style="display: none;"><table align="center"><tr><td>Códigos</td></tr></table></div>
    	<div id="acuerdos" style="display: none;"><table align="center"><tr><td>Acuerdos</td></tr></table></div>
    	<div id="circulares" style="display: none;"><table align="center"><tr><td>Circulares</td></tr></table></div>
    	<div id="manuales" style="display: none;"><table align="center"><tr><td>Manuales</td></tr></table></div>
    	<div id="instructivos" style="display: none;"><table align="center"><tr><td>Instructivos</td></tr></table></div></td>
    <td>&nbsp;</td>
  </tr>
</table>				
	
</body>
</html>