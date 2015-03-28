function show5(){
  if (!document.layers&&!document.all&&!document.getElementById)
  return
  
   var Digital=new Date()
   var hours=Digital.getHours()
   var minutes=Digital.getMinutes()
   var seconds=Digital.getSeconds()
  
  var dn="PM"
  if (hours<12)
  dn="AM"
  if (hours>12)
  hours=hours-12
  if (hours==0)
  hours=12
  
   if (minutes<=9)
   minutes="0"+minutes
   if (seconds<=9)
   seconds="0"+seconds
  //change font size here to your desire
  myclock=""+hours+":"+minutes+":"
   +seconds+" "+dn+""
  if (document.layers){
  document.layers.liveclock.document.write(myclock)
  document.layers.liveclock.document.close()
  }
  else if (document.all)
  liveclock.innerHTML=myclock
  else if (document.getElementById)
  document.getElementById("liveclock").innerHTML=myclock
  setTimeout("show5()",1000)
 }
 
 
 //funcion que llama a mostrar reloj y calendario
function muestraGadgets(){
  //showFlatCalendar();
  //editorTexto();
  //mostrarBarraGadgets();
  show5();
  //cambiaFrames();
}
 
 
//FUNCION QUE OCULTA O MUESTRA EL RELOJ 
function ocultaReloj(){
 	//document.getElementById(liveclock).
	if(document.getElementById("liveclock").style.display=='none'){
		document.getElementById("liveclock").style.display='';
	}	
	else {
		document.getElementById("liveclock").style.display='none';
	}
 }
 //window.onload=show5

function muestraFrame(cadena){
	top.parent.document.getElementById("iframeEditor").src=cadena;
}
