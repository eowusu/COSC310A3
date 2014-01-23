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
			else if (str.equalsIgnoreCase(list.get(i)+"s")){
				result = true;
				break;
			}
		}
		return result;
	}
	
	public static boolean contains(HashMap<String,String> map, String str){
		String value;
		if (str.charAt(str.length()-1)=='s'){
			String singular = str.substring(0,str.length()-1);
			value = map.get(singular);
		}
		else{
			value = map.get(str);
		}
		return value != null;
	}

}
