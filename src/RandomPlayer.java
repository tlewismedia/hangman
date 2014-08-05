/**
 * A RandomPlayer is a Player that makes their guesses by choosing randomly for
 * valid characters
 * @tomlewis
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


public class RandomPlayer extends Player {
	/**
	 * Constructs a RandomPlayer with a set of known words
	 * @throws FileNotFoundException
	 */
	public RandomPlayer(ArrayList<String> words, ArrayList<Character> charSet)
			throws FileNotFoundException {
		super(words, charSet);
	}

	/**
	 * Returns a random guess
	 * @return char guess
	 */
	public char getGuess() {
		Random r = new Random();
		char guess = ' ';
		int size = validLetters.size();
		boolean validGuess = false;
		int random;

		while (validGuess == false) {
			random = r.nextInt(size);
			guess = validLetters.get(random);

			// if guess is not found in failedGuesses or successGuesses
			if (!(failedGuesses.contains(guess) || successGuesses
					.contains(guess))) {
				validGuess = true;
			}
		}

		return guess;
	}

}
