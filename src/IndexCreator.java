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
		
		// initialize document parser to parse file
		DocumentParser dp = new DocumentParser();
		
		// parse XML/HTML out of string
		content = dp.parseTags(content);
		
		// parse punctuation and numbers out of string
		content = dp.parseNonWords(content);
		
		// parse filler words out of string
		content = dp.parseStopWords(content);
		
		// create list for index now that main words are identified
		String [] a = content.split("\\s");
		List<String> index = Arrays.asList(a);
		
		// create database and prompt user
		JDBC j = new JDBC();
		j.createDB(index);
		//j.selectIndex();
		//j.selectGroup();
		//j.selectGroupAsc();
		j.selectGroupDesc();
		j.closeDB();
	}
	
}
