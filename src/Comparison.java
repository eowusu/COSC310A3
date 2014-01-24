import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Comparison {
	/*
	 * The purpose of this class is to search through the libraries to determine if a term is contained in
	 * the given library
	 */

	/*
	 * This method searches for a string in an arraylist, and returns if it is there or not
	 */
	public static boolean contains(ArrayList<String> list, String str){
		boolean result = false;
		for(int i = 0; i<list.size();i++){
			if (str.toLowerCase().contains(list.get(i).toLowerCase())){
				result = true;
				break;
			}
		}
		return result;
	}
	
	/*
	 * Comment this matt!
	 */
	public static boolean contains(HashMap<String,String> map, String str){
		boolean result = false;
		Iterator<String> keySet = map.keySet().iterator();
		String currentKey = keySet.next();
		Scanner scan = new Scanner(currentKey);
		scan.useDelimiter(", *");
		while(currentKey != null&& !result){
			while(scan.hasNext()){
				String currentString = scan.next().toLowerCase();
				currentString = Punctuation.space(currentString);
				if(str.toLowerCase().contains(currentString)){
					result = true;
					break;
				}
			}
			if(keySet.hasNext()){
				currentKey = keySet.next();
				scan = new Scanner(currentKey);
				scan.useDelimiter(", *");
			}
			else
				currentKey = null;
			
		}
		return result;
	}

}
