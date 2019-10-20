package com.company;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.Random;


public class Main{
    private static int num1 = 0;
    private static int num2 = 0;
    private static int mathProblem = 0;

    private static String generateQuestion(int level) {
        SecureRandom random = new SecureRandom();
        boolean solvedmath = false;

        if (mathProblem == 4) {
            mathProblem = random.nextInt(3) + 1;
        }

        while (!solvedmath) {
            if (level == 1) {
                num1 = random.nextInt(9) + 1;
                num2 = random.nextInt(9) + 1;
            } else if (level == 2) {
                num1 = random.nextInt(99) + 1;
                num2 = random.nextInt(99) + 1;
            } else if (level == 3) {
                num1 = random.nextInt(999) + 1;
                num2 = random.nextInt(999) + 1;
            } else if (level == 4) {
                num1 = random.nextInt(9999) + 1;
                num2 = random.nextInt(9999) + 1;
            }

            solvedmath = true;

            if ((mathProblem == 4) && (num1 % num2 != 0)) {
                solvedmath = false;
            }
            if ((mathProblem == 3) && (num1 < num2)) {
                solvedmath = false;
            }
        }

        if (mathProblem == 1) {
            return String.format("How much is %d plus %d?", num1, num2);
        } else if (mathProblem == 2) {
            return String.format("How much is %d times %d?", num1, num2);
        } else if (mathProblem == 3) {
            return String.format("How much is %d minus %d?", num1, num2);
        } else  {
            return String.format("How much is %d divided by %d?", num1, num2);
        }
    }

    private static boolean verifyAnswer(double input) {
        if (mathProblem == 1) {
            return Double.compare(input, (double) (num1 + num2)) == 0;
        } else if (mathProblem == 2) {
            return Double.compare(input, (double) (num1 * num2)) == 0;
        } else if (mathProblem == 3) {
            return Double.compare(input, (double) (num1 - num2)) == 0;
        } else {
            return Double.compare(input, ((double) num1 / (double) num2)) == 0;
        }
    }

    private static String correctAnswerResponse() {
        SecureRandom random = new SecureRandom();
        int randomNum = random.nextInt(3) + 1;
        String response;

        switch (randomNum) {
            case 1:
                response = "Very good!";
                break;
            case 2:
                response = "Excellent!";
                break;
            case 3:
                response = "Nice work!";
                break;
            default:
                response = "Keep up the good work!";
                break;
        }

        return response;
    }

    private static String wrongAnswerResponse() {
        SecureRandom random = new SecureRandom();
        int randomNumber = random.nextInt(3) + 1;
        String response;

        switch (randomNumber) {
            case 1:
                response = "No.Please try again.";
                break;
            case 2:
                response = "Wrong. try once more.";
                break;
            case 3:
                response = "Don’t give up!";
                break;
            default:
                response = "No. Keep trying.";
                break;
        }

        return response;
    }

    private static int levelOfDifficulty() {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Select Difficulty Level:");
        System.out.println("Enter '1' for Level 1: ");
        System.out.println("Enter '2' for Level 2: ");
        System.out.println("Enter '3' for Level 3: ");
        System.out.println("Enter '4' for Level 4: ");

        return scnr.nextInt();
    }

    private static int SelectProblemType() {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Select Problem Type:");
        System.out.println("Enter '1' for Addition(+) ");
        System.out.println("Enter '2' for Multiplication(*)");
        System.out.println("Enter '3' for Subtraction(-)");
        System.out.println("Enter '4' for Division(/)");
        System.out.println("Enter '5' for A Random Problem");

        return scnr.nextInt();
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        int correctAnswers = 0;
        int DifficultyLevel = 0;

        double input = 0.0;
        String question = "";
        boolean end = false;

        System.out.println("Please select a difficulty level:");

        while (!end) {
            DifficultyLevel = levelOfDifficulty();
            System.out.println();
            mathProblem = SelectProblemType();

            System.out.println();
            System.out.println("Level " + DifficultyLevel + ":");

            for (int i = 0; i < 10; i++) {
                question = generateQuestion(DifficultyLevel);
                System.out.println("Question " + (i + 1) + ": " + question);

                input = scnr.nextDouble();

                if (verifyAnswer(input)) {
                    System.out.println(correctAnswerResponse());
                    correctAnswers++;
                } else {
                    System.out.println(wrongAnswerResponse());
                }
            }

            System.out.println("Total correct answers: " + correctAnswers);
            System.out.println("Total incorrect answers: " + (10 - correctAnswers));

            if (correctAnswers <= 7) {
                System.out.println("Please ask your teacher for extra help.");
            } else {
                System.out.println("Congratulations, you are ready to go to the next level!");
            }
            correctAnswers = 0;

            System.out.println("Enter 'Yes' to Continue or 'No' to Cuit.");

            System.out.println("New session?");

            if (scnr.next().charAt(0) == 'n') {
                end = true;
            }
        }
    }

}