<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<!-- EL DIV PRINCIPAL DEL ENCABEZADO SE CIERRA EN MENUSUPERIOR -->

<div class="ui-layout-north">
	<div class="content">
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" background="<%=request.getContextPath()%>/resources/images/top_gral.jpg" height="100%">
			<TBODY>
				<TR>
					<TD width=100 align=left valign="middle"><img
						src="<%=request.getContextPath()%>/resources/images/logo_gral.png">
					</TD>
					<TD width=301 align=left valign="middle"><img
						src="<%=request.getContextPath()%>/resources/images/logo_ampuniinve.png">
					</TD>
					<TD width=126 align=left valign="top"><SPAN></SPAN>
					</TD>
					<TD width=272 align=center valign="top">&nbsp;</TD>
					<TD width=28 align=middle>&nbsp;</TD>
					<!--<td width="150" align="center"></td>-->
					<TD width=380 align="right" valign="middle">
						<table width="362" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="165"><table width="141" border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<td width="53" align="right" class="txt_menu_top">&nbsp;</td>
											<td width="157">&nbsp;</td>
										</tr>
									</table>
								</td>
								<td width="103"><table width="89" border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<td width="107" class="txt_menu_top">Cerrar sesi&oacute;n</td>
											<td width="42" class="txt_menu_top"><a href="javascript:void(0)"
												onclick='$("#dialog-logout").dialog( "open" );'> <img
													src="<%=request.getContextPath()%>/resources/images/btn_cerrar.png"
													width="29" height="26" border="0"> </a></td>
										</tr>
									</table> <label for="textfield"></label>
								</td>
								<td width="204"><table width="89" border="0"
										cellspacing="0" cellpadding="0">
										<tr>
											<td width="47">Ayuda</td>
											<td width="42"><a href="javascript:void(0)"><img
													src="<%=request.getContextPath()%>/resources/images/btn_ayuda.png"
													width="29" height="26" border="0"> </a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table width="363" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="363" align="right" valign="middle"><TABLE
										border=0 cellSpacing=0 cellPadding=0 width="300" height="100%">
										<TBODY>
											<TR>
											<TR vAlign=top>
												<TD width=128 align=right valign="middle">&nbsp;</TD>
												<TD width=150 align="right" valign="middle"><DIV
														id=liveclock></DIV>
												</TD>
												<TD width=10 align="right" valign="middle"><IMG
													alt="Icono reloj"
													src="<%=request.getContextPath()%>/resources/images/icn_reloj.png"
													width=26 height=25>
												</TD>
											</TR>
										</TBODY>
									</TABLE>
								</td>
							</tr>
						</table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</div>

<!-- EL DIV PRINCIPAL DEL ENCABEZADO SE CIERRA EN MENUSUPERIOR -->

