<%@page import="java.util.Date"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>


<script type="text/javascript">
<% Long noCache = new Date().getTime();%>
	
    $(document).ready(
    	function(){

			$( "#tabsContenido<%=noCache%>" ).tabs();
			$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
				.removeClass( "ui-corner-all ui-corner-top" )
				.addClass( "ui-corner-bottom" );
			}
	);
    
    
</script>

<div id="tabsContenido<%=noCache%>" class="tabs-bottom" style="width: 500px; height: 300px;">
	<ul>
		<li><a href="#tabs-1">Bandeja de Entrada</a></li>
		<li><a href="#tabs-2">Bandeja de Salida</a></li>
		<li><a href="#tabs-3">Actuaciones</a></li>
	</ul>
	<div id="tabs-1">
		<fieldset>
			<legend>Recibidos:</legend>
			<select name="selectRecibidos<%=noCache %>" id="selectRecibidos<%=noCache %>"  style="width: 100%; height: 100%;" size="10">
			</select>
		</fieldset>
	</div>
	<div id="tabs-2">
		<fieldset>
			<legend>Enviados:</legend>
			<select name="selectEnviados<%=noCache %>" id="selectEnviados<%=noCache %>"  style="width: 100%; height: 100%;" size="10">
			</select>
		</fieldset>
	</div>
	<div id="tabs-3">
		<fieldset>
			<legend>Actuaciones:</legend>
			<select name="selectActuaciones<%=noCache %>" id="selectActuaciones<%=noCache %>"  style="width: 100%; height: 100%;" size="10">
			</select>
		</fieldset>	</div>
</div>
