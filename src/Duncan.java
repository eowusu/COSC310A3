import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duncan {

	public static void main(String[] args) {
<<<<<<< HEAD
		ArrayList<String> temp = new ArrayList<String>();
		Scanner greeter;
		try {
			greeter = new Scanner(new File("Greetings.txt"));
			greeter.useDelimiter(", *");
			while (greeter.hasNext()){
				temp.add(greeter.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(temp.get(3));
=======
		Codebot bot = new Codebot();
>>>>>>> duncan
	}

}
