
public class Punctuation {

	public static String space(String response) {
		if(response.charAt(0) != ' ')
			response = " "+response;
		if(response.charAt(response.length()-1) != ' ')
			response = response + " ";
		response = response.replace(",", " ,");
		response = response.replace("!", " !");
		response = response.replace(".", " .");
		response = response.replace("?", " ?");
		return response;
	}

}
