public class Task {
    protected String taskName;
    protected Boolean isDone = false;

    // Initialization
    public Task(String name) {
        this.taskName = name;
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

}
