/**
 * 
 */
package mx.gob.segob.nsjp.comun.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import mx.gob.segob.nsjp.comun.constants.KeygenencripDTO;



/**
 * @author IgnacioFO
 *
 */
public class EncriptadorUtil {
	
	 /**
	   * Turns array of bytes into string
	   *
	   * @param buf	Array of bytes to convert to hex string
	   * @return	Generated hex string
	   */
	   private static String asHex (byte buf[]) {
	    StringBuffer strbuf = new StringBuffer(buf.length * 2);
	    int i;

	    for (i = 0; i < buf.length; i++) {
	     if (((int) buf[i] & 0xff) < 0x10)
		    strbuf.append("0");

	     strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
	    }

	    return strbuf.toString();
	   }
	   
	   public static KeygenencripDTO encriptador(String password){
		   try{
			  
			   String[] args=new String[0];
			   // Get the KeyGenerator
			   	KeygenencripDTO keygenencripDTO=new KeygenencripDTO();
			     KeyGenerator kgen = KeyGenerator.getInstance("AES");
			     kgen.init(128); // 192 and 256 bits may not be available
			     // Generate the secret key specs.
			     SecretKey skey = kgen.generateKey();
			     byte[] raw = skey.getEncoded();
			     SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			     // Instantiate the cipher
			     Cipher cipher = Cipher.getInstance("AES");
			     cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			     byte[] encrypted =
			       cipher.doFinal((args.length == 0 ?password : args[0]).getBytes());
//			     System.out.println("encrypted string: " + asHex(encrypted));
//	
//			     cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//			     byte[] original =
//			       cipher.doFinal(encrypted);
//			     String originalString = new String(original);
//			     System.out.println("Original string: " +
//			       originalString + " " + asHex(original));
//			     
			     keygenencripDTO.setEncrypted(encrypted);
			     keygenencripDTO.setRaw(raw);
			     return keygenencripDTO;
		   }catch (Exception e) {
			   return null;
		   }
	   }
	   
	   public static boolean comparador(String cadenaNormar, KeygenencripDTO keygenencripDTO){
			try{
		   		KeyGenerator kgen = KeyGenerator.getInstance("AES");
			     kgen.init(128); // 192 and 256 bits may not be available
			     // Generate the secret key specs.
			     SecretKey skey = kgen.generateKey();
			     byte[] raw = skey.getEncoded();
			     SecretKeySpec skeySpec = new SecretKeySpec(keygenencripDTO.getRaw(), "AES");
			     // Instantiate the cipher
			     Cipher cipher = Cipher.getInstance("AES");
			      cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			     byte[] original =
			     cipher.doFinal(keygenencripDTO.getEncrypted());
			     String originalString = new String(original);
//			     System.out.println("Original string: " +
//			       originalString + " " + asHex(original));
			     
			     if(originalString.equals(cadenaNormar)){
			    	 return true;
			     }else{
			    	 return false;
			     }
	   		}catch (Exception e) {
	   			e.printStackTrace();
	   			return false;
	   		}
	   }	
	   
	   public static String desencripta(KeygenencripDTO keygenencripDTO){
			try{
		   		KeyGenerator kgen = KeyGenerator.getInstance("AES");
			     kgen.init(128); // 192 and 256 bits may not be available
			     // Generate the secret key specs.
			     SecretKey skey = kgen.generateKey();
			     byte[] raw = skey.getEncoded();
			     SecretKeySpec skeySpec = new SecretKeySpec(keygenencripDTO.getRaw(), "AES");
			     // Instantiate the cipher
			     Cipher cipher = Cipher.getInstance("AES");
			      cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			     byte[] original =
			     cipher.doFinal(keygenencripDTO.getEncrypted());
			     String originalString = new String(original);
//			     System.out.println("Original string: " +
//			       originalString + " " + asHex(original));
			     
			    
			    	 return originalString;
			     
	   		}catch (Exception e) {
	   			e.printStackTrace();
	   			return null;
	   		}
	   }	

}
