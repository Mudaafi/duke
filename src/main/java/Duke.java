import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

public class Duke {
    private static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "________________________________________________";
    private static List<Task> taskList = new ArrayList<Task>();
    private static final String TASK_TYPES[] = {"deadline", "todo", "event"};

    // Main
    public static void main(String[] args) {
	loadData();
        initialise();
	saveData();
    }

    // Load saved Data
    public static void loadData() {
	try {
	    File file = new File("savedData.txt");
	    Scanner scanner = new Scanner(file);
	    while (scanner.hasNextLine()) {
	        try {
	     	    String values[] = scanner.nextLine().split("@|@", 2);
		    Task newTask = createTask(values[0]);
		    if (Boolean.valueOf(values[1])) {
		        newTask.markDone();
		    }
		    taskList.add(newTask);
	        } catch (Exception e) {
		    System.out.println(e);
	        }
	    }
	} catch (Exception e) {
	    System.out.println("No previously saved Data.");
	}
    }

    // Save Current Data
    public static void saveData() {
	try {
	    FileWriter writer = new FileWriter("savedData.txt");
	    for (Task task : taskList) {
		String strSave = "";
		strSave += task.typeMap.get(task.type) + " "
				+ task.taskName;
		if (task.detailDesc != null && task.taskDetails != null) {
		    strSave += '/';
		}
		if (task.detailDesc != null) {
		    strSave += task.detailDesc;
		}
		strSave += " ";
		if (task.taskDetails != null) {
		    strSave += task.taskDetails;
		}
		strSave += "@|@" + task.isDone.toString() + "\n";
		writer.write(strSave);
	    }
	    writer.close();
	} catch (Exception e) {
	    System.out.println(e);
	}
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
            if (userInput.toLowerCase().equals("bye")) { // Only exit if user sends "bye" in lowercase
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
        if (userInput.toLowerCase().equals("list")) {
            dukeSays("Here's you're list!");
            for (int index = 0; index < taskList.size(); ++index) {
                System.out.println((index + 1) + ". " 
				+ taskList.get(index).genTaskDesc());
            }
            printSeparator();
	} else if (userInput.toLowerCase().contains("done")) {
	    try {
	        int index = Integer.valueOf(userInput.replace("done", "").trim()) - 1;
		taskList.get(index).markDone();
		dukeSays("Alrighty, I've marked task '" + String.valueOf(index + 1) + ". " + taskList.get(index).taskName + "' as done!");
	    } catch (Exception e) {
		dukeSays("Invalid 'done' statement. Please indicate the index of the task you wish to mark done.");
	    }
        } else {
	    Task newTask = createTask(userInput);
    	    taskList.add(newTask);
            dukeSays("I've added \"" + newTask.genTaskDesc() + "\" to your private list.");
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
  
    // TO-DO: Think about how this can be neater
    // Interface for class selection
    public static Task createTask(String userInput) {
	Task newTask;
	int minIndex = 999;
	int testIndex;
	String type = "";
	for (String typeIter : TASK_TYPES) {
	    testIndex = userInput.toLowerCase().indexOf(typeIter);
	    if (minIndex > testIndex && testIndex >= 0) {
		type = typeIter;
		minIndex = testIndex;
	    }
	}
	if (type == "deadline") {
	    newTask = new Deadline(userInput);
	} else if (type == "event") {
	    newTask = new Event(userInput);
	} else if (type == "todo") {
	    newTask = new ToDo(userInput);
	} else {
	    newTask = new Task(userInput);
	}
	return newTask;
    }
}
