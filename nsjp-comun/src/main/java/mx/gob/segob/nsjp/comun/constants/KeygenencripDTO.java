package mx.gob.segob.nsjp.comun.constants;

public class KeygenencripDTO {
	
	 
	   byte[] raw;
	   byte[] encrypted;
	   
	   public KeygenencripDTO() {
		
	   }
	   
	   public KeygenencripDTO(byte[] raw, byte[] encrypted){
		   this.raw=raw;
		   this.encrypted=encrypted;
	   }
	   
	
	/**
	 * @return the raw
	 */
	public byte[] getRaw() {
		return raw;
	}


	/**
	 * @param raw the raw to set
	 */
	public void setRaw(byte[] raw) {
		this.raw = raw;
	}


	/**
	 * @return the encrypted
	 */
	public byte[] getEncrypted() {
		return encrypted;
	}
	/**
	 * @param encrypted the encrypted to set
	 */
	public void setEncrypted(byte[] encrypted) {
		this.encrypted = encrypted;
	}

}
