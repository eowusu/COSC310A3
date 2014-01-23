import java.util.ArrayList;


public class Comparison {

	public static boolean contains(ArrayList<String> list, String str){
		boolean result = false;
		for(int i = 0; i<list.size()-1;i++){
			if (str.equalsIgnoreCase(list.get(i))){
				result = true;
				break;
			}
		}
		return result;
	}

}
