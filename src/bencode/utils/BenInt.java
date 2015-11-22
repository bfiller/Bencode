/**
 * 
 */
package bencode.utils;

import bencode.decode.*;
import bencode.encode.*;
public class BenInt extends BenItem{

	private int value;
	private String bencode;
	
	public BenInt(String s){
		//wraps the Integer to contain both Bencode and plaintext representations
		//Accepts Int represented as a bencoded String
		//to accept a plaintext integer, please pass as integer literal into the constructor
		Decoder d = new Decoder();
		bencode = s;
		String val = d.decodeInteger(s);
		value = Integer.parseInt(val);
		if (bencode == null){
			System.out.println("The string passed to BenInt Constructor does not represent an integer nor a bencoded integer");
		}
		
	}
	
	public BenInt(int value){
		//Takes an integer literal and stores it as both representations.
		//to accept a bencoded integer value, please pass the bencoded string value to the overloaded constructor.
		Encoder e = new Encoder();
		this.value = value;
		bencode = e.encode(value);
	}
	
	//Basic Getters and setters 
	public int getValue(){
		return value;
	}
	
	public String getBencode(){
		return bencode;
	}
	
	public void setFromLiteral(int value){
		Encoder e = new Encoder();
		this.value = value;
		bencode = e.encode(value);
	}
	
	public void setFromBencode(String b){
		Decoder d = new Decoder();
		bencode = d.decodeInteger(b);
		if (bencode == null){
			System.out.println("The string passed to BenInt Constructor does not represent an integer nor a bencoded integer");
		}
	}
	
	public void add(BenItem b){
		System.out.println("BenInt may not add BenItems");
	}
	
	public void addToList(BenItem b){
		System.out.println("BenInt may not add BenItems");
	}
	
	public String toString(){
		return "INT";
	}

}
