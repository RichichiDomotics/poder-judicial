<<script type="text/javascript">
	var idindi;
</script>


<TABLE  align="center" width="1025px" height="176" border=0 class="back_denuncia">
				        	<TBODY>
				        		<TR vAlign=top>
				        		</TR>	
				        		<TR vAlign=top>
				          			<TD width="37%" align="center" valign="middle">
				          				<table width="360" border="0" cellpadding="0" cellspacing="0" class="linea_derecha_gris">
							            	<tr>
							                	<td width="10" height="109">&nbsp;</td>
							                	<td width="4">&nbsp;</td>
							                	<td width="123"><IMG id=iVictimaCmpFoto alt=foto src="<%= request.getContextPath() %>/resources/images/img_denunciante.png"></td>
							                </tr>
				         				 </table>
				        			 </TD>
				             		 <TD width="45%" align="left" valign="top">
				           				<table width="92%" border="0" cellspacing="0" cellpadding="0">
				              				<tr>
				                				<td height="23">&nbsp;</td>
				              				</tr>
				            			</table>
				            			<TABLE style="BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-TOP: 0px; BORDER-RIGHT: 0px" class=celda2 cellSpacing=0 cellPadding=0 width="63%" height=91>
				              				<TBODY>
				              					<TR>
				                					<TD width="36%" height=25 align=left class="txt_gral_victima">Nombre:</TD>
				                					<TD width="64%">
				                						<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
				                                  			BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpNombre1 title="Escribir nombre" readOnly maxLength=40 size=30 type=text>
				                					</TD>
				              					</TR>
				              					<TR>
				                					<TD width="36%" height=28 align=left class="txt_gral_victima">Apellido Paterno:</TD>
				                					<TD height=28 width="64%">
				                						<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
				                							  BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoPaterno1 readOnly maxLength=40 size=30 type=text>
				                					</TD>
				              					</TR>
				              					<TR>
				                					<TD width="36%" height=29 align=left class="txt_gral_victima">Apellido Materno:</TD>
					            					<TD height=29>
					            						<INPUT style="BORDER-BOTTOM: 1px solid #c0c2c4; BORDER-LEFT: 1px solid #c0c2c4; BACKGROUND: #fff; BORDER-TOP: 1px solid #c0c2c4; 
					                  						BORDER-RIGHT: 1px solid #c0c2c4" id=iSolicitudCmpApellidoMaterno1 readOnly maxLength=40 size=30 type=text>
				                					</TD>
				          	  					</TR>
				         	  				</TBODY>
				            			</TABLE>
				           				<table width="64%%" border="0" cellspacing="0" cellpadding="0">
				           				   <tr>
				                		   		<td>&nbsp;</td>
				              			   </tr>
				            			</table>
				            			<table width="64%" border="0" cellspacing="0" cellpadding="0">
				              				<tr>
								                <td height="23" align="left"><INPUT type=button class="btn_guardar"  id="btnGuardarDatos" value="Guardar" ></td>
								            </tr>
				          				</table>
				         			 </TD>
				       			</TR>
					   			<TR vAlign=top>
				       			<TR vAlign=top>
				          		<TD colSpan=3>&nbsp;</TD>
				        		</TR>
				       		 </TBODY>
				    	</TABLE> 		
				    	
						<div id="cedulaIdentificacion" >
						    <dl>
			                    <dt id="cejaDatosGenerales">Datos Generales</dt>
			                      <dd>	
			                      <jsp:include page="datosSolicitud2.jsp">
			                      
			                      	<jsp:param value="JSR" name="JSR"/>			                      
			                      
			                      </jsp:include>
								
			                      </dd>
			                    <dt id="cejaDomicilio">Domicilio</dt>
			                      <dd>
			                    	<jsp:include page="ingresarDomicilioView.jsp"></jsp:include>
			                      </dd>
			                    <dt id="cejaMediosContacto">Medios de Contacto</dt>
			                       <dd>
			                         <jsp:include page="ingresarMediosContactoView.jsp"/>
			                       </dd>
			                  </dl>
			               </div>