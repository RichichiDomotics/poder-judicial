<%@page
    import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
             pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>     
        
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
       	<!--Hoja de estilos de Layout-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<!--Hojas de estilos de los combos multiselect-->
	<!-- link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" /-->
	
	<!--Hoja de estilos ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<!--Hoja de estilos windows engine (frames)-->
	
	
	<!--Hojas de estilos para los componentes UI de Jquery-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	
	<!--Hoja de estilos para el grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<!--scripts de java script-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	
	<!--script de windows engine (frames)-->
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	
	<!--script de jquery UI-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	
	<!--script de los combos multiselect-->
	<!-- script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script-->
	
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	

            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

            <script type="text/javascript">
            var idNumeroExpedienteOp1=parent.idNumeroExpedienteOp;
            var idExpedienteOp1=parent.idExpedienteop;
            var casonumero;
                $(document).ready(function() {
                	numeroCasoPorExpediente();
                	
                    
                	//$("#idcasoCarpetaInvestigacionDefensoria").val(casonumero);
                	$.ajax({
        				type: 'POST',
        				url: '<%= request.getContextPath()%>/ConsultaInvolucradosPorCalidadExpediente.do',
        				data: 'idNumeroExpediente='+idNumeroExpedienteOp1+'&calidadInvolucrado=PROBABLE_RESPONSABLE',
        				dataType: 'xml',
        				async: false,
        				success: function(xml){
        					
        					$(xml).find('involucradoDTO').each(function(){
        						var nombreCompleto=$(this).find('nombresDemograficoDTO').find('nombre').text()+" ";
        						nombreCompleto+=$(this).find('apellidoPaterno').text()+" ";
        						nombreCompleto+=$(this).find('apellidoMaterno').text()+" ";
        						$('#idprobableResponsableCID').append('<option id="' + $(this).find('elementoId').text() + '" value="' + nombreCompleto + '">' + nombreCompleto+ '</option>');
        					
        					});
        				}
                });

                	});


                function numeroCasoPorExpediente(){

                	var param ="";
                	param += 'numExpediente='+idExpedienteOp1;
                	
                	   $.ajax({
                	     type: 'POST',
                	     url: '<%= request.getContextPath()%>/obtenerNumeroCasoPorExpedienteCarpeta.do',
                		 data: param,
                		 dataType: 'xml',
                		 success: function(xml){
                			 $(xml).find('CasoDTO').each(function(){
                			casonumero= $(this).find('numeroGeneralCaso').text();
                			
                			 $('#idcasoCarpetaInvestigacionDefensoria').val(casonumero);
                			 });
                		  }
                		});
                		

                }
                
                function enviaCarpeta() {
                	var param ="";
                	param += 'folioSol='+$("#folioCadenaCustodia").val();
                	param+= '&caso='+$("#idcasoCarpetaInvestigacionDefensoria").val();
                	param+= '&proresp='+$('#idprobableResponsableCID option:selected').val();
                	   $.ajax({
                	     type: 'POST',
                	     url: '<%= request.getContextPath()%>/EnviarCarpeta.do',
                		 data: param,
                		 dataType: 'xml',
                		 success: function(xml){
                			 $(xml).find('expedienteDTO').each(function(){
                				regreso= $(this).find('expedienteId').text()+" ";
                				if(regreso!=0){alertDinamicoCerrar("Se envio correctamente la carpeta");}
                				else{customAlert("No se logró enviar la carpeta de investigación");}
                			 });
                		  },
						   error: function (xhr, ajaxOptions, thrownError) { alert(xhr.status); alert(thrownError); }
                		});
                	 }

              //Función para alertDinamicoCerrar
        		function alertDinamicoCerrar(textoAlert){						
        			$("#divAlertTextoCerrar").html(textoAlert);
        		    $( "#dialog-AlertCerrar" ).dialog({
        				autoOpen: true,
        				resizable: false,
        				modal: true,
        				buttons: {				
        					
        					"Aceptar": function() {						
        						parent.cerrarVentanaEnvio();
        						$( this ).dialog( "close" );
        						$("#divAlertTextoCerrar").html("");					
        					}				
        				}
        			});    
        		 }
        		                           
            </script>
        </head>
        <body >
       <!-- div para el alert dinamico antes de cerrar ventana -->
<div id="dialog-AlertCerrar" style="display: none">
	<table align="center">
		<tr>
        	<td align="center">
            	<span id="divAlertTextoCerrar"></span>
            </td>
        </tr>
     </table>              
</div>           
          <table class="fondoClaroAp">
                <tr>

                    <td  colspan="2" align="center">Enviar Carpeta de Investigación Defensoria
                    </td>
                </tr>
                <tr>

                    <td  colspan="2" align="right"><input type="button" value="Enviar" onclick="enviaCarpeta()" class="btn_Generico"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">Folio Solicitud</td>
                    <td align="right"><input type="text" id="folioCadenaCustodia" size="22"></td>
                </tr>
                <tr>
                    <td align="left">Caso</td>
                    <td align="right"><input type="text" id="idcasoCarpetaInvestigacionDefensoria" readonly="true" size="22"></td>
                </tr>
                 <tr>
                    <td align="left"><bean:message key="probableResponsable"/></td>
                    <td align="right"><select id="idprobableResponsableCID"  style="width:156px" >
                    <option value="-1">- seleccione -</option>
                    </select> </td>
                </tr>
                 <tr>
                    <td ><br/> </td>
                    <td>
                    </td>
                </tr>
                 <tr>
                    <td ><br/> </td>
                    <td>
                    </td>
                </tr>
            </table>
           
        </body>
    </html>