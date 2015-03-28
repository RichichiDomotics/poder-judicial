<%@page
    import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
             pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
        <head>
            <link type="text/css" rel="stylesheet"
                  href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/resources/css/layout_complex.css"
                  media="screen" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/resources/css/estilos.css"
                  media="screen" />
            <link rel="stylesheet" type="text/css" media="screen"
                  href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />

            <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
            <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>

            <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
            <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/layout_complex.js"></script>
            <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
            <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
            <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>

            <style>
                .transpa {
                    background-color: transparent;
                    border: 0;
                }
            </style>

            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

			<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
			
            <script type="text/javascript">
                var CONTEXT_ROOT = '<%= request.getContextPath()%>'; //nsjp-web
                var idExpediente ="";
                var reloadGrid=false;

                $(document).ready(function() {
                    gridSolicitud();
                    cargaCatalogo("/consultarCatalogoTipoBajaEvidencia", "tipoDeBaja", "evidencia");
                });

                $("#confirmaSolicitud").dialog("open");

                function confirmaSolicitud(){
                    // Obtenemos los parametros del jsp
                    var parametros = obtenParametros("parametro");
                    // Indicamos los parametros requeridos y los valores que
                    // no pueden adquirir del jsp
                    var requeridos = {tipoDeBaja:"-1",
                        nombre:"",
                        apellidoPaterno:"",
                        apellidoMaterno:"",
                        institucion:"",
                        puesto:""};
                    // Validamos que los parametros requeridos hayan sido
                    // capturados
                    var faltantes = validaParametrosRequeridos(parametros, requeridos);
                    if (faltantes.length > 0) {
//                        alert(faltantes);
                        // Si hay parametros faltantes lo indicamos
                        $("#mensajeValidacion").dialog({ autoOpen: true,
                            modal: true,
                            title: 'Atención',
                            dialogClass: 'alert',
                            position: [200,30],
                            width: 230,
                            height: 110,
                            maxWidth: 500,
                            buttons:{"Ok":function() {
                                    $(this).dialog("close");
                                }
                            }
                        });
                    } else {
                        // Si no hay parametros faltantes verificamos que los
                        // datos de funcionario que fueron capturados
                        // correspondan con un funcionario real en la base
                        ejecutaAction("/existeFuncionarioPorNombreInstitucionYPuesto",
                        function(respuesta){
                            var existe = $(respuesta).find("body").text();
                            // Si la respuesta es true
                            if (existe == "true") {
                                // le pedimos una confirmacion al usuario para
                                // dar de baja las evidencias
                                var evidenciasId = obtenParametrosDeUrlComoCadena("evidenciaId");
                                parametros += "&" + evidenciasId;
                                $("#confirmaSolicitud").dialog({ autoOpen: true,
                                    modal: true,
                                    title: 'Confirmación',
                                    dialogClass: 'alert',
                                    position: [200,30],
                                    width: 412,
                                    height: 120,
                                    maxWidth: 500,
                                    buttons:{"Dar de baja":function() {
                                            // Si el usuario confirma la baja
                                            // procedemos
                                            darDeBajaEvidencias(parametros, $(this));
                                        },
                                        "Cancelar":function() {
                                            // Si no, cerrados el dialogo
                                            $(this).dialog("close");
                                        }
                                    }
                                });
                            }else{
                                // Si no existe el funcionario mostramos un
                                // mensajeFuncionarioInexistente
                                // mensaje informando al usuario.
                                $("#mensajeFuncionarioInexistente").dialog({ autoOpen: true,
                                    modal: true,
                                    title: 'Atención',
                                    dialogClass: 'alert',
                                    position: [200,30],
                                    width: 412,
                                    height: 120,
                                    maxWidth: 500,
                                    buttons:{"Ok":function() {
                                            // Si no, cerrados el dialogo
                                            $(this).dialog("close");
                                        }
                                    }
                                });
                            }
                        },
                        parametros);
                    }
                }

                /**
                * Funcion para mandar que manda a llamar el action que registra
                * la baja de evidencias.
                */
                function darDeBajaEvidencias(parametros, ventana){
//                    alert(parametros);
                    ejecutaAction("/registrarBajaEvidencia",
                    function(respuesta){
                        ventana.dialog("close");
                        $("#mensajeExistoBaja").dialog({ autoOpen: true,
                            modal: true,
                            title: 'Atención',
                            dialogClass: 'alert',
                            position: [200,30],
                            width: 412,
                            height: 120,
                            maxWidth: 500,
                            buttons:{"Ok":function() {
                                    $(this).dialog("close");
                                }
                            }
                        });
                        $("#botonGuardaBajaEvidencia").attr("disabled", true);
                    },
                    parametros);
                }

                function gridSolicitud(){
                    // Llenamos el folio de la cadena:
                    var folio = obtenParametroDeUrl("folioCadenaDeCustodia");
                    $("#folioCadenaCustodia").val(folio);
                    $("#totalEvidencias").val(obtenParametrosDeUrlComoArreglo("evidenciaId").length);
                    // Hacemos el llenado de las evidencias seleccionadas por el
                    // usuario
                    var evidenciasId = window.location.href + "";
                    evidenciasId = evidenciasId.substring(evidenciasId.indexOf("?", 0) + 1, evidenciasId.length);
                    jQuery("#gridEvidencias").jqGrid({
                        url: CONTEXT_ROOT + '/consultarEvidenciasEnAlmacen.do',
                        datatype: "xml",
                        /**
                         * id
                         * Numero evidencia
                         * Informacion Evidencia
                         * Origen Evidencia
                         * Ultimo eslabon asociado
                         * Numero de eslabon
                         * Tipo de eslabon
                         * Almacen
                         */
                        colNames:['id','Número','Información', 'Origen', 'Último eslabón asociado', 'Número de eslabón', 'Tipo de eslabón', 'Almacén'],
                        colModel:[
                            {name:'id',index:'id', width:75, viewable:false, key:true, hidden:true},
                            {name:'numero',index:'numero', sorteable:false, align:"center"},
                            {name:'informacion',index:'informacion', sorteable:false},
                            {name:'origen',index:'origen', sortable:false},
                            {name:'ultimoEslabonAsociado',index:'ultimoEslabonAsociado', sortable:false, align:"center"},
                            {name:'numeroEslabon',index:'numeroEslabon', sortable:false, align:"center"},
                            {name:'tipoEslabon',index:'tipoEslabon', align:"right", sorteable:false, align:"center"},
                            {name:'almacen',index:'almacen', align:"right", sorteable:false, align:"center"},
                        ],
                        rowNum:10,
                        autowidth: true,
                        rowList:[10,20,30],
                        pager: jQuery('#pagerGridEvidencias'),
                        sortname: 'numero',
                        viewrecords: true,
                        sortorder: "desc",
                        postData:evidenciasId,
                        caption:"Evidencias por retirar"
                    }).navGrid('#pagerGridEvidencias',{edit:false,add:false,del:false});
                    $("#wrapGriEvidencias").css("display", "inherit");
                }
            </script>
        </head>
        <body>
            <div id="confirmaSolicitud" style="display: none">
                ¿Desea dar de baja las evidencias o indicios seleccionados?
            </div>
            <div id="mensajeValidacion" style="display: none">
                Por favor llene los campos requeridos
            </div>
            <div id="mensajeFuncionarioInexistente" style="display: none">
                No existe un funcionario que corresponda con la información capturada
            </div>
            <div id="mensajeExistoBaja" style="display: none">
                Las evidencias han sido dadas de baja correctamente
            </div>
            <table>
                <tr class="fondoFuerteAP">
                    <td style="color: white" colspan="2" align="center">
                        Información de la evidencia
                    </td>
                </tr>
                <tr>
                    <td align="left">Folio de cadena de custodia</td>
                    <td align="right"><input type="text" id="folioCadenaCustodia" readonly="true" class="parametro"></td>
                </tr>
                <tr>
                    <td align="left">Total de Evidencias</td>
                    <td align="right"><input type="text" id="totalEvidencias" readonly="true"></td>
                </tr>
                <tr>
                    <td align="left">
                        Tipo de baja de la evidencia*
                    </td>
                    <td align="right">
                        <select id="tipoDeBaja" class="parametro">
                            <option value="-1">--Seleccione una opción--</option>
                        </select>
                    </td>
                </tr>
            </table>
            <table>
                <tr class="fondoFuerteAP">
                    <td style="color: white" colspan="2" align="center">
                        Información del personal que autoriza la baja del indicio o evidencia
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        Apellido paterno*
                    </td>
                    <td align="right">
                        <input type="text" id="apellidoPaterno" class="parametro" size="60">
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        Apellido materno*
                    </td>
                    <td align="right">
                        <input type="text" id="apellidoMaterno" class="parametro" size="60">
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        Nombre(s)*
                    </td>
                    <td align="right">
                        <input type="text" id="nombre" class="parametro" size="60">
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        Institución*
                    </td>
                    <td align="right">
                        <input type="text" id="institucion" class="parametro" size="60">
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        Puesto*
                    </td>
                    <td align="right">
                        <input type="text" id="puesto" class="parametro" size="60">
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        Observaciones de baja de la evidencia
                    </td>
                    <td>
                        <textarea cols="" rows="8" id="observaciones" class="parametro" style="width: 100%"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="button" id="botonGuardaBajaEvidencia" value="Guardar" onclick="confirmaSolicitud();" class="btn_Generico"/>
                    </td>
                </tr>
            </table>
            <div id="nabtabgrid" >
                <div id="wrapGriEvidencias" style="display:none">
                    <table id="gridEvidencias" align="center"></table>
                    <div id="pagerGridEvidencias"></div>
                </div>
            </div>
        </body>
    </html>