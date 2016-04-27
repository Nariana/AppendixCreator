import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class IndexCreator {

	public static void main(String[] args) throws IOException {
		
		// create scanner to read user input
		Scanner s = new Scanner(System.in);
		String content = null;
		do{
		System.out.println("Welcome to the Index Creator. Enter a valid file path to begin.");
		
		// read input file path
//		String inpath = s.next();
//		System.out.println("You entered " + inpath);
//		System.out.println("Enter a valid path for the output file you would like to create. (example: \"output.txt\"");
//		
//		// read output file path
//		//String outpath = s.next();
//		String outpath = "C:/Users/ysands/Desktop/dummy.txt";
//		System.out.println("You entered " + outpath);
		

		
		// read contents of file into string
//		content = readFile(inpath);
		content = "!!The z the cats cats ran for their life! But I didn't stop it; alligators are my favorite.";
		// if file has <= 1 character, prompt user to try again
		if (content.length() <= 1)
			System.out.println("File looks empty or invalid. Try again.");
		}
		while(content.length() <= 1);
		
		// close scanner
		s.close();
		
		// parse XML/HTML out of string
		content = parseTags(content);
		
		// parse punctuation and numbers out of string
		content = parseNonWords(content);
		
		// parse filler words out of string
		content = parseStopWords(content);
		
		// create list for index now that main words are identified
		String [] a = content.split("\\s");
		List<String> index = Arrays.asList(a);
		
//		index = stemWords(index);
		
		JDBC j = new JDBC();
		j.createDB(index);
		//j.selectIndex();
		//j.selectGroup();
		//j.selectGroupAsc();
		j.selectGroupDesc();
		j.closeDB();
	}
	
	// read contents of file into string
	public static String readFile(String inpath){
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
	public static String parseTags(String content){
		content = content.replaceAll("<[^>]+>", "");
		return content;
	}
	
	// removes all punctuation and numbers in file
	public static String parseNonWords(String content){
		// removes all digits and punctuation, excluding conjoining apostrophes because they make conjunctions
		content = content.replaceAll("^\\p{Punct}+|\\W+\\p{Punct}+|\\p{Punct}+\\W+|\\p{Punct}+$"," ").replaceAll("\\p{Punct}&&[^\u0027]|\\p{Digit}", " ");
		//System.out.println(content);
		return content;
	}
	
	// removes stop words in file, such as article adjectives
	public static String parseStopWords(String content){
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
		content = content.replaceAll("^.{1}\\s+|\\s+.{1}\\s+|\\s+.{1}$", " ");
		//System.out.println(content);
		
		return content;
		
	}
	
	// TODO
	public static List<String> stemWords(List<String> index){
		Stemmer s = new Stemmer();
//		for(int i = 0; i < index.size(); i++){
//			index.set(i, s.stem(index.get(i)));
//		}
//		
		
		return index;
	}

}
