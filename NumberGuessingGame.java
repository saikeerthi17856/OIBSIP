import java.util.*;

public class NumberGuessingGame {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(" Welcome to the Number Guessing Game!");

        int totalScore = 0;
        int rounds = 0;

        boolean playAgain;
        do {
            rounds++;
            int roundScore = playOneRound();
            totalScore += roundScore;

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("\nGame Over!");
        System.out.println("Total Rounds Played: " + rounds);
        System.out.println("Total Score: " + totalScore + " points");
    }

    static int playOneRound() {
        int maxAttempts = 10;
        int targetNumber = (int) (Math.random() * 100) + 1;
        int attempts = 0;

        System.out.println("\n I have selected a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts to guess it.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = getValidInteger();
            attempts++;

            if (guess == targetNumber) {
                System.out.println(" Correct! You guessed it in " + attempts + " attempts.");
                int points = (maxAttempts - attempts + 1) * 10;  // Scoring system
                System.out.println("You earned " + points + " points.");
                return points;
            } else if (guess < targetNumber) {
                System.out.println(" Too low!");
            } else {
                System.out.println(" Too high!");
            }
        }

        System.out.println(" You've used all attempts. The correct number was: " + targetNumber);
        System.out.println("You earned 0 points.");
        return 0;
    }

    static int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}