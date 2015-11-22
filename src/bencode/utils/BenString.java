/**
 * 
 */
package bencode.utils;

import bencode.encode.*;
import bencode.decode.*;
public class BenString extends BenItem{

	//Bencoded and Plaintext representation of String
	//Stores each and can access and output these values.
	
	String value;
	String bencode;
	
	public BenString(String v, boolean bencoded){
		//Boolean value tells contructor if the passed String is Bencoded or Plaintext
		//Programmer must know what encoding type they are working with to instantiate properly
		if (bencoded && Character.isDigit(v.charAt(0))){
			bencode = v;
			Decoder d = new Decoder();
			value = d.decode(v);
			if (value == null){
				System.out.println("Invalid bencoded string-value was passed into constructor");
			}
		}else{
			value = v;
			Encoder e = new Encoder();
			bencode = e.encode(v);
		}
	}
	
	public String getValue(){
		return value;
	}
	
	public String getBencode(){
		return bencode;
	}
	
	public void setValue(String v){
		Decoder d = new Decoder();
		value = d.decode(v);
		if (value == null){
			System.out.println("Invalid bencoded string-value was passed into constructor");
		}
	}
	
	public void setBencode(String b){
		Encoder e = new Encoder();
		bencode = e.encode(b);
	}
	
	public void add(BenItem b){
		System.out.println("BenString may not add BenItems");
	}
	
	public void addToList(BenItem b){
		System.out.println("BenString may not add BenItems");
	}
	
	public boolean isString(){
		return true;
	}
	
	public String toString(){
		return "STRING";
	}
}
