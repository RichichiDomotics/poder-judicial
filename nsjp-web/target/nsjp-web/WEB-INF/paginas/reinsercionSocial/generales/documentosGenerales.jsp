<%@page import="mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.documento.DocumentoDTO"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<script type="text/javascript">
		var cNumeroExpediente;
		var numeroExpedienteId ;
		var listaDesplegada = '<%=SolicitudDTO.PARCIALES%>';
		var sentenciaId = null;
	$(document).ready(function() {
		 sentenciaId = jQuery("#sentenciaId").val();
		 cNumeroExpediente = jQuery("#carpeta").val();
		 numeroExpedienteId = jQuery("#numeroExpedienteId").val();
	
	
		$("#menuDocumentos").treeview();
			
		jQuery("#gridDocumentos").jqGrid({ 
			datatype: "xml", 
			colNames:['No. Caso','No. Expediente', 'Folio','Estatus','Fecha Creación','Fecha Limite','Institución','Remitente', 'TipoActividad'], 
			colModel:[ 	{name:'caso',index:'caso', width:150,hidden:true},
			           	{name:'expediente',index:'expediente', width:180,align:'center'}, 
						{name:'folio',index:'folio', width:100,align:'center'}, 
						{name:'estatus',index:'estatus', width:100,align:'center'}, 
						{name:'fechaCreacion',index:'fechaCreacion', width:100,align:'center'},
						{name:'fechaLimite',index:'fechaLimite', width:100,align:'center',hidden:true},
						{name:'institucion',index:'institucion', width:100,align:'center'},
						{name:'remitente',index:'remitente', width:200,align:'center'},
						{name:'tipoActividadId',index:'tipoActividadId', width:0,align:'center', hidden:true}
					],
			caption: "Documentos",
			pager: jQuery("#pagerGridDocumentos"),
			rowNum:10,
			rowList:[10,20,30,40,50,60,70,80,90,100],
			autoheight: true,
			autowidth: true,
			//sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			altRows : true,
		    shrinkToFit:true,
			ondblClickRow: function(rowid) {}
			
		}).navGrid("#pagerGridDocumentos",{edit:false,add:false,del:false});
	});
	
	var idWindowDetalleSolicitud=1;
	/*Funcion que acarrea el id del expediente, para devolverlo
	*a la pantalla de detalle 
	*/
	function dblClickRowBandejaSolicitudes(rowId){
		customVentana("iframewindowDetalleSolicitud"+idWindowDetalleSolicitud++, "Detalle Del Documento","/consultarDetalleSolicitudBandeja.do", "?idSolicitud=" +rowId + "&tipoUsuario=1" );
		customVentana("visorDocsPropiosFrame"+idWindowDetalleSolicitud,"Documento Asociado","/consultarArchivoDigitalIframe.do","?documentoId="+rowId+"&inFrame=true");
	}

	function mostrarDocumento(rowId){
		var actividadId = "";
		var id = jQuery("#gridDocumentos").jqGrid('getGridParam','selrow'); 
		if (id) { 
			var ret = jQuery("#gridDocumentos").jqGrid('getRowData',id);
			actividadId = ret.tipoActividadId;
		}
	
		var titulo = "";
		var parametros = "?numeroUnicoExpediente=" + cNumeroExpediente + "&documentoId=" + rowId + "&actividadId=" + actividadId + "&sentenciaId=" + sentenciaId;
		customVentana("iframewindowElaborarDocumentoRS", ""+titulo, "/elaborarDocumentosRS.do", parametros, function(){
			$('#cbxAccionesTab').empty();
		
			cargaActuaciones("");
			llenarGridDocumentos(listaDesplegada);			
		});
		
	}
	
	function llenarGridDocumentos(tipoConsulta) {
	
		var url  = '<%= request.getContextPath()%>/consultarDocumentosConCriterios.do';
			url += '?tipoSoliciutd=<%=TiposSolicitudes.REINSERCION_SOCIAL.getValorId() %>';
			//url += '&estatus=<%=EstatusSolicitud.ABIERTA.getValorId()%>';
			url += '&numeroExpedienteId=' + numeroExpedienteId; 
			url += '&cNumeroExpediente=' + cNumeroExpediente;
			url += '&tipoConsulta=' + tipoConsulta;
	
		jQuery("#gridDocumentos").jqGrid("clearGridData", true);
		
		if (tipoConsulta != '<%=SolicitudDTO.PARCIALES%>') {
			
			if(tipoConsulta != '<%=SolicitudDTO.GENERADAS%>'){
				jQuery("#gridDocumentos").jqGrid("setLabel", "remitente", "Destinatario");
				jQuery("#gridDocumentos").setCaption("Documentos Enviados");
			} else if(tipoConsulta != '<%=SolicitudDTO.PARCIALES%>') {
				jQuery("#gridDocumentos").jqGrid("setLabel", "remitente", "Remitente");
				jQuery("#gridDocumentos").setCaption("Documentos Recibidos");
			}
			
			jQuery("#gridDocumentos").jqGrid(
				"setGridParam", {
					url: url,
					ondblClickRow: function(rowid) {
						dblClickRowBandejaSolicitudes(rowid);
					}
				}
			);
			
		} else {
			jQuery("#gridDocumentos").jqGrid(
				"setGridParam", {
					url: url,
					ondblClickRow: function(rowid) {
						mostrarDocumento(rowid);
					}
				}
			);
			jQuery("#gridDocumentos").jqGrid("setLabel", "remitente", "Nombre Del Documento");
			jQuery("#gridDocumentos").setCaption("Documentos Generados");		
		}
		
		jQuery("#gridDocumentos").trigger("reloadGrid");
		
		listaDesplegada = tipoConsulta;	
	}

	function limpiarListaDocumentos(){
		jQuery("#gridDocumentos").jqGrid("clearGridData", true);
		jQuery("#gridDocumentos").trigger("reloadGrid");	
	
	}

</script>

<table >
	<tr>
	<td width="20%" valign="top">
		<ul id="menuDocumentos" class="filetree">
			<li>
				<span class='file'> 
					<a onclick='llenarGridDocumentos("<%=SolicitudDTO.POR_ATENDER%>");'>
						Documentos Recibidos
					</a>
				</span>
			</li>
			<li>
				<span class='file'>
					<a onclick='llenarGridDocumentos("<%=SolicitudDTO.GENERADAS%>");'>
						Documentos Enviados
					</a>
				</span>
			</li>
			<li>
				<span class='file'>
					<a onclick='llenarGridDocumentos("<%=SolicitudDTO.PARCIALES%>");'>
						Documentos Generados
					</a>
				</span>
			</li>
		</ul>
	</td>
	<td width="80%">
		<table id="gridDocumentos"></table>
		<div id="pagerGridDocumentos"></div>
	</td>
	</tr>
	
</table>

	