import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class OddWords{

	private static BinarySearchTree bst = new BinarySearchTree();
	
	public OddWords(){
		bst = new BinarySearchTree();
	}
	
	
	/*
	*main(String [] args)
	*Runs the processFile method if a .txt file is passed in
	*Else, exits the program and gives the user a message
	*@param args The file we want to pass in
	*/
	public static void main(String [] args){
		if(args.length != 0){
			for(String file : args){
				if(file.contains(".txt")){
					processFile(file);
				}
				else
				System.out.println("Error: Please Provide A Text File");
			}
		}
		else
			System.out.println("Please Provide A File To Process");
	}
	
	
	/*
	*processFile(String filename)
	*Processes the words in a text file, making them lowercase.
	*A word is a sequence of alphanumeric characters
	*Inserts the word if it isn't in the BinarySearchTree
	*Deletes the word if it has a duplicate in the binarysearchtree
	*Then calls printLexicon()
	*@param filename The text file to process
	*/
	public static void processFile(String filename){
		Scanner reader = null;
		try{
			reader = new Scanner(new FileReader(filename));
			while(reader.hasNextLine()){
				String word = reader.nextLine().trim().toLowerCase();
				System.out.println(processWord(word));
			}
		}
		catch(IOException error){
			error.printStackTrace();
		}
		finally{
			if(reader != null)
				reader.close();
			printLexicon();
		}
	}
	
	/*
	*processWord(String finalWord)
	*Processes the word to delete or insert into the BST
	*@param finalWord The word to process into the BST
	*/
	public static String processWord(String word){
		if(word != null){
			String finalWord = finalString(word);
			if(bst.search(finalWord) == false){
				bst.insert(finalWord);
				return "INSERTED";
			}
			else{
				bst.remove(finalWord);
				return "DELETED";
			}	
		}
		else
			return "NULL";
	}
	
	/*
	*finalString(String word)
	*Processes a string, making it completely alphanumeric
	*If a character in the string is not alphanumeric, replace it with a " "
	*@param word The word to process
	*@return finalWord The processed word
	*/
	public static String finalString(String word){
		if(word != null){
			String finalWord = "";
			for(int i = 0; i < word.length(); i++){
				if(isAlphaNum(word.charAt(i))){
					finalWord = finalWord + String.valueOf(word.charAt(i));
				}
				else
					finalWord = finalWord + " ";
			}
			return finalWord;
		}
		else
			return " ";
	}
	
	
	/*
	*printLexicon()
	*Prints the string "Lexicon"
	*Then dumps all the words in the BST in-order
	*/
	public static void printLexicon(){
		System.out.print("Lexicon: ");
		bst.dump();
	}
	
	
	/*
	*isAlphaNum(char letter)
	*Checks if a character is alphanumeric using patterns and regular expressions
	*@param letter The letter we are processing
	*@return true If the character is alphanumeric
	*@Author techiedelight.com
	*Reference in readMe document
	*/
	public static boolean isAlphaNum(char letter){
		String letter1 = String.valueOf(letter);
		Pattern alphaNum = Pattern.compile("^[a-zA-Z0-9]*$");
		return alphaNum.matcher(letter1).find();
	}	
		
	
	
}
