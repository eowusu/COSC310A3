import java.util.Scanner;


public class Duncan {

	public static void main(String[] args) {
		Codebot bot = new Codebot();
		String test = "One, Two, Three";
		Scanner scan = new Scanner(test);
		scan.useDelimiter(", *");
		while(scan.hasNext())
		{
			System.out.println(scan.next());
		}
		}

}
