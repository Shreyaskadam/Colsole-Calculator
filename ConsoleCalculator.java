import java.util.InputMismatchException;
import java.util.Scanner;

// A simple menu-based calculator that runs in the console
public class ConsoleCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // reads user input from keyboard
        boolean running = true; // keeps the program running until user chooses Exit

        System.out.println("=== Basic Console Calculator ===");
        System.out.println("Operators: +, -, *, /, %, power, square root");

        // Main loop: show menu, do calculation, repeat
        while (running) {
            printMenu();
            try {
                int choice = readInt(scanner, "Enter your choice: ");

                // Run the operation based on menu number
                switch (choice) {
                    case 1:
                        performBinary(scanner, "+"); // addition
                        break;
                    case 2:
                        performBinary(scanner, "-"); // subtraction
                        break;
                    case 3:
                        performBinary(scanner, "*"); // multiplication
                        break;
                    case 4:
                        performDivision(scanner);
                        break;
                    case 5:
                        performModulo(scanner);
                        break;
                    case 6:
                        performPower(scanner);
                        break;
                    case 7:
                        performSquareRoot(scanner);
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        running = false; // stop the loop
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 0-7.");
                        break;
                }
            } catch (InputMismatchException e) {
                // user typed letters instead of a number
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear bad input from scanner buffer
            } catch (IllegalArgumentException e) {
                // wrong value, e.g. negative number for square root
                System.out.println("Error: " + e.getMessage());
            } catch (ArithmeticException e) {
                // math rules broken, e.g. divide by zero
                System.out.println("Math error: " + e.getMessage());
            } catch (Exception e) {
                // any other unexpected problem
                System.out.println("Unexpected error: " + e.getMessage());
            }

            if (running) {
                System.out.println(); // blank line before next menu
            }
        }

        scanner.close();
    }

    // Shows all available operations
    private static void printMenu() {
        System.out.println("--------------------------------");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Modulo (%)");
        System.out.println("6. Power (^)");
        System.out.println("7. Square Root (sqrt)");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
    }

    // Handles +, -, * using two numbers
    private static void performBinary(Scanner scanner, String operator) {
        double first = readDouble(scanner, "Enter first number: ");
        double second = readDouble(scanner, "Enter second number: ");
        double result;

        switch (operator) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }

        // %.2f prints the number with 2 decimal places
        System.out.printf("Result: %.2f %s %.2f = %.2f%n", first, operator, second, result);
    }

    // Divides first number by second number
    private static void performDivision(Scanner scanner) {
        double dividend = readDouble(scanner, "Enter dividend: ");
        double divisor = readDouble(scanner, "Enter divisor: ");
        if (divisor == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        double result = dividend / divisor;
        System.out.printf("Result: %.2f / %.2f = %.2f%n", dividend, divisor, result);
    }

    // Remainder after division (modulo)
    private static void performModulo(Scanner scanner) {
        double dividend = readDouble(scanner, "Enter dividend: ");
        double divisor = readDouble(scanner, "Enter divisor: ");
        if (divisor == 0) {
            throw new ArithmeticException("Cannot calculate modulo with divisor zero.");
        }
        double result = dividend % divisor;
        System.out.printf("Result: %.2f %% %.2f = %.2f%n", dividend, divisor, result);
    }

    // Raises base to the power of exponent (e.g. 2^3 = 8)
    private static void performPower(Scanner scanner) {
        double base = readDouble(scanner, "Enter base: ");
        double exponent = readDouble(scanner, "Enter exponent: ");
        double result = Math.pow(base, exponent);
        System.out.printf("Result: %.2f ^ %.2f = %.2f%n", base, exponent, result);
    }

    // Square root of a number (only works for zero or positive)
    private static void performSquareRoot(Scanner scanner) {
        double number = readDouble(scanner, "Enter number: ");
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        }
        double result = Math.sqrt(number);
        System.out.printf("Result: sqrt(%.2f) = %.2f%n", number, result);
    }

    // Reads a whole number (used for menu choice)
    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // discard invalid text
            throw e; // let main() catch block handle the message
        }
    }

    // Reads a decimal number (used for calculations)
    private static double readDouble(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            // wrap in a clearer error message for the user
            throw new IllegalArgumentException("Please enter a valid numeric value.");
        }
    }
}
