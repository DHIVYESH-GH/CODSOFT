import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int score = 0; 
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int randomNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 7; 
            boolean guessedCorrectly = false;

            System.out.println("\nI have selected a number between 1 and 100. Can you guess it?");
            System.out.println("You have " + maxAttempts + " attempts to guess correctly.");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    score += (maxAttempts - attempts + 1); 
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all your attempts. The correct number was " + randomNumber + ".");
            }

            System.out.println("Your current score is: " + score);

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("\nThank you for playing! Your final score is: " + score);
        scanner.close();
    }
}
