import java.util.HashMap;

public class Task {
    protected String type = "P";
    protected String taskName;
    protected String taskDetails;
    protected String detailDesc;
    protected Boolean isDone = false;
    protected HashMap<String, String> typeMap = new HashMap<String, String>();

    // Initialization
    public Task(String name) {
        this.taskName = name;
        this.typeMap.put("P", "Parent Task Class");
        this.typeMap.put("T", "ToDo");
        this.typeMap.put("D", "Deadline");
        this.typeMap.put("E", "Event");
    }

    // Get 'tick' or 'cross' for isDone
    public String getStatusIcon() {
        if (this.isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    // Mark as Done
    public void markDone() {
        this.isDone = true;
    }

    // Mark as Not Done
    public void markNotDone() {
	this.isDone = false;
    }

    // Generate a String Describing the Task for listing
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
	    this.detailDesc = name.substring(indexBackslash + 1, name.indexOf(' ', indexBackslash)).trim();
	    String splitDetails[] = name.split('/' + this.detailDesc, 2);
	    this.taskName = splitDetails[0].trim();
	    if (splitDetails.length > 1) {
                this.taskDetails = splitDetails[1].trim();
	    }
	}
    }
}
