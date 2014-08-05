/**
 * A SystemPlayer is a Player that makes their guesses by choosing
 * systematically in order of English language letter frequency
 * @author tomlewis
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class SystemPlayer extends Player {
	private char[] guessGuide;
	private int cur = 0;

	/**
	 * Constructs a SystemPlayer with a set of known words
	 * @throws FileNotFoundException
	 */
	public SystemPlayer(ArrayList<String> words, ArrayList<Character> charSet)
			throws FileNotFoundException {
		super(words, charSet);
	}

	/**
	 * Returns a Systematic guess
	 * @return guess the guess provided for the game
	 */
	public char getGuess() {

		char[] guessGuide = { 'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd',
				'l', 'c', 'u', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k',
				'j', 'x', 'q', 'z' };

		// while current char is in failedGuess or SuccessGuesses
		while (failedGuesses.contains(guessGuide[cur])
				|| successGuesses.contains(guessGuide[cur])) {
			cur++;
		}

		return guessGuide[cur];

	}

}