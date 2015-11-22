/**
 * 
 */
package bencode.utils;

public abstract class BenItem {
	
	//abstract BenItem class which allows the Scanner to utilize the Stack DataStructure
	//Allows Bencoded String to be parsed into List of Items which can be individually useful
	//For example, one could easily search for BenDict items in a list of BenItems (see child classes)
	//Children : BenDict, BenList, BenString, BenInt
	
	public abstract void add(BenItem b);
	public abstract void addToList(BenItem b);
	public abstract String toString();
	public boolean isList(){
		return false;
	}
	
	public boolean isDict(){
		return false;
	}
	
	public boolean isString(){
		return false;
	}
}
	
