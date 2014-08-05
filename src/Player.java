import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A Player makes guesses to solve the keyphrase and keeps track of their own
 * guesses
 * @tomlewis
 */
public abstract class Player {
	protected ArrayList<Character> failedGuesses = new ArrayList<Character>();
	protected ArrayList<Character> successGuesses = new ArrayList<Character>();
	protected ArrayList<Character> validLetters = new ArrayList<Character>();
	protected ArrayList<String> wordBank = new ArrayList<String>();
	protected String keyWord = "";
	protected String maskPhrase = "";
	
	

	/**
	 * Constructs a Player with a set of known words
	 * @param wordFilePath file path of the word bank text file
	 * @param lettersFilePath file path of the valid letters file
	 * @throws FileNotFoundException
	 */
	public Player(ArrayList<String> words, ArrayList<Character> charSet)
			throws FileNotFoundException {
		wordBank = words;
		validLetters = charSet;
		genNewKeyWord();
	}

	/**
	 * Returns a guess
	 * @return guess
	 */
	public abstract char getGuess();

	/**
	 * Adds to failed guesses
	 * @param fGuess return this char if the guess failed
	 */
	public void addFailedGuess(char fGuess) {
		failedGuesses.add(fGuess);
	}

	/**
	 * Adds to a successful guesses
	 * @param sGuess return this char is the guess was successful
	 */
	public void addSuccessGuess(char sGuess) {
		successGuesses.add(sGuess);
	}
	
	/**
	 * Gets the current keyWord
	 * @return keyword
	 */
	public String getKeyWord(){
		return keyWord;
	}
	
	/**
	 * Sets the keyWord
	 * @param word String that sets the keyword
	 */
	public void setKeyWord(String word){
		keyWord = word;
	}
	
	/**
	 *Creates a new random keyWord and sets it
	 */
	public void genNewKeyWord(){
		Random r = new Random();
	    int size = wordBank.size();
	    int randomInt = r.nextInt(size);
		String newWord;
		newWord = wordBank.get(randomInt);
		if (newWord == keyWord){ //prevents a repeat
			if (randomInt == size) {
				newWord = wordBank.get(randomInt-1); //if end of arrayList
			}
			else {
				newWord = wordBank.get(randomInt+1); 
			}
		}
		keyWord = newWord; //set the keyWord
		
	}
	
	/**
	 * Creates a new random number of guesses allowed.
	 * @return randomInt an integer between 10 and 15
	 * @author EmmA Murray
	 */
	public int genNumGuesses()
	{
		final int MAX_GUESSES = 15;
		final int MIN_GUESSES = 10;
		
		Random r = new Random();
		int randomInt = r.nextInt(MAX_GUESSES - MIN_GUESSES + 1) + MIN_GUESSES; //random number between 10 and 15
		
		return randomInt;
	}

	
	/**
	 *Sets the masked phrase
	 *@param phrase String for phrase
	 */
	public void setMaskPhrase(String phrase){
		maskPhrase = phrase;
	}
	
}
