<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>Agenda</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/dailog.css" rel="stylesheet" type="text/css" />
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/calendar.css" rel="stylesheet" type="text/css" /> 
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/dp.css" rel="stylesheet" type="text/css" />   
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/alert.css" rel="stylesheet" type="text/css" /> 
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/main.css" rel="stylesheet" type="text/css" /> 
    

    <script src="<%= request.getContextPath() %>/resources/js/jquery-1.5.1.js" type="text/javascript"></script>  
    
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/Common.js" type="text/javascript"></script>    
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/datepicker_lang_US.js" type="text/javascript"></script>     
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.datepicker.js" type="text/javascript"></script>

    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.alert.js" type="text/javascript"></script>    
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.ifrmdailog.js" defer="defer" type="text/javascript"></script>
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/wdCalendar_lang_US.js" type="text/javascript"></script>    
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.calendar.js" type="text/javascript"></script>   
    
    <script type="text/javascript">
        $(document).ready(function() {     
           var view="week";          
           
            var DATA_BASE_URL = "<%= request.getContextPath() %>";
            var op = {
                view: view,
                theme:3,
                showday: new Date(),
                EditCmdhandler:Edit,
                DeleteCmdhandler:Delete,
                ViewCmdhandler:Edit,    
                onWeekOrMonthToDay:wtd,
                onBeforeRequestData: cal_beforerequest,
                onAfterRequestData: cal_afterrequest,
                onRequestDataError: cal_onerror, 
                autoload:true,
                readonly: true,
                url: DATA_BASE_URL + "/VerAgenda.do"
            };
            var $dv = $("#calhead");
            var _MH = document.documentElement.clientHeight;
            var dvH = $dv.height() + 2;
            op.height = _MH - dvH;
            op.eventItems =[];

            var p = $("#gridcontainer").bcalendar(op).BcalGetOp();
            if (p && p.datestrshow) {
                $("#txtdatetimeshow").text(p.datestrshow);
            }
            $("#caltoolbar").noSelect();
            
            $("#hdtxtshow").datepicker({ picker: "#txtdatetimeshow", showtarget: $("#txtdatetimeshow"),
                     onReturn:function(r){                          
                            var p = $("#gridcontainer").gotoDate(r).BcalGetOp();
                            if (p && p.datestrshow) {
                                $("#txtdatetimeshow").text(p.datestrshow);
                            }
                     } 
            });
            function cal_beforerequest(type)
            {
                var t="Loading data...";
                switch(type)
                {
                    case 1:
                        t="Loading data...";
                        break;
                    case 2:                      
                    case 3:  
                    case 4:    
                        t="The request is being processed ...";                                   
                        break;
                }
                $("#errorpannel").hide();
                $("#loadingpannel").html(t).show();    
            }
            function cal_afterrequest(type)
            {
                switch(type)
                {
                    case 1:
                        $("#loadingpannel").hide();
                        break;
                    case 2:
                    case 3:
                    case 4:
                        $("#loadingpannel").html("Success!");
                        window.setTimeout(function(){ $("#loadingpannel").hide();},2000);
                    break;
                }              
               
            }
            function cal_onerror(type,data)
            {
                $("#errorpannel").show();
            }
            function Edit(data){
               var eurl="<%= request.getContextPath() %>/AgregarDetalleAgenda.do?id={0}";   
                if(data){
                    var url = StrFormat(eurl,data);
                    OpenModelWindow(url,{ width: 700, height: 315, caption:"Agregar detalle",onclose:function(){
                       $("#gridcontainer").reload();
                    }});
                }
            }    
            function View(data){
                var eurl="<%= request.getContextPath() %>/AgregarDetalleAgenda.do?id={0}";   
                if(data){
                    var url = StrFormat(eurl,data);
                    OpenModelWindow(url,{ width: 700, height: 315, caption:"Agregar detalle",onclose:function(){
                       $("#gridcontainer").reload();
                    }});
                }
            }    
            function Delete(data,callback)
            {           
                
                $.alerts.okButton="OK";  
                $.alerts.cancelButton="Cancel";  
                hiConfirm("¿Desea eliminar el evento seleccionado?", 'Confirmar',function(r){ r && callback(0);});           
            }
            function wtd(p)
            {
               if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $("#showdaybtn").addClass("fcurrent");
            }
            //to show day view
            $("#showdaybtn").click(function(e) {
                //document.location.href="#day";
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $(this).addClass("fcurrent");
                var p = $("#gridcontainer").swtichView("day").BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
            });
            //to show week view
            $("#showweekbtn").click(function(e) {
                //document.location.href="#week";
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $(this).addClass("fcurrent");
                var p = $("#gridcontainer").swtichView("week").BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }

            });
            //to show month view
            $("#showmonthbtn").click(function(e) {
                //document.location.href="#month";
                $("#caltoolbar div.fcurrent").each(function() {
                    $(this).removeClass("fcurrent");
                })
                $(this).addClass("fcurrent");
                var p = $("#gridcontainer").swtichView("month").BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
            });
            
            $("#showreflashbtn").click(function(e){
                $("#gridcontainer").reload();
            });
            
            //Add a new event
            $("#faddbtn").click(function(e) {
                var url ="<%= request.getContextPath() %>/AgregarDetalleAgenda.do";
                OpenModelWindow(url,{ width: 700, height: 315, caption: "Nuevo evento"});
            });
            //go to today
            $("#showtodaybtn").click(function(e) {
                var p = $("#gridcontainer").gotoDate().BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }


            });
            //previous date range
            $("#sfprevbtn").click(function(e) {
                var p = $("#gridcontainer").previousRange().BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }

            });
            //next date range
            $("#sfnextbtn").click(function(e) {
                var p = $("#gridcontainer").nextRange().BcalGetOp();
                if (p && p.datestrshow) {
                    $("#txtdatetimeshow").text(p.datestrshow);
                }
            });
            
        });
        
        function recargarCalendario(cerrar){
        	$("#gridcontainer").reload();
        	//Funciones ubicadas en agendaUsuario.jsp
        	parent.limpiaTabla();
        	parent.cargaFechaActual();
        	parent.cargaActividadesDelUsuarioPorFechaActual();
        	if(cerrar){
        		CloseModelWindow();
        	}
        }
        
    </script>    
</head>
<body>
    <div>

      <div id="calhead" style="padding-left:1px;padding-right:1px;">          
            <div class="cHead"><div class="ftitle">Agenda</div>
            <div id="loadingpannel" class="ptogtitle loadicon" style="display: none;">Cargando datos...</div>
             <div id="errorpannel" class="ptogtitle loaderror" style="display: none;">No se pudieron cargar los datos, intente mas tarde</div>
            </div>          
            
            <div id="caltoolbar" class="ctoolbar">
              <div id="faddbtn" class="fbutton">
                <div><span title='Click to Create New Event' class="addcal">Nuevo Evento</span></div>
            </div>
            <div class="btnseparator"></div>
             <div id="showtodaybtn" class="fbutton">
                <div><span title='Click to back to today ' class="showtoday">Hoy</span></div>
            </div>
              <div class="btnseparator"></div>

            <div id="showdaybtn" class="fbutton">
                <div><span title='Day' class="showdayview">D&iacute;a</span></div>
            </div>
              <div  id="showweekbtn" class="fbutton fcurrent">
                <div><span title='Week' class="showweekview">Semana</span></div>
            </div>
              <div  id="showmonthbtn" class="fbutton">
                <div><span title='Month' class="showmonthview">Mes</span></div>

            </div>
            <div class="btnseparator"></div>
              <div  id="showreflashbtn" class="fbutton">
                <div><span title='Refresh view' class="showdayflash">Actualizar</span></div>
                </div>
             <div class="btnseparator"></div>
            <div id="sfprevbtn" title="Prev"  class="fbutton">
              <span class="fprev"></span>

            </div>
            <div id="sfnextbtn" title="Next" class="fbutton">
                <span class="fnext"></span>
            </div>
            <div class="fshowdatep fbutton">
                    <div>
                        <input type="hidden" name="txtshow" id="hdtxtshow" />
                        <span id="txtdatetimeshow">Cargando</span>

                    </div>
            </div>
            
            <div class="clear"></div>
            </div>
      </div>
      <div style="padding:1px;">

        <div class="t1 chromeColor">
            &nbsp;</div>
        <div class="t2 chromeColor">
            &nbsp;</div>
        <div id="dvCalMain" class="calmain printborder">
            <div id="gridcontainer" style="overflow-y: visible;">
            </div>
        </div>
        <div class="t2 chromeColor">

            &nbsp;</div>
        <div class="t1 chromeColor">
            &nbsp;
        </div>   
        </div>
     
  </div>
    
</body>
</html>
