public class ToDo extends Task {
    // Initialization
    public ToDo(String name) {
	super(name);
	this.type = "T";
	this.recordTaskDetails(name);
    }
}
