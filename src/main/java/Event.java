public class Event extends Task {
    // Initialization
    public Event(String name) {
	super(name);
	this.type = "E";
	this.recordTaskDetails(name);
    }
}
