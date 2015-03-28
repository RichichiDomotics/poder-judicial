<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript">
	
	/*
	 *Nombre del Programa : ingresarMediaFiliacion.jsp                                    
	 *Autor               : Cuauhtemoc Paredes Serrano                                              
	 *Compania            : Ultrasist                                                
	 *Proyecto            : NSJP                    Fecha: 01/03/2010 
	 *Marca de cambio     : N/A                                                     
	 *Descripcion General : Mostrar campos a ser llenados por el usuario                      
	 *Programa Dependiente :N/A                                                      
	 *Programa Subsecuente :N/A                                                      
	 *Cond. de ejecucion   :N/A                                                      
	 *Dias de ejecucion    :N/A                     Horario: N/A       
	 */
	
	
	
		$(document).ready(function(){			
			cargaCombo();
		});

   
	   function cargaCombo() {
	    	 
		  $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/cargaCombosMediaFiliacion.do',
	    	  data: '',
	    	  dataType: 'xml',
	    	  success: function(xml){
		    	 
	    		  $(xml).find('CatColorCabello').each(function(){
						$('#colCabello').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatColorOjos').each(function(){
						
						$('#colOjos').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatComplexion').each(function(){
						
						$('#comple').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaBoca').each(function(){
						
						$('#formBoca').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaCabello').each(function(){
						
						$('#formCabello').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaCara').each(function(){
						
						$('#formCara').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaCejas').each(function(){
						
						$('#formCeja').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaFrente').each(function(){
						
						$('#formFrente').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaMenton').each(function(){
						
						$('#formMenton').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaNariz').each(function(){
						
						$('#formNariz').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaOjos').each(function(){
						
						$('#formOjos').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatFormaOreja').each(function(){
						
						$('#formOrejas').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatGrosorCejas').each(function(){
						
						$('#grosCeja').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatTez').each(function(){
						
						$('#optez').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatTipoCara').each(function(){
						
						$('#tipCara').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    		  $(xml).find('CatTipoCabello').each(function(){
						
						$('#tipCabello').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcNombre').text() + '</option>');
					   });
	    	  }
	    	});
		 
		}
  	    
	    function guardarFiliacion() {
	    	var barba=document.getElementById("barba").value;
			var bigote=document.getElementById("bigote").value;
			var estatura=document.getElementById("estatura").value;
			var lentes=document.getElementById("lentes").value;
			var perfil=document.getElementById("perfil").value;
			var peso=document.getElementById("peso").value;
		    
		  $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/ingresarDatos.do',
	    	  data: 'barba='+barba +'&bigote='+bigote,  
	    	  dataType: 'xml'
	    	  //success: function(xml){
	    		//  var option;
	    		 // $(xml).find('ojos').each(function(){
				//	var index = $('#colOjos option').length + 1;
				//	$('#colOjos').append('<option value="' + $(this).find('idMediaFiliacion').text() + '">' + $(this).find('nombreMediaFiliacion').text() + '</option>');
			//	   });
	    	//  }
	    	});
		}
		
	</script>
	<title>Ingresar Media Filiacion</title>
	
	
	<!-- CSS INTERNA PARA EL ELEMENTO TITULO Y BODY -->
	<style type="text/css">
.Titulo {
	font-size: 18px;
	font-family: Arial, Helvetica, sans-serif;
	color: #FFF;
	background: #666;
}

.body {
	margin-top: 0px;
	margin-bottom: 0px;
	background-color: #EEE;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
</style>


	</head>

	<body >
	<table bgcolor="#CCCCCC" width="900" border="1" cellspacing="0" cellpadding="0" class="body">
				<tr>
					<td colspan="8" align="center" bgcolor="#000000" class="Titulo">INGRESAR
					MEDIA FILIACION</td>
				</tr>
				<tr>
					<td >Barba:</td>
					<td >Si<input  type="radio" id="barbasi" name="barba">
						 No<input  type="radio" id="barbano"  name="barba">
					<td >Bigote:</td>
					<td >Si<input  type="radio" id="bigotesi" name="bigote">
						 No<input  type="radio" id="bigoteno"  name="bigote">
					<td >Lentes:</td>
					<td >Si<input  type="radio" id="lentessi" name="lentes">
						 No<input  type="radio" id="lentesno"  name="lentes"></td>
					
				</tr>
				<tr>

					
					<td >Estatura:</td>
					<td ><input type="text" id="lentes" size="14"></td>
					<td>Perfil:</td>
					<td><input type="text" id="perfil" size="14"></td>
					<td >Peso:</td>
					<td ><input type="text" id="peso" size="14"></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td width="160">Color de Cabello </td>
					<td width="100"><select name="Color Cabello" id="colCabello">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td width="160">Color de Ojos				    </td>
					<td width="100"><select name="Color Ojos" id="colOjos">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td width="160">Complexion				    </td>
					<td width="100"><select name="Complexion" id="comple">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td width="160">Forma de la Boca </td>
					<td width="100"><select name="Forma Boca" id="formBoca">
					<option>-Seleccione-
					</option>
				    </select></td>
				</tr>
				<tr>
					<td>Forma del Cabello</td>
					<td><select name="Forma del Cabello" id="formCabello">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Forma de la Cara</td>
					<td><select name="Forma Cara" id="formCara">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Forma de la Ceja </td>
					<td><select name="Forma Ceja" id="formCeja">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Forma de la Frente </td>
					<td><select name="Forma frente" id="formFrente">
					<option>-Seleccione-
					</option>
				    </select></td>
				</tr>
				<tr>
					<td>Forma del Menton				    </td>
					<td><select name="Forma Menton" id="formMenton">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Forma de la Nariz </td>
					<td><select name="Forma Nariz" id="formNariz">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Forma de los Ojos </td>
					<td><select name="Forma Ojos" id="formOjos">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Forma de las Orejas</td>
					<td><select name="Forma Orejas" id="formOrejas">
					<option>-Seleccione-
					</option>
				    </select></td>
				</tr>
				<tr>
					<td>Grosor de cejas </td>
					<td><select name="Grosor ceja" id="grosCeja">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Tez </td>
					<td><select name="Tez4" id="optez">
					<option>-Seleccione-
					</option>
				    </select></td>
					
					<td>Tipo de Cara				    </td>
					<td><select name="Tipo cara" id="tipCara">
					<option>-Seleccione-
					</option>
				    </select></td>
					<td>Tipo de Cabello				    </td>
					<td><select name="Tipo Cabello" id="tipCabello">
					<option>-Seleccione-
					</option>
				    </select></td>
				</tr>
				<tr>
					<td colspan="2"></td>
					<td align="center" colspan="4"><button value="Guardar" class="btn_Generico">Guardar</button></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				
			</table>
	</body>
	
	