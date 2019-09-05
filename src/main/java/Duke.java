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
    private static final String[] TASK_TYPES = {"deadline", "todo", "event"};

    /**
     * The Main method by which Duke will be launched.
     */
    public static void main(String[] args) {
        loadData("savedData.txt");
        initialise();
        saveData("savedData.txt");
    }

    /**
     * This method loads saved data from the previous instance of Duke.
     * @param pathName - String that details the full path of a .txt file
     */
    public static void loadData(String pathName) {
        try {
            File file = new File(pathName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                try {
		    String separator = "@|@";
                    String[] values = scanner.nextLine().split(separator, 2);
                    Task newTask = createTask(values[0]);
		    separator = separator.substring(1);
		    values[1] = values[1].replace(separator, "").trim();
                    if (Boolean.valueOf(values[1])) {
                        newTask.markDone();
                    }
                    taskList.add(newTask);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
	    dukeSays(String.valueOf(taskList.size()) + " Tasks loaded from Memory");
        } catch (Exception e) {
            System.out.println("No previously saved Data.");
        }
    }

    /**
     * This method saves the Tasks stored in .taskList for future use.
     * @param pathName - String that details the full path of a .txt file
     */
    public static void saveData(String pathName) {
        try {
            FileWriter writer = new FileWriter(pathName);
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
	printSeparator();
        System.out.println(LOGO); // Logo
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
	if (userInput.split(" ").length < 1) {
	    return;
	}
        if (userInput.toLowerCase().equals("list")) {
            dukeSays("You have (" + String.valueOf(taskList.size()) + 
			    ") Tasks in your list!");
            for (int index = 0; index < taskList.size(); ++index) {
                System.out.println((index + 1) + ". "
                        + taskList.get(index).genTaskDesc());
            }
            printSeparator();
        } else if (userInput.split(" ")[0].toLowerCase().contains("done")) {
            try {
                int index = Integer.valueOf(userInput.replace("done", "").trim()) - 1;
                taskList.get(index).markDone();
                dukeSays("Alrighty, I've marked task '" + String.valueOf(index + 1)
                    + ") " + taskList.get(index).taskName + "' as done!");
            } catch (Exception e) {
                dukeSays("Invalid 'done' statement. Please indicate the index of the task you wish to mark done.");
            }
        } else if (userInput.split(" ")[0].toLowerCase().contains("delete")) {
            try {
                int index = Integer.valueOf(userInput.replace("delete", "").trim()) - 1;
                dukeSays("Task '" + String.valueOf(index + 1)
                    + ") " + taskList.get(index).taskName + "' deleted");
                taskList.remove(index);
            } catch (Exception e) {
                dukeSays("Invalid 'delete' statement. Please indicate the index of the task you wish to mark delete.");
            }
	} else if (userInput.split(" ")[0].toLowerCase().contains("find")) {
	    userInput = userInput.replace("find", "").trim();
	    findTasks(userInput);
        } else {
            Task newTask = createTask(userInput);
            taskList.add(newTask);
            dukeSays("I've added \"" + newTask.genTaskDesc() + "\" to your private list(" +
			    String.valueOf(taskList.size()) + ").");
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

    /**
     * Interface for class selection: Creates a class based on the user's input.
     * TO-DO: Think about how this can be neater
     * @param userInput The input that the user types in from the command line
     * @return Specific Task Object based on user's input. Default: Task
     */
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

    /**
     * Prints tasks from Duke.taskList based on the index provided.
     * @param ...index Varargs The indexes of tasks from taskList to be printed.
     */
    public static void printTaskByIndex(int ...indexes) {
	for (int index : indexes) {
	    System.out.println(String.valueOf(index + 1) + ". " 
			    + taskList.get(index).genTaskDesc());
	}
    }

    /**
     * Finds and prints each task that contains the string.
     * @param name The substring to be found
     */
    public static void findTasks(String name) {
	dukeSays("Here are the Tasks matching your query '" + name + "'.");
	for (int index = 0; index < taskList.size(); ++index) {
	    try {
		if(taskList.get(index).taskName.contains(name)) {
		    printTaskByIndex(index);
		}
	    } catch (Exception e) {
		System.out.println("Read invalid taskName");
	    }
	}
	printSeparator();
    }
}
