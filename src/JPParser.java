import java.util.*;
public class JPParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		boolean end = false;
		while(!end) {
			System.out.println("Enter sentence or type FINISH to end program: ");
			String s = in.next();
			System.out.println(s);
			if(s.equals("FINISH")) {
				break;
			}
			else {
				try {
					StringDivider sd = new StringDivider(s);
					sd.DivideString();
					Parse p = new Parse(sd);
					boolean valid = p.ValidSentence();
					if(valid) {
						System.out.println("Sentence is valid!");
					}
					else {
						System.out.println("Sentence is not valid!");
					}
				}
				catch(Exception e) {
					System.out.println("Something went wrong! Make sure your sentence ends in a period or question mark and doesn't contain non-Japanese characters.");
				}
			}
		}
	}

}
