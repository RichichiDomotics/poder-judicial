<%@page import="mx.gob.segob.nsjp.comun.enums.documento.TipoOficioEstructurado"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--		Hojas de estilos asociadas-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/treeview/jquery.treeview.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.treeview.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
<script type="text/javascript"> 
if(location.hostname.toLowerCase().indexOf('dhtmlgoodies') >=0) {
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-2042963-3']);
  _gaq.push(['_trackPageview']);
 
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
}
</script>
	<style type="text/css">
	body{
		font-family: Trebuchet MS, Lucida Sans Unicode, Arial, sans-serif;	/* Font to use */
		background-color:#E2EBED;
	}
	#footer{
		height:30px;
		vertical-align:middle;
		text-align:right;
		clear:both;
		padding-right:3px;
		background-color:#317082;
		margin-top:2px;
		width:790px;
	}
	#footer form{
		margin:0px;
		margin-top:2px;
	}
	#dhtmlgoodies_dragDropContainer{	/* Main container for this script */
		width:690px;
		height:820px;
		border:1px solid #317082;
		background-color:#FFF;
		-moz-user-select:none;
	}
	#dhtmlgoodies_dragDropContainer ul{	/* General rules for all <ul> */
		margin-top:0px;
		margin-left:0px;
		margin-bottom:0px;
		padding:2px;
	}
 
	#dhtmlgoodies_dragDropContainer li,#dragContent li,li#indicateDestination{	/* Movable items, i.e. <LI> */
		list-style-type:none;
		height:20px;
		background-color:#EEE;
		border:1px solid #000;
		padding:2px;
		margin-bottom:2px;
		cursor:pointer;
		font-size:0.9em;
	}
 
	li#indicateDestination{	/* Box indicating where content will be dropped - i.e. the one you use if you don't use arrow */
		border:1px dotted #600;
		background-color:#FFF;
	}
 
 
	/* LEFT COLUMN CSS */
	div#dhtmlgoodies_listOfItems{	/* Left column "Available students" */
 
		float:left;
		padding-left:10px;
		padding-right:10px;
 
		/* CSS HACK */
		width: 330px;	/* IE 5.x */
		width/* */:/**/320px;	/* Other browsers */
		width: /**/320px;
 
	}
	#dhtmlgoodies_listOfItems ul{	/* Left(Sources) column <ul> */
		height:860px;
 
	}
 
	div#dhtmlgoodies_listOfItems div{
		border:1px solid #999;
	}
	div#dhtmlgoodies_listOfItems div ul{	/* Left column <ul> */
		margin-left:10px;	/* Space at the left of list - the arrow will be positioned there */
	}
	#dhtmlgoodies_listOfItems div p{	/* Heading above left column */
		margin:0px;
		font-weight:bold;
		padding-left:12px;
		background-color:#317082;
		color:#FFF;
		margin-bottom:5px;
	}
	/* END LEFT COLUMN CSS */
 
	#dhtmlgoodies_dragDropContainer .mouseover{	/* Mouse over effect DIV box in right column */
		background-color:#E2EBED;
		border:1px solid #317082;
	}
 
	/* Start main container CSS */
 
	div#dhtmlgoodies_mainContainer{	/* Right column DIV */
		width:790px;
		
		
	}
	#dhtmlgoodies_mainContainer div{	/* Parent <div> of small boxes */
		float:left;
		margin-right:10px;
		margin-bottom:10px;
		margin-top:0px;
		border:1px solid #999;
 
		/* CSS HACK */
		width: 332px;	/* IE 5.x */
		width/* */:/**/330px;	/* Other browsers */
		width: /**/330px;
 
	}
	#dhtmlgoodies_mainContainer div ul{
		margin-left:10px;
	}
 
	#dhtmlgoodies_mainContainer div p{	/* Heading above small boxes */
		margin:0px;
		padding:0px;
		padding-left:10px;
		font-weight:bold;
		background-color:#317082;
		color:#FFF;
		margin-bottom:5px;
	}
 
	#dhtmlgoodies_mainContainer ul{	/* Small box in right column ,i.e <ul> */
		width:318px;
		height:200px;
		border:0px;
		margin-bottom:0px;
		overflow:hidden;
 
	}
 
	#dragContent{	/* Drag container */
		position:absolute;
		width:250px;
		height:20px;
		display:none;
		margin:0px;
		padding:0px;
		z-index:2000;
	}
 
	#dragDropIndicator{	/* DIV for the small arrow */
		position:absolute;
		width:7px;
		height:10px;
		display:none;
		z-index:1000;
		margin:0px;
		padding:0px;
	}
	</style>
	<style type="text/css" media="print">
	div#dhtmlgoodies_listOfItems{
		display:none;
	}
	body{
		background-color:#FFF;
	}
	img{
		display:none;
	}
	#dhtmlgoodies_dragDropContainer{
		border:0px;
		width:100%;
	}
	p{
		margin-bottom:0px;
	}
	</style>
	<script type="text/javascript">
	/************************************************************************************************************
	(C) www.dhtmlgoodies.com, November 2005
 
	Update log:
 
	December 20th, 2005 : Version 1.1: Added support for rectangle indicating where object will be dropped
	January 11th, 2006: Support for cloning, i.e. "copy & paste" items instead of "cut & paste"
	January 18th, 2006: Allowing multiple instances to be dragged to same box(applies to "cloning mode")
 
	This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.
 
		Terms of use:
	You are free to use this script as long as the copyright message is kept intact. However, you may not
	redistribute, sell or repost it without our permission.
 
	Thank you!
 
	www.dhtmlgoodies.com
	Alf Magne Kalleland
 
	************************************************************************************************************/
 
	/* VARIABLES YOU COULD MODIFY */
	var boxSizeArray = [20,4,4,3,7];	// Array indicating how many items there is rooom for in the right column ULs
 
	var arrow_offsetX = -5;	// Offset X - position of small arrow
	var arrow_offsetY = 0;	// Offset Y - position of small arrow
 
	var arrow_offsetX_firefox = -6;	// Firefox - offset X small arrow
	var arrow_offsetY_firefox = -13; // Firefox - offset Y small arrow
 
	var verticalSpaceBetweenListItems = 3;	// Pixels space between one <li> and next
											// Same value or higher as margin bottom in CSS for #dhtmlgoodies_dragDropContainer ul li,#dragContent li
 
 
	var indicateDestionationByUseOfArrow = false;	// Display arrow to indicate where object will be dropped(false = use rectangle)
 
	var cloneSourceItems = false;	// Items picked from main container will be cloned(i.e. "copy" instead of "cut").
	var cloneAllowDuplicates = true;	// Allow multiple instances of an item inside a small box(example: drag Student 1 to team A twice
 
	/* END VARIABLES YOU COULD MODIFY */
 
	var dragDropTopContainer = false;
	var dragTimer = -1;
	var dragContentObj = false;
	var contentToBeDragged = false;	// Reference to dragged <li>
	var contentToBeDragged_src = false;	// Reference to parent of <li> before drag started
	var contentToBeDragged_next = false; 	// Reference to next sibling of <li> to be dragged
	var destinationObj = false;	// Reference to <UL> or <LI> where element is dropped.
	var dragDropIndicator = false;	// Reference to small arrow indicating where items will be dropped
	var ulPositionArray = new Array();
	var mouseoverObj = false;	// Reference to highlighted DIV
 
	var MSIE = navigator.userAgent.indexOf('MSIE')>=0?true:false;
	var navigatorVersion = navigator.appVersion.replace(/.*?MSIE (\d\.\d).*/g,'$1')/1;
 
 
	var indicateDestinationBox = false;
	var idExpediente;
	var indiceTexto=0;
	
	function getTopPos(inputObj)
	{
	  var returnValue = inputObj.offsetTop;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetTop;
	  }
	  return returnValue;
	}
 
	function getLeftPos(inputObj)
	{
	  var returnValue = inputObj.offsetLeft;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetLeft;
	  }
	  return returnValue;
	}
 
	function cancelEvent()
	{
		return false;
	}
	function initDrag(e)	// Mouse button is pressed down on a LI
	{
		if(document.all)e = event;
		var st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
		var sl = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
 
		dragTimer = 0;
		dragContentObj.style.left = e.clientX + sl + 'px';
		dragContentObj.style.top = e.clientY + st + 'px';
		contentToBeDragged = this;
		pintaTexto(contentToBeDragged.id);
		contentToBeDragged_src = this.parentNode;
		contentToBeDragged_next = false;
		if(this.nextSibling){
			contentToBeDragged_next = this.nextSibling;
			if(!this.tagName && contentToBeDragged_next.nextSibling)contentToBeDragged_next = contentToBeDragged_next.nextSibling;
		}
		timerDrag();
		return false;
	}
 
	function timerDrag()
	{
		if(dragTimer>=0 && dragTimer<10){
			dragTimer++;
			setTimeout('timerDrag()',10);
			return;
		}
		if(dragTimer==10){
 
			if(cloneSourceItems && contentToBeDragged.parentNode.id=='allItems'){
				newItem = contentToBeDragged.cloneNode(true);
				newItem.onmousedown = contentToBeDragged.onmousedown;
				contentToBeDragged = newItem;
			}
			dragContentObj.style.display='block';
			dragContentObj.appendChild(contentToBeDragged);
		}
	}
 
	function moveDragContent(e)
	{
		if(dragTimer<10){
			if(contentToBeDragged){
				if(contentToBeDragged_next){
					contentToBeDragged_src.insertBefore(contentToBeDragged,contentToBeDragged_next);
				}else{
					contentToBeDragged_src.appendChild(contentToBeDragged);
				}
			}
			return;
		}
		if(document.all)e = event;
		var st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
		var sl = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
 
 
		dragContentObj.style.left = e.clientX + sl + 'px';
		dragContentObj.style.top = e.clientY + st + 'px';
 
		if(mouseoverObj)mouseoverObj.className='';
		destinationObj = false;
		dragDropIndicator.style.display='none';
		if(indicateDestinationBox)indicateDestinationBox.style.display='none';
		var x = e.clientX + sl;
		var y = e.clientY + st;
		var width = dragContentObj.offsetWidth;
		var height = dragContentObj.offsetHeight;
 
		var tmpOffsetX = arrow_offsetX;
		var tmpOffsetY = arrow_offsetY;
		if(!document.all){
			tmpOffsetX = arrow_offsetX_firefox;
			tmpOffsetY = arrow_offsetY_firefox;
		}
 
		for(var no=0;no<ulPositionArray.length;no++){
			var ul_leftPos = ulPositionArray[no]['left'];
			var ul_topPos = ulPositionArray[no]['top'];
			var ul_height = ulPositionArray[no]['height'];
			var ul_width = ulPositionArray[no]['width'];
 
			if((x+width) > ul_leftPos && x<(ul_leftPos + ul_width) && (y+height)> ul_topPos && y<(ul_topPos + ul_height)){
				var noExisting = ulPositionArray[no]['obj'].getElementsByTagName('LI').length;
				if(indicateDestinationBox && indicateDestinationBox.parentNode==ulPositionArray[no]['obj'])noExisting--;
				if(noExisting<boxSizeArray[no-1] || no==0){
					dragDropIndicator.style.left = ul_leftPos + tmpOffsetX + 'px';
					var subLi = ulPositionArray[no]['obj'].getElementsByTagName('LI');
 
					var clonedItemAllreadyAdded = false;
					if(cloneSourceItems && !cloneAllowDuplicates){
						for(var liIndex=0;liIndex<subLi.length;liIndex++){
							if(contentToBeDragged.id == subLi[liIndex].id)clonedItemAllreadyAdded = true;
						}
						if(clonedItemAllreadyAdded)continue;
					}
 
					for(var liIndex=0;liIndex<subLi.length;liIndex++){
						var tmpTop = getTopPos(subLi[liIndex]);
						if(!indicateDestionationByUseOfArrow){
							if(y<tmpTop){
								destinationObj = subLi[liIndex];
								indicateDestinationBox.style.display='block';
								subLi[liIndex].parentNode.insertBefore(indicateDestinationBox,subLi[liIndex]);
								break;
							}
						}else{
							if(y<tmpTop){
								destinationObj = subLi[liIndex];
								dragDropIndicator.style.top = tmpTop + tmpOffsetY - Math.round(dragDropIndicator.clientHeight/2) + 'px';
								dragDropIndicator.style.display='block';
								break;
							}
						}
					}
 
					if(!indicateDestionationByUseOfArrow){
						if(indicateDestinationBox.style.display=='none'){
							indicateDestinationBox.style.display='block';
							ulPositionArray[no]['obj'].appendChild(indicateDestinationBox);
						}
 
					}else{
						if(subLi.length>0 && dragDropIndicator.style.display=='none'){
							dragDropIndicator.style.top = getTopPos(subLi[subLi.length-1]) + subLi[subLi.length-1].offsetHeight + tmpOffsetY + 'px';
							dragDropIndicator.style.display='block';
						}
						if(subLi.length==0){
							dragDropIndicator.style.top = ul_topPos + arrow_offsetY + 'px'
							dragDropIndicator.style.display='block';
						}
					}
 
					if(!destinationObj)destinationObj = ulPositionArray[no]['obj'];
					mouseoverObj = ulPositionArray[no]['obj'].parentNode;
					mouseoverObj.className='mouseover';
					return;
				}
			}
		}
	}
 
	/* End dragging
	Put <LI> into a destination or back to where it came from.
	*/
	function dragDropEnd(e)
	{
		if(dragTimer==-1)return;
		if(dragTimer<10){
			dragTimer = -1;
			return;
		}
		dragTimer = -1;
		if(document.all)e = event;
 
 
		if(cloneSourceItems && (!destinationObj || (destinationObj && (destinationObj.id=='allItems' || destinationObj.parentNode.id=='allItems')))){
			contentToBeDragged.parentNode.removeChild(contentToBeDragged);
		}else{
 
			if(destinationObj){
				if(destinationObj.tagName=='UL'){
					destinationObj.appendChild(contentToBeDragged);
				}else{
					destinationObj.parentNode.insertBefore(contentToBeDragged,destinationObj);
				}
				mouseoverObj.className='';
				destinationObj = false;
				dragDropIndicator.style.display='none';
				if(indicateDestinationBox){
					indicateDestinationBox.style.display='none';
					document.body.appendChild(indicateDestinationBox);
				}
				contentToBeDragged = false;
				return;
			}
			if(contentToBeDragged_next){
				contentToBeDragged_src.insertBefore(contentToBeDragged,contentToBeDragged_next);
			}else{
				contentToBeDragged_src.appendChild(contentToBeDragged);
			}
		}
		contentToBeDragged = false;
		dragDropIndicator.style.display='none';
		if(indicateDestinationBox){
			indicateDestinationBox.style.display='none';
			document.body.appendChild(indicateDestinationBox);
 
		}
		mouseoverObj = false;
 
	}
 
	/*
	Preparing data to be saved
	*/
	function saveDragDropNodes()
	{
		var saveString = "";
		var uls = dragDropTopContainer.getElementsByTagName('UL');
		for(var no=0;no<uls.length;no++){	// LOoping through all <ul>
			var lis = uls[no].getElementsByTagName('LI');
			for(var no2=0;no2<lis.length;no2++){
				if(saveString.length>0)saveString = saveString + ";";
				saveString = saveString + uls[no].id + '|' + lis[no2].id;
			}
		}
 
		document.getElementById('saveContent').innerHTML = '<h1>Ready to save these nodes:</h1> ' + saveString.replace(/;/g,';<br>') + '<p>Format: ID of ul |(pipe) ID of li;(semicolon)</p><p>You can put these values into a hidden form fields, post it to the server and explode the submitted value there</p>';
 
	}

	function pintaTexto(texto){

		indiceTexto=texto;
		
		var nuevoTexto="";
		$.ajax({
	    	type: 'POST',
	    	url: '<%=request.getContextPath()%>/consultaCuerpoOficioTeoriaCaso.do',
	    	data: 'idIndiceOficio='+texto,
	    	async:false, 
	    	dataType: 'xml',
	    	success: function(xml){
	    		nuevoTexto=$(xml).find('documentoDTO').find('textoEtiquetaHijo').text();
	    		//alert(nuevoTexto);
	    	}
		});		
		
		//nuevoTexto=texto;
		
		$('.jquery_ckeditor').val(nuevoTexto);
		//alert($('#'+texto).id());
		//alert(texto);
	}

	function guardaTeoria(){
		var recuperaTexto=$('.jquery_ckeditor').val();
		//alert($('#'+texto).id());
		//alert(recuperaTexto);
		var mainContainer = document.getElementById('dhtmlgoodies_mainContainer');
		var uls = mainContainer.getElementsByTagName('UL');
		var idul="";
		for(var no=0;no<uls.length;no++){
			var lis = uls[no].getElementsByTagName('LI');
			for(var no2=0;no2<lis.length;no2++){
				idul+=lis[no2].id+",";
			}
		}
		//alert(idul);
		$.ajax({
	    	type: 'POST',
	    	url: '<%=request.getContextPath()%>/ingresaTeoriaCaso.do',
	    	data: 'idsIndices='+idul+'&idIndice='+indiceTexto+'&texto='+escape(recuperaTexto)+'&idExpediente='+idExpediente,
	    	async:false, 
	    	dataType: 'xml',
	    	success: function(xml){
	    		//$(xml).find('indiceEstructuradoDTO').each(function(){
		    		//alert($(this).find('cuerpoOficioEstructuradoId').text());
	    			// contenido+="<li id='"+$(this).find('indiceEstructuradoId').text()+"' onclick='pintaTexto(this.id)'>"+$(this).find('nombreEtiqueta').text()+"</li>";
    			//});
	    		alert("Teoria del caso Guardada");
	    	}
		});		
		//alert(idul);
	}
 
	function initDragDropScript()
	{
		idExpediente='<%= request.getParameter("idExpediente")%>';
		var unicoNumeroExpediente='<%= request.getParameter("numeroExpediente")%>';
		var idsArray=new Array();
		var contenido2="";
		$.ajax({
	    	type: 'POST',
	    	url: '<%=request.getContextPath()%>/consultarTeoriasDelCasoExpediente.do',
	    	data: 'numeroExpediente='+unicoNumeroExpediente,
	    	async:false, 
	    	dataType: 'xml',
	    	success: function(xml){
	    		var i=0;
	    		$(xml).find('cuerpoOficioEstructuradoDTO').each(function(){
		    		//alert($(this).find('nombreEtiqueta').text());
		    		//alert($(this).find('cuerpoOficioEstructuradoId').text());
		    		
		    		//alert($(this).find('indiceEstructuradoIdHijo').text());
		    		idsArray[i]=$(this).find('indiceEstructuradoIdHijo').text();
		    		i++;
		    		//var ids=($(this).find('indiceEstructuradoIdHijo').text()+"#-1");
	    			contenido2+="<li id='"+$(this).find('indiceEstructuradoIdHijo').text()+"#"+$(this).find('cuerpoOficioEstructuradoId').text()+"' onclick='pintaTexto(this.id)'>"+$(this).find('nombreEtiquetaHijo').text()+"</li>";
	    			//var row=$('#'+ids);
	    			//$(row).remove();
    			});
	    		//alert($(xml).find('cuerpoOficioEstructuradoId').text());
	    		//alert("listo");
	    	}
		});
		$("#box1").empty();
		$("#box1").append(contenido2);
		$("#box1").treeview();
		//box1
		
		
		var contenido="";
		$.ajax({
	    	type: 'POST',
	    	url: '<%=request.getContextPath()%>/consultarIndiceEstructurado.do',
	    	data: 'tipoOficio=' + '<%=TipoOficioEstructurado.TEORIA_DEL_CASO.getValorId()%>',
	    	async:false, 
	    	dataType: 'xml',
	    	success: function(xml){
	    		$(xml).find('indiceEstructuradoDTO').each(function(){
		    		//alert($(this).find('nombreEtiqueta').text());
		    		var op="1";
		    		for(j=0;j<idsArray.length;j++){
		    			var idhijo=$(this).find('indiceEstructuradoIdHijo').text();
		    			//alert(idsArray[j]+'!='+idhijo);
		    			if(idsArray[j]==idhijo){
		    				op="2";		
		    			}
		    		}
		    		//alert(op);
					if(op=="1"){
		    			contenido+="<li id='"+$(this).find('indiceEstructuradoIdHijo').text()+"#-1' onclick='pintaTexto(this.id)'>"+$(this).find('nombreEtiquetaHijo').text()+"</li>";
					}
		    	});
	    	}
		});
		
		$("#allItems").empty();
		$("#allItems").append(contenido);
		$("#allItems").treeview();

		
		
		
		dragContentObj = document.getElementById('dragContent');
		dragDropIndicator = document.getElementById('dragDropIndicator');
		dragDropTopContainer = document.getElementById('dhtmlgoodies_dragDropContainer');
		document.documentElement.onselectstart = cancelEvent;;
		var listItems = dragDropTopContainer.getElementsByTagName('LI');	// Get array containing all <LI>
		var itemHeight = false;
		for(var no=0;no<listItems.length;no++){
			listItems[no].onmousedown = initDrag;
			listItems[no].onselectstart = cancelEvent;
			if(!itemHeight)itemHeight = listItems[no].offsetHeight;
			if(MSIE && navigatorVersion/1<6){
				listItems[no].style.cursor='hand';
			}
		}
 
		var mainContainer = document.getElementById('dhtmlgoodies_mainContainer');
		var uls = mainContainer.getElementsByTagName('UL');
		itemHeight = itemHeight + verticalSpaceBetweenListItems;
		for(var no=0;no<uls.length;no++){
			uls[no].style.height = itemHeight * boxSizeArray[no]  + 'px';
		}
 
		var leftContainer = document.getElementById('dhtmlgoodies_listOfItems');
		var itemBox = leftContainer.getElementsByTagName('UL')[0];
 
		document.documentElement.onmousemove = moveDragContent;	// Mouse move event - moving draggable div
		document.documentElement.onmouseup = dragDropEnd;	// Mouse move event - moving draggable div
 
		var ulArray = dragDropTopContainer.getElementsByTagName('UL');
		for(var no=0;no<ulArray.length;no++){
			ulPositionArray[no] = new Array();
			ulPositionArray[no]['left'] = getLeftPos(ulArray[no]);
			ulPositionArray[no]['top'] = getTopPos(ulArray[no]);
			ulPositionArray[no]['width'] = ulArray[no].offsetWidth;
			ulPositionArray[no]['height'] = ulArray[no].clientHeight;
			ulPositionArray[no]['obj'] = ulArray[no];
		}
 
		if(!indicateDestionationByUseOfArrow){
			indicateDestinationBox = document.createElement('LI');
			indicateDestinationBox.id = 'indicateDestination';
			indicateDestinationBox.style.display='none';
			document.body.appendChild(indicateDestinationBox);
 
 
		}
		
		
	}

	
	window.onload = initDragDropScript;

	</script>
</head>
<body>
		<table  align="right" border="0">
			<tr>
				<td align="right">
					<input type="button" id="guardaTeoria" value="Guardar" onclick="guardaTeoria()" class="btn_Generico"/>
				</td>
			</tr>
			<tr>
				<td>
					 <!-- textarea id="teoriaCasoId" cols="45" rows="5"   ></textarea-->
					 <div style="margin-top: 0; margin-bottom: auto; vertical-align: top;margin-right: auto; margin-left: auto; width: 637px; height:500px;">
				<jsp:include page="/WEB-INF/paginas/ingresarNarrativaTeoriaView.jsp" flush="true"></jsp:include>
				
				</div>
				</td>
			</tr>
			
		</table>
<div style="padding-bottom:5px;width:728px;height:1px">
<script type="text/javascript"><!--
google_ad_client = "pub-0714236485040063";
/* 728x90, opprettet 14.08.08 */
google_ad_slot = "8581783445";
google_ad_width = 928;
google_ad_height = 150;
//-->
</script>
</div>
	<div id="dhtmlgoodies_dragDropContainer">
	<div id="topBar">
		<img src="/images/heading3.gif">
	</div>
	<div id="dhtmlgoodies_listOfItems">
		<div>
			<p>Índice Estructurado</p>
		<ul id="allItems">
			<li id="node1">Student A</li>
			<li id="node2">Student B</li>
			<li id="node3">Student C</li>
			<li id="node4">Student D</li>
			<li id="node5">Student E</li>
			<li id="node6">Student F</li>
			<li id="node7">Student G</li>
			<li id="node8">Student H</li>
			<li id="node9">Student I</li>
			<li id="node10">Student J</li>
			<li id="node11">Student K</li>
			<li id="node12">Student L</li>
			<li id="node13">Student M</li>
			<li id="node14">Student N</li>
			<li id="node15">Student O</li>
		</ul>
		</div>
	</div>
	<div id="dhtmlgoodies_mainContainer">
		<!-- ONE <UL> for each "room" -->
		<div>
			<p>Índice teoria del caso</p>
			<ul id="box1">
				
			</ul>
		</div>
	</div>
</div>
<div id="footer">
	
</div>
<ul id="dragContent"></ul>
<div id="dragDropIndicator"></div>
<div id="saveContent"></div>
</body>
</html>