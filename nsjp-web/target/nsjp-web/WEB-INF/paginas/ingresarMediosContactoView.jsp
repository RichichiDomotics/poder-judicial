 <link rel="stylesheet" type="text/css" media="screen"
              href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
        <link rel="stylesheet" type="text/css"
              href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-es.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script type="text/javascript">
var contextPath = '<%=request.getContextPath()%>';
var idTelefono = 0;
var idCorreo = 0;

function datosTelefono(){
	var ids = jQuery('#list5').getDataIDs();
	for(i=0; i < ids.length; i++){
		var retd = jQuery("#list5").jqGrid('getRowData',ids[i]);
		var datosTelefono =  retd.tipo;
		listaTele += datosTelefono;
	}
}

function obtenerMedios(){
	var parametros = '&medioContactoTelefono=';
	lstTelefonos = jQuery('#list5').getDataIDs();
	for(var i=0;i<lstTelefonos.length;i++){
		var ret2 = lstTelefonos[i];   
		var tel = jQuery("#list5").jqGrid('getRowData',ret2);
		parametros +=tel.idTipo + ',';
		parametros +=tel.pais + ',';
		parametros +=tel.lada + ',';
		parametros +=tel.Telefono + '|';
	}
	parametros += '&medioContactoCorreo=';
	lstCorreos = jQuery('#correos').getDataIDs();
	for(var i=0;i<lstCorreos.length;i++){
		var ret2 = lstCorreos[i];   
		var cor = jQuery("#correos").jqGrid('getRowData',ret2);
		parametros += cor.correo + ',';
	}
	
	return parametros;
}

/**
 * Limpia los campos de captura del popUp de correo
 */
function limpiarPopUpCorreo(){
	$('#txtCorreo').val('');
	$('#txtConfirmarCorreo').val('');
	$('#txtCorreo2').val('');
	$('#txtConfirmarCorreo2').val('');
}

/**
 * Limpia los campos de captura del popUp de teléfono
 */
function limpiarPopUpTelefono(){
	$('#cmbTipoTelefono').attr('selectedIndex',0);
	$('#txtCvePais').val('');
	$('#txtLada').val('');
	$('#txtTelefono').val('');
	$('#cmbTipoTelefono1').attr('selectedIndex',0);
	$('#txtCvePais1').val('');
	$('#txtLada1').val('');
	$('#txtTelefono1').val('');
}

function mediosContactoCorreoActualiza(){
	jQuery("#correos").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosCorreo.do?idInvolucrado='+idindi,datatype: "xml" });
	$("#correos").trigger("reloadGrid");	
	modificaGrid=false;
}

function mediosContactoTelefonoActualiza(){
	jQuery("#list5").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosTelefono.do?idInvolucrado='+idindi,datatype: "xml" });
	$("#list5").trigger("reloadGrid");	
}
 
 function disparaConsultaGridsMediosDeContacto(idInvolucradoXML)
 {
	 //disparamos la consulta del grid de correos
	jQuery("#correos").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosCorreo.do?idInvolucrado='+idInvolucradoXML+'',datatype: "xml" });
	$("#correos").trigger("reloadGrid");	
	//modificaGrid=true;
	//disparamos la consulta del grid de telefonos
	jQuery("#list5").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosTelefono.do?idInvolucrado='+idInvolucradoXML+'',datatype: "xml" });
	$("#list5").trigger("reloadGrid");
	//bloqueaCamposMediosDeContactoGrid();
 }

 function consultaGridsMediosDeContactoImplicado(idImplicadoXML)
 {
	 //disparamos la consulta del grid de correos
	jQuery("#correos").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosCorreoImplicado.do?idImplicado='+idImplicadoXML+'',datatype: "xml" });
	$("#correos").trigger("reloadGrid");	
	//modificaGrid=true;
	//disparamos la consulta del grid de telefonos
	jQuery("#list5").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosTelefonoImplicado.do?idImplicado='+idImplicadoXML+'',datatype: "xml" });
	$("#list5").trigger("reloadGrid");
	//bloqueaCamposMediosDeContactoGrid();
 }

 function disparaConsultaGridsMediosDeContactoFuncionario(idInvolucradoXML)
 {
	 //disparamos la consulta del grid de correos
	jQuery("#correos").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosCorreoFuncionario.do?idInvolucrado='+idInvolucradoXML+'',datatype: "xml" });
	$("#correos").trigger("reloadGrid");	
	//modificaGrid=true;
	//disparamos la consulta del grid de telefonos
	jQuery("#list5").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultaContactosTelefonoFuncionario.do?idInvolucrado='+idInvolucradoXML+'',datatype: "xml" });
	$("#list5").trigger("reloadGrid");
	//bloqueaCamposMediosDeContactoGrid();	
 }
 function bloqueaCamposMediosDeContactoGrid()
 {
	 $("#telefonobotonagre").attr("disabled","disabled");
	 $("#telefonobotonelim").attr("disabled","disabled");
	 $("#correobotonagre").attr("disabled","disabled");
	 $("#correobotonelim").attr("disabled","disabled");
 }
 
 function desbloqueaCamposMediosDeContactoGrid()
 {
	 $("#telefonobotonagre").attr("disabled","");
	 $("#telefonobotonelim").attr("disabled","");
	 $("#correobotonagre").attr("disabled","");
	 $("#correobotonelim").attr("disabled","");
 }
var ditable=0;

function isEmailAddress(theElement) {
	//var s = theElement.value;
	var s = theElement;
	var filter=/^[A-Za-z0-9_.-]+@[A-Za-z0-9_]+\.[A-Za-z0-9_.]+[A-za-z]$/;
	if (s.length == 0 ) return false;
	if (filter.test(s))
		return true;
	return false;
}

function alertDinamicoMedios(textoAlert){
    $("#divAlertTextoMedios").html(textoAlert);
    $( "#dialog-AlertMedios" ).dialog({
		autoOpen: true,
		resizable: false,
		//height:290,
		//width:300,
		modal: true,
		buttons: {
			"Aceptar": function() {
				$( this ).dialog( "close" );
				$("#divAlertTexto").html("");
				
				
			}
			
		}
	});		
    
    }
    
    //funcion para limpiar los datos de los Grids
    function limpiarDtsMdsDCntct()
    {
    	jQuery("#list5").clearGridData(true);
    	jQuery("#correos").clearGridData(true);
    }

</script>




<div id="dialog-AlertMedios" style="display: none">
	<table>
	<tr>
	<td>
	<span id="divAlertTextoMedios"></span>
	</table>	
	</div>
<div style="display: none">
<table border="0" width="100%" class="fondoClaroAp">
  <tr>
    <td>
	    <table>
			<tbody>
				<tr>
					<td>Número telefónico:</td>
					<td><input type="button" id='btnAgregarTelefono' value="+"/>&nbsp;<input type="button" id='btnEliminarTelefono' value="-"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="tblTelefonos">
							<tr id="tblTelefonosEncabezado">
								<td>Tipo de teléfono</td>
								<td>Código de País</td>
								<td>Lada</td>
								<td>Número telefónico</td>
							</tr>
						</table>	
					</td>
					
				</tr>
			</tbody>
		</table>
    </td>
    
    <td>
    	<table>
			<tbody>
				<tr>
					<td>Correo electrónico</td>
					<td><input type="button" id='btnAgregarCorreo' value="+"/>&nbsp;<input type="button" id='btnEliminarCorreo' value="-"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="tblCorreos">
							<tr id="tblCorreoEncabezado">
								<td>Correo electrónico</td>
							</tr>
						</table>	
					</td>
					
				</tr>
			</tbody>
		</table>
    </td>
  </tr>
</table>

<div id="divTelefono" title="Nuevo Teléfono">
	<table border="0" width="100%" class="fondoClaroAp">
		<tr>
			<td>Tipo de teléfono</td>
			<td>
				<select id="cmbTipoTelefono">						
					<option value="-1">-Seleccione-</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Código de País: </td>
			<td><input type="text" size="8" maxlength="8" id="txtCvePais"/></td>
		</tr>
		<tr>
			<td>Lada:</td>
			<td><input type="text" size="8" maxlength="8" id="txtLada"/></td>
		</tr>
		<tr>
			<td>Número telefónico:</td>
			<td><input type="text" size="15" maxlength="15" id="txtTelefono"/></td>
		</tr>
	</table>
</div>

<div id="divCorreo" title="Nuevo Correo">
	<table border="0" width="100%" class="fondoClaroAp">
		<tr>
			<td>Correo electrónico:</td>
			<td><input type="text" size="60" maxlength="60" id="txtCorreo"/></td>
		</tr>
		<tr>
			<td>Confirmar correo:</td>
			<td><input type="text" size="60" maxlength="60" id="txtConfirmarCorreo"/></td>
		</tr>
	</table>
</div>
<div id="divCorreo2" title="Nuevo Correo" style="display: none;">
	<table border="0" width="100%" class="fondoClaroAp">
		<tr>
			<td align="right">Correo electrónico:</td>
			<td><input type="text" size="60" maxlength="60" id="txtCorreo2"/></td> 
		</tr>
		<tr>
			<td align="right">Confirmar correo:</td>
			<td><input type="text" size="60" maxlength="60" id="txtConfirmarCorreo2"/></td>
		</tr>
	</table>
</div>
</div>
<div id="divTelefono2" title="Nuevo Teléfono" style="display: none;">
	<table border="0" width="100%" class="fondoClaroAp">
		<p align="center">
				</br><b>Los campos marcados con el s&iacute;mbolo (*) son obligatorios.</b></br></br>
		</p>
		<tr>
			<td align="right">* Tipo de teléfono:</td>
			<td>
				<select id="cmbTipoTelefono1">						
					<option value="-1">-Seleccione-</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">* Código de País:</td>
			<td><input type="text" size="8" maxlength="8" id="txtCvePais1" onKeyPress="return solonumeros(event);"/></td>
		</tr>
		<tr>
			<td align="right">* Lada:</td>
			<td><input type="text" size="8" maxlength="8" id="txtLada1" onKeyPress="return solonumeros(event);"/></td>
		</tr>
		<tr>
			<td align="right">* Número telefónico:</td>
			<td><input type="text" size="15" maxlength="15" id="txtTelefono1" onKeyPress="return solonumeros(event);"/></td>
		</tr>
	</table>
</div>

<div id="nuevaForma">
<table width="78%"><tr><td width="50%"><table id="list5"></table>
<div id="pager50"></div></td><td width="50%"><table id="correos"></table>
<div id="pager60"></div></td></tr></table>







</div>
<div id="divCorreo3" title="Nuevo Correo" style="display: none;">
	<table border="0" width="100%" class="fondoClaroAp">
		<tr>
			<td>Correo electrónico:</td>
			<td><input type="text" size="60" maxlength="60" id="txtCorreo3"/></td> 
		</tr>
		<tr>
			<td>Confirmar correo:</td>
			<td><input type="text" size="60" maxlength="60" id="txtConfirmarCorreo3"/></td>
		</tr>
	</table>
</div>

<div id="divTelefono3" title="Nuevo Teléfono" style="display: none;">
	<table border="0" width="100%" class="fondoClaroAp">
		<tr>
			<td>Tipo de teléfono</td>
			<td>
				<select id="cmbTipoTelefono3">						
					<option value="-1">-Seleccione-</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Código de País:</td>
			<td><input type="text" size="8" maxlength="8" id="txtCvePais3"/></td>
		</tr>
		<tr>
			<td>Lada:</td>
			<td><input type="text" size="8" maxlength="8" id="txtLada3"/></td>
		</tr>
		<tr>
			<td>Número telefónico:</td>
			<td><input type="text" size="15" maxlength="15" id="txtTelefono3"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
inicializaMediosContacto('<%= request.getContextPath()%>');


var correoCorrecto="";
var varTipo=""; 
var varTipoarr= new Array();
var varPais=""; 
var varLada; 
var varTelefono="";
var varCorreo;
var varidTipo;
var  x=0;
var  y=0;

//$("#cmbTipoTelefono").multiselect({ 
//	multiple: false, 
//	header: "Seleccione", 
//	position: { 
//		my: 'bottomr', 
//		at: 'bottom' 
//	},
//	selectedList: 1 
//});


/**
 * Función encargada de pintar los medios de contacto
 */
function pintaDatosContacto(xml){
		 
	$('#tblTelefonos').empty();
	$('#tblTelefonos').append('<tr><td>Tipo de teléfono</td>&nbsp;<td>Código de País</td>&nbsp;<td>Lada</td>&nbsp;<td>Número telefónico</td></tr>');
	$(xml).find('TelefonoDTO').each(function(){

		var tel = "";
		tel = new Telefono($(this).find('idCampo').first().text(),$(this).find('valor').first().text(),$(this).find('codigoPais').first().text(),$(this).find('codigoArea').first().text(),$(this).find('numeroTelefonico').first().text());
		$('#tblTelefonos').append('<tr><td>'+ tel.nombreTipoTelefono +'</td>&nbsp;<td>'+tel.cvePais+'</td>&nbsp;<td>'+tel.lada+'</td>&nbsp;<td>'+tel.telefono+'</td></tr>');
	});

	$('#tblCorreos').empty();
	$('#tblCorreos').append('<tr><td>Dirección electrónica</td></tr>');
	$(xml).find('CorreoElectronicoDTO').each(function(){

		var correo = "";
		correo = new Correo($(this).find('medioDeContadoId').first().text(),$(this).find('direccionElectronica').first().text());
		$('#tblCorreos').append('<tr><td>'+ correo.dirElectronica +'</td></tr>');
	});

	//_______________________________________________
	//Esta funcion era la que tenía originalmente 

	// var telefono = new Telefono($(xml).find('telefonosDTO').find('idCampo').text(),$(xml).find('telefonosDTO').find('valor').text(),$(xml).find('telefonosDTO').find('codigoPais').text(),$(xml).find('telefonosDTO').find('codigoArea').text(),$(xml).find('telefonosDTO').find('numeroTelefonico').text());

	//	lstTelefonos[idTelefono] = telefono;
	//	idTelefono++;
	//	$('#tblTelefonos').empty();
	//	$('#tblTelefonos').append('<tr><td>Tipo de teléfono</td>&nbsp;<td>Código de País</td>&nbsp;<td>Lada</td>&nbsp;<td>Número telefónico</td></tr>');
	//	for(var i=0;i<lstTelefonos.length;i++){
	//		var tel = lstTelefonos[i];
	//		$('#tblTelefonos').append('<tr><td>'+ tel.nombreTipoTelefono +'</td>&nbsp;<td>'+tel.cvePais+'</td>&nbsp;<td>'+tel.lada+'</td>&nbsp;<td>'+tel.telefono+'</td></tr>');
	//	}
}

/**
 * Función encargada de deshabilitar el funcionamiento de la pagina
 */
function deshabilitaMediosContacto(){

	$('#btnAgregarCorreo').attr("disabled","disabled");
	$('#btnAgregarTelefono').attr("disabled","disabled");
	$('#btnEliminarTelefono').attr("disabled","disabled");
	$('#btnEliminarCorreo').attr("disabled","disabled");	
	
}



$("#divCorreo2").dialog({ autoOpen: false, 
	modal: true, 
	title: 'Agregar correo', 
	dialogClass: 'alert', 
	width: 500 ,
	maxWidth: 600,
	buttons:{"Aceptar":function() 
		{
		if(parseInt(jQuery("#correos").getDataIDs().length) <30){
			y++;
			var correo1 = $('#txtCorreo2').val();
			var correo2 = $('#txtConfirmarCorreo2').val();
				if(correo1==correo2){
					varCorreo=correo1;
					if(isEmailAddress(correo1)){
						$(this).dialog("close");				
						var datarow = {correo: ""+ varCorreo +""};
						var su=jQuery("#correos").jqGrid('addRowData',y,datarow);
						idCorreo++;
					}else
						alertDinamicoMedios('Correo incorrecto');				
			}else{
				alertDinamicoMedios("El correo y la confirmaci\u00F3n no coinciden, favor de verificar");
			}
		}
		else{
			alertDinamicoMedios('Ya se capturaron 30 correos');
			$(this).dialog("close");
		}
		},
		"Cancelar":function() {
			$(this).dialog("close");
		}
	}
});

$.ajax({
	  type: 'POST',
	  url: '<%=request.getContextPath()%>/CargarCatalogoTiposTelefono.do',
	  data: '',
	  dataType: 'xml',
	  async: false,
	  success: function(xml){
		  var clave;
		  var valor;
		  var ok;
	      $(xml).find('catalogoDTO').each(function(){
	    	  clave=$(this).find('clave').text();
	    	  valor=$(this).find('valor').text();
		      $('#cmbTipoTelefono1').append('<option value="' + clave + '">' + valor + '</option>');
		      $('#cmbTipoTelefono3').append('<option value="' + clave + '">' + valor + '</option>');
		  });
	  }
});

//varTipo varPais varLada varTelefono
$("#divTelefono2").dialog({ autoOpen: false, 
	modal: true, 
	title: 'Agregar teléfono', 
	dialogClass: 'alert', 
	width: 355 ,
	height: 305,
	maxWidth: 600 ,
	buttons:{"Aceptar":function() {
		if(parseInt(jQuery("#list5").getDataIDs().length) <30)
		{
			if(parseInt($('#cmbTipoTelefono1 option:selected').val())!=-1 && $('#txtCvePais1').val().length>0 && $('#txtLada1').val().length && $('#txtTelefono1').val().length>0)
			{
				x++;
				
				$(this).dialog("close");
				varidTipo=$('#cmbTipoTelefono1 option:selected').val();
				varTipo=$('#cmbTipoTelefono1 option:selected').text();
		
				varPais=$('#txtCvePais1').val();
				varLada=$('#txtLada1').val();
				varTelefono=$('#txtTelefono1').val();
				idTelefono++;
				
				var datarow = {idTipo:""+varidTipo+"",tipo:""+varTipo+"",pais:""+varPais+"",lada:""+varLada+"",Telefono:""+varTelefono+""};
				var su=jQuery("#list5").jqGrid('addRowData',x,datarow);
			}
			else
			{
				alertDinamicoMedios("Favor de revisar que se han capturado todos los campos obligatorios");	
				//$(this).dialog("close")
			}
		}else{
			alertDinamicoMedios('Ya se capturaron 30 teléfonos');
			$(this).dialog("close");
		}
		},
		"Cancelar":function() {
			$(this).dialog("close");
		}
	}
});

function borraInfoCorre(){
	if(borraInfo>0)
	{
		var su=jQuery("#correos").jqGrid('delRowData',borraInfo);
		borraInfo=0;
	}
	else
	{
		alertDinamicoMedios("Seleccione el registro a borrar");
	}
	
	}
function borraInfoTelefono(){
	if(borraInfo>0)
	{
		var su=jQuery("#list5").jqGrid('delRowData',borraInfo);
		borraInfo=0;
	}
	else
	{
		alertDinamicoMedios("Seleccione el registro a borrar");
	}

}




jQuery("#list5").jqGrid({        
    url:'local',
	datatype: "xml",
   	colNames:['idTipo','Tipo','Código País', 'Lada', 'Teléfono'],
   	colModel:[
   	       {name:'idTipo',index:'tipo', width:55,editable:true,hidden:true}, 
   		{name:'tipo',index:'tipo', width:55,editable:true},  
   		{name:'pais',index:'invdate', width:90,editable:true},
   		{name:'lada',index:'name', width:100,editable:true},
   		{name:'Telefono',index:'amount', width:80, editable:true}
   			
   	],
   	rowNum:30,
   	rowList:[30],
   	pager: '#pager5',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc",
    multiselect: false,
    caption:"Número de Teléfono",
    editurl:"ingresarMediosContacto.jsp",
    toolbar: [true,"top"],
    
    ondblClickRow: function(rowID) {
        if (modificaGrid==true){
        	ditable=1;
        	modificarTelefono(rowID);
    	//jQuery("#list5").jqGrid('editRow',rowID);
		//this.disabled = 'true';
        	
        }
    						},
    					onSelectRow: function(rowID) {
							borraInfo=rowID;
    						
    						//jQuery("#list5").jqGrid('setSelection',rowID);
    						
    						}
}).navGrid("#pager5",{edit:false,add:false,del:false});
$("#t_list5").append("<input type='button' id='telefonobotonagre' class='btn_Generico' value='Ingresar N&uacute;mero Telef&oacute;nico' style='height:20px;font-size:-3'/>&nbsp;&nbsp;<input type='button' id='telefonobotonelim' value='Borrar N&uacute;mero Telef&oacute;nico' style='height:20px;font-size:-3' class='btn_Generico'/>");
$("#telefonobotonagre","#t_list5").click(function(){
	limpiarPopUpTelefono();
	$("#divTelefono2").dialog("open");
	
	
	
	//this.disabled = 'true';
});
$("#telefonobotonelim","#t_list5").click(function(){
	borraInfoTelefono();
	
	
});

jQuery("#correos").jqGrid({        
  	url:'local',
	datatype: "xml",
   	colNames:['Correo',],
   	colModel:[
   		{name:'correo',index:'tipo', width:300,editable:true},
   		
   			
   	],
   	rowNum:30,
   	rowList:[30],
   	pager: '#pager6',
   	width:300,
//autowidth: true,
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc",
    multiselect: false,
    caption:"Correo Electrónico",
    editurl:"ingresarMediosContacto.jsp",
    toolbar: [true,"top"],
    
    ondblClickRow: function(rowID) {
    	 if (modificaGrid==true){
    		 ditable=1;
    		 modificaCorreo(rowID);
    	 
   // jQuery("#correos").jqGrid('editRow',rowID);
	//	this.disabled = 'true';
		
    	 }
    						},
    					onSelectRow: function(rowID) {
    						borraInfo=rowID;

    						
    						}
}).navGrid("#correos",{edit:false,add:false,del:false});
$("#t_correos").append("<input type='button' id='correobotonagre' value='Ingresar Correo' style='height:20px;font-size:-3' class='btn_Generico'/>&nbsp;&nbsp;<input type='button' id='correobotonelim' value='Borrar Correo' style='height:20px;font-size:-3' class='btn_Generico'/>");
$("#correobotonagre","#t_correos").click(function(){
	limpiarPopUpCorreo();
	$("#divCorreo2").dialog("open");

});

$("#correobotonelim","#t_correos").click(function(){
	borraInfoCorre();
	
			
	
});

function modificaCorreo(idrow){

	var cor = jQuery("#correos").jqGrid('getRowData',idrow);
	
	var correo1 = $('#txtCorreo3').val(cor.correo);
	var correo2 = $('#txtConfirmarCorreo3').val(cor.correo);
	
$("#divCorreo3").dialog({ autoOpen: true, 
	modal: true, 
	title: 'Agregar correo', 
	dialogClass: 'alert', 
	width: 500 ,
	maxWidth: 600,
	buttons:{"Aceptar":function() {
	

		
		var correo1 = $('#txtCorreo3').val();
		var correo2 = $('#txtConfirmarCorreo3').val();
			if(correo1==correo2){
				varCorreo=correo1;									  
				
				$(this).dialog("close");
				
			//	var datarow = {correo: ""+ varCorreo +""};
				var su=jQuery("#correos").jqGrid('setRowData',idrow,{correo: ""+ varCorreo +""});
				
				//var su=jQuery("#correos").jqGrid('addRowData',idrow,datarow);

				
		}else{
			alertDinamicoMedios('Correo incorrecto');
		}
		
		},
		"Cancelar":function() {
			$(this).dialog("close");
		}
	}
});

}

function modificarTelefono(idrow){
	var tel = jQuery("#list5").jqGrid('getRowData',idrow);

	$('#cmbTipoTelefono3').find("option[value='"+tel.idTipo+"']").attr("selected","selected");
	
	//varTipo=$('#cmbTipoTelefono1 option:selected').val(tel.idTipo);
	varPais=$('#txtCvePais3').val(tel.pais);
	varLada=$('#txtLada3').val(tel.lada);
	varTelefono=$('#txtTelefono3').val(tel.Telefono);
	    
	$("#divTelefono3").dialog({ autoOpen: true, 
		modal: true, 
		title: 'Agregar teléfono', 
		dialogClass: 'alert', 
		width: 308.733 ,
		height: 215.733,
		maxWidth: 600 ,
		buttons:{"Aceptar":function() {
				if(parseInt($('#cmbTipoTelefono3 option:selected').val())!=-1)
				{
					$(this).dialog("close");
					varidTipo=$('#cmbTipoTelefono3 option:selected').val();
					varTipo=$('#cmbTipoTelefono3 option:selected').text();
		
					
					varPais=$('#txtCvePais3').val();
					varLada=$('#txtLada3').val();
					varTelefono=$('#txtTelefono3').val();
					var su=jQuery("#list5").jqGrid('setRowData',idrow,{idTipo:""+varidTipo+"",tipo:""+varTipo+"",pais:""+varPais+"",lada:""+varLada+"",Telefono:""+varTelefono+""}); 
				}
				else
				{
					alertDinamicoMedios("Favor de revisar su información");	
				}
			},
			"Cancelar":function() {
				$(this).dialog("close");
			}
		}
	});

	
}
</script>
<!-- -->
