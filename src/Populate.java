import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Populate {

	/*
	 * This method populates the list of greetings
	 */


	public static ArrayList<String> greetings() {
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

	public static ArrayList<String> closures() {
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
	
	/*
	 * This method populates the list of prompts
	 */
	public static ArrayList<String> prompts() {
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
	 * This method populates the list of affirmations
	 */
	public static ArrayList<String> affirmations() {
		ArrayList<String> temp = new ArrayList<String>();
		Scanner affirmationer;
		try {
			affirmationer = new Scanner(new File("Affirmations.txt"));
			affirmationer.useDelimiter(", *");
			while (affirmationer.hasNext()) {
				temp.add(affirmationer.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/*
	 * This method populates the list of negations
	 */
	public static ArrayList<String> negations() {
		ArrayList<String> temp = new ArrayList<String>();
		Scanner negationer;
		try {
			negationer = new Scanner(new File("Negations.txt"));
			negationer.useDelimiter(", *");
			while (negationer.hasNext()){
				temp.add(negationer.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/*
	 * This method populates the list of inquiries
	 */
	public static ArrayList<String> inquiries() {
		ArrayList<String> temp = new ArrayList<String>();
		Scanner inquirer;
		try {
			inquirer = new Scanner(new File("Inquiries.txt"));
			inquirer.useDelimiter(", *");
			while (inquirer.hasNext()){
				temp.add(inquirer.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}

}
