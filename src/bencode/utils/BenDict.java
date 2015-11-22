/**
 * 
 */
package bencode.utils;

import java.util.*;
public class BenDict extends BenItem{

	//BenDict holds an ArrayList of size 2 arrays (BenItem[2]) of BenItem objects
	//The dictionary must start with a String for the key
	//Anything can be the stored value, including other dicts or lists.
	
	private List<BenItem[]> dict;
	//idx keeps track of the index of our most current key-value pair
	private int idx;
	
	public BenDict(){
		this.dict = new ArrayList<BenItem[]>();
	}
	
	public void add(BenString bstring, BenItem bitem){
		//we can add a value directly to a BenDict object given a key and value pair
		BenItem[] pair = new BenItem[2];
		pair[0] = bstring;
		pair[1] = bitem;
		dict.add(pair);
	}
	
	public void add(BenItem[] pair){
		//We can also pass an array. If the size is appropriate it will accept.
		if (pair[0].isString() && pair.length == 2){
			dict.add(pair);
		}else{
			System.out.println("invalid key or invalid array size is (not 2)");
		}
	}
	
	public boolean isDict(){
		return true;
	}
	
	public List<BenItem[]> getDict(){
		return dict;
	}

	@Override
	public void addToList(BenItem b) {}

	@Override
	public void add(BenItem b) {
		//our add function which takes a BenItem and decides if it can add it to the dict.
		//If so, it stores it in the appropriate array cell 
		if (idx == 0){
			BenItem[] pair = new BenItem[2];
			pair[0] = null;
			pair[1]  = null;
			if (b.isString()){
				pair[0] = b;
				this.dict.add(pair);
				idx++;
			}else{
				System.out.println("Error: Invalid Bencoding format: "
						+ "String expected for key in dictionary.add()");
			}
		}else if (idx == 1){
			(dict.get(dict.size() - 1))[1] = b;
			idx = 0;
		}else{
			System.out.println("idx has reached an invalid value");
		}
	}

	@Override
	public String toString(){
		return "DICT";
	}
}
