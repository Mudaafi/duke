class CommandNewTask extends Command {
    protected String userInput;
    protected TaskType taskType;

    // Constructor
    public CommandNewTask(String userInput) {
        this.taskType = Parser.parseTaskType(userInput);
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList) {
        Task newTask = taskList.createTask(this.taskType, this.userInput);
        taskList.addTask(newTask);
        Ui.dukeSays("I've added "
                + newTask.genTaskDesc()
                + " to your private list("
                + String.valueOf(taskList.getSize())
                + ")."
        );
    }
}
