class CommandBlank extends Command {
    // Constructor
    public CommandBlank() {
    }

    /**
     * Executes a particular Command.
     */
    @Override
    public void execute(TaskList taskList) {
	Ui.printSeparator();
    }
}
