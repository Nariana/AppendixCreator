import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
//import java.nio.file.*;

public class IndexCreator {

	public static void main(String[] args) throws IOException {

		// create scanner to read user input
		Scanner s = new Scanner(System.in);
		String input = "";
		String content = "";

		do{
			System.out.println("Welcome to the Index Creator. Enter a valid file path to begin. Enter \"quit\" to close.");

			// read input file path
			input = s.next();
			System.out.println("You entered " + input);

			if(input.equals("quit")){
				System.out.println("Goodbye.");
				break;
			}

			// initialize document parser to parse file
			DocumentParser dp = new DocumentParser();
			
			// read contents of file into string
			//content = dp.readFile(input);
			content = "<html>!!The z the cats cats cats ran for their life! But I didn't stop it; alligators are my life. They're my favorite.</html>";
			// if file has <= 1 character, prompt user to try again
			if (content.length() <= 1){
				System.out.println("File looks empty or invalid. Try again.");
				continue;
			}

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

			do{
				System.out.println("Enter one of the following commands:\n" +
						"1 = Display all words as-is in order of appearance \n" +
						"2 = Display all words and count in order of appearance \n" +
						"3 = Display all words and count in alphabetical ascending order \n" +
						"4 = Display all words and count in alphabetical descending order \n" +
						"5 = Display all words and count in numerical ascending order \n" +
						"6 = Display all words and count in numerical descending order \n" +
						"7 = Start over \n");
				
				input = s.next();
				System.out.println("You entered " + input);
				if(input.equals("1"))
					j.selectIndex();
				
				else if(input.equals("2")){
					j.selectGroup();
				}
				else if(input.equals("3")){
					j.selectGroupAlphaAsc();
				}
				else if(input.equals("4")){
					j.selectGroupAlphaDesc();
				}
				else if(input.equals("5")){
					j.selectGroupNumAsc();
				}
				else if(input.equals("6")){
					j.selectGroupNumDesc();
				}
				else if(input.equals("7")){
					content = "";
					break;
				}
				else
					continue;

			}
			while(true);
			//close DB
			j.closeDB();
		}
		while(content.length() <= 1);
		
		//close scanner
		s.close();
	}
	
}