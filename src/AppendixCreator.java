import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class AppendixCreator {

	public static void main(String[] args) throws IOException {
		
		// create scanner to read user input
		Scanner s = new Scanner(System.in);
		String content = null;
		do{
		System.out.println("Welcome to the Appendix Creator. Enter a valid file path to begin.");
		
		// read input file path
		String inpath = s.next();
		System.out.println("You entered " + inpath);
		System.out.println("Enter a valid path for the output file you would like to create. (example: \"output.txt\"");
		
		// read output file path
		String outpath = s.next();
		System.out.println("You entered " + outpath);
		

		
		// read contents of file into string
		content = readFile(inpath);
		// if file has <= 1 character, prompt user to try again
		if (content.length() <= 1)
			System.out.println("File looks empty or invalid. Try again.");
		}
		while(content.length() <= 1);
		
		s.close();
		content = parseTags(content);
		
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
		System.out.println(content);
		return content;
	}

}
