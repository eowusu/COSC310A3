import java.util.ArrayList;
/*This class will correct potential spelling mistakes or misunderstood words from the user
 * 
 */
public class Matcher {
	
	private ArrayList<String> greetings;
    private ArrayList<String> closures;
    private ArrayList<String> affirmations;
    private ArrayList<String> negations;
    private ArrayList<String> compliments;
    private ArrayList<String> acknowledgements;
    private ArrayList<String> adverbs;
    private ArrayList<String> verbs;
    private ArrayList<String> pronouns;
    private ArrayList<String> topics;
    private ArrayList<String> common;
    private ArrayList<String> master;
	private int msize;
	
    public Matcher(){
        greetings = Populate.greetings();
        closures = Populate.closures();
        affirmations = Populate.affirmations();
        negations = Populate.negations();
        compliments = Populate.compliments();
        acknowledgements = Populate.acknowledgements();
        adverbs = Populate.adverbs();
        verbs = Populate.verbs();
        pronouns = Populate.pronouns();
        topics = Populate.topiclist();
        common = Populate.common();
        
        /*The master arraylist contains all words that might be recognized by our system
         * 
         */
        master = new ArrayList<String>();
        master.addAll(greetings);
        master.addAll(closures);
        master.addAll(affirmations);
        master.addAll(negations);
        master.addAll(compliments);
        master.addAll(acknowledgements);
        master.addAll(adverbs);
        master.addAll(verbs);
        master.addAll(pronouns);
        master.addAll(topics);
        master.addAll(common);
        msize = master.size();        
    }
    
    /*This method uses the methods below to take input in the form of a sentence as a String
     * and return a new sentene as a String where misspelled or misundertood words are replaced with words
     * the system is more likely to understand
     */
    public String fixSentence(String str){
    	String[] arr = breakSen(str);
    	String[] newArr = fixArr(arr);
    	String newSen = buildSen(newArr);
    	return newSen;
    }
    

	/* This method takes a sentence in the form of a single String, and breaks it into a String
	 * array where each entry is a word form the original string that was seperated by a sentence.
	 */
	private String[] breakSen(String str){
		String[] sar = str.split(" ");
		for(int i = 0;i<sar.length;i++){
			sar[i].replaceAll("\\s+","");
		}
		return sar;
	}
	
	/* This method takes input in the form of two words as Strings, and their lengths, and returns
	 * and integer value for the their Levenshtein distance, that is the number of changes that must 
	 * be made to either String before they exactly match
	 * If the Levenshtein distance is greater than five, the method will return five
	 */
	private int levDis(String s1, int len1, String s2, int len2){
		int val;
		/*if one of the strings is more than 5 characters longer than the other, return a value of
		5 and stop*/
		if(Math.abs(len1-len2)>5)
			return 5;
		/*if either of the strings are empty, return the length of the other
		 * 
		 */
		if(len1==0)
			return len2;
		if(len2==0)
			return len1;
		/* If the strings are not empty, check if the last characters from each string match
		 * 
		 */
		if(s1.charAt(len1-1)==s2.charAt(len2-1))
			val = 0;
		//if they are equal, the cost is 0
		else
			val = 1;
		//if they are not, the cost is 1
		//min from delete from s1, delete form s2, delete form both
		/*The minimum value is taken from the three possible ways that the remaining part of 
		 * the string can be matched i.e
		 * 		the last character is removed from string one and the cost is incremented by 1
		 * 		the last character is removed from string two and the cost is incremented by 1
		 * 		the last character is removed from both and the cost is incremented by the val variable above
		 * 
		 */
		int toReturn = Math.min(levDis(s1, len1-1, s2, len2)+1,
				Math.min(levDis(s1, len1, s2, len2-1)+1,
				levDis(s1, len1-1, s2, len2-1)+val));
		if(toReturn>5)
			return 5;
		return toReturn;
	}
	
	/* This method takes input in the form of a String array, and replaces each contained word
	 * with the word form our master arraylist, for which the Levenshtein distance is at a minimum
	 */
	private String[] fixArr(String[] arr){
		int j;
		int currentLD = 6;
		String prospect = "";
		for (int i=0; i<arr.length; i++){
			if (arr[i].length() > 1) {
				for (j = 0; j < msize; j++) {
					String tempSt = master.get(j).toLowerCase().replaceAll("\\s+","");
					int tempLD = levDis(tempSt.toLowerCase(), tempSt.length(), arr[i].toLowerCase(),
							arr[i].length());
					//The print statement below can be used for diagnostice purposes
					//System.out.println("Levenshtein Distance from "+tempSt+" to "+arr[i]+": "+tempLD);
					if (tempLD < currentLD) {
						currentLD = tempLD;
						prospect = tempSt;
					}
					else if(tempLD == currentLD && tempSt.charAt(0) == arr[i].charAt(0)){
						currentLD = tempLD;
						prospect = tempSt;
					}
				}
				currentLD = 6;
				//The prin statement below can be used for diagnostic purposes
				//System.out.println(arr[i] + "was compared to "+j+" words and was interpreted as " + prospect);
				arr[i] = prospect;
			}
		}
		return arr;
	}
	
	/* This method, takes a String array and constructs a single String made up of each of the entries
	 * in the array, seperated by a single string to produce a sentence
	 */
	private String buildSen(String[] arr){
		String sen = "";
		for (int i = 0 ; i < arr.length; i++){
			sen = sen + arr[i] + " ";
		}
		return sen;
		
	}

}
