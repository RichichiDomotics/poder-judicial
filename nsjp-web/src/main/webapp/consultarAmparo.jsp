        <script type="text/javascript">
        var idNumeroExpediente='<%= request.getParameter("idNumeroExpediente")%>';
         $(document).ready(function() {
        	 //alert(idNumeroExpediente);
         });

         function consultarAmparosPorExpediente(){
             jQuery("#gridAmparos").jqGrid({
                  url:'<%=request.getContextPath()%>'+'/consultarAmparosPorExpediente.do?idNumeroExpediente='+ idNumeroExpediente,
                  datatype: "xml",
                  colNames:['Folio del Amparo','Descripción','Amparo'],
                  colModel:[
                      {name:'folio',index:'1',  sortable:true, width:200},
                      {name:'descripcion',index:'2',  sortable:false, width:300},
                      {name:'verDocumento',index:'3',  sortable:false, width:250, align:'center'},
                  ],
                  pager: jQuery('#paginadorAmparos'),
                  rowNum:10,
                  rowList:[10,20,30],
                  caption:"* Amparo(s) asociado(s) al expediente",
                  //multiselect: true,
                  sortname: '1',
                  viewrecords: true,
                  sortorder: "asc",
              	  gridComplete: function(){
					  var ids = jQuery("#gridAmparos").jqGrid('getDataIDs');
					  for(var i=0;i < ids.length;i++){
						var cl = ids[i];
					 
						el = 
							"<a href='#' title='Ver Documento' onclick=\"consultaPDF("+cl+");\">Ver documento</a>";									 
						jQuery("#gridAmparos").jqGrid('setRowData',ids[i],{verDocumento:el});
					  }	
				    },
                  onSelectRow: function(id){
                   	 	consultarInvolucradosPorAmparo(id);
					}
              }).navGrid('#paginadorAmparos',{edit:false,add:false,del:false});
				$("#gridAmparos").trigger("reloadGrid");	
		}
         
         var recargarGridgInvolucradosDelAmparo=false;
         function consultarInvolucradosPorAmparo(idAmparo){
        	 var params;
 			 params="idAmparo=" + idAmparo;      
			 //params+="&tipoBusqueda="+tipoBusqueda; 
        	 
        	 if (recargarGridgInvolucradosDelAmparo==false){
        		 jQuery("#gridInvolucradosDelAmparo").jqGrid({
                     url:'<%=request.getContextPath()%>'+'/consultarInvolucradosAsociadosAAmparo.do?'+params,
                     datatype: "xml",
                     colNames:['Nombre completo','Calidad'],
                     colModel:[
                         {name:'nombre',index:'1',  sortable:false, width:200},
                         {name:'calidad',index:'2',  sortable:false, width:300},
                     ],
                     pager: jQuery('#paginadorInvolucradosDelAmparo'),
                     rowNum:10,
                     rowList:[10,20,30],
                     caption:"* Involucrado(s) que ampara",
                     //multiselect: true,
                     sortname: '2',
                     viewrecords: true,
                     sortorder: "asc",
                     onSelectRow: function(id){
   					
   				  }
                 }).navGrid('#paginadorInvolucradosDelAmparo',{edit:false,add:false,del:false});
   				$("#gridInvolucradosDelAmparo").trigger("reloadGrid");
   				recargarGridgInvolucradosDelAmparo=true;
        	 }else{//Recargar el grid
        		 jQuery("#gridInvolucradosDelAmparo").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarInvolucradosAsociadosAAmparo.do?'+params,datatype: "xml" });
				 $("#gridInvolucradosDelAmparo").trigger("reloadGrid");
        	 }
            
		}
         
 		

      	/*
      	*Funcion que permite averidguar si existen amparos en un expediente
      	*/
      	function existenAmparosEnExpediente()
      	{
      		var param="numeroExpediente="+numeroExpediente;
      		$.ajax({
      			type: 'POST',
      			url: '<%= request.getContextPath()%>/consultarAmparosPorExpediente.do?idNumeroExpediente='+ idNumeroExpediente,
      			data: param,
      			dataType: 'xml',
      			async: false,
      			success: function(xml){
  					if(parseInt($(xml).find('row').length) >0)
  						$("#lblAmparos").html("Si")
  					else
  						$("#lblAmparos").html("No")

      			}
      		});
      	}
         		
        </script>

	<table border="0" summary="Consulta información de amparos">
	  <tr>
	    <td>
	  		<div id="divGridAmparos"> 
		    	<table id="gridAmparos"></table>
		        <div id="paginadorAmparos"></div>
		    </div>
	    </td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>
	    	<div id="divGridInvolucradosDelAmparo"> 
		    	<table id="gridInvolucradosDelAmparo"></table>
		        <div id="paginadorInvolucradosDelAmparo"></div>
		    </div>
	    </td>
	  </tr>
	</table>