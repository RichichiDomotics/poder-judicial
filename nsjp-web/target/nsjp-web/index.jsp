<html>
	<head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fakeLoader.css">
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script>
            /*window.onload = detectarCarga;
            function detectarCarga(){
                document.getElementById("imgLOAD").style.display="none";
            }*/
            var ventanaPrincipal;
            function loadVentanaPrincipal() {
                //ventanaPrincipal=window.open('<%= request.getContextPath() %>/cargarLogin.do','ventanaPrincipal','toolbar=no,location=no,directories=no,status=no, menubar=no,scrollbars=no,resizable=no,width=' + window.screen.width + ',height=' + window.screen.height);
                ventanaPrincipal=window.open('<%= request.getContextPath() %>/cargarLogin.do','_self');
            }
		</script>
        <style>
            #imgLOAD {
                position: absolute;
                top: 50%;
                left: 50%;
                margin: -99px 0 0 -66px;
            }
            .waiting-circles{ padding: 0; display: inline-block;
                position: relative; width: 60px; height: 60px;}
            .waiting-circles-element{ margin: 0 2px 0 0; background-color: #e4e4e4;
                border: solid 1px #f4f4f4;
                width: 10px; height: 10px; display: inline-block;
                -moz-border-radius: 4px; -webkit-border-radius: 4px; border-radius: 4px;}
            .waiting-circles-play-0{ background-color: #9EC45F; }
            .waiting-circles-play-1{ background-color: #aEd46F; }
            .waiting-circles-play-2{ background-color: #bEe47F; }
        </style>
	</head>
<body onLoad="javascript:loadVentanaPrincipal();">
<!--Cargando Aplicacion .....-->
<div id="imgLOAD" style="text-align:center;">
    <img src="<%=request.getContextPath()%>/resources/images/logo_nsjph.jpg" />
    <div id="waiting4"></div>
    <br>
    <b>Cargando...</b>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-waiting.js"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#waiting4').waiting({
                className: 'waiting-circles',
                elements: 8,
                radius: 20,
                auto: true
            });
        });
    </script>
</div>
</body>
</html>
 