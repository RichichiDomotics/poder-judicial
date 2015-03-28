<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<%@page import="mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO"%>
<%@page import="mx.gob.segob.nsjp.web.login.action.LoginAction"%>


<div class="ui-layout-east">
	<div class="header">Bienvenido</div>
	<div class="content">
		<div id="accordionmenuderprincipal">
			<h4>
				<a href="javascript:void(0)">Datos Personales</a>
			</h4>
			<div>
				<center>
					<jsp:include page="/WEB-INF/paginas/datosPersonalesUsuario.jsp" flush="true"></jsp:include>
				</center>
			</div>
			<!-- <h4>
				<a href="javascript:void(0)">Calendario</a>
			</h4>
			<div>
				<center>
					<a href="javascript:void(0)"><img src="<%=request.getContextPath()%>/resources/images/img_calendario.png" width="201" height="318"> </a>
				</center>
			</div>-->
			<h6>
				<a href="javascript:void(0)">Agenda</a>
			</h6>
			<div>
				<center>
					<jsp:include page="/WEB-INF/paginas/agendaUsuario.jsp" flush="true"></jsp:include>
				</center>
				<br />

			</div>
			<h6>
				<a href="javascript:void(0)" id="" onclick="visorLeyesCodigos()">Consultar Leyes y C&oacute;digos</a>
			</h6>
			<div></div>
			<h1>
				<a href="javascript:void(0)">Chat</a>
			</h1>
			<div>
				<div id="dialogoChat" title="Chat" align="center">
					<iframe	src="<%=((ConfiguracionDTO) session.getAttribute(LoginAction.KEY_SESSION_CONFIGURACION_GLOBAL)).getUrlServidorChat()%>" frameborder="0" width="380" height="280">
					</iframe>
				</div>
				<center>
					<a onclick="ejecutaChat();" id="controlChat"><img src="<%=request.getContextPath()%>/resources/images/img_chat.png" width="130" height="104"> </a>
				</center>
			</div>
			<h1>
				<a href="javascript:void(0)">Clima</a>
			</h1>
			<div align="left">
				<div align="left" id="test"></div>
			</div>
		</div>
	</div>
	<!-- div class="footer">&nbsp;</div -->
</div>
