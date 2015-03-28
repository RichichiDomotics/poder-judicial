	
<script>

	//Comienzan Funciones para consultar los Catalogos de Media filiacion
			
    function cargaTamanoBoca(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTamanoBoca.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catTamanoBoca').each(function(){
            			$('#cmpTamanoBoca').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpTamanoBoca').multiselect('refresh');
    	    			});
    		}
    	});
    }
	
    function cargaTipoCara(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTipoCara.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catTipoCara').each(function(){
            			$('#cmpTipoCara').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpTipoCara').multiselect('refresh');
    	    			});
    		}
    	});
    }

    function cargaFormaMenton(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaMenton.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catFormaMenton').each(function(){
            			$('#cmpFormaMenton').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpFormaMenton').multiselect('refresh');
            			});
    		}
    	});
    }

    function cargaTipoMenton(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTipoMenton.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catTipoMenton').each(function(){
            			$('#cmpTipoMenton').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpTipoMenton').multiselect('refresh');
            			});
    		}
    	});
    }

    function cargaTez(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTez.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catTez').each(function(){
            			$('#cmpTez').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpTez').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaInclinacionMenton(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoInclinacionMenton.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catInclinacionMenton').each(function(){
            			$('#cmpInclinacionMenton').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpInclinacionMenton').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaColorCabello(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoColorCabello.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catColorCabello').each(function(){
            			$('#cmpColorCabello').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpColorCabello').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaFormaCabello(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaCabello.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catFormaCabello').each(function(){
            			$('#cmpFormaCabello').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpFormaCabello').multiselect('refresh');
    			});
    		}
    	});
    }
	
    function cargaCalvieTipo(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoCalvieTipo.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catCalvieTipo').each(function(){
            			$('#cmpCalvieTipo').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpCalvieTipo').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaCabelloImplantacion(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoCabelloImplantacion.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catCabelloImplantacion').each(function(){
            			$('#cmpCabelloImplantacion').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpCabelloImplantacion').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaCantidadCabello(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoCantidadCabello.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catCantidadCabello').each(function(){
            			$('#cmpCantidadCabello').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpCantidadCabello').multiselect('refresh');
    			});
    		}
    	});
    }
    
    function cargaOreja(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoOreja.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catOreja').each(function(){
            			$('#cmpOreja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpOreja').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaTamanoOreja(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTamanoOreja.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catTamanoOreja').each(function(){
            			$('#cmpTamanoOreja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpTamanoOreja').multiselect('refresh');
    			});
    		}
    	});
    }
    
    function cargaLobuloParticularidad(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoLobuloParticularidad.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catLobuloParticularidad').each(function(){
            			$('#cmpLobuloParticularidad').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpLobuloParticularidad').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaLobuloDimension(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoLobuloDimension.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catLobuloDimension').each(function(){
            			$('#cmpLobuloDimension').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpLobuloDimension').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaLobuloAdherencia(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoLobuloAdherencia.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catLobuloAdherencia').each(function(){
            			$('#cmpLobuloAdherencia').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpLobuloAdherencia').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaHelixAnterior(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixAnterior.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catHelixAnterior').each(function(){
            			$('#cmpHelixAnterior').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpHelixAnterior').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaHelixPosterior(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixPosterior.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catHelixPosterior').each(function(){
            			$('#cmpHelixPosterior').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpHelixPosterior').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaHelixContorno(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixContorno.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catHelixContorno').each(function(){
            			$('#cmpHelixContorno').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpHelixContorno').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaHelixAdherencia(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoHelixAdherencia.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catHelixAdherencia').each(function(){
            			$('#cmpHelixAdherencia').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpHelixAdherencia').multiselect('refresh');
    			});
    		}
    	});
    }

    function cargaFormaOreja(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaOreja.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catFormaOreja').each(function(){
            			$('#cmpFormaOreja').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpFormaOreja').multiselect('refresh');
    			});
    		}
    	});
    }
    
    function cargaColorOjos(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoColorOjos.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catColorOjos').each(function(){
            			$('#cmpColorOjos').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpColorOjos').multiselect('refresh');
    			});
    		}
    	});
    }
    
    function cargaTamanoOjos(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoTamanoOjos.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catTamanoOjos').each(function(){
            			$('#cmpTamanoOjos').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpTamanoOjos').multiselect('refresh');
    			});
    		}
    	});
    }
    
    function cargaFormaOjos(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoFormaOjos.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
        			$(xml).find('catFormaOjos').each(function(){
            			$('#cmpFormaOjos').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#cmpFormaOjos').multiselect('refresh');
    			});
    		}
    	});
    }

        function cargaAlturaNasoLabial(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoAlturaNasoLabial.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
//    			var option;
        			$(xml).find('catAlturaNasoLabial').each(function(){
            			$('#altura_nasal').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#altura_nasal').multiselect('refresh');
    			});
    		}
    	});
    }

        function cargaComisuras(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoComisuras.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
//    			var option;
        			$(xml).find('catCatalogoComisuras').each(function(){
            			$('#comisuras').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#comisuras').multiselect('refresh');
    			});
    		}
    	});
    }

        function cargaEspesorLabioInferior(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoEspesorLabioInferior.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
        			$(xml).find('catEspesorLabioInferior').each(function(){
            			$('#espesor_labio_inf').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#espesor_labio_inf').multiselect('refresh');
    			});
    		}
    	});
    }

        function cargaEspesorLabioSuperior(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoEspesorLabioSuperior.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
        			$(xml).find('catEspesorLabioSuperior').each(function(){
            			$('#espesor_labio_sup').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#espesor_labio_sup').multiselect('refresh');
    			});
    		}
    	});
    }

        function cargaProminencia(){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogoProminencia.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
        			$(xml).find('catProminencia').each(function(){
            			$('#prominencia').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#prominencia').multiselect('refresh');
    			});
    		}
    	});
    }

        function cargaCatalogoPonEn(catalogo, idObjetivo){
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarCatalogo' + catalogo + '.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
        			$(xml).find('cat' + catalogo).each(function(){
            			$('#' + idObjetivo).append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
            			$('#' + idObjetivo).multiselect('refresh');
    			});
    		}
    	});
    }

        function cargaImplantacionCeja(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarCatalogoImplantacionCeja.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catImplantacionCeja').each(function(){
	            			$('#cmpImplantacionCejas').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	            			$('#cmpImplantacionCejas').multiselect('refresh');
	    			});
	    		}
	    	});
	    }
	    function cargaFormaCejas(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarCatalogoFormaCeja.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catFormaCeja').each(function(){
	            			$('#cmpFormaCejas').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	            			$('#cmpFormaCejas').multiselect('refresh');
	    			});
	    		}
	    	});
	    }
	    function cargaTamanoCeja(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarCatalogoTamanoCeja.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTamanoCeja').each(function(){
	            			$('#cmpTamanoCejas').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	            			$('#cmpTamanoCejas').multiselect('refresh');
	    			});
		    			
	    		}
	    	});
	    }

	    function cargaTipoSangre(){
	    	$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/consultarCatalogoTipoSangre.do',
	    		data: '',
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			var option;
	        			$(xml).find('catTipoSangre').each(function(){
		        			$('#cmpTipoSangre').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
	            			$('#cmpTipoSangre').multiselect('refresh');
	    			});
	    		}
	    	});
	    }

	    //Fin da la carga de catalogos de media filiacion


	//obtiene los parametros de la pantalla de media filiacion
	 function obtenerParametrosMediaFiliacion(){
		 
	        var parametros = '&tamanoBoca=' + $('#cmpTamanoBoca option:selected').val();
	        parametros += '&tipoBoca=' + $('#cmpTipoCara option:selected').val();
	        parametros += '&formaMenton=' + $('#cmpFormaMenton option:selected').val();
	        parametros += '&tipoMenton=' + $('#cmpTipoMenton option:selected').val();
	        parametros += '&tez=' + $('#cmpTez option:selected').val();
	        parametros += '&inclinacionMenton=' + $('#cmpInclinacionMenton option:selected').val();

	        parametros += '&colorCabello=' + $('#cmpColorCabello option:selected').val();
	        parametros += '&formaCabello=' + $('#cmpFormaCabello option:selected').val();
	        parametros += '&calvieTipo=' + $('#cmpCalvieTipo option:selected').val();
	        parametros += '&cabelloImplantacion=' + $('#cmpCabelloImplantacion option:selected').val();
	        parametros += '&cantidadCabello=' + $('#cmpCantidadCabello option:selected').val();

	        parametros += '&tamanoOreja=' + $('#cmpTamanoOreja option:selected').val();
	        parametros += '&lobuloParticularidad=' + $('#cmpLobuloParticularidad option:selected').val();
	        parametros += '&lobuloDimension=' + $('#cmpLobuloDimension option:selected').val();
	        parametros += '&lobuloAdherencia=' + $('#cmpLobuloAdherencia option:selected').val();
	        parametros += '&helixAnterior=' + $('#cmpHelixAnterior option:selected').val();
	        parametros += '&helixPosterior=' + $('#cmpHelixPosterior option:selected').val();
	        parametros += '&helixContorno=' + $('#cmpHelixContorno option:selected').val();
	        parametros += '&helixAdherencia=' + $('#cmpHelixAdherencia option:selected').val();
	        parametros += '&formaOreja=' + $('#cmpFormaOreja option:selected').val();

	        parametros += '&tamanoOjos=' + $('#cmpTamanoOjos option:selected').val();
	        parametros += '&colorOjos=' + $('#cmpColorOjos option:selected').val();
	        parametros += '&formaOjos=' + $('#cmpFormaOjos option:selected').val();

	        parametros += '&implantacionCejas=' + $('#cmpImplantacionCejas option:selected').val();
	        parametros += '&formaCejas=' + $('#cmpFormaCejas option:selected').val();
	        parametros += '&tamanoCejas=' + $('#cmpTamanoCejas option:selected').val();

			if($("#Cicatrices1").is(':checked')){
				 parametros += '&cicatricesSenas=' + "1";
				
			}else{
				 parametros += '&cicatricesSenas=' + "0";
				
			}
			 parametros += '&cicatricesSenasText=' +$('#Cicatricestext').val();
			 if($("#Protesis1").is(':checked')){
				 parametros += '&protesisSenas=' + "1";
				
			}else{
				 parametros += '&protesisSenas=' + "0";
				 
			}
			 parametros += '&protesisSenasText=' +$('#Protesistext').val();

			 if($("#Tatuajes1").is(':checked')){
				 parametros += '&tatuajeSenas=' + "1";
				 
			}else{
				 parametros += '&tatuajeSenas=' + "0";
				
			}
			 parametros += '&tatuajeSenasText=' +$('#Tatuajestext').val();
			 parametros += '&otraSenasText=' +$('#Otrastext').val();
			 if($("#lunares1").is(':checked')){
				 parametros += '&lunaresSenas=' + "1";
				 
			}else{
				 parametros += '&lunaresSenas=' + "0";
				
			}
			 parametros += '&lunaresSenasText=' +$('#Lunarestext').val();
			 if($("#Defectos1").is(':checked')){
				 parametros += '&defectosSenas=' + "1";
				 
			}else{
				 parametros += '&defectosSenas=' + "0";
				
			}
			 parametros += '&defectosSenasText=' +$('#Defectostext').val();
			 
			 
			 parametros += '&factorrhOtros=' + $('#cmpFactorRH option:selected').val();
		    
				if($("#lentes1").is(':checked')){
					 parametros += '&lentesOtros=' + "1";
					 
				}else{
					 parametros += '&lentesOtros=' + "0";
					 
				}
				parametros += '&tipoSangreOtros=' + $('#cmpTipoSangre option:selected').val();
					
				 if($("#barba1").is(':checked')){
					 parametros += '&barbaOtros=' + "1";
					 
				}else{
					 parametros += '&barbaOtros=' + "0";
					 
				}
				 parametros += '&pesoOtros=' +$('#peso').val();

				 if($("#bigote1").is(':checked')){
					 parametros += '&bigoteOtros=' + "1";
					 
				}else{
					 parametros += '&bigoteOtros=' + "0";
					 
				}
			parametros += '&estauraOtros=' +$('#estatura').val();						
	       
            //Jacob
	        parametros += '&alturaNasoLabial=' + $('#altura_nasal option:selected').val();
	        parametros += '&comisuras=' + $('#comisuras option:selected').val();
	        parametros += '&espesorLabioInferior=' + $('#espesor_labio_inf option:selected').val();
	        parametros += '&espesorLabioSuperior=' + $('#espesor_labio_sup option:selected').val();
	        parametros += '&prominencia=' + $('#prominencia option:selected').val();
	        parametros += '&anchoNariz=' + $('#ancho_nariz option:selected').val();
	        parametros += '&alturaNariz=' + $('#altura_nariz option:selected').val();
	        parametros += '&baseNariz=' + $('#base_nariz option:selected').val();
	        parametros += '&raizNariz=' + $('#raiz_nariz option:selected').val();
	        parametros += '&frenteAltura=' + $('#frente_altura option:selected').val();
	        parametros += '&frenteAncho=' + $('#frente_ancho option:selected').val();
	        parametros += '&inclinacionFrente=' + $('#inclinacion_frente option:selected').val();
	     	parametros += '&idInvolucrado='+idInvolucrado;
	        parametros += '&idExpediente='+idExpediente;
            
	        $.ajax({
			     type: 'POST',
			     url: '<%= request.getContextPath()%>/GuardaInvolucradoIntegracionMediaFiliacion.do',
				 data: parametros,
				 dataType: 'xml',
				 success: function(xml){
					
				  }
				});
	        return parametros;
	   }

	    //pinta datos en media filiacion
	    function pintaDatosMediaFiliacion(xml){
			 //Media Filiacion Cara
			 var tamanoBoca=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tamanioBoca').find('idCampo').text();
			 $('#cmpTamanoBoca').find("option[value='"+tamanoBoca+"']").attr("selected","selected");
			 var tipoCara=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tipoCara').find('idCampo').text();
			 $('#cmpTipoCara').find("option[value='"+tipoCara+"']").attr("selected","selected");
			 var formaMenton=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaMenton').find('idCampo').text();
			 $('#cmpFormaMenton').find("option[value='"+formaMenton+"']").attr("selected","selected");
			 var tipoMenton=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tipoMenton').find('idCampo').text();
			 $('#cmpTipoMenton').find("option[value='"+tipoMenton+"']").attr("selected","selected");
			 var tez=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tez').find('idCampo').text();
			 $('#cmpTez').find("option[value='"+tez+"']").attr("selected","selected");
			 var inclinacionMenton=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('inclinacionMenton').find('idCampo').text();
			 $('#cmpInclinacionMenton').find("option[value='"+inclinacionMenton+"']").attr("selected","selected");

			 //Media Filiacion Cabello
			 var colorCabello=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('colorCabello').find('idCampo').text();
			 $('#cmpColorCabello').find("option[value='"+colorCabello+"']").attr("selected","selected");
			 var formaCabello=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaCabello').find('idCampo').text();
			 $('#cmpFormaCabello').find("option[value='"+formaCabello+"']").attr("selected","selected");
			 var calvieTipo=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('calvicieTipo').find('idCampo').text();
			 $('#cmpCalvieTipo').find("option[value='"+calvieTipo+"']").attr("selected","selected");
			 var cabelloImplantacion=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('cabelloImplantacion').find('idCampo').text();
			 $('#cmpCabelloImplantacion').find("option[value='"+cabelloImplantacion+"']").attr("selected","selected");
			 var cantidadCabello=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('cabelloCantidad').find('idCampo').text();
			 $('#cmpCantidadCabello').find("option[value='"+cantidadCabello+"']").attr("selected","selected");
			 
			 //Media Filiacion Oreja
			 var tamanoOreja=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaTamanio').find('idCampo').text();
			 $('#cmpTamanoOreja').find("option[value='"+tamanoOreja+"']").attr("selected","selected");
			 var lobuloParticularidad=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaLobParticularidad').find('idCampo').text();
			 $('#cmpLobuloParticularidad').find("option[value='"+lobuloParticularidad+"']").attr("selected","selected");
			 var lobuloDimension=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaLobDimension').find('idCampo').text();
			 $('#cmpLobuloDimension').find("option[value='"+lobuloDimension+"']").attr("selected","selected");
			 var lobuloAdherencia=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('orejaLobAdherencia').find('idCampo').text();
			 $('#cmpLobuloAdherencia').find("option[value='"+lobuloAdherencia+"']").attr("selected","selected");
			 var helixAnterior=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixAnterior').find('idCampo').find('idCampo').text();
			 $('#cmpHelixAnterior').find("option[value='"+helixAnterior+"']").attr("selected","selected");
			 var helixPosterior=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixPosterior').find('idCampo').text();
			 $('#cmpHelixPosterior').find("option[value='"+helixPosterior+"']").attr("selected","selected");
			 var helixContorno=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixContorno').find('idCampo').text();
			 $('#cmpHelixContorno').find("option[value='"+helixContorno+"']").attr("selected","selected");
			 var helixAdherencia=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('helixAdherencia').find('idCampo').text();
			 $('#cmpHelixAdherencia').find("option[value='"+helixAdherencia+"']").attr("selected","selected");
			 var formaOreja=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaOreja').find('idCampo').text();
			 $('#cmpFormaOreja').find("option[value='"+formaOreja+"']").attr("selected","selected");
			 
			 //Media Filiacion Ojos
			 var tamanoOjos=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tamanioOjo').find('idCampo').text();
			 $('#cmpTamanoOjos').find("option[value='"+tamanoOjos+"']").attr("selected","selected");
			 var colorOjos=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('colorOjos').find('idCampo').text();
			 $('#cmpColorOjos').find("option[value='"+colorOjos+"']").attr("selected","selected");
			 var formaOjos=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaOjos').find('idCampo').text();
			 $('#cmpFormaOjos').find("option[value='"+formaOjos+"']").attr("selected","selected");
			//Media filiacion Labios
			 var labioNasal=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('alturaNasoLabial').find('idCampo').text();
			 $('#altura_nasal').find("option[value='"+labioNasal+"']").attr("selected","selected");
			 var comisurasLabios=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('labioComisuras').find('idCampo').text();
			 $('#comisuras').find("option[value='"+comisurasLabios+"']").attr("selected","selected");
			 var prominenciaLabios=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('labiosProminencia').find('idCampo').text();
			 $('#prominencia').find("option[value='"+prominenciaLabios+"']").attr("selected","selected");
			 var espesorLabiosSup=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('espesorLabioSup').find('idCampo').text();
			 $('#espesor_labio_sup').find("option[value='"+espesorLabiosSup+"']").attr("selected","selected");
			 var espesorLabiosInfe=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('espesorLabioInf').find('idCampo').text();
			 $('#espesor_labio_inf').find("option[value='"+espesorLabiosInfe+"']").attr("selected","selected");

			 var anchonariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('anchoNariz').find('idCampo').text();
			 $('#ancho_nariz').find("option[value='"+anchonariz+"']").attr("selected","selected");
			 var alturaNariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('alturaNariz').find('idCampo').text();
			 $('#altura_nariz').find("option[value='"+alturaNariz+"']").attr("selected","selected");
			 var raiznariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('raizNariz').find('idCampo').text();
			 $('#raiz_nariz').find("option[value='"+raiznariz+"']").attr("selected","selected");
			 var baseNariz=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('baseNariz').find('idCampo').text();
			 $('#base_nariz').find("option[value='"+baseNariz+"']").attr("selected","selected");
			 
			 var alturaFrente=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('frenteAltura').find('idCampo').text();
			 $('#frente_altura').find("option[value='"+alturaFrente+"']").attr("selected","selected");
			 var anchoFrente=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('frenteAncho').find('idCampo').text();
			 $('#frente_ancho').find("option[value='"+anchoFrente+"']").attr("selected","selected");
			 var inclinacionFrente=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('frenteInclinacion').find('idCampo').text();
			 $('#inclinacion_frente').find("option[value='"+inclinacionFrente+"']").attr("selected","selected");
			var implantacionCejas=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('implantacionCeja').find('idCampo').text();
			 $('#cmpImplantacionCejas').find("option[value='"+implantacionCejas+"']").attr("selected","selected");
			 var formaCejas=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('formaCeja').find('idCampo').text();
			 $('#cmpFormaCejas').find("option[value='"+formaCejas+"']").attr("selected","selected");
			 var tamanoCejas=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tamanioCeja').find('idCampo').text();
			 $('#cmpTamanoCejas').find("option[value='"+tamanoCejas+"']").attr("selected","selected");
			 var cicatricesParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneCicatrices').text();
            if(cicatricesParti=="true"){
            	$('#Cicatrices1').attr("checked","checked");
            }else{
            	$('#Cicatrices2').attr("checked","checked");
            }
            var cicatricesPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionCicatrices').text();
            $('#Cicatricestext').val(cicatricesPartiText);
            var tatuParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneTatuajes').text();
            if(tatuParti=="true"){
            	$('#Tatuajes1').attr("checked","checked");
            }else{
            	$('#Tatuajes2').attr("checked","checked");
            }
            var tatuPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionTatuajes').text();
            $('#Tatuajestext').val(tatuPartiText);
            var lunaresParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneLunares').text();
            if(lunaresParti=="true"){
            	$('#Lunares1').attr("checked","checked");
            }else{
            	$('#Lunares2').attr("checked","checked");
            }
            var lunaresPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionLunares').text();
            $('#Lunarestext').val(lunaresPartiText);
            var defectosParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneDefectosFisicos').text();
            if(defectosParti=="true"){
            	$('#Defectos1').attr("checked","checked");
            }else{
            	$('#Defectos2').attr("checked","checked");
            }	
            var defectosPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionDefectosFisicos').text();
            $('#Defectostext').val(defectosPartiText);
            var protesisParti=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('tieneProtesis').text();
            if(protesisParti=="true"){
            	$('#Protesis1').attr("checked","checked");
            }else{
            	$('#Protesis2').attr("checked","checked");
            }
            var protesisPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionProtesis').text();
            $('#Protesistext').val(protesisPartiText);
            var otraPartiText=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('seniaParticularDTO').find('descripcionOtraSenia').text();
            $('#Otrastext').val(otraPartiText);

            var factorRH=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('factorRH').text();
            if(factorRH=="Negativo"){
				$('#cmpFactorRH').find("option[value=0]").attr("selected","selected");
			}else if(factorRH==""){
				$('#cmpFactorRH').find("option[value=-1]").attr("selected","selected");
			}
			else{        					
				$('#cmpFactorRH').find("option[value=1]").attr("selected","selected");
			}
			var lentes=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('usaLentes').text();
            if(lentes=="true"){
            	$('#lentes1').attr("checked","checked");
            }else{
            	$('#lentes2').attr("checked","checked");
            }
            var tipoSangre=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tipoSangre').find('idCampo').text();
			 $('#cmpTipoSangre').find("option[value='"+tipoSangre+"']").attr("selected","selected");
			 var barba=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tieneBarba').text();
                if(barba=="true"){
                	$('#barba1').attr("checked","checked");
                }else{
                	$('#barba2').attr("checked","checked");
                }
                var peso=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('peso').text();
                $('#peso').val(peso);
                var estatura=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('estatura').text();
                $('#estatura').val(estatura);
                var bigote=$(xml).find('involucradoDTO').find('mediaFiliacionDTO').find('tieneBigote').text();
                if(bigote=="true"){
                	$('#bigote1').attr("checked","checked");
                }else{
                	$('#bigote2').attr("checked","checked");
                }
		}

	    //deshabilita datos de la pantalla de mediafiliacion
		function deshabilitaDatosMediaFiliacion(){
			 $('#cmpTamanoBoca').attr("disabled","disabled");
			 $('#cmpTipoCara').attr("disabled","disabled");
			 $('#cmpFormaMenton').attr("disabled","disabled");
			 $('#cmpTipoMenton').attr("disabled","disabled");
			 $('#cmpTez').attr("disabled","disabled");
			 $('#cmpInclinacionMenton').attr("disabled","disabled");

			 $('#cmpColorCabello').attr("disabled","disabled");
			 $('#cmpFormaCabello').attr("disabled","disabled");
			 $('#cmpCalvieTipo').attr("disabled","disabled");
			 $('#cmpCabelloImplantacion').attr("disabled","disabled");
			 $('#cmpCantidadCabello').attr("disabled","disabled");
			 
			 $('#cmpTamanoOreja').attr("disabled","disabled");
			 $('#cmpLobuloParticularidad').attr("disabled","disabled");
			 $('#cmpLobuloDimension').attr("disabled","disabled");
			 $('#cmpLobuloAdherencia').attr("disabled","disabled");
			 $('#cmpHelixAnterior').attr("disabled","disabled");
			 $('#cmpHelixPosterior').attr("disabled","disabled");
			 $('#cmpHelixContorno').attr("disabled","disabled");
			 $('#cmpHelixAdherencia').attr("disabled","disabled");
			 $('#cmpFormcmpFormaOrejaaMenton').attr("disabled","disabled");

			 $('#cmpTamanoOjos').attr("disabled","disabled");
			 $('#cmpColorOjos').attr("disabled","disabled");
			 $('#cmpFormaOjos').attr("disabled","disabled");
			 $('#altura_nasal').attr("disabled","disabled");
			 $('#comisuras').attr("disabled","disabled");
			 $('#prominencia').attr("disabled","disabled");
			 $('#espesor_labio_sup').attr("disabled","disabled");
			 $('#espesor_labio_inf').attr("disabled","disabled");
			 $('#ancho_nariz').attr("disabled","disabled");
			 $('#altura_nariz').attr("disabled","disabled");
			 $('#raiz_nariz').attr("disabled","disabled");
			 $('#base_nariz').attr("disabled","disabled");
			 $('#frente_altura').attr("disabled","disabled");
			 $('#frente_ancho').attr("disabled","disabled");
			 $('#inclinacion_frente').attr("disabled","disabled");
			 $('#cmpImplantacionCejas').attr("disabled","disabled");
			 $('#cmpFormaCejas').attr("disabled","disabled");
			 $('#cmpTamanoCejas').attr("disabled","disabled");

			 $('#Cicatrices1').attr("disabled","disabled");
			 $('#Cicatrices2').attr("disabled","disabled");
			 $('#Cicatricestext').attr("disabled","disabled");
			 $('#Tatuajes1').attr("disabled","disabled");
			 $('#Tatuajes2').attr("disabled","disabled");
			 $('#Tatuajestext').attr("disabled","disabled");
			 $('#Lunares1').attr("disabled","disabled");
			 $('#Lunares2').attr("disabled","disabled");
			 $('#Lunarestext').attr("disabled","disabled");
			 $('#Defectos1').attr("disabled","disabled");
			 $('#Defectos2').attr("disabled","disabled");
			 $('#Defectostext').attr("disabled","disabled");
			 $('#Protesis1').attr("disabled","disabled");
			 $('#Protesis2').attr("disabled","disabled");
			 $('#Protesistext').attr("disabled","disabled");
			 $('#Otrastext').attr("disabled","disabled");
			 $('#cmpFactorRH').attr("disabled","disabled");
			 $('#lentes1').attr("disabled","disabled");
			 $('#lentes2').attr("disabled","disabled");
			 $('#cmpTipoSangre').attr("disabled","disabled");
			 $('#barba1').attr("disabled","disabled");
			 $('#barba2').attr("disabled","disabled");
			 $('#peso').attr("disabled","disabled");
			 $('#estatura').attr("disabled","disabled");
			 $('#bigote1').attr("disabled","disabled");
			 $('#bigote2').attr("disabled","disabled");
		}

		//habilita datos d ela pantalla media filiacion
		function habilitaDatosMediaFiliacion(){
			var $widget = $("select").multiselect();
			var	state = true; 
		   state = !state; 
		   $widget.multiselect(state ? 'disable' : 'enable'); 
			
			 $('#cmpTamanoBoca').attr("disabled","");
			 $('#cmpTipoCara').attr("disabled","");
			 $('#cmpFormaMenton').attr("disabled","");
			 $('#cmpTipoMenton').attr("disabled","");
			 $('#cmpTez').attr("disabled","");
			 $('#cmpInclinacionMenton').attr("disabled","");

			 $('#cmpColorCabello').attr("disabled","");
			 $('#cmpFormaCabello').attr("disabled","");
			 $('#cmpCalvieTipo').attr("disabled","");
			 $('#cmpCabelloImplantacion').attr("disabled","");
			 $('#cmpCantidadCabello').attr("disabled","");
			 
			 $('#cmpTamanoOreja').attr("disabled","");
			 $('#cmpLobuloParticularidad').attr("disabled","");
			 $('#cmpLobuloDimension').attr("disabled","");
			 $('#cmpLobuloAdherencia').attr("disabled","");
			 $('#cmpHelixAnterior').attr("disabled","");
			 $('#cmpHelixPosterior').attr("disabled","");
			 $('#cmpHelixContorno').attr("disabled","");
			 $('#cmpHelixAdherencia').attr("disabled","");
			 $('#cmpFormcmpFormaOrejaaMenton').attr("disabled","");

			 $('#cmpTamanoOjos').attr("disabled","");
			 $('#cmpColorOjos').attr("disabled","");
			 $('#cmpFormaOjos').attr("disabled","");
			 $('#altura_nasal').attr("disabled","");
			 $('#comisuras').attr("disabled","");
			 $('#prominencia').attr("disabled","");
			 $('#espesor_labio_sup').attr("disabled","");
			 $('#espesor_labio_inf').attr("disabled","");
			 $('#ancho_nariz').attr("disabled","");
			 $('#altura_nariz').attr("disabled","");
			 $('#raiz_nariz').attr("disabled","");
			 $('#base_nariz').attr("disabled","");
			 $('#frente_altura').attr("disabled","");
			 $('#frente_ancho').attr("disabled","");
			 $('#inclinacion_frente').attr("disabled","");

			 $('#cmpImplantacionCejas').attr("disabled","");
			 $('#cmpFormaCejas').attr("disabled","");
			 $('#cmpTamanoCejas').attr("disabled","");

			 $('#Cicatrices1').attr("disabled","");
			 $('#Cicatrices2').attr("disabled","");
			 $('#Cicatricestext').attr("disabled","");
			 $('#Tatuajes1').attr("disabled","");
			 $('#Tatuajes2').attr("disabled","");
			 $('#Tatuajestext').attr("disabled","");
			 $('#Lunares1').attr("disabled","");
			 $('#Lunares2').attr("disabled","");
			 $('#Lunarestext').attr("disabled","");
			 $('#Defectos1').attr("disabled","");
			 $('#Defectos2').attr("disabled","");
			 $('#Defectostext').attr("disabled","");
			 $('#Protesis1').attr("disabled","");
			 $('#Protesis2').attr("disabled","");
			 $('#Protesistext').attr("disabled","");
			 $('#Otrastext').attr("disabled","");
			 $('#cmpFactorRH').attr("disabled","");
			 $('#lentes1').attr("disabled","");
			 $('#lentes2').attr("disabled","");
			 $('#cmpTipoSangre').attr("disabled","");
			 $('#barba1').attr("disabled","");
			 $('#barba2').attr("disabled","");
			 $('#peso').attr("disabled","");
			 $('#estatura').attr("disabled","");
			 $('#bigote1').attr("disabled","");
			 $('#bigote2').attr("disabled","");
		} 


</script>
<body>

	<div id="tabs2" class="tabEstilo">
		<ul>
			<li><a href="#tabs-21">Cara</a>
			</li>
			<li><a href="#tabs-22">Cabello</a>
			</li>
			<li><a href="#tabs-23">Orejas</a>
			</li>
			<li><a href="#tabs-24">Ojos</a>
			</li>
			<li><a href="#tabs-25">Labios</a>
			</li>
			<li><a href="#tabs-26">Nariz</a>
			</li>
			<li><a href="#tabs-27">Frente</a>
			</li>
			<li><a href="#tabs-28">Cejas</a>
			</li>
			<li><a href="#tabs-29">Señas Particulares</a>
			</li>
			<li><a href="#tabs-30">Otros</a>
			</li>
		</ul>

		<div id="tabs-21">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="25">Tamaño de Boca:</td>
					<td width="53" height="25" align="left"><select
						id="cmpTamanoBoca" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Tipo de Cara:</td>
					<td width="53" height="25" align="left"><select
						id="cmpTipoCara" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">Forma de Mentón:</td>
					<td width="142" height="25" align="left"><select
						id="cmpFormaMenton" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Tipo Ment&oacute;n:</td>
					<td width="53" height="25" align="left"><select
						id="cmpTipoMenton" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>

				</tr>
				<tr>
					<td align="right" height="25">Tez:</td>
					<td width="53" height="25" align="left"><select id="cmpTez"
						style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td width="120" height="25" align="right">Inclinaci&oacute;n
						Ment&oacute;n:</td>
					<td width="142" height="25" align="left"><select
						id="cmpInclinacionMenton" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>

			</table>
		</div>
		<div id="tabs-22">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="25">Color de Cabello:</td>
					<td width="53" height="25" align="left"><select
						id="cmpColorCabello" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Forma de Cabello:</td>
					<td width="53" height="25" align="left"><select
						id="cmpFormaCabello" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Calvie Tipo:</td>
					<td width="142" height="25" align="left"><select
						id="cmpCalvieTipo" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Cabello Implantaci&oacute;n:</td>
					<td width="53" height="25" align="left"><select
						id="cmpCabelloImplantacion" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Cantidad:</td>
					<td width="142" height="25" align="left"><select
						id="cmpCantidadCabello" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>

			</table>
		</div>
		<div id="tabs-23">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="left">No Tiene:</td>
					<td><input type="checkbox" name="chkOreja" id="chkOreja"
						value="" /></td>
					<td width="120" height="25" align="right">Oreja:</td>
					<td width="142" height="25" align="left"><select id="cmpOreja"
						style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>
				<tr>
					<td align="right" height="25">Tamaño de Oreja:</td>
					<td width="53" height="25" align="left"><select
						id="cmpTamanoOreja" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Oreja L&oacute;bulo
						Particularidad:</td>
					<td width="53" height="25" align="left"><select
						id="cmpLobuloParticularidad" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Oreja L&oacute;bulo
						Dimensi&oacute;n:</td>
					<td width="142" height="25" align="left"><select
						id="cmpLobuloDimension" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Oreja L&oacute;bulo Adherencia:</td>
					<td width="53" height="25" align="left"><select
						id="cmpLobuloAdherencia" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">H&eacute;lix
						Anterior:</td>
					<td width="142" height="25" align="left"><select
						id="cmpHelixAnterior" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td width="120" height="25" align="right">H&eacute;lix
						Posterior:</td>
					<td width="142" height="25" align="left"><select
						id="cmpHelixPosterior" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">H&eacute;lix
						Contorno:</td>
					<td width="142" height="25" align="left"><select
						id="cmpHelixContorno" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td width="120" height="25" align="right">H&eacute;lix
						Adherencia:</td>
					<td width="142" height="25" align="left"><select
						id="cmpHelixAdherencia" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">Forma Oreja:</td>
					<td width="142" height="25" align="left"><select
						id="cmpFormaOreja" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>

			</table>
		</div>
		<div id="tabs-24">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="left">No Tiene:</td>
					<td><input type="checkbox" name="sin_ojo " id="sin_ojo"
						value="S" /></td>
					<td></td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">Tamaño Ojo:</td>
					<td width="142" height="25" align="left"><select
						id="cmpTamanoOjos" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">Color Ojo:</td>
					<td width="142" height="25" align="left"><select
						id="cmpColorOjos" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">Forma Ojo:</td>
					<td width="142" height="25" align="left"><select
						id="cmpFormaOjos" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>
				</tr>

			</table>
		</div>
		<div id="tabs-25">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="25">Altura Naso Labial:</td>
					<td width="53" height="25" align="left">
						<!--                                                                    <input type="text"
                                                                           id="altura_nasal" size="15" maxlength="15" />-->
						<select id="altura_nasal" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Comisuras:</td>
					<td width="53" height="25" align="left">
						<!--                                                                    <input type="text"
									id="comisuras" size="15" maxlength="15" />--> <select
						id="comisuras" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Espesor Labio
						Inferior:</td>
					<td width="142" height="25" align="left">
						<!--                                                                    <input type="text"
									id="espesor_labio_inf" size="15" maxlength="15" />--> <select
						id="espesor_labio_inf" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td align="right" height="25">Espesor Labio Superior:</td>
					<td width="53" height="25" align="left">
						<!--                                                                    <input type="text"
									id="espesor_labio_sup" size="15" maxlength="15" />--> <select
						id="espesor_labio_sup" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Prominencia:</td>
					<td width="142" height="25" align="left">
						<!--                                                                    <input type="text"
									id="prominencia" size="15" maxlength="15" />--> <select
						id="prominencia" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
				</tr>

			</table>
		</div>
		<div id="tabs-26">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="25">Ancho Nariz:</td>
					<td width="53" height="25" align="left">
						<!--                                                                    <input type="text"
									id="ancho_nariz" size="15" maxlength="15" />--> <select
						id="ancho_nariz" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Altura Nariz:</td>
					<td width="142" height="25" align="left">
						<!--                                                                    <input type="text"
									id="altura_nariz" size="15" maxlength="15" />--> <select
						id="altura_nariz" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Base Nariz:</td>
					<td width="142" height="25" align="left">
						<!--                                                                    <input type="text"
									id="base_nariz" size="15" maxlength="15" />--> <select
						id="base_nariz" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">Ra&iacute;z Nariz:</td>
					<td width="142" height="25" align="left">
						<!--                                                                <input type="text"
                                                                       id="raiz_nariz" size="15" maxlength="15" />-->
						<select id="raiz_nariz" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>
				</tr>

			</table>
		</div>
		<div id="tabs-27">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="25">Frente Altura:</td>
					<td width="53" height="25" align="left">
						<!--                                                                    <input type="text"
									id="frente_altura" size="15" maxlength="15" />--> <select
						id="frente_altura" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Frente Ancho:</td>
					<td width="142" height="25" align="left">
						<!--                                                                    <input type="text"
									id="frente_ancho" size="15" maxlength="15" />--> <select
						id="frente_ancho" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Frente
						Inclinaci&oacute;n:</td>
					<td width="142" height="25" align="left">
						<!--                                                                   <input type="text"
									id="inclinacion_frente" size="15" maxlength="15" />--> <select
						id="inclinacion_frente" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>
				</tr>

			</table>
		</div>
		<div id="tabs-28">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="25">Implantación</td>
					<td width="53" height="25" align="left"><select
						id="cmpImplantacionCejas" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Forma:</td>
					<td width="142" height="25" align="left"><select
						id="cmpFormaCejas" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>

				</tr>
				<tr>
					<td width="120" height="25" align="right">Tamaño:</td>
					<td width="142" height="25" align="left"><select
						id="cmpTamanoCejas" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td></td>
				</tr>

			</table>
		</div>
		<div id="tabs-29">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td align="right" height="25">Cicatrices:</td>
					<td width="53" height="25" align="right"><input type="radio"
						value="1" id="Cicatrices1" name="Cicatrices" size="15"
						maxlength="15" />Si</td>
					<td width="53" height="25" align="left"><input type="radio"
						value="0" id="Cicatrices2" name="Cicatrices" size="15"
						maxlength="15" />No</td>
					<td width="53" height="25" align="left"><input type="text"
						id="Cicatricestext" size="15" maxlength="15" /></td>
					<td align="right" height="25">Prótesis:</td>
					<td width="53" height="25" align="right"><input type="radio"
						id="Protesis1" name="Protesis" size="15" maxlength="15" />Si</td>
					<td width="53" height="25" align="left"><input type="radio"
						id="Protesis2" name="Protesis" size="15" maxlength="15" />No</td>
					<td width="53" height="25" align="left"><input type="text"
						id="Protesistext" size="15" maxlength="15" /></td>

				</tr>
				<tr>
					<td align="right" height="25">Tatuajes:</td>
					<td width="53" height="25" align="right"><input type="radio"
						id="Tatuajes1" name="Tatuajes" size="15" maxlength="15" />Si</td>
					<td width="53" height="25" align="left"><input type="radio"
						id="Tatuajes2" name="Tatuajes" size="15" maxlength="15" />No</td>
					<td width="53" height="25" align="left"><input type="text"
						id="Tatuajestext" size="15" maxlength="15" /></td>
					<td align="right" height="25">Otras:</td>
					<td width="53" height="25" align="left"><input type="text"
						id="Otrastext" size="15" maxlength="15" /></td>
				</tr>
				<tr>
					<td align="right" height="25">Lunares:</td>
					<td width="53" height="25" align="right"><input type="radio"
						id="Lunares1" name="Lunares" size="15" maxlength="15" />Si</td>
					<td width="53" height="25" align="left"><input type="radio"
						id="Lunares2" name="Lunares" size="15" maxlength="15" />No</td>
					<td width="53" height="25" align="left"><input type="text"
						id="Lunarestext" size="15" maxlength="15" /></td>
				</tr>
				<tr>
					<td align="right" height="25">Defectos Fisicos:</td>
					<td width="53" height="25" align="right"><input type="radio"
						id="Defectos1" name="Defectos" size="15" maxlength="15" />Si</td>
					<td width="53" height="25" align="left"><input type="radio"
						id="Defectos2" name="Defectos" size="15" maxlength="15" />No</td>
					<td width="53" height="25" align="left"><input type="text"
						id="Defectostext" size="15" maxlength="15" /></td>
				</tr>

			</table>
		</div>
		<div id="tabs-30">
			<table width="700px" height="250px" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td width="120" height="25" align="right">Factor RH:</td>
					<td width="142" height="25" align="left"><select
						id="cmpFactorRH" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
							<option value="0">- Negativo</option>
							<option value="1">+ Positivo</option>
					</select></td>
					<td align="right" height="25">Usa lentes:</td>
					<td width="53" height="25" align="right"><input type="radio"
						id="lentes1" name="lentes" value="1" size="15" maxlength="15" />Si
					</td>
					<td width="53" height="25" align="left"><input type="radio"
						id="lentes2" name="lentes" value="0" size="15" maxlength="15" />No
					</td>
				</tr>
				<tr>


					<td width="120" height="25" align="right">Tipo Sangre:</td>
					<td width="142" height="25" align="left"><select
						id="cmpTipoSangre" style="width: 180px;">
							<option value="-1">-Seleccione-</option>
					</select></td>
					<td width="120" height="25" align="right">Tiene barba:</td>
					<td width="53" height="25" align="right"><input type="radio"
						id="barba1" name="barba" value="1" size="15" maxlength="15" />Si
					</td>
					<td width="53" height="25" align="left"><input type="radio"
						id="barba2" name="barba" value="0" size="15" maxlength="15" />No
					</td>
				</tr>
				<tr>

					<td width="120" height="25" align="right">Peso(kg.):</td>
					<td width="142" height="25" align="left"><input type="text"
						id="peso" size="15" maxlength="15" onkeypress="return solonumeros(event);"/></td>
					<td width="120" height="25" align="right">Tiene bigote:</td>
					<td width="53" height="25" align="right"><input type="radio"
						id="bigote1" name="bigote" value="1" size="15" maxlength="15" />Si
					</td>
					<td width="53" height="25" align="left"><input type="radio"
						id="bigote2" name="bigote" value="0" size="15" maxlength="15" />No
					</td>
				</tr>
				<tr>
					<td width="120" height="25" align="right">Estatura(m.):</td>
					<td width="142" height="25" align="left"><input type="text"
						id="estatura" size="15" maxlength="15" onkeypress="return solonumeros(event);"/></td>
					<td></td>
				</tr>


			</table>
		</div>
	</div>




</body>

	<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>

<script type="text/javascript">

//tabs de media filiacion
$("#tabs2").tabs();

//empieza carga de catalogos
$("#cmpTamanoBoca,#cmpTipoCara,#cmpFormaMenton,#cmpTipoMenton,#cmpTez,#cmpInclinacionMenton").multiselect({ 
	   multiple: false, 
	   header: "Seleccione", 
	   position: { 
		      my: 'center', 
		      at: 'center' 
		   } ,
	   selectedList: 1 
	});

$("#cmpColorCabello,#cmpFormaCabello,#cmpCalvieTipo,#cmpCabelloImplantacion,#cmpCantidadCabello").multiselect({ 
	   multiple: false, 
	   header: "Seleccione", 
	   position: { 
		      my: 'center', 
		      at: 'center' 
		   } ,
	   selectedList: 1 
	});


$("#cmpOreja,#cmpTamanoOreja,#cmpLobuloParticularidad,#cmpLobuloDimension,#cmpLobuloAdherencia,#cmpHelixAnterior,#cmpHelixPosterior,#cmpHelixContorno,#cmpHelixAdherencia,#cmpFormaOreja").multiselect({ 
	   multiple: false, 
	   header: "Seleccione", 
	   position: { 
		      my: 'center', 
		      at: 'center' 
		   } ,
	   selectedList: 1 
	});

$("#cmpFormaOjos,#cmpTamanoOjos,#cmpColorOjos").multiselect({ 
	   multiple: false, 
	   header: "Seleccione", 
	   position: { 
		      my: 'center', 
		      at: 'center' 
		   } ,
	   selectedList: 1 
	});        		        

$("#cmpImplantacionCejas,#cmpFormaCejas,#cmpTamanoCejas,#cmpTipoSangre,#cmpFactorRH").multiselect({ 
	   multiple: false, 
	   header: "Seleccione", 
	   position: { 
		      my: 'center', 
		      at: 'center' 
		   } ,
	   selectedList: 1 
	});  	

cargaTamanoBoca();
cargaTipoCara();
cargaFormaMenton();
cargaTipoMenton();
cargaTez();
cargaInclinacionMenton();
cargaColorCabello();
cargaFormaCabello();
cargaCalvieTipo();
cargaCabelloImplantacion();
cargaCantidadCabello();		
cargaOreja();
cargaTamanoOreja();
cargaLobuloParticularidad();
cargaLobuloDimension();
cargaLobuloAdherencia();
cargaHelixAnterior();
cargaHelixPosterior();
cargaHelixContorno();
cargaHelixAdherencia();
cargaFormaOreja();
cargaColorOjos();
cargaTamanoOjos();
cargaFormaOjos();		
cargaAlturaNasoLabial();
cargaComisuras();
cargaEspesorLabioInferior();
cargaEspesorLabioSuperior();
cargaProminencia();
cargaCatalogoPonEn("AnchoNariz", "ancho_nariz");
cargaCatalogoPonEn("AlturaNariz", "altura_nariz");
cargaCatalogoPonEn("BaseNariz", "base_nariz");
cargaCatalogoPonEn("RaizNariz", "raiz_nariz");
cargaCatalogoPonEn("FrenteAltura", "frente_altura");
cargaCatalogoPonEn("FrenteAncho", "frente_ancho");
cargaCatalogoPonEn("InclinacionFrente", "inclinacion_frente");
cargaImplantacionCeja();
cargaFormaCejas();
cargaTamanoCeja();
cargaTipoSangre();
</script>
