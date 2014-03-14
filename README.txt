Codebot
=========

This is the repository for assignment 2 in COSC 310. The assignment is to create a simple chat agent.  
We chose to have our agent, codebot, function as a teaching assistant or tutor for first year computer 
science.


How to Compile and Run the code:

Download all files and run Test.java to personally interact with CodeBot
run ServerRun.java then ClientRun.java to observe two socketed instances of the agent have a conversation

If there are problems running, it is probably due to additional .jar files necessary to run the code.
If this is a problem, right click on the project and select Build Path>Configure Build Path.
From this window select Libraries, then Add JARs.
Navigate the folder called jarfiles within the project and select all files contained, to add to the project.
Then Select ok from the Build Path window. This should solve any problems.


Classes:

Codebot.java is the class that handles all interactions with the user. All other classes are used by this class. This class gathers information from the other classes and uses that information to attempt to answer user questions.  This class also handles regular parts of conversation such as greetings.  If the system is unable to answer a user question then this class will perform an internet search for the topic and show the user the resulting webpage.

Populate.java is the class that reads text files and stores them as either an arraylist of words (greetings.txt, prompts.txt, verbs.txt, etc), or a hashmap with multiple words as keys, and an explanation of the key as the value (topics.txt, details.txt, etc). This allows us to store a lot of information in text files without needing to search through it all every time that a user enters information to the system, since it will be in memory when the program starts up.

Punctuation.java formats each response to correspond with the way we store words in our libraries. That way we can properly search through them.  Specifically, this class puts extra spaces around words that are near punctuation as we do no store punctuation in out libraries.

Comparison.java searches through the libraries to determine if a term is contained in the given library. The libraries are defined in the text files and correspond to different parts of conversation, such as greetings, and different topics such as arrays.

Matcher.java is the class that checks for possible spelling mistakes and replaces words that aren't understood by the program with words that are more likely to be interpretted correctly. I.e. it would change varaibles into variables (a simple spelling error one might make).

Tag.java implements Stanford’s parts-of-speech tagging library, to take input in the form of text strings representing sentences, and delivers output in the form of arrays containing symbols representing the part of speech that each word of the original sentence was an instance of.

Winui.java is the class responsible for building the graphical user interface, that is used by the Codebot class.

CBnoUI.java is a version of Codebot.java, that does not use the winui.java GUI. This version is used for the networked sockets.

Server.java and Client.java build Sockets, and ServerSockets making the program network capable, and allowing the chat agent to speak with itself, with another automated agent, or with a user in a remote location.

ServerRun.java and ClientRun.java are the classes responsible for building instances of the Server.java and Client.java classes and simulating a conversation between two instances of the chat agent.

Wolf.java is a class that has implemented Wolfram Alpha's developer API and is responsible for properly constructing, executing, and returning information from search queries when information can not be found in one of the local banks of knowledge.



Recently implemented features and improvements include:

The addition of a GUI (pictured below), making the agent easier to use, and providing easy viewing of the conversation history.

Web based search capabilities have been added, allowing the agent to provide assistance on other topics related to science and mathematics. A user can now ask for information regarding our many topics, stored in files locally (many of which have been updated or added too) and receive answers immediately, or make requests regarding more peripheral topics, not directly related to computer science, and only wait a few seconds while Codebot retrieves relevant information from the net. A case of this is pictured below.

When a topic is brought up that Codebot has no locally stored information regarding, one of 5 responses is selected at random, in an attempt to resemble the confusion humans are prone to, rather than simply repeating the same phrase ad nauseam.

Spelling mistakes and plurality of words is handled through an implementation of the Levenshtein distance string metric. Due to hardware and runtime constraints, I have limited the system to only correcting spelling on words less than 8 characters in length, but if enough time is available, it will work to recognize strings of any size. The matcher class uses this to determine what an input sentence should be best interpreted as, changing phrases like “i can haz cheezbuggers.” to “I can has cheeseburger”, which although still grammatically incorrect, provides a better chance to help the user than the initial input did. These interpretations of input data are printed to the console.

When information is not available locally and web based resources are consulted, parts-of-speech tagging is used to identify the nouns, verbs and adjectives, that would provide the best search terms for the user's query. This is achieved via Stanford’s  parts-of-speech tagging library and API.

Network capabilities have been added via Java sockets, making the system accessible remotely in future versions. This could lead in time to the capability of a student at home being able to access code bot on a school server.


