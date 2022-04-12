package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;

public class Encryption {
	
	public String enctry(String ele) throws NoSuchAlgorithmException {
//		 /* MessageDigest instance for MD5. */  
//       MessageDigest m = MessageDigest.getInstance("MD5");  
//         
//       /* Add plain-text password bytes to digest using MD5 update() method. */  
//       m.update(ele.getBytes());  
//         
//       /* Convert the hash value into bytes */   
//       byte[] bytes = m.digest();  
//         
//       /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
//       StringBuilder s = new StringBuilder();  
//       for(int i=0; i< bytes.length ;i++)  
//       {  
//           s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
//       }  
//         
//       /* Complete hashed password in hexadecimal format */  
//       return (s.toString());
		
		return (ele);
	}
	
	public String encrypt(String pass) {
		String hash = BCrypt.hashpw(pass, BCrypt.gensalt());
		return hash;
	}
	public boolean decrypt(String pass,String hashedPassword) {
		boolean result = BCrypt.checkpw(pass, hashedPassword);
		return result;
	}
	
	
	
}
