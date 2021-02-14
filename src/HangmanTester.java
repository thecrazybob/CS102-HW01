import java.util.Scanner;

public class HangmanTester {
    public static void main(String[] args ) {
        
        // constants
        final String PROG_NAME = "Hangman v1.0";
        
        // variables
        int letterFound;
        int count;
		char letter;
		boolean play;
		String playAgain;
        Scanner scan;
        Hangman hangmanGame;

		// program code

		// Print a welcome message and set the initial properties
		System.out.println("Welcome to " + PROG_NAME + " game!");

		play = true;
        scan = new Scanner(System.in);
        
        while (play) {

            // Creates a new Hangman object for every time the user plays a new game 
            // so the chosen word will be different everytime
            hangmanGame = new Hangman();

            System.out.println("A secret word has been chosen. You have " + hangmanGame.getMaxAllowedIncorrectTries() + " tries in total to guess the word.");

            do {

                // Displays the information about the secret word and letters
                System.out.println("(dev only) actual word: " + hangmanGame.secretWord);
                System.out.println("The word so far: " + hangmanGame.getKnownSoFar());
                System.out.println("All letters: " + hangmanGame.getAllLetters());
                System.out.println("Used letters: " + hangmanGame.getUsedLetters());
                
                // Prompts the user for his guess
                System.out.print("Make your guess (one letter only): ");
                letter = scan.next().toUpperCase().charAt(0);
                
                // Checks whether the given letter is used before or is an invalid input (i.e. "1" or "2412")
                count = 0;
                while (count < hangmanGame.getUsedLetters().length() || !Character.isLetter(letter)) {
                    while (letter == hangmanGame.getUsedLetters().toUpperCase().charAt(count) || !Character.isLetter(letter)) {
                        System.out.println(); // for the program to look cleaner
                        System.out.println("You have written an invalid character (Used Before/Invalid Character).");
                        System.out.print("Make Your Guess (one letter only): ");
                        letter = scan.next().toUpperCase().charAt(0);
                        count = 0;
                    }
                    count++;
                }
                
                System.out.println();
                
                // Reports the result of the guess
                letterFound = hangmanGame.tryThis(letter);

                if (letterFound == -2) {
                    System.out.println("You have guessed wrong.");
                    System.out.println("You have " + (hangmanGame.getMaxAllowedIncorrectTries() - hangmanGame.getNumOfIncorrectTries()) + " tries left.");
                }
                
                else if (letterFound == -3) {
                    break;
                }
                
                else {
                    System.out.println("'" + letter + "' has been found " + letterFound + " times.");
                }

                System.out.println();
                
            } while (!hangmanGame.isGameOver());

            if (hangmanGame.hasLost()) {

                System.out.println("Unfortunately, You have lost.");
                System.out.println("Your attempt: " + hangmanGame.getKnownSoFar());
                System.out.println("Really!? I have defeated you player! I shall never be defeated!");
                System.out.println("You could NOT guess the word correctly.");
                System.out.println("Only I am worthy of knowing the word!");
                System.out.println("I will never tell the word to you...");

            }

            else {

                System.out.println("Congratulations! You won! The word is: " + hangmanGame.secretWord);

            }
            
            System.out.println();

            // Asks for and gets user's choice about playing again
            System.out.println("Would you like to play again?");
            System.out.println("To exit the game press 'N'.");
            System.out.println("If you want to continue playing, type any character other than 'N'");
            System.out.print("Continue?: ");
            playAgain = scan.next();

            if (playAgain.equalsIgnoreCase("N")) {
                play = false;
            }

        }
            
    	System.out.println("That was fun. Can't wait for the next time!!");
    	scan.close();

	}

}
