package models;

public abstract class TaskMain {
    protected String title;
    protected String description;
    protected String deadline;
    protected boolean isCompleted;
    protected int priority;

    public TaskMain(String title, String description, int priority, String deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.isCompleted = false;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public abstract void showDetails();

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String details() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n")
                .append(" Task Title    : ").append(title).append("\n")
                .append(" Priority      : ").append(priority == 1 ? "High" : (priority == 2 ? "Medium" : "Low")).append("\n")
                .append(" Deadline      : ").append(deadline).append("\n")
                .append(" Status        : ").append(isCompleted ? "Completed" : "Not completed").append("\n")
                .append("==============================");
        return sb.toString();
    }
}
