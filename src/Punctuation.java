
public class Punctuation {

	public static String space(String response) {
		response = " "+response+" ";
		response = response.replace(",", " ,");
		response = response.replace("!", " !");
		response = response.replace(".", " .");
		response = response.replace("?", " ?");
		return response;
	}

}
