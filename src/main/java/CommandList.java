class CommandList extends Command {

    // Constructor
    public CommandList() {
    }

    @Override
    public void execute(TaskList taskList) {
        Ui.dukeSays("You have ("
                + String.valueOf(taskList.getSize())
                + ") Tasks in your list!"
        );
        taskList.printTasks();
        Ui.printSeparator();
    }
}
