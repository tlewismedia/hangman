/**
 * Driver Class for Hangman game
 * @author Stephen
 */


import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;



public class HangmanGame{
		
	static ArrayList <String> wordsList = new ArrayList<String>();
	static ArrayList <Character> lettersList = new ArrayList<Character>();
	static File lettersFile;
	static File wordsFile;
	
	/**
	 * Main method for starting the game
	 * @throws IOException 
	 * @throws AlreadyGuessedException 
	 * @throws InvalidInputException 
	 * @param arg[] takes in arguments for paths letters.txt, words.txt, 
	 * 			and player type 'h','s','r', and 'c' 
	 */
	public static void main (String args[]) throws IOException, InvalidInputException, AlreadyGuessedException {

		String lettersFilePath = new String();
		String wordsFilePath = new String();
		String opponentType = new String();
		String selectedWord = new String();
		
		boolean AIPlayer = false;
		
		boolean done = false;
		
		while (!done){
			if(args.length == 3) {
				lettersFilePath = args[0];
				wordsFilePath = args[1];
				opponentType = args[2];
				done = true;
			
			}else {
				System.out.println("Missing arguments");
			}
		}
		
		getArrayLists(wordsFilePath, lettersFilePath);
		
		selectedWord = selectWord();
		
		/*
		 * Pulled the selectedWord parameter out for testing.
		 * Player playerType = new RandomPlayer(wordsList, lettersList, selectedWord);
		 * Modified by EmmA Murray 7/13/13
		 * Modified by Tom Lewis 7/21/13 -- fixed Null pointer exception, player wasn't being successfully instantiated
		 */
		
		//initialized for GUI instantiation below
		Player playerType = new RandomPlayer(wordsList, lettersList);
		
		if (opponentType.charAt(0) == 'r') {
			playerType = new RandomPlayer(wordsList, lettersList);
			AIPlayer = true;
		}
		else if (opponentType.charAt(0) == 's') {
			playerType = new SystemPlayer(wordsList, lettersList);
			AIPlayer = true;
		}
		else if (opponentType.charAt(0) == 'c') {
			playerType = new CleverPlayer(wordsList, lettersList);
			
			AIPlayer = true;
		}
		else if (opponentType.charAt(0) == 'h') {
			AIPlayer = false;
		}
	

		
		//HangmanLogic hangman;
		if (AIPlayer){
			
			System.out.println("playertype is: "+ playerType);
			System.out.println("about to make AI player");
			
			HangmanLogic hangman = new HangmanLogic(selectedWord, 10, playerType);
			
			//call the GUI				
			HangmanFrame frame = new HangmanFrame (hangman, playerType, opponentType.charAt(0));
			frame.playGame(opponentType.charAt(0));
		}
		else if (!AIPlayer){
			HangmanLogic hangman = new HangmanLogic('h');
			
			//call the GUI				
			HangmanFrame frame = new HangmanFrame (hangman, playerType, opponentType.charAt(0));
			frame.playGame(opponentType.charAt(0));
		}
		
			
		
	}
	
	/**
	 * Builds the array lists of letters and words
	 * @param 	wordsFileName 
	 * @param	lettersFileName
	 * 
	 **/
	public static void getArrayLists(String wordsFileName, String lettersFileName) {
		
		wordsFile = new File(wordsFileName);
		lettersFile = new File(lettersFileName);
		
		try{
			if(!wordsFile.exists()) throw new FileNotFoundException ("Error Reading: " +wordsFile.getName());
			
			Scanner in = new Scanner(wordsFile);
			
			while (in.hasNextLine()){
				wordsList.add(in.nextLine());
			}
			
			in.close();
		}catch (IOException e){
			System.out.println("Error: " + e.getMessage());
		}
		
		try{
			if(!lettersFile.exists()) throw new FileNotFoundException ("Error Reading: " +lettersFile.getName());
			
			Scanner in2 = new Scanner(lettersFile);
			
			while (in2.hasNextLine()){
				lettersList.add(in2.nextLine().charAt(0));
			}
			
			in2.close();
		}catch (IOException e){
			System.out.println("Error: " + e.getMessage());
		}
				
	}
	
	/**
	 * Selects a random word from wordlist
	 * @return selected String selected word
	 **/
	public static String selectWord(){
		
		Random randomGenerator = new Random();
		
		int index = randomGenerator.nextInt(wordsList.size());
		
		String selected = wordsList.get(index);
		
		return selected;
		
	}
	
	
	
}


