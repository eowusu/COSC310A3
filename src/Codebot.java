import java.awt.Desktop;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class Codebot {
        /*
         * This class is responsible for handling all user interaction. It is the central class.
         */

        /*
         * This is where our library gets stored into memory for fast access
         */
        private ArrayList<String> greetings;
        private ArrayList<String> closures;
        private ArrayList<String> affirmations;
        private ArrayList<String> negations;
        private ArrayList<String> prompts;
        private ArrayList<String> reprompts;
        private ArrayList<String> topicprompts;
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
        
        /*
         * This is our constructor. It populates the library and begins the session
         */
        public Codebot(){
                greetings = Populate.greetings();
                closures = Populate.closures();
                prompts = Populate.prompts();
                reprompts = Populate.reprompts();
                topicprompts = Populate.topicprompts();
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
         * This method gets the ball rolling. It will greet the user and then scan for their response
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
                        response = Punctuation.space(response);        //correctly format their response for searching through libraries
                        if (Comparison.contains(greetings,response)){
                                /*
                                 * If they greet codebot, codebot will reply with a prompt
                                 */
                                prompt();
                        } 
                        else if (Comparison.contains(affirmations, response)&&(lastSaidType.equals("prompt")||lastSaidType.equals("reprompt"))){
                                /*
                                 * If codebot prompted them, i.e. "Do you want help?" and they respond with yes (or any other affirmation)
                                 * then codebot inquires as to what they need help with
                                 */
                                inquire();
                        }
                        else if (Comparison.contains(negations, response)&&lastSaidType.equals("prompt")){
                                /*
                                 * If codebot prompted them, i.e. "Do you want help?" and they respond with no (or any other negation)
                                 * then they are done and codebot ends the session. Might want to confirm they are done before ending
                                 * the session
                                 */
                                endSession();
                        }
                        else if (Comparison.contains(topics, response)){
                                /*
                                 * Regardless of what was previously said, if they type a response that has a topic
                                 * in our library, codebot responds with the basic information about that topic
                                 */
                                tutor(response);
                        }
                        else if (Comparison.contains(negations, response)&&(lastSaidType.equals("tutor")||lastSaidType.equals("reprompt"))){
                                /*
                                 * If they negate our topicprompt (dont need additional help for a topic), prompt them
                                 */
                                if (lastSaidType.equals("tutor")){
                                        reprompt();}
                                else {
                                        endSession();}
                        }
                        else if (Comparison.contains(affirmations, response)&&lastSaidType.equals("tutor")){
                                /*
                                 * If they affirm our topicprompt (do need additional help for a topic), instruct them
                                 */
                                instruct(lastSaid);
                        }
                        else if (Comparison.contains(compliments, response)){
                                /*
                                 * If they compliment codebot, codebot acknowledges the compliment
                                 */
                                acknowledge();
                        }
                        else if (Comparison.contains(adverbs,response)&&lastSaidType.equals("tutor")){
                                /*
                                 * If we tutored them, i.e. "integers are..." and they respond with how, or when, (or any other adverb)
                                 * then codebot provides further instruction on the topic
                                 */
                                instruct(lastSaid);
                        }
                        else if (Comparison.contains(closures,response)){
                                /*
                                 * If they say bye, or any other closure, codebot ends the session
                                 */
                                endSession();
                        }
                        else{
                                /*
                                 * In the worst case, codebot asks if they want it to look up the answer for them
                                 */
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
                if (Comparison.contains(affirmations, newresponse)){ //if they say yes, search it
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
                else{ //otherwise prompt again
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
                reprompt();
                
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
                Iterator<String> keySet = topics.keySet().iterator();        // returns an iterable list of topics from the hashmap
                String currentKey = null;
                Scanner topicscan;
                while(keySet.hasNext() && !result){                //This will return each individual key to search through since some are comprise of multiple keywords
                        currentKey = keySet.next();
                        topicscan = new Scanner(currentKey);
                        topicscan.useDelimiter(", *");
                        while(topicscan.hasNext()){                //Once codebot has the whole key with all keywords for the topic, it looks for matches from what the user inputed
                                String currentString = topicscan.next().toLowerCase();
                                currentString = Punctuation.space(currentString);
                                if(topic.toLowerCase().contains(currentString)){
                                        result = true;                //if coedbot finds a match, it now knows the topic they were searching for and can use the key to find the instructions
                                        break;
                                }
                        }
                }
                String value = topics.get(currentKey);        //this will look up the instructions to return to them to the user
                lastSaid = currentKey;
                lastSaidType = "tutor";
                System.out.println(value);
                topicprompt();

        }
        
        /*
         * This method picks a random reprompt and prints it
         */
        private void topicprompt() {
                Random rand = new Random();
                String topicprompt = topicprompts.get(rand.nextInt(topicprompts.size()));
                System.out.println(topicprompt.substring(1));        
                String response = scan.nextLine();
                respond(response);
                
        }
        

        /*
         * This method asks if the user has any other questions
         */
        private void reprompt() {
                Random rand = new Random();
                String reprompt = reprompts.get(rand.nextInt(reprompts.size()));
                lastSaidType="reprompt";
                System.out.println(reprompt.substring(1));        
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

