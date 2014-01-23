import java.util.ArrayList;
import java.util.HashMap;


public class Comparison {

	public static boolean contains(ArrayList<String> list, String str){
		boolean result = false;
		for(int i = 0; i<list.size();i++){
			if (str.equalsIgnoreCase(list.get(i))){
				result = true;
				break;
			}
		}
		return result;
	}
	
	public static boolean contains(HashMap<String,String> map, String str){
		String value = map.get(str);
		return value != null;
	}

}
