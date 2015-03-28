<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
  <head>    
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">    
    <title>Calendar Details</title>    
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/main.css" rel="stylesheet" type="text/css" />       
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/dp.css" rel="stylesheet" />    
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/dropdown.css" rel="stylesheet" />    
    <link href="<%= request.getContextPath() %>/resources/css/wdCalendar/colorselect.css" rel="stylesheet" />   
     
    <script src="<%= request.getContextPath() %>/resources/js/jquery-1.5.1.js" type="text/javascript"></script>  
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/Common.js" type="text/javascript"></script>        
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.form.js" type="text/javascript"></script>     
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.validate.js" type="text/javascript"></script>     
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/datepicker_lang_US.js" type="text/javascript"></script>        
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.datepicker.js" type="text/javascript"></script>     
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.dropdown.js" type="text/javascript"></script>     
    <script src="<%= request.getContextPath() %>/resources/js/wdCalendar/Plugins/jquery.colorselect.js" type="text/javascript"></script>    
    
    <script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>    
     
    <script type="text/javascript">
        if (!DateAdd || typeof (DateDiff) != "function") {
            var DateAdd = function(interval, number, idate) {
                number = parseInt(number);
                var date;
                if (typeof (idate) == "string") {
                    date = idate.split(/\D/);
                    eval("var date = new Date(" + date.join(",") + ")");
                }
                if (typeof (idate) == "object") {
                    date = new Date(idate.toString());
                }
                switch (interval) {
                    case "y": date.setFullYear(date.getFullYear() + number); break;
                    case "m": date.setMonth(date.getMonth() + number); break;
                    case "d": date.setDate(date.getDate() + number); break;
                    case "w": date.setDate(date.getDate() + 7 * number); break;
                    case "h": date.setHours(date.getHours() + number); break;
                    case "n": date.setMinutes(date.getMinutes() + number); break;
                    case "s": date.setSeconds(date.getSeconds() + number); break;
                    case "l": date.setMilliseconds(date.getMilliseconds() + number); break;
                }
                return date;
            }
        }
        
        function getHM(date)
        {
             var hour =date.getHours();
             var minute= date.getMinutes();
             var ret= (hour>9?hour:"0"+hour)+":"+(minute>9?minute:"0"+minute) ;
             return ret;
        }
        var tareaSelectedOnLoad = '<jsp:getProperty name = "AgendaForm" property = "tipoTarea" />';
        
        $(document).ready(function() {
            $.ajax({
            	type: 'POST',
            	url: '<%= request.getContextPath()%>/consultarCatalogoTipoTarea.do',
            	dataType: 'xml',
            	async: false,
            	success: function(xml){
            		$(xml).find('listaCatalogo').find('tarea').each(function(){
            			if($(this).find('clave').text() == tareaSelectedOnLoad){
            				$('#tipoTarea').append('<option selected="true" value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
            			}else{
            				$('#tipoTarea').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
            			}
            		});
            	}
            });
            
            var arrT = [];
            var tt = "{0}:{1}";
            for (var i = 0; i < 24; i++) {
                arrT.push({ text: StrFormat(tt, [i >= 10 ? i : "0" + i, "00"]) }, { text: StrFormat(tt, [i >= 10 ? i : "0" + i, "30"]) });
            }
            $("#timezone").val(new Date().getTimezoneOffset()/60 * -1);
            $("#horaInicio").dropdown({
                dropheight: 150,
                dropwidth:60,
                selectedchange: function() { },
                items: arrT
            });
            $("#horaFinal").dropdown({
                dropheight: 150,
                dropwidth:60,
                selectedchange: function() { },
                items: arrT
            });
            $("#Savebtn").click(function() {
                 if (confirm("¿Estas seguro de guardar este evento?")) {                     

                	 var param = "";
                	 if(!isEmpty(document.getElementById("asunto").value) 
                	 && !isEmpty(document.getElementById("tipoTarea").value) 
                	 && !isEmpty(document.getElementById("fechaInicio").value)
                	 && !isEmpty(document.getElementById("horaInicio").value)
                	 && !isEmpty(document.getElementById("fechaFinal").value)
                	 && !isEmpty(document.getElementById("horaFinal").value)){
                    	 
	                     param += "idEvento=" + document.getElementById("idEvento").value;
	                     param += "&asunto=" + document.getElementById("asunto").value;
	                     param += "&tipoTarea=" + document.getElementById("tipoTarea").value;
	                     param += "&fechaInicio=" + document.getElementById("fechaInicio").value;
	                     param += "&horaInicio=" + document.getElementById("horaInicio").value;
	                     param += "&fechaFinal=" + document.getElementById("fechaFinal").value;
	                     param += "&horaFinal=" + document.getElementById("horaFinal").value;
	                     param += "&lugar=" + document.getElementById("lugar").value;
	                     param += "&descripcion=" + document.getElementById("descripcion").value;
	                     param += "&alarma=" + document.getElementById("alarma").checked;

	                     $.ajax({
	                     	type: 'POST',
	                     	url: '<%= request.getContextPath()%>/ModificaEvento.do',
	                     	data : param,
	                     	dataType: 'xml',
	                     	async: false,
	                     	success: function(xml){
		                     	var errorCode;
	        					errorCode=$(xml).find('response').find('code').text();
	        					if(parseInt(errorCode)==0){
	        						alertDinamico("Se guardó el evento con éxito");
									parent.recargarCalendario(true);
	        					}
	        					else{
	        						alertDinamico("Ocurrió un error");
	        					}

	                     	}
	                     });
                	 }else{
                		 alertDinamico("Los siguientes campos son obligatorios: \n - Asunto \n - Tipo Actividad \n - Fecha Inicio \n - Fecha Fin");
                	 }
 
                 }
            });
            
            $("#Closebtn").click(function() { CloseModelWindow(); });
            
            $("#Deletebtn").click(function() {
                 if (confirm("¿Estas seguro de eliminar este evento?")) {  
                	 var param = "";
                     param += "idEvento=" + document.getElementById("idEvento").value;
                     
                     $.ajax({
                     	type: 'POST',
                     	url: '<%= request.getContextPath()%>/EliminaEvento.do',
                     	data : param,
                     	dataType: 'xml',
                     	async: false,
                     	success: function(xml){
	                     	var errorCode;
        					errorCode=$(xml).find('response').find('code').text();
        					if(parseInt(errorCode)==0){
        						alertDinamico("Se eliminó el evento con éxito");
								parent.recargarCalendario(true);
        					}
        					else{
        						alertDinamico("Ocurrió un error");
        					}
                     	}
                     });
                }
            });
            
           $("#fechaInicio,#fechaFinal").datepicker({ picker: "<button class='calpick'></button>"});    

            $.validator.addMethod("date", function(value, element) {                             
                var arrs = value.split(i18n.datepicker.dateformat.separator);
                var year = arrs[i18n.datepicker.dateformat.year_index];
                var month = arrs[i18n.datepicker.dateformat.month_index];
                var day = arrs[i18n.datepicker.dateformat.day_index];
                var standvalue = [year,month,day].join("-");
                return this.optional(element) || /^(?:(?:1[6-9]|[2-9]\d)?\d{2}[\/\-\.](?:0?[1,3-9]|1[0-2])[\/\-\.](?:29|30))(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\d)?\d{2}[\/\-\.](?:0?[1,3,5,7,8]|1[02])[\/\-\.]31)(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])[\/\-\.]0?2[\/\-\.]29)(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:16|[2468][048]|[3579][26])00[\/\-\.]0?2[\/\-\.]29)(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?: \d{1,3})?)?$|^(?:(?:1[6-9]|[2-9]\d)?\d{2}[\/\-\.](?:0?[1-9]|1[0-2])[\/\-\.](?:0?[1-9]|1\d|2[0-8]))(?: (?:0?\d|1\d|2[0-3])\:(?:0?\d|[1-5]\d)\:(?:0?\d|[1-5]\d)(?:\d{1,3})?)?$/.test(standvalue);
            }, "Invalid date format");
            $.validator.addMethod("time", function(value, element) {
                return this.optional(element) || /^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$/.test(value);
            }, "Invalid time format");
            $.validator.addMethod("safe", function(value, element) {
                return this.optional(element) || /^[^$\<\>]+$/.test(value);
            }, "$<> not allowed");
            $("#fmEdit").validate({
                submitHandler: function(form) { $("#fmEdit").ajaxSubmit(options); },
                errorElement: "div",
                errorClass: "cusErrorPanel",
                errorPlacement: function(error, element) {
                    showerror(error, element);
                }
            });
            function showerror(error, target) {
                var pos = target.position();
                var height = target.height();
                var newpos = { left: pos.left, top: pos.top + height + 2 }
                var form = $("#fmEdit");             
                error.appendTo(form).css(newpos);
            }
        });
    </script>      
    <style type="text/css">     
    .calpick     {        
        width:16px;   
        height:16px;     
        border:none;        
        cursor:pointer;        
        background:url("resources/css/wdCalendar/images/icons/clndrsmll.gif") no-repeat center 0px;        
        margin-left:0px;    
    }      
    .mask{
        width:100%;
        height:100%;
        position:absolute;
        background-color:grey;
        z-index:1;
        filter:alpha(opacity=80);
        opacity:.8;
    }
    .mask div{
        width: 50%;
        text-align:center;
        position: absolute; 
        top: 50%;
        left: 25%;
        color:white;
        
    }
    </style>
  </head>
  <body>  
   <!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center">
					<span id="divAlertTexto"></span>
				</td>
			</tr>
		</table>	
	</div> 
	
    <div>      
      <div class="toolBotton">           
        <a id="Savebtn" class="imgbtn" href="javascript:void(0);">                
          <span class="Save"  title="Guarda el evento">Guardar(<u>S</u>)
          </span>          
        </a>                           
        
        <a id="Deletebtn" class="imgbtn" href="javascript:void(0);">                    
          <span class="Delete" title="Elimina el evento">Eliminar(<u>D</u>)
          </span>                
        </a>             
                  
        <a id="Closebtn" class="imgbtn" href="javascript:void(0);">                
          <span class="Close" title="Vierra la venta" >Cerrar
          </span></a>            
        </a>        
      </div>                  
      <div style="clear: both">         
      </div>        
      <div class="infocontainer">
      	<html:form action="/ModificaEvento.do" method="POST" styleId="AgendaForm" styleClass="fform" >
      	<html:hidden property="idEvento" name="AgendaForm" styleId="idEvento"></html:hidden>            
          <label>                    
            <span>*Asunto:</span>
            <html:text property="asunto" name="AgendaForm" styleId="asunto" styleClass="required safe" style="width:97%;" maxlength="200"></html:text>
          </label>
          <label>                    
            <span>*Tipo Actividad:</span>
            <html:select property="tipoTarea" name="AgendaForm" styleId="tipoTarea" styleClass="required safe" style="width:97%;">                
            	<option valur="">Selecccione una opcion</option>
            </html:select>            
          </label>     
          <label>                                        
            <div> 
              <span style="display:inline;">Fecha Inicio:</span>
              <html:text property="fechaInicio" name="AgendaForm" styleId="fechaInicio" styleClass="required date" style="padding-left:2px;width:90px;" maxlength="10"></html:text>
              <html:text property="horaInicio" name="AgendaForm" styleId="horaInicio" styleClass="required time" style="width:40px;" maxlength="5"></html:text>
              <span style="display:inline;">Fecha Fin: </span>
              <html:text property="fechaFinal" name="AgendaForm" styleId="fechaFinal" styleClass="required date" style="padding-left:2px;width:90px;" maxlength="10"></html:text>
              <html:text property="horaFinal" name="AgendaForm" styleId="horaFinal" styleClass="required time" style="width:40px;" maxlength="5"></html:text>
              <span style="display:inline;">Con Alarma:</span><html:checkbox name="AgendaForm" property="alarma" styleId="alarma"/>                    
            </div>                
          </label>                 
          <label>                    
            <span>Ubicaci&oacute;n:</span>
            <html:textarea property="lugar" name="AgendaForm" styleId="lugar" cols="20" rows="2" style="width:97%; height:70px"></html:textarea>
          </label>                 
          <label>                    
            <span>Descripci&oacute;n:</span>
            <html:text property="descripcion" name="AgendaForm" styleId="descripcion" style="width:97%;" maxlength="200"></html:text>            
          </label>                           
        </form>
        </html:form>   
      </div>         
    </div>
  </body>
</html>