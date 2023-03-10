import java.util.*;
public class StringDivider {
	public static Grammar g = new Grammar();
	public JPTerminals t = new JPTerminals();
	public String Sentence;
	public List<String> subs;
	List<String> VerbLikeAdjectives = Arrays.asList(t.VerbLikeAdjectives);
	List<String> IAdjectives = Arrays.asList(t.Adjectives);
	List<String> NaAdjectives = Arrays.asList(t.NaAdjectives);
	List<String> TeIAdjectives = Arrays.asList(t.TeAdjectives);
	List<String> TeNaAdjectives = Arrays.asList(t.TeNaAdjectives);
	List<String> NounList = new ArrayList<String>(Arrays.asList(t.Nouns));
	List<String> ProunounList = Arrays.asList(t.Pronouns);
	List<String> PastAdjNouns = new ArrayList<String>(Arrays.asList(t.PastAdjNouns));
	List<String> Particles = Arrays.asList(g.Particles);
	List<String> Verbs = new ArrayList<String>(Arrays.asList(t.IruEruConjugationVerbs));
	List<String> NounVerbs = new ArrayList<String>(Arrays.asList(t.NounVerbs));
	List<String> TeiruVerbs = new ArrayList<String>(Arrays.asList(t.TeiruVerbs));
	List<String> PastVerbs = new ArrayList<String>(Arrays.asList(t.PastVerbs));
	String TopicParticle = "は";
	String SubjectParticle = "が";
	String ObjectParticle = g.ObjectParticle;
	String TargetParticle = g.TargetParticle;
	String DeParticle = g.DeParticle;
	String Declarative = "だ";
	
	public StringDivider(String sen) {
		Sentence = sen;
		subs = new ArrayList<String>();
		List<String> BVerbs = new ArrayList<String>(Arrays.asList(t.BuConjugationVerbs));
		List<String> TVerbs = new ArrayList<String>(Arrays.asList(t.TuConjugationVerbs));
		List<String> GVerbs = new ArrayList<String>(Arrays.asList(t.GuConjugationVerbs));
		List<String> KVerbs = new ArrayList<String>(Arrays.asList(t.KuConjugationVerbs));
		List<String> MVerbs = new ArrayList<String>(Arrays.asList(t.MuConjugationVerbs));
		List<String> RVerbs = new ArrayList<String>(Arrays.asList(t.RuConjugationVerbs));
		List<String> UVerbs = new ArrayList<String>(Arrays.asList(t.UConjugationVerbs));
		List<String> SVerbs = new ArrayList<String>(Arrays.asList(t.SuConjugationVerbs));
		List<String> SuruVerbs = new ArrayList<String>(Arrays.asList(t.SuruVerbs));
		List<String> Suru = new ArrayList<String>(Arrays.asList(t.IrregularVerbSuru));
		List<String> Kuru = new ArrayList<String>(Arrays.asList(t.IrregularVerbKuru));
		List<String> NVerb = new ArrayList<String>(Arrays.asList(t.NuConjugationVerb));
		Verbs.addAll(0, BVerbs);
		Verbs.addAll(0, TVerbs);
		Verbs.addAll(0, GVerbs);
		Verbs.addAll(0, KVerbs);
		Verbs.addAll(0, MVerbs);
		Verbs.addAll(0, RVerbs);
		Verbs.addAll(0, UVerbs);
		Verbs.addAll(0, SVerbs);
		Verbs.addAll(0, SuruVerbs);
		Verbs.addAll(0, Suru);
		Verbs.addAll(0, Kuru);
		Verbs.addAll(0, NVerb);
	}
	
	//Divide string into component parts
	public void DivideString() {
		boolean end = false;
		int index = 0;
		int prevIndex = 0;
		while(!end) {
			String segment;
			String particle;
			if(Particles.contains(Sentence.substring(index, index + 1))) {
				if(Sentence.substring(index, index + 1).equals(DeParticle)) {
					String s1 = "Placeholder";
					String s2 = "Placeholder";
					String s3 = "Placeholder";
					if(index - 1 >= 0) {
						s1 = Sentence.substring(index - 1, index + 1);
					}
					if(index - 2 >= 0) {
						s2 = Sentence.substring(index - 2, index + 1);
					}
					if(index - 3 >= 0) {
						s3 = Sentence.substring(index - 3, index + 1);
					}
					if(TeNaAdjectives.contains(s1)) {
						segment = s1;
						subs.add(segment);
						prevIndex = index + 1;
					}
					else if(TeNaAdjectives.contains(s2)) {
						segment = s2;
						subs.add(segment);
						prevIndex = index + 1;
					}
					else if(TeNaAdjectives.contains(s3)) {
						segment = s3;
						subs.add(segment);
						prevIndex = index + 1;
					}
					else if(Sentence.charAt(index + 1) == 'い' && Sentence.charAt(index + 2) == 'る' && (Sentence.charAt(index - 1) == 'い' || Sentence .charAt(index - 1) == 'ん')) {
						segment = Sentence.substring(prevIndex, index + 3);
						//System.out.println(segment);
						//String d = Sentence.substring(index, index + 1);
						prevIndex = index + 3;
						/*if(prevIndex > Sentence.length()) {
							prevIndex = Sentence.length() - 1;
							index = prevIndex;
							continue;
						}*/
						subs.add(segment);
						//index = prevIndex;
						//continue;
						//subs.add(d);
					}
					else {
						segment = Sentence.substring(prevIndex, index);
						particle = Sentence.substring(index, index + 1);
						prevIndex = index + 1;
						subs.add(segment);
						subs.add(particle);
					}
				}
				else {
					String s1 = "Placeholder";
					String s2 = "Placeholder";
					String s3 = "Placeholder";
					if(index + 2 < Sentence.length()) {
						s1 = Sentence.substring(index, index + 2);
					}
					if(index + 3 < Sentence.length()) {
						s2 = Sentence.substring(index, index + 3);
					}
					if(index + 4 < Sentence.length()) {
						s3 = Sentence.substring(index, index + 4);
					}
					if(IsParticle(s1, s2, s3)) {
						segment = Sentence.substring(prevIndex, index);
						particle = Sentence.substring(index, index + 1);
						prevIndex = index + 1;
						subs.add(segment);
						subs.add(particle);
					}
				}
				/*String s1 = Sentence.substring(index, index + 2);
				String s2 = Sentence.substring(index, index + 3);
				String s3 = Sentence.substring(index, index + 4);
				if(IsParticle(s1, s2, s3)) {
					segment = Sentence.substring(prevIndex, index);
					particle = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					subs.add(segment);
					subs.add(particle);
				}*/
			}
			else {
				if(Sentence.substring(index, index + 1).equals(Declarative) && Sentence.charAt(index + 1) != 'っ') {
					segment = Sentence.substring(prevIndex, index);
					String d = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					subs.add(segment);
					subs.add(d);
				}
				else if(Sentence.substring(index, index + 1).equals("。") || Sentence.substring(index, index + 1).equals("？")) {
					segment = Sentence.substring(prevIndex, index);
					String d = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					subs.add(segment);
					subs.add(d);
					end = true;
				}
				else if(Sentence.substring(index, index + 1).equals("て") || Sentence.substring(index, index + 1).equals("で")) {
					if(Sentence.charAt(index + 1) == 'い' && Sentence.charAt(index + 2) == 'る') {
						segment = Sentence.substring(prevIndex, index + 3);
						//System.out.println(segment);
						//String d = Sentence.substring(index, index + 1);
						prevIndex = index + 3;
						if(prevIndex > Sentence.length()) {
							prevIndex = Sentence.length() - 1;
						}
						subs.add(segment);
						index = prevIndex;
						//index = prevIndex;
						//continue;
						//subs.add(d);
					}
					else {
						segment = Sentence.substring(prevIndex, index + 1);
						//System.out.println(segment);
						//String d = Sentence.substring(index, index + 1);
						prevIndex = index + 1;
						subs.add(segment);
						//subs.add(d);
					}
					/*segment = Sentence.substring(prevIndex, index + 1);
					System.out.println(segment);
					//String d = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					subs.add(segment);
					//subs.add(d);*/
				}
				else if(Sentence.substring(index, index + 1).equals("い") && Sentence.charAt(index + 1) != 'て' && Sentence.charAt(index + 1) != 'で' && Sentence.charAt(index + 1) != 'る'
						&& Sentence.charAt(index + 1) != 'た' && Sentence.charAt(index + 1) != 'だ') {
					segment = Sentence.substring(prevIndex, index + 1);
					//String d = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					subs.add(segment);
					//subs.add(d);
				}
				/*else if(Sentence.substring(index, index + 1).equals("な")) {
					segment = Sentence.substring(prevIndex, index + 1);
					//String d = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					subs.add(segment);
					//subs.add(d);
				}*/
				else if(Sentence.substring(index, index + 1).equals("る") || Sentence.substring(index, index + 1).equals("う") ||
						Sentence.substring(index, index + 1).equals("ぐ") || Sentence.substring(index, index + 1).equals("ぶ") || Sentence.substring(index, index + 1).equals("む") ||
						Sentence.substring(index, index + 1).equals("つ") && !Sentence.substring(index - 2, index).equals("てい") &&
						!Sentence.substring(index - 3, index).equals("いでい")) {
					if(Sentence.charAt(index + 1) == 'の') {
						segment = Sentence.substring(prevIndex, index + 2);
						//String d = Sentence.substring(index, index + 1);
						prevIndex = index + 2;
						subs.add(segment);
						//subs.add(d);
					}
					else {
						segment = Sentence.substring(prevIndex, index + 1);
						//String d = Sentence.substring(index, index + 1);
						prevIndex = index + 1;
						subs.add(segment);
						//subs.add(d);
					}
					/*segment = Sentence.substring(prevIndex, index + 1);
					//String d = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					subs.add(segment);
					//subs.add(d);*/
				}
				else if(Sentence.substring(index, index + 1).equals("く")) {
					if(Sentence.charAt(index + 1) == 'の') {
						segment = Sentence.substring(prevIndex, index + 2);
						//String d = Sentence.substring(index, index + 1);
						prevIndex = index + 2;
						subs.add(segment);
						//subs.add(d);
					}
					else {
						if(Sentence.charAt(index + 1) == 'て') {
							index++;
							continue;
						}
						else {
							segment = Sentence.substring(prevIndex, index + 1);
							//String d = Sentence.substring(index, index + 1);
							prevIndex = index + 1;
							subs.add(segment);
							//subs.add(d);
						}
					}
				}
				else if(Sentence.substring(index, index + 1).equals("す")) {
					if(Sentence.charAt(index + 1) == 'る') {
						if(Sentence.charAt(index + 2) == 'の') {
							segment = Sentence.substring(prevIndex, index + 3);
							//String d = Sentence.substring(index, index + 1);
							prevIndex = index + 3;
							subs.add(segment);
							//subs.add(d);
						}
						else {
							segment = Sentence.substring(prevIndex, index + 2);
							//String d = Sentence.substring(index, index + 1);
							prevIndex = index + 2;
							subs.add(segment);
							//subs.add(d);
						}
						/*segment = Sentence.substring(prevIndex, index + 2);
						//String d = Sentence.substring(index, index + 1);
						prevIndex = index + 2;
						subs.add(segment);
						//subs.add(d);*/
					}
					else {
						if(Sentence.charAt(index + 1) == 'の') {
							segment = Sentence.substring(prevIndex, index + 2);
							//String d = Sentence.substring(index, index + 1);
							prevIndex = index + 2;
							subs.add(segment);
							//subs.add(d);
						}
						else {
							segment = Sentence.substring(prevIndex, index + 1);
							//String d = Sentence.substring(index, index + 1);
							prevIndex = index + 1;
							subs.add(segment);
							//subs.add(d);
						}
					}
				}
				else if(Sentence.substring(index, index + 1).equals("た") || (Sentence.substring(index, index + 1).equals("だ") && (Sentence.charAt(index - 1) == 'い'
						|| Sentence.charAt(index - 1) == 'ん'))) {
					segment = Sentence.substring(prevIndex, index + 1);
					//String d = Sentence.substring(index, index + 1);
					prevIndex = index + 1;
					/*if(prevIndex >= Sentence.length()) {
						prevIndex = Sentence.length() - 1;
						index = prevIndex;
						continue;
					}*/
					subs.add(segment);
					//subs.add(d);
				}
				else if(Sentence.substring(index, index + 1).equals("な")) {
					String s1 = "Placeholder";
					String s2 = "Placeholder";
					String s3 = "Placeholder";
					if(index - 1 >= 0) {
						s1 = Sentence.substring(index - 1, index);
					}
					if(index - 2 >= 0) {
						s2 = Sentence.substring(index - 2, index);
					}
					if(index - 3 >= 0) {
						s3 = Sentence.substring(index - 3, index);
					}
					if(NaAdjectives.contains(s1)) {
						segment = s1;
						subs.add(segment);
						particle = Sentence.substring(index, index + 1);
						subs.add(particle);
						prevIndex = index + 1;
					}
					else if(NaAdjectives.contains(s2)) {
						segment = s2;
						subs.add(segment);
						particle = Sentence.substring(index, index + 1);
						subs.add(particle);
						prevIndex = index + 1;
					}
					else if(NaAdjectives.contains(s3)) {
						segment = s3;
						subs.add(segment);
						particle = Sentence.substring(index, index + 1);
						subs.add(particle);
						prevIndex = index + 1;
					}
					else {
						/*segment = Sentence.substring(prevIndex, index);
						particle = Sentence.substring(index, index + 1);
						prevIndex = index + 1;
						subs.add(segment);
						subs.add(particle);*/
					}
				}
			}
			
			index++;
			//System.out.println(prevIndex + " " + index);
		}
		
		
	}
	
	public boolean IsParticle(String s1, String s2, String s3) {
		boolean isParticle = true;
		//Check one, two, and three characters ahead to see if the character is a particle or part of a word.
		if(NounList.contains(s3) || NounList.contains(s2) || NounList.contains(s1)) {
			isParticle = false;
		}
		return isParticle;
	}
}
