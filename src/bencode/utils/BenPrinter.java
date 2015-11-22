/**
 * 
 */
package bencode.utils;

import java.util.*;

import bencode.utils.*;
public class BenPrinter {

	//BenPrinter allows us to quickly and easily print out the values stored in the List<BenItem>
	//Created for use with scanner to quickly parse decode, and print Bencoded data for easy 
	//plaintext reading
	
	//OUTPUT FORMAT:
	//FOR INT AND STRING:
	//(Int literal, Bencoded Int value)
	//(Plaintext, Bencoded Text)
	
	//FOR DICT { } values are placed between braces
	//FOR LIST [ ] values are placed between square brackets
	public void print(List<BenItem> list){
		//Prints out the values within the list. If Dict or List found,
		//calls are made to recursive helper functions.
		if (list == null){
			System.out.println("null list: Traversal terminated");
		}
		for (BenItem b : list){
			if (b == null){
				System.out.println("null BenItem: Traversal terminated");
			}
			if (b.isString()){
				BenString bs = (BenString) b;
				System.out.println("(" + bs.getValue() + ", " + bs.getBencode() + ")");
			}else if (b.isList()){
				System.out.print("[");
				BenList bl = (BenList) b;
				traverseList(bl);
				System.out.print("]");
			}else if (b.isDict()){
				System.out.print("{");
				BenDict bd = (BenDict) b;
				traverseDict(bd);
				System.out.print("}");
			}else{
				BenInt bn = (BenInt) b;
				System.out.println("(" + bn.getValue() + ", " + bn.getBencode() + ")");
			}
		}
	}
	
	public static void traverseList(BenList b){
		//recursively iterate through List, depth first
		//Prints out values within the list
		for (BenItem bi : b.getList()){
			if (bi == null){
				System.out.println("null b");
			}
			if (bi.isString()){
				BenString bs = (BenString) bi;
				System.out.print("(" + bs.getValue() + ", " + bs.getBencode() + ")");
			}else if (bi.isList()){
				System.out.print("[");
				BenList bl = (BenList) bi;
				traverseList(bl);
				System.out.print("]");
			}else if (bi.isDict()){
				System.out.print("{");
				BenDict bd2 = (BenDict) bi;
				traverseDict(bd2);
				System.out.print("}");
			}else{
				BenInt bn = (BenInt) bi;
				System.out.print("(" + bn.getValue() + ", " + bn.getBencode() + ")");
			}
		}
	}
	
	public static void traverseDict(BenDict bd){
		//Recursively iterate through Dict, depth first
		//prints out values within Dict
		for (BenItem[] ba : bd.getDict()){
			for (BenItem bi : ba){
				if (bi == null){
					System.out.println("null b");
				}
				if (bi.isString()){
					BenString bs = (BenString) bi;
					System.out.print("(" + bs.getValue() + ", " + bs.getBencode() + ")");
				}else if (bi.isList()){
					System.out.print("[");
					BenList bl = (BenList) bi;
					traverseList(bl);
					System.out.print("]");
				}else if (bi.isDict()){
					BenDict bd2 = (BenDict) bi;
					traverseDict(bd2);
				}else{
					BenInt bn = (BenInt) bi;
					System.out.print("(" + bn.getValue() + ", " + bn.getBencode() + ")");
				}
			}
		}
	}
}
