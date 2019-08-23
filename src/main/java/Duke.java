import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final static String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String LINE = "________________________________________________";
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
        String user_input;
        while (true) {
            System.out.print("User: ");
            user_input = scanner.nextLine();
            if (user_input.equals("bye")) { // Only exit if user sends "bye" in lowercase
                dukeSays("Bye. Hope to see you again soon!");
                break;
            } else { // Do something with user_input
                recordUser(user_input);
            }
        }
        printSeparator();
    }

    // Level 2 - Adds input to the list. If user requests "list", returns the entire list
    private static void recordUser(String user_input) {
        if (user_input.equals("list")) {
            dukeSays("Here's you're list!");
            for (int index = 0; index < user_list.size(); ++index) {
                System.out.println((index + 1) + ". " + user_list.get(index));
            }
            printSeparator();
        } else {
            dukeSays("I've added \"" + user_input + "\" to your private list.");
            user_list.add(user_input);
        }
    }

    // Level 1 - Prints user_input as though Duke is responding
    private static void echoUser(String user_input) {
        dukeSays(user_input);
    }

    private static void dukeSays(String string_x) {
        System.out.println("Duke: " + string_x + "\n");
    }
    // Prints out a standardized line separator
    private static void printSeparator() {
        System.out.println(LINE);
    }
}