import java.util.HashMap;

public class Task {
    protected String type = "P";
    protected String taskName;
    protected String taskDetails;
    protected String detailDesc;
    protected Boolean isDone = false;
    protected HashMap<String, String> typeMap = new HashMap<String, String>();
    protected static int taskCounter = 0;

    /**
     * Constructor for the 'Task' Class.
     * @param name Name of the task as inputted by the user
     */
    public Task(String name) {
        this.taskCounter += 1;
        this.taskName = name.replace("Parent Task Class", "").trim();
        this.typeMap.put("P", "Parent Task Class");
        this.typeMap.put("T", "ToDo");
        this.typeMap.put("D", "Deadline");
        this.typeMap.put("E", "Event");
    }

    /**
     * Get a 'tick' or 'cross' depending on .isDone
     * @return A special string that represents a tick for true or a cross for false
      */
    public String getStatusIcon() {
        if (this.isDone) {
            return "✓"; // / u2713
        } else {
            return "✘"; // / u2718
        }
    }

    /**
     * Sets a Task object's .isDone property to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Sets a Task object's .isDone property to false.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Generates a String Describing the Task Object.
     * Optimized for user's reading.
     * @return String detailing the Task Object, including type, isDone, taskName and taskDetails
     */
    public String genTaskDesc() {
        String generatedStr = "";
        if (!this.taskName.isEmpty()) {
            generatedStr += "[" + this.getStatusIcon() + "]" + "["
                    + this.type + "] " + this.taskName;
        }
        if (this.detailDesc != null || this.taskDetails != null) {
            generatedStr += " (";
            if (this.detailDesc != null && !this.detailDesc.isEmpty()) {
                generatedStr += this.detailDesc + ": ";
            }
            if (this.taskDetails != null && !this.taskDetails.isEmpty()) {
                generatedStr += this.taskDetails;
            }
            generatedStr += ")";
        }
        return generatedStr;
    }

    // Record Task Details
    protected void recordTaskDetails(String name) {
        //(?i) is regex which tells Java to be case-Insensitive
        name = name.replaceFirst("(?i)" + this.typeMap.get(this.type), "").trim();
        this.taskName = name;
        int indexBackslash = name.indexOf('/');
        //Check if '/' exists
        if (indexBackslash >= 0) {
            int indexMsg = name.indexOf(' ', indexBackslash);
            if (indexMsg >= 0) {
                this.detailDesc = name.substring(indexBackslash + 1, indexMsg);
            }
            String[] splitDetails = name.split('/' + this.detailDesc, 2);
            this.taskName = splitDetails[0].trim();
            if (splitDetails.length > 1) {
                this.taskDetails = splitDetails[1].trim();
            }
        }
    }
}
