/**
* Nombre del Programa : modificarTutor.js
* Autor               : Armando Castaneda
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 09/03/2011
* Marca de cambio     : N/A
* Descripcion General : Archivo javaScript con las funciones para la vista de modificarTutor
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/

/**
 * Función que crea los radiobuttons para Condición en ingresarTutor
 * @param contexto Contexto de la aplicación
 */
function cargarRbtCondicionTutor(contexto) {
	$.ajax({
		type: 'POST',
	    url:  contexto + '/CargarCondicionTutor.do',
	    data: '',
	    dataType: 'xml',
	    success: function(xml){
	    	$('#tdModTutTutorCondicion').empty();
		    $(xml).find('condicionDTO').each(function(){
		    	$('#tdModTutTutorCondicion').append('<input type="radio" name="rdbModTutTutorCondicion" value="' + $(this).find('glCatalogoId').text() +'">' + $(this).find('gcDescripcion').text() + '<br/>');
		    });
	    }
	});
}

//Funcion que carga los combos de tipo de identificacion
function cargaTipoDeIdentificacion(contexto) {
  $.ajax({
	  type: 'POST',
	  url: contexto + '/ingresarTipoDeDocumentoDeIdentificacion.do',
	  data: '',
	  dataType: 'xml',
	  success: function(xml){
		  $('#cbxModTutTipoIdentificacion').empty();
		  $('#cbxModTutTipoIdentificacion').append('<option value="-1">- Seleccione - </option>');
		  $(xml).find('catTipoIdentificacion').each(function(){
			$('#cbxModTutTipoIdentificacion').append('<option value="'
					 + $(this).find('gcNombre').text() + '">' 
					 + $(this).find('gcDescripcion').text() 
					 + '</option>');
		   });
	  }
	});
}


//Funcion que carga los combos de tipo de identificacion
function mostrarPantallaModificarTutor(contexto) {
  $.ajax({
	  type: 'POST',
	  url: contexto + '/MostrarDatosTutorModTutor.do',
	  data: '',
	  dataType: 'xml',
	  success:function(xml){
		  $('#renglonPrueba').empty();
		  //seteo el valor del combobox
		  $(xml).find('catTipoIdentificacionDTO').each(function(){
			  $('#cbxModTutTipoIdentificacion').attr('selectedIndex',parseInt($(this).find('glCatalogoId').text()));
		   });
		  //
		  $(xml).find('listaNombreDemograficoDTO').find('nombreDG').each(function(){
			$('#renglonPrueba').html($(this).find('gcNombre').text() + ' ' 
					 + $(this).find('gcApellidoPaterno').text() + ' ' 
					 + $(this).find('gcApellidoMaterno').text());
			});
		  //
		  $(xml).find('tutor').each(function(){
			  $('#txtModTutFolioIdentificacion').attr('value',$(this).find('gcFolioIdentificacion').text());
			  if($(this).find('gbEsServidor').text()=="true"){
				  //hacemos un check al checkbox de es Servidor publico
				  $('#chkModTutEsServidorPub').attr('checked','checked');
			  }
			  else{
				  $('#chkModTutEsServidorPub').attr('checked','');
			  }
		    });
	  }
  });
}

function generaDivPopupPorAncla(idAncla)
{
	alert(idAncla);
	var idAnclaSplit = idAncla.split('_');//($(this).attr("id")).split('_');
    generaPopup('divNo_'+idAnclaSplit[1]);
}

/**Funciones de prueba para la tabla HTML**/
function generaTabla(contexto)
{
	  $.ajax({
		  type: 'POST',
		  url: contexto + '/pruebaTablaHtml.do',
		  data: '',
		  dataType: 'xml',
		  async:false,
		  success: function(xml){
			  var totalRenglones=parseInt($(xml).find('nombreDGO').length);
			  if(totalRenglones>0)
			  {
				  var i=0;
				  
				  $(xml).find('nombreDGO').each(function(){
					  if(i!=totalRenglones)
					  {						  					  
						  $('#tbodyDetalle').append('<tr><td><a href="#" class="PopupAncla" id="ancla_'+i+'">'+(i+1)+'  ss </a></td><td>' + $(this).find('gcNombre').text() 
								  +'</td><td>' + $(this).find('gcApellidoPaterno').text() 
								  + '</td><td>'+$(this).find('gcApellidoPaterno').text() + '</td></tr>');
						  $('#divsDinamicos').append('<div class="dinamicosDIV" id="divNo_'+i+'"></div>');
						  $("#divNo_"+i).hide();
						  agregaCodigoDiv($("#divNo_"+i));
					  }
					  i++;
				   });
				  //mandamos a paginar la tabla
				  agregaPaginacion();
				  //crea popups
				  //creaPopups();
				  //asignaClickAncla(); 
				  creaPopups();
			  }
		  }
		});
}

function asignaClickAncla()
{
	//cachamos el evento change del select
    $(".PopupAnclaX").live("click", function(event) {
    	
        //llamamos la función para mostrar el popup
   //     var idAnclaSplit = ($(this).attr("id")).split('_');
     //   $(this).attr("href","javascript::generaPopup('divNo_"+idAnclaSplit[1]+"')");
    //    alert($('divNo_'+idAnclaSplit[1]).html());
        //generaPopup($(this).attr("id"));
         
 		   //creo una capa con la letra pulsada
 		   //var caja = $('<div class="dialogletra" title="Popup Iesimo"> div - XXX</div>');
 		   
 		  var idAnclaSplit = ($(this).attr("id")).split('_');
 		  var caja=$("#divNo_"+idAnclaSplit[1]);
 		  //alert(caja.html());
 		   //la convierto en caja de diálogo modal box
 		   caja.dialog({
 		      modal: false,
 		      buttons: {
 		         "Ok": function(){
 		            $(this).dialog("close");
 		         }
 		      },
 		      show: "fold",
 		      hide: "scale"
 		   });
       });  	
}

function creaPopups()
{
	//cachamos el evento change live de las anclas
    $(".PopupAncla").live("click", function(event) {
    	var idAnclaSplit = ($(this).attr("id")).split('_');
		  //var caja=$("#divNo_"+idAnclaSplit[1]);
    	agregaCodigoDiv("#divNo_"+idAnclaSplit[1]);
    	var $dialogo = $("#divNo_"+idAnclaSplit[1]).dialog({autoOpen: false,title: 'Consultar Victima',
  		  width: 700,
  		  minWidth: 650,
  		  maxWidth: 700,
  		  height: 700,
  		  show: "fold",
  		  hide: "scale",
  		  modal: false});
    	$dialogo.dialog('option', 'modal', false).dialog('open');
		}); 
}

function generaPopup(idDiv)
{
	alert(idDiv);
	var $dialogo = $(idDiv).dialog({autoOpen: false,title: 'Consultar Victima',
		  width: 700,
		  minWidth: 650,
		  maxWidth: 700,
		  height: 700,
		  show: "fold",
		  hide: "scale",
		  modal: false});
	$dialogo.dialog('option', 'modal', false).dialog('open'); 
}

/*************************************  Funcion para el paginado en las tablas  *****************************/
function agregaPaginacion() {
    $("#paginacion").html("");
    paginado();
    $("#paginacion").trigger('group');
    $("#pagno0").click();
    seteaLeyendasPaginacion();
}

var ii_numPags = 0;
var ii_currentPag = 0;
var ii_numRows = 0;
var ii_numRowsShowed = 0;
var ii_currentPos = 0;
var ii_currentTrip = 0;
function paginado() {
    $('table.paginated').each(function() {
        var currentPage = 0;
        var numPerPage = 3;
        var casillas = 3;
        var posicion = 0;
        var currentTrip = 0;
        var selected = 0;
        var $table = $(this);
        $table.bind('repaginate', function() {
            $table.find('tbody tr').hide()
                        .slice(currentPage * numPerPage,
                        (currentPage + 1) * numPerPage)
                        .show();
        }); //termina bind
        var numRows = $table.find('tbody tr').length;
        var numPages = Math.ceil(numRows / numPerPage);
        var numTrips = Math.ceil(numPages / casillas);

        ii_numPags = numPages; //ACT-20100810
        ii_numRows = numRows; //ACT-20100810
        ii_numRowsShowed = 0; //ACT-20100810
        ii_currentPag = 0; //ACT-20100810
        ii_currentPos = 0;
        ii_currentTrip = 0;
        var $pager = $("#paginacion");
        $pager.bind('group', function() {
            $pager.find('.page-number').hide()
						.slice(currentTrip * casillas, (currentTrip + 1) * casillas)
						.show();
        });
        $('<span id="textPagina"></span>').text('P\xE1gina: ').appendTo($pager);
        $('<span id="textAnterior"></span>').text('Anterior').appendTo($pager).bind('click', function() {
            posicion--;
            if (posicion < 0) {
                posicion = casillas - 1;
                currentTrip--;
                ii_currentTrip = currentTrip;
                $pager.trigger('group');
                if (currentTrip == 0)
                    $('#textAnterior').hide();
                if (currentTrip >= 0)
                    $('#textSiguiente').show();
            }
            selected = posicion + currentTrip * casillas;
            ii_currentPos = selected;
            ii_currentTrip = currentTrip;
            $('#pagno' + selected).click();
        });
        $('#textAnterior').hide();
        for (var page = 0; page < numPages; page++) {
            $('<span class="page-number" id="pagno' + page + '"></span>').text(page + 1)
                        .bind('click', { newPage: page }, function(event) {
                            currentPage = event.data['newPage'];
                            posicion = event.data['newPage'] - currentTrip * casillas;
                            $("#textPagina ~ .page-number").removeClass('ent2 ent1');
                            $("#textPagina ~ .page-number").addClass('ent2');
                            $("#textAnterior").addClass('ant')
                            $("#textSiguiente").addClass('entsig');
                            $table.trigger('repaginate');
                            $(this).removeClass('ent2');
                            $(this).addClass('ent1');
                            ii_currentPag = posicion + 1; //ACT-20100810
                            ii_currentPos = posicion + currentTrip * casillas;
                            ii_currentTrip = currentTrip;
                            //seteamos el numero de registros que se estan mostrando ACT-20100810
                            if (ii_numRows == 0)
                            { ii_numRowsShowed = 0 }
                            else if (ii_numRows <= numPerPage) {
                                ii_numRowsShowed = ii_numRows
                            }
                            else {
                                if (posicion == (ii_numPags - 1)) {
                                    //estamos en la ultima pagina
                                    ii_numRowsShowed = ii_numRows - ((ii_numPags - 1) * (numPerPage));
                                }
                                else {
                                    ii_numRowsShowed = numPerPage;
                                }
                            }
                            seteaLeyendasPaginacion(); //FIN ACT-20100810
                        }).appendTo($pager);
        }
        $('<span id="textSiguiente"></span>').text('Siguiente').appendTo($pager).bind('click', function() {
            posicion++;
            if (posicion > (casillas - 1)) {
                posicion = 0;
                currentTrip++;
                ii_currentTrip = currentTrip;
                $pager.trigger('group');
                if (currentTrip < numTrips)
                    $('#textAnterior').show();
                if (currentTrip == numTrips - 1)
                    $('#textSiguiente').hide();
            }
            selected = posicion + currentTrip * casillas;
            ii_currentPos = selected;
            ii_currentTrip = currentTrip;
            $('#pagno' + selected).click();
        });
        if (numPages <= 3)
            $('#textSiguiente').hide();
        $("#entpag").append($pager);
    });
    return false;
}

function seteaLeyendasPaginacion() 
{
    $("#spanNoMovsShow").html(String(ii_numRowsShowed));
    $("#spanNoMovsTotal").html(String(ii_numRows));
    $("#spanNoPagShow").html(String(ii_currentPos+1));//ii_currentPag));
    $("#spanNoPagsTotal").html(String(ii_numPags));
}

function irAPaginaX() 
{
    if ($("#irpag").val() != "") 
    {
        var pag = $("#irpag").val();
        if (pag <= ii_numPags && pag > 0) 
        {
            //corregimos el numero de pagina porque inician en 0
            pag = pag - 1;
            //seteamos el contador
            var i = ii_currentPos;
            if (pag < ii_currentPos) {
                //nos movemos hacia adelante
                do {
                    $('#textAnterior').click();
                    i--;
                } while (i > pag);
             }
             else {
                //nos movemos hacia atras
                do {
                    $('#textSiguiente').click();
                    i++;
                } while (i < pag);
            }
        }
    }
    
}

function solonumeros(e) {
    var unicode = e.charCode ? e.charCode : e.keyCode;

    // if the key is backspace, tab, or numeric 
    if (unicode == 8 || unicode == 9 || (unicode >= 48 && unicode <= 57)) {
        // we allow the key press 
        return true;
    }
    else {
        // otherwise we don't 
        return false;
    }
}
