import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class DocumentParser {
	// read contents of file into string
		public String readFile(String inpath){
			String message = null;
			try{
				message = new String(Files.readAllBytes(Paths.get(inpath)));
			}
			catch(IOException e) {
				message = "."; // return 1 character to trigger trying again
			}
			return message;
		}
		
		// removes XML/HTML tags in file
		public String parseTags(String content){
			content = content.replaceAll("<[^>]+>", "");
			return content;
		}
		
		// removes all punctuation and numbers in file
		public String parseNonWords(String content){
			// removes all digits and punctuation, excluding conjoining apostrophes because they make conjunctions
			content = content.replaceAll("^\\p{Punct}+|\\W+\\p{Punct}+|\\p{Punct}+\\W+|\\p{Punct}+$"," ").replaceAll("\\p{Punct}&&[^\u0027]|\\p{Digit}", " ");
			//System.out.println(content);
			return content;
		}
		
		// removes stop words in file, such as article adjectives
		public String parseStopWords(String content){
			// hard-coded URL with text file for stop words
			String stopURL = "C:/Users/ysands/workspace/IndexCreator/stopwords.txt";
			// read words in file into string
			String stopWords = readFile(stopURL);
			
			// split string into string list w/ stop word at each array
			String [] s = stopWords.split("\\s+");
			List<String> stopWordsList = Arrays.asList(s);
			//System.out.println(stopWordsList);
			
			// iterate over list to remove stop words
			for(int i = 0; i < stopWordsList.size(); i++){
				content = content.replaceAll("(?i)\\s*\\b"+ stopWordsList.get(i)+"\\b\\s*", " ");
			}
			
			// remove single characters
			//content = content.replaceAll("\\s+[^\\s]{1}\\s+", " ");
			content = content.replaceAll("^.{1}\\s+|\\s+.{1}\\s+|\\s+.{1}$", " ").replaceAll("\\p{Punct}", "");
			//System.out.println(content);
			
			return content;
			
		}
		
		// TODO
//		public List<String> stemWords(List<String> index){
//			Stemmer s = new Stemmer();
//			for(int i = 0; i < index.size(); i++){
//				index.set(i, s.stem(index.get(i)));
//			}
//			
//			
//			return index;
//		}

}
