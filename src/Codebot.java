import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Codebot {

	private ArrayList<String> greetings;
	private ArrayList<String> closures;
	private ArrayList<String> affirmations;
	private ArrayList<String> negations;
	private ArrayList<String> prompts;
	private ArrayList<String> inquiries;
	private Scanner scan;
	private String lastSaid;
	
	public Codebot(){
		greetings = Populate.greetings();
		closures = Populate.closures();
		prompts = Populate.prompts();
		affirmations = Populate.affirmations();
		negations = Populate.negations();
		inquiries = Populate.inquiries();
		scan = new Scanner(System.in);
		lastSaid="";
		beginSession();
	}
	
	/*
	 * This method gets the ball rolling
	 */
	private void beginSession() {
		Random rand = new Random();
		String greeting = greetings.get(rand.nextInt(greetings.size()));
		lastSaid = greeting;
		System.out.println(greeting);
		String response = scan.nextLine();
		respond(response);
		
	}

	/*
	 * This method takes a string as an input and selects a valid response
	 */
	private void respond(String response) {
		if (Comparison.contains(greetings,response)){
			prompt();
		} 
		else if (Comparison.contains(affirmations, response)&&Comparison.contains(prompts, lastSaid)){
			inquire();
		}
		else if (Comparison.contains(negations, response)&&Comparison.contains(prompts, lastSaid)){
			endSession();
		}
		else if (Comparison.contains(closures,response)){
			endSession();
		}
		else{
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

	/*
	 * This method prompts the user to ask a question.
	 */
	private void prompt(){
		Random rand = new Random();
		String prompt = prompts.get(rand.nextInt(prompts.size()));
		lastSaid = prompt;
		System.out.println(prompt);
		String response = scan.nextLine();
		respond(response);
	}
	
	/*
	 * This method asks the user what he/she needs help with
	 */
	private void inquire() {
		Random rand = new Random();
		String inquiry = inquiries.get(rand.nextInt(inquiries.size()));
		lastSaid = inquiry;
		System.out.println(inquiry);
		String response = scan.nextLine();
		respond(response);
		
	}

}
