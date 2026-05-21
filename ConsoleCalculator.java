import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Basic Console Calculator ===");
        System.out.println("Operators: +, -, *, /, %, power, square root");

        while (running) {
            printMenu();
            try {
                int choice = readInt(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        performBinary(scanner, "+");
                        break;
                    case 2:
                        performBinary(scanner, "-");
                        break;
                    case 3:
                        performBinary(scanner, "*");
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
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 0-7.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Math error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            if (running) {
                System.out.println();
            }
        }

        scanner.close();
    }

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

        System.out.printf("Result: %.2f %s %.2f = %.2f%n", first, operator, second, result);
    }

    private static void performDivision(Scanner scanner) {
        double dividend = readDouble(scanner, "Enter dividend: ");
        double divisor = readDouble(scanner, "Enter divisor: ");
        if (divisor == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        double result = dividend / divisor;
        System.out.printf("Result: %.2f / %.2f = %.2f%n", dividend, divisor, result);
    }

    private static void performModulo(Scanner scanner) {
        double dividend = readDouble(scanner, "Enter dividend: ");
        double divisor = readDouble(scanner, "Enter divisor: ");
        if (divisor == 0) {
            throw new ArithmeticException("Cannot calculate modulo with divisor zero.");
        }
        double result = dividend % divisor;
        System.out.printf("Result: %.2f %% %.2f = %.2f%n", dividend, divisor, result);
    }

    private static void performPower(Scanner scanner) {
        double base = readDouble(scanner, "Enter base: ");
        double exponent = readDouble(scanner, "Enter exponent: ");
        double result = Math.pow(base, exponent);
        System.out.printf("Result: %.2f ^ %.2f = %.2f%n", base, exponent, result);
    }

    private static void performSquareRoot(Scanner scanner) {
        double number = readDouble(scanner, "Enter number: ");
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        }
        double result = Math.sqrt(number);
        System.out.printf("Result: sqrt(%.2f) = %.2f%n", number, result);
    }

    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw e;
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new IllegalArgumentException("Please enter a valid numeric value.");
        }
    }
}
