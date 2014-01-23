import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Comparison {

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
	
	public static boolean contains(HashMap<String,String> map, String str){
		boolean result = false;
		Iterator<String> keySet = map.keySet().iterator();
		String currentKey = keySet.next();
		Scanner scan = new Scanner(currentKey);
		scan.useDelimiter(", *");
		while(keySet.hasNext() && !result){
			while(scan.hasNext()){
				String currentString = scan.next().toLowerCase();
				currentString = Punctuation.space(currentString);
				if(str.toLowerCase().contains(currentString)){
					result = true;
					break;
				}
			}
			currentKey = keySet.next();
			scan = new Scanner(currentKey);
			scan.useDelimiter(", *");
		}
		return result;
	}

}
