<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
    
   	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpActuacionesExpediente.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpDocumentosExpediente.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpInvolucradosExpediente.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpDiligenciasExpediente.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
      
	<style type="text/css">
	.texto{
		width: 225px; 
		border: 0; 
		background: #DDD;
	}
	.transpa {
		background-color: transparent;
		border: 0;
	}
			DD P {
				LINE-HEIGHT: 120%
			}
			#cedulaIdentificacion {
				PADDING-BOTTOM: 0px;
				PADDING-LEFT: 2px;
				WIDTH: 1030px;
				PADDING-RIGHT: 0px;
				HEIGHT: 380px;
				PADDING-TOP: 10px;
				background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
				background-repeat: no-repeat;
				border: 0px solid #000;
			}
			#cedulaIdentificacion DL {
				WIDTH: 1030px; HEIGHT: 390px
			}
			/*acordeon editar*/
			#cedulaIdentificacion DT {
				TEXT-ALIGN: right;
				PADDING-BOTTOM: 16px;
				PADDING-TOP: 2px;
				PADDING-LEFT: 0px;
				LINE-HEIGHT: 35px;
				TEXT-TRANSFORM: none;	
				/*acomodo texto*/PADDING-RIGHT: 40px;
				FONT-FAMILY: Arial, Helvetica, sans-serif;
				LETTER-SPACING: 1px;
				/*distancia persianas*/HEIGHT: 25px;
				COLOR: #f5f5f5;
				FONT-SIZE: 12px;
				FONT-WEIGHT: normal;	
				background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
				background-repeat: no-repeat;
				background-position: 28px;
			}
			#cedulaIdentificacion DT.active {
				BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
				background-repeat: no-repeat; 
				COLOR: #f5f5f5; 
				CURSOR: pointer;
				background-position: 30px;
			}
			#cedulaIdentificacion DT.hover {
				COLOR: #f5f5f5
			}
			#cedulaIdentificacion DT.hover.active {
				COLOR: #f5f5f5
			}
			#cedulaIdentificacion DD {
				BORDER-BOTTOM: #dbe9ea 0px solid; 
				BORDER-LEFT: 0px; 
				PADDING-BOTTOM: 1px; 
				PADDING-LEFT: 1px; 
				PADDING-RIGHT: 1px; 
				/*BACKGROUND: url(/nsjp-web/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom;*/ 
				BORDER-TOP: #dbe9ea 0px solid; 
				MARGIN-RIGHT: 1px; 
				BORDER-RIGHT: #dbe9ea 0px solid; 
				PADDING-TOP: 1px
			}
			/*distancia y color de numero*/
			#cedulaIdentificacion .slide-number {
				COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
			}
			#cedulaIdentificacion .active .slide-number {
				COLOR: #fff
			}
			#cedulaIdentificacion A {
				COLOR: #58595b;
				font-family: Arial, Helvetica, sans-serif;
			}
			#cedulaIdentificacion DD IMG {
				MARGIN: 0px; FLOAT: right
			}
			#cedulaIdentificacion H2 {
				MARGIN-TOP: 10px; FONT-SIZE: 2.5em
			}
			#cedulaIdentificacion .more {
				DISPLAY: block; PADDING-TOP: 10px
			}
			
				<!--ESTILOS PARA LAS TABS-->
	
	
		#tabs { height: 100% } 
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 600px; overflow: visible; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
		.tabEstilo  { height: 350px; overflow: auto; }
		
	</style>    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><tiles:getAsString name="title" /></title>	
	<script type="text/javascript">
		var contextoPagina = "${pageContext.request.contextPath}";
	    $(document).ready(function () {
        	
    	});
	</script>
</head>
<body>
	<tiles:insert attribute="body" flush="true" >
		<tiles:put name="detalles"   		value="/WEB-INF/paginas/reinsercionSocial/detalles.jsp"/>
	</tiles:insert>	
</body>
</html>