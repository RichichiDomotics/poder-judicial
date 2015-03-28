

   $(document).ready(function(){
	   //alert("ready");
		  cargaDefensor();
		});
		
		function cargaDefensor(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/ConsultarListaAbogados.do',
			data : '',
			dataType : 'xml',
			success : function(xml) {
				var option;
				$(xml).find('defensor').each(
						function() {
							$('#idDefensor').append('<option value="'+ $(this).find('gcnombre').text()
											+ '">'+ $(this).find('gcapellidoPaterno').text() + '</option>');
						});
			}
		});
	}
