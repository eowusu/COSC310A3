import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Codebot {

	private ArrayList<String> greetings;
	private ArrayList<String> closures;
	private Scanner scan;
	
	public Codebot(){
		greetings = populateGreetings();
		closures = populateClosures();
		scan = new Scanner(System.in);
		beginSession();
		
	}
	
	/*
	 * This method gets the ball rolling
	 */
	private void beginSession() {
		Random rand = new Random(greetings.size());
		String greeting = greetings.get(rand.nextInt());
		System.out.println(greeting);
		String response = scan.nextLine();
		respond(response);
		
	}

	/*
	 * This method takes a string as an input and selects a valid response
	 */
	private void respond(String response) {
		
		
	}

	/*
	 * This method populates the list of greetings
	 */
	private ArrayList<String> populateClosures() {
		ArrayList<String> temp = new ArrayList<String>();
		Scanner greeter;
		try {
			greeter = new Scanner(new File("Greetings.txt"));
			greeter.useDelimiter(", *");
			while (greeter.hasNext()){
				temp.add(greeter.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/*
	 * This method populates the list of closures
	 */
	private ArrayList<String> populateGreetings() {
		ArrayList<String> temp = new ArrayList<String>();
		Scanner closer;
		try {
			closer = new Scanner(new File("Closures.txt"));
			closer.useDelimiter(", *");
			while (closer.hasNext()){
				temp.add(closer.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
	

}
