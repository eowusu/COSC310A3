import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Codebot {

	private ArrayList<String> greetings;
	private ArrayList<String> closures;
<<<<<<< HEAD
=======
	private ArrayList<String> prompts;
>>>>>>> duncan
	private Scanner scan;
	
	public Codebot(){
		greetings = populateGreetings();
		closures = populateClosures();
<<<<<<< HEAD
=======
		prompts = populatePrompts();
>>>>>>> duncan
		scan = new Scanner(System.in);
		beginSession();
		
	}
	
<<<<<<< HEAD
=======

>>>>>>> duncan
	/*
	 * This method gets the ball rolling
	 */
	private void beginSession() {
<<<<<<< HEAD
		Random rand = new Random(greetings.size());
		String greeting = greetings.get(rand.nextInt());
=======
		Random rand = new Random();
		String greeting = greetings.get(rand.nextInt(greetings.size()));
>>>>>>> duncan
		System.out.println(greeting);
		String response = scan.nextLine();
		respond(response);
		
	}

	/*
	 * This method takes a string as an input and selects a valid response
	 */
	private void respond(String response) {
<<<<<<< HEAD
		
		
	}
=======
		if (greetings.contains(response)){
			prompt();
		} else if (closures.contains(response)){
			endSession();
		}else{
			System.out.println("Sorry, I am not that smart...yet");
			String newresponse = scan.nextLine();
			respond(newresponse);
		}
		
	}
	/*
	 * This method stops the ball from rolling
	 */
	private void endSession() {
		Random rand = new Random();
		String closure = closures.get(rand.nextInt(closures.size()));
		System.out.println(closure);	
	}

>>>>>>> duncan

	/*
	 * This method populates the list of greetings
	 */
<<<<<<< HEAD
	private ArrayList<String> populateClosures() {
=======
	private ArrayList<String> populateGreetings() {
>>>>>>> duncan
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
<<<<<<< HEAD
	private ArrayList<String> populateGreetings() {
=======
	private ArrayList<String> populateClosures() {
>>>>>>> duncan
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
	
<<<<<<< HEAD
=======
	/*
	 * This method populates the list of prompts
	 */
	private ArrayList<String> populatePrompts() {
		ArrayList<String> temp = new ArrayList<String>();
		Scanner prompter;
		try {
			prompter = new Scanner(new File("Prompts.txt"));
			prompter.useDelimiter(", *");
			while (prompter.hasNext()){
				temp.add(prompter.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
	/*
	 * This method prompts the user to ask a question.
	 */
	private void prompt(){
		Random rand = new Random();
		String prompt = prompts.get(rand.nextInt(prompts.size()));
		System.out.println(prompt);
		String response = scan.nextLine();
		respond(response);
	}
	
>>>>>>> duncan

}
