class Event extends Task {
    // Initialization
    Event(String name) {
        super(name);
        this.type = "E";
        this.recordTaskDetails(name);
    }
}
