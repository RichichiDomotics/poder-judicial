     function mask(str,textbox){  
   
     if(str.indexOf('.')==-1){      //Aplicar comas hasta el punto  
   
     textbox.value = mask1(str,textbox) ;  
     }else if(str.indexOf('.')>-1){  
   
     var str2 =  str.substring(0,    str.indexOf('.'));  
     var afpoint = str.substr(str.indexOf('.')) ;  
     textbox.value = mask1(str2,textbox) + afpoint ;  
   
     }  
   
     }  
   
     function mask1(str,delim){           //Aplica comas cada tres digitos  
   
     str=str.replace(/[^0-9]+/g,'');  
   
     str= reverse(str);  
     var str3="";  
   
     var i=0;  
   
         for (var k = 0; k < str.length; k++){  
   
          str3=str3 + str.charAt(k);  
   
          if(i==2){  
          i=0;  
   
             if(k!=(str.length-1)){  
   
                 str3=str3  + ","  
             }  
          }else{  
   
          i++;  
   
          }  
         }  
   
         if(str3.length>0){  
         str=reverse(str3);  
   
         }else{  
   
         str = "";  
         }  
   
         return "$" + str;  
   
         }  
   
     function reverse(theString){  //Reverse a String  
   
       var newString = "";  
       var counter = theString.length;   
   
     for (counter  ;counter > 0 ;counter -- ) {  
          newString += theString.substring(counter-1, counter);  
       }   
   
     return newString;  
     }  