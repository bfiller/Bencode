/**
 * 
 */
package bencode.encode;

import java.util.*;

public class Encoder {
	//Encoder class that can operate on Strings or ints to convert them to Bencode values
	//After encoding, the object stores the output so that encodings may be output faster
	//in the future. 
	
	//Future optimization may include a HashMap to store previously encoded Strings.
	
	public String encode(String s){
		//takes a non-Becoded ascii string and returns Bencoded byte-string represenation.
		StringBuilder sb = new StringBuilder();
		sb.append(s.length());
		sb.append(":");
		for (char c : s.toCharArray()){
			sb.append(c);
		}
		return sb.toString();
	}
	
	public String encode(int i){
		//Takes an integer literal and encodes it as a Bencoded String.
		StringBuilder sb = new StringBuilder();
		sb.append("i");
		sb.append(i);
		sb.append("e");
		return sb.toString();
	}
}
