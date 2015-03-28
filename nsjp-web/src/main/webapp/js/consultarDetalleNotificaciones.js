$(document).ready(function() {
    //tabprincipal
    var idNotificacion = obtenParametroDeUrl("idNotificacion");
    $("#tabprincipal").tabs();
    ejecutaAction("/consultarNotificacion", function(notificacion){
        var camposEnJsp = $(".notificacion");
        llenaCamposEnJspConObjetoXML(camposEnJsp, notificacion);
    }, "idNotificacion=" + idNotificacion)
});