<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar individuo</title>

	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/ingresarIndividuo.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modificarProbableResponsable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
		
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
		cargarCmbCalidad();
		$('#cmbCalidad').attr('selectedIndex',1);
	    $("#cmbCalidad").change(cambiaTabCalidad);
	    /*Inician llamados para creación de tabs*/
	    creaTab('Calidad','Calidad');
	    inhabilitaTabs('Calidad','Calidad');
	    creaTab('Denunciante','Denunciante');
	    creaTab('RLegal','RLegal');
	    creaTab('Victima','Victima');
	    creaTab('Testigo','Testigo');
	    creaTab('PResponsable','PResponsable');
	    creaTab('COrganizacional','COrganizacional');
	    creaTab('Tutor','Tutor');
	    creaTab('Traductor','Traductor');
	});

	/**
	* Función que cambia el tab principal a nivel de calidad del Individuo dependiendo la selección de cmbCalidad
	*/
	function cambiaTabCalidad(){
		habilitaTabs('Calidad','Calidad');
		var tabs = $('div.tabsCalidad ul.tabNavigationCalidad a');
		var seleccion = document.getElementById('cmbCalidad').selectedIndex;
		if(seleccion==1){
			tabs.filter('#tabDenunciante').click();
		}else if(seleccion==2){
			tabs.filter('#tabRLegal').click();
		}else if(seleccion==3){
			tabs.filter('#tabVictima').click();
			//cargarPestaniaVictima();
		}else if(seleccion==4){
			tabs.filter('#tabTestigo').click();
		}else if(seleccion==5){
			tabs.filter('#tabPResponsable').click();
			cargarPestaniaProbableResponsable();
		}else if(seleccion==6){
			tabs.filter('#tabCOrganizacional').click();
		}else if(seleccion==7){
			tabs.filter('#tabTutor').click();
			cargarPestaniaTutor();
		}else if(seleccion==8){
			tabs.filter('#tabTraductor').click();
		}
		inhabilitaTabs('Calidad','Calidad');
	}

	/**
	* función que carga los controles iniciales de la pestania de Probable Responsable
	*/
	function cargarPestaniaProbableResponsable(){
		cargarRbtCondicion('<%= request.getContextPath()%>');
		$("#chbProbResponsableEstaDetenido").bind("change",function(){
			if($(this).attr("checked")){
				$("#rbtProbResponsablePersonaMoral").attr({checked: false, disabled:true});
				$("#rbtProbResponsablePersonaFisica").attr({checked: true});
			} else{
				$("#rbtProbResponsablePersonaMoral").attr({checked: false, disabled:false});
				$("#rbtProbResponsablePersonaFisica").attr({checked: false, disabled:false});
			}
		});
		simulaLlenado();
	}

	/**
	* función que simula el llenado do los controles iniciales de la pestania de Probable Responsable
	*/
	function simulaLlenado(){
		$('#chbProbResponsableEstaDetenido').attr({checked: true});
		$('#chbProbResponsableMayorDeEdad').attr({checked: true});
		$("#rbtProbResponsablePersonaMoral").attr({checked: false, disabled:true});
		$('#rbtProbResponsablePersonaFisica').attr({checked: true});
		var a = $('#tdProbResponsableCondicion >:radio');
		var conta = parseInt(0);
		a.each(function(){
			if(conta==1){
				$(this).attr('checked',true);
			}
			conta = conta+ 1;
		});
	}
	
	/**
	* Función que carga el combo de calidad
	*/
	function cargarCmbCalidad() {
	    $.ajax({
	    	  type: 'POST',
	    	  url: '<%= request.getContextPath()%>/CargarIndividuo.do',
	    	  data: '',
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    	      $(xml).find('calidadDTO').each(function(){
				      $('#cmbCalidad').append('<option value="' + $(this).find('glCatalogoId').text() + '">' + $(this).find('gcDescripcion').text() + '</option>');
				  });
	    	  }
	    });
	}

	
	</script>
</head>
<body >

	<h2>Modificar individuo</h2>

	<table>
		<tbody>
			<tr>
				<td>Calidad:</td>
				<td>
					<select id="cmbCalidad">						
						<option value="-1">-Seleccione-</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<br/><br/>
	
	<div class="tabsCalidad">
		<ul class="tabNavigationCalidad">
			<li><a id="tabDenunciante" href="#denunciante">Denunciante</a></li>
			<li><a id="tabRLegal" href="#rLegal">Representante Legal</a></li>
			<li><a id="tabVictima" href="#victima">Víctima</a></li>
			<li><a id="tabTestigo" href="#testigo">Testigo</a></li>
			<li><a id="tabPResponsable" href="#pResponsable"><bean:message key="probableResponsable"/></a></li>
			<li><a id="tabCOrganizacional" href="#cOrganizacional">Contacto Organizacional</a></li>
			<li><a id="tabTutor" href="#tutor">Tutor</a></li>
			<li><a id="tabTraductor" href="#traductor">Traductor</a></li>
		</ul>
		<div id="denunciante">
			<h2>Denunciante</h2>
			<br/>
		</div>
		<div id="rLegal">
			<h2>Representante Legal</h2>
			<br/>
		</div>
		<div id="victima">
			<h2>Víctima</h2>
			<br/>
		</div>
		<div id="testigo">
			<h2>Testigo</h2>
			<br/>
		</div>
		<div id="pResponsable">
			<h2><bean:message key="probableResponsable"/></h2>
			<br/>
			<jsp:include page="modificarProbableResponsable.jsp" />
		</div>
		<div id="cOrganizacional">
			<h2>Contacto Organizacional</h2>
			<br/>
		</div>
		<div id="tutor">
			<h2>Tutor</h2>
			<br/>
		</div>
		<div id="traductor">
			<h2>Traductor</h2>
			<br/>
		</div>
	</div>
	<br/>
	<input type="button" value="Guardar" class="btn_Generico"/>
</body>
</html>