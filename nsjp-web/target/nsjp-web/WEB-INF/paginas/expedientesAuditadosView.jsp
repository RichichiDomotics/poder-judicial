
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lineas de Investigación</title>
        
        
        <!--		Hojas de estilos asociadas-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
        <script type="text/javascript">
        var gIdDepartamento=55;
    	var gIdEstatus="1";
    	var opIdEstatus="1";
            jQuery().ready(function () {
            	
            	 var idEspdientes = '<%=request.getParameter("idExpedientes")%>';
            	 var idFuncionario = '<%=request.getParameter("idFuncionario")%>';
            	 var tipoVisita = '<%=request.getParameter("tipoVisita")%>';            	
            	
            	
            	jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
					url: '<%=request.getContextPath()%>/nuevosNumerosExpedienteVisitaduria.do?idExpediente='+idEspdientes+'&idVisitador='+idFuncionario+'&tipoVisita='+tipoVisita+'',
					datatype: "xml", 
					colNames:['Expediente Auditado','Tipo de Visita','Enviar Notificación'], 
					colModel:[ 	{name:'Auditado',index:'dueno', width:120},
					           	{name:'TipoVisita',index:'visita', width:100},
								{name:'Enviar',index:'fechai', width:90}
							],
					pager: jQuery('#pager1'),
					rowNum:10,
					rowList:[10,20,30],
					autowidth: true,
					height:300,
					//sortname: 'detalle',
					caption: "Carpetas de Auditoría",
					viewrecords: true,
					onSelectRow: function(id){
						selectr(id);
						}
				});
            });
            
            function elaboraNotificacionAuditoria(numeroExpe)
            {     
            	var numeroEX="";
            	numeroEX=numeroEX+numeroExpe;
                  var actividad=0;
                  var formaID=4;
                  var titulo="op";
                  var usaeditor="";
                  var estatusId="";
                  //consultamos la forma para la notificacion de auditoria
                  $.ajax({
                        type: 'POST',
                        url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf=135',
                        data: '',
                        dataType: 'xml',
                        async: false,
                        success: function(xml){
                              actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
                              formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
                              titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
                              usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
                              estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
                        }
                  });
                  $.newWindow({id:"iframewindowElaborarSolicitud", statusBar: true, posx:20,posy:20,width:1140,height:550,title:"Notificación Auditoría", type:"iframe"});
            	$.updateWindowContent("iframewindowElaborarSolicitud",'<iframe src="<%= request.getContextPath() %>/elaborarNotificacionAuditoria.do?formaId='+formaID+'&numeroUnicoExpediente='+numeroExpe+'"    width="1140" height="550" />');
            }

            function desabilitarBoton(id){
            
            	var id2;
            	id2 = "#"+id;
            	$(id2).attr("disabled","disabled");
            }
          
            function cierraVentanaNotificacionAuditoria(){
        		var pantalla ="iframewindowElaborarSolicitud";
        		
        		$.closeWindow(pantalla);
        	}

            
          
        </script>
        
       
       
        
    </head>
    <body>
        <div id="tabprincipal">
        	<table  border=0; cellspacing="3" align="center" width="924px" height="500px">
            	<tr>
            		<td>
            			Se crearon la(s) Siguiente(s) Carpeta(s) de Auditoría:	
            		</td>
            	</tr>
            	<tr id="seccionDeBotones">
                	<td>
                		<div id="auditoria" >
							<table id="gridDetalleFrmPrincipal"></table>
							<div id="pager1"></div>
						</div>
                    </td>
              </tr>
            </table>
        </div>
    </body>
</html>