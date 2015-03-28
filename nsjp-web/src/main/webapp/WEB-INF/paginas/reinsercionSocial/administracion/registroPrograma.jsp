<%@page import="java.util.Date"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<script type="text/javascript">
<%Long noCache = new Date().getTime();%>

	var datosCC = {
		id:"", nombre:"", categoria:"", puntos:""
	};

	$(document).ready(
			function() {			
					jQuery("#apTabsContenido<%=noCache%>").tabs();
					jQuery("#adminProg<%=noCache%>").tabs();	
					jQuery("#adminCursos<%=noCache%>").tabs();
					jQuery("#adminTrabajos<%=noCache%>").tabs();
					
					var dates =	$("#catProgramaDTOFechaInicioPrograma, #catProgramaDTOFechaFinPrograma").datepicker(
						{
//							defaultDate: "+1w",
							changeMonth: true,
							numberOfMonths: 2,
							onSelect: function( selectedDate ) {
								var option = this.id == "catProgramaDTOFechaInicioPrograma" ? "minDate" : "maxDate",
								instance = $( this ).data( "datepicker" ),
								date = $.datepicker.parseDate(
								instance.settings.dateFormat ||
								$.datepicker._defaults.dateFormat,
								selectedDate, instance.settings );
								dates.not( this ).datepicker( "option", option, date );
													
							},
							showOn: "button",
							buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
							buttonImageOnly: true			
						}
					);
						
						
					var lastsel3; 
					jQuery("#jqGridCursos, #jqGridTrabajos, #jqGridCeresos").jqGrid( { 
						datatype: "local", 
						height: 110,
						width: 600,
						colNames:['Nombre', 'Categoría', 'Puntos'], 
						colModel:[ 	 
									{name:'nombre',		index:'nombre', 	editable: false}, 
									{name:'categoria',	index:'categoria', 	editable: false}, 
									{name:'puntos',		index:'puntos', 	editable: false,	width:100, align:'center',	formatter: 'integer','summaryType': 'sum' }
							 	],
						onSelectRow: function(id){
								}, 
						gridComplete: function(id){
							sumarPuntos(jQuery(this));
						},
						caption: "",
						footerrow : true, 
						userDataOnFooter : true, 
						altRows : true,
		           		autowidth:true,
            			shrinkToFit:true,
            			multiselect:true
					}); 		
					
			
					var footerCursos = 	{name:'TOTAL:' , puntos:0};			
					var footerTrabajos = 	{name:'TOTAL:' , puntos:0};	
			
					jQuery("#jqGridCursos").jqGrid('setCaption',"Cursos Asociados Al Programa");
					jQuery("#jqGridTrabajos").jqGrid('setCaption',"Trabajos Asociados Al Programa");
					jQuery("#jqGridCeresos").jqGrid('setCaption',"Centros De Detención Asociados Al Programa");
					
					jQuery("#dialog:ui-dialog" ).dialog( "destroy" );
					
					jQuery("#dialog-cursos, #dialog-trabajos, #dialog-ceresos" ).dialog({
						autoOpen: false,
						height: 'auto',
						width:'auto',
						modal: true,
						resizable: false
					});

					$( "#dialog-confirm" ).dialog({
						resizable: false,
						height:'auto',
						width:'auto',
						modal: true,
						autoOpen: false
					});
					
					jQuery("#dialog-registroCursosExito").dialog({
						autoOpen: false,
						height: 'auto',
						width:'auto',
						modal: true,
						buttons: {
							Ok: function() {
								$( this ).dialog( "close" );
							}
						},
						resizable: false
					});
					
					jQuery("#dialog-validarDatosCurso").dialog({
						autoOpen: false,
						height: 'auto',
						width:'auto',
						modal: true,
						buttons: {
							Ok: function() {
								$( this ).dialog( "close" );
							}
						},
						resizable: false
					});

					jQuery("#jqGridTrabajos").jqGrid("setLabel","categoria","Tipo de Curso","");
					jQuery("#jqGridCeresos").jqGrid("setLabel","categoria","Tipo de Centro","");
					jQuery("#jqGridCeresos").jqGrid(
						{ 
						footerrow : false, 
						userDataOnFooter : false
					}).hideCol("puntos").trigger("reloadGrid");
					jQuery("#jqGridCeresos").jqGrid('footerData','set',{categoria:''});

					jQuery( "#btnAsociarCurso" ).button().click(
						function() {
							var grid = jQuery("#jqGridCursos");
							
							jQuery("#dialog-cursos").dialog({ 
								title: "Asociar Curso",
								buttons: {
									"Asociar": function() {
										agregarDatoAGrid(grid, datosCC);
										jQuery("#lstCurso").find("option:selected").hide();
										jQuery("#lstCurso").val('-1');		
										jQuery("#checkboxesCurso :checkbox[value='"+datosCC.id+"']").attr('checked', true ); 
										jQuery( this ).dialog( "close" );
									},
									"Cancelar": function() {
										jQuery( this ).dialog( "close" );
									}
								},
								open:function(){
									jQuery("div[id^='detallesCC']").hide();
								},
								close: function() {
									jQuery( "#btnAsociarCurso" ).button("refresh");
									jQuery( "#btnAsociarCurso" ).removeClass('ui-state-focus');
									sumarPuntos(grid);
								}						
							});
							jQuery("#dialog-cursos").dialog("open");
						}
					);
					
					jQuery("#btnEliminarCurso").button().click(
						function() {
							var grid = jQuery("#jqGridCursos");
							var idSeleccionados = grid.jqGrid().getGridParam("selarrrow");
							var len = idSeleccionados.length;
							if(len<=0){
								jQuery( "#confirmText" ).html("Para poder eliminar, al menos se debe seleccionar un curso");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Curso",
									buttons: {
										"Aceptar": function() {
											$( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarCurso" ).button("refresh");
										jQuery( "#btnEliminarCurso" ).removeClass('ui-state-focus');
										sumarPuntos(grig);
									}
								});
							}else{
								jQuery( "#confirmText" ).html("Los elementos seleccionados serán eliminados del programa,<br /> ¿Desea continuar?");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Curso",
									buttons: {
										"Eliminar": function() {
											for(var i=len-1; i>=0; i--){
												var optionId = "#lstCurso option[value='"+idSeleccionados[i]+"']";
												var checkId = "#checkboxesCurso :checkbox[value='"+idSeleccionados[i]+"']";
												eliminarDatoGrid(grid, idSeleccionados[i]);
												jQuery(optionId).show();
												jQuery(checkId).attr('checked', false );
											}
											jQuery( this ).dialog( "close" );
										},
										"Cancelar": function() {
											jQuery( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarCurso" ).button("refresh");
										jQuery( "#btnEliminarCurso" ).removeClass('ui-state-focus');
										sumarPuntos(grid);
									}
								});
							}	
							
							jQuery("#dialog-confirm").dialog("open");
						}
					);
									
					jQuery("#lstCurso").change(
						function() {
							var optionId = jQuery(this).find("option:selected").attr("id");
							jQuery("div[id^='detallesCC']").hide();
							jQuery("#detalles" + optionId ).show();	
							datosCC.id = $(this).find("option:selected").val();
							datosCC.nombre = $.trim($(this).find("option:selected").text());
							datosCC.categoria = jQuery("#ccc"+optionId).val();
							datosCC.puntos = jQuery("#puntos"+optionId).val();
							jQuery("#detalles" + optionId ).show();
						}
					);


					jQuery("#btnAsociarTrabajo").button().click(
						function() {
							var grid = jQuery("#jqGridTrabajos");
							jQuery("#dialog-trabajos").dialog({ 
								title: "Asociar Trabajo",
								buttons: {
									"Asociar": function() {
										agregarDatoAGrid(grid, datosCC);
										jQuery("#lstTrabajo").find("option:selected").hide();
										jQuery("#lstTrabajo").val('-1');		
										jQuery("#checkboxesTrabajo :checkbox[value='"+datosCC.id+"']").attr('checked', true ); 
										jQuery( this ).dialog( "close" );
									},
									"Cancelar": function() {
										jQuery( this ).dialog( "close" );
									}
								},
								open:function(){
									jQuery("div[id^='detallesCT']").hide();
								},
								close: function() {
									jQuery( "#btnAsociarTrabajo" ).button("refresh");
									jQuery( "#btnAsociarTrabajo" ).removeClass('ui-state-focus');
									sumarPuntos(grid);
								}						
							});
							jQuery( "#dialog-trabajos" ).dialog("open" );
						}
					);
					
					jQuery( "#btnEliminarTrabajo" ).button().click(
						function() {
							var grid = jQuery("#jqGridTrabajos");
							var idSeleccionados = grid.jqGrid().getGridParam("selarrrow");
							var len = idSeleccionados.length;
							if(len<=0){
								jQuery( "#confirmText" ).html("Para poder eliminar, al menos se debe seleccionar un trabajo");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Trabajo",
									buttons: {
										"Aceptar": function() {
											$( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarTrabajo" ).button("refresh");
										jQuery( "#btnEliminarTrabajo" ).removeClass('ui-state-focus');
										sumarPuntos(grig);
									}
								});
							}else{
								jQuery( "#confirmText" ).html("Los elementos seleccionados serán eliminados del programa,<br /> ¿Desea continuar?");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Trabajo",
									buttons: {
										"Eliminar": function() {
											for(var i=len-1; i>=0; i--){
												var optionId = "#lstTrabajo option[value='"+idSeleccionados[i]+"']";
												var checkId = "#checkboxesTrabajo :checkbox[value='"+idSeleccionados[i]+"']";
												eliminarDatoGrid(grid, idSeleccionados[i]);
												jQuery(optionId).show();
												jQuery(checkId).attr('checked', false );
											}
											jQuery( this ).dialog( "close" );
										},
										"Cancelar": function() {
											jQuery( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarTrabajo" ).button("refresh");
										jQuery( "#btnEliminarTrabajo" ).removeClass('ui-state-focus');
										sumarPuntos(grid);
									}
								});
							}	
							
							jQuery( "#dialog-confirm" ).dialog("open");
						}
					);
									
					jQuery("#lstTrabajo").change(
						function() {
							
							var optionId = jQuery(this).find("option:selected").attr("id");
							jQuery("div[id^='detallesCT']").hide();
							jQuery("#detalles" + optionId ).show();
							datosCC.id = $(this).find("option:selected").val();
							datosCC.nombre = $.trim($(this).find("option:selected").text());
							datosCC.categoria = jQuery("#tipo"+optionId).val();
							datosCC.puntos = jQuery("#puntos"+optionId).val();
							jQuery("#detalles" + optionId ).show();
						}
					);


					jQuery("#btnAsociarCereso").button().click(
						function() {
							var grid = jQuery("#jqGridCeresos");
							jQuery("#dialog-ceresos").dialog({ 
								title: "Asociar Centro De Detención",
								buttons: {
									"Asociar": function() {
										agregarDatoAGrid(grid, datosCC);
										jQuery("#lstCereso").find("option:selected").hide();
										jQuery("#lstCereso").val('-1');		
										jQuery("#checkboxesCeresos :checkbox[value='"+datosCC.id+"']").attr('checked', true ); 
										jQuery( this ).dialog( "close" );
									},
									"Cancelar": function() {
										jQuery( this ).dialog( "close" );
									}
								},
								open:function(){
									jQuery("div[id^='detallesCS']").hide();
								},
								close: function() {
									jQuery( "#btnAsociarCereso" ).button("refresh");
									jQuery( "#btnAsociarCereso" ).removeClass('ui-state-focus');
								}						
							});
							jQuery( "#dialog-ceresos" ).dialog("open" );
						}
					);
					
					jQuery( "#btnEliminarCereso" ).button().click(
						function() {
							var grid = jQuery("#jqGridCeresos");
							var idSeleccionados = grid.jqGrid().getGridParam("selarrrow");
							var len = idSeleccionados.length;
							if(len<=0){
								jQuery( "#confirmText" ).html("Para poder eliminar, al menos se debe seleccionar un centro de detención");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Centro De Detención",
									buttons: {
										"Aceptar": function() {
											$( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarCereso" ).button("refresh");
										jQuery( "#btnEliminarCereso" ).removeClass('ui-state-focus');
									}
								});
							}else{
								jQuery( "#confirmText" ).html("Los elementos seleccionados serán eliminados del programa,<br /> ¿Desea continuar?");
								jQuery( "#dialog-confirm" ).dialog({ 
									title: "Eliminar Centro De Detención",
									buttons: {
										"Eliminar": function() {
											for(var i=len-1; i>=0; i--){
												var optionId = "#lstCereso option[value='"+idSeleccionados[i]+"']";
												var checkId = "#checkboxesCeresos :checkbox[value='"+idSeleccionados[i]+"']";
												eliminarDatoGrid(grid, idSeleccionados[i]);
												jQuery(optionId).show();
												jQuery(checkId).attr('checked', false );
											}
											jQuery( this ).dialog( "close" );
										},
										"Cancelar": function() {
											jQuery( this ).dialog( "close" );
										}
									},
									close: function() {
										jQuery( "#btnEliminarCereso" ).button("refresh");
										jQuery( "#btnEliminarCereso" ).removeClass('ui-state-focus');
									}
								});
							}	
							
							jQuery( "#dialog-confirm" ).dialog("open");
						}
					);
									
					jQuery("#lstCereso").change(
						function() {
							var optionId = jQuery(this).find("option:selected").attr("id");
							jQuery("div[id^='detallesCS']").hide();
							jQuery("#detalles" + optionId ).show();
							datosCC.id = $(this).find("option:selected").val();
							datosCC.nombre = $.trim($(this).find("option:selected").text());
							datosCC.categoria = jQuery("#tipo"+optionId).val();
							datosCC.puntos = jQuery("#puntos"+optionId).val();
							jQuery("#detalles" + optionId ).show();
						}
					);

					<%-- if (request.getParameter("nombreCurso") != null){%>
						jQuery("#dialog-registroCursosExito" ).dialog("open");
					<%}--%>	
			
					
					
					jQuery("input[name='besExternoCatTrabajo']").change(function(){
    					if (jQuery("input[name='besExternoCatTrabajo']:checked").val() == '0'){
    						jQuery(".datosEsExterno").hide();
    						jQuery("#catTipoTrabajoExternoId").val(-1);
    						jQuery("#numeroConvenioCatTrabajo").val("");
    					}
    					else if (jQuery("input[name='besExternoCatTrabajo']:checked").val() == '1'){
    						jQuery(".datosEsExterno").show();
    					}
					});										

					jQuery(".initRadio").attr("checked","checked");
					jQuery(".datosEsExterno").hide();
					
					
					jQuery("#gridCatProgramas").jqGrid({
						datatype: "xml", 
						rowNum:10,
						rowList:[10,20,30,40,50,60,70,80,90,100],
						autowidth: true,
						height:'auto',
						minWidth: 500, 
						minHeight: 200,						
						caption:"Programas",
						viewrecords: true,
						onSelectRow: function(id){
						},
						ondblClickRow: function(id) {
							llenarForma("apTabs-1", id);
						},
						sortorder: "asc",		           		
						autowidth:true,
            			shrinkToFit:true,
						url:'<%=request.getContextPath()%>/llenarGrid.do?gridID=apTabs-1',
						colNames:['Nombre','Tipo', 'Descripción', 'No. Puntos','Fecha Inicio', 'Fecha Termino'], 
						colModel:[ 	{name:'Nombre',index:'1' }, 
									{name:'Tipo',index:'2'}, 
									{name:'Descripcion',index:'3'}, 
									{name:'NoPuntos',index:'4'},
									{name:'FechaInicio',index:'5'},
									{name:'FechaTermino',index:'6'}
								],
						pager: jQuery('#pagerCatProgramas')
					}).navGrid('#pagerCatProgramas',{edit:false,add:false,del:false});
										
					jQuery("#gridCatCursos").jqGrid({
						datatype: "xml", 
						rowNum:10,
						rowList:[10,20,30,40,50,60,70,80,90,100],
						autowidth: true,
						height:'auto',
						minWidth: 500, 
						minHeight: 200,
						caption:"Cursos",
						viewrecords: true,
						onSelectRow: function(id){
						},
						ondblClickRow: function(id) {
							llenarForma("apTabs-2", id);
						},
						sortorder: "asc",		           		
						autowidth:true,
            			shrinkToFit:true,
						url:'<%=request.getContextPath()%>/llenarGrid.do?gridID=apTabs-2',
						colNames:['Nombre','Descripción', 'Tipo', 'Categoría','Tipo De Nivel Académico', 'No. Puntos', 'Duración'], 
						colModel:[ 	{name:'Nombre',index:'1'}, 
									{name:'Descripcion',index:'2'}, 
									{name:'Tipo',index:'3'}, 
									{name:'Categoria',index:'4'},
									{name:'TipoNivelAcademico',index:'5'},
									{name:'NoPuntos',index:'5'},
									{name:'Duracion',index:'6'}
								],
						pager: jQuery('#pagerCatCursos')
					}).navGrid('#pagerCatCursos',{edit:false,add:false,del:false});
										
					jQuery("#gridCatTrabajos").jqGrid({
						datatype: "xml", 
						rowNum:10,
						rowList:[10,20,30,40,50,60,70,80,90,100],
						autowidth: true,
						height:'auto',
						minWidth: 500, 
						minHeight: 200,						
						caption:"Trabajos",
						viewrecords: true,
						onSelectRow: function(id){
						},
						ondblClickRow: function(id) {
							llenarForma("apTabs-3", id);
						},
						sortorder: "asc",		           		
						autowidth:true,
            			shrinkToFit:true,
						url:'<%=request.getContextPath()%>/llenarGrid.do?gridID=apTabs-3',
						colNames:['Nombre','Descripción', 'Tipo De Trabajo', 'Tipo De Trabajo Externo','No. Convenio', 'No. Puntos'], 
						colModel:[ 	{name:'Nombre',index:'1'}, 
									{name:'Descripcion',index:'2'}, 
									{name:'TipoDeTrabajo',index:'3'}, 
									{name:'Tipo De Trabajo Externo',index:'4'},
									{name:'No. Convenio',index:'5'},
									{name:'No. Puntos',index:'6'}
								],
						pager: jQuery('#pagerCatTrabajos')
					}).navGrid('#pagerCatTrabajos',{edit:false,add:false,del:false});
					
					//jQuery("#gridCatProgramas").trigger("reloadGrid");
					//jQuery("#gridCatCursos").trigger("reloadGrid");
					//jQuery("#gridCatTrabajos").trigger("reloadGrid");					
					
					<%	String reg = request.getAttribute("regInsertado") != null
								 ? request.getAttribute("regInsertado").toString() 
								 : null;
						String accion = request.getAttribute("accion") != null
								 ? request.getAttribute("accion").toString() 
								 : "";
						String mensaje = "";
			if (reg != null) { %>
						jQuery( "#dialog-confirm" ).dialog({ 
							title: "Registro",
							buttons: {
								"Aceptar": function() {
									$( this ).dialog( "close" );
								}
							}
						});
						jQuery('#apTabsContenido<%=noCache%>').tabs("select", "#<%=reg%>");
																
						
						<%	if (reg.equals("apTabs-1")) {
								if (accion == "Actualizado"){
									mensaje = "El <strong>Programa</strong> se ha actualizado con éxito.";
								} else if (accion == "Eliminado"){ 
									mensaje = "El <strong>Programa</strong> se ha eliminado con éxito.";
								} else {
									mensaje = "El <strong>Programa</strong> se ha guardado con éxito.";
								} 
						  	} else if (reg.equals("apTabs-2")) {
								if (accion == "Actualizado"){
									mensaje = "El <strong>Curso</strong> se ha actualizado con éxito.";
								} else if (accion == "Eliminado"){ 
									mensaje = "El <strong>Curso</strong> se ha eliminado con éxito.";
								} else {
									mensaje = "El <strong>Curso</strong> se ha guardado con éxito.";
								} 
						} else if (reg.equals("apTabs-3")) {
							if (accion == "Actualizado"){
								mensaje = "El <strong>Trabajo</strong> se ha actualizado con éxito.";
							} else if (accion == "Eliminado"){ 
								mensaje = "El <strong>Trabajo</strong> se ha eliminado con éxito.";
							} else {
								mensaje = "El <strong>Trabajo</strong> se ha guardado con éxito.";
							}
						} else if (reg.equals("false")) {
							mensaje = "El <strong>Programa</strong> se ha eliminado con éxito.";		
						}
						%>
						jQuery( "#confirmText" ).html("<%=mensaje%>");
						jQuery( "#dialog-confirm" ).dialog("open");		
				<%}%>					
				
			}
	);

					function agregarDatoAGrid(grid, data){
						grid.jqGrid('addRowData',data.id, data);
						sumarPuntos(grid);
					}	
					
					function eliminarDatoGrid(grid,id){
						grid.delRowData(id);
					}
					
					function sumarPuntos(grid){
						var sum = grid.jqGrid('getCol','puntos',false,'sum');
						grid.jqGrid('footerData','set',{categoria:'Total',puntos:sum});
		sumarTotalPuntos();
						return sum;
					}
					
	<%-- if (request.getParameter("nombreCurso") != null){%>
						jQuery("#dialog-registroCursosExito" ).dialog("open");
	<%}--%>	

	function sumarTotalPuntos(){
		var sumCursos = jQuery("#jqGridCursos").jqGrid('getCol','puntos',false,'sum');
		var sumTrabajos = jQuery("#jqGridTrabajos").jqGrid('getCol','puntos',false,'sum');
		var total = sumCursos + sumTrabajos;
		jQuery("#totalPuntosCalc").val(total);
	}	
					
	function validarCheckBoxes(container){
		var checkId = "#" + container + " :checked";
		 var resultado = false;
	     jQuery(checkId).each(function() {
	       resultado = true;
	     });
		return resultado;
			}

	function validarDatosForma(idForma){
		if(idForma == '4' || idForma == '7'){
			idForma ='1';
					}
		if(idForma == '5' || idForma == '8'){
			idForma ='2';
		}			
		if(idForma == '6' || idForma == '9'){
			idForma ='3';
		}		
		switch(idForma)
		{
			case '1':
				var np = jQuery('#nombre').val();
				var ctp = jQuery('#catTipoProgramaId').val();
				var dp = jQuery('#descripcion').val();
				var fip = jQuery('#catProgramaDTOFechaInicioPrograma').val();
				var ffp = jQuery('#catProgramaDTOFechaFinPrograma').val();
				if(np.length<=0){
					customAlert("El campo <strong>Nombre</strong> es requerido", "Error");
					return false;
				}
 				if (ctp == -1){	 					
					customAlert("El campo <strong>Tipo de Programa</strong> es requerido", "Error");
					return false;
 				}   				
				if(dp.length<=0){
					customAlert("El campo <strong>Descripción</strong> es requerido", "Error");
					return false;
				}				
					
				if(fip.length<=0){
					customAlert("El campo <strong>Fecha de Inicio</strong> es requerido", "Error");
					return false;
				}

				if(ffp.length<=0){
					customAlert("El campo <strong>Fecha de Termino</strong> es requerido", "Error");
					return false;
				}				
				
				
				if(!validarCheckBoxes("checkboxesCurso")){
					if(!validarCheckBoxes("checkboxesTrabajo")){
					customAlert("Se debe asociar, al menos, un <strong>Curso</strong> al <strong>Programa</strong>", "Error");
					return false;					
					}
				}
				
				if(!validarCheckBoxes("checkboxesTrabajo")){
					if(!validarCheckBoxes("checkboxesCurso")){
						customAlert("Se debe asociar, al menos, un <strong>Trabajo</strong> al <strong>Programa</strong>", "Error");
						return false;					
					}
				}
				
				if(!validarCheckBoxes("checkboxesCeresos")){
					customAlert("Se debe asociar, al menos, un <strong>Centro De Detención</strong> al <strong>Programa</strong>", "Error");
					return false;										
				}
  				break;
			case '2':
						var nombreCurso = jQuery("#nombreCurso").val();
						var tipoCursoId = jQuery("#tipoCursoId").val();
						var adminCursoCategoriaCursoId = jQuery("#adminCursoCategoriaCursoId").val();
						var puntosCurso = jQuery("#puntosCurso").val();
						if ((nombreCurso === "" || nombreCurso === null)
								|| (puntosCurso === "" || puntosCurso === null || isNaN(puntosCurso) || puntosCurso<1)
								|| (tipoCursoId < 1)
								|| (adminCursoCategoriaCursoId < 1)
							){
							jQuery("#dialog-validarDatosCurso" ).dialog("open");
							return false;
				}
				break;
			case '3':
				var nct = jQuery('#nombreCatTrabajo').val();
				var dct = jQuery('#descripcionCatTrabajo').val();
				var pct = jQuery('#puntosCatTrabajo').val();
				
				if(nct.length<=0){
					customAlert("El campo <strong>Nombre</strong> es requerido", "Error");
					return false;
				}
				if(dct.length<=0){
					customAlert("El campo <strong>Descripción</strong> es requerido", "Error");
					return false;
				}
				if(pct.length<=0){
					customAlert("El campo <strong>Número de Puntos</strong> es requerido", "Error");
					return false;
				}
				if(isNaN(pct)){
					customAlert("El campo <strong>Número de Puntos</strong> debe ser un número entero", "Error");
					return false;
				}				
				
				if(jQuery("input[name='besExternoCatTrabajo']:checked").val() == '1'){
	 				var ctte = jQuery("#catTipoTrabajoExternoId").val();
	 				var ncct = jQuery("#numeroConvenioCatTrabajo").val();
	 				if (ctte == -1){	 					
						customAlert("El campo <strong>Tipo de Trabajo</strong> es requerido", "Error");
						return false;
	 				} 
					if(ncct.length<=0){
						customAlert("El campo <strong>Número de Convenio</strong> es requerido", "Error");
						return false;
					}	 				
				}
  				break;
			default:
  				return false;
		}
		
							return true;
		
						}
	
	function enviarForma(toPath){
		var enviar = validarDatosForma(toPath);
		if(enviar){
			var action= '<%=request.getContextPath()%>';
			switch(toPath)
			{
				case '1':
	  				action += '/guardarPrograma.do';
	  				break;
				case '2':
	  				action += '/guardarCurso.do';
	  				break;
				case '3':
	  				action += '/guardarTrabajo.do';
	  				break;
				case '4':
	  				action += '/actualizarPrograma.do';
	  				break;
				case '5':
	  				action += '/actualizarCurso.do';
	  				break;
				case '6':
	  				action += '/actualizarTrabajo.do';
	  				break;
				case '7':
					preguntarEliminar(action, "Programa");
					return false;
	  				break;
				case '8':
					preguntarEliminar(action, "Curso");
					return false;
	  				break;
				case '9':
					preguntarEliminar(action, "Trabajo");
					return false;
	  				break;	  				
				default:
	  				return false;
					}
			document.getElementById("adminProgForm").action=action;
			document.getElementById("adminProgForm").submit();
		}
	}
					
	function preguntarEliminar(action, tipo){
		var resp = false;
		
		jQuery( "#confirmText" ).html("¿Está seguro de que desea eliminar el <strong>" + tipo + "</strong>?");
		jQuery( "#dialog-confirm" ).dialog({ 
			title: "Eliminar "+ tipo,
			buttons: {
				"Aceptar": function() {
					resp = true;
					$( this ).dialog("close");
				},
				"Cancelar": function() {
					$( this ).dialog("close");
				}				
			},
			close: function(){
				if(resp){
					action += '/eliminar' + tipo + '.do';
					document.getElementById("adminProgForm").action=action;
							document.getElementById("adminProgForm").submit();	
						}
					}
		});				
		
		jQuery("#dialog-confirm").dialog("open");
	}
				
	function cambiarTab(tabId){
		jQuery('#adminProg<%=noCache%>').tabs("select", tabId);
	}									
	
	function llenarForma(catalogo, id){
		GetAJAXValues(
				catalogo, id,
			function(objRemoteData){
				llenandoForma(
						objRemoteData.catalogo,
						objRemoteData.object
				);
			}
		);		
	}

	function llenandoChecks(valor, select, grid, checks, categoria){
		
		jQuery("#"+select).val(valor);
		var optionId = jQuery("#"+select).find("option:selected").attr("id");
		
		datosCC.id = $("#"+select).find("option:selected").val();
		datosCC.nombre = $.trim($("#"+select).find("option:selected").text());
		datosCC.categoria = jQuery("#"+categoria+optionId).val();
		datosCC.puntos = jQuery("#puntos"+optionId).val();
		
		agregarDatoAGrid(jQuery("#"+grid), datosCC);
		jQuery("#"+select).find("option:selected").hide();
		jQuery("#"+select).val('-1');		
		jQuery("#"+ checks +" :checkbox[value='"+datosCC.id+"']").attr('checked', true ); 
		jQuery( this ).dialog( "close" );
		
	}

	function limpiandoChecks(valor, select, grid, checks){

		var optionId = "#" + select + " option[value='"+valor+"']";
		var checkId = "#" + checks + " :checkbox[value='"+valor+"']";
		eliminarDatoGrid(jQuery("#" + grid), valor);
		jQuery(optionId).show();
		jQuery(checkId).attr('checked', false );
		
	}
		
	function llenandoForma(catalogo, object){
		if(catalogo == "apTabs-1"){
			//jsonObject.put("CatProgramaPuntos", totalPuntos);
			jQuery('#programaId').val(object.CatProgramaId);
			jQuery('#nombre').val(object.CatProgramaNombre);
			jQuery('#catTipoProgramaId').val(object.CatTipoProgramaId);
			jQuery('#descripcion').val(object.CatProgramaDescripcion);
			jQuery('#catProgramaDTOFechaInicioPrograma').val(object.CatProgramaFechaInicio);
			jQuery('#catProgramaDTOFechaFinPrograma').val(object.CatProgramaFechaInicio);
			
			for (key in object.CatProgramaCursos) {
				llenandoChecks(
						object.CatProgramaCursos[key], 
						"lstCurso", 
						"jqGridCursos", 
						"checkboxesCurso",
						"ccc");
			}

			for (key in object.CatProgramaTrabajos) {
				llenandoChecks(
						object.CatProgramaTrabajos[key], 
						"lstTrabajo", 
						"jqGridTrabajos", 
						"checkboxesTrabajo",
						"tipo");
			}

			for (key in object.CatProgramaCeresos) {
				llenandoChecks(
						object.CatProgramaCeresos[key], 
						"lstCereso", 
						"jqGridCeresos", 
						"checkboxesCeresos",
						"tipo");
			}
			jQuery('#adminProg<%=noCache%>').tabs("select", "#regProg1");
			jQuery("#btnsGuardarPrograma").hide();
			jQuery("#btnsModificarPrograma").show();
		}else if(catalogo == "apTabs-2"){			
			jQuery("#idCurso").val(object.CatCursoId);
			jQuery("#nombreCurso").val(object.CatCursoNombre);
			jQuery("#descCurso").val(object.CatCursoDescripcion);
			jQuery("#tipoCursoId").val(object.CatTipoCursoId);
			jQuery("#adminCursoCategoriaCursoId").val(object.CatCategoriaCursoId);
			jQuery("#tipoNivelAcademicoId").val(object.CatTipoNivelAcademico);
			jQuery("#puntosCurso").val(object.CatCursoPuntos);
			jQuery("#duracionCurso").val(object.CatCursoDuracion);
			jQuery('#adminCursos<%=noCache%>').tabs("select", "#regCurso1");
			jQuery("#btnsGuardarCurso").hide();
			jQuery("#btnsModificarCurso").show();
			
		}else if(catalogo == "apTabs-3"){			
			jQuery('#idCatTrabajo').val(object.CatTrabajoId);
			jQuery('#nombreCatTrabajo').val(object.CatTrabajoNombre);
			jQuery('#descripcionCatTrabajo').val(object.CatTrabajoDescripcion);
			
			if(object.CatTrabajoBesExterno == 0){
				jQuery(".initRadio").attr("checked","checked");				
			}else{
				jQuery(".updateRadio").attr("checked","checked");
			}

			if (jQuery("input[name='besExternoCatTrabajo']:checked").val() == '0'){
				jQuery(".datosEsExterno").hide();
				jQuery("#catTipoTrabajoExternoId").val(-1);
				jQuery("#numeroConvenioCatTrabajo").val("");
			}
			else if (jQuery("input[name='besExternoCatTrabajo']:checked").val() == '1'){
				jQuery(".datosEsExterno").show();
			}
			
 			jQuery("#catTipoTrabajoExternoId").val(object.CatTipoTrabajoExternoId);
 			jQuery("#numeroConvenioCatTrabajo").val(object.CatTrabajoNoConvenio);
 			jQuery('#puntosCatTrabajo').val(object.CatTrabajoPuntos);
 			jQuery('#adminTrabajos<%=noCache%>').tabs("select", "#regTrabajo1");
			jQuery("#btnsGuardarTrabajo").hide();
			jQuery("#btnsModificarTrabajo").show();		

		}
	}
	
	function GetAJAXValues( catalogo, id, fnCallback ){
		if (GetAJAXValues.Xhr){
			GetAJAXValues.Xhr.abort();
		}

		GetAJAXValues.Xhr = $.ajax({
			type: "post",
			url:'<%=request.getContextPath()%>/obtenerCatalogoPorId.do',
			data: {
				catalogo: catalogo, id:id
			},
			dataType: "json",

			success: function( objData ){
				fnCallback({ catalogo: catalogo, object: objData});
			},

			error: function(){
				customAlert("No de puede mostrar los detalles.<br /> Intente de nuevo por favor ");
			},

			// I get called no matter what.
			complete: function(){
				GetAJAXValues.Xhr = null;
			}
		});
	}	

	function limpiarForma(){
		jQuery('#programaId').val("0");
		jQuery('#nombre').val("");
		jQuery('#catTipoProgramaId').val(-1);
		jQuery('#descripcion').val("");
		jQuery('#totalPuntosCalc').val("");
		jQuery('#catProgramaDTOFechaInicioPrograma').val("");
		jQuery('#catProgramaDTOFechaFinPrograma').val("");
		
		jQuery("#idCurso").val("");
		jQuery("#nombreCurso").val("");
		jQuery("#descCurso").val("");
		jQuery("#tipoCursoId").val(-1);
		jQuery("#adminCursoCategoriaCursoId").val(-1);
		jQuery("#tipoNivelAcademicoId").val(-1);
		jQuery("#puntosCurso").val("0");
		jQuery("#duracionCurso").val("");

		jQuery('#idCatTrabajo').val("0");
		jQuery('#nombreCatTrabajo').val("");
		jQuery('#descripcionCatTrabajo').val("");

		var allRowsInGrid  = $('#jqGridCursos').jqGrid('getDataIDs');
		for (key in allRowsInGrid) {
			limpiandoChecks(
					allRowsInGrid[key], 
					"lstCurso", 
					"jqGridCursos", 
					"checkboxesCurso");
		}
		allRowsInGrid  = $('#jqGridTrabajos').jqGrid('getDataIDs');
		for (key in allRowsInGrid) {
			limpiandoChecks(
					allRowsInGrid[key], 
					"lstTrabajo", 
					"jqGridTrabajos", 
					"checkboxesTrabajo");
		}

		allRowsInGrid  = $('#jqGridCeresos').jqGrid('getDataIDs');
		for (key in allRowsInGrid) {
			limpiandoChecks(
					allRowsInGrid[key],
					"lstCereso", 
					"jqGridCeresos", 
					"checkboxesCeresos");
		}
		
		jQuery(".initRadio").attr("checked","checked");				
		
		jQuery(".datosEsExterno").hide();
		jQuery("#catTipoTrabajoExternoId").val(-1);
		jQuery("#numeroConvenioCatTrabajo").val("");
		
		jQuery('#puntosCatTrabajo').val("");
				
		jQuery("#btnsGuardarPrograma").show();
		jQuery("#btnsModificarPrograma").hide();
		jQuery("#btnsGuardarCurso").show();
		jQuery("#btnsModificarCurso").hide();
		jQuery("#btnsGuardarTrabajo").show();
		jQuery("#btnsModificarTrabajo").hide();		
		
	}
					
</script>

<html:form action="/guardarPrograma" styleId="adminProgForm">

	<div id="apTabsContenido<%=noCache%>">
		<ul>
			<li><a href="#apTabs-1">Cat&aacute;logo De Programas</a></li>
			<li><a href="#apTabs-2">Cat&aacute;logo De Cursos</a></li>
			<li><a href="#apTabs-3">Cat&aacute;logo De Trabajos</a></li>
		</ul>
		<div id="apTabs-1">
			<div id="adminProg<%=noCache%>">
				<ul>
					<li><a href="#catProg1">Cat&aacute;logo</a></li>
					<li><a href="#regProg1">Datos Del Programas</a></li>
					<li><a href="#regProg2">Cursos Y Trabajos Asociados</a></li>
					<li><a href="#regProg3">Centros de Detenci&oacute;n
							Asociados</a></li>
				</ul>
				<div id="catProg1">
					<table id="gridCatProgramas"></table>
					<div id="pagerCatProgramas" style="vertical-align: top;"></div>
				</div>
				<div id="regProg1">
					<fieldset>
						<table border="0">
							<tr>
								<td align="right"><strong>Nombre<em>*</em>:</strong></td>
								<td align="left" colspan="3"><html:hidden
										name="ReinsercionSocial" property="programaId" 
										styleId="programaId"  />
									<html:text
										name="ReinsercionSocial" property="cnombre" maxlength="200"
										styleId="nombre" styleClass="texto"/>
								</td>
							</tr>
							<tr>
								<td align="right"><strong>Tipo de Programa<em>*</em>:</strong>
								</td>
								<td align="left" colspan="3"><html:select
										name="ReinsercionSocial" property="catTipoProgramaId"
										styleId="catTipoProgramaId" styleClass="texto">
										<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
										<html:optionsCollection name="ReinsercionSocial"
											property="lstCatTipoProgramaDTO" label="descripcion"
											value="catTipoProgramaId" />
									</html:select></td>
							</tr>					
							<tr>
								<td align="right"><strong>Descripci&oacute;n<em>*</em>:</strong>
								</td>
								<td align="left" colspan="3"><html:textarea
										name="ReinsercionSocial" property="descripcion" onkeypress=""
										styleId="descripcion" styleClass="texto" style="width: 99%;" > 
									</html:textarea> <script type="text/javascript">
									$('#descripcion').attr("maxlength", 200);</script></td>
							</tr>
							<tr>
								<td align="right"><strong>Total De Puntos:</strong></td>
								<td align="left" colspan="3"><input type="text"
									id="totalPuntosCalc" class="texto" readonly="readonly" /></td>
							</tr>					
							<tr>
								<td align="right"><strong>Fecha de Inicio<em>*</em>:</strong>
								</td>
								<td align="left"><html:text name="ReinsercionSocial"
										property="fechaInicioPrograma"
										styleId="catProgramaDTOFechaInicioPrograma" styleClass="texto"/>
								</td>
								<td align="right"><strong>Fecha de Termino<em>*</em>:</strong>
								</td>
								<td align="left"><html:text name="ReinsercionSocial"
										property="fechaFinPrograma"
										styleId="catProgramaDTOFechaFinPrograma" styleClass="texto"/>
								</td>
							</tr>						
							<tr>
								<td align="center" colspan="4">
									<button id="btnBuscar"  type="button" tabindex="6"
										class="btn_buscar" onclick="cambiarTab('#regProg2');">Continuar</button>
								</td>
							</tr>
						</table>
					</fieldset>			
				</div>
				<div id="regProg2">
					<fieldset>
						<table border="0">
							<tr>
								<td colspan="4">
									<div id="checkboxesCurso" style="display: none;">
										<logic:iterate name="ReinsercionSocial" property="cursos"
											id="curso">
											<html:multibox name="ReinsercionSocial"
												property="cursosSeleccionados">
												<bean:write name="curso"/>
											</html:multibox>
											<bean:write name="curso" />
											<br />
										</logic:iterate>
									</div>
									<table width="100%" id="contGridCursos">
										<tr>
											<td width="80%">
												<table id="jqGridCursos"></table></td>
											<td valign="top" width="20%">
												<button id="btnAsociarCurso" type="button" class="btn_buscar">Agregar
													Curso</button>
												<br />
												<button id="btnEliminarCurso" type="button" class="btn_buscar">Eliminar
													Curso</button></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td colspan="4">
									<div id="checkboxesTrabajo" style="display: none;">
										<logic:iterate name="ReinsercionSocial" property="trabajos"
											id="trabajo">
											<html:multibox name="ReinsercionSocial"
												property="trabajosSeleccionados">
												<bean:write name="trabajo"/>
											</html:multibox>
											<bean:write name="trabajo" />
											<br />
										</logic:iterate>
									</div>
									<table width="100%">
										<tr>
											<td width="80%">
												<table id="jqGridTrabajos"></table></td>
											<td valign="top" width="20%">
												<button id="btnAsociarTrabajo" type="button" class="btn_buscar">Agregar
													Trabajo</button>
												<br />
												<button id="btnEliminarTrabajo" type="button" class="btn_buscar">Eliminar
													Trabajo</button></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td align="center" colspan="4">
									<button id="btnBuscar" type="button" class="btn_buscar"
										onclick="cambiarTab('#regProg3');">Continuar</button></td>
							</tr>
							<tr>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center">&nbsp;</td>
							</tr>
						</table>
					</fieldset>				
				</div>
				<div id="regProg3">
					<fieldset>
						<table border="0">
							<tr>
								<td colspan="4">
									<div id="checkboxesCeresos" style="display: none;">
										<logic:iterate name="ReinsercionSocial" property="ceresos"
											id="cereso">
											<html:multibox name="ReinsercionSocial"
												property="ceresosSeleccionados">
												<bean:write name="cereso"/>
											</html:multibox>
											<bean:write name="cereso" />
											<br />
										</logic:iterate>
									</div>
									<table width="100%">
										<tr>
											<td width="80%">
												<table id="jqGridCeresos"></table></td>
											<td valign="top" width="20%">
												<button id="btnAsociarCereso" type="button" class="btn_buscar">Agregar
													Centro</button>
												<br />
												<button id="btnEliminarCereso" type="button" class="btn_buscar">Eliminar
													Centro</button></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td align="center" colspan="4">
									<div id="btnsGuardarPrograma">
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('1');">Guardar</button>
									</div>
									<div id="btnsModificarPrograma" style="display: none;">
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="limpiarForma();">Nuevo</button>
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('4');">Modificar</button>
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('7');">Eliminar</button>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center">&nbsp;</td>
							</tr>
						</table>
					</fieldset>								
				</div>
			</div>
		</div>
		<div id="apTabs-2">
			<div id="adminCursos<%=noCache%>">
				<ul>
					<li><a href="#catCurso1">Cat&aacute;logo</a></li>
					<li><a href="#regCurso1">Datos Del Curso</a></li>
				</ul>
				<div id="catCurso1">
					<table id="gridCatCursos"></table>
					<div id="pagerCatCursos" style="vertical-align: top;"></div>
				</div>
				<div id="regCurso1">
			<fieldset>
				<table>
					<tr>
								<td align="right"><strong>Nombre<em>*</em>:</strong></td>
						<td align="left" colspan="3">
									<html:hidden
										name="ReinsercionSocial" property="idCurso" 
										styleId="idCurso"  />
									<html:text
										name="ReinsercionSocial" property="nombreCurso"
										styleId="nombreCurso" styleClass="texto" /></td>
					</tr>
					<tr>
								<td align="right"><strong>Descripci&oacute;n:</strong></td>
								<td align="left" colspan="3"><html:textarea
										name="ReinsercionSocial" property="descCurso"
								styleId="descCurso" styleClass="texto" style="width: 99%;">
									</html:textarea></td>
					</tr>
					<tr>
								<td align="right"><strong>Tipo de curso<em>*</em>:</strong>
						</td>
								<td align="left" colspan="3"><html:select
										name="ReinsercionSocial" property="tipoCursoId"
										styleId="tipoCursoId" styleClass="texto">
								<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
								<html:optionsCollection name="ReinsercionSocial"
									property="lstCatTipoCursoDTO" label="descripcion"
									value="catTipoCursoId" />
									</html:select></td>
					</tr>
					<tr>
								<td align="right"><strong>Categor&iacute;a de
										curso<em>*</em>:</strong></td>
								<td align="left" colspan="3"><html:select
										name="ReinsercionSocial" property="adminCursoCategoriaCursoId"
										styleId="adminCursoCategoriaCursoId" styleClass="texto">
								<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
								<html:optionsCollection name="ReinsercionSocial"
									property="lstCatCategoriaCursoDTO" label="descripcion"
									value="catCategoriaCursoId" />
									</html:select></td>
					</tr>
					<tr>
								<td align="right"><strong>Tipo de nivel
										acad&eacute;mico</strong></td>
								<td align="left" colspan="3"><html:select
										name="ReinsercionSocial" property="tipoNivelAcademicoId"
										styleId="tipoNivelAcademicoId" styleClass="texto">
								<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
								<html:optionsCollection name="ReinsercionSocial"
									property="lstCatTipoNivelAcademicoDTO" label="descripcion"
									value="catTipoNivelAcademicoId" />
									</html:select></td>
							</tr>
							<tr>
								<td align="right"><strong>Puntos m&aacute;ximos a
										otorgar<em>*</em>:</strong></td>
								<td align="left" colspan="3"><html:text
										name="ReinsercionSocial" property="puntosCurso"
										styleId="puntosCurso" styleClass="texto"
										onkeypress="return solonumeros(event);" /></td>
							</tr>
							<tr>
								<td align="right"><strong>Duraci&oacute;n
										aproximada:</strong></td>
								<td align="left" colspan="3"><html:text
										name="ReinsercionSocial" property="duracionCurso"
										styleId="duracionCurso" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="center" colspan="4">
									<div id="btnsGuardarCurso">
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('2');">Guardar</button>
									</div>
									<div id="btnsModificarCurso" style="display: none;">
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="limpiarForma();">Nuevo</button>
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('5');">Modificar</button>
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('8');">Eliminar</button>
									</div>
						</td>
					</tr>
					<tr>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center">&nbsp;</td>
							</tr>
						</table>
					</fieldset>
				</div>
			</div>
		</div>
		<div id="apTabs-3">
			<div id="adminTrabajos<%=noCache%>">
				<ul>
					<li><a href="#catTrabajo1">Cat&aacute;logo</a></li>
					<li><a href="#regTrabajo1">Datos Del Trabajo</a></li>
				</ul>
				<div id="catTrabajo1">
					<table id="gridCatTrabajos"></table>
					<div id="pagerCatTrabajos" style="vertical-align: top;"></div>
				</div>
				<div id="regTrabajo1">
					<fieldset>
						<table border="0">
							<tr>
								<td align="right"><strong>Nombre<em>*</em>:</strong></td>
						<td align="left" colspan="3">
									<html:hidden
										name="ReinsercionSocial" property="idCatTrabajo" 
										styleId="idCatTrabajo"  />
									<html:text
										name="ReinsercionSocial" property="nombreCatTrabajo"
										styleId="nombreCatTrabajo" styleClass="texto" /></td>
							</tr>
							<tr>
								<td align="right"><strong>Descripci&oacute;n<em>*</em>:</strong>
						</td>
								<td align="left" colspan="3"><html:textarea
										name="ReinsercionSocial" property="descripcionCatTrabajo"
										styleId="descripcionCatTrabajo" styleClass="texto"
										style="width: 99%;">
									</html:textarea></td>
					</tr>
					<tr>
								<td align="right"><strong>Tipo De Trabajo<em>*</em>:</strong>
						</td>
								<td align="left"><html:radio name="ReinsercionSocial"
										property="besExternoCatTrabajo" value="0"
										styleId="besExternoCatTrabajo" styleClass="initRadio" />
									Interno</td>
								<td align="left"><html:radio name="ReinsercionSocial"
										property="besExternoCatTrabajo" value="1"
										styleId="besExternoCatTrabajo"styleClass="updateRadio" /> 
									Externo</td>
								<td align="left">&nbsp;</td>
							</tr>
							<tr class="datosEsExterno">
								<td align="right"><strong>Tipo de Trabajo Externo<em>*</em>:</strong>
						</td>
								<td align="left" colspan="3"><html:select
										name="ReinsercionSocial" property="catTipoTrabajoExternoId"
										styleId="catTipoTrabajoExternoId" styleClass="texto">
										<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
										<html:optionsCollection name="ReinsercionSocial"
											property="lstCatTipoTrabajoExternoDTO" label="descripcion"
											value="catTipoExternoId" />
									</html:select></td>
					</tr>
							<tr class="datosEsExterno">
								<td align="right"><strong>N&uacute;mero De
										Convenio<em>*</em>:</strong></td>
								<td align="left" colspan="3"><html:text
										name="ReinsercionSocial" property="numeroConvenioCatTrabajo"
										styleId="numeroConvenioCatTrabajo" styleClass="texto" /></td>
							</tr>
					<tr>
								<td align="right"><strong>N&uacute;mero De Puntos<em>*</em>:</strong>
								</td>
								<td align="left" colspan="3"><html:text
										name="ReinsercionSocial" property="puntosCatTrabajo"
										onkeypress="return solonumeros(event);"
										styleId="puntosCatTrabajo" styleClass="texto" /></td>
							</tr>
							<tr>
						<td align="center" colspan="4">
									<div id="btnsGuardarTrabajo">
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('3');">Guardar</button>
									</div>
									<div id="btnsModificarTrabajo" style="display: none;">
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="limpiarForma();">Nuevo</button>
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('6');">Modificar</button>
										<button id="btnBuscar" type="button" tabindex="6"
											class="btn_buscar" onclick="enviarForma('9');">Eliminar</button>
									</div>
						</td>
					</tr>
							<tr>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center" class="texto"
									style="background-color: inherit;">&nbsp;</td>
								<td align="center">&nbsp;</td>
							</tr>
				</table>

			</fieldset>
		</div>
		</div>		
	</div>	
	</div>


























	
	<div id="dialog-confirm" title="">
		<p>
			<span id="confirmText"></span>
		</p>
	</div>
	
	<div id="dialog-registroCursosExito"
		title="Operaci&oacute;n realizada con &eacute;xito">
		<p>El curso: ${param.nombreCurso} fu&eacute; registrado con
			&eacute;xito</p>
	</div>
	
	<div id="dialog-validarDatosCurso" title="Error de validaci&oacute;n">
		<p>Los datos requeridos para poder guardar un curso son los
			siguientes:
			<ul>
				<li>Nombre</li>
				<li>Tipo del curso</li>
				<li>Categor&iacute;a del curso</li>
				<li>Puntos otorgados</li>
			</ul>
			Uno o m&aacute;s de los campos anteriores no han sido ingresados.<br/>
			Favor de verificar la informaci&oacute;n.
		</p>
	</div>
		

	<div id="dialog-cursos">	
		<fieldset>
			<table>
				<tr>
					<td align="right"><strong>Selecionar curso:</strong>
					</td>
					<td align="left"><select name="lstCurso" id="lstCurso"
						class="texto">
							<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
							<logic:iterate name="ReinsercionSocial" property="lstCatCursoDTO"
								id="catCursoDTO">
								<option 
									value='<bean:write name="catCursoDTO" property="catCursoId" />'
									id='CC<bean:write name="catCursoDTO" property="catCursoId" />'>
									<bean:write name="catCursoDTO" property="cnombre" />
								</option>
							</logic:iterate>
					</select></td>
				</tr>
			</table>
			<logic:iterate name="ReinsercionSocial" property="lstCatCursoDTO"
				id="catCursoDTO">
				<div
					id="detallesCC<bean:write name="catCursoDTO" property="catCursoId" />">
					<table>	
						<tr>
							<td align="right"><strong>Tipo:</strong></td>
							<td align="left"><input
								id="ctcCC<bean:write name="catCursoDTO" property="catCursoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catCursoDTO" property="catTipoCursoDTO.descripcion" />' />
							</td>
						</tr>
						<tr>
							<td align="right"><strong>Descripci&oacute;n:</strong></td>
							<td align="left"><input
								id="descCC<bean:write name="catCursoDTO" property="catCursoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catCursoDTO" property="cdescripcion" />' />
							</td>
						</tr>				
						<tr>
							<td align="right"><strong>Categor&iacute;a:</strong></td>
							<td align="left"><input
								id="cccCC<bean:write name="catCursoDTO" property="catCursoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catCursoDTO" property="catCategoriaCursoDTO.descripcion" />' />
							</td>
						</tr>
						<tr>
							<td align="right"><strong>Tipo De Nivel Academico:</strong>
							</td>
							<td align="left"><input
								id="ctcCC<bean:write name="catCursoDTO" property="catCursoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catCursoDTO" property="catTipoNivelAcademicoDTO.descripcion" />' />
							</td>
						</tr>				
						<tr>
							<td align="right"><strong>Numero De Puntos:</strong></td>
							<td align="left"><input
								id="puntosCC<bean:write name="catCursoDTO" property="catCursoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catCursoDTO" property="ipuntos" />' />
							</td>
						</tr>
						<tr>
							<td align="right"><strong>Duraci&oacute;n:</strong></td>
							<td align="left"><input
								id="duraCC<bean:write name="catCursoDTO" property="catCursoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catCursoDTO" property="cduracion" />' />
							</td>
						</tr>
					</table>
				</div>
			</logic:iterate>
		</fieldset>
	</div>
	
	<div id="dialog-trabajos">	
		<fieldset>
			<table>
				<tr>
					<td align="right"><strong>Selecionar Trabajo:</strong>
					</td>
					<td align="left"><select name="lstTrabajo" id="lstTrabajo"
						class="texto">
							<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
							<logic:iterate name="ReinsercionSocial"
								property="lstCatTrabajoDTO" id="catTrabajoDTO">
								<option 
									value='<bean:write name="catTrabajoDTO" property="catTrabajoId" />'
									id='CT<bean:write name="catTrabajoDTO" property="catTrabajoId" />'>
									<bean:write name="catTrabajoDTO" property="cnombre" />
								</option>
							</logic:iterate>
					</select></td>
				</tr>
			</table>
			<logic:iterate name="ReinsercionSocial" property="lstCatTrabajoDTO"
				id="catTrabajoDTO">
				<div
					id="detallesCT<bean:write name="catTrabajoDTO" property="catTrabajoId" />">
					<table>	
						<tr>
							<td align="right"><strong>Descripci&oacute;n:</strong></td>
							<td align="left"><input
								id="descCT<bean:write name="catTrabajoDTO" property="catTrabajoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catTrabajoDTO" property="cdescripcion" />' />
							</td>
						</tr>				
						<tr>
							<td align="right"><strong>Tipo:</strong></td>
							<td align="left"><logic:equal name="catTrabajoDTO"
									property="besExterno" value="true">
									<input
										id="tipoCT<bean:write name="catTrabajoDTO" property="catTrabajoId" />"
										type="text" class="text" readonly="readonly" value="Externo" />
								</logic:equal> <logic:equal name="catTrabajoDTO" property="besExterno"
									value="false">
									<input
										id="tipoCT<bean:write name="catTrabajoDTO" property="catTrabajoId" />"
										type="text" class="text" readonly="readonly" value="Interno" />
								</logic:equal></td>
						</tr>
						<logic:equal name="catTrabajoDTO" property="besExterno"
							value="true">
							<tr>
								<td align="right"><strong>Tipo De Trabajo Externo:</strong>
								</td>
								<td align="left"><input
									id="ctteCT<bean:write name="catTrabajoDTO" property="catTrabajoId" />"
									type="text" class="text" readonly="readonly"
									value='<bean:write name="catTrabajoDTO" property="catTipoTrabajoExterno.descripcion" />' />
								</td>
							</tr>						
							<tr>
								<td align="right"><strong>N&uacute;mero De
										Convenio:</strong></td>
								<td align="left"><input
									id="ncCT<bean:write name="catTrabajoDTO" property="catTrabajoId" />"
									type="text" class="text" readonly="readonly"
									value='<bean:write name="catTrabajoDTO" property="cnumeroConvenio" />' />
								</td>
							</tr>
						</logic:equal>
						<tr>
							<td align="right"><strong>Numero De Puntos:</strong></td>
							<td align="left"><input
								id="puntosCT<bean:write name="catTrabajoDTO" property="catTrabajoId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="catTrabajoDTO" property="ipuntos" />' />
							</td>
						</tr>
					</table>
				</div>
			</logic:iterate>
		</fieldset>
	</div>
	
	<div id="dialog-ceresos">	
		<fieldset>
			<table>
				<tr>
					<td align="right"><strong>Selecionar Centro De
							Detención: </strong>
					</td>
					<td align="left"><select name="lstCereso" id="lstCereso"
						class="texto">
							<option value="-1">SELECCIONE UNA OPCI&Oacute;N</option>
							<logic:iterate name="ReinsercionSocial" property="ceresosDTO"
								id="centroDetencionDTO">
								<option 
									value='<bean:write name="centroDetencionDTO" property="centroDetencionId" />'
									id='CS<bean:write name="centroDetencionDTO" property="centroDetencionId" />'>
									<bean:write name="centroDetencionDTO" property="nombre" />
								</option>
							</logic:iterate>
					</select></td>
				</tr>
			</table>
			<logic:iterate name="ReinsercionSocial" property="ceresosDTO"
				id="centroDetencionDTO">
				<div
					id="detallesCS<bean:write name="centroDetencionDTO" property="centroDetencionId" />">
					<table>	
						<tr>
							<td align="right"><strong>Tipo:</strong></td>
							<td align="left"><input
								id="tipoCS<bean:write name="centroDetencionDTO" property="centroDetencionId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="centroDetencionDTO" property="tipo.valor" />' />
							</td>
						</tr>				
						<tr>
							<td align="right"><strong>Director:</strong></td>
							<td align="left"><input
								id="tipoCS<bean:write name="centroDetencionDTO" property="centroDetencionId" />"
								type="text" class="text" readonly="readonly"
								value='<bean:write name="centroDetencionDTO" property="nombreDirector" />' />
							</td>
						</tr>
					</table>
				</div>
			</logic:iterate>
		</fieldset>
	</div>	
</html:form>
