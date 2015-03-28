<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Generar integración de carpeta de investigación</title>
    
	<!--	Hojas de estilo -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<!--    Estilo para el acordeon de ingresar probable responsable-->
	<style type="text/css">
		dd p{line-height:120%}
		/*Seccion estilo acordeon Probable Responsable*/
		#iProbResponsablePane{width:1000px;height:355px;padding:1px;background:#fff;border:0px solid #b5c9e8}
		#iProbResponsablePane dl{width:1000px;height:355px}	
		#iProbResponsablePane dt{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#1C94C4}
		#iProbResponsablePane dt.inactive{height:46px;line-height:44px;text-align:right;padding:0 15px 0 0;font-size:1.1em;font-weight:bold;font-family: Tahoma, Geneva, sans-serif;text-transform:uppercase;letter-spacing:1px;background:#fff url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-inactive-1.jpg) 0 0 no-repeat;color:#E78F08}
		#iProbResponsablePane dt.active{cursor:pointer;color:#E78F08;background:#333333 url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide-title-active-1.jpg) 0 0 no-repeat}
		#iProbResponsablePane dt.hover{color:#E78F08;}
		#iProbResponsablePane dt.active.hover{color:#1C94C4}
		#iProbResponsablePane dd{padding:1px;background:url(<%= request.getContextPath() %>/images/jquery/plugins/easyaccordion/slide.jpg) bottom left repeat-x;border:1px solid #dbe9ea;border-left:0;margin-right:1px}
		#iProbResponsablePane .slide-number{color:#E78F08;left:10px;font-weight:bold}
		#iProbResponsablePane .active .slide-number{color:#fff;}
		#iProbResponsablePane a{color:#E78F08}
		#iProbResponsablePane dd img{float:right;margin:0 0 0 0px;}
		#iProbResponsablePane h2{font-size:2.5em;margin-top:10px}
		#iProbResponsablePane .more{padding-top:10px;display:block}
	</style>
      
    <!--Hoja de estilo para el texto dentro de los acordeones-->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
      
    <!--Scripts necesarios para el funcionamiento de la JSP-->
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
      
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
    
    <script type="text/javascript">
		jQuery().ready(	
		  function () {
			//creamos las tabs
			$("#tabs2").tabs();
			$("#tabsIntCarpetaInv").tabs();
			$("#tabsSolInvMin").tabs();
			$('#iProbResponsablePane').easyAccordion({ 
				  autoStart: false, 
				  slideInterval: 3000
				});
		});

        
      </script>
  </head>
  
  <body>
      <table border="0">
	      <tr valign="top">
		      <td>
		      	  <b>Crear Carpeta de unidad especializada de investigación</b>
		      </td>
	      </tr>
      </table>
      <table>
    	  <tr valign="top">
      		  <td>
	        	  <table width="100%"  border="1">
	        		  <tr valign="top">
	          			  <td>
	            			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                   			  <tr>
								      <td>Existe algún detenido: </td>
								      <td>
								          <input type="checkbox" name="chbIntCarpetaDetenido" id="chbIntCarpetaDetenido"/>
							      	  </td>
   							      </tr>
						      	  <tr>
								      <td class="seccion">
								          Casos Asignados     
								      </td>
								      <td>
								          <select id="cmbIntCarpetaCasosAsignados">
								              <option value="-1">Seleccione</option>
								          </select>
								      <td>
							      </tr>
			                  </table>
	              		  </td>
	            	  </tr>
	          	  </table>
	          </td>
    	  </tr>
    	  
      <tr valign="top">
        <td>
        <table width="100%" border="0">
          <tr valign="top">
            <td width="100%" valign="top">
              <!--  ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
                <div id="iProbResponsablePane">
                  <dl>
                    <dt id="cejaDatosGenerales">Denuncias De Atención Temprana</dt>
                      <dd>	
                        <table>
                        	<tr>
                        		<td>
                        			Informe policiaco
                        		</td>
                        		<td>
                        			<select id="cmbIntCarpetaInformePoliciaco">
						                <option value="-1">Seleccione</option>
						            </select>
                        		</td>
                        	</tr>
                        	
                        	<tr>
                        		<td>
                        			<input type="button" value="Realizar apuntes y notas" id="btnIntCarpetaApuntesNotas" class="btn_Generico"/>
                        		</td>
                        		<td>
                        			<input type="button" value="Salir" id="btnIntCarpetaDenunciasAsigSalir" class="btn_Generico"/>
                        		</td>
                        	</tr>
                        </table>
                      </dd>
                    <dt id="cejaDomicilio">Integrar Carpeta de Inv.</dt>
                      <dd>
                        <div id="tabsIntCarpetaInv">
                          <ul>
                            <li><a href="#tabsIntCarpetaInv1">Buscar carpetas</a></li>
                            <li><a href="#tabsIntCarpetaInv2">Asign. criterio de oportunidad</a></li>
                            <li><a href="#tabsIntCarpetaInv3">Sol. entradas y salidas almacén</a></li>
                            <li><a href="#">Salir</a></li>
                          </ul>
                          <div id="tabsIntCarpetaInv1">
                            <p>Tab 1.</p>
                          </div>
                          <div id="tabsIntCarpetaInv2">
                            <p>Tab 2</p>
                          </div>
                          <div id="tabsIntCarpetaInv3">
                            <p>Tab 3.</p>
                            <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
                          </div>
                        </div>
                      </dd>
                    <dt id="cejaMediaFiliacion">Crear Carpeta de Inv.</dt>
                      <dd>
                        <div id="tabs2">
                          <ul>
                            <li><a href="#tabs-21">Nunc tincidunt</a></li>
                            <li><a href="#tabs-22">Proin dolor</a></li>
                            <li><a href="#tabs-23">Aenean lacinia</a></li>
                          </ul>
                          <div id="tabs-21">
                            <p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
                          </div>
                          <div id="tabs-22">
                            <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
                          </div>
                          <div id="tabs-23">
                            <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
                            <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
                          </div>
                        </div>
                      </dd>
                      <dt id="cejaMediosContacto">Solicitar Inv. ministerial </dt>
                        <dd>
                          <div id="tabsSolInvMin">
                          <ul>
                            <li><a href="#tabsSolInvMin1">Designar Archivo temporal</a></li>
                            <li><a href="#tabsSolInvMin2">Realiza inv. periciales</a></li>
                            <li><a href="#tabsSolInvMin3">Realizar valoración de hecho</a></li>
                            <li><a href="#">Salir</a></li>
                          </ul>
                          <div id="tabsSolInvMin1">
                            <p>Tab 1.</p>
                          </div>
                          <div id="tabsSolInvMin2">
                            <p>Tab 2</p>
                          </div>
                          <div id="tabsSolInvMin3">
                            <p>Tab 3.</p>
                            <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
                          </div>
                        </div>
                        </dd>
                      
                      </dl>
                    </div>
                    <!-- FIN ACORDEON CU PROBABLE RESPONSABLE-INGRESAR ORGANIZACION  -->
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
  </body>
</html>