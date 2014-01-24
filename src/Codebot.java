import java.awt.Desktop;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	private HashMap<String,String> instructions;
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
		instructions = Populate.instructions();
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
		System.out.println(greeting.substring(1));
		String response = scan.nextLine() ;
		respond(response);
		
	}

	/*
	 * This method takes a string as an input and selects a valid response
	 */
	private void respond(String response) {
		if (response.isEmpty()){
			prompt();
		}
		else{
			response = Punctuation.space(response);
			if (Comparison.contains(greetings,response)){
				prompt();
			} 
			else if (Comparison.contains(affirmations, response)&&lastSaidType.equals("prompt")){
				inquire();
			}
			else if (Comparison.contains(negations, response)&&lastSaidType.equals("prompt")){
				endSession();
			}
			else if (Comparison.contains(topics, response)){
				tutor(response);
			}
			else if (Comparison.contains(compliments, response)){
				acknowledge();
			}
			else if (Comparison.contains(adverbs,response)&&lastSaidType.equals("tutor")){
				instruct(lastSaid);
			}
			else if (Comparison.contains(closures,response)){
				endSession();
			}
			else{
				google(response);
			}
		}
	}
	
	
	/*
	 * This method takes the user question and performs a google search in their default browser
	 * This is the worst case scenario of codebot not knowing what to do :(
	 */
	private void google(String response) {
		System.out.println("Sorry, I am not that smart...yet\nWant me to search that for you?");
		String newresponse = scan.nextLine();
		newresponse = Punctuation.space(newresponse);
		if (Comparison.contains(affirmations, newresponse)){
			try {
				String q = response.replace(' ', '+').substring(1,response.length()-1);
				Desktop desktop = java.awt.Desktop.getDesktop();
				URL oURL = new URL("https://www.google.com/#q="+q);
				desktop.browse(oURL.toURI());
				}
			catch (Exception e) {
				e.printStackTrace();
				}
			prompt();
		}
		else{
			prompt();
		}
		
	}

	/*
	 * This method provides further instruction based on the input topic
	 */
	private void instruct(String topic) {
		String value = instructions.get(topic);
		lastSaidType = "instruction";
		System.out.println(value);
		String response = scan.nextLine();
		respond(response);
		
	}

	/*
	 * This method acknowledges compliments
	 */
	private void acknowledge() {
		Random rand = new Random();
		String acknowledgement = acknowledgements.get(rand.nextInt(acknowledgements.size()));
		lastSaid = acknowledgement;
		lastSaidType = "acknowledgement";
		System.out.println(acknowledgement.substring(1));
		String response = scan.nextLine();
		respond(response);
		
	}

	/*
	 * Provides the user help when given a topic
	 */
	private void tutor(String topic) {
		boolean result = false;
		Iterator<String> keySet = topics.keySet().iterator();
		String currentKey = null;
		Scanner topicscan;
		while(keySet.hasNext() && !result){
			currentKey = keySet.next();
			topicscan = new Scanner(currentKey);
			topicscan.useDelimiter(", *");
			while(topicscan.hasNext()){
				String currentString = topicscan.next().toLowerCase();
				currentString = Punctuation.space(currentString);
				if(topic.toLowerCase().contains(currentString)){
					result = true;
					break;
				}
			}
		}
		String value = topics.get(currentKey);
		lastSaid = currentKey;
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
		System.out.println(closure.substring(1));	
	}

	/*
	 * This method prompts the user to ask a question.
	 */
	private void prompt(){
		Random rand = new Random();
		String prompt = prompts.get(rand.nextInt(prompts.size()));
		lastSaid = prompt;
		lastSaidType = "prompt";
		System.out.println(prompt.substring(1));
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
		System.out.println(inquiry.substring(1));
		String response = scan.nextLine();
		respond(response);
	}

}
