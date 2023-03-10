import java.util.*;



public class Parse {
	StringDivider dividedString;
	
	public enum Prev{
		NOUN,
		ADJECTIVE,
		NAADJECTIVE,
		TEADJECTIVE,
		VERB,
		VERBADJ,
		TOPICPARTICLE,
		SUBJECTPARTICLE,
		OBJECTPARTICLE,
		TARGETPARTICLE,
		DEPARTICLE,
		DECLARATIVE,
		START
	}
	
	public Parse(StringDivider s) {
		dividedString = s;
	}
	
	public boolean ValidSentence() {
		boolean end = false;
		int index = 0;
		Prev previous = Prev.START;
		while(!end) {
			String check = dividedString.subs.get(index);
			//check if word is noun
			//System.out.println(previous.toString());
			if(dividedString.NounList.contains(check) || dividedString.ProunounList.contains(check)) {
				//nouns can follow just about anything but other nouns and the end of the sentence. If the previous word wasn't either, continue
				if(dividedString.NaAdjectives.contains(check)) {
					if(previous == Prev.TEADJECTIVE || dividedString.subs.get(index + 1).equals("な")) {
						previous = Prev.NAADJECTIVE;
						index++;
						continue;
					}
				}
				if(previous != Prev.NOUN && previous != Prev.DECLARATIVE) {
					previous = Prev.NOUN;
					index++;
					continue;
				}
				else {
					//System.out.println(previous.toString());
					/*for(String s : dividedString.NounList) {
						System.out.println(s);
					}*/
					//System.out.println(check);
					System.out.println("Nounproblem");
					return false;
				}
			}
			
			if(dividedString.NounVerbs.contains(check)) {
				if(previous == Prev.OBJECTPARTICLE || previous == Prev.TARGETPARTICLE || previous == Prev.DEPARTICLE || previous == Prev.SUBJECTPARTICLE
						|| previous == Prev.TOPICPARTICLE || previous == Prev.START) {
					previous = Prev.NOUN;
					index++;
					continue;
				}
				else {
				System.out.println("Nounverbproblem");
				return false;
				}
			}
			
			if(dividedString.IAdjectives.contains(check) || dividedString.NaAdjectives.contains(check)) {
				if(previous == Prev.TEADJECTIVE || previous == Prev.START || previous == Prev.SUBJECTPARTICLE || previous == Prev.TOPICPARTICLE
						|| previous == Prev.TARGETPARTICLE || previous == Prev.DEPARTICLE) {
					if(dividedString.IAdjectives.contains(check)) {
						previous = Prev.ADJECTIVE;
					}
					else {
						previous = Prev.NAADJECTIVE;
					}
					//previous = Prev.ADJECTIVE;
					index++;
					continue;
				}
				else {
					System.out.println("adjproblem");
					return false;
				}
			}
			
			if(check.equals("な")) {
				if(previous == Prev.NAADJECTIVE){
					index++;
					continue;
				}
				else {
					System.out.println("naproblem");
					return false;
				}
			}
			
			if(dividedString.PastAdjNouns.contains(check)) {
				if(previous == Prev.TEADJECTIVE || previous == Prev.START || previous == Prev.TOPICPARTICLE || previous == Prev.SUBJECTPARTICLE) {
					previous = Prev.VERB;
					index++;
					continue;
				}
				else {
					System.out.println("pastproblem");
					return false;
				}
			}
			
			if(dividedString.TeIAdjectives.contains(check) || dividedString.TeNaAdjectives.contains(check)) {
				if(previous != Prev.ADJECTIVE && previous != Prev.NAADJECTIVE && previous != Prev.DECLARATIVE && previous != Prev.OBJECTPARTICLE) {
					previous = Prev.TEADJECTIVE;
					index++;
					continue;
				}
				else {
					System.out.println("teadjproblem");
					return false;
				}
			}
			
			//check if word is verb-like adjectives and so on. Check if each element is following something it's allowed to in the grammar.
			if(dividedString.VerbLikeAdjectives.contains(check)) {
				if(previous == Prev.SUBJECTPARTICLE) {
					previous = Prev.VERBADJ;
					index++;
					continue;
				}
				else {
					System.out.println("adjvproblem");
					return false;
				}
			}
			
			if(check.equals(dividedString.TopicParticle)){
				if(previous == Prev.NOUN) {
					previous = Prev.TOPICPARTICLE;
					index++;
					continue;
				}
				else {
					System.out.println("topproblem");
					return false;
				}
			}
			
			if(check.equals(dividedString.SubjectParticle)) {
				if(previous == Prev.NOUN) {
					previous = Prev.SUBJECTPARTICLE;
					index++;
					continue;
				}
				else {
					System.out.println("subproblem");
					return false;
				}
			}
			
			if(check.equals(dividedString.ObjectParticle)) {
				if(previous == Prev.NOUN) {
					previous = Prev.OBJECTPARTICLE;
					index++;
					continue;
				}
				else {
					System.out.println("objproblem");
					return false;
				}
			}
			
			if(check.equals(dividedString.TargetParticle)) {
				if(previous == Prev.NOUN) {
					previous = Prev.TARGETPARTICLE;
					index++;
					continue;
				}
				else {
					System.out.println("tarproblem");
					return false;
				}
			}
			
			if(check.equals(dividedString.DeParticle)) {
				if(previous == Prev.NOUN) {
					previous = Prev.DEPARTICLE;
				}
				else {
					System.out.println("deproblem");
					return false;
				}
			}
			
			if(dividedString.Verbs.contains(check) || dividedString.TeiruVerbs.contains(check) || dividedString.PastVerbs.contains(check)) {
				if(previous == Prev.SUBJECTPARTICLE || previous == Prev.TOPICPARTICLE || previous == Prev.OBJECTPARTICLE || previous == Prev.TARGETPARTICLE
						|| previous == Prev.DEPARTICLE ||previous == Prev.START) {
					previous = Prev.VERB;
					index++;
					continue;
				}
				else {
					System.out.println("verbproblem");
					return false;
				}
			}
			
			if(check.equals(dividedString.Declarative)) {
				if(previous == Prev.NOUN || previous == Prev.NAADJECTIVE || previous == Prev.VERBADJ) {
					previous = Prev.DECLARATIVE;
					index++;
					continue;
				}
				else {
					System.out.println("decproblem");
					return false;
				}
			}
			
			if(check.equals("。") || check.equals("？")) {
				if(previous != Prev.SUBJECTPARTICLE && previous != Prev.TOPICPARTICLE && previous != Prev.OBJECTPARTICLE && previous != Prev.TARGETPARTICLE
						&& previous != Prev.DEPARTICLE && previous != Prev.START) {
					end = true;
				}
				else {
					System.out.println("endproblem");
					return false;
				}
			}
			
			index++;
		}
		return true;
	}
}
