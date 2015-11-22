/**
 * 
 */
package bencode.utils;

/**
 * @author bfill_000
 *
 */
import java.util.*;

public class BenList extends BenItem{
	
	private List<BenItem> blist;
	//A list representation which makes it easy to handle multiple layers of Lists (listception)
	//Works well with various Data Stuctures such as Stack. Makes it easy to organize Bencoded data
	
	//More functionality could easily be added taking advantage of the inner List. However, to keep simplicity,
	//only certain methods have been implemented
	
	public BenList(){
		this.blist = new ArrayList<BenItem>();
	}
	
	public void add(BenItem b){
		blist.add(b);
	}
	
	public int size(){
		return blist.size();
	}
	
	public List<BenItem> getList(){
		return blist;
	}
	
	public void clearList(){
		blist.clear();
	}
	
	public boolean isList(){
		return true;
	}

	@Override
	public void addToList(BenItem b) {}
	
	public String toString(){
		return "LIST";
	}

}
