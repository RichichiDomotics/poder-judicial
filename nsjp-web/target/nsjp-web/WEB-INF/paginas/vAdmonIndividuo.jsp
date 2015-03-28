<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/js/jquery-1.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery-ui.css" />
<script src="<%= request.getContextPath()%>/js/jquery.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>



<script type="text/javascript">

   $(document).ready(function(){
	   //alert("ready");
	   	   cargaOcupacion();
	   cargaEstadoCivil();
	   cargaIdioma();	
	   cargaNacionalidad();
	   cargaEscolaridad();

	   cargaReligion();
	 
	   
		});
		
		//btnGenerarFicha btnIdentificarElem rdbIdentificarElem rdbGenerarFicha
		
	/*
	*Funcion que habilita los radio buttons
	*/
	
		
	/*
	*Funcion que dispara el Action para consultar el Estado Civil
	*/		
    function cargaEstadoCivil(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarListaEstadoCivil.do',
    		data: '',
    		dataType: 'xml',
    		success: function(xml){
    			var option;
    			$(xml).find('estadocivil').each(function(){
    				$('#estadoCivil').append('<option value="' + $(this).find('gcNombre').text() + '">' + $(this).find('gcDescripcion').text() + '</option>');
    			});
    		}
    	});
    }
		
		
    function cargaOcupacion(){
    	//alert("cargaOcupacion");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoOcupacion.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('ocupacion').each(function(){
    			$('#ocupacion').append('<option value="' + $(this).find('gcNombre').text() + '">'+ $(this).find('gcDescripcion').text() + '</option>');
    			});
    		}
    	});
    }

	/*
	*Funcion que dispara el Action para consultar el Idioma
	*/		
    function cargaIdioma(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoIdioma.do',
    		data: '',
    		dataType: 'xml',
    		success: function(xml){
    			var option;
    			$(xml).find('idioma').each(function(){
    				$('#lengua').append('<option value="' + $(this).find('gcNombre').text() + '">'+ $(this).find('gcDescripcion').text() + '</option>');
    			});
    		}
    	});
    }

    /*
	*Funcion que dispara el Action para consultar Nacionalidad
	*/		
    function cargaNacionalidad(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoNacionalidad.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			$(xml).find('nacionalidad').each(function(){
    				$('#nacionalidad').append('<option value="' + $(this).find('gcNombre').text() + '">'+ $(this).find('gcDescripcion').text() + '</option>');
    			});
    		}
    	});
    }
    
    function cargaEscolaridad(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoEscolaridad.do',
    		data: '',
    		dataType: 'xml',
    		success: function(xml){
    			var option;
    			$(xml).find('escolaridad').each(function(){
    				$('#escolaridad').append('<option value="' + $(this).find('gcNombre').text() + '">'+ $(this).find('gcDescripcion').text() + '</option>');
    			});
    		}
    	});
    } 
	  
   
    
    function cargaReligion(){
    	//alert("cargarDefensor");
    	$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/ConsultarCatalogoReligion.do',
    		data: '',
    		dataType: 'xml',
    		success: function(xml){
    			var option;
    			$(xml).find('religion').each(function(){
    				$('#religion').append('<option value="' + $(this).find('gcNombre').text() + '">'+ $(this).find('gcDescripcion').text() + '</option>');
    			});
    		}
    	});
    }
    

   
    //id="test" onload="prettyPrint();"
</script>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload=" prettyPrint();">
 
    <table width="734" border="0" align="center">
  <tr>
    <td colspan="2"  bgcolor="#9999FF" align="center">Datos generales:</td>
    </tr>

</table>

    
    
    
<table width="734" border="0" align="center" id="tdatospersonales">
    
      <tr>
    <td width="112">Nombre(s):</td>
    <td width="120"><input type = "text" name = "nombre12" size = "20" id=nombre /></td>
    <td width="122">Apellido paterno:</td>
    <td width="120"><input type = "text" name = "nombre13" size = "20" id=apPaterno/></td>
    <td width="122">Apellido materno:</td>
    <td width="120"><input type = "text" name = "nombre14" size = "20" id=apMaterno/></td>
  </tr>
  <tr>
    <td>Edad: </td>
    <td><input type = "text" name = "nombre15" size = "20" /></td>
    <td>Ocupación:</td>
    <td> <form >
        <select name="ocupa" multiple="multiple"  id="ocupacion" ondblclick="alert(this.value)" >
        </select>
         </form></td>
    <td>Sexo:</td>
    <td><select name="select4" id="sexo">
       <option selected="selected">- selecciona -</option>
        <option value="Femenino">femenino</option>
        <option value="Masculino">Masculino</option>
         
       
    </select></td>
  </tr>
  
  <tr>
  <td  >Estado civil</td>
    <td ><select name="select3" id="estadoCivil">
    <option selected="selected">- selecciona -</option>
      
    </select></td>
    <td>Nacionalidad:</td>
    <td>
      <form>
	
        <select name="nacion" multiple="multiple" size="5" id="nacionalidad" >
        </select>
        
        </form>
    </td>
    <td>RFC(opcional):</td>
    <td><input type = "text" name = "nombre2" size = "20" /></td>
  </tr>
  <tr>
    <td  >CURP(opcional):</td>
    <td ><input type = "text" name = "nombre3" size = "20" /></td>
     <td>Lengua </td>
    <td>
      
        <select name="select5" id="lengua">
        <option selected="selected">- selecciona -</option>
        </select>
  </td>
    <td>Escolaridad</td>
    <td><form id="form1" name="form1" method="post" action="">
      
        <select name="select5" id="escolaridad">
        <option selected="selected">- selecciona -</option>
        </select>
    </form></td>
  </tr>
     <tr>
    <td>Religión</td>
    <td><form id="form1" name="form1" method="post" action="">
      
        <select name="select5" id="religion">
        <option selected="selected">- selecciona -</option>
        </select>
    </form></td>
     <td></td>
    <td></td>
    <td></td>
    <td>
     
   </td>
  
    </tr>
  <tr>
    <td colspan="6" align="center"   bgcolor="#9999FF">Fecha de Nacimiento:</td>
  </tr>
  <tr>
    <td>Día:</td>
    <td><select name="select6" id="dia">

       <option>- Selecciona -</option>
    </select></td>
    <td>Mes: </td>
    <td><select name="select" id="mes">
       <option>- Selecciona -</option>
    </select></td>
    <td>Año:</td>
    <td><select name="año" id="anio">
     
      <option>- Selecciona -</option>
    </select></td>
  </tr> 
  </table>
<script type="text/javascript">
$(function(){
	$("#nacionalidad").multiselect();
	$("#ocupacion").multiselect();
	
});
</script>
</body>
</html>