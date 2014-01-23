import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Codebot {

	private ArrayList<String> greetings;
	private ArrayList<String> closures;
	private ArrayList<String> affirmations;
	private ArrayList<String> negations;
	private ArrayList<String> prompts;
	private ArrayList<String> inquiries;
	private ArrayList<String> compliments;
	private ArrayList<String> acknowledgements;
	private ArrayList<String> adverbs;
	private ArrayList<String> verbs;
	private ArrayList<String> pronouns;
	private HashMap<String,String> topics;
	private Scanner scan;
	private String lastSaid;
	private String lastSaidType;
	
	public Codebot(){
		greetings = Populate.greetings();
		closures = Populate.closures();
		prompts = Populate.prompts();
		affirmations = Populate.affirmations();
		negations = Populate.negations();
		inquiries = Populate.inquiries();
		compliments = Populate.compliments();
		acknowledgements = Populate.acknowledgements();
		adverbs = Populate.adverbs();
		verbs = Populate.verbs();
		pronouns = Populate.pronouns();
		topics = Populate.topics();
		scan = new Scanner(System.in);
		lastSaid="";
		lastSaidType="";
		beginSession();
	}
	
	/*
	 * This method gets the ball rolling
	 */
	private void beginSession() {
		Random rand = new Random();
		String greeting = greetings.get(rand.nextInt(greetings.size()));
		lastSaid = greeting;
		lastSaidType = "greeting";
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
		else if (Comparison.contains(affirmations, response)&&lastSaidType.equals("prompt")){
			inquire();
		}
		else if (Comparison.contains(negations, response)&&lastSaidType.equals("prompt")){
			endSession();
		}
		else if (Comparison.contains(topics, response)&&lastSaidType.equals("inquiry")){
			tutor(response);
		}
		else if (Comparison.contains(compliments, response)){
			acknowledge();
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
	 * This method acknowledges compliments
	 */
	private void acknowledge() {
		Random rand = new Random();
		String acknowledgement = acknowledgements.get(rand.nextInt(acknowledgements.size()));
		lastSaid = acknowledgement;
		lastSaidType = "acknowledgement";
		System.out.println(acknowledgement);
		String response = scan.nextLine();
		respond(response);
		
	}

	/*
	 * Provides the user help when given a topic
	 */
	private void tutor(String topic) {
		String value;
		if (topic.charAt(topic.length()-1)=='s'){
			String singular = topic.substring(0,topic.length()-1);
			value = topics.get(singular);
		}
		else{
			value = topics.get(topic);
		}
		lastSaid = value;
		lastSaidType = "tutor";
		System.out.println(value);
		String response = scan.nextLine();
		respond(response);
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
		lastSaidType = "prompt";
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
		lastSaidType = "inquiry";
		System.out.println(inquiry);
		String response = scan.nextLine();
		respond(response);
		
	}

}
