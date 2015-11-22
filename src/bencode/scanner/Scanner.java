/**
 * 
 */
package bencode.scanner;

import java.util.*;
import java.io.*;

import bencode.utils.*;
public class Scanner {
	
	//The scanner takes a String of Bencoded values and uses a tokenizer operation to return a list of 
	//BenItems. These BenItems represent both their encoded and Plaintext representations of their values.
	
	//The scanner tokenizer is implemented with a stack and a list in order to handle dictionaries and lists of 
	//varying depths.
	
	//The Stack holds the BenItem (abstract class) for which we are currently adding Ints and Strings. 
	//We pop the last Benlist or BenDict from the stack each time we see the 'e' character.
	
	//The scanner list output (List<BenItem>) can be printed out neatly using the BenPrinter class. (see included main function)
	//The BenPrinter class recursively prints out every item in the list.
	
	Stack<BenItem> stack;
	List<BenItem> list;
	boolean top;
	public Scanner(){
		stack = new Stack<>();
		list = new ArrayList<BenItem>();
	}
	
	public List<BenItem> tokenize(String str){
		//str must be a properly bencoded string of characters. 
		//remove whitespace
		str = str.replaceAll("\\s+",""); 
		if (str == ""){
			return new ArrayList<BenItem>();
		}else if (str == null){
			System.out.println("string passed was null");
			return new ArrayList<BenItem>();
		}else{
		//we create the top of our stack, a BenList, an object which contains a List<BenItem> 
		//which is returned at the end of tokenizer.
		BenList top = new BenList();
		stack.push(top);
		for (int i = 0; i < str.length(); i++){
			//Here we do a basic O(n) run through of the String using iterator i
			StringBuilder sb = new StringBuilder();
			char c = str.charAt(i);                                                                                     
			if (c == 'i'){
				//handle integer
				sb.append('i');
				i++;
				if (i < str.length() && str.charAt(i) == '-'){
					sb.append(str.charAt(i));
					i++;
				}
				while (i < str.length() && Character.isDigit(str.charAt(i))){
					sb.append(str.charAt(i));
					i++;
				}
				if (i < str.length() && str.charAt(i) != 'e'){
					System.out.println("integer never terminated with e");
				}else{
					sb.append('e');
				}
				//once out Stringbuilder contains the String for just the Bencoded value
				//we wrap the value in a BenItem child class 
				stack.peek().add(new BenInt(sb.toString()));
				//We push this BenItem onto whatever is at the top of our stack currently
			}else if (Character.isDigit(c)){
				//handle String
				while (Character.isDigit(c) && i < str.length()){
					sb.append(c);
					i++;
					c = str.charAt(i);
				}
				if (c != ':'){
					System.out.println("invalid String found");
					break;
				}
				int val = Integer.parseInt(sb.toString());
				while (val > 0 && i < str.length()){
					sb.append(str.charAt(i));
					val--;
					i++;
				}
				sb.append(str.charAt(i));
				stack.peek().add(new BenString(sb.toString(), true));
			}else if (c == 'd'){
				//handle dictionary
				//we push a new BenDict onto our stack
				stack.push(new BenDict());
			}else if (c == 'l'){
				//handle list
				//we push a new list on top of our stack
				stack.push(new BenList());
			}else if (c == 'e'){
				//we have found a lone 'e' and we pop our stack to reach the layer above
				BenItem bi = stack.pop();
				stack.peek().add(bi);
			}else{
				System.out.println("The scanner found the passed string to be invalid."
						+ " Please check that the string you entered into tokenizer is correct.");
				return new ArrayList<BenItem>();
			}
		}
		return top.getList();
		}
	}
}