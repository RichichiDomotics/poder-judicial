<%-- 
    Document   : consultarDetalleNotificaciones
    Created on : 19-jul-2011, 11:20:33
    Author     : sawbona
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Consultar Notifiaciones</title>
        <!--Hoja de estilos de Layout-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
        <script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
        <script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
        <!--script de windows engine (frames)-->
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
        <!--Hoja de estilos windows engine (frames)-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
        <script type="text/javascript" 	src="<%=request.getContextPath()%>/js/comun.js"></script>
        <script type="text/javascript" 	src="<%=request.getContextPath()%>/js/consultarDetalleNotificaciones.js"></script>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
        
        <script type="text/javascript">
            var CONTEXT_ROOT = '<%= request.getContextPath()%>'; //nsjp-web
        </script>
    </head>
    <body id="bodyDetalle">
        <div id="tabprincipal">
            <ul>
                <li><a href="#detalleNotificacion">Detalle de la notificación</a></li>
            </ul>
            <div id="detalleNotificacion">
                <table cellspacing="5">
                    <tr>
                        <td>
                           Número de notificacion:
                        </td>
                        <td id="consecutivoNotificacion" class="notificacion">

                        </td>
                    </tr>
                    <tr>
                        <td>
                           Lugar:
                        </td>
                        <td id="lugar" class="notificacion">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Motivo:
                        </td>
                        <td id="motivo" class="notificacion">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Domicilio:
                        </td>
                        <td id="domicilio" class="notificacion">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Lugar citado:
                        </td>
                        <td id="lugarCitado" class="notificacion">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Fecha citado:
                        </td>
                        <td id="fechaCitado" class="notificacion">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Penalidades:
                        </td>
                        <td id="penalidades" class="notificacion">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Autoriza:
                        </td>
                        <td id="personaAutoriza" class="notificacion">

                        </td>
                        <td>
                            Puesto:
                        </td>
                        <td id="puestoAutoriza" class="notificacion">

                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
