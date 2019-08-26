import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "________________________________________________";
    private static List user_list = new ArrayList<String>();

    // Main
    public static void main(String[] args) {
        initialise();
    }

    // Method that is supposed to run when program is launched.
    // Prints out Logo, Welcome msg, and echoes user input until user_input=="bye", which terminates the method.
    private static void initialise() {
        // Welcome Message
        System.out.println("Hello from the other sideeeeeeeeeeee\n" + LOGO); // Logo
        dukeSays("Hello! I'm Duke.\nDuke: What can I do for you?");
        printSeparator();
        // Begin taking in input
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            System.out.print("User: ");
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) { // Only exit if user sends "bye" in lowercase
                dukeSays("Bye. Hope to see you again soon!");
                break;
            } else { // Do something with userInput
                recordUser(userInput);
            }
        }
        printSeparator();
    }

    // Level 2 - Adds input to the list. If user requests "list", returns the entire list
    private static void recordUser(String userInput) {
        if (userInput.equals("list")) {
            dukeSays("Here's you're list!");
            for (int index = 0; index < user_list.size(); ++index) {
                System.out.println((index + 1) + ". " + user_list.get(index));
            }
            printSeparator();
        } else {
            dukeSays("I've added \"" + userInput + "\" to your private list.");
            user_list.add(userInput);
        }
    }

    // Level 1 - Prints userInput as though Duke is responding
    private static void echoUser(String userInput) {
        dukeSays(userInput);
    }

    private static void dukeSays(String stringX) {
        System.out.println("Duke: " + stringX + "\n");
    }

    // Prints out a standardized line separator
    private static void printSeparator() {
        System.out.println(LINE);
    }
}