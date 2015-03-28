/**
* Nombre del Programa : tabs.js
* Autor               : Arturo León
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 01/03/2011
* Marca de cambio     : N/A
* Descripcion General : Archivo javaScript con las funciones para las Tabs de integración
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/

/**
 * Función genérica para crear las tabs y darles estilo
 * @param nombreTabs Nombre del div TABS
 * @param nombreTabNavigation Nombre de la lista TabNavigation
 */
function creaTab(nombreTabs, nombreTabNavigation){
	var tabContainers = $('div.tabs' +  nombreTabs + ' > div');
	tabContainers.hide().filter(':first').show();
	$('div.tabs' +  nombreTabs + ' ul.tabNavigation' +  nombreTabNavigation + ' a').click(function () {
		tabContainers.hide();
		tabContainers.filter(this.hash).show();
		$('div.tabs' +  nombreTabs + ' ul.tabNavigation' +  nombreTabNavigation + ' a').removeClass('selected');
		$(this).addClass('selected');
		return false;
	}).filter(':first').click();
}

/**
 * Función que inhabilita todo un bloque de tabs
 * @param nombreTabs Nombre del div TABS
 * @param nombreTabNavigation Nombre de la lista TabNavigation
 */
function inhabilitaTabs(nombreTabs, nombreTabNavigation){
	$('div.tabs' +  nombreTabs + ' ul.tabNavigation' +  nombreTabNavigation + ' a').unbind('click');
}

/**
 * Función que habilita todo un bloque de tabs
 * @param nombreTabs Nombre del div TABS
 * @param nombreTabNavigation Nombre de la lista TabNavigation
 */
function habilitaTabs(nombreTabs, nombreTabNavigation){
	var tabContainers = $('div.tabs' +  nombreTabs + ' > div');
	$('div.tabs' +  nombreTabs + ' ul.tabNavigation' +  nombreTabNavigation + ' a').click(function () {
		tabContainers.hide();
		tabContainers.filter(this.hash).show();
		$('div.tabs' +  nombreTabs + ' ul.tabNavigation' +  nombreTabNavigation + ' a').removeClass('selected');
		$(this).addClass('selected');
		return false;
	});
}