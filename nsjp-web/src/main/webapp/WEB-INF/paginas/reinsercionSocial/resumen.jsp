<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<script type="text/javascript">
    $(document).ready(function(){
        $("#select1").change(function(event){
        		var opcion = myJSONObject[$(this).val()];
        
				var options = '';
					for (var i = 0; i < opcion.length; i++) {
						options += '<option value="' + opcion[i].optionValue + '">' + opcion[i].optionDisplay + '</option>';
					}
					$("#select2").html(options);
		});
		
		$("#select2").dblclick(function(event){
			var proceso = $("#select1").find("option:selected").text();
			var hrefProceso = $('#tabs li a:contains(' +  proceso +')').attr("href");
    		$("#tabs").tabs('select', hrefProceso);
			var subproceso = $(this).find("option:selected").text();
			var hrefSubproceso = $('#tabs li a:contains(' +  subproceso +')').attr("href");
			$("div[id|='subtabs']").tabs('select', hrefSubproceso);    		
		});
    });
        
var myJSONObject = {
	<logic:iterate name="ReinsercionSocial" property="procesosDTO" id="proceso" >
		"<bean:write name="proceso" property="id" />"
		: [
				<logic:iterate name="proceso" property="lstSubprocesos" id="subproceso">
        			{"optionValue": <bean:write name="subproceso" property="id" />, "optionDisplay": "<bean:write name="subproceso" property="cNombre" />"}
        			,
        		</logic:iterate>
    		 ]
    		 ,
    </logic:iterate>  		 
};    
    
    
</script>
<table>
	<tr>
		<td>
			<fieldset style="width: 350px;">
				<legend>Procesos</legend>
				
				<select name="select1" id="select1" size="10" style="width: 300px;">
					<logic:iterate name="ReinsercionSocial" property="procesosDTO" id="proceso" >
			    		<option value="<bean:write name="proceso" property="id" />"><bean:write name="proceso" property="cNombre" /></option>
			    	</logic:iterate>
				</select>
			</fieldset>		
		</td>
		<td>
			<fieldset style="width: 350px;">
				<legend>Subprocesos</legend> 
				<select name="select2" id="select2" size="10" style="width: 300px;">			 
				</select>
			</fieldset>		
		</td>
	</tr>
</table>
