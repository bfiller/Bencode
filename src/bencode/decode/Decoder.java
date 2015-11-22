/**
 * 
 */
package bencode.decode;

import java.util.*;

public class Decoder {
		//Reverse operation of encoder class. Decoder can take an individual Byte String or Integer in Bencoded format and convert it to a String
		//in it's plaintext formatting.
	
		//Future optimization may include a HashMap to store previously encoded Strings.
	
		public String decode(String b){
			//Parameter is a Bencoded Ambiguous String. Output is the plaintext formatted String.
			char c = b.charAt(0);                                                                                     
				if (c == 'i'){
					return decodeInteger(b);
				}else if (Character.isDigit(b.charAt(0))){
					return decodeByteString(b);
				}else{
					System.out.println("invalid output for single String input");
					return null;
				}
		}
		
		public String decodeByteString(String b){
			//Takes a known ByteString in Bencoded format and outputs it's Plaintext String value.
			StringBuilder sb = new StringBuilder(b);
			int i = 0;
			while (sb.charAt(i) != ':'){
				sb.deleteCharAt(i);
			}
			sb.deleteCharAt(i);
			return sb.toString();
		}
		
		public String decodeInteger(String b){
			//Takes a known Bencoded Integer in String format and outputs the plaintext String value.
			StringBuilder sb = new StringBuilder(b);
			int i = 0;
			if (sb.charAt(0) != 'i'){
				System.out.println("input is not a valid Bencoded Integer");
				return null;
			}
			sb.deleteCharAt(i);
			if (i < sb.length() && sb.charAt(i) == '-'){
				i++;
			}
			while (i < sb.length() && Character.isDigit(sb.charAt(i))){
				i++;
			}
			if (i < sb.length() && sb.charAt(i) != 'e'){
				return null;
			}
			sb.deleteCharAt(i);
			return sb.toString();
		}
}
