/**
 * A frame that displays the components of a computer vs. player Hangman game.
 * Solves the GUI requirement for CS 162 - Sec 400 ASSN 2.
 * @author EmmA Murray
 * @version 1.01 2013-07-07
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class HangmanFrame extends JFrame
{
	//stores primary frame info
	public static final int FRAME_WIDTH = 460;
	public static final int FRAME_HEIGHT = 475;
	final JFrame frame;
	
	//stores objects passed from the driver
	private HangmanLogic game;
	private char guesser;
	private Player p1;
	
	//stores raw images
	private BufferedImage bkg0;
	private BufferedImage bkg1;
	private BufferedImage bkg2;
	private BufferedImage bkg3;
	private BufferedImage bkg4;
	private BufferedImage bkg5;
	private BufferedImage bkg6;
	private BufferedImage bkg7;
	private BufferedImage bkg8;
	private BufferedImage bkg9;
	private BufferedImage bkg10;
	private BufferedImage bkg11;
	private BufferedImage bkg12;
	private BufferedImage bkg13;
	private BufferedImage bkg14;
	private BufferedImage bkg15;
	
	//stores images as JLabel components for use in panels
	private JLabel bkg0Icon;
	private JLabel bkg1Icon;
	private JLabel bkg2Icon;
	private JLabel bkg3Icon;
	private JLabel bkg4Icon;
	private JLabel bkg5Icon;
	private JLabel bkg6Icon;
	private JLabel bkg7Icon;
	private JLabel bkg8Icon;
	private JLabel bkg9Icon;
	private JLabel bkg10Icon;
	private JLabel bkg11Icon;
	private JLabel bkg12Icon;
	private JLabel bkg13Icon;
	private JLabel bkg14Icon;
	private JLabel bkg15Icon;

	//stores message labels
	private JLabel keyPhraseLabel;
	private JLabel numGuessLabel;
	private JLabel numGuessLeftLabelH;
	private JLabel numGuessLeftLabelC;
	private JLabel charGuessLabel;
	private JLabel progressH;
	private JLabel progressC;
	private JLabel guessedCharsH;
	private JLabel guessedCharsC;
	private JLabel warning1;
	private JLabel warning2;
	private JLabel warning3;
	private JLabel gameover;
	
	//stores input fields
	private JComboBox keyPhraseEntry;
	private JTextField numGuessEntry;
	private JTextField charGuessEntry;
	private JButton playButton;
	private JButton guessButton;
	private JButton nextButton;
	
	//stores constant names for use with card layout
	private final static String KEY_INPUT = "KEY_INPUT";
	private final static String GUESS_INPUT = "GUESS_INPUT";
	private final static String COM_INPUT = "COM_INPUT";
	private final static String GAMEOVER = "GAMEOVER";
	private final static String BKG0 = "BKG0";
	private final static String BKG1 = "BKG1";
	private final static String BKG2 = "BKG2";
	private final static String BKG3 = "BKG3";
	private final static String BKG4 = "BKG4";
	private final static String BKG5 = "BKG5";
	private final static String BKG6 = "BKG6";
	private final static String BKG7 = "BKG7";
	private final static String BKG8 = "BKG8";
	private final static String BKG9 = "BKG9";
	private final static String BKG10 = "BKG10";
	private final static String BKG11 = "BKG11";
	private final static String BKG12 = "BKG12";
	private final static String BKG13 = "BKG13";
	private final static String BKG14 = "BKG14";
	private final static String BKG15 = "BKG15";
	
	//stores card panels
	private JPanel imgPane;
	private JPanel inputPane;
	
	//stores individual cards
	private JPanel keyInputCard;
	private JPanel guessInputCard;
	private JPanel comInputCard;
	private JPanel gameOverCard;
	private JPanel bkg0Card;
	private JPanel bkg1Card;
	private JPanel bkg2Card;
	private JPanel bkg3Card;
	private JPanel bkg4Card;
	private JPanel bkg5Card;
	private JPanel bkg6Card;
	private JPanel bkg7Card;
	private JPanel bkg8Card;
	private JPanel bkg9Card;
	private JPanel bkg10Card;
	private JPanel bkg11Card;
	private JPanel bkg12Card;
	private JPanel bkg13Card;
	private JPanel bkg14Card;
	private JPanel bkg15Card;
	
	//stores the card layouts
	private CardLayout imgCards;
	private CardLayout inputCards;
	
	//breaks loop on initial user input for keyphrase and numeber of guesses
	private boolean valid = false;
		
	/**
	 * Loads images, initializes components and constructs the frame for the UI.
	 * @throws IOException 
	 * @throws AlreadyGuessedException 
	 * @throws InvalidInputException 
	 */
	
	public HangmanFrame(HangmanLogic game, Player p1, char guesser) throws IOException, InvalidInputException, AlreadyGuessedException
	{
		//store variables from the driver
		this.game = game;
		this.p1 = p1;
		this.guesser = guesser;
		
		//loads the images, components and cards for the card layouts
		try
		{
			this.getImages();
		}
		catch (IOException exception)
		{
			System.out.println("You are missing the image files." +
					"Please ensure that they are located in the directory " +
					"above the SRC folder and reload the game.");
		}
		this.getItems();
		this.getCards();
		
		//creates the primary frame
		frame = new JFrame("Hangman");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(imgPane, BorderLayout.CENTER);
		frame.add(inputPane, BorderLayout.SOUTH);
		
		/*
		 * Frame sizing borrowed from Stack Overflow:
		 * http://stackoverflow.com/questions/7776630/how-do-i-make-a-jframe-a-certain-size-not-including-the-border
		 */
		
		Container c = frame.getContentPane();
      Dimension d = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
      c.setPreferredSize(d);
      frame.pack();
      frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	/**
	 * Generates any initial computer input and starts the game.
	 */
	public void playGame(char guesser)
	{		

		if (guesser == 'h')
		{
			//generates the computer's secret word
			p1.genNewKeyWord();
			game.setKeyPhrase(p1.getKeyWord());
			progressH.setText(game.getGuessPhrase());
			
			//generates the # of guesses allowed
			game.setNumberOfGuesses(p1.genNumGuesses());
			numGuessLeftLabelH.setText("Guesses Left:" + game.getNumGuessesLeft());
			
			//sets the cards shown for image and input
			imgCards.show(imgPane, BKG0);
			inputCards.show(inputPane, GUESS_INPUT);
			
		}
		else
		{
			//sets the cards shown for image and input
			// TODO fix to show until input is correct?
			while (!valid)
			{
				imgCards.show(imgPane, BKG0);	
				inputCards.show(inputPane, KEY_INPUT);
			}
			
			inputCards.show(inputPane, COM_INPUT);
		}
	}
	
	/**
	 * Loads the hangman images.
	 * @throws IOException 
	 */
	
	private void getImages() throws IOException
	{
		//initialize image cards with their components
				//Borrowed the Image IO code from Stack Overflow: 
				//http://stackoverflow.com/questions/299495/java-swing-how-to-add-an-image-to-a-jpanel
				bkg0Card = new JPanel();
				bkg0 = ImageIO.read(new File("BKG0.jpg"));
				bkg0Icon = new JLabel(new ImageIcon(bkg0));
				bkg0Card.add(bkg0Icon);
				
				bkg1Card = new JPanel();
				bkg1 = ImageIO.read(new File("BKG1.jpg"));
				bkg1Icon = new JLabel(new ImageIcon(bkg1));
				bkg1Card.add(bkg1Icon);

				bkg2Card = new JPanel();
				bkg2 = ImageIO.read(new File("BKG2.jpg"));
				bkg2Icon = new JLabel(new ImageIcon(bkg2));
				bkg2Card.add(bkg2Icon);

				bkg3Card = new JPanel();
				bkg3 = ImageIO.read(new File("BKG3.jpg"));
				bkg3Icon = new JLabel(new ImageIcon(bkg3));
				bkg3Card.add(bkg3Icon);

				bkg4Card = new JPanel();
				bkg4 = ImageIO.read(new File("BKG4.jpg"));
				bkg4Icon = new JLabel(new ImageIcon(bkg4));
				bkg4Card.add(bkg4Icon);

				bkg5Card = new JPanel();
				bkg5 = ImageIO.read(new File("BKG5.jpg"));
				bkg5Icon = new JLabel(new ImageIcon(bkg5));
				bkg5Card.add(bkg5Icon);

				bkg6Card = new JPanel();
				bkg6 = ImageIO.read(new File("BKG6.jpg"));
				bkg6Icon = new JLabel(new ImageIcon(bkg6));
				bkg6Card.add(bkg6Icon);

				bkg7Card = new JPanel();
				bkg7 = ImageIO.read(new File("BKG7.jpg"));
				bkg7Icon = new JLabel(new ImageIcon(bkg7));
				bkg7Card.add(bkg7Icon);

				bkg8Card = new JPanel();
				bkg8 = ImageIO.read(new File("BKG8.jpg"));
				bkg8Icon = new JLabel(new ImageIcon(bkg8));
				bkg8Card.add(bkg8Icon);

				bkg9Card = new JPanel();
				bkg9 = ImageIO.read(new File("BKG9.jpg"));
				bkg9Icon = new JLabel(new ImageIcon(bkg9));
				bkg9Card.add(bkg9Icon);

				bkg10Card = new JPanel();
				bkg10 = ImageIO.read(new File("BKG10.jpg"));
				bkg10Icon = new JLabel(new ImageIcon(bkg10));
				bkg10Card.add(bkg10Icon);

				bkg11Card = new JPanel();
				bkg11 = ImageIO.read(new File("BKG11.jpg"));
				bkg11Icon = new JLabel(new ImageIcon(bkg11));
				bkg11Card.add(bkg11Icon);

				bkg12Card = new JPanel();
				bkg12 = ImageIO.read(new File("BKG12.jpg"));
				bkg12Icon = new JLabel(new ImageIcon(bkg12));
				bkg12Card.add(bkg12Icon);
				
				bkg13Card = new JPanel();
				bkg13 = ImageIO.read(new File("BKG13.jpg"));
				bkg13Icon = new JLabel(new ImageIcon(bkg13));
				bkg13Card.add(bkg13Icon);

				bkg14Card = new JPanel();
				bkg14 = ImageIO.read(new File("BKG14.jpg"));
				bkg14Icon = new JLabel(new ImageIcon(bkg14));
				bkg14Card.add(bkg14Icon);

				bkg15Card = new JPanel();
				bkg15 = ImageIO.read(new File("BKG15.jpg"));
				bkg15Icon = new JLabel(new ImageIcon(bkg15));
				bkg15Card.add(bkg15Icon);

	}
	
	/**
	 * Loads the individual components.
	 */
	
	private void getItems()
	{
		
		//initialize interaction components
				warning1 = new JLabel("");
				warning2 = new JLabel("");
				warning3 = new JLabel("");
				
				keyPhraseLabel = new JLabel("What's the magic word?");				
				numGuessLabel = new JLabel("How many guesses do I get?");
				charGuessLabel = new JLabel("Guess?");
				gameover = new JLabel();
				numGuessEntry = new JTextField(50);
				charGuessEntry = new JTextField(50);
				
				//human player labels
				numGuessLeftLabelH = new JLabel("Guesses Left:");
				guessedCharsH = new JLabel(game.getGuessedChars());
				progressH = new JLabel();
				
				//com player labels
				numGuessLeftLabelC = new JLabel("Guesses Left:");
				guessedCharsC = new JLabel(game.getGuessedChars());
				progressC = new JLabel();
				
				playButton = new JButton("Play!");
				playButton.addActionListener(new PlayButtonListener());
				
				guessButton = new JButton("Go!");
				guessButton.addActionListener(new GuessButtonListener());
				
				nextButton = new JButton("Next>>");
				nextButton.addActionListener(new NextButtonListener());
				
				//fills the word selection combobox from the wordsList file
				//words file being called from Player class because it is read and stored there
				keyPhraseEntry = new JComboBox();
				
				for (String c : p1.wordBank)
				{
					keyPhraseEntry.addItem(c);
				}
				
				keyPhraseEntry.setEditable(true);
	}
	
	/**
	 * Initializes and loads the cards for the image and input card layouts.
	 */
	private void getCards()
	{
		//initialize and set layout for key phrase input card components (user choosing)
		keyInputCard  = new JPanel();
		GroupLayout keyLayout = new GroupLayout(keyInputCard);
		keyInputCard.setLayout(keyLayout);
		keyLayout.setAutoCreateGaps(true);
		keyLayout.setAutoCreateContainerGaps(true);
		
		keyLayout.setHorizontalGroup(
				keyLayout.createSequentialGroup()
					.addGroup(keyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(keyPhraseLabel)
						.addComponent(numGuessLabel)
						.addComponent(playButton))
					.addGroup(keyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(keyPhraseEntry)
						.addComponent(numGuessEntry)
						.addComponent(warning1)));
		
		keyLayout.setVerticalGroup(
				keyLayout.createSequentialGroup()
					.addGroup(keyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(keyPhraseLabel)
						.addComponent(keyPhraseEntry))
					.addGroup(keyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(numGuessLabel)
						.addComponent(numGuessEntry))
					.addGroup(keyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(playButton)
						.addComponent(warning1)));
		
		// TODO fix the group layout so guessed characters spans all 3 columns
		//initialize and set layout for guess input card components (user guessing)
		guessInputCard = new JPanel();
		GroupLayout guessLayout = new GroupLayout(guessInputCard);
		guessInputCard.setLayout(guessLayout);
		guessLayout.setAutoCreateGaps(true);
		guessLayout.setAutoCreateContainerGaps(true);
		
		guessLayout.setHorizontalGroup(
				guessLayout.createSequentialGroup()
					.addGroup(guessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(guessedCharsH)
						.addComponent(numGuessLeftLabelH)
						.addComponent(charGuessLabel))
					.addGroup(guessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(progressH)
						.addComponent(charGuessEntry)
						.addComponent(warning2))
					.addGroup(guessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(guessButton)));
		
		guessLayout.setVerticalGroup(
				guessLayout.createSequentialGroup()
					.addGroup(guessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(guessedCharsH))
					.addGroup(guessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(numGuessLeftLabelH)
						.addComponent(progressH))
					.addGroup(guessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(charGuessLabel)
						.addComponent(charGuessEntry)
						.addComponent(guessButton))
					.addGroup(guessLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(warning2)));


		//initialize and set layout for guess input card components (com guessing)
		comInputCard = new JPanel();
		GroupLayout comLayout = new GroupLayout(comInputCard);
		comInputCard.setLayout(comLayout);
		comLayout.setAutoCreateGaps(true);
		comLayout.setAutoCreateContainerGaps(true);
		
		comLayout.setHorizontalGroup(
				comLayout.createSequentialGroup()
					.addGroup(comLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(guessedCharsC)
						.addComponent(numGuessLeftLabelC)
						.addComponent(progressC)
						.addComponent(nextButton)
						.addComponent(warning3)));
		
		comLayout.setVerticalGroup(
				comLayout.createSequentialGroup()
						.addComponent(guessedCharsC)
					.addGroup(comLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(numGuessLeftLabelC))
					.addGroup(comLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(progressC))					
					.addGroup(comLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(nextButton))
					.addGroup(comLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(warning3)));
					
		
		//initialize and set layout for gameover card components
		gameOverCard = new JPanel();
		gameOverCard.add(gameover, BorderLayout.CENTER);
		
		//initialize image and input card layouts
		imgPane = new JPanel();
		imgCards = new CardLayout();
		imgPane.setLayout(imgCards);
		imgPane.add(bkg0Card, BKG0);
		imgPane.add(bkg1Card, BKG1);
		imgPane.add(bkg2Card, BKG2);
		imgPane.add(bkg3Card, BKG3);
		imgPane.add(bkg4Card, BKG4);
		imgPane.add(bkg5Card, BKG5);
		imgPane.add(bkg6Card, BKG6);
		imgPane.add(bkg7Card, BKG7);
		imgPane.add(bkg8Card, BKG8);
		imgPane.add(bkg9Card, BKG9);
		imgPane.add(bkg10Card, BKG10);
		imgPane.add(bkg11Card, BKG11);
		imgPane.add(bkg12Card, BKG12);
		imgPane.add(bkg13Card, BKG13);
		imgPane.add(bkg14Card, BKG14);
		imgPane.add(bkg15Card, BKG15);
		
		
		inputPane = new JPanel();
		inputCards = new CardLayout();
		inputPane.setLayout(inputCards);
		inputPane.add(keyInputCard, KEY_INPUT);
		inputPane.add(guessInputCard, GUESS_INPUT);
		inputPane.add(comInputCard, COM_INPUT);
		inputPane.add(gameOverCard, GAMEOVER);
		

	}
	
	/**
	 * Action listener for the "Play" button.
	 */
	class PlayButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
				
			int numGuesses = 0;
							
			try
			{
				numGuesses = Integer.parseInt(numGuessEntry.getText());
				
				if (numGuesses < 1 || numGuesses > 15)
				{
					warning1.setText("Pick between 1 - 15 guesses.");
					numGuessEntry.setText("");
					
					
				}
				else
				{
					game.setKeyPhrase(keyPhraseEntry.getSelectedItem().toString());
					game.setNumberOfGuesses(numGuesses);
					warning1.setText("");
					valid = true;
					
					//added to initialize cleverPlayer's masked phrase -Tom
					
					if (guesser == 'c') {
						((CleverPlayer) p1).initCull(keyPhraseEntry.getSelectedItem().toString().length()); //tells player how many characters there are
					}
					
			
				}
			
			}
			catch (NumberFormatException exception)
			{
				warning1.setText("That is not a number!");
				numGuessEntry.setText("");
			} 
			
		}
	}
	
	/**
	 * Action listener for the "Go" button during guess stage.
	 */
	class GuessButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{ 
			
			try
			{
				game.guessCharacter(charGuessEntry.getText().charAt(0));
				
				if (game.isGameOver())
				{
					if (game.getWin())
					{
						gameover.setText("GAMEOVER!\n\nYou win! Thank you for saving me!");
						inputCards.show(inputPane, GAMEOVER);
						imgCards.show(imgPane, BKG0);
					}
					else
					{
						gameover.setText("GAMEOVER!\n\nYou lose!");
						inputCards.show(inputPane, GAMEOVER);
						inputPane.repaint();
						imgCards.show(imgPane, BKG15);
						imgPane.repaint();
					}
				}
				else
				{
					
					//resets the values for these fields
					guessedCharsH.setText(game.getGuessedChars());
					numGuessLeftLabelH.setText("Guesses Left: " + game.getNumGuessesLeft());
					progressH.setText(game.getGuessPhrase());
					warning2.setText("");
					charGuessEntry.setText("");
					
					//decides the img to use based on how close to the end the guesser is
					String gameImg = "BKG" + (game.MAX_GUESSES - game.getNumGuessesLeft());
					
					//resets the image card being shown
					imgCards.show(imgPane, gameImg);
					imgPane.repaint();
					
					System.out.println(gameImg);

				
				}
			}
			catch (InvalidInputException e)
			{
				warning2.setText("That is not a character!");
			} 
			catch (AlreadyGuessedException e)
			{
				warning2.setText("You've already guessed that!");
			}	
			catch (StringIndexOutOfBoundsException exception)
			{
				warning2.setText("Please enter a character.");
			}
		}
	}
	
	/**
	 * Action listener for the "Next" button during the computer's guess stage.
	 */
	class NextButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{ 
			
			try
			{
				game.guessCharacter(p1.getGuess());
				
				if (game.isGameOver())
				{
					if (game.getWin())
					{
						gameover.setText("GAMEOVER!\n\nThe computer wins! I'm saved!");
						inputCards.show(inputPane, GAMEOVER);
						imgCards.show(imgPane, BKG0);
					}
					else
					{
						gameover.setText("GAMEOVER!\n\nYou win...? I guess?");
						inputCards.show(inputPane, GAMEOVER);
						inputPane.repaint();
						imgCards.show(imgPane, BKG15);
						imgPane.repaint();
					}
				}
				else
				{
										
					//resets the values for these fields
					guessedCharsC.setText(game.getGuessedChars());
					numGuessLeftLabelC.setText("Guesses Left: " + game.getNumGuessesLeft());
					progressC.setText(game.getGuessPhrase());
					warning3.setText("");
					
					
					
					//decides the img to use based on how close to the end the guesser is
					String gameImg = "BKG" + (game.MAX_GUESSES - game.getNumGuessesLeft());
					
					//resets the image card being shown
					imgCards.show(imgPane, gameImg);
					imgPane.repaint();				
				}
			}
			catch (InvalidInputException e)
			{
				warning3.setText("Computer did not guess a character. Hit NEXT.");
			} 
			catch (AlreadyGuessedException e)
			{
				warning3.setText("Computer has already guessed that. Hit NEXT.");
			}	
		}
	}
}
	