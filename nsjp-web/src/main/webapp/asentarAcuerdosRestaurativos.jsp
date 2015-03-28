<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" session="false" %>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />


	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<script type="text/javascript">

	var sesionActiva = '<%= (request.getSession().getAttribute(LoginAction.KEY_SESSION_USUARIO_FIRMADO)!=null)%>';
				if(sesionActiva=="false"){
					//alert(sesionActiva);
	        		document.location.href="<%= request.getContextPath()%>/Logout.do";
	            	}
	$(document).ready(function() {

		$('#horaJudalizacion').timepicker({
            onSelect: function(time, inst) {
                $('#floating_selected_time').html('You selected ' + time);
            }
        });
        
		$("#fechaJudalizacion").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		$('#acuerdoNo').click(habilitaJudializacion);
		$('#acuerdoSi').click(habilitaLibro);
		$('#judializacionNo').click(habilitaElementos);
		$('#judializacionSi').click(mostrarElementoJudializacion);
		$('#guardarLibroGobierno').click(guardaLibro);
	});	


	function habilitaElementos(){

		$("#divFechaJudializacion").hide();		
		
		}

	function mostrarElementoJudializacion(){

		$('#divFechaJudializacion').show();		
				
	}

	function enviaDatosSolicitud (){
		
	        	document.solicitudRecurso.submit();

			}

	function habilitaJudializacion(){

		$('#divLibroGobierno').hide();
		$('#divJudializacion').show();
		

		}	

	function habilitaLibro(){

		$('#divLibroGobierno').show();
		$('#divJudializacion').hide();

		}	

	function guardaLibro(){

		$('#generarLibro').removeAttr('disabled');

		}
	</script>	

<body>
<table width="700px" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="7%">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="7%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="left"><form id="archivoDigital" name="solicitudRecurso" action="<%= request.getContextPath() %>/adjuntarArchivo.do" method="post" enctype="multipart/form-data" >
						 <strong>¿Se llegó a un acuerdo de las partes? </strong><strong>&nbsp;&nbsp;Si </strong>
                         <input type="radio" id="acuerdoSi"  name="acuerdos"/>
                         <strong>No
                         <input type="radio" id="acuerdoNo"  name="acuerdos"/>&nbsp;&nbsp;&nbsp;
                         </strong>
                         <input type="file" name="archivo" > 
							    		    	
          		</form></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
			<td rowspan="3"><div id="divLibroGobierno" style="display: none">
					<table width="100%" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="2" align="center"><strong>Mediador</strong>
							</td>
							<td width="3%">&nbsp;</td>
							<td width="3%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="22%" align="right"><strong>Nombre:</strong>
							</td>
							<td width="72%"><strong> <input type="text"
									size="30" id="nombreMediador" /> </strong>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right"><strong>Apellido. Paterno: </strong>
							</td>
							<td><strong> <input type="text" size="30"
									id="nombreMediador2" /> </strong>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
							<td rowspan="3">&nbsp;</td>
							<td rowspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td align="right"><strong>Apellido. Materno: </strong>
							</td>
							<td><strong> <input type="text" size="30"
									id="nombreMediador3" /> </strong>
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td align="right"><strong>Lapso de duración del
									convenio: </strong>
							</td>
							<td><strong> <input type="text" size="30"
									id="nombreMediador4" /> </strong>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td valign="top" align="right"><strong>Resumen
									sintetico del convenio:</strong>
							</td>
							<td valign="top"><strong> <textarea name="textarea"
										cols="50"></textarea> </strong>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td valign="top">&nbsp;</td>
							<td valign="top">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="button"
								value="Guardar" id="guardarLibroGobierno" /> <input
								type="button" value="Generar Libro de Gobierno"
								id="generarLibro" disabled="disabled" class="btn_Generico"/>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				<div id="divJudializacion" style="display: none">
					<table>
					<tr>
						<td>&nbsp;</td>
						<td><strong>Judializaci&oacute;n:</strong>
						</td>
						<td><strong>Si <input type="radio"
								id="judializacionSi" name="judializacion" /> No <input
								type="radio" id="judializacionNo" name="judializacion" /> </strong>
						</td>
						<td>&nbsp;</td>
					</tr>
					</table>
				</div>
				
				<div id="divFechaJudializacion" style="display: none">
						
						<table>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							
							<td><strong>Fecha de Judialización:</strong> <input type="text"
						id="fechaJudalizacion" /> <strong>Hora:</strong><input
						type="text" id="horaJudalizacion" /></td>
						</tr>					
						</table>
				
				</div>
			</td>
			<td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
