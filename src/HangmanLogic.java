/**
 * Contains the logic for the hangman game
 * Stephen Budidharma
 **/
import java.util.ArrayList;

public class HangmanLogic {
	
	public final int MAX_GUESSES = 15;

	private String keyPhrase;
	private int numberOfGuesses;
	private ArrayList<Character> guessedChars = new ArrayList<Character>();
	private char[] guessPhrase;
	private int failedGuesses;
	private boolean winCheck;
	
	private int counter = 0;
	private int guessesSoFar = 0;
	private Player playerType;
	private char guesser;
	
	
	/**
	 * hangmanlogic constructor.
	 * @return	none
	 * @throws	none
	 * MODIFIED by EmmA Murray
	 **/
	public HangmanLogic(char guesserIn){
		guesser = guesserIn;
		keyPhrase = "";
		numberOfGuesses = 0;
		
	}

	
	/**
	 * hangmanlogic constructor overloading.
	 * @param 	String, int, Player
	 * @return	none
	 * @throws	none
	 * 
	 **/
	public HangmanLogic(String phrase, int guesses, Player player){
		
		if(!isAlpha(phrase)){
			
			phrase.replaceAll("[^A-Za-z ]", "");	
		}
		
		phrase.toLowerCase();
		keyPhrase = phrase;
		numberOfGuesses = guesses;	
		playerType = player;
		
	}
	
	
	/**
	 * get key phrase.
	 * @param 	void
	 * @return	String
	 * @throws	none
	 * 
	 **/
	public String getKeyPhrase(){
		
		return keyPhrase;
		
	}
	
	
	/**
	 * get Number of Guesses.
	 * @param 	void
	 * @return	int
	 * @throws	none
	 * 
	 **/
	public int getNumberOfGuesses(){
		
		return numberOfGuesses;
		
	}
	
	/**
	 * Return true/false for whether or not the player has won.
	 * @return state string indicating whether or not the player has won
	 */
	public boolean getWin()
	{
		return winCheck;
	}
	
	/**
	 * Sets the key phrase.
	 * @param phrase user input from text field
	 * @author EmmA Murray 7/13/13
	 */
	public void setKeyPhrase(String phrase)
	{
		//converts input to lowercase
		if(!isAlpha(phrase)){
			
			phrase.replaceAll("[^A-Za-z ]", "");	
		}
		
		this.keyPhrase = phrase;
		//creates an array with length equal to the secret word for storing user progress
		this.guessPhrase = new char[keyPhrase.length()];
		
		for (int i = 0; i < guessPhrase.length; i++)
		{
			//sets all beginning values in the user progress array to '-'
			this.guessPhrase[i] = '-';
		}
	}
	
	
	/**
	 * Set the number of guesses allowed.
	 * @param numGuesses user input parsed as integer from text field
	 * @author EmmA Murray 7/13/13
	 */
	public void setNumberOfGuesses(int numGuesses)
	{
		this.numberOfGuesses = numGuesses;
	}
	
	
	/**
	 * guess character.
	 * @param 	char
	 * @return	boolean
	 * @throws	Exception
	 * MODIFIED by EmmA Murray
	 **/
	public boolean guessCharacter(char guess) throws InvalidInputException, AlreadyGuessedException 
	{
		
		int found =0;
		
		String guessBuffer;
		
		
		if (this.guessedChars.contains(guess))
		{				
			throw new AlreadyGuessedException("Your entry has been guessed before.");
		}
		
		guessBuffer = String.valueOf(guess);

		if (!isAlpha(guessBuffer))
		{
			throw new InvalidInputException("Your guess is not an alphabet.");
		}
		
		guessedChars.add(guess);
		counter++;
					
		for (int j=0; j < keyPhrase.length(); j++)
		{
			if(keyPhrase.charAt(j) == guess)
			{
				found++;
				
				//marks the positions of the letters in the progress array
				guessPhrase[j] = guess;
				
			}
	
		}
		/**
		 * modified to add player feedback back in
		 * Tom Lewis 7/21/13
		 */
		guessesSoFar++;
		
		if (found > 0)
		{
			if (guesser != 'h'){
				playerType.addSuccessGuess(guess);
				playerType.setMaskPhrase(getGuessPhrase());
			}
			return true;
		}
		else
		{
			if (guesser != 'h'){
				playerType.addFailedGuess(guess);
				playerType.setMaskPhrase(getGuessPhrase());
			}
			failedGuesses++;
			return false;
		}
	}
	
	/**
	 * This method returns the positions for the characters that the user 
	 * has guessed correctly.
	 * @return guessPhrase - returns the guessPhrase array as a string
	 * @author EmmA Murray 7/13/13
	 */
	public String getGuessPhrase()
	{
		String guessPhraseToString = "";
		
		for (char c : guessPhrase)
		{
			//concatenates each character in the array to form a string
			guessPhraseToString += c;
		}
		
		return guessPhraseToString;
	}
	
	/**
	 * This method returns the guessed characters.
	 * @return guessChars
	 * @author EmmA Murray 7/13/13
	 */
	public String getGuessedChars()
	{
		String guessChars = "Guessed Characters: " + guessedChars.toString();
		return guessChars;
	}
	
	/**
	 * get number of guesses left.
	 * @param 	void
	 * @return	int
	 * @throws	none
	 * 
	 **/
	public int getNumGuessesLeft(){
		
		return numberOfGuesses - failedGuesses;
		
	}
	
	
	/**
	 * check if the game is over.
	 * @param 	void
	 * @return	boolean
	 * @throws	none
	 * MODIFIED by EmmA Murray
	 **/
	public boolean isGameOver(){
		
		winCheck = true;
		
		for (char check : guessPhrase)
		{
			if (check == '-') { winCheck = false; }	
		}
		
		if (!winCheck && this.getNumGuessesLeft() > 0) 
		{ 
			return false; 
		}
		else { return true; }
		
	}
	
	/**
	 * check if guess is alpha numeric.
	 * @param 	String
	 * @return	boolean
	 * @throws	none
	 * MODIFIED by EmmA Murray
	 **/
	public boolean isAlpha(String name)
	{
		
		char[] chars = name.toCharArray();

	    for (char c : chars) 
	    {
	        if(!Character.isLetter(c)) 
	        {
	            return false;
	        }
	    }

	    return true;
	}
	
}


