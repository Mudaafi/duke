class ToDo extends Task {
    // Initialization
    ToDo(String name) {
        super(name);
        this.type = "T";
	this.recordTaskDetails(name);
    }
}
