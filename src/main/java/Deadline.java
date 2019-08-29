public class Deadline extends Task {
    // Initialization
    public Deadline(String name) {
	super(name);
	this.type = "D";
	this.recordTaskDetails(name);
    }
}
